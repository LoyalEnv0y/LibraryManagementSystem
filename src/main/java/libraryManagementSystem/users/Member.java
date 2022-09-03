package libraryManagementSystem.users;

import libraryManagementSystem.account.Account;
import libraryManagementSystem.account.AccountType;
import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.library.Address;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Member extends Person implements User {
    private static int instanceCount = 0;
    private final HashMap<BookItem, Integer> leasedBooks;
    private final ArrayList<BookItem> boughtBooks;
    private final Account account;
    private final AccountType accountType = AccountType.MEMBER;
    private String id;
    private Address address;

    public Member(String firstName, String secondName,
                  LocalDate birthDate, String gender,
                  Address address) {

        super(firstName, secondName, birthDate, gender);

        this.leasedBooks = new HashMap<>();
        this.boughtBooks = new ArrayList<>();
        updateAge();
        this.address = address;

        instanceCount++;
        setId();
        this.account = new Account(this.id);
    }

    public HashMap<BookItem, Integer> getLeasedBooks() {
        return leasedBooks;
    }

    public ArrayList<BookItem> getBoughtBooks() {
        return boughtBooks;
    }

    public Account getAccount() {
        return account;
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
        this.address = address;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Member ID: " + id + "\n" +
                "Account ID: " + account.getAccountId() + "\n" +
                "Number of Bought Books: " + boughtBooks.size() + "\n" +
                "Number of Leased Books: " + leasedBooks.size();
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
