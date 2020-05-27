import java.util.Scanner;

class Ex6 {
	public static void main(String[] args) {
		Scanner min,max;

		int min1,max1;

		System.out.println("Qual o minimo?");
		min = new Scanner(System.in);
		min1 = min.nextInt();

		System.out.println("Qual o maximo?");
		max = new Scanner(System.in);
		max1 = max.nextInt();

		for(int i = min1 ; i <= max1 ; i++){
			if( (i+1)%10 != 0){
				System.out.print(" " +i);
			}
			else{
				System.out.println(" " + i);
			}
		}

	}
}