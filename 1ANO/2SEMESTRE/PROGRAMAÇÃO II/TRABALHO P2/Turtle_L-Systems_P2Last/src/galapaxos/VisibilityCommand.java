package galapaxos;

class VisibilityCommand extends TurtleCommand {
    private boolean status;

    public VisibilityCommand(Turtle owner, boolean status) {
        this.owner = owner;
        this.status = status;
    }

    void execute() {
        this.owner.changeVisibility(this.status);
    }
}
