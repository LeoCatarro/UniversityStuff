/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Class: Use the assert statement

    File: BankAccount.java

*/
class BankAccount {
	
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public void deposit(double amount) {
        double oldBalance = balance;

        balance -= amount; // <--- here's a logical error
                           //       it should be
                           //       balance += amount

        assert balance > oldBalance :
            "\n\tSerious Error -- "+
            "balance becomes less after deposit";
            //assert balance > oldBalance;
    }

    public void withdraw(double amount) {
        double oldBalance = balance;

        balance -= amount;

        assert balance < oldBalance;
    }

    public double getBalance( ) {
        return balance;
    }
}