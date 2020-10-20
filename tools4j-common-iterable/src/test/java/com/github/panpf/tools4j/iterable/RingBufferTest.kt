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
import org.junit.Assert.*
import org.junit.Test

class RingBufferTest {

    @Test
    fun testIsFull() {
        val ringBuffer = RingBuffer<Char>(5)

        assertFalse(ringBuffer.isFull)

        ringBuffer.add('a')
        assertFalse(ringBuffer.isFull)

        ringBuffer.add('b')
        assertFalse(ringBuffer.isFull)

        ringBuffer.add('c')
        assertFalse(ringBuffer.isFull)

        ringBuffer.add('d')
        assertFalse(ringBuffer.isFull)

        ringBuffer.add('e')
        assertTrue(ringBuffer.isFull)

        ringBuffer.removeFirst(3)
        assertFalse(ringBuffer.isFull)

        assertNoThrow { ringBuffer.add('f') }
        assertFalse(ringBuffer.isFull)

        assertNoThrow { ringBuffer.add('g') }
        assertFalse(ringBuffer.isFull)

        assertNoThrow { ringBuffer.add('h') }
        assertTrue(ringBuffer.isFull)

        ringBuffer.removeFirst(5)
        assertFalse(ringBuffer.isFull)
    }

    @Test
    fun testIsEmpty() {
        val ringBuffer = RingBuffer<Char>(5)

        assertTrue(ringBuffer.isEmpty())

        ringBuffer.add('a')
        assertFalse(ringBuffer.isEmpty())

        ringBuffer.add('b')
        assertFalse(ringBuffer.isEmpty())

        ringBuffer.add('c')
        assertFalse(ringBuffer.isEmpty())

        ringBuffer.add('d')
        assertFalse(ringBuffer.isEmpty())

        ringBuffer.add('e')
        assertFalse(ringBuffer.isEmpty())

        ringBuffer.removeFirst(3)
        assertFalse(ringBuffer.isEmpty())

        assertNoThrow { ringBuffer.add('f') }
        assertFalse(ringBuffer.isEmpty())

        assertNoThrow { ringBuffer.add('g') }
        assertFalse(ringBuffer.isEmpty())

        assertNoThrow { ringBuffer.add('h') }
        assertFalse(ringBuffer.isEmpty())

        ringBuffer.removeFirst(5)
        assertTrue(ringBuffer.isEmpty())
    }

    @Test
    fun testSize() {
        val ringBuffer = RingBuffer<Char>(5)

        assertEquals(0, ringBuffer.size)

        assertNoThrow { ringBuffer.add('a') }
        assertEquals(1, ringBuffer.size)

        assertNoThrow { ringBuffer.add('b') }
        assertEquals(2, ringBuffer.size)

        assertNoThrow { ringBuffer.add('c') }
        assertEquals(3, ringBuffer.size)

        assertNoThrow { ringBuffer.add('d') }
        assertEquals(4, ringBuffer.size)

        assertNoThrow { ringBuffer.add('e') }
        assertEquals(5, ringBuffer.size)

        ringBuffer.removeFirst(3)
        assertEquals(2, ringBuffer.size)

        assertNoThrow { ringBuffer.add('f') }
        assertEquals(3, ringBuffer.size)

        assertNoThrow { ringBuffer.add('g') }
        assertEquals(4, ringBuffer.size)

        assertNoThrow { ringBuffer.add('h') }
        assertEquals(5, ringBuffer.size)

        ringBuffer.removeFirst(5)
        assertEquals(0, ringBuffer.size)
    }

    @Test
    fun testIterator() {
        val ringBuffer = RingBuffer<Char>(5)

        assertEquals("[]", Iterable { ringBuffer.iterator() }.joinToString(prefix = "[", postfix = "]"))

        assertNoThrow { ringBuffer.add('a') }
        assertEquals("[a]", Iterable { ringBuffer.iterator() }.joinToString(prefix = "[", postfix = "]"))

        assertNoThrow { ringBuffer.add('b') }
        assertEquals("[a, b]", Iterable { ringBuffer.iterator() }.joinToString(prefix = "[", postfix = "]"))

        assertNoThrow { ringBuffer.add('c') }
        assertEquals("[a, b, c]", Iterable { ringBuffer.iterator() }.joinToString(prefix = "[", postfix = "]"))

        assertNoThrow { ringBuffer.add('d') }
        assertEquals("[a, b, c, d]", Iterable { ringBuffer.iterator() }.joinToString(prefix = "[", postfix = "]"))

        assertNoThrow { ringBuffer.add('e') }
        assertEquals("[a, b, c, d, e]", Iterable { ringBuffer.iterator() }.joinToString(prefix = "[", postfix = "]"))

        ringBuffer.removeFirst(3)
        assertEquals("[d, e]", Iterable { ringBuffer.iterator() }.joinToString(prefix = "[", postfix = "]"))

        assertNoThrow { ringBuffer.add('f') }
        assertEquals("[d, e, f]", Iterable { ringBuffer.iterator() }.joinToString(prefix = "[", postfix = "]"))

        assertNoThrow { ringBuffer.add('g') }
        assertEquals("[d, e, f, g]", Iterable { ringBuffer.iterator() }.joinToString(prefix = "[", postfix = "]"))

        assertNoThrow { ringBuffer.add('h') }
        assertEquals("[d, e, f, g, h]", Iterable { ringBuffer.iterator() }.joinToString(prefix = "[", postfix = "]"))

        ringBuffer.removeFirst(5)
        assertEquals("[]", Iterable { ringBuffer.iterator() }.joinToString(prefix = "[", postfix = "]"))

        assertThrow(UnsupportedOperationException::class) { ringBuffer.iterator().remove() }
    }

    @Test
    fun testAdd() {
        val ringBuffer = RingBuffer<Char>(5)

        assertNoThrow { ringBuffer.add('a') }
        assertNoThrow { ringBuffer.add('b') }
        assertNoThrow { ringBuffer.add('c') }
        assertNoThrow { ringBuffer.add('d') }
        assertNoThrow { ringBuffer.add('e') }
        assertThrow(IllegalStateException::class) { ringBuffer.add('f') }

        ringBuffer.removeFirst(3)
        assertNoThrow { ringBuffer.add('f') }
        assertNoThrow { ringBuffer.add('g') }
        assertNoThrow { ringBuffer.add('h') }
        assertThrow(IllegalStateException::class) { ringBuffer.add('i') }
    }

    @Test
    fun testGet() {
        val ringBuffer = RingBuffer<Char>(5)

        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[0] }

        assertNoThrow { ringBuffer.add('a') }
        assertEquals('a', ringBuffer[0])
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[1] }

        assertNoThrow { ringBuffer.add('b') }
        assertEquals('a', ringBuffer[0])
        assertEquals('b', ringBuffer[1])
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[2] }

        assertNoThrow { ringBuffer.add('c') }
        assertEquals('a', ringBuffer[0])
        assertEquals('b', ringBuffer[1])
        assertEquals('c', ringBuffer[2])
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[3] }

        assertNoThrow { ringBuffer.add('d') }
        assertEquals('a', ringBuffer[0])
        assertEquals('b', ringBuffer[1])
        assertEquals('c', ringBuffer[2])
        assertEquals('d', ringBuffer[3])
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[4] }

        assertNoThrow { ringBuffer.add('e') }
        assertEquals('a', ringBuffer[0])
        assertEquals('b', ringBuffer[1])
        assertEquals('c', ringBuffer[2])
        assertEquals('d', ringBuffer[3])
        assertEquals('e', ringBuffer[4])
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[5] }

        ringBuffer.removeFirst(3)
        assertEquals('d', ringBuffer[0])
        assertEquals('e', ringBuffer[1])
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[2] }

        assertNoThrow { ringBuffer.add('f') }
        assertEquals('d', ringBuffer[0])
        assertEquals('e', ringBuffer[1])
        assertEquals('f', ringBuffer[2])
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[3] }

        assertNoThrow { ringBuffer.add('g') }
        assertEquals('d', ringBuffer[0])
        assertEquals('e', ringBuffer[1])
        assertEquals('f', ringBuffer[2])
        assertEquals('g', ringBuffer[3])
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[4] }

        assertNoThrow { ringBuffer.add('h') }
        assertEquals('d', ringBuffer[0])
        assertEquals('e', ringBuffer[1])
        assertEquals('f', ringBuffer[2])
        assertEquals('g', ringBuffer[3])
        assertEquals('h', ringBuffer[4])
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[5] }

        ringBuffer.removeFirst(5)
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[0] }
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[1] }
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[2] }
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[3] }
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[4] }
        assertThrow(IndexOutOfBoundsException::class) { ringBuffer[5] }
    }

    @Test
    fun testExpanded() {
        val ringBuffer = RingBuffer<Char>(3)

        ringBuffer.add('a')
        ringBuffer.add('b')
        ringBuffer.add('c')
        assertThrow(IllegalStateException::class) { ringBuffer.add('d') }

        assertThrow(IllegalArgumentException::class) { ringBuffer.expanded(0) }
        assertThrow(IllegalArgumentException::class) { ringBuffer.expanded(1) }
        assertThrow(IllegalArgumentException::class) { ringBuffer.expanded(2) }

        val newRingBuffer = ringBuffer.expanded(3)
        assertTrue(newRingBuffer !== ringBuffer)
        assertThrow(IllegalStateException::class) { ringBuffer.add('d') }
        assertThrow(IllegalStateException::class) { newRingBuffer.add('d') }

        val newRingBuffer1 = ringBuffer.expanded(4)
        assertTrue(newRingBuffer1 !== ringBuffer)
        assertThrow(IllegalStateException::class) { ringBuffer.add('d') }
        newRingBuffer1.add('d')
        assertThrow(IllegalStateException::class) { newRingBuffer1.add('e') }

        val newRingBuffer2 = ringBuffer.expanded(5)
        assertTrue(newRingBuffer2 !== ringBuffer)
        assertThrow(IllegalStateException::class) { ringBuffer.add('d') }
        newRingBuffer2.add('d')
        newRingBuffer2.add('e')
        assertThrow(IllegalStateException::class) { newRingBuffer2.add('f') }
    }

    @Test
    fun testToArray() {
        val ringBuffer = RingBuffer<Char>(3)
        ringBuffer.add('a')
        ringBuffer.add('b')
        ringBuffer.add('c')

        assertEquals("[a, b, c]", ringBuffer.toArray(arrayOfNulls<Char>(0)).joinToString(prefix = "[", postfix = "]"))
        assertEquals("[a, b, c]", ringBuffer.toArray(arrayOfNulls<Char>(3)).joinToString(prefix = "[", postfix = "]"))
        assertEquals("[a, b, c, null]", ringBuffer.toArray(arrayOfNulls<Char>(4)).joinToString(prefix = "[", postfix = "]"))
        assertEquals("[a, b, c]", ringBuffer.toArray().joinToString(prefix = "[", postfix = "]"))
    }
}