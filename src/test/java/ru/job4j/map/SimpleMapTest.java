package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleMapTest {

    SimpleMap<Integer, String> map;

    @Before
    public void initData() {
        map = new SimpleMap<>();
        map.put(1, "Mike");
        map.put(2, "Bill");
        map.put(3, "John");
    }

    @Test
    public void whenFullThenExpand() {
        map.put(4, "Sam");
        map.put(5, "Kate");
        map.put(6, "Jane");
        map.put(7, "Judy");
        map.put(8, "Chris");
        assertTrue(map.put(9, "Wanda"));
    }

    @Test
    public void whenPutKeyWithSameHashAndNotEqualsThenFalse() {
        assertFalse(map.put(9, "Sam"));
    }

    @Test
    public void whenSameKeyThenReplace() {
        map.put(1, "Sam");
        assertThat(map.get(1), is("Sam"));
    }

    @Test
    public void whenGetIsNullThenNull() {
        assertNull(map.get(4));
    }

    @Test
    public void whenGetThenValue() {
        assertThat(map.get(1), is("Mike"));
    }

    @Test
    public void whenRemoveThenTrue() {
        assertTrue(map.remove(1));
        assertNull(map.get(1));
    }

    @Test
    public void whenSameHashButNotEqualThenRemoveFalse() {
        assertFalse(map.remove(9));
    }

    @Test
    public void whenIterator() {
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoNextThenException() {
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModifyThenException() {
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        map.remove(2);
        iterator.next();
    }

    @Test
    public void whenExpandThenIterator() {
        map.put(4, "Sam");
        map.put(5, "Kate");
        map.put(6, "Jane");
        map.put(7, "Judy");
        map.put(8, "Chris");
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }
}