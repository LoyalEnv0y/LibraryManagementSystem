package libraryManagementSystem.users;

import libraryManagementSystem.account.Account;
import libraryManagementSystem.account.AccountType;

public interface User {
    AccountType getAccountType();
    String getId();
    Account getAccount();
    String getFirstName();
    String getSecondName();
}
