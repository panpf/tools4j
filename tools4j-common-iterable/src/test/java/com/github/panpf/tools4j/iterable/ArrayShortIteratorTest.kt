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

class ArrayShortIteratorTest {

    private val normalShortArray = shortArrayOf(4, 6, 2, 0)
    private val normalShortArrayToString = "4, 6, 2, 0"
    private val nullShortArray = null as ShortArray?
    private val nullShortArrayToString = ""
    private val emptyShortArray = shortArrayOf()
    private val emptyShortArrayToString = ""

    @Test
    fun testNormal() {
        Assert.assertEquals(normalShortArrayToString, ArrayShortIterator(normalShortArray).asSequence().joinToString { it.toString() })

        try {
            ArrayShortIterator(normalShortArray).next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testNull() {
        Assert.assertEquals(nullShortArrayToString, ArrayShortIterator(nullShortArray).asSequence().joinToString { it.toString() })

        try {
            ArrayShortIterator(nullShortArray).next()
            Assert.fail()
        } catch (e: Exception) {
            if (!(e is NoSuchElementException && e.message == "elements is null")) {
                Assert.fail()
            }
        }
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals(emptyShortArrayToString, ArrayShortIterator(emptyShortArray).asSequence().joinToString { it.toString() })

        try {
            ArrayShortIterator(emptyShortArray).next()
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
            ArrayShortIterator(normalShortArray).remove()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is UnsupportedOperationException) {
                Assert.fail()
            }
        }
    }
}