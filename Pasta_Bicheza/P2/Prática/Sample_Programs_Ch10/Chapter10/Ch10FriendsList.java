/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 10 Sample Program: Illustrate the manipulation of
                               a List

    File: Ch10FriendsList.java
*/

import java.util.*;

class Ch10FriendsList {

    public static void main(String[] arg) {
        
        Person       person;
        List<Person> friends;

        //Add members to a list
        friends = new ArrayList<Person>( );

        person = new Person("jane", 10, 'F');
        friends.add( person );

        person = new Person("jack",  6, 'M');
        friends.add( person );

        person = new Person("jill",  8, 'F');
        friends.add( person );

        person = new Person("john", 14, 'M');
        friends.add( person );

        //Traverse the list with an iterator
        Iterator<Person> itr = friends.iterator( );

        while ( itr.hasNext( ) ) {

            person = itr.next( );

            System.out.println(person.getName( ) );
        }
        
        //Traverse the list with a for-each loop
        System.out.println("");
        for (Person p : friends) {
            
            System.out.println(p.getName());
        }

        //Remove the second person
        //and traverse again
        friends.remove(1);

        System.out.println("");
        for (Person p : friends) {
            
            System.out.println(p.getName());
        }

    }
}