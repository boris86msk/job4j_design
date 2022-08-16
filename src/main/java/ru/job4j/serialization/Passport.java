package ru.job4j.serialization;

public class Passport {
    private final String vin;
    private String num;
    private String owner;


    public Passport(String vin, String num, String owner) {
        this.vin = vin;
        this.num = num;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Passport{"
                + "VIN='" + vin + '\''
                + ", num='" + num + '\''
                + ", owner='" + owner + '\''
                + '}';
    }
}
