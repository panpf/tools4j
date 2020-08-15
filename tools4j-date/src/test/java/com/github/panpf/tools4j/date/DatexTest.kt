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
package com.github.panpf.tools4j.date

import org.junit.Assert
import org.junit.Test
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DatexTest {

    @Test
    fun testFields() {
        val date1 = SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS").parse("2020/05/06 15:34:56 555")
        val date2 = SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS").parse("1999/04/02 19:56:23 999")

        Assert.assertEquals("2020", Datex.format(date1, Datex.y))
        Assert.assertEquals("1999", Datex.format(date2, Datex.y))

        Assert.assertEquals("202005", Datex.format(date1, Datex.yM_SHORT))
        Assert.assertEquals("199904", Datex.format(date2, Datex.yM_SHORT))

        Assert.assertEquals("2020-05", Datex.format(date1, Datex.yM))
        Assert.assertEquals("1999-04", Datex.format(date2, Datex.yM))

        Assert.assertEquals("20200506", Datex.format(date1, Datex.yMd_SHORT))
        Assert.assertEquals("19990402", Datex.format(date2, Datex.yMd_SHORT))

        Assert.assertEquals("2020-05-06", Datex.format(date1, Datex.yMd))
        Assert.assertEquals("1999-04-02", Datex.format(date2, Datex.yMd))

        Assert.assertEquals("2020-05-06 15", Datex.format(date1, Datex.yMdH))
        Assert.assertEquals("1999-04-02 19", Datex.format(date2, Datex.yMdH))

        Assert.assertEquals("2020-05-06 15:34", Datex.format(date1, Datex.yMdHm))
        Assert.assertEquals("1999-04-02 19:56", Datex.format(date2, Datex.yMdHm))

        Assert.assertEquals("2020-05-06 15:34:56", Datex.format(date1, Datex.yMdHms))
        Assert.assertEquals("1999-04-02 19:56:23", Datex.format(date2, Datex.yMdHms))

        Assert.assertEquals("2020-05-06 15:34:56 555", Datex.format(date1, Datex.yMdHmsS))
        Assert.assertEquals("1999-04-02 19:56:23 999", Datex.format(date2, Datex.yMdHmsS))
    }

    @Test
    fun testCreateCalendar() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        Assert.assertNotNull(Datex.createCalendar(Date()))
        Assert.assertNotNull(Datex.createCalendar(Date(), Locale.CANADA))
        Assert.assertNotNull(Datex.createCalendar(Date(), defaultFirstDayOfWeek))
        Assert.assertNotNull(Datex.createCalendar(Date(), defaultFirstDayOfWeek, Locale.CANADA))
    }

    @Test
    @Throws(ParseException::class)
    fun testToAndFormat() {
        val time = System.currentTimeMillis()
        Assert.assertEquals(Datex.toDate(time).time, time)

        Assert.assertEquals(Datex.format(Datex.toDate("2018", SimpleDateFormat("yyyy")), SimpleDateFormat("yyyy")), "2018")
        Assert.assertEquals(Datex.format(Datex.toDate("2018", "yyyy"), "yyyy"), "2018")
        Assert.assertEquals(Datex.format(Datex.toDate("2018", "yyyy", Locale.getDefault()), "yyyy", Locale.getDefault()), "2018")
    }

    @Test
    @Throws(ParseException::class)
    fun testGet() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        Assert.assertEquals(Datex.getCalendarField(Datex.toDate("2016-02-29 15:59:34 897", Datex.yMdHmsS), Calendar.MILLISECOND, defaultFirstDayOfWeek, Locale.getDefault()).toLong(), 897)
        Assert.assertEquals(Datex.getCalendarField(Datex.toDate("2016-02-29 15:59:34 897", Datex.yMdHmsS), Calendar.MILLISECOND, defaultFirstDayOfWeek).toLong(), 897)
        Assert.assertEquals(Datex.getCalendarField(Datex.toDate("2016-02-29 15:59:34 897", Datex.yMdHmsS), Calendar.MILLISECOND, Locale.getDefault()).toLong(), 897)
        Assert.assertEquals(Datex.getCalendarField(Datex.toDate("2016-02-29 15:59:34 897", Datex.yMdHmsS), Calendar.MILLISECOND).toLong(), 897)
    }

    @Test
    @Throws(ParseException::class)
    fun testDateAdd() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        Assert.assertEquals(Datex.format(Datex.addToDate(Datex.createCalendar(Datex.toDate("2016-02-29", Datex.yMd)), Calendar.YEAR, 1), Datex.yMd), "2017-02-28")

        Assert.assertEquals(Datex.format(Datex.addCalendarField(Datex.toDate("2016-02-29", Datex.yMd), Calendar.YEAR, 1), Datex.yMd), "2017-02-28")
        Assert.assertEquals(Datex.format(Datex.addCalendarField(Datex.toDate("2016-02-29", Datex.yMd), Calendar.YEAR, 1, defaultFirstDayOfWeek), Datex.yMd), "2017-02-28")
        Assert.assertEquals(Datex.format(Datex.addCalendarField(Datex.toDate("2016-02-29", Datex.yMd), Calendar.YEAR, 1, Locale.getDefault()), Datex.yMd), "2017-02-28")
        Assert.assertEquals(Datex.format(Datex.addCalendarField(Datex.toDate("2016-02-29", Datex.yMd), Calendar.YEAR, 1, defaultFirstDayOfWeek, Locale.getDefault()), Datex.yMd), "2017-02-28")
    }

    @Test
    @Throws(ParseException::class)
    fun testSame() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek
        val eraNoSameCalendar1 = Calendar.getInstance()
        val eraNoSameCalendar2 = Calendar.getInstance()
        eraNoSameCalendar2[Calendar.ERA] = 0

        Assert.assertTrue(Datex.isSameYear(Datex.createCalendar(Datex.toDate("2018", Datex.y)), Datex.createCalendar(Datex.toDate("2018", Datex.y))))
        Assert.assertFalse(Datex.isSameYear(Datex.toDate("2018", Datex.y), Datex.toDate("2017", Datex.y)))
        Assert.assertFalse(Datex.isSameYear(Datex.toDate("2018", Datex.y), Datex.toDate("2019", Datex.y), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameYear(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameMonth(Datex.createCalendar(Datex.toDate("2018-08", Datex.yM)), Datex.createCalendar(Datex.toDate("2018-08", Datex.yM))))
        Assert.assertTrue(Datex.isSameMonth(Datex.toDate("2018-08", Datex.yM), Datex.toDate("2018-08-01", Datex.yMd), Locale.getDefault()))
        Assert.assertTrue(Datex.isSameMonth(Datex.toDate("2018-08", Datex.yM), Datex.toDate("2018-08-31", Datex.yMd)))
        Assert.assertFalse(Datex.isSameMonth(Datex.toDate("2018-08", Datex.yM), Datex.toDate("2018-09", Datex.yM)))
        Assert.assertFalse(Datex.isSameMonth(Datex.toDate("2018-08", Datex.yM), Datex.toDate("2017-08", Datex.yM)))
        Assert.assertFalse(Datex.isSameMonth(Datex.toDate("2018-08", Datex.yM), Datex.toDate("2019-08", Datex.yM)))
        Assert.assertFalse(Datex.isSameMonth(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameMonthOfYear(Datex.createCalendar(Datex.toDate("2018-08", Datex.yM)), Datex.createCalendar(Datex.toDate("2019-08", Datex.yM))))
        Assert.assertTrue(Datex.isSameMonthOfYear(Datex.toDate("2018-08", Datex.yM), Datex.toDate("2019-08", Datex.yM), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameMonthOfYear(Datex.toDate("2018-08", Datex.yM), Datex.toDate("2019-07", Datex.yM)))
        Assert.assertFalse(Datex.isSameMonthOfYear(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameWeek(Datex.createCalendar(Datex.toDate("2018-08-07", Datex.yMd)), Datex.createCalendar(Datex.toDate("2018-08-05", Datex.yMd))))
        Assert.assertTrue(Datex.isSameWeek(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-11", Datex.yMd), defaultFirstDayOfWeek, Locale.getDefault()))
        Assert.assertTrue(Datex.isSameWeek(Datex.toDate("2018-08-31", Datex.yMd), Datex.toDate("2018-09-01", Datex.yMd), defaultFirstDayOfWeek))
        Assert.assertTrue(Datex.isSameWeek(Datex.toDate("2018-12-31", Datex.yMd), Datex.toDate("2019-01-01", Datex.yMd), Locale.getDefault()))
        Assert.assertTrue(Datex.isSameWeek(Datex.toDate("2019-01-01", Datex.yMd), Datex.toDate("2018-12-31", Datex.yMd)))
        Assert.assertFalse(Datex.isSameWeek(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-04", Datex.yMd)))
        Assert.assertFalse(Datex.isSameWeek(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-12", Datex.yMd)))
        Assert.assertFalse(Datex.isSameWeek(Datex.toDate("2019-12-31", Datex.yMd), Datex.toDate("2018-12-31", Datex.yMd)))
        Assert.assertFalse(Datex.isSameWeek(Datex.toDate("2018-12-31", Datex.yMd), Datex.toDate("2019-01-06", Datex.yMd)))
        Assert.assertFalse(Datex.isSameWeek(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameWeekOfYear(Datex.createCalendar(Datex.toDate("2018-08-07", Datex.yMd)), Datex.createCalendar(Datex.toDate("2019-08-04", Datex.yMd))))
        Assert.assertTrue(Datex.isSameWeekOfYear(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2019-08-10", Datex.yMd), defaultFirstDayOfWeek, Locale.getDefault()))
        Assert.assertFalse(Datex.isSameWeekOfYear(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2019-08-03", Datex.yMd), defaultFirstDayOfWeek))
        Assert.assertFalse(Datex.isSameWeekOfYear(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2019-08-11", Datex.yMd), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameWeekOfYear(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2019-08-11", Datex.yMd)))
        Assert.assertFalse(Datex.isSameWeekOfYear(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameWeekOfMonth(Datex.createCalendar(Datex.toDate("2018-08-07", Datex.yMd)), Datex.createCalendar(Datex.toDate("2018-07-08", Datex.yMd))))
        Assert.assertTrue(Datex.isSameWeekOfMonth(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-07-14", Datex.yMd), defaultFirstDayOfWeek, Locale.getDefault()))
        Assert.assertFalse(Datex.isSameWeekOfMonth(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-07-07", Datex.yMd), defaultFirstDayOfWeek))
        Assert.assertFalse(Datex.isSameWeekOfMonth(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-07-15", Datex.yMd), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameWeekOfMonth(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-07-15", Datex.yMd)))
        Assert.assertFalse(Datex.isSameWeekOfMonth(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameDay(Datex.createCalendar(Datex.toDate("2018-08-07", Datex.yMd)), Datex.createCalendar(Datex.toDate("2018-08-07 01", Datex.yMdH))))
        Assert.assertTrue(Datex.isSameDay(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-07 23", Datex.yMdH), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameDay(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-08", Datex.yMd)))
        Assert.assertFalse(Datex.isSameDay(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-06", Datex.yMd)))
        Assert.assertFalse(Datex.isSameDay(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2019-08-07", Datex.yMd)))
        Assert.assertFalse(Datex.isSameDay(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-09-07", Datex.yMd)))
        Assert.assertFalse(Datex.isSameDay(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameDayOfYear(Datex.createCalendar(Datex.toDate("2018-08-07", Datex.yMd)), Datex.createCalendar(Datex.toDate("2016-08-06", Datex.yMd))))
        Assert.assertTrue(Datex.isSameDayOfYear(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2016-08-06 01", Datex.yMdH), Locale.getDefault()))
        Assert.assertTrue(Datex.isSameDayOfYear(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2016-08-06 23", Datex.yMdH)))
        Assert.assertFalse(Datex.isSameDayOfYear(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2016-08-07", Datex.yMd)))
        Assert.assertFalse(Datex.isSameDayOfYear(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameDayOfMonth(Datex.createCalendar(Datex.toDate("2018-08-07", Datex.yMd)), Datex.createCalendar(Datex.toDate("2018-07-07", Datex.yMd))))
        Assert.assertTrue(Datex.isSameDayOfMonth(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2019-08-07", Datex.yMd), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameDayOfMonth(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-06", Datex.yMd)))
        Assert.assertFalse(Datex.isSameDayOfMonth(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameDayOfWeek(Datex.createCalendar(Datex.toDate("2018-08-07", Datex.yMd)), Datex.createCalendar(Datex.toDate("2018-08-14", Datex.yMd))))
        Assert.assertTrue(Datex.isSameDayOfWeek(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-21", Datex.yMd), defaultFirstDayOfWeek, Locale.getDefault()))
        Assert.assertTrue(Datex.isSameDayOfWeek(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-14", Datex.yMd), defaultFirstDayOfWeek))
        Assert.assertFalse(Datex.isSameDayOfWeek(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-08", Datex.yMd), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameDayOfWeek(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-15", Datex.yMd)))
        Assert.assertFalse(Datex.isSameDayOfWeek(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameDayOfWeekInMonth(Datex.createCalendar(Datex.toDate("2018-08-07", Datex.yMd)), Datex.createCalendar(Datex.toDate("2018-08-01", Datex.yMd))))
        Assert.assertTrue(Datex.isSameDayOfWeekInMonth(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-01", Datex.yMd), defaultFirstDayOfWeek, Locale.getDefault()))
        Assert.assertTrue(Datex.isSameDayOfWeekInMonth(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-01", Datex.yMd), defaultFirstDayOfWeek))
        Assert.assertTrue(Datex.isSameDayOfWeekInMonth(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-08-07", Datex.yMd), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameDayOfWeekInMonth(Datex.toDate("2018-08-07", Datex.yMd), Datex.toDate("2018-07-08", Datex.yMd)))
        Assert.assertFalse(Datex.isSameDayOfWeekInMonth(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameHour(Datex.createCalendar(Datex.toDate("2018-08-07 13", Datex.yMdH)), Datex.createCalendar(Datex.toDate("2018-08-07 13:01", Datex.yMdHm))))
        Assert.assertTrue(Datex.isSameHour(Datex.toDate("2018-08-07 13", Datex.yMdH), Datex.toDate("2018-08-07 13:59", Datex.yMdHm), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameHour(Datex.toDate("2018-08-07 13", Datex.yMdH), Datex.toDate("2018-08-07 14:00", Datex.yMdHm)))
        Assert.assertFalse(Datex.isSameHour(Datex.toDate("2018-08-07 13", Datex.yMdH), Datex.toDate("2018-08-07 12:59", Datex.yMdHm)))
        Assert.assertFalse(Datex.isSameHour(Datex.toDate("2018-08-07 13", Datex.yMdH), Datex.toDate("2019-08-07 12:59", Datex.yMdHm)))
        Assert.assertFalse(Datex.isSameHour(Datex.toDate("2018-08-07 13", Datex.yMdH), Datex.toDate("2018-09-07 12:59", Datex.yMdHm)))
        Assert.assertFalse(Datex.isSameHour(Datex.toDate("2018-08-07 13", Datex.yMdH), Datex.toDate("2018-08-06 12:59", Datex.yMdHm)))
        Assert.assertFalse(Datex.isSameHour(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameHourOf24H(Datex.createCalendar(Datex.toDate("2018-08-07 13", Datex.yMdH)), Datex.createCalendar(Datex.toDate("2018-08-06 13", Datex.yMdH))))
        Assert.assertTrue(Datex.isSameHourOf24H(Datex.toDate("2018-08-07 13", Datex.yMdH), Datex.toDate("2018-08-06 13", Datex.yMdH), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameHourOf24H(Datex.toDate("2018-08-07 13", Datex.yMdH), Datex.toDate("2018-08-06 14", Datex.yMdH)))
        Assert.assertFalse(Datex.isSameHourOf24H(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameHourOf12H(Datex.createCalendar(Datex.toDate("2018-08-07 13", Datex.yMdH)), Datex.createCalendar(Datex.toDate("2018-08-07 01", Datex.yMdH))))
        Assert.assertTrue(Datex.isSameHourOf12H(Datex.toDate("2018-08-07 13", Datex.yMdH), Datex.toDate("2018-08-07 01", Datex.yMdH), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameHourOf12H(Datex.toDate("2018-08-07 13", Datex.yMdH), Datex.toDate("2018-08-07 02", Datex.yMdH)))
        Assert.assertFalse(Datex.isSameHourOf12H(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameMinute(Datex.createCalendar(Datex.toDate("2018-08-07 13:01", Datex.yMdHm)), Datex.createCalendar(Datex.toDate("2018-08-07 13:01:01", Datex.yMdHms))))
        Assert.assertTrue(Datex.isSameMinute(Datex.toDate("2018-08-07 13:01", Datex.yMdHm), Datex.toDate("2018-08-07 13:01:59", Datex.yMdHms), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameMinute(Datex.toDate("2018-08-07 13:01", Datex.yMdHm), Datex.toDate("2018-08-07 13:02:00", Datex.yMdHms)))
        Assert.assertFalse(Datex.isSameMinute(Datex.toDate("2018-08-07 13:01", Datex.yMdHm), Datex.toDate("2018-08-07 13:00:59", Datex.yMdHms)))
        Assert.assertFalse(Datex.isSameMinute(Datex.toDate("2018-08-07 13:01", Datex.yMdHm), Datex.toDate("2019-08-07 13:00:59", Datex.yMdHms)))
        Assert.assertFalse(Datex.isSameMinute(Datex.toDate("2018-08-07 13:01", Datex.yMdHm), Datex.toDate("2018-09-07 13:00:59", Datex.yMdHms)))
        Assert.assertFalse(Datex.isSameMinute(Datex.toDate("2018-08-07 13:01", Datex.yMdHm), Datex.toDate("2018-08-06 13:00:59", Datex.yMdHms)))
        Assert.assertFalse(Datex.isSameMinute(Datex.toDate("2018-08-07 13:01", Datex.yMdHm), Datex.toDate("2018-08-07 14:00:59", Datex.yMdHms)))
        Assert.assertFalse(Datex.isSameMinute(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameMinuteOfHour(Datex.createCalendar(Datex.toDate("2018-08-07 13:01", Datex.yMdHm)), Datex.createCalendar(Datex.toDate("2018-08-07 14:01", Datex.yMdHm))))
        Assert.assertTrue(Datex.isSameMinuteOfHour(Datex.toDate("2018-08-07 13:01", Datex.yMdHm), Datex.toDate("2018-08-07 14:01", Datex.yMdHm), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameMinuteOfHour(Datex.toDate("2018-08-07 13:01", Datex.yMdHm), Datex.toDate("2018-08-07 14:02", Datex.yMdHm)))
        Assert.assertFalse(Datex.isSameMinuteOfHour(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameSecond(Datex.createCalendar(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms)), Datex.createCalendar(Datex.toDate("2018-08-07 13:01:25 001", Datex.yMdHmsS))))
        Assert.assertTrue(Datex.isSameSecond(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms), Datex.toDate("2018-08-07 13:01:25 999", Datex.yMdHmsS), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameSecond(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms), Datex.toDate("2018-08-07 13:01:26 000", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameSecond(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms), Datex.toDate("2018-08-07 13:01:24 999", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameSecond(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms), Datex.toDate("2019-08-07 13:01:24 999", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameSecond(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms), Datex.toDate("2018-09-07 13:01:24 999", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameSecond(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms), Datex.toDate("2018-08-06 13:01:24 999", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameSecond(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms), Datex.toDate("2018-08-07 14:01:24 999", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameSecond(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms), Datex.toDate("2018-08-07 13:02:24 999", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameSecond(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameSecondOfMinute(Datex.createCalendar(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms)), Datex.createCalendar(Datex.toDate("2018-08-07 13:02:25", Datex.yMdHms))))
        Assert.assertTrue(Datex.isSameSecondOfMinute(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms), Datex.toDate("2018-08-07 13:02:25", Datex.yMdHms), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameSecondOfMinute(Datex.toDate("2018-08-07 13:01:25", Datex.yMdHms), Datex.toDate("2018-08-07 13:02:26", Datex.yMdHms)))
        Assert.assertFalse(Datex.isSameSecondOfMinute(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameMillisecond(Datex.createCalendar(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS)), Datex.createCalendar(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS))))
        Assert.assertFalse(Datex.isSameMillisecond(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS), Datex.toDate("2018-08-07 13:01:25 332", Datex.yMdHmsS), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameMillisecond(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS), Datex.toDate("2019-08-07 13:01:26 332", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameMillisecond(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS), Datex.toDate("2018-09-07 13:01:26 332", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameMillisecond(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS), Datex.toDate("2018-08-06 13:01:26 332", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameMillisecond(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS), Datex.toDate("2018-08-07 14:01:26 332", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameMillisecond(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS), Datex.toDate("2018-08-07 13:02:26 332", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameMillisecond(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS), Datex.toDate("2018-08-07 13:01:27 332", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameMillisecond(eraNoSameCalendar1, eraNoSameCalendar2))

        Assert.assertTrue(Datex.isSameMillisecondOfSecond(Datex.createCalendar(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS)), Datex.createCalendar(Datex.toDate("2018-08-07 13:01:26 333", Datex.yMdHmsS))))
        Assert.assertTrue(Datex.isSameMillisecondOfSecond(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS), Datex.toDate("2018-08-07 13:01:26 333", Datex.yMdHmsS), Locale.getDefault()))
        Assert.assertFalse(Datex.isSameMillisecondOfSecond(Datex.toDate("2018-08-07 13:01:25 333", Datex.yMdHmsS), Datex.toDate("2018-08-07 13:01:26 334", Datex.yMdHmsS)))
        Assert.assertFalse(Datex.isSameMillisecondOfSecond(eraNoSameCalendar1, eraNoSameCalendar2))
    }

    @Test
    @Throws(ParseException::class)
    fun testDiffer() {
        val defaultFirstDayOfWeek = Calendar.getInstance().firstDayOfWeek

        Assert.assertTrue(Datex.differField(Datex.createCalendar(Datex.toDate("2018-05-28 08:58:58 888", Datex.yMdHmsS)), Datex.createCalendar(Datex.toDate("2018-05-28 08:58:58 895", Datex.yMdHmsS)), Calendar.MILLISECOND, 7))
        Assert.assertTrue(Datex.differField(Datex.createCalendar(Datex.toDate("2018-05-28 08:58:58 888", Datex.yMdHmsS)), Datex.createCalendar(Datex.toDate("2018-05-28 08:58:58 881", Datex.yMdHmsS)), Calendar.MILLISECOND, 7))
        Assert.assertTrue(Datex.differField(Datex.createCalendar(Datex.toDate("2018-05-28 08:58:58 888", Datex.yMdHmsS)), Datex.createCalendar(Datex.toDate("2018-05-28 08:58:58 881", Datex.yMdHmsS)), Calendar.MILLISECOND, 0))
        Assert.assertFalse(Datex.differField(Datex.createCalendar(Datex.toDate("2018-05-28 08:58:58 888", Datex.yMdHmsS)), Datex.createCalendar(Datex.toDate("2018-05-28 08:58:58 896", Datex.yMdHmsS)), Calendar.MILLISECOND, 7))
        Assert.assertFalse(Datex.differField(Datex.createCalendar(Datex.toDate("2018-05-28 08:58:58 888", Datex.yMdHmsS)), Datex.createCalendar(Datex.toDate("2018-05-28 08:58:58 850", Datex.yMdHmsS)), Calendar.MILLISECOND, 7))

        Assert.assertTrue(Datex.differCalendarField(Datex.toDate("2018-05-28 08:58:58 888", Datex.yMdHmsS), Datex.toDate("2018-05-28 08:58:58 895", Datex.yMdHmsS), Calendar.MILLISECOND, 7, defaultFirstDayOfWeek, Locale.getDefault()))
        Assert.assertTrue(Datex.differCalendarField(Datex.toDate("2018-05-28 08:58:58 888", Datex.yMdHmsS), Datex.toDate("2018-05-28 08:58:58 881", Datex.yMdHmsS), Calendar.MILLISECOND, 7, defaultFirstDayOfWeek))
        Assert.assertFalse(Datex.differCalendarField(Datex.toDate("2018-05-28 08:58:58 888", Datex.yMdHmsS), Datex.toDate("2018-05-28 08:58:58 896", Datex.yMdHmsS), Calendar.MILLISECOND, 7, Locale.getDefault()))
        Assert.assertFalse(Datex.differCalendarField(Datex.toDate("2018-05-28 08:58:58 888", Datex.yMdHmsS), Datex.toDate("2018-05-28 08:58:58 850", Datex.yMdHmsS), Calendar.MILLISECOND, 7))
    }
}