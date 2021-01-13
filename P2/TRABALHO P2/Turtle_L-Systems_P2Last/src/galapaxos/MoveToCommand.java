
package galapaxos;

class MoveToCommand extends TurtleCommand {
    private Position target;

    public MoveToCommand(Turtle owner, Position target) {
        this.owner = owner;
        this.target = target;
    }

    void execute() {
        this.owner.turnAndMove(this.target);
    }
}
