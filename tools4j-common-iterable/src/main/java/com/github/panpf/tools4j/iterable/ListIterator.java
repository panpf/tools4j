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

package com.github.panpf.tools4j.iterable;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * An iterator over a collection that supports indexed access.
 */
public interface ListIterator<T> extends Iterator<T> {
    // Query Operations
    @Override
    T next();

    @Override
    boolean hasNext();

    /**
     * Returns `true` if there are elements in the iteration before the current element.
     */
    public boolean hasPrevious();

    /**
     * Returns the previous element in the iteration and moves the cursor position backwards.
     */
    @NotNull
    public T previous();

    /**
     * Returns the index of the element that would be returned by a subsequent call to [next].
     */
    public int nextIndex();

    /**
     * Returns the index of the element that would be returned by a subsequent call to [previous].
     */
    public int previousIndex();
}