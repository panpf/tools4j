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

package com.github.panpf.tools4j.ranges.ktx

import com.github.panpf.tools4j.iterable.*
import com.github.panpf.tools4j.iterable.CharProgression
import com.github.panpf.tools4j.iterable.IntProgression
import com.github.panpf.tools4j.iterable.LongProgression
import com.github.panpf.tools4j.ranges.*
import java.util.*


/*
 * Range related extension methods or properties
 */


/* ******************************************* Byte Range *******************************************/


/**
 * Create a positive-order byte ranges
 */
inline fun Byte.rangeTo(endInclusive: Byte, step: Int): IntProgression = Rangex.rangeTo(this, endInclusive, step)

/**
 * Create a positive-order byte ranges that does not contain [end]
 */
inline fun Byte.until(end: Byte, step: Int): IntProgression = Rangex.until(this, end, step)


/* ******************************************* Short Range *******************************************/


/**
 * Create a positive-order short ranges
 */
inline fun Short.rangeTo(endInclusive: Short, step: Int): IntProgression = Rangex.rangeTo(this, endInclusive, step)

/**
 * Create a positive-order short ranges that does not contain [end]
 */
inline fun Short.until(end: Short, step: Int): IntProgression = Rangex.until(this, end, step)


/* ******************************************* Int Range *******************************************/


/**
 * Create a positive-order int ranges
 */
inline fun Int.rangeTo(endInclusive: Int, step: Int): IntProgression = Rangex.rangeTo(this, endInclusive, step)

/**
 * Create a positive-order int ranges that does not contain [end]
 */
inline fun Int.until(end: Int, step: Int): IntProgression = Rangex.until(this, end, step)


/* ******************************************* Long Range *******************************************/


/**
 * Create a positive-order long ranges
 */
inline fun Long.rangeTo(endInclusive: Long, step: Long): LongProgression = Rangex.rangeTo(this, endInclusive, step)

/**
 * Create a positive-order long ranges that does not contain [end]
 */
inline fun Long.until(end: Long, step: Long): LongProgression = Rangex.until(this, end, step)


/* ******************************************* Char Range *******************************************/


/**
 * Create a positive-order char ranges
 */
inline fun Char.rangeTo(endInclusive: Char, step: Int): CharProgression = Rangex.rangeTo(this, endInclusive, step)

/**
 * Create a positive-order char ranges that does not contain [end]
 */
inline fun Char.until(end: Char, step: Int): CharProgression = Rangex.until(this, end, step) /* ******************************************* reversed *******************************************/

/**
 * Returns a progression that goes over the same range in the opposite direction with the same step.
 */
inline fun IntProgression.reversed(): IntProgression = Rangex.reversed(this)

/**
 * Returns a progression that goes over the same range in the opposite direction with the same step.
 */
inline fun LongProgression.reversed(): LongProgression = Rangex.reversed(this)

/**
 * Returns a progression that goes over the same range in the opposite direction with the same step.
 */
inline fun CharProgression.reversed(): CharProgression = Rangex.reversed(this)


/* ******************************************* step *******************************************/

/**
 * Returns a progression that goes over the same range with the given step.
 */
inline fun IntProgression.step(step: Int): IntProgression = Rangex.step(this, step)

/**
 * Returns a progression that goes over the same range with the given step.
 */
inline fun LongProgression.step(step: Long): LongProgression = Rangex.step(this, step)

/**
 * Returns a progression that goes over the same range with the given step.
 */
inline fun CharProgression.step(step: Int): CharProgression = Rangex.step(this, step)


/* ******************************************* Year range ******************************************* */


/**
 * Create a positive-order year ranges
 */
inline infix fun Date.yearRangeTo(endInclusive: Date): YearRange = Rangex.yearRangeTo(this, endInclusive)

/**
 * Create a positive-order year range that does not contain [end]
 */
inline infix fun Date.yearUntil(end: Date): YearRange = Rangex.yearUntil(this, end)

/**
 * Create a reversed year range
 */
inline infix fun Date.yearDownTo(endInclusive: Date): YearProgression = Rangex.yearDownTo(this, endInclusive)

/**
 * Create a positive-order year ranges
 */
inline fun Date.yearRangeTo(endInclusive: Date, step: Int): YearProgression = Rangex.yearRangeTo(this, endInclusive, step)

/**
 * Create a positive-order year range that does not contain [end]
 */
inline fun Date.yearUntil(end: Date, step: Int): YearProgression = Rangex.yearUntil(this, end, step)


/* ******************************************* Month range ******************************************* */


/**
 * Create a positive-order month ranges
 */
inline infix fun Date.monthRangeTo(endInclusive: Date): MonthRange = Rangex.monthRangeTo(this, endInclusive)

/**
 * Create a positive-order month range that does not contain [end]
 */
inline infix fun Date.monthUntil(end: Date): MonthRange = Rangex.monthUntil(this, end)

/**
 * Create a reversed month range
 */
inline infix fun Date.monthDownTo(endInclusive: Date): MonthProgression = Rangex.monthDownTo(this, endInclusive)

/**
 * Create a positive-order month ranges
 */
inline fun Date.monthRangeTo(endInclusive: Date, step: Int): MonthProgression = Rangex.monthRangeTo(this, endInclusive, step)

/**
 * Create a positive-order month range that does not contain [end]
 */
inline fun Date.monthUntil(end: Date, step: Int): MonthProgression = Rangex.monthUntil(this, end, step)


/* ******************************************* Day range ******************************************* */


/**
 * Create a positive-order day ranges
 */
inline infix fun Date.dayRangeTo(endInclusive: Date): DayRange = Rangex.dayRangeTo(this, endInclusive)

/**
 * Create a positive-order day range that does not contain [end]
 */
inline infix fun Date.dayUntil(end: Date): DayRange = Rangex.dayUntil(this, end)

/**
 * Create a reversed day range
 */
inline infix fun Date.dayDownTo(endInclusive: Date): DayProgression = Rangex.dayDownTo(this, endInclusive)

/**
 * Create a positive-order day ranges
 */
inline fun Date.dayRangeTo(endInclusive: Date, step: Int): DayProgression = Rangex.dayRangeTo(this, endInclusive, step)

/**
 * Create a positive-order day range that does not contain [end]
 */
inline fun Date.dayUntil(end: Date, step: Int): DayProgression = Rangex.dayUntil(this, end, step)


/* ******************************************* Hour range ******************************************* */


/**
 * Create a positive-order hour ranges
 */
inline infix fun Date.hourRangeTo(endInclusive: Date): HourRange = Rangex.hourRangeTo(this, endInclusive)

/**
 * Create a positive-order hour range that does not contain [end]
 */
inline infix fun Date.hourUntil(end: Date): HourRange = Rangex.hourUntil(this, end)

/**
 * Create a reversed hour range
 */
inline infix fun Date.hourDownTo(endInclusive: Date): HourProgression = Rangex.hourDownTo(this, endInclusive)

/**
 * Create a positive-order hour ranges
 */
inline fun Date.hourRangeTo(endInclusive: Date, step: Int): HourProgression = Rangex.hourRangeTo(this, endInclusive, step)

/**
 * Create a positive-order hour range that does not contain [end]
 */
inline fun Date.hourUntil(end: Date, step: Int): HourProgression = Rangex.hourUntil(this, end, step)


/* ******************************************* Minute range ******************************************* */


/**
 * Create a positive-order minute ranges
 */
inline infix fun Date.minuteRangeTo(endInclusive: Date): MinuteRange = Rangex.minuteRangeTo(this, endInclusive)

/**
 * Create a positive-order minute range that does not contain [end]
 */
inline infix fun Date.minuteUntil(end: Date): MinuteRange = Rangex.minuteUntil(this, end)

/**
 * Create a reversed minute range
 */
inline infix fun Date.minuteDownTo(endInclusive: Date): MinuteProgression = Rangex.minuteDownTo(this, endInclusive)

/**
 * Create a positive-order minute ranges
 */
inline fun Date.minuteRangeTo(endInclusive: Date, step: Int): MinuteProgression = Rangex.minuteRangeTo(this, endInclusive, step)

/**
 * Create a positive-order minute range that does not contain [end]
 */
inline fun Date.minuteUntil(end: Date, step: Int): MinuteProgression = Rangex.minuteUntil(this, end, step)


/* ******************************************* Second range ******************************************* */


/**
 * Create a positive-order second ranges
 */
inline infix fun Date.secondRangeTo(endInclusive: Date): SecondRange = Rangex.secondRangeTo(this, endInclusive)

/**
 * Create a positive-order second range that does not contain [end]
 */
inline infix fun Date.secondUntil(end: Date): SecondRange = Rangex.secondUntil(this, end)

/**
 * Create a reversed second range
 */
inline infix fun Date.secondDownTo(endInclusive: Date): SecondProgression = Rangex.secondDownTo(this, endInclusive)

/**
 * Create a positive-order second ranges
 */
inline fun Date.secondRangeTo(endInclusive: Date, step: Int): SecondProgression = Rangex.secondRangeTo(this, endInclusive, step)

/**
 * Create a positive-order second range that does not contain [end]
 */
inline fun Date.secondUntil(end: Date, step: Int): SecondProgression = Rangex.secondUntil(this, end, step)


/* ******************************************* Millisecond range ******************************************* */


/**
 * Create a positive-order millisecond ranges
 */
inline infix fun Date.millisecondRangeTo(endInclusive: Date): MillisecondRange = Rangex.millisecondRangeTo(this, endInclusive)

/**
 * Create a positive-order millisecond range that does not contain [end]
 */
inline infix fun Date.millisecondUntil(end: Date): MillisecondRange = Rangex.millisecondUntil(this, end)

/**
 * Create a reversed millisecond range
 */
inline infix fun Date.millisecondDownTo(endInclusive: Date): MillisecondProgression = Rangex.millisecondDownTo(this, endInclusive)

/**
 * Create a positive-order millisecond ranges
 */
inline fun Date.millisecondRangeTo(endInclusive: Date, step: Int): MillisecondProgression = Rangex.millisecondRangeTo(this, endInclusive, step)

/**
 * Create a positive-order millisecond range that does not contain [end]
 */
inline fun Date.millisecondUntil(end: Date, step: Int): MillisecondProgression = Rangex.millisecondUntil(this, end, step)