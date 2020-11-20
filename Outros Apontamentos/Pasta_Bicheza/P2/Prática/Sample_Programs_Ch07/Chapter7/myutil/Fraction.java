package myutil;

/**
 * An instance of this class represents a fraction.
 *
 * @author Dr. Caffeine
 * 
 * Updated July, 2006
 *
 */
public class Fraction {

    /** the numerator of this fraction */
	private int numerator;

    /** the denominator of this fraction */
	private int denominator;
 
//--------------------------------------
//  Constructors
//--------------------------------------   
    /**
     * Creates a fraction 0/1
     */
    public Fraction( ) {
        this(0, 1);
    }
    
    /**
     * Creates a fraction number/1
     *
     * @param number the numerator
     */
    public Fraction(int number) {
        this(number, 1);
    }
    
    /**
     * Creates a copy of frac
     *
     * @param frac a copy of this parameter is created
     */
    public Fraction(Fraction frac) {
        this(frac.getNumerator(), frac.getDenominator());
    }
    
    /**
     * Creates a fraction num/denom. Create a negative
     * fraction as -num and denom. If negative values
     * are specified for both num and denom, the fraction
     * is converted to a positive. If num is positive and
     * denom is negative, the fraction will be converted to
     * have negative num and positive denom.
     * When the num is zero, denom is set to 1. Zero is
     * always represented as 0/1
     *
     * @param num   the numerator
     * @param denom the denominator
     */
    public Fraction(int num, int denom) {
        
        if (denom < 0) {
            num   = -num;
            denom = -denom;
        }
        
        if (num == 0) {
            denom = 1;
        }
        
        setNumerator(num);
        setDenominator(denom);
    }
 
//----------------------------------------
//  Class Methods
//  
//          public int gcd (int, int)
//
//----------------------------------------

    /**
     * Returns the greatest common divisor of 
     * the parameters m and n
     *
     * @param m the first number
     * @param n the second number
     *
     * @return the greatest common divisor of m and n
     */
    public static int gcd(int m, int n) {
    
        int r = n % m;
    
        while (r != 0) {
    
            n = m;
    
            m = r;
    
            r = n % m;
        }
    
        return m;
    }
    
    /**
     * Returns the smaller of the two parameters f1 and f2
     *
     * @param f1 the first fraction to compare
     * @param f2 the second fraction to compare
     *
     * @return the smaller of the two parameters
     */
    public static Fraction min(Fraction f1, Fraction f2) {
		
		//convert to decimals and then compare

		double f1_dec = f1.decimal();
		double f2_dec = f2.decimal();

		if (f1_dec <= f2_dec) { 

			return f1;

		} else {

			return f2;
		}
	}


//---------------------------------------------------------
//  Public Methods
//
//      Fraction    add             (   Fraction    )
//      Fraction    add             (   int         )
//      Fraction    divide          (   Fraction    )
//      Fraction    divide          (   int         )
//
//      boolean     equals          (   Fraction    )
// 
//      int         getDenominator  (               )
//      int         getNumerator    (               )
//
//      Fraction    multiply        (   Fraction    )
//      Fraction    multiply        (   int         )
//
//      void        setDenominator  (   int         )
//      void        setNumerator    (   int         )
//
//      Fraction    simplify        (               )
//      Fraction    subtract        (   Fraction    )
//      Fraction    subtract        (   int         )
//
//      String      toString        (               )
//
//---------------------------------------------------------

    /**
     * Returns the sum of this Fraction
     * and the parameter frac. The sum 
     * returned is NOT simplified.
     *
     * @param frac the Fraction to add to this
     *             Fraction
     *
     * @return the sum of this and frac
     */   
    public Fraction add(Fraction frac) {
        int a, b, c, d;
        
        Fraction sum;
        
        a = this.getNumerator();
        b = this.getDenominator();
        c = frac.getNumerator();
        d = frac.getDenominator();
        
        sum = new Fraction(a*d + b*c, b*d);
        
        return sum;
    }
    
    /**
     * Returns the sum of this Fraction
     * and the int parameter number. The sum 
     * returned is NOT simplified.
     *
     * @param number the integer to add to this
     *             Fraction
     *
     * @return the sum of this Fraction and number
     */   
    public Fraction add(int number) {
        
        Fraction frac = new Fraction(number, 1);
        
        Fraction sum = add(frac);
        
        return sum;
    }
    
 /* 
    //Alternative implmentation
    public Fraction add(int number) {
        int a, b, c;
        
        Fraction sum;
        
        a = this.getNumerator();
        b = this.getDenominator();
        c = number
        
        sum = new Fraction(a + b*c, b);
        
        return sum;
    }
*/

    
    
    /**
     * Returns the quotient of this Fraction
     * divided by the parameter frac. The quotient 
     * returned is NOT simplified.
     *
     * @param frac the divisor of the division
     *
     * @return the quotient of this fraction
     *         divided by frac
     */   
    public Fraction divide(Fraction frac) {
        int a, b, c, d;
        
        Fraction quotient;
        
        a = this.getNumerator();
        b = this.getDenominator();
        c = frac.getNumerator();
        d = frac.getDenominator();
        
        quotient = new Fraction(a*d, b*c);
        
        return quotient;
    }
    
    /**
     * Returns the quotient of this Fraction
     * divided by the int parameter number. The quotient 
     * returned is NOT simplified.
     *
     * @param number the divisor
     *
     * @return the quotient of this Fraction divided by number
     */   
    public Fraction divide(int number) {
        
        Fraction frac = new Fraction(number, 1);
        
        Fraction quotient = divide(frac);
        
        return quotient;
    }
    
    /**
     * Compares this fraction and the parameter frac for
     * equality. This method compares the two by first
     * reducing them to the simplest form.
     * 
     * @param frac the fraction object to compare
     *
     * @return true if this Fraction object and frac are equal
     */
    public boolean equals(Fraction frac) {
        
        Fraction f1 = simplify(); //simplify itself
        Fraction f2 = frac.simplify(); //simplify frac
        
        return (f1.getNumerator() == f2.getNumerator() &&
                f1.getDenominator() == f2.getDenominator());
    }
 
    /**
     * Returns the denominator of this fraction
     *
     * @return the denominator of this fraction
     */
	public int getDenominator( ) {
		return denominator;
	}  
    
    
    /**
     * Returns the numerator of this fraction
     *
     * @return the numerator of this fraction
     */
	public int getNumerator( ) {
		return numerator;
	}
    
    /**
     * Returns the product of this Fraction
     * and the parameter frac. The product 
     * returned is NOT simplified.
     *
     * @param frac the multiplier of the multiplication
     *
     * @return the product of this fraction
     *         and the parameter frac
     */   
    public Fraction multiply(Fraction frac) {
        int a, b, c, d;
        
        Fraction product;
        
        a = this.getNumerator();
        b = this.getDenominator();
        c = frac.getNumerator();
        d = frac.getDenominator();
        
        product = new Fraction(a*c, b*d);
        
        return product;
    }
    
    /**
     * Returns the product of this Fraction
     * and the int parameter number. The product 
     * returned is NOT simplified.
     *
     * @param number the muliplier
     *
     * @return the product of this Fraction and number
     */   
    public Fraction multiply(int number) {
        
        Fraction frac = new Fraction(number, 1);
        
        Fraction product = multiply(frac);
        
        return product;
    }
    
    /**
     * Sets the denominator of this fraction
     *
     * @param denom the denominator of this fraction
     */
	public void setDenominator(int denom) {
        if (denom == 0) {
            //Fatal error
            System.out.println("Fatal Error");
            System.exit(1);
        }
        denominator = denom;
    }
    
    /**
     * Sets the numerator of this fraction
     *
     * @param num the numerator of this fraction
     */
	public void setNumerator(int num) {
        numerator = num;
    }
     
    /**
     * Returns a new Fraction object that is in
     * the simplest form of this Fraction object. If
     * this Fraction is zero, then a simple copy of
     * it is returned.
     *
     * @return a Fraction object in the simplest form
     *         of this Fraction
     */
    public Fraction simplify( ) {
        
        int num   = getNumerator();
        int denom = getDenominator();
        
        int divisor = 1;
        
        if (num != 0) {
            divisor   = gcd(Math.abs(num), denom);
        }
        
        return new Fraction(num/divisor, denom/divisor);
    }
    
       
    /**
     * Returns the difference of this Fraction
     * and the parameter frac. The difference 
     * returned is NOT simplified.
     *
     * @param frac the Fraction to subtract from
     *             this Fraction
     *
     * @return the difference of this and frac
     */   
    public Fraction subtract(Fraction frac) {
        int a, b, c, d;
        
        Fraction diff;
        
        a = this.getNumerator();
        b = this.getDenominator();
        c = frac.getNumerator();
        d = frac.getDenominator();
        
        diff = new Fraction(a*d - b*c, b*d);
        
        return diff;
    }
    
    /**
     * Returns the difference of this Fraction
     * and the int parameter number. The difference 
     * returned is NOT simplified.
     *
     * @param number the int value to subtract
     *
     * @return the difference of this and number
     */   
    public Fraction subtract(int number) {
        
        Fraction frac = new Fraction(number, 1);
        
        Fraction difference = subtract(frac);
        
        return difference;
    }
    
    /**
     * Returns the String representation of this Fraction
     * 
     * @return the String representation of this Fraction
     */
    public String toString( ) {
        
        return getNumerator() + "/" + getDenominator();
    }
    
    //---------------------------------------------
    // Private methods
    //
    //      double decimal  (       )
    //
    //--------------------------------------------
    
    /**
     * Returns the decimal equivalent of this fraction
     *
     * @return the decimal equivalent of this fraction
     */
    private double decimal( ) {
		//returns the decimal equivalent
		return (double) getNumerator() / getDenominator();
	}
    
}

