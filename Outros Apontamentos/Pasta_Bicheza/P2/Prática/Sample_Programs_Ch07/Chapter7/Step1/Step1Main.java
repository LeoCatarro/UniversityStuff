/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani
    
    Chapter 7 Sample Development: Library Overdue Checker

    File: Step1/Step1Main.java

*/

import java.util.*;

class Step1Main {

    public static void main( String[] args ) {
       
       //Create three LibraryBook objects and output them
        GregorianCalendar dueDate;
        LibraryBook book1, book2, book3, book4;
        
        dueDate = new GregorianCalendar(2008, Calendar.MARCH, 14);
        book1   = new LibraryBook(dueDate);
        
        dueDate = new GregorianCalendar(2008, Calendar.FEBRUARY, 13);
        book2   = new LibraryBook(dueDate, 0.75);
        book2.setTitle("Introduction to OOP with Java");
        
        dueDate = new GregorianCalendar(2008, Calendar.JANUARY, 12);
        book3   = new LibraryBook(dueDate, 1.00, 100.00);
        book3.setTitle("Java for Smarties");

        dueDate = new GregorianCalendar(2008, Calendar.JANUARY, 1);
        book4   = new LibraryBook(dueDate, 1.50, 230.00, "Me and My Java");
        
        System.out.println(book1.toString());
        System.out.println(book2.toString());
        System.out.println(book3.toString());
        System.out.println(book4.toString());
    }
}