package libraryManagementSystem.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookISBNTest {
    private BookISBN bookISBN;

    public void start(String value) {
        this.bookISBN = new BookISBN(value);
    }

    @Test
    public void testInitializeValidISBNTen() {
        start("1617292230");
        assertEquals("1617292230", bookISBN.getValue());
    }

    @Test
    public void testInitializeValidISBNTenWithDashes() {
        start("1-61729-223-0");
        assertEquals("1-61729-223-0", bookISBN.getValue());
    }

    @Test
    public void testInitializeValidISBNTenWithX() {
        start("3-598-21507-X");
        assertEquals("3-598-21507-X", bookISBN.getValue());
    }

    @Test
    public void testInitializeInvalidISBNTen() {
        String value = "1617292233";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> start(value)
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
                () -> start(value)
        );

        assertEquals("\nERROR:\n  Value -> "
                        + value + " is not a valid ISBN value",
                exception.getMessage()
        );
    }

    @Test
    public void testSetValueTenWithValidInput() {
        start("3-598-21508-8");
        bookISBN.setValue("8-711-07559-7");
        assertEquals("8-711-07559-7", bookISBN.getValue());
    }

    @Test
    public void testSetValueTenWithValidInputWithoutDashes() {
        start("3-598-21508-8");
        bookISBN.setValue("8711075597");
        assertEquals("8711075597", bookISBN.getValue());
    }

    @Test
    public void testSetValueTenWithInvalidInput() {
        start("3-598-21508-8");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookISBN.setValue("8-711-07559-9")
        );

        assertEquals("\nERROR:\n  Value -> "
                        + "8-711-07559-9" + " is not a valid ISBN value",
                exception.getMessage()
        );
    }

    @Test
    public void testSetValueTenWithInvalidInputWithoutDashes() {
        start("3-598-21508-8");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookISBN.setValue("8711075599")
        );

        assertEquals("\nERROR:\n  Value -> "
                        + "8711075599" + " is not a valid ISBN value",
                exception.getMessage()
        );
    }

    @Test
    public void testInitializeValidISBNThirteen() {
        start("8699118040781");
        assertEquals("8699118040781", bookISBN.getValue());
    }

    @Test
    public void testInitializeValidISBNThirteenWithDashes() {
        start("8-699118-040781");
        assertEquals("8-699118-040781", bookISBN.getValue());
    }

    @Test
    public void testInitializeInvalidISBNThirteen() {
        String value = "9786055933285";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> start(value)
        );

        assertEquals("\nERROR:\n  Value -> "
                        + value + " is not a valid ISBN value",
                exception.getMessage()
        );
    }

    @Test
    public void testInitializeInvalidISBNThirteenWithDashes() {
        String value = "8-699118-040780";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> start(value)
        );

        assertEquals("\nERROR:\n  Value -> "
                        + value + " is not a valid ISBN value",
                exception.getMessage()
        );
    }

    @Test
    public void testSetValueThirteenWithValidInput() {
        start("8-697236-447109");
        bookISBN.setValue("8-697236-447109");
        assertEquals("8-697236-447109", bookISBN.getValue());
    }

    @Test
    public void testSetValueThirteenWithValidInputWithoutDashes() {
        start("978-0-306-40615-7");
        bookISBN.setValue("9780306406157");
        assertEquals("9780306406157", bookISBN.getValue());
    }

    @Test
    public void testSetValueThirteenWithInvalidInput() {
        start("8-697236-447109");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookISBN.setValue("8-697236-447100")
        );

        assertEquals("\nERROR:\n  Value -> "
                        + "8-697236-447100" + " is not a valid ISBN value",
                exception.getMessage()
        );
    }

    @Test
    public void testSetValueThirteenWithInvalidInputWithoutDashes() {
        start("9-786055-933289");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookISBN.setValue("9786055933284")
        );

        assertEquals("\nERROR:\n  Value -> "
                        + "9786055933284" + " is not a valid ISBN value",
                exception.getMessage()
        );
    }
}