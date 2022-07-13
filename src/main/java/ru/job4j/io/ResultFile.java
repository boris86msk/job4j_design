package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int j = 2; j < 10; j++) {
                for (int k = 2; k < 6; k++) {
                    if (j * k < 10) {
                        out.write((k + " * " + j + " =" + k * j + "    ").getBytes());
                    } else {
                        out.write((k + " * " + j + " =" + k * j + "   ").getBytes());
                    }
                }
                out.write(System.lineSeparator().getBytes());

            }
            out.write(System.lineSeparator().getBytes());
            for (int j = 2; j < 10; j++) {
                for (int k = 6; k < 10; k++) {
                    out.write((k + " * " + j + " =" + k * j + "   ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
