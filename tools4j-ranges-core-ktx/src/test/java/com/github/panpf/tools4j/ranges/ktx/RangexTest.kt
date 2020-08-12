/*
 * Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>
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

package com.github.panpf.tools4j.ranges.ktx

import org.junit.Assert
import org.junit.Test

class RangexTest {

    // todo 测试 reversed 和 step

    @Test
    fun testByteRange() {
        Assert.assertEquals(1.toByte().rangeTo(10.toByte(), 4).count().toLong(), 3)
        Assert.assertEquals(10.toByte().rangeTo(1.toByte(), (-4)).count().toLong(), 3)
        Assert.assertEquals(1.toByte().until(11.toByte(), 4).count().toLong(), 3)
    }

    @Test
    fun testShortRange() {
        Assert.assertEquals(1.toShort().rangeTo(10.toShort(), 4).count().toLong(), 3)
        Assert.assertEquals(10.toShort().rangeTo(1.toShort(), (-4)).count().toLong(), 3)
        Assert.assertEquals(1.toShort().until(11.toShort(), 4).count().toLong(), 3)
    }

    @Test
    fun testIntRange() {
        Assert.assertEquals(1.rangeTo(10, 4).count().toLong(), 3)
        Assert.assertEquals(10.rangeTo(1, (-4)).count().toLong(), 3)
        Assert.assertEquals(1.until(11, 4).count().toLong(), 3)
    }

    @Test
    fun testLongRange() {
        Assert.assertEquals(1L.rangeTo(10L, 4).count().toLong(), 3)
        Assert.assertEquals(10L.rangeTo(1L, (-4)).count().toLong(), 3)
        Assert.assertEquals(1L.until(11L, 4).count().toLong(), 3)
    }

    @Test
    fun testCharRange() {
        Assert.assertEquals(1.toChar().rangeTo(10.toChar(), 4).count().toLong(), 3)
        Assert.assertEquals(10.toChar().rangeTo(1.toChar(), (-4)).count().toLong(), 3)
        Assert.assertEquals(1.toChar().until(11.toChar(), 4).count().toLong(), 3)
    }
}