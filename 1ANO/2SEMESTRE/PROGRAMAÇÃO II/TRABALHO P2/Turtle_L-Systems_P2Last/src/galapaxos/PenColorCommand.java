package galapaxos;

import java.awt.Color;

class PenColorCommand extends TurtleCommand {
    private Color color;

    public PenColorCommand(Turtle owner, Color color) {
        this.owner = owner;
        this.color = color;
    }

    void execute() {
        this.owner.changePenColor(this.color);
    }
}
