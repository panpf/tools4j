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

public class ClosedFloatRange extends ClosedFloatingPointRange<Float> {

    private final float start;
    private final float endInclusive;

    public ClosedFloatRange(float start, float endInclusive) {
        this.start = start;
        this.endInclusive = endInclusive;
    }

    @Override
    public boolean lessThanOrEquals(@NotNull Float a, @NotNull Float b) {
        return a <= b;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClosedFloatRange o1 = (ClosedFloatRange) o;
        if (this.isEmpty() && o1.isEmpty()) return true;
        return start == o1.start && endInclusive == o1.endInclusive;
    }

    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : 31 * ((Float) start).hashCode() + ((Float) endInclusive).hashCode();
    }

    @NotNull
    @Override
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