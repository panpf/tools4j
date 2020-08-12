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

package com.github.panpf.tools4j.other;

import org.jetbrains.annotations.NotNull;

public class TotalTimeConfig {
    /**
     * level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    private final int level;
    @NotNull
    private final String divider;
    @NotNull
    private final String daySuffix;
    @NotNull
    private final String hourSuffix;
    @NotNull
    private final String minuteSuffix;
    @NotNull
    private final String secondSuffix;
    @NotNull
    private final String millisecondSuffix;

    public TotalTimeConfig(int level, @NotNull String divider, @NotNull String daySuffix, @NotNull String hourSuffix, @NotNull String minuteSuffix, @NotNull String secondSuffix, @NotNull String millisecondSuffix) {
        this.level = level;
        this.divider = divider;
        this.daySuffix = daySuffix;
        this.hourSuffix = hourSuffix;
        this.minuteSuffix = minuteSuffix;
        this.secondSuffix = secondSuffix;
        this.millisecondSuffix = millisecondSuffix;
    }

    public int getLevel() {
        return level;
    }

    @NotNull
    public String getDivider() {
        return divider;
    }

    @NotNull
    public String getDaySuffix() {
        return daySuffix;
    }

    @NotNull
    public String getHourSuffix() {
        return hourSuffix;
    }

    @NotNull
    public String getMinuteSuffix() {
        return minuteSuffix;
    }

    @NotNull
    public String getSecondSuffix() {
        return secondSuffix;
    }

    @NotNull
    public String getMillisecondSuffix() {
        return millisecondSuffix;
    }
}
