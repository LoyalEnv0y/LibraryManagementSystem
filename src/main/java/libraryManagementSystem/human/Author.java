package libraryManagementSystem.human;

import libraryManagementSystem.book.Book;

import java.time.LocalDate;
import java.util.ArrayList;

public class Author extends Person implements Cloneable {
    private final ArrayList<Book> books;

    public Author(String firstName, String secondName, LocalDate birthDate,
                  LocalDate deathDate, String gender) {
        super(firstName, secondName, birthDate, deathDate, gender);

        this.books = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
        }
    }

    public void addBook(ArrayList<Book> books) {
        books.stream()
                .filter(book -> !this.books.contains(book))
                .forEach(this.books::add);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Author ↴ \n").append(super.toString())
                .append("\nBooks {\n\n");

        for (Book book : books) {
            stringBuilder.append("[").append(book).append("]\n")
                    .append("\n");
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    @Override
    public Author clone() {
        try {
            return (Author) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
