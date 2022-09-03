package libraryManagementSystem.catalog;

import libraryManagementSystem.book.BookAuthor;
import libraryManagementSystem.book.BookISBN;
import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.customExceptions.BookDoesNotExistException;
import libraryManagementSystem.customExceptions.BookOutofStockException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static libraryManagementSystem.book.BookFormat.*;
import static libraryManagementSystem.book.BookStatus.*;
import static org.junit.jupiter.api.Assertions.*;

public class CatalogTest {
    private final BookItem bookItem1;
    private final BookItem bookItem2;
    private final BookItem bookItem3;
    private final BookItem bookItem4;
    private final BookItem bookItem5;

    private final BookAuthor author1;
    private final BookAuthor author2;
    private final BookAuthor author3;

    private final ArrayList<String> subjects;
    private final ArrayList<BookAuthor> authors;
    private final ArrayList<BookItem> bookItems;

    private final Catalog catalog;

    public CatalogTest() {
        bookItem1 = new BookItem(new BookISBN("978-3-7982-6276-8"),
                "Book-A", "A-Pub", "English",
                123, PAPERBACK, 12.30
        );
        bookItem2 = new BookItem(new BookISBN("978-7-7000-6763-2"),
                "Book-B", "A-Pub", "English",
                156, PAPERBACK, 12.50
        );
        bookItem3 = new BookItem(new BookISBN("978-7-1442-7182-4"),
                "Book-C", "A-Pub", "English",
                215, HARDCOVER, 13.50
        );
        bookItem4 = new BookItem(new BookISBN("978-8-3816-9598-5"),
                "Book-D", "A-Pub", "English",
                260, AUDIOBOOK, 11.50
        );
        bookItem5 = new BookItem(new BookISBN("978-2-3016-0100-1"),
                "Book-E", "A-Pub", "English",
                370, HARDCOVER, 9.10
        );
        author1 = new BookAuthor("Test", "Author",
                LocalDate.of(1970, 6, 22),
                "Male",
                LocalDate.of(2020, 4, 20)
        );
        author2 = new BookAuthor("Test2", "Author",
                LocalDate.of(1970, 6, 22),
                "Female",
                LocalDate.of(2020, 8, 20)
        );
        author3 = new BookAuthor("Test3", "Author",
                LocalDate.of(1970, 6, 22),
                "Female",
                LocalDate.of(2020, 8, 20)
        );

        this.bookItems = new ArrayList<>();
        bookItems.add(bookItem1);
        bookItems.add(bookItem2);
        bookItems.add(bookItem3);
        bookItems.add(bookItem4);

        this.catalog = new Catalog();
        catalog.addBookItem(bookItems);

        this.authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);
        authors.add(author3);

        this.subjects = new ArrayList<>();
        subjects.add("Horror");
        subjects.add("Adventure");
        subjects.add("Fantasy");
        subjects.add("Sci-Fi");
        subjects.add("Drama");
        subjects.add("History");
        subjects.add("Science");
    }

    @Test
    public void addExistingBookItem() {
        catalog.addBookItem(bookItem1);
        assertEquals(2, catalog.getBookItemStocks().get(bookItem1));
        assertEquals(bookItem1, catalog.getBookItemID().get(bookItem1.getId()));
    }

    @Test
    public void addNewBookItem() {
        catalog.addBookItem(bookItem5);
        assertEquals(1, catalog.getBookItemStocks().get(bookItem5));
        assertEquals(bookItem1, catalog.getBookItemID().get(bookItem1.getId()));
    }

    @Test
    public void addMultipleCopiesOfSameBookItem() {
        catalog.addBookItem(bookItem5, 10);
        assertEquals(10, catalog.getBookItemStocks().get(bookItem5));
        assertEquals(bookItem1, catalog.getBookItemID().get(bookItem1.getId()));
    }

    @Test
    public void removeBookItem() {
        catalog.removeBookItem(bookItem1);
        assertEquals(0, catalog.getBookItemStocks().get(bookItem1));
    }

    @Test
    public void removeMultipleBookItems() {
        catalog.removeBookItem(bookItems);
        assertEquals(0, catalog.getBookItemStocks().get(bookItem1));
    }

    @Test
    public void removeNonExistentBookItem() {
        BookDoesNotExistException bookDoesNotExistException = assertThrows(
                BookDoesNotExistException.class,
                () -> catalog.removeBookItem(bookItem5)
        );

        assertEquals("\nERROR\n  BookItem with the ID -> " +
                        bookItem5.getId() + " doesn't exist",
                bookDoesNotExistException.getMessage()
        );
    }

    @Test
    public void removeOutOfStockBookItem() {
        catalog.removeBookItem(bookItem1);

        BookOutofStockException bookOutofStockException = assertThrows(
                BookOutofStockException.class,
                () -> catalog.removeBookItem(bookItem1)
        );

        assertEquals("\nERROR\n  BookItem with the ID -> " +
                        bookItem1.getId() + " is out of stock",
                bookOutofStockException.getMessage()
        );
    }

    @Test
    public void deleteBookItemWithOneCopy() {
        catalog.deleteBookItem(bookItem1);
        assertNull(catalog.getBookItemStocks().get(bookItem1));
        assertNull(catalog.getBookItemID().get(bookItem1.getId()));
    }

    @Test
    public void deleteBookItemWithMultipleCopy() {
        catalog.deleteBookItem(bookItem1);
        catalog.deleteBookItem(bookItem1);

        assertNull(catalog.getBookItemStocks().get(bookItem1));
        assertNull(catalog.getBookItemID().get(bookItem1.getId()));
    }

    @Test
    public void searchBookItemById() {
        BookItem result = catalog.searchBookItemById(bookItem1.getId());
        assertEquals(bookItem1, result);
    }

    @Test
    public void searchBookItemByTitle() {
        bookItem2.setTitle("Book-A");

        ArrayList<BookItem> expected = new ArrayList<>();
        expected.add(bookItem1);
        expected.add(bookItem2);
        catalog.sortBooks(expected, "id");

        ArrayList<BookItem> result = catalog.searchBookItemByTitle("Book-A");
        catalog.sortBooks(result, "id");

        assertIterableEquals(expected, result);
    }

    @Test
    public void searchBookItemByAuthor() {
        bookItem1.addAuthor(author1);
        bookItem1.addAuthor(author2);
        bookItem2.addAuthor(author1);
        bookItem4.addAuthor(author1);
        bookItem4.addAuthor(author2);

        ArrayList<BookItem> expected = new ArrayList<>();
        expected.add(bookItem1);
        expected.add(bookItem2);
        expected.add(bookItem4);
        catalog.sortBooks(expected, "id");

        ArrayList<BookItem> result = catalog.searchBookItemsByAuthor("test", "author");
        catalog.sortBooks(result, "id");

        assertIterableEquals(expected, result);
    }

    @Test
    public void searchBookItemByStatus() {
        bookItem1.setStatus(LOANED);
        bookItem2.setStatus(LOANED);

        ArrayList<BookItem> expected = new ArrayList<>();
        expected.add(bookItem4);
        expected.add(bookItem3);
        catalog.sortBooks(expected, "id");

        ArrayList<BookItem> result = catalog.searchBookItemsByStatus(AVAILABLE);
        catalog.sortBooks(result, "id");

        assertIterableEquals(expected, result);
    }

    @Test
    public void searchBookItemByFormat() {
        ArrayList<BookItem> expected = new ArrayList<>();
        expected.add(bookItem1);
        expected.add(bookItem2);
        catalog.sortBooks(expected, "id");

        ArrayList<BookItem> result = catalog.searchBookItemsByFormat(PAPERBACK);
        catalog.sortBooks(result, "id");

        assertIterableEquals(expected, result);
    }

    @Test
    public void searchBookItemBySubject() {
        bookItem1.addSubject(subjects.get(0));
        bookItem1.addSubject(subjects.get(1));
        bookItem2.addSubject(subjects.get(0));
        bookItem2.addSubject(subjects.get(4));
        bookItem3.addSubject(subjects.get(6));
        bookItem3.addSubject(subjects.get(5));
        bookItem4.addSubject(subjects.get(0));
        bookItem4.addSubject(subjects.get(3));

        ArrayList<BookItem> expected = new ArrayList<>();
        expected.add(bookItem1);
        expected.add(bookItem2);
        expected.add(bookItem4);
        catalog.sortBooks(expected, "id");

        ArrayList<BookItem> result = catalog.searchBookItemsBySubject(subjects.get(0));
        catalog.sortBooks(result, "id");

        assertIterableEquals(expected, result);
    }

    @Test
    public void sortBookItemsById() {
        bookItems.add(bookItem5);
        ArrayList<BookItem> expected = new ArrayList<>(bookItems);

        Collections.shuffle(bookItems);
        catalog.sortBooks(bookItems, "ID");

        assertIterableEquals(expected, bookItems);
    }

    @Test
    public void sortBookItemsByTitle() {
        bookItems.add(bookItem5);
        ArrayList<BookItem> expected = new ArrayList<>(bookItems);

        Collections.shuffle(bookItems);
        catalog.sortBooks(bookItems, "tit le ");

        assertIterableEquals(expected, bookItems);
    }

    @Test
    public void sortBookItemsByPrice() {
        bookItems.add(bookItem5);

        ArrayList<BookItem> expected = new ArrayList<>();
        expected.add(bookItem3);
        expected.add(bookItem2);
        expected.add(bookItem1);
        expected.add(bookItem4);
        expected.add(bookItem5);

        Collections.shuffle(bookItems);
        catalog.sortBooks(bookItems, "PRICE\n");

        assertIterableEquals(expected, bookItems);
    }

    @Test
    public void sortBookItemsByStatus() {
        bookItems.add(bookItem5);

        bookItems.get(1).setStatus(LOANED);
        bookItems.get(2).setStatus(SOLD);
        bookItems.get(2).setStatus(REFUNDED);
        bookItems.get(3).setStatus(SOLD);
        bookItems.get(4).setStatus(LOST);

        ArrayList<BookItem> expected = new ArrayList<>(bookItems);

        Collections.shuffle(bookItems);
        catalog.sortBooks(bookItems, "\tStAtUs");

        assertIterableEquals(expected, bookItems);
    }

    @Test
    public void sortBookItemsByPageNumber() {
        bookItems.add(bookItem5);
        ArrayList<BookItem> expected = new ArrayList<>(bookItems);

        Collections.shuffle(bookItems);
        catalog.sortBooks(bookItems, "page");

        assertIterableEquals(expected, bookItems);
    }
}