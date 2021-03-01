import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QuadHashTable<T> extends HashTable<T> {

    private static final int DEFAULT_SIZE = 10;

    QuadHashTable() {
        this(DEFAULT_SIZE);
    }

    QuadHashTable(int dim) {

        this.dim = dim;

        allocateTable(dim);
        empty();
    }

    public int searchPos(T x) {

        int offset = 1;
        int currentPos = hash(x);
        int aux = currentPos;

        if (currentPos < 0 && aux < 0) {

            currentPos *= -1;
            aux *= -1;
        }

        while (arr[currentPos] != null && !arr[currentPos].element.equals(x)) {

            currentPos = aux + (int) Math.pow(offset, 2);
            offset++;

            if (currentPos >= arr.length)
                currentPos -= arr.length;
        }

        return currentPos;
    }
}