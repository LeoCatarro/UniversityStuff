
package galapaxos;

abstract class TurtleCommand {
    protected Turtle owner;

    TurtleCommand() {
    }

    abstract void execute();

    public void addOwner(Turtle t) {
        this.owner = t;
    }
}
