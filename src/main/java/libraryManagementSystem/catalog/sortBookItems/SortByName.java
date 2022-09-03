package libraryManagementSystem.catalog.sortBookItems;

import libraryManagementSystem.book.BookItem;

import java.util.Comparator;

public class SortByName implements Comparator<BookItem> {

    @Override
    public int compare(BookItem book1, BookItem book2) {
        String book1Name = book1.getTitle();
        String book2Name = book2.getTitle();

        for (int i = 0; i < book1Name.length() && i < book2Name.length(); i++) {
            if (book1Name.charAt(i) == book2Name.charAt(i)) {
                continue;
            }
            return book1Name.charAt(i) - book2Name.charAt(i);
        }

        return book1Name.length() - book2Name.length();
    }
}
