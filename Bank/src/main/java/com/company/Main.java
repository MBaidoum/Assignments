package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the bank of M. Bison, home of the Bison Dollar.");
        System.out.println("Please enter the number that corresponds to your desired action.");
        while(true) {
            System.out.println();
            System.out.println("1 - Login (Customer)");
            System.out.println("2 - Login (Employee)");
            System.out.println("3 - Sign Up");
            System.out.println("4 - Close the system");
            int input = scanner.nextInt();
            scanner.nextLine();
            try {
                switchCases(input, scanner);
            } catch (SQLException e) {
                e.printStackTrace();
                //System.out.println("Seems like you asked for something that didn't exist. Check your inputs for next time");
            }
        }

    }

    //Holds all the switch cases that determine the kind of user
    public static void switchCases(int input, Scanner scanner) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        EmployeeDaoImpl empDao = new EmployeeDaoImpl(connection);
        CustomerDaoImpl cusDao = new CustomerDaoImpl(connection);
        switch (input){
            case 1:
                System.out.println("Please enter your username");
                String username1 = scanner.nextLine();
                System.out.println("Please enter your password");
                String password1 = scanner.nextLine();
                int customerID = cusDao.verify(username1, password1);

                if(customerID > 0) {
                    customerActions(scanner, connection, customerID);
                } else {
                    System.out.println("Incorrect username or password, please try again");
                }
                break;
            case 2:
                System.out.println("Please enter your username");
                String username2 = scanner.nextLine();
                System.out.println("Please enter your password");
                String password2 = scanner.nextLine();
                boolean login2 = empDao.verify(username2, password2);

                if(login2) {
                    employeeActions(scanner, connection);
                } else {
                    System.out.println("Something went wrong, please try again");
                }
                break;
            case 3:
                System.out.println("Please pick a username");
                String username3 = scanner.nextLine();
                System.out.println("Please pick a password");
                String password3 = scanner.nextLine();
                Customer cus = new Customer();
                cus.setUsername(username3);
                cus.setPassword(password3);
                cusDao.addCustomer(cus);
                break;
            case 4:
                System.out.println("Thank you and come again!");
                System.exit(0);
            default:
                System.out.println("Please enter a number between 1 and 4.");
        }
    }

    //Contains the switch statement that has all the customer actions
    public static void customerActions(Scanner scanner, Connection connection, int customerID) throws SQLException {
        AccountDaoImpl accDao = new AccountDaoImpl(connection);
        TransDaoImpl transDao = new TransDaoImpl(connection);
        TransferDaoImpl transferDao = new TransferDaoImpl(connection);
        System.out.println("Welcome valued customer! What would you like to do");

        boolean flag = true;
        while(flag) {
            System.out.println("1 - Apply for a new account");
            System.out.println("2 - View the balance of one of your accounts");
            System.out.println("3 - Withdraw");
            System.out.println("4 - Deposit");
            System.out.println("5 - Post a transfer request");
            System.out.println("6 - Accept a transfer request");
            System.out.println("7 - Return to main menu");
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1:
                    ApplicationDaoImpl appDao = new ApplicationDaoImpl(connection);
                    Application app = new Application();
                    app.setCustomerID(customerID);
                    System.out.println("How much would you like to open your account with");
                    int amount = scanner.nextInt();
                    scanner.nextLine();
                    app.setAmount(amount);
                    appDao.addApplication(app);
                    break;

                case 2:
                    List<Account> accounts = accDao.getAccounts();
                    Iterator<Account> iterator = accounts.listIterator();
                    System.out.println("You have access to the following accounts:");
                    boolean atLeastOne = false;
                    while(iterator.hasNext()){
                        Account account = iterator.next();
                        if(account.getCustomerID() != customerID) {
                            continue;
                        }
                        System.out.println("AccountID:" + account.getAccountID());
                        atLeastOne = true;
                    }
                    if(!atLeastOne) {
                        System.out.println("Nothing");
                        System.out.println("You don't have any accounts!");
                        break;
                    }
                    System.out.println("Which one would you like to view?");
                    input = scanner.nextInt();
                    scanner.nextLine();
                    Account account = accDao.getAccount(input);
                    System.out.println("This account has " + account.getAmount() + " Bison Dollars");
                    break;
                case 3:
                    System.out.println("Which account would you like to withdraw from");
                    int accountID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("How much money would you like to withdraw");
                    int amount3 = scanner.nextInt();
                    scanner.nextLine();
                    if(amount3 <= 0){
                        System.out.println("Amount must be positive");
                        return;
                    }
                    transDao.addTransaction(amount3, accountID);
                    break;
                case 4:
                    System.out.println("Which account would you like to deposit to");
                    int accountID4 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("How much money would you like to deposit");
                    int amount4 = scanner.nextInt();
                    scanner.nextLine();
                    if(amount4 <= 0){
                        System.out.println("Amount must be positive");
                        return;
                    }
                    transDao.addTransaction(-amount4, accountID4);
                    break;
                case 5:
                    System.out.println("Which account would you like to send the money to");
                    int toAccount = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Which account of your would you like to take the money from");
                    int fromAccount = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("How much money to you want to transfer");
                    int amount5 = scanner.nextInt();
                    scanner.nextLine();
                    if(amount5 < 0){
                        System.out.println("Amount must be positive");
                        return;
                    }
                    transferDao.addTransfer(toAccount, fromAccount, amount5, customerID);
                    break;
                case 6:
                    transferDao.checkTransfer(customerID, scanner);
                    break;
                case 7:
                    flag = false;
                default:
                    System.out.println("Please enter a number between 1 and 7");
            }
        }

    }

    //Contains the switch statement that has all the employee actions
    public static void employeeActions(Scanner scanner, Connection connection) throws SQLException {
        AccountDaoImpl accDao = new AccountDaoImpl(connection);
        System.out.println("Welcome valued employee! What would you like to do");
        boolean flag = true;
        while(flag) {
            System.out.println("1 - Evaluate pending account applications");
            System.out.println("2 - View customer accounts");
            System.out.println("3 - View transaction log");
            System.out.println("4 - Return to main menu");
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1:
                    ApplicationDaoImpl appDao = new ApplicationDaoImpl(connection);
                    List<Application> apps;
                    apps = appDao.checkApplication();

                    ListIterator<Application> pendingList = apps.listIterator();
                    int skipped = 0;
                    while (pendingList.hasNext()) {
                        Application app = pendingList.next();
                        System.out.println("Customer number " + app.getCustomerID() + " has requested to open an account" +
                                " with " + app.getAmount() + " Bison Dollars in it");
                        System.out.println("Press 1 to approve, 2 to deny, or any other number to skip this application");
                        Account account = new Account();
                        account.setAmount(app.getAmount());
                        account.setCustomerID(app.getCustomerID());
                        input = scanner.nextInt();
                        scanner.nextLine();
                        if (input == 1) {

                            //accDao.addAccount(account);

                            appDao.updateApplication("approved", app);
                        } else if (input == 2) {
                            appDao.updateApplication("denied", app);
                        } else {
                            System.out.println("Application skipped");
                            skipped++;
                        }
                    }
                    System.out.println("There are/is " + skipped + " applications pending");
                    break;
                case 2:
                    CustomerDaoImpl cus = new CustomerDaoImpl(connection);
                    List<Customer> list = cus.listCustomers();
                    while (!list.isEmpty()) {
                        Customer customer = list.remove(0);
                        System.out.println("CustomerId number " + customer.getCustomerID() + " belongs to user "
                                + customer.getUsername());
                    }
                    break;
                case 3:
                    TransDaoImpl transDao = new TransDaoImpl(connection);
                    List<Transaction> list2 = transDao.listTransactions();
                    if(list2.isEmpty()) {
                        System.out.println("There are no transactions to view");
                        break;
                    }
                    while (!list2.isEmpty()) {
                        Transaction transaction = list2.remove(0);
                        Account account = accDao.getAccount(transaction.getAccountID());
                        System.out.println("In account number " + transaction.getAccountID() + " belonging to user number "
                                + account.getCustomerID() + " there was an balance change of " + transaction.getAmount());
                    }
                    break;
                case 4:
                    System.out.println("Returning to the main menu");
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter a number between 1 and 4");
            }
        }
    }

}
