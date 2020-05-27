import java.util.Scanner;
import java.util.Random;

class Jogo{
//MÉTODO PARA MOSTRAR O TABULEIRO n*n
static void tabuleiro(int x, int y){
	Random r = new Random();
	if(x == y){	
		for(int i=0 ; i<x ; i++){
			for(int j=0 ; j<y ; j++){
				System.out.print(r.nextInt(4)+1);
			}
			System.out.println();
		}
		System.out.println();
	}
	else{
		System.out.println("Impossivel criar tabuleiro(tabuleiro do tipo n*n)");
	}
}

//MÉTODO QUE MOSTRA A PONTUAÇÃO DE CADA JOGADA FEITA
/*	int pontos;
	pontos = (n*(n+1))/2;
	return pontos;
}
*/

//**********************MAIN*********************************
public static void main(String[] args) {
		int x , y , i , j;
		Scanner s;
		s = new Scanner(System.in);
		String[][] t;
		System.out.println("COLOR SQUARES: MODO-> ");
		System.out.println("");
		System.out.println("Qual as dimensoes do seu tabuleiro de jogo?");
		System.out.println("Qual o seu x?");
		x = s.nextInt();
		System.out.println("Qual o seu y?");
		y = s.nextInt();

		tabuleiro(x,y);
		//pontuacao(n);
	}	
}