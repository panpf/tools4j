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

package com.github.panpf.tools4j.ranges.date;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DateProgressionIterator implements Iterator<Date> {

    @NotNull
    private final DateProgression progression;
    @NotNull
    private final Date last;
    private final int step;

    @NotNull
    private Date next;
    private boolean hasNext;

    public DateProgressionIterator(@NotNull DateProgression progression, @NotNull Date first, @NotNull Date last, int step) {
        if (step == 0) throw new IllegalArgumentException("Step must be non-zero");
        this.progression = progression;
        this.last = last;
        this.step = step;
        this.hasNext = this.step > 0 ? first.compareTo(this.last) <= 0 : first.compareTo(this.last) >= 0;
        this.next = this.hasNext ? first : this.last;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    @NotNull
    public Date next() {
        if (!this.hasNext) {
            throw (new NoSuchElementException());
        } else {
            Date result = this.next;
            this.next = this.progression.nextDate(this.next);
            this.hasNext = this.step > 0 ? this.next.compareTo(this.last) <= 0 : (this.step < 0 && this.next.compareTo(this.last) >= 0);
            return result;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
