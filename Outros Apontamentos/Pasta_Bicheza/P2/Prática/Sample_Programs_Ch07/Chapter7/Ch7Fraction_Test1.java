/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 7 Sample Program: Testing the Fraction class

    File: Ch7Fraction_Test1.java

*/

import myutil.Fraction;

class Ch7Fraction_Test1 {
    
    public static void main(String[] arg) {

        Fraction f1, f2, f3;
    
        f1 = new Fraction(1, 2);
        f2 = new Fraction(3, 4);
        
        f3 = f1.add(f2);
        
        System.out.println("Sum of " + f1.toString() + " and " +
                                f2.toString() + " is " +
                                f3.toString());

  }
}
