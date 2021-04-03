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

package com.github.panpf.tools4j.date.ktx

import com.github.panpf.tools4j.date.Datex
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DatexTest {

    @Test
    fun testCreateCalendar() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        assertNotNull(Date().createCalendar())
        assertNotNull(Date().createCalendar(Locale.CANADA))
        assertNotNull(Date().createCalendar(defaultFirstDayOfWeek))
        assertNotNull(Date().createCalendar(defaultFirstDayOfWeek, Locale.CANADA))

        assertNotNull(System.currentTimeMillis().createCalendar())
        assertNotNull(System.currentTimeMillis().createCalendar(Locale.CANADA))
        assertNotNull(System.currentTimeMillis().createCalendar(defaultFirstDayOfWeek))
        assertNotNull(System.currentTimeMillis().createCalendar(defaultFirstDayOfWeek, Locale.CANADA))
    }

    @Test
    fun testToAndFormat() {
        val time = System.currentTimeMillis()
        assertEquals(time.toDate().time, time)

        assertEquals("2018".toDate(SimpleDateFormat("yyyy")).format(SimpleDateFormat("yyyy")), "2018")
        assertEquals("2018".toDate("yyyy").format("yyyy"), "2018")
        assertEquals("2018".toDate("yyyy", Locale.getDefault()).format("yyyy", Locale.getDefault()), "2018")

        assertEquals("2018".toDate(SimpleDateFormat("yyyy")).time.formatDate(SimpleDateFormat("yyyy")), "2018")
        assertEquals("2018".toDate("yyyy").time.formatDate("yyyy"), "2018")
        assertEquals("2018".toDate("yyyy", Locale.getDefault()).time.formatDate("yyyy", Locale.getDefault()), "2018")
    }

    @Test
    fun testFormatDuration() {
        val oneSecondMilliseconds = 1000.toLong()
        val oneMinuteMilliseconds = oneSecondMilliseconds * 60
        val oneHourMilliseconds = oneMinuteMilliseconds * 60
        val oneDayMilliseconds = oneHourMilliseconds * 24

        assertEquals("0毫秒", 0L.formatDuration("%d?天%h?小时%m?分钟%s?秒%ms?毫秒"))
        assertEquals("0毫秒", 0L.formatDuration("%d?天%h?小时%m?分钟%s?秒%ms毫秒"))
        assertEquals("0秒", 0L.formatDuration("%d?天%h?小时%m?分钟%s秒%ms?毫秒"))
        assertEquals("0分钟", 0L.formatDuration("%d?天%h?小时%m分钟%s?秒%ms?毫秒"))
        assertEquals("0小时", 0L.formatDuration("%d?天%h小时%m?分钟%s?秒%ms?毫秒"))
        assertEquals("0天", 0L.formatDuration("%d天%h?小时%m?分钟%s?秒%ms?毫秒"))
        assertEquals("0秒", 0L.formatDuration("%d?天%h?小时%m?分钟%s?秒"))
        assertEquals("0秒", 0L.formatDuration("%d?天%h?小时%m?分钟%s秒"))
        assertEquals("0分钟", 0L.formatDuration("%d?天%h?小时%m?分钟"))
        assertEquals("0分钟", 0L.formatDuration("%d?天%h?小时%m分钟"))
        assertEquals("0小时", 0L.formatDuration("%d?天%h?小时"))
        assertEquals("0小时", 0L.formatDuration("%d?天%h小时"))
        assertEquals("0天", 0L.formatDuration("%d?天"))
        assertEquals("0天", 0L.formatDuration("%d天"))

        assertEquals("0天0小时0分钟0秒0毫秒", 0L.formatDuration("%d天%h小时%m分钟%s秒%ms毫秒"))
        assertEquals("0天0小时0分钟0秒", 0L.formatDuration("%d天%h小时%m分钟%s秒"))
        assertEquals("0天0小时0分钟", 0L.formatDuration("%d天%h小时%m分钟"))
        assertEquals("0天0小时", 0L.formatDuration("%d天%h小时"))
        assertEquals("0天", 0L.formatDuration("%d天"))

        assertEquals("0小时0分钟0秒0毫秒", 0L.formatDuration("%d?天%h小时%m分钟%s秒%ms毫秒"))
        assertEquals("0天0分钟0秒0毫秒", 0L.formatDuration("%d天%h?小时%m分钟%s秒%ms毫秒"))
        assertEquals("0天0小时0秒0毫秒", 0L.formatDuration("%d天%h小时%m?分钟%s秒%ms毫秒"))
        assertEquals("0天0小时0分钟0毫秒", 0L.formatDuration("%d天%h小时%m分钟%s?秒%ms毫秒"))
        assertEquals("0天0小时0分钟0秒", 0L.formatDuration("%d天%h小时%m分钟%s秒%ms?毫秒"))

        assertEquals("0分钟0秒0毫秒", 0L.formatDuration("%d?天%h?小时%m分钟%s秒%ms毫秒"))
        assertEquals("0天0秒0毫秒", 0L.formatDuration("%d天%h?小时%m?分钟%s秒%ms毫秒"))
        assertEquals("0天0小时0毫秒", 0L.formatDuration("%d天%h小时%m?分钟%s?秒%ms毫秒"))
        assertEquals("0天0小时0分钟", 0L.formatDuration("%d天%h小时%m分钟%s?秒%ms?毫秒"))

        assertEquals("0秒0毫秒", 0L.formatDuration("%d?天%h?小时%m?分钟%s秒%ms毫秒"))
        assertEquals("0天0毫秒", 0L.formatDuration("%d天%h?小时%m?分钟%s?秒%ms毫秒"))
        assertEquals("0天0小时", 0L.formatDuration("%d天%h小时%m?分钟%s?秒%ms?毫秒"))

        assertEquals("0毫秒", 0L.formatDuration("%d?天%h?小时%m?分钟%s?秒%ms毫秒"))
        assertEquals("0天", 0L.formatDuration("%d天%h?小时%m?分钟%s?秒%ms?毫秒"))

        assertEquals("0 天 0 小时 0 分钟 0 秒 0 毫秒", 0L.formatDuration("%d 天 %h 小时 %m 分钟 %s 秒 %ms 毫秒"))

        // millisecond
        assertEquals("0s", 0L.formatDuration("%d?d %h?h %m?m %s?s"))
        assertEquals("0s", (-10L).formatDuration("%d?d %h?h %m?m %s?s"))
        assertEquals("590ms", 590L.formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("0s", 590L.formatDuration("%d?d %h?h %m?m %s?s"))
        assertEquals("1590ms", 1590L.formatDuration("%ms?ms"))
        assertEquals("1s 90ms", 1090L.formatDuration("%ss %ms?ms"))
        assertEquals("1s 090ms", 1090L.formatDuration("%ss %MS?ms"))

        // second
        assertEquals("3s", (oneSecondMilliseconds * 3).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("3s 590ms", (oneSecondMilliseconds * 3 + 590).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("3s", (oneSecondMilliseconds * 3 + 590).formatDuration("%d?d %h?h %m?m %s?s"))

        // minute
        assertEquals("3m", (oneMinuteMilliseconds * 3).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("3m 23s", (oneMinuteMilliseconds * 3 + oneSecondMilliseconds * 23).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("3m 23s 467ms", (oneMinuteMilliseconds * 3 + oneSecondMilliseconds * 23 + 467).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("3m 467ms", (oneMinuteMilliseconds * 3 + 467).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("3m", (oneMinuteMilliseconds * 3 + 467).formatDuration("%d?d %h?h %m?m %s?s"))

        // hour
        assertEquals("1h", (oneHourMilliseconds).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1h 23m", (oneHourMilliseconds + oneMinuteMilliseconds * 23).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1h 23m 23s", (oneHourMilliseconds + oneMinuteMilliseconds * 23 + oneSecondMilliseconds * 23).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1h 23s", (oneHourMilliseconds + oneSecondMilliseconds * 23).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1h 23m 23s 467ms", (oneHourMilliseconds + oneMinuteMilliseconds * 23 + oneSecondMilliseconds * 23 + 467).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1h 467ms", (oneHourMilliseconds + 467).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1h", (oneHourMilliseconds + 467).formatDuration("%d?d %h?h %m?m %s?s"))

        // day
        assertEquals("1d", (oneDayMilliseconds).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1d 1h 23m", (oneDayMilliseconds + oneHourMilliseconds + oneMinuteMilliseconds * 23).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1d 1h 23m 23s", (oneDayMilliseconds + oneHourMilliseconds + oneMinuteMilliseconds * 23 + oneSecondMilliseconds * 23).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1d 1h 23s", (oneDayMilliseconds + oneHourMilliseconds + oneSecondMilliseconds * 23).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1d 1h 23m 23s 467ms", (oneDayMilliseconds + oneHourMilliseconds + oneMinuteMilliseconds * 23 + oneSecondMilliseconds * 23 + 467).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1d 1h 467ms", (oneDayMilliseconds + oneHourMilliseconds + 467).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1d 467ms", (oneDayMilliseconds + 467).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1d", (oneDayMilliseconds + 467).formatDuration("%d?d %h?h %m?m %s?s"))

        assertEquals("1d 1h 23m 23s 467ms", (oneDayMilliseconds + oneHourMilliseconds + oneMinuteMilliseconds * 23 + oneSecondMilliseconds * 23 + 467).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("1d 1h 23m 23s", (oneDayMilliseconds + oneHourMilliseconds + oneMinuteMilliseconds * 23 + oneSecondMilliseconds * 23 + 467).formatDuration("%d?d %h?h %m?m %s?s"))
        assertEquals("1d 1h 23m", (oneDayMilliseconds + oneHourMilliseconds + oneMinuteMilliseconds * 23 + oneSecondMilliseconds * 23 + 467).formatDuration("%d?d %h?h %m?m"))
        assertEquals("1d 1h", (oneDayMilliseconds + oneHourMilliseconds + oneMinuteMilliseconds * 23 + oneSecondMilliseconds * 23 + 467).formatDuration("%d?d %h?h"))
        assertEquals("1d", (oneDayMilliseconds + oneHourMilliseconds + oneMinuteMilliseconds * 23 + oneSecondMilliseconds * 23 + 467).formatDuration("%d?d"))
        assertEquals("0ms", (0L).formatDuration("%d?d %h?h %m?m %s?s %ms?ms"))
        assertEquals("0s", (0L).formatDuration("%d?d %h?h %m?m %s?s"))
        assertEquals("0m", (0L).formatDuration("%d?d %h?h %m?m"))
        assertEquals("0h", (0L).formatDuration("%d?d %h?h"))
        assertEquals("0d", (0L).formatDuration("%d?d"))

        assertEquals("00:00:00", (0L).formatDuration("%H:%M:%S"))
        assertEquals("00:00:05", (oneSecondMilliseconds * 5.toLong()).formatDuration("%H:%M:%S"))
        assertEquals("00:00:59", (oneSecondMilliseconds * 59.toLong()).formatDuration("%H:%M:%S"))
        assertEquals("00:01:00", (oneMinuteMilliseconds).formatDuration("%H:%M:%S"))
        assertEquals("00:01:04", (oneMinuteMilliseconds + (oneSecondMilliseconds * 4)).formatDuration("%H:%M:%S"))
        assertEquals("01:00:00", (oneHourMilliseconds).formatDuration("%H:%M:%S"))
        assertEquals("01:00:04", (oneHourMilliseconds + (oneSecondMilliseconds * 4)).formatDuration("%H:%M:%S"))
        assertEquals("01:18:04", (oneHourMilliseconds + oneMinuteMilliseconds * 18 + (oneSecondMilliseconds * 4)).formatDuration("%H:%M:%S"))
        assertEquals("100:18:04", (oneHourMilliseconds * 100 + oneMinuteMilliseconds * 18 + (oneSecondMilliseconds * 4)).formatDuration("%H:%M:%S"))
    }

    @Test
    fun testGet() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        assertEquals("2016-02-29 15:59:34 897".toDate(Datex.yMdHmsS).getCalendarField(Calendar.MILLISECOND, defaultFirstDayOfWeek, Locale.getDefault()), 897)
        assertEquals("2016-02-29 15:59:34 897".toDate(Datex.yMdHmsS).getCalendarField(Calendar.MILLISECOND, defaultFirstDayOfWeek), 897)
        assertEquals("2016-02-29 15:59:34 897".toDate(Datex.yMdHmsS).getCalendarField(Calendar.MILLISECOND, Locale.getDefault()), 897)
        assertEquals("2016-02-29 15:59:34 897".toDate(Datex.yMdHmsS).getCalendarField(Calendar.MILLISECOND), 897)

        assertEquals("2016-02-29 15:59:34 897".toDate(Datex.yMdHmsS).time.getCalendarField(Calendar.MILLISECOND, defaultFirstDayOfWeek, Locale.getDefault()), 897)
        assertEquals("2016-02-29 15:59:34 897".toDate(Datex.yMdHmsS).time.getCalendarField(Calendar.MILLISECOND, defaultFirstDayOfWeek), 897)
        assertEquals("2016-02-29 15:59:34 897".toDate(Datex.yMdHmsS).time.getCalendarField(Calendar.MILLISECOND, Locale.getDefault()), 897)
        assertEquals("2016-02-29 15:59:34 897".toDate(Datex.yMdHmsS).time.getCalendarField(Calendar.MILLISECOND), 897)
    }

    @Test
    fun testAdd() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        assertEquals("2016-02-29".toDate(Datex.yMd).createCalendar().addToDate(Calendar.YEAR, 1).format(Datex.yMd), "2017-02-28")

        assertEquals("2016-02-29".toDate(Datex.yMd).addCalendarField(Calendar.YEAR, 1).format(Datex.yMd), "2017-02-28")
        assertEquals("2016-02-29".toDate(Datex.yMd).addCalendarField(Calendar.YEAR, 1, defaultFirstDayOfWeek).format(Datex.yMd), "2017-02-28")
        assertEquals("2016-02-29".toDate(Datex.yMd).addCalendarField(Calendar.YEAR, 1, Locale.getDefault()).format(Datex.yMd), "2017-02-28")
        assertEquals("2016-02-29".toDate(Datex.yMd).addCalendarField(Calendar.YEAR, 1, defaultFirstDayOfWeek, Locale.getDefault()).format(Datex.yMd), "2017-02-28")

        assertEquals("2016-02-29".toDate(Datex.yMd).time.addCalendarField(Calendar.YEAR, 1).format(Datex.yMd), "2017-02-28")
        assertEquals("2016-02-29".toDate(Datex.yMd).time.addCalendarField(Calendar.YEAR, 1, defaultFirstDayOfWeek).format(Datex.yMd), "2017-02-28")
        assertEquals("2016-02-29".toDate(Datex.yMd).time.addCalendarField(Calendar.YEAR, 1, Locale.getDefault()).format(Datex.yMd), "2017-02-28")
        assertEquals("2016-02-29".toDate(Datex.yMd).time.addCalendarField(Calendar.YEAR, 1, defaultFirstDayOfWeek, Locale.getDefault()).format(Datex.yMd), "2017-02-28")
    }

    @Test
    fun testSame() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        val eraNoSameCalendar1 = Calendar.getInstance()
        val eraNoSameCalendar2 = Calendar.getInstance()
        eraNoSameCalendar2.set(Calendar.ERA, 0)

        assertTrue("2018".toDate(Datex.y).createCalendar().isSameYear("2018".toDate(Datex.y).createCalendar()))
        assertFalse("2018".toDate(Datex.y).isSameYear("2017".toDate(Datex.y)))
        assertFalse("2018".toDate(Datex.y).isSameYear("2019".toDate(Datex.y), Locale.getDefault()))
        assertFalse("2018".toDate(Datex.y).time.isSameYear("2017".toDate(Datex.y).time))
        assertFalse("2018".toDate(Datex.y).time.isSameYear("2019".toDate(Datex.y).time, Locale.getDefault()))
        assertFalse(eraNoSameCalendar1.isSameYear(eraNoSameCalendar2))

        assertTrue("2018-08".toDate(Datex.yM).createCalendar().isSameMonth("2018-08".toDate(Datex.yM).createCalendar()))
        assertTrue("2018-08".toDate(Datex.yM).isSameMonth("2018-08-01".toDate(Datex.yMd), Locale.getDefault()))
        assertTrue("2018-08".toDate(Datex.yM).isSameMonth("2018-08-31".toDate(Datex.yMd)))
        assertFalse("2018-08".toDate(Datex.yM).isSameMonth("2018-09".toDate(Datex.yM)))
        assertFalse("2018-08".toDate(Datex.yM).isSameMonth("2017-08".toDate(Datex.yM)))
        assertFalse("2018-08".toDate(Datex.yM).isSameMonth("2019-08".toDate(Datex.yM)))
        assertTrue("2018-08".toDate(Datex.yM).time.isSameMonth("2018-08-01".toDate(Datex.yMd).time, Locale.getDefault()))
        assertTrue("2018-08".toDate(Datex.yM).time.isSameMonth("2018-08-31".toDate(Datex.yMd).time))
        assertFalse("2018-08".toDate(Datex.yM).time.isSameMonth("2018-09".toDate(Datex.yM).time))
        assertFalse("2018-08".toDate(Datex.yM).time.isSameMonth("2017-08".toDate(Datex.yM).time))
        assertFalse("2018-08".toDate(Datex.yM).time.isSameMonth("2019-08".toDate(Datex.yM).time))
        assertFalse(eraNoSameCalendar1.isSameMonth(eraNoSameCalendar2))

        assertTrue("2018-08".toDate(Datex.yM).createCalendar().isSameMonthOfYear("2019-08".toDate(Datex.yM).createCalendar()))
        assertTrue("2018-08".toDate(Datex.yM).isSameMonthOfYear("2019-08".toDate(Datex.yM), Locale.getDefault()))
        assertFalse("2018-08".toDate(Datex.yM).isSameMonthOfYear("2019-07".toDate(Datex.yM)))
        assertTrue("2018-08".toDate(Datex.yM).time.isSameMonthOfYear("2019-08".toDate(Datex.yM).time, Locale.getDefault()))
        assertFalse("2018-08".toDate(Datex.yM).time.isSameMonthOfYear("2019-07".toDate(Datex.yM).time))
        assertFalse(eraNoSameCalendar1.isSameMonthOfYear(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDate(Datex.yMd).createCalendar().isSameWeek("2018-08-05".toDate(Datex.yMd).createCalendar()))
        assertTrue("2018-12-31".toDate(Datex.yMd).isSameWeek("2019-01-01".toDate(Datex.yMd), defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-08-31".toDate(Datex.yMd).isSameWeek("2018-09-01".toDate(Datex.yMd), defaultFirstDayOfWeek))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameWeek("2018-08-11".toDate(Datex.yMd), Locale.getDefault()))
        assertTrue("2019-01-01".toDate(Datex.yMd).isSameWeek("2018-12-31".toDate(Datex.yMd)))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameWeek("2018-08-04".toDate(Datex.yMd)))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameWeek("2018-08-12".toDate(Datex.yMd)))
        assertFalse("2019-12-31".toDate(Datex.yMd).isSameWeek("2018-12-31".toDate(Datex.yMd)))
        assertFalse("2018-12-31".toDate(Datex.yMd).isSameWeek("2019-01-06".toDate(Datex.yMd)))
        assertTrue("2018-12-31".toDate(Datex.yMd).time.isSameWeek("2019-01-01".toDate(Datex.yMd).time, defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-08-31".toDate(Datex.yMd).time.isSameWeek("2018-09-01".toDate(Datex.yMd).time, defaultFirstDayOfWeek))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameWeek("2018-08-11".toDate(Datex.yMd).time, Locale.getDefault()))
        assertTrue("2019-01-01".toDate(Datex.yMd).time.isSameWeek("2018-12-31".toDate(Datex.yMd).time))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameWeek("2018-08-04".toDate(Datex.yMd).time))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameWeek("2018-08-12".toDate(Datex.yMd).time))
        assertFalse("2019-12-31".toDate(Datex.yMd).time.isSameWeek("2018-12-31".toDate(Datex.yMd).time))
        assertFalse("2018-12-31".toDate(Datex.yMd).time.isSameWeek("2019-01-06".toDate(Datex.yMd).time))
        assertFalse(eraNoSameCalendar1.isSameWeek(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDate(Datex.yMd).createCalendar().isSameWeekOfYear("2019-08-04".toDate(Datex.yMd).createCalendar()))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameWeekOfYear("2019-08-10".toDate(Datex.yMd), defaultFirstDayOfWeek, Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameWeekOfYear("2019-08-03".toDate(Datex.yMd), defaultFirstDayOfWeek))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameWeekOfYear("2019-08-11".toDate(Datex.yMd), Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameWeekOfYear("2019-08-11".toDate(Datex.yMd)))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameWeekOfYear("2019-08-10".toDate(Datex.yMd).time, defaultFirstDayOfWeek, Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameWeekOfYear("2019-08-03".toDate(Datex.yMd).time, defaultFirstDayOfWeek))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameWeekOfYear("2019-08-11".toDate(Datex.yMd).time, Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameWeekOfYear("2019-08-11".toDate(Datex.yMd).time))
        assertFalse(eraNoSameCalendar1.isSameWeekOfYear(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDate(Datex.yMd).createCalendar().isSameWeekOfMonth("2018-07-08".toDate(Datex.yMd).createCalendar()))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameWeekOfMonth("2018-07-14".toDate(Datex.yMd), defaultFirstDayOfWeek, Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameWeekOfMonth("2018-07-07".toDate(Datex.yMd), defaultFirstDayOfWeek))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameWeekOfMonth("2018-07-15".toDate(Datex.yMd), Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameWeekOfMonth("2018-07-15".toDate(Datex.yMd)))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameWeekOfMonth("2018-07-14".toDate(Datex.yMd).time, defaultFirstDayOfWeek, Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameWeekOfMonth("2018-07-07".toDate(Datex.yMd).time, defaultFirstDayOfWeek))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameWeekOfMonth("2018-07-15".toDate(Datex.yMd).time, Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameWeekOfMonth("2018-07-15".toDate(Datex.yMd).time))
        assertFalse(eraNoSameCalendar1.isSameWeekOfMonth(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDate(Datex.yMd).createCalendar().isSameDay("2018-08-07 01".toDate(Datex.yMdH).createCalendar()))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameDay("2018-08-07 23".toDate(Datex.yMdH), Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameDay("2018-08-08".toDate(Datex.yMd)))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameDay("2018-08-06".toDate(Datex.yMd)))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameDay("2019-08-07".toDate(Datex.yMd)))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameDay("2018-09-07".toDate(Datex.yMd)))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameDay("2018-08-07 23".toDate(Datex.yMdH).time, Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameDay("2018-08-08".toDate(Datex.yMd).time))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameDay("2018-08-06".toDate(Datex.yMd).time))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameDay("2019-08-07".toDate(Datex.yMd).time))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameDay("2018-09-07".toDate(Datex.yMd).time))
        assertFalse(eraNoSameCalendar1.isSameDay(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDate(Datex.yMd).createCalendar().isSameDayOfYear("2016-08-06".toDate(Datex.yMd).createCalendar()))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameDayOfYear("2016-08-06 01".toDate(Datex.yMdH), Locale.getDefault()))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameDayOfYear("2016-08-06 23".toDate(Datex.yMdH)))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameDayOfYear("2016-08-07".toDate(Datex.yMd)))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameDayOfYear("2016-08-06 01".toDate(Datex.yMdH).time, Locale.getDefault()))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameDayOfYear("2016-08-06 23".toDate(Datex.yMdH).time))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameDayOfYear("2016-08-07".toDate(Datex.yMd).time))
        assertFalse(eraNoSameCalendar1.isSameDayOfYear(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDate(Datex.yMd).createCalendar().isSameDayOfMonth("2018-07-07".toDate(Datex.yMd).createCalendar()))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameDayOfMonth("2019-08-07".toDate(Datex.yMd), Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameDayOfMonth("2018-08-06".toDate(Datex.yMd)))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameDayOfMonth("2019-08-07".toDate(Datex.yMd).time, Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameDayOfMonth("2018-08-06".toDate(Datex.yMd).time))
        assertFalse(eraNoSameCalendar1.isSameDayOfMonth(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDate(Datex.yMd).createCalendar().isSameDayOfWeek("2018-08-14".toDate(Datex.yMd).createCalendar()))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameDayOfWeek("2018-08-21".toDate(Datex.yMd), defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameDayOfWeek("2018-08-14".toDate(Datex.yMd), defaultFirstDayOfWeek))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameDayOfWeek("2018-08-08".toDate(Datex.yMd), Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameDayOfWeek("2018-08-15".toDate(Datex.yMd)))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameDayOfWeek("2018-08-21".toDate(Datex.yMd).time, defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameDayOfWeek("2018-08-14".toDate(Datex.yMd).time, defaultFirstDayOfWeek))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameDayOfWeek("2018-08-08".toDate(Datex.yMd).time, Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameDayOfWeek("2018-08-15".toDate(Datex.yMd).time))
        assertFalse(eraNoSameCalendar1.isSameDayOfWeek(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDate(Datex.yMd).createCalendar().isSameDayOfWeekInMonth("2018-08-01".toDate(Datex.yMd).createCalendar()))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameDayOfWeekInMonth("2018-08-01".toDate(Datex.yMd), defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameDayOfWeekInMonth("2018-08-01".toDate(Datex.yMd), defaultFirstDayOfWeek))
        assertTrue("2018-08-07".toDate(Datex.yMd).isSameDayOfWeekInMonth("2018-08-07".toDate(Datex.yMd), Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).isSameDayOfWeekInMonth("2018-07-08".toDate(Datex.yMd)))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameDayOfWeekInMonth("2018-08-01".toDate(Datex.yMd).time, defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameDayOfWeekInMonth("2018-08-01".toDate(Datex.yMd).time, defaultFirstDayOfWeek))
        assertTrue("2018-08-07".toDate(Datex.yMd).time.isSameDayOfWeekInMonth("2018-08-07".toDate(Datex.yMd).time, Locale.getDefault()))
        assertFalse("2018-08-07".toDate(Datex.yMd).time.isSameDayOfWeekInMonth("2018-07-08".toDate(Datex.yMd).time))
        assertFalse(eraNoSameCalendar1.isSameDayOfWeekInMonth(eraNoSameCalendar2))

        assertTrue("2018-08-07 13".toDate(Datex.yMdH).createCalendar().isSameHour("2018-08-07 13:01".toDate(Datex.yMdHm).createCalendar()))
        assertTrue("2018-08-07 13".toDate(Datex.yMdH).isSameHour("2018-08-07 13:59".toDate(Datex.yMdHm), Locale.getDefault()))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).isSameHour("2018-08-07 14:00".toDate(Datex.yMdHm)))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).isSameHour("2018-08-07 12:59".toDate(Datex.yMdHm)))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).isSameHour("2019-08-07 12:59".toDate(Datex.yMdHm)))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).isSameHour("2018-09-07 12:59".toDate(Datex.yMdHm)))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).isSameHour("2018-08-06 12:59".toDate(Datex.yMdHm)))
        assertTrue("2018-08-07 13".toDate(Datex.yMdH).time.isSameHour("2018-08-07 13:59".toDate(Datex.yMdHm).time, Locale.getDefault()))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).time.isSameHour("2018-08-07 14:00".toDate(Datex.yMdHm).time))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).time.isSameHour("2018-08-07 12:59".toDate(Datex.yMdHm).time))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).time.isSameHour("2019-08-07 12:59".toDate(Datex.yMdHm).time))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).time.isSameHour("2018-09-07 12:59".toDate(Datex.yMdHm).time))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).time.isSameHour("2018-08-06 12:59".toDate(Datex.yMdHm).time))
        assertFalse(eraNoSameCalendar1.isSameHour(eraNoSameCalendar2))

        assertTrue("2018-08-07 13".toDate(Datex.yMdH).createCalendar().isSameHourOf24H("2018-08-06 13".toDate(Datex.yMdH).createCalendar()))
        assertTrue("2018-08-07 13".toDate(Datex.yMdH).isSameHourOf24H("2018-08-06 13".toDate(Datex.yMdH), Locale.getDefault()))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).isSameHourOf24H("2018-08-06 14".toDate(Datex.yMdH)))
        assertTrue("2018-08-07 13".toDate(Datex.yMdH).time.isSameHourOf24H("2018-08-06 13".toDate(Datex.yMdH).time, Locale.getDefault()))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).time.isSameHourOf24H("2018-08-06 14".toDate(Datex.yMdH).time))
        assertFalse(eraNoSameCalendar1.isSameHourOf24H(eraNoSameCalendar2))

        assertTrue("2018-08-07 13".toDate(Datex.yMdH).createCalendar().isSameHourOf12H("2018-08-07 01".toDate(Datex.yMdH).createCalendar()))
        assertTrue("2018-08-07 13".toDate(Datex.yMdH).isSameHourOf12H("2018-08-07 01".toDate(Datex.yMdH), Locale.getDefault()))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).isSameHourOf12H("2018-08-07 02".toDate(Datex.yMdH)))
        assertTrue("2018-08-07 13".toDate(Datex.yMdH).time.isSameHourOf12H("2018-08-07 01".toDate(Datex.yMdH).time, Locale.getDefault()))
        assertFalse("2018-08-07 13".toDate(Datex.yMdH).time.isSameHourOf12H("2018-08-07 02".toDate(Datex.yMdH).time))
        assertFalse(eraNoSameCalendar1.isSameHourOf12H(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01".toDate(Datex.yMdHm).createCalendar().isSameMinute("2018-08-07 13:01:01".toDate(Datex.yMdHms).createCalendar()))
        assertTrue("2018-08-07 13:01".toDate(Datex.yMdHm).isSameMinute("2018-08-07 13:01:59".toDate(Datex.yMdHms), Locale.getDefault()))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).isSameMinute("2018-08-07 13:02:00".toDate(Datex.yMdHms)))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).isSameMinute("2018-08-07 13:00:59".toDate(Datex.yMdHms)))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).isSameMinute("2019-08-07 13:00:59".toDate(Datex.yMdHms)))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).isSameMinute("2018-09-07 13:00:59".toDate(Datex.yMdHms)))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).isSameMinute("2018-08-06 13:00:59".toDate(Datex.yMdHms)))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).isSameMinute("2018-08-07 14:00:59".toDate(Datex.yMdHms)))
        assertTrue("2018-08-07 13:01".toDate(Datex.yMdHm).time.isSameMinute("2018-08-07 13:01:59".toDate(Datex.yMdHms).time, Locale.getDefault()))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).time.isSameMinute("2018-08-07 13:02:00".toDate(Datex.yMdHms).time))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).time.isSameMinute("2018-08-07 13:00:59".toDate(Datex.yMdHms).time))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).time.isSameMinute("2019-08-07 13:00:59".toDate(Datex.yMdHms).time))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).time.isSameMinute("2018-09-07 13:00:59".toDate(Datex.yMdHms).time))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).time.isSameMinute("2018-08-06 13:00:59".toDate(Datex.yMdHms).time))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).time.isSameMinute("2018-08-07 14:00:59".toDate(Datex.yMdHms).time))
        assertFalse(eraNoSameCalendar1.isSameMinute(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01".toDate(Datex.yMdHm).createCalendar().isSameMinuteOfHour("2018-08-07 14:01".toDate(Datex.yMdHm).createCalendar()))
        assertTrue("2018-08-07 13:01".toDate(Datex.yMdHm).isSameMinuteOfHour("2018-08-07 14:01".toDate(Datex.yMdHm), Locale.getDefault()))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).isSameMinuteOfHour("2018-08-07 14:02".toDate(Datex.yMdHm)))
        assertTrue("2018-08-07 13:01".toDate(Datex.yMdHm).time.isSameMinuteOfHour("2018-08-07 14:01".toDate(Datex.yMdHm).time, Locale.getDefault()))
        assertFalse("2018-08-07 13:01".toDate(Datex.yMdHm).time.isSameMinuteOfHour("2018-08-07 14:02".toDate(Datex.yMdHm).time))
        assertFalse(eraNoSameCalendar1.isSameMinuteOfHour(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01:25".toDate(Datex.yMdHms).createCalendar().isSameSecond("2018-08-07 13:01:25 001".toDate(Datex.yMdHmsS).createCalendar()))
        assertTrue("2018-08-07 13:01:25".toDate(Datex.yMdHms).isSameSecond("2018-08-07 13:01:25 999".toDate(Datex.yMdHmsS), Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).isSameSecond("2018-08-07 13:01:26 000".toDate(Datex.yMdHmsS)))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).isSameSecond("2018-08-07 13:01:24 999".toDate(Datex.yMdHmsS)))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).isSameSecond("2019-08-07 13:01:26 999".toDate(Datex.yMdHmsS)))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).isSameSecond("2018-09-07 13:01:26 999".toDate(Datex.yMdHmsS)))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).isSameSecond("2018-08-06 13:01:26 999".toDate(Datex.yMdHmsS)))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).isSameSecond("2018-08-07 14:01:26 999".toDate(Datex.yMdHmsS)))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).isSameSecond("2018-08-07 13:02:26 999".toDate(Datex.yMdHmsS)))
        assertTrue("2018-08-07 13:01:25".toDate(Datex.yMdHms).time.isSameSecond("2018-08-07 13:01:25 999".toDate(Datex.yMdHmsS).time, Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).time.isSameSecond("2018-08-07 13:01:26 000".toDate(Datex.yMdHmsS).time))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).time.isSameSecond("2018-08-07 13:01:24 999".toDate(Datex.yMdHmsS).time))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).time.isSameSecond("2019-08-07 13:01:26 999".toDate(Datex.yMdHmsS).time))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).time.isSameSecond("2018-09-07 13:01:26 999".toDate(Datex.yMdHmsS).time))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).time.isSameSecond("2018-08-06 13:01:26 999".toDate(Datex.yMdHmsS).time))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).time.isSameSecond("2018-08-07 14:01:26 999".toDate(Datex.yMdHmsS).time))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).time.isSameSecond("2018-08-07 13:02:26 999".toDate(Datex.yMdHmsS).time))
        assertFalse(eraNoSameCalendar1.isSameSecond(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01:25".toDate(Datex.yMdHms).createCalendar().isSameSecondOfMinute("2018-08-07 13:02:25".toDate(Datex.yMdHms).createCalendar()))
        assertTrue("2018-08-07 13:01:25".toDate(Datex.yMdHms).isSameSecondOfMinute("2018-08-07 13:02:25".toDate(Datex.yMdHms), Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).isSameSecondOfMinute("2018-08-07 13:02:26".toDate(Datex.yMdHms)))
        assertTrue("2018-08-07 13:01:25".toDate(Datex.yMdHms).time.isSameSecondOfMinute("2018-08-07 13:02:25".toDate(Datex.yMdHms).time, Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25".toDate(Datex.yMdHms).time.isSameSecondOfMinute("2018-08-07 13:02:26".toDate(Datex.yMdHms).time))
        assertFalse(eraNoSameCalendar1.isSameSecondOfMinute(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).createCalendar().isSameMillisecond("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).createCalendar()))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).isSameMillisecond("2018-08-07 13:01:25 332".toDate(Datex.yMdHmsS), Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).isSameMillisecond("2019-08-07 13:01:26 332".toDate(Datex.yMdHmsS)))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).isSameMillisecond("2018-09-07 13:01:26 332".toDate(Datex.yMdHmsS)))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).isSameMillisecond("2018-08-06 13:01:26 332".toDate(Datex.yMdHmsS)))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).isSameMillisecond("2018-08-07 14:01:26 332".toDate(Datex.yMdHmsS)))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).isSameMillisecond("2018-08-07 13:02:26 332".toDate(Datex.yMdHmsS)))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).time.isSameMillisecond("2018-08-07 13:01:25 332".toDate(Datex.yMdHmsS).time, Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).time.isSameMillisecond("2019-08-07 13:01:26 332".toDate(Datex.yMdHmsS).time))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).time.isSameMillisecond("2018-09-07 13:01:26 332".toDate(Datex.yMdHmsS).time))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).time.isSameMillisecond("2018-08-06 13:01:26 332".toDate(Datex.yMdHmsS).time))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).time.isSameMillisecond("2018-08-07 14:01:26 332".toDate(Datex.yMdHmsS).time))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).time.isSameMillisecond("2018-08-07 13:02:26 332".toDate(Datex.yMdHmsS).time))
        assertFalse(eraNoSameCalendar1.isSameMillisecond(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).createCalendar().isSameMillisecondOfSecond("2018-08-07 13:01:26 333".toDate(Datex.yMdHmsS).createCalendar()))
        assertTrue("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).isSameMillisecondOfSecond("2018-08-07 13:01:26 333".toDate(Datex.yMdHmsS), Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).isSameMillisecondOfSecond("2018-08-07 13:01:26 334".toDate(Datex.yMdHmsS)))
        assertTrue("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).time.isSameMillisecondOfSecond("2018-08-07 13:01:26 333".toDate(Datex.yMdHmsS).time, Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25 333".toDate(Datex.yMdHmsS).time.isSameMillisecondOfSecond("2018-08-07 13:01:26 334".toDate(Datex.yMdHmsS).time))
        assertFalse(eraNoSameCalendar1.isSameMillisecondOfSecond(eraNoSameCalendar2))
    }

    @Test
    fun testDiffer() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        assertTrue("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).createCalendar().differField("2018-05-28 08:58:58 895".toDate(Datex.yMdHmsS).createCalendar(), Calendar.MILLISECOND, 7))
        assertTrue("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).createCalendar().differField("2018-05-28 08:58:58 881".toDate(Datex.yMdHmsS).createCalendar(), Calendar.MILLISECOND, 7))
        assertTrue("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).createCalendar().differField("2018-05-28 08:58:58 881".toDate(Datex.yMdHmsS).createCalendar(), Calendar.MILLISECOND, 0))
        assertFalse("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).createCalendar().differField("2018-05-28 08:58:58 896".toDate(Datex.yMdHmsS).createCalendar(), Calendar.MILLISECOND, 7))
        assertFalse("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).createCalendar().differField("2018-05-28 08:58:58 850".toDate(Datex.yMdHmsS).createCalendar(), Calendar.MILLISECOND, 7))

        assertTrue("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).differCalendarField("2018-05-28 08:58:58 895".toDate(Datex.yMdHmsS), Calendar.MILLISECOND, 7, defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).differCalendarField("2018-05-28 08:58:58 881".toDate(Datex.yMdHmsS), Calendar.MILLISECOND, 7, defaultFirstDayOfWeek))
        assertFalse("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).differCalendarField("2018-05-28 08:58:58 896".toDate(Datex.yMdHmsS), Calendar.MILLISECOND, 7, Locale.getDefault()))
        assertFalse("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).differCalendarField("2018-05-28 08:58:58 850".toDate(Datex.yMdHmsS), Calendar.MILLISECOND, 7))

        assertTrue("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).time.differCalendarField("2018-05-28 08:58:58 895".toDate(Datex.yMdHmsS).time, Calendar.MILLISECOND, 7, defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).time.differCalendarField("2018-05-28 08:58:58 881".toDate(Datex.yMdHmsS).time, Calendar.MILLISECOND, 7, defaultFirstDayOfWeek))
        assertFalse("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).time.differCalendarField("2018-05-28 08:58:58 896".toDate(Datex.yMdHmsS).time, Calendar.MILLISECOND, 7, Locale.getDefault()))
        assertFalse("2018-05-28 08:58:58 888".toDate(Datex.yMdHmsS).time.differCalendarField("2018-05-28 08:58:58 850".toDate(Datex.yMdHmsS).time, Calendar.MILLISECOND, 7))
    }
}