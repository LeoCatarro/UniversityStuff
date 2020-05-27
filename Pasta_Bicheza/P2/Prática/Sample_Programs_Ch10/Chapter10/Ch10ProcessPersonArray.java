/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 10 Sample Program: Illustrate the processing
                               of an array of Person objects

    File: Ch10ProcessPersonArray.java
*/

import java.util.*;

class Ch10ProcessPersonArray {

    public static void main (String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.getProperty("line.separator"));

        Person[]    person;         //declare the person array
        person = new Person[5];    //and then create it

        //----------- Create person Array -----------------//

        String      name, inpStr;
        int         age;
        char        gender;

        for (int i = 0; i < person.length; i++) {

            //read in data values
            System.out.print("Enter name: ");
            name = scanner.next();
            
            System.out.print("Enter age: ");
            age = scanner.nextInt();
            
            System.out.print("Enter gender: ");
            inpStr = scanner.next();
            gender = inpStr.charAt(0);

            //create a new Person and assign values
            person[i] = new Person( );

            person[i].setName  ( name   );
            person[i].setAge   ( age    );
            person[i].setGender( gender );
        }

        //-------------- Compute Average Age --------------//

        float sum = 0, averageAge;

        for (int i = 0; i < person.length; i++) {

            sum += person[i].getAge();
        }

        averageAge = sum / (float) person.length;

        System.out.println("Average age: " + averageAge);
        System.out.println("\n");


        //------ Find the youngest and oldest person ----------//
        //------ Approach No. 3: Using person reference -------//

        Person    youngest,       //points to the youngest person
                  oldest;         //points to the oldest person

        youngest = oldest = person[0];

        for (int i = 1; i < person.length; i++) {

            if ( person[i].getAge() < youngest.getAge() ) {
                //found a younger person
                youngest   = person[i];
            }
            else if ( person[i].getAge() > oldest.getAge() ) {
                //found an older person
                oldest     = person[i];
            }
        }

        System.out.println("Oldest  : " + oldest.getName()
                    + " is " +   oldest.getAge() + " years old.");

        System.out.println("Youngest: " + youngest.getName()
                    + " is " + youngest.getAge() + " years old.");


        //----------- Search for a particular person ------------//

        System.out.print("Name to search: ");
        String searchName = scanner.next();

        int i = 0;

        while ( i < person.length &&     //still more persons to search
                !person[i].getName().equals( searchName ) ) {
            i++;
        }

        if (i == person.length) {
            //not found - unsuccessful search
            System.out.println( searchName + " was not in the array" );

        } else {
            //found - successful search
            System.out.println("Found " + searchName + " at position " + i);
        }
   }
}