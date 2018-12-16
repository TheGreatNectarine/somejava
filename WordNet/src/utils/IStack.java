package utils;

public interface IStack<T> extends Iterable<T>{

    void push(T item);
    T pop();
    T peek();
    int size();
    boolean is_empty();

}
