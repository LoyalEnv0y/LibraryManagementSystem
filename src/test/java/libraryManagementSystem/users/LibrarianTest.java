package libraryManagementSystem.users;

import libraryManagementSystem.customExceptions.AccountNotActiveException;
import libraryManagementSystem.library.Address;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static libraryManagementSystem.account.AccountStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LibrarianTest {
    private final Librarian librarian;
    private final Member member;

    public LibrarianTest() {
        Address address = new Address(
                "Test Street", "Test City", "Test State",
                "Test zipcode", "Test Country"
        );

        librarian = new Librarian("Test", "Librarian",
                LocalDate.of(2000, 6, 22),
                "Male", address
        );

        member = new Member(
                "Test", "Member",
                LocalDate.of(2000, 6, 22),
                "Female", address
        );
    }

    @Test
    public void banMember() {
        librarian.banMember(member.getAccount());
        assertEquals(BANNED, member.getAccount().getAccountStatus());
    }

    @Test
    public void unbanMember() {
        librarian.banMember(member.getAccount());
        librarian.unbanMember(member.getAccount());
        assertEquals(ACTIVE, member.getAccount().getAccountStatus());
    }

    @Test
    public void activateMember() {
        librarian.activateMember(member);
        assertEquals(ACTIVE, member.getAccount().getAccountStatus());
    }

    @Test
    public void activateBannedMember() {
        librarian.banMember(member.getAccount());

        AccountNotActiveException accountNotActive = assertThrows(
                AccountNotActiveException.class,
                () -> librarian.activateMember(member)
        );

        assertEquals("\nERROR\n  Member: " + member.getFirstName() +
                        " " + member.getSecondName() + " is banned\n" +
                        "  To unban the user call unbanMember() method",
                accountNotActive.getMessage()
        );

    }

}
