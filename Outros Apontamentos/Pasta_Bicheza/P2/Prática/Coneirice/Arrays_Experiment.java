import java.util.Scanner;
public class Arrays_Experiment {

	public static void main(String[] args) {
		System.out.print("Quantos numeros deseja incluir na lista? ");
	    
	    Scanner input = new Scanner(System.in);
	    String n_int = input.next();
	    
	    int n = Integer.parseInt(n_int);
	    
	    int[] lista;
	    lista = new int[n];
	    
	    for(int x=0; x<n; x++){
	      System.out.print(x+1 + "º numero: ");
	      String n_para_lista = input.next();
	      int c = Integer.parseInt(n_para_lista);
	      lista[x] = c;
	    }
	    for(int x=0; x<n; x++){
	    	System.out.println(lista[x]);
	    }
	}

}
