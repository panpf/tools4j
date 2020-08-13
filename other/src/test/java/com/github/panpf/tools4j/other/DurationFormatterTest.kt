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
package com.github.panpf.tools4j.other

import org.junit.Assert
import org.junit.Test

class DurationFormatterTest {

    @Test
    fun testFormatTotalTime() {
        // millisecond
        Assert.assertEquals(DurationFormatter.formatTotalTime(0L, 0, " ", "d", "h", "m", "s", "ms"), "0s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(-10L, com.github.panpf.tools4j.other.TotalTimeConfig(0, " ", "d", "h", "m", "s", "ms")), "0s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(-10L), "0s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(590), "590ms")
        Assert.assertEquals(DurationFormatter.formatTotalTime(590, 1), "0s")

        // second
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 3), "3s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 3 + 590), "3s 590ms")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 3 + 590, 1), "3s")

        // minute
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 3), "3m")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 3 + 1000 * 23), "3m 23s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 3 + 1000 * 23 + 467), "3m 23s 467ms")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 3 + 467), "3m 467ms")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 3 + 467, 1), "3m")

        // hour
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60), "1h")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 + 1000 * 60 * 23), "1h 23m")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23), "1h 23m 23s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 + 1000 * 23), "1h 23s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467), "1h 23m 23s 467ms")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 + 467), "1h 467ms")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 + 467, 1), "1h")

        // day
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24), "1d")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23), "1d 1h 23m")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23), "1d 1h 23m 23s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 23), "1d 1h 23s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467), "1d 1h 23m 23s 467ms")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 467), "1d 1h 467ms")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 467), "1d 467ms")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 467, 1), "1d")

        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467), "1d 1h 23m 23s 467ms")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467, 1), "1d 1h 23m 23s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467, 2), "1d 1h 23m")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467, 3), "1d 1h")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467, 4), "1d")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467, 5), "0d")
        Assert.assertEquals(DurationFormatter.formatTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467, 6), "0d")
        Assert.assertEquals(DurationFormatter.formatTotalTime(0, 0), "0s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(0, 1), "0s")
        Assert.assertEquals(DurationFormatter.formatTotalTime(0, 2), "0m")
        Assert.assertEquals(DurationFormatter.formatTotalTime(0, 3), "0h")
        Assert.assertEquals(DurationFormatter.formatTotalTime(0, 4), "0d")
        Assert.assertEquals(DurationFormatter.formatTotalTime(0, 5), "0d")
    }

    @Test
    fun testFormatShortTotalTime() {
        // millisecond
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(0L), "0s")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(590), "590ms")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(590, 1), "0s")

        // second
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 3), "3s")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 3 + 590), "3s590ms")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 3 + 590, 1), "3s")

        // minute
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 3), "3m")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 3 + 1000 * 23), "3m23s")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 3 + 1000 * 23 + 467), "3m23s467ms")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 3 + 467), "3m467ms")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 3 + 467, 1), "3m")

        // hour
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60), "1h")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 + 1000 * 60 * 23), "1h23m")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23), "1h23m23s")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 + 1000 * 23), "1h23s")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467), "1h23m23s467ms")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 + 467), "1h467ms")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 + 467, 1), "1h")

        // day
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 * 24), "1d")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23), "1d1h23m")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23), "1d1h23m23s")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 23), "1d1h23s")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467), "1d1h23m23s467ms")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 467), "1d1h467ms")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 * 24 + 467), "1d467ms")
        Assert.assertEquals(DurationFormatter.formatShortTotalTime(1000 * 60 * 60 * 24 + 467, 1), "1d")
    }

    @Test
    fun testFormatTotalTimeZH() {
        // millisecond
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(0L), "0秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(590), "590毫秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(590, 1), "0秒")

        // second
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 3), "3秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 3 + 590), "3秒 590毫秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 3 + 590, 1), "3秒")

        // minute
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 3), "3分钟")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 3 + 1000 * 23), "3分钟 23秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 3 + 1000 * 23 + 467), "3分钟 23秒 467毫秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 3 + 467), "3分钟 467毫秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 3 + 467, 1), "3分钟")

        // hour
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60), "1小时")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 + 1000 * 60 * 23), "1小时 23分钟")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23), "1小时 23分钟 23秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 + 1000 * 23), "1小时 23秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467), "1小时 23分钟 23秒 467毫秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 + 467), "1小时 467毫秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 + 467, 1), "1小时")

        // day
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 * 24), "1天")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23), "1天 1小时 23分钟")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23), "1天 1小时 23分钟 23秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 23), "1天 1小时 23秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467), "1天 1小时 23分钟 23秒 467毫秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 467), "1天 1小时 467毫秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 * 24 + 467), "1天 467毫秒")
        Assert.assertEquals(DurationFormatter.formatTotalTimeZH(1000 * 60 * 60 * 24 + 467, 1), "1天")
    }

    @Test
    fun testFormatShortTotalTimeZH() {
        // millisecond
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(0L), "0秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(590), "590毫秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(590, 1), "0秒")

        // second
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 3), "3秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 3 + 590), "3秒590毫秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 3 + 590, 1), "3秒")

        // minute
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 3), "3分钟")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 3 + 1000 * 23), "3分钟23秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 3 + 1000 * 23 + 467), "3分钟23秒467毫秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 3 + 467), "3分钟467毫秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 3 + 467, 1), "3分钟")

        // hour
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60), "1小时")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 + 1000 * 60 * 23), "1小时23分钟")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23), "1小时23分钟23秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 + 1000 * 23), "1小时23秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467), "1小时23分钟23秒467毫秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 + 467), "1小时467毫秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 + 467, 1), "1小时")

        // day
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 * 24), "1天")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23), "1天1小时23分钟")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23), "1天1小时23分钟23秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 23), "1天1小时23秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 1000 * 60 * 23 + 1000 * 23 + 467), "1天1小时23分钟23秒467毫秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 * 24 + 1000 * 60 * 60 + 467), "1天1小时467毫秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 * 24 + 467), "1天467毫秒")
        Assert.assertEquals(DurationFormatter.formatShortTotalTimeZH(1000 * 60 * 60 * 24 + 467, 1), "1天")
    }

    @Test
    fun testFormatDurationTime() {
        Assert.assertEquals(DurationFormatter.formatDurationTime(0), "00:00:00")
        Assert.assertEquals(DurationFormatter.formatDurationTime(1000 * 5.toLong()), "00:00:05")
        Assert.assertEquals(DurationFormatter.formatDurationTime(1000 * 59.toLong()), "00:00:59")
        Assert.assertEquals(DurationFormatter.formatDurationTime(1000 * 60.toLong()), "00:01:00")
        Assert.assertEquals(DurationFormatter.formatDurationTime(1000 * 60 + (1000 * 4).toLong()), "00:01:04")
        Assert.assertEquals(DurationFormatter.formatDurationTime(1000 * 60 * 60.toLong()), "01:00:00")
        Assert.assertEquals(DurationFormatter.formatDurationTime(1000 * 60 * 60 + (1000 * 4).toLong()), "01:00:04")
        Assert.assertEquals(DurationFormatter.formatDurationTime(1000 * 60 * 60 + 1000 * 60 * 18 + (1000 * 4).toLong()), "01:18:04")
        Assert.assertEquals(DurationFormatter.formatDurationTime(1000 * 60 * 60 * 100 + 1000 * 60 * 18 + (1000 * 4).toLong()), "100:18:04")
    }

    @Test
    fun testFormatShortDurationTime() {
        Assert.assertEquals(DurationFormatter.formatShortDurationTime(0), "00:00")
        Assert.assertEquals(DurationFormatter.formatShortDurationTime(1000 * 5.toLong()), "00:05")
        Assert.assertEquals(DurationFormatter.formatShortDurationTime(1000 * 59.toLong()), "00:59")
        Assert.assertEquals(DurationFormatter.formatShortDurationTime(1000 * 60.toLong()), "01:00")
        Assert.assertEquals(DurationFormatter.formatShortDurationTime(1000 * 60 + (1000 * 4).toLong()), "01:04")
    }
}