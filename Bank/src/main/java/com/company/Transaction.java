package com.company;

//Class that creates the transaction object which mirrors a row on the transaction table
public class Transaction {

    private int transactionID;
    private int amount;
    private int accountID;

    public Transaction() {}

    public Transaction(int transactionID, int amount, int accountID) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.accountID = accountID;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
