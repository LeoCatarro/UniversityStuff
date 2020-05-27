
import galapagos.*;
import java.awt.*; //for using Color

/**
 * This sample program shows how to use a TurtleDrawingWindow
 * explicitly with two turtles.
 */
public class TurtleSample4 {
    
    public static void main( String args[] )  {
    	
        Turtle                betsy, emily;
        TurtleDrawingWindow   playground;
        
        int      size, turn;
        
        
        playground = new TurtleDrawingWindow( );
        playground.setVisible( true );
        
        //Create two turtles and add them to the playground
        
        betsy   = new Turtle(Turtle.NO_DEFAULT_WINDOW);
        emily   = new Turtle(Turtle.NO_DEFAULT_WINDOW);
        
        playground.add(betsy);
        playground.add(emily);
        
        //Don't show the turtle bodies
        betsy.hide( );        //if you want to show turtle bodies
        emily.hide( );        //then set their body color to distinguish
                              //them. Use the bodyColor method.
                              //   betsy.bodyColor( Color.blue );
                              //   emily.bodyColor( Color.yellow );
        
        //Set pen colors
        betsy.penColor(Color.BLUE);
        emily.penColor(Color.YELLOW);
         
        //Now draw a square
        
        size     = 100;           //logical units
        turn     = 90;            //in degree
       
        //betsy draw counter clockwise square
        betsy.pause( );
        betsy.move( size );  
        betsy.turn( turn );
        
        betsy.move( size );         
        betsy.turn( turn );            
        
        betsy.move( size );            
        betsy.turn( turn );
        
        betsy.move( size );            
        betsy.turn( turn );
        
        //emily draws clockwise square of the same size
        emily.pause( );
        emily.move( -size );    //If you make emily visible, you will
        emily.turn( -turn );    //notice that emily is moving backward
                                //If you want emily to face forward
        emily.move( -size );    //then you must change emily's heading        
        emily.turn( -turn );    //to 180 deg first and make both           
                                //size and turn positive, exactly like
        emily.move( -size );    //betsy's code          
        emily.turn( -turn );
        
        emily.move( -size );            
        emily.turn( -turn );
        
        //now start both turtles together
        betsy.start( );
        emily.start( );
        
        //NOTE:
        // Theoretically, the turtles move in their own thread, so if you don't
        // pause them, they would move immediately after receiving the first 
        // command. This makes betsy start moving while emily is still waiting 
        //for the commands to arrive. 
        // In reality you will not see the delay even if you don't put the
        // pause and start commands, because the sequence of commands in 
        // this program is very short. The time to set up the drawing window 
        // will take much longer time and executing the statements in 
        // this program.
       
	  }    
    
}