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

package com.github.panpf.tools4j.ranges;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Calendar;
import java.util.Date;

/**
 * Range tool method
 */
public class Rangex {

    private Rangex() {
    }


    private static int mod(int a, int b) {
        int mod = a % b;
        return mod >= 0 ? mod : mod + b;
    }

    private static long mod(long a, long b) {
        long mod = a % b;
        return mod >= 0L ? mod : mod + b;
    }


    private static int differenceModulo(int a, int b, int c) {
        return mod(mod(a, c) - mod(b, c), c);
    }

    private static long differenceModulo(long a, long b, long c) {
        return mod(mod(a, c) - mod(b, c), c);
    }

    public static int getProgressionLastElement(int start, int end, int step) {
        if (step > 0) {
            return end - differenceModulo(end, start, step);
        } else if (step < 0) {
            return end + differenceModulo(start, end, -step);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    public static long getProgressionLastElement(long start, long end, long step) {
        if (step > 0) {
            return end - differenceModulo(end, start, step);
        } else if (step < 0) {
            return end + differenceModulo(start, end, -step);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }


    /* ******************************************* Byte Range *******************************************/


    /**
     * Create a positive-order byte ranges
     */
    public static IntProgression rangeTo(byte start, byte endInclusive, int step) {
        return IntProgression.fromClosedRange(start, endInclusive, step);
    }

    /**
     * Create a positive-order byte range that does not contain [end]
     */
    public static IntProgression until(byte start, byte end, int step) {
        return IntProgression.fromClosedRange(start, end - 1, step);
    }

    /**
     * Create a reversed byte range
     */
    public static IntProgression downTo(byte start, byte endInclusive, int step) {
        return IntProgression.fromClosedRange(start, endInclusive, step);
    }

    /**
     * Create a reversed byte range that does not contain [end]
     */
    public static IntProgression downUntilTo(byte start, byte end, int step) {
        return IntProgression.fromClosedRange(start, end + 1, step);
    }

    /**
     * Create a reversed byte range that does not contain [end]
     */
    public static IntProgression downUntilTo(byte start, byte end) {
        return downUntilTo(start, end, -1);
    }


    /* ******************************************* Short Range *******************************************/


    /**
     * Create a positive-order short ranges
     */
    public static IntProgression rangeTo(short start, short endInclusive, int step) {
        return IntProgression.fromClosedRange(start, endInclusive, step);
    }

    /**
     * Create a positive-order short range that does not contain [end]
     */
    public static IntProgression until(short start, short end, int step) {
        return IntProgression.fromClosedRange(start, end - 1, step);
    }

    /**
     * Create a reversed short range
     */
    public static IntProgression downTo(short start, short endInclusive, int step) {
        return IntProgression.fromClosedRange(start, endInclusive, step);
    }

    /**
     * Create a reversed short range that does not contain [end]
     */
    public static IntProgression downUntilTo(short start, short end, int step) {
        return IntProgression.fromClosedRange(start, end + 1, step);
    }

    /**
     * Create a reversed short range that does not contain [end]
     */
    public static IntProgression downUntilTo(short start, short end) {
        return downUntilTo(start, end, -1);
    }


    /* ******************************************* Int Range *******************************************/


    /**
     * Create a positive-order int ranges
     */
    public static IntProgression rangeTo(int start, int endInclusive, int step) {
        return IntProgression.fromClosedRange(start, endInclusive, step);
    }

    /**
     * Create a positive-order int range that does not contain [end]
     */
    public static IntProgression until(int start, int end, int step) {
        return IntProgression.fromClosedRange(start, end - 1, step);
    }

    /**
     * Create a reversed int range
     */
    public static IntProgression downTo(int start, int endInclusive, int step) {
        return IntProgression.fromClosedRange(start, endInclusive, step);
    }

    /**
     * Create a reversed int range that does not contain [end]
     */
    public static IntProgression downUntilTo(int start, int end, int step) {
        return IntProgression.fromClosedRange(start, end + 1, step);
    }

    /**
     * Create a reversed int range that does not contain [end]
     */
    public static IntProgression downUntilTo(int start, int end) {
        return downUntilTo(start, end, -1);
    }


    /* ******************************************* Long Range *******************************************/


    /**
     * Create a positive-order long ranges
     */
    public static LongProgression rangeTo(long start, long endInclusive, long step) {
        return LongProgression.fromClosedRange(start, endInclusive, step);
    }

    /**
     * Create a positive-order long range that does not contain [end]
     */
    public static LongProgression until(long start, long end, long step) {
        return LongProgression.fromClosedRange(start, end - 1, step);
    }

    /**
     * Create a reversed long range
     */
    public static LongProgression downTo(long start, long endInclusive, long step) {
        return LongProgression.fromClosedRange(start, endInclusive, step);
    }

    /**
     * Create a reversed long range that does not contain [end]
     */
    public static LongProgression downUntilTo(long start, long end, long step) {
        return LongProgression.fromClosedRange(start, end + 1, step);
    }

    /**
     * Create a reversed long range that does not contain [end]
     */
    public static LongProgression downUntilTo(long start, long end) {
        return downUntilTo(start, end, -1);
    }


    /* ******************************************* Char Range *******************************************/


    /**
     * Create a positive-order char ranges
     */
    public static CharProgression rangeTo(char start, char endInclusive, int step) {
        return CharProgression.fromClosedRange(start, endInclusive, step);
    }

    /**
     * Create a positive-order char range that does not contain [end]
     */
    public static CharProgression until(char start, char end, int step) {
        return CharProgression.fromClosedRange(start, (char) Math.max(Math.min(end - 1, Character.MAX_VALUE), Character.MIN_VALUE), step);
    }

    /**
     * Create a reversed char range
     */
    public static CharProgression downTo(char start, char endInclusive, int step) {
        return CharProgression.fromClosedRange(start, endInclusive, step);
    }

    /**
     * Create a reversed char range that does not contain [end]
     */
    public static CharProgression downUntilTo(char start, char end, int step) {
        return CharProgression.fromClosedRange(start, (char) Math.max(Math.min(end + 1, Character.MAX_VALUE), Character.MIN_VALUE), step);
    }

    /**
     * Create a reversed char range that does not contain [end]
     */
    public static CharProgression downUntilTo(char start, char end) {
        return downUntilTo(start, end, -1);
    }


    /* ******************************************* reversed *******************************************/

    /**
     * Returns a progression that goes over the same range in the opposite direction with the same step.
     */
    @NotNull
    public static IntProgression reversed(@NotNull IntProgression progression) {
        return IntProgression.fromClosedRange(progression.getLast(), progression.getFirst(), -progression.getStep());
    }

    /**
     * Returns a progression that goes over the same range in the opposite direction with the same step.
     */
    @NotNull
    public static LongProgression reversed(@NotNull LongProgression progression) {
        return LongProgression.fromClosedRange(progression.getLast(), progression.getFirst(), -progression.getStep());
    }

    /**
     * Returns a progression that goes over the same range in the opposite direction with the same step.
     */
    @NotNull
    public static CharProgression reversed(@NotNull CharProgression progression) {
        return CharProgression.fromClosedRange(progression.getLast(), progression.getFirst(), -progression.getStep());
    }


    /* ******************************************* step *******************************************/

    /**
     * Returns a progression that goes over the same range with the given step.
     */
    @NotNull
    public static IntProgression step(@NotNull IntProgression progression, int step) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive, was: " + step + ".");
        return IntProgression.fromClosedRange(progression.getFirst(), progression.getLast(), progression.getStep() > 0 ? step : -step);
    }

    /**
     * Returns a progression that goes over the same range with the given step.
     */
    @NotNull
    public static LongProgression step(@NotNull LongProgression progression, long step) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive, was: " + step + ".");
        return LongProgression.fromClosedRange(progression.getFirst(), progression.getLast(), progression.getStep() > 0 ? step : -step);
    }

    /**
     * Returns a progression that goes over the same range with the given step.
     */
    @NotNull
    public static CharProgression step(@NotNull CharProgression progression, int step) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive, was: " + step + ".");
        return CharProgression.fromClosedRange(progression.getFirst(), progression.getLast(), progression.getStep() > 0 ? step : -step);
    }


    /* ******************************************* Year Range *******************************************/


    /**
     * Create a positive-order year ranges
     */
    @NotNull
    public static YearRange yearRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new YearRange(date, endInclusive, step);
    }

    /**
     * Create a positive-order year ranges
     */
    @NotNull
    public static YearRange yearRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return yearRangeTo(date, endInclusive, 1);
    }


    /**
     * Create a positive-order year range that does not contain [end]
     */
    @NotNull
    public static YearRange yearUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.YEAR, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new YearRange(date, endInclusiveDate, step);
    }

    /**
     * Create a positive-order year range that does not contain [end]
     */
    @NotNull
    public static YearRange yearUntil(@NotNull Date date, @NotNull Date end) {
        return yearUntil(date, end, 1);
    }


    /**
     * Create a reversed year range
     */
    @NotNull
    public static YearRange yearDownTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new YearRange(date, endInclusive, step);
    }

    /**
     * Create a reversed year range
     */
    @NotNull
    public static YearRange yearDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return yearDownTo(date, endInclusive, -1);
    }


    /**
     * Create a reversed year range that does not contain [end]
     */
    @NotNull
    public static YearRange yearDownUntilTo(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.YEAR, 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new YearRange(date, endInclusiveDate, step);
    }

    /**
     * Create a reversed year range that does not contain [end]
     */
    @NotNull
    public static YearRange yearDownUntilTo(@NotNull Date date, @NotNull Date end) {
        return yearDownUntilTo(date, end, -1);
    }


    /* ******************************************* Month Range *******************************************/


    /**
     * Create a positive-order month ranges
     */
    @NotNull
    public static MonthRange monthRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new MonthRange(date, endInclusive, step);
    }

    /**
     * Create a positive-order month ranges
     */
    @NotNull
    public static MonthRange monthRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return monthRangeTo(date, endInclusive, 1);
    }


    /**
     * Create a positive-order month range that does not contain [end]
     */
    @NotNull
    public static MonthRange monthUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MONTH, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MonthRange(date, endInclusiveDate, step);
    }

    /**
     * Create a positive-order month range that does not contain [end]
     */
    @NotNull
    public static MonthRange monthUntil(@NotNull Date date, @NotNull Date end) {
        return monthUntil(date, end, 1);
    }


    /**
     * Create a reversed month range
     */
    @NotNull
    public static MonthRange monthDownTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new MonthRange(date, endInclusive, step);
    }

    /**
     * Create a reversed month range
     */
    @NotNull
    public static MonthRange monthDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return monthDownTo(date, endInclusive, -1);
    }


    /**
     * Create a reversed month range that does not contain [end]
     */
    @NotNull
    public static MonthRange monthDownUntilTo(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MONTH, 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MonthRange(date, endInclusiveDate, step);
    }

    /**
     * Create a reversed month range that does not contain [end]
     */
    @NotNull
    public static MonthRange monthDownUntilTo(@NotNull Date date, @NotNull Date end) {
        return monthDownUntilTo(date, end, -1);
    }


    /* ******************************************* Day Range *******************************************/


    /**
     * Create a positive-order day ranges
     */
    @NotNull
    public static DayRange dayRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new DayRange(date, endInclusive, step);
    }

    /**
     * Create a positive-order day ranges
     */
    @NotNull
    public static DayRange dayRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return dayRangeTo(date, endInclusive, 1);
    }


    /**
     * Create a positive-order day range that does not contain [end]
     */
    @NotNull
    public static DayRange dayUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new DayRange(date, endInclusiveDate, step);
    }

    /**
     * Create a positive-order day range that does not contain [end]
     */
    @NotNull
    public static DayRange dayUntil(@NotNull Date date, @NotNull Date end) {
        return dayUntil(date, end, 1);
    }


    /**
     * Create a reversed day range
     */
    @NotNull
    public static DayRange dayDownTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new DayRange(date, endInclusive, step);
    }

    /**
     * Create a reversed day range
     */
    @NotNull
    public static DayRange dayDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return dayDownTo(date, endInclusive, -1);
    }


    /**
     * Create a reversed day range that does not contain [end]
     */
    @NotNull
    public static DayRange dayDownUntilTo(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new DayRange(date, endInclusiveDate, step);
    }

    /**
     * Create a reversed day range that does not contain [end]
     */
    @NotNull
    public static DayRange dayDownUntilTo(@NotNull Date date, @NotNull Date end) {
        return dayDownUntilTo(date, end, -1);
    }


    /* ******************************************* Hour Range *******************************************/


    /**
     * Create a positive-order hour ranges
     */
    @NotNull
    public static HourRange hourRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new HourRange(date, endInclusive, step);
    }

    /**
     * Create a positive-order hour ranges
     */
    @NotNull
    public static HourRange hourRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return hourRangeTo(date, endInclusive, 1);
    }


    /**
     * Create a positive-order hour range that does not contain [end]
     */
    @NotNull
    public static HourRange hourUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new HourRange(date, endInclusiveDate, step);
    }

    /**
     * Create a positive-order hour range that does not contain [end]
     */
    @NotNull
    public static HourRange hourUntil(@NotNull Date date, @NotNull Date end) {
        return hourUntil(date, end, 1);
    }


    /**
     * Create a reversed hour range
     */
    @NotNull
    public static HourRange hourDownTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new HourRange(date, endInclusive, step);
    }

    /**
     * Create a reversed hour range
     */
    @NotNull
    public static HourRange hourDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return hourDownTo(date, endInclusive, -1);
    }


    /**
     * Create a reversed hour range that does not contain [end]
     */
    @NotNull
    public static HourRange hourDownUntilTo(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new HourRange(date, endInclusiveDate, step);
    }

    /**
     * Create a reversed hour range that does not contain [end]
     */
    @NotNull
    public static HourRange hourDownUntilTo(@NotNull Date date, @NotNull Date end) {
        return hourDownUntilTo(date, end, -1);
    }


    /* ******************************************* Minute Range *******************************************/


    /**
     * Create a positive-order minute ranges
     */
    @NotNull
    public static MinuteRange minuteRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new MinuteRange(date, endInclusive, step);
    }

    /**
     * Create a positive-order minute ranges
     */
    @NotNull
    public static MinuteRange minuteRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return minuteRangeTo(date, endInclusive, 1);
    }


    /**
     * Create a positive-order minute range that does not contain [end]
     */
    @NotNull
    public static MinuteRange minuteUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MINUTE, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MinuteRange(date, endInclusiveDate, step);
    }

    /**
     * Create a positive-order minute range that does not contain [end]
     */
    @NotNull
    public static MinuteRange minuteUntil(@NotNull Date date, @NotNull Date end) {
        return minuteUntil(date, end, 1);
    }


    /**
     * Create a reversed minute range
     */
    @NotNull
    public static MinuteRange minuteDownTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new MinuteRange(date, endInclusive, step);
    }

    /**
     * Create a reversed minute range
     */
    @NotNull
    public static MinuteRange minuteDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return minuteDownTo(date, endInclusive, -1);
    }


    /**
     * Create a reversed minute range that does not contain [end]
     */
    @NotNull
    public static MinuteRange minuteDownUntilTo(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MINUTE, 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MinuteRange(date, endInclusiveDate, step);
    }

    /**
     * Create a reversed minute range that does not contain [end]
     */
    @NotNull
    public static MinuteRange minuteDownUntilTo(@NotNull Date date, @NotNull Date end) {
        return minuteDownUntilTo(date, end, -1);
    }


    /* ******************************************* Second Range *******************************************/


    /**
     * Create a positive-order second ranges
     */
    @NotNull
    public static SecondRange secondRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new SecondRange(date, endInclusive, step);
    }

    /**
     * Create a positive-order second ranges
     */
    @NotNull
    public static SecondRange secondRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return secondRangeTo(date, endInclusive, 1);
    }


    /**
     * Create a positive-order second range that does not contain [end]
     */
    @NotNull
    public static SecondRange secondUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.SECOND, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new SecondRange(date, endInclusiveDate, step);
    }

    /**
     * Create a positive-order second range that does not contain [end]
     */
    @NotNull
    public static SecondRange secondUntil(@NotNull Date date, @NotNull Date end) {
        return secondUntil(date, end, 1);
    }


    /**
     * Create a reversed second range
     */
    @NotNull
    public static SecondRange secondDownTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new SecondRange(date, endInclusive, step);
    }

    /**
     * Create a reversed second range
     */
    @NotNull
    public static SecondRange secondDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return secondDownTo(date, endInclusive, -1);
    }


    /**
     * Create a reversed second range that does not contain [end]
     */
    @NotNull
    public static SecondRange secondDownUntilTo(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.SECOND, 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new SecondRange(date, endInclusiveDate, step);
    }

    /**
     * Create a reversed second range that does not contain [end]
     */
    @NotNull
    public static SecondRange secondDownUntilTo(@NotNull Date date, @NotNull Date end) {
        return secondDownUntilTo(date, end, -1);
    }


    /* ******************************************* Millisecond Range *******************************************/


    /**
     * Create a positive-order millisecond ranges
     */
    @NotNull
    public static MillisecondRange millisecondRangeTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new MillisecondRange(date, endInclusive, step);
    }

    /**
     * Create a positive-order millisecond ranges
     */
    @NotNull
    public static MillisecondRange millisecondRangeTo(@NotNull Date date, @NotNull Date endInclusive) {
        return millisecondRangeTo(date, endInclusive, 1);
    }


    /**
     * Create a positive-order millisecond range that does not contain [end]
     */
    @NotNull
    public static MillisecondRange millisecondUntil(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MILLISECOND, -1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MillisecondRange(date, endInclusiveDate, step);
    }

    /**
     * Create a positive-order millisecond range that does not contain [end]
     */
    @NotNull
    public static MillisecondRange millisecondUntil(@NotNull Date date, @NotNull Date end) {
        return millisecondUntil(date, end, 1);
    }


    /**
     * Create a reversed millisecond range
     */
    @NotNull
    public static MillisecondRange millisecondDownTo(@NotNull Date date, @NotNull Date endInclusive, int step) {
        return new MillisecondRange(date, endInclusive, step);
    }

    /**
     * Create a reversed millisecond range
     */
    @NotNull
    public static MillisecondRange millisecondDownTo(@NotNull Date date, @NotNull Date endInclusive) {
        return millisecondDownTo(date, endInclusive, -1);
    }


    /**
     * Create a reversed millisecond range that does not contain [end]
     */
    @NotNull
    public static MillisecondRange millisecondDownUntilTo(@NotNull Date date, @NotNull Date end, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime());
        calendar.add(Calendar.MILLISECOND, 1);
        Date endInclusiveDate = new Date(calendar.getTimeInMillis());
        return new MillisecondRange(date, endInclusiveDate, step);
    }

    /**
     * Create a reversed millisecond range that does not contain [end]
     */
    @NotNull
    public static MillisecondRange millisecondDownUntilTo(@NotNull Date date, @NotNull Date end) {
        return millisecondDownUntilTo(date, end, -1);
    }


    /* ******************************************* require *******************************************/


    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static byte requireInRange(byte value, byte minValue, byte maxValue, @NotNull String paramName) {
        if (Rangex.in(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The byte parameter '%s' value is %d, must be >= %d && <= %d", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static byte requireInRange(byte value, byte minValue, byte maxValue) {
        return requireInRange(value, minValue, maxValue, "unknown");
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static short requireInRange(short value, short minValue, short maxValue, @NotNull String paramName) {
        if (Rangex.in(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The short parameter '%s' value is %d, must be >= %d && <= %d", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static short requireInRange(short value, short minValue, short maxValue) {
        return requireInRange(value, minValue, maxValue, "unknown");
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static int requireInRange(int value, int minValue, int maxValue, @NotNull String paramName) {
        if (Rangex.in(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The int parameter '%s' value is %d, must be >= %d && <= %d", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static int requireInRange(int value, int minValue, int maxValue) {
        return requireInRange(value, minValue, maxValue, "unknown");
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static long requireInRange(long value, long minValue, long maxValue, @NotNull String paramName) {
        if (Rangex.in(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The long parameter '%s' value is %d, must be >= %d && <= %d", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static long requireInRange(long value, long minValue, long maxValue) {
        return requireInRange(value, minValue, maxValue, "unknown");
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static float requireInRange(float value, float minValue, float maxValue, @NotNull String paramName) {
        if (Rangex.in(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The float parameter '%s' value is %s, must be >= %s && <= %s", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static float requireInRange(float value, float minValue, float maxValue) {
        return requireInRange(value, minValue, maxValue, "unknown");
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static double requireInRange(double value, double minValue, double maxValue, @NotNull String paramName) {
        if (Rangex.in(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The double parameter '%s' value is %s, must be >= %s && <= %s", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static double requireInRange(double value, double minValue, double maxValue) {
        return requireInRange(value, minValue, maxValue, "unknown");
    }


    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static byte requireNotInRange(byte value, byte minValue, byte maxValue, @NotNull String paramName) {
        if (Rangex.notIn(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The byte parameter '%s' value is %d, must be < %d || > %d", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static byte requireNotInRange(byte value, byte minValue, byte maxValue) {
        return requireNotInRange(value, minValue, maxValue, "unknown");
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static short requireNotInRange(short value, short minValue, short maxValue, @NotNull String paramName) {
        if (Rangex.notIn(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The short parameter '%s' value is %d, must be < %d || > %d", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static short requireNotInRange(short value, short minValue, short maxValue) {
        return requireNotInRange(value, minValue, maxValue, "unknown");
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static int requireNotInRange(int value, int minValue, int maxValue, @NotNull String paramName) {
        if (Rangex.notIn(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The int parameter '%s' value is %d, must be < %d || > %d", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static int requireNotInRange(int value, int minValue, int maxValue) {
        return requireNotInRange(value, minValue, maxValue, "unknown");
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static long requireNotInRange(long value, long minValue, long maxValue, @NotNull String paramName) {
        if (Rangex.notIn(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The long parameter '%s' value is %d, must be < %d || > %d", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static long requireNotInRange(long value, long minValue, long maxValue) {
        return requireNotInRange(value, minValue, maxValue, "unknown");
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static float requireNotInRange(float value, float minValue, float maxValue, @NotNull String paramName) {
        if (Rangex.notIn(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The float parameter '%s' value is %s, must be < %s || > %s", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static float requireNotInRange(float value, float minValue, float maxValue) {
        return requireNotInRange(value, minValue, maxValue, "unknown");
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static double requireNotInRange(double value, double minValue, double maxValue, @NotNull String paramName) {
        if (Rangex.notIn(value, minValue, maxValue)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The double parameter '%s' value is %s, must be < %s || > %s", paramName, value, minValue, maxValue));
        }
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static double requireNotInRange(double value, double minValue, double maxValue) {
        return requireNotInRange(value, minValue, maxValue, "unknown");
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    /* ******************************************* Byte Range *******************************************/


    /**
     * Create a positive-order byte ranges
     */
    public static IntRange rangeTo(byte start, byte endInclusive) {
        return new IntRange(start, endInclusive);
    }

    /**
     * Create a positive-order byte range that does not contain [end]
     */
    public static IntRange until(byte start, byte end) {
        return new IntRange(start, end - 1);
    }

    /**
     * Create a reversed byte range
     */
    public static IntProgression downTo(byte start, byte endInclusive) {
        return downTo(start, endInclusive, -1);
    }


    /* ******************************************* Short Range *******************************************/


    /**
     * Create a positive-order short ranges
     */
    public static IntRange rangeTo(short start, short endInclusive) {
        return new IntRange(start, endInclusive);
    }

    /**
     * Create a positive-order short range that does not contain [end]
     */
    public static IntRange until(short start, short end) {
        return new IntRange(start, end - 1);
    }

    /**
     * Create a reversed short range
     */
    public static IntProgression downTo(short start, short endInclusive) {
        return downTo(start, endInclusive, -1);
    }


    /* ******************************************* Int Range *******************************************/


    /**
     * Create a positive-order int ranges
     */
    public static IntRange rangeTo(int start, int endInclusive) {
        return new IntRange(start, endInclusive);
    }

    /**
     * Create a positive-order int range that does not contain [end]
     */
    public static IntRange until(int start, int end) {
        return new IntRange(start, end - 1);
    }

    /**
     * Create a reversed int range
     */
    public static IntProgression downTo(int start, int endInclusive) {
        return downTo(start, endInclusive, -1);
    }


    /* ******************************************* Long Range *******************************************/


    /**
     * Create a positive-order long ranges
     */
    public static LongRange rangeTo(long start, long endInclusive) {
        return new LongRange(start, endInclusive);
    }

    /**
     * Create a positive-order long range that does not contain [end]
     */
    public static LongRange until(long start, long end) {
        return new LongRange(start, end - 1);
    }

    /**
     * Create a reversed long range
     */
    public static LongProgression downTo(long start, long endInclusive) {
        return downTo(start, endInclusive, -1);
    }


    /* ******************************************* Float Range *******************************************/


    /**
     * Create a positive-order float ranges
     */
    public static ClosedFloatingPointRange<Float> rangeTo(float start, float endInclusive) {
        return new ClosedFloatRange(start, endInclusive);
    }


    /* ******************************************* Double Range *******************************************/


    /**
     * Create a positive-order double ranges
     */
    public static ClosedFloatingPointRange<Double> rangeTo(double start, double endInclusive) {
        return new ClosedDoubleRange(start, endInclusive);
    }


    /* ******************************************* Char Range *******************************************/


    /**
     * Create a positive-order char ranges
     */
    public static CharRange rangeTo(char start, char endInclusive) {
        return new CharRange(start, endInclusive);
    }

    /**
     * Create a positive-order char range that does not contain [end]
     */
    public static CharRange until(char start, char end) {
        return new CharRange(start, (char) Math.max(Math.min(end - 1, Character.MAX_VALUE), Character.MIN_VALUE));
    }

    /**
     * Create a reversed char range
     */
    public static CharProgression downTo(char start, char endInclusive) {
        return downTo(start, endInclusive, -1);
    }


    /* ******************************************* in *******************************************/

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean in(byte value, byte minValue, byte maxValue) {
        return value >= minValue && value <= maxValue;
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean in(short value, short minValue, short maxValue) {
        return value >= minValue && value <= maxValue;
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean in(int value, int minValue, int maxValue) {
        return value >= minValue && value <= maxValue;
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean in(long value, long minValue, long maxValue) {
        return value >= minValue && value <= maxValue;
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean in(float value, float minValue, float maxValue) {
        return value >= minValue && value <= maxValue;
    }

    /**
     * If [value] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean in(double value, double minValue, double maxValue) {
        return value >= minValue && value <= maxValue;
    }


    /* ******************************************* notIn *******************************************/

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean notIn(byte value, byte minValue, byte maxValue) {
        return value < minValue || value > maxValue;
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean notIn(short value, short minValue, short maxValue) {
        return value < minValue || value > maxValue;
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean notIn(int value, int minValue, int maxValue) {
        return value < minValue || value > maxValue;
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean notIn(long value, long minValue, long maxValue) {
        return value < minValue || value > maxValue;
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean notIn(float value, float minValue, float maxValue) {
        return value < minValue || value > maxValue;
    }

    /**
     * If [value] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
     */
    public static boolean notIn(double value, double minValue, double maxValue) {
        return value < minValue || value > maxValue;
    }


    /* ******************************************* coerceAtLeast *******************************************/


    /**
     * Ensures that this value is not less than the specified [minimumValue].
     *
     * @return this value if it's greater than or equal to the [minimumValue] or the [minimumValue] otherwise.
     */
    public static <T extends Comparable<T>> T coerceAtLeast(T self, T minimumValue) {
        return self.compareTo(minimumValue) < 0 ? minimumValue : self;
    }

    /**
     * Ensures that this value is not less than the specified [minimumValue].
     *
     * @return this value if it's greater than or equal to the [minimumValue] or the [minimumValue] otherwise.
     */
    public static byte coerceAtLeast(byte self, byte minimumValue) {
        return self < minimumValue ? minimumValue : self;
    }

    /**
     * Ensures that this value is not less than the specified [minimumValue].
     *
     * @return this value if it's greater than or equal to the [minimumValue] or the [minimumValue] otherwise.
     */
    public static short coerceAtLeast(short self, short minimumValue) {
        return self < minimumValue ? minimumValue : self;
    }

    /**
     * Ensures that this value is not less than the specified [minimumValue].
     *
     * @return this value if it's greater than or equal to the [minimumValue] or the [minimumValue] otherwise.
     */
    public static int coerceAtLeast(int self, int minimumValue) {
        return self < minimumValue ? minimumValue : self;
    }

    /**
     * Ensures that this value is not less than the specified [minimumValue].
     *
     * @return this value if it's greater than or equal to the [minimumValue] or the [minimumValue] otherwise.
     */
    public static long coerceAtLeast(long self, long minimumValue) {
        return self < minimumValue ? minimumValue : self;
    }

    /**
     * Ensures that this value is not less than the specified [minimumValue].
     *
     * @return this value if it's greater than or equal to the [minimumValue] or the [minimumValue] otherwise.
     */
    public static float coerceAtLeast(float self, float minimumValue) {
        return self < minimumValue ? minimumValue : self;
    }

    /**
     * Ensures that this value is not less than the specified [minimumValue].
     *
     * @return this value if it's greater than or equal to the [minimumValue] or the [minimumValue] otherwise.
     */
    public static double coerceAtLeast(double self, double minimumValue) {
        return self < minimumValue ? minimumValue : self;
    }


    /* ******************************************* coerceAtMost *******************************************/


    /**
     * Ensures that this value is not greater than the specified [maximumValue].
     *
     * @return this value if it's less than or equal to the [maximumValue] or the [maximumValue] otherwise.
     */
    public static <T extends Comparable<T>> T coerceAtMost(T self, T maximumValue) {
        return self.compareTo(maximumValue) > 0 ? maximumValue : self;
    }

    /**
     * Ensures that this value is not greater than the specified [maximumValue].
     *
     * @return this value if it's less than or equal to the [maximumValue] or the [maximumValue] otherwise.
     */
    public static byte coerceAtMost(byte self, byte maximumValue) {
        return self > maximumValue ? maximumValue : self;
    }

    /**
     * Ensures that this value is not greater than the specified [maximumValue].
     *
     * @return this value if it's less than or equal to the [maximumValue] or the [maximumValue] otherwise.
     */
    public static short coerceAtMost(short self, short maximumValue) {
        return self > maximumValue ? maximumValue : self;
    }

    /**
     * Ensures that this value is not greater than the specified [maximumValue].
     *
     * @return this value if it's less than or equal to the [maximumValue] or the [maximumValue] otherwise.
     */
    public static int coerceAtMost(int self, int maximumValue) {
        return self > maximumValue ? maximumValue : self;
    }

    /**
     * Ensures that this value is not greater than the specified [maximumValue].
     *
     * @return this value if it's less than or equal to the [maximumValue] or the [maximumValue] otherwise.
     */
    public static long coerceAtMost(long self, long maximumValue) {
        return self > maximumValue ? maximumValue : self;
    }

    /**
     * Ensures that this value is not greater than the specified [maximumValue].
     *
     * @return this value if it's less than or equal to the [maximumValue] or the [maximumValue] otherwise.
     */
    public static float coerceAtMost(float self, float maximumValue) {
        return self > maximumValue ? maximumValue : self;
    }

    /**
     * Ensures that this value is not greater than the specified [maximumValue].
     *
     * @return this value if it's less than or equal to the [maximumValue] or the [maximumValue] otherwise.
     */
    public static double coerceAtMost(double self, double maximumValue) {
        return self > maximumValue ? maximumValue : self;
    }


    /* ******************************************* coerceIn *******************************************/

    /**
     * Ensures that this value lies in the specified range [minimumValue]..[maximumValue].
     *
     * @return this value if it's in the range, or [minimumValue] if this value is less than [minimumValue], or [maximumValue] if this value is greater than [maximumValue].
     */
    public static <T extends Comparable<T>> T coerceIn(@NotNull T self, @Nullable T minimumValue, @Nullable T maximumValue) {
        if (minimumValue != null && maximumValue != null) {
            if (minimumValue.compareTo(maximumValue) > 0)
                throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + ".");
            if (self.compareTo(minimumValue) < 0) return minimumValue;
            if (self.compareTo(maximumValue) > 0) return maximumValue;
        } else {
            if (minimumValue != null && self.compareTo(minimumValue) < 0) return minimumValue;
            if (maximumValue != null && self.compareTo(maximumValue) > 0) return maximumValue;
        }
        return self;
    }

    /**
     * Ensures that this value lies in the specified range [minimumValue]..[maximumValue].
     *
     * @return this value if it's in the range, or [minimumValue] if this value is less than [minimumValue], or [maximumValue] if this value is greater than [maximumValue].
     */
    public static byte coerceIn(byte self, byte minimumValue, byte maximumValue) {
        if (minimumValue > maximumValue)
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + ".");
        if (self < minimumValue) return minimumValue;
        if (self > maximumValue) return maximumValue;
        return self;
    }

    /**
     * Ensures that this value lies in the specified range [minimumValue]..[maximumValue].
     *
     * @return this value if it's in the range, or [minimumValue] if this value is less than [minimumValue], or [maximumValue] if this value is greater than [maximumValue].
     */
    public static short coerceIn(short self, short minimumValue, short maximumValue) {
        if (minimumValue > maximumValue)
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + ".");
        if (self < minimumValue) return minimumValue;
        if (self > maximumValue) return maximumValue;
        return self;
    }

    /**
     * Ensures that this value lies in the specified range [minimumValue]..[maximumValue].
     *
     * @return this value if it's in the range, or [minimumValue] if this value is less than [minimumValue], or [maximumValue] if this value is greater than [maximumValue].
     */
    public static int coerceIn(int self, int minimumValue, int maximumValue) {
        if (minimumValue > maximumValue)
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + ".");
        if (self < minimumValue) return minimumValue;
        if (self > maximumValue) return maximumValue;
        return self;
    }

    /**
     * Ensures that this value lies in the specified range [minimumValue]..[maximumValue].
     *
     * @return this value if it's in the range, or [minimumValue] if this value is less than [minimumValue], or [maximumValue] if this value is greater than [maximumValue].
     */
    public static long coerceIn(long self, long minimumValue, long maximumValue) {
        if (minimumValue > maximumValue)
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + ".");
        if (self < minimumValue) return minimumValue;
        if (self > maximumValue) return maximumValue;
        return self;
    }

    /**
     * Ensures that this value lies in the specified range [minimumValue]..[maximumValue].
     *
     * @return this value if it's in the range, or [minimumValue] if this value is less than [minimumValue], or [maximumValue] if this value is greater than [maximumValue].
     */
    public static float coerceIn(float self, float minimumValue, float maximumValue) {
        if (minimumValue > maximumValue)
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + ".");
        if (self < minimumValue) return minimumValue;
        if (self > maximumValue) return maximumValue;
        return self;
    }

    /**
     * Ensures that this value lies in the specified range [minimumValue]..[maximumValue].
     *
     * @return this value if it's in the range, or [minimumValue] if this value is less than [minimumValue], or [maximumValue] if this value is greater than [maximumValue].
     */
    public static double coerceIn(double self, double minimumValue, double maximumValue) {
        if (minimumValue > maximumValue)
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + ".");
        if (self < minimumValue) return minimumValue;
        if (self > maximumValue) return maximumValue;
        return self;
    }

    /**
     * Ensures that this value lies in the specified [range].
     *
     * @return this value if it's in the [range], or `range.start` if this value is less than `range.start`, or `range.endInclusive` if this value is greater than `range.endInclusive`.
     */
    public static <T extends Comparable<T>> T coerceIn(@NotNull T self, ClosedFloatingPointRange<T> range) {
        if (range.isEmpty())
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + ".");
        if (range.lessThanOrEquals(self, range.getStart()) && !range.lessThanOrEquals(range.getStart(), self)) {
            // this < start equiv to this <= start && !(this >= start)
            return range.getStart();
        } else if (range.lessThanOrEquals(range.getEndInclusive(), self) && !range.lessThanOrEquals(self, range.getEndInclusive())) {
            // this > end equiv to this >= end && !(this <= end)
            return range.getEndInclusive();
        } else {
            return self;
        }
    }

    /**
     * Ensures that this value lies in the specified [range].
     *
     * @return this value if it's in the [range], or `range.start` if this value is less than `range.start`, or `range.endInclusive` if this value is greater than `range.endInclusive`.
     */
    public static <T extends Comparable<T>> T coerceIn(@NotNull T self, @NotNull ClosedRange<T> range) {
        if (range instanceof ClosedFloatingPointRange) {
            return coerceIn(self, (ClosedFloatingPointRange<T>) range);
        }
        if (range.isEmpty())
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + ".");
        if (self.compareTo(range.getStart()) < 0) {
            return range.getStart();
        } else if (self.compareTo(range.getEndInclusive()) > 0) {
            return range.getEndInclusive();
        } else {
            return self;
        }
    }

    /**
     * Ensures that this value lies in the specified [range].
     *
     * @return this value if it's in the [range], or `range.start` if this value is less than `range.start`, or `range.endInclusive` if this value is greater than `range.endInclusive`.
     */
    public static int coerceIn(int self, ClosedRange<Integer> range) {
//        if (range instanceof ClosedFloatingPointRange) {
//            return coerceIn(self, (ClosedFloatingPointRange<Integer>) range);
//        }
        if (range.isEmpty())
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + ".");
        if (self < range.getStart()) {
            return range.getStart();
        } else if (self > range.getEndInclusive()) {
            return range.getEndInclusive();
        } else {
            return self;
        }
    }

    /**
     * Ensures that this value lies in the specified [range].
     *
     * @return this value if it's in the [range], or `range.start` if this value is less than `range.start`, or `range.endInclusive` if this value is greater than `range.endInclusive`.
     */
    public static long coerceIn(long self, ClosedRange<Long> range) {
//        if (range instanceof ClosedFloatingPointRange) {
//            return coerceIn(self, (ClosedFloatingPointRange<Long>) range);
//        }
        if (range.isEmpty())
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + ".");
        if (self < range.getStart()) {
            return range.getStart();
        } else if (self > range.getEndInclusive()) {
            return range.getEndInclusive();
        } else {
            return self;
        }
    }
}
