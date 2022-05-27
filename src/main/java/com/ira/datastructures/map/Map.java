package com.ira.datastructures.map;

public interface Map<K, V> extends Iterable<HashMap.Entry<K, V>> {
    V put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    V remove(K key);

    int size();
}
