/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Development: Keyless Entry System.

    File: Step2/Ch8EntranceMonitor.java
*/

import java.util.*;
import java.io.*;


/**
 *   class Ch8EntranceMonitor (Main Class - Step 2)
 *
 *   The top-level control object for managing all other
 *   objects.
 *
 * @author Dr. Caffeine
 */
class Ch8EntranceMonitor {

    private Dorm       manager;

    private InputHandler input;

    private Door       door;
    
    private Scanner scanner;

    public Ch8EntranceMonitor( ) {

        manager = new Dorm();
        scanner = new Scanner(System.in);
        input   = new InputHandler();
        door    = new Door();
    }
  
    public static void main(String[] args) {

        Ch8EntranceMonitor sentry = new Ch8EntranceMonitor();
        sentry.start();
    }

    public void start( ) {

        openFile( );

      //  String roster = manager.getResidentList(); //TEMP

      //  System.out.println(roster); //TEMP

        processInputData();
    }


    private void openFile( ) {
        String filename;

        while (true) {
            
            System.out.println("File to open ('x' to cancel):");
            filename = scanner.next();

            if (filename.equals("x")) {//input routine is cancelled
                System.out.println("Program is cancelled.");
                System.exit(0);
            }

            try {
                manager.openFile(filename);
                return;
                
            } catch (FileNotFoundException e) {
                
                System.out.println("No such file");
                
            } catch (IOException e) {
                
                System.out.println("Error in reading file");
            }
        }
    }

    private void processInputData( ) {

        String name, room, pwd;
        
        while (true) {
            
            input.getInput();          
           
            name = input.getName();            
            room = input.getRoom();
            pwd  = input.getPassword();
        
            validate(name, room, pwd);
        }
        
    }

    public void validate(String name, String room, String password) {

        Resident res = manager.getResident(name);

        if (res == null) {
            System.out.println("Invalid Entry");
            
        } else if (res.getName().equals(name) &&
                   res.getRoom().equals(room) &&
                   res.getPassword().equals(password)) {
            door.open();

        } else {
            System.out.println("Invalid Entry");
        }
    }
}