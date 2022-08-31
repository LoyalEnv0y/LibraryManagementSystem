package libraryManagementSystem.domain;

import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.book.sortBookItems.SortByName;
import libraryManagementSystem.book.sortBookItems.SortByPrice;
import libraryManagementSystem.human.Person;

import java.util.Collections;
import java.util.List;

public class LibrarySystem {
    private final Person user;
    private final Library library;

    public LibrarySystem(Person user, Library library) {
        this.user = user;
        this.library = library;
    }

    public BookItem findBookItemById(int id) {
        Collections.sort(library.getBookItems());
        return library.getBookItems().get(binarySearchBooks(library.getBookItems(), id));
    }

    public BookItem findBookItemByTitle(String title) {
        library.getBookItems().sort(new SortByName());
        return library.getBookItems().get(binarySearchBooks(library.getBookItems(), title));
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
