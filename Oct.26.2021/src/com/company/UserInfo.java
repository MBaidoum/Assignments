package com.company;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserInfo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("What's your name? ");
        String name = scanner.nextLine();
        System.out.println();
        System.out.print("What's your roll number? ");
        String rollNo = scanner.nextLine();
        System.out.println();
        System.out.print("What are you interested in? ");
        String interest = scanner.nextLine();
        System.out.println();

        System.out.println("FOOL!");
        pause(1);
        System.out.println("Your identity has just been STOLEN!");
        pause(2);
        System.out.println("Behold!");
        pause(1);
        System.out.println("Hello, my name is " + name + " and my roll number is " + rollNo + ". My field of interest is "
                + interest + ".");
        pause(4);
        System.out.println("You made a mistake trusting me.");
    }

    public static void pause(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
