import java.util.Scanner;
public class Ex19 {
	public static void main (String[] args){
		Scanner input;
		input = new Scanner(System.in);
		String first, middle, last;
		System.out.print("First name: ");
		first = input.next();
		System.out.print("Middle name: ");
		middle = input.next();
		System.out.print("Last name: ");
		last = input.next();
		middle = middle.substring(0, 1);
		System.out.println(first + " " + middle + ". " + last);
	}
}
