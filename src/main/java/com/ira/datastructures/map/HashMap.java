package com.ira.datastructures.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_BUCKET_COUNT = 5;
    private ArrayList<Entry<K, V>>[] buckets;
    int size;

    public HashMap() {
        this(DEFAULT_BUCKET_COUNT);
    }

    public HashMap(int bucketCount) {
        buckets = new ArrayList[bucketCount];
    }

    @Override
    public V put(K key, V value) {
        var bucket = getBucket(key);
        var valueByKey = bucket.stream().filter(e -> e.key.equals(key)).findFirst();

        if (valueByKey.isPresent()) {
            var valueToReplace = valueByKey.get().value;
            valueByKey.get().value = value;
            return valueToReplace;
        }
        bucket.add(new Entry<>(key, value));
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        return getBucket(key).stream()
                .filter(entry -> entry.key.equals(key))
                .findFirst().map(entry -> entry.value)
                .orElse(null);
    }

    @Override
    public boolean containsKey(K key) {
        return getBucket(key).stream().anyMatch(entry -> entry.key.equals(key));
    }

    @Override
    public V remove(K key) {
        var bucket = getBucket(key);
        var elementToRemove = bucket.stream().filter(entry -> entry.key.equals(key)).findFirst();
        if (elementToRemove.isPresent()) {
            var value = elementToRemove.get().value;
            bucket.remove(elementToRemove.get());
            return value;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private ArrayList<Entry<K, V>> getBucket(K key) {
        var bucketNumber = key.hashCode() % buckets.length;

        if (buckets[bucketNumber] == null) {
            buckets[bucketNumber] = new ArrayList<>();
        }
        return buckets[bucketNumber];
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {

            private int bucketIndex;
            private Iterator<Entry<K, V>> bucketIterator = getNextBucketIterator();

            @Override
            public boolean hasNext() {
                return bucketIterator.hasNext();
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    bucketIndex++;
                    if (bucketIndex == buckets.length) {
                        throw new NoSuchElementException();
                    }
                    bucketIterator = getNextBucketIterator();
                }
                return bucketIterator.next().value;
            }

            @Override
            public void remove() {
                bucketIterator.remove();
                size--;
            }

            private Iterator<Entry<K, V>> getNextBucketIterator() {
                while (buckets[bucketIndex] == null) {
                    bucketIndex++;
                    if (bucketIndex == buckets.length) {
                        throw new NoSuchElementException();
                    }
                }
                bucketIterator = buckets[bucketIndex].iterator();
                return bucketIterator;
            }
        };
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
