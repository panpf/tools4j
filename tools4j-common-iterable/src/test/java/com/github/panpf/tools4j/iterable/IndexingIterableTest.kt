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

class IndexingIterableTest {

    @Test
    fun test() {
        val normalCharSequence = "faf54c32"
        val nullCharSequence = null as CharSequence?
        val emptyCharSequence = ""

        assertEquals(
                "IndexedValue{index=0, value=f}, IndexedValue{index=1, value=a}, IndexedValue{index=2, value=f}, IndexedValue{index=3, value=5}, IndexedValue{index=4, value=4}, IndexedValue{index=5, value=c}, IndexedValue{index=6, value=3}, IndexedValue{index=7, value=2}",
                IndexingIterable {
                    CharSequenceIterator(normalCharSequence)
                }.iterator().asSequence().joinToString { it.toString() })
        assertNoThrow {
            IndexingIterable { CharSequenceIterator(normalCharSequence) }.iterator().next()
        }

        assertEquals("", IndexingIterable {
            CharSequenceIterator(nullCharSequence)
        }.iterator().asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) {
            IndexingIterable { CharSequenceIterator(nullCharSequence) }.iterator().next()
        }

        assertEquals("", IndexingIterable {
            CharSequenceIterator(emptyCharSequence)
        }.iterator().asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) {
            IndexingIterable { CharSequenceIterator(emptyCharSequence) }.iterator().next()
        }

        assertThrow(UnsupportedOperationException::class) {
            IndexingIterable { CharSequenceIterator(normalCharSequence) }.iterator().remove()
        }

        val iterator = IndexingIterable { mutableListOf(1, 2, 3).iterator() }.iterator()
        while (iterator.hasNext()) {
            iterator.next()
            iterator.remove()
        }
    }
}