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

import com.github.panpf.tools4j.test.ktx.assertThrow
import org.junit.Assert.assertEquals
import org.junit.Test

class DistinctIteratorTest {

    @Test
    fun test() {
        val normalList = listOf("4", "5", "4", "5", "3", "1", "4")
        val emptyList = listOf<String>()
        val nullList1 = null as List<String>?

        assertEquals("4, 5, 3, 1", Iterable { DistinctIterator(normalList.iterator()) { it } }.joinToString())
        assertEquals("", Iterable { DistinctIterator(emptyList.iterator()) { it } }.joinToString())
        assertEquals("", Iterable { DistinctIterator(nullList1?.iterator()) { it } }.joinToString())

        assertThrow(UnsupportedOperationException::class) { DistinctIterator(normalList.iterator()) { it }.remove() }
    }
}