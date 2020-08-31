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

class ArrayCharIteratorTest {

    private val normalCharArray = charArrayOf('a', 't', 'g', '7')
    private val normalCharArrayToString = "a, t, g, 7"
    private val nullCharArray = null as CharArray?
    private val nullCharArrayToString = ""
    private val emptyCharArray = charArrayOf()
    private val emptyCharArrayToString = ""

    @Test
    fun testNormal() {
        Assert.assertEquals(normalCharArrayToString, ArrayCharIterator(normalCharArray).asSequence().joinToString { it.toString() })

        try {
            ArrayCharIterator(normalCharArray).next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testNull() {
        Assert.assertEquals(nullCharArrayToString, ArrayCharIterator(nullCharArray).asSequence().joinToString { it.toString() })

        try {
            ArrayCharIterator(nullCharArray).next()
            Assert.fail()
        } catch (e: Exception) {
            if (!(e is NoSuchElementException && e.message == "elements is null")) {
                Assert.fail()
            }
        }
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals(emptyCharArrayToString, ArrayCharIterator(emptyCharArray).asSequence().joinToString { it.toString() })

        try {
            ArrayCharIterator(emptyCharArray).next()
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
            ArrayCharIterator(normalCharArray).remove()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is UnsupportedOperationException) {
                Assert.fail()
            }
        }
    }
}