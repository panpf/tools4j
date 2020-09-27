/*
 * Copyright (C) 2020 panpf <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.panpf.tools4j.sequences;

import com.github.panpf.tools4j.common.IndexedValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A sequence which combines values from the underlying [sequence] with their indices and returns them as
 * [IndexedValue] objects.
 */
public class IndexingSequence<T> implements Sequence<IndexedValue<T>> {

    @Nullable
    private final Sequence<T> sequence;

    public IndexingSequence(@Nullable Sequence<T> sequence) {
        this.sequence = sequence;
    }

    @NotNull
    @Override
    public Iterator<IndexedValue<T>> iterator() {
        return new Iterator<IndexedValue<T>>() {
            @Nullable
            private final Iterator<T> iterator = sequence != null ? sequence.iterator() : null;

            int index = 0;

            @Override
            public IndexedValue<T> next() {
                if (iterator != null) {
                    return new IndexedValue<T>(index++, iterator.next());
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public boolean hasNext() {
                return iterator != null && iterator.hasNext();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }
}
