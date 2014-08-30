package edu.globalconflict.util;

import java.util.Iterator;

/**
 * Infinite iterator cycling over given iterable.
 *
 * @author mateusz
 * @since 22.08.14
 */
public final class CycleIterator<T> implements Iterator<T> {
    private Iterable<T> iterable;
    private Iterator<T> iterator;
    private int count = 0;

    public CycleIterator(Iterable<T> iterable) {
        this.iterable = iterable;
        this.iterator = iterable.iterator();
    }

    @Override
    public boolean hasNext() {
        if (!iterator.hasNext()) {
            iterator = iterable.iterator();
        }
        return iterator.hasNext();
    }

    @Override
    public T next() {
        ++count;
        return iterator.next();
    }

    @Override
    public void remove() {
        iterator.remove();
    }

    public int getCallCount() {
        return count;
    }
}
