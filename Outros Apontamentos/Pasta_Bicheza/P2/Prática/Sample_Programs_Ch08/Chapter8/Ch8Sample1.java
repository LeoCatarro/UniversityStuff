/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Program: Getting an exception

    File: Ch8Sample1.java

*/

import java.util.*;

class Ch8Sample1 {

    public static void main( String[] args ) {
/*
    Enter an input value that cannot be converted
    to an int, such as abc123. Error message such as

    Exception in thread "main" java.util.InputMismatchException
            at java.util.Scanner.throwFor(Scanner.java:819)
            at java.util.Scanner.next(Scanner.java:1431)
            at java.util.Scanner.nextInt(Scanner.java:2040)
            at java.util.Scanner.nextInt(Scanner.java:2000)
            at Ch8Sample1.main(Ch8Sample1.java:33)

    will result. A number format exception was thrown.
*/

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter integer: ");

        int number = scanner.nextInt();

        System.out.println(number);
    }
}