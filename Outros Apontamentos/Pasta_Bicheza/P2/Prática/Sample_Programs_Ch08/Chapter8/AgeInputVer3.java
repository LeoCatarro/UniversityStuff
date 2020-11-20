/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Class: Class to input age

    File: AgeInputVer3.java

*/

import java.util.*;

class AgeInputVer3 {

    /** Default prompt message */
    private static final String DEFAULT_MESSAGE = "Your age: ";

    /** Scanner for standard input */
    private Scanner scanner;

    /**
     * Default constructor
     */
    public AgeInputVer3( ) {
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

        int    age;

        while (true) {
            System.out.print(prompt);

            try {
                age = scanner.nextInt();

                if (age < 0) {
                    throw new Exception("Negative age is invalid");
                }

                return age; //input okay so return the value & exit

            } catch (InputMismatchException e) {

                scanner.next();
                
                System.out.println( "Input is invalid.\n" +
                                    "Please enter digits only");

            } catch (Exception e) {

                System.out.println("Error: "+ e.getMessage());
            }
        }
    }
}