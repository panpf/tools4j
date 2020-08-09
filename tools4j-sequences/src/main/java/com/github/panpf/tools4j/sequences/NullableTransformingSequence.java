/*
 * Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>
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

import com.github.panpf.tools4j.common.NullableTransformer;
import com.github.panpf.tools4j.common.Transformer;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A sequence which returns the results of applying the given [transformer] function to the values
 * in the underlying [sequence].
 */
public class NullableTransformingSequence<T, R> implements Sequence<R> {

    @NotNull
    private Sequence<T> sequence;
    @NotNull
    private NullableTransformer<T, R> transformer;

    public NullableTransformingSequence(@NotNull Sequence<T> sequence, @NotNull NullableTransformer<T, R> transformer) {
        this.sequence = sequence;
        this.transformer = transformer;
    }

    @NotNull
    @Override
    public Iterator<R> iterator() {
        return new Iterator<R>() {
            @NotNull
            private Iterator<T> iterator = sequence.iterator();

            @Override
            public R next() {
                return transformer.transform(iterator.next());
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public void remove() {

            }
        };
    }

    public <E> Sequence<E> flatten(@NotNull Transformer<R, Iterator<E>> iterator) {
        return new NullableFlatteningSequence<>(sequence, transformer, iterator);
    }
}
