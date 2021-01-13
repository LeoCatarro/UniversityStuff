package galapaxos;



import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String [] args) {
        KochCurve charMult = new KochCurve();
        Compiler comp = new Compiler();
        InterpreterClass it = new InterpreterClass();


        Scanner sc = new Scanner(System.in);
        Vector<TurtleStatement> vec;
        String word;

        try {
            System.out.println("Introduza o L-System que pretende Iterar: ");
            String system = sc.nextLine();
            System.out.println("Introduza o numero de vezes que quer iterar o L-System pretendido: ");
            int n = sc.nextInt();


                switch (system.toLowerCase()) { // o lowercase é para a palavra ser case insensitive


                      case "koch curve":

                          charMult.setStart("F");
                          charMult.addRule('F', "F+F-F-F+F");

                          word = charMult.loopIter(n);

                          comp.addRule('F', new Forward(8));
                          comp.addRule('+', new Turn(90));
                          comp.addRule('-', new Turn(-90));

                          vec = comp.compile(word);
                          it.run(vec);

                          break;

                      case "koch snowflake":

                        charMult.setStart("F");
                        charMult.addRule('F', "F+F--F+F");

                        word = charMult.loopIter(n);

                        comp.addRule('F', new Forward(5));
                        comp.addRule('+', new Turn(90));
                        comp.addRule('-', new Turn(-90));

                        vec = comp.compile(word);
                        it.run(vec);

                        break;

                      case "sierpinski triangle":

                        charMult.setStart("F");
                        charMult.addRule('G', "F");
                        charMult.addRule('F', "F-G+F+G-F");
                        charMult.addRule('G', "GG");

                        word = charMult.loopIter(n);

                        comp.addRule('F', new Forward(5));
                        comp.addRule('G', new Forward(5));
                        comp.addRule('+', new Turn(120));
                        comp.addRule('-', new Turn(-120));

                        vec = comp.compile(word);
                        it.run(vec);

                        break;

                      case "sierpinski arrowhead":

                        charMult.setStart("F");
                        charMult.addRule('G', "F");
                        charMult.addRule('F', "G-F-G");
                        charMult.addRule('G', "F+G+F");

                        word = charMult.loopIter(n);

                        comp.addRule('F', new Forward(5));
                        comp.addRule('G', new Forward(5));
                        comp.addRule('+', new Turn(60));
                        comp.addRule('-', new Turn(-60));

                        vec = comp.compile(word);
                        it.run(vec);

                      break;

                      case "dragon curve":

                        charMult.setStart("F");
                        charMult.addRule('F',"G");
                        charMult.addRule('F', "F+G");
                        charMult.addRule('G', "F-G");

                        word = charMult.loopIter(n);

                        comp.addRule('F', new Forward(5));
                        comp.addRule('G', new Forward(5));
                        comp.addRule('+', new Turn(90));
                        comp.addRule('-', new Turn(-90));

                        vec = comp.compile(word);
                        it.run(vec);

                        break;

                      case "cantor set":

                         charMult.setStart("F");
                         charMult.addRule('G', "F");
                         charMult.addRule('F', "FGF");
                         charMult.addRule('G', "GGG");

                         word = charMult.loopIter(n);

                         comp.addRule('F', new Forward(15));
                         comp.addRule('G', new Leap(5));
                         comp.addRule('+', new Turn(90));
                         comp.addRule('-', new Turn(-90));

                         vec = comp.compile(word);
                         it.run(vec);

                         break;


                      default:

                          System.out.println("O nome do L-System está incorreto!"+"\n"+"Cuidado com espaços incorretos!");

              }
          }catch (Exception e){

            System.out.println("Algo de errado não está certo!");
        }


    }
}
