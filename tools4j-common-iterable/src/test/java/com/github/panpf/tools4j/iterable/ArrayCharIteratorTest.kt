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

class ArrayCharIteratorTest {

    @Test
    fun test() {
        val normalCharArray = charArrayOf('a', 't', 'g', '7')
        val nullCharArray = null as CharArray?
        val emptyCharArray = charArrayOf()

        assertEquals("a, t, g, 7", ArrayCharIterator(normalCharArray).asSequence().joinToString { it.toString() })
        assertNoThrow { ArrayCharIterator(normalCharArray).next() }

        assertEquals("", ArrayCharIterator(nullCharArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayCharIterator(nullCharArray).next() }

        assertEquals("", ArrayCharIterator(emptyCharArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayCharIterator(emptyCharArray).next() }

        assertThrow(UnsupportedOperationException::class) { ArrayCharIterator(normalCharArray).remove() }
    }
}