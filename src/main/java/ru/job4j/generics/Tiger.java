package ru.job4j.generics;

public class Tiger extends Predator {
    private String nameClass = "Predator";

    @Override
    public String toString() {
        return "Tiger{"
                + "nameClass='" + nameClass + '\''
                + '}';
    }
}
