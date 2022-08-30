package libraryManagementSystem.book;

import libraryManagementSystem.human.Author;
import libraryManagementSystem.isbn.ISBN;
import libraryManagementSystem.isbn.ISBNVersion;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    private final Book book;
    private final Author author1;
    private final Author author2;
    private final ArrayList<String> subjects;
    private final ArrayList<Author> authors;

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
        this.author1 = new Author(
                "Alive", "Author",
                LocalDate.of(1970, 6, 22),
                null,
                "Male"
        );

        // To test the duplicate author case, copy the author1 and change name
        this.author2 = author1.clone();
        this.author2.setFirstName("George");

        // Initialize authors collection
        this.authors = new ArrayList<>();
        this.authors.add(author1);
        this.authors.add(author2);

        // Initialize book
        this.book = new Book(
                new ISBN("8-699118-040781", ISBNVersion.THIRTEEN_DIGIT),
                "Book A",
                "A Pub.",
                "English",
                332
        );
    }

    @Test
    public void testAddSubjectWhenItAlreadyExists() {
        book.addSubject(subjects);
        book.addSubject(subjects.get(0));

        assertEquals(subjects.size(), book.getSubjects().size());
    }

    @Test
    public void testDeleteSubject() {
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
    public void testAddAuthorWhenItAlreadyExists() {
        book.addAuthor(authors);
        book.addAuthor(author1);
        book.addAuthor(author2);

        assertEquals(authors.size(), book.getAuthors().size());
    }
}