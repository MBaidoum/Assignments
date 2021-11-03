package com.company;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

    void addCustomer(Customer cus) throws SQLException;

    int verify(String username, String password) throws SQLException;

    List<Customer> listCustomers() throws SQLException;
}
