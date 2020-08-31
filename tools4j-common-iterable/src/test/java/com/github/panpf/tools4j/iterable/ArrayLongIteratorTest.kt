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

class ArrayLongIteratorTest {

    private val normalLongArray = longArrayOf(4L, 6L, 2L, 0L)
    private val normalLongArrayToString = "4, 6, 2, 0"
    private val nullLongArray = null as LongArray?
    private val nullLongArrayToString = ""
    private val emptyLongArray = longArrayOf()
    private val emptyLongArrayToString = ""

    @Test
    fun testNormal() {
        Assert.assertEquals(normalLongArrayToString, ArrayLongIterator(normalLongArray).asSequence().joinToString { it.toString() })

        try {
            ArrayLongIterator(normalLongArray).next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testNull() {
        Assert.assertEquals(nullLongArrayToString, ArrayLongIterator(nullLongArray).asSequence().joinToString { it.toString() })

        try {
            ArrayLongIterator(nullLongArray).next()
            Assert.fail()
        } catch (e: Exception) {
            if (!(e is NoSuchElementException && e.message == "elements is null")) {
                Assert.fail()
            }
        }
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals(emptyLongArrayToString, ArrayLongIterator(emptyLongArray).asSequence().joinToString { it.toString() })

        try {
            ArrayLongIterator(emptyLongArray).next()
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
            ArrayLongIterator(normalLongArray).remove()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is UnsupportedOperationException) {
                Assert.fail()
            }
        }
    }
}