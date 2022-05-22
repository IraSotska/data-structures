package com.ira.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_ARRAY_SIZE = 5;
    private int arraySize;
    private int currentIndex;
    private Object[] array;

    public ArrayList() {
        this(DEFAULT_ARRAY_SIZE);
    }

    public ArrayList(int size) {
        arraySize = size;
        array = new Object[arraySize];
    }

    @Override
    public void add(E element) {
        add(element, currentIndex);
    }

    @Override
    public void add(E element, int index) {
        checkIndex(index, true);
        checkSize();
        System.arraycopy(array, index, array, index + 1, 1);
        array[index] = element;

        currentIndex++;
    }

    @Override
    public Object remove(int index) {
        checkIndex(index, false);
        var elementForRemove = array[index];
        System.arraycopy(array, index + 1, array, index, currentIndex - index);
        array[currentIndex] = null;
        currentIndex--;
        return elementForRemove;
    }

    @Override
    public Object get(int index) {
        checkIndex(index, false);
        return array[index];
    }

    @Override
    public Object set(E element, int index) {
        checkIndex(index, false);
        array[index] = element;
        return array[index];
    }

    @Override
    public void clear() {
        array = new Object[arraySize];
        currentIndex = 0;
    }

    @Override
    public int size() {
        return currentIndex;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == 0;
    }

    @Override
    public String toString() {
        var stringJoiner = new StringJoiner(", ", "[", "]");

        for (int i = 0; i < currentIndex; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    @Override
    public boolean contains(E element) {
        return lastIndexOf(element) != -1;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < currentIndex; i++) {
            if(Objects.equals(array[i], element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        for (int i = currentIndex - 1; i > -1; i--) {
            if(Objects.equals(array[i], element)) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index, boolean toLastIndex) {
        if (index < 0 || index > (toLastIndex ? currentIndex : currentIndex - 1)) {
            throw new IndexOutOfBoundsException("Index should be between 0 and " + currentIndex + " Current index is " + index);
        }
    }

    private void checkSize() {
        if (currentIndex + 1 == arraySize - 1) {
            arraySize *= 1.5;
            Object[] newArr = new Object[arraySize];
            System.arraycopy(array, 0, newArr, 0, array.length);
            array = newArr;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIteratorIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIteratorIndex < currentIndex;
            }

            @Override
            public E next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                E nextElement = (E) get(currentIteratorIndex);
                currentIteratorIndex++;
                return nextElement;
            }

            @Override
            public void remove() {
                ArrayList.this.remove(currentIteratorIndex);
            }
        };
    }
}