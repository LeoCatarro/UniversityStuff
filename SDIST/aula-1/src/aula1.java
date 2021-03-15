import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class aula1
{
    public static LinkedList<String> insertionSort(LinkedList<String> people)
    {
        Collections.sort(people);

        return people;
    }

    public static void main(String[] args) throws IOException {

        /*System.out.println("System java version: "+System.getProperty("java.version"));
        System.out.println("System java vendor: "+System.getProperty("java.vendor"));
        System.out.println("System classpath: "+System.getProperty("java.class.path"));

        for( Object k: System.getProperties().keySet())
        {
            System.out.println("\t K " + k.toString()+" = " + System.getProperties().getProperty(k.toString()));
        }

        for (int i=0; i < args.length; i++) {
            System.out.println("Hello " + args[i]);
        }*/
        try {
            byte[] b= new byte[128];
            int lidos= System.in.read(b);
            String s= new String(b, 0, lidos -1); // ou -2 no windows
            System.out.println("lido: "+lidos);
            System.out.println("s: "+s+"\n\n");
            int valor= Integer.parseInt(s.substring(0,lidos-1));
            valor = valor + 1;
            System.out.println("valor: "+valor);
        }
        catch(IllegalArgumentException iae) {
            System.out.println("ERRO: Inserir apenas digitos");
        }


        /*LinkedList<String> people = new LinkedList<String>();
        people.add("Maria");
        people.add("Joao");
        people.add("Manuel");

        System.out.println("Before Sort:" + people);
        System.out.println("After Sort:" + insertionSort(people));*/
    }
}
