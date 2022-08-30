package libraryManagementSystem.human;

import libraryManagementSystem.book.Book;
import libraryManagementSystem.isbn.ISBN;
import libraryManagementSystem.isbn.ISBNVersion;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {
    private final Author author;
    private final Book book1;
    private final Book book2;

    public AuthorTest() {
        this.author = new Author("Alive", "Author",
                LocalDate.of(1970, 6, 22),
                null,
                "Male");

        this.book1 = new Book(
                new ISBN("8-699118-040781", ISBNVersion.THIRTEEN_DIGIT),
                "A",
                "A-PUB.",
                "English",
                221
        );

        this.book2 = new Book(
                new ISBN("978-0-306-40615-7", ISBNVersion.THIRTEEN_DIGIT),
                "B",
                "B-PUB.",
                "French",
                524
        );
    }

    @Test
    public void testAddBookWhereBookAlreadyExists() {
        author.addBook(book1);
        author.addBook(book1);

        assertEquals(1, author.getBooks().size());
    }

    @Test
    public void testAddBookWhereBooksAlreadyExists() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        author.addBook(books);
        author.addBook(books);

        assertEquals(2, author.getBooks().size());
        System.out.println(author);
    }
}