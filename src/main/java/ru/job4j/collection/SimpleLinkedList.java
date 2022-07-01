package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;

    private int modCount = 0;

    private Node<E> first;

    private Node<E> last;

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(value, null);
        if (first == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int modCountEntrance = modCount;
            Node<E> node = first;

            @Override
            public boolean hasNext() {
                if (modCountEntrance != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rst = node.item;
                node = node.next;
                return rst;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
