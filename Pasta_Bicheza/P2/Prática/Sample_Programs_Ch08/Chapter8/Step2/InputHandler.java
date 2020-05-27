/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Development: Keyless Entry System

    File: Step2/InputHandler.java

*/

import java.util.*;


/**
 *  InputHandler class
 *
 * <p>
 * User interface for the resident to enter his/her
 * name, room number, and password
 *
 */
class InputHandler  {

//----------------------------------
//    Data Members
//----------------------------------

    /**
     * Constant for empty input
     */
    private static final String BLANK = "";

    /**
     * Name entered by the user
     */
    private String name;

    /**
     * Room entered by the user
     */
    private String room;

    /**
     * Password entered by the user
     */
    private String pwd;
    
    /**
     * Scanner for inputting data
     */
    private Scanner scanner;


//----------------------------------
//    Constructor
//----------------------------------

    /**
     * Default constructor
     */
    public InputHandler( ) {

        name = BLANK;
        room = BLANK;
        pwd  = BLANK;
        
        scanner = new Scanner(System.in);
    }

//---------------------------------------------
//
//      Public Methods
//
//
//---------------------------------------------

    /**
     * Inputs three pieces of information using
     * JOptionPane.
     */
    public void getInput( ) {
        
        System.out.print("Enter Name:");
        name = scanner.next();       
        
        System.out.print("Enter Room No.:");
        room = scanner.next();
        
        System.out.print("Enter Password:");
        pwd  = scanner.next(); 
    }

    /**
     * Returns the entered name.
     *
     * @return the entered name
     */
    public String getName( ) {

        return name;      
    }

    /**
     * Returns the entered room number.
     *
     * @return the entered room number
     */
    public String getRoom( ) {
        
        return room;        
    }

    /**
     * Returns the entered password.
     *
     * @return the entered password
     */
    public String getPassword( ) {
        
        return pwd;
    }
}