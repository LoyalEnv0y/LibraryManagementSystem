package libraryManagementSystem.book.sortBookItems;

import libraryManagementSystem.book.BookItem;

import java.util.Comparator;

public class SortByFormat implements Comparator<BookItem> {

    @Override
    public int compare(BookItem bookItem1, BookItem bookItem2) {
        return bookItem1.getFormat().ordinal() - bookItem2.getFormat().ordinal();
    }
}
