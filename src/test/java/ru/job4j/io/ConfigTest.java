package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("boris pokidov"));
    }

    @Test
    public void whenPairHaveComment() {
        String path = "data/pair_have_comment.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("address"));
    }

    @Test
    public void whenPairHaveThreeEquals() {
        String path = "data/pair_have_three_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("address"), is("nevsky prospekt=15"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenKeyPairIsNull() {
        String path = "data/key_pair_is_null.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenIsNotEquals() {
        String path = "data/is_not_equals.properties";
        Config config = new Config(path);
        config.load();
    }
}