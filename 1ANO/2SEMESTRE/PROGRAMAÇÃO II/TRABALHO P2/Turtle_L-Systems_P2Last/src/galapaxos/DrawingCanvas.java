package galapaxos;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.Line2D.Float;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DrawingCanvas extends Canvas {
    private static final long serialVersionUID = 1L;
    private final double DEFAULT_UNIT = 1.5D;
    private final int GRID_INTERVAL_FACTOR = 50;
    private Graphics graphic;
    private int originX;
    private int originY;
    private int centerX;
    private int centerY;
    private double unit;
    private boolean drawGrid;
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private int canvasWidth;
    private int canvasHeight;
    private List<DrawingController> drawers;

    public DrawingCanvas() {
        this.setGrid(true);
        this.unit = 1.5D;
        this.centerX = 0;
        this.centerY = 0;
        this.drawers = new ArrayList();
    }

    public void addOwner(DrawingController newdrawer) {
        this.drawers.add(newdrawer);
        newdrawer.setCanvas(this);
    }

    public void clear() {
        if (this.graphic != null) {
            this.graphic.setColor(this.getBackground());
            this.graphic.fillRect(0, 0, this.getSize().width, this.getSize().height);
        }

    }

    public void drawPolygon(Graphics g, Polygon polygon, Color color) {
        int size = polygon.npoints;
        int[] xpoints = polygon.xpoints;
        int[] ypoints = polygon.ypoints;

        for(int i = 0; i < size; ++i) {
            xpoints[i] = (int)Math.round((double)this.originX + (double)xpoints[i] * this.unit);
            ypoints[i] = (int)Math.round((double)this.originY - (double)ypoints[i] * this.unit);
        }

        Polygon poly = new Polygon(xpoints, ypoints, size);
        g.setColor(color);
        g.drawPolygon(poly);
        g.fillPolygon(poly);
    }

    public void drawText(Graphics g, Color color, int pensize, Position position, String text) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke((float)pensize));
        g2d.setPaint(color);
        int x = (int)Math.round((double)this.originX + position.x * this.unit);
        int y = (int)Math.round((double)this.originY - position.y * this.unit);
        g2d.drawString(text, x, y);
        g2d.setStroke(new BasicStroke(1.0F));
    }

    public void init() {
        this.canvasWidth = this.getSize().width;
        this.canvasHeight = this.getSize().height;
        this.setOrigin(this.centerX, this.centerY);
        this.offScreenImage = this.createImage(this.canvasWidth, this.canvasHeight);
        this.offScreenGraphics = this.offScreenImage.getGraphics();
        Graphics g = this.getGraphics();
        this.drawGrids(g);
        this.drawAxis(g);
    }

    public void paint(Graphics g) {
        if (this.offScreenGraphics != null) {
            this.offScreenGraphics.clearRect(0, 0, this.canvasWidth, this.canvasHeight);
            this.drawGrids(this.offScreenGraphics);
            this.drawAxis(this.offScreenGraphics);
            this.drawContents(this.offScreenGraphics);
            g.drawImage(this.offScreenImage, 0, 0, this);
        }

    }

    public void plot(Graphics graphic, Color color, double size, Position pt1, Position pt2) {
        Graphics2D g2d = (Graphics2D)graphic;
        g2d.setPaint(color);
        int x1 = (int)Math.round((double)this.originX + pt1.x * this.unit);
        int y1 = (int)Math.round((double)this.originY - pt1.y * this.unit);
        int x2 = (int)Math.round((double)this.originX + pt2.x * this.unit);
        int y2 = (int)Math.round((double)this.originY - pt2.y * this.unit);
        g2d.setStroke(new BasicStroke((float)size));
        g2d.draw(new Float((float)x1, (float)y1, (float)x2, (float)y2));
        g2d.setStroke(new BasicStroke(1.0F));
    }

    public void setGrid(boolean showGrid) {
        this.drawGrid = showGrid;
    }

    public void setOrigin(int x, int y) {
        this.originX = (int)((double)(this.getSize().width / 2) - (double)x * this.unit);
        this.originY = (int)((double)(this.getSize().height / 2) + (double)y * this.unit);
        this.centerX = x;
        this.centerY = y;
    }

    public void setUnit(double pixelsPerUnit) {
        this.unit = pixelsPerUnit;
        this.setOrigin(this.centerX, this.centerY);
    }

    public void update(Graphics g) {
        this.paint(g);
    }

    private void drawAxis(Graphics g) {
        if (this.drawGrid) {
            g.setColor(Color.darkGray);
        } else {
            g.setColor(Color.lightGray);
        }

        g.drawLine(0, this.originY, this.getSize().width, this.originY);
        g.drawLine(this.originX, 0, this.originX, this.getSize().height);
    }

    private void drawContents(Graphics g) {
        Iterator var3 = this.drawers.iterator();

        while(var3.hasNext()) {
            DrawingController dc = (DrawingController)var3.next();
            dc.redraw(g);
        }

    }

    private void drawGrids(Graphics g) {
        g.setColor(Color.cyan);
        if (this.drawGrid) {
            int gridSpacing = (int)Math.round(50.0D * this.unit);
            int widthMax = this.getSize().width;
            int heightMax = this.getSize().height;

            int j;
            for(j = this.originX + gridSpacing; j < widthMax; j += gridSpacing) {
                g.drawLine(j, 0, j, heightMax);
            }

            for(j = this.originX - gridSpacing; j > 0; j -= gridSpacing) {
                g.drawLine(j, 0, j, heightMax);
            }

            for(j = this.originY + gridSpacing; j < heightMax; j += gridSpacing) {
                g.drawLine(0, j, widthMax, j);
            }

            for(j = this.originY - gridSpacing; j > 0; j -= gridSpacing) {
                g.drawLine(0, j, widthMax, j);
            }
        }

    }
}
