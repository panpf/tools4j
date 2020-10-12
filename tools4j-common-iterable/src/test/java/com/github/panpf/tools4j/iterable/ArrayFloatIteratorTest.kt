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

class ArrayFloatIteratorTest {

    @Test
    fun test() {
        val normalFloatArray = floatArrayOf(4.5f, 6.8f, 2.3f, 0.6f)
        val nullFloatArray = null as FloatArray?
        val emptyFloatArray = floatArrayOf()

        assertEquals("4.5, 6.8, 2.3, 0.6", ArrayFloatIterator(normalFloatArray).asSequence().joinToString { it.toString() })
        assertNoThrow { ArrayFloatIterator(normalFloatArray).next() }

        assertEquals("", ArrayFloatIterator(nullFloatArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayFloatIterator(nullFloatArray).next() }

        assertEquals("", ArrayFloatIterator(emptyFloatArray).asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { ArrayFloatIterator(emptyFloatArray).next() }

        assertThrow(UnsupportedOperationException::class) { ArrayFloatIterator(normalFloatArray).remove() }
    }
}