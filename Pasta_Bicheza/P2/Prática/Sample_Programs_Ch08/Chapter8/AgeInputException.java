/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Class: Customized Exception Class

    File: AgeInputException.java

*/

class AgeInputException extends Exception {

    /** Default prompt message */
    private static final String DEFAULT_MESSAGE = "Input out of bounds";

    /** Lower bound of age input */
    private int lowerBound;

    /** Upper bound of age input */
    private int upperBound;

    /** Entered value */
    private int value;

    /**
     * Default constructor
     *
     * @param low   the lower bound of age input
     * @param high  the upper bound of age input
     * @param input the input value
     */
    public AgeInputException(int low, int high, int input) {
        this(DEFAULT_MESSAGE, low, high, input);
    }

    /**
     * Constructor
     *
     * @apram msg   the error message
     * @param low   the lower bound of age input
     * @param high  the upper bound of age input
     * @param input the input value
     */
    public AgeInputException(String msg,
                             int low, int high, int input) {
        super(msg);

        if (low > high) {
            throw new IllegalArgumentException();
        }

        lowerBound = low;
        upperBound = high;
        value      = input;
    }

    /**
     * Returns the lower bound of the age input
     */
    public int lowerBound() {

        return lowerBound;
    }

    /**
     * Returns the upper bound of the age input
     */
    public int upperBound() {

        return  upperBound;
    }

    /**
     * Returns the entered value
     *
     * @return the entered value
     */
    public int value() {
        return value;
    }

}