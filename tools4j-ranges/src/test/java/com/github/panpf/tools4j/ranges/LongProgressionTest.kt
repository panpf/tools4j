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

@file:Suppress("UnusedImport")

package com.github.panpf.tools4j.ranges

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class LongProgressionTest {

    @Test
    fun testFromClosedRange() {
        LongProgression.fromClosedRange(10, 15, 1)
        LongProgression.fromClosedRange(10, 15, -1)
        try {
            LongProgression.fromClosedRange(10, 15, 0)
            fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testIterator() {
        assertEquals("10, 11, 12, 13, 14, 15", LongProgression(10, 15, 1).joinToString())
        assertEquals("10, 12, 14, 16, 18, 20", LongProgression(10, 20, 2).joinToString())
        assertEquals("20, 17, 14, 11", LongProgression(20, 10, -3).joinToString())
        assertEquals("", LongProgression(20, 10, 3).joinToString())
        assertEquals("", LongProgression(10, 20, -3).joinToString())

        val iterator = LongProgression(10, 10, 1).iterator()
        iterator.next()
        try {
            iterator.next()
            fail()
        } catch (e: Exception) {
        }
        try {
            iterator.remove()
            fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testIsEmpty() {
        assertFalse(LongProgression(10, 15, 1).isEmpty)
        assertFalse(LongProgression(15, 10, -1).isEmpty)
        assertTrue(LongProgression(10, 15, -1).isEmpty)
        assertTrue(LongProgression(15, 10, 1).isEmpty)
    }

    @Test
    fun testGet() {
        val range1 = LongProgression(10, 15, 1)
        assertEquals(10, range1.first)
        assertEquals(15, range1.last)
        assertEquals(1, range1.step)

        val intProgression2 = LongProgression(99, 40, -5)
        assertEquals(99, intProgression2.first)
        assertEquals(44, intProgression2.last)
        assertEquals(-5, intProgression2.step)
    }

    @Test
    fun testEquals() {
        val range1 = LongProgression(10, 15, 1)
        assertTrue(range1 == range1)

        @Suppress("ReplaceCallWithBinaryOperator")
        assertFalse(LongProgression(10, 15, 1).equals(null))
        assertFalse(LongProgression(10, 15, 1) == Date())

        assertTrue(LongProgression(15, 10, 1) == LongProgression(40, 99, -5))
        assertFalse(LongProgression(10, 15, 1) == LongProgression(15, 10, 1))
        assertFalse(LongProgression(15, 10, 1) == LongProgression(10, 15, 1))

        assertFalse(LongProgression(10, 15, 1) == LongProgression(11, 15, 1))
        assertFalse(LongProgression(10, 15, 1) == LongProgression(10, 16, 1))
        assertFalse(LongProgression(10, 15, 1) == LongProgression(10, 15, 2))
        assertTrue(LongProgression(10, 15, 1) == LongProgression(10, 15, 1))
    }

    @Test
    fun testHashCode() {
        assertEquals(kotlin.ranges.LongProgression.fromClosedRange(10, 15, 1).hashCode(), LongProgression.fromClosedRange(10, 15, 1).hashCode())
        assertEquals(kotlin.ranges.LongProgression.fromClosedRange(99, 40, -5).hashCode(), LongProgression.fromClosedRange(99, 40, -5).hashCode())
        assertEquals(kotlin.ranges.LongProgression.fromClosedRange(15, 10, 1).hashCode(), LongProgression.fromClosedRange(15, 10, 1).hashCode())
        assertEquals(kotlin.ranges.LongProgression.fromClosedRange(40, 99, -5).hashCode(), LongProgression.fromClosedRange(40, 99, -5).hashCode())
    }

    @Test
    fun testToString() {
        assertEquals(kotlin.ranges.LongProgression.fromClosedRange(10, 15, 1).toString(), LongProgression.fromClosedRange(10, 15, 1).toString())
        assertEquals(kotlin.ranges.LongProgression.fromClosedRange(99, 40, -5).toString(), LongProgression.fromClosedRange(99, 40, -5).toString())
    }
}