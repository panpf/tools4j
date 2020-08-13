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

@file:Suppress("RemoveRedundantSpreadOperator")

package com.github.panpf.tools4j.collections.ktx

import com.github.panpf.tools4j.common.Transformer
import org.junit.Assert.*
import org.junit.Test

class ArrayxTest {

    @Test
    fun testNullOrEmpty() {
        assertTrue((null as Array<String>?).isNullOrEmpty())
        assertTrue((arrayOf<String>()).isNullOrEmpty())
        assertFalse(arrayOf("1").isNullOrEmpty())
        assertTrue(arrayOf("1").isNotNullOrEmpty())
        assertFalse((null as Array<String>?).isNotNullOrEmpty())
        assertFalse(arrayOf<String>().isNotNullOrEmpty())

        assertTrue((null as CharArray?).isNullOrEmpty())
        assertTrue((charArrayOf()).isNullOrEmpty())
        assertFalse(charArrayOf(1.toChar()).isNullOrEmpty())
        assertTrue(charArrayOf(1.toChar()).isNotNullOrEmpty())
        assertFalse((null as CharArray?).isNotNullOrEmpty())
        assertFalse(charArrayOf().isNotNullOrEmpty())

        assertTrue((null as ByteArray?).isNullOrEmpty())
        assertTrue((byteArrayOf()).isNullOrEmpty())
        assertFalse(byteArrayOf(1.toByte()).isNullOrEmpty())
        assertTrue(byteArrayOf(1.toByte()).isNotNullOrEmpty())
        assertFalse((null as ByteArray?).isNotNullOrEmpty())
        assertFalse(byteArrayOf().isNotNullOrEmpty())

        assertTrue((null as ShortArray?).isNullOrEmpty())
        assertTrue((shortArrayOf()).isNullOrEmpty())
        assertFalse(shortArrayOf(1.toShort()).isNullOrEmpty())
        assertTrue(shortArrayOf(1.toShort()).isNotNullOrEmpty())
        assertFalse((null as ShortArray?).isNotNullOrEmpty())
        assertFalse(shortArrayOf().isNotNullOrEmpty())

        assertTrue((null as IntArray?).isNullOrEmpty())
        assertTrue((intArrayOf()).isNullOrEmpty())
        assertFalse(intArrayOf(1).isNullOrEmpty())
        assertTrue(intArrayOf(1).isNotNullOrEmpty())
        assertFalse((null as IntArray?).isNotNullOrEmpty())
        assertFalse(intArrayOf().isNotNullOrEmpty())

        assertTrue((null as LongArray?).isNullOrEmpty())
        assertTrue((longArrayOf()).isNullOrEmpty())
        assertFalse(longArrayOf(1.toLong()).isNullOrEmpty())
        assertTrue(longArrayOf(1.toLong()).isNotNullOrEmpty())
        assertFalse((null as LongArray?).isNotNullOrEmpty())
        assertFalse(longArrayOf().isNotNullOrEmpty())

        assertTrue((null as FloatArray?).isNullOrEmpty())
        assertTrue((floatArrayOf()).isNullOrEmpty())
        assertFalse(floatArrayOf(1.toFloat()).isNullOrEmpty())
        assertTrue(floatArrayOf(1.toFloat()).isNotNullOrEmpty())
        assertFalse((null as FloatArray?).isNotNullOrEmpty())
        assertFalse(floatArrayOf().isNotNullOrEmpty())

        assertTrue((null as DoubleArray?).isNullOrEmpty())
        assertTrue((doubleArrayOf()).isNullOrEmpty())
        assertFalse(doubleArrayOf(1.toDouble()).isNullOrEmpty())
        assertTrue(doubleArrayOf(1.toDouble()).isNotNullOrEmpty())
        assertFalse((null as DoubleArray?).isNotNullOrEmpty())
        assertFalse(doubleArrayOf().isNotNullOrEmpty())

        assertTrue((null as BooleanArray?).isNullOrEmpty())
        assertTrue((booleanArrayOf()).isNullOrEmpty())
        assertFalse(booleanArrayOf(true).isNullOrEmpty())
        assertTrue(booleanArrayOf(true).isNotNullOrEmpty())
        assertFalse((null as BooleanArray?).isNotNullOrEmpty())
        assertFalse(booleanArrayOf().isNotNullOrEmpty())
    }

    @Test
    fun testJoinToArrayString() {
        assertEquals("[key4, key3, key2]", arrayOf("4", "3", "2").joinToArrayString(Transformer { "key$it" }))
        assertEquals("[4, 3, 2]", arrayOf("4", "3", "2").joinToArrayString())

        assertEquals("[key4, key3, key2]", charArrayOf('4', '3', '2').joinToArrayString(Transformer { "key${it}" }))
        assertEquals("[4, 3, 2]", charArrayOf('4', '3', '2').joinToArrayString())

        assertEquals("[key4, key3, key2]", byteArrayOf(4.toByte(), 3.toByte(), 2.toByte()).joinToArrayString(Transformer { "key$it" }))
        assertEquals("[4, 3, 2]", byteArrayOf(4.toByte(), 3.toByte(), 2.toByte()).joinToArrayString())

        assertEquals("[key4, key3, key2]", shortArrayOf(4.toShort(), 3.toShort(), 2.toShort()).joinToArrayString(Transformer { "key$it" }))
        assertEquals("[4, 3, 2]", shortArrayOf(4.toShort(), 3.toShort(), 2.toShort()).joinToArrayString())

        assertEquals("[key4, key3, key2]", intArrayOf(4, 3, 2).joinToArrayString(Transformer { "key$it" }))
        assertEquals("[4, 3, 2]", intArrayOf(4, 3, 2).joinToArrayString())

        assertEquals("[key4, key3, key2]", longArrayOf(4.toLong(), 3.toLong(), 2.toLong()).joinToArrayString(Transformer { "key$it" }))
        assertEquals("[4, 3, 2]", longArrayOf(4.toLong(), 3.toLong(), 2.toLong()).joinToArrayString())

        assertEquals("[key4.0, key3.0, key2.0]", floatArrayOf(4.toFloat(), 3.toFloat(), 2.toFloat()).joinToArrayString(Transformer { "key$it" }))
        assertEquals("[4.0, 3.0, 2.0]", floatArrayOf(4.toFloat(), 3.toFloat(), 2.toFloat()).joinToArrayString())

        assertEquals("[key4.0, key3.0, key2.0]", doubleArrayOf(4.toDouble(), 3.toDouble(), 2.toDouble()).joinToArrayString(Transformer { "key$it" }))
        assertEquals("[4.0, 3.0, 2.0]", doubleArrayOf(4.toDouble(), 3.toDouble(), 2.toDouble()).joinToArrayString())

        assertEquals("[keytrue, keyfalse, keyfalse]", booleanArrayOf(true, false, false).joinToArrayString(Transformer { "key$it" }))
        assertEquals("[true, false, false]", booleanArrayOf(true, false, false).joinToArrayString())
    }
}