package ru.job4j.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
            V obj = cache.getOrDefault(key, new SoftReference<>(null)).get();
                if (obj == null) {
                    obj = load(key);
                    put(key, obj);
                }
        return obj;
    }

    protected abstract V load(K key);
}
