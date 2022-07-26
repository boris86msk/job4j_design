package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * класс, имплементирующий FileVisitor может не тоько переопределять
 * егометоды но и сожержать необходимые поля, конструктор, логику.
 */
public class SearchFiles implements FileVisitor<Path> {
    private Predicate<Path> condition;
    private List<Path> paths = new ArrayList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() {
        return paths;
    }

    /**
     * переопределяем preVisitDirectory для контроля обхода дерева
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        System.out.println("Enter to dir " + dir);
        return CONTINUE;
    }

    /**
     * переопределяем visitFile для действий по файлам (фильтрация)
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if(condition.test(file)) {
            paths.add(file);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return CONTINUE;
    }
}
