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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator over a progression of values of type `Character`.
 */
@SuppressWarnings("WeakerAccess")
public class CharRangeIterator implements Iterator<Character> {
    private int step;

    private char finalElement;
    private char next;
    private boolean hasNext;

    public CharRangeIterator(char start, char endInclusive, int step) {
        if (step == 0) throw new IllegalArgumentException("Step must be non-zero");
        this.step = step;
        finalElement = (char) Rangex.getProgressionLastElement(start, endInclusive, step);
        hasNext = step > 0 ? start <= finalElement : start >= finalElement;
        next = hasNext ? start : finalElement;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Character next() {
        char value = next;
        if (value == finalElement) {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            hasNext = false;
        } else {
            next = (char) Math.max(Math.min(next + step, Character.MAX_VALUE), Character.MIN_VALUE);
        }
        return value;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
