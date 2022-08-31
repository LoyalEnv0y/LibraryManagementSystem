package libraryManagementSystem.users;

import libraryManagementSystem.account.Account;
import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.domain.Address;
import libraryManagementSystem.human.Person;

import static libraryManagementSystem.account.AccountStatus.*;

import java.time.LocalDate;
import java.util.*;

public class Member extends Person {
    private static int instanceCount = 0;
    private final Map<BookItem, Integer> leasedBooks;
    private final List<BookItem> boughtBooks;
    private final Account account;
    private String id;
    private Address address;

    public Member(String firstName, String secondName, LocalDate birthDate,
                  String gender, Address address, Account account) {
        super(firstName, secondName, birthDate, null, gender);

        instanceCount++;
        setId();

        this.address = address;
        this.account = account;
        this.leasedBooks = new HashMap<>();
        this.boughtBooks = new ArrayList<>();
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public String getId() {
        return id;
    }

    private void setId() {
        this.id = "M" + (instanceCount + 1000);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        checkAccountStatus("change address");
        this.address = address;
    }

    public Map<BookItem, Integer> getLeasedBooks() {
        return leasedBooks;
    }

    public void addLeasedBook(BookItem leasedBook, int leasedDays) {
        checkAccountStatus("lease a book");
        leasedBooks.putIfAbsent(leasedBook, leasedDays);
    }

    public void addLeasedBook(Map<BookItem, Integer> leasedBooks) {
        checkAccountStatus("lease a book");
        leasedBooks.forEach(this.leasedBooks::putIfAbsent);
    }

    public void removeLeasedBook(BookItem leasedBook) {
        leasedBooks.remove(leasedBook);
    }

    public void removeLeasedBook(Map<BookItem, Integer> leasedBooks) {
        leasedBooks.keySet().forEach(this.leasedBooks::remove);
    }

    public List<BookItem> getBoughtBooks() {
        return boughtBooks;
    }

    public void addBoughtBook(BookItem boughtBook) {
        checkAccountStatus("buy a book");

        if (!boughtBooks.contains(boughtBook)) {
            boughtBooks.add(boughtBook);
        }
    }

    public void addBoughtBook(List<BookItem> boughtBooks) {
        checkAccountStatus("buy books");

        boughtBooks.stream()
                .filter(boughtBook -> !this.boughtBooks.contains(boughtBook))
                .forEach(this.boughtBooks::add);
    }

    public void removeBoughtBook(BookItem boughtBook) {
        boughtBooks.remove(boughtBook);
    }

    public void removeBoughtBook(List<BookItem> boughtBooks) {
        this.boughtBooks.removeAll(boughtBooks);
    }

    public Account getAccount() {
        return account;
    }

    private void checkAccountStatus(String act) {
        if (account.getAccountStatus() != ACTIVE) {
            throw new IllegalStateException(
                    "\nERROR\n  Cannot " + act +
                            " while account is not active"
            );
        }
    }

    @Override
    public String toString() {
        return "Member ID: " + "'" + id + "'\n" +
                "First Name: " + "'" + super.getFirstName() + "'\n" +
                "Second Name: " + "'" + super.getSecondName() + "'\n" +
                "Account ID: " + "'" + account.getAccountNumber() + "'\n" +
                "Number of Bought Books: " + "'" + boughtBooks.size() + "'\n" +
                "Number of Leased Books: " + "'" + leasedBooks.size() + "'";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Member comparedMember)) {
            return false;
        }

        return this.id.equals(comparedMember.getId());
    }
}
