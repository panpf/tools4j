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

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A sequence that skips the specified number of values from the underlying [sequence] and returns
 * all values after that.
 */
public class DropSequence<T> implements Sequence<T>, DropTakeSequence<T> {

    @NotNull
    private final Sequence<T> sequence;
    private final int count;

    public DropSequence(@NotNull Sequence<T> sequence, final int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Param 'count' is less than to zero.");
        }
        this.sequence = sequence;
        this.count = count;
    }

    @NotNull
    @Override
    public Sequence<T> drop(int n) {
        return new DropSequence<T>(sequence, count + n);
    }

    @NotNull
    @Override
    public Sequence<T> take(int n) {
        return new SubSequence<T>(sequence, count, count + n);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private final Iterator<T> iterator = sequence.iterator();
            private int left = count;

            // Shouldn't be called from constructor to avoid premature iteration
            private void drop() {
                while (left > 0 && iterator.hasNext()) {
                    iterator.next();
                    left--;
                }
            }

            @Override
            public T next() {
                drop();
                return iterator.next();
            }

            @Override
            public boolean hasNext() {
                drop();
                return iterator.hasNext();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }
}
