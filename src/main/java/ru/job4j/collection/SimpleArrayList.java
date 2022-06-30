package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            arrayUpp();
        }
        modCount++;
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        T res = null;
        if (Objects.checkIndex(index, container.length) == index) {
            res = container[index];
            modCount++;
            container[index] = newValue;
        }
        return res;
    }

    @Override
    public T remove(int index) {
        T res = null;
        if (Objects.checkIndex(index, container.length) == index) {
            res = container[index];
            System.arraycopy(
                    container,
                    index + 1,
                    container,
                    index,
                    container.length - index - 1
            );
            container[container.length - 1] = null;
            modCount++;
            size--;
        }

        return res;
    }

    @Override
    public T get(int index) {
        T res = null;
        if (Objects.checkIndex(index, container.length) == index) {
            res = container[index];
        }
        return res;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int modCountFirst = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCountFirst != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[index++];
            }

        };
    }

    public void arrayUpp() {
        container = Arrays.copyOf(container, container.length * 2);
    }
}
