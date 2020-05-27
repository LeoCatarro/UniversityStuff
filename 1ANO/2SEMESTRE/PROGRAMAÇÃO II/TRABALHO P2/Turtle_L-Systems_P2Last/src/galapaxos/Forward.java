package galapaxos;

public class Forward extends TurtleStatement {
    double distance;
    public Forward(double distance) { this.distance = distance; }
    public double getDistance() { return distance; }
    public void run(Interpreter interpreter) {
        interpreter.runForward(this);
    }
}