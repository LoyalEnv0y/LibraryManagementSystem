package libraryManagementSystem.book;

import java.util.ArrayList;
import java.util.List;

public abstract class Book {
    private final BookISBN isbn;
    private final ArrayList<String> subjects;
    private final ArrayList<String> authors;
    private final String language;
    private String title;
    private String publisher;
    private int numberOfPages;

    public Book(BookISBN isbn, String title, String publisher,
                String language, int numberOfPages) {

        this.isbn = isbn;
        this.subjects = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.language = language;
        this.title = title;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
    }

    public BookISBN getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public void addSubject(String subject) {
        String formattedSubject = formatSubject(subject);

        if (!subjects.contains(formattedSubject)) {
            this.subjects.add(formattedSubject);
        }
    }

    public void addSubject(List<String> subjects) {
        subjects.forEach(this::addSubject);
    }

    public void deleteSubject(String subject) {
        String formattedSubject = formatSubject(subject);
        this.subjects.remove(formattedSubject);
    }

    public void deleteSubject(List<String> subjects) {
        subjects.forEach(this::deleteSubject);
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void addAuthor(BookAuthor author) {
        String fullName = getFullAuthorName(author);
        if (!authors.contains(fullName)) {
            authors.add(fullName);
        }
    }

    public void addAuthor(List<BookAuthor> authors) {
        authors.forEach(this::addAuthor);
    }

    public void deleteAuthor(BookAuthor author) {
        String fullName = getFullAuthorName(author);
        authors.remove(fullName);
    }

    public void deleteAuthor(List<BookAuthor> authors) {
        authors.forEach(this::deleteAuthor);
    }

    public String getLanguage() {
        return language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    private String formatSubject(String subject) {
        return subject.toUpperCase().strip();
    }

    private String getFullAuthorName(BookAuthor author) {
        return author.getFirstName() + " " + author.getSecondName();
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Book comparedBook)) {
            return false;
        }

        return this.isbn.equals(comparedBook.getIsbn());
    }
}
