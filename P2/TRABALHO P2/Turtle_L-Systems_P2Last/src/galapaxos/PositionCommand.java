package galapaxos;

class PositionCommand extends TurtleCommand {
    private Position position;

    public PositionCommand(Turtle owner, Position position) {
        this.owner = owner;
        this.position = position;
    }

    void execute() {
        this.owner.changeRedrawPosition(this.position);
    }
}
