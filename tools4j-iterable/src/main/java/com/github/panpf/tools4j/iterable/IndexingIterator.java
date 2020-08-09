package com.github.panpf.tools4j.iterable;

import com.github.panpf.tools4j.common.IndexedValue;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

@SuppressWarnings("WeakerAccess")
public class IndexingIterator<T> implements Iterator<IndexedValue<T>> {

    @NotNull
    private Iterator<T> iterator;
    private int index = 0;

    public IndexingIterator(@NotNull Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public IndexedValue<T> next() {
        return new IndexedValue<>(index++, iterator.next());
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
