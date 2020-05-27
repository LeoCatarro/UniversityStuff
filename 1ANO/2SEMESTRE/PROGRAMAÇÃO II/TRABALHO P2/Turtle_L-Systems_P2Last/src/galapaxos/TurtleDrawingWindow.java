package galapaxos;

import javax.swing.JFrame;

public class TurtleDrawingWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private DrawingCanvas canvas;

    public TurtleDrawingWindow() {
        super("Turtle Playground");
        this.setSize(700, 500);
        this.setLocation(200, 150);
        this.setResizable(false);
        this.canvas = new DrawingCanvas();
        this.add(this.canvas);
        this.setDefaultCloseOperation(3);
    }

    public void add(DrawingController owner) {
        this.canvas.addOwner(owner);
    }

    public void clear() {
        this.canvas.clear();
    }

    public void setGrid(boolean showGrid) {
        this.canvas.setGrid(showGrid);
    }

    public void setOrigin(int x, int y) {
        this.canvas.setOrigin(x, y);
    }

    public void setUnit(double pixelsPerUnit) {
        this.canvas.setUnit(pixelsPerUnit);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this.initCanvas();
        }

    }

    private void initCanvas() {
        this.canvas.init();
        this.canvas.repaint();
    }
}
