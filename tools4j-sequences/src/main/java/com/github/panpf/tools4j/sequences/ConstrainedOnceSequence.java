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
import java.util.concurrent.atomic.AtomicReference;

class ConstrainedOnceSequence<T> implements Sequence<T> {

    @NotNull
    private final AtomicReference<Sequence<T>> sequenceRef;

    ConstrainedOnceSequence(@NotNull Sequence<T> sequence) {
        this.sequenceRef = new AtomicReference<Sequence<T>>(sequence);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        Sequence<T> sequence = sequenceRef.getAndSet(null);
        if (sequence == null) {
            throw new IllegalStateException("This sequence can be consumed only once.");
        }
        return sequence.iterator();
    }
}
