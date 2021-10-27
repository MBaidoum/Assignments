package com.company;

import java.util.Scanner;

public class TheeNumbers {

    //Public class that has one method for averaging three numbers and returns that average as a double
    public class Average {

        public static double average(double a, double b, double c) {
            return (a + b + c) / 3;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("This program will give the average of three numbers");
        System.out.print("Enter the first number ");
        double first = scanner.nextDouble();
        System.out.println();
        System.out.print("Enter the second number ");
        double second = scanner.nextDouble();
        System.out.println();
        System.out.print("Enter the third number ");
        double third = scanner.nextDouble();
        System.out.println();

        System.out.println("Your average is: " + Average.average(first, second, third));
    }
}
