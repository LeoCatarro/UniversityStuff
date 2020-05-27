import java.util.Scanner;

class Ex5_9{
	public static void main(String[] args){
		int power;
		Scanner power1;

		System.out.println("What is the power of 10?");
		power1 = new Scanner(System.in);
		power = power1.nextInt();

		switch(power){
			
			case 6: 
				System.out.println("Million");
				break;

			case 9: 
				System.out.println("Billion");
				break;

			case 12: 
				System.out.println("Trillion");
				break;

			case 15: 
				System.out.println("Quadrillion");
				break;

			case 18: 
				System.out.println("Quintillion");
				break;

			case 21: 
				System.out.println("Sextillion");
				break;

			case 30: 
				System.out.println("Nonillion");
				break;

			case 100: 
				System.out.println("Googol");
				break;

			default: 
				System.out.println("The input value has no corresponding word");
				break;		  
		}
	}
}	