package com.sotska.datastructures.map;

public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {
    V put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    V remove(K key);

    int size();

    interface Entry<K, V> {
        K getKey();

        V getValue();

        int getHash();

        void setHash(int hash);

        Entry<K, V> getNext();

        void setNext(Entry<K, V> next);
    }
}
