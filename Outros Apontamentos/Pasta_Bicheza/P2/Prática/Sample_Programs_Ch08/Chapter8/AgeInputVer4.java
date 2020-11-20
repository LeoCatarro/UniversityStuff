/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Class: Class to input age

    File: AgeInputVer4.java

*/

import java.util.*;


class AgeInputVer4 {

    /** Default prompt message */
    private static final String DEFAULT_MESSAGE = "Your age:";

    /** Default constant for the age lower bound */
    private static final int DEFAULT_LOWER_BOUND = 0;

    /** Default constant for the age upper bound */
    private static final int DEFAULT_UPPER_BOUND = 99;

    /** Age lower bound */
    private int lowerBound;

    /** Age upper bound */
    private int upperBound;
    
    /** Scanner for standard input */
    private Scanner scanner;

    /**
     * Default constructor
     */
    public AgeInputVer4( ) {
        init(DEFAULT_LOWER_BOUND, DEFAULT_UPPER_BOUND);
    }

    /**
     * Contructs a age input with the specified lower
     * and upper bounds.
     *
     * @param low  the lower bound of acceptable input values
     * @param high the upper bound of acceptable input values
     */
    public AgeInputVer4(int low, int high)
                throws IllegalArgumentException {

        if (low > high) {
            throw new IllegalArgumentException(
                "Low (" + low + ") was " +
                "larger than high(" + high + ")");
        } else {
            init(low, high);
        }
    }

    /**
     * Inputs the age from an input dialog with
     * default prompt
     */
    public int getAge() throws Exception {

        return getAge(DEFAULT_MESSAGE);
    }

    /**
     * Inputs the age from an input dialog with
     * the designated prompt
     *
     * @param prompt message to prompt the user
     */
    public int getAge(String prompt) throws Exception {

        int    age;

        while (true) {
            System.out.print(prompt);

            try {
                age = scanner.nextInt();

                if (age < lowerBound || age > upperBound) {
                    throw new Exception("Input out of bound");
                }

                return age; //input okay so return the value & exit

            } catch (InputMismatchException e) {

                scanner.next();
                
                System.out.println( "Input is invalid.\n" +
                                    "Please enter digits only");
            }
        }
    }


    /**
     * Sets the lower and upper bounds of the input and
     * creates a new scanner.
     *
     * @param low  the lower bound of acceptable input values
     * @param high the upper bound of acceptable input values
     */
    private void init(int low, int high) {
        lowerBound = low;
        upperBound = high;       
        scanner    = new Scanner(System.in);
    }
}