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

class TransformingIterableTest {

    private val normalArray = arrayOf("faf54c32", "faf54ce", "fa32")
    private val normalArrayToString = "8, 7, 4"
    private val emptyArray = arrayOf<String>()
    private val emptyArrayToString = ""

    @Test
    fun testNormal() {
        Assert.assertEquals(normalArrayToString, TransformingIterable(normalArray.asIterable()) { it.length }.iterator().asSequence().joinToString { it.toString() })

        try {
            TransformingIterable(normalArray.asIterable()) { it.length }.iterator().next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals(emptyArrayToString, TransformingIterable(emptyArray.asIterable()) { it.length }.iterator().asSequence().joinToString { it.toString() })

        try {
            TransformingIterable(emptyArray.asIterable()) { it.length }.iterator().next()
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
            TransformingIterable(normalArray.asIterable()) { it.length }.iterator().remove()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is UnsupportedOperationException) {
                Assert.fail()
            }
        }
    }
}