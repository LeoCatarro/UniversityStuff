/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 7 Sample Development: Library Overdue Checker

    File: BookTracker.java
    
    This is a Helper class provided to the client programmers to manage 
    a collection of LibraryBook objects.

*/

import java.util.*;

/**
 * This class keeps track of LibraryBook objects.
 * To use this class you must
 * define a class named LibraryBook and provide the following methods:
 * <li>
 * public double  getCharge(Calendar dueDate) 
 * public String  toString( )
 *
 * @author Dr Caffeine
 */
class BookTracker {
    
    /** Error condition for asking total charge when no book is added */
    public static final int ERROR = -1;
    
    /** Maintains a list of library books */
    private List<LibraryBook> books;

//-----------------------------------------
//
//    Constructors:
//
//-----------------------------------------

    /**
     * No-argument Constructor.
     *
     */   
    public BookTracker( ) {
        
        books = new LinkedList<LibraryBook>();
    }
    
//-------------------------------------------------
//      Public Methods:
//
//           public void    add(LibraryBook)
//
//           public double  getCharge( )
//           public double  getCharge(Calendar )
//
//           public String  getList( )
//
//------------------------------------------------

    /** 
     * Adds the book to the list
     *
     * @param the book to add to the list
     */
    public void add(LibraryBook book) {
        books.add(book);
    }

    /**
     * Returns the total charge of the overdue books. Return
     * date is set to today.
     *
     * @return the total charge. ERROR if no books are entered
     */
    public double getCharge( ) {
        return getCharge(new GregorianCalendar()); //set today as due date
    }
    
    /**
     * Returns the total charge of the overdue books. 
     * 
     * @param returnDate date the books are returned.
     *
     * @return the total charge. ERROR if no books are entered
     */
    public double getCharge(GregorianCalendar returnDate) {
        
        if (books.isEmpty()) {
            return ERROR;
        } else {
            return totalCharge(returnDate);
        }
    }
    
    /**
     * Returns a list of books with its data.
     * 
     *
     * @return the summary book list.
     */
    public String getList( ) {
        
        StringBuffer result = new StringBuffer("");
        
        String lineSeparator = System.getProperty("line.separator");
        
//        Iterator itr = books.iterator( );
//        
//        while (itr.hasNext()) {
//            
//            LibraryBook book = (LibraryBook) itr.next();
//            
//            result.append(book.toString() + lineSeparator);
//        }
        
        for (LibraryBook book: books) {
            result.append(book.toString() + lineSeparator);
        }
        
        return result.toString();
    }
    
//-------------------------------------------------
//      Private Methods:
//
//           public double  totalCharge( )
//
//------------------------------------------------ 
   
    /**
     * Computes the total charge of overdue for the books
     * in the list
     *
     * @param returnDate date the books are returned
     *
     * @return the total charge of overdue books
     */
    private double totalCharge(GregorianCalendar returnDate) {
        double totalCharge = 0.0;
        
//        Iterator itr = books.iterator( );
//        
//        while (itr.hasNext()) {
//            
//            LibraryBook book = (LibraryBook) itr.next();
//            
//            totalCharge += book.computeCharge(returnDate);
//        }
        
        for (LibraryBook book: books) {
            totalCharge += book.computeCharge(returnDate);
        }
        return totalCharge;
    }  
}
