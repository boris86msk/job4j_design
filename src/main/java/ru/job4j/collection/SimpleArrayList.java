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
        Objects.checkIndex(index, size);
        T res = container[index];
        modCount++;
        container[index] = newValue;
        return res;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T res = container[index];
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
        return res;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
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

    private void arrayUpp() {
        if (container.length == 0) {
            container = (T[]) new Object[10];
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }
}
