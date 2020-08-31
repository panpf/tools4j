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

class ArrayDoubleIteratorTest {

    private val normalDoubleArray = doubleArrayOf(4.5, 6.8, 2.3, 0.6)
    private val normalDoubleArrayToString = "4.5, 6.8, 2.3, 0.6"
    private val nullDoubleArray = null as DoubleArray?
    private val nullDoubleArrayToString = ""
    private val emptyDoubleArray = doubleArrayOf()
    private val emptyDoubleArrayToString = ""

    @Test
    fun testNormal() {
        Assert.assertEquals(normalDoubleArrayToString, ArrayDoubleIterator(normalDoubleArray).asSequence().joinToString { it.toString() })

        try {
            ArrayDoubleIterator(normalDoubleArray).next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testNull() {
        Assert.assertEquals(nullDoubleArrayToString, ArrayDoubleIterator(nullDoubleArray).asSequence().joinToString { it.toString() })

        try {
            ArrayDoubleIterator(nullDoubleArray).next()
            Assert.fail()
        } catch (e: Exception) {
            if (!(e is NoSuchElementException && e.message == "elements is null")) {
                Assert.fail()
            }
        }
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals(emptyDoubleArrayToString, ArrayDoubleIterator(emptyDoubleArray).asSequence().joinToString { it.toString() })

        try {
            ArrayDoubleIterator(emptyDoubleArray).next()
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
            ArrayDoubleIterator(normalDoubleArray).remove()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is UnsupportedOperationException) {
                Assert.fail()
            }
        }
    }
}