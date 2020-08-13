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

package com.github.panpf.tools4j.lang.ktx

import org.junit.Assert.*
import org.junit.Test

class NumberxTest {

    @Test
    fun testRequireNotZero() {
        1.toByte().requireNotZero()
        try {
            0.toByte().requireNotZero()
            fail()
        } catch (ignored: Exception) {
        }

        1.toShort().requireNotZero()
        try {
            0.toShort().requireNotZero()
            fail()
        } catch (ignored: Exception) {
        }

        1.requireNotZero()
        try {
            0.requireNotZero()
            fail()
        } catch (ignored: Exception) {
        }

        1L.requireNotZero()
        try {
            0L.requireNotZero()
            fail()
        } catch (ignored: Exception) {
        }

        1f.requireNotZero()
        try {
            0f.requireNotZero()
            fail()
        } catch (ignored: Exception) {
        }

        1.0.requireNotZero()
        try {
            0.0.requireNotZero()
            fail()
        } catch (ignored: Exception) {
        }
    }

    @Test
    fun testPad() {
        assertEquals(10.pad(5), "00010")
        assertEquals(10L.pad(5), "00010")
    }

    @Test
    fun testOrZero() {
        assertEquals(0.toByte(), (null as Byte?).orZero())
        assertEquals(1.toByte(), 1.toByte().orZero())

        assertEquals(0.toShort(), (null as Short?).orZero())
        assertEquals(1.toShort(), 1.toShort().orZero())

        assertEquals(0.toInt(), (null as Int?).orZero())
        assertEquals(1.toInt(), 1.toInt().orZero())

        assertEquals(0.toLong(), (null as Long?).orZero())
        assertEquals(1.toLong(), 1.toLong().orZero())

        assertEquals(0.toFloat(), (null as Float?).orZero())
        assertEquals(1.toFloat(), 1.toFloat().orZero())

        assertEquals(0.toDouble(), (null as Double?).orZero(), 0.toDouble())
        assertEquals(1.toDouble(), 1.toDouble().orZero(), 0.toDouble())
    }

    @Test
    fun testTo() {
        assertEquals("5".toByteOrDefault(4.toByte()).toString(), 5.toString())
        assertEquals("g".toByteOrDefault(4.toByte()).toString(), 4.toString())
        assertEquals("g".toByteOrZero().toString(), 0.toString())
        assertEquals("".toByteOrDefault(4.toByte()).toString(), 4.toString())
        assertEquals(null.toByteOrDefault(4.toByte()).toString(), 4.toString())

        assertEquals("5".toShortOrDefault(4.toShort()).toString(), 5.toString())
        assertEquals("g".toShortOrDefault(4.toShort()).toString(), 4.toString())
        assertEquals("g".toShortOrZero().toString(), 0.toString())
        assertEquals("".toShortOrDefault(4.toShort()).toString(), 4.toString())
        assertEquals(null.toShortOrDefault(4.toShort()).toString(), 4.toString())

        assertEquals("5".toIntOrDefault(4).toString(), 5.toString())
        assertEquals("g".toIntOrDefault(4).toString(), 4.toString())
        assertEquals("g".toIntOrZero().toString(), 0.toString())
        assertEquals("".toIntOrDefault(4).toString(), 4.toString())
        assertEquals(null.toIntOrDefault(4).toString(), 4.toString())

        assertEquals("5".toLongOrDefault(4).toString(), 5.toString())
        assertEquals("g".toLongOrDefault(4).toString(), 4.toString())
        assertEquals("g".toLongOrZero().toString(), 0.toString())
        assertEquals("".toLongOrDefault(4).toString(), 4.toString())
        assertEquals(null.toLongOrDefault(4).toString(), 4.toString())

        assertEquals("5.5".toFloatOrDefault(4.5f).toString(), 5.5f.toString())
        assertEquals("g".toFloatOrDefault(4.4f).toString(), 4.4f.toString())
        assertEquals("g".toFloatOrZero().toString(), 0.0f.toString())
        assertEquals("".toFloatOrDefault(4.4f).toString(), 4.4f.toString())
        assertEquals(null.toFloatOrDefault(4.4f).toString(), 4.4f.toString())

        assertEquals("5.5".toDoubleOrDefault(4.4).toString(), 5.5.toString())
        assertEquals("g".toDoubleOrDefault(4.4).toString(), 4.4.toString())
        assertEquals("g".toDoubleOrZero().toString(), 0.0.toString())
        assertEquals("".toDoubleOrDefault(4.4).toString(), 4.4.toString())
        assertEquals(null.toDoubleOrDefault(4.4).toString(), 4.4.toString())
    }

    @Test
    fun testIntToHexString() {
        assertEquals("105d", 4189.toHexString())
        assertEquals("7ad", 1965.toHexString())
        assertEquals("105d", 4189.toHexStringOr("error"))
        assertEquals("7ad", 1965.toHexStringOr("error"))
        assertEquals("error", (null as Int?).toHexStringOr("error"))
        assertEquals("105d", 4189.toHexStringOrNull())
        assertEquals("7ad", 1965.toHexStringOrNull())
        assertEquals("null", (null as Int?).toHexStringOrNull())
    }

    @Test
    fun testIntToBinaryString() {
        assertEquals("1000001011101", 4189.toBinaryString())
        assertEquals("11110101101", 1965.toBinaryString())
        assertEquals("1000001011101", 4189.toBinaryStringOr("error"))
        assertEquals("11110101101", 1965.toBinaryStringOr("error"))
        assertEquals("error", (null as Int?).toBinaryStringOr("error"))
        assertEquals("1000001011101", 4189.toBinaryStringOrNull())
        assertEquals("11110101101", 1965.toBinaryStringOrNull())
        assertEquals("null", (null as Int?).toBinaryStringOrNull())
    }

    @Test
    fun testIntToOctalString() {
        assertEquals("10135", 4189.toOctalString())
        assertEquals("3655", 1965.toOctalString())
        assertEquals("10135", 4189.toOctalStringOr("error"))
        assertEquals("3655", 1965.toOctalStringOr("error"))
        assertEquals("error", (null as Int?).toOctalStringOr("error"))
        assertEquals("10135", 4189.toOctalStringOrNull())
        assertEquals("3655", 1965.toOctalStringOrNull())
        assertEquals("null", (null as Int?).toOctalStringOrNull())
    }

    @Test
    fun testLongToHexString() {
        assertEquals("4c9f58dac1ee", 84247274242542.toHexString())
        assertEquals("25827f576dfc", 41242412412412.toHexString())
        assertEquals("4c9f58dac1ee", 84247274242542.toHexStringOr("error"))
        assertEquals("25827f576dfc", 41242412412412.toHexStringOr("error"))
        assertEquals("error", (null as Long?).toHexStringOr("error"))
        assertEquals("4c9f58dac1ee", 84247274242542.toHexStringOrNull())
        assertEquals("25827f576dfc", 41242412412412.toHexStringOrNull())
        assertEquals("null", (null as Long?).toHexStringOrNull())
    }

    @Test
    fun testLongToBinaryString() {
        assertEquals("10011001001111101011000110110101100000111101110", 84247274242542.toBinaryString())
        assertEquals("1001011000001001111111010101110110110111111100", 41242412412412.toBinaryString())
        assertEquals("10011001001111101011000110110101100000111101110", 84247274242542.toBinaryStringOr("error"))
        assertEquals("1001011000001001111111010101110110110111111100", 41242412412412.toBinaryStringOr("error"))
        assertEquals("error", (null as Long?).toBinaryStringOr("error"))
        assertEquals("10011001001111101011000110110101100000111101110", 84247274242542.toBinaryStringOrNull())
        assertEquals("1001011000001001111111010101110110110111111100", 41242412412412.toBinaryStringOrNull())
        assertEquals("null", (null as Long?).toBinaryStringOrNull())
    }

    @Test
    fun testLongToOctalString() {
        assertEquals("2311753066540756", 84247274242542.toOctalString())
        assertEquals("1130117725666774", 41242412412412.toOctalString())
        assertEquals("2311753066540756", 84247274242542.toOctalStringOr("error"))
        assertEquals("1130117725666774", 41242412412412.toOctalStringOr("error"))
        assertEquals("error", (null as Long?).toOctalStringOr("error"))
        assertEquals("2311753066540756", 84247274242542.toOctalStringOrNull())
        assertEquals("1130117725666774", 41242412412412.toOctalStringOrNull())
        assertEquals("null", (null as Long?).toOctalStringOrNull())
    }
}