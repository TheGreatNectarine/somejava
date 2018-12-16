import java.util.concurrent.ThreadLocalRandom;

public class Sort {

    public static void shuffle(Card[] a) {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < a.length; i++) {
            int r_ind = r.nextInt(i + 1);
            swap(a, i, r_ind);
        }
    }

    public static void sort(Card[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a, a[j], a[j - 1])) {
                    swap(a, j, j - 1);
                }
            }
        }
    }

    private static boolean less(Card[] arr, Card c1, Card c2) {
        return c1.compareTo(c2) < 0;
    }

    private static void swap(Card[] arr, int i, int j) {
        Card temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
