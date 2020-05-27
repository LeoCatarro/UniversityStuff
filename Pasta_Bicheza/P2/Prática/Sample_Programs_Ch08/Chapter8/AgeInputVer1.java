/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Class: Class to input age

    File: AgeInputVer1.java

*/

import java.util.*;

class AgeInputVer1 {

    /** Default prompt message */
    private static final String DEFAULT_MESSAGE = "Your age: ";
    
    /** Scanner for standard input */
    private Scanner scanner;

    /**
     * Default constructor
     */
    public AgeInputVer1( ) {
        scanner = new Scanner(System.in);
    }

    /**
     * Inputs the age from an input dialog with
     * default prompt
     */
    public int getAge() {

        return getAge(DEFAULT_MESSAGE);
    }

    /**
     * Inputs the age from an input dialog with
     * the designated prompt
     *
     * @param prompt message to prompt the user
     */
    public int getAge(String prompt) {

        System.out.print(prompt);

        int age = scanner.nextInt();

        return age;
    }
}