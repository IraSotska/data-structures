package com.sotska.datastructures.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_BUCKET_COUNT = 5;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final int DEFAULT_GROW_FACTOR = 2;
    private final double loadFactor;
    private final int growFactor;
    private Map.Entry<K, V>[] buckets;
    int size;

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
        checkOccupancy();
        var newEntry = new Entry<>(key,
                value,
                key == null ? 0 : key.hashCode());
        return put(newEntry, buckets);
    }

    @Override
    public V get(K key) {
        var currentEntry = buckets[getBucketIndex(new Entry<>(key), buckets.length)];

        for (Map.Entry<K, V> entry : currentEntry) {
            if (Objects.equals(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        var bucket = buckets[getBucketIndex(new Entry<>(key), buckets.length)];
        if (bucket != null) {
            for (Map.Entry<K, V> entry : bucket) {
                if (Objects.equals(entry.getKey(), key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        var currentEntry = buckets[getBucketIndex(new Entry<>(key), buckets.length)];
        if (currentEntry != null) {
            for (var iterator = currentEntry.iterator(); iterator.hasNext(); ) {
                var entry = iterator.next();
                if (Objects.equals(entry.getKey(), key)) {
                    V valueToRemove = entry.getValue();
                    iterator.remove();
                    return valueToRemove;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public HashMapIterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator<>();
    }

    private V put(Map.Entry<K, V> entry, Map.Entry<K, V>[] targetBuckets) {
        var bucketIndex = getBucketIndex(entry, targetBuckets.length);
        var currentEntry = targetBuckets[bucketIndex];

        if (currentEntry == null) {
            targetBuckets[bucketIndex] = entry;
            size++;
            return null;
        } else {
            V previousValue = null;
            for (Map.Entry<K, V> current : currentEntry) {
                if (Objects.equals(current.getKey(), entry.getKey())) {
                    previousValue = current.getValue();
                    current.setValue(entry.getValue());
                }
            }
            if (previousValue == null) {
                entry.setNext(currentEntry);
                targetBuckets[bucketIndex] = entry;
                size++;
            }
            return previousValue;
        }
    }

    private int getBucketIndex(Map.Entry<K, V> entry, int bucketsLength) {
        int bucketIndex;

        if (entry.getKey() == null) {
            bucketIndex = 0;
        } else {
            if (entry.getHash() == 0) {
                entry.setHash(entry.getKey().hashCode());
            }
            if (entry.getHash() == Integer.MIN_VALUE) {
                bucketIndex = 0;
            } else {
                bucketIndex = Math.abs(entry.getHash()) % bucketsLength;
            }
        }
        return bucketIndex;
    }

    @SuppressWarnings("unchecked")
    private void checkOccupancy() {
        if (buckets.length * loadFactor < size) {
            var newBuckets = new Entry[buckets.length * growFactor];
            size = 0;
            for (Map.Entry<K, V> entry : this) {

                var currentEntry = entry;
                while (currentEntry != null) {
                    var next = currentEntry.getNext();
                    currentEntry.setNext(null);
                    put(currentEntry, newBuckets);
                    currentEntry = next;
                }
                put(entry, newBuckets);
            }
            buckets = newBuckets;
        }
    }

    @SuppressWarnings("unchecked")
    private void removeEntry(Object key) {
        buckets[getBucketIndex(new Entry<>((K) key), buckets.length)] = null;
    }

    class HashMapIterator<E> implements java.util.Iterator<Map.Entry<K, V>> {
        private int bucketIndex;
        private java.util.Iterator<Map.Entry<K, V>> bucketIterator = getNextBucketIterator();

        @Override
        public boolean hasNext() {
            return bucketIterator.hasNext() || bucketIndex != buckets.length - 1;
        }

        @Override
        public Map.Entry<K, V> next() {
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

        private java.util.Iterator<Map.Entry<K, V>> getNextBucketIterator() {
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
                throw new NoSuchElementException("No such element at map.");
            }
        }
    }

    private class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private int hash;
        private Map.Entry<K, V> next;

        private Entry(K key) {
            this.key = key;
        }

        private Entry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public Map.Entry<K, V> getEntry() {
            return this;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public void setHash(int hash) {
            this.hash = hash;
        }

        @Override
        public int getHash() {
            return hash;
        }

        @Override
        public Map.Entry<K, V> getNext() {
            return next;
        }

        @Override
        public void setNext(Map.Entry<K, V> next) {
            this.next = next;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator<>();
        }

        private class EntryIterator<E> implements java.util.Iterator<Map.Entry<K, V>> {
            private Map.Entry<K, V> previousEntry = getEntry();
            boolean isFirstEntry = true;
            boolean isSecondEntry = true;

            @Override
            public boolean hasNext() {
                if (isFirstEntry) {
                    return true;
                }
                if (isSecondEntry) {
                    return previousEntry.getNext() != null;
                }
                return previousEntry.getNext().getNext() != null;
            }

            @Override
            public Map.Entry<K, V> next() {
                if (hasNext()) {
                    if (isFirstEntry || isSecondEntry) {
                        if (isFirstEntry && isSecondEntry) {
                            isFirstEntry = false;
                            return previousEntry;
                        }
                        isSecondEntry = false;
                    } else {
                        previousEntry = previousEntry.getNext();
                    }
                    return previousEntry.getNext();
                }
                throw new NoSuchElementException("No such element at bucket.");
            }

            @Override
            public void remove() {
                if (!isFirstEntry && isSecondEntry && previousEntry.getNext() == null) {
                    removeEntry(previousEntry.getKey());
                    return;
                }
                previousEntry.setNext(previousEntry.getNext().getNext());
            }
        }
    }
}
