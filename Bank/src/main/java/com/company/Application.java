package com.company;

//Class that creates the application object which mirrors a row on the application table
public class Application {

    private int applicationID;
    private int amount;
    private String approvalState;
    private int customerID;

    public Application() {}

    public Application(int applicationID, int amount, String approvalState, int customerID) {
        this.applicationID = applicationID;
        this.amount = amount;
        this.approvalState = approvalState;
        this.customerID = customerID;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(String approvalState) {
        this.approvalState = approvalState;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
