
import galapagos.*;

/**
 * This sample program shows the most basic way
 * of using a Turtle. This turtle draws a square
 * whose side length is 100 logical units. The
 * default drawing window has horizontal and vertical grid
 * lines 50 logical units apart. Notice the pen size,
 * line color, and others.
 * 
 */
public class TurtleSample1 {
    
    public static void main( String args[] ) {
    	
        Turtle   myTurtle;
        
        int      size, turn;
        
        myTurtle = new Turtle( );
                
        size     = 100;           //logical units
        turn     = 90;            //in degree
       
        //draw a square
        myTurtle.move( size ); 
        myTurtle.turn( turn );
        
        myTurtle.move( size ); 
        myTurtle.turn( turn );
        
        myTurtle.move( size ); 
        myTurtle.turn( turn );
        
        myTurtle.move( size ); 
        myTurtle.turn( turn );
       
    }        
}