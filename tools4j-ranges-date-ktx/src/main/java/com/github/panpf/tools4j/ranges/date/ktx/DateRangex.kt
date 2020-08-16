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

package com.github.panpf.tools4j.ranges.date.ktx

import com.github.panpf.tools4j.ranges.date.*
import java.util.*


/*
 * Date Time Range related extension methods or properties
 */


/* ******************************************* Year range ******************************************* */


/**
 * Create a positive-order year ranges
 */
inline infix fun Date.yearRangeTo(endInclusive: Date): YearRange = DateRangex.yearRangeTo(this, endInclusive)

/**
 * Create a positive-order year range that does not contain [end]
 */
inline infix fun Date.yearUntil(end: Date): YearRange = DateRangex.yearUntil(this, end)

/**
 * Create a reversed year range
 */
inline infix fun Date.yearDownTo(endInclusive: Date): YearProgression = DateRangex.yearDownTo(this, endInclusive)

/**
 * Create a positive-order year ranges
 */
inline fun Date.yearRangeTo(endInclusive: Date, step: Int): YearProgression = DateRangex.yearRangeTo(this, endInclusive, step)

/**
 * Create a positive-order year range that does not contain [end]
 */
inline fun Date.yearUntil(end: Date, step: Int): YearProgression = DateRangex.yearUntil(this, end, step)


/* ******************************************* Month range ******************************************* */


/**
 * Create a positive-order month ranges
 */
inline infix fun Date.monthRangeTo(endInclusive: Date): MonthRange = DateRangex.monthRangeTo(this, endInclusive)

/**
 * Create a positive-order month range that does not contain [end]
 */
inline infix fun Date.monthUntil(end: Date): MonthRange = DateRangex.monthUntil(this, end)

/**
 * Create a reversed month range
 */
inline infix fun Date.monthDownTo(endInclusive: Date): MonthProgression = DateRangex.monthDownTo(this, endInclusive)

/**
 * Create a positive-order month ranges
 */
inline fun Date.monthRangeTo(endInclusive: Date, step: Int): MonthProgression = DateRangex.monthRangeTo(this, endInclusive, step)

/**
 * Create a positive-order month range that does not contain [end]
 */
inline fun Date.monthUntil(end: Date, step: Int): MonthProgression = DateRangex.monthUntil(this, end, step)


/* ******************************************* Day range ******************************************* */


/**
 * Create a positive-order day ranges
 */
inline infix fun Date.dayOfMonthRangeTo(endInclusive: Date): DayOfMonthRange = DateRangex.dayOfMonthRangeTo(this, endInclusive)

/**
 * Create a positive-order day range that does not contain [end]
 */
inline infix fun Date.dayOfMonthUntil(end: Date): DayOfMonthRange = DateRangex.dayOfMonthUntil(this, end)

/**
 * Create a reversed day range
 */
inline infix fun Date.dayOfMonthDownTo(endInclusive: Date): DayOfMonthProgression = DateRangex.dayOfMonthDownTo(this, endInclusive)

/**
 * Create a positive-order day ranges
 */
inline fun Date.dayOfMonthRangeTo(endInclusive: Date, step: Int): DayOfMonthProgression = DateRangex.dayOfMonthRangeTo(this, endInclusive, step)

/**
 * Create a positive-order day range that does not contain [end]
 */
inline fun Date.dayOfMonthUntil(end: Date, step: Int): DayOfMonthProgression = DateRangex.dayOfMonthUntil(this, end, step)


/* ******************************************* Hour range ******************************************* */


/**
 * Create a positive-order hour ranges
 */
inline infix fun Date.hourOfDayRangeTo(endInclusive: Date): HourOfDayRange = DateRangex.hourOfDayRangeTo(this, endInclusive)

/**
 * Create a positive-order hour range that does not contain [end]
 */
inline infix fun Date.hourOfDayUntil(end: Date): HourOfDayRange = DateRangex.hourOfDayUntil(this, end)

/**
 * Create a reversed hour range
 */
inline infix fun Date.hourOfDayDownTo(endInclusive: Date): HourOfDayProgression = DateRangex.hourOfDayDownTo(this, endInclusive)

/**
 * Create a positive-order hour ranges
 */
inline fun Date.hourOfDayRangeTo(endInclusive: Date, step: Int): HourOfDayProgression = DateRangex.hourOfDayRangeTo(this, endInclusive, step)

/**
 * Create a positive-order hour range that does not contain [end]
 */
inline fun Date.hourOfDayUntil(end: Date, step: Int): HourOfDayProgression = DateRangex.hourOfDayUntil(this, end, step)


/* ******************************************* Minute range ******************************************* */


/**
 * Create a positive-order minute ranges
 */
inline infix fun Date.minuteRangeTo(endInclusive: Date): MinuteRange = DateRangex.minuteRangeTo(this, endInclusive)

/**
 * Create a positive-order minute range that does not contain [end]
 */
inline infix fun Date.minuteUntil(end: Date): MinuteRange = DateRangex.minuteUntil(this, end)

/**
 * Create a reversed minute range
 */
inline infix fun Date.minuteDownTo(endInclusive: Date): MinuteProgression = DateRangex.minuteDownTo(this, endInclusive)

/**
 * Create a positive-order minute ranges
 */
inline fun Date.minuteRangeTo(endInclusive: Date, step: Int): MinuteProgression = DateRangex.minuteRangeTo(this, endInclusive, step)

/**
 * Create a positive-order minute range that does not contain [end]
 */
inline fun Date.minuteUntil(end: Date, step: Int): MinuteProgression = DateRangex.minuteUntil(this, end, step)


/* ******************************************* Second range ******************************************* */


/**
 * Create a positive-order second ranges
 */
inline infix fun Date.secondRangeTo(endInclusive: Date): SecondRange = DateRangex.secondRangeTo(this, endInclusive)

/**
 * Create a positive-order second range that does not contain [end]
 */
inline infix fun Date.secondUntil(end: Date): SecondRange = DateRangex.secondUntil(this, end)

/**
 * Create a reversed second range
 */
inline infix fun Date.secondDownTo(endInclusive: Date): SecondProgression = DateRangex.secondDownTo(this, endInclusive)

/**
 * Create a positive-order second ranges
 */
inline fun Date.secondRangeTo(endInclusive: Date, step: Int): SecondProgression = DateRangex.secondRangeTo(this, endInclusive, step)

/**
 * Create a positive-order second range that does not contain [end]
 */
inline fun Date.secondUntil(end: Date, step: Int): SecondProgression = DateRangex.secondUntil(this, end, step)


/* ******************************************* Millisecond range ******************************************* */


/**
 * Create a positive-order millisecond ranges
 */
inline infix fun Date.millisecondRangeTo(endInclusive: Date): MillisecondRange = DateRangex.millisecondRangeTo(this, endInclusive)

/**
 * Create a positive-order millisecond range that does not contain [end]
 */
inline infix fun Date.millisecondUntil(end: Date): MillisecondRange = DateRangex.millisecondUntil(this, end)

/**
 * Create a reversed millisecond range
 */
inline infix fun Date.millisecondDownTo(endInclusive: Date): MillisecondProgression = DateRangex.millisecondDownTo(this, endInclusive)

/**
 * Create a positive-order millisecond ranges
 */
inline fun Date.millisecondRangeTo(endInclusive: Date, step: Int): MillisecondProgression = DateRangex.millisecondRangeTo(this, endInclusive, step)

/**
 * Create a positive-order millisecond range that does not contain [end]
 */
inline fun Date.millisecondUntil(end: Date, step: Int): MillisecondProgression = DateRangex.millisecondUntil(this, end, step)


/* ******************************************* reversed *******************************************/


/**
 * Returns a year progression that goes over the same range in the opposite direction with the same step.
 */
inline fun YearProgression.reversed(): YearProgression = DateRangex.reversed(this)

/**
 * Returns a month progression that goes over the same range in the opposite direction with the same step.
 */
inline fun MonthProgression.reversed(): MonthProgression = DateRangex.reversed(this)

/**
 * Returns a day of month progression that goes over the same range in the opposite direction with the same step.
 */
inline fun DayOfMonthProgression.reversed(): DayOfMonthProgression = DateRangex.reversed(this)

/**
 * Returns a hour of day progression that goes over the same range in the opposite direction with the same step.
 */
inline fun HourOfDayProgression.reversed(): HourOfDayProgression = DateRangex.reversed(this)

/**
 * Returns a minute progression that goes over the same range in the opposite direction with the same step.
 */
inline fun MinuteProgression.reversed(): MinuteProgression = DateRangex.reversed(this)

/**
 * Returns a second progression that goes over the same range in the opposite direction with the same step.
 */
inline fun SecondProgression.reversed(): SecondProgression = DateRangex.reversed(this)

/**
 * Returns a millisecond progression that goes over the same range in the opposite direction with the same step.
 */
inline fun MillisecondProgression.reversed(): MillisecondProgression = DateRangex.reversed(this)


/* ******************************************* step *******************************************/


/**
 * Returns a year progression that goes over the same range with the given step.
 */
inline fun YearProgression.step(step: Int): YearProgression = DateRangex.step(this, step)

/**
 * Returns a month progression that goes over the same range with the given step.
 */
inline fun MonthProgression.step(step: Int): MonthProgression = DateRangex.step(this, step)

/**
 * Returns a day of month progression that goes over the same range with the given step.
 */
inline fun DayOfMonthProgression.step(step: Int): DayOfMonthProgression = DateRangex.step(this, step)

/**
 * Returns a hour of day progression that goes over the same range with the given step.
 */
inline fun HourOfDayProgression.step(step: Int): HourOfDayProgression = DateRangex.step(this, step)

/**
 * Returns a minute progression that goes over the same range with the given step.
 */
inline fun MinuteProgression.step(step: Int): MinuteProgression = DateRangex.step(this, step)

/**
 * Returns a second progression that goes over the same range with the given step.
 */
inline fun SecondProgression.step(step: Int): SecondProgression = DateRangex.step(this, step)

/**
 * Returns a millisecond progression that goes over the same range with the given step.
 */
inline fun MillisecondProgression.step(step: Int): MillisecondProgression = DateRangex.step(this, step)