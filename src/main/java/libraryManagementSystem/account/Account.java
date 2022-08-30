package libraryManagementSystem.account;

import static libraryManagementSystem.account.AccountStatus.*;

import java.time.LocalDateTime;

public class Account {
    private final LocalDateTime createdAt;
    private final String ownerId;
    private int accountNumber;
    private static int instanceCount = 0;
    private String userName;
    private String password;
    private AccountStatus status;
    private AccountType accountType;

    public Account(String userName, String password, String ownerId, AccountType accountType) {
        this.createdAt = LocalDateTime.now();
        this.userName = userName;
        this.password = password;
        this.status = ACTIVE;
        this.ownerId = ownerId;
        this.accountType = accountType;
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
        if (status != ACTIVE) {
            throw new IllegalStateException(
                    "\nERROR\n  Cannot change username while account" +
                            "is not active"
            );
        }
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (status != ACTIVE) {
            throw new IllegalStateException(
                    "\nERROR\n  Cannot change password while account" +
                            "is not active"
            );
        }

        this.password = password;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String  getOwnerId() {
        return ownerId;
    }

    public boolean isAdmin() {
        return accountType == AccountType.ADMIN;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Account comparedAccount)) {
            return false;
        }

        return this.accountNumber == comparedAccount.getAccountNumber();
    }
}
