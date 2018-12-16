package Biblioteka;

import practice.IStack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements IStack<T> {

    private Node top;
    private int size;

    @Override
    public void push(T item) {
        if (item == null)
            throw new NullPointerException("An attempt was mate to add 'Null' object");
        Node old_top = top;
        if (this.is_empty()) {
            top = new Node();
            top.item = item;
        } else {
            top = new Node();
            top.item = item;
            top.next = old_top;
        }
        size++;
    }

    @Override
    public T pop() {
        if (is_empty())
            throw new NoSuchElementException("An attempt was made to remove non-existing element");
        T to_return = top.item;
        top = top.next;
        return to_return;
    }

    @Override
    public T peek() {
        return top.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean is_empty() {
        return top == null;
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

    private class Node {
        T item;
        Node next;
    }

}
