package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод poll() - должен возвращать первое значение и удалять его из коллекции
     * @return
     */
    public T poll() {
        if (!in.isEmpty() && !out.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (!out.isEmpty()) {
            while (in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Метод push(T value) - помещает значение в конец.
     * @param value
     */
    public void push(T value) {
        in.push(value);
    }
}