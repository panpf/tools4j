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

import com.github.panpf.tools4j.iterable.IterableUtil;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator over a progression of values of type `Int`.
 */
public class IntRangeIterator implements Iterator<Integer> {

    private final int step;

    private final int finalElement;
    private int next;
    private boolean hasNext;

    public IntRangeIterator(int start, int endInclusive, int step) {
        if (step == 0) throw new IllegalArgumentException("Step must be non-zero");
        this.step = step;
        this.finalElement = IterableUtil.getProgressionLastElement(start, endInclusive, step);
        this.hasNext = step > 0 ? start <= finalElement : start >= finalElement;
        this.next = hasNext ? start : finalElement;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Integer next() {
        int value = next;
        if (value == finalElement) {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            hasNext = false;
        } else {
            next += step;
        }
        return value;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
