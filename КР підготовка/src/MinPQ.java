import java.util.ArrayList;

public class MinPQ<T extends Comparable<T>> {
    private T[] heap;
    private int n;

    public MinPQ() {
        heap = (T[]) new Object[10];
        n = 0;
    }

    public void insert(T t) {
        heap[n++] = t;
        swim(n);
    }

    public T del_min() {
        T min = heap[1];
        exch(heap, 1, n--);
        sink(1);
        heap[n + 1] = null;
        return min;
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(heap[j], heap[j + 1])) j++;
            if (!less(heap[k], heap[j])) break;
            exch(heap, k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while (k > 1 && greater(heap[k / 2], heap[k])) {
            exch(heap, k, k / 2);
            k /= 2;
        }
    }


    private boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private boolean greater(T a, T b) {
        return a.compareTo(b) > 0;
    }

    private void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
