package libraryManagementSystem.isbn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ISBNHandlerTest {
    private ISBNHandler handler;

    @BeforeEach
    public void start() {
        handler = new ISBNHandler();
    }

    @Test
    public void testFormatValue() {
        assertEquals("1617292230", handler.formatValue("1-61729-223-0"));
    }

    @Test
    public void testFormatValueIncludingX() {
        assertEquals("359821507X", handler.formatValue("3-598-21507-X"));
    }

    @Test
    public void testFormatValueWithWhiteSpaces() {
        assertEquals("3125657512", handler.formatValue("\n3-12-   565  751-2\t"));
    }

    @Test
    public void testFormatValueThirteen() {
        assertEquals("8697236447109", handler.formatValue("8-697236-447109"));
    }

    @Test
    public void testFormatValueThirteenWithWhiteSpaces() {
        assertEquals("9781617292231", handler.formatValue("\n9-78  1617-    292 231\t"));
    }

    @Test
    public void testVerifyValidValueWithTen() {
        assertTrue(handler.verifyValue("1-61729-223-0"));
    }

    @Test
    public void testVerifyValidValueWithTenWithoutDashes() {
        assertTrue(handler.verifyValue("1617292230"));
    }

    @Test
    public void testVerifyValidValueWithTenIncludingX() {
        assertTrue(handler.verifyValue("3-598-21507-X"));
    }

    @Test
    public void testVerifyValidValueWithTenIncludingXWithoutDashes() {
        assertTrue(handler.verifyValue("359821507X"));
    }

    @Test
    public void testVerifyInvalidValueWithTen() {
        assertFalse(handler.verifyValue("1-61729-223-2"));
    }

    @Test
    public void testVerifyInvalidValueWithTenWithInvalidChars() {
        assertFalse(handler.verifyValue("1-6y72a-2/3-2"));
    }

    @Test
    public void testVerifyInvalidValueWithTenWithoutDashes() {
        assertFalse(handler.verifyValue("1617292232"));
    }

    @Test
    public void testVerifyInvalidValueWithTenIncludingX() {
        assertFalse(handler.verifyValue("1-61729-223-X"));
    }

    @Test
    public void testVerifyInvalidValueWithTenIncludingXAndInvalidChars() {
        assertFalse(handler.verifyValue("1-b1729-2a3-X"));
    }

    @Test
    public void testVerifyInvalidValueWithTenIncludingXWithoutDashes() {
        assertFalse(handler.verifyValue("161729223X"));
    }

    @Test
    public void testVerifyValueWithShortLength() {
        assertFalse(handler.verifyValue("123"));
    }

    @Test
    public void testVerifyValueWithLongLength() {
        assertFalse(handler.verifyValue("123124125345634"));
    }

    @Test
    public void testVerifyVersionTenWithValidVersion() {
        assertTrue(handler.verifyVersion(10, ISBNVersion.TEN_DIGIT));
    }

    @Test
    public void testVerifyVersionThirteenWithValidVersion() {
        assertTrue(handler.verifyVersion(13, ISBNVersion.THIRTEEN_DIGIT));
    }

    @Test
    public void testVerifyVersionWithInValidLength() {
        assertFalse(handler.verifyVersion(8, ISBNVersion.THIRTEEN_DIGIT));
    }

    @Test
    public void testVerifyVersionTenWithInvalidVersion() {
        assertFalse(handler.verifyVersion(10, ISBNVersion.THIRTEEN_DIGIT));
    }

    @Test
    public void testVerifyVersionThirteenWithInvalidVersion() {
        assertFalse(handler.verifyVersion(13, ISBNVersion.TEN_DIGIT));
    }

    @Test
    public void testVerifyVersionTenWithInValidLength() {
        assertFalse(handler.verifyVersion(8, ISBNVersion.TEN_DIGIT));
    }

    @Test
    public void testVerifyValidValueWithThirteen() {
        assertTrue(handler.verifyValue("978-0-306-40615-7"));
    }

    @Test
    public void testVerifyValidValueWithThirteenWithoutDashes() {
        assertTrue(handler.verifyValue("9780306406157"));
    }

    @Test
    public void testVerifyInvalidValueWithThirteen() {
        assertFalse(handler.verifyValue("8-697236-447101"));
    }

    @Test
    public void testVerifyInvalidValueWithThirteenWithInvalidChars() {
        assertFalse(handler.verifyValue("9-781617-29223X"));
    }
}

