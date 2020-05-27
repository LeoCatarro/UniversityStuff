package galapaxos;

class DrawTextCommand extends TurtleCommand {
    private String text;
    private Position position;

    public DrawTextCommand(Turtle owner, String text, Position position) {
        this.owner = owner;
        this.text = text;
        this.position = position;
    }

    void execute() {
        this.owner.drawTextMemory(this.text, this.position);
    }
}
