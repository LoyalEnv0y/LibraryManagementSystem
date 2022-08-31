package libraryManagementSystem.domain;

import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.users.Librarian;
import libraryManagementSystem.users.Member;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private Address address;
    private final List<BookItem> bookItems;
    private final List<Member> members;
    private final List<Librarian> librarians;

    public Library(String name, Address address) {
        this.name = name;
        this.address = address;
        this.bookItems = new ArrayList<>();
        this.members = new ArrayList<>();
        this.librarians = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public void addBookItem(BookItem bookItem) {
        if (!this.bookItems.contains(bookItem)) {
            this.bookItems.add(bookItem);
        }
    }

    public void addBookItem(List<BookItem> bookItems) {
        bookItems.stream()
                .filter(bookItem -> !this.bookItems.contains(bookItem))
                .forEach(this.bookItems::add);
    }

    public void removeBookItem(BookItem bookItem) {
        this.bookItems.remove(bookItem);
    }

    public void removeBookItem(List<BookItem> bookItems) {
        this.bookItems.removeAll(bookItems);
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addMember(Member member) {
        if (!this.members.contains(member)) {
            this.members.add(member);
        }
    }

    public void addMember(List<Member> members) {
        members.stream()
                .filter(member -> !this.members.contains(member))
                .forEach(this.members::add);
    }

    public void removeMember(Member member) {
        this.members.remove(member);
    }

    public void removeMember(List<Member> members) {
        this.members.removeAll(members);
    }

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public void addLibrarian(Librarian librarian) {
        if (!this.librarians.contains(librarian)) {
            this.librarians.add(librarian);
        }
    }

    public void addLibrarian(List<Librarian> librarians) {
        librarians.stream()
                .filter(librarian -> !this.librarians.contains(librarian))
                .forEach(this.librarians::add);
    }

    public void removeLibrarian(Librarian librarian) {
        this.librarians.remove(librarian);
    }

    public void removeLibrarian(List<Librarian> librarians) {
        this.librarians.removeAll(librarians);
    }
}
