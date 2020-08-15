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

package com.github.panpf.tools4j.date;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Date tool method
 */
public class Datex {

    private Datex() {
    }

    //  todo 对 long 的扩展再搞回来
    // formatTotalTime 和 formatDuration 放到这里来并搞成专业的形式

    @NotNull
    public static final String y = "yyyy";

    @NotNull
    public static final String yM = "yyyy-MM";

    @NotNull
    public static final String yMd = "yyyy-MM-dd";

    @NotNull
    public static final String yMdH = "yyyy-MM-dd HH";

    @NotNull
    public static final String yMdHm = "yyyy-MM-dd HH:mm";

    @NotNull
    public static final String yMdHms = "yyyy-MM-dd HH:mm:ss";

    @NotNull
    public static final String yMdHmsS = "yyyy-MM-dd HH:mm:ss SSS";

    @NotNull
    public static final String yM_SHORT = "yyyyMM";

    @NotNull
    public static final String yMd_SHORT = "yyyyMMdd";


    /* ******************************************* toDate ****************************************** */


    /**
     * Millisecond to Date
     */
    @NotNull
    public static Date toDate(long millisecondValue) {
        return new Date(millisecondValue);
    }


    /**
     * Convert formatted date string to Date
     */
    @NotNull
    public static Date toDate(@NotNull String formattedDate, @NotNull SimpleDateFormat format) throws ParseException {
        return format.parse(formattedDate);
    }

    /**
     * Convert formatted date string to Date
     */
    @NotNull
    public static Date toDate(@NotNull String formattedDate, @NotNull String pattern, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, new SimpleDateFormat(pattern, locale));
    }

    /**
     * Convert formatted date string to Date
     */
    @NotNull
    public static Date toDate(@NotNull String formattedDate, @NotNull String pattern) throws ParseException {
        return toDate(formattedDate, new SimpleDateFormat(pattern));
    }


    /* ******************************************* createCalendar ****************************************** */


    /**
     * Create a Calendar
     */
    @NotNull
    public static Calendar createCalendar(@NotNull Date date, int firstDayOfWeek, @NotNull Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setFirstDayOfWeek(firstDayOfWeek);
        calendar.setTimeInMillis(date.getTime());
        return calendar;
    }

    /**
     * Create a Calendar
     */
    @NotNull
    public static Calendar createCalendar(@NotNull Date date, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(firstDayOfWeek);
        calendar.setTimeInMillis(date.getTime());
        return calendar;
    }

    /**
     * Create a Calendar
     */
    @NotNull
    public static Calendar createCalendar(@NotNull Date date, @NotNull Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTimeInMillis(date.getTime());
        return calendar;
    }

    /**
     * Create a Calendar
     */
    @NotNull
    public static Calendar createCalendar(@NotNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        return calendar;
    }



    /* ******************************************* format ****************************************** */


    /**
     * Convert Date to a formatted string
     */
    @NotNull
    public static String format(@NotNull Date date, @NotNull SimpleDateFormat format) {
        return format.format(date);
    }

    /**
     * Convert Date to a formatted string
     */
    @NotNull
    public static String format(@NotNull Date date, @NotNull String pattern, @NotNull Locale locale) {
        return format(date, new SimpleDateFormat(pattern, locale));
    }

    /**
     * Convert Date to a formatted string
     */
    @NotNull
    public static String format(@NotNull Date date, @NotNull String pattern) {
        return format(date, new SimpleDateFormat(pattern));
    }


    /* ******************************************* get\* ****************************************** */


    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(@NotNull Date date, int field, int firstDayOfWeek, @NotNull Locale locale) {
        return createCalendar(date, firstDayOfWeek, locale).get(field);
    }

    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(@NotNull Date date, int field, int firstDayOfWeek) {
        return createCalendar(date, firstDayOfWeek).get(field);
    }

    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(@NotNull Date date, int field, @NotNull Locale locale) {
        return createCalendar(date, locale).get(field);
    }

    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(@NotNull Date date, int field) {
        return createCalendar(date).get(field);
    }


    /* ******************************************* add\* ****************************************** */


    /**
     * Increase the specified calendar field and return to Date
     */
    @NotNull
    public static Date addToDate(@NotNull Calendar calendar, int field, int amount) {
        calendar.add(field, amount);
        return new Date(calendar.getTimeInMillis());
    }


    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(@NotNull Date date, int field, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return addToDate(createCalendar(date, firstDayOfWeek, locale), field, amount);
    }

    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(@NotNull Date date, int field, int amount, int firstDayOfWeek) {
        return addToDate(createCalendar(date, firstDayOfWeek), field, amount);
    }

    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(@NotNull Date date, int field, int amount, @NotNull Locale locale) {
        return addToDate(createCalendar(date, locale), field, amount);
    }

    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(@NotNull Date date, int field, int amount) {
        return addToDate(createCalendar(date), field, amount);
    }


    /* ******************************************* isSame\* ****************************************** */


    /**
     * Return true if the year is the same
     */
    public static boolean isSameYear(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR);
    }

    /**
     * Returns true if the year and month are the same
     */
    public static boolean isSameMonth(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH);
    }

    /**
     * Return true if the months is the same
     */
    public static boolean isSameMonthOfYear(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH);
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(@NotNull Calendar calendar, @NotNull Calendar target) {
        if (calendar.get(Calendar.ERA) != target.get(Calendar.ERA)) {
            return false;
        } else if (calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)) {
            // 年份一样可以直接用 WEEK_OF_YEAR 对比
            return calendar.get(Calendar.WEEK_OF_YEAR) == target.get(Calendar.WEEK_OF_YEAR);
        } else if (differField(calendar, target, Calendar.DAY_OF_YEAR, 7)) {
            // day 只相差 7 天说明是年末那几天，也可以直接用 WEEK_OF_YEAR 对比
            return calendar.get(Calendar.WEEK_OF_YEAR) == target.get(Calendar.WEEK_OF_YEAR);
        } else {
            // day 相差超 7 天以上绝不可能是同一周
            return false;
        }
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.WEEK_OF_YEAR) == target.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.WEEK_OF_MONTH) == target.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * Returns true if the year, month, week and day are the same
     */
    public static boolean isSameDay(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Return true if the dayOfYear is the same
     */
    public static boolean isSameDayOfYear(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.DAY_OF_YEAR) == target.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Return true if the dayOfMonth is the same
     */
    public static boolean isSameDayOfMonth(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.DAY_OF_WEEK) == target.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) == target.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * Returns true if the year, month, week, day and hour are the same
     */
    public static boolean isSameHour(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.HOUR_OF_DAY) == target.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Return true if the 24H hour is the same
     */
    public static boolean isSameHourOf24H(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.HOUR_OF_DAY) == target.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Return true if the 12H hour is the same
     */
    public static boolean isSameHourOf12H(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.HOUR) == target.get(Calendar.HOUR);
    }

    /**
     * Returns true if the year, month, week, day, hour and minute are the same
     */
    public static boolean isSameMinute(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.HOUR_OF_DAY) == target.get(Calendar.HOUR_OF_DAY)
                && calendar.get(Calendar.MINUTE) == target.get(Calendar.MINUTE);
    }

    /**
     * Return true if the minuteOfHour is the same
     */
    public static boolean isSameMinuteOfHour(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.MINUTE) == target.get(Calendar.MINUTE);
    }

    /**
     * Returns true if the year, month, week, day, hour, minute and second are the same
     */
    public static boolean isSameSecond(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.HOUR_OF_DAY) == target.get(Calendar.HOUR_OF_DAY)
                && calendar.get(Calendar.MINUTE) == target.get(Calendar.MINUTE)
                && calendar.get(Calendar.SECOND) == target.get(Calendar.SECOND);
    }

    /**
     * Return true if the secondOfMinute is the same
     */
    public static boolean isSameSecondOfMinute(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.SECOND) == target.get(Calendar.SECOND);
    }

    /**
     * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
     */
    public static boolean isSameMillisecond(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.HOUR_OF_DAY) == target.get(Calendar.HOUR_OF_DAY)
                && calendar.get(Calendar.MINUTE) == target.get(Calendar.MINUTE)
                && calendar.get(Calendar.SECOND) == target.get(Calendar.SECOND)
                && calendar.get(Calendar.MILLISECOND) == target.get(Calendar.MILLISECOND);
    }

    /**
     * Return true if the millisecondOfSecond is the same
     */
    public static boolean isSameMillisecondOfSecond(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA)
                && calendar.get(Calendar.MILLISECOND) == target.get(Calendar.MILLISECOND);
    }


    /**
     * Return true if the year is the same
     */
    public static boolean isSameYear(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameYear(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the year is the same
     */
    public static boolean isSameYear(@NotNull Date date, @NotNull Date target) {
        return isSameYear(createCalendar(date), createCalendar(target));
    }

    /**
     * Returns true if the year and month are the same
     */
    public static boolean isSameMonth(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameMonth(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Returns true if the year and month are the same
     */
    public static boolean isSameMonth(@NotNull Date date, @NotNull Date target) {
        return isSameMonth(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the months is the same
     */
    public static boolean isSameMonthOfYear(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameMonthOfYear(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the months is the same
     */
    public static boolean isSameMonthOfYear(@NotNull Date date, @NotNull Date target) {
        return isSameMonthOfYear(createCalendar(date), createCalendar(target));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(@NotNull Date date, @NotNull Date target, int firstDayOfWeek, @NotNull Locale locale) {
        return isSameWeek(createCalendar(date, firstDayOfWeek, locale), createCalendar(target, firstDayOfWeek, locale));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(@NotNull Date date, @NotNull Date target, int firstDayOfWeek) {
        return isSameWeek(createCalendar(date, firstDayOfWeek), createCalendar(target, firstDayOfWeek));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameWeek(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(@NotNull Date date, @NotNull Date target) {
        return isSameWeek(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(@NotNull Date date, @NotNull Date target, int firstDayOfWeek, @NotNull Locale locale) {
        return isSameWeekOfYear(createCalendar(date, firstDayOfWeek, locale), createCalendar(target, firstDayOfWeek, locale));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(@NotNull Date date, @NotNull Date target, int firstDayOfWeek) {
        return isSameWeekOfYear(createCalendar(date, firstDayOfWeek), createCalendar(target, firstDayOfWeek));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameWeekOfYear(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(@NotNull Date date, @NotNull Date target) {
        return isSameWeekOfYear(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(@NotNull Date date, @NotNull Date target, int firstDayOfWeek, @NotNull Locale locale) {
        return isSameWeekOfMonth(createCalendar(date, firstDayOfWeek, locale), createCalendar(target, firstDayOfWeek, locale));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(@NotNull Date date, @NotNull Date target, int firstDayOfWeek) {
        return isSameWeekOfMonth(createCalendar(date, firstDayOfWeek), createCalendar(target, firstDayOfWeek));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameWeekOfMonth(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(@NotNull Date date, @NotNull Date target) {
        return isSameWeekOfMonth(createCalendar(date), createCalendar(target));
    }

    /**
     * Returns true if the year, month, week and day are the same
     */
    public static boolean isSameDay(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameDay(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Returns true if the year, month, week and day are the same
     */
    public static boolean isSameDay(@NotNull Date date, @NotNull Date target) {
        return isSameDay(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the dayOfYear is the same
     */
    public static boolean isSameDayOfYear(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameDayOfYear(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the dayOfYear is the same
     */
    public static boolean isSameDayOfYear(@NotNull Date date, @NotNull Date target) {
        return isSameDayOfYear(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the dayOfMonth is the same
     */
    public static boolean isSameDayOfMonth(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameDayOfMonth(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the dayOfMonth is the same
     */
    public static boolean isSameDayOfMonth(@NotNull Date date, @NotNull Date target) {
        return isSameDayOfMonth(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(@NotNull Date date, @NotNull Date target, int firstDayOfWeek, @NotNull Locale locale) {
        return isSameDayOfWeek(createCalendar(date, firstDayOfWeek, locale), createCalendar(target, firstDayOfWeek, locale));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(@NotNull Date date, @NotNull Date target, int firstDayOfWeek) {
        return isSameDayOfWeek(createCalendar(date, firstDayOfWeek), createCalendar(target, firstDayOfWeek));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameDayOfWeek(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(@NotNull Date date, @NotNull Date target) {
        return isSameDayOfWeek(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(@NotNull Date date, @NotNull Date target, int firstDayOfWeek, @NotNull Locale locale) {
        return isSameDayOfWeekInMonth(createCalendar(date, firstDayOfWeek, locale), createCalendar(target, firstDayOfWeek, locale));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(@NotNull Date date, @NotNull Date target, int firstDayOfWeek) {
        return isSameDayOfWeekInMonth(createCalendar(date, firstDayOfWeek), createCalendar(target, firstDayOfWeek));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameDayOfWeekInMonth(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(@NotNull Date date, @NotNull Date target) {
        return isSameDayOfWeekInMonth(createCalendar(date), createCalendar(target));
    }

    /**
     * Returns true if the year, month, week, day and hour are the same
     */
    public static boolean isSameHour(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameHour(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Returns true if the year, month, week, day and hour are the same
     */
    public static boolean isSameHour(@NotNull Date date, @NotNull Date target) {
        return isSameHour(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the 24H hour is the same
     */
    public static boolean isSameHourOf24H(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameHourOf24H(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the 24H hour is the same
     */
    public static boolean isSameHourOf24H(@NotNull Date date, @NotNull Date target) {
        return isSameHourOf24H(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the 12H hour is the same
     */
    public static boolean isSameHourOf12H(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameHourOf12H(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the 12H hour is the same
     */
    public static boolean isSameHourOf12H(@NotNull Date date, @NotNull Date target) {
        return isSameHourOf12H(createCalendar(date), createCalendar(target));
    }

    /**
     * Returns true if the year, month, week, day, hour and minute are the same
     */
    public static boolean isSameMinute(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameMinute(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Returns true if the year, month, week, day, hour and minute are the same
     */
    public static boolean isSameMinute(@NotNull Date date, @NotNull Date target) {
        return isSameMinute(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the minuteOfHour is the same
     */
    public static boolean isSameMinuteOfHour(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameMinuteOfHour(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the minuteOfHour is the same
     */
    public static boolean isSameMinuteOfHour(@NotNull Date date, @NotNull Date target) {
        return isSameMinuteOfHour(createCalendar(date), createCalendar(target));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute and second are the same
     */
    public static boolean isSameSecond(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameSecond(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute and second are the same
     */
    public static boolean isSameSecond(@NotNull Date date, @NotNull Date target) {
        return isSameSecond(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the secondOfMinute is the same
     */
    public static boolean isSameSecondOfMinute(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameSecondOfMinute(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the secondOfMinute is the same
     */
    public static boolean isSameSecondOfMinute(@NotNull Date date, @NotNull Date target) {
        return isSameSecondOfMinute(createCalendar(date), createCalendar(target));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
     */
    public static boolean isSameMillisecond(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameMillisecond(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
     */
    public static boolean isSameMillisecond(@NotNull Date date, @NotNull Date target) {
        return isSameMillisecond(createCalendar(date), createCalendar(target));
    }

    /**
     * Return true if the millisecondOfSecond is the same
     */
    public static boolean isSameMillisecondOfSecond(@NotNull Date date, @NotNull Date target, @NotNull Locale locale) {
        return isSameMillisecondOfSecond(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the millisecondOfSecond is the same
     */
    public static boolean isSameMillisecondOfSecond(@NotNull Date date, @NotNull Date target) {
        return isSameMillisecondOfSecond(createCalendar(date), createCalendar(target));
    }


    /* ******************************************* differ\* ****************************************** */


    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differField(@NotNull Calendar calendar, @NotNull Calendar target, int field, int amount) {
        if (amount == 0) {
            return true;
        } else {
            int finalAmount = calendar.getTimeInMillis() < target.getTimeInMillis() ? Math.abs(amount) : Math.abs(amount) * -1;
            long cacheTimeInMillis = calendar.getTimeInMillis();
            calendar.add(field, finalAmount);
            long newTimeInMillis = calendar.getTimeInMillis();
            calendar.setTimeInMillis(cacheTimeInMillis);
            return finalAmount > 0 ? target.getTimeInMillis() <= newTimeInMillis : target.getTimeInMillis() >= newTimeInMillis;
        }
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(@NotNull Date date, @NotNull Date target, int field, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return differField(createCalendar(date, firstDayOfWeek, locale), createCalendar(target, firstDayOfWeek, locale), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(@NotNull Date date, @NotNull Date target, int field, int amount, int firstDayOfWeek) {
        return differField(createCalendar(date, firstDayOfWeek), createCalendar(target, firstDayOfWeek), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(@NotNull Date date, @NotNull Date target, int field, int amount, @NotNull Locale locale) {
        return differField(createCalendar(date, locale), createCalendar(target, locale), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(@NotNull Date date, @NotNull Date target, int field, int amount) {
        return differField(createCalendar(date), createCalendar(target), field, amount);
    }
}