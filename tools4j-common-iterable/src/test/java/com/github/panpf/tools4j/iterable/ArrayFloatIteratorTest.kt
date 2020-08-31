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

class ArrayFloatIteratorTest {

    private val normalFloatArray = floatArrayOf(4.5f, 6.8f, 2.3f, 0.6f)
    private val normalFloatArrayToString = "4.5, 6.8, 2.3, 0.6"
    private val nullFloatArray = null as FloatArray?
    private val nullFloatArrayToString = ""
    private val emptyFloatArray = floatArrayOf()
    private val emptyFloatArrayToString = ""

    @Test
    fun testNormal() {
        Assert.assertEquals(normalFloatArrayToString, ArrayFloatIterator(normalFloatArray).asSequence().joinToString { it.toString() })

        try {
            ArrayFloatIterator(normalFloatArray).next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testNull() {
        Assert.assertEquals(nullFloatArrayToString, ArrayFloatIterator(nullFloatArray).asSequence().joinToString { it.toString() })

        try {
            ArrayFloatIterator(nullFloatArray).next()
            Assert.fail()
        } catch (e: Exception) {
            if (!(e is NoSuchElementException && e.message == "elements is null")) {
                Assert.fail()
            }
        }
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals(emptyFloatArrayToString, ArrayFloatIterator(emptyFloatArray).asSequence().joinToString { it.toString() })

        try {
            ArrayFloatIterator(emptyFloatArray).next()
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
            ArrayFloatIterator(normalFloatArray).remove()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is UnsupportedOperationException) {
                Assert.fail()
            }
        }
    }
}