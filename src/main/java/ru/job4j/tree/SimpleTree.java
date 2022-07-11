package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> pred = s -> s.children.size() > 2;
        return findByPredicate(pred).isEmpty();
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = findBy(parent).isPresent()
                && findBy(child).isEmpty();
        if (rsl) {
            Node<E> needParent = findBy(parent).get();
            needParent.children.add(new Node<>(child));
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E val) {
        Predicate<Node<E>> pred = e -> e.value.equals(val);
        return findByPredicate(pred);
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
