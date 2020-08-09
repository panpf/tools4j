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

/**
 * A sequence that skips the values from the underlying [sequence] while the given [predicate] returns `true` and returns
 * all values after that.
 */
public class DropWhileSequence<T> implements Sequence<T> {

    @NotNull
    private Sequence<T> sequence;
    @NotNull
    private Predicate<T> predicate;

    public DropWhileSequence(@NotNull Sequence<T> sequence, @NotNull Predicate<T> predicate) {
        this.sequence = sequence;
        this.predicate = predicate;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @NotNull
            private Iterator<T> iterator = sequence.iterator();
            private int dropState = -1; // -1 for not dropping, 1 for nextItem, 0 for normal iteration
            @Nullable
            private T nextItem = null;

            private void drop() {
                while (iterator.hasNext()) {
                    T item = iterator.next();
                    if (!predicate.accept(item)) {
                        nextItem = item;
                        dropState = 1;
                        return;
                    }
                }
                dropState = 0;
            }

            @Override
            public T next() {
                if (dropState == -1)
                    drop();

                if (dropState == 1) {
                    T result = nextItem;
                    nextItem = null;
                    dropState = 0;
                    return result;
                }
                return iterator.next();
            }

            @Override
            public boolean hasNext() {
                if (dropState == -1)
                    drop();
                return dropState == 1 || iterator.hasNext();
            }

            @Override
            public void remove() {

            }
        };
    }
}
