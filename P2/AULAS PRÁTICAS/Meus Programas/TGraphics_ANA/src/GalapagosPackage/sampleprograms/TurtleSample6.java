
import java.awt.Color;

import galapagos.*;

/**
 * This sample program shows how to create the drawing window
 * explicitly and adding a turtle to it. If you want to use
 * a default drawing window, you don't have to create the
 * drawing window explicitly as shown here. If you want to change
 * the properties such as the different origin point or grid spacing,
 * you need to create the drawing window explicitly.
 * 
 * The turtle draws a triangle and prints the text at each turn.
 * While drawing, the turtle body appears and disappears.
 */
public class TurtleSample6 {
    
    public static void main( String args[] ) {
    	
        Turtle   t;
        
        TurtleDrawingWindow win = new TurtleDrawingWindow();
        
        int      size, turn;
        
        t = new Turtle(Turtle.NO_DEFAULT_WINDOW);
        t.penColor(Color.red);
        
        win.add(t);
        win.setVisible(true);
                
        size     = 100;    //logical units
        turn     = 120;    //in degree
       
        //draw a triangle
        t.move( size ); 
        t.turn( turn );
        
        t.print("First Turn");
        
        t.hide(); //hide the turtle
        
        t.move( size ); 
        t.turn( turn );
        
        t.print("Second Turn");
        
        t.show(); //show the turtle
        
        t.move( size ); 
        t.turn( turn );
        
        t.print("Third Turn");
        
        t.hide();
               
    }        
}