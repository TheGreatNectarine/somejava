public interface Set<K extends Comparable<K>, V> {

    boolean empty();

    void put(K key, V val);

    V get(K key);

    void delete(K key);

    Iterable<K> keys();

    K min();

    K max();

    K floor(K key);

    K ceiling(K key);

    int rank(K key);

    int size();
}
