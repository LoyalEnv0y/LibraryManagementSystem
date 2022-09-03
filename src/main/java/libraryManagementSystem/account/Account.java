package libraryManagementSystem.account;

import libraryManagementSystem.customExceptions.AccountNotActiveException;
import libraryManagementSystem.customExceptions.IllegalPermissionException;

import java.time.LocalDateTime;

import static libraryManagementSystem.account.AccountStatus.*;

public class Account {
    private static int instanceCount = 0;
    private final LocalDateTime createdAt;
    private final String userId;
    private int accountId;
    private String userName;
    private String password;
    private AccountStatus accountStatus;

    public Account(String userId) {
        this.createdAt = LocalDateTime.now();
        this.userName = "User";
        this.password = "user@123";
        this.accountStatus = FROZEN;
        this.userId = userId;

        instanceCount++;
        setAccountId();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public int getAccountId() {
        return accountId;
    }

    private void setAccountId() {
        this.accountId = instanceCount + 1000;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (accountStatus != ACTIVE) {
            throw new AccountNotActiveException(
                    "\nERROR\n  Cannot change username while account " +
                            "is not active"
            );
        }
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (accountStatus != ACTIVE) {
            throw new AccountNotActiveException(
                    "\nERROR\n  Cannot change password while account " +
                            "is not active"
            );
        }

        this.password = password;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus, boolean admin) {
        if (!admin) {
            if (this.accountStatus == BANNED || accountStatus == BANNED) {
                throw new IllegalPermissionException(
                        "\nERROR\n  Only an administrator can ban or " +
                                "unban an account"
                );
            }
        }

        this.accountStatus = accountStatus;
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

        return this.accountId == comparedAccount.getAccountId();
    }
}
