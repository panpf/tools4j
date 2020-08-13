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

package com.github.panpf.tools4j.sequences;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A sequence that returns values through its iterator. The values are evaluated lazily, and the sequence
 * is potentially infinite.
 * <p>
 * Sequences can be iterated multiple times, however some sequence implementations might constrain themselves
 * to be iterated only once. That is mentioned specifically in their documentation (e.g. [generateSequence] overload).
 * The latter sequences throw an exception on an attempt to iterate them the second time.
 * <p>
 * Sequence operations, like [Sequence.map], [Sequence.filter] etc, generally preserve that property of a sequence, and
 * again it's documented for an operation if it doesn't.
 */
public interface Sequence<T> {
    /**
     * Returns an [Iterator] that returns the values from the sequence.
     * <p>
     * Throws an exception if the sequence is constrained to be iterated once and `iterator` is invoked the second time.
     */
    @NotNull
    Iterator<T> iterator();
}
