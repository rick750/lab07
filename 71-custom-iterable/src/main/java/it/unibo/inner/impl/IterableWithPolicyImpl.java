package it.unibo.inner.impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

/**
 * IterableWithPolicy implementation.
 */
public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private final T[] elements;
    private final Predicate<T> filter;

    public IterableWithPolicyImpl(T[] elements) {
        this.elements = elements;
        this.filter = new Predicate<>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        };
    }

    public IterableWithPolicyImpl(T[] elements, Predicate<T> filter) {
        this.elements = elements;
        this.filter = filter;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        
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
                return this.current < IterableWithPolicyImpl.this.elements.length;
            }
            
            @Override
            public T next() {
                return IterableWithPolicyImpl.this.elements[current++];
            }
        }
        return new InnerIterator();
    }    
}
