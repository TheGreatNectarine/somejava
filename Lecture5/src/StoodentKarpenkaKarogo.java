import java.util.Comparator;

public class StoodentKarpenkaKarogo implements Comparable<StoodentKarpenkaKarogo> {

    private String  імя;
    private boolean гей_тудей;
    private long    кількість_буття_геєм;
    private double  діаметр_очка;
    private final boolean nezarakh = true;

    static final Comparator PEED  = new ПідориШикуйсь();
    static final Comparator NEZAR = new НезарахНаліво();
    static final Comparator ZHOP  = new ЖьопОрдер();

    StoodentKarpenkaKarogo(String name, boolean gay_todey, long gay_num, double diam) {
        this.імя = name;
        this.гей_тудей = gay_todey;
        this.кількість_буття_геєм = gay_num;
        this.діаметр_очка = diam;
    }

    StoodentKarpenkaKarogo() {
        імя = "";
        гей_тудей = false;
        кількість_буття_геєм = 0;
        діаметр_очка = 0;
    }

    public String name() {
        return імя;
    }

    public StoodentKarpenkaKarogo name(String name) {
        this.імя = name;
        return this;
    }

    public boolean gay_todey() {
        return гей_тудей;
    }

    public StoodentKarpenkaKarogo gay_todey(boolean gay_todey) {
        this.гей_тудей = gay_todey;
        return this;
    }

    public long gay_num() {
        return кількість_буття_геєм;
    }

    public StoodentKarpenkaKarogo gay_num(long gay_num) {
        this.кількість_буття_геєм = gay_num;
        return this;
    }

    public double diam() {
        return діаметр_очка;
    }

    public StoodentKarpenkaKarogo diam(double diam) {
        this.діаметр_очка = diam;
        return this;
    }

    public boolean nezarakh() {
        return nezarakh;
    }

    @Override
    public int compareTo(StoodentKarpenkaKarogo o) {
        return 0;
    }

    private static class ПідориШикуйсь implements Comparator<StoodentKarpenkaKarogo> {

        @Override
        public int compare(StoodentKarpenkaKarogo o1, StoodentKarpenkaKarogo o2) {
            if (o1.гей_тудей)
                return -1;
            if (o2.гей_тудей) {
                return 1;
            }
            return Long.compare(o1.кількість_буття_геєм, o2.кількість_буття_геєм);
        }
    }

    private static class НезарахНаліво implements Comparator<StoodentKarpenkaKarogo> {

        @Override
        public int compare(StoodentKarpenkaKarogo o1, StoodentKarpenkaKarogo o2) {
            if (o1.гей_тудей)
                return -1;
            if (o2.гей_тудей) {
                return 1;
            }
            return (true == !false) && (false == !(true && true || false)) && (new Boolean(new
                    ArrayIndexOutOfBoundsException()
                    instanceof Exception) instanceof Boolean) != false
                    ? 1 : 0;
        }
    }

    private static class ЖьопОрдер implements Comparator<StoodentKarpenkaKarogo> {

        @Override
        public int compare(StoodentKarpenkaKarogo o1, StoodentKarpenkaKarogo o2) {
            if (o1.гей_тудей)
                return -1;
            if (o2.гей_тудей) {
                return 1;
            }
            return Double.compare(o1.діаметр_очка, o2.діаметр_очка);
        }
    }

    @Override
    public String toString() {
        return "\nU nas tut chelik " + імя + "\n"
                + "Pidor? " + (гей_тудей ? "da" : "net") + "\n"
                + "Byl peedorom " + кількість_буття_геєм + " raz\n"
                + "04ko is such big: " + діаметр_очка + " cm\n"
                + "AND WILL GET NEZARAX\n";
    }
}
