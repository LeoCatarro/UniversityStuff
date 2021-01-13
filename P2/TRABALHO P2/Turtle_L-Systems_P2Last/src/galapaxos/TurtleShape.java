package galapaxos;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

class TurtleShape {
    private static final Color DEFAULT_COLOR;
    private static final int SHAPE_SIZE = 26;
    private Point[] shape;
    private Color color;

    static {
        DEFAULT_COLOR = Color.GREEN;
    }

    public TurtleShape() {
        this.initShape();
        this.color = DEFAULT_COLOR;
    }

    public Color getColor() {
        return this.color;
    }

    public Polygon getShape(Position turtlePosition, double turtleOrientation) {
        Polygon shape = new Polygon();
        Point[] var9;
        int var8 = (var9 = this.shape).length;

        for(int var7 = 0; var7 < var8; ++var7) {
            Point pt = var9[var7];
            Position pos = this.compose(turtlePosition, turtleOrientation, pt);
            shape.addPoint((int)pos.x, (int)pos.y);
        }

        return shape;
    }

    void setRefShape(Point[] points) {
        this.shape = points;
    }

    void setColor(Color color) {
        this.color = color;
    }

    private Position compose(Position turtlePosition, double turtleOrientation, Point refPoint) {
        Position p = new Position(0.0D, 0.0D);
        p.x = turtlePosition.x + (double)refPoint.x * Math.cos(turtleOrientation) - (double)refPoint.y * Math.sin(turtleOrientation);
        p.y = turtlePosition.y + (double)refPoint.x * Math.sin(turtleOrientation) + (double)refPoint.y * Math.cos(turtleOrientation);
        return p;
    }

    private void initShape() {
        this.shape = new Point[26];
        this.shape[0] = new Point(0, 0);
        this.shape[1] = new Point(5, 1);
        this.shape[2] = new Point(6, 3);
        this.shape[3] = new Point(10, 6);
        this.shape[4] = new Point(11, 10);
        this.shape[5] = new Point(13, 6);
        this.shape[6] = new Point(17, 6);
        this.shape[7] = new Point(19, 10);
        this.shape[8] = new Point(20, 6);
        this.shape[9] = new Point(24, 2);
        this.shape[10] = new Point(27, 3);
        this.shape[11] = new Point(29, 2);
        this.shape[12] = new Point(30, 1);
        this.shape[13] = new Point(30, -1);
        this.shape[14] = new Point(29, -2);
        this.shape[15] = new Point(27, -3);
        this.shape[16] = new Point(24, -2);
        this.shape[17] = new Point(20, -6);
        this.shape[18] = new Point(19, -10);
        this.shape[19] = new Point(17, -6);
        this.shape[20] = new Point(13, -6);
        this.shape[21] = new Point(11, -10);
        this.shape[22] = new Point(10, -6);
        this.shape[23] = new Point(6, -3);
        this.shape[24] = new Point(5, -1);
        this.shape[25] = new Point(0, 0);
    }
}
