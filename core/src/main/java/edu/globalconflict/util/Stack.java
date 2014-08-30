package edu.globalconflict.util;

import java.util.Iterator;

/**
 * Efficient array-based stack implementation.
 *
 * @author mateusz
 * @since 17.08.14
 */
public final class Stack<T> implements Iterable<T> {
    private static final int RESIZE_STEP = 8;

    private Object[] elements;
    private int size = 0;

    public Stack() {
        this(RESIZE_STEP);
    }

    public Stack(int capacity) {
        elements = new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        return size == 0 ? null : (T) elements[size - 1];
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0) {
            return null;
        } else {
            final T element = (T) elements[--size];
            elements[size] = null;
            return element;
        }
    }

    public boolean push(T element) {
        if (element == null) {
            return false;
        }

        if (size == elements.length && !resize(size + RESIZE_STEP)) {
            return false;
        }

        elements[size++] = element;
        return true;
    }

    public boolean resize(int newCapacity) {
        if (newCapacity <= elements.length) {
            return false;
        }

        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;

        return true;
    }

    public void clear() {
        while (size > 0) {
            elements[--size] = null;
        }
    }

    public void remove(T element) {
        final int index = indexOf(element);
        if (index != -1) {
            final int srcPos = index + 1;
            System.arraycopy(elements, srcPos, elements, index, size - srcPos);
            elements[--size] = null;
        }
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; ++i) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public boolean empty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elements.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    class StackIterator implements Iterator<T> {
        private int currentIndex = Stack.this.size;

        @Override
        public boolean hasNext() {
            return currentIndex > 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            return (T) Stack.this.elements[--currentIndex];
        }

        @Override
        public void remove() {
            // TODO
        }
    }
}
