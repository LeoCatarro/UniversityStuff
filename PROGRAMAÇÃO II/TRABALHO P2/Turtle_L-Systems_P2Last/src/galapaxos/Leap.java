package galapaxos;

public class Leap extends TurtleStatement {

    double distance;
    public Leap(double distance) { this.distance = distance; }
    public double getDistance() { return distance; }
    public void run(Interpreter interpreter) {
        interpreter.runLeap(this);
    }
}
