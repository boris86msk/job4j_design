package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    /**
     * для return сохраняем объект из удаляемой head
     * создаем временную ссылку для Ноды, идущей за удаляемой head
     * обнуляем поля удаляемой head
     * записываем в head "новую" head по временной ссылке
     *
     * @return value удаляемой head
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T rst = head.value;
        Node<T> newNode = head.next;
        head.next = null;
        head.value = null;
        head = newNode;
        return rst;
    }

    /**
     * алгоритм: 1) запоминаем ссылку на head.next
     * 2) переводим ее на null, т.к. это теперь хвост списка
     * 3) далее while с условием временная ссылка на след.элемент не null
     * 4) нужна еще одна временная переменная для следующ/следующ элемента
     * 5) далее идет переустановка (сдвиг) Нодов и первод одной ссылки
     * @return
     */

    public boolean revert() {
        boolean rsl = head != null && head.next != null;
        if (rsl) {
            Node<T> element = head.next;
            head.next = null;
            while (element != null) {
                Node<T> next = element.next;
                element.next = head;
                head = element;
                element = next;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
