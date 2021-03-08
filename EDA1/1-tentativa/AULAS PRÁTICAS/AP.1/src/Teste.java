public class Teste {
    public static void main(String[] args) {
        //Fracao fracA = new Fracao();
        Fracao fracB = new Fracao(2,4);
        Fracao fracC = new Fracao(1, 4);
        int b;
        b=fracB.compareTo(fracC);
        System.out.println(b);
    }
}
