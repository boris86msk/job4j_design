package ru.job4j.serialization;

public class Passport {
    private final String VIN;
    private String num;
    private String owner;


    public Passport(String VIN, String num, String owner) {
        this.VIN = VIN;
        this.num = num;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Passport{"
                + "VIN='" + VIN + '\''
                + ", num='" + num + '\''
                + ", owner='" + owner + '\''
                + '}';
    }
}
