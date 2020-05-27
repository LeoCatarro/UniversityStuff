package galapaxos;

class TurnCommand extends TurtleCommand {
    private double degree;

    public TurnCommand(Turtle owner, double degree) {
        this.owner = owner;
        this.degree = degree;
    }

    void execute() {
        this.owner.rotate(this.degree);
    }
}
