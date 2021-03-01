public class ABPTest<E extends Comparable<? super E>> implements Iterable<E>,ABP<E> {

    NoABP<E> raiz;

    static class NoABP<E> {
        E elemento;
        NoABP<E> esq;
        NoABP<E> dir;

        NoABP(E e) {
            elemento = e;
            esq = null;
            dir = null;
        }

        NoABP(E e, NoABP<E> esq, NoABP<E> dir) {
            elemento = e;
            this.esq = esq;
            this.dir = dir;
        }
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(E x, NoABP<E> n) {
        if(n==null){
            return false;
        }
        else{
            if(n.elemento.compareTo(x)<0){
                return contains(x,n.dir);
            }
            else
            if(n.elemento.compareTo(x)>0)
                return contains(x,n.esq);
            else
                return true;
        }
    }

    public E findMin() {
        if(isEmpty()) {
            return null;
        }
        return(findMin(raiz));
    }
    private E findMin(NoABP<E> n){
        if(n.esq == null)
            return n.elemento;
        else
            return findMin(n.esq);
    }

    public E findMax() {
        return null;
    }

    public void insere(E x) {
        raiz = insere(x,raiz);
    }
    private NoABP<E> insere(E x,NoABP<E> n){
        if(n == null)
            n = new NoABP<E>(x,null,null);
        else if((n.elemento).compareTo(x)>0)
            n.esq = insere(x,n.esq);
        else if((n.elemento).compareTo(x)<0)
            n.dir = insere(x,n.dir);
        return n;
    }

    public void remove(E x) {
        raiz = remove(x,raiz);
    }
    private NoABP<E> remove(E x, NoABP<E> n){
        if(n == null){
            return n;
        }
        if(n.elemento.compareTo(x)<0)
            n.dir = remove(x,n.dir);
        else if(n.elemento.compareTo(x)>0)
            n.esq = remove(x,n.esq);
        else if(n.esq != null && n.dir != null){
            E min = findMin(n.dir);
            n.elemento = min;
            n.dir = remove(min,n.dir);
        }
        else if(n.esq == null)
            n = n.dir;
        else
            n = n.esq;
        return n;
    }

    public void printEmOrdem() {

    }

    public void printPosOrdem() {

    }

    public void printPreOrdem() {

    }
}