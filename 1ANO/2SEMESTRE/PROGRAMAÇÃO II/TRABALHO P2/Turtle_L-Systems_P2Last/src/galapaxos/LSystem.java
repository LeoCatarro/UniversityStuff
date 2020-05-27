package galapaxos;

public interface LSystem {
    void setStart(String start);
    void addRule(Character symbol, String word);
    String iter(String word);
    String loopIter(int n);
}