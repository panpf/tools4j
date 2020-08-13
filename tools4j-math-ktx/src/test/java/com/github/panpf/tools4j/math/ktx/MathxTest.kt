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

package com.github.panpf.tools4j.math.ktx

import org.junit.Assert
import org.junit.Test
import java.math.RoundingMode

class MathxTest {

    @Test
    fun testNormalDivide() {
        try {
            @Suppress("DIVISION_BY_ZERO")
            2.div(0)
            Assert.fail()
        } catch (e: Exception) {
        }

        try {

            2.div(2)
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testDivide() {
        Assert.assertEquals(15.toByte().divide(3.toByte()), 5f, 0f)
        Assert.assertEquals((null as Byte?).divide(0.toByte()), 0f, 0f)
        Assert.assertEquals(15.toByte().divide((null as Byte?)), 0f, 0f)
        Assert.assertEquals((null as Byte?).divide((null as Byte?)), 0f, 0f)

        Assert.assertEquals(15.toShort().divide(3.toShort()), 5f, 0f)
        Assert.assertEquals((null as Short?).divide(0.toShort()), 0f, 0f)
        Assert.assertEquals(15.toShort().divide((null as Short?)), 0f, 0f)
        Assert.assertEquals((null as Short?).divide((null as Short?)), 0f, 0f)

        Assert.assertEquals(15.divide(3), 5f, 0f)
        Assert.assertEquals((null as Int?).divide(0), 0f, 0f)
        Assert.assertEquals(15.divide((null as Int?)), 0f, 0f)
        Assert.assertEquals((null as Int?).divide((null as Int?)), 0f, 0f)

        Assert.assertEquals(15.toLong().divide(3.toLong()), 5.0, 0.0)
        Assert.assertEquals((null as Long?).divide(0.toLong()), 0.0, 0.0)
        Assert.assertEquals(15.toLong().divide((null as Long?)), 0.0, 0.0)
        Assert.assertEquals((null as Long?).divide((null as Long?)), 0.0, 0.0)

        Assert.assertEquals(15f.divide(3f), 5f, 0f)
        Assert.assertEquals((null as Float?).divide(0.toFloat()), 0f, 0f)
        Assert.assertEquals(15.toFloat().divide((null as Float?)), 0f, 0f)
        Assert.assertEquals((null as Float?).divide((null as Float?)), 0f, 0f)

        Assert.assertEquals(15.0.divide(3.0), 5.0, 0.0)
        Assert.assertEquals((null as Double?).divide(0.toDouble()), 0.0, 0.0)
        Assert.assertEquals(15.toDouble().divide((null as Double?)), 0.0, 0.0)
        Assert.assertEquals((null as Double?).divide((null as Double?)), 0.0, 0.0)
    }

    @Test
    fun testDivideToInt() {
        Assert.assertEquals(15.toByte().divideToInt(3.toByte()), 5)
        Assert.assertEquals((null as Byte?).divideToInt(0.toByte()), 0)
        Assert.assertEquals(15.toByte().divideToInt((null as Byte?)), 0)
        Assert.assertEquals((null as Byte?).divideToInt((null as Byte?)), 0)

        Assert.assertEquals(15.toShort().divideToInt(3.toShort()), 5)
        Assert.assertEquals((null as Short?).divideToInt(0.toShort()), 0)
        Assert.assertEquals(15.toShort().divideToInt((null as Short?)), 0)
        Assert.assertEquals((null as Short?).divideToInt((null as Short?)), 0)

        Assert.assertEquals(15.divideToInt(3), 5)
        Assert.assertEquals((null as Int?).divideToInt(0), 0)
        Assert.assertEquals(15.divideToInt((null as Int?)), 0)
        Assert.assertEquals((null as Int?).divideToInt((null as Int?)), 0)

        Assert.assertEquals(15.toLong().divideToInt(3.toLong()), 5)
        Assert.assertEquals((null as Long?).divideToInt(0.toLong()), 0)
        Assert.assertEquals(15.toLong().divideToInt((null as Long?)), 0)
        Assert.assertEquals((null as Long?).divideToInt((null as Long?)), 0)

        Assert.assertEquals(15f.divideToInt(3f), 5)
        Assert.assertEquals((null as Float?).divideToInt(0.toFloat()), 0)
        Assert.assertEquals(15.toFloat().divideToInt((null as Float?)), 0)
        Assert.assertEquals((null as Float?).divideToInt((null as Float?)), 0)

        Assert.assertEquals(15.0.divideToInt(3.0), 5)
        Assert.assertEquals((null as Double?).divideToInt(0.toDouble()), 0)
        Assert.assertEquals(15.toDouble().divideToInt((null as Double?)), 0)
        Assert.assertEquals((null as Double?).divideToInt((null as Double?)), 0)
    }

    @Test
    fun testDivideToLong() {
        Assert.assertEquals(15.toByte().divideToLong(3.toByte()), 5)
        Assert.assertEquals((null as Byte?).divideToLong(0.toByte()), 0)
        Assert.assertEquals(15.toByte().divideToLong((null as Byte?)), 0)
        Assert.assertEquals((null as Byte?).divideToLong((null as Byte?)), 0)

        Assert.assertEquals(15.toShort().divideToLong(3.toShort()), 5)
        Assert.assertEquals((null as Short?).divideToLong(0.toShort()), 0)
        Assert.assertEquals(15.toShort().divideToLong((null as Short?)), 0)
        Assert.assertEquals((null as Short?).divideToLong((null as Short?)), 0)

        Assert.assertEquals(15.divideToLong(3), 5)
        Assert.assertEquals((null as Int?).divideToLong(0), 0)
        Assert.assertEquals(15.divideToLong((null as Int?)), 0)
        Assert.assertEquals((null as Int?).divideToLong((null as Int?)), 0)

        Assert.assertEquals(15.toLong().divideToLong(3.toLong()), 5)
        Assert.assertEquals(15.toLong().divideToLong(0.toLong()), 0)
        Assert.assertEquals((null as Long?).divideToLong(0.toLong()), 0)
        Assert.assertEquals(15.toLong().divideToLong((null as Long?)), 0)
        Assert.assertEquals((null as Long?).divideToLong((null as Long?)), 0)

        Assert.assertEquals(15f.divideToLong(3f), 5)
        Assert.assertEquals((null as Float?).divideToLong(0.toFloat()), 0)
        Assert.assertEquals(15.toFloat().divideToLong((null as Float?)), 0)
        Assert.assertEquals((null as Float?).divideToLong((null as Float?)), 0)

        Assert.assertEquals(15.0.divideToLong(3.0), 5)
        Assert.assertEquals((null as Double?).divideToLong(0.toDouble()), 0)
        Assert.assertEquals(15.toDouble().divideToLong((null as Double?)), 0)
        Assert.assertEquals((null as Double?).divideToLong((null as Double?)), 0)
    }

    @Test
    fun testScale() {
        Assert.assertEquals(0.2489.scale(2).toString(), 0.25f.toString())
        Assert.assertEquals(0.2449.scale(2).toString(), 0.24f.toString())

        Assert.assertEquals(0.2489.scale(2, RoundingMode.UP).toString(), 0.25f.toString())
        Assert.assertEquals(0.2449.scale(2, RoundingMode.UP).toString(), 0.25f.toString())

        Assert.assertEquals(0.2589f.scale(1).toString(), 0.3f.toString())
        Assert.assertEquals(0.2449f.scale(1).toString(), 0.2f.toString())

        Assert.assertEquals(0.2589f.scale(1, RoundingMode.UP).toString(), 0.3f.toString())
        Assert.assertEquals(0.2449f.scale(1, RoundingMode.UP).toString(), 0.3f.toString())

        Assert.assertEquals(0.2489.scale(0).toString(), 0f.toString())
        Assert.assertEquals(0.2449.scale(0).toString(), 0f.toString())
    }

    @Test
    fun testProportion() {
        Assert.assertEquals(30.toByte().proportion(11.toByte(), 4), 2.7273f, 0f)
        Assert.assertEquals(30.toByte().proportion(0.toByte(), 4), 1f, 0f)
        Assert.assertEquals(0.toByte().proportion(11.toByte(), 4), 0f, 0f)
        Assert.assertEquals(0.toByte().proportion(0.toByte(), 4), 0f, 0f)

        Assert.assertEquals(30.toByte().proportion(11.toByte()), 2.73f, 0f)
        Assert.assertEquals(30.toByte().proportion((null as Byte?)), 1f, 0f)
        Assert.assertEquals((null as Byte?).proportion(11.toByte()), 0f, 0f)
        Assert.assertEquals((null as Byte?).proportion((null as Byte?)), 0f, 0f)

        Assert.assertEquals(30.toShort().proportion(11.toShort(), 4), 2.7273f, 0f)
        Assert.assertEquals(30.toShort().proportion(0.toShort(), 4), 1f, 0f)
        Assert.assertEquals(0.toShort().proportion(11.toShort(), 4), 0f, 0f)
        Assert.assertEquals(0.toShort().proportion(0.toShort(), 4), 0f, 0f)

        Assert.assertEquals(30.toShort().proportion(11.toShort()), 2.73f, 0f)
        Assert.assertEquals(30.toShort().proportion((null as Short?)), 1f, 0f)
        Assert.assertEquals((null as Short?).proportion(11.toShort()), 0f, 0f)
        Assert.assertEquals((null as Short?).proportion((null as Short?)), 0f, 0f)

        Assert.assertEquals(30.proportion(11, 4), 2.7273f, 0f)
        Assert.assertEquals(30.proportion(0, 4), 1f, 0f)
        Assert.assertEquals(0.proportion(11, 4), 0f, 0f)
        Assert.assertEquals(0.proportion(0, 4), 0f, 0f)

        Assert.assertEquals(30.proportion(11), 2.73f, 0f)
        Assert.assertEquals(30.proportion((null as Int?)), 1f, 0f)
        Assert.assertEquals((null as Int?).proportion(11), 0f, 0f)
        Assert.assertEquals((null as Int?).proportion((null as Int?)), 0f, 0f)

        Assert.assertEquals(30.toLong().proportion(11.toLong(), 4), 2.7273f, 0f)
        Assert.assertEquals(30.toLong().proportion(0.toLong(), 4), 1f, 0f)
        Assert.assertEquals(0.toLong().proportion(11.toLong(), 4), 0f, 0f)
        Assert.assertEquals(0.toLong().proportion(0.toLong(), 4), 0f, 0f)

        Assert.assertEquals(30.toLong().proportion(11.toLong()), 2.73f, 0f)
        Assert.assertEquals(30.toLong().proportion((null as Long?)), 1f, 0f)
        Assert.assertEquals((null as Long?).proportion(11.toLong()), 0f, 0f)
        Assert.assertEquals((null as Long?).proportion((null as Long?)), 0f, 0f)

        Assert.assertEquals(30.toFloat().proportion(11.toFloat(), 4), 2.7273f, 0f)
        Assert.assertEquals(30.toFloat().proportion(0.toFloat(), 4), 1f, 0f)
        Assert.assertEquals(0.toFloat().proportion(11.toFloat(), 4), 0f, 0f)
        Assert.assertEquals(0.toFloat().proportion(0.toFloat(), 4), 0f, 0f)

        Assert.assertEquals(30.toFloat().proportion(11.toFloat()), 2.73f, 0f)
        Assert.assertEquals(30.toFloat().proportion((null as Float?)), 1f, 0f)
        Assert.assertEquals((null as Float?).proportion(11.toFloat()), 0f, 0f)
        Assert.assertEquals((null as Float?).proportion((null as Float?)), 0f, 0f)

        Assert.assertEquals(30.toDouble().proportion(11.toDouble(), 4), 2.7273f, 0f)
        Assert.assertEquals(30.toDouble().proportion(0.toDouble(), 4), 1f, 0f)
        Assert.assertEquals(0.toDouble().proportion(11.toDouble(), 4), 0f, 0f)
        Assert.assertEquals(0.toDouble().proportion(0.toDouble(), 4), 0f, 0f)

        Assert.assertEquals(30.toDouble().proportion(11.toDouble()), 2.73f, 0f)
        Assert.assertEquals(30.toDouble().proportion((null as Double?)), 1f, 0f)
        Assert.assertEquals((null as Double?).proportion(11.toDouble()), 0f, 0f)
        Assert.assertEquals((null as Double?).proportion((null as Double?)), 0f, 0f)
    }

    @Test
    fun testPercent() {
        Assert.assertEquals(11.toByte().percent(30.toByte(), 4), 36.6667f, 0f)
        Assert.assertEquals(11.toByte().percent(0.toByte(), 4), 100f, 0f)
        Assert.assertEquals(0.toByte().percent(30.toByte(), 4), 0f, 0f)
        Assert.assertEquals(0.toByte().percent(0.toByte(), 4), 0f, 0f)

        Assert.assertEquals(11.toByte().percent(30.toByte()), 36.67f, 0f)
        Assert.assertEquals(11.toByte().percent((null as Byte?)), 100f, 0f)
        Assert.assertEquals((null as Byte?).percent(30.toByte()), 0f, 0f)
        Assert.assertEquals((null as Byte?).percent((null as Byte?)), 0f, 0f)

        Assert.assertEquals(11.toShort().percent(30.toShort(), 4), 36.6667f, 0f)
        Assert.assertEquals(11.toShort().percent(0.toShort(), 4), 100f, 0f)
        Assert.assertEquals(0.toShort().percent(30.toShort(), 4), 0f, 0f)
        Assert.assertEquals(0.toShort().percent(0.toShort(), 4), 0f, 0f)

        Assert.assertEquals(11.toShort().percent(30.toShort()), 36.67f, 0f)
        Assert.assertEquals(11.toShort().percent((null as Short?)), 100f, 0f)
        Assert.assertEquals((null as Short?).percent(30.toShort()), 0f, 0f)
        Assert.assertEquals((null as Short?).percent((null as Short?)), 0f, 0f)

        Assert.assertEquals(11.percent(30, 4), 36.6667f, 0f)
        Assert.assertEquals(11.percent(0, 4), 100f, 0f)
        Assert.assertEquals(0.percent(30, 4), 0f, 0f)
        Assert.assertEquals(0.percent(0, 4), 0f, 0f)

        Assert.assertEquals(11.percent(30), 36.67f, 0f)
        Assert.assertEquals(11.percent((null as Int?)), 100f, 0f)
        Assert.assertEquals((null as Int?).percent(30), 0f, 0f)
        Assert.assertEquals((null as Int?).percent((null as Int?)), 0f, 0f)

        Assert.assertEquals(11.toLong().percent(30.toLong(), 4), 36.6667f, 0f)
        Assert.assertEquals(11.toLong().percent(0.toLong(), 4), 100f, 0f)
        Assert.assertEquals(0.toLong().percent(30.toLong(), 4), 0f, 0f)
        Assert.assertEquals(0.toLong().percent(0.toLong(), 4), 0f, 0f)

        Assert.assertEquals(11.toLong().percent(30.toLong()), 36.67f, 0f)
        Assert.assertEquals(11.toLong().percent((null as Long?)), 100f, 0f)
        Assert.assertEquals((null as Long?).percent(30.toLong()), 0f, 0f)
        Assert.assertEquals((null as Long?).percent((null as Long?)), 0f, 0f)

        Assert.assertEquals(11.toFloat().percent(30.toFloat(), 4), 36.6667f, 0f)
        Assert.assertEquals(11.toFloat().percent(0.toFloat(), 4), 100f, 0f)
        Assert.assertEquals(0.toFloat().percent(30.toFloat(), 4), 0f, 0f)
        Assert.assertEquals(0.toFloat().percent(0.toFloat(), 4), 0f, 0f)

        Assert.assertEquals(11.toFloat().percent(30.toFloat()), 36.67f, 0f)
        Assert.assertEquals(11.toFloat().percent((null as Float?)), 100f, 0f)
        Assert.assertEquals((null as Float?).percent(30.toFloat()), 0f, 0f)
        Assert.assertEquals((null as Float?).percent((null as Float?)), 0f, 0f)

        Assert.assertEquals(11.toDouble().percent(30.toDouble(), 4), 36.6667f, 0f)
        Assert.assertEquals(11.toDouble().percent(0.toDouble(), 4), 100f, 0f)
        Assert.assertEquals(0.toDouble().percent(30.toDouble(), 4), 0f, 0f)
        Assert.assertEquals(0.toDouble().percent(0.toDouble(), 4), 0f, 0f)

        Assert.assertEquals(11.toDouble().percent(30.toDouble()), 36.67f, 0f)
        Assert.assertEquals(11.toDouble().percent((null as Double?)), 100f, 0f)
        Assert.assertEquals((null as Double?).percent(30.toDouble()), 0f, 0f)
        Assert.assertEquals((null as Double?).percent((null as Double?)), 0f, 0f)
    }

    @Test
    fun testFormat() {
        Assert.assertEquals((3.0 / 8.0).format("%"), "37.5%")
        Assert.assertEquals((3.0f / 8.0f).format("%"), "37.5%")
    }

    @Test
    fun testFormatPercentWith() {
        Assert.assertEquals(3.formatPercentWith(8), "37.5%")
        Assert.assertEquals(3.formatPercentWith(8, 2, true), "37.50%")
        Assert.assertEquals(3.formatPercentWith(0), "100%")

        Assert.assertEquals(3.0.formatPercentWith(8.0), "37.5%")
        Assert.assertEquals(3.0.formatPercentWith(8.0, 2, true), "37.50%")
        Assert.assertEquals(3.0.formatPercentWith(0.0), "100%")

        Assert.assertEquals(3f.formatPercentWith(8f), "37.5%")
        Assert.assertEquals(3f.formatPercentWith(8f, 2, true), "37.50%")
        Assert.assertEquals(3f.formatPercentWith(0f), "100%")

        Assert.assertEquals(3L.formatPercentWith(8L), "37.5%")
        Assert.assertEquals(3L.formatPercentWith(8L, 2, true), "37.50%")
        Assert.assertEquals(3L.formatPercentWith(0L), "100%")
    }
}