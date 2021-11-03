package com.company;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    void addEmployee(Employee employee) throws SQLException;

    void updateEmployee(Employee employee, int id) throws SQLException;

    void deleteEmployee(int id) throws SQLException;

    List<Employee> getEmployees() throws SQLException;

    Employee getEmployeeById(int id) throws SQLException;
}
