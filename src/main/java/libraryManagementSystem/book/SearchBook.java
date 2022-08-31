package libraryManagementSystem.book;

import libraryManagementSystem.book.sortBookItems.*;
import libraryManagementSystem.library.Library;

import java.util.Collections;
import java.util.List;

public abstract class SearchBook {
    private final Library library;

    public SearchBook(Library library) {
        this.library = library;
    }

    public Library getLibrary() {
        return library;
    }

    public BookItem searchBookItemById(int id) {
        sortBooks();
        return library.getBookItems().get(binarySearchBooks(library.getBookItems(), id));
    }

    public BookItem searchBookItemByTitle(String title) {
        sortBooks("title");
        return library.getBookItems().get(binarySearchBooks(library.getBookItems(), title));
    }

    public List<BookItem> searchBookItemsByAuthor(String firstName, String secondName) {
        String capitalizedFirstName = firstName.substring(0, 1)
                .toUpperCase() + firstName.substring(1);
        String capitalizedSecondName = secondName.substring(0, 1)
                .toUpperCase() + secondName.substring(1);

        return library.getBookItems().stream()
                .filter(bookItem -> bookItem.getAuthors()
                        .contains(capitalizedFirstName + " " + capitalizedSecondName))
                .toList();
    }

    public List<BookItem> searchBookItemsByStatus(BookStatus status) {
        return library.getBookItems().stream()
                .filter(bookItem -> bookItem.getStatus() == status)
                .toList();
    }

    public List<BookItem> searchBookItemsByFormat(BookFormat format) {
        return library.getBookItems().stream()
                .filter(bookItem -> bookItem.getFormat() == format)
                .toList();
    }

    public List<BookItem> searchBookItemsBySubject(String subject) {
        String formattedSubject = subject.toUpperCase().strip();

        return library.getBookItems().stream()
                .filter(bookItem -> bookItem.getSubjects()
                        .contains(formattedSubject)).toList();
    }

    public void sortBooks() {
        Collections.sort(library.getBookItems());
    }

    public void sortBooks(String field) {
        String formattedField = field.replace(" ", "")
                .toLowerCase().strip();

        switch (formattedField) {
            case "id" -> sortBooks();
            case "title", "name" -> library.getBookItems().sort(new SortByName());
            case "price" -> library.getBookItems().sort(new SortByPrice());
            case "status" -> library.getBookItems().sort(new SortByStatus());
            case "format", "type" -> library.getBookItems().sort(new SortByFormat());
            case "page", "pagenumber" -> library.getBookItems().sort(new SortByPage());
        }
    }

    private int binarySearchBooks(List<BookItem> list, int ID) {
        int low = 0;
        int high = list.size();

        while (low <= high) {
            int mid = (low + high) / 2;

            if (list.get(mid).getId() == ID) {
                return mid;
            } else if (list.get(mid).getId() > ID) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return -1;
    }

    private int binarySearchBooks(List<BookItem> list, String title) {
        int low = 0;
        int high = list.size();

        while (low <= high) {
            int mid = (low + high) / 2;

            if (list.get(mid).getTitle().equals(title)) {
                return mid;
            } else if (list.get(mid).getTitle().compareTo(title) > 0) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return -1;
    }
}
