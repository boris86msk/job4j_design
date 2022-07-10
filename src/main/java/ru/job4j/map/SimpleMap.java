package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rst = false;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        MapEntry<K, V> newCouple = new MapEntry<>(key, value);
        int index = indexFor(hash(key));
        if (table[index] == null) {
            table[index] = newCouple;
            modCount++;
            count++;
            rst = true;
        }

        return rst;
    }

    private int hash(K key) {
        int h = key.hashCode();
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> newCouple = table[i];
                int index = indexFor(hash(newCouple.key));
                newTable[index] = newCouple;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key));
        return table[index] != null && table[index].key.equals(key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rst = false;
        int index = indexFor(hash(key));
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            rst = true;
            modCount++;
            count--;
        }
        return rst;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int modCountEntrance = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (modCountEntrance != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (K) table[index++];
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
