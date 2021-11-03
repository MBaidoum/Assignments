package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TransDaoImpl implements TransactionDao{

    Connection connection;

    public TransDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addTransaction(int amount, int accountID) throws SQLException {
        AccountDaoImpl accDao = new AccountDaoImpl(connection);
        Account acc = accDao.getAccount(accountID);
        if(!accDao.updateAccount(acc, amount))
            return;
        String sql = "insert into transaction (amount, accountID) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, -amount);
        preparedStatement.setInt(2, accountID);
        int count = preparedStatement.executeUpdate();
        if(count <= 0)
            System.out.println("Calamity! Something has gone awry!");

    }

    @Override
    public List<Transaction> listTransactions() throws SQLException {
        String sql = "select * from transaction";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet transaction = preparedStatement.executeQuery();
        List<Transaction> list = new LinkedList<>();
        while(transaction.next()) {
            int id = transaction.getInt("transactionID");
            int amount = transaction.getInt("amount");
            int accountID = transaction.getInt("accountID");
            list.add(new Transaction(id, amount, accountID));
        }
        return list;
    }
}
