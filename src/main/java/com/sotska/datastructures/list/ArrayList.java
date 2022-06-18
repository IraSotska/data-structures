package com.sotska.datastructures.list;

import java.util.*;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_INITIAL_CAPACITY = 5;
    private static final double DEFAULT_LOAD_FACTOR = 1.5;
    private int size;
    private T[] array;
    private final double loadFactor;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public ArrayList(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity, double loadFactor) {
        this.loadFactor = loadFactor;
        array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T elementForRemove = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        array[size - 1] = null;
        size--;
        return elementForRemove;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        validateIndex(index);
        var oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        var stringJoiner = new StringJoiner(", ", "[", "]");
        this.forEach(element -> stringJoiner.add(String.valueOf(element)));

        return stringJoiner.toString();
    }

    @Override
    public boolean contains(T value) {
        return lastIndexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ArrayListIterator<T> iterator() {
        return new ArrayListIterator<>();
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index should be between 0 and " + size + " Current index is " + index);
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between 0 and " + size + " Current index is " + index);
        }
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == array.length - 1) {
            T[] newArray = (T[]) new Object[(int) (array.length * loadFactor)];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    private class ArrayListIterator<E> implements Iterator<T> {
        private int currentIteratorIndex = 0;
        private boolean isCurrentElementCanBeRemoved;

        @Override
        public boolean hasNext() {
            return currentIteratorIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextElement = get(currentIteratorIndex);
            currentIteratorIndex++;
            isCurrentElementCanBeRemoved = true;
            return nextElement;
        }

        @Override
        public void remove() {
            if (isCurrentElementCanBeRemoved) {
                ArrayList.this.remove(currentIteratorIndex - 1);
                isCurrentElementCanBeRemoved = false;
            } else {
                throw new IllegalStateException();
            }
        }
    }
}