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

import com.github.panpf.tools4j.test.ktx.assertThrow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class AbstractIteratorTest {

    @Test
    fun test() {
        assertEquals("1, 2, 3, 4, 5, 6, 7, 8, 9, 10", IntTestIterator(1, 10).asSequence().joinToString { it.toString() })

        assertEquals("", IntTestIterator(1, 0).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { IntTestIterator(1, 0).next() }
        assertFalse(IntTestIterator(1, 0).apply { hasNext() }.hasNext())

        assertThrow(NoSuchElementException::class) { NullTestIterator().next() }

        assertThrow(IllegalStateException::class) {
            val iterator = FailedTestIterator()
            try {
                iterator.hasNext()
            } catch (e: Exception) {
            }
            iterator.next()
        }
    }

    class IntTestIterator(start: Int, private val end: Int) : AbstractIterator<Int>() {

        private var currentValue: Int = start

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

    class NullTestIterator : AbstractIterator<Int>() {

        override fun remove() {
            throw UnsupportedOperationException("remove")
        }

        override fun computeNext() {
            setNext(null)
        }
    }

    class FailedTestIterator : AbstractIterator<Int>() {

        override fun remove() {
            throw UnsupportedOperationException("remove")
        }

        override fun computeNext() {
            throw RuntimeException()
        }
    }
}