package galapagos;

import java.awt.*;

/**
 * Superclass of different types of turtle commands.
 * Each command correspond to a Turtle operation such as move and turn.
 *  
 * Date Modified: February, 2009
 *
 * @author Dr Caffeine 
 */
abstract class TurtleCommand {
    
    protected Turtle owner;
    
    abstract void execute( );
    
    public void addOwner(Turtle t) {
        owner = t;
    }  
}

class DrawTextCommand extends TurtleCommand {   //********  DRAW TEXT COMMAND
	
	private String text;
	private Position position;
	
	public DrawTextCommand(Turtle owner, String text, Position position) {
		this.owner = owner;
		this.text = text;
		this.position = position;
	}
	
	void execute( ) {
		owner.drawTextMemory(text, position);
	}
}


class HeadingCommand extends TurtleCommand {	//********  HEADING
	
    private double heading;
    
    public HeadingCommand(Turtle owner, double heading) {
        this.owner = owner;
        this.heading = heading; //headin in degree
    }
    
    void execute( ) {
        owner.changeOrientation(heading);
    }
}

class JumpToCommand extends TurtleCommand {		//********  JUMPTO
	
    private Position position;
    
    public JumpToCommand(Turtle owner, Position position)  {
    	
        this.owner = owner;
        this.position = position;
    }
    
    void execute( )  {
        owner.changePosition(position);
    }
}

class MoveCommand extends TurtleCommand {		//********  MOVE
    private double length;
    
    public MoveCommand(Turtle owner, double length) {
        this.owner  = owner;
        this.length = length;
    }
    
    void execute( ) {
        owner.draw(length);
    }
}

class MoveToCommand extends TurtleCommand {		//********  MOVETO
	
    private Position target;
    
    public MoveToCommand( Turtle owner, Position target ) {
    	
        this.owner = owner;
        this.target = target;
    }
    
    void execute( ) {
    	
        owner.turnAndMove( target );
    }
}

class OrientationCommand extends TurtleCommand {	//********  ORIENTATION
	
    private double orientation;
    
    public OrientationCommand(Turtle owner, double orientation)  {
    	
        this.owner  = owner;
        this.orientation = orientation;
    }
    
    void execute( ) {
        owner.changeRedrawOrientation( orientation );
    }
}

class PenColorCommand extends TurtleCommand {		//********  PEN COLOR
	
    private Color color;
    
    public PenColorCommand( Turtle owner, Color color ) {
    	
        this.owner = owner;
        this.color = color;
    }
    
    void execute( ) {
    	
        owner.changePenColor( color );
    }
}

class PenSizeCommand extends TurtleCommand {		//********  PEN SIZE
	
    private float size;
    
    public PenSizeCommand(Turtle owner, float size)  {
        this.owner = owner;
        this.size  = size;
    }
    
    void execute( ) {
    	
        owner.changePenSize( size );
    }
}

class PenStateCommand extends TurtleCommand {		//********  PEN STATE
	
    private int state;
    
    public PenStateCommand(Turtle owner, int state)  {
    	
        this.owner = owner;
        this.state = state;
    }
    
    void execute( ) {
    	
        owner.changePenState( state );
    }
}


class PlotCommand extends TurtleCommand {			//********  PLOT
	
    private Position target;
    private int      penState;
    private Color    penColor;
    private double   penSize;
    
    public PlotCommand(Turtle owner,    Position target, 
                       int    penState, Color    penColor, 
                       double penSize ) {
        this.owner    = owner;
        this.target   = target;
        this.penState = penState;
        this.penColor = penColor;
        this.penSize  = penSize;
    }
    
    void execute( ) {
    	
        owner.plot( target, penState, penColor, penSize );
    }
}

class PositionCommand extends TurtleCommand {			//********  POSITION
    
    private Position position;
    
    public PositionCommand(Turtle owner, Position position) {
        this.owner = owner;
        this.position  = position;
    }
    
    void execute( ) {
    	
        owner.changeRedrawPosition( position );
    } 
}

class PrintCommand extends TurtleCommand {   //********  PRINT COMMAND
	
	private String text;
	
	public PrintCommand(Turtle owner, String text) {
		this.owner = owner;
		this.text = text;
	}
	
	void execute( ) {
		owner.drawText(text);
	}
}


class SpeedCommand extends TurtleCommand {			//********  SPEED 
	
    private int speed;
    
    public SpeedCommand( Turtle owner, int speed ) {
    	
        this.owner = owner;
        this.speed  = speed;
    }
    
    void execute( ) {
    	
        owner.changeSpeed(speed);
    }
}


class TurnCommand extends TurtleCommand {             //********  TURN
    
	private double degree;
    
    public TurnCommand(Turtle owner, double degree) {
        this.owner = owner;
        this.degree = degree;
    }
    
    void execute( ) {
    	
        owner.rotate(degree);
    }
}

class VisibilityCommand extends TurtleCommand {       //******** VISIBILITY
	
	private boolean status;
	
	public VisibilityCommand(Turtle owner, boolean status) {
		this.owner = owner;
		this.status = status;
	}
	
	void execute( ) {
		owner.changeVisibility(status);
	}
}


