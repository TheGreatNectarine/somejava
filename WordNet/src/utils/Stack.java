package utils;

import java.util.Iterator;

public class Stack<T> implements IStack<T> {

    private T[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public Stack() {
        items = (T[]) new Object[10];
    }

    public void push(T item) throws IllegalArgumentException {
        if (item == null)
            throw new IllegalArgumentException("An attempt was made to push 'Null' object into 'stack'");
        resize_if_necessary();
        items[size++] = item;
    }

    public T pop() {
        if (size == 0) {
            throw new NullPointerException("No elements in 'stack' object");
        }
        T to_return = items[--size];
        items[size] = null;
        return to_return;
    }

    public T peek() {
        if (size == 0) {
            throw new NullPointerException("No elements in 'stack' object");
        }
        return items[size-1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean is_empty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize_if_necessary() {
        if (size > items.length-1) {
            T[] newItems = (T[]) new Object[items.length*2];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        } else if (size == items.length/4) {
            T[] newItems = (T[]) new Object[items.length/2];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            int current;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public T next() {
                return items[current++];
            }
        };
    }
}
