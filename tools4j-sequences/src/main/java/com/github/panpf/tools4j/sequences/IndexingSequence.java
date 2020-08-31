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

import java.util.Iterator;

/**
 * A sequence which combines values from the underlying [sequence] with their indices and returns them as
 * [IndexedValue] objects.
 */
public class IndexingSequence<T> implements Sequence<IndexedValue<T>> {

    @NotNull
    private final Sequence<T> sequence;

    public IndexingSequence(@NotNull Sequence<T> sequence) {
        this.sequence = sequence;
    }

    @NotNull
    @Override
    public Iterator<IndexedValue<T>> iterator() {
        return new Iterator<IndexedValue<T>>() {
            @NotNull
            private final Iterator<T> iterator = sequence.iterator();

            int index = 0;

            @Override
            public IndexedValue<T> next() {
                return new IndexedValue<>(index++, iterator.next());
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            public void remove() {
            }
        };
    }
}
