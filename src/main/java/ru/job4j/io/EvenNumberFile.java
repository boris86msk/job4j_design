package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    /**
     * проверка на четность числа каждой строки документа
     * @param args
     */
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0){
                    System.out.println(line + " is even");
                } else {
                    System.out.println(line + " is not even");
                }
            }
            System.out.println("----END----");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
