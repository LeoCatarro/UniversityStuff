/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 10 Sample Program: A test main program for
            verifying the Step 2 AddressBook class.

    File: Step2/TestAddressBook.java
*/

class TestAddressBook {

   public static void main(String[] args) {

        AddressBook   myBook;
        Person        person;

        myBook = new AddressBook(4);

        //add four Person objects
        for (int i = 0; i < 4; i++) {
            person = new Person("Ms. X" + i, 10, 'F');
            myBook.add(person);
        }

        //add the fifth person and see if
        //a new array is created
        person = new Person("fifth one", 10, 'F');
        myBook.add(person);
   }
}