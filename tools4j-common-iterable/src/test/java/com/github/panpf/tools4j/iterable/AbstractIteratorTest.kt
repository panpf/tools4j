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

class AbstractIteratorTest {

    @Test
    fun testNormal() {
        Assert.assertEquals("1, 2, 3, 4, 5, 6, 7, 8, 9, 10", IntTestIterator(1, 10).asSequence().joinToString { it.toString() })
    }

    @Test
    fun testEmpty() {
        Assert.assertEquals("", IntTestIterator(1, 0).asSequence().joinToString { it.toString() })

        try {
            IntTestIterator(1, 0).next()
        } catch (e: Exception) {
            if (e !is NoSuchElementException) {
                Assert.fail()
            }
        }

        Assert.assertFalse(IntTestIterator(1, 0).apply { hasNext() }.hasNext())
    }

    @Test
    fun testNull() {
        try {
            NullTestIterator().next()
        } catch (e: Exception) {
            if (e !is NoSuchElementException) {
                Assert.fail()
            }
        }
    }

    class IntTestIterator(start: Int, private val end: Int) : AbstractIterator<Int>() {

        var currentValue: Int = start

        override fun remove() {
            throw UnsupportedOperationException("remove")
        }

        override fun computeNext() {
            val currentValue = this.currentValue
            if (currentValue <= end) {
                setNext(currentValue)
                this.currentValue++
            } else {
                done()
            }
        }
    }

    class NullTestIterator() : AbstractIterator<Int>() {

        override fun remove() {
            throw UnsupportedOperationException("remove")
        }

        override fun computeNext() {
            setNext(null)
        }
    }
}