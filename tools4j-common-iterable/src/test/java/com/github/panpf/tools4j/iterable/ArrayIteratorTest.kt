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

class ArrayIteratorTest {

    private val normalArray = arrayOf("faf", "54", "c", "32")
    private val normalArrayToString = "faf, 54, c, 32"
    private val nullArray = null as Array<*>?
    private val nullArrayToString = ""
    private val emptyArray = arrayOf<Any>()
    private val emptyArrayToString = ""

    @Test
    fun testNormal() {
        Assert.assertEquals(normalArrayToString, ArrayIterator(normalArray).asSequence().joinToString { it.toString() })

        try {
            ArrayIterator(normalArray).next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testNull() {
        Assert.assertEquals(nullArrayToString, ArrayIterator(nullArray).asSequence().joinToString { it.toString() })

        try {
            ArrayIterator(nullArray).next()
            Assert.fail()
        } catch (e: Exception) {
            if (!(e is NoSuchElementException && e.message == "elements is null")) {
                Assert.fail()
            }
        }
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals(emptyArrayToString, ArrayIterator(emptyArray).asSequence().joinToString { it.toString() })

        try {
            ArrayIterator(emptyArray).next()
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
            ArrayIterator(normalArray).remove()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is UnsupportedOperationException) {
                Assert.fail()
            }
        }
    }
}