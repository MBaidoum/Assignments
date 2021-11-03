package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {

    Connection connection;

    public ApplicationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addApplication(Application app) throws SQLException {
        String sql = "insert into application (amount, approvalState, customerID) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, app.getAmount());
        preparedStatement.setString(2, "pending");
        preparedStatement.setInt(3, app.getCustomerID());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Application successfully made!  An employee will review it as soon as possible");
        else
            System.out.println("Something has gone wrong");
    }

    @Override
    public void updateApplication(String state, Application app) throws SQLException {
        String sql = "update application set approvalState = ? where applicationID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, state);
        preparedStatement.setInt(2, app.getApplicationID());
        int count = preparedStatement.executeUpdate();
        if(count > 0 && state == "approved") {
            System.out.println("Application state changed");
            Account acc = new Account();
            acc.setAmount(app.getAmount());
            acc.setCustomerID(app.getCustomerID());
            AccountDaoImpl accDao = new AccountDaoImpl(connection);
            accDao.addAccount(acc);
        } else if(count > 0 && state == "denied")
            System.out.println("Application has been denied");
         else
            System.out.println("Something has gone wrong");
    }

    @Override
    public List<Application> checkApplication() throws SQLException {
        String sql = "select * from application where approvalState = 'pending'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet applications = preparedStatement.executeQuery();
        List<Application> list = new LinkedList<>();
        while(applications.next()) {
            int id = applications.getInt("applicationID");
            int amount = applications.getInt("amount");
            int customerID = applications.getInt("customerID");
            list.add(new Application(id, amount, "pending", customerID));
        }
        return list;
    }
}
