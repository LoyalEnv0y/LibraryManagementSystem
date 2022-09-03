package libraryManagementSystem.library;

import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.catalog.Catalog;
import libraryManagementSystem.customExceptions.AccountNotActiveException;
import libraryManagementSystem.customExceptions.BookOutofStockException;
import libraryManagementSystem.users.Member;

import java.util.List;
import java.util.Map;

import static libraryManagementSystem.account.AccountStatus.ACTIVE;

public class CustomerBooksManager {
    private final Catalog catalog;
    private final Member member;

    public CustomerBooksManager(Member member, Catalog catalog) {
        this.member = member;
        this.catalog = catalog;
    }

    public Catalog getLibrary() {
        return catalog;
    }

    public Member getMember() {
        return member;
    }

    public void addLeasedBook(BookItem leasedBook, int leasedDays) {
        if (!isAccountActive()) {
            throw new AccountNotActiveException("\nERROR\n  Cannot " +
                    "lease book while account is not active");
        }

        if (!catalog.isBookAvailable(leasedBook)) {
            throw new BookOutofStockException("\nERROR\n  " +
                    leasedBook.getTitle() + " is out of stock");
        }

        if (leasedDays < 0 || leasedDays > 10) {
            throw new IllegalArgumentException("\nERROR\n  " +
                    "Leased days must be bigger then 0 and lower then 11"
            );
        }

        member.getLeasedBooks().putIfAbsent(leasedBook, leasedDays);
        catalog.removeBookItem(leasedBook);
    }

    public void addLeasedBook(Map<BookItem, Integer> leasedBooks) {
        leasedBooks.forEach(this::addLeasedBook);
    }

    public void removeLeasedBook(BookItem leasedBook) {
        member.getLeasedBooks().remove(leasedBook);
        catalog.addBookItem(leasedBook);
    }

    public void removeLeasedBook(List<BookItem> leasedBooks) {
        leasedBooks.forEach(this::removeLeasedBook);
    }

    public void removeLeasedBook(Map<BookItem, Integer> leasedBooks) {
        leasedBooks.keySet().forEach(this::removeLeasedBook);
    }

    public void addBoughtBook(BookItem boughtBook) {
        if (!isAccountActive()) {
            throw new AccountNotActiveException("\nERROR\n  Cannot " +
                    "buy book while account is not active");
        }

        if (!catalog.isBookAvailable(boughtBook)) {
            throw new BookOutofStockException("\nERROR\n  " +
                    boughtBook.getTitle() + " is out of stock");
        }

        member.getBoughtBooks().add(boughtBook);
        catalog.removeBookItem(boughtBook);
    }

    public void addBoughtBook(List<BookItem> boughtBooks) {
        boughtBooks.forEach(this::addBoughtBook);
    }

    public void removeBoughtBook(BookItem boughtBook) {
        member.getBoughtBooks().remove(boughtBook);
        catalog.addBookItem(boughtBook);
    }

    public void removeBoughtBook(List<BookItem> boughtBooks) {
        boughtBooks.forEach(this::removeBoughtBook);
    }

    private boolean isAccountActive() {
        return member.getAccount().getAccountStatus() == ACTIVE;
    }
}
