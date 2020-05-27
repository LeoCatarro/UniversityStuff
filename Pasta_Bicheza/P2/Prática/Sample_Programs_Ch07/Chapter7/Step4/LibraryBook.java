/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 7 Sample Development: Library Overdue Checker (Step 4)

    File: Step4/BookTracker.java
    
    Implements the computation for overdue charge

*/

import java.util.*;


/**
 * An instance of this class represents a single library book.
 *
 * @author Dr Caffeine
 */
class LibraryBook {

    /** Conversion factor from milliseconds to days */
    private static final double MILLISEC_TO_DAY = 1.0 / 1000 / 60 / 60 / 24;

    /** Default charge per day */
    private static final double CHARGE_PER_DAY = 0.50;

    /** Default maximum charge */
    private static final double MAX_CHARGE = 50.00;    
    
    /** Default title of the book */
    private static final String DEFAULT_TITLE = "Title unknown";
 
    /** Due date of the book */
    private GregorianCalendar dueDate;   
    
    /** Title of the book (optional) */
    private String title;
    
    /** Charge due per overdue day */
    private double chargePerDay;
    
    /** Maximum charge for the overdue */
    private double maximumCharge;
    
//-----------------------------------------
//
//    Constructors:
//
//-----------------------------------------

    /**
     * Constructor.
     *
     * @param dueDate the due date of this book
     *
     */
    public LibraryBook(GregorianCalendar dueDate) { 
        
        this(dueDate, CHARGE_PER_DAY);
    }
    
    /**
     * Constructor.
     *
     * @param dueDate the due date of this book
     * @param chargePerDay charge per overdue day
     *
     */
    public LibraryBook(GregorianCalendar dueDate, 
                       double chargePerDay) {
                           
        this(dueDate, chargePerDay, MAX_CHARGE);
    }

    
    /**
     * Constructor.
     *
     * @param dueDate       the due date of this book
     * @param chargePerDay  charge per overdue day
     * @param maximumCharge the maximum possible charge
     * @param title         the title of this book
     *
     */
    public LibraryBook(GregorianCalendar dueDate, 
                       double chargePerDay, 
                       double maximumCharge) {
                            
        this(dueDate, chargePerDay, maximumCharge, DEFAULT_TITLE);
    }
    
    /**
     * Constructor.
     *
     * @param dueDate       the due date of this book
     * @param chargePerDay  charge per overdue day
     * @param maximumCharge the maximum possible charge
     * @param title         the title of this book
     *
     */
    public LibraryBook(GregorianCalendar dueDate, 
                       double chargePerDay, 
                       double maximumCharge,
                       String title) {
                            
        setDueDate(dueDate);
        setChargePerDay(chargePerDay);
        setMaximumCharge(maximumCharge);
        setTitle(title);
    }

//-------------------------------------------------
//      Public Methods:
//
//          double            computeCharge( )
//
//          double            getChargePerDay( )
//          GregorianCalendar getDueDate( )
//          double            getMaxCharge( )
//          String            getTitle( )
//
//          void              setChargePerDay(double)
//          void              setDueDate(GregorianCalendar )
//          void              setMaxCharge(double)
//          void              setTitle(String)
//
//          String            toString( )
//
//------------------------------------------------

     /**
     * Computes the overdue charge for the specified
     * return date
     *
     * @param returnDate the date the book is/to be returned
     *
     * @return the amount of overdue charge
     */
    public double computeCharge(GregorianCalendar returnDate) {
        
        double charge = 0.0;
        
        long dueTime = dueDate.getTimeInMillis();
        long returnTime = returnDate.getTimeInMillis();
        
        long diff = returnTime - dueTime;
        
        if (diff > 0) {
            charge = chargePerDay * diff * MILLISEC_TO_DAY;
            
            if (charge > maximumCharge) {
                charge = maximumCharge;
            }
        }
        
        return charge;
    }


    /**
     * Returns the per day charge of this book
     *
     * @return the per day charge of this book
     */
    public double getChargePerDay( ) {
        return chargePerDay;
    }    
    
    /**
     * Returns the due date of this book
     *
     * @return the due date of this book
     */
    public GregorianCalendar getDueDate( ) {
        return dueDate;
    } 
    
    /**
     * Returns the maximum possible charge of this book
     *
     * @return the maximum possible charge of this book
     */
    public double getMaxCharge( ) {
        return maximumCharge;
    }
     
    /**
     * Returns the title of this book
     *
     * @return the title of this book
     */
    public String getTitle( ) {
        return title;
    }
          
    /**
     * Sets the per day charge of this book
     * 
     * @param charge the per day charge of this book
     */
    public void setChargePerDay(double charge) {
        chargePerDay = charge;
    }
   
    /**
     * Sets the due date of this book
     * 
     * @param date the due date of this book
     */
    public void setDueDate(GregorianCalendar date) {
        dueDate = date;
    }


    /**
     * Sets the maximum possible charge for this book
     * 
     * @param charge the maximum possible charge for this book
     */
    public void setMaximumCharge(double charge) {
        maximumCharge = charge;
    }
    
    /**
     * Sets the title of this book
     * 
     * @param title the title of this book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    
    /**
     * Returns the string representation of this book
     * in the format <title> <charge per day> <max chanrge> <due date>
     *
     * @return string representation of this book
     */
    public String toString( ) {
        
        return String.format("%-30s    $%5.2f    $%7.2f    %4$tm/%4$td/%4$ty", 
                             getTitle(), getChargePerDay(),
                             getMaxCharge(), dueDate.getTime());
                             
        //or you can do the following to get a similar result
        /*
        String tab = "\t";
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        DecimalFormat     df = new DecimalFormat("0.00");
        
        return getTitle() + tab + "$ " + df.format(getChargePerDay()) + tab + "$ " +
                df.format(getMaxCharge()) + tab + sdf.format(dueDate.getTime());
        */
    }
 }
