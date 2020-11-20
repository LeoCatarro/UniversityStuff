package galapaxos;

public class PenDown extends TurtleStatement {

    public PenDown() {}

    public void run(Interpreter interpreter) {
        interpreter.runPenDown(this);
    }
}