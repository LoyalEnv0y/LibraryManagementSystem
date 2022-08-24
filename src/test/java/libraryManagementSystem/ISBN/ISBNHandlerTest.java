package libraryManagementSystem.ISBN;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ISBNHandlerTest {
    private ISBNHandler handler;

    @Before
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
        assertEquals("3125657512", handler.formatValue("\n3-12-   565751-2\t"));
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
    public void testVerifyInvalidValueWithTenWithoutDashes() {
        assertFalse(handler.verifyValue("1617292232"));
    }

    @Test
    public void testVerifyInvalidValueWithTenIncludingX() {
        assertFalse(handler.verifyValue("1-61729-223-X"));
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
}

