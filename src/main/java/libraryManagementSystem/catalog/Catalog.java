package libraryManagementSystem.catalog;

import libraryManagementSystem.book.BookFormat;
import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.book.BookStatus;
import libraryManagementSystem.catalog.sortBookItems.SortByName;
import libraryManagementSystem.catalog.sortBookItems.SortByPage;
import libraryManagementSystem.catalog.sortBookItems.SortByPrice;
import libraryManagementSystem.catalog.sortBookItems.SortByStatus;
import libraryManagementSystem.customExceptions.BookDoesNotExistException;
import libraryManagementSystem.customExceptions.BookOutofStockException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Catalog {
    private final HashMap<BookItem, Integer> bookItemStocks;
    private final HashMap<Integer, BookItem> bookItemID;

    public Catalog() {
        this.bookItemStocks = new HashMap<>();
        this.bookItemID = new HashMap<>();
    }

    public HashMap<BookItem, Integer> getBookItemStocks() {
        return bookItemStocks;
    }

    public HashMap<Integer, BookItem> getBookItemID() {
        return bookItemID;
    }

    //***************ADDING, REMOVING AND DELETING BOOK ITEMS***************//
    public void addBookItem(BookItem bookItem, int count) {
        if (count < 1) {
            throw new IllegalArgumentException(
                    "\nERROR\n  Cannot add negative or zero books to catalog"
            );
        }

        bookItem.getIsbn().verifyIsbn();

        bookItemStocks.put(bookItem,
                bookItemStocks.getOrDefault(bookItem, 0) + count
        );

        bookItemID.putIfAbsent(bookItem.getId(), bookItem);
    }

    public void addBookItem(BookItem bookItem) {
        addBookItem(bookItem, 1);
    }

    public void addBookItem(List<BookItem> bookItems) {
        bookItems.forEach(this::addBookItem);
    }

    public void removeBookItem(BookItem bookItem) {
        if (bookItemStocks.get(bookItem) == null) {
            throw new BookDoesNotExistException(
                    "\nERROR\n  BookItem with the ID -> " + bookItem.getId() +
                            " doesn't exist"
            );
        }

        if (bookItemStocks.get(bookItem) == 0) {
            throw new BookOutofStockException(
                    "\nERROR\n  BookItem with the ID -> " + bookItem.getId() +
                            " is out of stock"
            );
        }

        bookItem.getIsbn().verifyIsbn();

        bookItemStocks.put(bookItem, bookItemStocks.get(bookItem) - 1);
    }

    public void removeBookItem(List<BookItem> bookItems) {
        bookItems.forEach(this::removeBookItem);
    }

    public void deleteBookItem(BookItem bookItem) {
        bookItemStocks.remove(bookItem);
        bookItemID.remove(bookItem.getId());
    }

    //*************************SEARCHING BOOK ITEMS*************************//
    public BookItem searchBookItemById(int id) {
        return bookItemID.get(id);
    }

    public ArrayList<BookItem> searchBookItemByTitle(String title) {
        return new ArrayList<>(bookItemStocks.keySet().stream()
                .filter(bookItem -> bookItem.getTitle().equals(title)).toList());
    }

    public ArrayList<BookItem> searchBookItemsByAuthor(String firstName, String secondName) {
        String capitalizedFirstName = firstName.substring(0, 1)
                .toUpperCase() + firstName.substring(1);
        String capitalizedSecondName = secondName.substring(0, 1)
                .toUpperCase() + secondName.substring(1);

        return new ArrayList<>(bookItemStocks.keySet().stream()
                .filter(bookItem -> bookItem.getAuthors()
                        .contains(capitalizedFirstName + " " + capitalizedSecondName))
                .toList());
    }

    public ArrayList<BookItem> searchBookItemsByStatus(BookStatus status) {
        return new ArrayList<>(bookItemStocks.keySet().stream()
                .filter(bookItem -> bookItem.getStatus() == status)
                .toList());
    }

    public ArrayList<BookItem> searchBookItemsByFormat(BookFormat format) {
        return new ArrayList<>(bookItemStocks.keySet().stream()
                .filter(bookItem -> bookItem.getFormat() == format)
                .toList());
    }

    public ArrayList<BookItem> searchBookItemsBySubject(String subject) {
        String formattedSubject = subject.toUpperCase().strip();

        return new ArrayList<>(bookItemStocks.keySet().stream()
                .filter(bookItem -> bookItem.getSubjects()
                        .contains(formattedSubject)).toList());
    }

    public boolean isBookAvailable(BookItem bookItem) {
        return bookItemStocks.getOrDefault(bookItem, 0) != 0;
    }

    //**************************SORTING BOOK ITEMS**************************//
    public void sortBooks(List<BookItem> bookItems, String field) {
        String formattedField = field.replace(" ", "")
                .toLowerCase().strip();

        switch (formattedField) {
            case "title", "name" -> bookItems.sort(new SortByName());
            case "price", "pricetag" -> bookItems.sort(new SortByPrice());
            case "status", "availability" -> bookItems.sort(new SortByStatus());
            case "page", "pagenumber" -> bookItems.sort(new SortByPage());
            default -> Collections.sort(bookItems);
        }
    }
}
