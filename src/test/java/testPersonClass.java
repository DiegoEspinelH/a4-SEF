import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testPersonClass {
    @Test
    public void testAddPerson_testcase1(){
        //test for correct input
        Person testPerson = new Person("73@#!#abcPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
        assertFalse(testPerson.addPerson());
    }
    
}

