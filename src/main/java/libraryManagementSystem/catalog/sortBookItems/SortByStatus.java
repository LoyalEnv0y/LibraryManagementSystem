package libraryManagementSystem.catalog.sortBookItems;

import libraryManagementSystem.book.BookItem;

import java.util.Comparator;

public class SortByStatus implements Comparator<BookItem> {

    @Override
    public int compare(BookItem bookItem1, BookItem bookItem2) {
        return bookItem1.getStatus().ordinal() - bookItem2.getStatus().ordinal();
    }
}
