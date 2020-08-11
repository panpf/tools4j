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

@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4j.datetime.ktx

import com.github.panpf.tools4j.datetime.Datex
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/*
 * Date related extension methods or properties
 */

/**
 * Create a Calendar
 */
inline fun Date.createCalendar(firstDayOfWeek: Int, locale: Locale): Calendar = Datex.createCalendar(this, firstDayOfWeek, locale)

/**
 * Create a Calendar
 */
inline fun Date.createCalendar(firstDayOfWeek: Int): Calendar = Datex.createCalendar(this, firstDayOfWeek)

/**
 * Create a Calendar
 */
inline fun Date.createCalendar(locale: Locale): Calendar = Datex.createCalendar(this, locale)

/**
 * Create a Calendar
 */
inline fun Date.createCalendar(): Calendar = Datex.createCalendar(this)


/**
 * Create a Calendar
 */
inline fun Long.createCalendar(firstDayOfWeek: Int, locale: Locale): Calendar = Datex.createCalendar(this, firstDayOfWeek, locale)

/**
 * Create a Calendar
 */
inline fun Long.createCalendar(firstDayOfWeek: Int): Calendar = Datex.createCalendar(this, firstDayOfWeek)

/**
 * Create a Calendar
 */
inline fun Long.createCalendar(locale: Locale): Calendar = Datex.createCalendar(this, locale)

/**
 * Create a Calendar
 */
inline fun Long.createCalendar(): Calendar = Datex.createCalendar(this)


/*
 * toDate
 */

/**
 * Millisecond to Date
 */
inline fun Long.toDate(): Date = Datex.toDate(this)


/**
 * Convert formatted date string to Date
 */
@Throws(ParseException::class)
inline fun String.toDate(format: SimpleDateFormat): Date = Datex.toDate(this, format)

/**
 * Convert formatted date string to Date
 */
@Throws(ParseException::class)
inline fun String.toDate(pattern: String, locale: Locale): Date = Datex.toDate(this, pattern, locale)

/**
 * Convert formatted date string to Date
 */
@Throws(ParseException::class)
inline fun String.toDate(pattern: String): Date = Datex.toDate(this, pattern)

/**
 * Convert formatted date string to Date using pattern 'yyyy'
 */
@Throws(ParseException::class)
inline fun String.toDateY(locale: Locale): Date = Datex.toDateY(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy'
 */
@Throws(ParseException::class)
inline fun String.toDateY(): Date = Datex.toDateY(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM'
 */
@Throws(ParseException::class)
inline fun String.toDateYM(locale: Locale): Date = Datex.toDateYM(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM'
 */
@Throws(ParseException::class)
inline fun String.toDateYM(): Date = Datex.toDateYM(this)

/**
 * Convert formatted date string to Date using pattern 'yyyyMM'
 */
@Throws(ParseException::class)
inline fun String.toDateYMCompact(locale: Locale): Date = Datex.toDateYMCompact(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyyMM'
 */
@Throws(ParseException::class)
inline fun String.toDateYMCompact(): Date = Datex.toDateYMCompact(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd'
 */
@Throws(ParseException::class)
inline fun String.toDateYMD(locale: Locale): Date = Datex.toDateYMD(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd'
 */
@Throws(ParseException::class)
inline fun String.toDateYMD(): Date = Datex.toDateYMD(this)

/**
 * Convert formatted date string to Date using pattern 'yyyyMMdd'
 */
@Throws(ParseException::class)
inline fun String.toDateYMDCompact(locale: Locale): Date = Datex.toDateYMDCompact(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyyMMdd'
 */
@Throws(ParseException::class)
inline fun String.toDateYMDCompact(): Date = Datex.toDateYMDCompact(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH'
 */
@Throws(ParseException::class)
inline fun String.toDateYMDH(locale: Locale): Date = Datex.toDateYMDH(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH'
 */
@Throws(ParseException::class)
inline fun String.toDateYMDH(): Date = Datex.toDateYMDH(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm'
 */
@Throws(ParseException::class)
inline fun String.toDateYMDHM(locale: Locale): Date = Datex.toDateYMDHM(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm'
 */
@Throws(ParseException::class)
inline fun String.toDateYMDHM(): Date = Datex.toDateYMDHM(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss'
 */
@Throws(ParseException::class)
inline fun String.toDateYMDHMS(locale: Locale): Date = Datex.toDateYMDHMS(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss'
 */
@Throws(ParseException::class)
inline fun String.toDateYMDHMS(): Date = Datex.toDateYMDHMS(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss SSS'
 */
@Throws(ParseException::class)
inline fun String.toDateYMDHMSM(locale: Locale): Date = Datex.toDateYMDHMSM(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss SSS'
 */
@Throws(ParseException::class)
inline fun String.toDateYMDHMSM(): Date = Datex.toDateYMDHMSM(this)


/**
 * Convert formatted date string to Date
 */
@Throws(ParseException::class)
inline fun String.toMillisecond(format: SimpleDateFormat): Long = Datex.toMillisecond(this, format)

/**
 * Convert formatted date string to Date
 */
@Throws(ParseException::class)
inline fun String.toMillisecond(pattern: String, locale: Locale): Long = Datex.toMillisecond(this, pattern, locale)

/**
 * Convert formatted date string to Date
 */
@Throws(ParseException::class)
inline fun String.toMillisecond(pattern: String): Long = Datex.toMillisecond(this, pattern)

/**
 * Convert formatted date string to Date using pattern 'yyyy'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondY(locale: Locale): Long = Datex.toMillisecondY(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondY(): Long = Datex.toMillisecondY(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYM(locale: Locale): Long = Datex.toMillisecondYM(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYM(): Long = Datex.toMillisecondYM(this)

/**
 * Convert formatted date string to Date using pattern 'yyyyMM'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMCompact(locale: Locale): Long = Datex.toMillisecondYMCompact(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyyMM'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMCompact(): Long = Datex.toMillisecondYMCompact(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMD(locale: Locale): Long = Datex.toMillisecondYMD(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMD(): Long = Datex.toMillisecondYMD(this)

/**
 * Convert formatted date string to Date using pattern 'yyyyMMdd'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMDCompact(locale: Locale): Long = Datex.toMillisecondYMDCompact(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyyMMdd'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMDCompact(): Long = Datex.toMillisecondYMDCompact(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMDH(locale: Locale): Long = Datex.toMillisecondYMDH(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMDH(): Long = Datex.toMillisecondYMDH(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMDHM(locale: Locale): Long = Datex.toMillisecondYMDHM(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMDHM(): Long = Datex.toMillisecondYMDHM(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMDHMS(locale: Locale): Long = Datex.toMillisecondYMDHMS(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMDHMS(): Long = Datex.toMillisecondYMDHMS(this)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss SSS'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMDHMSM(locale: Locale): Long = Datex.toMillisecondYMDHMSM(this, locale)

/**
 * Convert formatted date string to Date using pattern 'yyyy-MM-dd HH:mm:ss SSS'
 */
@Throws(ParseException::class)
inline fun String.toMillisecondYMDHMSM(): Long = Datex.toMillisecondYMDHMSM(this)


/*
 * format
 */


/**
 * Convert Date to a formatted string
 */
inline fun Date.format(format: SimpleDateFormat): String = Datex.format(this, format)

/**
 * Convert Date to a formatted string
 */
inline fun Date.format(pattern: String, locale: Locale): String = Datex.format(this, pattern, locale)

/**
 * Convert Date to a formatted string
 */
inline fun Date.format(pattern: String): String = Datex.format(this, pattern)

/**
 * Convert Date to a formatted string using pattern 'yyyy'
 */
inline fun Date.formatY(locale: Locale): String = Datex.formatY(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy'
 */
inline fun Date.formatY(): String = Datex.formatY(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM'
 */
inline fun Date.formatYM(locale: Locale): String = Datex.formatYM(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM'
 */
inline fun Date.formatYM(): String = Datex.formatYM(this)

/**
 * Convert Date to a formatted string using pattern 'yyyyMM'
 */
inline fun Date.formatYMCompact(locale: Locale): String = Datex.formatYMCompact(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyyMM'
 */
inline fun Date.formatYMCompact(): String = Datex.formatYMCompact(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd'
 */
inline fun Date.formatYMD(locale: Locale): String = Datex.formatYMD(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd'
 */
inline fun Date.formatYMD(): String = Datex.formatYMD(this)

/**
 * Convert Date to a formatted string using pattern 'yyyyMMdd'
 */
inline fun Date.formatYMDCompact(locale: Locale): String = Datex.formatYMDCompact(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyyMMdd'
 */
inline fun Date.formatYMDCompact(): String = Datex.formatYMDCompact(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH'
 */
inline fun Date.formatYMDH(locale: Locale): String = Datex.formatYMDH(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH'
 */
inline fun Date.formatYMDH(): String = Datex.formatYMDH(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm'
 */
inline fun Date.formatYMDHM(locale: Locale): String = Datex.formatYMDHM(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm'
 */
inline fun Date.formatYMDHM(): String = Datex.formatYMDHM(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss'
 */
inline fun Date.formatYMDHMS(locale: Locale): String = Datex.formatYMDHMS(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss'
 */
inline fun Date.formatYMDHMS(): String = Datex.formatYMDHMS(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss SSS'
 */
inline fun Date.formatYMDHMSM(locale: Locale): String = Datex.formatYMDHMSM(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss SSS'
 */
inline fun Date.formatYMDHMSM(): String = Datex.formatYMDHMSM(this)


/**
 * Convert Date to a formatted string
 */
inline fun Long.format(format: SimpleDateFormat): String = Datex.format(this, format)

/**
 * Convert Date to a formatted string
 */
inline fun Long.format(pattern: String, locale: Locale): String = Datex.format(this, pattern, locale)

/**
 * Convert Date to a formatted string
 */
inline fun Long.format(pattern: String): String = Datex.format(this, pattern)

/**
 * Convert Date to a formatted string using pattern 'yyyy'
 */
inline fun Long.formatY(locale: Locale): String = Datex.formatY(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy'
 */
inline fun Long.formatY(): String = Datex.formatY(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM'
 */
inline fun Long.formatYM(locale: Locale): String = Datex.formatYM(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM'
 */
inline fun Long.formatYM(): String = Datex.formatYM(this)

/**
 * Convert Date to a formatted string using pattern 'yyyyMM'
 */
inline fun Long.formatYMCompact(locale: Locale): String = Datex.formatYMCompact(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyyMM'
 */
inline fun Long.formatYMCompact(): String = Datex.formatYMCompact(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd'
 */
inline fun Long.formatYMD(locale: Locale): String = Datex.formatYMD(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd'
 */
inline fun Long.formatYMD(): String = Datex.formatYMD(this)

/**
 * Convert Date to a formatted string using pattern 'yyyyMMdd'
 */
inline fun Long.formatYMDCompact(locale: Locale): String = Datex.formatYMDCompact(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyyMMdd'
 */
inline fun Long.formatYMDCompact(): String = Datex.formatYMDCompact(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH'
 */
inline fun Long.formatYMDH(locale: Locale): String = Datex.formatYMDH(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH'
 */
inline fun Long.formatYMDH(): String = Datex.formatYMDH(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm'
 */
inline fun Long.formatYMDHM(locale: Locale): String = Datex.formatYMDHM(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm'
 */
inline fun Long.formatYMDHM(): String = Datex.formatYMDHM(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss'
 */
inline fun Long.formatYMDHMS(locale: Locale): String = Datex.formatYMDHMS(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss'
 */
inline fun Long.formatYMDHMS(): String = Datex.formatYMDHMS(this)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss SSS'
 */
inline fun Long.formatYMDHMSM(locale: Locale): String = Datex.formatYMDHMSM(this, locale)

/**
 * Convert Date to a formatted string using pattern 'yyyy-MM-dd HH:mm:ss SSS'
 */
inline fun Long.formatYMDHMSM(): String = Datex.formatYMDHMSM(this)


/*
 * get
 */


/**
 * Get year from calendar
 */
inline fun Calendar.getYear(): Int = Datex.getYear(this)

/**
 * Get month from calendar
 */
inline fun Calendar.getMonth(): Int = Datex.getMonth(this)

/**
 * Get weekOfYear from calendar
 */
inline fun Calendar.getWeekOfYear(): Int = Datex.getWeekOfYear(this)

/**
 * Get weekOfMonth from calendar
 */
inline fun Calendar.getWeekOfMonth(): Int = Datex.getWeekOfMonth(this)

/**
 * Get dayOfYear from calendar
 */
inline fun Calendar.getDayOfYear(): Int = Datex.getDayOfYear(this)

/**
 * Get dayOfMonth from calendar
 */
inline fun Calendar.getDayOfMonth(): Int = Datex.getDayOfMonth(this)

/**
 * Get dayOfWeek from calendar
 */
inline fun Calendar.getDayOfWeek(): Int = Datex.getDayOfWeek(this)

/**
 * Get dayOfWeekInMonth from calendar
 */
inline fun Calendar.getDayOfWeekInMonth(): Int = Datex.getDayOfWeekInMonth(this)

/**
 * Get hourOfDay from calendar
 */
inline fun Calendar.getHourOfDay(): Int = Datex.getHourOfDay(this)

/**
 * Get hour from calendar
 */
inline fun Calendar.getHour(): Int = Datex.getHour(this)

/**
 * Get minute from calendar
 */
inline fun Calendar.getMinute(): Int = Datex.getMinute(this)

/**
 * Get second from calendar
 */
inline fun Calendar.getSecond(): Int = Datex.getSecond(this)

/**
 * Get millisecond from calendar
 */
inline fun Calendar.getMillisecond(): Int = Datex.getMillisecond(this)


/**
 * Get calendar field from millisecond
 */
inline fun Date.getCalendarField(field: Int, firstDayOfWeek: Int, locale: Locale): Int = Datex.getCalendarField(this, field, firstDayOfWeek, locale)

/**
 * Get calendar field from millisecond
 */
inline fun Date.getCalendarField(field: Int, firstDayOfWeek: Int): Int = Datex.getCalendarField(this, field, firstDayOfWeek)

/**
 * Get calendar field from millisecond
 */
inline fun Date.getCalendarField(field: Int, locale: Locale): Int = Datex.getCalendarField(this, field, locale)

/**
 * Get calendar field from millisecond
 */
inline fun Date.getCalendarField(field: Int): Int = Datex.getCalendarField(this, field)

/**
 * Get year from date
 */
inline fun Date.getCalendarYear(locale: Locale): Int = Datex.getCalendarYear(this, locale)

/**
 * Get year from date
 */
inline fun Date.getCalendarYear(): Int = Datex.getCalendarYear(this)

/**
 * Get month from date
 */
inline fun Date.getCalendarMonth(locale: Locale): Int = Datex.getCalendarMonth(this, locale)

/**
 * Get month from date
 */
inline fun Date.getCalendarMonth(): Int = Datex.getCalendarMonth(this)

/**
 * Get weekOfYear from date
 */
inline fun Date.getCalendarWeekOfYear(firstDayOfWeek: Int, locale: Locale): Int = Datex.getCalendarWeekOfYear(this, firstDayOfWeek, locale)

/**
 * Get weekOfYear from date
 */
inline fun Date.getCalendarWeekOfYear(firstDayOfWeek: Int): Int = Datex.getCalendarWeekOfYear(this, firstDayOfWeek)

/**
 * Get weekOfYear from date
 */
inline fun Date.getCalendarWeekOfYear(locale: Locale): Int = Datex.getCalendarWeekOfYear(this, locale)

/**
 * Get weekOfYear from date
 */
inline fun Date.getCalendarWeekOfYear(): Int = Datex.getCalendarWeekOfYear(this)

/**
 * Get weekOfMonth from date
 */
inline fun Date.getCalendarWeekOfMonth(firstDayOfWeek: Int, locale: Locale): Int = Datex.getCalendarWeekOfMonth(this, firstDayOfWeek, locale)

/**
 * Get weekOfMonth from date
 */
inline fun Date.getCalendarWeekOfMonth(firstDayOfWeek: Int): Int = Datex.getCalendarWeekOfMonth(this, firstDayOfWeek)

/**
 * Get weekOfMonth from date
 */
inline fun Date.getCalendarWeekOfMonth(locale: Locale): Int = Datex.getCalendarWeekOfMonth(this, locale)

/**
 * Get weekOfMonth from date
 */
inline fun Date.getCalendarWeekOfMonth(): Int = Datex.getCalendarWeekOfMonth(this)

/**
 * Get dayOfYear from date
 */
inline fun Date.getCalendarDayOfYear(locale: Locale): Int = Datex.getCalendarDayOfYear(this, locale)

/**
 * Get dayOfYear from date
 */
inline fun Date.getCalendarDayOfYear(): Int = Datex.getCalendarDayOfYear(this)

/**
 * Get dayOfMonth from date
 */
inline fun Date.getCalendarDayOfMonth(locale: Locale): Int = Datex.getCalendarDayOfMonth(this, locale)

/**
 * Get dayOfMonth from date
 */
inline fun Date.getCalendarDayOfMonth(): Int = Datex.getCalendarDayOfMonth(this)

/**
 * Get dayOfWeek from date
 */
inline fun Date.getCalendarDayOfWeek(firstDayOfWeek: Int, locale: Locale): Int = Datex.getCalendarDayOfWeek(this, firstDayOfWeek, locale)

/**
 * Get dayOfWeek from date
 */
inline fun Date.getCalendarDayOfWeek(firstDayOfWeek: Int): Int = Datex.getCalendarDayOfWeek(this, firstDayOfWeek)

/**
 * Get dayOfWeek from date
 */
inline fun Date.getCalendarDayOfWeek(locale: Locale): Int = Datex.getCalendarDayOfWeek(this, locale)

/**
 * Get dayOfWeek from date
 */
inline fun Date.getCalendarDayOfWeek(): Int = Datex.getCalendarDayOfWeek(this)

/**
 * Get dayOfWeekInMonth from date
 */
inline fun Date.getCalendarDayOfWeekInMonth(firstDayOfWeek: Int, locale: Locale): Int = Datex.getCalendarDayOfWeekInMonth(this, firstDayOfWeek, locale)

/**
 * Get dayOfWeekInMonth from date
 */
inline fun Date.getCalendarDayOfWeekInMonth(firstDayOfWeek: Int): Int = Datex.getCalendarDayOfWeekInMonth(this, firstDayOfWeek)

/**
 * Get dayOfWeekInMonth from date
 */
inline fun Date.getCalendarDayOfWeekInMonth(locale: Locale): Int = Datex.getCalendarDayOfWeekInMonth(this, locale)

/**
 * Get dayOfWeekInMonth from date
 */
inline fun Date.getCalendarDayOfWeekInMonth(): Int = Datex.getCalendarDayOfWeekInMonth(this)

/**
 * Get hourOfDay from date
 */
inline fun Date.getCalendarHourOfDay(locale: Locale): Int = Datex.getCalendarHourOfDay(this, locale)

/**
 * Get hourOfDay from date
 */
inline fun Date.getCalendarHourOfDay(): Int = Datex.getCalendarHourOfDay(this)

/**
 * Get hour from date
 */
inline fun Date.getCalendarHour(locale: Locale): Int = Datex.getCalendarHour(this, locale)

/**
 * Get hour from date
 */
inline fun Date.getCalendarHour(): Int = Datex.getCalendarHour(this)

/**
 * Get minute from date
 */
inline fun Date.getCalendarMinute(locale: Locale): Int = Datex.getCalendarMinute(this, locale)

/**
 * Get minute from date
 */
inline fun Date.getCalendarMinute(): Int = Datex.getCalendarMinute(this)

/**
 * Get second from date
 */
inline fun Date.getCalendarSecond(locale: Locale): Int = Datex.getCalendarSecond(this, locale)

/**
 * Get second from date
 */
inline fun Date.getCalendarSecond(): Int = Datex.getCalendarSecond(this)

/**
 * Get millisecond from date
 */
inline fun Date.getCalendarMillisecond(locale: Locale): Int = Datex.getCalendarMillisecond(this, locale)

/**
 * Get millisecond from date
 */
inline fun Date.getCalendarMillisecond(): Int = Datex.getCalendarMillisecond(this)


/**
 * Get calendar field from millisecond
 */
inline fun Long.getCalendarField(field: Int, firstDayOfWeek: Int, locale: Locale): Int = Datex.getCalendarField(this, field, firstDayOfWeek, locale)

/**
 * Get calendar field from millisecond
 */
inline fun Long.getCalendarField(field: Int, firstDayOfWeek: Int): Int = Datex.getCalendarField(this, field, firstDayOfWeek)

/**
 * Get calendar field from millisecond
 */
inline fun Long.getCalendarField(field: Int, locale: Locale): Int = Datex.getCalendarField(this, field, locale)

/**
 * Get calendar field from millisecond
 */
inline fun Long.getCalendarField(field: Int): Int = Datex.getCalendarField(this, field)

/**
 * Get year from date
 */
inline fun Long.getCalendarYear(locale: Locale): Int = Datex.getCalendarYear(this, locale)

/**
 * Get year from date
 */
inline fun Long.getCalendarYear(): Int = Datex.getCalendarYear(this)

/**
 * Get month from date
 */
inline fun Long.getCalendarMonth(locale: Locale): Int = Datex.getCalendarMonth(this, locale)

/**
 * Get month from date
 */
inline fun Long.getCalendarMonth(): Int = Datex.getCalendarMonth(this)

/**
 * Get weekOfYear from date
 */
inline fun Long.getCalendarWeekOfYear(firstDayOfWeek: Int, locale: Locale): Int = Datex.getCalendarWeekOfYear(this, firstDayOfWeek, locale)

/**
 * Get weekOfYear from date
 */
inline fun Long.getCalendarWeekOfYear(firstDayOfWeek: Int): Int = Datex.getCalendarWeekOfYear(this, firstDayOfWeek)

/**
 * Get weekOfYear from date
 */
inline fun Long.getCalendarWeekOfYear(locale: Locale): Int = Datex.getCalendarWeekOfYear(this, locale)

/**
 * Get weekOfYear from date
 */
inline fun Long.getCalendarWeekOfYear(): Int = Datex.getCalendarWeekOfYear(this)

/**
 * Get weekOfMonth from date
 */
inline fun Long.getCalendarWeekOfMonth(firstDayOfWeek: Int, locale: Locale): Int = Datex.getCalendarWeekOfMonth(this, firstDayOfWeek, locale)

/**
 * Get weekOfMonth from date
 */
inline fun Long.getCalendarWeekOfMonth(firstDayOfWeek: Int): Int = Datex.getCalendarWeekOfMonth(this, firstDayOfWeek)

/**
 * Get weekOfMonth from date
 */
inline fun Long.getCalendarWeekOfMonth(locale: Locale): Int = Datex.getCalendarWeekOfMonth(this, locale)

/**
 * Get weekOfMonth from date
 */
inline fun Long.getCalendarWeekOfMonth(): Int = Datex.getCalendarWeekOfMonth(this)

/**
 * Get dayOfYear from date
 */
inline fun Long.getCalendarDayOfYear(locale: Locale): Int = Datex.getCalendarDayOfYear(this, locale)

/**
 * Get dayOfYear from date
 */
inline fun Long.getCalendarDayOfYear(): Int = Datex.getCalendarDayOfYear(this)

/**
 * Get dayOfMonth from date
 */
inline fun Long.getCalendarDayOfMonth(locale: Locale): Int = Datex.getCalendarDayOfMonth(this, locale)

/**
 * Get dayOfMonth from date
 */
inline fun Long.getCalendarDayOfMonth(): Int = Datex.getCalendarDayOfMonth(this)

/**
 * Get dayOfWeek from date
 */
inline fun Long.getCalendarDayOfWeek(firstDayOfWeek: Int, locale: Locale): Int = Datex.getCalendarDayOfWeek(this, firstDayOfWeek, locale)

/**
 * Get dayOfWeek from date
 */
inline fun Long.getCalendarDayOfWeek(firstDayOfWeek: Int): Int = Datex.getCalendarDayOfWeek(this, firstDayOfWeek)

/**
 * Get dayOfWeek from date
 */
inline fun Long.getCalendarDayOfWeek(locale: Locale): Int = Datex.getCalendarDayOfWeek(this, locale)

/**
 * Get dayOfWeek from date
 */
inline fun Long.getCalendarDayOfWeek(): Int = Datex.getCalendarDayOfWeek(this)

/**
 * Get dayOfWeekInMonth from date
 */
inline fun Long.getCalendarDayOfWeekInMonth(firstDayOfWeek: Int, locale: Locale): Int = Datex.getCalendarDayOfWeekInMonth(this, firstDayOfWeek, locale)

/**
 * Get dayOfWeekInMonth from date
 */
inline fun Long.getCalendarDayOfWeekInMonth(firstDayOfWeek: Int): Int = Datex.getCalendarDayOfWeekInMonth(this, firstDayOfWeek)

/**
 * Get dayOfWeekInMonth from date
 */
inline fun Long.getCalendarDayOfWeekInMonth(locale: Locale): Int = Datex.getCalendarDayOfWeekInMonth(this, locale)

/**
 * Get dayOfWeekInMonth from date
 */
inline fun Long.getCalendarDayOfWeekInMonth(): Int = Datex.getCalendarDayOfWeekInMonth(this)

/**
 * Get hourOfDay from date
 */
inline fun Long.getCalendarHourOfDay(locale: Locale): Int = Datex.getCalendarHourOfDay(this, locale)

/**
 * Get hourOfDay from date
 */
inline fun Long.getCalendarHourOfDay(): Int = Datex.getCalendarHourOfDay(this)

/**
 * Get hour from date
 */
inline fun Long.getCalendarHour(locale: Locale): Int = Datex.getCalendarHour(this, locale)

/**
 * Get hour from date
 */
inline fun Long.getCalendarHour(): Int = Datex.getCalendarHour(this)

/**
 * Get minute from date
 */
inline fun Long.getCalendarMinute(locale: Locale): Int = Datex.getCalendarMinute(this, locale)

/**
 * Get minute from date
 */
inline fun Long.getCalendarMinute(): Int = Datex.getCalendarMinute(this)

/**
 * Get second from date
 */
inline fun Long.getCalendarSecond(locale: Locale): Int = Datex.getCalendarSecond(this, locale)

/**
 * Get second from date
 */
inline fun Long.getCalendarSecond(): Int = Datex.getCalendarSecond(this)

/**
 * Get millisecond from date
 */
inline fun Long.getCalendarMillisecond(locale: Locale): Int = Datex.getCalendarMillisecond(this, locale)

/**
 * Get millisecond from date
 */
inline fun Long.getCalendarMillisecond(): Int = Datex.getCalendarMillisecond(this)


/*
 * add
 */


/**
 * Increase the specified calendar field and return to Date
 */
inline fun Calendar.addToDate(field: Int, amount: Int): Date = Datex.addToDate(this, field, amount)


/**
 * Increase the specified calendar field
 */
inline fun Date.addCalendarField(field: Int, amount: Int, firstDayOfWeek: Int, locale: Locale): Date = Datex.addCalendarField(this, field, amount, firstDayOfWeek, locale)

/**
 * Increase the specified calendar field
 */
inline fun Date.addCalendarField(field: Int, amount: Int, firstDayOfWeek: Int): Date = Datex.addCalendarField(this, field, amount, firstDayOfWeek)

/**
 * Increase the specified calendar field
 */
inline fun Date.addCalendarField(field: Int, amount: Int, locale: Locale): Date = Datex.addCalendarField(this, field, amount, locale)

/**
 * Increase the specified calendar field
 */
inline fun Date.addCalendarField(field: Int, amount: Int): Date = Datex.addCalendarField(this, field, amount)

/**
 * Increase the YEAR field
 */
inline fun Date.addYear(amount: Int, locale: Locale): Date = Datex.addYear(this, amount, locale)

/**
 * Increase the YEAR field
 */
inline fun Date.addYear(amount: Int): Date = Datex.addYear(this, amount)

/**
 * Increase the MONTH field
 */
inline fun Date.addMonth(amount: Int, locale: Locale): Date = Datex.addMonth(this, amount, locale)

/**
 * Increase the MONTH field
 */
inline fun Date.addMonth(amount: Int): Date = Datex.addMonth(this, amount)

/**
 * Increase the WEEK_OF_YEAR field
 */
inline fun Date.addWeekOfYear(amount: Int, firstDayOfWeek: Int, locale: Locale): Date = Datex.addWeekOfYear(this, amount, firstDayOfWeek, locale)

/**
 * Increase the WEEK_OF_YEAR field
 */
inline fun Date.addWeekOfYear(amount: Int, firstDayOfWeek: Int): Date = Datex.addWeekOfYear(this, amount, firstDayOfWeek)

/**
 * Increase the WEEK_OF_YEAR field
 */
inline fun Date.addWeekOfYear(amount: Int, locale: Locale): Date = Datex.addWeekOfYear(this, amount, locale)

/**
 * Increase the WEEK_OF_YEAR field
 */
inline fun Date.addWeekOfYear(amount: Int): Date = Datex.addWeekOfYear(this, amount)

/**
 * Increase the WEEK_OF_MONTH field
 */
inline fun Date.addWeekOfMonth(amount: Int, firstDayOfWeek: Int, locale: Locale): Date = Datex.addWeekOfMonth(this, amount, firstDayOfWeek, locale)

/**
 * Increase the WEEK_OF_MONTH field
 */
inline fun Date.addWeekOfMonth(amount: Int, firstDayOfWeek: Int): Date = Datex.addWeekOfMonth(this, amount, firstDayOfWeek)

/**
 * Increase the WEEK_OF_MONTH field
 */
inline fun Date.addWeekOfMonth(amount: Int, locale: Locale): Date = Datex.addWeekOfMonth(this, amount, locale)

/**
 * Increase the WEEK_OF_MONTH field
 */
inline fun Date.addWeekOfMonth(amount: Int): Date = Datex.addWeekOfMonth(this, amount)

/**
 * Increase the DAY_OF_YEAR field
 */
inline fun Date.addDayOfYear(amount: Int, locale: Locale): Date = Datex.addDayOfYear(this, amount, locale)

/**
 * Increase the DAY_OF_YEAR field
 */
inline fun Date.addDayOfYear(amount: Int): Date = Datex.addDayOfYear(this, amount)

/**
 * Increase the DAY_OF_MONTH field
 */
inline fun Date.addDayOfMonth(amount: Int, locale: Locale): Date = Datex.addDayOfMonth(this, amount, locale)

/**
 * Increase the DAY_OF_MONTH field
 */
inline fun Date.addDayOfMonth(amount: Int): Date = Datex.addDayOfMonth(this, amount)

/**
 * Increase the DAY_OF_WEEK_IN_MONTH field
 */
inline fun Date.addDayOfWeekInMonth(amount: Int, firstDayOfWeek: Int, locale: Locale): Date = Datex.addDayOfWeekInMonth(this, amount, firstDayOfWeek, locale)

/**
 * Increase the DAY_OF_WEEK_IN_MONTH field
 */
inline fun Date.addDayOfWeekInMonth(amount: Int, firstDayOfWeek: Int): Date = Datex.addDayOfWeekInMonth(this, amount, firstDayOfWeek)

/**
 * Increase the DAY_OF_WEEK_IN_MONTH field
 */
inline fun Date.addDayOfWeekInMonth(amount: Int, locale: Locale): Date = Datex.addDayOfWeekInMonth(this, amount, locale)

/**
 * Increase the DAY_OF_WEEK_IN_MONTH field
 */
inline fun Date.addDayOfWeekInMonth(amount: Int): Date = Datex.addDayOfWeekInMonth(this, amount)

/**
 * Increase the DAY_OF_WEEK field
 */
inline fun Date.addDayOfWeek(amount: Int, firstDayOfWeek: Int, locale: Locale): Date = Datex.addDayOfWeek(this, amount, firstDayOfWeek, locale)

/**
 * Increase the DAY_OF_WEEK field
 */
inline fun Date.addDayOfWeek(amount: Int, firstDayOfWeek: Int): Date = Datex.addDayOfWeek(this, amount, firstDayOfWeek)

/**
 * Increase the DAY_OF_WEEK field
 */
inline fun Date.addDayOfWeek(amount: Int, locale: Locale): Date = Datex.addDayOfWeek(this, amount, locale)

/**
 * Increase the DAY_OF_WEEK field
 */
inline fun Date.addDayOfWeek(amount: Int): Date = Datex.addDayOfWeek(this, amount)

/**
 * Increase the HOUR_OF_DAY field
 */
inline fun Date.addHourOfDay(amount: Int, locale: Locale): Date = Datex.addHourOfDay(this, amount, locale)

/**
 * Increase the HOUR_OF_DAY field
 */
inline fun Date.addHourOfDay(amount: Int): Date = Datex.addHourOfDay(this, amount)

/**
 * Increase the HOUR field
 */
inline fun Date.addHour(amount: Int, locale: Locale): Date = Datex.addHour(this, amount, locale)

/**
 * Increase the HOUR field
 */
inline fun Date.addHour(amount: Int): Date = Datex.addHour(this, amount)

/**
 * Increase the MINUTE field
 */
inline fun Date.addMinute(amount: Int, locale: Locale): Date = Datex.addMinute(this, amount, locale)

/**
 * Increase the MINUTE field
 */
inline fun Date.addMinute(amount: Int): Date = Datex.addMinute(this, amount)

/**
 * Increase the SECOND field
 */
inline fun Date.addSecond(amount: Int, locale: Locale): Date = Datex.addSecond(this, amount, locale)

/**
 * Increase the SECOND field
 */
inline fun Date.addSecond(amount: Int): Date = Datex.addSecond(this, amount)

/**
 * Increase the MILLISECOND field
 */
inline fun Date.addMillisecond(amount: Int, locale: Locale): Date = Datex.addMillisecond(this, amount, locale)

/**
 * Increase the MILLISECOND field
 */
inline fun Date.addMillisecond(amount: Int): Date = Datex.addMillisecond(this, amount)


/**
 * Increase the specified calendar field
 */
inline fun Long.addCalendarField(field: Int, amount: Int, firstDayOfWeek: Int, locale: Locale): Date = Datex.addCalendarField(this, field, amount, firstDayOfWeek, locale)

/**
 * Increase the specified calendar field
 */
inline fun Long.addCalendarField(field: Int, amount: Int, firstDayOfWeek: Int): Date = Datex.addCalendarField(this, field, amount, firstDayOfWeek)

/**
 * Increase the specified calendar field
 */
inline fun Long.addCalendarField(field: Int, amount: Int, locale: Locale): Date = Datex.addCalendarField(this, field, amount, locale)

/**
 * Increase the specified calendar field
 */
inline fun Long.addCalendarField(field: Int, amount: Int): Date = Datex.addCalendarField(this, field, amount)

/**
 * Increase the YEAR field
 */
inline fun Long.addYear(amount: Int, locale: Locale): Date = Datex.addYear(this, amount, locale)

/**
 * Increase the YEAR field
 */
inline fun Long.addYear(amount: Int): Date = Datex.addYear(this, amount)

/**
 * Increase the MONTH field
 */
inline fun Long.addMonth(amount: Int, locale: Locale): Date = Datex.addMonth(this, amount, locale)

/**
 * Increase the MONTH field
 */
inline fun Long.addMonth(amount: Int): Date = Datex.addMonth(this, amount)

/**
 * Increase the WEEK_OF_YEAR field
 */
inline fun Long.addWeekOfYear(amount: Int, firstDayOfWeek: Int, locale: Locale): Date = Datex.addWeekOfYear(this, amount, firstDayOfWeek, locale)

/**
 * Increase the WEEK_OF_YEAR field
 */
inline fun Long.addWeekOfYear(amount: Int, firstDayOfWeek: Int): Date = Datex.addWeekOfYear(this, amount, firstDayOfWeek)

/**
 * Increase the WEEK_OF_YEAR field
 */
inline fun Long.addWeekOfYear(amount: Int, locale: Locale): Date = Datex.addWeekOfYear(this, amount, locale)

/**
 * Increase the WEEK_OF_YEAR field
 */
inline fun Long.addWeekOfYear(amount: Int): Date = Datex.addWeekOfYear(this, amount)

/**
 * Increase the WEEK_OF_MONTH field
 */
inline fun Long.addWeekOfMonth(amount: Int, firstDayOfWeek: Int, locale: Locale): Date = Datex.addWeekOfMonth(this, amount, firstDayOfWeek, locale)

/**
 * Increase the WEEK_OF_MONTH field
 */
inline fun Long.addWeekOfMonth(amount: Int, firstDayOfWeek: Int): Date = Datex.addWeekOfMonth(this, amount, firstDayOfWeek)

/**
 * Increase the WEEK_OF_MONTH field
 */
inline fun Long.addWeekOfMonth(amount: Int, locale: Locale): Date = Datex.addWeekOfMonth(this, amount, locale)

/**
 * Increase the WEEK_OF_MONTH field
 */
inline fun Long.addWeekOfMonth(amount: Int): Date = Datex.addWeekOfMonth(this, amount)

/**
 * Increase the DAY_OF_YEAR field
 */
inline fun Long.addDayOfYear(amount: Int, locale: Locale): Date = Datex.addDayOfYear(this, amount, locale)

/**
 * Increase the DAY_OF_YEAR field
 */
inline fun Long.addDayOfYear(amount: Int): Date = Datex.addDayOfYear(this, amount)

/**
 * Increase the DAY_OF_MONTH field
 */
inline fun Long.addDayOfMonth(amount: Int, locale: Locale): Date = Datex.addDayOfMonth(this, amount, locale)

/**
 * Increase the DAY_OF_MONTH field
 */
inline fun Long.addDayOfMonth(amount: Int): Date = Datex.addDayOfMonth(this, amount)

/**
 * Increase the DAY_OF_WEEK_IN_MONTH field
 */
inline fun Long.addDayOfWeekInMonth(amount: Int, firstDayOfWeek: Int, locale: Locale): Date = Datex.addDayOfWeekInMonth(this, amount, firstDayOfWeek, locale)

/**
 * Increase the DAY_OF_WEEK_IN_MONTH field
 */
inline fun Long.addDayOfWeekInMonth(amount: Int, firstDayOfWeek: Int): Date = Datex.addDayOfWeekInMonth(this, amount, firstDayOfWeek)

/**
 * Increase the DAY_OF_WEEK_IN_MONTH field
 */
inline fun Long.addDayOfWeekInMonth(amount: Int, locale: Locale): Date = Datex.addDayOfWeekInMonth(this, amount, locale)

/**
 * Increase the DAY_OF_WEEK_IN_MONTH field
 */
inline fun Long.addDayOfWeekInMonth(amount: Int): Date = Datex.addDayOfWeekInMonth(this, amount)

/**
 * Increase the DAY_OF_WEEK field
 */
inline fun Long.addDayOfWeek(amount: Int, firstDayOfWeek: Int, locale: Locale): Date = Datex.addDayOfWeek(this, amount, firstDayOfWeek, locale)

/**
 * Increase the DAY_OF_WEEK field
 */
inline fun Long.addDayOfWeek(amount: Int, firstDayOfWeek: Int): Date = Datex.addDayOfWeek(this, amount, firstDayOfWeek)

/**
 * Increase the DAY_OF_WEEK field
 */
inline fun Long.addDayOfWeek(amount: Int, locale: Locale): Date = Datex.addDayOfWeek(this, amount, locale)

/**
 * Increase the DAY_OF_WEEK field
 */
inline fun Long.addDayOfWeek(amount: Int): Date = Datex.addDayOfWeek(this, amount)

/**
 * Increase the HOUR_OF_DAY field
 */
inline fun Long.addHourOfDay(amount: Int, locale: Locale): Date = Datex.addHourOfDay(this, amount, locale)

/**
 * Increase the HOUR_OF_DAY field
 */
inline fun Long.addHourOfDay(amount: Int): Date = Datex.addHourOfDay(this, amount)

/**
 * Increase the HOUR field
 */
inline fun Long.addHour(amount: Int, locale: Locale): Date = Datex.addHour(this, amount, locale)

/**
 * Increase the HOUR field
 */
inline fun Long.addHour(amount: Int): Date = Datex.addHour(this, amount)

/**
 * Increase the MINUTE field
 */
inline fun Long.addMinute(amount: Int, locale: Locale): Date = Datex.addMinute(this, amount, locale)

/**
 * Increase the MINUTE field
 */
inline fun Long.addMinute(amount: Int): Date = Datex.addMinute(this, amount)

/**
 * Increase the SECOND field
 */
inline fun Long.addSecond(amount: Int, locale: Locale): Date = Datex.addSecond(this, amount, locale)

/**
 * Increase the SECOND field
 */
inline fun Long.addSecond(amount: Int): Date = Datex.addSecond(this, amount)

/**
 * Increase the MILLISECOND field
 */
inline fun Long.addMillisecond(amount: Int, locale: Locale): Date = Datex.addMillisecond(this, amount, locale)

/**
 * Increase the MILLISECOND field
 */
inline fun Long.addMillisecond(amount: Int): Date = Datex.addMillisecond(this, amount)


/*
 * isSame
 */


/**
 * Return true if the year is the same
 */
inline fun Calendar.isSameYear(target: Calendar): Boolean = Datex.isSameYear(this, target)

/**
 * Returns true if the year and month are the same
 */
inline fun Calendar.isSameMonth(target: Calendar): Boolean = Datex.isSameMonth(this, target)

/**
 * Return true if the months is the same
 */
inline fun Calendar.isSameMonthOfYear(target: Calendar): Boolean = Datex.isSameMonthOfYear(this, target)

/**
 * Returns true if the year, month, and week are the same
 */
inline fun Calendar.isSameWeek(target: Calendar): Boolean = Datex.isSameWeek(this, target)

/**
 * Return true if the weekOfYear is the same
 */
inline fun Calendar.isSameWeekOfYear(target: Calendar): Boolean = Datex.isSameWeekOfYear(this, target)

/**
 * Return true if the weekOfMonth is the same
 */
inline fun Calendar.isSameWeekOfMonth(target: Calendar): Boolean = Datex.isSameWeekOfMonth(this, target)

/**
 * Returns true if the year, month, week and day are the same
 */
inline fun Calendar.isSameDay(target: Calendar): Boolean = Datex.isSameDay(this, target)

/**
 * Return true if the dayOfYear is the same
 */
inline fun Calendar.isSameDayOfYear(target: Calendar): Boolean = Datex.isSameDayOfYear(this, target)

/**
 * Return true if the dayOfMonth is the same
 */
inline fun Calendar.isSameDayOfMonth(target: Calendar): Boolean = Datex.isSameDayOfMonth(this, target)

/**
 * Return true if the dayOfWeek is the same
 */
inline fun Calendar.isSameDayOfWeek(target: Calendar): Boolean = Datex.isSameDayOfWeek(this, target)

/**
 * Return true if the dayOfWeekInMonth is the same
 */
inline fun Calendar.isSameDayOfWeekInMonth(target: Calendar): Boolean = Datex.isSameDayOfWeekInMonth(this, target)

/**
 * Returns true if the year, month, week, day and hour are the same
 */
inline fun Calendar.isSameHour(target: Calendar): Boolean = Datex.isSameHour(this, target)

/**
 * Return true if the 24H hour is the same
 */
inline fun Calendar.isSameHourOf24H(target: Calendar): Boolean = Datex.isSameHourOf24H(this, target)

/**
 * Return true if the 12H hour is the same
 */
inline fun Calendar.isSameHourOf12H(target: Calendar): Boolean = Datex.isSameHourOf12H(this, target)

/**
 * Returns true if the year, month, week, day, hour and minute are the same
 */
inline fun Calendar.isSameMinute(target: Calendar): Boolean = Datex.isSameMinute(this, target)

/**
 * Return true if the minuteOfHour is the same
 */
inline fun Calendar.isSameMinuteOfHour(target: Calendar): Boolean = Datex.isSameMinuteOfHour(this, target)

/**
 * Returns true if the year, month, week, day, hour, minute and second are the same
 */
inline fun Calendar.isSameSecond(target: Calendar): Boolean = Datex.isSameSecond(this, target)

/**
 * Return true if the secondOfMinute is the same
 */
inline fun Calendar.isSameSecondOfMinute(target: Calendar): Boolean = Datex.isSameSecondOfMinute(this, target)

/**
 * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
 */
inline fun Calendar.isSameMillisecond(target: Calendar): Boolean = Datex.isSameMillisecond(this, target)

/**
 * Return true if the millisecondOfSecond is the same
 */
inline fun Calendar.isSameMillisecondOfSecond(target: Calendar): Boolean = Datex.isSameMillisecondOfSecond(this, target)


/**
 * Return true if the year is the same
 */
inline fun Date.isSameYear(target: Date, locale: Locale): Boolean = Datex.isSameYear(this, target, locale)

/**
 * Return true if the year is the same
 */
inline fun Date.isSameYear(target: Date): Boolean = Datex.isSameYear(this, target)

/**
 * Returns true if the year and month are the same
 */
inline fun Date.isSameMonth(target: Date, locale: Locale): Boolean = Datex.isSameMonth(this, target, locale)

/**
 * Returns true if the year and month are the same
 */
inline fun Date.isSameMonth(target: Date): Boolean = Datex.isSameMonth(this, target)

/**
 * Return true if the months is the same
 */
inline fun Date.isSameMonthOfYear(target: Date, locale: Locale): Boolean = Datex.isSameMonthOfYear(this, target, locale)

/**
 * Return true if the months is the same
 */
inline fun Date.isSameMonthOfYear(target: Date): Boolean = Datex.isSameMonthOfYear(this, target)

/**
 * Returns true if the year, month, and week are the same
 */
inline fun Date.isSameWeek(target: Date, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.isSameWeek(this, target, firstDayOfWeek, locale)

/**
 * Returns true if the year, month, and week are the same
 */
inline fun Date.isSameWeek(target: Date, firstDayOfWeek: Int): Boolean = Datex.isSameWeek(this, target, firstDayOfWeek)

/**
 * Returns true if the year, month, and week are the same
 */
inline fun Date.isSameWeek(target: Date, locale: Locale): Boolean = Datex.isSameWeek(this, target, locale)

/**
 * Returns true if the year, month, and week are the same
 */
inline fun Date.isSameWeek(target: Date): Boolean = Datex.isSameWeek(this, target)

/**
 * Return true if the weekOfYear is the same
 */
inline fun Date.isSameWeekOfYear(target: Date, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.isSameWeekOfYear(this, target, firstDayOfWeek, locale)

/**
 * Return true if the weekOfYear is the same
 */
inline fun Date.isSameWeekOfYear(target: Date, firstDayOfWeek: Int): Boolean = Datex.isSameWeekOfYear(this, target, firstDayOfWeek)

/**
 * Return true if the weekOfYear is the same
 */
inline fun Date.isSameWeekOfYear(target: Date, locale: Locale): Boolean = Datex.isSameWeekOfYear(this, target, locale)

/**
 * Return true if the weekOfYear is the same
 */
inline fun Date.isSameWeekOfYear(target: Date): Boolean = Datex.isSameWeekOfYear(this, target)

/**
 * Return true if the weekOfMonth is the same
 */
inline fun Date.isSameWeekOfMonth(target: Date, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.isSameWeekOfMonth(this, target, firstDayOfWeek, locale)

/**
 * Return true if the weekOfMonth is the same
 */
inline fun Date.isSameWeekOfMonth(target: Date, firstDayOfWeek: Int): Boolean = Datex.isSameWeekOfMonth(this, target, firstDayOfWeek)

/**
 * Return true if the weekOfMonth is the same
 */
inline fun Date.isSameWeekOfMonth(target: Date, locale: Locale): Boolean = Datex.isSameWeekOfMonth(this, target, locale)

/**
 * Return true if the weekOfMonth is the same
 */
inline fun Date.isSameWeekOfMonth(target: Date): Boolean = Datex.isSameWeekOfMonth(this, target)

/**
 * Returns true if the year, month, week and day are the same
 */
inline fun Date.isSameDay(target: Date, locale: Locale): Boolean = Datex.isSameDay(this, target, locale)

/**
 * Returns true if the year, month, week and day are the same
 */
inline fun Date.isSameDay(target: Date): Boolean = Datex.isSameDay(this, target)

/**
 * Return true if the dayOfYear is the same
 */
inline fun Date.isSameDayOfYear(target: Date, locale: Locale): Boolean = Datex.isSameDayOfYear(this, target, locale)

/**
 * Return true if the dayOfYear is the same
 */
inline fun Date.isSameDayOfYear(target: Date): Boolean = Datex.isSameDayOfYear(this, target)

/**
 * Return true if the dayOfMonth is the same
 */
inline fun Date.isSameDayOfMonth(target: Date, locale: Locale): Boolean = Datex.isSameDayOfMonth(this, target, locale)

/**
 * Return true if the dayOfMonth is the same
 */
inline fun Date.isSameDayOfMonth(target: Date): Boolean = Datex.isSameDayOfMonth(this, target)

/**
 * Return true if the dayOfWeek is the same
 */
inline fun Date.isSameDayOfWeek(target: Date, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.isSameDayOfWeek(this, target, firstDayOfWeek, locale)

/**
 * Return true if the dayOfWeek is the same
 */
inline fun Date.isSameDayOfWeek(target: Date, firstDayOfWeek: Int): Boolean = Datex.isSameDayOfWeek(this, target, firstDayOfWeek)

/**
 * Return true if the dayOfWeek is the same
 */
inline fun Date.isSameDayOfWeek(target: Date, locale: Locale): Boolean = Datex.isSameDayOfWeek(this, target, locale)

/**
 * Return true if the dayOfWeek is the same
 */
inline fun Date.isSameDayOfWeek(target: Date): Boolean = Datex.isSameDayOfWeek(this, target)

/**
 * Return true if the dayOfWeekInMonth is the same
 */
inline fun Date.isSameDayOfWeekInMonth(target: Date, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.isSameDayOfWeekInMonth(this, target, firstDayOfWeek, locale)

/**
 * Return true if the dayOfWeekInMonth is the same
 */
inline fun Date.isSameDayOfWeekInMonth(target: Date, firstDayOfWeek: Int): Boolean = Datex.isSameDayOfWeekInMonth(this, target, firstDayOfWeek)

/**
 * Return true if the dayOfWeekInMonth is the same
 */
inline fun Date.isSameDayOfWeekInMonth(target: Date, locale: Locale): Boolean = Datex.isSameDayOfWeekInMonth(this, target, locale)

/**
 * Return true if the dayOfWeekInMonth is the same
 */
inline fun Date.isSameDayOfWeekInMonth(target: Date): Boolean = Datex.isSameDayOfWeekInMonth(this, target)

/**
 * Returns true if the year, month, week, day and hour are the same
 */
inline fun Date.isSameHour(target: Date, locale: Locale): Boolean = Datex.isSameHour(this, target, locale)

/**
 * Returns true if the year, month, week, day and hour are the same
 */
inline fun Date.isSameHour(target: Date): Boolean = Datex.isSameHour(this, target)

/**
 * Return true if the 24H hour is the same
 */
inline fun Date.isSameHourOf24H(target: Date, locale: Locale): Boolean = Datex.isSameHourOf24H(this, target, locale)

/**
 * Return true if the 24H hour is the same
 */
inline fun Date.isSameHourOf24H(target: Date): Boolean = Datex.isSameHourOf24H(this, target)

/**
 * Return true if the 12H hour is the same
 */
inline fun Date.isSameHourOf12H(target: Date, locale: Locale): Boolean = Datex.isSameHourOf12H(this, target, locale)

/**
 * Return true if the 12H hour is the same
 */
inline fun Date.isSameHourOf12H(target: Date): Boolean = Datex.isSameHourOf12H(this, target)

/**
 * Returns true if the year, month, week, day, hour and minute are the same
 */
inline fun Date.isSameMinute(target: Date, locale: Locale): Boolean = Datex.isSameMinute(this, target, locale)

/**
 * Returns true if the year, month, week, day, hour and minute are the same
 */
inline fun Date.isSameMinute(target: Date): Boolean = Datex.isSameMinute(this, target)

/**
 * Return true if the minuteOfHour is the same
 */
inline fun Date.isSameMinuteOfHour(target: Date, locale: Locale): Boolean = Datex.isSameMinuteOfHour(this, target, locale)

/**
 * Return true if the minuteOfHour is the same
 */
inline fun Date.isSameMinuteOfHour(target: Date): Boolean = Datex.isSameMinuteOfHour(this, target)

/**
 * Returns true if the year, month, week, day, hour, minute and second are the same
 */
inline fun Date.isSameSecond(target: Date, locale: Locale): Boolean = Datex.isSameSecond(this, target, locale)

/**
 * Returns true if the year, month, week, day, hour, minute and second are the same
 */
inline fun Date.isSameSecond(target: Date): Boolean = Datex.isSameSecond(this, target)

/**
 * Return true if the secondOfMinute is the same
 */
inline fun Date.isSameSecondOfMinute(target: Date, locale: Locale): Boolean = Datex.isSameSecondOfMinute(this, target, locale)

/**
 * Return true if the secondOfMinute is the same
 */
inline fun Date.isSameSecondOfMinute(target: Date): Boolean = Datex.isSameSecondOfMinute(this, target)

/**
 * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
 */
inline fun Date.isSameMillisecond(target: Date, locale: Locale): Boolean = Datex.isSameMillisecond(this, target, locale)

/**
 * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
 */
inline fun Date.isSameMillisecond(target: Date): Boolean = Datex.isSameMillisecond(this, target)

/**
 * Return true if the millisecondOfSecond is the same
 */
inline fun Date.isSameMillisecondOfSecond(target: Date, locale: Locale): Boolean = Datex.isSameMillisecondOfSecond(this, target, locale)

/**
 * Return true if the millisecondOfSecond is the same
 */
inline fun Date.isSameMillisecondOfSecond(target: Date): Boolean = Datex.isSameMillisecondOfSecond(this, target)


/**
 * Return true if the year is the same
 */
inline fun Long.isSameYear(target: Long, locale: Locale): Boolean = Datex.isSameYear(this, target, locale)

/**
 * Return true if the year is the same
 */
inline fun Long.isSameYear(target: Long): Boolean = Datex.isSameYear(this, target)

/**
 * Returns true if the year and month are the same
 */
inline fun Long.isSameMonth(target: Long, locale: Locale): Boolean = Datex.isSameMonth(this, target, locale)

/**
 * Returns true if the year and month are the same
 */
inline fun Long.isSameMonth(target: Long): Boolean = Datex.isSameMonth(this, target)

/**
 * Return true if the months is the same
 */
inline fun Long.isSameMonthOfYear(target: Long, locale: Locale): Boolean = Datex.isSameMonthOfYear(this, target, locale)

/**
 * Return true if the months is the same
 */
inline fun Long.isSameMonthOfYear(target: Long): Boolean = Datex.isSameMonthOfYear(this, target)

/**
 * Returns true if the year, month, and week are the same
 */
inline fun Long.isSameWeek(target: Long, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.isSameWeek(this, target, firstDayOfWeek, locale)

/**
 * Returns true if the year, month, and week are the same
 */
inline fun Long.isSameWeek(target: Long, firstDayOfWeek: Int): Boolean = Datex.isSameWeek(this, target, firstDayOfWeek)

/**
 * Returns true if the year, month, and week are the same
 */
inline fun Long.isSameWeek(target: Long, locale: Locale): Boolean = Datex.isSameWeek(this, target, locale)

/**
 * Returns true if the year, month, and week are the same
 */
inline fun Long.isSameWeek(target: Long): Boolean = Datex.isSameWeek(this, target)

/**
 * Return true if the weekOfYear is the same
 */
inline fun Long.isSameWeekOfYear(target: Long, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.isSameWeekOfYear(this, target, firstDayOfWeek, locale)

/**
 * Return true if the weekOfYear is the same
 */
inline fun Long.isSameWeekOfYear(target: Long, firstDayOfWeek: Int): Boolean = Datex.isSameWeekOfYear(this, target, firstDayOfWeek)

/**
 * Return true if the weekOfYear is the same
 */
inline fun Long.isSameWeekOfYear(target: Long, locale: Locale): Boolean = Datex.isSameWeekOfYear(this, target, locale)

/**
 * Return true if the weekOfYear is the same
 */
inline fun Long.isSameWeekOfYear(target: Long): Boolean = Datex.isSameWeekOfYear(this, target)

/**
 * Return true if the weekOfMonth is the same
 */
inline fun Long.isSameWeekOfMonth(target: Long, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.isSameWeekOfMonth(this, target, firstDayOfWeek, locale)

/**
 * Return true if the weekOfMonth is the same
 */
inline fun Long.isSameWeekOfMonth(target: Long, firstDayOfWeek: Int): Boolean = Datex.isSameWeekOfMonth(this, target, firstDayOfWeek)

/**
 * Return true if the weekOfMonth is the same
 */
inline fun Long.isSameWeekOfMonth(target: Long, locale: Locale): Boolean = Datex.isSameWeekOfMonth(this, target, locale)

/**
 * Return true if the weekOfMonth is the same
 */
inline fun Long.isSameWeekOfMonth(target: Long): Boolean = Datex.isSameWeekOfMonth(this, target)

/**
 * Returns true if the year, month, week and day are the same
 */
inline fun Long.isSameDay(target: Long, locale: Locale): Boolean = Datex.isSameDay(this, target, locale)

/**
 * Returns true if the year, month, week and day are the same
 */
inline fun Long.isSameDay(target: Long): Boolean = Datex.isSameDay(this, target)

/**
 * Return true if the dayOfYear is the same
 */
inline fun Long.isSameDayOfYear(target: Long, locale: Locale): Boolean = Datex.isSameDayOfYear(this, target, locale)

/**
 * Return true if the dayOfYear is the same
 */
inline fun Long.isSameDayOfYear(target: Long): Boolean = Datex.isSameDayOfYear(this, target)

/**
 * Return true if the dayOfMonth is the same
 */
inline fun Long.isSameDayOfMonth(target: Long, locale: Locale): Boolean = Datex.isSameDayOfMonth(this, target, locale)

/**
 * Return true if the dayOfMonth is the same
 */
inline fun Long.isSameDayOfMonth(target: Long): Boolean = Datex.isSameDayOfMonth(this, target)

/**
 * Return true if the dayOfWeek is the same
 */
inline fun Long.isSameDayOfWeek(target: Long, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.isSameDayOfWeek(this, target, firstDayOfWeek, locale)

/**
 * Return true if the dayOfWeek is the same
 */
inline fun Long.isSameDayOfWeek(target: Long, firstDayOfWeek: Int): Boolean = Datex.isSameDayOfWeek(this, target, firstDayOfWeek)

/**
 * Return true if the dayOfWeek is the same
 */
inline fun Long.isSameDayOfWeek(target: Long, locale: Locale): Boolean = Datex.isSameDayOfWeek(this, target, locale)

/**
 * Return true if the dayOfWeek is the same
 */
inline fun Long.isSameDayOfWeek(target: Long): Boolean = Datex.isSameDayOfWeek(this, target)

/**
 * Return true if the dayOfWeekInMonth is the same
 */
inline fun Long.isSameDayOfWeekInMonth(target: Long, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.isSameDayOfWeekInMonth(this, target, firstDayOfWeek, locale)

/**
 * Return true if the dayOfWeekInMonth is the same
 */
inline fun Long.isSameDayOfWeekInMonth(target: Long, firstDayOfWeek: Int): Boolean = Datex.isSameDayOfWeekInMonth(this, target, firstDayOfWeek)

/**
 * Return true if the dayOfWeekInMonth is the same
 */
inline fun Long.isSameDayOfWeekInMonth(target: Long, locale: Locale): Boolean = Datex.isSameDayOfWeekInMonth(this, target, locale)

/**
 * Return true if the dayOfWeekInMonth is the same
 */
inline fun Long.isSameDayOfWeekInMonth(target: Long): Boolean = Datex.isSameDayOfWeekInMonth(this, target)

/**
 * Returns true if the year, month, week, day and hour are the same
 */
inline fun Long.isSameHour(target: Long, locale: Locale): Boolean = Datex.isSameHour(this, target, locale)

/**
 * Returns true if the year, month, week, day and hour are the same
 */
inline fun Long.isSameHour(target: Long): Boolean = Datex.isSameHour(this, target)

/**
 * Return true if the 24H hour is the same
 */
inline fun Long.isSameHourOf24H(target: Long, locale: Locale): Boolean = Datex.isSameHourOf24H(this, target, locale)

/**
 * Return true if the 24H hour is the same
 */
inline fun Long.isSameHourOf24H(target: Long): Boolean = Datex.isSameHourOf24H(this, target)

/**
 * Return true if the 12H hour is the same
 */
inline fun Long.isSameHourOf12H(target: Long, locale: Locale): Boolean = Datex.isSameHourOf12H(this, target, locale)

/**
 * Return true if the 12H hour is the same
 */
inline fun Long.isSameHourOf12H(target: Long): Boolean = Datex.isSameHourOf12H(this, target)

/**
 * Returns true if the year, month, week, day, hour and minute are the same
 */
inline fun Long.isSameMinute(target: Long, locale: Locale): Boolean = Datex.isSameMinute(this, target, locale)

/**
 * Returns true if the year, month, week, day, hour and minute are the same
 */
inline fun Long.isSameMinute(target: Long): Boolean = Datex.isSameMinute(this, target)

/**
 * Return true if the minuteOfHour is the same
 */
inline fun Long.isSameMinuteOfHour(target: Long, locale: Locale): Boolean = Datex.isSameMinuteOfHour(this, target, locale)

/**
 * Return true if the minuteOfHour is the same
 */
inline fun Long.isSameMinuteOfHour(target: Long): Boolean = Datex.isSameMinuteOfHour(this, target)

/**
 * Returns true if the year, month, week, day, hour, minute and second are the same
 */
inline fun Long.isSameSecond(target: Long, locale: Locale): Boolean = Datex.isSameSecond(this, target, locale)

/**
 * Returns true if the year, month, week, day, hour, minute and second are the same
 */
inline fun Long.isSameSecond(target: Long): Boolean = Datex.isSameSecond(this, target)

/**
 * Return true if the secondOfMinute is the same
 */
inline fun Long.isSameSecondOfMinute(target: Long, locale: Locale): Boolean = Datex.isSameSecondOfMinute(this, target, locale)

/**
 * Return true if the secondOfMinute is the same
 */
inline fun Long.isSameSecondOfMinute(target: Long): Boolean = Datex.isSameSecondOfMinute(this, target)

/**
 * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
 */
inline fun Long.isSameMillisecond(target: Long, locale: Locale): Boolean = Datex.isSameMillisecond(this, target, locale)

/**
 * Returns true if the year, month, week, day, hour, minute, second and millisecond are the same
 */
inline fun Long.isSameMillisecond(target: Long): Boolean = Datex.isSameMillisecond(this, target)

/**
 * Return true if the millisecondOfSecond is the same
 */
inline fun Long.isSameMillisecondOfSecond(target: Long, locale: Locale): Boolean = Datex.isSameMillisecondOfSecond(this, target, locale)

/**
 * Return true if the millisecondOfSecond is the same
 */
inline fun Long.isSameMillisecondOfSecond(target: Long): Boolean = Datex.isSameMillisecondOfSecond(this, target)


/*
 * differ
 */


/**
 * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
 */
inline fun Calendar.differField(target: Calendar, field: Int, amount: Int): Boolean = Datex.differField(this, target, field, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] year
 */
inline fun Calendar.differYear(target: Calendar, amount: Int): Boolean = Datex.differYear(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] month
 */
inline fun Calendar.differMonth(target: Calendar, amount: Int): Boolean = Datex.differMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
 */
inline fun Calendar.differWeekOfYear(target: Calendar, amount: Int): Boolean = Datex.differWeekOfYear(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
 */
inline fun Calendar.differWeekOfMonth(target: Calendar, amount: Int): Boolean = Datex.differWeekOfMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfYear
 */
inline fun Calendar.differDayOfYear(target: Calendar, amount: Int): Boolean = Datex.differDayOfYear(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfMonth
 */
inline fun Calendar.differDayOfMonth(target: Calendar, amount: Int): Boolean = Datex.differDayOfMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
 */
inline fun Calendar.differDayOfWeek(target: Calendar, amount: Int): Boolean = Datex.differDayOfWeek(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
 */
inline fun Calendar.differDayOfWeekInMonth(target: Calendar, amount: Int): Boolean = Datex.differDayOfWeekInMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] hourOfDay
 */
inline fun Calendar.differHourOfDay(target: Calendar, amount: Int): Boolean = Datex.differHourOfDay(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] hour
 */
inline fun Calendar.differHour(target: Calendar, amount: Int): Boolean = Datex.differHour(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] minute
 */
inline fun Calendar.differMinute(target: Calendar, amount: Int): Boolean = Datex.differMinute(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] second
 */
inline fun Calendar.differSecond(target: Calendar, amount: Int): Boolean = Datex.differSecond(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] millisecond
 */
inline fun Calendar.differMillisecond(target: Calendar, amount: Int): Boolean = Datex.differMillisecond(this, target, amount)


/**
 * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
 */
inline fun Date.differCalendarField(target: Date, field: Int, amount: Int, firstDayOfWeek: Int, locale: Locale): Boolean =
        Datex.differCalendarField(this, target, field, amount, firstDayOfWeek, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
 */
inline fun Date.differCalendarField(target: Date, field: Int, amount: Int, firstDayOfWeek: Int): Boolean =
        Datex.differCalendarField(this, target, field, amount, firstDayOfWeek)

/**
 * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
 */
inline fun Date.differCalendarField(target: Date, field: Int, amount: Int, locale: Locale): Boolean =
        Datex.differCalendarField(this, target, field, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
 */
inline fun Date.differCalendarField(target: Date, field: Int, amount: Int): Boolean =
        Datex.differCalendarField(this, target, field, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] year
 */
inline fun Date.differYear(target: Date, amount: Int, locale: Locale): Boolean = Datex.differYear(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] year
 */
inline fun Date.differYear(target: Date, amount: Int): Boolean = Datex.differYear(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] month
 */
inline fun Date.differMonth(target: Date, amount: Int, locale: Locale): Boolean = Datex.differMonth(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] month
 */
inline fun Date.differMonth(target: Date, amount: Int): Boolean = Datex.differMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
 */
inline fun Date.differWeekOfYear(target: Date, amount: Int, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.differWeekOfYear(this, target, amount, firstDayOfWeek, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
 */
inline fun Date.differWeekOfYear(target: Date, amount: Int, firstDayOfWeek: Int): Boolean = Datex.differWeekOfYear(this, target, amount, firstDayOfWeek)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
 */
inline fun Date.differWeekOfYear(target: Date, amount: Int, locale: Locale): Boolean = Datex.differWeekOfYear(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
 */
inline fun Date.differWeekOfYear(target: Date, amount: Int): Boolean = Datex.differWeekOfYear(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
 */
inline fun Date.differWeekOfMonth(target: Date, amount: Int, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.differWeekOfMonth(this, target, amount, firstDayOfWeek, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
 */
inline fun Date.differWeekOfMonth(target: Date, amount: Int, firstDayOfWeek: Int): Boolean = Datex.differWeekOfMonth(this, target, amount, firstDayOfWeek)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
 */
inline fun Date.differWeekOfMonth(target: Date, amount: Int, locale: Locale): Boolean = Datex.differWeekOfMonth(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
 */
inline fun Date.differWeekOfMonth(target: Date, amount: Int): Boolean = Datex.differWeekOfMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfYear
 */
inline fun Date.differDayOfYear(target: Date, amount: Int, locale: Locale): Boolean = Datex.differDayOfYear(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfYear
 */
inline fun Date.differDayOfYear(target: Date, amount: Int): Boolean = Datex.differDayOfYear(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfMonth
 */
inline fun Date.differDayOfMonth(target: Date, amount: Int, locale: Locale): Boolean = Datex.differDayOfMonth(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfMonth
 */
inline fun Date.differDayOfMonth(target: Date, amount: Int): Boolean = Datex.differDayOfMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
 */
inline fun Date.differDayOfWeek(target: Date, amount: Int, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.differDayOfWeek(this, target, amount, firstDayOfWeek, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
 */
inline fun Date.differDayOfWeek(target: Date, amount: Int, firstDayOfWeek: Int): Boolean = Datex.differDayOfWeek(this, target, amount, firstDayOfWeek)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
 */
inline fun Date.differDayOfWeek(target: Date, amount: Int, locale: Locale): Boolean = Datex.differDayOfWeek(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
 */
inline fun Date.differDayOfWeek(target: Date, amount: Int): Boolean = Datex.differDayOfWeek(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
 */
inline fun Date.differDayOfWeekInMonth(target: Date, amount: Int, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.differDayOfWeekInMonth(this, target, amount, firstDayOfWeek, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
 */
inline fun Date.differDayOfWeekInMonth(target: Date, amount: Int, firstDayOfWeek: Int): Boolean = Datex.differDayOfWeekInMonth(this, target, amount, firstDayOfWeek)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
 */
inline fun Date.differDayOfWeekInMonth(target: Date, amount: Int, locale: Locale): Boolean = Datex.differDayOfWeekInMonth(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
 */
inline fun Date.differDayOfWeekInMonth(target: Date, amount: Int): Boolean = Datex.differDayOfWeekInMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] hourOfDay
 */
inline fun Date.differHourOfDay(target: Date, amount: Int, locale: Locale): Boolean = Datex.differHourOfDay(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] hourOfDay
 */
inline fun Date.differHourOfDay(target: Date, amount: Int): Boolean = Datex.differHourOfDay(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] hour
 */
inline fun Date.differHour(target: Date, amount: Int, locale: Locale): Boolean = Datex.differHour(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] hour
 */
inline fun Date.differHour(target: Date, amount: Int): Boolean = Datex.differHour(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] minute
 */
inline fun Date.differMinute(target: Date, amount: Int, locale: Locale): Boolean = Datex.differMinute(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] minute
 */
inline fun Date.differMinute(target: Date, amount: Int): Boolean = Datex.differMinute(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] second
 */
inline fun Date.differSecond(target: Date, amount: Int, locale: Locale): Boolean = Datex.differSecond(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] second
 */
inline fun Date.differSecond(target: Date, amount: Int): Boolean = Datex.differSecond(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] millisecond
 */
inline fun Date.differMillisecond(target: Date, amount: Int, locale: Locale): Boolean = Datex.differMillisecond(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] millisecond
 */
inline fun Date.differMillisecond(target: Date, amount: Int): Boolean = Datex.differMillisecond(this, target, amount)


/**
 * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
 */
inline fun Long.differCalendarField(target: Long, field: Int, amount: Int, firstDayOfWeek: Int, locale: Locale): Boolean =
        Datex.differCalendarField(this, target, field, amount, firstDayOfWeek, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
 */
inline fun Long.differCalendarField(target: Long, field: Int, amount: Int, firstDayOfWeek: Int): Boolean =
        Datex.differCalendarField(this, target, field, amount, firstDayOfWeek)

/**
 * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
 */
inline fun Long.differCalendarField(target: Long, field: Int, amount: Int, locale: Locale): Boolean =
        Datex.differCalendarField(this, target, field, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
 */
inline fun Long.differCalendarField(target: Long, field: Int, amount: Int): Boolean =
        Datex.differCalendarField(this, target, field, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] year
 */
inline fun Long.differYear(target: Long, amount: Int, locale: Locale): Boolean = Datex.differYear(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] year
 */
inline fun Long.differYear(target: Long, amount: Int): Boolean = Datex.differYear(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] month
 */
inline fun Long.differMonth(target: Long, amount: Int, locale: Locale): Boolean = Datex.differMonth(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] month
 */
inline fun Long.differMonth(target: Long, amount: Int): Boolean = Datex.differMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
 */
inline fun Long.differWeekOfYear(target: Long, amount: Int, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.differWeekOfYear(this, target, amount, firstDayOfWeek, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
 */
inline fun Long.differWeekOfYear(target: Long, amount: Int, firstDayOfWeek: Int): Boolean = Datex.differWeekOfYear(this, target, amount, firstDayOfWeek)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
 */
inline fun Long.differWeekOfYear(target: Long, amount: Int, locale: Locale): Boolean = Datex.differWeekOfYear(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfYear
 */
inline fun Long.differWeekOfYear(target: Long, amount: Int): Boolean = Datex.differWeekOfYear(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
 */
inline fun Long.differWeekOfMonth(target: Long, amount: Int, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.differWeekOfMonth(this, target, amount, firstDayOfWeek, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
 */
inline fun Long.differWeekOfMonth(target: Long, amount: Int, firstDayOfWeek: Int): Boolean = Datex.differWeekOfMonth(this, target, amount, firstDayOfWeek)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
 */
inline fun Long.differWeekOfMonth(target: Long, amount: Int, locale: Locale): Boolean = Datex.differWeekOfMonth(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] weekOfMonth
 */
inline fun Long.differWeekOfMonth(target: Long, amount: Int): Boolean = Datex.differWeekOfMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfYear
 */
inline fun Long.differDayOfYear(target: Long, amount: Int, locale: Locale): Boolean = Datex.differDayOfYear(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfYear
 */
inline fun Long.differDayOfYear(target: Long, amount: Int): Boolean = Datex.differDayOfYear(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfMonth
 */
inline fun Long.differDayOfMonth(target: Long, amount: Int, locale: Locale): Boolean = Datex.differDayOfMonth(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfMonth
 */
inline fun Long.differDayOfMonth(target: Long, amount: Int): Boolean = Datex.differDayOfMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
 */
inline fun Long.differDayOfWeek(target: Long, amount: Int, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.differDayOfWeek(this, target, amount, firstDayOfWeek, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
 */
inline fun Long.differDayOfWeek(target: Long, amount: Int, firstDayOfWeek: Int): Boolean = Datex.differDayOfWeek(this, target, amount, firstDayOfWeek)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
 */
inline fun Long.differDayOfWeek(target: Long, amount: Int, locale: Locale): Boolean = Datex.differDayOfWeek(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeek
 */
inline fun Long.differDayOfWeek(target: Long, amount: Int): Boolean = Datex.differDayOfWeek(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
 */
inline fun Long.differDayOfWeekInMonth(target: Long, amount: Int, firstDayOfWeek: Int, locale: Locale): Boolean = Datex.differDayOfWeekInMonth(this, target, amount, firstDayOfWeek, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
 */
inline fun Long.differDayOfWeekInMonth(target: Long, amount: Int, firstDayOfWeek: Int): Boolean = Datex.differDayOfWeekInMonth(this, target, amount, firstDayOfWeek)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
 */
inline fun Long.differDayOfWeekInMonth(target: Long, amount: Int, locale: Locale): Boolean = Datex.differDayOfWeekInMonth(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] dayOfWeekInMonth
 */
inline fun Long.differDayOfWeekInMonth(target: Long, amount: Int): Boolean = Datex.differDayOfWeekInMonth(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] hourOfDay
 */
inline fun Long.differHourOfDay(target: Long, amount: Int, locale: Locale): Boolean = Datex.differHourOfDay(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] hourOfDay
 */
inline fun Long.differHourOfDay(target: Long, amount: Int): Boolean = Datex.differHourOfDay(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] hour
 */
inline fun Long.differHour(target: Long, amount: Int, locale: Locale): Boolean = Datex.differHour(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] hour
 */
inline fun Long.differHour(target: Long, amount: Int): Boolean = Datex.differHour(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] minute
 */
inline fun Long.differMinute(target: Long, amount: Int, locale: Locale): Boolean = Datex.differMinute(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] minute
 */
inline fun Long.differMinute(target: Long, amount: Int): Boolean = Datex.differMinute(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] second
 */
inline fun Long.differSecond(target: Long, amount: Int, locale: Locale): Boolean = Datex.differSecond(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] second
 */
inline fun Long.differSecond(target: Long, amount: Int): Boolean = Datex.differSecond(this, target, amount)

/**
 * Return true if the difference from the [target] does not exceed the [amount] millisecond
 */
inline fun Long.differMillisecond(target: Long, amount: Int, locale: Locale): Boolean = Datex.differMillisecond(this, target, amount, locale)

/**
 * Return true if the difference from the [target] does not exceed the [amount] millisecond
 */
inline fun Long.differMillisecond(target: Long, amount: Int): Boolean = Datex.differMillisecond(this, target, amount)