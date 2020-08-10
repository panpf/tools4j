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
package com.github.panpf.tools4j.compare

import org.junit.Assert
import org.junit.Test
import java.util.*

class ComparexTest {

    @Test
    fun testAreEqual() {
        Assert.assertTrue(Comparex.areEqual("123", "123"))
        Assert.assertTrue(Comparex.areEqual(String(StringBuilder("123")), String(StringBuilder("123"))))
        Assert.assertTrue(Comparex.areEqual(java.lang.Double.valueOf("1.2"), java.lang.Double.valueOf("1.2")))
        Assert.assertTrue(Comparex.areEqual(java.lang.Double.valueOf("1.2"), 1.2))
        Assert.assertTrue(Comparex.areEqual(1.2, java.lang.Double.valueOf("1.2")))
        Assert.assertFalse(Comparex.areEqual(java.lang.Double.valueOf("1.3"), java.lang.Double.valueOf("1.2")))
        Assert.assertTrue(Comparex.areEqual(java.lang.Float.valueOf("1.2"), java.lang.Float.valueOf("1.2")))
        Assert.assertTrue(Comparex.areEqual(java.lang.Float.valueOf("1.2"), 1.2f))
        Assert.assertTrue(Comparex.areEqual(1.2f, java.lang.Float.valueOf("1.2")))
        Assert.assertFalse(Comparex.areEqual(java.lang.Float.valueOf("1.3"), java.lang.Float.valueOf("1.2")))
    }

    @Test
    fun testCompare() {
        Assert.assertEquals(Comparex.compareValues(1, 4).toLong(), -1)
        Assert.assertEquals(Comparex.compareValues(6, 4).toLong(), 1)
        Assert.assertEquals(Comparex.compareValues(6, 6).toLong(), 0)
        Assert.assertEquals(Comparex.compareValues(null, 6).toLong(), -1)
        Assert.assertEquals(Comparex.compareValues(6, null).toLong(), 1)

        val transformer: CompareTransformer<String, Int> = CompareTransformer<String, Int> { s -> s?.length }
        Assert.assertEquals(Comparex.compareValuesBy("1", "1234", transformer).toLong(), -1)
        Assert.assertEquals(Comparex.compareValuesBy("123456", "1234", transformer).toLong(), 1)
        Assert.assertEquals(Comparex.compareValuesBy("123456", "123456", transformer).toLong(), 0)
        Assert.assertEquals(Comparex.compareValuesBy(null, "123456", transformer).toLong(), -1)
        Assert.assertEquals(Comparex.compareValuesBy("123456", null, transformer).toLong(), 1)

        val comparator: Comparator<String?> = Comparex.compareBy<String, Int> { s -> s?.length }
        Assert.assertEquals(comparator.compare("1", "1234").toLong(), -1)
        Assert.assertEquals(comparator.compare("123456", "1234").toLong(), 1)
        Assert.assertEquals(comparator.compare("123456", "123456").toLong(), 0)
        Assert.assertEquals(comparator.compare(null, "123456").toLong(), -1)
        Assert.assertEquals(comparator.compare("123456", null).toLong(), 1)

        val comparatorDescending: Comparator<String?> = Comparex.compareByDescending<String, Int> { s -> s?.length }
        Assert.assertEquals(comparatorDescending.compare("1", "1234").toLong(), 1)
        Assert.assertEquals(comparatorDescending.compare("123456", "1234").toLong(), -1)
        Assert.assertEquals(comparatorDescending.compare("123456", "123456").toLong(), 0)
        Assert.assertEquals(comparatorDescending.compare(null, "123456").toLong(), 1)
        Assert.assertEquals(comparatorDescending.compare("123456", null).toLong(), -1)
    }

    @Test
    fun testMaxOf() {
        Assert.assertEquals(Comparex.maxOf("abcd", "abcdef"), "abcdef")
        Assert.assertEquals(Comparex.maxOf("abcdef", "abcd"), "abcdef")
        Assert.assertEquals(Comparex.maxOf(1.toByte(), 6.toByte()).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(6.toByte(), 1.toByte()).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(1.toShort(), 6.toShort()).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(6.toShort(), 1.toShort()).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(1, 6).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(6, 1).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(1L, 6L), 6L)
        Assert.assertEquals(Comparex.maxOf(6L, 1L), 6L)
        Assert.assertEquals(Comparex.maxOf(1f, 6f), 6f, 0f)
        Assert.assertEquals(Comparex.maxOf(6f, 1f), 6f, 0f)
        Assert.assertEquals(Comparex.maxOf(1.0, 6.0), 6.0, 0.0)
        Assert.assertEquals(Comparex.maxOf(6.0, 1.0), 6.0, 0.0)
        Assert.assertEquals(Comparex.maxOf("abcd", "abcde", "abcdef"), "abcdef")
        Assert.assertEquals(Comparex.maxOf("abcdef", "abcde", "abcd"), "abcdef")
        Assert.assertEquals(Comparex.maxOf(1.toByte(), 4.toByte(), 6.toByte()).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(6.toByte(), 4.toByte(), 1.toByte()).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(1.toShort(), 4.toShort(), 6.toShort()).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(6.toShort(), 4.toShort(), 1.toShort()).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(1, 4, 6).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(6, 4, 1).toLong(), 6)
        Assert.assertEquals(Comparex.maxOf(1L, 4L, 6L), 6L)
        Assert.assertEquals(Comparex.maxOf(6L, 4L, 1L), 6L)
        Assert.assertEquals(Comparex.maxOf(1f, 4f, 6f), 6f, 0f)
        Assert.assertEquals(Comparex.maxOf(6f, 4f, 1f), 6f, 0f)
        Assert.assertEquals(Comparex.maxOf(1.0, 4.0, 6.0), 6.0, 0.0)
        Assert.assertEquals(Comparex.maxOf(6.0, 4.0, 1.0), 6.0, 0.0)
    }

    @Test
    fun testMinOf() {
        Assert.assertEquals(Comparex.minOf("abcd", "abcdef"), "abcd")
        Assert.assertEquals(Comparex.minOf("abcdef", "abcd"), "abcd")
        Assert.assertEquals(Comparex.minOf(1.toByte(), 6.toByte()).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(6.toByte(), 1.toByte()).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(1.toShort(), 6.toShort()).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(6.toShort(), 1.toShort()).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(1, 6).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(6, 1).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(1L, 6L), 1L)
        Assert.assertEquals(Comparex.minOf(6L, 1L), 1L)
        Assert.assertEquals(Comparex.minOf(1f, 6f), 1f, 0f)
        Assert.assertEquals(Comparex.minOf(6f, 1f), 1f, 0f)
        Assert.assertEquals(Comparex.minOf(1.0, 6.0), 1.0, 0.0)
        Assert.assertEquals(Comparex.minOf(6.0, 1.0), 1.0, 0.0)
        Assert.assertEquals(Comparex.minOf("abcd", "abcde", "abcdef"), "abcd")
        Assert.assertEquals(Comparex.minOf("abcdef", "abcde", "abcd"), "abcd")
        Assert.assertEquals(Comparex.minOf(1.toByte(), 4.toByte(), 6.toByte()).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(6.toByte(), 4.toByte(), 1.toByte()).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(1.toShort(), 4.toShort(), 6.toShort()).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(6.toShort(), 4.toShort(), 1.toShort()).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(1, 4, 6).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(6, 4, 1).toLong(), 1)
        Assert.assertEquals(Comparex.minOf(1L, 4L, 6L), 1L)
        Assert.assertEquals(Comparex.minOf(6L, 4L, 1L), 1L)
        Assert.assertEquals(Comparex.minOf(1f, 4f, 6f), 1f, 0f)
        Assert.assertEquals(Comparex.minOf(6f, 4f, 1f), 1f, 0f)
        Assert.assertEquals(Comparex.minOf(1.0, 4.0, 6.0), 1.0, 0.0)
        Assert.assertEquals(Comparex.minOf(6.0, 4.0, 1.0), 1.0, 0.0)
    }
}