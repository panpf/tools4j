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

class ArrayBooleanIteratorTest {

    private val normalBooleanArray = booleanArrayOf(true, false, false, true)
    private val normalBooleanArrayToString = "true, false, false, true"
    private val nullBooleanArray = null as BooleanArray?
    private val nullBooleanArrayToString = ""
    private val emptyBooleanArray = booleanArrayOf()
    private val emptyBooleanArrayToString = ""

    @Test
    fun testNormal() {
        Assert.assertEquals(normalBooleanArrayToString, ArrayBooleanIterator(normalBooleanArray).asSequence().joinToString { it.toString() })

        try {
            ArrayBooleanIterator(normalBooleanArray).next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testNull() {
        Assert.assertEquals(nullBooleanArrayToString, ArrayBooleanIterator(nullBooleanArray).asSequence().joinToString { it.toString() })

        try {
            ArrayBooleanIterator(nullBooleanArray).next()
            Assert.fail()
        } catch (e: Exception) {
            if (!(e is NoSuchElementException && e.message == "elements is null")) {
                Assert.fail()
            }
        }
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals(emptyBooleanArrayToString, ArrayBooleanIterator(emptyBooleanArray).asSequence().joinToString { it.toString() })

        try {
            ArrayBooleanIterator(emptyBooleanArray).next()
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
            ArrayBooleanIterator(booleanArrayOf(true, false, false, true)).remove()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is UnsupportedOperationException) {
                Assert.fail()
            }
        }
    }
}