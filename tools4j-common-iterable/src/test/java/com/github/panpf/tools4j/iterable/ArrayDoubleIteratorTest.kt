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

import com.github.panpf.tools4j.test.ktx.assertNoThrow
import com.github.panpf.tools4j.test.ktx.assertThrow
import org.junit.Assert.assertEquals
import org.junit.Test

class ArrayDoubleIteratorTest {

    @Test
    fun test() {
        val normalDoubleArray = doubleArrayOf(4.5, 6.8, 2.3, 0.6)
        val nullDoubleArray = null as DoubleArray?
        val emptyDoubleArray = doubleArrayOf()

        assertEquals("4.5, 6.8, 2.3, 0.6", ArrayDoubleIterator(normalDoubleArray).asSequence().joinToString { it.toString() })
        assertNoThrow { ArrayDoubleIterator(normalDoubleArray).next() }

        assertEquals("", ArrayDoubleIterator(nullDoubleArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayDoubleIterator(nullDoubleArray).next() }

        assertEquals("", ArrayDoubleIterator(emptyDoubleArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayDoubleIterator(emptyDoubleArray).next() }

        assertThrow(UnsupportedOperationException::class) { ArrayDoubleIterator(normalDoubleArray).remove() }
    }
}