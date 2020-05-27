/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 10 Sample Program: Address Book Maintenance (Step 3)

    File: Step3/AddressBook.java
*/

/**
 * This class is designed to manage an address book that contains
 * Person objects. The user can specify the size of the address book
 * when it is created. If no size is specified, then the default size
 * is set to 25 Person objects.
 *
 */
class AddressBook {     //Step 3: Implement the search method


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

    /**
     * The number of elements in the <code>entry</code> array,
     * which is also the position to add the next Person object
     */
    private int        count;

//--------------------------------
//    Constructors
//--------------------------------

    /**
     * Default constructor.
     * Creates an address book of size 25.
     */
    public AddressBook( ) {
        this(DEFAULT_SIZE);
    }


    /**
     * Creates an address book with the designated size.
     *
     * @param size the size of this address book.
     */
    public AddressBook(int size){
        if (size <= 0 ) {
            throw new IllegalArgumentException("Size must be positive.");
        }

        entry = new Person[size];

        //System.out.println("Array of "+ size + " is created."); //TEMP
    }


//-------------------------------------------------
//      Public Methods:
//
//          void    add       (   Person     )
//          Person  search    (    String    )
//
//------------------------------------------------

    /**
     * Adds a new Person to this address book.
     * If the overflow occurs, the array size
     * is increased by 50 percent.
     *
     * @param newPerson a new Person object to add
     */
    public void add(Person newPerson) {

        assert count >= 0 && count <= entry.length;

        if (count == entry.length) {   //no more space left,
            expand( );                //create a new larger array
        }

        //at this point, entry refers to a new larger array
        entry[count] = newPerson;
        count++;
    }


    /**
     * Searches this address book for a Person
     * whose name is <code>searchName</code>.
     *
     * @param searchName the name to search
     *
     * @return a Person object if found; otherwise null
     */
    public Person search( String searchName ) {
        
        Person foundPerson;
        int    loc = 0;

        while (loc < count &&
                !searchName.equals( entry[loc].getName())) {
            loc++;
        }

        if (loc == count) {

            foundPerson = null;
            
        } else {

            foundPerson = entry[loc];
        }

        return foundPerson;
    }


//-------------------------------------------------
//      Private Methods:
//
//          void  expand   (           )
//
//------------------------------------------------

    /**
     * Enlarges the size of <code>entry</code> array to
     * eliminate the overflow condition. The new array
     * is 50 percent larger than the current array.
     */
    private void expand( ) {

        //create a new array whose size is 150% of
        //the current array
        int newLength = (int) (1.5 * entry.length);
        Person[] temp = new Person[newLength];

        //now copy the data to the new array
        for (int i = 0; i < entry.length; i++) {
            temp[i] = entry[i];
        }

        //finally set the variable entry to point to the new array
        entry = temp;

        System.out.println("Inside the method enlarge");            //TEMP
        System.out.println("Size of a new array: " + entry.length); //TEMP
    }
}
