package com.company;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CustomerDaoImplTest {

    @Test
    public void verify() throws SQLException {
        //Arrange
        CustomerDaoImpl customerDao = new CustomerDaoImpl(ConnectionFactory.getConnection());

        //Act
        int actualResult = customerDao.verify("SKerrigan", "0000");

        //Assert
        int expectedResult = 1;
        assertEquals(expectedResult, actualResult);
    }
}
