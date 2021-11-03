package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AccountDaoImpl implements AccountDao{

    Connection connection;

    public AccountDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void addAccount(Account acc) throws SQLException {
        String sql = "insert into account (amount, customerID) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, acc.getAmount());
        preparedStatement.setInt(2, acc.getCustomerID());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Account successfully added");
        else
            System.out.println("Something has gone wrong");

    }

    @Override
    public boolean updateAccount(Account acc, int difference) throws SQLException {
        if(acc.getAmount() - difference < 0) {
            System.out.println("Illegal transaction; please enter an amount less or equal to your account total");
            return false;
        }

        String sql = "update account set amount = ? where accountID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, acc.getAmount() - difference);
        preparedStatement.setInt(2, acc.getAccountID());
        int count = preparedStatement.executeUpdate();
        if(count > 0) {
            System.out.println("Transaction made successfully");
            return true;
        } else {
            System.out.println("Something has gone wrong");
            return false;
        }
    }

    @Override
    public List<Account> getAccounts() throws SQLException {
        String sql = "select *  from account";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet account = preparedStatement.executeQuery();
        List<Account> list = new LinkedList<>();
        while(account.next()) {
            int accountID = account.getInt("accountId");
            int amount = account.getInt("amount");
            int customerID = account.getInt("customerID");
            list.add(new Account(accountID, amount, customerID));
        }
        return list;
    }

    @Override
    public Account getAccount(int id) throws SQLException {
        String sql = "select *  from account where accountID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet account = preparedStatement.executeQuery();
        account.next();
        int accountID = account.getInt("accountID");
        int amount = account.getInt("amount");
        int customerID = account.getInt("customerID");
        return new Account(accountID, amount, customerID);
    }
}
