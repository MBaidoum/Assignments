package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TransferDaoImpl implements TransferDao{

    Connection connection;

    public TransferDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addTransfer(int toAccountID, int fromAccountID, int amount, int customerID) throws SQLException {
        String sql = "select * from account where accountID = ? and customerID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, fromAccountID);
        preparedStatement.setInt(2, customerID);
        ResultSet rs = preparedStatement.executeQuery();
        if(!rs.next()) {
            System.out.println("Something went wrong! Remember that you can't transfer from an account you don't own");
            return;
        } else if(rs.getInt("amount") < amount) {
            System.out.println("You may not transfer more money than you have");
            return;
        }
        sql = "insert into transfer (toAccountID, fromAccountID, amount, customerID) values (?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, toAccountID);
        preparedStatement.setInt(2, fromAccountID);
        preparedStatement.setInt(3, amount);
        preparedStatement.setInt(4, customerID);
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Transfer request made");
        else
            System.out.println("Something has gone terribly wrong");
    }

    @Override
    public void checkTransfer(int customerID, Scanner scanner) throws SQLException {
        TransDaoImpl transDao = new TransDaoImpl(connection);
        String sql = "select t.toAccountID, t.fromAccountID, t.amount, t.customerID, t.transferID from transfer t, " +
                "account a where t.toAccountID = a.accountID and a.customerID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, customerID);
        ResultSet rs = preparedStatement.executeQuery();
        if(!rs.next()) {
            System.out.println("Looks like you have no transfer requests");
            return;
        }
        do {
            System.out.println("Customer number " + rs.getInt("customerID") + " wants to give you " +
                    rs.getInt("amount") + "Bison Dollars");
            System.out.println("Press 1 to accept, 2 to reject, or anything else to skip");
            int answer = scanner.nextInt();
            switch (answer) {
                case 1:
                    transDao.addTransaction(rs.getInt("amount"), rs.getInt("toAccountID"));
                    transDao.addTransaction(-rs.getInt("amount"), rs.getInt("fromAccountID"));
                    deleteTransfer(rs.getInt("transferID"));
                    break;
                case 2:
                    deleteTransfer(rs.getInt("transferID"));
                    break;
                default:
            }
        }while (rs.next());
    }

    private void deleteTransfer(int transferID) throws SQLException {
        String sql = "delete from transfer where transferID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, transferID);
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Transfer removed");
        else
            System.out.println("Something went wrong");
    }
}
