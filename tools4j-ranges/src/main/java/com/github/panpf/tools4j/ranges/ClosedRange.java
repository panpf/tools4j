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

/**
 * Represents a range of values (for example, numbers or characters).
 * See the [Kotlin language documentation](http://kotlinlang.org/docs/reference/ranges.html) for more information.
 */
public interface ClosedRange<T extends Comparable<T>> {
    /**
     * The minimum value in the range.
     */
    @NotNull
    T getStart();

    /**
     * The maximum value in the range (inclusive).
     */
    @NotNull
    T getEndInclusive();

    /**
     * Checks whether the specified [value] belongs to the range.
     */
    boolean contains(@NotNull T value);

    /**
     * Checks whether the range is empty.
     */
    boolean isEmpty();
}
