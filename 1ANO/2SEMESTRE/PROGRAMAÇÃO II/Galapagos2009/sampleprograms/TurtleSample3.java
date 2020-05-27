
import galapagos.*;


/**
 * This sample program shows how to use a TurtleDrawingWindow
 * explicitly. If TurtleDrawingWindow is not used, then a 
 * Turtle will create one internally. If you want to set
 * the parameters such as moving the location of logical
 * origin point to places other than the center of window,
 * changing the window scale (zoom in or zoom out view), etc.
 * Also, you must use a TurtleDrawingWindow if you want
 * use multiple Turtle objects on a single window.
 *
 */
public class TurtleSample3 {
    
    public static void main( String args[] ) {
    	
        Turtle                myTurtle;
        TurtleDrawingWindow   playground;
        
        int size, turn;
        
        
        playground = new TurtleDrawingWindow( );
        
        
        //**************** IMPORTANT ***************//
        //You must pass an int argument so the turtle
        //will not create a default drawing window. You must
        //create a turtle in this manner if you want to
        //add a turtle to the drawing window you create within
        //your program. Sample code for exercise 4.16 on page
        //190 is therefore wrong. Sorry.
        
        myTurtle   = new Turtle(Turtle.NO_DEFAULT_WINDOW);
        
        
        //You must connect the turtle to a TurtleDrawingWindow
        //by 'adding' it to the window.
        
        playground.add(myTurtle);
                
        //******************************************//
        
        
        //Set some properties for the playground.
        //Experiment with different values.
        
        playground.setUnit( 2.5 );  //one logical unit is equal to 3 pixels
                                    //default is 1, i.e. one unit equlas one pixel
        
        playground.setOrigin( 75, 50 ); //logical point (100,100) is at the 
                                        //center of the window
        
        playground.setGrid( true );  //Displays the grid line, which is a default
                                     //change the argument to false and see
                                     //what happens
        
        playground.setVisible( true ); //Don't forget to make the window visible.
                                       //You can make the
                                       //window visible first and then set properties
                                       //using setUnit, setOrigin, etc., but in this
                                       //case you may see the display in the original
                                       //property and suddenly change to the properties
                                       //you set.
         
        //Now draw a square
        
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