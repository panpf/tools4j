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

class IntProgressionTest {

    @Test
    fun testFromClosedRange() {
        IntProgression.fromClosedRange(10, 15, 1)
        IntProgression.fromClosedRange(10, 15, -1)
        try {
            IntProgression.fromClosedRange(10, 15, 0)
            fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testIterator() {
        assertEquals("10, 11, 12, 13, 14, 15", IntProgression(10, 15, 1).joinToString())
        assertEquals("10, 12, 14, 16, 18, 20", IntProgression(10, 20, 2).joinToString())
        assertEquals("20, 17, 14, 11", IntProgression(20, 10, -3).joinToString())
        assertEquals("", IntProgression(20, 10, 3).joinToString())
        assertEquals("", IntProgression(10, 20, -3).joinToString())

        val iterator = IntProgression(10, 10, 1).iterator()
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
        assertFalse(IntProgression(10, 15, 1).isEmpty)
        assertFalse(IntProgression(15, 10, -1).isEmpty)
        assertTrue(IntProgression(10, 15, -1).isEmpty)
        assertTrue(IntProgression(15, 10, 1).isEmpty)
    }

    @Test
    fun testGet() {
        val intProgression1 = IntProgression(10, 15, 1)
        assertEquals(10, intProgression1.first)
        assertEquals(15, intProgression1.last)
        assertEquals(1, intProgression1.step)

        val intProgression2 = IntProgression(99, 40, -5)
        assertEquals(99, intProgression2.first)
        assertEquals(44, intProgression2.last)
        assertEquals(-5, intProgression2.step)
    }

    @Test
    fun testEquals() {
        val progression1 = IntProgression(10, 15, 1)
        assertTrue(progression1 == progression1)

        @Suppress("ReplaceCallWithBinaryOperator")
        assertFalse(IntProgression(10, 15, 1).equals(null))
        assertFalse(IntProgression(10, 15, 1) == Date())

        assertTrue(IntProgression(15, 10, 1) == IntProgression(40, 99, -5))
        assertFalse(IntProgression(10, 15, 1) == IntProgression(15, 10, 1))
        assertFalse(IntProgression(15, 10, 1) == IntProgression(10, 15, 1))

        assertFalse(IntProgression(10, 15, 1) == IntProgression(11, 15, 1))
        assertFalse(IntProgression(10, 15, 1) == IntProgression(10, 16, 1))
        assertFalse(IntProgression(10, 15, 1) == IntProgression(10, 15, 2))
        assertTrue(IntProgression(10, 15, 1) == IntProgression(10, 15, 1))
    }

    @Test
    fun testHashCode() {
        assertEquals(kotlin.ranges.IntProgression.fromClosedRange(10, 15, 1).hashCode(), IntProgression.fromClosedRange(10, 15, 1).hashCode())
        assertEquals(kotlin.ranges.IntProgression.fromClosedRange(99, 40, -5).hashCode(), IntProgression.fromClosedRange(99, 40, -5).hashCode())
        assertEquals(kotlin.ranges.IntProgression.fromClosedRange(15, 10, 1).hashCode(), IntProgression.fromClosedRange(15, 10, 1).hashCode())
        assertEquals(kotlin.ranges.IntProgression.fromClosedRange(40, 99, -5).hashCode(), IntProgression.fromClosedRange(40, 99, -5).hashCode())
    }

    @Test
    fun testToString() {
        assertEquals(kotlin.ranges.IntProgression.fromClosedRange(10, 15, 1).toString(), IntProgression.fromClosedRange(10, 15, 1).toString())
        assertEquals(kotlin.ranges.IntProgression.fromClosedRange(99, 40, -5).toString(), IntProgression.fromClosedRange(99, 40, -5).toString())
    }
}