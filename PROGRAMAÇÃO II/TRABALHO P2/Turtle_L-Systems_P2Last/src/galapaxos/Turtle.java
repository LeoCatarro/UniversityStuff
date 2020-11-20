package galapaxos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Enumeration;
import java.util.Vector;

public class Turtle implements DrawingController, Runnable {
    public static final int CREATE_DEFAULT_WINDOW = 0;
    public static final int NO_DEFAULT_WINDOW = 0;
    private static final int PEN_DOWN = 0;
    private static final int PEN_UP = 1;
    private static final int DEFAULT_SPEED = 20;
    private static final double DEFAULT_PENSIZE = 2.0D;
    private static final Color DEFAULT_COLOR;
    private static final int STEP_INCR = 2;
    private int penState;
    private double penSize;
    private Color penColor;
    private Position currentPosition;
    private double currentOrientation;
    private int currentSpeed;
    private Graphics myGraphics;
    private DrawingCanvas myCanvas;
    private Position redrawCurrentPosition;
    private double redrawCurrentOrientation;
    private Vector<TurtleCommand> memory;
    private Vector<TurtleCommand> instructionBuffer;
    private TurtleShape myShape;
    private boolean showBody;
    private boolean inMotion;
    private TurtleDrawingWindow playGround;

    static {
        DEFAULT_COLOR = Color.ORANGE;
    }

    public Turtle() {
        this(1);
    }

    public Turtle(int choice) {
        this.init();
        if (choice != 0) {
            this.playGround = new TurtleDrawingWindow();
            this.playGround.add(this);
            this.playGround.setVisible(true);
        }

    }

    public void backup(double length) {
        this.instructionBuffer.add(new MoveCommand(this, -length));
    }

    public void bodyColor(Color color) {
        this.myShape.setColor(color);
    }

    public void bodyShape(Point[] point) {
        this.myShape.setRefShape(point);
    }

    public void forward(double length) {
        this.instructionBuffer.add(new MoveCommand(this, length));
    }

    public void heading(double degree) {
        this.instructionBuffer.add(new HeadingCommand(this, degree));
    }

    public void hide() {
        this.instructionBuffer.add(new VisibilityCommand(this, false));
    }

    public void init() {
        this.reset();
        this.initMemory();
    }

    public void jumpTo(double x, double y) {
        this.instructionBuffer.add(new JumpToCommand(this, new Position(x, y)));
    }

    public void move(double length) {
        this.instructionBuffer.add(new MoveCommand(this, length));
    }

    public void moveTo(double targetX, double targetY) {
        Position target = new Position(targetX, targetY);
        this.instructionBuffer.add(new MoveToCommand(this, target));
    }

    public void pause() {
        this.inMotion = false;
    }

    public void penColor(Color color) {
        this.instructionBuffer.add(new PenColorCommand(this, color));
    }

    public void penDown() {
        this.instructionBuffer.add(new PenStateCommand(this, 0));
    }

    public void penSize(int penSize) {
        this.instructionBuffer.add(new PenSizeCommand(this, (float)penSize));
    }

    public void penUp() {
        this.instructionBuffer.add(new PenStateCommand(this, 1));
    }

    public void print(String message) {
        this.instructionBuffer.add(new PrintCommand(this, message));
    }

    public void redraw(Graphics g) {
        this.myGraphics = g;
        this.redrawCurrentPosition = new Position(0.0D, 0.0D);
        this.redrawCurrentOrientation = 0.0D;
        Enumeration iter = this.memory.elements();

        while(iter.hasMoreElements()) {
            ((TurtleCommand)iter.nextElement()).execute();
        }

        if (!this.memory.isEmpty() && this.showBody) {
            Polygon p = this.myShape.getShape(this.redrawCurrentPosition, Math.toRadians(this.redrawCurrentOrientation));
            this.myCanvas.drawPolygon(this.myGraphics, p, this.myShape.getColor());
        }

    }

    public void run() {
        while(true) {
            if (this.inMotion) {
                while(!this.instructionBuffer.isEmpty()) {
                    ((TurtleCommand)this.instructionBuffer.remove(0)).execute();
                }
            }
        }
    }

    public void setCanvas(DrawingCanvas canvas) {
        this.myCanvas = canvas;
        this.startRunning();
    }

    public void show() {
        this.instructionBuffer.add(new VisibilityCommand(this, true));
    }

    public void speed(int speed) {
        this.instructionBuffer.add(new SpeedCommand(this, speed));
    }

    public void start() {
        this.inMotion = true;
    }

    public void turn(double degree) {
        this.instructionBuffer.add(new TurnCommand(this, degree));
    }

    void changeOrientation(double orientation) {
        this.currentOrientation = orientation;
        this.memory.add(new OrientationCommand(this, orientation));
    }

    void changePenColor(Color color) {
        this.penColor = color;
    }

    void changePenSize(float size) {
        this.penSize = (double)size;
    }

    void changePenState(int penState) {
        if (penState >= 0 && penState <= 1) {
            this.penState = penState;
        } else {
            this.penState = 0;
        }

    }

    void changePosition(Position newLocation) {
        this.currentPosition = newLocation;
        this.memory.add(new PositionCommand(this, newLocation));
    }

    void changeRedrawOrientation(double orientation) {
        this.redrawCurrentOrientation = orientation;
    }

    void changeRedrawPosition(Position position) {
        this.redrawCurrentPosition = position;
    }

    void changeSpeed(int speed) {
        if (speed > 0) {
            this.currentSpeed = speed;
        }

    }

    void changeVisibility(boolean status) {
        this.showBody = status;
    }

    void draw(double length) {
        Position target = new Position();
        double angle;
        if (length < 0.0D) {
            angle = Math.toRadians(this.currentOrientation + 180.0D);
            length = -length;
        } else {
            angle = Math.toRadians(this.currentOrientation);
        }

        double totalTime = length / (double)this.currentSpeed;
        double totalSteps = length / 2.0D;
        int pauseTime = (int)Math.round(1000.0D * totalTime / totalSteps);

        for(double distance = 2.0D; distance <= length; distance += 2.0D) {
            target.x = this.currentPosition.x + distance * Math.cos(angle);
            target.y = this.currentPosition.y + distance * Math.sin(angle);
            this.memory.add(new PlotCommand(this, target, this.penState, this.penColor, this.penSize));
            this.myCanvas.repaint();

            try {
                Thread.sleep((long)pauseTime);
            } catch (Exception var14) {
            }
        }

        this.currentPosition = target;
    }

    void drawShape(double orientation) {
        Polygon p = this.myShape.getShape(this.redrawCurrentPosition, Math.toRadians(orientation));
        this.myCanvas.drawPolygon(this.myGraphics, p, this.myShape.getColor());
    }

    void drawText(String text) {
        this.memory.add(new DrawTextCommand(this, text, this.currentPosition));
        this.myCanvas.repaint();
    }

    void drawTextMemory(String text, Position position) {
        this.myCanvas.drawText(this.myGraphics, Color.BLACK, 2, position, text);
    }

    void plot(Position target, int penState, Color penColor, double penSize) {
        if (penState == 0) {
            this.myCanvas.plot(this.myGraphics, penColor, penSize, this.redrawCurrentPosition, target);
        }

        this.redrawCurrentPosition = target;
    }

    void rotate(double degree) {
        double orientationIncr = 0.0D;
        double totalTime = degree / (double)(4 * this.currentSpeed);
        double totalSteps = degree / 8.0D;
        int pauseTime = (int)Math.round(1000.0D * totalTime / totalSteps);
        int degreeIncr = 8;
        byte sign;
        if (degree < 0.0D) {
            sign = -1;
        } else {
            sign = 1;
        }

        double limit = Math.abs(degree);

        for(orientationIncr += (double)degreeIncr; orientationIncr <= limit; orientationIncr += (double)degreeIncr) {
            this.memory.add(new OrientationCommand(this, this.currentOrientation + (double)sign * orientationIncr));
            this.myCanvas.repaint();

            try {
                Thread.sleep((long)pauseTime);
            } catch (Exception var15) {
            }
        }

        this.currentOrientation += degree;
        this.memory.add(new OrientationCommand(this, this.currentOrientation));
    }

    void turnAndMove(Position target) {
        double deltaX = target.x - this.currentPosition.x;
        double deltaY = target.y - this.currentPosition.y;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (distance != 0.0D) {
            double orientation = Math.toDegrees(Math.atan2(deltaY, deltaX));
            this.changeOrientation(orientation);
            this.draw(distance);
        }

    }

    private void initMemory() {
        this.memory = new Vector();
        this.instructionBuffer = new Vector();
        this.myShape = new TurtleShape();
    }

    private void reset() {
        this.currentOrientation = 0.0D;
        this.currentPosition = new Position(0.0D, 0.0D);
        this.currentSpeed = 20;
        this.penState = 0;
        this.penColor = DEFAULT_COLOR;
        this.penSize = 2.0D;
        this.showBody = true;
        this.inMotion = true;
        this.myCanvas = null;
    }

    private void startRunning() {
        Thread myThread = new Thread(this);
        myThread.start();
    }
}
