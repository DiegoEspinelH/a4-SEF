import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPersonClass {
    @Test
    public void testAddPerson_testcase1(){
        //test for correct input
        Person testPerson1 = new Person("73@#!#abPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
        assertTrue(testPerson1.addPerson());

        //test with bad person ID - not correct length - shorter than 10 chars - len = 9
        Person testPerson2 = new Person("73@#!#bPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
        assertFalse(testPerson2.addPerson());
        
        //test with bad person ID - not correct length - longer than 10 chars - len = 11
        Person testPerson3 = new Person("73@#!#abcPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
        assertFalse(testPerson3.addPerson());


    }   
    
}

