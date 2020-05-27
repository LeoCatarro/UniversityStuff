/*
Exercicio 5.12: Programa que indica se o ano é bissexto ou nao
*/

import java.util.Scanner;

class Ex5_12{
	public static void main(String[] args){
		int ano;
		Scanner year;

		System.out.println("Qual o ano?");
		year = new Scanner(System.in);
		ano = year.nextInt();

		if(ano % 4 == 0 && ano % 100 != 0){
			System.out.println("That is a Leap Year");
		}

		else if((ano % 4 == 0) && (ano % 100 == 0) && (ano % 400 == 0)){
			System.out.println("That is a Leap Year");
		}

		else{
			System.out.println("That is Not a Leap Year");
		}
	}
}