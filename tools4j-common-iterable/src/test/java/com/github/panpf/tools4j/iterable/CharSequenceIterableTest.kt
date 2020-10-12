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

class CharSequenceIterableTest {

    @Test
    fun test() {
        val normalCharSequence = "faf54c32"
        val nullCharSequence = null as CharSequence?
        val emptyCharSequence = ""

        assertEquals("f, a, f, 5, 4, c, 3, 2", CharSequenceIterable(normalCharSequence).iterator().asSequence().joinToString { it.toString() })
        assertNoThrow { CharSequenceIterable(normalCharSequence).iterator().next() }

        assertEquals("", CharSequenceIterable(nullCharSequence).iterator().asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { CharSequenceIterable(nullCharSequence).iterator().next() }

        assertEquals("", CharSequenceIterable(emptyCharSequence).iterator().asSequence().joinToString { it.toString() })
        assertThrow(NoSuchElementException::class) { CharSequenceIterable(emptyCharSequence).iterator().next() }

        assertThrow(UnsupportedOperationException::class) { CharSequenceIterable(normalCharSequence).iterator().remove() }
    }
}