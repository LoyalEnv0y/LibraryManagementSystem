package libraryManagementSystem.library;

import libraryManagementSystem.catalog.Catalog;
import libraryManagementSystem.users.Librarian;
import libraryManagementSystem.users.Member;
import libraryManagementSystem.users.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private final Library library;
    private final User user1;
    private final User user2;
    private final User user3;
    private final List<User> users;

    public LibraryTest() {
        Address address = new Address(
                "Test Street", "Test City", "Test State",
                "Test zipcode", "Test Country"
        );

        Catalog catalog = new Catalog();
        library = new Library("Test Library", address, catalog);

        user1 = new Member("Test", "User",
                LocalDate.of(2000, 6, 22),
                "Male", address
        );
        user2 = new Member("Test2", "User",
                LocalDate.of(2000, 6, 22),
                "Male", address
        );
        user3 = new Librarian("Test3", "User",
                LocalDate.of(2000, 6, 22),
                "Female", address
        );

        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    @Test
    public void addUser() {
        library.addUser(user1);
        assertTrue(library.getUserMap().containsKey(user1.getId()));
    }

    @Test
    public void addUserAddsUsersAccount() {
        library.addUser(user1);

        assertTrue(library.getAccountMap()
                .containsKey(user1.getAccount().getAccountId())
        );
    }

    @Test
    public void addMultipleUsers() {
        library.addUser(users);

        assertEquals(users.size(), library.getUserMap().size());

        for (User user : users) {
            assertTrue(library.getUserMap().containsValue(user));
        }
    }

    @Test
    public void addMultipleUsersUsingMap() {
        Map<String, User> userMap = new HashMap<>();
        for (User user : users) {
            userMap.put(user.getId(), user);
        }

        library.addUser(userMap);

        for (User user : userMap.values()) {
            assertTrue(library.getUserMap().containsKey(user.getId()));
        }
    }

    @Test
    public void deleteUser() {
        library.addUser(user1);
        library.deleteUser(user1);

        assertFalse(library.getUserMap().containsValue(user1));
    }

    @Test
    public void deleteUserWithUserId() {
        library.addUser(user1);
        library.deleteUser(user1.getId());

        assertFalse(library.getUserMap().containsValue(user1));
    }

    @Test
    public void findUserWithUserId() {
        library.addUser(user1);
        assertEquals(user1, library.findUser(user1.getId()));
    }

    @Test
    public void findUserWithAccount() {
        library.addUser(user1);
        assertEquals(user1, library.findUser(user1.getAccount()));
    }

    @Test
    public void findUserWithAccountId() {
        library.addUser(user1);

        assertEquals(user1,
                library.findUser(user1.getAccount().getAccountId())
        );
    }

    @Test
    public void findAccountWithAccountId() {
        library.addUser(user1);

        assertEquals(user1.getAccount(),
                library.findAccount(user1.getAccount().getAccountId())
        );
    }

    @Test
    public void findAccountWithAccount() {
        library.addUser(user1);

        assertEquals(user1.getAccount(),
                library.findAccount(user1.getAccount())
        );
    }

    @Test
    public void findAccountWithUser() {
        library.addUser(user1);

        assertEquals(user1.getAccount(),
                library.findAccount(user1)
        );
    }

    @Test
    public void findAccountWithUserId() {
        library.addUser(user1);

        assertEquals(user1.getAccount(),
                library.findAccount(user1.getId())
        );
    }
}
