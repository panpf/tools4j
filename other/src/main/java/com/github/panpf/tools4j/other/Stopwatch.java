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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * The implementation of a stopwatch timer can not only implement a complete stopwatch program, but also can be used to record the time of each node of a time-consuming task.
 */
public final class Stopwatch {

    /**
     * Starting time
     */
    public final long startTime;

    /**
     * End Time
     */
    private long endTime;

    /**
     * Last CountLap
     */
    @Nullable
    private Stopwatch.CountLap lastCountLap;

    /**
     * Count lap history
     */
    @Nullable
    public final ArrayList<CountLap> historyList;

    /**
     * Create StopwatchTimer
     *
     * @param saveHistory If true, saves CountLap history
     */
    public Stopwatch(boolean saveHistory) {
        this.startTime = System.currentTimeMillis();
        this.historyList = saveHistory ? new ArrayList<CountLap>() : null;
    }

    /**
     * Create StopwatchTimer, CountLap history is not saved by default
     */
    public Stopwatch() {
        this(false);
    }

    /**
     * Count lap and return this CountLap information
     */
    @NotNull
    public final Stopwatch.CountLap countLap() {
        if (this.isEnded()) {
            throw new IllegalStateException("It has ended");
        } else {
            long currentTime = System.currentTimeMillis();
            Stopwatch.CountLap lastLap = this.lastCountLap;
            long distanceFromLastTime = lastLap != null ? currentTime - lastLap.time : currentTime - this.startTime;
            long distanceFromStartTime = currentTime - this.startTime;
            Stopwatch.CountLap newCountLap = new Stopwatch.CountLap(currentTime, distanceFromLastTime, distanceFromStartTime);

            ArrayList<CountLap> historyList = this.historyList;
            if (this.historyList != null) {
                historyList.add(newCountLap);
            }

            this.lastCountLap = newCountLap;
            return newCountLap;
        }
    }

    /**
     * Get end Time
     */
    public final long getEndTime() {
        return this.endTime;
    }

    /**
     * Get last CountLap
     */
    @Nullable
    public final Stopwatch.CountLap getLastCountLap() {
        return this.lastCountLap;
    }

    /**
     * Return true if it is ended. Cannot execute the countLap() method after the end
     */
    public final boolean isEnded() {
        return this.endTime != 0L;
    }

    /**
     * End and no longer available. Cannot execute the countLap() method after the end
     */
    public final void end() {
        if (!this.isEnded()) {
            this.endTime = System.currentTimeMillis();
        }
    }

    /**
     * Store count lap information
     */
    public static final class CountLap {
        /**
         * Counting time
         */
        public final long time;

        /**
         * Distance from last time
         */
        public final long distanceLastTime;

        /**
         * Distance to start time
         */
        public final long distanceStartTime;

        public CountLap(long time, long distanceLastTime, long distanceStartTime) {
            this.time = time;
            this.distanceLastTime = distanceLastTime;
            this.distanceStartTime = distanceStartTime;
        }
    }
}
