package libraryManagementSystem.book;

import libraryManagementSystem.customExceptions.IllegalBookStatusException;
import libraryManagementSystem.customExceptions.IllegalDateException;

import javax.naming.SizeLimitExceededException;
import java.time.LocalDate;

import static libraryManagementSystem.book.BookStatus.*;

public class BookItem extends Book implements Cloneable, Comparable<BookItem> {
    private static int instanceCount = 0;
    private final BookFormat format;
    private int id;
    private BookStatus status;
    private double price;
    private LocalDate dateOfBorrow;
    private LocalDate dateOfDue;

    public BookItem(BookISBN isbn, String title, String publisher,
                    String language, int numberOfPages,
                    BookFormat format, double price) {

        super(isbn, title, publisher, language, numberOfPages);

        this.format = format;
        this.status = AVAILABLE;
        this.price = price;

        instanceCount++;
        setId();
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    private void setId() {
        this.id = 1000 + instanceCount;
    }

    public int getId() {
        return id;
    }

    public BookFormat getFormat() {
        return format;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        IllegalBookStatusException illegalBookStatus =
                new IllegalBookStatusException(
                        "\nERROR\n  When the status is " + this.status +
                                " it cannot be set to " + status
                );

        switch (this.status) {
            // When a book is Available it cannot be set to Refunded
            case AVAILABLE -> {
                if (status == REFUNDED) throw illegalBookStatus;
            }
            // When a book is Sold it can ONLY be set to Refunded
            case SOLD -> {
                if (status != REFUNDED) throw illegalBookStatus;
            }
            // When a book is Lost it can only be set to Available (in case it is found)
            case LOST -> {
                if (status != AVAILABLE) throw illegalBookStatus;
            }
            // When a book is Loaned it cannot be set to Sold
            case LOANED -> {
                if (status == SOLD) throw illegalBookStatus;
            }
        }

        // Refunded state does not have any restrains
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws SizeLimitExceededException {
        if (price < 0) {
            throw new SizeLimitExceededException(
                    "\nERROR\n  Price cannot be negative."
            );
        }
        this.price = price;
    }

    public LocalDate getDateOfBorrow() {
        return dateOfBorrow;
    }

    public void setDateOfBorrow(LocalDate dateOfBorrow) {
        if (status == LOST || status == SOLD) {
            throw new IllegalBookStatusException(
                    "\nERROR\n  Cannot set the date of borrow " +
                            "of a book when it is " + this.status
            );
        }
        this.dateOfBorrow = dateOfBorrow;
    }

    public LocalDate getDateOfDue() {
        return dateOfDue;
    }

    public void setDateOfDue(LocalDate dateOfDue) {
        if (dateOfBorrow == null) {
            throw new IllegalDateException(
                    "\nERROR\n  When the date of borrow is null " +
                            "setting date of due is not possible"
            );
        }

        if (dateOfBorrow.isAfter(dateOfDue)) {
            throw new IllegalDateException(
                    "\nERROR\n  Date of due cannot be earlier then " +
                            "date of barrow"
            );
        }

        this.dateOfDue = dateOfDue;
    }

    @Override
    public int compareTo(BookItem o) {
        return this.id - o.id;
    }

    @Override
    public String toString() {
        return id + " " + super.toString() + " " + status + " " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof BookItem comparedBookItem)) {
            return false;
        }

        return this.getId() == comparedBookItem.getId();
    }

    @Override
    public BookItem clone() {
        return new BookItem(
                getIsbn(),
                getTitle(),
                getPublisher(),
                getLanguage(),
                getNumberOfPages(),
                getFormat(),
                getPrice()
        );
    }
}
