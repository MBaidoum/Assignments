package com.company;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDao {

    void addTransaction(int amount, int customerID) throws SQLException;


    List<Transaction> listTransactions() throws SQLException;
}
