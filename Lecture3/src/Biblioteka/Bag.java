package Biblioteka;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<T> implements Iterable<T> {

    private Node first;
    private int size;

    public Bag() {

        first = null;
        size = 0;

    }

    public void add(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    public int get_size() {
        return size;
    }

    public boolean is_empty() {
        return first == null;
    }

    private class Node {
        T item;
        Node next;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException("No elements to iterate on");
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }


}
