package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    /**
     * Для создания архива используется класс ZipOutputStream. Для создания объекта ZipOutputStream
     * в его конструктор передается поток вывода: new FileOutputStream(target).
     * Для записи файлов в архив для каждого файла создается объект ZipEntry, в конструктор которого
     * передается имя архивируемого файла. А чтобы добавить каждый объект ZipEntry в архив,
     * применяется метод zip.putNextEntry()
     */
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * параметры:
     * -d - directory - которую мы хотим архивировать.
     * -e - exclude - расширение исключаемых файлов.
     * -o - output - путь zip-папки.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName argsName = ArgsName.of(args);
        String[] argsArr = {
                argsName.get("d"),
                argsName.get("e"),
                argsName.get("o")
        };
        Search.paramValidate(argsArr);
        Path path = Paths.get(argsName.get("d"));
        Predicate<Path> pred = p -> !p.toFile().getName().endsWith(argsName.get("e"));
        List<Path> list = Search.search(path, pred);
        zip.packFiles(list, new File(argsName.get("o")));
    }
}
