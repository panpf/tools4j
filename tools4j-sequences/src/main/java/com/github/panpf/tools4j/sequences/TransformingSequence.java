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

import com.github.panpf.tools4j.common.Transformer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A sequence which returns the results of applying the given [transformer] function to the values
 * in the underlying [sequence].
 */
public class TransformingSequence<T, R> implements Sequence<R> {

    @Nullable
    private final Sequence<T> sequence;
    @NotNull
    private final Transformer<T, R> transformer;

    public TransformingSequence(@Nullable Sequence<T> sequence, @NotNull Transformer<T, R> transformer) {
        this.sequence = sequence;
        this.transformer = transformer;
    }

    @NotNull
    @Override
    public Iterator<R> iterator() {
        return new Iterator<R>() {
            @Nullable
            private final Iterator<T> iterator = sequence != null ? sequence.iterator() : null;

            @Override
            public R next() {
                if (iterator != null) {
                    return transformer.transform(iterator.next());
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

    @NotNull
    public <E> Sequence<E> flatten(@NotNull Transformer<R, Iterator<E>> iterator) {
        return new FlatteningSequence<>(sequence, transformer, iterator);
    }
}
