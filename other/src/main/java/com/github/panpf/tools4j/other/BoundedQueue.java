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

package com.github.panpf.tools4j.other;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * This is a queue of limited length. When the queue is full, the new element will push the elements of the queue header out of the queue.
 */
public class BoundedQueue<E> implements Queue<E> {

    private final Queue<E> queue = new LinkedList<E>();

    private int maxSize;

    /**
     * Create a queue of limited length
     */
    public BoundedQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Get the max size
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Adjust max size
     */
    public void setMaxSize(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("Param 'maxSize' less than 1");
        }
        this.maxSize = maxSize;
        adjustSize(0);
    }

    private void adjustSize(int addSize) {
        if ((queue.size() + addSize) > maxSize) {
            for (int w = 0, size = ((queue.size() + addSize) - maxSize); w < size; w++) {
                try {
                    queue.poll();
                } catch (NoSuchElementException ignored) {
                }
            }
        }
    }

    /**
     * Is the queue full?
     */
    public boolean isFull() {
        return queue.size() >= maxSize;
    }

    /**
     * Add element, return true if added successfully
     */
    @Override
    public boolean add(E e) {
        adjustSize(1);
        return queue.add(e);
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        boolean result = true;
        for (E e : c) {
            result &= add(e);
        }
        return result;
    }

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return queue.removeAll(c);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return queue.containsAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return queue.retainAll(c);
    }

    @Override
    public E remove() {
        return queue.remove();
    }

    @Override
    public E poll() {
        return queue.poll();
    }

    @Override
    public E element() {
        return queue.element();
    }

    @Override
    public E peek() {
        return queue.peek();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return queue.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return queue.toArray(a);
    }

    @Override
    public void clear() {
        queue.clear();
    }
}