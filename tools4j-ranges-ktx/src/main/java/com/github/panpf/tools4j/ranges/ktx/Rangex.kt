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