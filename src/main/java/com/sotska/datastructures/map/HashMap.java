package com.sotska.datastructures.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_BUCKET_COUNT = 5;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final int DEFAULT_GROW_FACTOR = 2;
    private final double loadFactor;
    private final int growFactor;
    private List<Entry<K, V>>[] buckets;
    int size;

    public HashMap() {
        this(DEFAULT_BUCKET_COUNT, DEFAULT_LOAD_FACTOR, DEFAULT_GROW_FACTOR);
    }

    public HashMap(int bucketCount, double loadFactor, int growFactor) {
        this.loadFactor = loadFactor;
        this.growFactor = growFactor;
        buckets = new ArrayList[bucketCount];
    }

    @Override
    public V put(K key, V value) {
        checkOccupancy();
        return put(key, value, buckets);
    }

    @Override
    public V get(K key) {
        return getBucket(key, buckets).stream()
                .filter(entry -> entry.key.equals(key))
                .findFirst().map(entry -> entry.value)
                .orElse(null);
    }

    @Override
    public boolean containsKey(K key) {
        return getBucket(key, buckets).stream().anyMatch(entry -> entry.key.equals(key));
    }

    @Override
    public V remove(K key) {
        var bucket = getBucket(key, buckets);
        var elementToRemove = bucket.stream()
                .filter(entry -> entry.key.equals(key)).findFirst();
        elementToRemove.ifPresent(bucket::remove);
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

    private V put(K key, V value, List<Entry<K, V>>[] targetBuckets) {
        var bucket = getBucket(key, targetBuckets);
        var valueByKey = bucket.stream().filter(e -> Objects.equals(e.key, key)).findFirst();

        if (valueByKey.isPresent()) {
            var valueToReplace = valueByKey.get().value;
            valueByKey.get().value = value;
            return valueToReplace;
        }
        bucket.add(new Entry<>(key, value));
        size++;
        return null;
    }

    private List<Entry<K, V>> getBucket(K key, List<Entry<K, V>>[] buckets) {
        int bucketIndex;
        if (key == null) {
            bucketIndex = 0;
        } else {
            var hash = key.hashCode();
            if(hash == Integer.MIN_VALUE) {
                bucketIndex = 0;
            }
            else {
                bucketIndex = Math.abs(hash) % buckets.length;
            }
        }

        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new ArrayList<>(1);
        }
        return buckets[bucketIndex];
    }

    private void checkOccupancy() {
        if (buckets.length * loadFactor < size) {
            var newBuckets = new ArrayList[buckets.length * growFactor];
            size = 0;
            fillNewBuckets(newBuckets);
            buckets = newBuckets;
        }
    }

    private void fillNewBuckets(List<Entry<K, V>>[] newBuckets) {
        for (Entry<K, V> r : this) {
            put(r.key, r.value, newBuckets);
        }
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private int bucketIndex;
            private Iterator<Entry<K, V>> bucketIterator = getNextBucketIterator();

            @Override
            public boolean hasNext() {
                return bucketIterator.hasNext() || bucketIndex != buckets.length - 1;
            }

            @Override
            public Entry<K, V> next() {
                if (!bucketIterator.hasNext()) {
                    bucketIterator = getNextBucketIterator();
                }
                return bucketIterator.next();
            }

            @Override
            public void remove() {
                bucketIterator.remove();
                size--;
            }

            private Iterator<Entry<K, V>> getNextBucketIterator() {
                increaseAndCheckBucketIndex();
                while (buckets[bucketIndex] == null) {
                    increaseAndCheckBucketIndex();
                }
                bucketIterator = buckets[bucketIndex].iterator();
                return bucketIterator;
            }

            private void increaseAndCheckBucketIndex() {
                bucketIndex++;
                if (bucketIndex == buckets.length) {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    protected static class Entry<K, V> {
        private final K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
