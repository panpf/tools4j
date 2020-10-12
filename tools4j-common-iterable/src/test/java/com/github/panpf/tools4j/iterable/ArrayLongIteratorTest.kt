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

class ArrayLongIteratorTest {

    @Test
    fun test() {
        val normalLongArray = longArrayOf(4L, 6L, 2L, 0L)
        val nullLongArray = null as LongArray?
        val emptyLongArray = longArrayOf()

        assertEquals("4, 6, 2, 0", ArrayLongIterator(normalLongArray).asSequence().joinToString { it.toString() })
        assertNoThrow { ArrayLongIterator(normalLongArray).next() }

        assertEquals("", ArrayLongIterator(nullLongArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayLongIterator(nullLongArray).next() }

        assertEquals("", ArrayLongIterator(emptyLongArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayLongIterator(emptyLongArray).next() }

        assertThrow(UnsupportedOperationException::class) { ArrayLongIterator(normalLongArray).remove() }
    }
}