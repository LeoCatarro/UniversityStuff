/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 10 Sample Program: Simple class used to illustrate
                               an array of objects.

    File: Person.java
*/

/**
 * The Person class is used in Chapter 10
 */
class Person {

//----------------------------------
//    Data Members
//----------------------------------

    /**
     * The name of this person
     */
    private String  name;

    /**
     * The age of this person
     */
    private int     age;

    /**
     * The gender of this person
     */
    private char    gender;


//----------------------------------
//    Constructors
//----------------------------------

    /**
     * Default constructor
     */
    public Person() {
        this("Not Given", 0, 'U');
    }

    /**
     * Constructs a new Person with passed name,
     * age,and gender.
     *
     * @param name   name of the new Person
     * @param age    age of the new Person
     * @param gender gender of the new Person
     */
    public Person(String name, int age, char gender) {
        this.age    = age;
        this.name   = name;
        this.gender = gender;
    }

//-------------------------------------------------
//      Public Methods:
//
//          int     getAge    (            )
//          char    getGender (            )
//          String  getName   (            )
//
//          void    setAge    (   int      )
//          void    setGender (   char     )
//          void    setName   (   String   )
//
//------------------------------------------------

    /**
     * Returns the age of this person.
     *
     * @return the age of this person
     */
    public int getAge( ) {
        return age;
    }

    /**
     * Returns the gender of this person.
     *
     * @return the gender of this person
     */
    public char getGender( ) {
        return gender;
    }

    /**
     * Returns the name of this person.
     *
     * @return the name of this person
     */
    public String getName( ) {
        return name;
    }

    /**
     * Sets the age of this person.
     *
     * @param age this person's age
     */
    public void setAge( int age ) {
        this.age = age;
    }

    /**
     * Sets the gender of this person.
     *
     * @param gender this person's age
     */
    public void setGender( char gender ) {
        this.gender = gender;
    }

    /**
     * Sets the name of this person.
     *
     * @param age this person's name
     */
    public void setName( String name ) {
        this.name = name;
    }

}