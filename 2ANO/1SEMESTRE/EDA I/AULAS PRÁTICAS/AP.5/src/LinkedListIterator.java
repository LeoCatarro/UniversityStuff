public class LinkedListIterator<AnyType> implements java.util.Iterator<AnyType> {

    private SingleNode<AnyType> current;

    private LinkedListIterator(SingleNode<AnyType> current){
    }

    public boolean hasNext(){
        return (current != null);
    }

    public AnyType next(){
        if(hasNext()){
            AnyType result = current;
        }
        else{
        }
        return result;
    }

    public void remove(){

    }
}
