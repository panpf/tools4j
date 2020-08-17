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

class LongRangeTest {

    @Test
    fun testGet() {
        val range = LongRange(10, 15)
        Assert.assertEquals(10, range.start)
        Assert.assertEquals(15, range.endInclusive)
        Assert.assertEquals(1, range.step)

        val range1 = LongRange(99, 40)
        Assert.assertEquals(99, range1.start)
        Assert.assertEquals(40, range1.endInclusive)
        Assert.assertEquals(1, range1.step)
    }

    @Test
    fun testContains() {
        Assert.assertTrue(LongRange(10, 15).contains(10))
        Assert.assertTrue(LongRange(10, 15).contains(11))
        Assert.assertTrue(LongRange(10, 15).contains(15))
        Assert.assertFalse(LongRange(10, 15).contains(9))
        Assert.assertFalse(LongRange(10, 15).contains(16))
    }

    @Test
    fun testIsEmpty() {
        Assert.assertTrue(LongRange(15, 10).isEmpty)
        Assert.assertFalse(LongRange(10, 15).isEmpty)
    }

    @Test
    fun testHashCode() {
        Assert.assertEquals(kotlin.ranges.LongRange(10, 15).hashCode(), LongRange(10, 15).hashCode())
        Assert.assertEquals(kotlin.ranges.LongRange(99, 40).hashCode(), LongRange(99, 40).hashCode())
    }

    @Test
    fun testToString() {
        Assert.assertEquals(kotlin.ranges.LongRange(10, 15).toString(), LongRange(10, 15).toString())
        Assert.assertEquals(kotlin.ranges.LongRange(99, 40).toString(), LongRange(99, 40).toString())
    }
}