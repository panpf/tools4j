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

import java.util.Iterator;

/**
 * A progression of values of type `Long`.
 */
public class LongProgression implements Iterable<Long> {

    private final long first;
    private final long last;
    private final long step;

    public LongProgression(long first, long last, long step) {
        if (step == 0) throw new IllegalArgumentException("Step must be non-zero");
        this.first = first;
        this.last = ProgressionUtil.getProgressionLastElement(first, last, step);
        this.step = step;
    }

    @NotNull
    public Iterator<Long> iterator() {
        return new LongProgressionIterator(first, last, this.step);
    }

    public boolean isEmpty() {
        return this.step > 0 ? this.first > this.last : this.first < this.last;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongProgression o1 = (LongProgression) o;
        if (this.isEmpty() && o1.isEmpty()) return true;
        return first == o1.first && last == o1.last && step == o1.step;
    }

    @Override
    public int hashCode() {
        return isEmpty() ? -1 : (int) (31 * (31 * (first ^ (first >>> 32)) + (last ^ (last >>> 32))) + (step ^ (step >>> 32)));
    }

    @NotNull
    @Override
    public String toString() {
        return this.step > 0 ? this.first + ".." + this.last + " step " + this.step : this.first + " downTo " + this.last + " step " + -this.step;
    }

    @NotNull
    public static LongProgression fromClosedRange(long rangeStart, long rangeEndInclusive, long step) {
        return new LongProgression(rangeStart, rangeEndInclusive, step);
    }

    public long getFirst() {
        return first;
    }

    public long getLast() {
        return last;
    }

    public long getStep() {
        return step;
    }
}
