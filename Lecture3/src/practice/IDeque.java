package practice;

public interface IDeque<T> extends Iterable<T> {
    boolean isEmpty();
    // чи порожня?
    int size();
    // кількість елементів в деці
    void addFirst(T item);
    // додаємо на початок
    void addLast(T item);
    // додаємо в кінець
    T removeFirst();
    // видаляємо і повертаємо перший
    T removeLast();
    // видаляємо і повертаємо останній
}
