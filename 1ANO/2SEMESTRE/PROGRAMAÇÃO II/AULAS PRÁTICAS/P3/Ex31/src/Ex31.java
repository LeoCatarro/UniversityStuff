import java.util.Scanner;
import galapagos.*;

public class Ex31{
    public static void main(String[] args) {
        Turtle turtle;
        Scanner c, a;
        float comp, alt;
        double c1, c2, a1, a2;
        turtle = new Turtle();

        System.out.println("Qual o comprimento do retangulo?");
        c = new Scanner(System.in);
        comp = c.nextFloat();

        System.out.println("Qual a altura do retangulo?");
        a = new Scanner(System.in);
        alt = a.nextFloat();

        //DESENHO DO RETANGULO DADO PELO UTILIZADOR:
        turtle.speed(100);
        turtle.move(comp);
        turtle.turn(90);
        turtle.move(alt);
        turtle.turn(90);
        turtle.move(comp);
        turtle.turn(90);
        turtle.move(alt);

        //DESENHO 40% MAIOR:
        turtle.turn(90);
        turtle.move(1.40 * comp);
        turtle.turn(90);
        turtle.move(1.40 * alt);
        turtle.turn(90);
        turtle.move(1.40 * comp);
        turtle.turn(90);
        turtle.move(1.40 * alt);

        //DESENHO 80% MAIOR:
        turtle.turn(90);
        turtle.speed(100);
        turtle.move(1.80 * comp);
        turtle.turn(90);
        turtle.move(1.80 * alt);
        turtle.turn(90);
        turtle.move(1.80 * comp);
        turtle.turn(90);
        turtle.move(1.80 * alt);


    }

}




