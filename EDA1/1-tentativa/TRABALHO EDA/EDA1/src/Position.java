public class Position<T> {

    private T element;
    private int row;
    private int column;

    Position () {
        this(null,0,0);
    }
    Position (T element) {
        this.element = element;
    }

    Position (T element, int row, int column) {

        this.element = element;
        this.row = row;
        this.column = column;
    }

    public String toString() {

        return element + ":(" + Integer.toString(row) + "," + column + ")" + "   ";
    }

    public boolean equals (Position<T> x) {

        if (x.element.equals(element) && x.row == row && x.column == column )
            return true;

        return false;
    }
}


