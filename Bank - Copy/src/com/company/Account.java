package com.company;

public class Account {

    private int accountID;
    private int amount;
    private int customerID;

    public Account() {
    }

    public Account(int accountID, int amount, int customerID) {
        this.accountID = accountID;
        this.amount = amount;
        this.customerID = customerID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
