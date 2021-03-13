//1)
//1.1)
class Fracao implements Comparable<Fracao>{
    public int numerador;
    public int denominador;


    //Construtor vazio
    public Fracao() {
        this.numerador = 0;
        this.denominador = 1;
    }

    //Construtor que recebe 1 parametro
    public Fracao(int m) {
        this.numerador = m;
    }

    //Construtor que recebe os 2 parametros
    public Fracao(int n, int d) {
        this.numerador = n;
        this.denominador = d;
    }

    //1.3)
    //Getter e Setter
    public int getNumerador() {
        return numerador;
    }

    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public void setDenominador(int denominador) {
        this.denominador = denominador;
    }

    //1.4)
    public String toString() {
        return (numerador + "/" + denominador);
    }

    //1.5)
    public void reduce() {

    }

    public int mdc(int n, int d){
        if(n<d){
            return mdc(d,n);
        }
        else{
            return (d==0)?n:mdc(d,n%d);
        }
    }

    //1.6)
    public Fracao sum(Fracao x){
        return new Fracao(numerador*x.denominador + x.numerador*denominador , denominador*x.denominador);
    }

    public Fracao mul(Fracao x){
        return new Fracao(numerador*x.numerador , denominador*x.denominador);
    }

    public Fracao div(Fracao x){
        return new Fracao(numerador*x.denominador , denominador*x.numerador);
    }

    //1.7)
    @Override
    public int compareTo(Fracao x) {
        if((double)numerador/denominador > (double)x.numerador / x.denominador){
            return 1;
        }
        else if((double)numerador/denominador == (double)x.numerador/x.denominador){
            return 0;
        }
        else{
            return -1;
        }
    }
}