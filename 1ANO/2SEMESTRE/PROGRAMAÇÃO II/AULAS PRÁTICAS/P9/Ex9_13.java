import java.util.Scanner;

class Ex9_13{
	public static void main(String[] args) {
		
		/* 	DE UM CHAR, DEVOLVER A SUA POSIÇÃO NA TABELA ASCII, SENDO O CHAR DADO COMO UMA CONSTANTE E NAO PEDIDO AO UTILIZADOR
		char s;
		s = 'X';
		System.out.println(s);
		System.out.println( (int) s );
		*/

		char s;
		Scanner scanner;

		System.out.println("Insira o seu character");
		scanner = new Scanner(System.in);
		s = scanner.next().charAt(0);

		System.out.println(s);
		System.out.println( (int)s ); 
	}

}
