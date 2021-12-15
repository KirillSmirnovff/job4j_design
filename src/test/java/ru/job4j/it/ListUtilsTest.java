package ru.job4j.it;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        Predicate<Integer> filter = s -> s > 1;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(List.of(0, 1)));
    }

    @Test (expected = IllegalStateException.class)
    public void whenListIsEmptyThenException() {
        List<Integer> input = new ArrayList<>();
        Predicate<Integer> filter = s -> s > 1;
        ListUtils.removeIf(input, filter);
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        Predicate<Integer> filter = s -> s > 1;
        ListUtils.replaceIf(input, filter, 4);
        assertThat(input, is(List.of(0, 1, 4)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 1, 3));
        List<Integer> filter = List.of(0, 1);
        ListUtils.removeAll(input, filter);
        assertThat(input, is(List.of(2, 3)));
    }
}