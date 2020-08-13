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
    }

    @Test
    fun testToAndFormat() {
        val time = System.currentTimeMillis()
        assertEquals(time.toDate().time, time)

        assertEquals("2018".toDate(SimpleDateFormat("yyyy")).format(SimpleDateFormat("yyyy")), "2018")
        assertEquals("2018".toDate("yyyy").format("yyyy"), "2018")
        assertEquals("2018".toDate("yyyy", Locale.getDefault()).format("yyyy", Locale.getDefault()), "2018")
        assertEquals("2018".toDateY().formatY(), "2018")
        assertEquals("2018".toDateY(Locale.getDefault()).formatY(Locale.getDefault()), "2018")
        assertEquals("2018-06".toDateYM().formatYM(), "2018-06")
        assertEquals("2018-06".toDateYM(Locale.getDefault()).formatYM(Locale.getDefault()), "2018-06")
        assertEquals("201806".toDateYMCompact().formatYMCompact(), "201806")
        assertEquals("201806".toDateYMCompact(Locale.getDefault()).formatYMCompact(Locale.getDefault()), "201806")
        assertEquals("2018-06-23".toDateYMD().formatYMD(), "2018-06-23")
        assertEquals("2018-06-23".toDateYMD(Locale.getDefault()).formatYMD(Locale.getDefault()), "2018-06-23")
        assertEquals("20180623".toDateYMDCompact().formatYMDCompact(), "20180623")
        assertEquals("20180623".toDateYMDCompact(Locale.getDefault()).formatYMDCompact(Locale.getDefault()), "20180623")
        assertEquals("2018-06-23 21".toDateYMDH().formatYMDH(), "2018-06-23 21")
        assertEquals("2018-06-23 21".toDateYMDH(Locale.getDefault()).formatYMDH(Locale.getDefault()), "2018-06-23 21")
        assertEquals("2018-06-23 21:59".toDateYMDHM().formatYMDHM(), "2018-06-23 21:59")
        assertEquals("2018-06-23 21:59".toDateYMDHM(Locale.getDefault()).formatYMDHM(Locale.getDefault()), "2018-06-23 21:59")
        assertEquals("2018-06-23 21:59:01".toDateYMDHMS().formatYMDHMS(), "2018-06-23 21:59:01")
        assertEquals("2018-06-23 21:59:01".toDateYMDHMS(Locale.getDefault()).formatYMDHMS(Locale.getDefault()), "2018-06-23 21:59:01")
        assertEquals("2018-06-23 21:59:01 999".toDateYMDHMSM().formatYMDHMSM(), "2018-06-23 21:59:01 999")
        assertEquals("2018-06-23 21:59:01 999".toDateYMDHMSM(Locale.getDefault()).formatYMDHMSM(Locale.getDefault()), "2018-06-23 21:59:01 999")

        assertEquals("2016-02-29".toDateYMD().formatYMD(), "2016-02-29")
        assertEquals("2017-02-29".toDateYMD().formatYMD(), "2017-03-01")
    }

    @Test
    fun testGet() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getYear(), 2016)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarYear(), 2016)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarYear(Locale.getDefault()), 2016)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getMonth(), 1)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarMonth(), 1)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarMonth(Locale.getDefault()), 1)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getWeekOfYear(), 10)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarWeekOfYear(), 10)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarWeekOfYear(defaultFirstDayOfWeek), 10)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarWeekOfYear(Locale.getDefault()), 10)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarWeekOfYear(defaultFirstDayOfWeek, Locale.getDefault()), 10)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getWeekOfMonth(), 5)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarWeekOfMonth(), 5)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarWeekOfMonth(defaultFirstDayOfWeek), 5)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarWeekOfMonth(Locale.getDefault()), 5)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarWeekOfMonth(defaultFirstDayOfWeek, Locale.getDefault()), 5)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getDayOfYear(), 60)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfYear(), 60)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfYear(Locale.getDefault()), 60)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getDayOfMonth(), 29)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfMonth(), 29)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfMonth(Locale.getDefault()), 29)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getDayOfWeekInMonth(), 5)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfWeekInMonth(), 5)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfWeekInMonth(defaultFirstDayOfWeek), 5)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfWeekInMonth(Locale.getDefault()), 5)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfWeekInMonth(defaultFirstDayOfWeek, Locale.getDefault()), 5)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getDayOfWeek(), 2)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfWeek(), 2)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfWeek(defaultFirstDayOfWeek), 2)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfWeek(Locale.getDefault()), 2)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarDayOfWeek(defaultFirstDayOfWeek, Locale.getDefault()), 2)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getHourOfDay(), 15)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarHourOfDay(), 15)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarHourOfDay(Locale.getDefault()), 15)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getHour(), 3)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarHour(), 3)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarHour(Locale.getDefault()), 3)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getMinute(), 59)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarMinute(), 59)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarMinute(Locale.getDefault()), 59)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getSecond(), 34)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarSecond(), 34)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarSecond(Locale.getDefault()), 34)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().createCalendar().getMillisecond(), 897)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarMillisecond(), 897)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarMillisecond(Locale.getDefault()), 897)

        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarField(Calendar.MILLISECOND, defaultFirstDayOfWeek, Locale.getDefault()), 897)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarField(Calendar.MILLISECOND, defaultFirstDayOfWeek), 897)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarField(Calendar.MILLISECOND, Locale.getDefault()), 897)
        assertEquals("2016-02-29 15:59:34 897".toDateYMDHMSM().getCalendarField(Calendar.MILLISECOND), 897)
    }

    @Test
    fun testAdd() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        assertEquals("2016-02-29".toDateYMD().createCalendar().addToDate(Calendar.YEAR, 1).formatYMD(), "2017-02-28")

        assertEquals("2016-02-29".toDateYMD().addCalendarField(Calendar.YEAR, 1).formatYMD(), "2017-02-28")
        assertEquals("2016-02-29".toDateYMD().addCalendarField(Calendar.YEAR, 1, defaultFirstDayOfWeek).formatYMD(), "2017-02-28")
        assertEquals("2016-02-29".toDateYMD().addCalendarField(Calendar.YEAR, 1, Locale.getDefault()).formatYMD(), "2017-02-28")
        assertEquals("2016-02-29".toDateYMD().addCalendarField(Calendar.YEAR, 1, defaultFirstDayOfWeek, Locale.getDefault()).formatYMD(), "2017-02-28")

        assertEquals("2016-02-29".toDateYMD().addYear(1).formatYMD(), "2017-02-28")
        assertEquals("2016-02-29".toDateYMD().addYear(-1).formatYMD(), "2015-02-28")
        assertEquals("2016-02-29".toDateYMD().addYear(1, Locale.getDefault()).formatYMD(), "2017-02-28")
        assertEquals("2016-02-29".toDateYMD().addYear(-1, Locale.getDefault()).formatYMD(), "2015-02-28")

        assertEquals("2017-01-29".toDateYMD().addMonth(1).formatYMD(), "2017-02-28")
        assertEquals("2017-03-29".toDateYMD().addMonth(-1).formatYMD(), "2017-02-28")
        assertEquals("2017-01-29".toDateYMD().addMonth(1, Locale.getDefault()).formatYMD(), "2017-02-28")
        assertEquals("2017-03-29".toDateYMD().addMonth(-1, Locale.getDefault()).formatYMD(), "2017-02-28")

        assertEquals("2016-02-10".toDateYMD().addWeekOfMonth(1, defaultFirstDayOfWeek).formatYMD(), "2016-02-17")
        assertEquals("2016-02-10".toDateYMD().addWeekOfMonth(1).formatYMD(), "2016-02-17")
        assertEquals("2016-02-10".toDateYMD().addWeekOfMonth(-1, defaultFirstDayOfWeek).formatYMD(), "2016-02-03")
        assertEquals("2016-02-10".toDateYMD().addWeekOfMonth(1, defaultFirstDayOfWeek, Locale.getDefault()).formatYMD(), "2016-02-17")
        assertEquals("2016-02-10".toDateYMD().addWeekOfMonth(1, Locale.getDefault()).formatYMD(), "2016-02-17")
        assertEquals("2016-02-10".toDateYMD().addWeekOfMonth(-1, defaultFirstDayOfWeek, Locale.getDefault()).formatYMD(), "2016-02-03")

        assertEquals("2016-02-10".toDateYMD().addWeekOfYear(1).formatYMD(), "2016-02-17")
        assertEquals("2016-02-10".toDateYMD().addWeekOfYear(1, defaultFirstDayOfWeek).formatYMD(), "2016-02-17")
        assertEquals("2016-02-10".toDateYMD().addWeekOfYear(-1, defaultFirstDayOfWeek).formatYMD(), "2016-02-03")
        assertEquals("2016-02-10".toDateYMD().addWeekOfYear(1, defaultFirstDayOfWeek, Locale.getDefault()).formatYMD(), "2016-02-17")
        assertEquals("2016-02-10".toDateYMD().addWeekOfYear(1, Locale.getDefault()).formatYMD(), "2016-02-17")
        assertEquals("2016-02-10".toDateYMD().addWeekOfYear(-1, defaultFirstDayOfWeek, Locale.getDefault()).formatYMD(), "2016-02-03")

        assertEquals("2016-02-29".toDateYMD().addDayOfWeek(1).formatYMD(), "2016-03-01")
        assertEquals("2016-02-29".toDateYMD().addDayOfWeek(1, defaultFirstDayOfWeek).formatYMD(), "2016-03-01")
        assertEquals("2016-03-01".toDateYMD().addDayOfWeek(-1, defaultFirstDayOfWeek).formatYMD(), "2016-02-29")
        assertEquals("2016-02-29".toDateYMD().addDayOfWeek(1, defaultFirstDayOfWeek, Locale.getDefault()).formatYMD(), "2016-03-01")
        assertEquals("2016-02-29".toDateYMD().addDayOfWeek(1, Locale.getDefault()).formatYMD(), "2016-03-01")
        assertEquals("2016-03-01".toDateYMD().addDayOfWeek(-1, defaultFirstDayOfWeek, Locale.getDefault()).formatYMD(), "2016-02-29")

        assertEquals("2016-02-29".toDateYMD().addDayOfWeekInMonth(1).formatYMD(), "2016-03-07")
        assertEquals("2016-02-29".toDateYMD().addDayOfWeekInMonth(1, defaultFirstDayOfWeek).formatYMD(), "2016-03-07")
        assertEquals("2016-03-07".toDateYMD().addDayOfWeekInMonth(-1, defaultFirstDayOfWeek).formatYMD(), "2016-02-29")
        assertEquals("2016-02-29".toDateYMD().addDayOfWeekInMonth(1, defaultFirstDayOfWeek, Locale.getDefault()).formatYMD(), "2016-03-07")
        assertEquals("2016-02-29".toDateYMD().addDayOfWeekInMonth(1, Locale.getDefault()).formatYMD(), "2016-03-07")
        assertEquals("2016-03-07".toDateYMD().addDayOfWeekInMonth(-1, defaultFirstDayOfWeek, Locale.getDefault()).formatYMD(), "2016-02-29")

        assertEquals("2016-02-29".toDateYMD().addDayOfMonth(1).formatYMD(), "2016-03-01")
        assertEquals("2016-03-01".toDateYMD().addDayOfMonth(-1).formatYMD(), "2016-02-29")
        assertEquals("2016-02-29".toDateYMD().addDayOfMonth(1, Locale.getDefault()).formatYMD(), "2016-03-01")
        assertEquals("2016-03-01".toDateYMD().addDayOfMonth(-1, Locale.getDefault()).formatYMD(), "2016-02-29")

        assertEquals("2016-02-29".toDateYMD().addDayOfYear(1).formatYMD(), "2016-03-01")
        assertEquals("2016-03-01".toDateYMD().addDayOfYear(-1).formatYMD(), "2016-02-29")
        assertEquals("2016-02-29".toDateYMD().addDayOfYear(1, Locale.getDefault()).formatYMD(), "2016-03-01")
        assertEquals("2016-03-01".toDateYMD().addDayOfYear(-1, Locale.getDefault()).formatYMD(), "2016-02-29")

        assertEquals("2016-02-29 23:26:33".toDateYMDHMS().addHour(1).formatYMDHMS(), "2016-03-01 00:26:33")
        assertEquals("2016-03-01 00:26:33".toDateYMDHMS().addHour(-1).formatYMDHMS(), "2016-02-29 23:26:33")
        assertEquals("2016-02-29 23:26:33".toDateYMDHMS().addHour(1, Locale.getDefault()).formatYMDHMS(), "2016-03-01 00:26:33")
        assertEquals("2016-03-01 00:26:33".toDateYMDHMS().addHour(-1, Locale.getDefault()).formatYMDHMS(), "2016-02-29 23:26:33")

        assertEquals("2016-02-29 23:26:33".toDateYMDHMS().addHourOfDay(1).formatYMDHMS(), "2016-03-01 00:26:33")
        assertEquals("2016-03-01 00:26:33".toDateYMDHMS().addHourOfDay(-1).formatYMDHMS(), "2016-02-29 23:26:33")
        assertEquals("2016-02-29 23:26:33".toDateYMDHMS().addHourOfDay(1, Locale.getDefault()).formatYMDHMS(), "2016-03-01 00:26:33")
        assertEquals("2016-03-01 00:26:33".toDateYMDHMS().addHourOfDay(-1, Locale.getDefault()).formatYMDHMS(), "2016-02-29 23:26:33")

        assertEquals("2016-02-29 15:59:33".toDateYMDHMS().addMinute(1).formatYMDHMS(), "2016-02-29 16:00:33")
        assertEquals("2016-02-29 16:00:33".toDateYMDHMS().addMinute(-1).formatYMDHMS(), "2016-02-29 15:59:33")
        assertEquals("2016-02-29 15:59:33".toDateYMDHMS().addMinute(1, Locale.getDefault()).formatYMDHMS(), "2016-02-29 16:00:33")
        assertEquals("2016-02-29 16:00:33".toDateYMDHMS().addMinute(-1, Locale.getDefault()).formatYMDHMS(), "2016-02-29 15:59:33")

        assertEquals("2016-02-29 15:59:59".toDateYMDHMS().addSecond(1).formatYMDHMS(), "2016-02-29 16:00:00")
        assertEquals("2016-02-29 16:00:00".toDateYMDHMS().addSecond(-1).formatYMDHMS(), "2016-02-29 15:59:59")
        assertEquals("2016-02-29 15:59:59".toDateYMDHMS().addSecond(1, Locale.getDefault()).formatYMDHMS(), "2016-02-29 16:00:00")
        assertEquals("2016-02-29 16:00:00".toDateYMDHMS().addSecond(-1, Locale.getDefault()).formatYMDHMS(), "2016-02-29 15:59:59")

        assertEquals("2016-02-29 15:59:59 999".toDateYMDHMSM().addMillisecond(1).formatYMDHMSM(), "2016-02-29 16:00:00 000")
        assertEquals("2016-02-29 16:00:00 000".toDateYMDHMSM().addMillisecond(-1).formatYMDHMSM(), "2016-02-29 15:59:59 999")
        assertEquals("2016-02-29 15:59:59 999".toDateYMDHMSM().addMillisecond(1, Locale.getDefault()).formatYMDHMSM(), "2016-02-29 16:00:00 000")
    }

    @Test
    fun testSame() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        val eraNoSameCalendar1 = Calendar.getInstance()
        val eraNoSameCalendar2 = Calendar.getInstance()
        eraNoSameCalendar2.set(Calendar.ERA, 0)

        assertTrue("2018".toDateY().createCalendar().isSameYear("2018".toDateY().createCalendar()))
        assertFalse("2018".toDateY().isSameYear("2017".toDateY()))
        assertFalse("2018".toDateY().isSameYear("2019".toDateY(), Locale.getDefault()))
        assertFalse(eraNoSameCalendar1.isSameYear(eraNoSameCalendar2))

        assertTrue("2018-08".toDateYM().createCalendar().isSameMonth("2018-08".toDateYM().createCalendar()))
        assertTrue("2018-08".toDateYM().isSameMonth("2018-08-01".toDateYMD(), Locale.getDefault()))
        assertTrue("2018-08".toDateYM().isSameMonth("2018-08-31".toDateYMD()))
        assertFalse("2018-08".toDateYM().isSameMonth("2018-09".toDateYM()))
        assertFalse("2018-08".toDateYM().isSameMonth("2017-08".toDateYM()))
        assertFalse("2018-08".toDateYM().isSameMonth("2019-08".toDateYM()))
        assertFalse(eraNoSameCalendar1.isSameMonth(eraNoSameCalendar2))

        assertTrue("2018-08".toDateYM().createCalendar().isSameMonthOfYear("2019-08".toDateYM().createCalendar()))
        assertTrue("2018-08".toDateYM().isSameMonthOfYear("2019-08".toDateYM(), Locale.getDefault()))
        assertFalse("2018-08".toDateYM().isSameMonthOfYear("2019-07".toDateYM()))
        assertFalse(eraNoSameCalendar1.isSameMonthOfYear(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDateYMD().createCalendar().isSameWeek("2018-08-05".toDateYMD().createCalendar()))
        assertTrue("2018-12-31".toDateYMD().isSameWeek("2019-01-01".toDateYMD(), defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-08-31".toDateYMD().isSameWeek("2018-09-01".toDateYMD(), defaultFirstDayOfWeek))
        assertTrue("2018-08-07".toDateYMD().isSameWeek("2018-08-11".toDateYMD(), Locale.getDefault()))
        assertTrue("2019-01-01".toDateYMD().isSameWeek("2018-12-31".toDateYMD()))
        assertFalse("2018-08-07".toDateYMD().isSameWeek("2018-08-04".toDateYMD()))
        assertFalse("2018-08-07".toDateYMD().isSameWeek("2018-08-12".toDateYMD()))
        assertFalse("2019-12-31".toDateYMD().isSameWeek("2018-12-31".toDateYMD()))
        assertFalse("2018-12-31".toDateYMD().isSameWeek("2019-01-06".toDateYMD()))
        assertFalse(eraNoSameCalendar1.isSameWeek(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDateYMD().createCalendar().isSameWeekOfYear("2019-08-04".toDateYMD().createCalendar()))
        assertTrue("2018-08-07".toDateYMD().isSameWeekOfYear("2019-08-10".toDateYMD(), defaultFirstDayOfWeek, Locale.getDefault()))
        assertFalse("2018-08-07".toDateYMD().isSameWeekOfYear("2019-08-03".toDateYMD(), defaultFirstDayOfWeek))
        assertFalse("2018-08-07".toDateYMD().isSameWeekOfYear("2019-08-11".toDateYMD(), Locale.getDefault()))
        assertFalse("2018-08-07".toDateYMD().isSameWeekOfYear("2019-08-11".toDateYMD()))
        assertFalse(eraNoSameCalendar1.isSameWeekOfYear(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDateYMD().createCalendar().isSameWeekOfMonth("2018-07-08".toDateYMD().createCalendar()))
        assertTrue("2018-08-07".toDateYMD().isSameWeekOfMonth("2018-07-14".toDateYMD(), defaultFirstDayOfWeek, Locale.getDefault()))
        assertFalse("2018-08-07".toDateYMD().isSameWeekOfMonth("2018-07-07".toDateYMD(), defaultFirstDayOfWeek))
        assertFalse("2018-08-07".toDateYMD().isSameWeekOfMonth("2018-07-15".toDateYMD(), Locale.getDefault()))
        assertFalse("2018-08-07".toDateYMD().isSameWeekOfMonth("2018-07-15".toDateYMD()))
        assertFalse(eraNoSameCalendar1.isSameWeekOfMonth(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDateYMD().createCalendar().isSameDay("2018-08-07 01".toDateYMDH().createCalendar()))
        assertTrue("2018-08-07".toDateYMD().isSameDay("2018-08-07 23".toDateYMDH(), Locale.getDefault()))
        assertFalse("2018-08-07".toDateYMD().isSameDay("2018-08-08".toDateYMD()))
        assertFalse("2018-08-07".toDateYMD().isSameDay("2018-08-06".toDateYMD()))
        assertFalse("2018-08-07".toDateYMD().isSameDay("2019-08-07".toDateYMD()))
        assertFalse("2018-08-07".toDateYMD().isSameDay("2018-09-07".toDateYMD()))
        assertFalse(eraNoSameCalendar1.isSameDay(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDateYMD().createCalendar().isSameDayOfYear("2016-08-06".toDateYMD().createCalendar()))
        assertTrue("2018-08-07".toDateYMD().isSameDayOfYear("2016-08-06 01".toDateYMDH(), Locale.getDefault()))
        assertTrue("2018-08-07".toDateYMD().isSameDayOfYear("2016-08-06 23".toDateYMDH()))
        assertFalse("2018-08-07".toDateYMD().isSameDayOfYear("2016-08-07".toDateYMD()))
        assertFalse(eraNoSameCalendar1.isSameDayOfYear(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDateYMD().createCalendar().isSameDayOfMonth("2018-07-07".toDateYMD().createCalendar()))
        assertTrue("2018-08-07".toDateYMD().isSameDayOfMonth("2019-08-07".toDateYMD(), Locale.getDefault()))
        assertFalse("2018-08-07".toDateYMD().isSameDayOfMonth("2018-08-06".toDateYMD()))
        assertFalse(eraNoSameCalendar1.isSameDayOfMonth(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDateYMD().createCalendar().isSameDayOfWeek("2018-08-14".toDateYMD().createCalendar()))
        assertTrue("2018-08-07".toDateYMD().isSameDayOfWeek("2018-08-21".toDateYMD(), defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-08-07".toDateYMD().isSameDayOfWeek("2018-08-14".toDateYMD(), defaultFirstDayOfWeek))
        assertFalse("2018-08-07".toDateYMD().isSameDayOfWeek("2018-08-08".toDateYMD(), Locale.getDefault()))
        assertFalse("2018-08-07".toDateYMD().isSameDayOfWeek("2018-08-15".toDateYMD()))
        assertFalse(eraNoSameCalendar1.isSameDayOfWeek(eraNoSameCalendar2))

        assertTrue("2018-08-07".toDateYMD().createCalendar().isSameDayOfWeekInMonth("2018-08-01".toDateYMD().createCalendar()))
        assertTrue("2018-08-07".toDateYMD().isSameDayOfWeekInMonth("2018-08-01".toDateYMD(), defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-08-07".toDateYMD().isSameDayOfWeekInMonth("2018-08-01".toDateYMD(), defaultFirstDayOfWeek))
        assertTrue("2018-08-07".toDateYMD().isSameDayOfWeekInMonth("2018-08-07".toDateYMD(), Locale.getDefault()))
        assertFalse("2018-08-07".toDateYMD().isSameDayOfWeekInMonth("2018-07-08".toDateYMD()))
        assertFalse(eraNoSameCalendar1.isSameDayOfWeekInMonth(eraNoSameCalendar2))

        assertTrue("2018-08-07 13".toDateYMDH().createCalendar().isSameHour("2018-08-07 13:01".toDateYMDHM().createCalendar()))
        assertTrue("2018-08-07 13".toDateYMDH().isSameHour("2018-08-07 13:59".toDateYMDHM(), Locale.getDefault()))
        assertFalse("2018-08-07 13".toDateYMDH().isSameHour("2018-08-07 14:00".toDateYMDHM()))
        assertFalse("2018-08-07 13".toDateYMDH().isSameHour("2018-08-07 12:59".toDateYMDHM()))
        assertFalse("2018-08-07 13".toDateYMDH().isSameHour("2019-08-07 12:59".toDateYMDHM()))
        assertFalse("2018-08-07 13".toDateYMDH().isSameHour("2018-09-07 12:59".toDateYMDHM()))
        assertFalse("2018-08-07 13".toDateYMDH().isSameHour("2018-08-06 12:59".toDateYMDHM()))
        assertFalse(eraNoSameCalendar1.isSameHour(eraNoSameCalendar2))

        assertTrue("2018-08-07 13".toDateYMDH().createCalendar().isSameHourOf24H("2018-08-06 13".toDateYMDH().createCalendar()))
        assertTrue("2018-08-07 13".toDateYMDH().isSameHourOf24H("2018-08-06 13".toDateYMDH(), Locale.getDefault()))
        assertFalse("2018-08-07 13".toDateYMDH().isSameHourOf24H("2018-08-06 14".toDateYMDH()))
        assertFalse(eraNoSameCalendar1.isSameHourOf24H(eraNoSameCalendar2))

        assertTrue("2018-08-07 13".toDateYMDH().createCalendar().isSameHourOf12H("2018-08-07 01".toDateYMDH().createCalendar()))
        assertTrue("2018-08-07 13".toDateYMDH().isSameHourOf12H("2018-08-07 01".toDateYMDH(), Locale.getDefault()))
        assertFalse("2018-08-07 13".toDateYMDH().isSameHourOf12H("2018-08-07 02".toDateYMDH()))
        assertFalse(eraNoSameCalendar1.isSameHourOf12H(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01".toDateYMDHM().createCalendar().isSameMinute("2018-08-07 13:01:01".toDateYMDHMS().createCalendar()))
        assertTrue("2018-08-07 13:01".toDateYMDHM().isSameMinute("2018-08-07 13:01:59".toDateYMDHMS(), Locale.getDefault()))
        assertFalse("2018-08-07 13:01".toDateYMDHM().isSameMinute("2018-08-07 13:02:00".toDateYMDHMS()))
        assertFalse("2018-08-07 13:01".toDateYMDHM().isSameMinute("2018-08-07 13:00:59".toDateYMDHMS()))
        assertFalse("2018-08-07 13:01".toDateYMDHM().isSameMinute("2019-08-07 13:00:59".toDateYMDHMS()))
        assertFalse("2018-08-07 13:01".toDateYMDHM().isSameMinute("2018-09-07 13:00:59".toDateYMDHMS()))
        assertFalse("2018-08-07 13:01".toDateYMDHM().isSameMinute("2018-08-06 13:00:59".toDateYMDHMS()))
        assertFalse("2018-08-07 13:01".toDateYMDHM().isSameMinute("2018-08-07 14:00:59".toDateYMDHMS()))
        assertFalse(eraNoSameCalendar1.isSameMinute(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01".toDateYMDHM().createCalendar().isSameMinuteOfHour("2018-08-07 14:01".toDateYMDHM().createCalendar()))
        assertTrue("2018-08-07 13:01".toDateYMDHM().isSameMinuteOfHour("2018-08-07 14:01".toDateYMDHM(), Locale.getDefault()))
        assertFalse("2018-08-07 13:01".toDateYMDHM().isSameMinuteOfHour("2018-08-07 14:02".toDateYMDHM()))
        assertFalse(eraNoSameCalendar1.isSameMinuteOfHour(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01:25".toDateYMDHMS().createCalendar().isSameSecond("2018-08-07 13:01:25 001".toDateYMDHMSM().createCalendar()))
        assertTrue("2018-08-07 13:01:25".toDateYMDHMS().isSameSecond("2018-08-07 13:01:25 999".toDateYMDHMSM(), Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25".toDateYMDHMS().isSameSecond("2018-08-07 13:01:26 000".toDateYMDHMSM()))
        assertFalse("2018-08-07 13:01:25".toDateYMDHMS().isSameSecond("2018-08-07 13:01:24 999".toDateYMDHMSM()))
        assertFalse("2018-08-07 13:01:25".toDateYMDHMS().isSameSecond("2019-08-07 13:01:26 999".toDateYMDHMSM()))
        assertFalse("2018-08-07 13:01:25".toDateYMDHMS().isSameSecond("2018-09-07 13:01:26 999".toDateYMDHMSM()))
        assertFalse("2018-08-07 13:01:25".toDateYMDHMS().isSameSecond("2018-08-06 13:01:26 999".toDateYMDHMSM()))
        assertFalse("2018-08-07 13:01:25".toDateYMDHMS().isSameSecond("2018-08-07 14:01:26 999".toDateYMDHMSM()))
        assertFalse("2018-08-07 13:01:25".toDateYMDHMS().isSameSecond("2018-08-07 13:02:26 999".toDateYMDHMSM()))
        assertFalse(eraNoSameCalendar1.isSameSecond(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01:25".toDateYMDHMS().createCalendar().isSameSecondOfMinute("2018-08-07 13:02:25".toDateYMDHMS().createCalendar()))
        assertTrue("2018-08-07 13:01:25".toDateYMDHMS().isSameSecondOfMinute("2018-08-07 13:02:25".toDateYMDHMS(), Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25".toDateYMDHMS().isSameSecondOfMinute("2018-08-07 13:02:26".toDateYMDHMS()))
        assertFalse(eraNoSameCalendar1.isSameSecondOfMinute(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01:25 333".toDateYMDHMSM().createCalendar().isSameMillisecond("2018-08-07 13:01:25 333".toDateYMDHMSM().createCalendar()))
        assertFalse("2018-08-07 13:01:25 333".toDateYMDHMSM().isSameMillisecond("2018-08-07 13:01:25 332".toDateYMDHMSM(), Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25 333".toDateYMDHMSM().isSameMillisecond("2019-08-07 13:01:26 332".toDateYMDHMSM()))
        assertFalse("2018-08-07 13:01:25 333".toDateYMDHMSM().isSameMillisecond("2018-09-07 13:01:26 332".toDateYMDHMSM()))
        assertFalse("2018-08-07 13:01:25 333".toDateYMDHMSM().isSameMillisecond("2018-08-06 13:01:26 332".toDateYMDHMSM()))
        assertFalse("2018-08-07 13:01:25 333".toDateYMDHMSM().isSameMillisecond("2018-08-07 14:01:26 332".toDateYMDHMSM()))
        assertFalse("2018-08-07 13:01:25 333".toDateYMDHMSM().isSameMillisecond("2018-08-07 13:02:26 332".toDateYMDHMSM()))
        assertFalse(eraNoSameCalendar1.isSameMillisecond(eraNoSameCalendar2))

        assertTrue("2018-08-07 13:01:25 333".toDateYMDHMSM().createCalendar().isSameMillisecondOfSecond("2018-08-07 13:01:26 333".toDateYMDHMSM().createCalendar()))
        assertTrue("2018-08-07 13:01:25 333".toDateYMDHMSM().isSameMillisecondOfSecond("2018-08-07 13:01:26 333".toDateYMDHMSM(), Locale.getDefault()))
        assertFalse("2018-08-07 13:01:25 333".toDateYMDHMSM().isSameMillisecondOfSecond("2018-08-07 13:01:26 334".toDateYMDHMSM()))
        assertFalse(eraNoSameCalendar1.isSameMillisecondOfSecond(eraNoSameCalendar2))

        assertFalse(eraNoSameCalendar1.isSameYear(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameMonth(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameMonthOfYear(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameWeek(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameWeekOfYear(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameWeekOfMonth(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameDay(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameDayOfYear(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameDayOfMonth(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameDayOfWeek(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameDayOfWeekInMonth(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameHour(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameHourOf24H(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameHourOf12H(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameMinute(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameMinuteOfHour(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameSecond(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameSecondOfMinute(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameMillisecond(eraNoSameCalendar2))
        assertFalse(eraNoSameCalendar1.isSameMillisecondOfSecond(eraNoSameCalendar2))
    }

    @Test
    fun testDiffer() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        assertTrue("2018-05-28 08:58:58 888".toDateYMDHMSM().createCalendar().differField("2018-05-28 08:58:58 895".toDateYMDHMSM().createCalendar(), Calendar.MILLISECOND, 7))
        assertTrue("2018-05-28 08:58:58 888".toDateYMDHMSM().createCalendar().differField("2018-05-28 08:58:58 881".toDateYMDHMSM().createCalendar(), Calendar.MILLISECOND, 7))
        assertTrue("2018-05-28 08:58:58 888".toDateYMDHMSM().createCalendar().differField("2018-05-28 08:58:58 881".toDateYMDHMSM().createCalendar(), Calendar.MILLISECOND, 0))
        assertFalse("2018-05-28 08:58:58 888".toDateYMDHMSM().createCalendar().differField("2018-05-28 08:58:58 896".toDateYMDHMSM().createCalendar(), Calendar.MILLISECOND, 7))
        assertFalse("2018-05-28 08:58:58 888".toDateYMDHMSM().createCalendar().differField("2018-05-28 08:58:58 850".toDateYMDHMSM().createCalendar(), Calendar.MILLISECOND, 7))

        assertTrue("2018-05-28 08:58:58 888".toDateYMDHMSM().differCalendarField("2018-05-28 08:58:58 895".toDateYMDHMSM(), Calendar.MILLISECOND, 7, defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-05-28 08:58:58 888".toDateYMDHMSM().differCalendarField("2018-05-28 08:58:58 881".toDateYMDHMSM(), Calendar.MILLISECOND, 7, defaultFirstDayOfWeek))
        assertFalse("2018-05-28 08:58:58 888".toDateYMDHMSM().differCalendarField("2018-05-28 08:58:58 896".toDateYMDHMSM(), Calendar.MILLISECOND, 7, Locale.getDefault()))
        assertFalse("2018-05-28 08:58:58 888".toDateYMDHMSM().differCalendarField("2018-05-28 08:58:58 850".toDateYMDHMSM(), Calendar.MILLISECOND, 7))

        assertTrue("2018".toDateY().createCalendar().differYear("2011".toDateY().createCalendar(), 7))
        assertTrue("2018".toDateY().differYear("2025".toDateY(), 7, Locale.getDefault()))
        assertFalse("2018".toDateY().differYear("2010".toDateY(), 7))
        assertFalse("2018".toDateY().differYear("2026".toDateY(), 7))

        assertTrue("2018-05".toDateYM().createCalendar().differMonth("2018-12".toDateYM().createCalendar(), 7))
        assertTrue("2018-05".toDateYM().differMonth("2017-10".toDateYM(), 7, Locale.getDefault()))
        assertFalse("2018-05".toDateYM().differMonth("2019-01".toDateYM(), 7))
        assertFalse("2018-05".toDateYM().differMonth("2017-09".toDateYM(), 7))

        assertTrue("2018-05-28".toDateYMD().createCalendar().differWeekOfYear("2018-06-25".toDateYMD().createCalendar(), 4))
        assertTrue("2018-05-28".toDateYMD().differWeekOfYear("2018-04-30".toDateYMD(), 4, defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-05-28".toDateYMD().differWeekOfYear("2018-04-30".toDateYMD(), 4, Locale.getDefault()))
        assertFalse("2018-05-28".toDateYMD().differWeekOfYear("2018-06-26".toDateYMD(), 4, defaultFirstDayOfWeek))
        assertFalse("2018-05-28".toDateYMD().differWeekOfYear("2018-04-29".toDateYMD(), 4))

        assertTrue("2018-05-28".toDateYMD().createCalendar().differWeekOfMonth("2018-06-25".toDateYMD().createCalendar(), 4))
        assertTrue("2018-05-28".toDateYMD().differWeekOfMonth("2018-04-30".toDateYMD(), 4, defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-05-28".toDateYMD().differWeekOfMonth("2018-04-30".toDateYMD(), 4, Locale.getDefault()))
        assertFalse("2018-05-28".toDateYMD().differWeekOfMonth("2018-06-26".toDateYMD(), 4, defaultFirstDayOfWeek))
        assertFalse("2018-05-28".toDateYMD().differWeekOfMonth("2018-04-29".toDateYMD(), 4))

        assertTrue("2018-05-28".toDateYMD().createCalendar().differDayOfYear("2018-06-04".toDateYMD().createCalendar(), 7))
        assertTrue("2018-05-28".toDateYMD().differDayOfYear("2018-05-21".toDateYMD(), 7, Locale.getDefault()))
        assertFalse("2018-05-28".toDateYMD().differDayOfYear("2018-06-05".toDateYMD(), 7))
        assertFalse("2018-05-28".toDateYMD().differDayOfYear("2018-05-20".toDateYMD(), 7))

        assertTrue("2018-05-28".toDateYMD().createCalendar().differDayOfMonth("2018-06-04".toDateYMD().createCalendar(), 7))
        assertTrue("2018-05-28".toDateYMD().differDayOfMonth("2018-05-21".toDateYMD(), 7, Locale.getDefault()))
        assertFalse("2018-05-28".toDateYMD().differDayOfMonth("2018-06-05".toDateYMD(), 7))
        assertFalse("2018-05-28".toDateYMD().differDayOfMonth("2018-05-20".toDateYMD(), 7))

        assertTrue("2018-05-28".toDateYMD().createCalendar().differDayOfWeek("2018-06-04".toDateYMD().createCalendar(), 7))
        assertTrue("2018-05-28".toDateYMD().differDayOfWeek("2018-05-21".toDateYMD(), 7, defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-05-28".toDateYMD().differDayOfWeek("2018-05-21".toDateYMD(), 7, Locale.getDefault()))
        assertFalse("2018-05-28".toDateYMD().differDayOfWeek("2018-06-05".toDateYMD(), 7, defaultFirstDayOfWeek))
        assertFalse("2018-05-28".toDateYMD().differDayOfWeek("2018-05-20".toDateYMD(), 7))

        assertTrue("2018-05-28".toDateYMD().createCalendar().differDayOfWeekInMonth("2018-06-25".toDateYMD().createCalendar(), 4))
        assertTrue("2018-05-28".toDateYMD().differDayOfWeekInMonth("2018-04-30".toDateYMD(), 4, defaultFirstDayOfWeek, Locale.getDefault()))
        assertTrue("2018-05-28".toDateYMD().differDayOfWeekInMonth("2018-04-30".toDateYMD(), 4, Locale.getDefault()))
        assertFalse("2018-05-28".toDateYMD().differDayOfWeekInMonth("2018-06-26".toDateYMD(), 4, defaultFirstDayOfWeek))
        assertFalse("2018-05-28".toDateYMD().differDayOfWeekInMonth("2018-04-29".toDateYMD(), 4))

        assertTrue("2018-05-28 20".toDateYMDH().createCalendar().differHourOfDay("2018-05-29 3".toDateYMDH().createCalendar(), 7))
        assertTrue("2018-05-28 20".toDateYMDH().differHourOfDay("2018-05-28 13".toDateYMDH(), 7, Locale.getDefault()))
        assertFalse("2018-05-28 20".toDateYMDH().differHourOfDay("2018-05-29 4".toDateYMDH(), 7))
        assertFalse("2018-05-28 20".toDateYMDH().differHourOfDay("2018-05-28 12".toDateYMDH(), 7))

        assertTrue("2018-05-28 08".toDateYMDH().createCalendar().differHour("2018-05-28 15".toDateYMDH().createCalendar(), 7))
        assertTrue("2018-05-28 08".toDateYMDH().differHour("2018-05-28 01".toDateYMDH(), 7, Locale.getDefault()))
        assertFalse("2018-05-28 08".toDateYMDH().differHour("2018-05-28 16".toDateYMDH(), 7))
        assertFalse("2018-05-28 08".toDateYMDH().differHour("2018-05-28 00".toDateYMDH(), 7))

        assertTrue("2018-05-28 08:58".toDateYMDHM().createCalendar().differMinute("2018-05-28 09:05".toDateYMDHM().createCalendar(), 7))
        assertTrue("2018-05-28 08:58".toDateYMDHM().differMinute("2018-05-28 08:51".toDateYMDHM(), 7, Locale.getDefault()))
        assertFalse("2018-05-28 08:58".toDateYMDHM().differMinute("2018-05-28 09:06".toDateYMDHM(), 7))
        assertFalse("2018-05-28 08:58".toDateYMDHM().differMinute("2018-05-28 08:50".toDateYMDHM(), 7))

        assertTrue("2018-05-28 08:58:58".toDateYMDHMS().createCalendar().differSecond("2018-05-28 08:59:05".toDateYMDHMS().createCalendar(), 7))
        assertTrue("2018-05-28 08:58:58".toDateYMDHMS().differSecond("2018-05-28 08:58:51".toDateYMDHMS(), 7, Locale.getDefault()))
        assertFalse("2018-05-28 08:58:58".toDateYMDHMS().differSecond("2018-05-28 08:59:06".toDateYMDHMS(), 7))
        assertFalse("2018-05-28 08:58:58".toDateYMDHMS().differSecond("2018-05-28 08:58:50".toDateYMDHMS(), 7))

        assertTrue("2018-05-28 08:58:58 888".toDateYMDHMSM().createCalendar().differMillisecond("2018-05-28 08:58:58 895".toDateYMDHMSM().createCalendar(), 7))
        assertTrue("2018-05-28 08:58:58 888".toDateYMDHMSM().differMillisecond("2018-05-28 08:58:58 881".toDateYMDHMSM(), 7, Locale.getDefault()))
        assertFalse("2018-05-28 08:58:58 888".toDateYMDHMSM().differMillisecond("2018-05-28 08:58:58 896".toDateYMDHMSM(), 7))
        assertFalse("2018-05-28 08:58:58 888".toDateYMDHMSM().differMillisecond("2018-05-28 08:58:58 850".toDateYMDHMSM(), 7))
    }
}