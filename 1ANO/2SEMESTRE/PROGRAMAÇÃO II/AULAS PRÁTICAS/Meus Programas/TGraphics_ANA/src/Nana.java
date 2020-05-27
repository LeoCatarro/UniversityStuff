import galapagos.*;

public class Nana {
    public static void main(String[] args){
        Turtle turtle;
        turtle = new Turtle();

        turtle.speed(150);

        //MAKING AN "A"
        turtle.turn(180);
        turtle.penUp();
        turtle.move(200);
        turtle.penDown();
        turtle.turn(-90);
        turtle.move(100);
        turtle.turn(-90);
        turtle.move(50);
        turtle.turn(-90);
        turtle.move(50);
        turtle.turn(-90);
        turtle.move(50);
        turtle.turn(180);
        turtle.move(50);
        turtle.turn(-90);
        turtle.move(50);

        //MAKING A "N"
        turtle.turn(90);
        turtle.penUp();
        turtle.move(50);
        turtle.penDown();
        turtle.turn(90);
        turtle.move(100);
        turtle.turn(-135);
        turtle.move(140);
        turtle.turn(135);
        turtle.move(100);
        turtle.turn(180);
        turtle.penUp();
        turtle.move(100);
        turtle.turn(90);
        turtle.move(50);
        turtle.penDown();
        turtle.turn(90);

        //MAKING AN "A"
        turtle.move(100);
        turtle.turn(-90);
        turtle.move(50);
        turtle.turn(-90);
        turtle.move(50);
        turtle.turn(-90);
        turtle.move(50);
        turtle.turn(180);
        turtle.move(50);
        turtle.turn(-90);
        turtle.move(50);




    }
}