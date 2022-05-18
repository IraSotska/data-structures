package com.ira.datastructures.list;

public interface List<T> extends Iterable<T> {
    // add value to the end of the list
    void add(T t);

    // [A, B, C, null, null ] size = 3
    // add (D, [0,1,2,3])
    // we can add value by index between [0, size]
    // otherwise throw new IndexOutOfBoundsException
    void add(T t, int index);

    // we can remove value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException

    // [A, B, C] remove = 0
    // [B (index = 0) , C (index = 1)]
    Object remove(int index);

    // [A, B, C] size = 3
    // we can get value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException
    Object get(int index);

    // we can set value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException
    Object set(T t, int index);

    void clear();

    int size();

    boolean isEmpty();

    boolean contains(T t);

    // [A, B, A, C] indexOf(A) -> 0
    // -1 if not exist
    int indexOf(T t);

    // [A, B, A, C] lastIndexOf(A) -> 2
    int lastIndexOf(T t);

    // [A, B, C]
    String toString();
}
