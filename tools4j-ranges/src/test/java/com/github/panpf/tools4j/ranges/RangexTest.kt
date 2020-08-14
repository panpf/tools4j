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

package com.github.panpf.tools4j.ranges

import org.junit.Assert.*
import org.junit.Test

class RangexTest {

    @Test
    fun testByteRange() {
        assertEquals(Rangex.rangeTo(1.toByte(), 10.toByte()).count().toLong(), 10)
        assertEquals(Rangex.rangeTo(1.toByte(), 1.toByte()).count().toLong(), 1)
        assertEquals(Rangex.rangeTo(0.toByte(), 1.toByte()).count().toLong(), 2)
        assertEquals(Rangex.rangeTo(1.toByte(), 0.toByte()).count().toLong(), 0)
        assertEquals(Rangex.rangeTo(1.toByte(), 10.toByte(), 4).count().toLong(), 3)
        assertEquals(Rangex.rangeTo(10.toByte(), 1.toByte(), -4).count().toLong(), 3)

        assertEquals(Rangex.until(1.toByte(), 10.toByte()).count().toLong(), 9)
        assertEquals(Rangex.until(1.toByte(), 1.toByte()).count().toLong(), 0)
        assertEquals(Rangex.until(1.toByte(), 0.toByte()).count().toLong(), 0)
        assertEquals(Rangex.until(0.toByte(), 1.toByte()).count().toLong(), 1)
        assertEquals(Rangex.until(1.toByte(), 11.toByte(), 4).count().toLong(), 3)
        assertEquals(Rangex.until(11.toByte(), 1.toByte(), -4).count().toLong(), 3)

        assertEquals(Rangex.downTo(10.toByte(), 1.toByte()).count().toLong(), 10)
        assertEquals(Rangex.downTo(1.toByte(), 1.toByte()).count().toLong(), 1)
        assertEquals(Rangex.downTo(0.toByte(), 1.toByte()).count().toLong(), 0)
        assertEquals(Rangex.downTo(1.toByte(), 0.toByte()).count().toLong(), 2)
    }

    @Test
    fun testShortRange() {
        assertEquals(Rangex.rangeTo(1.toShort(), 10.toShort()).count().toLong(), 10)
        assertEquals(Rangex.rangeTo(1.toShort(), 1.toShort()).count().toLong(), 1)
        assertEquals(Rangex.rangeTo(0.toShort(), 1.toShort()).count().toLong(), 2)
        assertEquals(Rangex.rangeTo(1.toShort(), 0.toShort()).count().toLong(), 0)
        assertEquals(Rangex.rangeTo(1.toShort(), 10.toShort(), 4).count().toLong(), 3)
        assertEquals(Rangex.rangeTo(10.toShort(), 1.toShort(), -4).count().toLong(), 3)

        assertEquals(Rangex.until(1.toShort(), 10.toShort()).count().toLong(), 9)
        assertEquals(Rangex.until(1.toShort(), 1.toShort()).count().toLong(), 0)
        assertEquals(Rangex.until(1.toShort(), 0.toShort()).count().toLong(), 0)
        assertEquals(Rangex.until(0.toShort(), 1.toShort()).count().toLong(), 1)
        assertEquals(Rangex.until(1.toShort(), 11.toShort(), 4).count().toLong(), 3)
        assertEquals(Rangex.until(11.toShort(), 1.toShort(), -4).count().toLong(), 3)

        assertEquals(Rangex.downTo(10.toShort(), 1.toShort()).count().toLong(), 10)
        assertEquals(Rangex.downTo(1.toShort(), 1.toShort()).count().toLong(), 1)
        assertEquals(Rangex.downTo(0.toShort(), 1.toShort()).count().toLong(), 0)
        assertEquals(Rangex.downTo(1.toShort(), 0.toShort()).count().toLong(), 2)
    }

    @Test
    fun testIntRange() {
        assertEquals(Rangex.rangeTo(1, 10).count().toLong(), 10)
        assertEquals(Rangex.rangeTo(1, 1).count().toLong(), 1)
        assertEquals(Rangex.rangeTo(0, 1).count().toLong(), 2)
        assertEquals(Rangex.rangeTo(1, 0).count().toLong(), 0)
        assertEquals(Rangex.rangeTo(1, 10, 4).count().toLong(), 3)
        assertEquals(Rangex.rangeTo(10, 1, -4).count().toLong(), 3)

        assertEquals(Rangex.until(1, 10).count().toLong(), 9)
        assertEquals(Rangex.until(1, 1).count().toLong(), 0)
        assertEquals(Rangex.until(1, 0).count().toLong(), 0)
        assertEquals(Rangex.until(0, 1).count().toLong(), 1)
        assertEquals(Rangex.until(1, 11, 4).count().toLong(), 3)
        assertEquals(Rangex.until(11, 1, -4).count().toLong(), 3)

        assertEquals(Rangex.downTo(10, 1).count().toLong(), 10)
        assertEquals(Rangex.downTo(1, 1).count().toLong(), 1)
        assertEquals(Rangex.downTo(0, 1).count().toLong(), 0)
        assertEquals(Rangex.downTo(1, 0).count().toLong(), 2)
    }

    @Test
    fun testLongRange() {
        assertEquals(Rangex.rangeTo(1L, 10L).count().toLong(), 10)
        assertEquals(Rangex.rangeTo(1L, 1L).count().toLong(), 1)
        assertEquals(Rangex.rangeTo(0L, 1L).count().toLong(), 2)
        assertEquals(Rangex.rangeTo(1L, 0L).count().toLong(), 0)
        assertEquals(Rangex.rangeTo(1L, 10L, 4L).count().toLong(), 3)

        assertEquals(Rangex.until(1L, 10L).count().toLong(), 9)
        assertEquals(Rangex.until(1L, 1L).count().toLong(), 0)
        assertEquals(Rangex.until(1L, 0L).count().toLong(), 0)
        assertEquals(Rangex.until(0L, 1L).count().toLong(), 1)
        assertEquals(Rangex.until(1L, 11L, 4L).count().toLong(), 3)
        assertEquals(Rangex.until(11L, 1L, -4L).count().toLong(), 3)

        assertEquals(Rangex.downTo(10L, 1L).count().toLong(), 10)
        assertEquals(Rangex.downTo(1L, 1L).count().toLong(), 1)
        assertEquals(Rangex.downTo(0L, 1L).count().toLong(), 0)
        assertEquals(Rangex.downTo(1L, 0L).count().toLong(), 2)
        assertEquals(Rangex.rangeTo(10L, 1L, -4L).count().toLong(), 3)
    }

    @Test
    fun testCharRange() {
        assertEquals(Rangex.rangeTo(1.toChar(), 10.toChar()).count().toLong(), 10)
        assertEquals(Rangex.rangeTo(1.toChar(), 1.toChar()).count().toLong(), 1)
        assertEquals(Rangex.rangeTo(0.toChar(), 1.toChar()).count().toLong(), 2)
        assertEquals(Rangex.rangeTo(1.toChar(), 0.toChar()).count().toLong(), 0)
        assertEquals(Rangex.rangeTo(1.toChar(), 10.toChar(), 4).count().toLong(), 3)

        assertEquals(Rangex.until(1.toChar(), 10.toChar()).count().toLong(), 9)
        assertEquals(Rangex.until(1.toChar(), 1.toChar()).count().toLong(), 0)
        assertEquals(Rangex.until(1.toChar(), 0.toChar()).count().toLong(), 0)
        assertEquals(Rangex.until(0.toChar(), 1.toChar()).count().toLong(), 1)
        assertEquals(Rangex.until(1.toChar(), 11.toChar(), 4).count().toLong(), 3)
        assertEquals(Rangex.until(11.toChar(), 1.toChar(), -4).count().toLong(), 3)

        assertEquals(Rangex.downTo(10.toChar(), 1.toChar()).count().toLong(), 10)
        assertEquals(Rangex.downTo(1.toChar(), 1.toChar()).count().toLong(), 1)
        assertEquals(Rangex.downTo(0.toChar(), 1.toChar()).count().toLong(), 0)
        assertEquals(Rangex.downTo(1.toChar(), 0.toChar()).count().toLong(), 2)
        assertEquals(Rangex.rangeTo(10.toChar(), 0.toChar(), -4).count().toLong(), 3)
    }

    @Test
    fun tesFloatRange() {
        assertTrue(Rangex.rangeTo(1f, 10f).contains(5f))
        assertEquals(1f.rangeTo(10f).contains(5f), Rangex.rangeTo(1f, 10f).contains(5f))

        assertFalse(Rangex.rangeTo(1f, 10f).contains(0f))
        assertEquals(1f.rangeTo(10f).contains(0f), Rangex.rangeTo(1f, 10f).contains(0f))

        assertFalse(Rangex.rangeTo(1f, 10f).contains(11f))
        assertEquals(1f.rangeTo(10f).contains(11f), Rangex.rangeTo(1f, 10f).contains(11f))
    }

    @Test
    fun tesDoubleRange() {
        assertTrue(Rangex.rangeTo(1.toDouble(), 10.toDouble()).contains(5.toDouble()))
        assertFalse(Rangex.rangeTo(1.toDouble(), 10.toDouble()).contains(0.toDouble()))
        assertFalse(Rangex.rangeTo(1.toDouble(), 10.toDouble()).contains(11.toDouble()))
    }

    @Test
    fun testRequireInRange() {
        Rangex.requireInRange(2.toByte(), 0.toByte(), 5.toByte())
        try {
            Rangex.requireInRange((-1).toByte(), 0.toByte(), 5.toByte())
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            Rangex.requireInRange(6.toByte(), 0.toByte(), 5.toByte())
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        Rangex.requireInRange(2.toShort(), 0.toShort(), 5.toShort())
        try {
            Rangex.requireInRange((-1).toShort(), 0.toShort(), 5.toShort())
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            Rangex.requireInRange(6.toShort(), 0.toShort(), 5.toShort())
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        Rangex.requireInRange(2, 0, 5)
        try {
            Rangex.requireInRange(-1, 0, 5)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            Rangex.requireInRange(6, 0, 5)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        Rangex.requireInRange(2L, 0L, 5L)
        try {
            Rangex.requireInRange(-1L, 0L, 5L)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            Rangex.requireInRange(6L, 0L, 5L)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        Rangex.requireInRange(2f, 0f, 5f)
        try {
            Rangex.requireInRange(-1f, 0f, 5f)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            Rangex.requireInRange(6f, 0f, 5f)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        Rangex.requireInRange(2.0, 0.0, 5.0)
        try {
            Rangex.requireInRange(-1.0, 0.0, 5.0)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            Rangex.requireInRange(6.0, 0.0, 5.0)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
    }

    @Test
    fun testRequireNotInRange() {
        Rangex.requireNotInRange(6.toByte(), 0.toByte(), 5.toByte())
        Rangex.requireNotInRange((-1).toByte(), 0.toByte(), 5.toByte())
        try {
            Rangex.requireNotInRange(2.toByte(), 0.toByte(), 5.toByte())
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        Rangex.requireNotInRange(6.toShort(), 0.toShort(), 5.toShort())
        Rangex.requireNotInRange((-1).toShort(), 0.toShort(), 5.toShort())
        try {
            Rangex.requireNotInRange(2.toShort(), 0.toShort(), 5.toShort())
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        Rangex.requireNotInRange(6, 0, 5)
        Rangex.requireNotInRange(-1, 0, 5)
        try {
            Rangex.requireNotInRange(2, 0, 5)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        Rangex.requireNotInRange(6L, 0L, 5L)
        Rangex.requireNotInRange(-1L, 0L, 5L)
        try {
            Rangex.requireNotInRange(2L, 0L, 5L)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        Rangex.requireNotInRange(6f, 0f, 5f)
        Rangex.requireNotInRange(-1f, 0f, 5f)
        try {
            Rangex.requireNotInRange(2f, 0f, 5f)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
        Rangex.requireNotInRange(6.0, 0.0, 5.0)
        Rangex.requireNotInRange(-1.0, 0.0, 5.0)
        try {
            Rangex.requireNotInRange(2.0, 0.0, 5.0)
            fail()
        } catch (ignored: java.lang.Exception) {
        }
    }

    @Test
    fun testReversed() {
        assertEquals((1..10).reversed().joinToString(), Rangex.reversed(Rangex.rangeTo(1, 10)).joinToString())
        assertEquals((1L..10L).reversed().joinToString(), Rangex.reversed(Rangex.rangeTo(1L, 10L)).joinToString())
        assertEquals((1.toChar()..10.toChar()).reversed().joinToString(), Rangex.reversed(Rangex.rangeTo(1.toChar(), 10.toChar())).joinToString())
    }

    @Test
    fun testStep() {
        assertEquals((1..10).step(3).joinToString(), Rangex.step(Rangex.rangeTo(1, 10), 3).joinToString())
        assertEquals((1L..10L).step(3).joinToString(), Rangex.step(Rangex.rangeTo(1L, 10L), 3).joinToString())
        assertEquals((1.toChar()..10.toChar()).step(3).joinToString(), Rangex.step(Rangex.rangeTo(1.toChar(), 10.toChar()), 3).joinToString())

        assertEquals(kotlin.ranges.IntProgression.fromClosedRange(10, 1, -3).step(3).joinToString(), Rangex.step(IntProgression.fromClosedRange(10, 1, -3), 3).joinToString())
        assertEquals(kotlin.ranges.LongProgression.fromClosedRange(10L, 1L, -3).step(3).joinToString(), Rangex.step(LongProgression.fromClosedRange(10L, 1L, -3), 3).joinToString())
        assertEquals(kotlin.ranges.CharProgression.fromClosedRange(10.toChar(), 1.toChar(), -3).step(3).joinToString(), Rangex.step(CharProgression.fromClosedRange(10.toChar(), 1.toChar(), -3), 3).joinToString())

        try {
            Rangex.step(IntProgression.fromClosedRange(10, 1, -3), 0)
            fail()
        } catch (e: Exception) {
            if(!(e is IllegalArgumentException && e.message.orEmpty().startsWith("Step must be positive, was:"))){
                fail()
            }
        }
        try {
            Rangex.step(LongProgression.fromClosedRange(10L, 1L, -3), 0)
            fail()
        } catch (e: Exception) {
            if(!(e is IllegalArgumentException && e.message.orEmpty().startsWith("Step must be positive, was:"))){
                fail()
            }
        }
        try {
            Rangex.step(CharProgression.fromClosedRange(10.toChar(), 1.toChar(), -3), 0)
            fail()
        } catch (e: Exception) {
            if(!(e is IllegalArgumentException && e.message.orEmpty().startsWith("Step must be positive, was:"))){
                fail()
            }
        }
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */

    @Test
    fun testIn() {
        assertTrue(Rangex.`in`(9.toByte(), 3.toByte(), 10.toByte()))
        assertFalse(Rangex.`in`(2.toByte(), 3.toByte(), 10.toByte()))
        assertFalse(Rangex.`in`(11.toByte(), 3.toByte(), 10.toByte()))

        assertTrue(Rangex.`in`(9.toShort(), 3.toShort(), 10.toShort()))
        assertFalse(Rangex.`in`(2.toShort(), 3.toShort(), 10.toShort()))
        assertFalse(Rangex.`in`(11.toShort(), 3.toShort(), 10.toShort()))

        assertTrue(Rangex.`in`(9, 3, 10))
        assertFalse(Rangex.`in`(2, 3, 10))
        assertFalse(Rangex.`in`(11, 3, 10))

        assertTrue(Rangex.`in`(9L, 3L, 10L))
        assertFalse(Rangex.`in`(2L, 3L, 10L))
        assertFalse(Rangex.`in`(11L, 3L, 10L))

        assertTrue(Rangex.`in`(9f, 3f, 10f))
        assertFalse(Rangex.`in`(2f, 3f, 10f))
        assertFalse(Rangex.`in`(11f, 3f, 10f))

        assertTrue(Rangex.`in`(9.0, 3.0, 10.0))
        assertFalse(Rangex.`in`(2.0, 3.0, 10.0))
        assertFalse(Rangex.`in`(11.0, 3.0, 10.0))
    }

    @Test
    fun testNotIn() {
        assertFalse(Rangex.notIn(9.toByte(), 3.toByte(), 10.toByte()))
        assertTrue(Rangex.notIn(2.toByte(), 3.toByte(), 10.toByte()))
        assertTrue(Rangex.notIn(11.toByte(), 3.toByte(), 10.toByte()))

        assertFalse(Rangex.notIn(9.toShort(), 3.toShort(), 10.toShort()))
        assertTrue(Rangex.notIn(2.toShort(), 3.toShort(), 10.toShort()))
        assertTrue(Rangex.notIn(11.toShort(), 3.toShort(), 10.toShort()))

        assertFalse(Rangex.notIn(9, 3, 10))
        assertTrue(Rangex.notIn(2, 3, 10))
        assertTrue(Rangex.notIn(11, 3, 10))

        assertFalse(Rangex.notIn(9L, 3L, 10L))
        assertTrue(Rangex.notIn(2L, 3L, 10L))
        assertTrue(Rangex.notIn(11L, 3L, 10L))

        assertFalse(Rangex.notIn(9f, 3f, 10f))
        assertTrue(Rangex.notIn(2f, 3f, 10f))
        assertTrue(Rangex.notIn(11f, 3f, 10f))

        assertFalse(Rangex.notIn(9.0, 3.0, 10.0))
        assertTrue(Rangex.notIn(2.0, 3.0, 10.0))
        assertTrue(Rangex.notIn(11.0, 3.0, 10.0))
    }


    @Test
    fun testCoerceAtLeast() {
        assertEquals(5.toByte(), Rangex.coerceAtLeast(5.toByte(), 4.toByte()))
        assertEquals(5.toByte().coerceAtLeast(4.toByte()), Rangex.coerceAtLeast(5.toByte(), 4.toByte()))
        assertEquals(6.toByte(), Rangex.coerceAtLeast(5.toByte(), 6.toByte()))
        assertEquals(5.toByte().coerceAtLeast(6.toByte()), Rangex.coerceAtLeast(5.toByte(), 6.toByte()))

        assertEquals(5.toShort(), Rangex.coerceAtLeast(5.toShort(), 4.toShort()))
        assertEquals(5.toShort().coerceAtLeast(4.toShort()), Rangex.coerceAtLeast(5.toShort(), 4.toShort()))
        assertEquals(6.toShort(), Rangex.coerceAtLeast(5.toShort(), 6.toShort()))
        assertEquals(5.toShort().coerceAtLeast(6.toShort()), Rangex.coerceAtLeast(5.toShort(), 6.toShort()))

        assertEquals(5, Rangex.coerceAtLeast(5, 4))
        assertEquals(5.coerceAtLeast(4), Rangex.coerceAtLeast(5, 4))
        assertEquals(6, Rangex.coerceAtLeast(5, 6))
        assertEquals(5.coerceAtLeast(6), Rangex.coerceAtLeast(5, 6))

        assertEquals(5.toLong(), Rangex.coerceAtLeast(5.toLong(), 4.toLong()))
        assertEquals(5.toLong().coerceAtLeast(4.toLong()), Rangex.coerceAtLeast(5.toLong(), 4.toLong()))
        assertEquals(6.toLong(), Rangex.coerceAtLeast(5.toLong(), 6.toLong()))
        assertEquals(5.toLong().coerceAtLeast(6.toLong()), Rangex.coerceAtLeast(5.toLong(), 6.toLong()))

        assertEquals(5.toFloat(), Rangex.coerceAtLeast(5.toFloat(), 4.toFloat()), 0.toFloat())
        assertEquals(5.toFloat().coerceAtLeast(4.toFloat()), Rangex.coerceAtLeast(5.toFloat(), 4.toFloat()), 0.toFloat())
        assertEquals(6.toFloat(), Rangex.coerceAtLeast(5.toFloat(), 6.toFloat()), 0.toFloat())
        assertEquals(5.toFloat().coerceAtLeast(6.toFloat()), Rangex.coerceAtLeast(5.toFloat(), 6.toFloat()), 0.toFloat())

        assertEquals(5.toDouble(), Rangex.coerceAtLeast(5.toDouble(), 4.toDouble()), 0.toDouble())
        assertEquals(5.toDouble().coerceAtLeast(4.toDouble()), Rangex.coerceAtLeast(5.toDouble(), 4.toDouble()), 0.toDouble())
        assertEquals(6.toDouble(), Rangex.coerceAtLeast(5.toDouble(), 6.toDouble()), 0.toDouble())
        assertEquals(5.toDouble().coerceAtLeast(6.toDouble()), Rangex.coerceAtLeast(5.toDouble(), 6.toDouble()), 0.toDouble())

        assertEquals("5", Rangex.coerceAtLeast("5", "4"))
        assertEquals("5".coerceAtLeast("4"), Rangex.coerceAtLeast("5", "4"))
        assertEquals("6", Rangex.coerceAtLeast("5", "6"))
        assertEquals("5".coerceAtLeast("6"), Rangex.coerceAtLeast("5", "6"))
    }

    @Test
    fun testCoerceAtMost() {
        assertEquals(5.toByte(), Rangex.coerceAtMost(5.toByte(), 6.toByte()))
        assertEquals(5.toByte().coerceAtMost(6.toByte()), Rangex.coerceAtMost(5.toByte(), 6.toByte()))
        assertEquals(4.toByte(), Rangex.coerceAtMost(5.toByte(), 4.toByte()))
        assertEquals(5.toByte().coerceAtMost(4.toByte()), Rangex.coerceAtMost(5.toByte(), 4.toByte()))

        assertEquals(5.toShort(), Rangex.coerceAtMost(5.toShort(), 6.toShort()))
        assertEquals(5.toShort().coerceAtMost(6.toShort()), Rangex.coerceAtMost(5.toShort(), 6.toShort()))
        assertEquals(4.toShort(), Rangex.coerceAtMost(5.toShort(), 4.toShort()))
        assertEquals(5.toShort().coerceAtMost(4.toShort()), Rangex.coerceAtMost(5.toShort(), 4.toShort()))

        assertEquals(5, Rangex.coerceAtMost(5, 6))
        assertEquals(5.coerceAtMost(6), Rangex.coerceAtMost(5, 6))
        assertEquals(4, Rangex.coerceAtMost(5, 4))
        assertEquals(5.coerceAtMost(4), Rangex.coerceAtMost(5, 4))

        assertEquals(5.toLong(), Rangex.coerceAtMost(5.toLong(), 6.toLong()))
        assertEquals(5.toLong().coerceAtMost(6.toLong()), Rangex.coerceAtMost(5.toLong(), 6.toLong()))
        assertEquals(4.toLong(), Rangex.coerceAtMost(5.toLong(), 4.toLong()))
        assertEquals(5.toLong().coerceAtMost(4.toLong()), Rangex.coerceAtMost(5.toLong(), 4.toLong()))

        assertEquals(5.toFloat(), Rangex.coerceAtMost(5.toFloat(), 6.toFloat()), 0.toFloat())
        assertEquals(5.toFloat().coerceAtMost(6.toFloat()), Rangex.coerceAtMost(5.toFloat(), 6.toFloat()), 0.toFloat())
        assertEquals(4.toFloat(), Rangex.coerceAtMost(5.toFloat(), 4.toFloat()), 0.toFloat())
        assertEquals(5.toFloat().coerceAtMost(4.toFloat()), Rangex.coerceAtMost(5.toFloat(), 4.toFloat()), 0.toFloat())

        assertEquals(5.toDouble(), Rangex.coerceAtMost(5.toDouble(), 6.toDouble()), 0.toDouble())
        assertEquals(5.toDouble().coerceAtMost(6.toDouble()), Rangex.coerceAtMost(5.toDouble(), 6.toDouble()), 0.toDouble())
        assertEquals(4.toDouble(), Rangex.coerceAtMost(5.toDouble(), 4.toDouble()), 0.toDouble())
        assertEquals(5.toDouble().coerceAtMost(4.toDouble()), Rangex.coerceAtMost(5.toDouble(), 4.toDouble()), 0.toDouble())

        assertEquals("5", Rangex.coerceAtMost("5", "6"))
        assertEquals("5".coerceAtMost("6"), Rangex.coerceAtMost("5", "6"))
        assertEquals("4", Rangex.coerceAtMost("5", "4"))
        assertEquals("5".coerceAtMost("4"), Rangex.coerceAtMost("5", "4"))
    }

    @Test
    fun testCoerceIn() {
        assertEquals(5.toByte(), Rangex.coerceIn(5.toByte(), 4.toByte(), 6.toByte()))
        assertEquals(5.toByte().coerceIn(4.toByte(), 6.toByte()), Rangex.coerceIn(5.toByte(), 4.toByte(), 6.toByte()))
        assertEquals(4.toByte(), Rangex.coerceIn(3.toByte(), 4.toByte(), 6.toByte()))
        assertEquals(3.toByte().coerceIn(4.toByte(), 6.toByte()), Rangex.coerceIn(3.toByte(), 4.toByte(), 6.toByte()))
        assertEquals(6.toByte(), Rangex.coerceIn(7.toByte(), 4.toByte(), 6.toByte()))
        assertEquals(7.toByte().coerceIn(4.toByte(), 6.toByte()), Rangex.coerceIn(7.toByte(), 4.toByte(), 6.toByte()))
        try {
            Rangex.coerceIn(7.toByte(), 4.toByte(), 3.toByte())
            fail()
        } catch (e: Exception) {
        }

        assertEquals(5.toShort(), Rangex.coerceIn(5.toShort(), 4.toShort(), 6.toShort()))
        assertEquals(5.toShort().coerceIn(4.toShort(), 6.toShort()), Rangex.coerceIn(5.toShort(), 4.toShort(), 6.toShort()))
        assertEquals(4.toShort(), Rangex.coerceIn(3.toShort(), 4.toShort(), 6.toShort()))
        assertEquals(3.toShort().coerceIn(4.toShort(), 6.toShort()), Rangex.coerceIn(3.toShort(), 4.toShort(), 6.toShort()))
        assertEquals(6.toShort(), Rangex.coerceIn(7.toShort(), 4.toShort(), 6.toShort()))
        assertEquals(7.toShort().coerceIn(4.toShort(), 6.toShort()), Rangex.coerceIn(7.toShort(), 4.toShort(), 6.toShort()))
        try {
            Rangex.coerceIn(7.toShort(), 4.toShort(), 3.toShort())
            fail()
        } catch (e: Exception) {
        }

        assertEquals(5, Rangex.coerceIn(5, 4, 6))
        assertEquals(5.coerceIn(4, 6), Rangex.coerceIn(5, 4, 6))
        assertEquals(4, Rangex.coerceIn(3, 4, 6))
        assertEquals(3.coerceIn(4, 6), Rangex.coerceIn(3, 4, 6))
        assertEquals(6, Rangex.coerceIn(7, 4, 6))
        assertEquals(7.coerceIn(4, 6), Rangex.coerceIn(7, 4, 6))
        try {
            Rangex.coerceIn(7, 4, 3)
            fail()
        } catch (e: Exception) {
        }

        assertEquals(5.toLong(), Rangex.coerceIn(5.toLong(), 4.toLong(), 6.toLong()))
        assertEquals(5.toLong().coerceIn(4.toLong(), 6.toLong()), Rangex.coerceIn(5.toLong(), 4.toLong(), 6.toLong()))
        assertEquals(4.toLong(), Rangex.coerceIn(3.toLong(), 4.toLong(), 6.toLong()))
        assertEquals(3.toLong().coerceIn(4.toLong(), 6.toLong()), Rangex.coerceIn(3.toLong(), 4.toLong(), 6.toLong()))
        assertEquals(6.toLong(), Rangex.coerceIn(7.toLong(), 4.toLong(), 6.toLong()))
        assertEquals(7.toLong().coerceIn(4.toLong(), 6.toLong()), Rangex.coerceIn(7.toLong(), 4.toLong(), 6.toLong()))
        try {
            Rangex.coerceIn(7.toLong(), 4.toLong(), 3.toLong())
            fail()
        } catch (e: Exception) {
        }

        assertEquals(5.toFloat(), Rangex.coerceIn(5.toFloat(), 4.toFloat(), 6.toFloat()), 0.0f)
        assertEquals(5.toFloat().coerceIn(4.toFloat(), 6.toFloat()), Rangex.coerceIn(5.toFloat(), 4.toFloat(), 6.toFloat()), 0.0f)
        assertEquals(4.toFloat(), Rangex.coerceIn(3.toFloat(), 4.toFloat(), 6.toFloat()), 0.0f)
        assertEquals(3.toFloat().coerceIn(4.toFloat(), 6.toFloat()), Rangex.coerceIn(3.toFloat(), 4.toFloat(), 6.toFloat()), 0.0f)
        assertEquals(6.toFloat(), Rangex.coerceIn(7.toFloat(), 4.toFloat(), 6.toFloat()), 0.0f)
        assertEquals(7.toFloat().coerceIn(4.toFloat(), 6.toFloat()), Rangex.coerceIn(7.toFloat(), 4.toFloat(), 6.toFloat()), 0.0f)
        try {
            Rangex.coerceIn(7.toFloat(), 4.toFloat(), 3.toFloat())
            fail()
        } catch (e: Exception) {
        }

        assertEquals(5.toDouble(), Rangex.coerceIn(5.toDouble(), 4.toDouble(), 6.toDouble()), 0.0)
        assertEquals(5.toDouble().coerceIn(4.toDouble(), 6.toDouble()), Rangex.coerceIn(5.toDouble(), 4.toDouble(), 6.toDouble()), 0.0)
        assertEquals(4.toDouble(), Rangex.coerceIn(3.toDouble(), 4.toDouble(), 6.toDouble()), 0.0)
        assertEquals(3.toDouble().coerceIn(4.toDouble(), 6.toDouble()), Rangex.coerceIn(3.toDouble(), 4.toDouble(), 6.toDouble()), 0.0)
        assertEquals(6.toDouble(), Rangex.coerceIn(7.toDouble(), 4.toDouble(), 6.toDouble()), 0.0)
        assertEquals(7.toDouble().coerceIn(4.toDouble(), 6.toDouble()), Rangex.coerceIn(7.toDouble(), 4.toDouble(), 6.toDouble()), 0.0)
        try {
            Rangex.coerceIn(7.toDouble(), 4.toDouble(), 3.toDouble())
            fail()
        } catch (e: Exception) {
        }

        assertEquals("5".coerceIn("4", "6"), Rangex.coerceIn("5", "4", "6"))
        assertEquals("5".coerceIn("4", "6"), Rangex.coerceIn("5", "4", "6"))
        assertEquals("3".coerceIn("4", "6"), Rangex.coerceIn("3", "4", "6"))
        assertEquals("3".coerceIn("4", "6"), Rangex.coerceIn("3", "4", "6"))
        assertEquals("7".coerceIn("4", "6"), Rangex.coerceIn("7", "4", "6"))
        assertEquals("7".coerceIn("4", "6"), Rangex.coerceIn("7", "4", "6"))
        assertEquals("3".coerceIn(null, "6"), Rangex.coerceIn("3", null, "6"))
        assertEquals("3".coerceIn(null, "2"), Rangex.coerceIn("3", null, "2"))
        assertEquals("7".coerceIn("4", null), Rangex.coerceIn("7", "4", null))
        assertEquals("7".coerceIn("8", null), Rangex.coerceIn("7", "8", null))
        assertEquals("7".coerceIn(null, null), Rangex.coerceIn("7", null, null))
        try {
            Rangex.coerceIn("7", "4", "3")
            fail()
        } catch (e: Exception) {
        }

        assertEquals(5, Rangex.coerceIn(5, Rangex.rangeTo(4, 6)))
        assertEquals(5.coerceIn(4..6), Rangex.coerceIn(5, Rangex.rangeTo(4, 6)))
        assertEquals(4, Rangex.coerceIn(3, Rangex.rangeTo(4, 6)))
        assertEquals(3.coerceIn(4..6), Rangex.coerceIn(3, Rangex.rangeTo(4, 6)))
        assertEquals(6, Rangex.coerceIn(7, Rangex.rangeTo(4, 6)))
        assertEquals(7.coerceIn(4..6), Rangex.coerceIn(7, Rangex.rangeTo(4, 6)))
        try {
            Rangex.coerceIn(7, Rangex.rangeTo(4, 3))
            fail()
        } catch (e: Exception) {
        }

        assertEquals(5.toLong(), Rangex.coerceIn(5.toLong(), Rangex.rangeTo(4.toLong(), 6.toLong())))
        assertEquals(5.toLong().coerceIn(4.toLong()..6.toLong()), Rangex.coerceIn(5.toLong(), Rangex.rangeTo(4.toLong(), 6.toLong())))
        assertEquals(4.toLong(), Rangex.coerceIn(3.toLong(), Rangex.rangeTo(4.toLong(), 6.toLong())))
        assertEquals(3.toLong().coerceIn(4.toLong()..6.toLong()), Rangex.coerceIn(3.toLong(), Rangex.rangeTo(4.toLong(), 6.toLong())))
        assertEquals(6.toLong(), Rangex.coerceIn(7.toLong(), Rangex.rangeTo(4.toLong(), 6.toLong())))
        assertEquals(7.toLong().coerceIn(4.toLong()..6.toLong()), Rangex.coerceIn(7.toLong(), Rangex.rangeTo(4.toLong(), 6.toLong())))
        try {
            Rangex.coerceIn(7.toLong(), Rangex.rangeTo(4.toLong(), 3.toLong()))
            fail()
        } catch (e: Exception) {
        }

        assertEquals(5f, Rangex.coerceIn(5f, Rangex.rangeTo(4f, 6f)))
        assertEquals(5f, Rangex.coerceIn(5f, Rangex.rangeTo(5f, 10f)))
        assertEquals(10f, Rangex.coerceIn(10f, Rangex.rangeTo(5f, 10f)))
        assertEquals(5f.coerceIn(4f..6f), Rangex.coerceIn(5f, Rangex.rangeTo(4f, 6f)))
        assertEquals(4f, Rangex.coerceIn(3f, Rangex.rangeTo(4f, 6f)))
        assertEquals(3f.coerceIn(4f..6f), Rangex.coerceIn(3f, Rangex.rangeTo(4f, 6f)))
        assertEquals(6f, Rangex.coerceIn(7f, Rangex.rangeTo(4f, 6f)))
        assertEquals(7f.coerceIn(4f..6f), Rangex.coerceIn(7f, Rangex.rangeTo(4f, 6f)))
        try {
            Rangex.coerceIn(7f, Rangex.rangeTo(4f, 3f))
            fail()
        } catch (e: Exception) {
            if (!(e is IllegalArgumentException && e.message.orEmpty().startsWith("Cannot coerce value to an empty range:"))) {
                fail()
            }
        }

        assertEquals(5f.coerceIn(4f..6f), Rangex.coerceIn(5f, Rangex.rangeTo(4f, 6f) as ClosedRange<Float>))
        assertEquals("5".coerceIn("4".."6"), Rangex.coerceIn("5", Rangex.rangeTo("4", "6") as ClosedRange<String>))
        try {
            Rangex.coerceIn("5", Rangex.rangeTo("4", "3") as ClosedRange<String>)
            fail()
        } catch (e: Exception) {
            if (!(e is IllegalArgumentException && e.message.orEmpty().startsWith("Cannot coerce value to an empty range:"))) {
                fail()
            }
        }
        assertEquals("5".coerceIn("6".."7"), Rangex.coerceIn("5", Rangex.rangeTo("6", "7") as ClosedRange<String>))
        assertEquals("8".coerceIn("6".."7"), Rangex.coerceIn("8", Rangex.rangeTo("6", "7") as ClosedRange<String>))
    }
}