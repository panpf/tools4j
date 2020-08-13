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

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

public class ConstrainedOnceIterable<T> implements Iterable<T> {

    private final AtomicReference<Iterable<T>> iterableRef;

    public ConstrainedOnceIterable(Iterable<T> sequence) {
        this.iterableRef = new AtomicReference<>(sequence);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        Iterable<T> iterable = iterableRef.getAndSet(null);
        if (iterable == null) {
            throw new IllegalStateException("This sequence can be consumed only once.");
        }
        return iterable.iterator();
    }
}
