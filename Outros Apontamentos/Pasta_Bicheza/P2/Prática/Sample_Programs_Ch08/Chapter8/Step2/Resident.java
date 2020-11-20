/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Development: Keyless Entry System.

    File: Step1/Resident.java
*/

import java.io.*;

/**
 *   class Resident (Step 1)
 *
 *   An instance of this class represents a dorm resident.
 *
 * @author Dr. Caffeine
 */
class Resident implements Serializable {

//----------------------------------
//    Data Members
//----------------------------------

    /**
     * The name of this resident
     */
    private String  name;

    /**
     * The room of this resident
     */
    private String  room;

    /**
     * The password assigned to this resident
     */
    private String  password;


//----------------------------------
//    Constructors
//----------------------------------

    /**
     * Default constructor
     */
    public Resident( ) {
        this("unassigned", "000", "XY12#$ab");
    }

    /**
     * Constructs a new Resident with passed name,
     * room, and password.
     *
     * @param name   name of the new Resident
     * @param room   room of the new Resident
     * @param pwd    password of the new Resident
     *
     * @exception IllegalArgumentException if pwd
     *            has less than four or more than
     *            eight characters.
     */
    public Resident(String name, String room, String pwd)
                throws IllegalArgumentException {
        setName(name);
        setRoom(room);
        setPassword(pwd);
    }

//-------------------------------------------------
//      Public Methods:
//
//          String  getName      (            )
//          String  getPassword  (            )
//          String  getRoom      (            )
//
//          void    setName      (   String   )
//          void    setPassword  (   String   )
//          void    setRoom      (   String   )
//
//------------------------------------------------



    /**
     * Returns the name of this Resident.
     *
     * @return the name of this Resident
     */
    public String getName( ) {
        return name;
    }


    /**
     * Returns the password of this Resident.
     *
     * @return the password of this Resident
     */
    public String getPassword( ) {
        return password;
    }


    /**
     * Returns the room of this Resident.
     *
     * @return the room of this Resident
     */
    public String getRoom( ) {
        return room;
    }



    /**
     * Sets the name of this Resident.
     *
     * @param name this Resident's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the ID of this Resident.
     *
     * @param ID this Resident's ID
     */
    public void setPassword(String pwd) {
        int length = pwd.length();

        if (length < 8) {
            throw new IllegalArgumentException();
        } else {
            this.password = pwd;
        }
    }


    /**
     * Sets the room of this Resident.
     *
     * @param room this Resident's room
     */
    public void setRoom(String room) {
        this.room = room;
    }

}