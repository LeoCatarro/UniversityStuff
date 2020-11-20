package galapaxos;

import java.util.List;

public interface Interpreter {
    void run(List<TurtleStatement> program);
    void runForward(Forward statement);
    void runTurn(Turn statement);
    void runPenUp(PenUp statement);
    void runPenDown(PenDown statement);
    void runLeap(Leap statement);
}