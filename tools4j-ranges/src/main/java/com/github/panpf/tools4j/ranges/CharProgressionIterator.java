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

import com.github.panpf.tools4j.iterable.CharIterator;

import java.util.NoSuchElementException;

/**
 * An iterator over a progression of values of type `Char`.
 */
public class CharProgressionIterator extends CharIterator {

    private final int step;
    private final char finalElement;

    private boolean hasNext;
    private char next;


    /**
     * An iterator over a progression of values of type `Char`.
     *
     * @param step the number by which the value is incremented on each step.
     */
    public CharProgressionIterator(char first, char last, int step) {
        this.step = step;
        finalElement = last;
        hasNext = step > 0 ? first <= last : first >= last;
        next = hasNext ? first : finalElement;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Character nextChar() {
        char value = next;
        if (value == finalElement) {
            if (!hasNext()) throw new NoSuchElementException();
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
