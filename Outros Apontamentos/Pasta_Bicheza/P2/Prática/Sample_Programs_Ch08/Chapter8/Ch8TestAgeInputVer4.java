/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 8 Sample Program: Input a person's age

    File: Ch8TestAgeInputVer4.java

*/


class Ch8TestAgeInputVer4 {

    public static void main( String[] args ) {

        int   entrantAge;

        try {

            AgeInputVer4 input = new AgeInputVer4(15, 18);

            //This will cause IllegalArgumentException
            // AgeInputVer4 input = new AgeInputVer4(20, 18);

            entrantAge = input.getAge("Your Age:");

            System.out.println("Input Okay. Age = " + entrantAge);

        } catch (IllegalArgumentException e) {
            System.out.println("Internal Error:" + e.getMessage());

        } catch (Exception e) {

            System.out.println("Sorry, you do not qualify to enter" +
                               " the junior competition");
        }
    }
}