public class  LinkedListIterator<E> implements java.util.Iterator<E> {

    SingleNode<E> NodeAtual;
    SingleNode<E> NodePrev;

    public LinkedListIterator(SingleNode<E> c) {

        NodeAtual=c;

    }

    public boolean hasNext() {

        if (NodeAtual.getNext()==null){

            return false;

        }else{

            return true;

        }

    }

    public E next() {

        if (this.hasNext()){

            NodePrev=NodeAtual;
            NodeAtual=NodeAtual.getNext();
        }

        return NodeAtual.element();

    }


    public void remove() {

        SingleNode<E> save = NodePrev;
        this.next();
        NodePrev=save;
        NodePrev.setNext(NodeAtual);
    }

}