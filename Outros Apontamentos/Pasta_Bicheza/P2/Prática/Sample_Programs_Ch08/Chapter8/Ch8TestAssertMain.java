/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Program: Test the BankAccount class
                              with Assertion enabled.

    File: Ch8TestAssertMain.java

    NOTE: The assertion feature requires Java 2 SDK 1.4 or later.
          Run this main class with assertions enabled as

            java -ea Ch8TestAssertMain

          Add -classpath option as appropriate to the individual
          setup.

*/

class Ch8TestAssertMain {

    public static void main( String[] args ) {

        BankAccount acct = new BankAccount(200);

        acct.withdraw(10);

        System.out.println("Current Balance: " + acct.getBalance());

        acct.deposit(25);

        System.out.println("Current Balance: " + acct.getBalance());
    }
}