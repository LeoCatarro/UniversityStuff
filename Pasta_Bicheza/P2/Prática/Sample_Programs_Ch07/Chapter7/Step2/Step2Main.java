/*
    Introduction to OOP with Java (5tht Ed), McGraw-Hill

    Wu/Otani
    
    Chapter 7 Sample Development: Library Overdue Checker

    File: Step2/Step2Main.java

*/

import java.util.*;

class Step2Main {

    public static void main( String[] args ) {
        
        //Create 20 Library Book objects
        BookTracker bookTracker = new BookTracker();
        
        GregorianCalendar dueDate, returnDate;
        LibraryBook book;
        
        returnDate = new GregorianCalendar(2008, Calendar.MARCH, 15);
                
        //Check the error condition
        System.out.println("Error: No books added. Return code - " +
                            bookTracker.getCharge(returnDate));
                            
        System.out.println("Output for empty book list:\n" + 
                            bookTracker.getList( ));
        
        //Add 20 books    
        System.out.println("\nAdding 20 books...\n");
        
        for (int i = 0; i < 20; i++) {
            
           dueDate = new GregorianCalendar(2008, Calendar.MARCH, i+1); 
           
           book = new LibraryBook(dueDate);
           book.setTitle("Book Number " + (i+1));
           
           bookTracker.add(book);
            
        }
        
        System.out.println("Total Charge: $" + bookTracker.getCharge(returnDate));
        System.out.println("\n");
        System.out.println("List: \n" + bookTracker.getList());
    }
}