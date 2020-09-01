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

import com.github.panpf.tools4j.common.Transformer2;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A sequence which takes the values from two parallel underlying sequences, passes them to the given
 * [transform] function and returns the values returned by that function. The sequence stops returning
 * values as soon as one of the underlying sequences stops returning values.
 */
public class MergingSequence<T1, T2, V> implements Sequence<V> {

    @NotNull
    private final Sequence<T1> sequence1;
    @NotNull
    private final Sequence<T2> sequence2;
    @NotNull
    private final Transformer2<T1, T2, V> transform;

    public MergingSequence(@NotNull Sequence<T1> sequence1, @NotNull Sequence<T2> sequence2, @NotNull Transformer2<T1, T2, V> transform) {
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        this.transform = transform;
    }

    @NotNull
    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {

            @NotNull
            private final Iterator<T1> iterator1 = sequence1.iterator();
            @NotNull
            private final Iterator<T2> iterator2 = sequence2.iterator();

            @Override
            public V next() {
                return transform.transform(iterator1.next(), iterator2.next());
            }

            @Override
            public boolean hasNext() {
                return iterator1.hasNext() && iterator2.hasNext();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }
}
