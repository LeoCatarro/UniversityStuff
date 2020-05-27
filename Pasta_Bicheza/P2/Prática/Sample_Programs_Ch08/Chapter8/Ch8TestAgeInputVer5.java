/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Program: Input a person's age

    File: Ch8TestAgeInputVer5.java

*/


class Ch8TestAgeInputVer5 {

    public static void main( String[] args ) {

        int   entrantAge;

        try {

            AgeInputVer5 input = new AgeInputVer5(25, 50);

            entrantAge   = input.getAge("Your Age:");

            System.out.println("Input Okay. Age = " + entrantAge);

        } catch (AgeInputException e) {

            System.out.println("Error: " + e.value() + " is entered. It is " +
                               "outside the valid range of [" + e.lowerBound() +
                               ", " + e.upperBound() + "]");
        }
    }
}