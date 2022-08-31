package libraryManagementSystem.library;

import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.book.SearchBook;
import libraryManagementSystem.users.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibrarySystemMember extends SearchBook {
    private final Member user;

    public LibrarySystemMember(Member user, Library library) {
        super(library);
        this.user = user;
    }

    public Member getUser() {
        return user;
    }

    public void leaseBook(BookItem bookItem, int leasedDays) {
        user.addLeasedBook(bookItem, leasedDays);
    }

    public void leaseBook(Map<BookItem, Integer> bookItems) {
        user.addLeasedBook(bookItems);
    }

    public void returnBook(BookItem bookItem) {
        user.removeLeasedBook(bookItem);
    }

    public void returnBook(HashMap<BookItem, Integer> bookItems) {
        user.removeLeasedBook(bookItems);
    }

    public void buyBook(BookItem bookItem) {
        user.addBoughtBook(bookItem);
    }

    public void buyBook(List<BookItem> bookItems) {
        user.addBoughtBook(bookItems);
    }

    public void refundBook(BookItem bookItem) {
        user.removeBoughtBook(bookItem);
    }

    public void refundBook(List<BookItem> bookItems) {
        user.removeBoughtBook(bookItems);
    }

    public void updateAccount(String field, String value) {
        String formattedField = field.replace(" ", "")
                .toLowerCase().strip();

        switch (formattedField) {
            case "username" -> user.getAccount().setUserName(value);
            case "password" -> user.getAccount().setPassword(value);
        }
    }

    public void updateAddress(Address newAddress) {
        user.setAddress(newAddress);
    }
}
