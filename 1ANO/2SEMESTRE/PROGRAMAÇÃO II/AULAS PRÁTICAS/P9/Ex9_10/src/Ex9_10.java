import java.util.Scanner;

public class Ex9_10 {
    public static void main(String[] args) {
        Scanner sc;
        String s;

        System.out.println("Insira o seu caracter:");
        sc = new Scanner(System.in);
        s = sc.nextLine();

        if(s.length()>1){
            System.out.println("ERRO:Insira apenas 1 caracter!");
        }
        else{
            if(s.charAt(0) != '@') {
                while (s.charAt(0) != '@') {
                    System.out.println("Cód ASCII:" + (int) s.charAt(0));
                    s = sc.nextLine();
                }
            }
            else{
                System.out.println("Foi encontrado o caracter '@' , logo o programa termina aqui!");
            }
        }

    }
}
