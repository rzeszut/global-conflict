package edu.globalconflict.util;

/**
 * Efficient array-based stack implementation.
 *
 * @author mateusz
 * @since 17.08.14
 */
public final class Stack<T> {
    private Object[] elements;
    private int size = 0;

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
        if (element == null || size == elements.length) {
            // TODO: add automatic resizing
            return false;
        } else {
            elements[size++] = element;
            return true;
        }
    }

    public void clear() {
        for (; size > 0; --size) {
            elements[size - 1] = null;
        }
    }

    public void remove(T element) {
        final int index = indexOf(element);
        elements[index] = null;
        reshapeArray();
    }

    private void reshapeArray() {
        int firstFreeIndex = -1;
        int removedElements = 0;

        for (int i = 0; i < size; ++i) {
            final Object element = elements[i];

            // if an element was removed
            if (element == null) {
                // if this is the first empty place, save it
                if (firstFreeIndex == -1) {
                    firstFreeIndex = i;
                }
                ++removedElements;
            } else if (firstFreeIndex != -1) {
                // if there was a free space before current element, move it there and free current location
                elements[firstFreeIndex++] = element;
                elements[i] = null;
            }
        }

        size -= removedElements;
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
}
