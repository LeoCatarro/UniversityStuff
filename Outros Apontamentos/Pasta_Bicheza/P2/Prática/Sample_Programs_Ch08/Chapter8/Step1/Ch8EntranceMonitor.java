/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Development: Keyless Entry System.

    File: Step1/Ch8EntranceMonitor.java
*/


import java.io.*;
import java.util.*;

/**
 *   class Ch8EntranceMonitor (Main Class - Step 1)
 *
 *   The top-level control object for managing all other
 *   objects.
 *
 * @author Dr. Caffeine
 */
class Ch8EntranceMonitor {

    private Dorm manager;
    
    private Scanner scanner;
    
    
    public static void main(String[] args) {

        Ch8EntranceMonitor sentry = new Ch8EntranceMonitor();
        sentry.start();
    }

    public Ch8EntranceMonitor( ) {

        manager = new Dorm();
        scanner = new Scanner(System.in);
    }
    
    public void start( ) {

        openFile( );

        String roster = manager.getResidentList();

        System.out.println(roster);
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


}