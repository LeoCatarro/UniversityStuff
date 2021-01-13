package galapaxos;

public class PenUp extends TurtleStatement {

    public PenUp() {

    }

    public void run(Interpreter interpreter) {
        interpreter.runPenUp(this);
    }
}