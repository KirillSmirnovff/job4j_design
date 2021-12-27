package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int size = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(key.hashCode()));
        boolean result = table[index] == null;
        if (result) {
            expand();
            table[index] = new MapEntry<>(key, value);
            size++;
            modCount++;
        }
        if (!result) {
            if (key.hashCode() == table[index].key.hashCode()
                    && key.equals(table[index].key)) {
                table[index].value = value;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if (size / LOAD_FACTOR >= capacity) {
            MapEntry<K, V>[] tempTable = table;
            capacity *= 2;
            size = 0;
            table = new MapEntry[capacity];
            for (MapEntry<K, V> mapEntry : tempTable) {
                if (mapEntry != null) {
                    put(mapEntry.key, mapEntry.value);
                }
            }
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        boolean result = table[index] != null
                && key.hashCode() == table[index].key.hashCode()
                && key.equals(table[index].key);
        if (result) {
            table[index] = null;
            modCount++;
            size--;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private final int expectedModCount = modCount;
            private int count = 0;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (count < size && table[index] == null) {
                    index++;
                }
                return count < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                count++;
                return table[index].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}