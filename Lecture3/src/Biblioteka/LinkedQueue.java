package Biblioteka;

import practice.IQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements IQueue<T>{

    Node first;
    Node last;
    int size;

    private class Node {
        Node next;
        T item;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(T item) {
        null_check(item);

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

        size++;

    }

    @Override
    public T dequeue() {
        no_element_check();

        T to_remove = first.item;
        first = first.next;
        if (isEmpty()) {
            last = first;
        }
        size--;
        return to_remove;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node current;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T to_return = current.item;
                current = current.next;
                return to_return;
            }
        };
    }

    private void null_check(T item) throws NullPointerException {
        if (item == null)
            throw new NullPointerException("An attempt was mate to add 'Null' object");
    }

    private void no_element_check() {
        if (isEmpty())
            throw new NoSuchElementException("An attempt was made to remove non-existing element");
    }
}
