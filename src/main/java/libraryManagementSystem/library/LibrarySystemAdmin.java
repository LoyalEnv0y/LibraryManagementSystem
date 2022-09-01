package libraryManagementSystem.library;

import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.book.SearchBook;
import libraryManagementSystem.users.Librarian;
import libraryManagementSystem.users.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibrarySystemAdmin extends SearchBook {
    private final Librarian librarian;

    public LibrarySystemAdmin(Library library, Librarian librarian) {
        super(library);
        this.librarian = librarian;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void addBookItem(BookItem bookItem) {
        super.getLibrary().addBookItem(bookItem);
    }

    public void addBookItem(List<BookItem> bookItems) {
        super.getLibrary().addBookItem(bookItems);
    }

    public void removeBookItem(BookItem bookItem) {
        super.getLibrary().removeBookItem(bookItem);
    }

    public void removeBookItem(List<BookItem> bookItems) {
        super.getLibrary().removeBookItem(bookItems);
    }

    public void issueBook(Member member, BookItem bookItem, int leasedDays) {
        member.addLeasedBook(bookItem, leasedDays);
    }

    public void issueBook(Member member, Map<BookItem, Integer> leasedBooks) {
        member.addLeasedBook(leasedBooks);
    }

    public void returnBook(Member member, BookItem bookItem) {
        member.removeLeasedBook(bookItem);
    }

    public void returnBook(Member member, HashMap<BookItem, Integer> bookItems) {
        member.removeLeasedBook(bookItems);
    }

    public void sellBook(Member member, BookItem bookItem) {
        member.addBoughtBook(bookItem);
    }

    public void sellBook(Member member, List<BookItem> boughtBooks) {
        member.addBoughtBook(boughtBooks);
    }

    public void takeRefund(Member member, BookItem bookItem) {
        member.removeBoughtBook(bookItem);
    }

    public void takeRefund(Member member, List<BookItem> bookItems) {
        member.removeBoughtBook(bookItems);
    }
}
