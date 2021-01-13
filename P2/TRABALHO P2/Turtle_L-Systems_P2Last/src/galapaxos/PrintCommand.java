package galapaxos;

class PrintCommand extends TurtleCommand {
    private String text;

    public PrintCommand(Turtle owner, String text) {
        this.owner = owner;
        this.text = text;
    }

    void execute() {
        this.owner.drawText(this.text);
    }
}
