package ru.job4j.io;

import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "src/main/java/ru/job4j/io/data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"),is("boris pokidov"));
    }

    @Test
    public void whenPairHaveThreeEquals() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"),is("password"));
    }

    @Test
    public void print() {
        String path = "src/main/java/ru/job4j/io/data/pair_have_three_equals.txt";
        Config config = new Config(path);
        config.load();
        Set set = config.values.entrySet();
        System.out.println(set);
    }
}