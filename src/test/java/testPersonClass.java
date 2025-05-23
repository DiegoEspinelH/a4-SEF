import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPersonClass {
    //test case 1 will test conditions of person ID to add person
    @Test
    public void testAddPerson_testcase1(){
        //test for correct input | expected: true
        Person testPerson1 = new Person("73@#!#abPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
        assertTrue(testPerson1.addPerson());

        //test with bad person ID - not correct length - shorter than 10 chars - len = 9 | expected: false
        Person testPerson2 = new Person("73@#!#bPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
        assertFalse(testPerson2.addPerson());
        
        //test with bad person ID - not correct length - longer than 10 chars - len = 11
        Person testPerson3 = new Person("73@#!#abcPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
        assertFalse(testPerson3.addPerson());

        //test with bad person ID - first two digits not numbers | expected: false
        Person testPerson4 = new Person("7b@#!#abcPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
        assertFalse(testPerson4.addPerson());

        //test with bad person ID -  not enough special chars in ID (less than two) in indexes 3 to 8 | expected: false
        Person testPerson5 = new Person("73@#!aabcPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
        assertFalse(testPerson5.addPerson());
        
        //test with bad person ID -  one of last two chars is not uppercase | expected: false
        Person testPerson6 = new Person("73@#!#abcpA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
        assertFalse(testPerson6.addPerson());

        //test with bad person ID -  both of last two chars is not uppercase | expected: false
        Person testPerson7 = new Person("73@#!#abcpa", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
        assertFalse(testPerson7.addPerson());
    }


 
}

