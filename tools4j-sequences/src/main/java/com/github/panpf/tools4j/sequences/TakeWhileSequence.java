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

/**
 * A sequence that returns values from the underlying [sequence] while the [predicate] function returns
 * `true`, and stops returning values once the function returns `false` for the next element.
 */
public class TakeWhileSequence<T> implements Sequence<T> {

    @NotNull
    private Sequence<T> sequence;
    @NotNull
    private Predicate<T> predicate;

    public TakeWhileSequence(@NotNull Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        this.sequence = sequence;
        this.predicate = predicate;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @NotNull
            Iterator<T> iterator = sequence.iterator();
            int nextState = -1; // -1 for unknown, 0 for done, 1 for continue
            @Nullable
            T nextItem = null;

            private void calcNext() {
                if (iterator.hasNext()) {
                    T item = iterator.next();
                    if (predicate.accept(item)) {
                        nextState = 1;
                        nextItem = item;
                        return;
                    }
                }
                nextState = 0;
            }

            @Override
            public T next() {
                if (nextState == -1)
                    calcNext(); // will change nextState
                if (nextState == 0)
                    throw new NoSuchElementException();
                T result = nextItem;

                // Clean next to avoid keeping reference on yielded instance
                nextItem = null;
                nextState = -1;
                return result;
            }

            @Override
            public boolean hasNext() {
                if (nextState == -1)
                    calcNext(); // will change nextState
                return nextState == 1;
            }

            @Override
            public void remove() {

            }
        };
    }
}
