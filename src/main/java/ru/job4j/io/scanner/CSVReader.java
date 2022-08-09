package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String out = argsName.get("out");
        String del = argsName.get("delimiter");
        String filter = argsName.get("filter");

        List<String> keys = Arrays.stream(filter.split(",")).toList();
        Map<Integer, List<String>> filteredFile = new HashMap<>();
        Map<Integer, Integer> accordanceMap = new HashMap<>();

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
                for (int i = 0; i < line.length; i++) {
                    if (filteredFile.containsKey(i)) {
                        filteredFile.get(i).add(line[i]);
                    }
                }
            }

            try (PrintWriter out2 = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(out)
                    ))) {

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < filteredFile.size(); j++) {
                        if (j < filteredFile.size() - 1) {
                            out2.print(filteredFile.get(accordanceMap.get(j)).get(i) + ";");
                        } else {
                            out2.print(filteredFile.get(accordanceMap.get(j)).get(i));
                        }
                    }
                    out2.println();
                }

            } catch (Exception IOException) {
                IOException.printStackTrace();
            }

        } catch (Exception IOException) {
            IOException.printStackTrace();
        }
    }
}
