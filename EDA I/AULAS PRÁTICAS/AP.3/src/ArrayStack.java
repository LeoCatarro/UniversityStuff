import java.lang.reflect.Array;

public class ArrayStack<E> implements Stack<E> {

    E[] array;
    int size;

    private ArrayStack(){
            array = (E[]) new Object[100];
            size = 0;
    }

    public ArrayStack(int n){
        array = (E[]) new Object[n];
        size = 0;
    }

    public void push(E o) {

        array[size] = o;
        size++;
    }


    public E top() {
        if(size==0) {
            return array[0];
        }
        else{
            return array[size-1];
        }
    }


    public E pop() {
        E o = array[size-1];
        array[size-1] = null;
        size--;
        return o;
    }


    public int size() {
        return size;
    }


    public boolean empty() {
        if(array[0] == null){
            return true;
        }
        else{
            return false;
        }
    }


    public String toString(){
        String o="[";

        for (int i = 0; i < size; i++){
            o += (i<size-1)? array[i]+";": array[i];
        }
        return o +"]";
    }


    public static void main(String[] args){
        Stack<Integer> S = new ArrayStack<>(50);
        System.out.println(S.empty());
        S.push(12);
        S.push(15);
        S.push(20);
        S.push(50);
        System.out.println(S);
        S.pop();
        System.out.println(S);
        System.out.println(S.top());
        System.out.println(S);
    }
}
