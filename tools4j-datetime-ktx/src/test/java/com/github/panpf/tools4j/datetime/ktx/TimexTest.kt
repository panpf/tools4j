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
package com.github.panpf.tools4j.datetime.ktx

import com.github.panpf.tools4j.datetime.TotalTimeConfig
import org.junit.Assert
import org.junit.Test

class TimexTest {

    @Test
    fun testFormatTotalTime() {
        // millisecond
        Assert.assertEquals(0L.formatTotalTime(0, " ", "d", "h", "m", "s", "ms"), "0s")
        Assert.assertEquals((-10L).formatTotalTime(TotalTimeConfig(0, " ", "d", "h", "m", "s", "ms")), "0s")
        Assert.assertEquals((-10L).formatTotalTime(), "0s")
        Assert.assertEquals(590L.formatTotalTime(), "590ms")
        Assert.assertEquals(590L.formatTotalTime(1), "0s")

        // second
        Assert.assertEquals((1000L * 3).formatTotalTime(), "3s")
        Assert.assertEquals((1000L * 3 + 590).formatTotalTime(), "3s 590ms")
        Assert.assertEquals((1000L * 3 + 590).formatTotalTime(1), "3s")

        // minute
        Assert.assertEquals((1000L * 60 * 3).formatTotalTime(), "3m")
        Assert.assertEquals((1000L * 60 * 3 + 1000L * 23).formatTotalTime(), "3m 23s")
        Assert.assertEquals((1000L * 60 * 3 + 1000L * 23 + 467).formatTotalTime(), "3m 23s 467ms")
        Assert.assertEquals((1000L * 60 * 3 + 467).formatTotalTime(), "3m 467ms")
        Assert.assertEquals((1000L * 60 * 3 + 467).formatTotalTime(1), "3m")

        // hour
        Assert.assertEquals((1000L * 60 * 60).formatTotalTime(), "1h")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23).formatTotalTime(), "1h 23m")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23).formatTotalTime(), "1h 23m 23s")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 23).formatTotalTime(), "1h 23s")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatTotalTime(), "1h 23m 23s 467ms")
        Assert.assertEquals((1000L * 60 * 60 + 467).formatTotalTime(), "1h 467ms")
        Assert.assertEquals((1000L * 60 * 60 + 467).formatTotalTime(1), "1h")

        // day
        Assert.assertEquals((1000L * 60 * 60 * 24).formatTotalTime(), "1d")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23).formatTotalTime(), "1d 1h 23m")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23).formatTotalTime(), "1d 1h 23m 23s")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 23).formatTotalTime(), "1d 1h 23s")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatTotalTime(), "1d 1h 23m 23s 467ms")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 467).formatTotalTime(), "1d 1h 467ms")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 467).formatTotalTime(), "1d 467ms")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 467).formatTotalTime(1), "1d")

        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatTotalTime(), "1d 1h 23m 23s 467ms")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatTotalTime(1), "1d 1h 23m 23s")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatTotalTime(2), "1d 1h 23m")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatTotalTime(3), "1d 1h")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatTotalTime(4), "1d")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatTotalTime(5), "0d")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatTotalTime(6), "0d")
        Assert.assertEquals((0L).formatTotalTime(0), "0s")
        Assert.assertEquals((0L).formatTotalTime(1), "0s")
        Assert.assertEquals((0L).formatTotalTime(2), "0m")
        Assert.assertEquals((0L).formatTotalTime(3), "0h")
        Assert.assertEquals((0L).formatTotalTime(4), "0d")
        Assert.assertEquals((0L).formatTotalTime(5), "0d")
    }

    @Test
    fun testFormatShortTotalTime() {
        // millisecond
        Assert.assertEquals((0L).formatShortTotalTime(), "0s")
        Assert.assertEquals((590L).formatShortTotalTime(), "590ms")
        Assert.assertEquals((590L).formatShortTotalTime(1), "0s")

        // second
        Assert.assertEquals((1000L * 3).formatShortTotalTime(), "3s")
        Assert.assertEquals((1000L * 3 + 590).formatShortTotalTime(), "3s590ms")
        Assert.assertEquals((1000L * 3 + 590).formatShortTotalTime(1), "3s")

        // minute
        Assert.assertEquals((1000L * 60 * 3).formatShortTotalTime(), "3m")
        Assert.assertEquals((1000L * 60 * 3 + 1000L * 23).formatShortTotalTime(), "3m23s")
        Assert.assertEquals((1000L * 60 * 3 + 1000L * 23 + 467).formatShortTotalTime(), "3m23s467ms")
        Assert.assertEquals((1000L * 60 * 3 + 467).formatShortTotalTime(), "3m467ms")
        Assert.assertEquals((1000L * 60 * 3 + 467).formatShortTotalTime(1), "3m")

        // hour
        Assert.assertEquals((1000L * 60 * 60).formatShortTotalTime(), "1h")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23).formatShortTotalTime(), "1h23m")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23).formatShortTotalTime(), "1h23m23s")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 23).formatShortTotalTime(), "1h23s")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatShortTotalTime(), "1h23m23s467ms")
        Assert.assertEquals((1000L * 60 * 60 + 467).formatShortTotalTime(), "1h467ms")
        Assert.assertEquals((1000L * 60 * 60 + 467).formatShortTotalTime(1), "1h")

        // day
        Assert.assertEquals((1000L * 60 * 60 * 24).formatShortTotalTime(), "1d")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23).formatShortTotalTime(), "1d1h23m")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23).formatShortTotalTime(), "1d1h23m23s")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 23).formatShortTotalTime(), "1d1h23s")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatShortTotalTime(), "1d1h23m23s467ms")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 467).formatShortTotalTime(), "1d1h467ms")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 467).formatShortTotalTime(), "1d467ms")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 467).formatShortTotalTime(1), "1d")
    }

    @Test
    fun testFormatTotalTimeZH() {
        // millisecond
        Assert.assertEquals((0L).formatTotalTimeZH(), "0秒")
        Assert.assertEquals((590L).formatTotalTimeZH(), "590毫秒")
        Assert.assertEquals((590L).formatTotalTimeZH(1), "0秒")

        // second
        Assert.assertEquals((1000L * 3).formatTotalTimeZH(), "3秒")
        Assert.assertEquals((1000L * 3 + 590).formatTotalTimeZH(), "3秒 590毫秒")
        Assert.assertEquals((1000L * 3 + 590).formatTotalTimeZH(1), "3秒")

        // minute
        Assert.assertEquals((1000L * 60 * 3).formatTotalTimeZH(), "3分钟")
        Assert.assertEquals((1000L * 60 * 3 + 1000L * 23).formatTotalTimeZH(), "3分钟 23秒")
        Assert.assertEquals((1000L * 60 * 3 + 1000L * 23 + 467).formatTotalTimeZH(), "3分钟 23秒 467毫秒")
        Assert.assertEquals((1000L * 60 * 3 + 467).formatTotalTimeZH(), "3分钟 467毫秒")
        Assert.assertEquals((1000L * 60 * 3 + 467).formatTotalTimeZH(1), "3分钟")

        // hour
        Assert.assertEquals((1000L * 60 * 60).formatTotalTimeZH(), "1小时")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23).formatTotalTimeZH(), "1小时 23分钟")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23).formatTotalTimeZH(), "1小时 23分钟 23秒")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 23).formatTotalTimeZH(), "1小时 23秒")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatTotalTimeZH(), "1小时 23分钟 23秒 467毫秒")
        Assert.assertEquals((1000L * 60 * 60 + 467).formatTotalTimeZH(), "1小时 467毫秒")
        Assert.assertEquals((1000L * 60 * 60 + 467).formatTotalTimeZH(1), "1小时")

        // day
        Assert.assertEquals((1000L * 60 * 60 * 24).formatTotalTimeZH(), "1天")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23).formatTotalTimeZH(), "1天 1小时 23分钟")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23).formatTotalTimeZH(), "1天 1小时 23分钟 23秒")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 23).formatTotalTimeZH(), "1天 1小时 23秒")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatTotalTimeZH(), "1天 1小时 23分钟 23秒 467毫秒")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 467).formatTotalTimeZH(), "1天 1小时 467毫秒")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 467).formatTotalTimeZH(), "1天 467毫秒")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 467).formatTotalTimeZH(1), "1天")
    }

    @Test
    fun testFormatShortTotalTimeZH() {
        // millisecond
        Assert.assertEquals((0L).formatShortTotalTimeZH(), "0秒")
        Assert.assertEquals((590L).formatShortTotalTimeZH(), "590毫秒")
        Assert.assertEquals((590L).formatShortTotalTimeZH(1), "0秒")

        // second
        Assert.assertEquals((1000L * 3).formatShortTotalTimeZH(), "3秒")
        Assert.assertEquals((1000L * 3 + 590).formatShortTotalTimeZH(), "3秒590毫秒")
        Assert.assertEquals((1000L * 3 + 590).formatShortTotalTimeZH(1), "3秒")

        // minute
        Assert.assertEquals((1000L * 60 * 3).formatShortTotalTimeZH(), "3分钟")
        Assert.assertEquals((1000L * 60 * 3 + 1000L * 23).formatShortTotalTimeZH(), "3分钟23秒")
        Assert.assertEquals((1000L * 60 * 3 + 1000L * 23 + 467).formatShortTotalTimeZH(), "3分钟23秒467毫秒")
        Assert.assertEquals((1000L * 60 * 3 + 467).formatShortTotalTimeZH(), "3分钟467毫秒")
        Assert.assertEquals((1000L * 60 * 3 + 467).formatShortTotalTimeZH(1), "3分钟")

        // hour
        Assert.assertEquals((1000L * 60 * 60).formatShortTotalTimeZH(), "1小时")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23).formatShortTotalTimeZH(), "1小时23分钟")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23).formatShortTotalTimeZH(), "1小时23分钟23秒")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 23).formatShortTotalTimeZH(), "1小时23秒")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatShortTotalTimeZH(), "1小时23分钟23秒467毫秒")
        Assert.assertEquals((1000L * 60 * 60 + 467).formatShortTotalTimeZH(), "1小时467毫秒")
        Assert.assertEquals((1000L * 60 * 60 + 467).formatShortTotalTimeZH(1), "1小时")

        // day
        Assert.assertEquals((1000L * 60 * 60 * 24).formatShortTotalTimeZH(), "1天")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23).formatShortTotalTimeZH(), "1天1小时23分钟")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23).formatShortTotalTimeZH(), "1天1小时23分钟23秒")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 23).formatShortTotalTimeZH(), "1天1小时23秒")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 1000L * 60 * 23 + 1000L * 23 + 467).formatShortTotalTimeZH(), "1天1小时23分钟23秒467毫秒")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 1000L * 60 * 60 + 467).formatShortTotalTimeZH(), "1天1小时467毫秒")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 467).formatShortTotalTimeZH(), "1天467毫秒")
        Assert.assertEquals((1000L * 60 * 60 * 24 + 467).formatShortTotalTimeZH(1), "1天")
    }

    @Test
    fun testFormatDurationTime() {
        Assert.assertEquals((0L).formatDurationTime(), "00:00:00")
        Assert.assertEquals((1000L * 5).formatDurationTime(), "00:00:05")
        Assert.assertEquals((1000L * 59).formatDurationTime(), "00:00:59")
        Assert.assertEquals((1000L * 60).formatDurationTime(), "00:01:00")
        Assert.assertEquals((1000L * 60 + (1000L * 4)).formatDurationTime(), "00:01:04")
        Assert.assertEquals((1000L * 60 * 60).formatDurationTime(), "01:00:00")
        Assert.assertEquals((1000L * 60 * 60 + (1000L * 4)).formatDurationTime(), "01:00:04")
        Assert.assertEquals((1000L * 60 * 60 + 1000L * 60 * 18 + (1000L * 4)).formatDurationTime(), "01:18:04")
        Assert.assertEquals((1000L * 60 * 60 * 100 + 1000L * 60 * 18 + (1000L * 4)).formatDurationTime(), "100:18:04")
    }

    @Test
    fun testFormatShortDurationTime() {
        Assert.assertEquals((0L).formatShortDurationTime(), "00:00")
        Assert.assertEquals((1000L * 5).formatShortDurationTime(), "00:05")
        Assert.assertEquals((1000L * 59).formatShortDurationTime(), "00:59")
        Assert.assertEquals((1000L * 60).formatShortDurationTime(), "01:00")
        Assert.assertEquals((1000L * 60 + (1000L * 4)).formatShortDurationTime(), "01:04")
    }
}