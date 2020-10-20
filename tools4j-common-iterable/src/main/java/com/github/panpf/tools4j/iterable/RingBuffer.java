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

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;

/**
 * Provides ring buffer implementation.
 * <p>
 * Buffer overflow is not allowed so [add] doesn't overwrite tail but raises an exception.
 */
public class RingBuffer<T> extends AbstractList<T> implements RandomAccess {

    @NotNull
    private final Object[] buffer;

    private final int capacity;
    private int startIndex = 0;
    private int size;

    public RingBuffer(@NotNull Object[] buffer, int filledSize) {
        this.buffer = buffer;
        if (filledSize < 0) {
            throw new IllegalArgumentException("ring buffer filled size should not be negative but it is " + filledSize);
        }
        if (filledSize > buffer.length) {
            throw new IllegalArgumentException("ring buffer filled size: " + filledSize + " cannot be larger than the buffer size: " + buffer.length);
        }
        this.capacity = buffer.length;
        this.size = filledSize;
    }

    public RingBuffer(int capacity) {
        this(new Object[capacity], 0);
    }

    /**
     * Add [element] to the buffer or fail with [IllegalStateException] if no free space available in the buffer
     */
    @Override
    public boolean add(T element) {
        if (isFull()) {
            throw new IllegalStateException("ring buffer is full");
        }

        buffer[forward(startIndex, size)] = element;
        size++;
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        //noinspection unchecked
        return (T) buffer[forward(startIndex, index)];
    }

    /**
     * Removes [n] first elements from the buffer or fails with [IllegalArgumentException] if not enough elements in the buffer to remove
     */
    public void removeFirst(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n shouldn't be negative but it is " + n);
        }
        if (n > size) {
            throw new IllegalArgumentException("n shouldn't be greater than the buffer size: n = " + n + ", size = " + size);
        }

        if (n > 0) {
            int start = startIndex;
            int end = forward(start, n);

            if (start > end) {
                Arrays.fill(buffer, start, capacity, null);
                Arrays.fill(buffer, 0, end, null);
            } else {
                Arrays.fill(buffer, start, end, null);
            }

            startIndex = end;
            size -= n;
        }
    }

    private int forward(int self, int n) {
        return (self + n) % capacity;
    }

    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Creates a new ring buffer with the capacity equal to the minimum of [maxCapacity] and 1.5 * [capacity].
     * The returned ring buffer contains the same elements as this ring buffer.
     */
    @NotNull
    public RingBuffer<T> expanded(int maxCapacity) {
        int newCapacity = Math.min(capacity + (capacity >> 1) + 1, maxCapacity);
        Object[] newBuffer = startIndex == 0 ? Arrays.copyOf(buffer, newCapacity) : toArray(new Object[newCapacity]);
        return new RingBuffer<T>(newBuffer, size);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new AbstractIterator<T>() {
            private int count = size;
            private int index = startIndex;

            @Override
            public void computeNext() {
                if (count == 0) {
                    done();
                } else {
                    //noinspection unchecked
                    setNext((T) buffer[index]);
                    index = forward(index, 1);
                    count--;
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }

    @NotNull
    @Override
    @SuppressWarnings("TypeParameterHidesVisibleType")
    public <T> T[] toArray(@NotNull T[] array) {
        T[] result = array.length < this.size ? Arrays.copyOf(array, this.size) : array;

        int size = this.size;

        int widx = 0;
        int idx = startIndex;

        while (widx < size && idx < capacity) {
            //noinspection unchecked
            result[widx] = (T) buffer[idx];
            widx++;
            idx++;
        }

        idx = 0;
        while (widx < size) {
            //noinspection unchecked
            result[widx] = (T) buffer[idx];
            widx++;
            idx++;
        }
        if (result.length > this.size) result[this.size] = null;

        return result;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return toArray(new Object[size]);
    }
}
