package libraryManagementSystem.book;

import libraryManagementSystem.users.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookAuthor extends Person {
    private final ArrayList<Book> books;
    private LocalDate deathDate;
    private int age;

    public BookAuthor(String firstName, String secondName,
                      LocalDate birthDate, String gender) {

        super(firstName, secondName, birthDate, gender);

        this.books = new ArrayList<>();
    }

    public BookAuthor(String firstName, String secondName,
                      LocalDate birthDate, String gender, LocalDate deathDate) {

        this(firstName, secondName, birthDate, gender);
        this.deathDate = deathDate;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
        }
    }

    public void addBook(List<Book> books) {
        books.forEach(this::addBook);
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        if (this.deathDate != null) {
            throw new IllegalStateException(
                    "\nERROR\n  Author is already dead"
            );
        }

        this.deathDate = deathDate;
    }

    @Override
    public void updateAge() {
        LocalDate currentDate = LocalDate.now();
        if (deathDate != null) {
            currentDate = deathDate;
        }

        int age = currentDate.getYear() - getBirthDate().getYear();

        if (currentDate.minusYears(age).isBefore(getBirthDate())) {
            age--;
        }

        this.age = age;
    }

    @Override
    public int getAge() {
        updateAge();
        return age;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Books: " + books;
    }
}
