//*******************************************************************************************
//****************TESTE PARA FAZER MÉTODOS EM JAVA(METODO QUE SOMA 2 NUMEROS)****************
//*******************************************************************************************		

import java.util.Scanner;

class Soma2numeros{

	//FUNÇAO QUE CALCULA A SOMA DE 2 NUMEROS
	static int soma(int a, int b){
	return a + b;
	}


	//MAIN
	public static void main(String[] args) {
		int a,b,c,d;
		
		Scanner s = new Scanner(System.in);
		a = s.nextInt();
		b = s.nextInt();
		c = s.nextInt();
		d = s.nextInt();

		System.out.println(add(a,b,c,d));
	}


	static double add(int a, int b, int c , int d){

	return a/b * c/d;
	}
}