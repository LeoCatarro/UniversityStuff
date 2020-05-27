public interface Queue<AnyType> {
    void enqueue(AnyType o);
    AnyType front();
    AnyType dequeue();
    int size();
    boolean empty();}
