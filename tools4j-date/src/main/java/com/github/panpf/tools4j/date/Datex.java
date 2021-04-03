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
import org.jetbrains.annotations.Nullable;

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
    public static Date toDate(@NotNull String formattedDate, @NotNull String pattern, @Nullable Locale locale) throws ParseException {
        return toDate(formattedDate, locale != null ? new SimpleDateFormat(pattern, locale) : new SimpleDateFormat(pattern));
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
    public static Calendar createCalendar(@NotNull Date date, int firstDayOfWeek, @Nullable Locale locale) {
        Calendar calendar = locale != null ? Calendar.getInstance(locale) : Calendar.getInstance();
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
    public static Calendar createCalendar(@NotNull Date date, @Nullable Locale locale) {
        Calendar calendar = locale != null ? Calendar.getInstance(locale) : Calendar.getInstance();
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


    /**
     * Create a Calendar
     */
    @NotNull
    public static Calendar createCalendar(long millisecondValue, int firstDayOfWeek, @Nullable Locale locale) {
        Calendar calendar = locale != null ? Calendar.getInstance(locale) : Calendar.getInstance();
        calendar.setFirstDayOfWeek(firstDayOfWeek);
        calendar.setTimeInMillis(millisecondValue);
        return calendar;
    }

    /**
     * Create a Calendar
     */
    @NotNull
    public static Calendar createCalendar(long millisecondValue, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(firstDayOfWeek);
        calendar.setTimeInMillis(millisecondValue);
        return calendar;
    }

    /**
     * Create a Calendar
     */
    @NotNull
    public static Calendar createCalendar(long millisecondValue, @Nullable Locale locale) {
        Calendar calendar = locale != null ? Calendar.getInstance(locale) : Calendar.getInstance();
        calendar.setTimeInMillis(millisecondValue);
        return calendar;
    }

    /**
     * Create a Calendar
     */
    @NotNull
    public static Calendar createCalendar(long millisecondValue) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecondValue);
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
    public static String format(@NotNull Date date, @NotNull String pattern, @Nullable Locale locale) {
        return (locale != null ? new SimpleDateFormat(pattern, locale) : new SimpleDateFormat(pattern)).format(date);
    }

    /**
     * Convert Date to a formatted string
     */
    @NotNull
    public static String format(@NotNull Date date, @NotNull String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }


    /**
     * Convert Date to a formatted string
     */
    @NotNull
    public static String format(long millisecondValue, @NotNull SimpleDateFormat format) {
        return format.format(new Date(millisecondValue));
    }

    /**
     * Convert Date to a formatted string
     */
    @NotNull
    public static String format(long millisecondValue, @NotNull String pattern, @Nullable Locale locale) {
        return (locale != null ? new SimpleDateFormat(pattern, locale) : new SimpleDateFormat(pattern)).format(new Date(millisecondValue));
    }

    /**
     * Convert Date to a formatted string
     */
    @NotNull
    public static String format(long millisecondValue, @NotNull String pattern) {
        return new SimpleDateFormat(pattern).format(new Date(millisecondValue));
    }


    /**
     * Format the length of time according to the specified 'pattern'
     *
     * @param pattern Formatting pattern，The following types are supported:
     *                <blockquote>
     *                <table summary="Time length formatting pattern desc">
     *                  <tr>
     *                      <td>%d、%d?、%D、%D?</td>
     *                      <td>Day，</td>
     *                  </tr>
     *                  <tr>
     *                      <td>%h、%h?、%H、%H?</td>
     *                      <td>Hour</td>
     *                  </tr>
     *                  <tr>
     *                      <td>%m、%m?、%M、%M?</td>
     *                      <td>Minute</td>
     *                  </tr>
     *                  <tr>
     *                      <td>%s、%s?、%S、%S?</td>
     *                      <td>Second</td>
     *                  </tr>
     *                  <tr>
     *                      <td>%ms、%ms?、%MS、%MS?</td>
     *                      <td>Millisecond</td>
     *                  </tr>
     *                </table>
     *                </blockquote>
     *                As shown in the table above, each type has several variants.
     *                The variant containing'?' means that if the result of this item is 0, then it can be ignored in the output；
     *                Uppercase means, if necessary, add 0 in front of the output result to ensure that the output result always maintains a fixed length
     *
     *                for example:
     *                <blockquote>
     *                <table summary="Time length formatting pattern example">
     *                  <tr>
     *                      <th> Example </th>
     *                      <th> Result </th>
     *                  </tr>
     *                  <tr>
     *                      <td> formatTimeLength(3623000, "%hh%mm%ss") </td>
     *                      <td> "1h0m23s" </td>
     *                  </tr>
     *                  <tr>
     *                      <td> formatTimeLength(3623000, "%h?h%m?m%s?s") </td>
     *                      <td> "1h23s" </td>
     *                  </tr>
     *                  <tr>
     *                      <td> formatTimeLength(4684000, "%h:%m:%s") </td>
     *                      <td> "1:18:4" </td>
     *                  </tr>
     *                  <tr>
     *                      <td> formatTimeLength(4684000, "%H:%M:%S") </td>
     *                      <td> "01:18:04" </td>
     *                  </tr>
     *                  <tr>
     *                      <td> formatTimeLength(91403467, "%dDay %hHour %mMinute %sSecond %msMillisecond") </td>
     *                      <td> "1Day 1Hour 23Minute 23Second 467Millisecond" </td>
     *                  </tr>
     *                </table>
     *                </blockquote>
     */
    @NotNull
    // todo 改名 formatDuration
    public static String formatTimeLength(long timeLength, @NotNull String pattern) {
        timeLength = Math.max(timeLength, 0);
        final long oneSecondMilliseconds = 1000;
        final long oneMinuteMilliseconds = oneSecondMilliseconds * 60;
        final long oneHourMilliseconds = oneMinuteMilliseconds * 60;
        final long oneDayMilliseconds = oneHourMilliseconds * 24;

        String[] items = pattern.split("%");
        String daySuffix = null, hourSuffix = null, minuteSuffix = null, secondSuffix = null, millisecondSuffix = null;
        boolean dayAllowIgnore = false, hourAllowIgnore = false, minuteAllowIgnore = false, secondAllowIgnore = false, millisecondAllowIgnore = false;
        boolean dayPad = false, hourPad = false, minutePad = false, secondPad = false, millisecondPad = false;
        for (String item : items) {
            if (item.toLowerCase().startsWith("ms")) {
                millisecondAllowIgnore = item.length() > 2 && item.charAt(2) == '?';
                millisecondSuffix = item.substring(millisecondAllowIgnore ? 3 : 2);
                millisecondPad = item.startsWith("MS");
            } else if (item.toLowerCase().startsWith("s")) {
                secondAllowIgnore = item.length() > 1 && item.charAt(1) == '?';
                secondSuffix = item.substring(secondAllowIgnore ? 2 : 1);
                secondPad = item.startsWith("S");
            } else if (item.toLowerCase().startsWith("m")) {
                minuteAllowIgnore = item.length() > 1 && item.charAt(1) == '?';
                minuteSuffix = item.substring(minuteAllowIgnore ? 2 : 1);
                minutePad = item.startsWith("M");
            } else if (item.toLowerCase().startsWith("h")) {
                hourAllowIgnore = item.length() > 1 && item.charAt(1) == '?';
                hourSuffix = item.substring(hourAllowIgnore ? 2 : 1);
                hourPad = item.startsWith("H");
            } else if (item.toLowerCase().startsWith("d")) {
                dayAllowIgnore = item.length() > 1 && item.charAt(1) == '?';
                daySuffix = item.substring(dayAllowIgnore ? 2 : 1);
                dayPad = item.startsWith("D");
            }
        }
        if (daySuffix == null && hourSuffix == null && minuteSuffix == null && secondSuffix == null && millisecondSuffix == null) {
            throw new IllegalArgumentException("Invalid pattern '" + pattern + "', for example：'\\h:\\m:\\s'");
        }

        StringBuilder builder = new StringBuilder();
        if (daySuffix != null) {
            long day = timeLength / oneDayMilliseconds;
            if (day > 0 || !dayAllowIgnore) {
                builder.append(dayPad ? String.format("%02d", day) : day).append(daySuffix);
            }
        }

        if (hourSuffix != null) {
            long hour;
            if (daySuffix != null) {
                hour = timeLength % oneDayMilliseconds / oneHourMilliseconds;
            } else {
                hour = timeLength / oneHourMilliseconds;
            }
            if (hour > 0 || !hourAllowIgnore) {
                builder.append(hourPad ? String.format("%02d", hour) : hour).append(hourSuffix);
            }
        }

        if (minuteSuffix != null) {
            long minute;
            if (daySuffix != null || hourSuffix != null) {
                minute = timeLength % oneHourMilliseconds / oneMinuteMilliseconds;
            } else {
                minute = timeLength / oneMinuteMilliseconds;
            }
            if (minute > 0 || !minuteAllowIgnore) {
                builder.append(minutePad ? String.format("%02d", minute) : minute).append(minuteSuffix);
            }
        }

        if (secondSuffix != null) {
            long second;
            if (daySuffix != null || hourSuffix != null || minuteSuffix != null) {
                second = timeLength % oneMinuteMilliseconds / oneSecondMilliseconds;
            } else {
                second = timeLength / oneSecondMilliseconds;
            }
            if (second > 0 || !secondAllowIgnore) {
                builder.append(secondPad ? String.format("%02d", second) : second).append(secondSuffix);
            }
        }

        if (millisecondSuffix != null) {
            long millisecond;
            if (daySuffix != null || hourSuffix != null || minuteSuffix != null || secondSuffix != null) {
                millisecond = timeLength % oneSecondMilliseconds;
            } else {
                millisecond = timeLength;
            }
            if (millisecond > 0 || !millisecondAllowIgnore) {
                builder.append(millisecondPad ? String.format("%03d", millisecond) : millisecond).append(millisecondSuffix);
            }
        }

        if (builder.length() == 0) {
            if (millisecondSuffix != null) {
                builder.append(millisecondPad ? String.format("%03d", 0) : 0).append(millisecondSuffix);
            } else if (secondSuffix != null) {
                builder.append(secondPad ? String.format("%02d", 0) : 0).append(secondSuffix);
            } else if (minuteSuffix != null) {
                builder.append(minutePad ? String.format("%02d", 0) : 0).append(minuteSuffix);
            } else if (hourSuffix != null) {
                builder.append(hourPad ? String.format("%02d", 0) : 0).append(hourSuffix);
            } else {
                builder.append(dayPad ? String.format("%02d", 0) : 0).append(daySuffix);
            }
        }

        return builder.toString().trim();
    }


    /* ******************************************* get\* ****************************************** */


    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(@NotNull Date date, int field, int firstDayOfWeek, @Nullable Locale locale) {
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
    public static int getCalendarField(@NotNull Date date, int field, @Nullable Locale locale) {
        return createCalendar(date, locale).get(field);
    }

    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(@NotNull Date date, int field) {
        return createCalendar(date).get(field);
    }


    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(long millisecondValue, int field, int firstDayOfWeek, @Nullable Locale locale) {
        return createCalendar(millisecondValue, firstDayOfWeek, locale).get(field);
    }

    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(long millisecondValue, int field, int firstDayOfWeek) {
        return createCalendar(millisecondValue, firstDayOfWeek).get(field);
    }

    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(long millisecondValue, int field, @Nullable Locale locale) {
        return createCalendar(millisecondValue, locale).get(field);
    }

    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(long millisecondValue, int field) {
        return createCalendar(millisecondValue).get(field);
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
    public static Date addCalendarField(@NotNull Date date, int field, int amount, int firstDayOfWeek, @Nullable Locale locale) {
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
    public static Date addCalendarField(@NotNull Date date, int field, int amount, @Nullable Locale locale) {
        return addToDate(createCalendar(date, locale), field, amount);
    }

    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(@NotNull Date date, int field, int amount) {
        return addToDate(createCalendar(date), field, amount);
    }


    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(long millisecondValue, int field, int amount, int firstDayOfWeek, @Nullable Locale locale) {
        return addToDate(createCalendar(millisecondValue, firstDayOfWeek, locale), field, amount);
    }

    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(long millisecondValue, int field, int amount, int firstDayOfWeek) {
        return addToDate(createCalendar(millisecondValue, firstDayOfWeek), field, amount);
    }

    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(long millisecondValue, int field, int amount, @Nullable Locale locale) {
        return addToDate(createCalendar(millisecondValue, locale), field, amount);
    }

    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(long millisecondValue, int field, int amount) {
        return addToDate(createCalendar(millisecondValue), field, amount);
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
    public static boolean isSameYear(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameMonth(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameMonthOfYear(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameWeek(@NotNull Date date, @NotNull Date target, int firstDayOfWeek, @Nullable Locale locale) {
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
    public static boolean isSameWeek(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameWeekOfYear(@NotNull Date date, @NotNull Date target, int firstDayOfWeek, @Nullable Locale locale) {
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
    public static boolean isSameWeekOfYear(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameWeekOfMonth(@NotNull Date date, @NotNull Date target, int firstDayOfWeek, @Nullable Locale locale) {
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
    public static boolean isSameWeekOfMonth(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameDay(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameDayOfYear(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameDayOfMonth(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameDayOfWeek(@NotNull Date date, @NotNull Date target, int firstDayOfWeek, @Nullable Locale locale) {
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
    public static boolean isSameDayOfWeek(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameDayOfWeekInMonth(@NotNull Date date, @NotNull Date target, int firstDayOfWeek, @Nullable Locale locale) {
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
    public static boolean isSameDayOfWeekInMonth(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameHour(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameHourOf24H(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameHourOf12H(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameMinute(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameMinuteOfHour(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameSecond(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameSecondOfMinute(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameMillisecond(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
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
    public static boolean isSameMillisecondOfSecond(@NotNull Date date, @NotNull Date target, @Nullable Locale locale) {
        return isSameMillisecondOfSecond(createCalendar(date, locale), createCalendar(target, locale));
    }

    /**
     * Return true if the millisecondOfSecond is the same
     */
    public static boolean isSameMillisecondOfSecond(@NotNull Date date, @NotNull Date target) {
        return isSameMillisecondOfSecond(createCalendar(date), createCalendar(target));
    }


    /**
     * Return true if the year is the same
     */
    public static boolean isSameYear(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameYear(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the year is the same
     */
    public static boolean isSameYear(long millisecondValue, long targetMillisecondValue) {
        return isSameYear(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Returns true if the year and month are the same
     */
    public static boolean isSameMonth(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameMonth(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Returns true if the year and month are the same
     */
    public static boolean isSameMonth(long millisecondValue, long targetMillisecondValue) {
        return isSameMonth(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the months is the same
     */
    public static boolean isSameMonthOfYear(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameMonthOfYear(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the months is the same
     */
    public static boolean isSameMonthOfYear(long millisecondValue, long targetMillisecondValue) {
        return isSameMonthOfYear(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek, @Nullable Locale locale) {
        return isSameWeek(createCalendar(millisecondValue, firstDayOfWeek, locale), createCalendar(targetMillisecondValue, firstDayOfWeek, locale));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek) {
        return isSameWeek(createCalendar(millisecondValue, firstDayOfWeek), createCalendar(targetMillisecondValue, firstDayOfWeek));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameWeek(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(long millisecondValue, long targetMillisecondValue) {
        return isSameWeek(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek, @Nullable Locale locale) {
        return isSameWeekOfYear(createCalendar(millisecondValue, firstDayOfWeek, locale), createCalendar(targetMillisecondValue, firstDayOfWeek, locale));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek) {
        return isSameWeekOfYear(createCalendar(millisecondValue, firstDayOfWeek), createCalendar(targetMillisecondValue, firstDayOfWeek));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameWeekOfYear(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(long millisecondValue, long targetMillisecondValue) {
        return isSameWeekOfYear(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek, @Nullable Locale locale) {
        return isSameWeekOfMonth(createCalendar(millisecondValue, firstDayOfWeek, locale), createCalendar(targetMillisecondValue, firstDayOfWeek, locale));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek) {
        return isSameWeekOfMonth(createCalendar(millisecondValue, firstDayOfWeek), createCalendar(targetMillisecondValue, firstDayOfWeek));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameWeekOfMonth(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(long millisecondValue, long targetMillisecondValue) {
        return isSameWeekOfMonth(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Returns true if the year, month, week and day are the same
     */
    public static boolean isSameDay(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameDay(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Returns true if the year, month, week and day are the same
     */
    public static boolean isSameDay(long millisecondValue, long targetMillisecondValue) {
        return isSameDay(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the dayOfYear is the same
     */
    public static boolean isSameDayOfYear(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameDayOfYear(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the dayOfYear is the same
     */
    public static boolean isSameDayOfYear(long millisecondValue, long targetMillisecondValue) {
        return isSameDayOfYear(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the dayOfMonth is the same
     */
    public static boolean isSameDayOfMonth(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameDayOfMonth(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the dayOfMonth is the same
     */
    public static boolean isSameDayOfMonth(long millisecondValue, long targetMillisecondValue) {
        return isSameDayOfMonth(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek, @Nullable Locale locale) {
        return isSameDayOfWeek(createCalendar(millisecondValue, firstDayOfWeek, locale), createCalendar(targetMillisecondValue, firstDayOfWeek, locale));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek) {
        return isSameDayOfWeek(createCalendar(millisecondValue, firstDayOfWeek), createCalendar(targetMillisecondValue, firstDayOfWeek));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameDayOfWeek(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(long millisecondValue, long targetMillisecondValue) {
        return isSameDayOfWeek(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek, @Nullable Locale locale) {
        return isSameDayOfWeekInMonth(createCalendar(millisecondValue, firstDayOfWeek, locale), createCalendar(targetMillisecondValue, firstDayOfWeek, locale));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek) {
        return isSameDayOfWeekInMonth(createCalendar(millisecondValue, firstDayOfWeek), createCalendar(targetMillisecondValue, firstDayOfWeek));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameDayOfWeekInMonth(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue) {
        return isSameDayOfWeekInMonth(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Returns true if the year, month, week, day and hour are the same
     */
    public static boolean isSameHour(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameHour(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Returns true if the year, month, week, day and hour are the same
     */
    public static boolean isSameHour(long millisecondValue, long targetMillisecondValue) {
        return isSameHour(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the 24H hour is the same
     */
    public static boolean isSameHourOf24H(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameHourOf24H(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the 24H hour is the same
     */
    public static boolean isSameHourOf24H(long millisecondValue, long targetMillisecondValue) {
        return isSameHourOf24H(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the 12H hour is the same
     */
    public static boolean isSameHourOf12H(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameHourOf12H(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the 12H hour is the same
     */
    public static boolean isSameHourOf12H(long millisecondValue, long targetMillisecondValue) {
        return isSameHourOf12H(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Returns true if the year, month, week, day, hour and minute are the same
     */
    public static boolean isSameMinute(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameMinute(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Returns true if the year, month, week, day, hour and minute are the same
     */
    public static boolean isSameMinute(long millisecondValue, long targetMillisecondValue) {
        return isSameMinute(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the minuteOfHour is the same
     */
    public static boolean isSameMinuteOfHour(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameMinuteOfHour(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the minuteOfHour is the same
     */
    public static boolean isSameMinuteOfHour(long millisecondValue, long targetMillisecondValue) {
        return isSameMinuteOfHour(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute and second are the same
     */
    public static boolean isSameSecond(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameSecond(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute and second are the same
     */
    public static boolean isSameSecond(long millisecondValue, long targetMillisecondValue) {
        return isSameSecond(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the secondOfMinute is the same
     */
    public static boolean isSameSecondOfMinute(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameSecondOfMinute(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the secondOfMinute is the same
     */
    public static boolean isSameSecondOfMinute(long millisecondValue, long targetMillisecondValue) {
        return isSameSecondOfMinute(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
     */
    public static boolean isSameMillisecond(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameMillisecond(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
     */
    public static boolean isSameMillisecond(long millisecondValue, long targetMillisecondValue) {
        return isSameMillisecond(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
    }

    /**
     * Return true if the millisecondOfSecond is the same
     */
    public static boolean isSameMillisecondOfSecond(long millisecondValue, long targetMillisecondValue, @Nullable Locale locale) {
        return isSameMillisecondOfSecond(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale));
    }

    /**
     * Return true if the millisecondOfSecond is the same
     */
    public static boolean isSameMillisecondOfSecond(long millisecondValue, long targetMillisecondValue) {
        return isSameMillisecondOfSecond(createCalendar(millisecondValue), createCalendar(targetMillisecondValue));
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
    public static boolean differCalendarField(@NotNull Date date, @NotNull Date target, int field, int amount, int firstDayOfWeek, @Nullable Locale locale) {
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
    public static boolean differCalendarField(@NotNull Date date, @NotNull Date target, int field, int amount, @Nullable Locale locale) {
        return differField(createCalendar(date, locale), createCalendar(target, locale), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(@NotNull Date date, @NotNull Date target, int field, int amount) {
        return differField(createCalendar(date), createCalendar(target), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(long millisecondValue, long targetMillisecondValue, int field, int amount, int firstDayOfWeek, @Nullable Locale locale) {
        return differField(createCalendar(millisecondValue, firstDayOfWeek, locale), createCalendar(targetMillisecondValue, firstDayOfWeek, locale), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(long millisecondValue, long targetMillisecondValue, int field, int amount, int firstDayOfWeek) {
        return differField(createCalendar(millisecondValue, firstDayOfWeek), createCalendar(targetMillisecondValue, firstDayOfWeek), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(long millisecondValue, long targetMillisecondValue, int field, int amount, @Nullable Locale locale) {
        return differField(createCalendar(millisecondValue, locale), createCalendar(targetMillisecondValue, locale), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(long millisecondValue, long targetMillisecondValue, int field, int amount) {
        return differField(createCalendar(millisecondValue), createCalendar(targetMillisecondValue), field, amount);
    }
}