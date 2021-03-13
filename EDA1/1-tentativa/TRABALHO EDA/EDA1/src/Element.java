public class Element<T> {

    T element;

    Element() {

        this(null);
    }


    Element(T x) {

        element = x;
    }

    public String toString() {

        String s = "";

        return s + element;
    }
}
