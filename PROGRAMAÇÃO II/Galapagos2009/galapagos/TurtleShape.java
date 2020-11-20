	
package galapagos;

import java.awt.*;

/**
 * A class responsible for maintaining and manipulating the turtle shape.
 * 
 * Modified: Feb 24, 2009
 * 
 * @author Dr Caffeine
 */
 
class TurtleShape {
    
//------------------------------------
//	Data members
//------------------------------------

    /**
     * The default color of this body shape
     */
    private static final Color DEFAULT_COLOR = Color.GREEN;

    /**
     * The size of an array to store the points of the robot shape.
     */
    private static final int	SHAPE_SIZE   = 26;

    /**
     * An array of Point objects to represent the turtle's shape in the
     * local coordinate system. Given a turtle's position, we can compute
     * the points of the turtle shape in global values from this array.
     */
    private Point[]   shape;              //base shape in local coordinates
    
    
    /**
     * The fill color of this body shape
     */
    private Color     color;

    
//------------------------------------
//	Constructors
//------------------------------------

    /**
    * A default constructor for a standard turtle shape.
    */
    public TurtleShape( ) {
    	
        //setup the default shape
        initShape();  
        
        color = DEFAULT_COLOR; //set default color
    }

    
//------------------------------------
//	Public Methods:
//
//       Color      getColor       (                       )
//       Polygon    getShape       (   Position, double    )
//    
//       void       setColor       (   Color               )
//       Polygon    setRefShape    (                       )    
//
//------------------------------------

    
    /**
     * Returns the current fill color of this body shape.
     *
     * @return the current fill color of this body shape
     *
     */
    public Color getColor( ) {
    	
        return color;
    }
    
    
    /**
     * Computes a polygon that represents the turtle's shape.
     * 
     * @param turtlePosition    The turtle's current position
     * @param turtleOrientation The turtle's orientation in radian
     * @return A polygon for the turtle shape specified in the global coordinate
     * system.
     */
    public Polygon getShape (Position turtlePosition, 
    		                 double turtleOrientation) {  
    	
        Polygon   shape = new Polygon( );
        Position  pos;
        
        for (Point pt : this.shape) {
            
            pos = compose(turtlePosition, turtleOrientation, pt);
            
            shape.addPoint((int)pos.x, (int)pos.y);
        }
        
        return shape;            
    }
    
    
    /**
     * Sets the reference body shape for this turtle shape. Define the shape
     * relative to  (0,0), the pen point. 
     *
     * @param points An array of java.awt.Point objects
     */
    void setRefShape(Point[ ] points) {
    	
        shape = points;
    }
    
    /**
     * Sets the fill color for this body shape
     *
     * @param color  a new Color for this body shape
     */
    void setColor(Color color) {
    	
        this.color = color;
    }
    
	
//------------------------------------
//	Private Methods:
//
//      Position      compose     (  Position   )
//      void          initShape   (		        )
//
//------------------------------------

    /**
     * Compute a position of reference point in the global coordinate system.
     *
     * @param turtlePosition     the turtle's current position
     * @param turtleOrientation  the turtle's current orientation
     * @param refPoint           the point in the local coordinate system
     *
     * @return A position of point in the global coordinate system
     */
    private Position compose(Position turtlePosition, 
                             double turtleOrientation, Point refPoint ) {
    	
        Position p = new Position(0, 0);
        
        p.x = turtlePosition.x + refPoint.x * Math.cos(turtleOrientation) 
                         - refPoint.y * Math.sin(turtleOrientation);
                         
        p.y = turtlePosition.y + refPoint.x * Math.sin(turtleOrientation)
                         + refPoint.y * Math.cos(turtleOrientation);
                         
        return p;
    }
    
    
    /**
     * Initializes the default shape (array) for the turtle. 
     * Elements in the array provide polygon coordinate points in 
     * pixels relative to the turtle's position which is (0,0) in the
     * local coordinate system.
     */
    private void initShape( )
    {
        shape = new Point[SHAPE_SIZE];

        shape[0]  = new Point(  0,  0 );
        shape[1]  = new Point(  5,  1 );
        shape[2]  = new Point(  6,  3 );
        shape[3]  = new Point( 10,  6 );
        shape[4]  = new Point( 11, 10 );
        shape[5]  = new Point( 13,  6 );
        shape[6]  = new Point( 17,  6 );
        shape[7]  = new Point( 19, 10 );
        shape[8]  = new Point( 20,  6 );
        shape[9]  = new Point( 24,  2 );
        shape[10] = new Point( 27,  3 );
        shape[11] = new Point( 29,  2 );
        shape[12] = new Point( 30,  1 );
        shape[13] = new Point( 30, -1 );
        shape[14] = new Point( 29, -2 );
        shape[15] = new Point( 27, -3 );
        shape[16] = new Point( 24, -2 );
        shape[17] = new Point( 20, -6 );
        shape[18] = new Point( 19, -10);
        shape[19] = new Point( 17, -6 );
        shape[20] = new Point( 13, -6 );
        shape[21] = new Point( 11, -10);
        shape[22] = new Point( 10, -6 );
        shape[23] = new Point(  6, -3 );
        shape[24] = new Point(  5, -1 );
        shape[25] = new Point(  0,  0 );
    }

}