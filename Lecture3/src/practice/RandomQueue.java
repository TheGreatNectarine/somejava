package practice;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class RandomQueue<T> {

    private T[] items;
    private int size;
    private int index_taken;

    @SuppressWarnings("unchecked")
    RandomQueue() {
        items = (T[]) new Object[10];
    }

    public boolean isEmpty() {
        return size == 0;
    }

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
        int index = ThreadLocalRandom.current().nextInt(0, size);
        index_taken = index;
        T to_return = items[index];
        compress_if_necessary();
        return to_return;
    }

    public T sample() {
        if (size == 0) {
            throw new NullPointerException("No elements in 'random queue' object");
        }
        return items[ThreadLocalRandom.current().nextInt(0, size)];
    }

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

    @SuppressWarnings("unchecked")
    private void resize_if_necessary() {
        if (size > items.length-1) {
            T[] newItems = (T[]) new Object[items.length * 2];
            for (int i = 0; i < size; i++)
                newItems[i] = items[i];
            items = newItems;
        } else if (size == items.length / 4) {
            T[] newItems = (T[]) new Object[items.length / 2];
            for (int i = 0; i < size; i++)
                newItems[i] = items[i];
            items = newItems;
        }

    }

    private void compress_if_necessary() {
        for (int i = index_taken; i < size - 1; i++) {
            items[i] = items[i+1];
        }
        items[--size] = null;
    }
}

