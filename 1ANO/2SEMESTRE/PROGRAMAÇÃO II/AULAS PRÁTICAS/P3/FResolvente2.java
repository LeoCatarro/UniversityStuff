/*
	CAPÍTULO 3: EX.18
	Programa: Fórmula Resolvente
	Programa que recebe os coeficientes de uma equação de 2ºgrau(a, b, c) e retorna os valores de X , quando igualado a expressão a 0
*/

import java.util.Scanner;
import javax.swing.*;

class FResolvente{
	public static void main(String[] args){

	float a, b, c, delta;
	double x1, x2;	     
	Scanner A, B, C;

	System.out.println("Qual o seu a?");
	A = new Scanner(System.in);
	a = A.nextFloat();

	System.out.println("Qual o seu b?") ;
	B = new Scanner(System.in);
	b = B.nextFloat();

	System.out.println("Qual o seu c?");
	C = new Scanner(System.in);
	c = C.nextFloat();


		if( (a > 0) && (b * b >= 4 * a * c)){

			delta = ((b * b)-(4 * a * c));
			x1 = ((-b) + (Math.sqrt(delta))) / (2 * a);
			x2 = ((-b) - (Math.sqrt(delta))) / (2 * a); 

			System.out.println("Os seus valores de X sao:");
			System.out.println("x=" + x1);
			System.out.println("x=" + x2);
		}
		  
		else{
			JOptionPane.showMessageDialog(null, "Impossivel calcular as raizes!", "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}		
