package libraryManagementSystem.human;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    /**
     * "Bud Period" is a term that I came up with to indicate the current date
     * is before the birthdate.
     * <p>
     * For example: If I were born in 2000.01.20 and the current date is
     * 2002.01.10 then I would be 1 years old not 2. From the start of the
     * current year to the birthdate is what I call "Bud Period"
     */
    private final Person deadPerson;
    private final Person deadPersonWithBudPeriod;
    private final Person alivePerson;

    public PersonTest() {
        deadPerson = new Author("Dead", "Person",
                LocalDate.of(1970, 6, 22),
                LocalDate.of(2020, 7, 10),
                "Male"
        );

        deadPersonWithBudPeriod = new Author("Dead",
                "Person With Bud Period",
                LocalDate.of(1970, 6, 22),
                LocalDate.of(2020, 5, 10),
                "Female"
        );

        alivePerson = new Author("Alive", "Person",
                LocalDate.of(1970, 6, 22),
                null,
                "Male"
        );
    }

    @Test
    public void testSetFirstNameWhenPersonIsDead() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> deadPerson.setFirstName("Jacob")
        );

        assertEquals("\nERROR\n  Can't change a dead person's name",
                exception.getMessage()
        );
    }

    @Test
    public void testSetSecondNameWhenPersonIsDead() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> deadPerson.setSecondName("Jacob")
        );

        assertEquals("\nERROR\n  Can't change a dead person's name",
                exception.getMessage()
        );
    }

    @Test
    public void testSetGenderWhenPersonIsDead() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> deadPerson.setGender("Female")
        );

        assertEquals("\nERROR\n  Can't change a dead person's gender",
                exception.getMessage()
        );
    }

    @Test
    public void testGetAgeWhenThePersonIsDead() {
        assertEquals(50, deadPerson.getAge());
    }

    @Test
    public void testGetAgeWhenThePersonIsDeadAndItsBudPeriod() {
        assertEquals(49, deadPersonWithBudPeriod.getAge());
    }

    /*
     * Since the getAge() method works with LocalTime.of() method I can't
     * expect the below code to pass all the time because as the time changes
     * the result of getAge() will be different.
     * <p>
     * I can get around this error by simply reusing the code of getAge below
     * but that would defeat the purpose of the test.
     * <p>
     * When you are testing the code please remove the comments and reconfigure
     * the result.
     */

/*    @Test
    public void testGetAgeWhenThePersonIsAlive() {
        assertEquals(expectedAge, alivePerson.getAge());
    }*/
}
