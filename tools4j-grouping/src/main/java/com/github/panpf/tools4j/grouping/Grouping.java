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

package com.github.panpf.tools4j.grouping;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Represents a source of elements with a [keyOf] function, which can be applied to each element to get its key.
 * <p>
 * A [Grouping] structure serves as an intermediate step in group-and-fold operations:
 * they group elements by their keys and then fold each group with some aggregating operation.
 * <p>
 * It is created by attaching Transformer[T, K] keySelector` function to a source of elements.
 * To get an instance of [Grouping] use one of `groupingBy` extension functions:
 * - [Iterable.groupingBy]
 * - [Sequence.groupingBy]
 * - [Array.groupingBy]
 * - [CharSequence.groupingBy]
 * <p>
 * For the list of group-and-fold operations available, see the [extension functions](#extension-functions) for `Grouping`.
 */
public interface Grouping<T, K> {
    /**
     * Returns an [Iterator] over the elements of the source of this grouping.
     */
    @NotNull
    Iterator<T> sourceIterator();

    /**
     * Extracts the key of an [element].
     */
    @NotNull
    K keyOf(@NotNull T element);
}