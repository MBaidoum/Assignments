package com.company;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        // write your code here
//        Employee employee  = new Employee();
//        employee.setName("Obama");
//        employee.setEmail("o@gmail.com");

        //Employee Dao
        EmployeeDao dao = EmployeeDaoFactory.getEmployeeDao();
        //dao.addEmployee(employee);
//        dao.updateEmployee(employee, 1);
        //dao.deleteEmployee(5);

        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while(flag) {
            System.out.println("Press the buttons");
            System.out.println("Press 1 to add a dude");
            System.out.println("Press 2 to change a dude");
            System.out.println("Press 3 to ice a dude");
            System.out.println("Press 4 to get all dudes");
            System.out.println("Press 5 to get a specific dude");
            System.out.println("Press 6 to get out of here dude");

            int input = 5;//scanner.nextInt();
            //scanner.nextLine();
            switch (input){
                case 1:
                    //Add
                    System.out.println("Put first name:");
                    String firstName1 = scanner.nextLine();
                    System.out.println("Put email:");
                    String email1 = scanner.nextLine();
                    Employee employee1 = new Employee();
                    employee1.setName(firstName1);
                    employee1.setEmail(email1);
                    dao.addEmployee(employee1);
                    break;
                case 2:
                    //update
                    System.out.println("Pick the id of the dud you want to change forever");
                    int id2 = scanner.nextInt();
                    System.out.println("Put first name:");
                    String firstName2 = scanner.nextLine();
                    System.out.println("Put email:");
                    String email2 = scanner.nextLine();
                    Employee employee2 = new Employee();
                    employee2.setName(firstName2);
                    employee2.setEmail(email2);
                    dao.updateEmployee(employee2, id2);
                    break;
                case 3:
                    System.out.println("Pick the id of the dude you want to ice");
                    int id3 = scanner.nextInt();
                    dao.deleteEmployee(id3);
                    break;
                case 4:
                    List<Employee> employees = dao.getEmployees();
                    while(employees.size() > 0) {
                        Employee employee4 = employees.remove(0);
                        System.out.println("Employee number " + employee4.getId() + " is " + employee4.getName() + " and " +
                                "their email is " + employee4.getEmail());
                    }
                    break;
                case 5:
                    System.out.println("Enter the id number for your preferred dude");
                    int id5 = scanner.nextInt();
                    Employee employee5 = dao.getEmployeeById(id5);
                    System.out.println("Employee number " + employee5.getId() + " is " + employee5.getName() + " and " +
                            "their email is " + employee5.getEmail());
                    break;
                case 6:
                    System.out.println("Bye Bye");
                    flag = false;
                    break;
                default:
                    System.out.println("You gotta give me a number between 1 and 6");
            }
            System.out.println();
        }


    }
}
