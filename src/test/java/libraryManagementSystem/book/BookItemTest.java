package libraryManagementSystem.book;

import libraryManagementSystem.isbn.ISBN;
import libraryManagementSystem.isbn.ISBNVersion;

import org.junit.jupiter.api.Test;

import javax.naming.SizeLimitExceededException;

import java.time.LocalDate;

import static libraryManagementSystem.book.BookStatus.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookItemTest {
    private final BookItem bookItem;

    public BookItemTest() {
        bookItem = new BookItem(
                new ISBN("8-699118-040781", ISBNVersion.THIRTEEN_DIGIT),
                "Book A",
                "A Pub.",
                "English",
                332,
                BookFormat.PAPERBACK,
                14.40
        );
    }

    @Test
    public void testId() {
        BookItem bookItem2 = bookItem.clone();
        int idShouldBe = BookItem.getNumberOfBookItems() + 1000;
        assertEquals(idShouldBe, bookItem2.getId());
    }

    @Test
    public void testStatusIsSetToAvailableByDefault() {
        assertEquals(AVAILABLE, bookItem.getStatus());
    }

    // I am so sorry. I will try to write a documentation for the below test
    @Test
    public void testSetStatusChangesStatusCorrectly() {
        for (BookStatus status : BookStatus.values()) {
            for (BookStatus statusToBeSet : BookStatus.values()) {
                String currentErrorMessage = "\nERROR\n  When the status is "
                        + status + " it cannot be set to "
                        + statusToBeSet;

                BookItem testBook = bookItem.clone();
                if (status == REFUNDED) {
                    testBook.setStatus(SOLD);
                }
                testBook.setStatus(status);

                switch (status) {

                    //************Setting Status When Status is AVAILABLE************//
                    case AVAILABLE -> {
                        if (statusToBeSet == REFUNDED) {
                            IllegalStateException exception = assertThrows(
                                    IllegalStateException.class,
                                    () -> testBook.setStatus(statusToBeSet)
                            );

                            assertEquals(currentErrorMessage,
                                    exception.getMessage()
                            );

                            continue;
                        }
                    }

                    //**************Setting Status When Status is SOLD***************//
                    case SOLD -> {
                        if (statusToBeSet != REFUNDED) {
                            IllegalStateException exception = assertThrows(
                                    IllegalStateException.class,
                                    () -> testBook.setStatus(statusToBeSet)
                            );

                            assertEquals(currentErrorMessage,
                                    exception.getMessage()
                            );

                            continue;
                        }
                    }

                    //************Setting Status When Status is LOANED*************//
                    case LOANED -> {
                        if (statusToBeSet == SOLD) {
                            IllegalStateException exception = assertThrows(
                                    IllegalStateException.class,
                                    () -> testBook.setStatus(statusToBeSet)
                            );

                            assertEquals(currentErrorMessage,
                                    exception.getMessage()
                            );

                            continue;
                        }
                    }

                    //***************Setting Status When Status is LOST**************//
                    case LOST -> {
                        if (statusToBeSet != AVAILABLE) {
                            IllegalStateException exception = assertThrows(
                                    IllegalStateException.class,
                                    () -> testBook.setStatus(statusToBeSet)
                            );

                            assertEquals(currentErrorMessage,
                                    exception.getMessage()
                            );

                            continue;
                        }
                    }
                }

                System.out.println(status + " " + statusToBeSet);
                testBook.setStatus(statusToBeSet);
                assertEquals(statusToBeSet, testBook.getStatus());
            }
        }
    }

    @Test
    public void testSetPriceWithPriceTooLow() {
        SizeLimitExceededException exception = assertThrows(
                SizeLimitExceededException.class,
                () -> bookItem.setPrice(-1)
        );

        assertEquals("\nERROR\n  Price cannot be negative.",
                exception.getMessage()
        );
    }

    @Test
    public void testSetDateOfBorrowWithValidStatus() {
        LocalDate dateToBeSet = LocalDate.of(2022, 10, 4);

        bookItem.setDateOfBorrow(dateToBeSet);
        assertEquals(dateToBeSet, bookItem.getDateOfBorrow());
    }

    @Test
    public void testSetDateOfBorrowWithInvalidStatuses() {
        LocalDate dateToBeSet = LocalDate.of(2022, 10, 4);
        BookStatus[] unavailableStatuses = new BookStatus[]{LOST, SOLD};

        for (BookStatus status : unavailableStatuses) {
            if (bookItem.getStatus() == LOST) {
                bookItem.setStatus(AVAILABLE);
            }
            bookItem.setStatus(status);

            IllegalStateException stateException = assertThrows(
                    IllegalStateException.class,
                    () -> bookItem.setDateOfBorrow(dateToBeSet)
            );

            assertEquals("\nERROR\n  Cannot set the date of borrow " +
                            "of a book when it is " + bookItem.getStatus(),
                    stateException.getMessage()
            );
        }
    }

    @Test
    public void testSetDueDate() {
        LocalDate borrowDate = LocalDate.of(2022, 10, 4);
        LocalDate dueDate = borrowDate.plusDays(3);
        bookItem.setDateOfBorrow(borrowDate);
        bookItem.setDateOfDue(dueDate);

        assertEquals(dueDate, bookItem.getDateOfDue());
    }

    @Test
    public void testSetDueDateWhenBorrowDateNull() {
        LocalDate dueDate = LocalDate.of(2022, 10, 4);

        IllegalStateException stateException = assertThrows(
                IllegalStateException.class,
                () -> bookItem.setDateOfDue(dueDate)
        );

        assertEquals("\nERROR\n  When the date of borrow is null " +
                        "setting date of due is not possible",
                stateException.getMessage()
        );
    }

    @Test
    public void testSetDueDateWhenDueDateIsEarlierThenBorrow() {
        LocalDate borrowDate = LocalDate.of(2022, 10, 4);
        LocalDate dueDate = borrowDate.minusDays(3);
        bookItem.setDateOfBorrow(borrowDate);

        IllegalArgumentException stateException = assertThrows(
                IllegalArgumentException.class,
                () -> bookItem.setDateOfDue(dueDate)
        );

        assertEquals("\nERROR\n  Date of due cannot be earlier then " +
                        "date of barrow",
                stateException.getMessage()
        );
    }
}
