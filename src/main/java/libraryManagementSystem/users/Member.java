package libraryManagementSystem.users;

import libraryManagementSystem.account.Account;
import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.domain.Address;
import libraryManagementSystem.human.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.id = "M" + instanceCount + 1000;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map<BookItem, Integer> getLeasedBooks() {
        return leasedBooks;
    }

    public void addLeasedBook(BookItem leasedBook, int leasedDays) {
        leasedBooks.putIfAbsent(leasedBook, leasedDays);
    }

    public void addLeasedBook(Map<BookItem, Integer> leasedBooks) {
        leasedBooks.forEach(this.leasedBooks::putIfAbsent);
    }

    public List<BookItem> getBoughtBooks() {
        return boughtBooks;
    }

    public void addBoughtBook(BookItem boughtBook) {
        if (!boughtBooks.contains(boughtBook)) {
            boughtBooks.add(boughtBook);
        }
    }

    public void addBoughtBook(List<BookItem> boughtBooks) {
        boughtBooks.stream()
                .filter(boughtBook -> !this.boughtBooks.contains(boughtBook))
                .forEach(this.boughtBooks::add);
    }

    public Account getAccount() {
        return account;
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
