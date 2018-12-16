import java.util.concurrent.ThreadLocalRandom;

class Percolation {

    static final int iterations = 1000;
    static final int field_dim = 100;

    private static final boolean OPEN = true;

    private boolean[] field;
    private int size;
    private int open_count;
    private WeightedQuickUnion qu;

    Percolation(int size) {
        this.size = size;
        field = new boolean[size * size + 2];
        field[0] = OPEN;
        field[size * size + 1] = OPEN;
        qu = new WeightedQuickUnion(field.length);
    }

    private boolean opened(int p) {
        return field[p] == OPEN;
    }

    private boolean closed(int p) {
        return !opened(p);
    }

    private void open(int p) {
        if (closed(p)) {
            field[p] = OPEN;
            open_count++;
        }
    }

    private void connect_to_near(Sides side, int p) {
        switch (side) {
            case UP:
                if (opened(p) && opened(p - size))
                    qu.union(p, p - size);
                break;
            case DOWN:
                if (opened(p) && opened(p + size))
                    qu.union(p, p + size);
                break;
            case LEFT:
                if (opened(p) && opened(p - 1))
                    qu.union(p, p - 1);
                break;
            case RIGHT:
                if (opened(p) && opened(p + 1))
                    qu.union(p, p + 1);
                break;
        }
    }

    private void connect(int p) {
        if (p >= 1 && p <= size) {
            qu.union(0, p);
        } else if (p >= size * size - size && p <= size * size) {
            qu.union(p, size * size + 1);
        }
        if (p % size != 0) {
            connect_to_near(Sides.RIGHT, p);
        }
        if (p % size != 1) {
            connect_to_near(Sides.LEFT, p);
        }
        if (p > size) {
            connect_to_near(Sides.UP, p);
        }
        if (p < size * size - size + 1) {
            connect_to_near(Sides.DOWN, p);
        }
    }

    void open_and_connect_randomly() {
        int p = ThreadLocalRandom.current().nextInt(1, size * size + 1);
        open(p);
        connect(p);
    }

    boolean percolates() {
        return qu.connected(0, size * size + 1);
    }

    int get_open_count() {
        return open_count;
    }

    public enum Sides {
        UP, RIGHT, DOWN, LEFT
    }

}


