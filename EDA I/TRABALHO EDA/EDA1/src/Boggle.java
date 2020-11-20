import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Boggle {

    static LinkedList<LinkedList> list = new LinkedList<>();

    Map indexMap = new HashMap();
    static QuadHashTable<String> table = new QuadHashTable<>();
    static int counter = 0;

    String word;

    static char matrix [] [] = {

            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '}
    };

    public Boggle () {

        this(null);
    }

    public Boggle (char matrix [] []) {

        this.matrix = matrix;

    }

    private boolean hasWord(String s) {

        return s.equals(table.search(s));

    }

    private char [] [] readBoggle (BufferedReader b) throws IOException {

        String line1;

        while ((line1 = b.readLine()) != null) {

            String array = line1;
            System.out.println(array.toLowerCase());

            int count = 0;

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {

                    if (count < array.length()) {
                        matrix[i][j] = array.toLowerCase().charAt(count);

                        count++;
                    }
                }
            }
        }
        return matrix;
    }

    //usamos um HashMap para guardar as posicoes em que a chave é a Letra
    //visited para saber se ja estivemos na casa [i][j]
    //recursiva para percorrer os adjacentes
    private void findWord(char boggle[][], boolean visited[][], int i,
                         int j, String str)
    {

        visited[i][j] = true;
        str = str + boggle[i][j];

        LinkedList<Position<Character>> ls =  new LinkedList<>();

        if (hasWord(str))
        {
            counter++;

            for (char c : str.toCharArray())
            {
                ArrayList coordinates = (ArrayList)indexMap.get(c);
                String xy = (String)coordinates.get(0);

                if(coordinates.size() != 1)
                {
                    coordinates.remove(xy);
                    indexMap.put(c,coordinates);
                }

                String [] rowCol = xy.split(":");
                int x = Integer.parseInt(rowCol[0]);
                int y = Integer.parseInt(rowCol[1]);

                Position<Character> pos = new Position<>(c, x, y);

                ls.add(pos);
            }
            System.out.println(str + ": " + ls);
            list.add(ls);

        }


        for (int row = i - 1; row <= i + 1 && row < 4; row++)
            for (int column = j - 1; column <= j + 1 && column < 4; column++)
                if (row >= 0 && column >= 0 && !visited[row][column])
                    findWord(boggle, visited, row, column, str);
        visited[i][j] = false;
    }

    //usamos um HashMap para guardar as posicoes em que a chave é a Letra
    //usamos um ArrayList para diferenciar posicoes diferentes com a mesma Letra
    private LinkedList<LinkedList> solve () {

        boolean visited[][] = new boolean[4][4];


        String str = "";

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
            {
                if(indexMap.get(matrix[i][j]) != null)
                {
                    ArrayList list = (ArrayList) indexMap.get(matrix[i][j]);
                    list.add(i+":"+j);
                    indexMap.put(matrix[i][j],list);

                }else if(matrix[i][j] != ' ')
                {
                    ArrayList list = new ArrayList();
                    list.add(i+":"+j);
                    indexMap.put(matrix[i][j],list);
                }

            }



        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                findWord(matrix, visited, i, j, str);



        return list;
    }

    public String toString() {

        String  str = "{";

        for (int i = 0; i < 4; i++) {
            for ( int j = 0; j < 4 ; j++) {

                str += " " + matrix[i][j] + ",";
            }
            if(i < 3)
                str += "  \n";
        }

        str += "}";

        return str;
    }

    public static void main (String args []) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Leonardo\\Desktop\\allwords.txt"));
        BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Leonardo\\Desktop\\boggle5.txt"));


        String line;

        while((line = br.readLine()) != null) {

            table.insert(line);
        }

        Boggle boogle = new Boggle(matrix);

        boogle.readBoggle(br1);

        System.out.println(boogle);


        System.out.println(boogle.solve());

        System.out.println("Encontradas " + counter + " soluções");
    }
}