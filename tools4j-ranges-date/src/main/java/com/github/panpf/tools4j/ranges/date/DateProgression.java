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

package com.github.panpf.tools4j.ranges.date;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Iterator;

public abstract class DateProgression implements Iterable<Date> {

    @NotNull
    private final Date first;
    @NotNull
    private final Date last;
    private final int step;

    public DateProgression(@NotNull Date first, @NotNull Date last, int step) {
        if (step == 0) throw new IllegalArgumentException("Step must be non-zero");
        this.first = first;
        this.last = last;
        this.step = step;
    }

    @NotNull
    public Iterator<Date> iterator() {
        return (new DateProgressionIterator(this, this.getFirst(), this.getLast(), this.step));
    }

    @NotNull
    public abstract Date nextDate(@NotNull Date var1);

    @NotNull
    public Date getFirst() {
        return this.first;
    }

    @NotNull
    public Date getLast() {
        return this.last;
    }

    public final int getStep() {
        return this.step;
    }
}
