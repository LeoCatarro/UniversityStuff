import Interfaces.Calculadora;

import java.util.Scanner;

public class helloWorld /*implements Calculadora */{


    public static int soma(int n1, int n2) {
        return n1 + n2;
    }


    public static int dif(int n1, int n2) {
        return n1 - n2;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n1 = input.nextInt();
        int n2 = input.nextInt();
        System.out.println("oi: " + soma(n1, n2));
        System.out.println("oi2: " +dif(n1, n2));
    }


}
