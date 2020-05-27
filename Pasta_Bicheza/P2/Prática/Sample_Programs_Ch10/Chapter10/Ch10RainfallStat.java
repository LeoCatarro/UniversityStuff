/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 10 Sample Program: Compute different statistics
                                from monthly rainfall averages.

    File: Ch10RainfallStat.java
*/

import java.util.*;

class Ch10RainfallStat {

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] monthName = { "January", "February", "March",
                               "April", "May", "June", "July",
                               "August", "September", "October",
                               "November", "December"  };

        double[]  rainfall = new double[12];

        double[]  quarterAverage = new double[4];

        double    annualAverage, sum;

        double    oddMonthSum, oddMonthAverage,
                  evenMonthSum, evenMonthAverage;

        sum = 0.0;

        for (int i = 0; i < rainfall.length; i++) {

            System.out.print("Rainfall for " + monthName[i] + ": ");
            rainfall[i] = scanner.nextDouble();
            
            sum += rainfall[i];
        }

        annualAverage = sum / 12.0;

        System.out.format( "Annual Average Rainfall:%6.2f\n\n", annualAverage );


        oddMonthSum  = 0.0;
        evenMonthSum = 0.0;

        ///////////// Odd and Even Month Averages //////////////////

        //----------------- Variation ---------------------/
        /*
        for (int i = 0; i < rainfall.length; i += 2 ) {
            oddMonthSum += rainfall[i];
            evenMonthSum += rainfall[i+1];
        }
        */
        //-------------------------------------------------/

        //compute the average for the odd months
        for (int i = 0; i < rainfall.length; i += 2) {

            oddMonthSum += rainfall[i];
        }

        oddMonthAverage = oddMonthSum / 6.0;

        //compute the average for the even months
        for (int i = 1; i < rainfall.length; i += 2) {

            evenMonthSum += rainfall[i];
        }

        evenMonthAverage = evenMonthSum / 6.0;

        System.out.format("Odd Month Rainfall Average: %6.2f\n", oddMonthAverage );

        System.out.format("Even Month Rainfall Average:%6.2f\n\n", evenMonthAverage );


        /////////////////// Quarter Averages //////////////////////

        for (int i = 0; i < 4; i++) {

            sum = 0;

            for (int j = 0; j < 3; j++) {    //compute the sum of
                sum += rainfall[3*i + j];    //one quarter
            }

            quarterAverage[i] = sum / 3.0;   //average for Quarter i+1

            System.out.format("Rainfall Average Qtr%3d:%6.2f\n", i+1, quarterAverage[i] );

        }
   }
}