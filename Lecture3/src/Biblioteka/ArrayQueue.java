package Biblioteka;

import practice.IQueue;

import java.util.Iterator;

public class ArrayQueue<T> implements IQueue<T> {

    private T[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int size) {
        items = (T[]) new Object[size];
    }

    public ArrayQueue() {
        this(10);
    }
    @SuppressWarnings("unchecked")
    private void resize_if_necessary() {
        if (size > items.length - 1) {
            T[] newItems = (T[]) new Object[items.length * 2];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        } else if (size == items.length / 4) {
            T[] newItems = (T[]) new Object[items.length / 2];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
    }

    private void compress() {
        for (int i = 0; i < size - 1; i++) {
            items[i] = items[i+1];
        }
        items[--size] = null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public void enqueue(T item) throws IllegalArgumentException {
        if (item == null)
            throw new IllegalArgumentException("An attempt was made to enqueue 'Null' object");
        resize_if_necessary();
        items[size++] = item;
    }

    public T dequeue() throws IllegalArgumentException{
        if (size == 0) {
            throw new NullPointerException("No elements in 'random queue' object");
        }
        T to_return = items[0];
        compress();
        return to_return;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return items[index++];
            }
        };
    }
}