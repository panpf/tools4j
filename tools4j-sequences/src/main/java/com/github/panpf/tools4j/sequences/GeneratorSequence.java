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

import com.github.panpf.tools4j.common.InitialValue;
import com.github.panpf.tools4j.common.NextValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GeneratorSequence<T> implements Sequence<T> {

    @NotNull
    private final InitialValue<T> getInitialValue;
    @NotNull
    private final NextValue<T> getNextValue;

    public GeneratorSequence(@NotNull InitialValue<T> getInitialValue, @NotNull NextValue<T> getNextValue) {
        this.getInitialValue = getInitialValue;
        this.getNextValue = getNextValue;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Nullable
            private T nextItem = null;
            private int nextState = -2; // -2 for initial unknown, -1 for next unknown, 0 for done, 1 for continue

            private void calcNext() {
                if (nextState == -2) {
                    nextItem = getInitialValue.get();
                } else {
                    if (nextItem == null) {
                        throw new IllegalArgumentException("'nextItem' is null");
                    }
                    nextItem = getNextValue.next(nextItem);
                }
                nextState = nextItem == null ? 0 : 1;
            }

            @Override
            public T next() {
                if (nextState < 0)
                    calcNext();

                if (nextState == 0)
                    throw new NoSuchElementException();
                T result = nextItem;
                // Do not clean nextItem (to avoid keeping reference on yielded instance) -- need to keep state for getNextValue
                nextState = -1;
                return result;
            }

            @Override
            public boolean hasNext() {
                if (nextState < 0)
                    calcNext();
                return nextState == 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }
}
