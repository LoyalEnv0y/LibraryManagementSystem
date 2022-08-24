package libraryManagementSystem.ISBN;

import org.junit.Test;

import static org.junit.Assert.*;

public class ISBNTest {
    private ISBN isbn;

    public void start(String value, ISBNVersion version) {
        this.isbn = new ISBN(value, version);
    }

    @Test
    public void testInitializeValidISBNTen() {
        start("1617292230", ISBNVersion.TEN_DIGIT);
        assertEquals("1617292230", isbn.getValue());
    }

    @Test
    public void testInitializeValidISBNTenWithDashes() {
        start("1-61729-223-0", ISBNVersion.TEN_DIGIT);
        assertEquals("1617292230", isbn.getValue());
    }

    @Test
    public void testInitializeValidISBNTenWithX() {
        start("3-598-21507-X", ISBNVersion.TEN_DIGIT);
        assertEquals("359821507X", isbn.getValue());
    }

    @Test
    public void testInitializeValidISBNTenWithWrongVersion() {
        String value = "1617292230";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> start(value, ISBNVersion.THIRTEEN_DIGIT)
        );

        assertEquals("\nERROR:\n  Incompatible length -> "
                        + value.length() + " is not compatible"
                        + " with " + ISBNVersion.THIRTEEN_DIGIT,
                exception.getMessage()
        );
    }

    @Test
    public void testInitializeInvalidISBNTen() {
        String value = "1617292233";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> start(value, ISBNVersion.TEN_DIGIT)
        );

        assertEquals("\nERROR:\n  Value -> "
                        + value + " is not a valid ISBN value",
                exception.getMessage()
        );
    }

    @Test
    public void testInitializeInvalidISBNTenWithDashes() {
        String value = "1-61729-223-3";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> start(value, ISBNVersion.TEN_DIGIT)
        );

        assertEquals("\nERROR:\n  Value -> "
                        + value + " is not a valid ISBN value",
                exception.getMessage()
        );
    }

    @Test
    public void testSetValueTenWithValidInput() {
        start("3-598-21508-8", ISBNVersion.TEN_DIGIT);
        isbn.setValue("8-711-07559-7");
        assertEquals("8711075597", isbn.getValue());
    }

    @Test
    public void testSetValueTenWithValidInputWithoutDashes() {
        start("3-598-21508-8", ISBNVersion.TEN_DIGIT);
        isbn.setValue("8711075597");
        assertEquals("8711075597", isbn.getValue());
    }

    @Test
    public void testSetValueTenWithInvalidInput() {
        start("3-598-21508-8", ISBNVersion.TEN_DIGIT);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> isbn.setValue("8-711-07559-9")
        );

        assertEquals("\nERROR:\n  Value -> "
                        + "8-711-07559-9" + " is not a valid ISBN value",
                exception.getMessage()
        );
    }

    @Test
    public void testSetValueTenWithInvalidInputWithoutDashes() {
        start("3-598-21508-8", ISBNVersion.TEN_DIGIT);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> isbn.setValue("8711075599")
        );

        assertEquals("\nERROR:\n  Value -> "
                        + "8711075599" + " is not a valid ISBN value",
                exception.getMessage()
        );
    }

    @Test
    public void testSetValueTenWithInvalidVersion() {
        start("3-598-21508-8", ISBNVersion.TEN_DIGIT);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> isbn.setValue("8-697236-447109")
        );

        assertEquals("\nERROR:\n  Incompatible length -> "
                        + "8-697236-447109".length() + " is not compatible"
                        + " with " + ISBNVersion.TEN_DIGIT,
                exception.getMessage()
        );
    }
}
