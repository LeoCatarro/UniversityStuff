public class Classificacao{

    private final Integer numero;
    private final Integer nota;
    private final String descricao;
    private final String obs;

    public Classificacao(Integer numero, Integer nota, String descricao, String obs) {
        this.numero = numero;
        this.nota = nota;
        this.descricao = descricao;
        this.obs = obs;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getObs() {
        return obs;
    }


    public String toString() {
        return "Classificacao{" +
                "numero=" + numero +
                ", nota=" + nota +
                ", descricao='" + descricao + '\'' +
                ", obs='" + obs + '\'' +
                '}';
    }

    public static void main(String[] args) {

        Classificacao c = new Classificacao(43025,15,"Podia ser melhor", "Excelente Aluno");

        System.out.println(c.toString());
    }
}
