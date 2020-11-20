public interface iHashTable<T> {

    public int nElements();
    public float loadFactor();
    public abstract int searchPos(T x);
    public void allocateTable(int dim);
    public void empty();
    public T search(T x);
    public void remove(T x);
    public void insert(T x);
    public void reHash();
    public void print();
}
