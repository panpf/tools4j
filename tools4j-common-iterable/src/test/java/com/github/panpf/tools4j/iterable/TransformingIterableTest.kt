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

class TransformingIterableTest {

    @Test
    fun test() {
        val normalArray = arrayOf("faf54c32", "faf54ce", "fa32")
        val emptyArray = arrayOf<String>()

        assertEquals("8, 7, 4",
                TransformingIterable(normalArray.asIterable()) { it.length }.iterator().asSequence().joinToString { it.toString() })
        assertNoThrow { TransformingIterable(normalArray.asIterable()) { it.length }.iterator().next() }

        assertEquals("",
                TransformingIterable(emptyArray.asIterable()) { it.length }.iterator().asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) {
            TransformingIterable(emptyArray.asIterable()) { it.length }.iterator().next()
        }

        assertThrow(UnsupportedOperationException::class) {
            TransformingIterable(normalArray.asIterable()) { it.length }.iterator().remove()
        }

        val iterator = TransformingIterable(mutableListOf(1, 2, 3)) { it }.iterator()
        while (iterator.hasNext()) {
            iterator.next()
            iterator.remove()
        }
    }
}