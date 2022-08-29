package libraryManagementSystem.member;
import libraryManagementSystem.book.BookItem;
import libraryManagementSystem.domain.Address;
import libraryManagementSystem.human.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Member extends Person {
    private int id;
    private static int memberCount = 0;
    private Address address;
    private Map<BookItem, Integer> leasedBooks;
    private List<BookItem> boughtBooks;
    private AccountStatus accountStatus;

    public Member(String firstName, String secondName,
                  LocalDate birthDate, String gender) {

        super(firstName, secondName, birthDate, null, gender);

    }
}
