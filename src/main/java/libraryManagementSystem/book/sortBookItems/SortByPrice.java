package libraryManagementSystem.book.sortBookItems;

import libraryManagementSystem.book.BookItem;

import java.util.Comparator;

public class SortByPrice implements Comparator<BookItem> {

    @Override
    public int compare(BookItem book1, BookItem book2) {
        return (int) (book1.getPrice() - book2.getPrice());
    }
}
