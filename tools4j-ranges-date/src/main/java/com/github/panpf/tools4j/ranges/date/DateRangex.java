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

package com.github.panpf.tools4j.ranges.date;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

/**
 * Date Time Range tool method
 */
public class DateRangex {

    private DateRangex() {
    }


    /* ******************************************* Year Range *******************************************/

    /**
     * Create a positive-order year ranges
     */
    @NotNull
    public static YearRange yearRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new YearRange(date, endInclusive);
    }

    /**
     * Create a positive-order year range that does not contain [end]
     */
    @NotNull
    public static YearRange yearUntil(@NotNull Date date, @NotNull Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.YEAR, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new YearRange(date, endInclusiveDate);
    }

    /**
     * Create a reversed year range
     */
    @NotNull
    public static YearProgression yearDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new YearProgression(date, endInclusive, -1);
    }

    /**
     * Create a positive-order year ranges
     */
    @NotNull
    public static YearProgression yearRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new YearProgression(date, endInclusive, step);
    }

    /**
     * Create a positive-order year range that does not contain [end]
     */
    @NotNull
    public static YearProgression yearUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.YEAR, step > 0 ? -1 : 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new YearProgression(date, endInclusiveDate, step);
    }


    /* ******************************************* Month Range *******************************************/


    /**
     * Create a positive-order month ranges
     */
    @NotNull
    public static MonthRange monthRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new MonthRange(date, endInclusive);
    }

    /**
     * Create a positive-order month range that does not contain [end]
     */
    @NotNull
    public static MonthRange monthUntil(@NotNull Date date, @NotNull Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MONTH, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MonthRange(date, endInclusiveDate);
    }

    /**
     * Create a reversed month range
     */
    @NotNull
    public static MonthProgression monthDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new MonthProgression(date, endInclusive, -1);
    }

    /**
     * Create a positive-order month ranges
     */
    @NotNull
    public static MonthProgression monthRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new MonthProgression(date, endInclusive, step);
    }

    /**
     * Create a positive-order month range that does not contain [end]
     */
    @NotNull
    public static MonthProgression monthUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MONTH, step > 0 ? -1 : 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MonthProgression(date, endInclusiveDate, step);
    }


    /* ******************************************* Day Range *******************************************/


    /**
     * Create a positive-order day ranges
     */
    @NotNull
    public static DayOfMonthRange dayOfMonthRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new DayOfMonthRange(date, endInclusive);
    }

    /**
     * Create a positive-order day range that does not contain [end]
     */
    @NotNull
    public static DayOfMonthRange dayOfMonthUntil(@NotNull Date date, @NotNull Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new DayOfMonthRange(date, endInclusiveDate);
    }

    /**
     * Create a reversed day range
     */
    @NotNull
    public static DayOfMonthProgression dayOfMonthDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new DayOfMonthProgression(date, endInclusive, -1);
    }

    /**
     * Create a positive-order day ranges
     */
    @NotNull
    public static DayOfMonthProgression dayOfMonthRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new DayOfMonthProgression(date, endInclusive, step);
    }

    /**
     * Create a positive-order day range that does not contain [end]
     */
    @NotNull
    public static DayOfMonthProgression dayOfMonthUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, step > 0 ? -1 : 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new DayOfMonthProgression(date, endInclusiveDate, step);
    }


    /* ******************************************* Hour Range *******************************************/


    /**
     * Create a positive-order hour ranges
     */
    @NotNull
    public static HourOfDayRange hourOfDayRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new HourOfDayRange(date, endInclusive);
    }

    /**
     * Create a positive-order hour range that does not contain [end]
     */
    @NotNull
    public static HourOfDayRange hourOfDayUntil(@NotNull Date date, @NotNull Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new HourOfDayRange(date, endInclusiveDate);
    }

    /**
     * Create a reversed hour range
     */
    @NotNull
    public static HourOfDayProgression hourOfDayDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new HourOfDayProgression(date, endInclusive, -1);
    }

    /**
     * Create a positive-order hour ranges
     */
    @NotNull
    public static HourOfDayProgression hourOfDayRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new HourOfDayProgression(date, endInclusive, step);
    }

    /**
     * Create a positive-order hour range that does not contain [end]
     */
    @NotNull
    public static HourOfDayProgression hourOfDayUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, step > 0 ? -1 : 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new HourOfDayProgression(date, endInclusiveDate, step);
    }


    /* ******************************************* Minute Range *******************************************/


    /**
     * Create a positive-order minute ranges
     */
    @NotNull
    public static MinuteRange minuteRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new MinuteRange(date, endInclusive);
    }

    /**
     * Create a positive-order minute range that does not contain [end]
     */
    @NotNull
    public static MinuteRange minuteUntil(@NotNull Date date, @NotNull Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MINUTE, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MinuteRange(date, endInclusiveDate);
    }

    /**
     * Create a reversed minute range
     */
    @NotNull
    public static MinuteProgression minuteDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new MinuteProgression(date, endInclusive, -1);
    }

    /**
     * Create a positive-order minute ranges
     */
    @NotNull
    public static MinuteProgression minuteRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new MinuteProgression(date, endInclusive, step);
    }

    /**
     * Create a positive-order minute range that does not contain [end]
     */
    @NotNull
    public static MinuteProgression minuteUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MINUTE, step > 0 ? -1 : 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MinuteProgression(date, endInclusiveDate, step);
    }


    /* ******************************************* Second Range *******************************************/

    /**
     * Create a positive-order second ranges
     */
    @NotNull
    public static SecondRange secondRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new SecondRange(date, endInclusive);
    }

    /**
     * Create a positive-order second range that does not contain [end]
     */
    @NotNull
    public static SecondRange secondUntil(@NotNull Date date, @NotNull Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.SECOND, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new SecondRange(date, endInclusiveDate);
    }

    /**
     * Create a reversed second range
     */
    @NotNull
    public static SecondProgression secondDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new SecondProgression(date, endInclusive, -1);
    }

    /**
     * Create a positive-order second ranges
     */
    @NotNull
    public static SecondProgression secondRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new SecondProgression(date, endInclusive, step);
    }

    /**
     * Create a positive-order second range that does not contain [end]
     */
    @NotNull
    public static SecondProgression secondUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.SECOND, step > 0 ? -1 : 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new SecondProgression(date, endInclusiveDate, step);
    }


    /* ******************************************* Millisecond Range *******************************************/


    /**
     * Create a positive-order millisecond ranges
     */
    @NotNull
    public static MillisecondRange millisecondRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new MillisecondRange(date, endInclusive);
    }

    /**
     * Create a positive-order millisecond range that does not contain [end]
     */
    @NotNull
    public static MillisecondRange millisecondUntil(@NotNull Date date, @NotNull Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MILLISECOND, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MillisecondRange(date, endInclusiveDate);
    }

    /**
     * Create a reversed millisecond range
     */
    @NotNull
    public static MillisecondProgression millisecondDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return new MillisecondProgression(date, endInclusive, -1);
    }

    /**
     * Create a positive-order millisecond ranges
     */
    @NotNull
    public static MillisecondProgression millisecondRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new MillisecondProgression(date, endInclusive, step);
    }

    /**
     * Create a positive-order millisecond range that does not contain [end]
     */
    @NotNull
    public static MillisecondProgression millisecondUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MILLISECOND, step > 0 ? -1 : 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MillisecondProgression(date, endInclusiveDate, step);
    }


    /* ******************************************* reversed *******************************************/


    /**
     * Returns a year progression that goes over the same range in the opposite direction with the same step.
     */
    @NotNull
    public static YearProgression reversed(@NotNull YearProgression progression) {
        return YearProgression.fromClosedRange(progression.getLast(), progression.getFirst(), -progression.getStep());
    }

    /**
     * Returns a month progression that goes over the same range in the opposite direction with the same step.
     */
    @NotNull
    public static MonthProgression reversed(@NotNull MonthProgression progression) {
        return MonthProgression.fromClosedRange(progression.getLast(), progression.getFirst(), -progression.getStep());
    }

    /**
     * Returns a day of month progression that goes over the same range in the opposite direction with the same step.
     */
    @NotNull
    public static DayOfMonthProgression reversed(@NotNull DayOfMonthProgression progression) {
        return DayOfMonthProgression.fromClosedRange(progression.getLast(), progression.getFirst(), -progression.getStep());
    }

    /**
     * Returns a hour of day progression that goes over the same range in the opposite direction with the same step.
     */
    @NotNull
    public static HourOfDayProgression reversed(@NotNull HourOfDayProgression progression) {
        return HourOfDayProgression.fromClosedRange(progression.getLast(), progression.getFirst(), -progression.getStep());
    }

    /**
     * Returns a minute progression that goes over the same range in the opposite direction with the same step.
     */
    @NotNull
    public static MinuteProgression reversed(@NotNull MinuteProgression progression) {
        return MinuteProgression.fromClosedRange(progression.getLast(), progression.getFirst(), -progression.getStep());
    }

    /**
     * Returns a second progression that goes over the same range in the opposite direction with the same step.
     */
    @NotNull
    public static SecondProgression reversed(@NotNull SecondProgression progression) {
        return SecondProgression.fromClosedRange(progression.getLast(), progression.getFirst(), -progression.getStep());
    }

    /**
     * Returns a millisecond progression that goes over the same range in the opposite direction with the same step.
     */
    @NotNull
    public static MillisecondProgression reversed(@NotNull MillisecondProgression progression) {
        return MillisecondProgression.fromClosedRange(progression.getLast(), progression.getFirst(), -progression.getStep());
    }


    /* ******************************************* step *******************************************/

    /**
     * Returns a year progression that goes over the same range with the given step.
     */
    @NotNull
    public static YearProgression step(@NotNull YearProgression progression, int step) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive, was: " + step + ".");
        return YearProgression.fromClosedRange(progression.getFirst(), progression.getLast(), progression.getStep() > 0 ? step : -step);
    }

    /**
     * Returns a month progression that goes over the same range with the given step.
     */
    @NotNull
    public static MonthProgression step(@NotNull MonthProgression progression, int step) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive, was: " + step + ".");
        return MonthProgression.fromClosedRange(progression.getFirst(), progression.getLast(), progression.getStep() > 0 ? step : -step);
    }

    /**
     * Returns a day of month progression that goes over the same range with the given step.
     */
    @NotNull
    public static DayOfMonthProgression step(@NotNull DayOfMonthProgression progression, int step) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive, was: " + step + ".");
        return DayOfMonthProgression.fromClosedRange(progression.getFirst(), progression.getLast(), progression.getStep() > 0 ? step : -step);
    }

    /**
     * Returns a hour of day progression that goes over the same range with the given step.
     */
    @NotNull
    public static HourOfDayProgression step(@NotNull HourOfDayProgression progression, int step) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive, was: " + step + ".");
        return HourOfDayProgression.fromClosedRange(progression.getFirst(), progression.getLast(), progression.getStep() > 0 ? step : -step);
    }

    /**
     * Returns a minute progression that goes over the same range with the given step.
     */
    @NotNull
    public static MinuteProgression step(@NotNull MinuteProgression progression, int step) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive, was: " + step + ".");
        return MinuteProgression.fromClosedRange(progression.getFirst(), progression.getLast(), progression.getStep() > 0 ? step : -step);
    }

    /**
     * Returns a second progression that goes over the same range with the given step.
     */
    @NotNull
    public static SecondProgression step(@NotNull SecondProgression progression, int step) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive, was: " + step + ".");
        return SecondProgression.fromClosedRange(progression.getFirst(), progression.getLast(), progression.getStep() > 0 ? step : -step);
    }

    /**
     * Returns a millisecond progression that goes over the same range with the given step.
     */
    @NotNull
    public static MillisecondProgression step(@NotNull MillisecondProgression progression, int step) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive, was: " + step + ".");
        return MillisecondProgression.fromClosedRange(progression.getFirst(), progression.getLast(), progression.getStep() > 0 ? step : -step);
    }
}