package libraryManagementSystem.human;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public abstract class Person {
    private final LocalDate birthDate;
    private final IllegalArgumentException illegalNameChange;
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
        illegalNameChange = new IllegalArgumentException(
                "\nERROR\n  Can't change a dead person's name"
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (deathDate != null) {
            throw illegalNameChange;
        }

        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        if (deathDate != null) {
            throw illegalNameChange;
        }

        this.secondName = secondName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        if (deathDate == null) {
            this.deathDate = null;
            return;
        }

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
        if (deathDate != null) {
            throw new IllegalArgumentException(
                    "\nERROR\n  Can't change a dead person's gender"
            );
        }

        this.gender = gender;
    }

    public int getAge() {
        LocalDate currentDate = LocalDate.now();

        if (deathDate != null) {
            currentDate = deathDate;
        }

        int age = currentDate.getYear() - birthDate.getYear();

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

        return "Full Name: " + "'" + firstName + " " + secondName + "'\n" +
                "Date of Birth: " + "'" + birthDate + "'\n" +
                "Age: " + "'" + getAge() + "'\n" +
                "Date of Death: " + "'" + deathDate + "'\n" +
                "Gender: " + "'" + gender + "'\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Author comparedPerson)) {
            return false;
        }

        return this.firstName.equals(comparedPerson.getFirstName()) &&
                this.secondName.equals(comparedPerson.getSecondName()) &&
                this.birthDate == comparedPerson.getBirthDate() &&
                this.getAge() == comparedPerson.getAge() &&
                this.gender.equals(comparedPerson.getGender());
    }
}
