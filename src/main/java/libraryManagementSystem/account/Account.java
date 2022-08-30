package libraryManagementSystem.account;

import libraryManagementSystem.human.Person;
import libraryManagementSystem.users.Member;

import static libraryManagementSystem.account.AccountStatus.*;

import java.time.LocalDateTime;

// TODO WHEN YOU IMPLEMENT THE LIBRARIAN METHOD CHANGE THE 26TH LINE

public class Account {
    private final LocalDateTime createdAt;
    private final Person owner;
    private int accountNumber;
    private static int instanceCount = 0;
    private String userName;
    private String password;
    private AccountStatus status;
    private boolean isAdmin;

    public Account(String userName, String password, Person owner) {
        this.createdAt = LocalDateTime.now();
        this.userName = userName;
        this.password = password;
        this.status = ACTIVE;
        this.owner = owner;
        this.isAdmin = owner.getClass() != Member.class;
        instanceCount++;
        setAccountNumber();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber() {
        this.accountNumber = instanceCount + 1000;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Person getOwner() {
        return owner;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
