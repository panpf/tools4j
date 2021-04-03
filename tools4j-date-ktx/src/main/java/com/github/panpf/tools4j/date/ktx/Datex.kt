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

@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4j.date.ktx

import com.github.panpf.tools4j.date.Datex
import org.jetbrains.annotations.NotNull
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/*
 * Date related extension methods or properties
 */


/* ******************************************* toDate ****************************************** */


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


/* ******************************************* createCalendar ****************************************** */


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


/* ******************************************* createCalendar ****************************************** */


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
 * Convert Date to a formatted string
 */
inline fun Long.formatDate(format: SimpleDateFormat): String = Datex.format(this, format)

/**
 * Convert Date to a formatted string
 */
inline fun Long.formatDate(pattern: String, locale: Locale): String = Datex.format(this, pattern, locale)

/**
 * Convert Date to a formatted string
 */
inline fun Long.formatDate(pattern: String): String = Datex.format(this, pattern)

/**
 * Format the length of time according to the specified 'pattern'
 *
 * @param pattern Formatting pattern，The following types are supported:
 *
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
inline fun Long.formatTimeLength(pattern: String) = Datex.formatTimeLength(this, pattern)



/* ******************************************* get\* ****************************************** */


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


/* ******************************************* add\* ****************************************** */


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


/* ******************************************* isSame\* ****************************************** */


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


/* ******************************************* differ\* ****************************************** */


/**
 * Return true if the difference from the [target] does not exceed the [amount] specified calendar field
 */
inline fun Calendar.differField(target: Calendar, field: Int, amount: Int): Boolean = Datex.differField(this, target, field, amount)

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