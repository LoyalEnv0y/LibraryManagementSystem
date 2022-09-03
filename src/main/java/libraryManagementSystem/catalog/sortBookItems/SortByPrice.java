package libraryManagementSystem.catalog.sortBookItems;


import libraryManagementSystem.book.BookItem;

import java.util.Comparator;

public class SortByPrice implements Comparator<BookItem> {

    @Override
    public int compare(BookItem book1, BookItem book2) {
        return Double.compare(book2.getPrice(), book1.getPrice());
    }
}
