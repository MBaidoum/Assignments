package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringExampleTest {

    @Test
    public void testForCompareFirstAndLastCharactersAreSame(){
        StringExample se = new StringExample();
        boolean actialResult = se.compareFirstAndLastCharctersAreSame("ABAB");
        assertEquals(actialResult, true);
//        assertTrue();
//        assertFalse();

    }

}
