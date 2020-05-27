public interface Stack<E> {
    void push(E o);
    E top();
    E pop();
    int size();
    boolean empty();
}
