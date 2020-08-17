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

class ComparableRangeTest {

    @Test
    fun testGet() {
        val range = ComparableRange(Date(10), Date(15))
        Assert.assertEquals(Date(10), range.start)
        Assert.assertEquals(Date(15), range.endInclusive)

        val range1 = ComparableRange(99f, 40f)
        Assert.assertEquals(99f, range1.start)
        Assert.assertEquals(40f, range1.endInclusive)
    }

    @Test
    fun testContains() {
        Assert.assertTrue(ComparableRange(Date(10), Date(15)).contains(Date(10)))
        Assert.assertTrue(ComparableRange(Date(10), Date(15)).contains(Date(11)))
        Assert.assertTrue(ComparableRange(Date(10), Date(15)).contains(Date(15)))
        Assert.assertFalse(ComparableRange(Date(10), Date(15)).contains(Date(9)))
        Assert.assertFalse(ComparableRange(Date(10), Date(15)).contains(Date(16)))
    }

    @Test
    fun testIsEmpty() {
        Assert.assertTrue(ComparableRange(Date(15), Date(10)).isEmpty)
        Assert.assertFalse(ComparableRange(Date(10), Date(15)).isEmpty)
    }

    @Test
    fun testHashCode() {
        Assert.assertEquals((Date(10)..Date(15)).hashCode(), ComparableRange(Date(10), Date(15)).hashCode())
        Assert.assertEquals((Date(99)..Date(40)).hashCode(), ComparableRange(Date(99), Date(40)).hashCode())
    }

    @Test
    fun testToString() {
        Assert.assertEquals((Date(10)..Date(15)).toString(), ComparableRange(Date(10), Date(15)).toString())
        Assert.assertEquals((Date(99)..Date(40)).toString(), ComparableRange(Date(99), Date(40)).toString())
    }

    @Test
    fun testEquals() {
        val range1 = ComparableRange(Date(10), Date(15))
        Assert.assertTrue(range1 == range1)

        @Suppress("ReplaceCallWithBinaryOperator")
        (Assert.assertFalse(ComparableRange(Date(10), Date(15)).equals(null)))
        Assert.assertFalse(ComparableRange(Date(10), Date(15)) == Date())

        Assert.assertTrue(ComparableRange(Date(15), Date(10)) == ComparableRange(99f, 40f))
        Assert.assertFalse(ComparableRange(Date(10), Date(15)) == ComparableRange(Date(15), Date(10)))
        Assert.assertFalse(ComparableRange(Date(15), Date(10)) == ComparableRange(Date(10), Date(15)))

        Assert.assertFalse(ComparableRange(Date(10), Date(15)) == ComparableRange(Date(11), Date(15)))
        Assert.assertFalse(ComparableRange(Date(10), Date(15)) == ComparableRange(Date(10), Date(16)))
        Assert.assertTrue(ComparableRange(Date(10), Date(15)) == ComparableRange(Date(10), Date(15)))
    }
}