public class Classificacao implements Comparable<Classificacao>{

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

    @Override
    public int compareTo(Classificacao c) {
        int result = this.nota.compareTo(c.nota);
        return result == 0 ? this.nota.compareTo(c.nota) : result;
    }
}
