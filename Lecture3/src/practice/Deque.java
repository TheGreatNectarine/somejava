package practice;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements IDeque<T> {

    private Node first;
    private Node last;

    private int count;

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void addFirst(T item) throws NullPointerException {
        null_check(item);

        Node oldFirst = first;
        Node newFirst = new Node();
        newFirst.item = item;

        if (first == null) {
            first = newFirst;
            last = newFirst;
        } else {
            first = newFirst;
            newFirst.next = oldFirst;
            oldFirst.prev = first;
        }

        count++;
    }

    @Override
    public void addLast(T item) {
        null_check(item);

        Node oldLast = last;
        Node newLast = new Node();
        newLast.item = item;

        if (last == null) {
            first = newLast;
            last = newLast;
        } else {
            last = newLast;
            newLast.prev = oldLast;
            oldLast.next = last;
        }

        count++;
    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (first == null)
            throw new NoSuchElementException("An attempt was made to remove non-existing element");
        Node to_remove = first;
        first = first.next;
        if (first == null) {
            last = null;
        } else first.prev = null;
        count--;
        return to_remove.item;
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if (last == null)
            throw new NoSuchElementException("An attempt was made to remove non-existing element ");
        Node to_remove = last;
        last = last.prev;
        if (last == null) {
            first = null;
        } else last.next = null;
        count--;
        return to_remove.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class Node {
        T item;
        Node next;
        Node prev;
    }

    private class DequeIterator implements Iterator<T> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    String toSting() {
        StringBuilder sb = new StringBuilder().append("[");
        Node n = first;
        while (true) {
            if (n == null) {
                sb.append("]");
                break;
            }
            sb.append(n.item).append(", ");
            n = n.next;
        }
        return sb.toString();
    }

    private void null_check(T item) throws NullPointerException {
        if (item == null)
            throw new NullPointerException("An attempt was mate to add 'Null' object");
    }
}
