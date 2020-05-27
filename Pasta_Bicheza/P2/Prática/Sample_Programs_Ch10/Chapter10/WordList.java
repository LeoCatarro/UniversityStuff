/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani

    Chapter 9 Sample Development: Word Concordance

    File: WordList.java

*/


/**
 *   class WordList (Helper Class)
 *
 *   The helper class for Ch9WordConcordance program. This
 *   class maintains the word list.
 *
 */

import java.util.*;


class WordList  {

//----------------------------------
//    Data Members
//----------------------------------
    /** Sorted hash map for maintaining the word list */
    SortedMap<String, Integer> table;

//----------------------------------
//    Constructors
//----------------------------------

    /**
     * Default constructor
     */
    public WordList( ) {
        table = new TreeMap<String, Integer>();
    }

//----------------------------------
//    Public Methods
//
//          void   add              ( String    )
//          String getConcordance   (           )
//
//----------------------------------

    /**
     * Adds a new entry to the word list
     *
     * @param word word to add to the list
     */
    public void add(String word) {
 
        int val;

        if (table.containsKey(word)) {

            val = table.get(word) + 1;

        } else { //word occurs for the first time

            val = 1;
        }

        table.put(word, val);
    }

    /**
     * Returns the word concordance as a String
     *
     * @return the word concordance as a String
     */
    public String getConcordance( ) {
        String line;
        String lineTerminator = System.getProperties().getProperty("line.separator");

        StringBuffer strBuf = new StringBuffer("");

        for (Map.Entry<String,Integer> entry : table.entrySet()) {
            
            line  =  entry.getValue().toString() + "\t" +
                     entry.getKey() + lineTerminator;

            strBuf.append(line);            
        }

        return strBuf.toString();
    }

    /**
     * Clears all entry from the map
     */
    public void reset( ) {
        table.clear();
    }

}
