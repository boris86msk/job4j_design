package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileReader;

public class ReadFile {
    /**
     * Пример чтения файла из корня проекта
     * метод split с параметром System.lineSeparator() разбивает
     * файл по строкам для удобства дальнейшей обработки
     * @param args
     */
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
                System.out.println(text);
            }



//            String[] lines = text.toString().split(System.lineSeparator());
//            for (String line : lines) {
//                System.out.println(line);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try (FileReader rid = new FileReader("input.txt")) {
//            StringBuilder text = new StringBuilder();
//            int rd;
//            while ((rd = rid.read()) != -1) {
//                text.append((char) rd);
//                System.out.println(text);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
