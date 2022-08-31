package libraryManagementSystem.book;

import libraryManagementSystem.isbn.ISBN;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private final ISBN isbn;
    private final ArrayList<String> subjects;
    private final ArrayList<String> authors;
    private final String language;
    private String title;
    private String publisher;
    private int numberOfPages;

    public Book(ISBN isbn, String title, String publisher, String language, int numberOfPages) {
        this.isbn = isbn;
        this.title = title;
        this.subjects = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.publisher = publisher;
        this.language = language;
        this.numberOfPages = numberOfPages;
    }

    public ISBN getIsbn() {
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
        String formattedSubject = subject.toUpperCase().strip();

        if (!subjects.contains(formattedSubject)) {
            this.subjects.add(formattedSubject);
        }
    }

    public void addSubject(ArrayList<String> subjects) {
        List<String> formattedSubjects = subjects
                .stream().map(subject -> subject.toUpperCase().strip())
                .toList();

        formattedSubjects.stream()
                .filter(subject -> !this.subjects.contains(subject))
                .forEach(this.subjects::add);
    }

    public void deleteSubject(String subject) {
        String formattedSubject = subject.toUpperCase().strip();
        this.subjects.remove(formattedSubject);
    }

    public void deleteSubject(ArrayList<String> subjects) {
        List<String> formattedSubjects = subjects
                .stream().map(subject -> subject.toUpperCase().strip())
                .toList();

        this.subjects.removeAll(formattedSubjects);
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void addAuthor(BookAuthor author) {
        String fullName = author.getFirstName() + " " + author.getSecondName();
        if (!authors.contains(fullName)) {
            authors.add(fullName);
        }
    }

    public void addAuthor(ArrayList<BookAuthor> authors) {
        authors.stream()
                .filter(author -> !this.authors.contains(
                        author.getFirstName() + " " + author.getSecondName()))
                .forEach(this::addAuthor);
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "Title: " + "'" + title + "'\n" +
                "Subjects: " + "'" + subjects + "'\n" +
                "Authors: " + "'" + authors + "'\n" +
                "Publisher: " + "'" + publisher + "'\n" +
                "Language: " + "'" + language + "'\n" +
                "Pages: " + "'" + numberOfPages + "'";
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
