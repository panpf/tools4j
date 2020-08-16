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

package com.github.panpf.tools4j.ranges.ktx

import org.junit.Assert
import org.junit.Test

class RangexTest {

    @Test
    fun testByteRange() {
        Assert.assertEquals(1.toByte().rangeTo(10.toByte(), 4).count().toLong(), 3)
        Assert.assertEquals(10.toByte().rangeTo(1.toByte(), (-4)).count().toLong(), 3)
        Assert.assertEquals(1.toByte().until(11.toByte(), 4).count().toLong(), 3)
        Assert.assertEquals(11.toByte().until(1.toByte(), -4).count().toLong(), 3)
    }

    @Test
    fun testShortRange() {
        Assert.assertEquals(1.toShort().rangeTo(10.toShort(), 4).count().toLong(), 3)
        Assert.assertEquals(10.toShort().rangeTo(1.toShort(), (-4)).count().toLong(), 3)
        Assert.assertEquals(1.toShort().until(11.toShort(), 4).count().toLong(), 3)
        Assert.assertEquals(11.toShort().until(1.toShort(), -4).count().toLong(), 3)
    }

    @Test
    fun testIntRange() {
        Assert.assertEquals(1.rangeTo(10, 4).count().toLong(), 3)
        Assert.assertEquals(10.rangeTo(1, (-4)).count().toLong(), 3)
        Assert.assertEquals(1.until(11, 4).count().toLong(), 3)
        Assert.assertEquals(11.until(1, -4).count().toLong(), 3)
    }

    @Test
    fun testLongRange() {
        Assert.assertEquals(1L.rangeTo(10L, 4).count().toLong(), 3)
        Assert.assertEquals(10L.rangeTo(1L, (-4)).count().toLong(), 3)
        Assert.assertEquals(1L.until(11L, 4).count().toLong(), 3)
        Assert.assertEquals(11L.until(1L, -4).count().toLong(), 3)
    }

    @Test
    fun testCharRange() {
        Assert.assertEquals(1.toChar().rangeTo(10.toChar(), 4).count().toLong(), 3)
        Assert.assertEquals(10.toChar().rangeTo(1.toChar(), (-4)).count().toLong(), 3)
        Assert.assertEquals(1.toChar().until(11.toChar(), 4).count().toLong(), 3)
        Assert.assertEquals(11.toChar().until(1.toChar(), -4).count().toLong(), 3)
    }

    @Test
    fun testRequireInRange() {
        2.toByte().requireInRange(0.toByte(), 5.toByte())
        try {
            (-1).toByte().requireInRange(0.toByte(), 5.toByte())
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            6.toByte().requireInRange(0.toByte(), 5.toByte())
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        2.toShort().requireInRange(0.toShort(), 5.toShort())
        try {
            (-1).toShort().requireInRange(0.toShort(), 5.toShort())
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            6.toShort().requireInRange(0.toShort(), 5.toShort())
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        2.requireInRange(0, 5)
        try {
            (-1).requireInRange(0, 5)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            6.requireInRange(0, 5)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        2L.requireInRange(0L, 5L)
        try {
            (-1L).requireInRange(0L, 5L)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            6L.requireInRange(0L, 5L)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        2f.requireInRange(0f, 5f)
        try {
            (-1f).requireInRange(0f, 5f)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            6f.requireInRange(0f, 5f)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        2.0.requireInRange(0.0, 5.0)
        try {
            (-1.0).requireInRange(0.0, 5.0)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        try {
            6.0.requireInRange(0.0, 5.0)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
    }

    @Test
    fun testRequireNotInRange() {
        6.toByte().requireNotInRange(0.toByte(), 5.toByte())
        (-1).toByte().requireNotInRange(0.toByte(), 5.toByte())
        try {
            2.toByte().requireNotInRange(0.toByte(), 5.toByte())
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        6.toShort().requireNotInRange(0.toShort(), 5.toShort())
        (-1).toShort().requireNotInRange(0.toShort(), 5.toShort())
        try {
            2.toShort().requireNotInRange(0.toShort(), 5.toShort())
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        6.requireNotInRange(0, 5)
        (-1).requireNotInRange(0, 5)
        try {
            2.requireNotInRange(0, 5)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        6L.requireNotInRange(0L, 5L)
        (-1L).requireNotInRange(0L, 5L)
        try {
            2L.requireNotInRange(0L, 5L)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        6f.requireNotInRange(0f, 5f)
        (-1f).requireNotInRange(0f, 5f)
        try {
            2f.requireNotInRange(0f, 5f)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
        6.0.requireNotInRange(0.0, 5.0)
        (-1.0).requireNotInRange(0.0, 5.0)
        try {
            2.0.requireNotInRange(0.0, 5.0)
            Assert.fail()
        } catch (ignored: java.lang.Exception) {
        }
    }
}