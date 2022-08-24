package libraryManagementSystem.ISBN;

import org.junit.Test;

import static org.junit.Assert.*;

public class ISBNTest {
    private ISBN isbn;

    public void start(String value, ISBNVersion version) {
        this.isbn = new ISBN(value, version);
    }

    @Test
    public void testFormatValueWithDashes() {
        String value = "1-61729-223-0";
        start(value, ISBNVersion.TEN_DIGIT);
        assertEquals("1617292230", isbn.formatValue(value));
    }

    @Test
    public void testFormatValueWithXAsCheckDigit() {
        String value = "3-598-21507-X";
        start(value, ISBNVersion.TEN_DIGIT);
        assertEquals("359821507X", isbn.formatValue(value));
    }

    @Test
    public void testFormatValueWithWhiteSpaces() {
        String value = "\n3-12-   565751-2\t";
        start(value, ISBNVersion.TEN_DIGIT);
        assertEquals("3125657512", isbn.formatValue(value));
    }

    @Test
    public void testSetValueWithValidInput() {
        start("3-598-21508-8", ISBNVersion.TEN_DIGIT);
        isbn.setValue("8-711-07559-7");
        assertEquals("8711075597", isbn.getValue());
        assertTrue(isbn.isValid());
    }

    @Test
    public void testSetValueWithValidInputWithoutDashes() {
        start("3-598-21508-8", ISBNVersion.TEN_DIGIT);
        isbn.setValue("8711075597");
        assertEquals("8711075597", isbn.getValue());
        assertTrue(isbn.isValid());
    }

    @Test
    public void testSetValueWithInvalidInput() {
        start("3-598-21508-8", ISBNVersion.TEN_DIGIT);
        isbn.setValue("8-711-07559-9");
        assertEquals("8711075599", isbn.getValue());
        assertFalse(isbn.isValid());
    }
}
