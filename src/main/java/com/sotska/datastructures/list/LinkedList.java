package com.sotska.datastructures.list;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {

    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        var newNode = new Node<>(value);
        if (size == 0) {
            firstNode = lastNode = newNode;
        } else if (index == size) {
            lastNode.nextNode = newNode;
            newNode.previousNode = lastNode;
            lastNode = newNode;
        } else if (index == 0) {
            firstNode.previousNode = newNode;
            newNode.nextNode = firstNode;
            firstNode = newNode;
        } else {
            var previousElement = getNode(index - 1);
            newNode.previousNode = previousElement;
            newNode.nextNode = previousElement.nextNode;
            previousElement.nextNode.previousNode = newNode;
            previousElement.nextNode = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Node<T> nodeToRemove;

        if (index == 0) {
            nodeToRemove = firstNode;
            firstNode = firstNode.nextNode;
        } else if (index == size) {
            nodeToRemove = lastNode;
            lastNode = lastNode.previousNode;
        } else if (size == 1) {
            nodeToRemove = firstNode;
            firstNode = lastNode = null;
        } else {
            nodeToRemove = getNode(index);
            nodeToRemove.previousNode.nextNode = nodeToRemove.nextNode;
            nodeToRemove.nextNode.previousNode = nodeToRemove.previousNode;
        }
        size--;
        return nodeToRemove.value;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return getNode(index).value;
    }

    @Override
    public T set(T value, int index) {
        checkIndex(index);
        var nodeAtIndex = getNode(index);
        var oldValue = nodeAtIndex.value;
        nodeAtIndex.value = value;
        return oldValue;
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
    public boolean contains(T value) {
        return lastIndexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        var currentNode = firstNode;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(currentNode.value, value)) {
                return i;
            }
            currentNode = currentNode.nextNode;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        var currentElement = lastNode;

        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(currentElement.value, value)) {
                return i;
            }
            currentElement = currentElement.previousNode;
        }
        return -1;
    }

    @Override
    public String toString() {
        var stringJoiner = new StringJoiner(", ", "[", "]");
        this.forEach(element -> stringJoiner.add(String.valueOf(element)));

        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>();
    }

    private Node<T> getNode(int index) {
        Node<T> currentNode;
        if (index > size / 2) {
            currentNode = lastNode;
            for (int i = 0; i < size - index - 1; i++) {
                currentNode = currentNode.previousNode;
            }
        } else {
            currentNode = firstNode;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.nextNode;
            }
        }
        return currentNode;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index should be between 0 and " + size + " Current index is " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between 0 and " + size + " Current index is " + index);
        }
    }

    class Iterator<E> implements java.util.Iterator<T> {
        private Node<T> currentElement;
        private boolean isCurrentElementCanBeRemoved;

        @Override
        public boolean hasNext() {
            if (currentElement != null) {
                return currentElement.nextNode != null;
            }
            return size != 0;
        }

        @Override
        public T next() {
            if (currentElement == null) {
                currentElement = firstNode;
            } else if (currentElement.nextNode == null) {
                throw new NoSuchElementException();
            } else {
                currentElement = currentElement.nextNode;
            }
            isCurrentElementCanBeRemoved = true;
            return currentElement.value;
        }

        @Override
        public void remove() {
            if (isCurrentElementCanBeRemoved) {
                LinkedList.this.remove(indexOf(currentElement.value));
                isCurrentElementCanBeRemoved = false;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private static class Node<T> {
        private Node<T> nextNode;
        private Node<T> previousNode;
        private T value;

        private Node(T value) {
            this.value = value;
        }
    }
}
