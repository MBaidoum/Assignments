package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public interface TransferDao {

    void addTransfer(int toAccountID, int fromAccountID, int amount, int customerID) throws SQLException;

    void checkTransfer(int customerID, Scanner scanner) throws SQLException;
}
