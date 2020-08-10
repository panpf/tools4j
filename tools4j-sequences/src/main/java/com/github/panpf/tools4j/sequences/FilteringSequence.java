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

import com.github.panpf.tools4j.common.Predicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilteringSequence<T> implements Sequence<T> {

    @NotNull
    private Sequence<T> sequence;
    private boolean sendWhen;
    @NotNull
    private Predicate<T> predicate;

    /**
     * A sequence that returns the values from the underlying [sequence] that either match or do not match
     * the specified [predicate].
     *
     * @param sendWhen If `true`, values for which the predicate returns `true` are returned. Otherwise,
     *                 values for which the predicate returns `false` are returned
     */
    public FilteringSequence(@NotNull Sequence<T> sequence, boolean sendWhen, @NotNull Predicate<T> predicate) {
        this.sequence = sequence;
        this.sendWhen = sendWhen;
        this.predicate = predicate;
    }

    /**
     * A sequence that returns the values from the underlying [sequence] that either match or do not match
     * the specified [predicate].
     */
    public FilteringSequence(@NotNull Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        this(sequence, true, predicate);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @NotNull
            private final Iterator<T> iterator = sequence.iterator();
            private int nextState = -1; // -1 for unknown, 0 for done, 1 for continue
            @Nullable
            T nextItem = null;

            private void calcNext() {
                while (iterator.hasNext()) {
                    T item = iterator.next();
                    if (predicate.accept(item) == sendWhen) {
                        nextItem = item;
                        nextState = 1;
                        return;
                    }
                }
                nextState = 0;
            }

            @NotNull
            @Override
            public T next() {
                if (nextState == -1)
                    calcNext();
                if (nextState == 0)
                    throw new NoSuchElementException();
                T result = nextItem;
                nextItem = null;
                nextState = -1;
                if (result == null) {
                    throw new IllegalArgumentException("'result' is null");
                }
                return result;
            }

            @Override
            public void remove() {

            }

            @Override
            public boolean hasNext() {
                if (nextState == -1)
                    calcNext();
                return nextState == 1;
            }
        };
    }
}
