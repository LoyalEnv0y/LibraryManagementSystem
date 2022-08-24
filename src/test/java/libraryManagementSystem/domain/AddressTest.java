package libraryManagementSystem.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddressTest {
    private Address address;

    @Before
    public void start() {
        this.address = new Address(
                "Example Street, no: 985",
                "Example City",
                "Example State",
                "985985",
                "Example Country"
        );
    }

    @Test
    public void testUpdateStreetAddress() {
        String newAddress = "240 West 55th Street";
        address.setStreetAddress(newAddress);
        assertEquals(newAddress, address.getStreetAddress());
    }

    @Test
    public void testUpdateState() {
        String state = "New York";
        address.setState(state);
        assertEquals(state, address.getState());
    }

    @Test
    public void testUpdateCity() {
        String newCity = "New York City";
        address.setCity(newCity);
        assertEquals(newCity, address.getCity());
    }

    @Test
    public void testUpdateZipcode() {
        String zipcode = "10019";
        address.setZipcode(zipcode);
        assertEquals(zipcode, address.getZipcode());
    }

    @Test
    public void testUpdateCountry() {
        String country = "USA";
        address.setCountry(country);
        assertEquals(country, address.getCountry());
    }
}
