package libraryManagementSystem.users;

import libraryManagementSystem.account.Account;
import libraryManagementSystem.domain.Address;
import libraryManagementSystem.human.Person;

import java.time.LocalDate;

public class Librarian extends Person {
    private static int instanceCount = 0;
    private String id;
    private Account account;
    private Address address;

    public Librarian(String firstName, String secondName, LocalDate birthDate,
                     String gender, Address address) {
        super(firstName, secondName, birthDate, null, gender);

        instanceCount++;
        setId();
        this.address = address;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public String getId() {
        return id;
    }

    private void setId() {
        this.id = "L" + (instanceCount + 1000);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member ID: " + "'" + id + "'\n" +
                "First Name: " + "'" + super.getFirstName() + "'\n" +
                "Second Name: " + "'" + super.getSecondName() + "'\n" +
                "Account ID: " + "'" + account.getAccountNumber() + "'";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Librarian comparedLibrarian)) {
            return false;
        }

        return this.id.equals(comparedLibrarian.getId());
    }
}
