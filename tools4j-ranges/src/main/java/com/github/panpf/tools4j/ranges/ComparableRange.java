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

package com.github.panpf.tools4j.ranges;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ComparableRange<T extends Comparable<T>> implements ClosedRange<T> {

    @NotNull
    private final T start;
    @NotNull
    private final T endInclusive;

    public ComparableRange(@NotNull T start, @NotNull T endInclusive) {
        this.start = start;
        this.endInclusive = endInclusive;
    }

    @NotNull
    @Override
    public T getStart() {
        return start;
    }

    @NotNull
    @Override
    public T getEndInclusive() {
        return endInclusive;
    }

    @Override
    public boolean contains(@NotNull T value) {
        return start.compareTo(value) <= 0 && value.compareTo(endInclusive) <= 0;
    }

    @Override
    public boolean isEmpty() {
        return start.compareTo(endInclusive) > 0;
    }

    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : 31 * start.hashCode() + endInclusive.hashCode();
    }

    @NotNull
    @Override
    public String toString() {
        return this.start + ".." + this.endInclusive;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparableRange<?> o1 = (ComparableRange<?>) o;
        if (this.isEmpty() && o1.isEmpty()) return true;
        return start.equals(o1.start) && endInclusive.equals(o1.endInclusive);
    }
}
