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

package com.github.panpf.tools4j.datetime;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimexTest {

    @Test
    public void testTotalTime() {
        // millisecond
        assertEquals(Timex.totalTime(0L, 0, " ", "d", "h", "m", "s", "ms"), "0s");
        assertEquals(Timex.totalTime(-10L, new TotalTimeConfig(0, " ", "d", "h", "m", "s", "ms")), "0s");
        assertEquals(Timex.totalTime(-10L), "0s");
        assertEquals(Timex.totalTime(590), "590ms");
        assertEquals(Timex.totalTime(590, 1), "0s");

        // second
        assertEquals(Timex.totalTime(1000 * 3), "3s");
        assertEquals(Timex.totalTime(1000 * 3 + 590), "3s 590ms");
        assertEquals(Timex.totalTime(1000 * 3 + 590, 1), "3s");

        // minute
        assertEquals(Timex.totalTime(1000 * 60 * 3), "3m");
        assertEquals(Timex.totalTime(1000 * 60 * 3 + (1000 * 23)), "3m 23s");
        assertEquals(Timex.totalTime(1000 * 60 * 3 + (1000 * 23) + 467), "3m 23s 467ms");
        assertEquals(Timex.totalTime(1000 * 60 * 3 + 467), "3m 467ms");
        assertEquals(Timex.totalTime(1000 * 60 * 3 + 467, 1), "3m");

        // hour
        assertEquals(Timex.totalTime(1000 * 60 * 60), "1h");
        assertEquals(Timex.totalTime(1000 * 60 * 60 + (1000 * 60 * 23)), "1h 23m");
        assertEquals(Timex.totalTime(1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23)), "1h 23m 23s");
        assertEquals(Timex.totalTime(1000 * 60 * 60 + (1000 * 23)), "1h 23s");
        assertEquals(Timex.totalTime(1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467), "1h 23m 23s 467ms");
        assertEquals(Timex.totalTime(1000 * 60 * 60 + 467), "1h 467ms");
        assertEquals(Timex.totalTime(1000 * 60 * 60 + 467, 1), "1h");

        // day
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24), "1d");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23)), "1d 1h 23m");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23)), "1d 1h 23m 23s");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 23)), "1d 1h 23s");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467), "1d 1h 23m 23s 467ms");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 467), "1d 1h 467ms");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 467), "1d 467ms");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 467, 1), "1d");
    }

    @Test
    public void testTotalTimeLevel() {
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467), "1d 1h 23m 23s 467ms");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467, 1), "1d 1h 23m 23s");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467, 2), "1d 1h 23m");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467, 3), "1d 1h");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467, 4), "1d");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467, 5), "0d");
        assertEquals(Timex.totalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467, 6), "0d");

        assertEquals(Timex.totalTime(0, 0), "0s");
        assertEquals(Timex.totalTime(0, 1), "0s");
        assertEquals(Timex.totalTime(0, 2), "0m");
        assertEquals(Timex.totalTime(0, 3), "0h");
        assertEquals(Timex.totalTime(0, 4), "0d");
        assertEquals(Timex.totalTime(0, 5), "0d");
    }

    @Test
    public void testShortTotalTime() {
        // millisecond
        assertEquals(Timex.shortTotalTime(0L), "0s");
        assertEquals(Timex.shortTotalTime(590), "590ms");
        assertEquals(Timex.shortTotalTime(590, 1), "0s");

        // second
        assertEquals(Timex.shortTotalTime(1000 * 3), "3s");
        assertEquals(Timex.shortTotalTime(1000 * 3 + 590), "3s590ms");
        assertEquals(Timex.shortTotalTime(1000 * 3 + 590, 1), "3s");

        // minute
        assertEquals(Timex.shortTotalTime(1000 * 60 * 3), "3m");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 3 + (1000 * 23)), "3m23s");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 3 + (1000 * 23) + 467), "3m23s467ms");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 3 + 467), "3m467ms");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 3 + 467, 1), "3m");

        // hour
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60), "1h");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 + (1000 * 60 * 23)), "1h23m");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23)), "1h23m23s");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 + (1000 * 23)), "1h23s");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467), "1h23m23s467ms");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 + 467), "1h467ms");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 + 467, 1), "1h");

        // day
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 * 24), "1d");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23)), "1d1h23m");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23)), "1d1h23m23s");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 23)), "1d1h23s");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467), "1d1h23m23s467ms");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 467), "1d1h467ms");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 * 24 + 467), "1d467ms");
        assertEquals(Timex.shortTotalTime(1000 * 60 * 60 * 24 + 467, 1), "1d");
    }

    @Test
    public void testTotalTimeZH() {
        // millisecond
        assertEquals(Timex.totalTimeZH(0L), "0秒");
        assertEquals(Timex.totalTimeZH(590), "590毫秒");
        assertEquals(Timex.totalTimeZH(590, 1), "0秒");

        // second
        assertEquals(Timex.totalTimeZH(1000 * 3), "3秒");
        assertEquals(Timex.totalTimeZH(1000 * 3 + 590), "3秒 590毫秒");
        assertEquals(Timex.totalTimeZH(1000 * 3 + 590, 1), "3秒");

        // minute
        assertEquals(Timex.totalTimeZH(1000 * 60 * 3), "3分钟");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 3 + (1000 * 23)), "3分钟 23秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 3 + (1000 * 23) + 467), "3分钟 23秒 467毫秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 3 + 467), "3分钟 467毫秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 3 + 467, 1), "3分钟");

        // hour
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60), "1小时");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 + (1000 * 60 * 23)), "1小时 23分钟");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23)), "1小时 23分钟 23秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 + (1000 * 23)), "1小时 23秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467), "1小时 23分钟 23秒 467毫秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 + 467), "1小时 467毫秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 + 467, 1), "1小时");

        // day
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 * 24), "1天");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23)), "1天 1小时 23分钟");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23)), "1天 1小时 23分钟 23秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 23)), "1天 1小时 23秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467), "1天 1小时 23分钟 23秒 467毫秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 467), "1天 1小时 467毫秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 * 24 + 467), "1天 467毫秒");
        assertEquals(Timex.totalTimeZH(1000 * 60 * 60 * 24 + 467, 1), "1天");
    }

    @Test
    public void testShortTotalTimeZH() {
        // millisecond
        assertEquals(Timex.shortTotalTimeZH(0L), "0秒");
        assertEquals(Timex.shortTotalTimeZH(590), "590毫秒");
        assertEquals(Timex.shortTotalTimeZH(590, 1), "0秒");

        // second
        assertEquals(Timex.shortTotalTimeZH(1000 * 3), "3秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 3 + 590), "3秒590毫秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 3 + 590, 1), "3秒");

        // minute
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 3), "3分钟");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 3 + (1000 * 23)), "3分钟23秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 3 + (1000 * 23) + 467), "3分钟23秒467毫秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 3 + 467), "3分钟467毫秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 3 + 467, 1), "3分钟");

        // hour
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60), "1小时");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 + (1000 * 60 * 23)), "1小时23分钟");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23)), "1小时23分钟23秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 + (1000 * 23)), "1小时23秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467), "1小时23分钟23秒467毫秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 + 467), "1小时467毫秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 + 467, 1), "1小时");

        // day
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 * 24), "1天");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23)), "1天1小时23分钟");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23)), "1天1小时23分钟23秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 23)), "1天1小时23秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + (1000 * 60 * 23) + (1000 * 23) + 467), "1天1小时23分钟23秒467毫秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 467), "1天1小时467毫秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 * 24 + 467), "1天467毫秒");
        assertEquals(Timex.shortTotalTimeZH(1000 * 60 * 60 * 24 + 467, 1), "1天");
    }

    @Test
    public void testDuration() {
        assertEquals(Timex.duration(0), "00:00:00");
        assertEquals(Timex.duration(1000 * 5), "00:00:05");
        assertEquals(Timex.duration(1000 * 59), "00:00:59");
        assertEquals(Timex.duration(1000 * 60), "00:01:00");
        assertEquals(Timex.duration((1000 * 60) + (1000 * 4)), "00:01:04");
        assertEquals(Timex.duration(1000 * 60 * 60), "01:00:00");
        assertEquals(Timex.duration((1000 * 60 * 60) + (1000 * 4)), "01:00:04");
        assertEquals(Timex.duration((1000 * 60 * 60) + (1000 * 60 * 18) + (1000 * 4)), "01:18:04");
        assertEquals(Timex.duration((1000 * 60 * 60 * 100) + (1000 * 60 * 18) + (1000 * 4)), "100:18:04");

        assertEquals(Timex.shortDuration(0), "00:00");
        assertEquals(Timex.shortDuration(1000 * 5), "00:05");
        assertEquals(Timex.shortDuration(1000 * 59), "00:59");
        assertEquals(Timex.shortDuration(1000 * 60), "01:00");
        assertEquals(Timex.shortDuration((1000 * 60) + (1000 * 4)), "01:04");
    }
}
