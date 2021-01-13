package galapagos;


/**
 * The Position object represent a point in the
 * coordinate system. We cannot use java.awt.Point
 * because we need double values for x and y.
 * 
 * Date Modified: Feb 24, 2009 (just rearranging braces to conform to style
 *                              guide)
 * 
 * @author Dr Caffeine
 * 
 */
class Position {
	
	public double x;
	public double y;

	public Position( ) {
		this(0.0, 0.0);
	}

	public Position(double x, double y) {
		
		this.x = x;
		this.y = y;
	}
}