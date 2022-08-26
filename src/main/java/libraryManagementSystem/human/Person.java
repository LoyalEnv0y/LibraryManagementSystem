package libraryManagementSystem.human;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//TODO: TEST THE FOLLOWING METHODS: setDeathDate, getAge, toString.

public abstract class Person {
    private final LocalDate birthDate;
    private String firstName;
    private String secondName;
    private LocalDate deathDate;
    private String gender;

    public Person(String firstName, String secondName, LocalDate birthDate, LocalDate deathDate, String gender) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.gender = gender;
        setDeathDate(deathDate);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        if (deathDate.isBefore(birthDate)) {
            throw new IllegalArgumentException(
                    "\nError\n  Date of death cannot be earlier then date of birth"
            );
        }

        this.deathDate = deathDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - birthDate.getYear();

        if (deathDate != null) {
            currentDate = deathDate;
        }

        if (currentDate.minusYears(age).isBefore(birthDate)) {
            age--;
        }

        return age;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String birthDate = this.birthDate.format(formatter);
        String deathDate = (this.deathDate == null) ? "-" : this.deathDate.format(formatter);

        return "Person ↴\n" +
                "\tFull Name: " + "'" + firstName + " " + secondName + "'\n" +
                "\tDate of Birth: " + "'" + birthDate + "'\n" +
                "\tAge: " + "'" + getAge() + "'\n" +
                "\tDate of Death: " + "'" + deathDate + "'\n" +
                "\tGender: " + "'" + gender + "'\n";
    }
}
