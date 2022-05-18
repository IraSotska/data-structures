package com.ira.datastructures.list;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {

    private Node firstNode;
    private Node lastNode;
    private int size = 0;

    @Override
    public void add(T t) {
        var newNode = new Node(t);

        if (firstNode == null) {
            firstNode = lastNode = newNode;
        } else {
            lastNode.nextElement = newNode;
            newNode.previousElement = lastNode;
            lastNode = newNode;
        }
        size++;
    }

    @Override
    public void add(T t, int index) {
        checkIndex(index, true);
        var newNode = new Node(t);
        if (index == 0) {
            firstNode.previousElement = firstNode = newNode;
        } else if (index == size) {
            lastNode.nextElement = newNode;
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
    public Object remove(int index) {
        checkIndex(index, false);
        Node elementToRemove;

        if (index == 0) {
            elementToRemove = firstNode;
            firstNode = firstNode.nextElement;
        } else if (index == size) {
            elementToRemove = lastNode;
            lastNode = lastNode.previousElement;
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
    public Object set(T t, int index) {
        checkIndex(index, false);
        var newNode = new Node(t);
        var nodeAtIndex = findElementByIndex(index);
        nodeAtIndex.previousElement.nextElement = newNode;
        nodeAtIndex.nextElement.previousElement = newNode;
        newNode.previousElement = nodeAtIndex.previousElement;
        newNode.nextElement = nodeAtIndex.nextElement;
        return t;
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
    public boolean contains(T t) {
        return lastIndexOf(t) != -1;
    }

    @Override
    public int indexOf(T t) {
        var currentElement = firstNode;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(currentElement.data, t)) {
                return i;
            }
            currentElement = currentElement.nextElement;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T t) {
        var currentElement = lastNode;

        for (int i = size - 1; i > -1; i--) {
            if (Objects.equals(currentElement.data, t)) {
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


    private Node findElementByIndex(int index) {
        var currentNode = firstNode;
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
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node currentElement = firstNode;

            @Override
            public boolean hasNext() {
                return currentElement != null;
            }

            @Override
            public T next() {
                var result = currentElement.data;
                currentElement = currentElement.nextElement;
                return (T) result;
            }

            @Override
            public void remove() {
                LinkedList.this.remove(indexOf((T) currentElement.data));
            }
        };
    }

    class Node {
        private Node nextElement;
        private Node previousElement;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }
    }
}
