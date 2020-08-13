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
 * Represents a range of floating point numbers.
 * Extends [ClosedRange] interface providing custom operation [lessThanOrEquals] for comparing values of range domain type.
 * <p>
 * This interface is implemented by floating point ranges returned by [Float.rangeTo] and [Double.rangeTo] operators to
 * achieve IEEE-754 comparison order instead of total order of floating point numbers.
 */
public abstract class ClosedFloatingPointRange<T extends Comparable<T>> implements ClosedRange<T> {

    @Override
    public boolean contains(@NotNull T value) {
        return lessThanOrEquals(getStart(), value) && lessThanOrEquals(value, getEndInclusive());
    }

    @Override
    public boolean isEmpty() {
        return !lessThanOrEquals(getStart(), getEndInclusive());
    }

    /**
     * Compares two values of range domain type and returns true if first is less than or equal to second.
     */
    public abstract boolean lessThanOrEquals(@NotNull T a, @NotNull T b);
}
