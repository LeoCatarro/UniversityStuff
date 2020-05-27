package galapaxos;

import java.util.List;

public class InterpreterClass implements Interpreter {

    Turtle turtle = new Turtle(Turtle.CREATE_DEFAULT_WINDOW); // para so ter 1 janela aberta
    TurtleDrawingWindow turtleDraw = new TurtleDrawingWindow();

    InterpreterClass() {
        //turtleDraw.setOrigin(230,0);
        turtle.speed(6000); // de modo a ser instantaneo a construção do gráfico
        turtle.penSize(1);

        turtleDraw.setGrid(false);
        turtleDraw.add(turtle);
        turtleDraw.setVisible(true);
        turtle.hide();
    }

    public void run(List<TurtleStatement> program) {
        for (int i = 0; i < program.size(); i++) {

            TurtleStatement t = program.get(i);
            t.run(this);            //simplesmente diz que vai correr nesta class (this)

        }
    }

    public void runForward(Forward statement) {
        turtle.move(statement.getDistance());

    }

    public void runTurn(Turn statement) {
        turtle.turn(statement.getRotation());

    }

    public void runPenUp(PenUp statement) {
        turtle.penUp();

    }

    public void runPenDown(PenDown statement) {
        turtle.penDown();
    }

    public void runLeap(Leap statement) {
        turtle.penUp();
        turtle.move(statement.getDistance());
        turtle.penDown();
    }

}