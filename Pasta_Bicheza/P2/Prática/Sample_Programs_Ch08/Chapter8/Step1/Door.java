/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Development: Keyless Entry System.

    File: Dorm.java
*/

/**
 * A helper class that simulates the opening
 * of an entrance door for Chapter 8 Sample Development
 *
 */
public class Door {

    /**
     * Default constructor
     */
    public Door( ) {

    }

//-------------------------------------------------
//      Public Methods:
//
//          void    open     (      )
//
//------------------------------------------------

    /**
     * Simulates the opening of an entrance door
     *
     */
    public void open( ) {
        System.out.println("Door is unlocked. \n" +
                           "Welcome to Park Extravaganza\n");
    }
}