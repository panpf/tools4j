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

class CharRangeTest {

    @Test
    fun testGet() {
        val range = CharRange(10.toChar(), 15.toChar())
        Assert.assertEquals(10, range.start.toInt())
        Assert.assertEquals(15, range.endInclusive.toInt())
        Assert.assertEquals(1, range.step.toInt())

        val range1 = CharRange(99.toChar(), 40.toChar())
        Assert.assertEquals(99, range1.start.toInt())
        Assert.assertEquals(40, range1.endInclusive.toInt())
        Assert.assertEquals(1, range1.step.toInt())
    }

    @Test
    fun testContains() {
        Assert.assertTrue(CharRange(10.toChar(), 15.toChar()).contains(10.toChar()))
        Assert.assertTrue(CharRange(10.toChar(), 15.toChar()).contains(11.toChar()))
        Assert.assertTrue(CharRange(10.toChar(), 15.toChar()).contains(15.toChar()))
        Assert.assertFalse(CharRange(10.toChar(), 15.toChar()).contains(9.toChar()))
        Assert.assertFalse(CharRange(10.toChar(), 15.toChar()).contains(16.toChar()))
    }

    @Test
    fun testIsEmpty() {
        Assert.assertTrue(CharRange(15.toChar(), 10.toChar()).isEmpty)
        Assert.assertFalse(CharRange(10.toChar(), 15.toChar()).isEmpty)
    }

    @Test
    fun testHashCode() {
        Assert.assertEquals(kotlin.ranges.CharRange(10.toChar(), 15.toChar()).hashCode(), CharRange(10.toChar(), 15.toChar()).hashCode())
        Assert.assertEquals(kotlin.ranges.CharRange(99.toChar(), 40.toChar()).hashCode(), CharRange(99.toChar(), 40.toChar()).hashCode())
    }

    @Test
    fun testToString() {
        Assert.assertEquals(kotlin.ranges.CharRange(10.toChar(), 15.toChar()).toString(), CharRange(10.toChar(), 15.toChar()).toString())
        Assert.assertEquals(kotlin.ranges.CharRange(99.toChar(), 40.toChar()).toString(), CharRange(99.toChar(), 40.toChar()).toString())
    }
}