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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class MonthRange extends DateProgression implements ClosedRange<Date> {

    public MonthRange(@NotNull Date start, @NotNull Date endInclusive) {
        super(start, endInclusive, 1);
    }

    @NotNull
    public Date nextDate(@NotNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.MONTH, this.getStep());
        return new Date(calendar.getTimeInMillis());
    }

    @NotNull
    @Override
    public Date getStart() {
        return getFirst();
    }

    @NotNull
    @Override
    public Date getEndInclusive() {
        return getLast();
    }

    @Override
    public boolean contains(@NotNull Date value) {
        return getFirst().compareTo(value) <= 0 && value.compareTo(getLast()) <= 0;
    }

    public boolean isEmpty() {
        return getFirst().compareTo(getLast()) > 0;
    }

    @NotNull
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        return dateFormat.format(getFirst()) + ".." + dateFormat.format(getLast());
    }
}
