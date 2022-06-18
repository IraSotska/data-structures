package com.sotska.datastructures.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_BUCKET_COUNT = 5;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final int DEFAULT_GROW_FACTOR = 2;
    private final double loadFactor;
    private final int growFactor;
    private Entry<K, V>[] buckets;
    private int size;

    public HashMap() {
        this(DEFAULT_BUCKET_COUNT, DEFAULT_LOAD_FACTOR, DEFAULT_GROW_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int bucketCount, double loadFactor, int growFactor) {
        this.loadFactor = loadFactor;
        this.growFactor = growFactor;
        buckets = new Entry[bucketCount];
    }

    @Override
    public V put(K key, V value) {
        checkOccupancyAndGrowBucketCount();
        return put(key, value, buckets);
    }

    @Override
    public V get(K key) {
        var bucket = getBucketByKey(key);
        return bucket == null ? null : bucket.value;
    }

    @Override
    public boolean containsKey(K key) {
        return getBucketByKey(key) != null;
    }

    @Override
    public V remove(K key) {
        for (var iterator = this.iterator(); iterator.hasNext(); ) {
            var nextEntry = iterator.next();
            if (nextEntry.getKey().equals(key)) {
                V valueToRemove = nextEntry.getValue();
                iterator.remove();
                return valueToRemove;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator<>();
    }

    private V put(K key, V value, Entry<K, V>[] targetBuckets) {
        var bucketIndex = getBucketIndex(key, targetBuckets.length);
        var currentEntry = targetBuckets[bucketIndex];

        if (currentEntry == null) {
            var newEntry = new Entry<>(key, value);
            targetBuckets[bucketIndex] = newEntry;
        } else {
            while (currentEntry != null) {
                if (currentEntry.key == key) {
                    var previousValue = currentEntry.value;
                    currentEntry.value = value;
                    return previousValue;
                }
                currentEntry = currentEntry.next;
            }
            var newEntry = new Entry<>(key, value);
            newEntry.next = targetBuckets[bucketIndex];
            targetBuckets[bucketIndex] = newEntry;
        }
        size++;
        return null;
    }

    private int getBucketIndex(K key, int bucketsLength) {
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();

        if (hash == Integer.MIN_VALUE) {
            return 0;
        } else {
            return Math.abs(hash) % bucketsLength;
        }
    }

    private Entry<K, V> getBucketByKey(K key) {
        var entry = buckets[getBucketIndex(key, buckets.length)];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void checkOccupancyAndGrowBucketCount() {
        if (buckets.length * loadFactor < size) {
            var newBuckets = new Entry[buckets.length * growFactor];
            for (Map.Entry<K, V> entry : this) {
                var indexAtNewBuckets = getBucketIndex(entry.getKey(), newBuckets.length);
                var newBucket = newBuckets[indexAtNewBuckets];

                if (newBucket != null) {
                    ((Entry<K, V>) entry).next = null;
                    newBucket.next = (Entry<K, V>) entry;
                }
                newBuckets[indexAtNewBuckets] = (Entry<K, V>) entry;
            }
            buckets = newBuckets;
        }
    }

    private class HashMapIterator<E> implements Iterator<Map.Entry<K, V>> {
        private int bucketIndex;
        private Entry<K, V> currentEntry = iterateToNextBucket();
        private boolean firstIteration = true;
        private boolean canRemove;

        @Override
        public boolean hasNext() {
            if (firstIteration) {
                return currentEntry != null;
            }
            return currentEntry.next != null;
        }

        @Override
        public Entry<K, V> next() {
            if (firstIteration) {
                firstIteration = false;
                canRemove = true;
                return currentEntry;
            }
            if (currentEntry.next == null) {
                currentEntry = iterateToNextBucket();
            }
            if (currentEntry == null) {
                throw new NoSuchElementException("No such element at map.");
            }
            canRemove = true;
            currentEntry = currentEntry.next;
            return currentEntry;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Have no element to remove.");
            }
            var index = getBucketIndex(currentEntry.key, buckets.length);
            var bucket = buckets[index];
            if (bucket.next == null) {
                buckets[index] = null;
            } else {
                var previous = bucket;
                while (previous.next != null) {
                    if (previous.next.equals(currentEntry)) {
                        previous.next = currentEntry.next;
                    }
                    previous = previous.next;
                }
            }
            size--;
        }

        private Entry<K, V> iterateToNextBucket() {
            bucketIndex++;
            if (bucketIndex == buckets.length) {
                return null;
            }
            while (buckets[bucketIndex] == null) {
                bucketIndex++;
                if (bucketIndex == buckets.length) {
                    return null;
                }
            }
            return buckets[bucketIndex];
        }
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public K getKey() {
            return key;
        }
    }
}
