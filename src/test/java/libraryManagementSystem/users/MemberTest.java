package libraryManagementSystem.users;

import libraryManagementSystem.account.Account;
import libraryManagementSystem.book.BookFormat;
import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.domain.Address;
import libraryManagementSystem.isbn.ISBN;
import libraryManagementSystem.isbn.ISBNVersion;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static libraryManagementSystem.account.AccountType.MEMBER;
import static libraryManagementSystem.account.AccountStatus.*;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
    private final BookItem leasedBook1;
    private final BookItem leasedBook2;
    private final BookItem boughtBook1;
    private final BookItem boughtBook2;
    private final Address address2;
    private final Member member;

    public MemberTest() {
        Account account = new Account(
                "exampleUser", "123123", "M123",
                MEMBER
        );

        leasedBook1 = new BookItem(
                new ISBN("8-699118-040781", ISBNVersion.THIRTEEN_DIGIT),
                "Book A", "A Pub.", "English",
                332, BookFormat.PAPERBACK, 14.40
        );

        leasedBook2 = leasedBook1.clone();
        leasedBook2.setTitle("Book B");

        boughtBook1 = leasedBook1.clone();
        boughtBook1.setTitle("Book C");

        boughtBook2 = boughtBook1.clone();
        boughtBook2.setTitle("Book D");

        Address address = new Address(
                "Example Street", "Example City",
                "Example State", "12344", "Example Country"
        );

        address2 = new Address(
                "new Address", "new City",
                "new State", "12344", "new Country"
        );

        member = new Member(
                "Will", "Smith",
                LocalDate.of(1990, 6, 22),
                "Male", address, account
        );
    }

    @Test
    public void testId() {
        int instanceCount = Member.getInstanceCount();
        assertEquals("M" + (instanceCount + 1000), member.getId());
    }

    @Test
    public void testSetAddress() {
        member.setAddress(address2);
        assertEquals("new Address", member.getAddress().getStreetAddress());
    }

    @Test
    public void testSetAddressWhenAccountStatusNotValid() {
        member.getAccount().setAccountStatus(FROZEN);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> member.setAddress(address2)
        );

        assertEquals("\nERROR\n  Cannot change address while " +
                "account is not active", exception.getMessage()
        );
    }

    @Test
    public void testAddLeasedBook() {
        Map<BookItem, Integer> leasedBooks = new HashMap<>();
        leasedBooks.put(leasedBook1, 3);
        leasedBooks.put(leasedBook2, 5);

        member.addLeasedBook(leasedBooks);

        assertTrue(member.getLeasedBooks().containsKey(leasedBook1));
        assertEquals(3, member.getLeasedBooks().get(leasedBook1));
    }

    @Test
    public void testAddLeasedBookWithDuplicateBook() {
        member.addLeasedBook(leasedBook1, 3);
        member.addLeasedBook(leasedBook1, 4);

        assertEquals(3, member.getLeasedBooks().get(leasedBook1));
    }

    @Test
    public void testAddLeasedBookWithInvalidAccountStatus() {
        member.getAccount().setAccountStatus(BANNED);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> member.addLeasedBook(leasedBook1, 3)
        );

        assertEquals("\nERROR\n  Cannot lease a book while " +
                "account is not active", exception.getMessage()
        );
    }

    @Test
    public void testRemoveLeasedBook() {
        member.addLeasedBook(leasedBook1, 5);
        member.removeLeasedBook(leasedBook1);

        assertFalse(member.getLeasedBooks().containsKey(leasedBook1));
    }

    @Test
    public void testRemoveMultipleLeasedBooks() {
        Map<BookItem, Integer> leasedBooks = new HashMap<>();
        leasedBooks.put(leasedBook1, 3);
        leasedBooks.put(leasedBook2, 5);

        member.addLeasedBook(leasedBooks);
        member.removeLeasedBook(leasedBooks);

        assertNull(member.getLeasedBooks().get(leasedBook1));
        assertNull(member.getLeasedBooks().get(leasedBook2));
    }

    @Test
    public void testAddBoughtBook() {
        List<BookItem> boughtBooks = new ArrayList<>();
        boughtBooks.add(boughtBook1);
        boughtBooks.add(boughtBook2);

        member.addBoughtBook(boughtBooks);

        assertTrue(member.getBoughtBooks().contains(boughtBook1));
        assertTrue(member.getBoughtBooks().contains(boughtBook2));
    }

    @Test
    public void testAddBoughtBookWithDuplicateBook() {
        member.addBoughtBook(boughtBook1);
        member.addBoughtBook(boughtBook1);

        assertEquals(1, member.getBoughtBooks().size());
    }

    @Test
    public void testAddBoughtBookWithInvalidAccountStatus() {
        member.getAccount().setAccountStatus(FROZEN);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> member.addBoughtBook(boughtBook1)
        );

        assertEquals("\nERROR\n  Cannot buy a book while " +
                "account is not active", exception.getMessage()
        );
    }

    @Test
    public void testAddMultipleBoughtBooksWithInvalidAccountStatus() {
        member.getAccount().setAccountStatus(FROZEN);

        List<BookItem> boughtBooks = new ArrayList<>();
        boughtBooks.add(boughtBook1);
        boughtBooks.add(boughtBook2);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> member.addBoughtBook(boughtBooks)
        );

        assertEquals("\nERROR\n  Cannot buy books while " +
                "account is not active", exception.getMessage()
        );
    }

    @Test
    public void testRemoveBoughtBook() {
        member.addBoughtBook(boughtBook1);
        member.removeBoughtBook(boughtBook1);

        assertFalse(member.getBoughtBooks().contains(boughtBook1));
    }

    @Test
    public void testRemoveMultipleBoughtBooks() {
        List<BookItem> boughtBooks = new ArrayList<>();
        boughtBooks.add(boughtBook1);
        boughtBooks.add(boughtBook2);

        member.addBoughtBook(boughtBooks);
        member.removeBoughtBook(boughtBooks);

        assertFalse(member.getBoughtBooks().contains(boughtBook1));
        assertFalse(member.getBoughtBooks().contains(boughtBook2));
    }
}