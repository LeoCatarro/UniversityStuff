import java.util.LinkedList;


public class NotasDeSO2 {

    public Classificacao c;
    public LinkedList<Classificacao> cList;


    //Default Constructor
    public NotasDeSO2(Classificacao c, LinkedList<Classificacao> cList) {
        this.c = c;
        this.cList = cList;
    }


    //Getters e Setters
    public Classificacao getC() { return c; }

    public void setC(Classificacao c) { this.c = c; }

    public LinkedList<Classificacao> getcList() { return cList; }

    public void setcList(LinkedList<Classificacao> cList) { this.cList = cList; }


    //Methods
    public void adicionaClassif(Classificacao c) {
        this.cList.add(c);
    }

    public void printNotas() {
        for(Classificacao c : this.cList) {
            System.out.println(c.toString());
        }
    }

    public LinkedList<Classificacao> getListaOrdenadaDeClassif() {

        LinkedList<Classificacao> copiedList = this.cList;
        LinkedList<Classificacao> sortedList = new LinkedList<>();

        while(copiedList.size() > 0) {

            //Check highest classification
            Classificacao maxC = copiedList.getFirst();

            for(Classificacao c : copiedList) {
                if(c.getNota() > maxC.getNota())
                    maxC = c;
            }
            sortedList.add(maxC);
            copiedList.remove(maxC);
        }

        System.out.println("SortedList:");
        for (Classificacao c : sortedList) {
            System.out.println(c.toString());
        }

        return sortedList;      //LinkedList<T> toString() method needs to be edited!
    }

    public static void main(String[] args) {
        Classificacao c = new Classificacao(0, 0, "", "");
        LinkedList<Classificacao> cList = new LinkedList<Classificacao>();
        NotasDeSO2 notas = new NotasDeSO2(c, cList);

        c = new Classificacao(43025, 11, "NADA", "BOM");
        notas.adicionaClassif(c);

        c = new Classificacao(43026, 14, "BOM", "NADA");
        notas.adicionaClassif(c);

        c = new Classificacao(43027, 11, "NADA", "NADA");
        notas.adicionaClassif(c);

        c = new Classificacao(43029, 19, "BOM", "BOM");
        notas.adicionaClassif(c);

        c = new Classificacao(43030, 14, "BOM", "BOM");
        notas.adicionaClassif(c);

        System.out.println("NOTAS:");
        notas.printNotas();

        System.out.println("NOTAS ORDENADAS:");
        notas.getListaOrdenadaDeClassif();
    }
}
