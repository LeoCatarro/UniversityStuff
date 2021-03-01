import java.awt.event.ActionListener;
import java.util.List;

public class NotasDeSO2 {

    public void adicionaClassif(Classificacao c, List<Classificacao> cList)
    {
        cList.add(c);
    }

    public List<Classificacao> getListOrdenadaDeClassif(List<Classificacao> cList)
    {

        return cList;
    }

    public static void main(String[] args) {

        NotasDeSO2 notas = new NotasDeSO2();

        Classificacao c = new Classificacao(43025,15,"Podia ser melhor", "Excelente Aluno");

        notas.adicionaClassif(c, cList);

        Classificacao c = new Classificacao(43026,15,"Podia ser melhor", "Excelente Aluno");

        Classificacao c = new Classificacao(43027,15,"Podia ser melhor", "Excelente Aluno");

    }
}
