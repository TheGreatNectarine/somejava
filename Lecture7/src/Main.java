import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
//        ThreadLocalRandom                 r = ThreadLocalRandom.current();
//        BinarySearchSet<Integer, Integer> t = new BinarySearchSet<>();
//        t.put(10,10);
//        t.put(7,7);
//        t.put(5,5);
//        t.put(3,3);
//        t.put(6,6);
//        t.put(9,9);
//        t.put(8,8);
//        t.put(14,14);
//        t.put(12,12);
//        t.put(13,13);
//        t.put(11,11);
//        t.put(20,20);
//        System.out.println(t.size());
//        System.out.println(t.rank(10));
//        System.out.println(t.ceiling(1000));
//        for (Integer i : t.keys()) {
//            System.out.println(i);
//        }
        SymbolTable<Integer, Integer> t = new SymbolTable<>();
        t.put(10, 10);
        t.put(7, 7);
        t.put(5, 5);
        t.put(3, 3);
        t.put(6, 6);
        t.put(9, 9);
        t.put(8, 8);
        t.put(14, 14);
        t.put(12, 12);
        t.put(13, 13);
        t.put(11, 11);
        t.put(20, 20);
        t.put(6, 6);
//        t.get(20);
//        t.put(20, 20);
//        t.delete(4);
//        t.delete(4);
//        t.delete(4);
//        t.delete(5);

        print(t.keys());

//        System.out.println(t.size(0, 20));
//        t.delete(10);
//        for (Integer i : t.keys()) {
//            System.out.println(i);
//        }
    }

    private static void print(Iterable arr) {
        for (Object i : arr) {
            System.out.println(i);
        }
    }

}
