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
import com.github.panpf.tools4j.test.ktx.assertTwoEquals
import com.github.panpf.tools4j.test.ktx.assertTwoThrow
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*
import kotlin.NoSuchElementException

class WindowedIteratorTest {

    @Test
    fun test() {
        val normalList = listOf(1, 2, 3, 4, 5)
        val normalRange = 1.rangeTo(5)
        val emptyList = listOf<Int>()
        @Suppress("EmptyRange") val emptyRange = 1.rangeTo(0)
        val nullList = null as List<Int>?
        val nullRange = null as IntRange?

        // Test illegal size and step parameter
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.windowed(0, 0, true).toString() },
                { WindowedIterator(normalList.iterator(), 0, 0, true).toString() })
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.windowed(1, 0, true).toString() },
                { WindowedIterator(normalList.iterator(), 1, 0, true).toString() })
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.windowed(0, 1, true).toString() },
                { WindowedIterator(normalList.iterator(), 0, 1, true).toString() })

        // Test the size parameter
        assertTwoEquals("[[1], [2], [3], [4], [5]]",
                normalList.windowed(1, 1, true).toString(),
                WindowedIterator(normalList.iterator(), 1, 1, true).toList().toString())
        assertTwoEquals("[[1, 2], [2, 3], [3, 4], [4, 5], [5]]",
                normalList.windowed(2, 1, true).toString(),
                WindowedIterator(normalList.iterator(), 2, 1, true).toList().toString())
        assertTwoEquals("[[1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5], [5]]",
                normalList.windowed(3, 1, true).toString(),
                WindowedIterator(normalList.iterator(), 3, 1, true).toList().toString())
        assertTwoEquals("[[1, 2, 3, 4], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]]",
                normalList.windowed(4, 1, true).toString(),
                WindowedIterator(normalList.iterator(), 4, 1, true).toList().toString())
        assertTwoEquals("[[1, 2, 3, 4, 5], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]]",
                normalList.windowed(5, 1, true).toString(),
                WindowedIterator(normalList.iterator(), 5, 1, true).toList().toString())
        assertTwoEquals("[[1, 2, 3, 4, 5], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]]",
                normalList.windowed(6, 1, true).toString(),
                WindowedIterator(normalList.iterator(), 6, 1, true).toList().toString())

        assertTwoEquals("[[1], [2], [3], [4], [5]]",
                normalRange.windowed(1, 1, true).toString(),
                WindowedIterator(normalRange.iterator(), 1, 1, true).toList().toString())
        assertTwoEquals("[[1, 2], [2, 3], [3, 4], [4, 5], [5]]",
                normalRange.windowed(2, 1, true).toString(),
                WindowedIterator(normalRange.iterator(), 2, 1, true).toList().toString())
        assertTwoEquals("[[1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5], [5]]",
                normalRange.windowed(3, 1, true).toString(),
                WindowedIterator(normalRange.iterator(), 3, 1, true).toList().toString())
        assertTwoEquals("[[1, 2, 3, 4], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]]",
                normalRange.windowed(4, 1, true).toString(),
                WindowedIterator(normalRange.iterator(), 4, 1, true).toList().toString())
        assertTwoEquals("[[1, 2, 3, 4, 5], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]]",
                normalRange.windowed(5, 1, true).toString(),
                WindowedIterator(normalRange.iterator(), 5, 1, true).toList().toString())
        assertTwoEquals("[[1, 2, 3, 4, 5], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]]",
                normalRange.windowed(6, 1, true).toString(),
                WindowedIterator(normalRange.iterator(), 6, 1, true).toList().toString())

        // Test the step parameter
        assertTwoEquals("[[1, 2], [3, 4], [5]]",
                normalList.windowed(2, 2, true).toString(),
                WindowedIterator(normalList.iterator(), 2, 2, true).toList().toString())
        assertTwoEquals("[[1, 2], [4, 5]]",
                normalList.windowed(2, 3, true).toString(),
                WindowedIterator(normalList.iterator(), 2, 3, true).toList().toString())
        assertTwoEquals("[[1, 2], [5]]",
                normalList.windowed(2, 4, true).toString(),
                WindowedIterator(normalList.iterator(), 2, 4, true).toList().toString())
        assertTwoEquals("[[1, 2]]",
                normalList.windowed(2, 5, true).toString(),
                WindowedIterator(normalList.iterator(), 2, 5, true).toList().toString())
        assertTwoEquals("[[1, 2]]",
                normalList.windowed(2, 6, true).toString(),
                WindowedIterator(normalList.iterator(), 2, 6, true).toList().toString())

        assertTwoEquals("[[1, 2], [3, 4], [5]]",
                normalRange.windowed(2, 2, true).toString(),
                WindowedIterator(normalRange.iterator(), 2, 2, true).toList().toString())
        assertTwoEquals("[[1, 2], [4, 5]]",
                normalRange.windowed(2, 3, true).toString(),
                WindowedIterator(normalRange.iterator(), 2, 3, true).toList().toString())
        assertTwoEquals("[[1, 2], [5]]",
                normalRange.windowed(2, 4, true).toString(),
                WindowedIterator(normalRange.iterator(), 2, 4, true).toList().toString())
        assertTwoEquals("[[1, 2]]",
                normalRange.windowed(2, 5, true).toString(),
                WindowedIterator(normalRange.iterator(), 2, 5, true).toList().toString())
        assertTwoEquals("[[1, 2]]",
                normalRange.windowed(2, 6, true).toString(),
                WindowedIterator(normalRange.iterator(), 2, 6, true).toList().toString())

        // Test the partialWindows parameter
        assertTwoEquals("[[1], [2], [3], [4], [5]]",
                normalList.windowed(1, 1, false).toString(),
                WindowedIterator(normalList.iterator(), 1, 1, false).toList().toString())
        assertTwoEquals("[[1, 2], [2, 3], [3, 4], [4, 5]]",
                normalList.windowed(2, 1, false).toString(),
                WindowedIterator(normalList.iterator(), 2, 1, false).toList().toString())
        assertTwoEquals("[[1, 2], [3, 4]]",
                normalList.windowed(2, 2, false).toString(),
                WindowedIterator(normalList.iterator(), 2, 2, false).toList().toString())
        assertTwoEquals("[[1, 2, 3], [2, 3, 4], [3, 4, 5]]",
                normalList.windowed(3, 1, false).toString(),
                WindowedIterator(normalList.iterator(), 3, 1, false).toList().toString())
        assertTwoEquals("[[1, 2, 3, 4], [2, 3, 4, 5]]",
                normalList.windowed(4, 1, false).toString(),
                WindowedIterator(normalList.iterator(), 4, 1, false).toList().toString())
        assertTwoEquals("[[1, 2, 3, 4, 5]]",
                normalList.windowed(5, 1, false).toString(),
                WindowedIterator(normalList.iterator(), 5, 1, false).toList().toString())
        assertTwoEquals("[]",
                normalList.windowed(6, 1, false).toString(),
                WindowedIterator(normalList.iterator(), 6, 1, false).toList().toString())

        assertTwoEquals("[[1], [2], [3], [4], [5]]",
                normalRange.windowed(1, 1, false).toString(),
                WindowedIterator(normalRange.iterator(), 1, 1, false).toList().toString())
        assertTwoEquals("[[1, 2], [2, 3], [3, 4], [4, 5]]",
                normalRange.windowed(2, 1, false).toString(),
                WindowedIterator(normalRange.iterator(), 2, 1, false).toList().toString())
        assertTwoEquals("[[1, 2, 3], [2, 3, 4], [3, 4, 5]]",
                normalRange.windowed(3, 1, false).toString(),
                WindowedIterator(normalRange.iterator(), 3, 1, false).toList().toString())
        assertTwoEquals("[[1, 2, 3, 4], [2, 3, 4, 5]]",
                normalRange.windowed(4, 1, false).toString(),
                WindowedIterator(normalRange.iterator(), 4, 1, false).toList().toString())
        assertTwoEquals("[[1, 2, 3, 4, 5]]",
                normalRange.windowed(5, 1, false).toString(),
                WindowedIterator(normalRange.iterator(), 5, 1, false).toList().toString())
        assertTwoEquals("[]",
                normalRange.windowed(6, 1, false).toString(),
                WindowedIterator(normalRange.iterator(), 6, 1, false).toList().toString())

        // Test empty or null
        assertTwoEquals("[]",
                emptyList.windowed(1, 1, true).toString(),
                WindowedIterator(emptyList.iterator(), 1, 1, true).toList().toString())
        assertTwoEquals("[]",
                emptyList.windowed(2, 1, true).toString(),
                WindowedIterator(emptyList.iterator(), 2, 1, true).toList().toString())
        assertTwoEquals("[]",
                emptyRange.windowed(1, 1, true).toString(),
                WindowedIterator(emptyRange.iterator(), 1, 1, true).toList().toString())
        assertTwoEquals("[]",
                emptyRange.windowed(2, 1, true).toString(),
                WindowedIterator(emptyRange.iterator(), 2, 1, true).toList().toString())

        assertTwoEquals("[]",
                emptyList.windowed(1, 1, true).toString(),
                WindowedIterator(nullList?.iterator(), 1, 1, true).toList().toString())
        assertTwoEquals("[]",
                emptyList.windowed(2, 1, true).toString(),
                WindowedIterator(nullList?.iterator(), 2, 1, true).toList().toString())


        assertThrow(NoSuchElementException::class) { WindowedIterator(nullList?.iterator(), 1, 1, true).next() }

        assertThrow(UnsupportedOperationException::class) { WindowedIterator(nullList?.iterator(), 1, 1, true).remove() }

        // Test big size
        val bigRange = 1.rangeTo(4403)
        assertEquals(bigRange.windowed(1048, 1047, true).toString(), WindowedIterator(bigRange.iterator(), 1048, 1047, true).toList().toString())
    }

    private fun <T> Iterator<T>.toList(): List<T> {
        val result = ArrayList<T>()
        for (t in this) {
            result.add(t)
        }
        return result;
    }
}