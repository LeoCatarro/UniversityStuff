public class LinkedList<T> implements Iterable<T> {

    int size;
    SingleNode<T> Header;
    LinkedListIterator<T> list;

    public LinkedList() {

        size = 0;
        Header = new SingleNode<T>();
        list = new LinkedListIterator<T>(Header);

    }

    public java.util.Iterator<T> iterator() {

        return list;

    }

    public int size() {

        return size;

    }

    public boolean isEmpty() {

        return size == 0;
    }

    public SingleNode<T> header() {

        return Header;
    }

    public void clear() {

        Header = new SingleNode<T>();
        list = new LinkedListIterator<T>(Header);
    }

    public void add (T x) {

        SingleNode<T> node;
        SingleNode<T> newNode = new SingleNode<>();

        node = Header;

        while (node.getNext() != null) {

            node = node.getNext();
        }

        node.setNext(newNode);

        newNode.setElement(x);

        size++;
    }


    public void add(int i, T x) {

        SingleNode<T> Atual;

        size++;
        Atual = list.NodeAtual;

        for (int z = 0; z < i; z++) {
            list.next();
            Atual = list.NodeAtual;
        }
        SingleNode<T> newNode = new SingleNode<T>(x, Atual.getNext());
        Atual.setNext(newNode);
        list.NodePrev = Atual;
        list.NodeAtual = newNode;

        list = new LinkedListIterator<T>(this.header());

    }

    public void remove(int ind) {

        SingleNode<T> Atual = new SingleNode<T>();

        size--;
        for (int z = 0; z <= ind; z++) {
            list.next();
            Atual = list.NodeAtual;
        }
        list.remove();
        list = new LinkedListIterator<T>(this.header());

    }
    @Override
    public String toString() {

        String s = "[ ";
        while (list.hasNext()) {

            s = s + list.next();

        }

        s += "]";

        list = new LinkedListIterator<T>(this.header());

        return s +"\n";
    }


    public static void main (String args []) {


        LinkedList<Integer> list = new LinkedList<>();

        list.add(45);

        list.add(21);
        list.add(3);
        list.add(1);

        System.out.println(list);
    }
}