package libraryManagementSystem.book;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookAuthorTest {
    /**
     * "Bud Period" is a term that I came up with to indicate the current date
     * is before the birthdate.
     * <p>
     * For example: If I were born in 2000.01.20 and the current date is
     * 2002.01.10 then I would be 1 years old not 2. From the start of the
     * current year to the birthdate is what I call "Bud Period"
     */
    private final BookAuthor author;
    private final BookAuthor authorWithBudPeriod;
    private final Book book1;
    private final Book book2;

    public BookAuthorTest() {
        this.authorWithBudPeriod = new BookAuthor("Test", "Author",
                LocalDate.of(1970, 6, 22),
                "Male",
                LocalDate.of(2020, 4, 20)
        );

        this.author = new BookAuthor("Test",
                "Author with bud period",
                LocalDate.of(1970, 6, 22),
                "Male",
                LocalDate.of(2020, 8, 20)
        );

        this.book1 = new BookItem(
                new BookISBN("978-3-7982-6276-8"),
                "Test Title",
                "Test Publisher",
                "Language",
                123,
                BookFormat.PAPERBACK,
                12.30
        );

        this.book2 = new BookItem(
                new BookISBN("978-7-7000-6763-2"),
                "Test Title2",
                "Test Publisher2",
                "Language",
                123,
                BookFormat.PAPERBACK,
                12.40
        );
    }

    @Test
    public void testAddBook() {
        author.addBook(book1);
        assertEquals(1, author.getBooks().size());
    }

    @Test
    public void testAddMultipleBooks() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        author.addBook(books);
        assertEquals(2, author.getBooks().size());
    }

    @Test
    public void testAddDuplicateBook() {
        author.addBook(book1);
        author.addBook(book1);

        assertEquals(1, author.getBooks().size());
    }

    @Test
    public void testSetDeathDateForDeadAuthor() {
        IllegalStateException stateException = assertThrows(
                IllegalStateException.class,
                () -> author.setDeathDate(LocalDate.now())
        );

        assertEquals("\nERROR\n  Author is already dead",
                stateException.getMessage()
        );
    }

    @Test
    public void updateAgeForDeadAuthorWithBudPeriod() {
        assertEquals(49, authorWithBudPeriod.getAge());
    }

    @Test
    public void updateAgeForDeadAuthorWithoutBudPeriod() {
        assertEquals(50, author.getAge());
    }
}