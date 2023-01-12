package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Emulator {
    public static final Integer CACHING_DIR = 1;
    public static final Integer LOAD = 2;
    public static final Integer DOWNLOAD = 3;
    public static String dir;
    public Map<String, DirFileCache> dirMap = new HashMap<>();

    public static final String MENU = """
                1 => указать кэшируемую директорию.
                2 => загрузить содержимое файла в кэш.
                3 => получить содержимое файла из кэша.
                любое другое число => ВЫХОД.
            """;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Emulator emulator = new Emulator();
        start(scanner, emulator);
    }

    private static void start(Scanner scan, Emulator emulator) throws IOException {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scan.nextLine());
            if (CACHING_DIR == userChoice) {
                System.out.println("Введите путь к директории");
                String path = scan.nextLine();
                dir = path;
                if (!emulator.dirMap.containsKey(path)) {
                    DirFileCache dfc = new DirFileCache(path);
                    emulator.dirMap.put(path, dfc);
                }
                emulator.dirMap.get(path).getListFiles();
            } else if (LOAD == userChoice) {
                System.out.println("Текущая директория " + dir);
                System.out.println("Введите имя файла с расширением .txt");
                String fileName = scan.nextLine();
                if (!Files.exists(Paths.get(String.format("%s\\%s", dir, fileName)))) {
                    throw new IllegalArgumentException(String.format("file %s not found", fileName));
                } else {
                    String file = Files.readString(Paths.get(String.format("%s\\%s", dir, fileName)));
                    emulator.dirMap.get(dir).put(String.format(fileName), file);
                }
            } else if (DOWNLOAD == userChoice) {
                System.out.println("Введите имя файла из текущей директории:");
                System.out.println(dir);
                String fileName = scan.nextLine();
                if (!Files.exists(Paths.get(String.format("%s/%s", dir, fileName)))) {
                    throw new IllegalArgumentException(String.format("file %s not found", fileName));
                }
                String text = emulator.dirMap.get(dir).get(fileName);
                emulator.printFile(text, fileName);
            }  else {
                run = false;
                System.out.println("Конец работы");
            }
        }
    }

    public void printFile(String text, String fileName) {
        System.out.println("------------------- " + fileName + " ---------------------");
        System.out.println(text);
        System.out.println("-----------------------------------------------------");
    }
}
