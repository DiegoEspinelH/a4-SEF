import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

    private Person person;

    @Before
    public void setUp() {
        person = new Person("AB@123456Z", "John", "123 High St", "15/04/1990");
    }

    // Tests for isValidID
    @Test
    public void testValidID() {
        assertTrue(person.isValidID("AB@123456Z"));
    }

    @Test
    public void testMissingSpecialChar() {
        assertFalse(person.isValidID("AB123456Z"));
    }

    @Test
    public void testShortID() {
        assertFalse(person.isValidID("A@123Z"));
    }

    @Test
    public void testLowercaseEndID() {
        assertFalse(person.isValidID("AB@123456a"));
    }

    @Test
    public void testValidHashID() {
        assertTrue(person.isValidID("XY#987654X"));
    }

    // Tests for isValidDateFormat
    @Test
    public void testValidDate() {
        assertTrue(person.isValidDateFormat("15/04/1990"));
    }

    @Test
    public void testInvalidDateFormat() {
        assertFalse(person.isValidDateFormat("1990-04-15"));
    }

    @Test
    public void testInvalidDayDate() {
        assertFalse(person.isValidDateFormat("32/01/2000"));
    }

    @Test
    public void testEdgeDate() {
        assertTrue(person.isValidDateFormat("01/01/1900"));
    }

    @Test
    public void testLettersInDate() {
        assertFalse(person.isValidDateFormat("ab/cd/efgh"));
    }

    // Tests for canChangeAddress
    @Test
    public void testSameAddress() {
        assertFalse(person.canChangeAddress("123 Main St", "123 Main St"));
    }

    @Test
    public void testValidAddressChange() {
        assertTrue(person.canChangeAddress("123 Main St", "456 New Ave"));
    }

    @Test
    public void testNullNewAddress() {
        assertFalse(person.canChangeAddress("123 Main St", null));
    }

    @Test
    public void testEmptyNewAddress() {
        assertFalse(person.canChangeAddress("123 Main St", ""));
    }

    @Test
    public void testWhitespaceDiffAddress() {
        assertTrue(person.canChangeAddress("123 Main St", "123  Main St"));
    }
}
