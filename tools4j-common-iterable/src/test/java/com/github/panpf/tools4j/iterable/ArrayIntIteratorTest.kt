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

class ArrayIntIteratorTest {

    private val normalIntArray = intArrayOf(4, 6, 2, 0)
    private val normalIntArrayToString = "4, 6, 2, 0"
    private val nullIntArray = null as IntArray?
    private val nullIntArrayToString = ""
    private val emptyIntArray = intArrayOf()
    private val emptyIntArrayToString = ""

    @Test
    fun testNormal() {
        Assert.assertEquals(normalIntArrayToString, ArrayIntIterator(normalIntArray).asSequence().joinToString { it.toString() })

        try {
            ArrayIntIterator(normalIntArray).next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testNull() {
        Assert.assertEquals(nullIntArrayToString, ArrayIntIterator(nullIntArray).asSequence().joinToString { it.toString() })

        try {
            ArrayIntIterator(nullIntArray).next()
            Assert.fail()
        } catch (e: Exception) {
            if (!(e is NoSuchElementException && e.message == "elements is null")) {
                Assert.fail()
            }
        }
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals(emptyIntArrayToString, ArrayIntIterator(emptyIntArray).asSequence().joinToString { it.toString() })

        try {
            ArrayIntIterator(emptyIntArray).next()
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
            ArrayIntIterator(normalIntArray).remove()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is UnsupportedOperationException) {
                Assert.fail()
            }
        }
    }
}