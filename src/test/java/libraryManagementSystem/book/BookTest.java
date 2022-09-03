package libraryManagementSystem.book;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class BookTest {
    private final Book book;
    private final BookAuthor author1;
    private final BookAuthor author2;
    private final ArrayList<String> subjects;
    private final ArrayList<BookAuthor> authors;

    public BookTest() {
        // Initialize the subjects
        this.subjects = new ArrayList<>();
        subjects.add("Horror");
        subjects.add("Adventure");
        subjects.add("Fantasy");
        subjects.add("Sci-Fi");
        subjects.add("Drama");
        subjects.add("History");
        subjects.add("Science");

        // Initialize authors
        this.author1 = new BookAuthor(
                "Test", "Author",
                LocalDate.of(1970, 6, 22),
                "Male"
        );

        // To test the duplicate author case, copy the author1 and change the name
        // It should add the author since they are different
        this.author2 = new BookAuthor(
                "Test2", "Author",
                LocalDate.of(1970, 6, 22),
                "Male"
        );

        // Initialize authors collection
        this.authors = new ArrayList<>();
        this.authors.add(author1);
        this.authors.add(author2);

        // Initialize book
        this.book = new BookItem(
                new BookISBN("978-3-7982-6276-8"),
                "Test Title",
                "Test Publisher",
                "Language",
                123,
                BookFormat.PAPERBACK,
                12.30
        );
    }

    @Test
    public void testAddSubject() {
        book.addSubject(this.subjects.get(0));
        assertEquals("HORROR", book.getSubjects().get(0));
    }

    @Test
    public void testAddMultipleSubjects() {
        ArrayList<String> subjectsToBeAdded = new ArrayList<>();
        subjectsToBeAdded.add(subjects.get(0).toUpperCase());
        subjectsToBeAdded.add(subjects.get(1).toUpperCase());

        book.addSubject(subjectsToBeAdded);
        assertIterableEquals(subjectsToBeAdded, book.getSubjects());
    }

    @Test
    public void testAddDuplicateSubject() {
        book.addSubject(subjects);
        book.addSubject(subjects.get(0));

        assertEquals(subjects.size(), book.getSubjects().size());
    }

    @Test
    public void testDeleteSubject() {
        book.addSubject(subjects);
        book.deleteSubject(subjects.get(0));

        assertEquals(subjects.size() - 1,
                book.getSubjects().size()
        );
    }

    @Test
    public void testDeleteMultipleSubjects() {
        ArrayList<String> subjectsToBeDeleted = new ArrayList<>();
        subjectsToBeDeleted.add(subjects.get(0));
        subjectsToBeDeleted.add(subjects.get(1));

        book.addSubject(subjects);
        book.deleteSubject(subjectsToBeDeleted);

        assertEquals(subjects.size() - subjectsToBeDeleted.size(),
                book.getSubjects().size()
        );
    }

    @Test
    public void testAddAuthor() {
        book.addAuthor(author1);
        String authorFullName = authors.get(0).getFirstName() + " " +
                authors.get(0).getSecondName();

        assertEquals(authorFullName, book.getAuthors().get(0));
    }

    @Test
    public void testAddMultipleAuthors() {
        book.addAuthor(authors);
        assertEquals(authors.size(), book.getAuthors().size());
    }

    @Test
    public void testDeleteAuthor() {
        book.addAuthor(authors);
        book.deleteAuthor(author1);

        assertEquals(1, book.getAuthors().size());
    }

    @Test
    public void testDeleteMultipleAuthor() {
        book.addAuthor(authors);
        book.deleteAuthor(authors);
        assertEquals(0, book.getAuthors().size());
    }

    @Test
    public void testAddDuplicateAuthor() {
        book.addAuthor(authors);
        book.addAuthor(author1);
        book.addAuthor(author2);
        assertEquals(authors.size(), book.getAuthors().size());
    }
}