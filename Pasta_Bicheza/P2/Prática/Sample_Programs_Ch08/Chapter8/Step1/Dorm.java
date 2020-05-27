/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Development: Keyless Entry System.

    File: Dorm.java
*/

import java.io.*;
import java.util.*;

/**
 *   class Dorm (Helper Class)
 *
 *   The helper class for Chapter 8 Sample Development Program.
 *   A Dorm object maintains a list of Resident objects.
 *
 * @author Dr. Caffeine
 */
public class Dorm  {

//----------------------------------
//    Data Members
//----------------------------------
    private Map<String,Resident> residentTable;


//----------------------------------
//    Constructors
//----------------------------------

    /**
     * Default constructor
     */
    public Dorm( ) {
        residentTable = new HashMap<String,Resident>();
    }

    /**
     * Constructs a new Dorm with
     * the list of Resident initially read
     * from the designated file. If the file
     * does not exist then a FileNotFoundException
     * is thrown. If the contents of the
     * file cannot be read, an IOException is thrown.
     *
     * @param filename the name of the file to open
     *
     * @exception FileNotFoundException
     * @exception IOException
     */
    public Dorm(String filename)
                    throws FileNotFoundException,
                           IOException {

        openFile(filename);
    }


//----------------------------------
//    Public Methods
//
//          void     add            ( Resident  )
//          void     delete         ( Resident  )
//          Resident getResident    ( String    )
//
//          void     openFile       ( String    )
//          void     saveFile       ( String    )
//
//----------------------------------

    /**
     * Adds the passed Resident object to the list.
     * If the Resident's name is already
     * in the list, IllegalArgumentException is
     * thrown.
     *
     * @param resident a Resident object to add to the list
     *
     * @exception IllegalArgumentException when the resident's
     *            name is a duplicate.
     */
    public void add(Resident resident)
                        throws IllegalArgumentException{

        if (residentTable.containsKey(resident.getName())) {
            
            throw new IllegalArgumentException(
                "Resident with the same name already exists");
                
        } else {
            residentTable.put(resident.getName(), resident);
        }
    }


    /**
     * Deletes the passed Resident object from the list.
     * If this resident does not exist in the list, then
     * nothing happens.
     *
     * @param name the name of a Resident object to remove
     *             from the list
     */
    public void delete(String name) {

        residentTable.remove(name);
    }


    /**
     * Returns the Resident object in the list with
     * the given name. Returns null if no
     * matching resident is found in the list.
     *
     * @return the Resident object found in the list;
     *         null if not found
     */
    public Resident getResident(String name) {

        return residentTable.get(name);
    }

    /**
     * Returns the complete list of Residents as
     * a single String. A line terminator separates
     * each resident listing.
     *
     * @return the complete list as a single String.
     *         Returns an empty String if the list
     *         contains no resident.
     *
     */
    public String getResidentList( ) {

        StringBuffer result = new StringBuffer("");

        String tab = "\t";
        String lineSeparator = System.getProperty("line.separator");
        
        for (Resident res : residentTable.values()){
            result.append(res.getName()     + tab +
                          res.getRoom()     + tab +
                          res.getPassword() + tab +
                          lineSeparator);
        }

        return result.toString();
    }



    /**
     * Reads a list of Resident objects
     * from the designated file. If the file
     * does not exist then a FileNotFoundException
     * is thrown. If the contents of the
     * file cannot be read, an IOException is thrown.
     *
     * @param filename the name of the file to open
     *
     * @exception FileNotFoundException
     * @exception IOException
     */
    public void openFile(String filename)
                    throws FileNotFoundException,
                           IOException {

        File inFile = new File(filename); 
        
        FileInputStream inFileStream =
                    new FileInputStream(inFile);
                    
        ObjectInputStream inObjectStream =
                    new ObjectInputStream(inFileStream);

        try {
            residentTable = (Map<String,Resident>) inObjectStream.readObject();
            
        } catch (ClassNotFoundException e) {
            throw new IOException("Unrecognized data in the designated file");
        }

        inObjectStream.close();

    }

    /**
     * Saves the list of Resident objects to the designated file.
     * If the file does not exist or the contents of the
     * file cannot be saved, an exception is thrown.
     *
     * @param filename the name of the file to save the data
     */
    public void saveFile(String filename)
                    throws IOException {

        File outFile = new File(filename);
        
        FileOutputStream outFileStream =
                 new FileOutputStream(outFile);
                 
        ObjectOutputStream outObjectStream =
                 new ObjectOutputStream(outFileStream);

        outObjectStream.writeObject(residentTable);

        outObjectStream.close();
    }

}
