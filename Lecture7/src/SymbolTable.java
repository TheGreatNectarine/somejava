import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.function.Predicate;

public class SymbolTable<K extends Comparable<K>, V> implements Set<K, V> {

    private Node[] map;
    private int    size;

    @SuppressWarnings("unchecked")
    SymbolTable() throws ClassNotFoundException {
        Class<?> c = Class.forName("SymbolTable$Node");
        map = (Node[]) Array.newInstance(c, 1);
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    public boolean has(K key) {
        return get(key) != null;
    }

    @Override
    public void put(K key, V val) {
        if (key != null) {
            int rank = rank(key);
            if (empty()) {
                Node first = new Node(key, val);
                map[size++] = first;
                return;
            }
            if (rank < size && map[rank].key.compareTo(key) == 0) {
                map[rank].val = val;
            } else {
                if (size == map.length)
                    resize(2 * map.length);
                System.arraycopy(map, rank, map, rank + 1, size - rank);
                map[rank] = new Node(key, val);
                size++;
            }
        }
    }

    @Override
    public V get(K key) {
        if (empty()) return null;
        int i = rank(key);
        if (i < size && map[i].key.compareTo(key) == 0) return map[i].val;
        else return null;
    }

    //TODO
    @Override
    public void delete(K key) {
        if (this.has(key)) {
            if (key != null && !empty()) {
                map = shift(map, rank(key));
                size--;
                if (size <= map.length / 4)
                    resize(map.length / 2);
            }
        }
    }

    @Override
    public Iterable<K> keys() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<K>, Iterable<K> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public K next() {
            return map[i++].key;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Iterator<K> iterator() {
            return this;
        }
    }

    @Override
    public K min() {
        return map[0].key;
    }

    @Override
    public K max() {
        return map[size - 1].key;
    }

    public void delete_min() {
        delete(map[0].key);
    }

    public void delete_max() {
        delete(map[size - 1].key);
    }

    public K select(int k) {
        return map[k].key;
    }

    @Override
    public K floor(K key) {
        Node val = map[0];
        for (int i = 1; i < size; i++) {
            if (map[i].key.compareTo(val.key) > 0 && map[i].key.compareTo(key) < 0)
                val = map[i];
        }
        return val.key;
    }

    @Override
    public K ceiling(K key) {
        Node val = map[size - 1];
        for (int i = size - 2; i > 0; i--) {
            if (map[i].key.compareTo(val.key) < 0 && map[i].key.compareTo(key) > 0)
                val = map[i];
        }
        return val.key;
    }

    @Override
    public int rank(K key) {
        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(map[mid].key);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    @Override
    public int size() {
        return size;
    }

    public int size(K lo, K hi) {
        return rank(hi) - rank(lo);
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        Node[] copy = (Node[]) Array.newInstance(Node.class, capacity);
        System.arraycopy(map, 0, copy, 0, Math.min(capacity, map.length));
        map = copy;
    }

    @SuppressWarnings("unchecked")
    private Node[] shift(Node[] arr, int ind) {
        Node[] new_arr = (Node[]) Array.newInstance(Node.class, arr.length - 1);
        for (int i = 0; i < ind; i++) {
            new_arr[i] = arr[i];
        }
        for (int i = ind; i < arr.length - 1; i++) {
            new_arr[i] = arr[i + 1];
        }
        return new_arr;
    }

    private Node val(Node[] arr, Predicate<Node> p) {
        Node valid = null;
        for (Node n : arr) {
            if (p.test(n)) {
                valid = n;
            }
        }
        return valid;
    }

    class Node {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
}
