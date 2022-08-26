package libraryManagementSystem.book;

import libraryManagementSystem.human.Author;
import libraryManagementSystem.isbn.ISBN;

import java.util.ArrayList;

public class Book {
    private final ISBN isbn;
    private final ArrayList<String> subjects;
    private final String language;
    private String title;
    private Author author;
    private String publisher;
    private int numberOfPages;

    public Book(ISBN isbn, String title, ArrayList<String> subjects, String publisher, String language, int numberOfPages) {
        this.isbn = isbn;
        this.title = title;
        this.subjects = subjects;
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

    public void addSubject(ArrayList<String> subjects) {
        subjects.stream()
                .filter(subject -> !this.subjects.contains(subject))
                .forEach(this.subjects::add);
    }

    public void addSubject(String subject) {
        if (!subjects.contains(subject)) {
            this.subjects.add(subject);
        }
    }

    public void deleteSubject(String subject) {
        this.subjects.remove(subject);
    }

    public void deleteSubject(ArrayList<String> subject) {
        this.subjects.removeAll(subject);
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
        return "Book ↴\n" +
                "\tTitle: " + "'" + title + "'" +
                "\tSubjects: " + "'" + subjects + "'\n" +
                "\tPublisher: " + "'" + publisher + "'\n" +
                "\tLanguage: " + "'" + language + "'\n" +
                "\tPages: " + "'" + numberOfPages + "'";
    }
}
