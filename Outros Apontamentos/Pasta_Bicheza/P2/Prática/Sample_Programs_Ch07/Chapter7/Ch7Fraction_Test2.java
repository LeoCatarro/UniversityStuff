/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 7 Sample Program: Testing the Fraction class - Part 2

    File: Ch7Fraction_Test2.java

*/

import myutil.Fraction;

class Ch7Fraction_Test2 {
    
    public static void main(String[] arg) {

    /* Test all four contructors and toString. */
    Fraction f0 = new Fraction();
    Fraction f1 = new Fraction(3);
    Fraction f2 = new Fraction(12, 20);
    Fraction f3 = new Fraction(f2);

    System.out.println("\nTesting constructors and toString():");
    System.out.println("The fraction f0 is " + f0.toString());
    System.out.println("The fraction f1 is " + f1);    // toString is implicit.
    System.out.println("The fraction f2 is " + f2);
    System.out.println("The fraction f3 is " + f3 + ", which should equal f2");

    /* Test the add method. */
    System.out.println("\nTesting add:");
 
    Fraction sumOfTwo = f1.add(f2);              // Sum of f1 and f2.
    Fraction sumOfThree = f0.add(f1.add(f2));    // Sum of f0, f1, and f2.

    System.out.println("The sum of " + f1 + " and " + f2 + " is " + sumOfTwo);
    System.out.println("The sum of " + f0 + ", " + f1 + " and " + f2 + " is " +
                       sumOfThree);
  

    /* Test the methods used in Part III. */
    System.out.println("\nTesting setNumerator:");

    f3.setNumerator(7);
    System.out.println("Now f3 is " + f3 + ", which should be 7/20");

  }
}
