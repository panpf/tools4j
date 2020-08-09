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

package com.github.panpf.tools4j.iterable;

import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;

@SuppressWarnings("WeakerAccess")
public class ArrayIntIterator extends IntIterator {

    private int index = 0;

    @Nullable
    private int[] elements;

    public ArrayIntIterator(@Nullable int[] elements) {
        this.elements = elements;
    }

    @Override
    public boolean hasNext() {
        return elements != null && index < elements.length;
    }

    @Override
    public Integer nextInt() {
        if (elements == null) throw new NoSuchElementException("elements is null");
        try {
            return elements[index++];
        } catch (ArrayIndexOutOfBoundsException e) {
            index -= 1;
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
