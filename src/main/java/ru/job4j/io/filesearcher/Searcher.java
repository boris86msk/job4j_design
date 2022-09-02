package ru.job4j.io.filesearcher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Searcher {
    private static Path path;
    private  static String searchObject;
    private static String saveFile;

    private Searcher(Path path, String searchObject, String saveFile) {
        Searcher.path = path;
        Searcher.searchObject = searchObject;
        Searcher.saveFile = saveFile;
    }
    /**
     * Ключи
     * -d - директория, в которой начинать поиск.
     * -n - имя файла, маска, либо регулярное выражение.
     * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
     * -o - результат записать в файл.
     * @param args
     */

    public static void main(String[] args) throws IOException {
        ArgsName arg = ArgsName.of(args);
        Path path = Paths.get(arg.get("d"));
        String searchObject = arg.get("n");
        String saveFile = arg.get("o");
        Searcher searcher = new Searcher(path, searchObject, saveFile);

        paramValidate(arg);
        switch (arg.get("t")) {
            case ("mask"):
                scanByMask();
                break;
            case ("name"):
                searchByName();
                break;
            case ("regex"):
                searchByRegex();
                break;
            default:
                break;
        }
    }

    private static void scanByMask() throws IOException {
        searchObject = searchObject.replace("?", "\\w");
        searchObject = searchObject.replace("*", "\\w+");
        searchByRegex();
    }

    private static void searchByName() throws IOException {
        Predicate<Path> pred = p -> p.toFile().getName().endsWith(searchObject);
        List<Path> list = search(path, pred);
        save(list, saveFile);
    }

    private static void searchByRegex() throws IOException {
        Predicate<Path> pred = p -> Pattern.matches(searchObject, p.toFile().getName());
        List<Path> list = search(path, pred);
        save(list, saveFile);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void save(List<Path> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (Path path : log) {
                out.printf("%s%n", path);
            }
        } catch (Exception ioexception) {
            ioexception.printStackTrace();
        }
    }

    static void paramValidate(ArgsName args) {
        if (args.get("d") == null || args.get("n") == null
                || args.get("t") == null || args.get("o") == null) {
            throw new IllegalArgumentException("insufficient number of arguments.");
        }
        File file = new File(args.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format(
                    "Not exist %s", file.getAbsolutePath()
            ));
        } else if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "Not directory %s", file.getAbsoluteFile()
            ));
        }
        if (!(args.get("t").equals("mask") || args.get("t").equals("name")
                || args.get("t").equals("regex"))) {
            throw new IllegalArgumentException(String.format(
                    "invalid search type parameter \"%s\" ", args.get("t")
            ));
        }
    }
}
