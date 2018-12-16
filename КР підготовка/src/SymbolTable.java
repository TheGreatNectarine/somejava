import java.lang.reflect.Array;
import java.util.Iterator;

public class SymbolTable<K extends Comparable<K>, V> {
    private Node[] map;
    private int    size;

    public void put(K k, V v) {
        if (k != null) {
            int rank = rank(k);
            if (empty()) {
                map[size++] = new Node(k, v);
                return;
            }
            if (rank < size && map[rank].k.compareTo(k) == 0) {
                map[rank].v = v;
            } else {
                if (size == map.length) {
                    resize(size * 2);
                }
                System.arraycopy(map, rank, map, rank + 1, size - rank);
                map[rank] = new Node(k, v);
                size++;
            }
        }
    }

    public V get(K k) {
        if (empty()) return null;
        int rank = rank(k);
        if (rank < size && map[rank].k.compareTo(k) == 0) return map[rank].v;
        else return null;
    }

    public void delete(K key) {
        if (this.has(key)) {
            if (key != null && !empty()) {
                map = divide(map, rank(key));
                size--;
                if (size <= map.length / 4)
                    resize(map.length / 2);
            }
        }
    }

    public K select(int k) {
        return map[k].k;
    }

    private int rank(K k) {
        int lo = 0, hi = map.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = k.compareTo(map[mid].k);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else return mid;
        }
        return lo;
    }

    public boolean has(K k) {
        return get(k) != null;
    }

    public boolean empty() {
        return size == 0;
    }

    public Iterable<K> keys() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterable<K>, Iterator<K> {

        private int i = 0;

        @Override
        public Iterator<K> iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public K next() {
            return map[i++].k;
        }

        @Override
        public void remove() {
            delete(map[i].k);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        if (capacity != map.length) {
            Node[] copy = (Node[]) Array.newInstance(Node.class, capacity);
            System.arraycopy(map, 0, copy, 0, Math.min(capacity, map.length));
            map = copy;
        }
    }

    @SuppressWarnings("unchecked")
    private Node[] divide(Node[] arr, int ind) {
        Node[] new_arr = (Node[]) Array.newInstance(Node.class, arr.length - 1);
        for (int i = 0; i < ind; i++) {
            new_arr[i] = arr[i];
        }
        for (int i = ind; i < arr.length - 1; i++) {
            new_arr[i] = arr[i + 1];
        }
        return new_arr;
    }

    class Node {
        K k;
        V v;

        Node(K key, V val) {
            this.k = key;
            this.v = val;
        }
    }
}
