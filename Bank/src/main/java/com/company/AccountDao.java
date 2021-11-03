package com.company;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {

    void addAccount(Account acc) throws SQLException;

    boolean updateAccount(Account acc, int difference) throws SQLException;

    List<Account> getAccounts() throws SQLException;

    Account getAccount(int id) throws SQLException;
}
