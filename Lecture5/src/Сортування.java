import java.util.Comparator;

public class Сортування {

    @SuppressWarnings("unchecked")
    public static Comparable[] вставкою(Comparable[] input, Comparator c) {
        Comparable temp;
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (c.compare(input[j], input[j-1]) > 0) {
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        return input;
    }

    @SuppressWarnings("unchecked")
    public static Comparable[] вибором(Comparable[] arr, Comparator c) {
        for (int i = 0; i < arr.length-1; i++) {
            int index = i;
            for (int j = i+1; j < arr.length; j++)
                if (c.compare(arr[j], arr[index]) > 0)
                    index = j;
            ексщчь(c, arr[index], arr[i]);
        }
        return arr;
    }

    @SuppressWarnings("unchecked")
    private static void ексщчь(Comparator c, Comparable c1, Comparable c2) {
        if (c.compare(c1, c2) > 0) {
            Comparable temp = c1;
            c1 = c2;
            c2 = temp;
        }
    }

    public static void sort(Comparable[] a) {
        Comparable[] tmp = new Comparable[a.length];
        sort(a, tmp, 0, a.length-1);
    }


    private static void sort(Comparable[] a, Comparable[] tmp, int left, int right) {
        if (left < right) {
            int center = (left+right)/2;
            sort(a, tmp, left, center);
            sort(a, tmp, center+1, right);
            merge(a, tmp, left, center+1, right);
        }
    }

    @SuppressWarnings("unchecked")
    private static void merge(Comparable[] a, Comparable[] tmp, int left, int right, int rightEnd) {
        int leftEnd = right-1;
        int k = left;
        int num = rightEnd-left+1;

        while (left <= leftEnd && right <= rightEnd)
            if (a[left].compareTo(a[right]) <= 0)
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while (left <= leftEnd)
            tmp[k++] = a[left++];

        while (right <= rightEnd)
            tmp[k++] = a[right++];

        for (int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }


}

class Main {

    public static void main(String[] args) {
        int[] array = {2, 2, 6, 3, 8, 9, 1, 5, 2, 6, 7, 11};
        array = sort(array);
        for (int anArray : array) {
            System.out.println(anArray);
        }
    }

    static int[] merge(int[] a, int[] b) {
        int[] merged = new int[a.length+b.length];

        if (a[a.length-1] <= b[0]) {
            System.arraycopy(a, 0, merged, 0, a.length);
            System.arraycopy(b, 0, merged, a.length, b.length);
        } else {
            int k = 0;
            int j = 0;
            for (int i = 0; i < merged.length; i++) {
                if (k < a.length && j < b.length) {
                    if (a[k] <= b[j]) {
                        merged[i] = a[k++];
                    } else {
                        merged[i] = b[j++];
                    }
                } else {
                    if (k >= a.length) {
                        for (int h = k+j; h < merged.length; h++) {
                            merged[h] = b[j++];
                        }
                    }
                    if (j >= b.length) {
                        for (int h = k+j; h < merged.length; h++) {
                            merged[h] = a[k++];
                        }
                    }
                }
            }
        }
        return merged;
    }

    static int[] sort(int a[]) {
        int mid = a.length/2;
        int[] a_1 = new int[mid];
        int[] a_2 = new int[mid+a.length%2];

        System.arraycopy(a, 0, a_1, 0, a_1.length);
        System.arraycopy(a, mid, a_2, 0, a_2.length);
        if (a_1.length >= 2 && a_2.length >= 2) {
            a_1 = sort(a_1);
            a_2 = sort(a_2);
        }

        return merge(a_1, a_2);

    }
}