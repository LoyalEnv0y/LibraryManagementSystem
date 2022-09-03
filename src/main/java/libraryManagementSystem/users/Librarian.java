package libraryManagementSystem.users;

import libraryManagementSystem.account.Account;
import libraryManagementSystem.account.AccountType;
import libraryManagementSystem.customExceptions.AccountNotActiveException;
import libraryManagementSystem.library.Address;

import java.time.LocalDate;

import static libraryManagementSystem.account.AccountStatus.*;

public class Librarian extends Person implements User {
    private static int instanceCount = 0;
    private final Account account;
    private final AccountType accountType = AccountType.ADMIN;
    private String id;
    private Address address;

    public Librarian(String firstName, String secondName,
                     LocalDate birthDate, String gender, Address address) {

        super(firstName, secondName, birthDate, gender);

        this.address = address;

        instanceCount++;
        setId();
        this.account = new Account(this.id);
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public void banMember(Account memberAccount) {
        memberAccount.setAccountStatus(BANNED, true);
    }

    public void unbanMember(Account memberAccount) {
        memberAccount.setAccountStatus(ACTIVE, true);
    }

    public void activateMember(Member member) {
        if (member.getAccount().getAccountStatus() == BANNED) {
            throw new AccountNotActiveException(
                    "\nERROR\n  Member: " + member.getFirstName() +
                            " " + member.getSecondName() + " is banned\n" +
                            "  To unban the user call unbanMember() method"
            );
        }

        member.getAccount().setAccountStatus(ACTIVE, true);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Librarian ID: " + id + "\n" +
                "Account ID: " + account.getAccountId();
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
