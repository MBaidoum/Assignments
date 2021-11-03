package com.company;

import java.sql.SQLException;

public interface EmployeeDao {

    boolean verify(String username, String password) throws SQLException;

}
