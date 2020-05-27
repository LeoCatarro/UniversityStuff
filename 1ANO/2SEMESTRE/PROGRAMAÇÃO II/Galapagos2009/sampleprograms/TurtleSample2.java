
import galapagos.*;
import java.awt.*; //for using Color

/**
 * This sample program shows several different of changing
 * the turtle's properties such as pen color, pen size,
 * body color, etc.
 */
public class TurtleSample2  {
    
    public static void main( String args[] ) {
    	
        Turtle   myTurtle;
        
        int      size, turn;
        
        myTurtle = new Turtle( );
                
        size     = 100;           //logical units
        turn     = 90;            //in degree
       
        //set some properties
        myTurtle.bodyColor( Color.BLACK ); //body color is black
        
        myTurtle.jumpTo( 50, 0 );          //set the starting position to (50,0)
                                           //jumping to it
        
        //draw a square
        myTurtle.penSize( 5 );            //draw the bottom side 
        myTurtle.penColor( Color.GREEN ); //in green 5 units wide
        myTurtle.move( size );  
        myTurtle.turn( turn );
        
        myTurtle.penUp( );                //just move along the right side
        myTurtle.move( size );            //without drawing by placing the
        myTurtle.turn( turn );            //pen in the up position
        
        myTurtle.penDown( );              //draw again by putting the
        myTurtle.penSize( 3 );            //pen down; set pen size to 3
        myTurtle.penColor( Color.BLUE );  //and color to blue
        myTurtle.move( size );            
        myTurtle.turn( turn );
        
        myTurtle.speed( 5 );
        myTurtle.penSize( 7 );            //draw the final side in one-fourth
        myTurtle.penColor( Color.RED );   //the default speed (which is 20) with 
        myTurtle.move( size );            //pen size 7 and color red 
        myTurtle.turn( turn );
       
	  }    
    
}