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
        int index = hash(key.hashCode());
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
        return hashCode % capacity;
    }

    private void expand() {
        if (size / (double) capacity > LOAD_FACTOR) {
            MapEntry<K, V>[] tempTable = table;
            capacity *= 2;
            modCount = 0;
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
        int index = hash(key.hashCode());
        if (table[index] != null) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int index = hash(key.hashCode());
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
                return count < size;
            }

            @Override
            public K next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K result = null;
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        index = i + 1;
                        count++;
                        result =  table[i].key;
                        break;
                    }
                }
                return result;
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