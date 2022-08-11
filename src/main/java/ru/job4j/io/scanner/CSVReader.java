package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String path = argsName.get("path");
        String out = argsName.get("out");
        String del = argsName.get("delimiter");
        String filter = argsName.get("filter");
        validateArgs(path, out, del, filter);

        List<String> keys = Arrays.stream(filter.split(",")).toList();
        Map<Integer, List<String>> filteredFile = new HashMap<>();
        Map<Integer, Integer> accordanceMap = new HashMap<>();
        int columnSize = 1;

        try (var scanner = new Scanner(new BufferedReader(new FileReader(path)))) {
            scanner.useDelimiter(System.lineSeparator());

            String[] headings = scanner.next().split(del);
            for (int i = 0; i < headings.length; i++) {
                if (keys.contains(headings[i])) {
                    filteredFile.put(i, new ArrayList());
                    filteredFile.get(i).add(headings[i]);
                    accordanceMap.put(keys.indexOf(headings[i]), i);
                }
            }

            while (scanner.hasNext()) {
                String[] line = scanner.next().split(del);
                columnSize++;
                for (int i = 0; i < line.length; i++) {
                    if (filteredFile.containsKey(i)) {
                        filteredFile.get(i).add(line[i]);
                    }
                }
            }

            try (PrintWriter out2 = new PrintWriter(
                    new BufferedOutputStream(out.contains("stdout")?
                            new PrintStream(System.out) : new FileOutputStream(out)
                    ))) {

                for (int i = 0; i < columnSize; i++) {
                    for (int j = 0; j < filteredFile.size(); j++) {
                        if (j < filteredFile.size() - 1) {
                            out2.print(filteredFile.get(accordanceMap.get(j)).get(i) + ";");
                        } else {
                            out2.print(filteredFile.get(accordanceMap.get(j)).get(i));
                        }
                    }
                    out2.println();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validateArgs (String path, String out, String delimiter, String filter) {
        if (path == null || out == null || delimiter == null || filter == null
                || path.isEmpty() || out.isEmpty() || delimiter.isEmpty() || filter.isEmpty()) {
            throw new IllegalArgumentException("incorrect number of arguments");
        }

        Path path2 = Paths.get(path);
        if (!Files.exists(path2) || !path2.toFile().isFile()) {
            throw new IllegalArgumentException(String.format("a nonexistent path has been introduced: %s", path));
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        CSVReader.handle(argsName);
    }
}
