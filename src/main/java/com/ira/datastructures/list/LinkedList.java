package com.ira.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<E> implements List<E> {

    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size = 0;

    @Override
    public void add(E element) {
        add(element, size);
    }

    @Override
    public void add(E element, int index) {
        checkIndex(index, true);
        var newNode = new Node<>(element);
        if (size == 0) {
            firstNode = lastNode = newNode;
        } else if (index == size) {
            lastNode.nextElement = newNode;
            newNode.previousElement = lastNode;
            lastNode = newNode;
        } else if (index == 0) {
            firstNode.previousElement = newNode;
            newNode.nextElement = firstNode;
            firstNode = newNode;
        } else {
            var previousElement = findElementByIndex(index - 1);
            newNode.previousElement = previousElement;
            newNode.nextElement = previousElement.nextElement;
            previousElement.nextElement.previousElement = newNode;
            previousElement.nextElement = newNode;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index, false);
        Node<E> elementToRemove;

        if (index == 0) {
            elementToRemove = firstNode;
            firstNode = firstNode.nextElement;
        } else if (index == size) {
            elementToRemove = lastNode;
            lastNode = lastNode.previousElement;
        } else if (size == 1) {
            elementToRemove = firstNode;
            firstNode = lastNode = null;
        } else {
            elementToRemove = findElementByIndex(index);
            elementToRemove.previousElement.nextElement = elementToRemove.nextElement;
            elementToRemove.nextElement.previousElement = elementToRemove.previousElement;
        }
        size--;
        return elementToRemove.data;
    }

    @Override
    public Object get(int index) {
        checkIndex(index, false);
        return findElementByIndex(index).data;
    }

    @Override
    public Object set(E element, int index) {
        checkIndex(index, false);
        var newNode = new Node<>(element);
        var nodeAtIndex = findElementByIndex(index);
        nodeAtIndex.previousElement.nextElement = newNode;
        nodeAtIndex.nextElement.previousElement = newNode;
        newNode.previousElement = nodeAtIndex.previousElement;
        newNode.nextElement = nodeAtIndex.nextElement;
        return element;
    }

    @Override
    public void clear() {
        firstNode = lastNode = null;
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
    public boolean contains(E element) {
        return lastIndexOf(element) != -1;
    }

    @Override
    public int indexOf(E element) {
        var currentElement = firstNode;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(currentElement.data, element)) {
                return i;
            }
            currentElement = currentElement.nextElement;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        var currentElement = lastNode;

        for (int i = size - 1; i > -1; i--) {
            if (Objects.equals(currentElement.data, element)) {
                return i;
            }
            currentElement = currentElement.previousElement;
        }
        return -1;
    }

    @Override
    public String toString() {
        var stringJoiner = new StringJoiner(", ", "[", "]");

        for (int i = 0; i < size; i++) {
            stringJoiner.add(String.valueOf(get(i)));
        }
        return stringJoiner.toString();
    }


    private Node<E> findElementByIndex(int index) {
        Node<E> currentNode = firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.nextElement;
        }
        return currentNode;
    }

    private void checkIndex(int index, boolean toLastIndex) {
        if (index < 0 || index > (toLastIndex ? size : size - 1)) {
            throw new IndexOutOfBoundsException("Index should be between 0 and " + size + " Current index is " + index);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private Node<E> currentElement = firstNode;

            @Override
            public boolean hasNext() {
                return currentElement != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = currentElement.data;
                currentElement = currentElement.nextElement;
                return result;
            }

            @Override
            public void remove() {
                LinkedList.this.remove(indexOf(currentElement.data));
            }
        };
    }

    static class Node<E> {
        private Node<E> nextElement;
        private Node<E> previousElement;
        private E data;

        private Node(E data) {
            this.data = data;
        }
    }
}
