import java.lang.Math;

public abstract class HashTable<T> {

    int dim, currentSize;
    protected Element<T> [] arr;

    public int hash(T x) {

        return x.hashCode() % dim;
    }

    public int nextPrime(int n) {

        if(!isPrime(n))
            n = nextPrime(++n);
        return n;
    }
    boolean isPrime(int M) {
        for(int i = 2; i <= Math.sqrt(M); i++)
            if(M % i == 0) return false;
        return true;
    }


    public int nElements() {

        return currentSize;
    }

    public float loadFactor() {

        return nElements() / dim;
    }

    public abstract int searchPos(T x);

    public void allocateTable(int dim){

        if(isPrime(dim))
            arr = new Element[dim];
        else
            arr = new Element[nextPrime(dim)];

        this.dim = arr.length;
    }

    /*
    Colocar a tabela vazia
    */
    public void empty() {

        currentSize = 0;

        for(int i = 0; i < arr.length; i++)  {

            arr[i] = null;
        }

    }


    public T search(T x) {

        int currentPos = searchPos(x);

        if (arr[currentPos] != null)
            return arr[currentPos].element;

        else
            return null;
    }

    public void remove(T x) {

        int currentPos = searchPos(x);

        if (x.equals(search(x)))

            arr[currentPos] = null;

        currentSize--;
    }

    /*
    Inserir na Tabela x
    Se ja existir x, nao fazer nada
    */
    public void insert(T x) {

        int currentPos = searchPos(x);


        if (x.equals(search(x))) //se o elemento ja existe sai da funcao
            return;

        arr[currentPos] = new Element<>(x);

        currentSize++;

        if (currentSize > arr.length / 2)//fator de carga > 0.5 reHash
            reHash();
    }

    public void reHash() {

        Element<T> [] oldArr = arr;

        allocateTable(2 * arr.length);
        currentSize = 0;

        for (int i = 0; i < oldArr.length; i++) {
            if (oldArr[i] != null)
                insert(oldArr[i].element);
        }
    }

    public String toString () {

        String s = "";

        for (int i = 0; i < arr.length; i++) {

            s += arr[i] + "\n";
        }

        return s;
    }
}