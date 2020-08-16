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

package com.github.panpf.tools4j.ranges.ktx

import com.github.panpf.tools4j.ranges.CharProgression
import com.github.panpf.tools4j.ranges.IntProgression
import com.github.panpf.tools4j.ranges.LongProgression
import com.github.panpf.tools4j.ranges.Rangex


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
inline fun Char.until(end: Char, step: Int): CharProgression = Rangex.until(this, end, step)


/* ******************************************* require *******************************************/


/**
 * If [this] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun <T : Comparable<T>> T.requireInRange(minValue: T, maxValue: T, paramName: String? = null): T = Rangex.requireInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Byte.requireInRange(minValue: Byte, maxValue: Byte, paramName: String? = null): Byte = Rangex.requireInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Short.requireInRange(minValue: Short, maxValue: Short, paramName: String? = null): Short = Rangex.requireInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Int.requireInRange(minValue: Int, maxValue: Int, paramName: String? = null): Int = Rangex.requireInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Long.requireInRange(minValue: Long, maxValue: Long, paramName: String? = null): Long = Rangex.requireInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Float.requireInRange(minValue: Float, maxValue: Float, paramName: String? = null): Float = Rangex.requireInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is within the range of [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Double.requireInRange(minValue: Double, maxValue: Double, paramName: String? = null): Double = Rangex.requireInRange(this, minValue, maxValue, paramName)


/**
 * If [this] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun <T : Comparable<T>> T.requireNotInRange(minValue: T, maxValue: T, paramName: String? = null): T = Rangex.requireNotInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Byte.requireNotInRange(minValue: Byte, maxValue: Byte, paramName: String? = null): Byte = Rangex.requireNotInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Short.requireNotInRange(minValue: Short, maxValue: Short, paramName: String? = null): Short = Rangex.requireNotInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Int.requireNotInRange(minValue: Int, maxValue: Int, paramName: String? = null): Int = Rangex.requireNotInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Long.requireNotInRange(minValue: Long, maxValue: Long, paramName: String? = null): Long = Rangex.requireNotInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Float.requireNotInRange(minValue: Float, maxValue: Float, paramName: String? = null): Float = Rangex.requireNotInRange(this, minValue, maxValue, paramName)

/**
 * If [this] is not in the range [minValue] and [maxValue], it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun Double.requireNotInRange(minValue: Double, maxValue: Double, paramName: String? = null): Double = Rangex.requireNotInRange(this, minValue, maxValue, paramName)