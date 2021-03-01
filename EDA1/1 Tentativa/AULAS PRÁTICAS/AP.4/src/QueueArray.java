public class QueueArray<AnyType> implements Queue<AnyType> {
    int size;
    AnyType[] queue;

    public QueueArray(){
        queue = (AnyType[]) new Object[50];
    }
    public QueueArray(int n){
           queue = (AnyType[]) new Object[n];
    }


    public void enqueue(AnyType x){
        queue[size] = x;
        size++;
    }
    public AnyType dequeue(){
        AnyType o = queue[0];
        queue[0] = null;
        size--;
        return o;
    }
    public AnyType front(){
        return queue[0];
    }
    public boolean empty(){
        if(queue[0] == null){
            return true;
        }
        else{
            return false;
        }
    }
    public int size(){
        return queue.length;
    }
}
