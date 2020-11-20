package galapaxos;

class HeadingCommand extends TurtleCommand {
    private double heading;

    public HeadingCommand(Turtle owner, double heading) {
        this.owner = owner;
        this.heading = heading;
    }

    void execute() {
        this.owner.changeOrientation(this.heading);
    }
}
