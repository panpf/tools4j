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

class IndexingIterableTest {

    private val normalCharSequence = "faf54c32"
    private val normalCharSequenceToString = "IndexedValue{index=0, value=f}, IndexedValue{index=1, value=a}, IndexedValue{index=2, value=f}, IndexedValue{index=3, value=5}, IndexedValue{index=4, value=4}, IndexedValue{index=5, value=c}, IndexedValue{index=6, value=3}, IndexedValue{index=7, value=2}"
    private val nullCharSequence = null as CharSequence?
    private val nullCharSequenceToString = ""
    private val emptyCharSequence = ""
    private val emptyCharSequenceToString = ""

    @Test
    fun testNormal() {
        Assert.assertEquals(normalCharSequenceToString, IndexingIterable<Char> { CharSequenceIterator(normalCharSequence) }.iterator().asSequence().joinToString { it.toString() })

        try {
            IndexingIterable<Char> { CharSequenceIterator(normalCharSequence) }.iterator().next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testNull() {
        Assert.assertEquals(nullCharSequenceToString, IndexingIterable<Char> { CharSequenceIterator(nullCharSequence) }.iterator().asSequence().joinToString { it.toString() })

        try {
            IndexingIterable<Char> { CharSequenceIterator(nullCharSequence) }.iterator().next()
            Assert.fail()
        } catch (e: Exception) {
            if (!(e is NoSuchElementException && e.message == "elements is null")) {
                Assert.fail()
            }
        }
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals(emptyCharSequenceToString, IndexingIterable<Char> { CharSequenceIterator(emptyCharSequence) }.iterator().asSequence().joinToString { it.toString() })

        try {
            IndexingIterable<Char> { CharSequenceIterator(emptyCharSequence) }.iterator().next()
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
            IndexingIterable<Char> { CharSequenceIterator(normalCharSequence) }.iterator().remove()
            Assert.fail()
        } catch (e: Exception) {
            if (e !is UnsupportedOperationException) {
                Assert.fail()
            }
        }
    }
}