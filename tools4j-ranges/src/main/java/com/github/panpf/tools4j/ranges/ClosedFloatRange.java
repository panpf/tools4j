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

package com.github.panpf.tools4j.ranges;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("WeakerAccess")
public class ClosedFloatRange extends ClosedFloatingPointRange<Float> {

    private final float start;
    private final float endInclusive;

    public ClosedFloatRange(float start, float endInclusive) {
        this.start = start;
        this.endInclusive = endInclusive;
    }

    @Override
    public boolean contains(@NotNull Float value) {
        return start <= value && value <= endInclusive;
    }

    public boolean isEmpty() {
        return this.start > this.endInclusive;
    }

    @Override
    public boolean lessThanOrEquals(@NotNull Float a, @NotNull Float b) {
        return a <= b;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return other instanceof ClosedFloatRange && (this.isEmpty() && ((ClosedFloatRange) other).isEmpty() || this.start == ((ClosedFloatRange) other).start && this.endInclusive == ((ClosedFloatRange) other).endInclusive);
    }

    @Override
    public int hashCode() {
        return (int) (this.isEmpty() ? -1 : 31 * (31 * this.start + this.endInclusive));
    }

    @NotNull
    public String toString() {
        return this.start + ".." + this.endInclusive;
    }

    @NotNull
    @Override
    public Float getStart() {
        return this.start;
    }

    @NotNull
    @Override
    public Float getEndInclusive() {
        return this.endInclusive;
    }
}