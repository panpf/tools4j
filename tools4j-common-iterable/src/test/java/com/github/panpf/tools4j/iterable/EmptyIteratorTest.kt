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
import org.junit.Assert.assertFalse
import org.junit.Test

class EmptyIteratorTest {

    @Test
    fun test() {
        assertEquals("", EmptyIterator.INSTANCE.asSequence().joinToString { it.toString() })

        assertThrow(NoSuchElementException::class) { EmptyIterator.INSTANCE.previous() }
        assertThrow(NoSuchElementException::class) { EmptyIterator.INSTANCE.next() }

        assertThrow(UnsupportedOperationException::class) { EmptyIterator.INSTANCE.remove() }

        assertNoThrow { EmptyIterator.INSTANCE.set(null) }
        assertNoThrow { EmptyIterator.INSTANCE.add(null) }

        assertFalse(EmptyIterator.INSTANCE.hasPrevious())
        assertFalse(EmptyIterator.INSTANCE.hasNext())

        assertEquals(-1, EmptyIterator.INSTANCE.nextIndex())
        assertEquals(-1, EmptyIterator.INSTANCE.previousIndex())
    }
}