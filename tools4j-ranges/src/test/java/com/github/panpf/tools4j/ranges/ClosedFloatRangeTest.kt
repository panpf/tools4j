/*
 * Copyright (C) 2020 panpf <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2f (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2f
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.panpf.tools4j.ranges

import org.junit.Assert
import org.junit.Test
import java.util.*

class ClosedFloatRangeTest {

    @Test
    fun testGet() {
        val range = ClosedFloatRange(10f, 15f)
        Assert.assertEquals(10f, range.start, 0f)
        Assert.assertEquals(15f, range.endInclusive, 0f)

        val range1 = ClosedFloatRange(99f, 40f)
        Assert.assertEquals(99f, range1.start, 0f)
        Assert.assertEquals(40f, range1.endInclusive, 0f)
    }

    @Test
    fun testContains() {
        Assert.assertTrue(ClosedFloatRange(10f, 15f).contains(10f))
        Assert.assertTrue(ClosedFloatRange(10f, 15f).contains(11f))
        Assert.assertTrue(ClosedFloatRange(10f, 15f).contains(15f))
        Assert.assertFalse(ClosedFloatRange(10f, 15f).contains(9f))
        Assert.assertFalse(ClosedFloatRange(10f, 15f).contains(16f))
    }

    @Test
    fun testIsEmpty() {
        Assert.assertTrue(ClosedFloatRange(15f, 10f).isEmpty)
        Assert.assertFalse(ClosedFloatRange(10f, 15f).isEmpty)
    }

    @Test
    fun testHashCode() {
        Assert.assertEquals((10f..15f).hashCode(), ClosedFloatRange(10f, 15f).hashCode())
        Assert.assertEquals((99f..40f).hashCode(), ClosedFloatRange(99f, 40f).hashCode())
    }

    @Test
    fun testToString() {
        Assert.assertEquals((10f..15f).toString(), ClosedFloatRange(10f, 15f).toString())
        Assert.assertEquals((99f..40f).toString(), ClosedFloatRange(99f, 40f).toString())
    }

    @Test
    fun testEquals() {
        val range1 = ClosedFloatRange(10f, 15f)
        Assert.assertTrue(range1 == range1)

        @Suppress("ReplaceCallWithBinaryOperator")
        (Assert.assertFalse(ClosedFloatRange(10f, 15f).equals(null)))
        Assert.assertFalse(ClosedFloatRange(10f, 15f) == Date())

        Assert.assertTrue(ClosedFloatRange(15f, 10f) == ClosedFloatRange(99f, 40f))
        Assert.assertFalse(ClosedFloatRange(10f, 15f) == ClosedFloatRange(15f, 10f))
        Assert.assertFalse(ClosedFloatRange(15f, 10f) == ClosedFloatRange(10f, 15f))

        Assert.assertFalse(ClosedFloatRange(10f, 15f) == ClosedFloatRange(11f, 15f))
        Assert.assertFalse(ClosedFloatRange(10f, 15f) == ClosedFloatRange(10f, 16f))
        Assert.assertTrue(ClosedFloatRange(10f, 15f) == ClosedFloatRange(10f, 15f))
    }
}