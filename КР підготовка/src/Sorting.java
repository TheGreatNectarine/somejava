import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Sorting {

    public static void main(String[] args) {
        ThreadLocalRandom r   = ThreadLocalRandom.current();
        Integer[]         arr = new Integer[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int rand = r.nextInt(i + 1);
            exch(arr, i, rand);
        }
        bubble(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void bubble(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr[j], arr[j - 1])) {
                    exch(arr, j, j - 1);
                }
            }
        }
    }

    static void selection(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = 0; j < arr.length; j++) {
                if (less(arr[j], arr[min])) {
                    min = j;
                }
            }
            exch(arr, min, i);
        }
    }

    static void insertion(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr[j], arr[j - 1])) {
                    exch(arr, j, j - 1);
                } else
                    break;
            }
        }
    }

    static void shell(Comparable[] arr) {
        int len = arr.length;
        int h   = 1;
        while ((h = h * 3 + 1) < len) ;
        while ((h /= 3) >= 1) {
            for (int i = 0; i < len; i++) {
                for (int j = i; j > 0; j -= h) {
                    if (less(arr[j], arr[j - h])) {
                        exch(arr, j, j - h);
                    } else break;
                }
            }
        }
    }

    static void mergesort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi > lo) {
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);
            sort(a, aux, mid + 1, hi);
            merge(a, aux, lo, mid, hi);
        }
    }

    static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert sorted(a, lo, mid);
        assert sorted(a, mid + 1, hi);
        System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        int i = lo, j = mid + 1;
        for (int k = lo; k < mid; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
        assert sorted(a, 0, a.length - 1);
    }

    static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) break;
            }
            while (less(a[lo], a[--j])) {
                if (j == lo) break;
            }
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    static void qsort(Comparable[] a, int lo, int hi) {
        if (hi > lo) {
            int j = partition(a, lo, hi);
            qsort(a, lo, j - 1);
            qsort(a, j + 1, hi);
        }
    }

    static void quicksort(Comparable[] a) {
        qsort(a, 0, a.length - 1);
    }


    static private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    static private void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static boolean sorted(Comparable[] arr, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            if (!less(arr[i - 1], arr[i])) {
                return false;
            }
        }
        return true;
    }
}