package galapaxos;

class MoveCommand extends TurtleCommand {
    private double length;

    public MoveCommand(Turtle owner, double length) {
        this.owner = owner;
        this.length = length;
    }

    void execute() {
        this.owner.draw(this.length);
    }
}
