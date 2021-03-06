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

class ArrayBooleanIteratorTest {

    @Test
    fun test() {
        val normalBooleanArray = booleanArrayOf(true, false, false, true)
        val nullBooleanArray = null as BooleanArray?
        val emptyBooleanArray = booleanArrayOf()

        assertEquals("true, false, false, true", ArrayBooleanIterator(normalBooleanArray).asSequence().joinToString { it.toString() })
        assertNoThrow { ArrayBooleanIterator(normalBooleanArray).next() }

        assertEquals("", ArrayBooleanIterator(nullBooleanArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayBooleanIterator(nullBooleanArray).next() }

        assertEquals("", ArrayBooleanIterator(emptyBooleanArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayBooleanIterator(emptyBooleanArray).next() }

        assertThrow(UnsupportedOperationException::class) { ArrayBooleanIterator(booleanArrayOf(true, false, false, true)).remove() }
    }
}