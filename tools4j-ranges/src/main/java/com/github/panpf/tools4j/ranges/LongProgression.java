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

import com.github.panpf.tools4j.iterable.IterableUtil;
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
        this.last = IterableUtil.getProgressionLastElement(first, last, step);
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
    public boolean equals(@Nullable Object other) {
        return other instanceof LongProgression && (
                this.isEmpty() && ((LongProgression) other).isEmpty()
                        || this.first == ((LongProgression) other).first
                        && this.last == ((LongProgression) other).last
                        && this.step == ((LongProgression) other).step);
    }

    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : (int) (31 * (31 * this.first + this.last) + this.step);
    }

    @NotNull
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
