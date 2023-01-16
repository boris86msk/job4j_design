package ru.job4j.kiss;

import java.util.Comparator;

public class CatCompar implements Comparator<Cat> {
    @Override
    public int compare(Cat car1, Cat car2) {
        int res;
        if (car1.id > car2.id) {
            res = 1;
        } else if (car1.id < car2.id) {
            res = -1;
        } else {
            res = 0;
        }
        return res;
    }
}
