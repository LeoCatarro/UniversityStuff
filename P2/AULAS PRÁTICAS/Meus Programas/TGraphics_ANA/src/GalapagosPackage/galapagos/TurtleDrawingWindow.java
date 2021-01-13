package galapagos;

import javax.swing.*;


/**
 * A simple window for allowing turtles to draw. You can add multiple
 * turtles to this window. With the current implementation, drawing
 * becomes jaggy when you add multiple turtles. This performance
 * problem will be addressed in the next release.
 * 
 * Date Modified: February, 2009
 *
 * @author Dr Caffeine
 *
 */
public class TurtleDrawingWindow extends JFrame {

//--------------------------------------
//	Data members
//--------------------------------------

	private static final long serialVersionUID = 1L;
	
	/**
     * Canvas for which the owner turtles will draw
     */
    private DrawingCanvas canvas;
    
    
//--------------------------------------
//	Constructors
//--------------------------------------

    /**
     * A default constructor that creates an instance of the DrawingCanvas 
     * class. Grid lines are drawn, the center position of the canvas is
     * set to (0,0) of the logical (user) coordinate, and the scaling 
     * unit is set to the default size defined by the constant 
     * DrawingCanvas.DEFAULT_UNIT.
     */	
    public TurtleDrawingWindow( ) {
    	
        super("Turtle Playground");  
        
        setSize(700, 500);
        setLocation(200, 150);
        setResizable(false);

        canvas = new DrawingCanvas( );
        add(canvas);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


//--------------------------------------
//	Public Methods:
//
//  void    add             ( DrawingController    )
//  void    clear           (                      )
//
//
//  void    setGrid         ( boolean              )
//  void    setOrigin       ( int, int             )
//  void    setUnit         ( int                  )
//  void    setVisible      ( boolean              )
//
//--------------------------------------

    
    /**
     * A mutator method that adds another DrawingController that will
     * draw its trajectory on this canvas.
     *
     * @param owner A DrawingController that will draw its trajectory on this canvas.
     */
    public void add(DrawingController owner) {        
    	
        canvas.addOwner(owner);
    }

    /**
     * Erases the current contents of the PlottingCanvas by painting the whole canvas 
     * with the background color.
     */
    public void clear( ) {
    
        canvas.clear( );
    }

    
    /**
     * A mutator method that sets the flag for drawing the grid lines. Passing true
     * will make the grid lines to appear.
     *
     * @param showGrid Pass 'true' to show the grid lines.
     *
     */
    public void setGrid(boolean showGrid) {
    
        canvas.setGrid(showGrid);
        
    }

    /**
     * A mutator method that sets the origin point. The parameter (x,y)
     * specifies the point in the logical coordinate system that corresponds
     * to the physical center position of the canvas. By default, the center
     * of the canvas represents the point (0,0) of the logical coordinate 
     * system. For example, if you pass 100 and 200 for the x and y parameters,
     * the logical coordinate center point is shifted 100 units to the left 
     * and 200 units down.
     *
     * @param x The x-coordinate of a logical point that corresponds to the 
     *          center of the canvas.
     *          
     * @param y The y-coordinate of a logical point that corresponds to the 
     *          center of the canvas.
     *
     */
    public void setOrigin(int x, int y) {
    	
        canvas.setOrigin( x, y );
    }
  
    /**
     * A mutator method that sets the scaling factor. The default scaling 
     * is 2 pixels per single logical unit. Increase the number for a more 
     * zoomed view and decrease the number for a more zoomed out view.
     * 
     * @param pixelsPerUnit The scaling unit.
     */
    public void setUnit(double pixelsPerUnit) {
    	
        canvas.setUnit(pixelsPerUnit );        
    }
    
    /**
     * Overrides the inherited method so the canvas is set up 
     * correctly.
     */
    public void setVisible(boolean visible) {
    	
        super.setVisible(visible);
        
        if (visible) {
            initCanvas( );
        }
    }
            
    /**
     * Initializes the drawing canvas
     *
     */
    private void initCanvas(  ) {
    	
        canvas.init();                        
        canvas.repaint();                        
    }
}