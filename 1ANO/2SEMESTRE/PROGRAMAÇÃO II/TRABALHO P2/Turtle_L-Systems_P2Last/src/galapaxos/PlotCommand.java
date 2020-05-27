package galapaxos;

import java.awt.Color;

class PlotCommand extends TurtleCommand {
    private Position target;
    private int penState;
    private Color penColor;
    private double penSize;

    public PlotCommand(Turtle owner, Position target, int penState, Color penColor, double penSize) {
        this.owner = owner;
        this.target = target;
        this.penState = penState;
        this.penColor = penColor;
        this.penSize = penSize;
    }

    void execute() {
        this.owner.plot(this.target, this.penState, this.penColor, this.penSize);
    }
}
