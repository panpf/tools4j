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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class WindowedIterator<T> implements Iterator<List<T>> {

    @Nullable
    private final Iterator<T> iterator;
    private final int size;
    private final int step;
    private final boolean partialWindows;

    @Nullable
    private List<T> nextElement;
    private RingBuffer<T> cachedBuffer;

    public WindowedIterator(@Nullable Iterator<T> iterator, int size, int step, boolean partialWindows) {
        if (size <= 0 || step <= 0) {
            if (size != step){
                throw new IllegalArgumentException("Both size " + size + " and step " + step + " must be greater than zero.");
            } else {
                throw new IllegalArgumentException("size " + size + " must be greater than zero.");
            }
        }
        this.iterator = iterator;
        this.size = size;
        this.step = step;
        this.partialWindows = partialWindows;
        this.nextElement = calculateNext();
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    @NotNull
    @Override
    public List<T> next() {
        final List<T> nextElement = this.nextElement;
        if (nextElement == null) {
            throw new NoSuchElementException();
        } else {
            this.nextElement = calculateNext();
            return nextElement;
        }
    }

    @Nullable
    private List<T> calculateNext() {
        int bufferInitialCapacity = Math.min(size, 1024);
        int gap = step - size;
        if (gap >= 0) {
            ArrayList<T> buffer = new ArrayList<T>(bufferInitialCapacity);
            while (iterator != null && iterator.hasNext()) {
                buffer.add(iterator.next());
                if (buffer.size() == size) {
                    break;
                }
            }
            if (!buffer.isEmpty()) {
                if (buffer.size() == size) {
                    int skipCount = 0;
                    while (skipCount++ < gap && iterator.hasNext()) {
                        iterator.next();
                    }
                    return buffer;
                } else if(partialWindows){
                    return buffer;
                }
            }
        } else {
            RingBuffer<T> buffer;
            RingBuffer<T> cachedBuffer = this.cachedBuffer;
            if (cachedBuffer != null) {
                buffer = cachedBuffer;
            } else {
                RingBuffer<T> newBuffer = new RingBuffer<T>(bufferInitialCapacity);
                this.cachedBuffer = newBuffer;
                buffer = newBuffer;
            }
            while (iterator != null && iterator.hasNext()) {
                T e = iterator.next();
                buffer.add(e);
                if (buffer.isFull()) {
                    if (buffer.size() < size) {
                        RingBuffer<T> newBuffer  = buffer.expanded(size);
                        buffer = newBuffer;
                        this.cachedBuffer = newBuffer;
                        continue;
                    }

                    ArrayList<T> result = new ArrayList<T>(buffer);
                    buffer.removeFirst(step);
                    return result;
                }
            }
            if (partialWindows) {
                if (buffer.size() > step) {
                    ArrayList<T> result = new ArrayList<T>(buffer);
                    buffer.removeFirst(step);
                    return result;
                }
                if (!buffer.isEmpty()) {
                    ArrayList<T> result = new ArrayList<T>(buffer);
                    buffer.removeFirst(buffer.size());
                    return result;
                }
            }
        }
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
