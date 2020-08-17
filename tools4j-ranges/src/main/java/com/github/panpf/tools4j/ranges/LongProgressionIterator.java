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

package com.github.panpf.tools4j.ranges;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator over a progression of values of type `Long`.
 */
public class LongProgressionIterator implements Iterator<Long> {

    private final long step;
    private final long finalElement;

    private boolean hasNext;
    private long next;


    /**
     * An iterator over a progression of values of type `Long`.
     *
     * @param step the number by which the value is incremented on each step.
     */
    public LongProgressionIterator(long first, long last, long step) {
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
    public final Long next() {
        long value = next;
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
