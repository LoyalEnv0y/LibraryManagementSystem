package libraryManagementSystem.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {
    private Person person;

    @Before
    public void start() {
        Address address = new Address(
                "Example Street, no: 985",
                "Example City",
                "Example State",
                "985985",
                "Example Country"
        );

        this.person = new Person(
                "ExampleName",
                address,
                "example@example.com",
                "555-034-55"
        );
    }

    @Test
    public void testUpdateName() {
        String newName = "Anakin Skywalker";
        person.setName(newName);
        assertEquals(newName, person.getName());
    }

    @Test
    public void testUpdateAddress() {
        Address newAddress = new Address(
                "Galactia Section, 14nt level, Jedi Temple",
                "Coruscant",
                "Coruscant",
                "4124142343",
                "Galactic Republic"
        );
        person.setAddress(newAddress);
        assertEquals(newAddress, person.getAddress());
    }

    @Test
    public void testUpdateEmail() {
        String newEmail = "AnakinSkywalker@Jedi.com";
        person.setEmail(newEmail);
        assertEquals(newEmail, person.getEmail());
    }

    @Test
    public void testUpdatePhone() {
        String newPhone = "19283791123785095802";
        person.setPhone(newPhone);
        assertEquals(newPhone, person.getPhone());
    }
}
