package ru.job4j.collection;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * Метод должен возвращать значение и удалять его из коллекции.
     * @return
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Метод помещает значение в коллекцию в head.
     * @param value
     */
    public void push(T value) {
        linked.addFirst(value);
    }

    public boolean isEmpty() {
        return linked.iterator().hasNext();
    }
}
