package ru.job4j.kiss;

public class Cat {
    public int id;

    public Cat(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + '}';
    }
}
