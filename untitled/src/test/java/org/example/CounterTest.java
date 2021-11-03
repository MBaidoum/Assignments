package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CounterTest {

    Counter counter;

    @Before
    public void setup() {
        System.out.println("before test");
        counter = new Counter();
    }

    @Test
    public void testForCounterIncrement() {
        System.out.println("increment test");
        //Counter counter = new Counter();
        int countValue = counter.increment();
        assertEquals(countValue, 1);
    }

    @Test
    public void testForCounterDecrement() {
        System.out.println("decrement test");
        //Counter counter = new Counter();
        int countValue = counter.decrement();
        assertEquals(countValue, -1);
    }

    @After
    public void tearDown(){
        System.out.println("after test");
        counter = null;
    }

}
