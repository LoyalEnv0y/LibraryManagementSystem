package libraryManagementSystem.ISBN;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ISBNHandlerTest {
    private ISBNHandler handler;

    @Before
    public void start() {
        handler = new ISBNHandler("1-61729-223-0", ISBNVersion.TEN_DIGIT);
    }

    @Test
    public void testVerifyValueWithTen() {
        assertTrue(handler.verifyValue("1-61729-223-0"));
    }

    @Test
    public void testVerifyValueWithTen2() {
        assertTrue(handler.verifyValue("3-598-21508-8"));
    }
    @Test
    public void testVerifyValueWithTenIncludingX() {
        assertTrue(handler.verifyValue("3-598-21507-X"));
    }
    @Test
    public void testVerifyValueWithTenWithoutDashes() {
        assertTrue(handler.verifyValue("3598215088"));
    }
}
