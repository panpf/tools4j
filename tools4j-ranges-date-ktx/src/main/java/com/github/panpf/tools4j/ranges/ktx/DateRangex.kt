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
inline infix fun Date.dayRangeTo(endInclusive: Date): DayRange = DateRangex.dayRangeTo(this, endInclusive)

/**
 * Create a positive-order day range that does not contain [end]
 */
inline infix fun Date.dayUntil(end: Date): DayRange = DateRangex.dayUntil(this, end)

/**
 * Create a reversed day range
 */
inline infix fun Date.dayDownTo(endInclusive: Date): DayProgression = DateRangex.dayDownTo(this, endInclusive)

/**
 * Create a positive-order day ranges
 */
inline fun Date.dayRangeTo(endInclusive: Date, step: Int): DayProgression = DateRangex.dayRangeTo(this, endInclusive, step)

/**
 * Create a positive-order day range that does not contain [end]
 */
inline fun Date.dayUntil(end: Date, step: Int): DayProgression = DateRangex.dayUntil(this, end, step)


/* ******************************************* Hour range ******************************************* */


/**
 * Create a positive-order hour ranges
 */
inline infix fun Date.hourRangeTo(endInclusive: Date): HourRange = DateRangex.hourRangeTo(this, endInclusive)

/**
 * Create a positive-order hour range that does not contain [end]
 */
inline infix fun Date.hourUntil(end: Date): HourRange = DateRangex.hourUntil(this, end)

/**
 * Create a reversed hour range
 */
inline infix fun Date.hourDownTo(endInclusive: Date): HourProgression = DateRangex.hourDownTo(this, endInclusive)

/**
 * Create a positive-order hour ranges
 */
inline fun Date.hourRangeTo(endInclusive: Date, step: Int): HourProgression = DateRangex.hourRangeTo(this, endInclusive, step)

/**
 * Create a positive-order hour range that does not contain [end]
 */
inline fun Date.hourUntil(end: Date, step: Int): HourProgression = DateRangex.hourUntil(this, end, step)


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