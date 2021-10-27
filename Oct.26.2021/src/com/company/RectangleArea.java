package com.company;

import java.util.Scanner;

public class RectangleArea {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("This program will give you the area of a rectangle rounded down to the nearest integer");
        System.out.print("Please input the length: ");
        double length = scanner.nextDouble();
        System.out.println();

        System.out.println("Please input the width: ");
        double width = scanner.nextDouble();
        System.out.println();

        System.out.print("Your rectangle's area is: ");
        int area = (int) (length * width);
        System.out.println(area);
    }
}
