package galapagos;

import java.awt.*;
import java.awt.geom.*; //Line2D


/**
 * Generic Canvas descendant object for drawing lines. This object does not
 * have any intelligence or memory. This object can have zero or more drawers 
 * who will be notified when the component refReshing is needed. This object 
 * will pass the Graphics object to the associated DrawingController objects. 
 * The notified controllers can then draw whatever they want on the passed 
 * Graphics object.
 *
 *Date Modified: Feb 24, 2009
 * 
 * Changed the array of DrawingController to List of DrawingController and 
 * removed the restriction of maximum number of DrawingController objects
 * associated to this DrawingCanvas.
 *
 * @author Dr Caffeine
 * 
 */
public class DrawingCanvas extends Canvas {

//--------------------------------------
//	Data members
//--------------------------------------
	
	private static final long serialVersionUID = 1L;
	
    /**
     * A class constant for the scaling factor, # of pixels per logical unit.
     * 
     */
    private final double  DEFAULT_UNIT = 1.5; 

    /**
     * A class constant for the number of logical units between the grid lines.
     */
    private final int     GRID_INTERVAL_FACTOR = 50; //one interval per 50cm 

    /**
     * A Graphics object for this canvas.
     */   
    private Graphics   graphic;

    /**
     * The window's pixel coordinate value that corresponds to the x-coordinate 
     * of the origin (0,0). For example, if the canvas size is 800 by 600 
     * and the origin (0,0) of the logical coordinate unit is at the 
     * center of the canvas, then  originX is set to 400 and originY 
     * is set to 300.
     */
    private int  originX; //origin (0,0) of a graph in the window's pixel coord
    
    /**
     * The window's pixel coordinate value that corresponds to the y-coordinate 
     * of the origin (0,0). For example, if the canvas size is 800 by 600 
     * and the origin (0,0) of the logical coordinate unit is at the center 
     * of the canvas, then originX is set to 400 and originY is set to 300.
     */
    private int  originY; 
	
	
    /**
     * The x-coordinate of the point in the logical coordinate system that 
     * corresponds to the physical center of the canvas.
     */
    private int  centerX; //center of a window in logical (user) coord
    
    /**
     * The y-coordinate of the point in the logical coordinate system that 
     * corresponds to the physical center of the canvas.
     */
    private int   centerY; 
  
    /**
     * The current scaling factor value.
     */                        
    private double   unit;
  
    /**
     * Set to true to draw grid lines.
     */
    private boolean  drawGrid;
  
    /**
     * An Image used for double-buffering.
     */
    private Image    offScreenImage;
  
    /**
     * A Graphics object associated to the offscreen Image used for 
     * double-buffering.
     */
    private Graphics  offScreenGraphics;
  
    /**
     * The current width, in pixels, of the canvas.
     */
    private int  canvasWidth;
	
    /**
     * The current height, in pixels, of the canvas.
     */
    private int  canvasHeight;
  
    /**
     * An array of objects that implements DrawingController interface.
     */  
    private java.util.List<DrawingController>  drawers;

    
//--------------------------------------
//	Constructors
//--------------------------------------

    /**
     * A default constructor that creates an instance of the PlottingCanvas
     * class. Grid lines are drawn, the center position of the canvas is 
     * set to (0,0) of the logical (user) coordinate, and the scaling 
     * unit is set to the default size defined by the constant DEFAULT_UNIT.
     */	
    public DrawingCanvas() {
    	
        super( );	
        setGrid(true);

        unit    = DEFAULT_UNIT;	

        centerX = 0; //default center of the window will
        centerY = 0; //correspond to logical coordinate (0,0)

        drawers = new java.util.ArrayList<DrawingController>( );      
    }


//--------------------------------------
//	Public Methods:
//
//  void    addOwner        ( DrawingController         )
//  void    clear           (                           )
//
//  void    drawPolygon     (Graphics, Polygon, Color   )
//  void    drawText        (Graphics, Color, int, Position, String)
//
//  void    init            (                           )
//  void    paint           ( Graphics                  )
//  void    plot            ( Position, Position        )
//
//  void    setGrid         ( boolean                   )
//  void    setOrigin       ( int, int                  )
//  void    setUnit         ( int                       )
//
//  void    update          ( Graphics                  )
//
//--------------------------------------

    
        
    /**
     * A mutator method that adds another DrawingController that will
     * draw its trajectory on this canvas.
     *
     * @param newdrawer  A DrawingController that will draw its trajectory 
     *                   on this canvas.
     */
    public void addOwner(DrawingController newdrawer) {
    	
        drawers.add(newdrawer);
        
        newdrawer.setCanvas(this);         
    }

    /**
     * Erases the current contents of the PlottingCanvas by painting 
     * the whole canvas with the background color.
     */
    public void clear( ) {
    	
        if (graphic != null) {
        	
        	graphic.setColor(getBackground());
        	graphic.fillRect(0, 0, getSize().width, getSize().height);
        }
    }

    /**
     * Draws the polygon in the specified color on Graphics g
     * 
     * @param g
     * @param polygon
     * @param color
     */
    public void drawPolygon(Graphics g, Polygon polygon, Color color) {
    	
        int size = polygon.npoints;
        int xpoints[] = polygon.xpoints;
        int ypoints[] = polygon.ypoints;
        
        for (int i = 0; i < size; i++ ) {
            xpoints[i] = (int) Math.round(originX + xpoints[i] * unit);
            ypoints[i] = (int) Math.round(originY - ypoints[i] * unit);
        }
        
        Polygon poly = new Polygon(xpoints, ypoints, size);
        
        g.setColor(color);
        g.drawPolygon(poly);
        g.fillPolygon(poly);
    }


    /**
     * Draws the text on the Graphics g in the specified color with pensize 
     * thickness at position location.
     * 
     * @param g         the Graphics to draw text on
     * @param color     the color of the pen
     * @param pensize   the thickness of the pen
     * @param position  the position to draw the text
     * @param text      the content to display
     */
	public void drawText(Graphics g, Color color, int pensize,
			             Position position, String text) {
		
		 Graphics2D g2d = (Graphics2D) g;
	        
	        g2d.setStroke(new BasicStroke(pensize));
	        g2d.setPaint(color);
	        
	        int x = (int) Math.round(originX + position.x * unit);
	        int y = (int) Math.round(originY - position.y * unit);

	        g2d.drawString( text, x, y );
	        
	        g2d.setStroke( new BasicStroke( 1 ) );	
	}
	
	
	/**
     * Initializes the necessary objects for double-buffering drawing. Gets
     * the size information of the canvas, which is necessary later 
     * in translating logical coordinates into the window's pixel 
     * coordinates for drawing.
     * For the drawing to occur correctly, this method MUST be called after 
     * the container object (e.g. Frame) that contains this canvas is 
     * shown and before a drawing method of this canvas is called. 
     *
     */
    public void init( ) {
    	
        canvasWidth       = getSize().width;
        canvasHeight      = getSize().height;
        
        setOrigin( centerX, centerY );

        offScreenImage    = createImage( canvasWidth, canvasHeight );
        offScreenGraphics = offScreenImage.getGraphics();
        
        Graphics g = getGraphics();
        drawGrids( g );
        drawAxis( g );        
    }
    
    
    /**
     * Painting of the canvas is done by drawing the whole contents using
     * the double-buffering technique.
     *
     * @param g  A Graphics object the painting takes place.
     *
     */
    public void paint(Graphics g) {
    	
        if (offScreenGraphics != null) {
        	
            offScreenGraphics.clearRect( 0, 0, canvasWidth, canvasHeight );

            drawGrids   (offScreenGraphics);
            drawAxis    (offScreenGraphics);
            drawContents(offScreenGraphics);

            g.drawImage (offScreenImage, 0, 0, this);
        }

    }

    /**
     * Draws a line between two points pt1 and pt2 on the parameter graphic
     * in the specified color and pen size.
     *
     * @param graphic A Graphics object where the line is drawn.
     * @param color   A color used to draw the line.
     * @param size    The pen size.
     * @param pt1     The starting point of the line.
     * @param pt2     The ending point of the line.
     *
     */
    public void plot(Graphics graphic, Color color, double size,
                     Position pt1, Position pt2) {
        int x1, y1, x2, y2;

        Graphics2D g2d = (Graphics2D) graphic;

        g2d.setPaint(color);

        x1 = (int) Math.round(originX + pt1.x * unit);
        y1 = (int) Math.round(originY - pt1.y * unit);
        x2 = (int) Math.round(originX + pt2.x * unit);
        y2 = (int) Math.round(originY - pt2.y * unit);

        g2d.setStroke(new BasicStroke( (float) size ));
        g2d.draw(new Line2D.Float(x1, y1, x2, y2));
        
        //reset it back so the subsequent drawing won't get affected
        g2d.setStroke(new BasicStroke( 1 ));
    }


    /**
     * A mutator method that sets the flag for drawing the grid lines. 
     * Passing true will make the grid lines to appear.
     *
     * @param showGrid Pass 'true' to show the grid lines.
     *
     */
    public void setGrid(boolean showGrid)  {
    	
        drawGrid = showGrid;
    }

    /**
     * A mutator method that sets the origin point. The parameter (x,y)
     * specifies the point in the logical coordinate system that corresponds
     * to the physical center position of the canvas. By default, the center
     * of the canvas represents the point (0,0) of the 
     * logical coordinate system.
     * For example, if you pass 100 and 200 for the x and y parameters, the 
     * logical coordinate center point is shifted 100 units to the left and 
     * 200 units down.
     *
     * @param x   The x-coordinate of a logical point that corresponds 
     *            to the center of the canvas.
     *            
     * @param y   The y-coordinate of a logical point that corresponds 
     *            to the center of the canvas.
     *
     */
    public void setOrigin(int x, int y) {
    	
        originX = (int) (getSize( ).width / 2 - x * unit);
        originY = (int) (getSize( ).height/ 2 + y * unit);
        
        centerX = x;
        centerY = y;
    }

    /**
     * A mutator method that sets the scaling factor. The default scaling is 2 pixels 
     * per single logical unit. Increase the number for a more zoomed view and decrease 
     * the number for a more zoomed out view.
     * 
     * @param pixelsPerUnit The scaling unit.
     */
    public void setUnit(double pixelsPerUnit)  {
    	
        unit = pixelsPerUnit;
        setOrigin(centerX, centerY);
    }

    /**
     * Overrides the inherited update so no refreshing is done. 
     * Since double-buffering is used, new image is drawn over the old one
     * without erasing the old one. This technique avoids flickering.
     *
     * NOTE: do not call this method directly.
     *
     * @param g A Graphics object where the drawing takes place.
     *     
     */
	public void update(Graphics g) {
		
        paint(g);
    } 
    

//--------------------------------------
//	Private Methods:
//		
//	  	void	drawAxis	  ( Graphics	  )
//      void    drawContents  ( Graphics      )
//		void  	drawPath	  ( Graphics      )
//
//--------------------------------------

    /**
     * Draws the X and Y axis in black if the grid lines are shown and in 
     * gray if the no grid lines are shown.
     * 
     * @param g The Graphics object where the X and Y axis are drawn.
     */
    private void drawAxis(Graphics g) {
    	
        if (drawGrid)
            g.setColor(Color.darkGray);
        else
            g.setColor(Color.lightGray);      
          
        //draw the X axis
        g.drawLine(0, originY, getSize().width, originY);
              
        //draw the Y axis
        g.drawLine(originX, 0, originX, getSize().height);
    }


    /**
     * Asks the owner DrawingController objects to draw their contents
     * 
     * @param g The Graphics object where the contents are drawn.
     */
    private void drawContents(Graphics g) {
    	
    	for (DrawingController dc : drawers) {
    		dc.redraw(g);
    	}
    }
    

    /**
     * Draws the grid lines in cyan. The spacing between the grid lines 
     * is set to  (default spacing * scale factor) logical units.
     * 
     * @param g The Graphics object where the grid lines are drawn.
     */    
    private void drawGrids(Graphics g) {
    	
        g.setColor( Color.cyan);
        
        if (drawGrid) {
            int gridSpacing = (int) Math.round(GRID_INTERVAL_FACTOR * unit);
            int widthMax     = getSize().width;
            int heightMax    = getSize().height;
            
            for (int i = originX+gridSpacing; i < widthMax; i += gridSpacing) {
                g.drawLine(i, 0, i, heightMax);
            }
            
            for (int i = originX-gridSpacing; i > 0; i -= gridSpacing) {
                g.drawLine(i, 0, i, heightMax);
            }
            
            for (int j = originY+gridSpacing; j < heightMax; j += gridSpacing) {
                g.drawLine(0, j, widthMax, j);
            }
            
            for (int j = originY-gridSpacing; j > 0; j -= gridSpacing) {
                g.drawLine(0, j, widthMax, j);
            }
        }
    }
}