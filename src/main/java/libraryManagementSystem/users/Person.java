package libraryManagementSystem.users;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Person {
    private final LocalDate birthDate;
    private String firstName;
    private String secondName;
    private int age;
    private String gender;

    public Person(String firstName, String secondName,
                  LocalDate birthDate, String gender) {

        this.firstName = formatName(firstName);
        this.secondName = formatName(secondName);
        this.birthDate = birthDate;
        this.gender = gender;
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

    public void updateAge() {
        LocalDate currentDate = LocalDate.now();

        int age = currentDate.getYear() - birthDate.getYear();

        if (currentDate.minusYears(age).isBefore(birthDate)) {
            age--;
        }

        this.age = age;
    }

    public int getAge() {
        updateAge();
        return this.age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String formatName(String name) {
        return (name.substring(0, 1).toUpperCase() +
                name.substring(1)).strip();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String birthDate = this.birthDate.format(formatter);

        return "Full Name: " + firstName + " " + secondName + "\n" +
                "Date of Birth: " + birthDate + "\n" +
                "Age: " + getAge() + "\n" +
                "Gender: " + gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Person comparedPerson)) {
            return false;
        }

        return this.firstName.equals(comparedPerson.getFirstName()) &&
                this.secondName.equals(comparedPerson.getSecondName()) &&
                this.birthDate == comparedPerson.getBirthDate() &&
                this.gender.equals(comparedPerson.getGender());
    }
}
