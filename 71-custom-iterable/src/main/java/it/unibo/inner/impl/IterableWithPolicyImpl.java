package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

/**
 * IterableWithPolicy implementation.
 */
public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private final T[] elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] elements) {
        this.elements = elements;
        this.filter = new Predicate<>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        };
    }

    public IterableWithPolicyImpl(final T[] elements, final Predicate<T> filter) {
        this.elements = elements;
        this.filter = filter;
    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.filter = filter;       
    }

    @Override
    public Iterator<T> iterator() {
        class InnerIterator implements Iterator<T>{
            private int current;

            public InnerIterator() {
                this.current = 0;
            }

            @Override
            public boolean hasNext() {
                for (; current < elements.length; current++) {
                    final T elem = elements[current];
                    if (filter.test(elem)) {
                        return true;
                    }
                }
                return false;
            }
            
            @Override
            public T next() {
                if (hasNext()) {                    
                    return elements[current++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        }
        return new InnerIterator();
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }    
}
