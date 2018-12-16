package practice;

public interface IQueue<T> extends Iterable<T> {

    boolean isEmpty();
    int size();
    void enqueue(T item);
    T dequeue();

}
