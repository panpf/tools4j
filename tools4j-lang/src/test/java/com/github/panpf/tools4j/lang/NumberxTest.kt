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
package com.github.panpf.tools4j.lang

import org.junit.Assert
import org.junit.Test

class NumberxTest {

    @Test
    fun testRequireNotZero() {
        Numberx.requireNotZero(1.toByte())
        try {
            Numberx.requireNotZero(0.toByte())
            Assert.fail()
        } catch (ignored: Exception) {
        }
        Numberx.requireNotZero(1.toShort())
        try {
            Numberx.requireNotZero(0.toShort())
            Assert.fail()
        } catch (ignored: Exception) {
        }
        Numberx.requireNotZero(1)
        try {
            Numberx.requireNotZero(0)
            Assert.fail()
        } catch (ignored: Exception) {
        }
        Numberx.requireNotZero(1L)
        try {
            Numberx.requireNotZero(0L)
            Assert.fail()
        } catch (ignored: Exception) {
        }
        Numberx.requireNotZero(1f)
        try {
            Numberx.requireNotZero(0f)
            Assert.fail()
        } catch (ignored: Exception) {
        }
        Numberx.requireNotZero(1.0)
        try {
            Numberx.requireNotZero(0.0)
            Assert.fail()
        } catch (ignored: Exception) {
        }
    }

    @Test
    fun testPad() {
        Assert.assertEquals(Numberx.pad(10, 5), "00010")
        Assert.assertEquals(Numberx.pad(10L, 5), "00010")
    }

    @Test
    fun testOrZero() {
        Assert.assertEquals(0.toByte(), Numberx.orZero((null as Byte?)))
        Assert.assertEquals(1.toByte(), Numberx.orZero(1.toByte()))

        Assert.assertEquals(0.toShort(), Numberx.orZero((null as Short?)))
        Assert.assertEquals(1.toShort(), Numberx.orZero(1.toShort()))

        Assert.assertEquals(0.toInt(), Numberx.orZero((null as Int?)))
        Assert.assertEquals(1.toInt(), Numberx.orZero(1.toInt()))

        Assert.assertEquals(0.toLong(), Numberx.orZero((null as Long?)))
        Assert.assertEquals(1.toLong(), Numberx.orZero(1.toLong()))

        Assert.assertEquals(0.toFloat(), Numberx.orZero((null as Float?)))
        Assert.assertEquals(1.toFloat(), Numberx.orZero(1.toFloat()))

        Assert.assertEquals(0.toDouble(), Numberx.orZero((null as Double?)), 0.toDouble())
        Assert.assertEquals(1.toDouble(), Numberx.orZero(1.toDouble()), 0.toDouble())
    }

    @Test
    fun testTo() {
        Assert.assertEquals(Numberx.toByteOrDefault("5", 4.toByte()).toString(), 5.toString())
        Assert.assertEquals(Numberx.toByteOrDefault("g", 4.toByte()).toString(), 4.toString())
        Assert.assertEquals(Numberx.toByteOrZero("g").toString(), 0.toString())
        Assert.assertEquals(Numberx.toByteOrDefault("", 4.toByte()).toString(), 4.toString())
        Assert.assertEquals(Numberx.toByteOrDefault(null, 4.toByte()).toString(), 4.toString())
        Assert.assertEquals(Numberx.toShortOrDefault("5", 4.toShort()).toString(), 5.toString())
        Assert.assertEquals(Numberx.toShortOrDefault("g", 4.toShort()).toString(), 4.toString())
        Assert.assertEquals(Numberx.toShortOrZero("g").toString(), 0.toString())
        Assert.assertEquals(Numberx.toShortOrDefault("", 4.toShort()).toString(), 4.toString())
        Assert.assertEquals(Numberx.toShortOrDefault(null, 4.toShort()).toString(), 4.toString())
        Assert.assertEquals(Numberx.toIntOrDefault("5", 4).toString(), 5.toString())
        Assert.assertEquals(Numberx.toIntOrDefault("g", 4).toString(), 4.toString())
        Assert.assertEquals(Numberx.toIntOrZero("g").toString(), 0.toString())
        Assert.assertEquals(Numberx.toIntOrDefault("", 4).toString(), 4.toString())
        Assert.assertEquals(Numberx.toIntOrDefault(null, 4).toString(), 4.toString())
        Assert.assertEquals(Numberx.toLongOrDefault("5", 4).toString(), 5.toString())
        Assert.assertEquals(Numberx.toLongOrDefault("g", 4).toString(), 4.toString())
        Assert.assertEquals(Numberx.toLongOrZero("g").toString(), 0.toString())
        Assert.assertEquals(Numberx.toLongOrDefault("", 4).toString(), 4.toString())
        Assert.assertEquals(Numberx.toLongOrDefault(null, 4).toString(), 4.toString())
        Assert.assertEquals(Numberx.toFloatOrDefault("5.5", 4.5f).toString(), 5.5f.toString())
        Assert.assertEquals(Numberx.toFloatOrDefault("g", 4.4f).toString(), 4.4f.toString())
        Assert.assertEquals(Numberx.toFloatOrZero("g").toString(), 0.0f.toString())
        Assert.assertEquals(Numberx.toFloatOrDefault("", 4.4f).toString(), 4.4f.toString())
        Assert.assertEquals(Numberx.toFloatOrDefault(null, 4.4f).toString(), 4.4f.toString())
        Assert.assertEquals(Numberx.toDoubleOrDefault("5.5", 4.4).toString(), 5.5.toString())
        Assert.assertEquals(Numberx.toDoubleOrDefault("g", 4.4).toString(), 4.4.toString())
        Assert.assertEquals(Numberx.toDoubleOrZero("g").toString(), 0.0.toString())
        Assert.assertEquals(Numberx.toDoubleOrDefault("", 4.4).toString(), 4.4.toString())
        Assert.assertEquals(Numberx.toDoubleOrDefault(null, 4.4).toString(), 4.4.toString())
    }
}