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

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A sequence that returns at most [count] values from the underlying [sequence], and stops returning values
 * as soon as that count is reached.
 */
public class TakeSequence<T> implements Sequence<T>, DropTakeSequence<T> {

    @NotNull
    private final Sequence<T> sequence;
    private final int count;

    public TakeSequence(@NotNull Sequence<T> sequence, final int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Param 'count' is less than to zero.");
        }
        this.sequence = sequence;
        this.count = count;
    }

    @NotNull
    @Override
    public Sequence<T> drop(int n) {
        //noinspection unchecked
        return n >= count ? (Sequence<T>) Sequencex.emptySequence() : new SubSequence<>(sequence, n, count);
    }

    @NotNull
    @Override
    public Sequence<T> take(int n) {
        return n >= count ? this : new TakeSequence<>(sequence, n);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public void remove() {

            }

            int left = count;
            final Iterator<T> iterator = sequence.iterator();

            @Override
            public T next() {
                if (left == 0) throw new NoSuchElementException();
                left--;
                return iterator.next();
            }

            @Override
            public boolean hasNext() {
                return left > 0 && iterator.hasNext();
            }
        };
    }
}
