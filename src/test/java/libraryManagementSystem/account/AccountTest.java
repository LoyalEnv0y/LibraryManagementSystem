package libraryManagementSystem.account;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static libraryManagementSystem.account.AccountStatus.*;
import static libraryManagementSystem.account.AccountType.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private final Account memberAccount;
    private final Account adminAccount;
    private final LocalDateTime creationTime;

    public AccountTest() {
        this.memberAccount = new Account(
                "exampleUser",
                "123123",
                "M123",
                MEMBER
        );

        this.adminAccount = new Account(
                "exampleAdmin",
                "132132",
                "L132",
                ADMIN
        );
        creationTime = LocalDateTime.now();
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
        adminAccount.setUserName(newUserName);
        assertEquals(newUserName, adminAccount.getUserName());
    }

    @Test
    public void testSetUserNameWithInvalidAccountStatus() {
        memberAccount.setStatus(BANNED);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> memberAccount.setUserName("Should not work")
        );

        assertEquals("\nERROR\n  Cannot change username while " +
                "account" + "is not active", exception.getMessage()
        );
    }

    @Test
    public void testSetPasswordWithValidAccountStatus() {
        String newUserPassword = "132132";
        adminAccount.setPassword(newUserPassword);
        assertEquals(newUserPassword, adminAccount.getPassword());
    }

    @Test
    public void testSetPasswordWithInvalidAccountStatus() {
        memberAccount.setStatus(BANNED);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> memberAccount.setPassword("Should not work")
        );

        assertEquals("\nERROR\n  Cannot change password while " +
                "account" + "is not active", exception.getMessage()
        );
    }
}
