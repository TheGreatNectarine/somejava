public class Tester {
    public static void main(String[] args) {
        Deck d = new Deck(100);
        d.fill();
        for (Card c : d.cards()) {
            c.show();
        }
        Sort.sort(d.cards());
        System.out.println("========SORTED=======");
        for (Card c : d.cards()) {
            c.show();
        }
        assert sorted(d.cards());
        System.out.println("ASSERTION PASSED DECK SORTED");
    }

    static private boolean sorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i-1]) < 0) {
                return false;
            }
        }
        return true;
    }
 }
