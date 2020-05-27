/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 10 Sample Program: A test main program for
            verifying the Step 1 AddressBook class.

    File: TestAddressBook.java
*/

import java.util.*;

class TestAddressBook { //Step 1 Test Main

    public static void main(String args[]) {

        AddressBook myBook;
        String      inputStr;
        int         size;
        
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("Array size: ");
            inputStr = scanner.next();

            if (inputStr.equalsIgnoreCase("stop")) {
                break;
            }

            size = Integer.parseInt(inputStr);

            try {
                myBook = new AddressBook(size);

            } catch (IllegalArgumentException e) {
                System.out.println("Exception Thrown: size = " + size);
            }
        }
    }
}