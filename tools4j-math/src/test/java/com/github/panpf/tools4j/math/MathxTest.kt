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
package com.github.panpf.tools4j.math

import org.junit.Assert
import org.junit.Test
import java.math.RoundingMode

class MathxTest {

    @Test
    fun testNormalDivide() {
        try {
            @Suppress("DIVISION_BY_ZERO")
            2 / 0
            Assert.fail()
        } catch (ignored: Exception) {
        }
        try {
            2 / 2
        } catch (var2: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testDivide() {
        Assert.assertEquals(Mathx.divide(15.toByte(), 3.toByte()), 5f, 0f)
        Assert.assertEquals(Mathx.divide(0.toByte(), 3.toByte()), 0f, 0f)
        Assert.assertEquals(Mathx.divide(15.toByte(), 0.toByte()), 0f, 0f)
        Assert.assertEquals(Mathx.divide(0.toByte(), 0.toByte()), 0f, 0f)
        Assert.assertEquals(Mathx.divide(15.toShort(), 3.toShort()), 5f, 0f)
        Assert.assertEquals(Mathx.divide(0.toShort(), 3.toShort()), 0f, 0f)
        Assert.assertEquals(Mathx.divide(15.toShort(), 0.toShort()), 0f, 0f)
        Assert.assertEquals(Mathx.divide(0.toShort(), 0.toShort()), 0f, 0f)
        Assert.assertEquals(Mathx.divide(15, 3), 5f, 0f)
        Assert.assertEquals(Mathx.divide(0, 3), 0f, 0f)
        Assert.assertEquals(Mathx.divide(15, 0), 0f, 0f)
        Assert.assertEquals(Mathx.divide(0, 0), 0f, 0f)
        Assert.assertEquals(Mathx.divide(15L, 3L), 5.0, 0.0)
        Assert.assertEquals(Mathx.divide(0L, 3L), 0.0, 0.0)
        Assert.assertEquals(Mathx.divide(15L, 0L), 0.0, 0.0)
        Assert.assertEquals(Mathx.divide(0L, 0L), 0.0, 0.0)
        Assert.assertEquals(Mathx.divide(15f, 3f), 5f, 0f)
        Assert.assertEquals(Mathx.divide(0f, 3f), 0f, 0f)
        Assert.assertEquals(Mathx.divide(15f, 0f), 0f, 0f)
        Assert.assertEquals(Mathx.divide(0f, 0f), 0f, 0f)
        Assert.assertEquals(Mathx.divide(15.0, 3.0), 5.0, 0.0)
        Assert.assertEquals(Mathx.divide(0.0, 3.0), 0.0, 0.0)
        Assert.assertEquals(Mathx.divide(15.0, 0.0), 0.0, 0.0)
        Assert.assertEquals(Mathx.divide(0.0, 0.0), 0.0, 0.0)
    }

    @Test
    fun testDivideToInt() {
        Assert.assertEquals(Mathx.divideToInt(15.toByte(), 3.toByte()).toLong(), 5)
        Assert.assertEquals(Mathx.divideToInt(0.toByte(), 3.toByte()).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(15.toByte(), 0.toByte()).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(0.toByte(), 0.toByte()).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(15.toShort(), 3.toShort()).toLong(), 5)
        Assert.assertEquals(Mathx.divideToInt(0.toShort(), 3.toShort()).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(15.toShort(), 0.toShort()).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(0.toShort(), 0.toShort()).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(15, 3).toLong(), 5)
        Assert.assertEquals(Mathx.divideToInt(0, 3).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(15, 0).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(0, 0).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(15L, 3L).toLong(), 5)
        Assert.assertEquals(Mathx.divideToInt(0, 3L).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(15L, 0L).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(0, 0L).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(15f, 3f).toLong(), 5)
        Assert.assertEquals(Mathx.divideToInt(0f, 3f).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(15f, 0f).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(0f, 0f).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(15.0, 3.0).toLong(), 5)
        Assert.assertEquals(Mathx.divideToInt(0.0, 3.0).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(15.0, 0.0).toLong(), 0)
        Assert.assertEquals(Mathx.divideToInt(0.0, 0.0).toLong(), 0)
    }

    @Test
    fun testDivideToLong() {
        Assert.assertEquals(Mathx.divideToLong(15.toByte(), 3.toByte()), 5L)
        Assert.assertEquals(Mathx.divideToLong(0.toByte(), 3.toByte()), 0L)
        Assert.assertEquals(Mathx.divideToLong(15.toByte(), 0.toByte()), 0L)
        Assert.assertEquals(Mathx.divideToLong(0.toByte(), 0.toByte()), 0L)
        Assert.assertEquals(Mathx.divideToLong(15.toShort(), 3.toShort()), 5L)
        Assert.assertEquals(Mathx.divideToLong(0.toShort(), 3.toShort()), 0L)
        Assert.assertEquals(Mathx.divideToLong(15.toShort(), 0.toShort()), 0L)
        Assert.assertEquals(Mathx.divideToLong(0.toShort(), 0.toShort()), 0L)
        Assert.assertEquals(Mathx.divideToLong(15, 3), 5L)
        Assert.assertEquals(Mathx.divideToLong(0, 3), 0L)
        Assert.assertEquals(Mathx.divideToLong(15, 0), 0L)
        Assert.assertEquals(Mathx.divideToLong(0, 0), 0L)
        Assert.assertEquals(Mathx.divideToLong(15L, 3L), 5L)
        Assert.assertEquals(Mathx.divideToLong(0L, 3L), 0L)
        Assert.assertEquals(Mathx.divideToLong(15L, 0L), 0L)
        Assert.assertEquals(Mathx.divideToLong(0L, 0L), 0L)
        Assert.assertEquals(Mathx.divideToLong(15f, 3f), 5L)
        Assert.assertEquals(Mathx.divideToLong(0f, 3f), 0L)
        Assert.assertEquals(Mathx.divideToLong(15f, 0f), 0L)
        Assert.assertEquals(Mathx.divideToLong(0f, 0f), 0L)
        Assert.assertEquals(Mathx.divideToLong(15.0, 3.0), 5L)
        Assert.assertEquals(Mathx.divideToLong(0.0, 3.0), 0L)
        Assert.assertEquals(Mathx.divideToLong(15.0, 0.0), 0L)
        Assert.assertEquals(Mathx.divideToLong(0.0, 0.0), 0L)
    }

    @Test
    fun testScale() {
        Assert.assertEquals(Mathx.scale(0.2489, 2).toString(), 0.25f.toString())
        Assert.assertEquals(Mathx.scale(0.2449, 2).toString(), 0.24f.toString())
        Assert.assertEquals(Mathx.scale(0.2489, 2, RoundingMode.UP).toString(), 0.25f.toString())
        Assert.assertEquals(Mathx.scale(0.2449, 2, RoundingMode.UP).toString(), 0.25f.toString())
        Assert.assertEquals(Mathx.scale(0.2589f, 1).toString(), 0.3f.toString())
        Assert.assertEquals(Mathx.scale(0.2449f, 1).toString(), 0.2f.toString())
        Assert.assertEquals(Mathx.scale(0.2589f, 1, RoundingMode.UP).toString(), 0.3f.toString())
        Assert.assertEquals(Mathx.scale(0.2449f, 1, RoundingMode.UP).toString(), 0.3f.toString())
        Assert.assertEquals(Mathx.scale(0.2489, 0).toString(), 0f.toString())
        Assert.assertEquals(Mathx.scale(0.2449, 0).toString(), 0f.toString())
    }

    @Test
    fun testProportion() {
        Assert.assertEquals(Mathx.proportion(30.toByte(), 11.toByte(), 4), 2.7273f, 0f)
        Assert.assertEquals(Mathx.proportion(30.toByte(), 0.toByte(), 4), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0.toByte(), 11.toByte(), 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0.toByte(), 0.toByte(), 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(30.toByte(), 11.toByte()), 2.73f, 0f)
        Assert.assertEquals(Mathx.proportion(30.toByte(), 0.toByte()), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0.toByte(), 11.toByte()), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0.toByte(), 0.toByte()), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(30.toShort(), 11.toShort(), 4), 2.7273f, 0f)
        Assert.assertEquals(Mathx.proportion(30.toShort(), 0.toShort(), 4), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0.toShort(), 11.toShort(), 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0.toShort(), 0.toShort(), 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(30.toShort(), 11.toShort()), 2.73f, 0f)
        Assert.assertEquals(Mathx.proportion(30.toShort(), 0.toShort()), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0.toShort(), 11.toShort()), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0.toShort(), 0.toShort()), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(30, 11, 4), 2.7273f, 0f)
        Assert.assertEquals(Mathx.proportion(30, 0, 4), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0, 11, 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0, 0, 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(30, 11), 2.73f, 0f)
        Assert.assertEquals(Mathx.proportion(30, 0), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0, 11), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0, 0), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(30L, 11L, 4), 2.7273f, 0f)
        Assert.assertEquals(Mathx.proportion(30L, 0L, 4), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0L, 11L, 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0L, 0L, 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(30L, 11L), 2.73f, 0f)
        Assert.assertEquals(Mathx.proportion(30L, 0L), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0L, 11L), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0L, 0L), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(30f, 11f, 4), 2.7273f, 0f)
        Assert.assertEquals(Mathx.proportion(30f, 0f, 4), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0f, 11f, 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0f, 0f, 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(30f, 11f), 2.73f, 0f)
        Assert.assertEquals(Mathx.proportion(30f, 0f), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0f, 11f), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0f, 0f), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(30.0, 11.0, 4), 2.7273f, 0f)
        Assert.assertEquals(Mathx.proportion(30.0, 0.0, 4), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0.0, 11.0, 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0.0, 0.0, 4), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(30.0, 11.0), 2.73f, 0f)
        Assert.assertEquals(Mathx.proportion(30.0, 0.0), 1f, 0f)
        Assert.assertEquals(Mathx.proportion(0.0, 11.0), 0f, 0f)
        Assert.assertEquals(Mathx.proportion(0.0, 0.0), 0f, 0f)
    }

    @Test
    fun testPercent() {
        Assert.assertEquals(Mathx.percent(11.toByte(), 30.toByte(), 4), 36.6667f, 0f)
        Assert.assertEquals(Mathx.percent(11.toByte(), 0.toByte(), 4), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0.toByte(), 30.toByte(), 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0.toByte(), 0.toByte(), 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(11.toByte(), 30.toByte()), 36.67f, 0f)
        Assert.assertEquals(Mathx.percent(11.toByte(), 0.toByte()), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0.toByte(), 30.toByte()), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0.toByte(), 0.toByte()), 0f, 0f)
        Assert.assertEquals(Mathx.percent(11.toShort(), 30.toShort(), 4), 36.6667f, 0f)
        Assert.assertEquals(Mathx.percent(11.toShort(), 0.toShort(), 4), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0.toShort(), 30.toShort(), 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0.toShort(), 0.toShort(), 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(11.toShort(), 30.toShort()), 36.67f, 0f)
        Assert.assertEquals(Mathx.percent(11.toShort(), 0.toShort()), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0.toShort(), 30.toShort()), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0.toShort(), 0.toShort()), 0f, 0f)
        Assert.assertEquals(Mathx.percent(11, 30, 4), 36.6667f, 0f)
        Assert.assertEquals(Mathx.percent(11, 0, 4), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0, 30, 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0, 0, 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(11, 30), 36.67f, 0f)
        Assert.assertEquals(Mathx.percent(11, 0), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0, 30), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0, 0), 0f, 0f)
        Assert.assertEquals(Mathx.percent(11L, 30L, 4), 36.6667f, 0f)
        Assert.assertEquals(Mathx.percent(11L, 0L, 4), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0L, 30L, 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0L, 0L, 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(11L, 30L), 36.67f, 0f)
        Assert.assertEquals(Mathx.percent(11L, 0L), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0L, 30L), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0L, 0L), 0f, 0f)
        Assert.assertEquals(Mathx.percent(11f, 30f, 4), 36.6667f, 0f)
        Assert.assertEquals(Mathx.percent(11f, 0f, 4), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0f, 30f, 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0f, 0f, 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(11f, 30f), 36.67f, 0f)
        Assert.assertEquals(Mathx.percent(11f, 0f), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0f, 30f), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0f, 0f), 0f, 0f)
        Assert.assertEquals(Mathx.percent(11.0, 30.0, 4), 36.6667f, 0f)
        Assert.assertEquals(Mathx.percent(11.0, 0.0, 4), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0.0, 30.0, 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0.0, 0.0, 4), 0f, 0f)
        Assert.assertEquals(Mathx.percent(11.0, 30.0), 36.67f, 0f)
        Assert.assertEquals(Mathx.percent(11.0, 0.0), 100f, 0f)
        Assert.assertEquals(Mathx.percent(0.0, 30.0), 0f, 0f)
        Assert.assertEquals(Mathx.percent(0.0, 0.0), 0f, 0f)
    }

    @Test
    fun testFormat() {
        Assert.assertEquals(Mathx.format(3.0 / 8.0, "%", 2, false), "37.5%")
        Assert.assertEquals(Mathx.format(3.0f / 8.0f, "%", 2, false), "37.5%")
    }

    @Test
    fun testFormatPercent() {
        Assert.assertEquals(Mathx.formatPercent(3, 8), "37.5%")
        Assert.assertEquals(Mathx.formatPercent(3, 8, 2), "37.5%")
        Assert.assertEquals(Mathx.formatPercent(3, 8, 2, true), "37.50%")
        Assert.assertEquals(Mathx.formatPercent(3, 0), "100%")
        Assert.assertEquals(Mathx.formatPercent(3.0, 8.0), "37.5%")
        Assert.assertEquals(Mathx.formatPercent(3.0, 8.0, 2), "37.5%")
        Assert.assertEquals(Mathx.formatPercent(3.0, 8.0, 2, true), "37.50%")
        Assert.assertEquals(Mathx.formatPercent(3.0, 0.0), "100%")
        Assert.assertEquals(Mathx.formatPercent(3f, 8f), "37.5%")
        Assert.assertEquals(Mathx.formatPercent(3f, 8f, 2), "37.5%")
        Assert.assertEquals(Mathx.formatPercent(3f, 8f, 2, true), "37.50%")
        Assert.assertEquals(Mathx.formatPercent(3f, 0f), "100%")
        Assert.assertEquals(Mathx.formatPercent(3L, 8L), "37.5%")
        Assert.assertEquals(Mathx.formatPercent(3L, 8L, 2), "37.5%")
        Assert.assertEquals(Mathx.formatPercent(3L, 8L, 2, true), "37.50%")
        Assert.assertEquals(Mathx.formatPercent(3L, 0L), "100%")
    }
}