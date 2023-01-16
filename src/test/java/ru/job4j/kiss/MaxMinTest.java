package ru.job4j.kiss;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {
    MaxMin maxMin = new MaxMin();
    List<Cat> cats = new ArrayList<>();
    CatCompar catCompar = new CatCompar();

    @Test
    void wenMaxNine() {
        cats.add(new Cat(0));
        cats.add(new Cat(9));
        cats.add(new Cat(5));
        cats.add(new Cat(3));
        cats.add(new Cat(-1));
        int max = maxMin.max(cats, catCompar).id;
        assertThat(max).isEqualTo(9);
    }

    @Test
    void wenMinMinusOne() {
        cats.add(new Cat(10));
        cats.add(new Cat(-1));
        cats.add(new Cat(4));
        cats.add(new Cat(0));
        cats.add(new Cat(7));
        int min = maxMin.min(cats, catCompar).id;
        assertThat(min).isEqualTo(-1);
    }

}