package com.github.panpf.tools4j.iterable;

import com.github.panpf.tools4j.common.DefaultValue;
import com.github.panpf.tools4j.common.IndexedValue;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class IndexingIterable<T> implements Iterable<IndexedValue<T>> {

    @NotNull
    private final DefaultValue<Iterator<T>> defaultValue;

    public IndexingIterable(@NotNull DefaultValue<Iterator<T>> defaultValue) {
        this.defaultValue = defaultValue;
    }

    @NotNull
    @Override
    public Iterator<IndexedValue<T>> iterator() {
        return new IndexingIterator<T>(defaultValue.get());
    }
}
