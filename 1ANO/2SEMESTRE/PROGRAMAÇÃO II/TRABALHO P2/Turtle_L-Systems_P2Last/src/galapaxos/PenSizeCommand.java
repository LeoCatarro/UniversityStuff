package galapaxos;

class PenSizeCommand extends TurtleCommand {
    private float size;

    public PenSizeCommand(Turtle owner, float size) {
        this.owner = owner;
        this.size = size;
    }

    void execute() {
        this.owner.changePenSize(this.size);
    }
}
