package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rst = true;
        while (rst) {
            if (data.length > 1) {
                if (data[row].length == 0 && row != data.length - 1) {
                    row++;
                } else if (row == data.length - 1) {
                    if (data[row].length == 0) {
                        rst = false;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else if (data[row].length == 0) {
                rst = false;
            } else {
                break;
            }
        }
        return rst;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column >= data[row].length) {
            row++;
            column = 0;
            while (data[row].length == 0) {
                row++;
            }
        }
        return data[row][column++];
    }
}
