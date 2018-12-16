import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Third {

    static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    static <T extends Comparable<? super T>> boolean isSorted(Iterable<T> l) {
        Iterator<T> i = l.iterator();
        if (!i.hasNext())
            return true;
        Comparable cur = i.next();
        for (; i.hasNext(); ) {
            Comparable next = i.next();
            if (cur.compareTo(next) > 0)
                return false;
            cur = next;
        }
        return true;
    }

    public static void main(String[] args) {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        Integer[]         a = new Integer[10];
        for (int i = 0; i < 10; i++) {
            a[i] = r.nextInt(0, 10);
        }
        System.out.println(isSorted(a));
        Arrays.sort(a);
        System.out.println(isSorted(a));

        LinkedList<Integer> l = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            l.add(r.nextInt(10));
        }
        System.out.println(isSorted(l));
        Collections.sort(l);
        System.out.println(isSorted(l));
    }


}
