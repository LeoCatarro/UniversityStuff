public class SingleNode<AnyType> {

    private AnyType data;
    private SingleNode<AnyType> next;

//CONSTRUTORES
    public SingleNode(AnyType data){
        this.data = data;
        this.next = null;
    }

    public SingleNode(){
        this(null,null);
    }

    public SingleNode(AnyType x, SingleNode<AnyType> n){
        this.data = data;
        this.next = next;
    }

//MÉTODOS
    public AnyType getElement() throws InvalidNodeException{
        return data;
    }

    public SingleNode<AnyType> getNext(){
        return next;
    }

    public void setElement(AnyType x){
        this.data = data;
    }

    public void setNext(SingleNode<AnyType> n){
        this.next = next;
    }
}
