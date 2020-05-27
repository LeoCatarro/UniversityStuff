import java.util.Scanner;

class Ex9_10{
	public static void main(String[] args) {
		
		/* 	DE UM CHAR, DEVOLVER A SUA POSIÇÃO NA TABELA ASCII, SENDO O CHAR DADO COMO UMA CONSTANTE E NAO PEDIDO AO UTILIZADOR
		char s;
		s = 'X';
		System.out.println(s);
		System.out.println( (int) s );
		*/
//*******************************************************************************************************************************
		/*   ENTRADA SENDO CHAR
		char s;
		Scanner scanner;

		System.out.println("Insira o seu character");
		scanner = new Scanner(System.in);
		s = scanner.next().charAt(0);

		System.out.println(s);
		System.out.println( (int)s );
		*/
//*****************************************************************************************************************************
		//ENTRADA ACEITE COMO STRING:(INCOMPLETO)
		String s;
		//Scanner scanner;

		System.out.println("Insira o character:");
		Scanner sc = new Scanner(System.in);
		s = sc.nextLine();
			if(s.length()>1){
			System.out.println("ERROR: Insira apenas um character");
		}
		else{
				System.out.println("pila");

			
		}

	}

}
