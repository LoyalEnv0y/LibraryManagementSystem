package libraryManagementSystem.book;

import libraryManagementSystem.isbn.ISBN;

import javax.naming.SizeLimitExceededException;
import java.time.LocalDate;

import static libraryManagementSystem.book.BookStatus.AVAILABLE;
import static libraryManagementSystem.book.BookStatus.REFUNDED;
import static libraryManagementSystem.book.BookStatus.SOLD;

// TODO: WRITE TESTS

public class BookItem extends Book {
    private  int id;
    private static int numberOfBookItems = 0;
    private final BookFormat format;
    private BookStatus status;
    private double price;
    private LocalDate dateOfBorrow;
    private LocalDate dateOfDue;

    public BookItem(ISBN isbn, String title, String publisher, String language,
                    int numberOfPages, BookStatus status, BookFormat format,
                    double price) {

        super(isbn, title, publisher, language, numberOfPages);
        numberOfBookItems++;
        setId();
        this.status = status;
        this.format = format;
        this.price = price;
    }

    public BookItem(ISBN isbn, String title, String publisher, String language,
                    int numberOfPages, BookStatus status, BookFormat format,
                    double price, LocalDate dateOfBorrow) {

        this(isbn, title, publisher, language, numberOfPages, status, format, price);

        this.dateOfBorrow = dateOfBorrow;
        if (this.status != BookStatus.SOLD) {
            // Default due date
            dateOfDue = dateOfBorrow.plusDays(1);
        }
    }

    private void setId() {
        this.id = 1000 + numberOfBookItems;
    }

    private int getId() {
        return id;
    }

    public static int getNumberOfBookItems() {
        return numberOfBookItems;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        IllegalStateException illegalStatus = new IllegalStateException(
                "\nERROR\n  When the status is " + this.status +
                        " it cannot be set to " + status
        );

        switch (this.status) {
            // When a book is Available it cannot be set to Refunded
            case AVAILABLE -> {
                if (status == REFUNDED) throw illegalStatus;
            }
            // When a book is Sold it can ONLY be set to Refunded
            case SOLD -> {
                if (status != REFUNDED) throw illegalStatus;
            }
            // When a book is Lost it can only be set to Available (in case it is found)
            case LOST -> {
                if (status != AVAILABLE) throw illegalStatus;
            }
            // When a book is Loaned it cannot be set to Refunded or Sold
            case LOANED -> {
                if (status == REFUNDED || status == SOLD) throw illegalStatus;
            }
        }

        // Refunded state does not have any restrains
        this.status = status;
    }

    public BookFormat getFormat() {
        return format;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws SizeLimitExceededException {
        if (price < 0 || price > Integer.MAX_VALUE) {
            throw new SizeLimitExceededException(
                    "\nERROR\n  Price limit exceeded. -> " +
                            "[" + 0 + ", " + Integer.MAX_VALUE + "]"
            );
        }
        this.price = price;
    }

    public LocalDate getDateOfBorrow() {
        return dateOfBorrow;
    }

    public void setDateOfBorrow(LocalDate dateOfBorrow) {
        if (status != AVAILABLE && status != REFUNDED) {
            throw new IllegalStateException(
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
            throw new IllegalStateException(
                    "\nERROR\n  When the date of borrow is null " +
                            "setting date of due is not possible"
            );
        }

        this.dateOfDue = dateOfDue;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Format: " + "'" + format + "'\n" +
                "Status: " + "'" + status + "'\n" +
                "Price: " + "'" + price + "'\n" +
                "Date of Borrow: " + "'" + dateOfBorrow + "'\n" +
                "Date of Due: " + "'" + dateOfDue + "'";
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
}
