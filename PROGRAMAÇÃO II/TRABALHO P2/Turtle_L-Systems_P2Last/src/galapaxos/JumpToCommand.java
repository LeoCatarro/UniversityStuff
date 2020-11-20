package galapaxos;

class JumpToCommand extends TurtleCommand {
    private Position position;

    public JumpToCommand(Turtle owner, Position position) {
        this.owner = owner;
        this.position = position;
    }

    void execute() {
        this.owner.changePosition(this.position);
    }
}
