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

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Iterator;

@SuppressWarnings("WeakerAccess")
public abstract class DateRange implements Iterable<Date>, ClosedRange<Date> {

    @NotNull
    private final Date start;
    @NotNull
    private final Date endInclusive;
    private final int step;

    public DateRange(@NotNull Date start, @NotNull Date endInclusive, int step) {
        if (step == 0) throw new IllegalArgumentException("Step must be non-zero");
        this.start = start;
        this.endInclusive = endInclusive;
        this.step = step;
    }

    @NotNull
    public Iterator<Date> iterator() {
        return (new DateRangeIterator(this, this.getStart(), this.getEndInclusive(), this.step));
    }

    @Override
    public boolean contains(@NotNull Date value) {
        return this.step > 0 ? value.compareTo(this.getStart()) >= 0 && value.compareTo(this.getEndInclusive()) <= 0 : (this.step < 0 && (value.compareTo(this.getStart()) <= 0 && value.compareTo(this.getEndInclusive()) >= 0));
    }

    @Override
    public boolean isEmpty() {
        return this.step > 0 ? this.getStart().compareTo(this.getEndInclusive()) > 0 : (this.step >= 0 || this.getStart().compareTo(this.getEndInclusive()) < 0);
    }

    @NotNull
    public abstract Date nextDate(@NotNull Date var1);

    @NotNull
    public Date getStart() {
        return this.start;
    }

    @NotNull
    public Date getEndInclusive() {
        return this.endInclusive;
    }

    public final int getStep() {
        return this.step;
    }
}
