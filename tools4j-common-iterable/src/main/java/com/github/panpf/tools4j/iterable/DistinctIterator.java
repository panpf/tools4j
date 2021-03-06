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
import com.github.panpf.tools4j.iterable.AbstractIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Iterator;

public class DistinctIterator<T, K> extends AbstractIterator<T> {

    @Nullable
    private final Iterator<T> source;
    @NotNull
    private final Transformer<T, K> keySelector;
    @NotNull
    private final HashSet<K> observed = new HashSet<K>();

    public DistinctIterator(@Nullable Iterator<T> source, @NotNull Transformer<T, K> keySelector) {
        this.source = source;
        this.keySelector = keySelector;
    }

    @Override
    public void computeNext() {
        if (source != null) {
            while (source.hasNext()) {
                T next = source.next();
                K key = keySelector.transform(next);

                if (observed.add(key)) {
                    setNext(next);
                    return;
                }
            }
        }

        done();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
