/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Class: Class to input age

    File: AgeInputVer2.java

*/

import java.util.*;

class AgeInputVer2 {

    /** Default prompt message */
    private static final String DEFAULT_MESSAGE = "Your age: ";

    /** Scanner for standard input */
    private Scanner scanner;

    /**
     * Default constructor
     */
    public AgeInputVer2( ) {
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
                return age;

            } catch (InputMismatchException e) {
                
                scanner.next(); //remove the leftover garbage 
                                //from the input buffer
                
                System.out.println("Invalid Entry. Please enter digits only.");
          
            }
        }
    }
}