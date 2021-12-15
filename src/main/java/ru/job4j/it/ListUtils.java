package ru.job4j.it;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        for (int j = 0; j < list.size(); j++) {
            i.next();
            if (j == index) {
                i.add(value);
                break;
            }
        }

    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> i = list.listIterator();
        if (list.isEmpty()) {
            throw new IllegalStateException();
        }
        do {
            if (filter.test(i.next())) {
                i.remove();
            }
        } while (i.hasNext());
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> i = list.listIterator();
        if (list.isEmpty()) {
            throw new IllegalStateException();
        }
        do {
            if (filter.test(i.next())) {
                i.set(value);
            }
        } while (i.hasNext());
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> i = list.listIterator();
        if (list.isEmpty()) {
            throw new IllegalStateException();
        }
        do {
            if (elements.contains(i.next())) {
                i.remove();
            }
        } while (i.hasNext());
    }
}