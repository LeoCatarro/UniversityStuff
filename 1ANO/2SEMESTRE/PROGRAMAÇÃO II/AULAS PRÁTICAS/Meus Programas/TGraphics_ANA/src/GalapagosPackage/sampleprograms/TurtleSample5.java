
import galapagos.*;
import java.awt.*; //for using Color
import javax.swing.*;

/**
 * This sample program shows how to use a TurtleDrawingWindow.
 * 
 */
public class TurtleSample5 {
    
    public static void main( String args[] ) {
    	
        Turtle                myTurtle;
        TurtleDrawingWindow   playground;
        
        int  size, turn;     
        
        playground = new TurtleDrawingWindow( );
        
        myTurtle   = new Turtle(Turtle.NO_DEFAULT_WINDOW);
        myTurtle.bodyColor(Color.magenta);
                
        playground.add( myTurtle );
        
        playground.setVisible( true );
                
        //Get input
        size = Integer.parseInt(
        		JOptionPane.showInputDialog("Enter the size of a square:"));
         
        turn = 90;
       
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