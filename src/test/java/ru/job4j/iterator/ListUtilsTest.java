package ru.job4j.iterator;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);

        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Predicate<Integer> pred = s -> s % 2 == 0;
        ListUtils.removeIf(input, pred);
        assertThat(input, is(Arrays.asList(1, 3, 5)));
    }

    @Test
    public void whenReplaceIfPredicate() {
        List<String> input = new ArrayList<>(Arrays.asList("pen", "bookcase", "book"));
        Predicate<String> pred = s -> s.length() > 4;
        ListUtils.replaceIf(input, pred, "...");
        assertThat(input, is(Arrays.asList("pen", "...", "book")));
    }

    @Test
    public void whenRemoveAll() {
        List<String> input = new ArrayList<>(Arrays.asList("111", "000", "354", "000", "010"));
        List<String> in = new ArrayList<>(List.of("000", "111"));
        ListUtils.removeAll(input,  in);
        assertThat(input, is(Arrays.asList("354", "010")));
    }
}