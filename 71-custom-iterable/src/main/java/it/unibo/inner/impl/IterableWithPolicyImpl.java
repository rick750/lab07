package it.unibo.inner.impl;

import java.util.Iterator;
import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

/**
 * IterableWithPolicy implementation.
 */
public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private final T[] array;

    public IterableWithPolicyImpl(T[] array) {
        this.array = array;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        
    }

    public Iterator<T> iterator() {
        class InnerIterator implements Iterator<T>{
            private int current;

            public InnerIterator() {
                this.current = 0;
            }

            @Override
            public boolean hasNext() {
                return this.current < IterableWithPolicyImpl.this.array.length;
            }
            
            @Override
            public T next() {
                return IterableWithPolicyImpl.this.array[current++];
            }
        }
        return new InnerIterator();
    }    
}
