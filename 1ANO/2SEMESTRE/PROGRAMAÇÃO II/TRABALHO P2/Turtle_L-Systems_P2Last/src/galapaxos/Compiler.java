package galapaxos;

import java.util.HashMap;
import java.util.Vector;

public class Compiler {
    HashMap<Character, TurtleStatement> charToAction;

    public Compiler(){
        charToAction = new HashMap<Character, TurtleStatement>();

    }
    public void addRule(Character letter, TurtleStatement statement) {
            charToAction.put(letter, statement);

    }
    protected TurtleStatement compile(Character c) {
       return charToAction.get(c);

    }
    protected Vector<TurtleStatement> compile(String word) {
        Vector<TurtleStatement> result = new Vector<>();
        for (int i = 0; i < word.length(); i++) {
            result.add(compile(word.charAt(i)));
        }
        return result;
    }
}