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

class CharProgressionTest {

    @Test
    fun testFromClosedRange() {
        CharProgression.fromClosedRange(10.toChar(), 15.toChar(), 1)
        CharProgression.fromClosedRange(10.toChar(), 15.toChar(), -1)
        try {
            CharProgression.fromClosedRange(10.toChar(), 15.toChar(), 0)
            fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testIterator() {
        assertEquals("10, 11, 12, 13, 14, 15", CharProgression(10.toChar(), 15.toChar(), 1).joinToString { it.toInt().toString() })
        assertEquals("10, 12, 14, 16, 18, 20", CharProgression(10.toChar(), 20.toChar(), 2).joinToString { it.toInt().toString() })
        assertEquals("20, 17, 14, 11", CharProgression(20.toChar(), 10.toChar(), -3).joinToString { it.toInt().toString() })
        assertEquals("", CharProgression(20.toChar(), 10.toChar(), 3).joinToString { it.toInt().toString() })
        assertEquals("", CharProgression(10.toChar(), 20.toChar(), -3).joinToString { it.toInt().toString() })

        val iterator = CharProgression(10.toChar(), 10.toChar(), 1).iterator()
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
        assertFalse(CharProgression(10.toChar(), 15.toChar(), 1).isEmpty)
        assertFalse(CharProgression(15.toChar(), 10.toChar(), -1).isEmpty)
        assertTrue(CharProgression(10.toChar(), 15.toChar(), -1).isEmpty)
        assertTrue(CharProgression(15.toChar(), 10.toChar(), 1).isEmpty)
    }

    @Test
    fun testGet() {
        val range1 = CharProgression(10.toChar(), 15.toChar(), 1)
        assertEquals(10, range1.first.toInt())
        assertEquals(15, range1.last.toInt())
        assertEquals(1, range1.step.toInt())

        val intProgression2 = CharProgression(99.toChar(), 40.toChar(), -5)
        assertEquals(99, intProgression2.first.toInt())
        assertEquals(44, intProgression2.last.toInt())
        assertEquals(-5, intProgression2.step.toInt())
    }

    @Test
    fun testEquals() {
        val range1 = CharProgression(10.toChar(), 15.toChar(), 1)
        assertTrue(range1 == range1)

        @Suppress("ReplaceCallWithBinaryOperator")
        assertFalse(CharProgression(10.toChar(), 15.toChar(), 1).equals(null))
        assertFalse(CharProgression(10.toChar(), 15.toChar(), 1) == Date())

        assertTrue(CharProgression(15.toChar(), 10.toChar(), 1) == CharProgression(40.toChar(), 99.toChar(), -5))
        assertFalse(CharProgression(10.toChar(), 15.toChar(), 1) == CharProgression(15.toChar(), 10.toChar(), 1))
        assertFalse(CharProgression(15.toChar(), 10.toChar(), 1) == CharProgression(10.toChar(), 15.toChar(), 1))

        assertFalse(CharProgression(10.toChar(), 15.toChar(), 1) == CharProgression(11.toChar(), 15.toChar(), 1))
        assertFalse(CharProgression(10.toChar(), 15.toChar(), 1) == CharProgression(10.toChar(), 16.toChar(), 1))
        assertFalse(CharProgression(10.toChar(), 15.toChar(), 1) == CharProgression(10.toChar(), 15.toChar(), 2))
        assertTrue(CharProgression(10.toChar(), 15.toChar(), 1) == CharProgression(10.toChar(), 15.toChar(), 1))
    }

    @Test
    fun testHashCode() {
        assertEquals(kotlin.ranges.CharProgression.fromClosedRange(10.toChar(), 15.toChar(), 1).hashCode(), CharProgression.fromClosedRange(10.toChar(), 15.toChar(), 1).hashCode())
        assertEquals(kotlin.ranges.CharProgression.fromClosedRange(99.toChar(), 40.toChar(), -5).hashCode(), CharProgression.fromClosedRange(99.toChar(), 40.toChar(), -5).hashCode())
        assertEquals(kotlin.ranges.CharProgression.fromClosedRange(15.toChar(), 10.toChar(), 1).hashCode(), CharProgression.fromClosedRange(15.toChar(), 10.toChar(), 1).hashCode())
        assertEquals(kotlin.ranges.CharProgression.fromClosedRange(40.toChar(), 99.toChar(), -5).hashCode(), CharProgression.fromClosedRange(40.toChar(), 99.toChar(), -5).hashCode())
    }

    @Test
    fun testToString() {
        assertEquals(kotlin.ranges.CharProgression.fromClosedRange(10.toChar(), 15.toChar(), 1).toString(), CharProgression.fromClosedRange(10.toChar(), 15.toChar(), 1).toString())
        assertEquals(kotlin.ranges.CharProgression.fromClosedRange(99.toChar(), 40.toChar(), -5).toString(), CharProgression.fromClosedRange(99.toChar(), 40.toChar(), -5).toString())
    }
}