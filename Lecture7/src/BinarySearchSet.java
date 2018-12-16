import edu.princeton.cs.algs4.Queue;

public class BinarySearchSet<K extends Comparable<K>, V> implements Set<K, V> {

    private Node root;

    @Override
    public boolean empty() {
        return root == null;
    }

    @Override
    public void put(K key, V val) {
        if (val == null)
            delete(key);
        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) return new Node(key, val);
        int compare = key.compareTo(x.key);
        if (compare < 0)
            x.left = put(x.left, key, val);
        else if (compare > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.size = 1 + size(x.right) + size(x.left);
        return x;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    //TODO
    @Override
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = delete_min(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node delete_min(Node x) {
        if (x.left == null) return x.right;
        x.left = delete_min(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> q = new Queue<>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, Queue<K> q) {
        if (x == null) return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }

    @Override
    public K min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    @Override
    public K max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    @Override
    public K floor(K key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node floor(Node x, K key) {
        if (x == null)
            return null;
        int compare = key.compareTo(x.key);
        if (compare == 0)
            return x;
        if (compare < 0)
            return floor(x.left, key);
        Node r = floor(x.right, key);
        if (r != null)
            return r;
        return x;
    }

    @Override
    public K ceiling(K key) {
        Node x = ceiling(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node ceiling(Node x, K key) {
        if (x == null)
            return null;
        int compare = key.compareTo(x.key);
        if (compare == 0)
            return x;
        if (compare > 0)
            return ceiling(x.right, key);
        Node l = ceiling(x.left, key);
        if (l != null)
            return l;
        return x;
    }

    @Override
    public int rank(K key) {
        return rank(key, root);
    }

    private int rank(K key, Node x) {
        if (x == null) return 0;
        int compare = key.compareTo(x.key);
        if (compare < 0)
            return rank(key, x.left);
        else if (compare > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.size;
    }

    class Node {
        K    key;
        V    val;
        Node left;
        Node right;
        int size = 1;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

}
