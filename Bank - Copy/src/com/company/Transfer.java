package com.company;

public class Transfer {

    private int transferID;
    private int toAccount;
    private int fromAccount;
    private int amount;
    private int customerID;

    public Transfer() {}

    public Transfer(int transferID, int toAccount, int fromAccount, int amount, int customerID) {
        this.transferID = transferID;
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.amount = amount;
        this.customerID = customerID;
    }

    public int getTransferID() {
        return transferID;
    }

    public void setTransferID(int transferID) {
        this.transferID = transferID;
    }

    public int getToAccount() {
        return toAccount;
    }

    public void setToAccount(int toAccount) {
        this.toAccount = toAccount;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
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
