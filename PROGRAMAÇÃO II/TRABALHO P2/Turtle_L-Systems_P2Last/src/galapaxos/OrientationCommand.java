package galapaxos;

class OrientationCommand extends TurtleCommand {
    private double orientation;

    public OrientationCommand(Turtle owner, double orientation) {
        this.owner = owner;
        this.orientation = orientation;
    }

    void execute() {
        this.owner.changeRedrawOrientation(this.orientation);
    }
}