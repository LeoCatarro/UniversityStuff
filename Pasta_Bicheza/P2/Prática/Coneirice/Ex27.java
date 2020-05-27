import java.util.Scanner;
public class Ex27 {

	public static void main(String[] args) {
		Scanner input;
		input = new Scanner(System.in);
		
		String string;
		
		System.out.print("Input a string: ");
		string = input.nextLine();
		
		System.out.println(string.length());
		System.out.println(string.substring(0, 1));
		System.out.println(string.substring(string.length()-1, string.length()));

	}

}
