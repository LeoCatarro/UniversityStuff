package galapaxos;

class SpeedCommand extends TurtleCommand {
    private int speed;

    public SpeedCommand(Turtle owner, int speed) {
        this.owner = owner;
        this.speed = speed;
    }

    void execute() {
        this.owner.changeSpeed(this.speed);
    }
}
