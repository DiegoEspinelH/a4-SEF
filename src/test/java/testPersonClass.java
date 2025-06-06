import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TestPersonClass {
    //valid person will be the object with correct inputs to test the file is successfully written
    private Person validPerson = new Person("73@#!#abPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
    
    private Person shorterIdPerson = new Person("73@#!#bPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
    private Person longerIdPerson = new Person("73@#!#abcPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
    private Person invalidAddressPerson = new Person("73@#!#abcPA", "james", "24|melbourne street|melbourne|Queensland|Australia", "25-05-2001");
    private Person wrongDateFormatPerson = new Person("73@#!#abcPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25/05/2001");
    
    
    //test case 1 will test conditions to add person
    @Test
    public void testAddPerson(){

        //test for correct input | expected: true
        assertTrue(validPerson.addPerson());
        
        //test with bad person ID - not correct length - shorter than 10 chars - len = 9 | expected: false
        assertFalse(shorterIdPerson.addPerson());
        
        //test with bad person ID - not correct length - longer than 10 chars - len = 11 | expected: false
        assertFalse(longerIdPerson.addPerson());
        
        //test with bad address format - state is not Victoria | expected: false
        assertFalse(invalidAddressPerson.addPerson());
        
        //test with wrong date format - use of '/' separators and not '-' |  expected: false
        assertFalse(wrongDateFormatPerson.addPerson());
        
    }

    private Person evenIdPerson = new Person("23@#!#abPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2010");
    private Person underAgePerson = new Person("73@#!#abPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2010");

    
    //test case 2 will test conditions to update personal details
    @Test
    public void testUpdateDetails(){

        //test to do a valid change of details | changed: name and address | expected: true
        assertTrue(validPerson.updatePersonalDetails("73@#!#abPA", "julian", "45|franklin street|melbourne|Victoria|Australia", "25-05-2001"));
        
        //test to check that a person whose id starts with an even number cannot change their id | changed: id | expected: false
        assertFalse(evenIdPerson.updatePersonalDetails("73@#!#abPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001"));
        
        //test to check an underaged person cannot change their address | changed: (valid) address | expected: false
        assertFalse(underAgePerson.updatePersonalDetails("73@#!#abPA", "james", "45|franklin street|melbourne|Victoria|Australia", "25-05-2010"));
        
        //test to change only birthdate | changed: (valid) birthdate | expected: true
        assertFalse(validPerson.updatePersonalDetails("73@#!#abPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "30-05-2001"));
        
        //test to check if birthdate is modified, no other detail can be modified | changed: name and birthdate | expected: false
        assertFalse(validPerson.updatePersonalDetails("73@#!#abPA", "julian", "24|melbourne street|melbourne|Victoria|Australia", "30-05-2001"));
        
        
    }
    
    private Person under21Person = new Person("73@#!#abPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2006");
    private Person over21Person = new Person("73@#!#abPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-1996");
    @Test
    public void testDemeritPoints(){
        
        //test with valid inputs to add demerit points | expected: "Success"
        String validAdd = validPerson.addDemeritPoints("25-05-2025", 3);
        assertEquals(validAdd, "Success");

        //test with invalid date format of infriction | expected: "Failed"
        String invalidDateInfriction = validPerson.addDemeritPoints("25/05/2025", 3);
        assertEquals(invalidDateInfriction, "Failed");

        //test with demerit points out of range | expected: "Failed"
        String invalidDemeritAmount = validPerson.addDemeritPoints("25-05-2025", 7);
        assertEquals(invalidDemeritAmount, "Failed");

        //test to check licence suspension of person under 21 | Input: total of 7 demerit points within 2 years | expected: true
        String suspendUnder21_a = under21Person.addDemeritPoints("25-05-2025", 2);
        String suspendUnder21_b = under21Person.addDemeritPoints("25-04-2025", 4);
        String suspendUnder21_c = under21Person.addDemeritPoints("25-03-2025", 1);
        assertTrue(under21Person.getLicenceStatus());

        //test to check licence suspension of person over 21 | Input: total of 9 demerit points within 2 years | expected: false
        String suspendOver21_a = over21Person.addDemeritPoints("25-05-2025", 3);
        String suspendOver21_b = over21Person.addDemeritPoints("25-04-2025", 3);
        String suspendOver21_c = over21Person.addDemeritPoints("25-03-2025", 3);
        assertFalse(over21Person.getLicenceStatus());

    }



 
}

