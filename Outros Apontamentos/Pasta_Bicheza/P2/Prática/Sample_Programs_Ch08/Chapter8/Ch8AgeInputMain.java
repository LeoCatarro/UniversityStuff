/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Program: Input a person's age

    File: Ch8AgeInputMain.java

*/

import java.util.*;

class Ch8AgeInputMain {
    
    public static void main( String[] args ) {
        
        GregorianCalendar today;
        
        int age, thisYear, bornYr;
        
        String answer;
        
        Scanner scanner = new Scanner(System.in);

        AgeInputVer1 input = new AgeInputVer1( ); //no exception handling
//        AgeInputVer2 input = new AgeInputVer2( ); //first level exception handling
//        AgeInputVer3 input = new AgeInputVer3( ); //more exception handling

        age   = input.getAge("How old are you? ");

        today    = new GregorianCalendar( );
        thisYear = today.get(Calendar.YEAR);

        bornYr   = thisYear - age;

        System.out.print("Already had your birthday this year? (Y or N)");      
        answer = scanner.next();
        
        if (answer.equals("N") || answer.equals("n") ) {
            bornYr--;
        }

        System.out.println("\nYou are born in " + bornYr);
    }
}