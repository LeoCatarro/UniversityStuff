/*
	Program Exercise3
	Attempting to display the number of caracters in a given input
*/

import java.util.Scanner;

class three{
	public static void main(String[] args){
	Scanner entrada = new Scanner(System.in);
	String input;
	System.out.println("Escreva algo");
	input = entrada.next();
	System.out.println("Input has" + input.length() + "caracters");

	}
}	