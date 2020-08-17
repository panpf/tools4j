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

import org.junit.Assert
import org.junit.Test
import java.util.*

class ClosedDoubleRangeTest {

    @Test
    fun testGet() {
        val range = ClosedDoubleRange(10.0, 15.0)
        Assert.assertEquals(10.0, range.start, 0.0)
        Assert.assertEquals(15.0, range.endInclusive, 0.0)

        val range1 = ClosedDoubleRange(99.0, 40.0)
        Assert.assertEquals(99.0, range1.start, 0.0)
        Assert.assertEquals(40.0, range1.endInclusive, 0.0)
    }

    @Test
    fun testContains() {
        Assert.assertTrue(ClosedDoubleRange(10.0, 15.0).contains(10.0))
        Assert.assertTrue(ClosedDoubleRange(10.0, 15.0).contains(11.0))
        Assert.assertTrue(ClosedDoubleRange(10.0, 15.0).contains(15.0))
        Assert.assertFalse(ClosedDoubleRange(10.0, 15.0).contains(9.0))
        Assert.assertFalse(ClosedDoubleRange(10.0, 15.0).contains(16.0))
    }

    @Test
    fun testIsEmpty() {
        Assert.assertTrue(ClosedDoubleRange(15.0, 10.0).isEmpty)
        Assert.assertFalse(ClosedDoubleRange(10.0, 15.0).isEmpty)
    }

    @Test
    fun testHashCode() {
        Assert.assertEquals((10.0..15.0).hashCode(), ClosedDoubleRange(10.0, 15.0).hashCode())
        Assert.assertEquals((99.0..40.0).hashCode(), ClosedDoubleRange(99.0, 40.0).hashCode())
    }

    @Test
    fun testToString() {
        Assert.assertEquals((10.0..15.0).toString(), ClosedDoubleRange(10.0, 15.0).toString())
        Assert.assertEquals((99.0..40.0).toString(), ClosedDoubleRange(99.0, 40.0).toString())
    }

    @Test
    fun testEquals() {
        val range1 = ClosedDoubleRange(10.0, 15.0)
        Assert.assertTrue(range1 == range1)

        @Suppress("ReplaceCallWithBinaryOperator")
        (Assert.assertFalse(ClosedDoubleRange(10.0, 15.0).equals(null)))
        Assert.assertFalse(ClosedDoubleRange(10.0, 15.0) == Date())

        Assert.assertTrue(ClosedDoubleRange(15.0, 10.0) == ClosedDoubleRange(99.0, 40.0))
        Assert.assertFalse(ClosedDoubleRange(10.0, 15.0) == ClosedDoubleRange(15.0, 10.0))
        Assert.assertFalse(ClosedDoubleRange(15.0, 10.0) == ClosedDoubleRange(10.0, 15.0))

        Assert.assertFalse(ClosedDoubleRange(10.0, 15.0) == ClosedDoubleRange(11.0, 15.0))
        Assert.assertFalse(ClosedDoubleRange(10.0, 15.0) == ClosedDoubleRange(10.0, 16.0))
        Assert.assertTrue(ClosedDoubleRange(10.0, 15.0) == ClosedDoubleRange(10.0, 15.0))
    }
}