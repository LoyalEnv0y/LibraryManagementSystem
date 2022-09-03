package libraryManagementSystem.account;

import libraryManagementSystem.customExceptions.AccountNotActiveException;
import libraryManagementSystem.customExceptions.IllegalPermissionException;
import libraryManagementSystem.library.Address;
import libraryManagementSystem.users.Librarian;
import libraryManagementSystem.users.Member;
import libraryManagementSystem.users.User;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static libraryManagementSystem.account.AccountStatus.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private final Account memberAccount;
    private final Account adminAccount;
    private final Librarian librarian;
    private final LocalDateTime creationTime;

    public AccountTest() {
        Address address = new Address(
                "Test Street", "Test City", "Test State",
                "Test zipcode", "Test Country"
        );

        User member = new Member(
                "example", "member",
                LocalDate.of(2000, 6, 22),
                "Male", address
        );

        this.librarian = new Librarian(
                "example", "Librarian",
                LocalDate.of(2000, 6, 22),
                "Female", address
        );

        this.memberAccount = member.getAccount();
        this.adminAccount = librarian.getAccount();
        creationTime = LocalDateTime.now();
    }

    @Test
    public void testAccountsAreSetToFrozenByDefault() {
        assertEquals(FROZEN, memberAccount.getAccountStatus());
        assertEquals(FROZEN, adminAccount.getAccountStatus());
    }

    @Test
    public void testCreatedTime() {
        Duration memberDiff = Duration.between(creationTime, memberAccount.getCreatedAt());
        Duration adminDiff = Duration.between(creationTime, adminAccount.getCreatedAt());

        assertTrue(memberDiff.getSeconds() <= 1);
        assertTrue(adminDiff.getSeconds() <= 1);
    }

    @Test
    public void testSetUserNameWithValidAccountStatus() {
        String newUserName = "ChangedName";
        memberAccount.setAccountStatus(ACTIVE, false);
        memberAccount.setUserName(newUserName);
        assertEquals(newUserName, memberAccount.getUserName());
    }

    @Test
    public void testSetUserNameWithInvalidAccountStatus() {
        memberAccount.setAccountStatus(FROZEN, false);

        AccountNotActiveException exception = assertThrows(
                AccountNotActiveException.class,
                () -> memberAccount.setUserName("Should not work")
        );

        assertEquals("\nERROR\n  Cannot change username while " +
                "account is not active", exception.getMessage()
        );
    }

    @Test
    public void testSetPasswordWithValidAccountStatus() {
        String newUserPassword = "132132";
        memberAccount.setAccountStatus(ACTIVE, false);
        memberAccount.setPassword(newUserPassword);
        assertEquals(newUserPassword, memberAccount.getPassword());
    }

    @Test
    public void testSetPasswordWithInvalidAccountStatus() {
        AccountNotActiveException exception = assertThrows(
                AccountNotActiveException.class,
                () -> memberAccount.setPassword("newPassword")
        );

        assertEquals("\nERROR\n  Cannot change password while " +
                "account is not active", exception.getMessage()
        );
    }

    @Test
    public void testSetAccountStatusWhenUnbanned() {
        memberAccount.setAccountStatus(ACTIVE, false);
        assertEquals(ACTIVE, memberAccount.getAccountStatus());

        memberAccount.setAccountStatus(FROZEN, false);
        assertEquals(FROZEN, memberAccount.getAccountStatus());
    }

    @Test
    public void testSetAccountStatusWhenBanned() {
        librarian.banMember(memberAccount);

        IllegalPermissionException permissionException = assertThrows(
                IllegalPermissionException.class,
                () -> memberAccount.setAccountStatus(ACTIVE, false)
        );

        assertEquals("\nERROR\n  Only an administrator can ban or " +
                "unban an account", permissionException.getMessage());
    }

    @Test
    public void testSetAccountStatusToBannedWithoutAdminPermission() {
        IllegalPermissionException permissionException = assertThrows(
                IllegalPermissionException.class,
                () -> memberAccount.setAccountStatus(BANNED, false)
        );

        assertEquals("\nERROR\n  Only an administrator can ban or " +
                "unban an account", permissionException.getMessage());
    }
}
