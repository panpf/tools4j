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

package com.github.panpf.tools4j.iterable

import org.junit.Assert
import org.junit.Test

class ArrayByteIteratorTest {

    private val normalByteArray = byteArrayOf(3, 4, 2, 9)
    private val normalByteArrayToString = "3, 4, 2, 9"
    private val nullByteArray = null as ByteArray?
    private val nullByteArrayToString = ""
    private val emptyByteArray = byteArrayOf()
    private val emptyByteArrayToString = ""

    @Test
    fun testNormal() {
        Assert.assertEquals(normalByteArrayToString, ArrayByteIterator(normalByteArray).asSequence().joinToString { it.toString() })

        try {
            ArrayByteIterator(normalByteArray).next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testNull() {
        Assert.assertEquals(nullByteArrayToString, ArrayByteIterator(nullByteArray).asSequence().joinToString { it.toString() })

        try {
            ArrayByteIterator(nullByteArray).next()
            Assert.fail()
        } catch (e: Exception) {
            if (!(e is NoSuchElementException && e.message == "elements is null")) {
                Assert.fail()
            }
        }
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals(emptyByteArrayToString, ArrayByteIterator(emptyByteArray).asSequence().joinToString { it.toString() })

        try {
            ArrayByteIterator(emptyByteArray).next()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is NoSuchElementException) {
                Assert.fail()
            }
        }
    }

    @Test
    fun testRemove() {
        try {
            ArrayByteIterator(normalByteArray).remove()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is UnsupportedOperationException) {
                Assert.fail()
            }
        }
    }
}