package galapaxos;

class PenStateCommand extends TurtleCommand {
    private int state;

    public PenStateCommand(Turtle owner, int state) {
        this.owner = owner;
        this.state = state;
    }

    void execute() {
        this.owner.changePenState(this.state);
    }
}

