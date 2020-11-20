import java.util.Scanner;

class Ex27{
	public static void main(String[] args){

	String input;
	Scanner s_input;

	System.out.println("Insira uma Frase");
	s_input = new Scanner(System.in);
	input = s_input.nextLine();

	System.out.println(input.length());
	System.out.println(input.substring(0,1));
	System.out.println(input.substring(input.length()-1,input.length()));	
	}
}