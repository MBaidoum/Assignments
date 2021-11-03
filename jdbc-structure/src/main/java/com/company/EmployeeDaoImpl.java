package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{

    Connection connection;

    public EmployeeDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "insert into employee (name, email) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Employee saved");
        else
            System.out.println("Calamity! Something has gone awry!");

    }

    @Override
    public void updateEmployee(Employee employee, int id) throws SQLException {
        String sql = "update employee set name = ?, email = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        preparedStatement.setInt(3, employee.getId());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Employee changed");
        else
            System.out.println("Calamity! Something has gone awry!");

    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        String sql = "delete from employee where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, Integer.toString(id));
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Employee removed");
        else
            System.out.println("Calamity! Something has gone awry!");

    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        String sql = "select *  from employee";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet employees = preparedStatement.executeQuery();
        List<Employee> list = new LinkedList<Employee>();
        while(employees.next()) {
            int id = employees.getInt("id");
            String name = employees.getString("name");
            String email = employees.getString("email");
            list.add(new Employee(id, name, email));
        }
        return list;
    }

    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "select * from employee where id = 7";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet employees = preparedStatement.executeQuery();
        employees.next();
        String name = employees.getString("name");
        String email = employees.getString("email");
        Employee employee = new Employee(id, name, email);

        return employee;
    }
}
