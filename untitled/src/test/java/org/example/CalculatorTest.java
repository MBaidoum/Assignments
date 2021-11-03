package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void addNumberTest() {
        // Arrange
        Calculator cal = new Calculator();

        // Act
        int actualResult = cal.addNumber(10, 20);

        // Assert
        int expectedResult = 30;
        assertEquals(expectedResult, actualResult);
    }
}
