/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 10 Sample Program: Address Book Maintenance (Step 1)

    File: Step1/AddressBook.java
*/

/**
 * This class is designed to manage an address book that contains
 * Person objects. The user can specify the size of the address book
 * when it is created. If no size is specified, then the default size
 * is set to 25 Person objects.
 *
 *
 */
class AddressBook  {      //Step 1: Skeleton


//--------------------------------
//    Data Members
//--------------------------------

    /**
     * Default size of the array
     */
    private static final int  DEFAULT_SIZE = 25;

    /**
     * The array of Person objects
     */
    private Person[]   entry;

//--------------------------------
//    Constructors
//--------------------------------

    /**
     * Default constructor.
     * Creates an address book of size 25.
     */
    public AddressBook( ) {
        this( DEFAULT_SIZE );
    }


    /**
     * Creates an address book with the designated size.
     *
     * @param size the size of this address book.
     */
    public AddressBook(int size) {
        if (size <= 0 ) {
            throw new IllegalArgumentException("Size must be positive.");
        }

        entry = new Person[size];

        System.out.println("Array of "+ size + " is created."); //TEMP
    }
}