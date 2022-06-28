package ru.job4j.generics;

public class Predator extends Animal {
    private String nameClass = "Predator";

    @Override
    public String toString() {
        return "Predator{" +
                "nameClass='" + nameClass + '\'' +
                '}';
    }
}
