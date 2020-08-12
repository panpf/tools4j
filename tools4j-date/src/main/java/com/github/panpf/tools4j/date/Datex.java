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


    /**
     * Create a Calendar
     */
    @NotNull
    public static Calendar createCalendar(long millisecondValue, int firstDayOfWeek, @NotNull Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
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
    public static Calendar createCalendar(long millisecondValue, @NotNull Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
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


    /*
     * toDate
     */


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

    /**
     * Convert formatted date string to Date using pattern 'yyyy'
     */
    @NotNull
    public static Date toDateY(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy", locale);
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy'
     */
    @NotNull
    public static Date toDateY(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy");
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM'
     */
    @NotNull
    public static Date toDateYM(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM", locale);
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM'
     */
    @NotNull
    public static Date toDateYM(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM");
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyyMM'
     */
    @NotNull
    public static Date toDateYMCompact(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyyMM", locale);
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyyMM'
     */
    @NotNull
    public static Date toDateYMCompact(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyyMM");
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd'
     */
    @NotNull
    public static Date toDateYMD(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd", locale);
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd'
     */
    @NotNull
    public static Date toDateYMD(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd");
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyyMMdd'
     */
    @NotNull
    public static Date toDateYMDCompact(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyyMMdd", locale);
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyyMMdd'
     */
    @NotNull
    public static Date toDateYMDCompact(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyyMMdd");
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH'
     */
    @NotNull
    public static Date toDateYMDH(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH", locale);
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH'
     */
    @NotNull
    public static Date toDateYMDH(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH");
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm'
     */
    @NotNull
    public static Date toDateYMDHM(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm", locale);
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm'
     */
    @NotNull
    public static Date toDateYMDHM(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm");
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss'
     */
    @NotNull
    public static Date toDateYMDHMS(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm:ss", locale);
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss'
     */
    @NotNull
    public static Date toDateYMDHMS(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss SSS'
     */
    @NotNull
    public static Date toDateYMDHMSM(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm:ss SSS", locale);
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss SSS'
     */
    @NotNull
    public static Date toDateYMDHMSM(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm:ss SSS");
    }


    /**
     * Convert formatted date string to Date
     */
    public static long toMillisecond(@NotNull String formattedDate, @NotNull SimpleDateFormat format) throws ParseException {
        return format.parse(formattedDate).getTime();
    }

    /**
     * Convert formatted date string to Date
     */
    public static long toMillisecond(@NotNull String formattedDate, @NotNull String pattern, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, new SimpleDateFormat(pattern, locale)).getTime();
    }

    /**
     * Convert formatted date string to Date
     */
    public static long toMillisecond(@NotNull String formattedDate, @NotNull String pattern) throws ParseException {
        return toDate(formattedDate, new SimpleDateFormat(pattern)).getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy'
     */
    public static long toMillisecondY(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy", locale).getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy'
     */
    public static long toMillisecondY(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy").getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM'
     */
    public static long toMillisecondYM(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM", locale).getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM'
     */
    public static long toMillisecondYM(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM").getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyyMM'
     */
    public static long toMillisecondYMCompact(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyyMM", locale).getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyyMM'
     */
    public static long toMillisecondYMCompact(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyyMM").getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd'
     */
    public static long toMillisecondYMD(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd", locale).getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd'
     */
    public static long toMillisecondYMD(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd").getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyyMMdd'
     */
    public static long toMillisecondYMDCompact(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyyMMdd", locale).getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyyMMdd'
     */
    public static long toMillisecondYMDCompact(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyyMMdd").getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH'
     */
    public static long toMillisecondYMDH(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH", locale).getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH'
     */
    public static long toMillisecondYMDH(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH").getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm'
     */
    public static long toMillisecondYMDHM(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm", locale).getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm'
     */
    public static long toMillisecondYMDHM(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm").getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss'
     */
    public static long toMillisecondYMDHMS(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm:ss", locale).getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss'
     */
    public static long toMillisecondYMDHMS(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm:ss").getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss SSS'
     */
    public static long toMillisecondYMDHMSM(@NotNull String formattedDate, @NotNull Locale locale) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm:ss SSS", locale).getTime();
    }

    /**
     * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss SSS'
     */
    public static long toMillisecondYMDHMSM(@NotNull String formattedDate) throws ParseException {
        return toDate(formattedDate, "yyyy-MM-dd HH:mm:ss SSS").getTime();
    }


    /*
     * format
     */


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

    /**
     * Convert Date to a formatted string using pattern 'yyyy'
     */
    @NotNull
    public static String formatY(@NotNull Date date, @NotNull Locale locale) {
        return format(date, "yyyy", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy'
     */
    @NotNull
    public static String formatY(@NotNull Date date) {
        return format(date, "yyyy");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM'
     */
    @NotNull
    public static String formatYM(@NotNull Date date, @NotNull Locale locale) {
        return format(date, "yyyy-MM", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM'
     */
    @NotNull
    public static String formatYM(@NotNull Date date) {
        return format(date, "yyyy-MM");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyyMM'
     */
    @NotNull
    public static String formatYMCompact(@NotNull Date date, @NotNull Locale locale) {
        return format(date, "yyyyMM", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyyMM'
     */
    @NotNull
    public static String formatYMCompact(@NotNull Date date) {
        return format(date, "yyyyMM");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd'
     */
    @NotNull
    public static String formatYMD(@NotNull Date date, @NotNull Locale locale) {
        return format(date, "yyyy-MM-dd", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd'
     */
    @NotNull
    public static String formatYMD(@NotNull Date date) {
        return format(date, "yyyy-MM-dd");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyyMMdd'
     */
    @NotNull
    public static String formatYMDCompact(@NotNull Date date, @NotNull Locale locale) {
        return format(date, "yyyyMMdd", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyyMMdd'
     */
    @NotNull
    public static String formatYMDCompact(@NotNull Date date) {
        return format(date, "yyyyMMdd");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH'
     */
    @NotNull
    public static String formatYMDH(@NotNull Date date, @NotNull Locale locale) {
        return format(date, "yyyy-MM-dd HH", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH'
     */
    @NotNull
    public static String formatYMDH(@NotNull Date date) {
        return format(date, "yyyy-MM-dd HH");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm'
     */
    @NotNull
    public static String formatYMDHM(@NotNull Date date, @NotNull Locale locale) {
        return format(date, "yyyy-MM-dd HH:mm", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm'
     */
    @NotNull
    public static String formatYMDHM(@NotNull Date date) {
        return format(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss'
     */
    @NotNull
    public static String formatYMDHMS(@NotNull Date date, @NotNull Locale locale) {
        return format(date, "yyyy-MM-dd HH:mm:ss", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss'
     */
    @NotNull
    public static String formatYMDHMS(@NotNull Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss SSS'
     */
    @NotNull
    public static String formatYMDHMSM(@NotNull Date date, @NotNull Locale locale) {
        return format(date, "yyyy-MM-dd HH:mm:ss SSS", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss SSS'
     */
    @NotNull
    public static String formatYMDHMSM(@NotNull Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss SSS");
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
    public static String format(long millisecondValue, @NotNull String pattern, @NotNull Locale locale) {
        return format(new Date(millisecondValue), new SimpleDateFormat(pattern, locale));
    }

    /**
     * Convert Date to a formatted string
     */
    @NotNull
    public static String format(long millisecondValue, @NotNull String pattern) {
        return format(new Date(millisecondValue), new SimpleDateFormat(pattern));
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy'
     */
    @NotNull
    public static String formatY(long millisecondValue, @NotNull Locale locale) {
        return format(new Date(millisecondValue), "yyyy", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy'
     */
    @NotNull
    public static String formatY(long millisecondValue) {
        return format(new Date(millisecondValue), "yyyy");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM'
     */
    @NotNull
    public static String formatYM(long millisecondValue, @NotNull Locale locale) {
        return format(new Date(millisecondValue), "yyyy-MM", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM'
     */
    @NotNull
    public static String formatYM(long millisecondValue) {
        return format(new Date(millisecondValue), "yyyy-MM");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyyMM'
     */
    @NotNull
    public static String formatYMCompact(long millisecondValue, @NotNull Locale locale) {
        return format(new Date(millisecondValue), "yyyyMM", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyyMM'
     */
    @NotNull
    public static String formatYMCompact(long millisecondValue) {
        return format(new Date(millisecondValue), "yyyyMM");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd'
     */
    @NotNull
    public static String formatYMD(long millisecondValue, @NotNull Locale locale) {
        return format(new Date(millisecondValue), "yyyy-MM-dd", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd'
     */
    @NotNull
    public static String formatYMD(long millisecondValue) {
        return format(new Date(millisecondValue), "yyyy-MM-dd");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyyMMdd'
     */
    @NotNull
    public static String formatYMDCompact(long millisecondValue, @NotNull Locale locale) {
        return format(new Date(millisecondValue), "yyyyMMdd", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyyMMdd'
     */
    @NotNull
    public static String formatYMDCompact(long millisecondValue) {
        return format(new Date(millisecondValue), "yyyyMMdd");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH'
     */
    @NotNull
    public static String formatYMDH(long millisecondValue, @NotNull Locale locale) {
        return format(new Date(millisecondValue), "yyyy-MM-dd HH", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH'
     */
    @NotNull
    public static String formatYMDH(long millisecondValue) {
        return format(new Date(millisecondValue), "yyyy-MM-dd HH");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm'
     */
    @NotNull
    public static String formatYMDHM(long millisecondValue, @NotNull Locale locale) {
        return format(new Date(millisecondValue), "yyyy-MM-dd HH:mm", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm'
     */
    @NotNull
    public static String formatYMDHM(long millisecondValue) {
        return format(new Date(millisecondValue), "yyyy-MM-dd HH:mm");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss'
     */
    @NotNull
    public static String formatYMDHMS(long millisecondValue, @NotNull Locale locale) {
        return format(new Date(millisecondValue), "yyyy-MM-dd HH:mm:ss", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss'
     */
    @NotNull
    public static String formatYMDHMS(long millisecondValue) {
        return format(new Date(millisecondValue), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss SSS'
     */
    @NotNull
    public static String formatYMDHMSM(long millisecondValue, @NotNull Locale locale) {
        return format(new Date(millisecondValue), "yyyy-MM-dd HH:mm:ss SSS", locale);
    }

    /**
     * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss SSS'
     */
    @NotNull
    public static String formatYMDHMSM(long millisecondValue) {
        return format(new Date(millisecondValue), "yyyy-MM-dd HH:mm:ss SSS");
    }


    /*
     * get
     */


    /**
     * Get year from calendar
     */
    public static int getYear(@NotNull Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Get month from calendar
     */
    public static int getMonth(@NotNull Calendar calendar) {
        return calendar.get(Calendar.MONTH);
    }

    /**
     * Get weekOfYear from calendar
     */
    public static int getWeekOfYear(@NotNull Calendar calendar) {
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Get weekOfMonth from calendar
     */
    public static int getWeekOfMonth(@NotNull Calendar calendar) {
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * Get dayOfYear from calendar
     */
    public static int getDayOfYear(@NotNull Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Get dayOfMonth from calendar
     */
    public static int getDayOfMonth(@NotNull Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get dayOfWeek from calendar
     */
    public static int getDayOfWeek(@NotNull Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get dayOfWeekInMonth from calendar
     */
    public static int getDayOfWeekInMonth(@NotNull Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * Get hourOfDay from calendar
     */
    public static int getHourOfDay(@NotNull Calendar calendar) {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Get hour from calendar
     */
    public static int getHour(@NotNull Calendar calendar) {
        return calendar.get(Calendar.HOUR);
    }

    /**
     * Get minute from calendar
     */
    public static int getMinute(@NotNull Calendar calendar) {
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * Get second from calendar
     */
    public static int getSecond(@NotNull Calendar calendar) {
        return calendar.get(Calendar.SECOND);
    }

    /**
     * Get millisecond from calendar
     */
    public static int getMillisecond(@NotNull Calendar calendar) {
        return calendar.get(Calendar.MILLISECOND);
    }


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

    /**
     * Get year from date
     */
    public static int getCalendarYear(@NotNull Date date, @NotNull Locale locale) {
        return getYear(createCalendar(date, locale));
    }

    /**
     * Get year from date
     */
    public static int getCalendarYear(@NotNull Date date) {
        return getYear(createCalendar(date));
    }

    /**
     * Get month from date
     */
    public static int getCalendarMonth(@NotNull Date date, @NotNull Locale locale) {
        return getMonth(createCalendar(date, locale));
    }

    /**
     * Get month from date
     */
    public static int getCalendarMonth(@NotNull Date date) {
        return getMonth(createCalendar(date));
    }

    /**
     * Get weekOfYear from date
     */
    public static int getCalendarWeekOfYear(@NotNull Date date, int firstDayOfWeek, @NotNull Locale locale) {
        return getWeekOfYear(createCalendar(date, firstDayOfWeek, locale));
    }

    /**
     * Get weekOfYear from date
     */
    public static int getCalendarWeekOfYear(@NotNull Date date, int firstDayOfWeek) {
        return getWeekOfYear(createCalendar(date, firstDayOfWeek));
    }

    /**
     * Get weekOfYear from date
     */
    public static int getCalendarWeekOfYear(@NotNull Date date, @NotNull Locale locale) {
        return getWeekOfYear(createCalendar(date, locale));
    }

    /**
     * Get weekOfYear from date
     */
    public static int getCalendarWeekOfYear(@NotNull Date date) {
        return getWeekOfYear(createCalendar(date));
    }

    /**
     * Get weekOfMonth from date
     */
    public static int getCalendarWeekOfMonth(@NotNull Date date, int firstDayOfWeek, @NotNull Locale locale) {
        return getWeekOfMonth(createCalendar(date, firstDayOfWeek, locale));
    }

    /**
     * Get weekOfMonth from date
     */
    public static int getCalendarWeekOfMonth(@NotNull Date date, int firstDayOfWeek) {
        return getWeekOfMonth(createCalendar(date, firstDayOfWeek));
    }

    /**
     * Get weekOfMonth from date
     */
    public static int getCalendarWeekOfMonth(@NotNull Date date, @NotNull Locale locale) {
        return getWeekOfMonth(createCalendar(date, locale));
    }

    /**
     * Get weekOfMonth from date
     */
    public static int getCalendarWeekOfMonth(@NotNull Date date) {
        return getWeekOfMonth(createCalendar(date));
    }

    /**
     * Get dayOfYear from date
     */
    public static int getCalendarDayOfYear(@NotNull Date date, @NotNull Locale locale) {
        return getDayOfYear(createCalendar(date, locale));
    }

    /**
     * Get dayOfYear from date
     */
    public static int getCalendarDayOfYear(@NotNull Date date) {
        return getDayOfYear(createCalendar(date));
    }

    /**
     * Get dayOfMonth from date
     */
    public static int getCalendarDayOfMonth(@NotNull Date date, @NotNull Locale locale) {
        return getDayOfMonth(createCalendar(date, locale));
    }

    /**
     * Get dayOfMonth from date
     */
    public static int getCalendarDayOfMonth(@NotNull Date date) {
        return getDayOfMonth(createCalendar(date));
    }

    /**
     * Get dayOfWeek from date
     */
    public static int getCalendarDayOfWeek(@NotNull Date date, int firstDayOfWeek, @NotNull Locale locale) {
        return getDayOfWeek(createCalendar(date, firstDayOfWeek, locale));
    }

    /**
     * Get dayOfWeek from date
     */
    public static int getCalendarDayOfWeek(@NotNull Date date, int firstDayOfWeek) {
        return getDayOfWeek(createCalendar(date, firstDayOfWeek));
    }

    /**
     * Get dayOfWeek from date
     */
    public static int getCalendarDayOfWeek(@NotNull Date date, @NotNull Locale locale) {
        return getDayOfWeek(createCalendar(date, locale));
    }

    /**
     * Get dayOfWeek from date
     */
    public static int getCalendarDayOfWeek(@NotNull Date date) {
        return getDayOfWeek(createCalendar(date));
    }

    /**
     * Get dayOfWeekInMonth from date
     */
    public static int getCalendarDayOfWeekInMonth(@NotNull Date date, int firstDayOfWeek, @NotNull Locale locale) {
        return getDayOfWeekInMonth(createCalendar(date, firstDayOfWeek, locale));
    }

    /**
     * Get dayOfWeekInMonth from date
     */
    public static int getCalendarDayOfWeekInMonth(@NotNull Date date, int firstDayOfWeek) {
        return getDayOfWeekInMonth(createCalendar(date, firstDayOfWeek));
    }

    /**
     * Get dayOfWeekInMonth from date
     */
    public static int getCalendarDayOfWeekInMonth(@NotNull Date date, @NotNull Locale locale) {
        return getDayOfWeekInMonth(createCalendar(date, locale));
    }

    /**
     * Get dayOfWeekInMonth from date
     */
    public static int getCalendarDayOfWeekInMonth(@NotNull Date date) {
        return getDayOfWeekInMonth(createCalendar(date));
    }

    /**
     * Get hourOfDay from date
     */
    public static int getCalendarHourOfDay(@NotNull Date date, @NotNull Locale locale) {
        return getHourOfDay(createCalendar(date, locale));
    }

    /**
     * Get hourOfDay from date
     */
    public static int getCalendarHourOfDay(@NotNull Date date) {
        return getHourOfDay(createCalendar(date));
    }

    /**
     * Get hour from date
     */
    public static int getCalendarHour(@NotNull Date date, @NotNull Locale locale) {
        return getHour(createCalendar(date, locale));
    }

    /**
     * Get hour from date
     */
    public static int getCalendarHour(@NotNull Date date) {
        return getHour(createCalendar(date));
    }

    /**
     * Get minute from date
     */
    public static int getCalendarMinute(@NotNull Date date, @NotNull Locale locale) {
        return getMinute(createCalendar(date, locale));
    }

    /**
     * Get minute from date
     */
    public static int getCalendarMinute(@NotNull Date date) {
        return getMinute(createCalendar(date));
    }

    /**
     * Get second from date
     */
    public static int getCalendarSecond(@NotNull Date date, @NotNull Locale locale) {
        return getSecond(createCalendar(date, locale));
    }

    /**
     * Get second from date
     */
    public static int getCalendarSecond(@NotNull Date date) {
        return getSecond(createCalendar(date));
    }

    /**
     * Get millisecond from date
     */
    public static int getCalendarMillisecond(@NotNull Date date, @NotNull Locale locale) {
        return getMillisecond(createCalendar(date, locale));
    }

    /**
     * Get millisecond from date
     */
    public static int getCalendarMillisecond(@NotNull Date date) {
        return getMillisecond(createCalendar(date));
    }


    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(long millisecondValue, int field, int firstDayOfWeek, @NotNull Locale locale) {
        return createCalendar(new Date(millisecondValue), firstDayOfWeek, locale).get(field);
    }

    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(long millisecondValue, int field, int firstDayOfWeek) {
        return createCalendar(new Date(millisecondValue), firstDayOfWeek).get(field);
    }

    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(long millisecondValue, int field, @NotNull Locale locale) {
        return createCalendar(new Date(millisecondValue), locale).get(field);
    }

    /**
     * Get calendar field from millisecond
     */
    public static int getCalendarField(long millisecondValue, int field) {
        return createCalendar(new Date(millisecondValue)).get(field);
    }

    /**
     * Get year from date
     */
    public static int getCalendarYear(long millisecondValue, @NotNull Locale locale) {
        return getYear(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get year from date
     */
    public static int getCalendarYear(long millisecondValue) {
        return getYear(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get month from date
     */
    public static int getCalendarMonth(long millisecondValue, @NotNull Locale locale) {
        return getMonth(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get month from date
     */
    public static int getCalendarMonth(long millisecondValue) {
        return getMonth(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get weekOfYear from date
     */
    public static int getCalendarWeekOfYear(long millisecondValue, int firstDayOfWeek, @NotNull Locale locale) {
        return getWeekOfYear(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale));
    }

    /**
     * Get weekOfYear from date
     */
    public static int getCalendarWeekOfYear(long millisecondValue, int firstDayOfWeek) {
        return getWeekOfYear(createCalendar(new Date(millisecondValue), firstDayOfWeek));
    }

    /**
     * Get weekOfYear from date
     */
    public static int getCalendarWeekOfYear(long millisecondValue, @NotNull Locale locale) {
        return getWeekOfYear(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get weekOfYear from date
     */
    public static int getCalendarWeekOfYear(long millisecondValue) {
        return getWeekOfYear(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get weekOfMonth from date
     */
    public static int getCalendarWeekOfMonth(long millisecondValue, int firstDayOfWeek, @NotNull Locale locale) {
        return getWeekOfMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale));
    }

    /**
     * Get weekOfMonth from date
     */
    public static int getCalendarWeekOfMonth(long millisecondValue, int firstDayOfWeek) {
        return getWeekOfMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek));
    }

    /**
     * Get weekOfMonth from date
     */
    public static int getCalendarWeekOfMonth(long millisecondValue, @NotNull Locale locale) {
        return getWeekOfMonth(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get weekOfMonth from date
     */
    public static int getCalendarWeekOfMonth(long millisecondValue) {
        return getWeekOfMonth(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get dayOfYear from date
     */
    public static int getCalendarDayOfYear(long millisecondValue, @NotNull Locale locale) {
        return getDayOfYear(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get dayOfYear from date
     */
    public static int getCalendarDayOfYear(long millisecondValue) {
        return getDayOfYear(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get dayOfMonth from date
     */
    public static int getCalendarDayOfMonth(long millisecondValue, @NotNull Locale locale) {
        return getDayOfMonth(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get dayOfMonth from date
     */
    public static int getCalendarDayOfMonth(long millisecondValue) {
        return getDayOfMonth(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get dayOfWeek from date
     */
    public static int getCalendarDayOfWeek(long millisecondValue, int firstDayOfWeek, @NotNull Locale locale) {
        return getDayOfWeek(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale));
    }

    /**
     * Get dayOfWeek from date
     */
    public static int getCalendarDayOfWeek(long millisecondValue, int firstDayOfWeek) {
        return getDayOfWeek(createCalendar(new Date(millisecondValue), firstDayOfWeek));
    }

    /**
     * Get dayOfWeek from date
     */
    public static int getCalendarDayOfWeek(long millisecondValue, @NotNull Locale locale) {
        return getDayOfWeek(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get dayOfWeek from date
     */
    public static int getCalendarDayOfWeek(long millisecondValue) {
        return getDayOfWeek(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get dayOfWeekInMonth from date
     */
    public static int getCalendarDayOfWeekInMonth(long millisecondValue, int firstDayOfWeek, @NotNull Locale locale) {
        return getDayOfWeekInMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale));
    }

    /**
     * Get dayOfWeekInMonth from date
     */
    public static int getCalendarDayOfWeekInMonth(long millisecondValue, int firstDayOfWeek) {
        return getDayOfWeekInMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek));
    }

    /**
     * Get dayOfWeekInMonth from date
     */
    public static int getCalendarDayOfWeekInMonth(long millisecondValue, @NotNull Locale locale) {
        return getDayOfWeekInMonth(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get dayOfWeekInMonth from date
     */
    public static int getCalendarDayOfWeekInMonth(long millisecondValue) {
        return getDayOfWeekInMonth(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get hourOfDay from date
     */
    public static int getCalendarHourOfDay(long millisecondValue, @NotNull Locale locale) {
        return getHourOfDay(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get hourOfDay from date
     */
    public static int getCalendarHourOfDay(long millisecondValue) {
        return getHourOfDay(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get hour from date
     */
    public static int getCalendarHour(long millisecondValue, @NotNull Locale locale) {
        return getHour(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get hour from date
     */
    public static int getCalendarHour(long millisecondValue) {
        return getHour(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get minute from date
     */
    public static int getCalendarMinute(long millisecondValue, @NotNull Locale locale) {
        return getMinute(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get minute from date
     */
    public static int getCalendarMinute(long millisecondValue) {
        return getMinute(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get second from date
     */
    public static int getCalendarSecond(long millisecondValue, @NotNull Locale locale) {
        return getSecond(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get second from date
     */
    public static int getCalendarSecond(long millisecondValue) {
        return getSecond(createCalendar(new Date(millisecondValue)));
    }

    /**
     * Get millisecond from date
     */
    public static int getCalendarMillisecond(long millisecondValue, @NotNull Locale locale) {
        return getMillisecond(createCalendar(new Date(millisecondValue), locale));
    }

    /**
     * Get millisecond from date
     */
    public static int getCalendarMillisecond(long millisecondValue) {
        return getMillisecond(createCalendar(new Date(millisecondValue)));
    }


    /*
     * add
     */


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

    /**
     * Increase the YEAR field
     */
    @NotNull
    public static Date addYear(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.YEAR, amount, locale);
    }

    /**
     * Increase the YEAR field
     */
    @NotNull
    public static Date addYear(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.YEAR, amount);
    }

    /**
     * Increase the MONTH field
     */
    @NotNull
    public static Date addMonth(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.MONTH, amount, locale);
    }

    /**
     * Increase the MONTH field
     */
    @NotNull
    public static Date addMonth(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.MONTH, amount);
    }

    /**
     * Increase the WEEK_OF_YEAR field
     */
    @NotNull
    public static Date addWeekOfYear(@NotNull Date date, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.WEEK_OF_YEAR, amount, firstDayOfWeek, locale);
    }

    /**
     * Increase the WEEK_OF_YEAR field
     */
    @NotNull
    public static Date addWeekOfYear(@NotNull Date date, int amount, int firstDayOfWeek) {
        return addCalendarField(date, Calendar.WEEK_OF_YEAR, amount, firstDayOfWeek);
    }

    /**
     * Increase the WEEK_OF_YEAR field
     */
    @NotNull
    public static Date addWeekOfYear(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.WEEK_OF_YEAR, amount, locale);
    }

    /**
     * Increase the WEEK_OF_YEAR field
     */
    @NotNull
    public static Date addWeekOfYear(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.WEEK_OF_YEAR, amount);
    }

    /**
     * Increase the WEEK_OF_MONTH field
     */
    @NotNull
    public static Date addWeekOfMonth(@NotNull Date date, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.WEEK_OF_MONTH, amount, firstDayOfWeek, locale);
    }

    /**
     * Increase the WEEK_OF_MONTH field
     */
    @NotNull
    public static Date addWeekOfMonth(@NotNull Date date, int amount, int firstDayOfWeek) {
        return addCalendarField(date, Calendar.WEEK_OF_MONTH, amount, firstDayOfWeek);
    }

    /**
     * Increase the WEEK_OF_MONTH field
     */
    @NotNull
    public static Date addWeekOfMonth(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.WEEK_OF_MONTH, amount, locale);
    }

    /**
     * Increase the WEEK_OF_MONTH field
     */
    @NotNull
    public static Date addWeekOfMonth(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.WEEK_OF_MONTH, amount);
    }

    /**
     * Increase the DAY_OF_YEAR field
     */
    @NotNull
    public static Date addDayOfYear(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.DAY_OF_YEAR, amount, locale);
    }

    /**
     * Increase the DAY_OF_YEAR field
     */
    @NotNull
    public static Date addDayOfYear(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * Increase the DAY_OF_MONTH field
     */
    @NotNull
    public static Date addDayOfMonth(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.DAY_OF_MONTH, amount, locale);
    }

    /**
     * Increase the DAY_OF_MONTH field
     */
    @NotNull
    public static Date addDayOfMonth(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * Increase the DAY_OF_WEEK_IN_MONTH field
     */
    @NotNull
    public static Date addDayOfWeekInMonth(@NotNull Date date, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.DAY_OF_WEEK_IN_MONTH, amount, firstDayOfWeek, locale);
    }

    /**
     * Increase the DAY_OF_WEEK_IN_MONTH field
     */
    @NotNull
    public static Date addDayOfWeekInMonth(@NotNull Date date, int amount, int firstDayOfWeek) {
        return addCalendarField(date, Calendar.DAY_OF_WEEK_IN_MONTH, amount, firstDayOfWeek);
    }

    /**
     * Increase the DAY_OF_WEEK_IN_MONTH field
     */
    @NotNull
    public static Date addDayOfWeekInMonth(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.DAY_OF_WEEK_IN_MONTH, amount, locale);
    }

    /**
     * Increase the DAY_OF_WEEK_IN_MONTH field
     */
    @NotNull
    public static Date addDayOfWeekInMonth(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.DAY_OF_WEEK_IN_MONTH, amount);
    }

    /**
     * Increase the DAY_OF_WEEK field
     */
    @NotNull
    public static Date addDayOfWeek(@NotNull Date date, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.DAY_OF_WEEK, amount, firstDayOfWeek, locale);
    }

    /**
     * Increase the DAY_OF_WEEK field
     */
    @NotNull
    public static Date addDayOfWeek(@NotNull Date date, int amount, int firstDayOfWeek) {
        return addCalendarField(date, Calendar.DAY_OF_WEEK, amount, firstDayOfWeek);
    }

    /**
     * Increase the DAY_OF_WEEK field
     */
    @NotNull
    public static Date addDayOfWeek(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.DAY_OF_WEEK, amount, locale);
    }

    /**
     * Increase the DAY_OF_WEEK field
     */
    @NotNull
    public static Date addDayOfWeek(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.DAY_OF_WEEK, amount);
    }

    /**
     * Increase the HOUR_OF_DAY field
     */
    @NotNull
    public static Date addHourOfDay(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.HOUR_OF_DAY, amount, locale);
    }

    /**
     * Increase the HOUR_OF_DAY field
     */
    @NotNull
    public static Date addHourOfDay(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * Increase the HOUR field
     */
    @NotNull
    public static Date addHour(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.HOUR, amount, locale);
    }

    /**
     * Increase the HOUR field
     */
    @NotNull
    public static Date addHour(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.HOUR, amount);
    }

    /**
     * Increase the MINUTE field
     */
    @NotNull
    public static Date addMinute(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.MINUTE, amount, locale);
    }

    /**
     * Increase the MINUTE field
     */
    @NotNull
    public static Date addMinute(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.MINUTE, amount);
    }

    /**
     * Increase the SECOND field
     */
    @NotNull
    public static Date addSecond(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.SECOND, amount, locale);
    }

    /**
     * Increase the SECOND field
     */
    @NotNull
    public static Date addSecond(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.SECOND, amount);
    }

    /**
     * Increase the MILLISECOND field
     */
    @NotNull
    public static Date addMillisecond(@NotNull Date date, int amount, @NotNull Locale locale) {
        return addCalendarField(date, Calendar.MILLISECOND, amount, locale);
    }

    /**
     * Increase the MILLISECOND field
     */
    @NotNull
    public static Date addMillisecond(@NotNull Date date, int amount) {
        return addCalendarField(date, Calendar.MILLISECOND, amount);
    }


    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(long millisecondValue, int field, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return addToDate(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale), field, amount);
    }

    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(long millisecondValue, int field, int amount, int firstDayOfWeek) {
        return addToDate(createCalendar(new Date(millisecondValue), firstDayOfWeek), field, amount);
    }

    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(long millisecondValue, int field, int amount, @NotNull Locale locale) {
        return addToDate(createCalendar(new Date(millisecondValue), locale), field, amount);
    }

    /**
     * Increase the specified calendar field
     */
    @NotNull
    public static Date addCalendarField(long millisecondValue, int field, int amount) {
        return addToDate(createCalendar(new Date(millisecondValue)), field, amount);
    }

    /**
     * Increase the YEAR field
     */
    @NotNull
    public static Date addYear(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.YEAR, amount, locale);
    }

    /**
     * Increase the YEAR field
     */
    @NotNull
    public static Date addYear(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.YEAR, amount);
    }

    /**
     * Increase the MONTH field
     */
    @NotNull
    public static Date addMonth(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.MONTH, amount, locale);
    }

    /**
     * Increase the MONTH field
     */
    @NotNull
    public static Date addMonth(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.MONTH, amount);
    }

    /**
     * Increase the WEEK_OF_YEAR field
     */
    @NotNull
    public static Date addWeekOfYear(long millisecondValue, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.WEEK_OF_YEAR, amount, firstDayOfWeek, locale);
    }

    /**
     * Increase the WEEK_OF_YEAR field
     */
    @NotNull
    public static Date addWeekOfYear(long millisecondValue, int amount, int firstDayOfWeek) {
        return addCalendarField(new Date(millisecondValue), Calendar.WEEK_OF_YEAR, amount, firstDayOfWeek);
    }

    /**
     * Increase the WEEK_OF_YEAR field
     */
    @NotNull
    public static Date addWeekOfYear(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.WEEK_OF_YEAR, amount, locale);
    }

    /**
     * Increase the WEEK_OF_YEAR field
     */
    @NotNull
    public static Date addWeekOfYear(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.WEEK_OF_YEAR, amount);
    }

    /**
     * Increase the WEEK_OF_MONTH field
     */
    @NotNull
    public static Date addWeekOfMonth(long millisecondValue, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.WEEK_OF_MONTH, amount, firstDayOfWeek, locale);
    }

    /**
     * Increase the WEEK_OF_MONTH field
     */
    @NotNull
    public static Date addWeekOfMonth(long millisecondValue, int amount, int firstDayOfWeek) {
        return addCalendarField(new Date(millisecondValue), Calendar.WEEK_OF_MONTH, amount, firstDayOfWeek);
    }

    /**
     * Increase the WEEK_OF_MONTH field
     */
    @NotNull
    public static Date addWeekOfMonth(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.WEEK_OF_MONTH, amount, locale);
    }

    /**
     * Increase the WEEK_OF_MONTH field
     */
    @NotNull
    public static Date addWeekOfMonth(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.WEEK_OF_MONTH, amount);
    }

    /**
     * Increase the DAY_OF_YEAR field
     */
    @NotNull
    public static Date addDayOfYear(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_YEAR, amount, locale);
    }

    /**
     * Increase the DAY_OF_YEAR field
     */
    @NotNull
    public static Date addDayOfYear(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * Increase the DAY_OF_MONTH field
     */
    @NotNull
    public static Date addDayOfMonth(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_MONTH, amount, locale);
    }

    /**
     * Increase the DAY_OF_MONTH field
     */
    @NotNull
    public static Date addDayOfMonth(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * Increase the DAY_OF_WEEK_IN_MONTH field
     */
    @NotNull
    public static Date addDayOfWeekInMonth(long millisecondValue, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_WEEK_IN_MONTH, amount, firstDayOfWeek, locale);
    }

    /**
     * Increase the DAY_OF_WEEK_IN_MONTH field
     */
    @NotNull
    public static Date addDayOfWeekInMonth(long millisecondValue, int amount, int firstDayOfWeek) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_WEEK_IN_MONTH, amount, firstDayOfWeek);
    }

    /**
     * Increase the DAY_OF_WEEK_IN_MONTH field
     */
    @NotNull
    public static Date addDayOfWeekInMonth(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_WEEK_IN_MONTH, amount, locale);
    }

    /**
     * Increase the DAY_OF_WEEK_IN_MONTH field
     */
    @NotNull
    public static Date addDayOfWeekInMonth(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_WEEK_IN_MONTH, amount);
    }

    /**
     * Increase the DAY_OF_WEEK field
     */
    @NotNull
    public static Date addDayOfWeek(long millisecondValue, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_WEEK, amount, firstDayOfWeek, locale);
    }

    /**
     * Increase the DAY_OF_WEEK field
     */
    @NotNull
    public static Date addDayOfWeek(long millisecondValue, int amount, int firstDayOfWeek) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_WEEK, amount, firstDayOfWeek);
    }

    /**
     * Increase the DAY_OF_WEEK field
     */
    @NotNull
    public static Date addDayOfWeek(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_WEEK, amount, locale);
    }

    /**
     * Increase the DAY_OF_WEEK field
     */
    @NotNull
    public static Date addDayOfWeek(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.DAY_OF_WEEK, amount);
    }

    /**
     * Increase the HOUR_OF_DAY field
     */
    @NotNull
    public static Date addHourOfDay(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.HOUR_OF_DAY, amount, locale);
    }

    /**
     * Increase the HOUR_OF_DAY field
     */
    @NotNull
    public static Date addHourOfDay(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * Increase the HOUR field
     */
    @NotNull
    public static Date addHour(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.HOUR, amount, locale);
    }

    /**
     * Increase the HOUR field
     */
    @NotNull
    public static Date addHour(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.HOUR, amount);
    }

    /**
     * Increase the MINUTE field
     */
    @NotNull
    public static Date addMinute(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.MINUTE, amount, locale);
    }

    /**
     * Increase the MINUTE field
     */
    @NotNull
    public static Date addMinute(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.MINUTE, amount);
    }

    /**
     * Increase the SECOND field
     */
    @NotNull
    public static Date addSecond(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.SECOND, amount, locale);
    }

    /**
     * Increase the SECOND field
     */
    @NotNull
    public static Date addSecond(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.SECOND, amount);
    }

    /**
     * Increase the MILLISECOND field
     */
    @NotNull
    public static Date addMillisecond(long millisecondValue, int amount, @NotNull Locale locale) {
        return addCalendarField(new Date(millisecondValue), Calendar.MILLISECOND, amount, locale);
    }

    /**
     * Increase the MILLISECOND field
     */
    @NotNull
    public static Date addMillisecond(long millisecondValue, int amount) {
        return addCalendarField(new Date(millisecondValue), Calendar.MILLISECOND, amount);
    }


    /*
     * isSame
     */


    /**
     * Return true if the year is the same
     */
    public static boolean isSameYear(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR);
    }

    /**
     * Returns true if the year and month are the same
     */
    public static boolean isSameMonth(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH);
    }

    /**
     * Return true if the months is the same
     */
    public static boolean isSameMonthOfYear(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH);
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
        } else if (differDayOfYear(calendar, target, 7)) {
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
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.WEEK_OF_YEAR) == target.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.WEEK_OF_MONTH) == target.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * Returns true if the year, month, week and day are the same
     */
    public static boolean isSameDay(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Return true if the dayOfYear is the same
     */
    public static boolean isSameDayOfYear(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.DAY_OF_YEAR) == target.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Return true if the dayOfMonth is the same
     */
    public static boolean isSameDayOfMonth(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.DAY_OF_WEEK) == target.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) == target.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * Returns true if the year, month, week, day and hour are the same
     */
    public static boolean isSameHour(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.HOUR_OF_DAY) == target.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Return true if the 24H hour is the same
     */
    public static boolean isSameHourOf24H(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.HOUR_OF_DAY) == target.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Return true if the 12H hour is the same
     */
    public static boolean isSameHourOf12H(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.HOUR) == target.get(Calendar.HOUR);
    }

    /**
     * Returns true if the year, month, week, day, hour and minute are the same
     */
    public static boolean isSameMinute(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.HOUR_OF_DAY) == target.get(Calendar.HOUR_OF_DAY) && calendar.get(Calendar.MINUTE) == target.get(Calendar.MINUTE);
    }

    /**
     * Return true if the minuteOfHour is the same
     */
    public static boolean isSameMinuteOfHour(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.MINUTE) == target.get(Calendar.MINUTE);
    }

    /**
     * Returns true if the year, month, week, day, hour, minute and second are the same
     */
    public static boolean isSameSecond(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.HOUR_OF_DAY) == target.get(Calendar.HOUR_OF_DAY) && calendar.get(Calendar.MINUTE) == target.get(Calendar.MINUTE)
                && calendar.get(Calendar.SECOND) == target.get(Calendar.SECOND);
    }

    /**
     * Return true if the secondOfMinute is the same
     */
    public static boolean isSameSecondOfMinute(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.SECOND) == target.get(Calendar.SECOND);
    }

    /**
     * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
     */
    public static boolean isSameMillisecond(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == target.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.HOUR_OF_DAY) == target.get(Calendar.HOUR_OF_DAY) && calendar.get(Calendar.MINUTE) == target.get(Calendar.MINUTE)
                && calendar.get(Calendar.SECOND) == target.get(Calendar.SECOND) && calendar.get(Calendar.MILLISECOND) == target.get(Calendar.MILLISECOND);
    }

    /**
     * Return true if the millisecondOfSecond is the same
     */
    public static boolean isSameMillisecondOfSecond(@NotNull Calendar calendar, @NotNull Calendar target) {
        return calendar.get(Calendar.ERA) == target.get(Calendar.ERA) && calendar.get(Calendar.MILLISECOND) == target.get(Calendar.MILLISECOND);
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


    /**
     * Return true if the year is the same
     */
    public static boolean isSameYear(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameYear(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the year is the same
     */
    public static boolean isSameYear(long millisecondValue, long targetMillisecondValue) {
        return isSameYear(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Returns true if the year and month are the same
     */
    public static boolean isSameMonth(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameMonth(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Returns true if the year and month are the same
     */
    public static boolean isSameMonth(long millisecondValue, long targetMillisecondValue) {
        return isSameMonth(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the months is the same
     */
    public static boolean isSameMonthOfYear(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameMonthOfYear(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the months is the same
     */
    public static boolean isSameMonthOfYear(long millisecondValue, long targetMillisecondValue) {
        return isSameMonthOfYear(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek, @NotNull Locale locale) {
        return isSameWeek(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek, locale));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek) {
        return isSameWeek(createCalendar(new Date(millisecondValue), firstDayOfWeek), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameWeek(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Returns true if the year, month, and week are the same
     */
    public static boolean isSameWeek(long millisecondValue, long targetMillisecondValue) {
        return isSameWeek(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek, @NotNull Locale locale) {
        return isSameWeekOfYear(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek, locale));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek) {
        return isSameWeekOfYear(createCalendar(new Date(millisecondValue), firstDayOfWeek), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameWeekOfYear(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the weekOfYear is the same
     */
    public static boolean isSameWeekOfYear(long millisecondValue, long targetMillisecondValue) {
        return isSameWeekOfYear(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek, @NotNull Locale locale) {
        return isSameWeekOfMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek, locale));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek) {
        return isSameWeekOfMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameWeekOfMonth(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the weekOfMonth is the same
     */
    public static boolean isSameWeekOfMonth(long millisecondValue, long targetMillisecondValue) {
        return isSameWeekOfMonth(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Returns true if the year, month, week and day are the same
     */
    public static boolean isSameDay(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameDay(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Returns true if the year, month, week and day are the same
     */
    public static boolean isSameDay(long millisecondValue, long targetMillisecondValue) {
        return isSameDay(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the dayOfYear is the same
     */
    public static boolean isSameDayOfYear(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameDayOfYear(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the dayOfYear is the same
     */
    public static boolean isSameDayOfYear(long millisecondValue, long targetMillisecondValue) {
        return isSameDayOfYear(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the dayOfMonth is the same
     */
    public static boolean isSameDayOfMonth(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameDayOfMonth(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the dayOfMonth is the same
     */
    public static boolean isSameDayOfMonth(long millisecondValue, long targetMillisecondValue) {
        return isSameDayOfMonth(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek, @NotNull Locale locale) {
        return isSameDayOfWeek(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek, locale));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek) {
        return isSameDayOfWeek(createCalendar(new Date(millisecondValue), firstDayOfWeek), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameDayOfWeek(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the dayOfWeek is the same
     */
    public static boolean isSameDayOfWeek(long millisecondValue, long targetMillisecondValue) {
        return isSameDayOfWeek(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek, @NotNull Locale locale) {
        return isSameDayOfWeekInMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek, locale));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue, int firstDayOfWeek) {
        return isSameDayOfWeekInMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameDayOfWeekInMonth(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the dayOfWeekInMonth is the same
     */
    public static boolean isSameDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue) {
        return isSameDayOfWeekInMonth(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Returns true if the year, month, week, day and hour are the same
     */
    public static boolean isSameHour(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameHour(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Returns true if the year, month, week, day and hour are the same
     */
    public static boolean isSameHour(long millisecondValue, long targetMillisecondValue) {
        return isSameHour(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the 24H hour is the same
     */
    public static boolean isSameHourOf24H(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameHourOf24H(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the 24H hour is the same
     */
    public static boolean isSameHourOf24H(long millisecondValue, long targetMillisecondValue) {
        return isSameHourOf24H(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the 12H hour is the same
     */
    public static boolean isSameHourOf12H(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameHourOf12H(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the 12H hour is the same
     */
    public static boolean isSameHourOf12H(long millisecondValue, long targetMillisecondValue) {
        return isSameHourOf12H(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Returns true if the year, month, week, day, hour and minute are the same
     */
    public static boolean isSameMinute(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameMinute(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Returns true if the year, month, week, day, hour and minute are the same
     */
    public static boolean isSameMinute(long millisecondValue, long targetMillisecondValue) {
        return isSameMinute(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the minuteOfHour is the same
     */
    public static boolean isSameMinuteOfHour(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameMinuteOfHour(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the minuteOfHour is the same
     */
    public static boolean isSameMinuteOfHour(long millisecondValue, long targetMillisecondValue) {
        return isSameMinuteOfHour(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute and second are the same
     */
    public static boolean isSameSecond(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameSecond(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute and second are the same
     */
    public static boolean isSameSecond(long millisecondValue, long targetMillisecondValue) {
        return isSameSecond(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the secondOfMinute is the same
     */
    public static boolean isSameSecondOfMinute(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameSecondOfMinute(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the secondOfMinute is the same
     */
    public static boolean isSameSecondOfMinute(long millisecondValue, long targetMillisecondValue) {
        return isSameSecondOfMinute(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
     */
    public static boolean isSameMillisecond(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameMillisecond(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
     */
    public static boolean isSameMillisecond(long millisecondValue, long targetMillisecondValue) {
        return isSameMillisecond(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }

    /**
     * Return true if the millisecondOfSecond is the same
     */
    public static boolean isSameMillisecondOfSecond(long millisecondValue, long targetMillisecondValue, @NotNull Locale locale) {
        return isSameMillisecondOfSecond(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale));
    }

    /**
     * Return true if the millisecondOfSecond is the same
     */
    public static boolean isSameMillisecondOfSecond(long millisecondValue, long targetMillisecondValue) {
        return isSameMillisecondOfSecond(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)));
    }


    /*
     * differ
     */


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
     * Return true if the difference from the [target] does not exceed the [amount] year
     */
    public static boolean differYear(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.YEAR, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] month
     */
    public static boolean differMonth(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.MONTH, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
     */
    public static boolean differWeekOfYear(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.WEEK_OF_YEAR, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
     */
    public static boolean differWeekOfMonth(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.WEEK_OF_MONTH, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfYear
     */
    public static boolean differDayOfYear(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfMonth
     */
    public static boolean differDayOfMonth(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
     */
    public static boolean differDayOfWeek(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.DAY_OF_WEEK, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
     */
    public static boolean differDayOfWeekInMonth(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.DAY_OF_WEEK_IN_MONTH, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] hourOfDay
     */
    public static boolean differHourOfDay(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] hour
     */
    public static boolean differHour(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.HOUR, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] minute
     */
    public static boolean differMinute(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.MINUTE, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] second
     */
    public static boolean differSecond(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.SECOND, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] millisecond
     */
    public static boolean differMillisecond(@NotNull Calendar calendar, @NotNull Calendar target, int amount) {
        return differField(calendar, target, Calendar.MILLISECOND, amount);
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

    /**
     * Return true if the difference from the [target] does not exceed the [amount] year
     */
    public static boolean differYear(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differYear(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] year
     */
    public static boolean differYear(@NotNull Date date, @NotNull Date target, int amount) {
        return differYear(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] month
     */
    public static boolean differMonth(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differMonth(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] month
     */
    public static boolean differMonth(@NotNull Date date, @NotNull Date target, int amount) {
        return differMonth(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
     */
    public static boolean differWeekOfYear(@NotNull Date date, @NotNull Date target, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return differWeekOfYear(createCalendar(date, firstDayOfWeek, locale), createCalendar(target, firstDayOfWeek, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
     */
    public static boolean differWeekOfYear(@NotNull Date date, @NotNull Date target, int amount, int firstDayOfWeek) {
        return differWeekOfYear(createCalendar(date, firstDayOfWeek), createCalendar(target, firstDayOfWeek), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
     */
    public static boolean differWeekOfYear(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differWeekOfYear(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
     */
    public static boolean differWeekOfYear(@NotNull Date date, @NotNull Date target, int amount) {
        return differWeekOfYear(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
     */
    public static boolean differWeekOfMonth(@NotNull Date date, @NotNull Date target, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return differWeekOfMonth(createCalendar(date, firstDayOfWeek, locale), createCalendar(target, firstDayOfWeek, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
     */
    public static boolean differWeekOfMonth(@NotNull Date date, @NotNull Date target, int amount, int firstDayOfWeek) {
        return differWeekOfMonth(createCalendar(date, firstDayOfWeek), createCalendar(target, firstDayOfWeek), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
     */
    public static boolean differWeekOfMonth(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differWeekOfMonth(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
     */
    public static boolean differWeekOfMonth(@NotNull Date date, @NotNull Date target, int amount) {
        return differWeekOfMonth(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfYear
     */
    public static boolean differDayOfYear(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differDayOfYear(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfYear
     */
    public static boolean differDayOfYear(@NotNull Date date, @NotNull Date target, int amount) {
        return differDayOfYear(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfMonth
     */
    public static boolean differDayOfMonth(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differDayOfMonth(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfMonth
     */
    public static boolean differDayOfMonth(@NotNull Date date, @NotNull Date target, int amount) {
        return differDayOfMonth(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
     */
    public static boolean differDayOfWeek(@NotNull Date date, @NotNull Date target, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return differDayOfWeek(createCalendar(date, firstDayOfWeek, locale), createCalendar(target, firstDayOfWeek, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
     */
    public static boolean differDayOfWeek(@NotNull Date date, @NotNull Date target, int amount, int firstDayOfWeek) {
        return differDayOfWeek(createCalendar(date, firstDayOfWeek), createCalendar(target, firstDayOfWeek), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
     */
    public static boolean differDayOfWeek(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differDayOfWeek(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
     */
    public static boolean differDayOfWeek(@NotNull Date date, @NotNull Date target, int amount) {
        return differDayOfWeek(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
     */
    public static boolean differDayOfWeekInMonth(@NotNull Date date, @NotNull Date target, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return differDayOfWeekInMonth(createCalendar(date, firstDayOfWeek, locale), createCalendar(target, firstDayOfWeek, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
     */
    public static boolean differDayOfWeekInMonth(@NotNull Date date, @NotNull Date target, int amount, int firstDayOfWeek) {
        return differDayOfWeekInMonth(createCalendar(date, firstDayOfWeek), createCalendar(target, firstDayOfWeek), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
     */
    public static boolean differDayOfWeekInMonth(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differDayOfWeekInMonth(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
     */
    public static boolean differDayOfWeekInMonth(@NotNull Date date, @NotNull Date target, int amount) {
        return differDayOfWeekInMonth(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] hourOfDay
     */
    public static boolean differHourOfDay(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differHourOfDay(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] hourOfDay
     */
    public static boolean differHourOfDay(@NotNull Date date, @NotNull Date target, int amount) {
        return differHourOfDay(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] hour
     */
    public static boolean differHour(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differHour(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] hour
     */
    public static boolean differHour(@NotNull Date date, @NotNull Date target, int amount) {
        return differHour(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] minute
     */
    public static boolean differMinute(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differMinute(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] minute
     */
    public static boolean differMinute(@NotNull Date date, @NotNull Date target, int amount) {
        return differMinute(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] second
     */
    public static boolean differSecond(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differSecond(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] second
     */
    public static boolean differSecond(@NotNull Date date, @NotNull Date target, int amount) {
        return differSecond(createCalendar(date), createCalendar(target), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] millisecond
     */
    public static boolean differMillisecond(@NotNull Date date, @NotNull Date target, int amount, @NotNull Locale locale) {
        return differMillisecond(createCalendar(date, locale), createCalendar(target, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] millisecond
     */
    public static boolean differMillisecond(@NotNull Date date, @NotNull Date target, int amount) {
        return differMillisecond(createCalendar(date), createCalendar(target), amount);
    }


    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(long millisecondValue, long targetMillisecondValue, int field, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return differField(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek, locale), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(long millisecondValue, long targetMillisecondValue, int field, int amount, int firstDayOfWeek) {
        return differField(createCalendar(new Date(millisecondValue), firstDayOfWeek), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(long millisecondValue, long targetMillisecondValue, int field, int amount, @NotNull Locale locale) {
        return differField(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
     */
    public static boolean differCalendarField(long millisecondValue, long targetMillisecondValue, int field, int amount) {
        return differField(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), field, amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] year
     */
    public static boolean differYear(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differYear(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] year
     */
    public static boolean differYear(long millisecondValue, long targetMillisecondValue, int amount) {
        return differYear(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] month
     */
    public static boolean differMonth(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differMonth(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] month
     */
    public static boolean differMonth(long millisecondValue, long targetMillisecondValue, int amount) {
        return differMonth(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
     */
    public static boolean differWeekOfYear(long millisecondValue, long targetMillisecondValue, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return differWeekOfYear(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
     */
    public static boolean differWeekOfYear(long millisecondValue, long targetMillisecondValue, int amount, int firstDayOfWeek) {
        return differWeekOfYear(createCalendar(new Date(millisecondValue), firstDayOfWeek), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
     */
    public static boolean differWeekOfYear(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differWeekOfYear(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
     */
    public static boolean differWeekOfYear(long millisecondValue, long targetMillisecondValue, int amount) {
        return differWeekOfYear(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
     */
    public static boolean differWeekOfMonth(long millisecondValue, long targetMillisecondValue, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return differWeekOfMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
     */
    public static boolean differWeekOfMonth(long millisecondValue, long targetMillisecondValue, int amount, int firstDayOfWeek) {
        return differWeekOfMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
     */
    public static boolean differWeekOfMonth(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differWeekOfMonth(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
     */
    public static boolean differWeekOfMonth(long millisecondValue, long targetMillisecondValue, int amount) {
        return differWeekOfMonth(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfYear
     */
    public static boolean differDayOfYear(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differDayOfYear(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfYear
     */
    public static boolean differDayOfYear(long millisecondValue, long targetMillisecondValue, int amount) {
        return differDayOfYear(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfMonth
     */
    public static boolean differDayOfMonth(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differDayOfMonth(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfMonth
     */
    public static boolean differDayOfMonth(long millisecondValue, long targetMillisecondValue, int amount) {
        return differDayOfMonth(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
     */
    public static boolean differDayOfWeek(long millisecondValue, long targetMillisecondValue, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return differDayOfWeek(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
     */
    public static boolean differDayOfWeek(long millisecondValue, long targetMillisecondValue, int amount, int firstDayOfWeek) {
        return differDayOfWeek(createCalendar(new Date(millisecondValue), firstDayOfWeek), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
     */
    public static boolean differDayOfWeek(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differDayOfWeek(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
     */
    public static boolean differDayOfWeek(long millisecondValue, long targetMillisecondValue, int amount) {
        return differDayOfWeek(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
     */
    public static boolean differDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue, int amount, int firstDayOfWeek, @NotNull Locale locale) {
        return differDayOfWeekInMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek, locale), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek, locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
     */
    public static boolean differDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue, int amount, int firstDayOfWeek) {
        return differDayOfWeekInMonth(createCalendar(new Date(millisecondValue), firstDayOfWeek), createCalendar(new Date(targetMillisecondValue), firstDayOfWeek), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
     */
    public static boolean differDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differDayOfWeekInMonth(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
     */
    public static boolean differDayOfWeekInMonth(long millisecondValue, long targetMillisecondValue, int amount) {
        return differDayOfWeekInMonth(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] hourOfDay
     */
    public static boolean differHourOfDay(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differHourOfDay(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] hourOfDay
     */
    public static boolean differHourOfDay(long millisecondValue, long targetMillisecondValue, int amount) {
        return differHourOfDay(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] hour
     */
    public static boolean differHour(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differHour(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] hour
     */
    public static boolean differHour(long millisecondValue, long targetMillisecondValue, int amount) {
        return differHour(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] minute
     */
    public static boolean differMinute(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differMinute(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] minute
     */
    public static boolean differMinute(long millisecondValue, long targetMillisecondValue, int amount) {
        return differMinute(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] second
     */
    public static boolean differSecond(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differSecond(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] second
     */
    public static boolean differSecond(long millisecondValue, long targetMillisecondValue, int amount) {
        return differSecond(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] millisecond
     */
    public static boolean differMillisecond(long millisecondValue, long targetMillisecondValue, int amount, @NotNull Locale locale) {
        return differMillisecond(createCalendar(new Date(millisecondValue), locale), createCalendar(new Date(targetMillisecondValue), locale), amount);
    }

    /**
     * Return true if the difference from the [target] does not exceed the [amount] millisecond
     */
    public static boolean differMillisecond(long millisecondValue, long targetMillisecondValue, int amount) {
        return differMillisecond(createCalendar(new Date(millisecondValue)), createCalendar(new Date(targetMillisecondValue)), amount);
    }
}
