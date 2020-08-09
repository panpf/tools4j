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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

public final class DatexTest {

    @Test
    public final void testCreateCalendar() {
        int defaultFirstDayOfWeek = Calendar.getInstance().getFirstDayOfWeek();

        assertNotNull(Datex.createCalendar(new Date()));
        assertNotNull(Datex.createCalendar(new Date(), Locale.CANADA));
        assertNotNull(Datex.createCalendar(new Date(), defaultFirstDayOfWeek));
        assertNotNull(Datex.createCalendar(new Date(), defaultFirstDayOfWeek, Locale.CANADA));

        assertNotNull(Datex.createCalendar(System.currentTimeMillis()));
        assertNotNull(Datex.createCalendar(System.currentTimeMillis(), Locale.CANADA));
        assertNotNull(Datex.createCalendar(System.currentTimeMillis(), defaultFirstDayOfWeek));
        assertNotNull(Datex.createCalendar(System.currentTimeMillis(), defaultFirstDayOfWeek, Locale.CANADA));
    }

    @Test
    public final void testToAndFormat() throws ParseException {
        long time = System.currentTimeMillis();
        assertEquals(Datex.toDate(time).getTime(), time);

        assertEquals(Datex.format(Datex.toDate("2018", new SimpleDateFormat("yyyy")), new SimpleDateFormat("yyyy")), "2018");
        assertEquals(Datex.format(Datex.toDate("2018", "yyyy"), "yyyy"), "2018");
        assertEquals(Datex.format(Datex.toDate("2018", "yyyy", Locale.getDefault()), "yyyy", Locale.getDefault()), "2018");
        assertEquals(Datex.formatY(Datex.toDateY("2018")), "2018");
        assertEquals(Datex.formatY(Datex.toDateY("2018", Locale.getDefault()), Locale.getDefault()), "2018");
        assertEquals(Datex.formatYM(Datex.toDateYM("2018-06")), "2018-06");
        assertEquals(Datex.formatYM(Datex.toDateYM("2018-06", Locale.getDefault()), Locale.getDefault()), "2018-06");
        assertEquals(Datex.formatYMCompact(Datex.toDateYMCompact("201806")), "201806");
        assertEquals(Datex.formatYMCompact(Datex.toDateYMCompact("201806", Locale.getDefault()), Locale.getDefault()), "201806");
        assertEquals(Datex.formatYMD(Datex.toDateYMD("2018-06-23")), "2018-06-23");
        assertEquals(Datex.formatYMD(Datex.toDateYMD("2018-06-23", Locale.getDefault()), Locale.getDefault()), "2018-06-23");
        assertEquals(Datex.formatYMDCompact(Datex.toDateYMDCompact("20180623")), "20180623");
        assertEquals(Datex.formatYMDCompact(Datex.toDateYMDCompact("20180623", Locale.getDefault()), Locale.getDefault()), "20180623");
        assertEquals(Datex.formatYMDH(Datex.toDateYMDH("2018-06-23 21")), "2018-06-23 21");
        assertEquals(Datex.formatYMDH(Datex.toDateYMDH("2018-06-23 21", Locale.getDefault()), Locale.getDefault()), "2018-06-23 21");
        assertEquals(Datex.formatYMDHM(Datex.toDateYMDHM("2018-06-23 21:59")), "2018-06-23 21:59");
        assertEquals(Datex.formatYMDHM(Datex.toDateYMDHM("2018-06-23 21:59", Locale.getDefault()), Locale.getDefault()), "2018-06-23 21:59");
        assertEquals(Datex.formatYMDHMS(Datex.toDateYMDHMS("2018-06-23 21:59:01")), "2018-06-23 21:59:01");
        assertEquals(Datex.formatYMDHMS(Datex.toDateYMDHMS("2018-06-23 21:59:01", Locale.getDefault()), Locale.getDefault()), "2018-06-23 21:59:01");
        assertEquals(Datex.formatYMDHMSM(Datex.toDateYMDHMSM("2018-06-23 21:59:01 999")), "2018-06-23 21:59:01 999");
        assertEquals(Datex.formatYMDHMSM(Datex.toDateYMDHMSM("2018-06-23 21:59:01 999", Locale.getDefault()), Locale.getDefault()), "2018-06-23 21:59:01 999");

        assertEquals(Datex.formatYMD(Datex.toDateYMD("2016-02-29")), "2016-02-29");
        assertEquals(Datex.formatYMD(Datex.toDateYMD("2017-02-29")), "2017-03-01");


        assertEquals(Datex.format(Datex.toMillisecond("2018", new SimpleDateFormat("yyyy")), new SimpleDateFormat("yyyy")), "2018");
        assertEquals(Datex.format(Datex.toMillisecond("2018", "yyyy"), "yyyy"), "2018");
        assertEquals(Datex.format(Datex.toMillisecond("2018", "yyyy", Locale.getDefault()), "yyyy", Locale.getDefault()), "2018");
        assertEquals(Datex.formatY(Datex.toMillisecondY("2018")), "2018");
        assertEquals(Datex.formatY(Datex.toMillisecondY("2018", Locale.getDefault()), Locale.getDefault()), "2018");
        assertEquals(Datex.formatYM(Datex.toMillisecondYM("2018-06")), "2018-06");
        assertEquals(Datex.formatYM(Datex.toMillisecondYM("2018-06", Locale.getDefault()), Locale.getDefault()), "2018-06");
        assertEquals(Datex.formatYMCompact(Datex.toMillisecondYMCompact("201806")), "201806");
        assertEquals(Datex.formatYMCompact(Datex.toMillisecondYMCompact("201806", Locale.getDefault()), Locale.getDefault()), "201806");
        assertEquals(Datex.formatYMD(Datex.toMillisecondYMD("2018-06-23")), "2018-06-23");
        assertEquals(Datex.formatYMD(Datex.toMillisecondYMD("2018-06-23", Locale.getDefault()), Locale.getDefault()), "2018-06-23");
        assertEquals(Datex.formatYMDCompact(Datex.toMillisecondYMDCompact("20180623")), "20180623");
        assertEquals(Datex.formatYMDCompact(Datex.toMillisecondYMDCompact("20180623", Locale.getDefault()), Locale.getDefault()), "20180623");
        assertEquals(Datex.formatYMDH(Datex.toMillisecondYMDH("2018-06-23 21")), "2018-06-23 21");
        assertEquals(Datex.formatYMDH(Datex.toMillisecondYMDH("2018-06-23 21", Locale.getDefault()), Locale.getDefault()), "2018-06-23 21");
        assertEquals(Datex.formatYMDHM(Datex.toMillisecondYMDHM("2018-06-23 21:59")), "2018-06-23 21:59");
        assertEquals(Datex.formatYMDHM(Datex.toMillisecondYMDHM("2018-06-23 21:59", Locale.getDefault()), Locale.getDefault()), "2018-06-23 21:59");
        assertEquals(Datex.formatYMDHMS(Datex.toMillisecondYMDHMS("2018-06-23 21:59:01")), "2018-06-23 21:59:01");
        assertEquals(Datex.formatYMDHMS(Datex.toMillisecondYMDHMS("2018-06-23 21:59:01", Locale.getDefault()), Locale.getDefault()), "2018-06-23 21:59:01");
        assertEquals(Datex.formatYMDHMSM(Datex.toMillisecondYMDHMSM("2018-06-23 21:59:01 999")), "2018-06-23 21:59:01 999");
        assertEquals(Datex.formatYMDHMSM(Datex.toMillisecondYMDHMSM("2018-06-23 21:59:01 999", Locale.getDefault()), Locale.getDefault()), "2018-06-23 21:59:01 999");

        assertEquals(Datex.formatYMD(Datex.toMillisecondYMD("2016-02-29")), "2016-02-29");
        assertEquals(Datex.formatYMD(Datex.toMillisecondYMD("2017-02-29")), "2017-03-01");
    }

    @Test
    public final void testGet() throws ParseException {
        int defaultFirstDayOfWeek = Calendar.getInstance().getFirstDayOfWeek();

        assertEquals(Datex.getYear(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 2016);
        assertEquals(Datex.getCalendarYear(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 2016);
        assertEquals(Datex.getCalendarYear(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 2016);

        assertEquals(Datex.getMonth(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 1);
        assertEquals(Datex.getCalendarMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 1);
        assertEquals(Datex.getCalendarMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 1);

        assertEquals(Datex.getWeekOfYear(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 10);
        assertEquals(Datex.getCalendarWeekOfYear(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek), 10);
        assertEquals(Datex.getCalendarWeekOfYear(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 10);
        assertEquals(Datex.getCalendarWeekOfYear(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 10);
        assertEquals(Datex.getCalendarWeekOfYear(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek, Locale.getDefault()), 10);

        assertEquals(Datex.getWeekOfMonth(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 5);
        assertEquals(Datex.getCalendarWeekOfMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek), 5);
        assertEquals(Datex.getCalendarWeekOfMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 5);
        assertEquals(Datex.getCalendarWeekOfMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 5);
        assertEquals(Datex.getCalendarWeekOfMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek, Locale.getDefault()), 5);

        assertEquals(Datex.getDayOfYear(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 60);
        assertEquals(Datex.getCalendarDayOfYear(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 60);
        assertEquals(Datex.getCalendarDayOfYear(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 60);

        assertEquals(Datex.getDayOfMonth(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 29);
        assertEquals(Datex.getCalendarDayOfMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 29);
        assertEquals(Datex.getCalendarDayOfMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 29);

        assertEquals(Datex.getDayOfWeekInMonth(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 5);
        assertEquals(Datex.getCalendarDayOfWeekInMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek), 5);
        assertEquals(Datex.getCalendarDayOfWeekInMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 5);
        assertEquals(Datex.getCalendarDayOfWeekInMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 5);
        assertEquals(Datex.getCalendarDayOfWeekInMonth(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek, Locale.getDefault()), 5);

        assertEquals(Datex.getDayOfWeek(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 2);
        assertEquals(Datex.getCalendarDayOfWeek(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 2);
        assertEquals(Datex.getCalendarDayOfWeek(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek), 2);
        assertEquals(Datex.getCalendarDayOfWeek(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 2);
        assertEquals(Datex.getCalendarDayOfWeek(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek, Locale.getDefault()), 2);

        assertEquals(Datex.getHourOfDay(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 15);
        assertEquals(Datex.getCalendarHourOfDay(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 15);
        assertEquals(Datex.getCalendarHourOfDay(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 15);

        assertEquals(Datex.getHour(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 3);
        assertEquals(Datex.getCalendarHour(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 3);
        assertEquals(Datex.getCalendarHour(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 3);

        assertEquals(Datex.getMinute(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 59);
        assertEquals(Datex.getCalendarMinute(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 59);
        assertEquals(Datex.getCalendarMinute(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 59);

        assertEquals(Datex.getSecond(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 34);
        assertEquals(Datex.getCalendarSecond(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 34);
        assertEquals(Datex.getCalendarSecond(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 34);

        assertEquals(Datex.getMillisecond(Datex.createCalendar(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"))), 897);
        assertEquals(Datex.getCalendarMillisecond(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897")), 897);
        assertEquals(Datex.getCalendarMillisecond(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 897);

        assertEquals(Datex.getCalendarField(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Calendar.MILLISECOND, defaultFirstDayOfWeek, Locale.getDefault()), 897);
        assertEquals(Datex.getCalendarField(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Calendar.MILLISECOND, defaultFirstDayOfWeek), 897);
        assertEquals(Datex.getCalendarField(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Calendar.MILLISECOND, Locale.getDefault()), 897);
        assertEquals(Datex.getCalendarField(Datex.toDateYMDHMSM("2016-02-29 15:59:34 897"), Calendar.MILLISECOND), 897);


        assertEquals(Datex.getYear(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 2016);
        assertEquals(Datex.getCalendarYear(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 2016);
        assertEquals(Datex.getCalendarYear(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 2016);

        assertEquals(Datex.getMonth(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 1);
        assertEquals(Datex.getCalendarMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 1);
        assertEquals(Datex.getCalendarMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 1);

        assertEquals(Datex.getWeekOfYear(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 10);
        assertEquals(Datex.getCalendarWeekOfYear(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek), 10);
        assertEquals(Datex.getCalendarWeekOfYear(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 10);
        assertEquals(Datex.getCalendarWeekOfYear(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 10);
        assertEquals(Datex.getCalendarWeekOfYear(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek, Locale.getDefault()), 10);

        assertEquals(Datex.getWeekOfMonth(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 5);
        assertEquals(Datex.getCalendarWeekOfMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek), 5);
        assertEquals(Datex.getCalendarWeekOfMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 5);
        assertEquals(Datex.getCalendarWeekOfMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 5);
        assertEquals(Datex.getCalendarWeekOfMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek, Locale.getDefault()), 5);

        assertEquals(Datex.getDayOfYear(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 60);
        assertEquals(Datex.getCalendarDayOfYear(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 60);
        assertEquals(Datex.getCalendarDayOfYear(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 60);

        assertEquals(Datex.getDayOfMonth(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 29);
        assertEquals(Datex.getCalendarDayOfMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 29);
        assertEquals(Datex.getCalendarDayOfMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 29);

        assertEquals(Datex.getDayOfWeekInMonth(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 5);
        assertEquals(Datex.getCalendarDayOfWeekInMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek), 5);
        assertEquals(Datex.getCalendarDayOfWeekInMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 5);
        assertEquals(Datex.getCalendarDayOfWeekInMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 5);
        assertEquals(Datex.getCalendarDayOfWeekInMonth(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek, Locale.getDefault()), 5);

        assertEquals(Datex.getDayOfWeek(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 2);
        assertEquals(Datex.getCalendarDayOfWeek(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 2);
        assertEquals(Datex.getCalendarDayOfWeek(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek), 2);
        assertEquals(Datex.getCalendarDayOfWeek(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 2);
        assertEquals(Datex.getCalendarDayOfWeek(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), defaultFirstDayOfWeek, Locale.getDefault()), 2);

        assertEquals(Datex.getHourOfDay(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 15);
        assertEquals(Datex.getCalendarHourOfDay(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 15);
        assertEquals(Datex.getCalendarHourOfDay(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 15);

        assertEquals(Datex.getHour(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 3);
        assertEquals(Datex.getCalendarHour(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 3);
        assertEquals(Datex.getCalendarHour(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 3);

        assertEquals(Datex.getMinute(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 59);
        assertEquals(Datex.getCalendarMinute(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 59);
        assertEquals(Datex.getCalendarMinute(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 59);

        assertEquals(Datex.getSecond(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 34);
        assertEquals(Datex.getCalendarSecond(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 34);
        assertEquals(Datex.getCalendarSecond(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 34);

        assertEquals(Datex.getMillisecond(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"))), 897);
        assertEquals(Datex.getCalendarMillisecond(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897")), 897);
        assertEquals(Datex.getCalendarMillisecond(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Locale.getDefault()), 897);

        assertEquals(Datex.getCalendarField(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Calendar.MILLISECOND, defaultFirstDayOfWeek, Locale.getDefault()), 897);
        assertEquals(Datex.getCalendarField(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Calendar.MILLISECOND, defaultFirstDayOfWeek), 897);
        assertEquals(Datex.getCalendarField(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Calendar.MILLISECOND, Locale.getDefault()), 897);
        assertEquals(Datex.getCalendarField(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:34 897"), Calendar.MILLISECOND), 897);
    }

    @Test
    public final void testDateAdd() throws ParseException {
        int defaultFirstDayOfWeek = Calendar.getInstance().getFirstDayOfWeek();

        assertEquals(Datex.formatYMD(Datex.addToDate(Datex.createCalendar(Datex.toDateYMD("2016-02-29")), Calendar.YEAR, 1)), "2017-02-28");

        assertEquals(Datex.formatYMD(Datex.addCalendarField(Datex.toDateYMD("2016-02-29"), Calendar.YEAR, 1)), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addCalendarField(Datex.toDateYMD("2016-02-29"), Calendar.YEAR, 1, defaultFirstDayOfWeek)), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addCalendarField(Datex.toDateYMD("2016-02-29"), Calendar.YEAR, 1, Locale.getDefault())), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addCalendarField(Datex.toDateYMD("2016-02-29"), Calendar.YEAR, 1, defaultFirstDayOfWeek, Locale.getDefault())), "2017-02-28");

        assertEquals(Datex.formatYMD(Datex.addYear(Datex.toDateYMD("2016-02-29"), 1)), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addYear(Datex.toDateYMD("2016-02-29"), -1)), "2015-02-28");
        assertEquals(Datex.formatYMD(Datex.addYear(Datex.toDateYMD("2016-02-29"), 1, Locale.getDefault())), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addYear(Datex.toDateYMD("2016-02-29"), -1, Locale.getDefault())), "2015-02-28");

        assertEquals(Datex.formatYMD(Datex.addMonth(Datex.toDateYMD("2017-01-29"), 1)), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addMonth(Datex.toDateYMD("2017-03-29"), -1)), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addMonth(Datex.toDateYMD("2017-01-29"), 1, Locale.getDefault())), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addMonth(Datex.toDateYMD("2017-03-29"), -1, Locale.getDefault())), "2017-02-28");

        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toDateYMD("2016-02-10"), 1)), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toDateYMD("2016-02-10"), 1, defaultFirstDayOfWeek)), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toDateYMD("2016-02-10"), -1, defaultFirstDayOfWeek)), "2016-02-03");
        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toDateYMD("2016-02-10"), 1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toDateYMD("2016-02-10"), 1, Locale.getDefault())), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toDateYMD("2016-02-10"), -1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-03");

        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toDateYMD("2016-02-10"), 1)), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toDateYMD("2016-02-10"), 1, defaultFirstDayOfWeek)), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toDateYMD("2016-02-10"), -1, defaultFirstDayOfWeek)), "2016-02-03");
        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toDateYMD("2016-02-10"), 1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toDateYMD("2016-02-10"), 1, Locale.getDefault())), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toDateYMD("2016-02-10"), -1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-03");

        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toDateYMD("2016-02-29"), 1)), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toDateYMD("2016-02-29"), 1, defaultFirstDayOfWeek)), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toDateYMD("2016-03-01"), -1, defaultFirstDayOfWeek)), "2016-02-29");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toDateYMD("2016-02-29"), 1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toDateYMD("2016-02-29"), 1, Locale.getDefault())), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toDateYMD("2016-03-01"), -1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-29");

        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toDateYMD("2016-02-29"), 1)), "2016-03-07");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toDateYMD("2016-02-29"), 1, defaultFirstDayOfWeek)), "2016-03-07");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toDateYMD("2016-03-07"), -1, defaultFirstDayOfWeek)), "2016-02-29");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toDateYMD("2016-02-29"), 1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-03-07");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toDateYMD("2016-02-29"), 1, Locale.getDefault())), "2016-03-07");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toDateYMD("2016-03-07"), -1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-29");

        assertEquals(Datex.formatYMD(Datex.addDayOfMonth(Datex.toDateYMD("2016-02-29"), 1)), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfMonth(Datex.toDateYMD("2016-03-01"), -1)), "2016-02-29");
        assertEquals(Datex.formatYMD(Datex.addDayOfMonth(Datex.toDateYMD("2016-02-29"), 1, Locale.getDefault())), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfMonth(Datex.toDateYMD("2016-03-01"), -1, Locale.getDefault())), "2016-02-29");

        assertEquals(Datex.formatYMD(Datex.addDayOfYear(Datex.toDateYMD("2016-02-29"), 1)), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfYear(Datex.toDateYMD("2016-03-01"), -1)), "2016-02-29");
        assertEquals(Datex.formatYMD(Datex.addDayOfYear(Datex.toDateYMD("2016-02-29"), 1, Locale.getDefault())), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfYear(Datex.toDateYMD("2016-03-01"), -1, Locale.getDefault())), "2016-02-29");

        assertEquals(Datex.formatYMDHMS(Datex.addHour(Datex.toDateYMDHMS("2016-02-29 23:26:33"), 1)), "2016-03-01 00:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHour(Datex.toDateYMDHMS("2016-03-01 00:26:33"), -1)), "2016-02-29 23:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHour(Datex.toDateYMDHMS("2016-02-29 23:26:33"), 1, Locale.getDefault())), "2016-03-01 00:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHour(Datex.toDateYMDHMS("2016-03-01 00:26:33"), -1, Locale.getDefault())), "2016-02-29 23:26:33");

        assertEquals(Datex.formatYMDHMS(Datex.addHourOfDay(Datex.toDateYMDHMS("2016-02-29 23:26:33"), 1)), "2016-03-01 00:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHourOfDay(Datex.toDateYMDHMS("2016-03-01 00:26:33"), -1)), "2016-02-29 23:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHourOfDay(Datex.toDateYMDHMS("2016-02-29 23:26:33"), 1, Locale.getDefault())), "2016-03-01 00:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHourOfDay(Datex.toDateYMDHMS("2016-03-01 00:26:33"), -1, Locale.getDefault())), "2016-02-29 23:26:33");

        assertEquals(Datex.formatYMDHMS(Datex.addMinute(Datex.toDateYMDHMS("2016-02-29 15:59:33"), 1)), "2016-02-29 16:00:33");
        assertEquals(Datex.formatYMDHMS(Datex.addMinute(Datex.toDateYMDHMS("2016-02-29 16:00:33"), -1)), "2016-02-29 15:59:33");
        assertEquals(Datex.formatYMDHMS(Datex.addMinute(Datex.toDateYMDHMS("2016-02-29 15:59:33"), 1, Locale.getDefault())), "2016-02-29 16:00:33");
        assertEquals(Datex.formatYMDHMS(Datex.addMinute(Datex.toDateYMDHMS("2016-02-29 16:00:33"), -1, Locale.getDefault())), "2016-02-29 15:59:33");

        assertEquals(Datex.formatYMDHMS(Datex.addSecond(Datex.toDateYMDHMS("2016-02-29 15:59:59"), 1)), "2016-02-29 16:00:00");
        assertEquals(Datex.formatYMDHMS(Datex.addSecond(Datex.toDateYMDHMS("2016-02-29 16:00:00"), -1)), "2016-02-29 15:59:59");
        assertEquals(Datex.formatYMDHMS(Datex.addSecond(Datex.toDateYMDHMS("2016-02-29 15:59:59"), 1, Locale.getDefault())), "2016-02-29 16:00:00");
        assertEquals(Datex.formatYMDHMS(Datex.addSecond(Datex.toDateYMDHMS("2016-02-29 16:00:00"), -1, Locale.getDefault())), "2016-02-29 15:59:59");

        assertEquals(Datex.formatYMDHMSM(Datex.addMillisecond(Datex.toDateYMDHMSM("2016-02-29 15:59:59 999"), 1)), "2016-02-29 16:00:00 000");
        assertEquals(Datex.formatYMDHMSM(Datex.addMillisecond(Datex.toDateYMDHMSM("2016-02-29 16:00:00 000"), -1)), "2016-02-29 15:59:59 999");
        assertEquals(Datex.formatYMDHMSM(Datex.addMillisecond(Datex.toDateYMDHMSM("2016-02-29 15:59:59 999"), 1, Locale.getDefault())), "2016-02-29 16:00:00 000");
        assertEquals(Datex.formatYMDHMSM(Datex.addMillisecond(Datex.toDateYMDHMSM("2016-02-29 16:00:00 000"), -1, Locale.getDefault())), "2016-02-29 15:59:59 999");


        assertEquals(Datex.formatYMD(Datex.addToDate(Datex.createCalendar(Datex.toMillisecondYMD("2016-02-29")), Calendar.YEAR, 1)), "2017-02-28");

        assertEquals(Datex.formatYMD(Datex.addCalendarField(Datex.toMillisecondYMD("2016-02-29"), Calendar.YEAR, 1)), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addCalendarField(Datex.toMillisecondYMD("2016-02-29"), Calendar.YEAR, 1, defaultFirstDayOfWeek)), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addCalendarField(Datex.toMillisecondYMD("2016-02-29"), Calendar.YEAR, 1, Locale.getDefault())), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addCalendarField(Datex.toMillisecondYMD("2016-02-29"), Calendar.YEAR, 1, defaultFirstDayOfWeek, Locale.getDefault())), "2017-02-28");

        assertEquals(Datex.formatYMD(Datex.addYear(Datex.toMillisecondYMD("2016-02-29"), 1)), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addYear(Datex.toMillisecondYMD("2016-02-29"), -1)), "2015-02-28");
        assertEquals(Datex.formatYMD(Datex.addYear(Datex.toMillisecondYMD("2016-02-29"), 1, Locale.getDefault())), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addYear(Datex.toMillisecondYMD("2016-02-29"), -1, Locale.getDefault())), "2015-02-28");

        assertEquals(Datex.formatYMD(Datex.addMonth(Datex.toMillisecondYMD("2017-01-29"), 1)), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addMonth(Datex.toMillisecondYMD("2017-03-29"), -1)), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addMonth(Datex.toMillisecondYMD("2017-01-29"), 1, Locale.getDefault())), "2017-02-28");
        assertEquals(Datex.formatYMD(Datex.addMonth(Datex.toMillisecondYMD("2017-03-29"), -1, Locale.getDefault())), "2017-02-28");

        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toMillisecondYMD("2016-02-10"), 1)), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toMillisecondYMD("2016-02-10"), 1, defaultFirstDayOfWeek)), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toMillisecondYMD("2016-02-10"), -1, defaultFirstDayOfWeek)), "2016-02-03");
        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toMillisecondYMD("2016-02-10"), 1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toMillisecondYMD("2016-02-10"), 1, Locale.getDefault())), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfMonth(Datex.toMillisecondYMD("2016-02-10"), -1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-03");

        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toMillisecondYMD("2016-02-10"), 1)), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toMillisecondYMD("2016-02-10"), 1, defaultFirstDayOfWeek)), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toMillisecondYMD("2016-02-10"), -1, defaultFirstDayOfWeek)), "2016-02-03");
        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toMillisecondYMD("2016-02-10"), 1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toMillisecondYMD("2016-02-10"), 1, Locale.getDefault())), "2016-02-17");
        assertEquals(Datex.formatYMD(Datex.addWeekOfYear(Datex.toMillisecondYMD("2016-02-10"), -1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-03");

        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toMillisecondYMD("2016-02-29"), 1)), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toMillisecondYMD("2016-02-29"), 1, defaultFirstDayOfWeek)), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toMillisecondYMD("2016-03-01"), -1, defaultFirstDayOfWeek)), "2016-02-29");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toMillisecondYMD("2016-02-29"), 1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toMillisecondYMD("2016-02-29"), 1, Locale.getDefault())), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeek(Datex.toMillisecondYMD("2016-03-01"), -1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-29");

        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toMillisecondYMD("2016-02-29"), 1)), "2016-03-07");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toMillisecondYMD("2016-02-29"), 1, defaultFirstDayOfWeek)), "2016-03-07");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toMillisecondYMD("2016-03-07"), -1, defaultFirstDayOfWeek)), "2016-02-29");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toMillisecondYMD("2016-02-29"), 1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-03-07");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toMillisecondYMD("2016-02-29"), 1, Locale.getDefault())), "2016-03-07");
        assertEquals(Datex.formatYMD(Datex.addDayOfWeekInMonth(Datex.toMillisecondYMD("2016-03-07"), -1, defaultFirstDayOfWeek, Locale.getDefault())), "2016-02-29");

        assertEquals(Datex.formatYMD(Datex.addDayOfMonth(Datex.toMillisecondYMD("2016-02-29"), 1)), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfMonth(Datex.toMillisecondYMD("2016-03-01"), -1)), "2016-02-29");
        assertEquals(Datex.formatYMD(Datex.addDayOfMonth(Datex.toMillisecondYMD("2016-02-29"), 1, Locale.getDefault())), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfMonth(Datex.toMillisecondYMD("2016-03-01"), -1, Locale.getDefault())), "2016-02-29");

        assertEquals(Datex.formatYMD(Datex.addDayOfYear(Datex.toMillisecondYMD("2016-02-29"), 1)), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfYear(Datex.toMillisecondYMD("2016-03-01"), -1)), "2016-02-29");
        assertEquals(Datex.formatYMD(Datex.addDayOfYear(Datex.toMillisecondYMD("2016-02-29"), 1, Locale.getDefault())), "2016-03-01");
        assertEquals(Datex.formatYMD(Datex.addDayOfYear(Datex.toMillisecondYMD("2016-03-01"), -1, Locale.getDefault())), "2016-02-29");

        assertEquals(Datex.formatYMDHMS(Datex.addHour(Datex.toMillisecondYMDHMS("2016-02-29 23:26:33"), 1)), "2016-03-01 00:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHour(Datex.toMillisecondYMDHMS("2016-03-01 00:26:33"), -1)), "2016-02-29 23:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHour(Datex.toMillisecondYMDHMS("2016-02-29 23:26:33"), 1, Locale.getDefault())), "2016-03-01 00:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHour(Datex.toMillisecondYMDHMS("2016-03-01 00:26:33"), -1, Locale.getDefault())), "2016-02-29 23:26:33");

        assertEquals(Datex.formatYMDHMS(Datex.addHourOfDay(Datex.toMillisecondYMDHMS("2016-02-29 23:26:33"), 1)), "2016-03-01 00:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHourOfDay(Datex.toMillisecondYMDHMS("2016-03-01 00:26:33"), -1)), "2016-02-29 23:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHourOfDay(Datex.toMillisecondYMDHMS("2016-02-29 23:26:33"), 1, Locale.getDefault())), "2016-03-01 00:26:33");
        assertEquals(Datex.formatYMDHMS(Datex.addHourOfDay(Datex.toMillisecondYMDHMS("2016-03-01 00:26:33"), -1, Locale.getDefault())), "2016-02-29 23:26:33");

        assertEquals(Datex.formatYMDHMS(Datex.addMinute(Datex.toMillisecondYMDHMS("2016-02-29 15:59:33"), 1)), "2016-02-29 16:00:33");
        assertEquals(Datex.formatYMDHMS(Datex.addMinute(Datex.toMillisecondYMDHMS("2016-02-29 16:00:33"), -1)), "2016-02-29 15:59:33");
        assertEquals(Datex.formatYMDHMS(Datex.addMinute(Datex.toMillisecondYMDHMS("2016-02-29 15:59:33"), 1, Locale.getDefault())), "2016-02-29 16:00:33");
        assertEquals(Datex.formatYMDHMS(Datex.addMinute(Datex.toMillisecondYMDHMS("2016-02-29 16:00:33"), -1, Locale.getDefault())), "2016-02-29 15:59:33");

        assertEquals(Datex.formatYMDHMS(Datex.addSecond(Datex.toMillisecondYMDHMS("2016-02-29 15:59:59"), 1)), "2016-02-29 16:00:00");
        assertEquals(Datex.formatYMDHMS(Datex.addSecond(Datex.toMillisecondYMDHMS("2016-02-29 16:00:00"), -1)), "2016-02-29 15:59:59");
        assertEquals(Datex.formatYMDHMS(Datex.addSecond(Datex.toMillisecondYMDHMS("2016-02-29 15:59:59"), 1, Locale.getDefault())), "2016-02-29 16:00:00");
        assertEquals(Datex.formatYMDHMS(Datex.addSecond(Datex.toMillisecondYMDHMS("2016-02-29 16:00:00"), -1, Locale.getDefault())), "2016-02-29 15:59:59");

        assertEquals(Datex.formatYMDHMSM(Datex.addMillisecond(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:59 999"), 1)), "2016-02-29 16:00:00 000");
        assertEquals(Datex.formatYMDHMSM(Datex.addMillisecond(Datex.toMillisecondYMDHMSM("2016-02-29 16:00:00 000"), -1)), "2016-02-29 15:59:59 999");
        assertEquals(Datex.formatYMDHMSM(Datex.addMillisecond(Datex.toMillisecondYMDHMSM("2016-02-29 15:59:59 999"), 1, Locale.getDefault())), "2016-02-29 16:00:00 000");
        assertEquals(Datex.formatYMDHMSM(Datex.addMillisecond(Datex.toMillisecondYMDHMSM("2016-02-29 16:00:00 000"), -1, Locale.getDefault())), "2016-02-29 15:59:59 999");
    }

    @Test
    public final void testSame() throws ParseException {
        int defaultFirstDayOfWeek = Calendar.getInstance().getFirstDayOfWeek();

        Calendar eraNoSameCalendar1 = Calendar.getInstance();
        Calendar eraNoSameCalendar2 = Calendar.getInstance();
        eraNoSameCalendar2.set(Calendar.ERA, 0);

        assertTrue(Datex.isSameYear(Datex.createCalendar(Datex.toDateY("2018")), Datex.createCalendar(Datex.toDateY("2018"))));
        assertFalse(Datex.isSameYear(Datex.toDateY("2018"), Datex.toDateY("2017")));
        assertFalse(Datex.isSameYear(Datex.toDateY("2018"), Datex.toDateY("2019"), Locale.getDefault()));
        assertFalse(Datex.isSameYear(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMonth(Datex.createCalendar(Datex.toDateYM("2018-08")), Datex.createCalendar(Datex.toDateYM("2018-08"))));
        assertTrue(Datex.isSameMonth(Datex.toDateYM("2018-08"), Datex.toDateYMD("2018-08-01"), Locale.getDefault()));
        assertTrue(Datex.isSameMonth(Datex.toDateYM("2018-08"), Datex.toDateYMD("2018-08-31")));
        assertFalse(Datex.isSameMonth(Datex.toDateYM("2018-08"), Datex.toDateYM("2018-09")));
        assertFalse(Datex.isSameMonth(Datex.toDateYM("2018-08"), Datex.toDateYM("2017-08")));
        assertFalse(Datex.isSameMonth(Datex.toDateYM("2018-08"), Datex.toDateYM("2019-08")));
        assertFalse(Datex.isSameMonth(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMonthOfYear(Datex.createCalendar(Datex.toDateYM("2018-08")), Datex.createCalendar(Datex.toDateYM("2019-08"))));
        assertTrue(Datex.isSameMonthOfYear(Datex.toDateYM("2018-08"), Datex.toDateYM("2019-08"), Locale.getDefault()));
        assertFalse(Datex.isSameMonthOfYear(Datex.toDateYM("2018-08"), Datex.toDateYM("2019-07")));
        assertFalse(Datex.isSameMonthOfYear(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameWeek(Datex.createCalendar(Datex.toDateYMD("2018-08-07")), Datex.createCalendar(Datex.toDateYMD("2018-08-05"))));
        assertTrue(Datex.isSameWeek(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-11"), defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.isSameWeek(Datex.toDateYMD("2018-08-31"), Datex.toDateYMD("2018-09-01"), defaultFirstDayOfWeek));
        assertTrue(Datex.isSameWeek(Datex.toDateYMD("2018-12-31"), Datex.toDateYMD("2019-01-01"), Locale.getDefault()));
        assertTrue(Datex.isSameWeek(Datex.toDateYMD("2019-01-01"), Datex.toDateYMD("2018-12-31")));
        assertFalse(Datex.isSameWeek(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-04")));
        assertFalse(Datex.isSameWeek(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-12")));
        assertFalse(Datex.isSameWeek(Datex.toDateYMD("2019-12-31"), Datex.toDateYMD("2018-12-31")));
        assertFalse(Datex.isSameWeek(Datex.toDateYMD("2018-12-31"), Datex.toDateYMD("2019-01-06")));
        assertFalse(Datex.isSameWeek(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameWeekOfYear(Datex.createCalendar(Datex.toDateYMD("2018-08-07")), Datex.createCalendar(Datex.toDateYMD("2019-08-04"))));
        assertTrue(Datex.isSameWeekOfYear(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2019-08-10"), defaultFirstDayOfWeek, Locale.getDefault()));
        assertFalse(Datex.isSameWeekOfYear(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2019-08-03"), defaultFirstDayOfWeek));
        assertFalse(Datex.isSameWeekOfYear(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2019-08-11"), Locale.getDefault()));
        assertFalse(Datex.isSameWeekOfYear(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2019-08-11")));
        assertFalse(Datex.isSameWeekOfYear(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameWeekOfMonth(Datex.createCalendar(Datex.toDateYMD("2018-08-07")), Datex.createCalendar(Datex.toDateYMD("2018-07-08"))));
        assertTrue(Datex.isSameWeekOfMonth(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-07-14"), defaultFirstDayOfWeek, Locale.getDefault()));
        assertFalse(Datex.isSameWeekOfMonth(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-07-07"), defaultFirstDayOfWeek));
        assertFalse(Datex.isSameWeekOfMonth(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-07-15"), Locale.getDefault()));
        assertFalse(Datex.isSameWeekOfMonth(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-07-15")));
        assertFalse(Datex.isSameWeekOfMonth(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameDay(Datex.createCalendar(Datex.toDateYMD("2018-08-07")), Datex.createCalendar(Datex.toDateYMDH("2018-08-07 01"))));
        assertTrue(Datex.isSameDay(Datex.toDateYMD("2018-08-07"), Datex.toDateYMDH("2018-08-07 23"), Locale.getDefault()));
        assertFalse(Datex.isSameDay(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-08")));
        assertFalse(Datex.isSameDay(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-06")));
        assertFalse(Datex.isSameDay(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2019-08-07")));
        assertFalse(Datex.isSameDay(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-09-07")));
        assertFalse(Datex.isSameDay(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameDayOfYear(Datex.createCalendar(Datex.toDateYMD("2018-08-07")), Datex.createCalendar(Datex.toDateYMD("2016-08-06"))));
        assertTrue(Datex.isSameDayOfYear(Datex.toDateYMD("2018-08-07"), Datex.toDateYMDH("2016-08-06 01"), Locale.getDefault()));
        assertTrue(Datex.isSameDayOfYear(Datex.toDateYMD("2018-08-07"), Datex.toDateYMDH("2016-08-06 23")));
        assertFalse(Datex.isSameDayOfYear(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2016-08-07")));
        assertFalse(Datex.isSameDayOfYear(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameDayOfMonth(Datex.createCalendar(Datex.toDateYMD("2018-08-07")), Datex.createCalendar(Datex.toDateYMD("2018-07-07"))));
        assertTrue(Datex.isSameDayOfMonth(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2019-08-07"), Locale.getDefault()));
        assertFalse(Datex.isSameDayOfMonth(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-06")));
        assertFalse(Datex.isSameDayOfMonth(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameDayOfWeek(Datex.createCalendar(Datex.toDateYMD("2018-08-07")), Datex.createCalendar(Datex.toDateYMD("2018-08-14"))));
        assertTrue(Datex.isSameDayOfWeek(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-21"), defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.isSameDayOfWeek(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-14"), defaultFirstDayOfWeek));
        assertFalse(Datex.isSameDayOfWeek(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-08"), Locale.getDefault()));
        assertFalse(Datex.isSameDayOfWeek(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-15")));
        assertFalse(Datex.isSameDayOfWeek(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameDayOfWeekInMonth(Datex.createCalendar(Datex.toDateYMD("2018-08-07")), Datex.createCalendar(Datex.toDateYMD("2018-08-01"))));
        assertTrue(Datex.isSameDayOfWeekInMonth(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-01"), defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.isSameDayOfWeekInMonth(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-01"), defaultFirstDayOfWeek));
        assertTrue(Datex.isSameDayOfWeekInMonth(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-08-07"), Locale.getDefault()));
        assertFalse(Datex.isSameDayOfWeekInMonth(Datex.toDateYMD("2018-08-07"), Datex.toDateYMD("2018-07-08")));
        assertFalse(Datex.isSameDayOfWeekInMonth(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameHour(Datex.createCalendar(Datex.toDateYMDH("2018-08-07 13")), Datex.createCalendar(Datex.toDateYMDHM("2018-08-07 13:01"))));
        assertTrue(Datex.isSameHour(Datex.toDateYMDH("2018-08-07 13"), Datex.toDateYMDHM("2018-08-07 13:59"), Locale.getDefault()));
        assertFalse(Datex.isSameHour(Datex.toDateYMDH("2018-08-07 13"), Datex.toDateYMDHM("2018-08-07 14:00")));
        assertFalse(Datex.isSameHour(Datex.toDateYMDH("2018-08-07 13"), Datex.toDateYMDHM("2018-08-07 12:59")));
        assertFalse(Datex.isSameHour(Datex.toDateYMDH("2018-08-07 13"), Datex.toDateYMDHM("2019-08-07 12:59")));
        assertFalse(Datex.isSameHour(Datex.toDateYMDH("2018-08-07 13"), Datex.toDateYMDHM("2018-09-07 12:59")));
        assertFalse(Datex.isSameHour(Datex.toDateYMDH("2018-08-07 13"), Datex.toDateYMDHM("2018-08-06 12:59")));
        assertFalse(Datex.isSameHour(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameHourOf24H(Datex.createCalendar(Datex.toDateYMDH("2018-08-07 13")), Datex.createCalendar(Datex.toDateYMDH("2018-08-06 13"))));
        assertTrue(Datex.isSameHourOf24H(Datex.toDateYMDH("2018-08-07 13"), Datex.toDateYMDH("2018-08-06 13"), Locale.getDefault()));
        assertFalse(Datex.isSameHourOf24H(Datex.toDateYMDH("2018-08-07 13"), Datex.toDateYMDH("2018-08-06 14")));
        assertFalse(Datex.isSameHourOf24H(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameHourOf12H(Datex.createCalendar(Datex.toDateYMDH("2018-08-07 13")), Datex.createCalendar(Datex.toDateYMDH("2018-08-07 01"))));
        assertTrue(Datex.isSameHourOf12H(Datex.toDateYMDH("2018-08-07 13"), Datex.toDateYMDH("2018-08-07 01"), Locale.getDefault()));
        assertFalse(Datex.isSameHourOf12H(Datex.toDateYMDH("2018-08-07 13"), Datex.toDateYMDH("2018-08-07 02")));
        assertFalse(Datex.isSameHourOf12H(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMinute(Datex.createCalendar(Datex.toDateYMDHM("2018-08-07 13:01")), Datex.createCalendar(Datex.toDateYMDHMS("2018-08-07 13:01:01"))));
        assertTrue(Datex.isSameMinute(Datex.toDateYMDHM("2018-08-07 13:01"), Datex.toDateYMDHMS("2018-08-07 13:01:59"), Locale.getDefault()));
        assertFalse(Datex.isSameMinute(Datex.toDateYMDHM("2018-08-07 13:01"), Datex.toDateYMDHMS("2018-08-07 13:02:00")));
        assertFalse(Datex.isSameMinute(Datex.toDateYMDHM("2018-08-07 13:01"), Datex.toDateYMDHMS("2018-08-07 13:00:59")));
        assertFalse(Datex.isSameMinute(Datex.toDateYMDHM("2018-08-07 13:01"), Datex.toDateYMDHMS("2019-08-07 13:00:59")));
        assertFalse(Datex.isSameMinute(Datex.toDateYMDHM("2018-08-07 13:01"), Datex.toDateYMDHMS("2018-09-07 13:00:59")));
        assertFalse(Datex.isSameMinute(Datex.toDateYMDHM("2018-08-07 13:01"), Datex.toDateYMDHMS("2018-08-06 13:00:59")));
        assertFalse(Datex.isSameMinute(Datex.toDateYMDHM("2018-08-07 13:01"), Datex.toDateYMDHMS("2018-08-07 14:00:59")));
        assertFalse(Datex.isSameMinute(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMinuteOfHour(Datex.createCalendar(Datex.toDateYMDHM("2018-08-07 13:01")), Datex.createCalendar(Datex.toDateYMDHM("2018-08-07 14:01"))));
        assertTrue(Datex.isSameMinuteOfHour(Datex.toDateYMDHM("2018-08-07 13:01"), Datex.toDateYMDHM("2018-08-07 14:01"), Locale.getDefault()));
        assertFalse(Datex.isSameMinuteOfHour(Datex.toDateYMDHM("2018-08-07 13:01"), Datex.toDateYMDHM("2018-08-07 14:02")));
        assertFalse(Datex.isSameMinuteOfHour(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameSecond(Datex.createCalendar(Datex.toDateYMDHMS("2018-08-07 13:01:25")), Datex.createCalendar(Datex.toDateYMDHMSM("2018-08-07 13:01:25 001"))));
        assertTrue(Datex.isSameSecond(Datex.toDateYMDHMS("2018-08-07 13:01:25"), Datex.toDateYMDHMSM("2018-08-07 13:01:25 999"), Locale.getDefault()));
        assertFalse(Datex.isSameSecond(Datex.toDateYMDHMS("2018-08-07 13:01:25"), Datex.toDateYMDHMSM("2018-08-07 13:01:26 000")));
        assertFalse(Datex.isSameSecond(Datex.toDateYMDHMS("2018-08-07 13:01:25"), Datex.toDateYMDHMSM("2018-08-07 13:01:24 999")));
        assertFalse(Datex.isSameSecond(Datex.toDateYMDHMS("2018-08-07 13:01:25"), Datex.toDateYMDHMSM("2019-08-07 13:01:24 999")));
        assertFalse(Datex.isSameSecond(Datex.toDateYMDHMS("2018-08-07 13:01:25"), Datex.toDateYMDHMSM("2018-09-07 13:01:24 999")));
        assertFalse(Datex.isSameSecond(Datex.toDateYMDHMS("2018-08-07 13:01:25"), Datex.toDateYMDHMSM("2018-08-06 13:01:24 999")));
        assertFalse(Datex.isSameSecond(Datex.toDateYMDHMS("2018-08-07 13:01:25"), Datex.toDateYMDHMSM("2018-08-07 14:01:24 999")));
        assertFalse(Datex.isSameSecond(Datex.toDateYMDHMS("2018-08-07 13:01:25"), Datex.toDateYMDHMSM("2018-08-07 13:02:24 999")));
        assertFalse(Datex.isSameSecond(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameSecondOfMinute(Datex.createCalendar(Datex.toDateYMDHMS("2018-08-07 13:01:25")), Datex.createCalendar(Datex.toDateYMDHMS("2018-08-07 13:02:25"))));
        assertTrue(Datex.isSameSecondOfMinute(Datex.toDateYMDHMS("2018-08-07 13:01:25"), Datex.toDateYMDHMS("2018-08-07 13:02:25"), Locale.getDefault()));
        assertFalse(Datex.isSameSecondOfMinute(Datex.toDateYMDHMS("2018-08-07 13:01:25"), Datex.toDateYMDHMS("2018-08-07 13:02:26")));
        assertFalse(Datex.isSameSecondOfMinute(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMillisecond(Datex.createCalendar(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333")), Datex.createCalendar(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333"))));
        assertFalse(Datex.isSameMillisecond(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333"), Datex.toDateYMDHMSM("2018-08-07 13:01:25 332"), Locale.getDefault()));
        assertFalse(Datex.isSameMillisecond(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333"), Datex.toDateYMDHMSM("2019-08-07 13:01:26 332")));
        assertFalse(Datex.isSameMillisecond(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333"), Datex.toDateYMDHMSM("2018-09-07 13:01:26 332")));
        assertFalse(Datex.isSameMillisecond(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333"), Datex.toDateYMDHMSM("2018-08-06 13:01:26 332")));
        assertFalse(Datex.isSameMillisecond(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333"), Datex.toDateYMDHMSM("2018-08-07 14:01:26 332")));
        assertFalse(Datex.isSameMillisecond(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333"), Datex.toDateYMDHMSM("2018-08-07 13:02:26 332")));
        assertFalse(Datex.isSameMillisecond(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333"), Datex.toDateYMDHMSM("2018-08-07 13:01:27 332")));
        assertFalse(Datex.isSameMillisecond(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMillisecondOfSecond(Datex.createCalendar(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333")), Datex.createCalendar(Datex.toDateYMDHMSM("2018-08-07 13:01:26 333"))));
        assertTrue(Datex.isSameMillisecondOfSecond(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333"), Datex.toDateYMDHMSM("2018-08-07 13:01:26 333"), Locale.getDefault()));
        assertFalse(Datex.isSameMillisecondOfSecond(Datex.toDateYMDHMSM("2018-08-07 13:01:25 333"), Datex.toDateYMDHMSM("2018-08-07 13:01:26 334")));
        assertFalse(Datex.isSameMillisecondOfSecond(eraNoSameCalendar1, eraNoSameCalendar2));


        assertTrue(Datex.isSameYear(Datex.createCalendar(Datex.toMillisecondY("2018")), Datex.createCalendar(Datex.toMillisecondY("2018"))));
        assertFalse(Datex.isSameYear(Datex.toMillisecondY("2018"), Datex.toMillisecondY("2017")));
        assertFalse(Datex.isSameYear(Datex.toMillisecondY("2018"), Datex.toMillisecondY("2019"), Locale.getDefault()));
        assertFalse(Datex.isSameYear(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMonth(Datex.createCalendar(Datex.toMillisecondYM("2018-08")), Datex.createCalendar(Datex.toMillisecondYM("2018-08"))));
        assertTrue(Datex.isSameMonth(Datex.toMillisecondYM("2018-08"), Datex.toMillisecondYMD("2018-08-01"), Locale.getDefault()));
        assertTrue(Datex.isSameMonth(Datex.toMillisecondYM("2018-08"), Datex.toMillisecondYMD("2018-08-31")));
        assertFalse(Datex.isSameMonth(Datex.toMillisecondYM("2018-08"), Datex.toMillisecondYM("2018-09")));
        assertFalse(Datex.isSameMonth(Datex.toMillisecondYM("2018-08"), Datex.toMillisecondYM("2017-08")));
        assertFalse(Datex.isSameMonth(Datex.toMillisecondYM("2018-08"), Datex.toMillisecondYM("2019-08")));
        assertFalse(Datex.isSameMonth(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMonthOfYear(Datex.createCalendar(Datex.toMillisecondYM("2018-08")), Datex.createCalendar(Datex.toMillisecondYM("2019-08"))));
        assertTrue(Datex.isSameMonthOfYear(Datex.toMillisecondYM("2018-08"), Datex.toMillisecondYM("2019-08"), Locale.getDefault()));
        assertFalse(Datex.isSameMonthOfYear(Datex.toMillisecondYM("2018-08"), Datex.toMillisecondYM("2019-07")));
        assertFalse(Datex.isSameMonthOfYear(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameWeek(Datex.createCalendar(Datex.toMillisecondYMD("2018-08-07")), Datex.createCalendar(Datex.toMillisecondYMD("2018-08-05"))));
        assertTrue(Datex.isSameWeek(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-11"), defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.isSameWeek(Datex.toMillisecondYMD("2018-08-31"), Datex.toMillisecondYMD("2018-09-01"), defaultFirstDayOfWeek));
        assertTrue(Datex.isSameWeek(Datex.toMillisecondYMD("2018-12-31"), Datex.toMillisecondYMD("2019-01-01"), Locale.getDefault()));
        assertTrue(Datex.isSameWeek(Datex.toMillisecondYMD("2019-01-01"), Datex.toMillisecondYMD("2018-12-31")));
        assertFalse(Datex.isSameWeek(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-04")));
        assertFalse(Datex.isSameWeek(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-12")));
        assertFalse(Datex.isSameWeek(Datex.toMillisecondYMD("2019-12-31"), Datex.toMillisecondYMD("2018-12-31")));
        assertFalse(Datex.isSameWeek(Datex.toMillisecondYMD("2018-12-31"), Datex.toMillisecondYMD("2019-01-06")));
        assertFalse(Datex.isSameWeek(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameWeekOfYear(Datex.createCalendar(Datex.toMillisecondYMD("2018-08-07")), Datex.createCalendar(Datex.toMillisecondYMD("2019-08-04"))));
        assertTrue(Datex.isSameWeekOfYear(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2019-08-10"), defaultFirstDayOfWeek, Locale.getDefault()));
        assertFalse(Datex.isSameWeekOfYear(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2019-08-03"), defaultFirstDayOfWeek));
        assertFalse(Datex.isSameWeekOfYear(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2019-08-11"), Locale.getDefault()));
        assertFalse(Datex.isSameWeekOfYear(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2019-08-11")));
        assertFalse(Datex.isSameWeekOfYear(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameWeekOfMonth(Datex.createCalendar(Datex.toMillisecondYMD("2018-08-07")), Datex.createCalendar(Datex.toMillisecondYMD("2018-07-08"))));
        assertTrue(Datex.isSameWeekOfMonth(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-07-14"), defaultFirstDayOfWeek, Locale.getDefault()));
        assertFalse(Datex.isSameWeekOfMonth(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-07-07"), defaultFirstDayOfWeek));
        assertFalse(Datex.isSameWeekOfMonth(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-07-15"), Locale.getDefault()));
        assertFalse(Datex.isSameWeekOfMonth(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-07-15")));
        assertFalse(Datex.isSameWeekOfMonth(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameDay(Datex.createCalendar(Datex.toMillisecondYMD("2018-08-07")), Datex.createCalendar(Datex.toMillisecondYMDH("2018-08-07 01"))));
        assertTrue(Datex.isSameDay(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMDH("2018-08-07 23"), Locale.getDefault()));
        assertFalse(Datex.isSameDay(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-08")));
        assertFalse(Datex.isSameDay(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-06")));
        assertFalse(Datex.isSameDay(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2019-08-07")));
        assertFalse(Datex.isSameDay(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-09-07")));
        assertFalse(Datex.isSameDay(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameDayOfYear(Datex.createCalendar(Datex.toMillisecondYMD("2018-08-07")), Datex.createCalendar(Datex.toMillisecondYMD("2016-08-06"))));
        assertTrue(Datex.isSameDayOfYear(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMDH("2016-08-06 01"), Locale.getDefault()));
        assertTrue(Datex.isSameDayOfYear(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMDH("2016-08-06 23")));
        assertFalse(Datex.isSameDayOfYear(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2016-08-07")));
        assertFalse(Datex.isSameDayOfYear(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameDayOfMonth(Datex.createCalendar(Datex.toMillisecondYMD("2018-08-07")), Datex.createCalendar(Datex.toMillisecondYMD("2018-07-07"))));
        assertTrue(Datex.isSameDayOfMonth(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2019-08-07"), Locale.getDefault()));
        assertFalse(Datex.isSameDayOfMonth(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-06")));
        assertFalse(Datex.isSameDayOfMonth(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameDayOfWeek(Datex.createCalendar(Datex.toMillisecondYMD("2018-08-07")), Datex.createCalendar(Datex.toMillisecondYMD("2018-08-14"))));
        assertTrue(Datex.isSameDayOfWeek(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-21"), defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.isSameDayOfWeek(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-14"), defaultFirstDayOfWeek));
        assertFalse(Datex.isSameDayOfWeek(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-08"), Locale.getDefault()));
        assertFalse(Datex.isSameDayOfWeek(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-15")));
        assertFalse(Datex.isSameDayOfWeek(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameDayOfWeekInMonth(Datex.createCalendar(Datex.toMillisecondYMD("2018-08-07")), Datex.createCalendar(Datex.toMillisecondYMD("2018-08-01"))));
        assertTrue(Datex.isSameDayOfWeekInMonth(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-01"), defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.isSameDayOfWeekInMonth(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-01"), defaultFirstDayOfWeek));
        assertTrue(Datex.isSameDayOfWeekInMonth(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-08-07"), Locale.getDefault()));
        assertFalse(Datex.isSameDayOfWeekInMonth(Datex.toMillisecondYMD("2018-08-07"), Datex.toMillisecondYMD("2018-07-08")));
        assertFalse(Datex.isSameDayOfWeekInMonth(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameHour(Datex.createCalendar(Datex.toMillisecondYMDH("2018-08-07 13")), Datex.createCalendar(Datex.toMillisecondYMDHM("2018-08-07 13:01"))));
        assertTrue(Datex.isSameHour(Datex.toMillisecondYMDH("2018-08-07 13"), Datex.toMillisecondYMDHM("2018-08-07 13:59"), Locale.getDefault()));
        assertFalse(Datex.isSameHour(Datex.toMillisecondYMDH("2018-08-07 13"), Datex.toMillisecondYMDHM("2018-08-07 14:00")));
        assertFalse(Datex.isSameHour(Datex.toMillisecondYMDH("2018-08-07 13"), Datex.toMillisecondYMDHM("2018-08-07 12:59")));
        assertFalse(Datex.isSameHour(Datex.toMillisecondYMDH("2018-08-07 13"), Datex.toMillisecondYMDHM("2019-08-07 12:59")));
        assertFalse(Datex.isSameHour(Datex.toMillisecondYMDH("2018-08-07 13"), Datex.toMillisecondYMDHM("2018-09-07 12:59")));
        assertFalse(Datex.isSameHour(Datex.toMillisecondYMDH("2018-08-07 13"), Datex.toMillisecondYMDHM("2018-08-06 12:59")));
        assertFalse(Datex.isSameHour(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameHourOf24H(Datex.createCalendar(Datex.toMillisecondYMDH("2018-08-07 13")), Datex.createCalendar(Datex.toMillisecondYMDH("2018-08-06 13"))));
        assertTrue(Datex.isSameHourOf24H(Datex.toMillisecondYMDH("2018-08-07 13"), Datex.toMillisecondYMDH("2018-08-06 13"), Locale.getDefault()));
        assertFalse(Datex.isSameHourOf24H(Datex.toMillisecondYMDH("2018-08-07 13"), Datex.toMillisecondYMDH("2018-08-06 14")));
        assertFalse(Datex.isSameHourOf24H(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameHourOf12H(Datex.createCalendar(Datex.toMillisecondYMDH("2018-08-07 13")), Datex.createCalendar(Datex.toMillisecondYMDH("2018-08-07 01"))));
        assertTrue(Datex.isSameHourOf12H(Datex.toMillisecondYMDH("2018-08-07 13"), Datex.toMillisecondYMDH("2018-08-07 01"), Locale.getDefault()));
        assertFalse(Datex.isSameHourOf12H(Datex.toMillisecondYMDH("2018-08-07 13"), Datex.toMillisecondYMDH("2018-08-07 02")));
        assertFalse(Datex.isSameHourOf12H(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMinute(Datex.createCalendar(Datex.toMillisecondYMDHM("2018-08-07 13:01")), Datex.createCalendar(Datex.toMillisecondYMDHMS("2018-08-07 13:01:01"))));
        assertTrue(Datex.isSameMinute(Datex.toMillisecondYMDHM("2018-08-07 13:01"), Datex.toMillisecondYMDHMS("2018-08-07 13:01:59"), Locale.getDefault()));
        assertFalse(Datex.isSameMinute(Datex.toMillisecondYMDHM("2018-08-07 13:01"), Datex.toMillisecondYMDHMS("2018-08-07 13:02:00")));
        assertFalse(Datex.isSameMinute(Datex.toMillisecondYMDHM("2018-08-07 13:01"), Datex.toMillisecondYMDHMS("2018-08-07 13:00:59")));
        assertFalse(Datex.isSameMinute(Datex.toMillisecondYMDHM("2018-08-07 13:01"), Datex.toMillisecondYMDHMS("2019-08-07 13:00:59")));
        assertFalse(Datex.isSameMinute(Datex.toMillisecondYMDHM("2018-08-07 13:01"), Datex.toMillisecondYMDHMS("2018-09-07 13:00:59")));
        assertFalse(Datex.isSameMinute(Datex.toMillisecondYMDHM("2018-08-07 13:01"), Datex.toMillisecondYMDHMS("2018-08-06 13:00:59")));
        assertFalse(Datex.isSameMinute(Datex.toMillisecondYMDHM("2018-08-07 13:01"), Datex.toMillisecondYMDHMS("2018-08-07 14:00:59")));
        assertFalse(Datex.isSameMinute(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMinuteOfHour(Datex.createCalendar(Datex.toMillisecondYMDHM("2018-08-07 13:01")), Datex.createCalendar(Datex.toMillisecondYMDHM("2018-08-07 14:01"))));
        assertTrue(Datex.isSameMinuteOfHour(Datex.toMillisecondYMDHM("2018-08-07 13:01"), Datex.toMillisecondYMDHM("2018-08-07 14:01"), Locale.getDefault()));
        assertFalse(Datex.isSameMinuteOfHour(Datex.toMillisecondYMDHM("2018-08-07 13:01"), Datex.toMillisecondYMDHM("2018-08-07 14:02")));
        assertFalse(Datex.isSameMinuteOfHour(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameSecond(Datex.createCalendar(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25")), Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 001"))));
        assertTrue(Datex.isSameSecond(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25"), Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 999"), Locale.getDefault()));
        assertFalse(Datex.isSameSecond(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25"), Datex.toMillisecondYMDHMSM("2018-08-07 13:01:26 000")));
        assertFalse(Datex.isSameSecond(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25"), Datex.toMillisecondYMDHMSM("2018-08-07 13:01:24 999")));
        assertFalse(Datex.isSameSecond(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25"), Datex.toMillisecondYMDHMSM("2019-08-07 13:01:24 999")));
        assertFalse(Datex.isSameSecond(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25"), Datex.toMillisecondYMDHMSM("2018-09-07 13:01:24 999")));
        assertFalse(Datex.isSameSecond(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25"), Datex.toMillisecondYMDHMSM("2018-08-06 13:01:24 999")));
        assertFalse(Datex.isSameSecond(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25"), Datex.toMillisecondYMDHMSM("2018-08-07 14:01:24 999")));
        assertFalse(Datex.isSameSecond(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25"), Datex.toMillisecondYMDHMSM("2018-08-07 13:02:24 999")));
        assertFalse(Datex.isSameSecond(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameSecondOfMinute(Datex.createCalendar(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25")), Datex.createCalendar(Datex.toMillisecondYMDHMS("2018-08-07 13:02:25"))));
        assertTrue(Datex.isSameSecondOfMinute(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25"), Datex.toMillisecondYMDHMS("2018-08-07 13:02:25"), Locale.getDefault()));
        assertFalse(Datex.isSameSecondOfMinute(Datex.toMillisecondYMDHMS("2018-08-07 13:01:25"), Datex.toMillisecondYMDHMS("2018-08-07 13:02:26")));
        assertFalse(Datex.isSameSecondOfMinute(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMillisecond(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333")), Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333"))));
        assertFalse(Datex.isSameMillisecond(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333"), Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 332"), Locale.getDefault()));
        assertFalse(Datex.isSameMillisecond(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333"), Datex.toMillisecondYMDHMSM("2019-08-07 13:01:26 332")));
        assertFalse(Datex.isSameMillisecond(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333"), Datex.toMillisecondYMDHMSM("2018-09-07 13:01:26 332")));
        assertFalse(Datex.isSameMillisecond(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333"), Datex.toMillisecondYMDHMSM("2018-08-06 13:01:26 332")));
        assertFalse(Datex.isSameMillisecond(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333"), Datex.toMillisecondYMDHMSM("2018-08-07 14:01:26 332")));
        assertFalse(Datex.isSameMillisecond(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333"), Datex.toMillisecondYMDHMSM("2018-08-07 13:02:26 332")));
        assertFalse(Datex.isSameMillisecond(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333"), Datex.toMillisecondYMDHMSM("2018-08-07 13:01:27 332")));
        assertFalse(Datex.isSameMillisecond(eraNoSameCalendar1, eraNoSameCalendar2));

        assertTrue(Datex.isSameMillisecondOfSecond(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333")), Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:26 333"))));
        assertTrue(Datex.isSameMillisecondOfSecond(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333"), Datex.toMillisecondYMDHMSM("2018-08-07 13:01:26 333"), Locale.getDefault()));
        assertFalse(Datex.isSameMillisecondOfSecond(Datex.toMillisecondYMDHMSM("2018-08-07 13:01:25 333"), Datex.toMillisecondYMDHMSM("2018-08-07 13:01:26 334")));
        assertFalse(Datex.isSameMillisecondOfSecond(eraNoSameCalendar1, eraNoSameCalendar2));
    }

    @Test
    public final void testDiffer() throws ParseException {
        int defaultFirstDayOfWeek = Calendar.getInstance().getFirstDayOfWeek();

        assertTrue(Datex.differField(Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 895")), Calendar.MILLISECOND, 7));
        assertTrue(Datex.differField(Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 881")), Calendar.MILLISECOND, 7));
        assertTrue(Datex.differField(Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 881")), Calendar.MILLISECOND, 0));
        assertFalse(Datex.differField(Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 896")), Calendar.MILLISECOND, 7));
        assertFalse(Datex.differField(Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 850")), Calendar.MILLISECOND, 7));

        assertTrue(Datex.differCalendarField(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888"), Datex.toDateYMDHMSM("2018-05-28 08:58:58 895"), Calendar.MILLISECOND, 7, defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.differCalendarField(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888"), Datex.toDateYMDHMSM("2018-05-28 08:58:58 881"), Calendar.MILLISECOND, 7, defaultFirstDayOfWeek));
        assertFalse(Datex.differCalendarField(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888"), Datex.toDateYMDHMSM("2018-05-28 08:58:58 896"), Calendar.MILLISECOND, 7, Locale.getDefault()));
        assertFalse(Datex.differCalendarField(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888"), Datex.toDateYMDHMSM("2018-05-28 08:58:58 850"), Calendar.MILLISECOND, 7));

        assertTrue(Datex.differYear(Datex.createCalendar(Datex.toDateY("2018")), Datex.createCalendar(Datex.toDateY("2011")), 7));
        assertTrue(Datex.differYear(Datex.toDateY("2018"), Datex.toDateY("2025"), 7, Locale.getDefault()));
        assertFalse(Datex.differYear(Datex.toDateY("2018"), Datex.toDateY("2010"), 7));
        assertFalse(Datex.differYear(Datex.toDateY("2018"), Datex.toDateY("2026"), 7));

        assertTrue(Datex.differMonth(Datex.createCalendar(Datex.toDateYM("2018-05")), Datex.createCalendar(Datex.toDateYM("2018-12")), 7));
        assertTrue(Datex.differMonth(Datex.toDateYM("2018-05"), Datex.toDateYM("2017-10"), 7, Locale.getDefault()));
        assertFalse(Datex.differMonth(Datex.toDateYM("2018-05"), Datex.toDateYM("2019-01"), 7));
        assertFalse(Datex.differMonth(Datex.toDateYM("2018-05"), Datex.toDateYM("2017-09"), 7));

        assertTrue(Datex.differWeekOfYear(Datex.createCalendar(Datex.toDateYMD("2018-05-28")), Datex.createCalendar(Datex.toDateYMD("2018-06-25")), 4));
        assertTrue(Datex.differWeekOfYear(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-04-30"), 4, defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.differWeekOfYear(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-04-30"), 4, Locale.getDefault()));
        assertFalse(Datex.differWeekOfYear(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-06-26"), 4, defaultFirstDayOfWeek));
        assertFalse(Datex.differWeekOfYear(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-04-29"), 4));

        assertTrue(Datex.differWeekOfMonth(Datex.createCalendar(Datex.toDateYMD("2018-05-28")), Datex.createCalendar(Datex.toDateYMD("2018-06-25")), 4));
        assertTrue(Datex.differWeekOfMonth(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-04-30"), 4, defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.differWeekOfMonth(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-04-30"), 4, Locale.getDefault()));
        assertFalse(Datex.differWeekOfMonth(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-06-26"), 4, defaultFirstDayOfWeek));
        assertFalse(Datex.differWeekOfMonth(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-04-29"), 4));

        assertTrue(Datex.differDayOfYear(Datex.createCalendar(Datex.toDateYMD("2018-05-28")), Datex.createCalendar(Datex.toDateYMD("2018-06-04")), 7));
        assertTrue(Datex.differDayOfYear(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-05-21"), 7, Locale.getDefault()));
        assertFalse(Datex.differDayOfYear(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-06-05"), 7));
        assertFalse(Datex.differDayOfYear(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-05-20"), 7));

        assertTrue(Datex.differDayOfMonth(Datex.createCalendar(Datex.toDateYMD("2018-05-28")), Datex.createCalendar(Datex.toDateYMD("2018-06-04")), 7));
        assertTrue(Datex.differDayOfMonth(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-05-21"), 7, Locale.getDefault()));
        assertFalse(Datex.differDayOfMonth(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-06-05"), 7));
        assertFalse(Datex.differDayOfMonth(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-05-20"), 7));

        assertTrue(Datex.differDayOfWeek(Datex.createCalendar(Datex.toDateYMD("2018-05-28")), Datex.createCalendar(Datex.toDateYMD("2018-06-04")), 7));
        assertTrue(Datex.differDayOfWeek(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-05-21"), 7, defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.differDayOfWeek(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-05-21"), 7, Locale.getDefault()));
        assertFalse(Datex.differDayOfWeek(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-06-05"), 7, defaultFirstDayOfWeek));
        assertFalse(Datex.differDayOfWeek(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-05-20"), 7));

        assertTrue(Datex.differDayOfWeekInMonth(Datex.createCalendar(Datex.toDateYMD("2018-05-28")), Datex.createCalendar(Datex.toDateYMD("2018-06-25")), 4));
        assertTrue(Datex.differDayOfWeekInMonth(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-04-30"), 4, defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.differDayOfWeekInMonth(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-04-30"), 4, Locale.getDefault()));
        assertFalse(Datex.differDayOfWeekInMonth(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-06-26"), 4, defaultFirstDayOfWeek));
        assertFalse(Datex.differDayOfWeekInMonth(Datex.toDateYMD("2018-05-28"), Datex.toDateYMD("2018-04-29"), 4));

        assertTrue(Datex.differHourOfDay(Datex.createCalendar(Datex.toDateYMDH("2018-05-28 20")), Datex.createCalendar(Datex.toDateYMDH("2018-05-29 3")), 7));
        assertTrue(Datex.differHourOfDay(Datex.toDateYMDH("2018-05-28 20"), Datex.toDateYMDH("2018-05-28 13"), 7, Locale.getDefault()));
        assertFalse(Datex.differHourOfDay(Datex.toDateYMDH("2018-05-28 20"), Datex.toDateYMDH("2018-05-29 4"), 7));
        assertFalse(Datex.differHourOfDay(Datex.toDateYMDH("2018-05-28 20"), Datex.toDateYMDH("2018-05-28 12"), 7));

        assertTrue(Datex.differHour(Datex.createCalendar(Datex.toDateYMDH("2018-05-28 08")), Datex.createCalendar(Datex.toDateYMDH("2018-05-28 15")), 7));
        assertTrue(Datex.differHour(Datex.toDateYMDH("2018-05-28 08"), Datex.toDateYMDH("2018-05-28 01"), 7, Locale.getDefault()));
        assertFalse(Datex.differHour(Datex.toDateYMDH("2018-05-28 08"), Datex.toDateYMDH("2018-05-28 16"), 7));
        assertFalse(Datex.differHour(Datex.toDateYMDH("2018-05-28 08"), Datex.toDateYMDH("2018-05-28 00"), 7));

        assertTrue(Datex.differMinute(Datex.createCalendar(Datex.toDateYMDHM("2018-05-28 08:58")), Datex.createCalendar(Datex.toDateYMDHM("2018-05-28 09:05")), 7));
        assertTrue(Datex.differMinute(Datex.toDateYMDHM("2018-05-28 08:58"), Datex.toDateYMDHM("2018-05-28 08:51"), 7, Locale.getDefault()));
        assertFalse(Datex.differMinute(Datex.toDateYMDHM("2018-05-28 08:58"), Datex.toDateYMDHM("2018-05-28 09:06"), 7));
        assertFalse(Datex.differMinute(Datex.toDateYMDHM("2018-05-28 08:58"), Datex.toDateYMDHM("2018-05-28 08:50"), 7));

        assertTrue(Datex.differSecond(Datex.createCalendar(Datex.toDateYMDHMS("2018-05-28 08:58:58")), Datex.createCalendar(Datex.toDateYMDHMS("2018-05-28 08:59:05")), 7));
        assertTrue(Datex.differSecond(Datex.toDateYMDHMS("2018-05-28 08:58:58"), Datex.toDateYMDHMS("2018-05-28 08:58:51"), 7, Locale.getDefault()));
        assertFalse(Datex.differSecond(Datex.toDateYMDHMS("2018-05-28 08:58:58"), Datex.toDateYMDHMS("2018-05-28 08:59:06"), 7));
        assertFalse(Datex.differSecond(Datex.toDateYMDHMS("2018-05-28 08:58:58"), Datex.toDateYMDHMS("2018-05-28 08:58:50"), 7));

        assertTrue(Datex.differMillisecond(Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toDateYMDHMSM("2018-05-28 08:58:58 895")), 7));
        assertTrue(Datex.differMillisecond(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888"), Datex.toDateYMDHMSM("2018-05-28 08:58:58 881"), 7, Locale.getDefault()));
        assertFalse(Datex.differMillisecond(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888"), Datex.toDateYMDHMSM("2018-05-28 08:58:58 896"), 7));
        assertFalse(Datex.differMillisecond(Datex.toDateYMDHMSM("2018-05-28 08:58:58 888"), Datex.toDateYMDHMSM("2018-05-28 08:58:58 850"), 7));


        assertTrue(Datex.differField(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 895")), Calendar.MILLISECOND, 7));
        assertTrue(Datex.differField(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 881")), Calendar.MILLISECOND, 7));
        assertTrue(Datex.differField(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 881")), Calendar.MILLISECOND, 0));
        assertFalse(Datex.differField(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 896")), Calendar.MILLISECOND, 7));
        assertFalse(Datex.differField(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 850")), Calendar.MILLISECOND, 7));

        assertTrue(Datex.differCalendarField(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888"), Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 895"), Calendar.MILLISECOND, 7, defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.differCalendarField(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888"), Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 881"), Calendar.MILLISECOND, 7, defaultFirstDayOfWeek));
        assertFalse(Datex.differCalendarField(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888"), Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 896"), Calendar.MILLISECOND, 7, Locale.getDefault()));
        assertFalse(Datex.differCalendarField(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888"), Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 850"), Calendar.MILLISECOND, 7));

        assertTrue(Datex.differYear(Datex.createCalendar(Datex.toMillisecondY("2018")), Datex.createCalendar(Datex.toMillisecondY("2011")), 7));
        assertTrue(Datex.differYear(Datex.toMillisecondY("2018"), Datex.toMillisecondY("2025"), 7, Locale.getDefault()));
        assertFalse(Datex.differYear(Datex.toMillisecondY("2018"), Datex.toMillisecondY("2010"), 7));
        assertFalse(Datex.differYear(Datex.toMillisecondY("2018"), Datex.toMillisecondY("2026"), 7));

        assertTrue(Datex.differMonth(Datex.createCalendar(Datex.toMillisecondYM("2018-05")), Datex.createCalendar(Datex.toMillisecondYM("2018-12")), 7));
        assertTrue(Datex.differMonth(Datex.toMillisecondYM("2018-05"), Datex.toMillisecondYM("2017-10"), 7, Locale.getDefault()));
        assertFalse(Datex.differMonth(Datex.toMillisecondYM("2018-05"), Datex.toMillisecondYM("2019-01"), 7));
        assertFalse(Datex.differMonth(Datex.toMillisecondYM("2018-05"), Datex.toMillisecondYM("2017-09"), 7));

        assertTrue(Datex.differWeekOfYear(Datex.createCalendar(Datex.toMillisecondYMD("2018-05-28")), Datex.createCalendar(Datex.toMillisecondYMD("2018-06-25")), 4));
        assertTrue(Datex.differWeekOfYear(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-04-30"), 4, defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.differWeekOfYear(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-04-30"), 4, Locale.getDefault()));
        assertFalse(Datex.differWeekOfYear(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-06-26"), 4, defaultFirstDayOfWeek));
        assertFalse(Datex.differWeekOfYear(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-04-29"), 4));

        assertTrue(Datex.differWeekOfMonth(Datex.createCalendar(Datex.toMillisecondYMD("2018-05-28")), Datex.createCalendar(Datex.toMillisecondYMD("2018-06-25")), 4));
        assertTrue(Datex.differWeekOfMonth(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-04-30"), 4, defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.differWeekOfMonth(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-04-30"), 4, Locale.getDefault()));
        assertFalse(Datex.differWeekOfMonth(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-06-26"), 4, defaultFirstDayOfWeek));
        assertFalse(Datex.differWeekOfMonth(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-04-29"), 4));

        assertTrue(Datex.differDayOfYear(Datex.createCalendar(Datex.toMillisecondYMD("2018-05-28")), Datex.createCalendar(Datex.toMillisecondYMD("2018-06-04")), 7));
        assertTrue(Datex.differDayOfYear(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-05-21"), 7, Locale.getDefault()));
        assertFalse(Datex.differDayOfYear(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-06-05"), 7));
        assertFalse(Datex.differDayOfYear(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-05-20"), 7));

        assertTrue(Datex.differDayOfMonth(Datex.createCalendar(Datex.toMillisecondYMD("2018-05-28")), Datex.createCalendar(Datex.toMillisecondYMD("2018-06-04")), 7));
        assertTrue(Datex.differDayOfMonth(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-05-21"), 7, Locale.getDefault()));
        assertFalse(Datex.differDayOfMonth(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-06-05"), 7));
        assertFalse(Datex.differDayOfMonth(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-05-20"), 7));

        assertTrue(Datex.differDayOfWeek(Datex.createCalendar(Datex.toMillisecondYMD("2018-05-28")), Datex.createCalendar(Datex.toMillisecondYMD("2018-06-04")), 7));
        assertTrue(Datex.differDayOfWeek(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-05-21"), 7, defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.differDayOfWeek(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-05-21"), 7, Locale.getDefault()));
        assertFalse(Datex.differDayOfWeek(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-06-05"), 7, defaultFirstDayOfWeek));
        assertFalse(Datex.differDayOfWeek(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-05-20"), 7));

        assertTrue(Datex.differDayOfWeekInMonth(Datex.createCalendar(Datex.toMillisecondYMD("2018-05-28")), Datex.createCalendar(Datex.toMillisecondYMD("2018-06-25")), 4));
        assertTrue(Datex.differDayOfWeekInMonth(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-04-30"), 4, defaultFirstDayOfWeek, Locale.getDefault()));
        assertTrue(Datex.differDayOfWeekInMonth(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-04-30"), 4, Locale.getDefault()));
        assertFalse(Datex.differDayOfWeekInMonth(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-06-26"), 4, defaultFirstDayOfWeek));
        assertFalse(Datex.differDayOfWeekInMonth(Datex.toMillisecondYMD("2018-05-28"), Datex.toMillisecondYMD("2018-04-29"), 4));

        assertTrue(Datex.differHourOfDay(Datex.createCalendar(Datex.toMillisecondYMDH("2018-05-28 20")), Datex.createCalendar(Datex.toMillisecondYMDH("2018-05-29 3")), 7));
        assertTrue(Datex.differHourOfDay(Datex.toMillisecondYMDH("2018-05-28 20"), Datex.toMillisecondYMDH("2018-05-28 13"), 7, Locale.getDefault()));
        assertFalse(Datex.differHourOfDay(Datex.toMillisecondYMDH("2018-05-28 20"), Datex.toMillisecondYMDH("2018-05-29 4"), 7));
        assertFalse(Datex.differHourOfDay(Datex.toMillisecondYMDH("2018-05-28 20"), Datex.toMillisecondYMDH("2018-05-28 12"), 7));

        assertTrue(Datex.differHour(Datex.createCalendar(Datex.toMillisecondYMDH("2018-05-28 08")), Datex.createCalendar(Datex.toMillisecondYMDH("2018-05-28 15")), 7));
        assertTrue(Datex.differHour(Datex.toMillisecondYMDH("2018-05-28 08"), Datex.toMillisecondYMDH("2018-05-28 01"), 7, Locale.getDefault()));
        assertFalse(Datex.differHour(Datex.toMillisecondYMDH("2018-05-28 08"), Datex.toMillisecondYMDH("2018-05-28 16"), 7));
        assertFalse(Datex.differHour(Datex.toMillisecondYMDH("2018-05-28 08"), Datex.toMillisecondYMDH("2018-05-28 00"), 7));

        assertTrue(Datex.differMinute(Datex.createCalendar(Datex.toMillisecondYMDHM("2018-05-28 08:58")), Datex.createCalendar(Datex.toMillisecondYMDHM("2018-05-28 09:05")), 7));
        assertTrue(Datex.differMinute(Datex.toMillisecondYMDHM("2018-05-28 08:58"), Datex.toMillisecondYMDHM("2018-05-28 08:51"), 7, Locale.getDefault()));
        assertFalse(Datex.differMinute(Datex.toMillisecondYMDHM("2018-05-28 08:58"), Datex.toMillisecondYMDHM("2018-05-28 09:06"), 7));
        assertFalse(Datex.differMinute(Datex.toMillisecondYMDHM("2018-05-28 08:58"), Datex.toMillisecondYMDHM("2018-05-28 08:50"), 7));

        assertTrue(Datex.differSecond(Datex.createCalendar(Datex.toMillisecondYMDHMS("2018-05-28 08:58:58")), Datex.createCalendar(Datex.toMillisecondYMDHMS("2018-05-28 08:59:05")), 7));
        assertTrue(Datex.differSecond(Datex.toMillisecondYMDHMS("2018-05-28 08:58:58"), Datex.toMillisecondYMDHMS("2018-05-28 08:58:51"), 7, Locale.getDefault()));
        assertFalse(Datex.differSecond(Datex.toMillisecondYMDHMS("2018-05-28 08:58:58"), Datex.toMillisecondYMDHMS("2018-05-28 08:59:06"), 7));
        assertFalse(Datex.differSecond(Datex.toMillisecondYMDHMS("2018-05-28 08:58:58"), Datex.toMillisecondYMDHMS("2018-05-28 08:58:50"), 7));

        assertTrue(Datex.differMillisecond(Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888")), Datex.createCalendar(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 895")), 7));
        assertTrue(Datex.differMillisecond(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888"), Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 881"), 7, Locale.getDefault()));
        assertFalse(Datex.differMillisecond(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888"), Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 896"), 7));
        assertFalse(Datex.differMillisecond(Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 888"), Datex.toMillisecondYMDHMSM("2018-05-28 08:58:58 850"), 7));
    }
}
