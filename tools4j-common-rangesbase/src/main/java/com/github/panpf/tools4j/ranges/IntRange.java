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

/**
 * A range of values of type `Integer`.
 */
public class IntRange extends IntProgression implements ClosedRange<Integer> {

    public IntRange(int start, int endInclusive) {
        super(start, endInclusive, 1);
    }

    @NotNull
    @Override
    public Integer getStart() {
        return getFirst();
    }

    @NotNull
    @Override
    public Integer getEndInclusive() {
        return getLast();
    }

    @Override
    public boolean contains(@NotNull Integer value) {
        return getFirst() <= value && value <= getLast();
    }

    public boolean isEmpty() {
        return getFirst() > getLast();
    }

    @Override
    public int hashCode() {
        return (this.isEmpty() ? -1 : 31 * (31 * this.getFirst() + this.getLast()));
    }

    @NotNull
    public String toString() {
        return this.getFirst() + ".." + this.getLast();
    }
}