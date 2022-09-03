package libraryManagementSystem.library;

import libraryManagementSystem.book.BookISBN;
import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.catalog.Catalog;
import libraryManagementSystem.customExceptions.AccountNotActiveException;
import libraryManagementSystem.customExceptions.BookOutofStockException;
import libraryManagementSystem.users.Member;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static libraryManagementSystem.account.AccountStatus.*;
import static libraryManagementSystem.book.BookFormat.PAPERBACK;
import static org.junit.jupiter.api.Assertions.*;

public class TestCustomerBooksManager {
    private final CustomerBooksManager customerBooksManager;
    private final BookItem bookItem1;
    private final BookItem bookItem2;
    private final Member member;

    public TestCustomerBooksManager() {
        bookItem1 = new BookItem(new BookISBN("978-3-7982-6276-8"),
                "Book-A", "A-Pub", "English",
                123, PAPERBACK, 12.30
        );
        bookItem2 = new BookItem(new BookISBN("978-7-7000-6763-2"),
                "Book-B", "A-Pub", "English",
                156, PAPERBACK, 12.50
        );


        Catalog catalog = new Catalog();
        catalog.addBookItem(bookItem1);
        catalog.addBookItem(bookItem2);

        this.member = new Member(
                "example", "member",
                LocalDate.of(2000, 6, 22),
                "Male", new Address(
                "Test Street", "Test City", "Test State",
                "Test zipcode", "Test Country")
        );
        member.getAccount().setAccountStatus(ACTIVE, false);

        this.customerBooksManager = new CustomerBooksManager(member, catalog);
    }

    @Test
    public void addLeasedBook() {
        customerBooksManager.addLeasedBook(bookItem1, 4);
        assertTrue(member.getLeasedBooks().containsKey(bookItem1));
    }

    @Test
    public void addMultipleLeasedBooks() {
        Map<BookItem, Integer> leasedBooks = new HashMap<>();
        leasedBooks.put(bookItem1, 4);
        leasedBooks.put(bookItem2, 5);

        customerBooksManager.addLeasedBook(leasedBooks);

        assertIterableEquals(leasedBooks.keySet(),
                member.getLeasedBooks().keySet()
        );
    }

    @Test
    public void addLeasedBookWhileAccountNotActive() {
        member.getAccount().setAccountStatus(FROZEN, false);

        AccountNotActiveException accountNotActive = assertThrows(
                AccountNotActiveException.class,
                () -> customerBooksManager.addLeasedBook(bookItem1, 4)
        );

        assertEquals("\nERROR\n  Cannot " +
                        "lease book while account is not active",
                accountNotActive.getMessage()
        );
    }

    @Test
    public void addLeasedOutOfStockBook() {
        customerBooksManager.addLeasedBook(bookItem1, 4);

        BookOutofStockException bookOutofStock = assertThrows(
                BookOutofStockException.class,
                () -> customerBooksManager.addLeasedBook(bookItem1, 4)
        );

        assertEquals("\nERROR\n  " +
                        bookItem1.getTitle() + " is out of stock",
                bookOutofStock.getMessage()
        );
    }

    @Test
    public void addLeasedBookWithIllegalDays() {
        IllegalArgumentException illegalArgument = assertThrows(
                IllegalArgumentException.class,
                () -> customerBooksManager.addLeasedBook(bookItem1, -1)
        );
        IllegalArgumentException illegalArgument2 = assertThrows(
                IllegalArgumentException.class,
                () -> customerBooksManager.addLeasedBook(bookItem1, 12)
        );

        String errorMessage = "\nERROR\n  Leased days must be bigger " +
                "then 0 and lower then 11";

        assertEquals(errorMessage, illegalArgument.getMessage());
        assertEquals(errorMessage, illegalArgument2.getMessage());
    }

    @Test
    public void removeLeasedBook() {
        customerBooksManager.addLeasedBook(bookItem1, 2);
        customerBooksManager.removeLeasedBook(bookItem1);

        assertFalse(member.getLeasedBooks().containsKey(bookItem1));
    }

    @Test
    public void removeMultipleLeasedBooks() {
        List<BookItem> leasedBooks = new ArrayList<>();
        leasedBooks.add(bookItem1);
        leasedBooks.add(bookItem2);

        customerBooksManager.addLeasedBook(bookItem1, 2);
        customerBooksManager.addLeasedBook(bookItem2, 5);

        customerBooksManager.removeLeasedBook(leasedBooks);
        assertEquals(0, member.getLeasedBooks().size());
    }

    @Test
    public void removeMultipleLeasedBooksWithMap() {
        Map<BookItem, Integer> leasedBooks = new HashMap<>();
        leasedBooks.put(bookItem1, 4);
        leasedBooks.put(bookItem2, 5);

        customerBooksManager.addLeasedBook(leasedBooks);

        customerBooksManager.removeLeasedBook(leasedBooks);
        assertEquals(0, member.getLeasedBooks().size());
    }

    @Test
    public void addBoughtBook() {
        customerBooksManager.addBoughtBook(bookItem1);
        assertTrue(member.getBoughtBooks().contains(bookItem1));
    }

    @Test
    public void addMultipleBoughtBooks() {
        List<BookItem> boughtBook = new ArrayList<>();
        boughtBook.add(bookItem1);
        boughtBook.add(bookItem2);

        customerBooksManager.addBoughtBook(boughtBook);

        assertIterableEquals(boughtBook, member.getBoughtBooks());
    }

    @Test
    public void addBoughtBookWhileAccountNotActive() {
        member.getAccount().setAccountStatus(FROZEN, false);

        AccountNotActiveException accountNotActive = assertThrows(
                AccountNotActiveException.class,
                () -> customerBooksManager.addBoughtBook(bookItem1)
        );

        assertEquals("\nERROR\n  Cannot " +
                        "buy book while account is not active",
                accountNotActive.getMessage()
        );
    }

    @Test
    public void addBoughtOutOfStockBook() {
        customerBooksManager.addBoughtBook(bookItem1);

        BookOutofStockException bookOutofStock = assertThrows(
                BookOutofStockException.class,
                () -> customerBooksManager.addBoughtBook(bookItem1)
        );

        assertEquals("\nERROR\n  " +
                        bookItem1.getTitle() + " is out of stock",
                bookOutofStock.getMessage()
        );
    }

    @Test
    public void removeBoughtBook() {
        customerBooksManager.addBoughtBook(bookItem1);
        customerBooksManager.removeBoughtBook(bookItem1);

        assertFalse(member.getBoughtBooks().contains(bookItem1));
    }

    @Test
    public void removeMultipleBoughtBooks() {
        List<BookItem> boughtBook = new ArrayList<>();
        boughtBook.add(bookItem1);
        boughtBook.add(bookItem2);

        customerBooksManager.addBoughtBook(bookItem1);
        customerBooksManager.addBoughtBook(bookItem2);

        customerBooksManager.removeBoughtBook(boughtBook);
        assertEquals(0, member.getBoughtBooks().size());
    }
}