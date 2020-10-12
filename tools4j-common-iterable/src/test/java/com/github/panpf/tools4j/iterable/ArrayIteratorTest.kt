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

class ArrayIteratorTest {

    @Test
    fun test() {
        val normalArray = arrayOf("faf", "54", "c", "32")
        val nullArray = null as Array<*>?
        val emptyArray = arrayOf<Any>()

        assertEquals("faf, 54, c, 32", ArrayIterator(normalArray).asSequence().joinToString { it.toString() })
        assertNoThrow { ArrayIterator(normalArray).next() }

        assertEquals("", ArrayIterator(nullArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayIterator(nullArray).next() }

        assertEquals("", ArrayIterator(emptyArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayIterator(emptyArray).next() }

        assertThrow(UnsupportedOperationException::class) { ArrayIterator(normalArray).remove() }
    }
}