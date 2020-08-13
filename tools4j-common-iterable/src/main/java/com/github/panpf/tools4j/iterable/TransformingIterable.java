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

package com.github.panpf.tools4j.iterable;

import com.github.panpf.tools4j.common.Transformer;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A iterable which returns the results of applying the given [transformer] function to the values
 * in the underlying [Iterable].
 */
public class TransformingIterable<T, R> implements Iterable<R> {

    @NotNull
    private final Iterable<T> iterable;
    @NotNull
    private final Transformer<T, R> transformer;

    public TransformingIterable(@NotNull Iterable<T> iterable, @NotNull Transformer<T, R> transformer) {
        this.iterable = iterable;
        this.transformer = transformer;
    }

    @NotNull
    @Override
    public Iterator<R> iterator() {
        return new Iterator<R>() {
            @NotNull
            private final Iterator<T> iterator = iterable.iterator();

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
}
