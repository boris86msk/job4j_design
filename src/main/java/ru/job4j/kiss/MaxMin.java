package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> pred = e -> e < 0;
        return search(value, comparator, pred);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> pred = e -> e > 0;
        return search(value, comparator, pred);
    }

    private <T> T search(List<T> value, Comparator<T> comparator, Predicate pred) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        T res = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            int numb = comparator.compare(res, value.get(i));
            if (pred.test(numb)) {
                res = value.get(i);
            }
        }
        return res;
    }
}
