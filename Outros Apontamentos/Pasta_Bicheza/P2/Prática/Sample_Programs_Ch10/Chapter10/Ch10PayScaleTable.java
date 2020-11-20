/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 10 Sample Program: Sample program for processing
                                2-D array of double.

    File: Ch10PayScaleTable.java
*/

class Ch10PayScaleTable  {

    public static void main(String[] args) {

        //Declare and initialize the pay scale table

        double[][] payScaleTable
                        = {  {10.50, 12.00, 14.50, 16.75, 18.00},
                             {20.50, 22.25, 24.00, 26.25, 28.00},
                             {34.00, 36.50, 38.00, 40.35, 43.00},
                             {50.00, 60.00, 70.00, 80.00, 99.99}  };


        //Find the average pay of Level 2 employees
        double sum = 0.0, average;

        for (int j = 0; j < 5; j++) {
            sum += payScaleTable[2][j];
        }

        average = sum / 5;

        System.out.println(" Average of Level 2 Employees: " + average );
        System.out.println("\n");

        //Display the pay difference at each grade level
        double difference;

        for (int i = 0; i < 4; i++) {
            difference = payScaleTable[i][4] - payScaleTable[i][0];
            System.out.println("Pay difference at Grade Level " +
                                     i + " is " + difference);
        }

        //Print out the pay scale table
        System.out.println("\n");

        for (int i = 0; i < payScaleTable.length; i++) {

            for (int j = 0; j < payScaleTable[i].length; j++) {

                System.out.print(payScaleTable[i][j] + "    " );
            }

            System.out.println("");
        }
        
        //the above nested loop can be written as a nested for-each loop
//        for (double[] row : payScaleTable) {
//            for (double pay : row) {
//                System.out.print(pay + "     ");
//            }
//            System.out.println("");
//        }

        //Increase the pay by 1.50 for every level/step
        //and display the resulting table
        System.out.println("\n");

        for (int i = 0; i < payScaleTable.length; i++) {

            for (int j = 0; j < payScaleTable[i].length; j++) {

                payScaleTable[i][j] += 1.50;

                System.out.print(payScaleTable[i][j] + "    " );
            }

            System.out.println("");
        }


    }

}