import java.util.Scanner;

class FMLname{
	public static void main(String[] args){
		Scanner first, middle, last;
		String first_name, middle_name, last_name;

		System.out.println("Insira o seu First Name");
		first = new Scanner(System.in);
		first_name = first.next();

		System.out.println("Insira o seu Middle Name");
		middle = new Scanner(System.in);
		middle_name = middle.next();

		System.out.println("Insira o seu Last Name");
		last = new Scanner(System.in);
		last_name = last.next();

		System.out.println(first_name + " " + middle_name.substring(0,1) +"." + last_name + ".");
	}
}