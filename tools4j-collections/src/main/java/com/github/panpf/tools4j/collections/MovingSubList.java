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

package com.github.panpf.tools4j.collections;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

public class MovingSubList<E> extends AbstractList<E> implements RandomAccess {

    @NotNull
    private final List<E> list;

    public MovingSubList(@NotNull List<E> list) {
        this.list = list;
    }

    private int fromIndex = 0;
    private int _size = 0;

    public void move(int fromIndex, int toIndex) {
        int size = list.size();
        if (fromIndex < 0 || toIndex > size) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", size: " + size);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
        this.fromIndex = fromIndex;
        this._size = toIndex - fromIndex;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= _size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + _size);
        }

        return list.get(fromIndex + index);
    }

    @Override
    public int size() {
        return _size;
    }
}
