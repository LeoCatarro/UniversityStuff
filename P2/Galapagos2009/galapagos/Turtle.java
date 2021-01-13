package galapagos;

import java.awt.*;
import java.util.*;


/**
 * This Turtle draws geometric shapes a DrawingCanvas. This turtle will run
 * in a separate Thread so multiple turtles can be drawing on a single
 * canvas.
 * 
 * Date Modified: February, 2009
 *  
 *    Made the class up-to-date Java 5.0 and cleaned up the code a bit. 
 *
 * @author Dr Caffeine
 *
 */
public class Turtle implements DrawingController, Runnable {
    
//--------------------------------------
//    Data members
//-------------------------------------- 

    /**
     * Constant for creating a default drawing window
     */
    public final static int CREATE_DEFAULT_WINDOW = 1;
    
    /**
     * Constant for not creating any default drawing window
     */
    public final static int NO_DEFAULT_WINDOW     = 0;
    
    /**
     * Constant for pen down state.
     */
    private final static int PEN_DOWN = 0;
    
    /**
     * Constant for pen up state
     */
    private final static int PEN_UP   = 1;  
    
    
    /**
     * Default speed of this turtle; unit is logical units/sec
     */
    private final static int    DEFAULT_SPEED = 20; 
    
    /**
     * Default pen size used in drawing
     */
    private final static double  DEFAULT_PENSIZE = 2.0;
    
    /**
     * Default color used in drawing
     */
    private final static Color  DEFAULT_COLOR = Color.GREEN;

    /**
     * Number of logical units this turtle moves in one drawing
     */
    private final static int    STEP_INCR = 2;
 
    /**
     * The position (down or up) of this turtle's pen
     */  
    private int     penState;
    
    /**
     * The pen size of this turtle in logical units
     */
    private double  penSize;


    /**
     * This turtle's pen color
     */ 
    private Color   penColor;
 
    /**
     * This turtle's current position in the logical coordinate
     */   
    private Position  currentPosition;
    
    /**
     * This turtle's current orientation. 
     */
    private double  currentOrientation;
    
    /**
     * This turtle's current speed
     */
    private int  currentSpeed;

    /**
     * The Graphics object returned from the connected
     * DrawingCanvas. This Graphics object is used by this
     * turtle to draw its trajectory.
     */
    private Graphics  myGraphics; 
    
    /**
     * The DrawingCanvas which this turtle is drawing
     * its trajectory.
     */
    private DrawingCanvas myCanvas;  
    
    /**
     * The position of this turtle during the redrawing 
     * phase.
     */
    private Position  redrawCurrentPosition;
    
    /**
     * The orientation of this turtle during the redrawing
     * phase
     */
    private double  redrawCurrentOrientation;
    
    /**
     * This turtle's memory to remember the past commands. Once the
     * command gets stored in memory, it'll stay there forever. The purpose
     * is to redraw the frame when redrawing of the content is necessary
     */ 
    private Vector<TurtleCommand>   memory;
    
    /**
     * This turtle's instruction buffer to store user commands. This is
     * a queue. As long as the queue is not empty, we continue
     * removing the first (head) command in the queue and executing it. 
     * A new command is added to the tail.
     */
    private Vector<TurtleCommand>   instructionBuffer;
    
    /**
     * The shape of this turtle to draw on the canvas.
     */
    private TurtleShape   myShape;
     
    /**
     * The visibility flag: true makes turtle visible; 
     * otherwise invisible.
     */
    private boolean  showBody;
    
    /**
     * The motion flag: true makes turtle moving immediately
     * after receiving command; otherwise the turtle is
     * in the pause mode and will not move until told to move
     */
    private boolean   inMotion;
    
    
    /**
     * Default drawing window for this turtle. When this turtle
     * is added explicitly to another drawing canvas, then
     * this drawing window is ignored.
     */
    private TurtleDrawingWindow  playGround;
    
    
//--------------------------------------
//    Constructors
//--------------------------------------

    /**
     * A default constructor that creates an instance of the Turtle class with
     * a default drawing window.
     * The orientation of this turtle is 0 degree, i.e., facing East where the 
     * top of the screen is North. The pen is down with its color black and pen size
     * 2 logical units wide. 
     */    
    public Turtle( )  {
        this(CREATE_DEFAULT_WINDOW);
    }
    
    /**
     * Constructs a standard turtle with or without default drawing window assigned.
     * The orientation of this turtle is 0 degree, i.e., facing East where the 
     * top of the screen is North. The pen is down with its color black and pen size
     * 2 logical units wide.
     *
     * @param choice zero means no default window; non-zero will create a 
     *                      default window
     *                      
     */
    public Turtle(int choice) {
        init( );
        
        if (choice != NO_DEFAULT_WINDOW) {
          //non-zero so create a default window
          
          playGround   = new TurtleDrawingWindow( );
          playGround.add( this ); //this method will eventually result in
                                  //calling this object's setCanvas method
          playGround.setVisible( true );
       }
    }
      
       
     
        
//--------------------------------------
//    Public Methods:
//
//
//        void    backup          ( double                  )
//        void    bodyColor       ( Color                   )
//        void    bodyShape       ( Point[ ]                )
//
//        void    forward         ( double                  )
//
//        void    hide            (                         )
//        void    heading         (                         )
//
//        void    init            (                         )
//
//        void    move            ( double                  )
//        void    moveTo          ( double, double          )
//
//        void    pause           (                         )
//        
//        void    penDown         (                         )
//        void    penUp           (                         )
// 
//        void    redraw          ( Graphics                )  
//
//        void    setCanvas       ( DrawingCanvas           )
//        void    start           (                         )
//        void    show            (                         )
//		  void    speed           ( int                     )
//  
//        void    turn            ( double                  )
//
//--------------------------------------
    
    /**
     * Moves this turtle backward for a specified length.
     *
     * @param length the length to move backward
     */
    public void backup(double length) {
        instructionBuffer.add(new MoveCommand(this, -length));
    }
    
    /**
     * Changes the body color of this turtle. You cannot change the body
     * color while the turtle is moving. Basically you want to set
     * the turtle's body color once.
     *
     * @param color a new body color
     *
     */
    public void bodyColor(Color color) {
        myShape.setColor(color);
    }
    
    /**
     * Sets the shape of this turtle to the give shape
     * expressed in an array of Point objects. The shape
     * can be any polygon. You cannot change the body
     * shape while the turtle is moving. Basically you want to set
     * the turtle's body shape once.
     *
     * @param point  an array of Point objects representing a polygon
     *
     */
    public void bodyShape(Point[] point) {
       myShape.setRefShape( point );
    }
    
    
    /**
     * Moves this turtle forward for a given length. This method is 
     * the same as the move method with a positive argument.
     *
     * @param length the length to move forward
     */
    public void forward(double length) {
        instructionBuffer.add(new MoveCommand(this, length));
    }
    
    /**
     * Sets this turtle's heading for a specified degree. The current
     * heading is irrelevant. If the specified degree is
     * negative, then it is a clockwise direction
     *
     * @param degree the new orientation of this turtle
     *
     */
    public void heading(double degree) {
        instructionBuffer.add(new HeadingCommand(this, degree));
    }
        
 
    /**
     * Hides this turtle, that is, the turtle body is not displayed..
     *
     */
    public void hide( ) {
        instructionBuffer.add(new VisibilityCommand(this, false));
    }
       
    
    /**
     * Initializes this turtle. When a turtle is first created, it
     * is initialized automatically. The user needs to call this method
     * explicitly, when a new drawing is required by the same turtle.
     */
    public void init( ) {
        reset( );
        initMemory( );
    }
    

    /**
     * Jumps to the given (x, y). This is a warp jump so no drawing will
     * take place, even when the pen is down. 
     *
     * @param x the x coordinate of the target position
     * @param y the y coordinate of the target position
     */
    public void jumpTo( double x, double y ) {
      
        instructionBuffer.add(new JumpToCommand(this, new Position(x, y)));
    }
    
    
    /**
     * Moves this turtle's for a specified length. Positive length moves
     * the turtle forward and the negative length moves the turtle
     * backward relative to its orientation. The position of
     * this turtle is set to the new location. The orientation remains
     * the same.
     */
    public void move( double length ) {
      
        instructionBuffer.add(new MoveCommand(this, length));
    }
    
    /**
     * Moves this turtle to the specified target (x, y) position.
     * The turtle's orientation will be adjusted to face the
     * the target (x,y) position, regardless where this turtle
     * is facing before this method is called.
     *
     * @param targetX the x coordinate of the target point
     * @param targetY the y coordinate of the target point
     *
     */
    public void moveTo( double targetX, double targetY ) {
    	
        Position target = new Position(targetX, targetY);
        
        instructionBuffer.add(new MoveToCommand(this, target));
    }
    
    
    /**
     * Stops this turtle from moving. Once in the pause mode
     * this turtle needs to be activated by calling the start
     * method. Otherwise the turtle will never move again.
     *
     */
    public void pause( ) {
        inMotion = false;
    }

    /**
     * Sets the color of the turtle's pen.
     *
     * @param color a new pen color for subsequent drawing
     */
    public void penColor(Color color) {
        instructionBuffer.add(new PenColorCommand(this, color));
    }
    
    
    /**
     * Sets the pen state to down. Subsequent movement by this turtle
     * will result in drawing the line.
     */
    public void penDown( ) {
        instructionBuffer.add(new PenStateCommand(this, PEN_DOWN));
    }    

    /**
     * Sets the size of this turtle's pen.
     *
     * @param penSize the new pen size
     *
     */
    public void penSize( int penSize ) {
        instructionBuffer.add(new PenSizeCommand(this, penSize));
    }
    
    /**
     * Sets the pen state to up. Subsequent movement by this turtle
     * will not result in drawing the line, i.e., this turtle moves to a
     * specified location without drawing.
     */
    public void penUp( )  {
        instructionBuffer.add(new PenStateCommand(this, PEN_UP));
    }
    
    /**
     * Prints the specified message at the current position. The color
     * of text is fixed to black. You cannot change the text color.
     * 
     * @param message the text to display
     */
    public void print(String message) {
    	
    	instructionBuffer.add(
    			new PrintCommand(this, message));
    }
    
    /**
     * Required method to implement the DrawingController interface.
     * Redraws the shape currently drawn by the turtle. This method is
     * called from DrawingCanvas when the repaint method is invoked
     * by the system. Do not call this method directly from your code.
     *
     * @param g  the Graphics object where this turtle can draw
     */
    public void redraw( Graphics g ) {
        myGraphics = g;
                
        redrawCurrentPosition = new Position(0,0);
        redrawCurrentOrientation = 0.0;
        
        Enumeration<TurtleCommand> iter = memory.elements();
        
        while (iter.hasMoreElements()) {
        	iter.nextElement().execute();
        }
        
        //draw the turtle shape only if there's something in the memory
        //and the visibility flag is set (default is to show it)
        //When the memory is empty then it means the Turtle received
        //no instructions. So in this case, even if the showBody is true,
        //we decided not to display the turtle body. We only show when it 
        //received at least one command.
        if (!memory.isEmpty() && showBody) { 
          
            Polygon p = myShape.getShape(redrawCurrentPosition, 
                                   Math.toRadians(redrawCurrentOrientation));
                                         
            myCanvas.drawPolygon(myGraphics, p, myShape.getColor( ));                   
        }            
    }
    
    /** 
     * Required method to implement the Runnable interface. This method is 
     * called eventually by the system, and when called, will execute the 
     * commands in the instruction buffer provided that this turtle is in 
     * the motion mode (i.e. the pause method is not called).
     */
    public void run( ) { 
    	
       while (true) {
    	   
    	   if (inMotion) {
    		   
    		   while (!instructionBuffer.isEmpty()) {
    			   
    			   //remove the first command and execute it
    			   instructionBuffer.remove(0).execute();    			  
    		   }
    	   }
       }
    }
    
    /**
     * Required method to implement the DrawingController interface.
     * This method is called by a DrawingCanvas object.
     * Do not call this method directly from your code.
     *
     * @param canvas the object that called this method
     */
    public void setCanvas(DrawingCanvas canvas) {
    	
        myCanvas = canvas;

        //now the canvas is set, we can start the thread
        startRunning( );
    }
    

    /**
     * Shows this turtle, that is, the turtle body is displayed.
     *
     */
    public void show( ) {
        instructionBuffer.add(new VisibilityCommand(this, true));
    }
    
    
    /**
     * Changes the speed of this turtle
     *
     * @param speed the new speed of this turtle
     *
     */
    public void speed(int speed) {
        instructionBuffer.add(new SpeedCommand(this, speed ));     
    }

    /**
     * Starts moving this turtle in its own thread.
     */
    public void start( ) {               
        inMotion = true;
    }
    
    
    /**
     * Turns the turtle 
     */
    public void turn(double degree) {
        instructionBuffer.add(new TurnCommand( this, degree ));
    }
    
    
    
 //--------------------------------------
//    Package Methods:
//        
//
//        void    changeOrientation    ( double       )
//        void    changePenColor       ( Color        )
//        void    changePenSize        ( float        )
//        void    changePosition       ( Position     )
//        void    changeSpeed          ( int          )
//		  void    changeVisibility     ( boolean      )
//
//        void    plot                 ( Position     )
//    
//
//--------------------------------------

    /**
     * Sets the orientation of this turtle. 
     *
     * @param orientation the direction of this turtle's heading
     *
     */
    void changeOrientation(double orientation) {
    	
        this.currentOrientation = orientation;
        
        memory.add(new OrientationCommand(this, orientation));
    }   

    /**
     * Sets the pen color to the specified color.
     * 
     * @param color the new color
     */
    void changePenColor(Color color) {
        this.penColor = color;
    }
    
    /**
     * Sets the pen size to the specified size.
     * 
     * @param size the new pen size
     */
    void changePenSize(float size) {
        this.penSize = size;
    }
    
    /**
     * Sets the pen position of this turtle, true for pen down and
     * false for pen up.
     *
     * @param penState either Turtle.PEN_DOWN or Turtle.PEN_UP
     *
     */
    void changePenState(int penState) {
    	
        if (penState < PEN_DOWN || penState > PEN_UP) {
        	
            this.penState = PEN_DOWN;
            
        } else {
        	
            this.penState = penState;
        }
    }
    
    
    /**
     * Sets the current position of this turtle. Calling the method has
     * the effect of a turtle warping to a new position instantaneously. No
     * drawing will occur even if the pen is down.
     *
     * @param newLocation the new location of this turtle
     *
     */
    void changePosition(Position newLocation) {
    	
        this.currentPosition = newLocation;
        
        memory.add(new PositionCommand(this, newLocation));
    }
    
    /**
     * Changes the orientation of the turtle when redrawing
     * the commands in the 'memory' Vector
     * 
     * @param orientation the new orientation of this turtle
     */
    void changeRedrawOrientation(double orientation) {
    	
        this.redrawCurrentOrientation = orientation;
        
    }
    
    /**
     * Changes the position of the turtle while commands
     * in the 'memory' vector are being executed.
     * 
     * @param position the new position
     */
    void changeRedrawPosition(Position position)  {
        
        this.redrawCurrentPosition = position;
    }
    
    /**
     * Changes the speed of this turtle to the designated
     * speed.
     * 
     * @param speed the new moving speed of this turtle
     */
    void changeSpeed(int speed) {
    	
        if ( speed > 0 ) { //ignore negative speed
        	
            this.currentSpeed = speed;
        }
    }
    
    /**
     * Change the visibility of the Turtle body. 
     * 
     * @param status true to show Turtle body; false to hide the body
     */
    void changeVisibility(boolean status) {
    	
    	showBody = status;
    }
    
    /**
     * Draws this turtle's trajectory for a specified length. Positive length 
     * moves the turtle forward and the negative length moves the turtle
     * backward relative to its orientation. The position of
     * this turtle is set to the new location. The orientation remains
     * the same.
     */
    void draw(double length) {   
    	
        Position target = new Position( ); 
        double   angle, distance, totalTime, totalSteps;
        int      pauseTime;
        
        if (length < 0.0) {
            angle = Math.toRadians(currentOrientation + 180.0);
            length = -length;
            
        } else {
        	
            angle = Math.toRadians(currentOrientation);
        }
        
        totalTime  = length / currentSpeed; //unit is second
        totalSteps = length / STEP_INCR;
        
        pauseTime = (int) Math.round( 1000 * totalTime / totalSteps ); 
                        //unit is millisec
        
        distance = STEP_INCR;
        
        while (distance <= length) {
            
            target.x = currentPosition.x + distance * Math.cos(angle); 
            target.y = currentPosition.y + distance * Math.sin(angle);
                        
            memory.add(new PlotCommand(this, target, penState,
                		                penColor, penSize));
             
            myCanvas.repaint(); //this forces the repaint of the canvas

            try {
               Thread.sleep( pauseTime );
            }
            catch (Exception e) { }
            
            distance += STEP_INCR;
        }
        
        currentPosition = target;                   
    }
    
    /**
     * Draw the turtle shape facing the given orientation
     */
    void drawShape( double orientation ) {
    	
        Polygon p = myShape.getShape(redrawCurrentPosition, 
        		                     Math.toRadians(orientation));
        myCanvas.drawPolygon(myGraphics, p, myShape.getColor());        
    }
   
    /**
     * Draws the specified text at the current position of the
     * turtle.
     * 
     * @param text
     */
	void drawText(String text) {
		
		memory.add(new DrawTextCommand(this, text, currentPosition));
		
		myCanvas.repaint();		
	}
	
	
    /**
     * Draws text specified by the DrawTextCommand command in the memory
     * 
     * @param text        the text to display
     * @param position    the position on the canvas to display the text
     */
    void drawTextMemory(String text, Position position) {
		
		myCanvas.drawText(myGraphics, Color.BLACK, 2, position, text);
	}
	
    /**
     * If the pen is down, this method calls the DrawingCanvas plot method
     * to draw a line between redrawCurrentPosition to target
     * in the specified color and pen size. 
     *
     * @param target     the next starting position for redrawing.
     * @param penState   the state of the pen (UP or DOWN).
     * @param penColor   the pen color.
     * @param penSize    the thickness of the pen.
     *
     */    
    void plot(Position target, int penState, Color penColor, double penSize) {
      
        if ( penState == PEN_DOWN ) { //draw only when the pen is down
            myCanvas.plot(myGraphics, penColor, penSize, 
            		      redrawCurrentPosition, target);
        }
        
        redrawCurrentPosition = target;
    }
    
    
    /**
     * Turns this turtle's orientation for a specified degree in
     * counter clockwise direction. If the specified degree is
     * negative, then it is a clockwise direction
     *
     * @param degree the amount of degree this turtle rotates from
     * the current orientation
     *
     */
    void rotate(double degree) {

        int   degreeIncr, sign;
        
        double limit, orientationIncr = 0;
        
        double totalTime  = degree / (4*currentSpeed); //unit is second
        double totalSteps = degree / (4*STEP_INCR);
        
        int pauseTime = (int) Math.round( 1000 * totalTime / totalSteps ); 
                        //unit is millisec
        
        degreeIncr = 4*STEP_INCR;
        
        if (degree < 0) sign = -1; else sign = 1;

        limit = Math.abs( degree );
        
        orientationIncr += degreeIncr;
        
        while (orientationIncr <= limit) {

            memory.add(new OrientationCommand(this, currentOrientation
                                                    + sign*orientationIncr));
            myCanvas.repaint( );
            
            try {
               Thread.sleep(pauseTime);
            }
            catch (Exception e) { }
            
            orientationIncr += degreeIncr;
            
        }
        
        currentOrientation += degree;
        
        memory.add(new OrientationCommand(this, currentOrientation));
    }
    
    /**
     * Implements the MoveTo command by using the changeOrientation
     * and draw methods.
     *
     * @param target a target position this turtle moves to
     *               from its current position.
     */
    void turnAndMove( Position target ) {
        //compute the orientation and the distance
        //between the current position and the target
        //position (x,y)
      
        double distance, orientation, deltaY, deltaX;
                
        deltaX = target.x - currentPosition.x;
        deltaY = target.y - currentPosition.y;
        
        distance    = Math.sqrt( deltaX * deltaX + deltaY * deltaY );
        
        //there's nothing to do if the distance is zero
        if (distance != 0 ) {
          
            orientation = Math.toDegrees(Math.atan2( deltaY, deltaX));
            
            changeOrientation(orientation);
            draw(distance);
        }
    }
    
    
    
//--------------------------------------
//    Private Methods:
//
//        void    initMemory          (            )
//        void    reset               (            )
//        void    startRunning        (            )
//
//--------------------------------------


    /**
     * Clears this turtle's memory.
     */
    private void initMemory( ) {
    	
       memory = new Vector<TurtleCommand>( ); 
       
       instructionBuffer  = new Vector<TurtleCommand>( );  
       
       //NOTE:  We're using Vector here because it is synchronized 
       //		automatically, unlike other Java Collection classes, 
       //		which we need to synchronize manually. 
       
       myShape = new TurtleShape( );
    }
    
    
    /**
     * Resets this turtle to it's initial state
     */
    private void reset( ) {
        this.currentOrientation = 0.0;
        this.currentPosition    = new Position( 0, 0);
        this.currentSpeed       = DEFAULT_SPEED;
        
        this.penState           = PEN_DOWN;
        this.penColor           = DEFAULT_COLOR;
        this.penSize            = DEFAULT_PENSIZE;
        
        this.showBody           = true;
        this.inMotion           = true;
        
        myCanvas                = null;       
    }
    
    
    /**
     * Starts this turtle running in its own thread.
     *
     */
    private void startRunning( ) {  
    	
        Thread myThread = new Thread( this );
        myThread.start( );
    }

}