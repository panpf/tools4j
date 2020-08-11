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

package com.github.panpf.tools4j.iterable;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

public final class YearProgression extends DateProgression {

    public YearProgression(@NotNull Date first, @NotNull Date last, int step) {
        super(first, last, step);
    }

    @NotNull
    public Date nextDate(@NotNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.YEAR, this.getStep());
        return new Date(calendar.getTimeInMillis());
    }
}
