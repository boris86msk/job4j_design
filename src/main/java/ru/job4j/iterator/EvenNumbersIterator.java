package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int i = 0;
    while (i == 0 && index < data.length) {
        if (data[index] % 2 == 0) {
            i++;
        } else {
            index++;
        }
    }
    return i > 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (data[index] % 2 > 0) {
            index++;
        }
    return data[index++];
    }
}
