import java.util.Scanner;

public class Name{
	public static void main(String[] args){
		String name;
		Scanner scan;
		String middleName;
		scan = new Scanner(System.in);
		name = scan.nextLine();

		for( int i=0; i<name.length();i++){
			if(name.charAt(i)==" "){
				middleName = name.charAt(i+1);
				break;
			}
		}
		System.out.println(middleName);
	}
}