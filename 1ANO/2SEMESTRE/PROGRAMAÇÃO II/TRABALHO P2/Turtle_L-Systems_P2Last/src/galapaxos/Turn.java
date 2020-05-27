package galapaxos;

public class Turn extends TurtleStatement{

        double rotation;
        public Turn(double rotation) { this.rotation = rotation; }
        public double getRotation() { return rotation; }
        public void run(Interpreter interpreter) {
            interpreter.runTurn(this);
    }
}
