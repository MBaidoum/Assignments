package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{

    Connection connection;

    public CustomerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addCustomer(Customer cus) throws SQLException {
        String sql = "insert into customer (username, password) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cus.getUsername());
        preparedStatement.setString(2, cus.getPassword());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Successfully added to the system saved");
        else
            System.out.println("Calamity! Something has gone awry!  Choose a different username");
    }

    @Override
    public int verify(String username, String password) throws SQLException {
        String sql  = "select * from customer where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        return rs.getInt("customerID");
        }

    @Override
    public List<Customer> listCustomers() throws SQLException {
        String sql = "call sp_get_customers()";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet customers = preparedStatement.executeQuery();
        List<Customer> list = new LinkedList<>();
        while(customers.next()) {
            int id = customers.getInt("customerID");
            String username = customers.getString("username");
            String password = customers.getString("password");
            list.add(new Customer(id, username, password));
        }
        return list;
    }
}
