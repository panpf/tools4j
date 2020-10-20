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

package com.github.panpf.tools4j.sequences

import com.github.panpf.tools4j.common.InitialValue
import com.github.panpf.tools4j.common.NextValue
import com.github.panpf.tools4j.iterable.EmptyIterator
import com.github.panpf.tools4j.test.ktx.assertNoThrow
import com.github.panpf.tools4j.test.ktx.assertThrow
import com.github.panpf.tools4j.test.ktx.assertTwoEquals
import com.github.panpf.tools4j.test.ktx.assertTwoThrow
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap
import com.github.panpf.tools4j.common.Pair as Pair2

class SequencexTest {

    @Test
    fun testJoinToArrayString() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null

        assertTwoEquals("[60, 30, 70, 20, 10]",
                normalSequence0.joinToString(transform = { it + "0" }, prefix = "[", postfix = "]"),
                Sequencex.joinToArrayString(normalSequence1) { it + "0" })
        assertTwoEquals("[]",
                emptySequence0.joinToString(transform = { it + "0" }, prefix = "[", postfix = "]"),
                Sequencex.joinToArrayString(emptySequence1) { it + "0" })
        assertEquals("[]",
                Sequencex.joinToArrayString(nullSequence1) { it + "0" })

        assertTwoEquals("[6, 3, 7, 2, 1]",
                normalSequence0.joinToString(prefix = "[", postfix = "]"),
                Sequencex.joinToArrayString(normalSequence1))
        assertTwoEquals("[]",
                emptySequence0.joinToString(prefix = "[", postfix = "]"),
                Sequencex.joinToArrayString(emptySequence1))
        assertEquals("[]",
                Sequencex.joinToArrayString(nullSequence1))
    }

    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */

    @Test
    fun testAsSequence() {
        assertTwoEquals(
                "4, 2, 1",
                Hashtable(hashMapOf("2" to 2, "4" to 4, "1" to 1)).elements().asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(Hashtable(hashMapOf("2" to 2, "4" to 4, "1" to 1)).elements()))
        )
        assertEquals(
                "",
                Sequencex.joinToString(Sequencex.asSequence(null as Enumeration<String>?))
        )
        assertThrow(UnsupportedOperationException::class) {
            Sequencex.asSequence(Hashtable(hashMapOf("2" to 2, "4" to 4, "1" to 1)).elements()).iterator().remove()
        }
        assertThrow(NoSuchElementException::class) {
            Sequencex.asSequence(null as Enumeration<String>?).iterator().next()
        }

        assertTwoEquals(
                "2, 4, 1",
                listOf("2", "4", "1").iterator().asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(listOf("2", "4", "1").iterator()))
        )
        assertEquals(
                "",
                Sequencex.joinToString(Sequencex.asSequence(null as Iterator<String>?))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2, 4, 1",
                listOf("2", "4", "1").asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(listOf("2", "4", "1")))
        )
        assertEquals(
                "",
                Sequencex.joinToString(Sequencex.asSequence(null as Iterable<String>?))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2, 4, 1",
                listOf("2", "4", "1").asSequence().asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(Sequencex.asSequence(listOf("2", "4", "1"))))
        )
        @Suppress("RedundantAsSequence")
        assertEquals(
                "",
                Sequencex.joinToString(Sequencex.asSequence(null as Sequence<String>?))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2=22, 4=4444, 1=1",
                mapOf("2" to "22", "4" to "4444", "1" to "1").asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(mapOf("2" to "22", "4" to "4444", "1" to "1")))
        )
        @Suppress("RedundantAsSequence")
        assertEquals(
                "",
                Sequencex.joinToString(Sequencex.asSequence(null as Map<String, String>?))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2, 4, 1",
                arrayOf("2", "4", "1").asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(arrayOf("2", "4", "1")))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2, 4, 1",
                byteArrayOf(2, 4, 1).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(byteArrayOf(2, 4, 1)))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2, 4, 1",
                shortArrayOf(2, 4, 1).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(shortArrayOf(2, 4, 1)))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2, 4, 1",
                intArrayOf(2, 4, 1).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(intArrayOf(2, 4, 1)))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2, 4, 1",
                longArrayOf(2, 4, 1).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(longArrayOf(2, 4, 1)))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2.0, 4.0, 1.0",
                floatArrayOf(2f, 4f, 1f).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(floatArrayOf(2f, 4f, 1f)))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2.0, 4.0, 1.0",
                doubleArrayOf(2.0, 4.0, 1.0).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(doubleArrayOf(2.0, 4.0, 1.0)))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "true, false, false",
                booleanArrayOf(true, false, false).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(booleanArrayOf(true, false, false)))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "a, h, t",
                charArrayOf('a', 'h', 't').asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(charArrayOf('a', 'h', 't')))
        )
    }

    @Test
    fun testAsIterable() {
        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "a, h, t",
                charArrayOf('a', 'h', 't').asSequence().asIterable().joinToString(),
                Sequencex.asIterable(Sequencex.asSequence(charArrayOf('a', 'h', 't'))).joinToString()
        )
        @Suppress("RedundantAsSequence")
        assertEquals(
                "",
                Sequencex.asIterable(null as Sequence<String>?).joinToString()
        )
    }

    @Test
    fun testSequenceOf() {
        assertTwoEquals(
                "5, 8, 3, 2",
                sequenceOf(5, 8, 3, 2).joinToString(),
                Sequencex.joinToString(Sequencex.sequenceOf(5, 8, 3, 2))
        )

        assertTwoEquals(
                "fasf, 5234, _+, vzxvefg",
                sequenceOf("fasf", "5234", "_+", "vzxvefg").joinToString(),
                Sequencex.joinToString(Sequencex.sequenceOf("fasf", "5234", "_+", "vzxvefg"))
        )
    }

    @Test
    fun testEmptySequence() {
        val sequence = Sequencex.emptySequence<Int>()
        assertTrue(EmptySequence.INSTANCE === sequence)
        assertTrue(EmptyIterator.INSTANCE === sequence.iterator())
        assertTrue(sequence is DropTakeSequence<Int>)
        assertTrue(EmptySequence.INSTANCE === (sequence as DropTakeSequence<Int>).drop(10))
        assertTrue(EmptySequence.INSTANCE === (sequence as DropTakeSequence<Int>).take(10))
    }

    @Test
    fun testConstrainOnce() {
        val sequence = Sequencex.sequenceOf(5, 8, 3, 2)

        val onceSequence = Sequencex.constrainOnce(sequence)
        assertTrue(onceSequence is ConstrainedOnceSequence)
        assertTrue(onceSequence !== sequence)

        assertNoThrow { onceSequence.iterator() }
        assertThrow(IllegalStateException::class) { onceSequence.iterator() }

        val onceSequence1 = Sequencex.constrainOnce(onceSequence)
        assertTrue(onceSequence === onceSequence1)
    }

    @Test
    fun testGenerateSequence() {
        var next = 0
        assertTwoEquals(
                "0, 1, 2, 3, 4, 5",
                generateSequence { if (next <= 5) next++ else null }.joinToString(),
                Sequencex.joinToString(Sequencex.generateSequence(IntInitialValue(5)))
        )

        assertTwoEquals(
                "0, 1, 2, 3, 4, 5",
                generateSequence(0) { if (it < 5) it + 1 else null }.joinToString(),
                Sequencex.joinToString(Sequencex.generateSequence(0) { if (it < 5) it + 1 else null })
        )

        assertTwoEquals(
                "",
                generateSequence(null as Int?) { if (it < 5) it + 1 else null }.joinToString(),
                Sequencex.joinToString(Sequencex.generateSequence(null as Int?) { if (it < 5) it + 1 else null })
        )

        assertTwoEquals(
                "0, 1, 2, 3, 4, 5",
                generateSequence({ 0 }) { if (it < 5) it + 1 else null }.joinToString(),
                Sequencex.joinToString(Sequencex.generateSequence(InitialValue { 0 }, NextValue<Int> { if (it < 5) it + 1 else null }))
        )
    }

    @Test
    fun testFlatten() {
        assertTwoEquals(
                "a, b, c, d, e, f, g, h, i",
                sequenceOf(
                        sequenceOf("a", "b", "c"),
                        sequenceOf("d", "e", "f"),
                        sequenceOf("g", "h", "i")
                ).flatten().joinToString(),
                Sequencex.joinToString(Sequencex.flatten(Sequencex.sequenceOf(
                        Sequencex.sequenceOf("a", "b", "c"),
                        Sequencex.sequenceOf("d", "e", "f"),
                        Sequencex.sequenceOf("g", "h", "i")
                )))
        )

        assertTwoEquals(
                "a, b, c, d, e, f, g, h, i",
                sequenceOf(
                        sequenceOf("a", "b", "c"),
                        sequenceOf("d", "e", "f"),
                        sequenceOf("g", "h", "i")
                ).map { it }.flatten().joinToString(),
                Sequencex.joinToString(Sequencex.flatten(Sequencex.map(Sequencex.sequenceOf(
                        Sequencex.sequenceOf("a", "b", "c"),
                        Sequencex.sequenceOf("d", "e", "f"),
                        Sequencex.sequenceOf("g", "h", "i")
                )) { it }))
        )

        assertTwoEquals(
                "a, b, c, d, e, f, g, h, i",
                sequenceOf(
                        listOf("a", "b", "c"),
                        listOf("d", "e", "f"),
                        listOf("g", "h", "i")
                ).flatten().joinToString(),
                Sequencex.joinToString(Sequencex.flattenOfIterable(Sequencex.sequenceOf(
                        listOf("a", "b", "c"),
                        listOf("d", "e", "f"),
                        listOf("g", "h", "i")
                )))
        )
    }

    @Test
    fun testContains() {
        val normalSequence0 = sequenceOf("a", "b", "c")
        val normalSequence1 = Sequencex.sequenceOf("a", "b", "c")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(true, normalSequence0.contains("a"), Sequencex.contains(normalSequence1, "a"))
        assertTwoEquals(true, normalSequence0.contains("b"), Sequencex.contains(normalSequence1, "b"))
        assertTwoEquals(true, normalSequence0.contains("c"), Sequencex.contains(normalSequence1, "c"))
        assertTwoEquals(false, normalSequence0.contains("d"), Sequencex.contains(normalSequence1, "d"))
        assertTwoEquals(false, emptySequence0.contains("a"), Sequencex.contains(emptySequence1, "a"))
        assertTwoEquals(false, emptySequence0.contains("d"), Sequencex.contains(emptySequence1, "d"))
        assertFalse(Sequencex.contains(emptySequence1, "a"))
        assertFalse(Sequencex.contains(nullSequence1, "d"))
    }

    @Test
    fun testElementAt() {
        val normalSequence0 = sequenceOf("a", "b", "c")
        val normalSequence1 = Sequencex.sequenceOf("a", "b", "c")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals("a", normalSequence0.elementAt(0), Sequencex.elementAt(normalSequence1, 0))
        assertTwoEquals("b", normalSequence0.elementAt(1), Sequencex.elementAt(normalSequence1, 1))
        assertTwoEquals("c", normalSequence0.elementAt(2), Sequencex.elementAt(normalSequence1, 2))
        assertTwoThrow(IndexOutOfBoundsException::class, { normalSequence0.elementAt(-1) }, { Sequencex.elementAt(normalSequence1, -1) })
        assertTwoThrow(IndexOutOfBoundsException::class, { normalSequence0.elementAt(3) }, { Sequencex.elementAt(normalSequence1, 3) })

        assertTwoEquals("a", normalSequence0.elementAtOrElse(0) { "j" }, Sequencex.elementAtOrElse(normalSequence1, 0) { "j" })
        assertTwoEquals("b", normalSequence0.elementAtOrElse(1) { "k" }, Sequencex.elementAtOrElse(normalSequence1, 1) { "j" })
        assertTwoEquals("c", normalSequence0.elementAtOrElse(2) { "j" }, Sequencex.elementAtOrElse(normalSequence1, 2) { "j" })
        assertTwoEquals("j", normalSequence0.elementAtOrElse(-1) { "j" }, Sequencex.elementAtOrElse(normalSequence1, -1) { "j" })
        assertTwoEquals("k", normalSequence0.elementAtOrElse(3) { "k" }, Sequencex.elementAtOrElse(normalSequence1, 3) { "k" })
        assertEquals("x", Sequencex.elementAtOrElse(nullSequence1, 0) { "x" })
        assertEquals("y", Sequencex.elementAtOrElse(nullSequence1, 1) { "y" })
        assertEquals("z", Sequencex.elementAtOrElse(nullSequence1, 2) { "z" })
        assertEquals("j", Sequencex.elementAtOrElse(nullSequence1, -1) { "j" })
        assertEquals("k", Sequencex.elementAtOrElse(nullSequence1, 3) { "k" })

        assertTwoEquals("a", normalSequence0.elementAtOrNull(0), Sequencex.elementAtOrNull(normalSequence1, 0))
        assertTwoEquals("b", normalSequence0.elementAtOrNull(1), Sequencex.elementAtOrNull(normalSequence1, 1))
        assertTwoEquals("c", normalSequence0.elementAtOrNull(2), Sequencex.elementAtOrNull(normalSequence1, 2))
        assertTwoEquals(null, normalSequence0.elementAtOrNull(-1), Sequencex.elementAtOrNull(normalSequence1, -1))
        assertTwoEquals(null, normalSequence0.elementAtOrNull(3), Sequencex.elementAtOrNull(normalSequence1, 3))
        assertNull(Sequencex.elementAtOrNull(nullSequence1, 0))
        assertNull(Sequencex.elementAtOrNull(nullSequence1, 1))
        assertNull(Sequencex.elementAtOrNull(nullSequence1, 2))
        assertNull(Sequencex.elementAtOrNull(nullSequence1, -1))
        assertNull(Sequencex.elementAtOrNull(nullSequence1, 3))
    }

    @Test
    fun testFind() {
        val normalSequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")

        assertTwoEquals("aj", normalSequence0.find { it.startsWith("a") }, Sequencex.find(normalSequence1) { it.startsWith("a") })
        assertTwoEquals("bj", normalSequence0.find { it.startsWith("b") }, Sequencex.find(normalSequence1) { it.startsWith("b") })
        assertTwoEquals("cj", normalSequence0.find { it.startsWith("c") }, Sequencex.find(normalSequence1) { it.startsWith("c") })
        assertTwoEquals(null, normalSequence0.find { it.startsWith("k") }, Sequencex.find(normalSequence1) { it.startsWith("k") })

        assertTwoEquals("aj", normalSequence0.findLast { it.startsWith("a") }, Sequencex.findLast(normalSequence1) { it.startsWith("a") })
        assertTwoEquals("bo", normalSequence0.findLast { it.startsWith("b") }, Sequencex.findLast(normalSequence1) { it.startsWith("b") })
        assertTwoEquals("cj", normalSequence0.findLast { it.startsWith("c") }, Sequencex.findLast(normalSequence1) { it.startsWith("c") })
        assertTwoEquals(null, normalSequence0.findLast { it.startsWith("k") }, Sequencex.findLast(normalSequence1) { it.startsWith("k") })
    }

    @Test
    fun testFirst() {
        val normalSequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals("aj", normalSequence0.first(), Sequencex.first(normalSequence1))
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.first() }, { Sequencex.first(emptySequence1) })
        assertThrow(NoSuchElementException::class) { Sequencex.first(nullSequence1) }

        assertTwoEquals("aj", normalSequence0.first { it.startsWith("a") }, Sequencex.first(normalSequence1) { it.startsWith("a") })
        assertTwoEquals("bj", normalSequence0.first { it.startsWith("b") }, Sequencex.first(normalSequence1) { it.startsWith("b") })
        assertTwoEquals("cj", normalSequence0.first { it.startsWith("c") }, Sequencex.first(normalSequence1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { normalSequence0.first { it.startsWith("k") } }, { Sequencex.first(normalSequence1) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.first { it.startsWith("a") } }, { Sequencex.first(emptySequence1) { it.startsWith("a") } })
        assertThrow(NoSuchElementException::class) { Sequencex.first(nullSequence1) { it.startsWith("a") } }

        assertTwoEquals("aj", normalSequence0.firstOrNull(), Sequencex.firstOrNull(normalSequence1))
        assertTwoEquals(null, emptySequence0.firstOrNull(), Sequencex.firstOrNull(emptySequence1))
        assertNull(Sequencex.firstOrNull(nullSequence1))

        assertTwoEquals("aj", normalSequence0.firstOrNull { it.startsWith("a") }, Sequencex.firstOrNull(normalSequence1) { it.startsWith("a") })
        assertTwoEquals("bj", normalSequence0.firstOrNull { it.startsWith("b") }, Sequencex.firstOrNull(normalSequence1) { it.startsWith("b") })
        assertTwoEquals("cj", normalSequence0.firstOrNull { it.startsWith("c") }, Sequencex.firstOrNull(normalSequence1) { it.startsWith("c") })
        assertTwoEquals(null, normalSequence0.firstOrNull { it.startsWith("k") }, Sequencex.firstOrNull(normalSequence1) { it.startsWith("k") })
        assertTwoEquals(null, emptySequence0.firstOrNull { it.startsWith("k") }, Sequencex.firstOrNull(emptySequence1) { it.startsWith("k") })
        assertNull(Sequencex.firstOrNull(nullSequence1) { it.startsWith("k") })
    }

    @Test
    fun testIndexOf() {
        val normalSequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(0, normalSequence0.indexOf("aj"), Sequencex.indexOf(normalSequence1, "aj"))
        assertTwoEquals(1, normalSequence0.indexOf("bj"), Sequencex.indexOf(normalSequence1, "bj"))
        assertTwoEquals(2, normalSequence0.indexOf("cj"), Sequencex.indexOf(normalSequence1, "cj"))
        assertTwoEquals(3, normalSequence0.indexOf("bo"), Sequencex.indexOf(normalSequence1, "bo"))
        assertTwoEquals(-1, normalSequence0.indexOf("bb"), Sequencex.indexOf(normalSequence1, "bb"))
        assertTwoEquals(-1, emptySequence0.indexOf("bb"), Sequencex.indexOf(emptySequence1, "bb"))
        assertEquals(-1, Sequencex.indexOf(nullSequence1, "bb"))

        assertTwoEquals(0, normalSequence0.indexOfFirst { it.startsWith("a") }, Sequencex.indexOfFirst(normalSequence1) { it.startsWith("a") })
        assertTwoEquals(1, normalSequence0.indexOfFirst { it.startsWith("b") }, Sequencex.indexOfFirst(normalSequence1) { it.startsWith("b") })
        assertTwoEquals(2, normalSequence0.indexOfFirst { it.startsWith("c") }, Sequencex.indexOfFirst(normalSequence1) { it.startsWith("c") })
        assertTwoEquals(-1, normalSequence0.indexOfFirst { it.startsWith("k") }, Sequencex.indexOfFirst(normalSequence1) { it.startsWith("k") })
        assertTwoEquals(-1, emptySequence0.indexOfFirst { it.startsWith("k") }, Sequencex.indexOfFirst(emptySequence1) { it.startsWith("k") })
        assertEquals(-1, Sequencex.indexOfFirst(nullSequence1) { it.startsWith("k") })

        assertTwoEquals(0, normalSequence0.indexOfLast { it.startsWith("a") }, Sequencex.indexOfLast(normalSequence1) { it.startsWith("a") })
        assertTwoEquals(3, normalSequence0.indexOfLast { it.startsWith("b") }, Sequencex.indexOfLast(normalSequence1) { it.startsWith("b") })
        assertTwoEquals(2, normalSequence0.indexOfLast { it.startsWith("c") }, Sequencex.indexOfLast(normalSequence1) { it.startsWith("c") })
        assertTwoEquals(-1, normalSequence0.indexOfLast { it.startsWith("k") }, Sequencex.indexOfLast(normalSequence1) { it.startsWith("k") })
        assertTwoEquals(-1, emptySequence0.indexOfLast { it.startsWith("k") }, Sequencex.indexOfLast(emptySequence1) { it.startsWith("k") })
        assertEquals(-1, Sequencex.indexOfLast(nullSequence1) { it.startsWith("k") })

        assertTwoEquals(0, normalSequence0.lastIndexOf("aj"), Sequencex.lastIndexOf(normalSequence1, "aj"))
        assertTwoEquals(1, normalSequence0.lastIndexOf("bj"), Sequencex.lastIndexOf(normalSequence1, "bj"))
        assertTwoEquals(2, normalSequence0.lastIndexOf("cj"), Sequencex.lastIndexOf(normalSequence1, "cj"))
        assertTwoEquals(3, normalSequence0.lastIndexOf("bo"), Sequencex.lastIndexOf(normalSequence1, "bo"))
        assertTwoEquals(-1, normalSequence0.lastIndexOf("bb"), Sequencex.lastIndexOf(normalSequence1, "bb"))
        assertEquals(-1, Sequencex.lastIndexOf(nullSequence1, "bb"))
    }

    @Test
    fun testLast() {
        val normalSequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals("bo", normalSequence0.last(), Sequencex.last(normalSequence1))
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.last() }, { Sequencex.last(emptySequence1) })
        assertThrow(NoSuchElementException::class) { Sequencex.last(nullSequence1) }

        assertTwoEquals("aj", normalSequence0.last { it.startsWith("a") }, Sequencex.last(normalSequence1) { it.startsWith("a") })
        assertTwoEquals("bo", normalSequence0.last { it.startsWith("b") }, Sequencex.last(normalSequence1) { it.startsWith("b") })
        assertTwoEquals("cj", normalSequence0.last { it.startsWith("c") }, Sequencex.last(normalSequence1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { normalSequence0.last { it.startsWith("k") } }, { Sequencex.last(normalSequence1) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.last { it.startsWith("a") } }, { Sequencex.last(emptySequence1) { it.startsWith("a") } })
        assertThrow(NoSuchElementException::class) { Sequencex.last(nullSequence1) { it.startsWith("a") } }

        assertTwoEquals("bo", normalSequence0.lastOrNull(), Sequencex.lastOrNull(normalSequence1))
        assertTwoEquals(null, emptySequence0.lastOrNull(), Sequencex.lastOrNull(emptySequence1))
        assertNull(Sequencex.lastOrNull(nullSequence1))

        assertTwoEquals("aj", normalSequence0.lastOrNull { it.startsWith("a") }, Sequencex.lastOrNull(normalSequence1) { it.startsWith("a") })
        assertTwoEquals("bo", normalSequence0.lastOrNull { it.startsWith("b") }, Sequencex.lastOrNull(normalSequence1) { it.startsWith("b") })
        assertTwoEquals("cj", normalSequence0.lastOrNull { it.startsWith("c") }, Sequencex.lastOrNull(normalSequence1) { it.startsWith("c") })
        assertTwoEquals(null, normalSequence0.lastOrNull { it.startsWith("k") }, Sequencex.lastOrNull(normalSequence1) { it.startsWith("k") })
        assertTwoEquals(null, emptySequence0.lastOrNull { it.startsWith("k") }, Sequencex.lastOrNull(emptySequence1) { it.startsWith("k") })
        assertNull(Sequencex.lastOrNull(nullSequence1) { it.startsWith("k") })
    }

    @Test
    fun testSingle() {
        val singleSequence0 = sequenceOf("cj")
        val singleSequence1 = Sequencex.sequenceOf("cj")
        val multiSequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val multiSequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals("cj", singleSequence0.single(), Sequencex.single(singleSequence1))
        assertTwoThrow(IllegalArgumentException::class, { multiSequence0.single() }, { Sequencex.single(multiSequence1) })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.single() }, { Sequencex.single(emptySequence1) })
        assertThrow(NoSuchElementException::class) { Sequencex.single(nullSequence1) }

        assertTwoEquals("cj",
                singleSequence0.single { it.startsWith("c") },
                Sequencex.single(singleSequence1) { it.startsWith("c") })
        assertTwoEquals("cj",
                multiSequence0.single { it.startsWith("c") },
                Sequencex.single(multiSequence1) { it.startsWith("c") })
        assertTwoThrow(IllegalArgumentException::class,
                { multiSequence0.single { it.startsWith("b") } },
                { Sequencex.single(multiSequence1) { it.startsWith("b") } })
        assertTwoThrow(NoSuchElementException::class,
                { singleSequence0.single { it.startsWith("b") } },
                { Sequencex.single(singleSequence1) { it.startsWith("b") } })
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.single { it.startsWith("c") } },
                { Sequencex.single(emptySequence1) { it.startsWith("c") } })
        assertThrow(NoSuchElementException::class) { Sequencex.single(nullSequence1) { it.startsWith("b") } }

        assertTwoEquals("cj", singleSequence0.singleOrNull(), Sequencex.singleOrNull(singleSequence1))
        assertTwoEquals(null, multiSequence0.singleOrNull(), Sequencex.singleOrNull(multiSequence1))
        assertTwoEquals(null, emptySequence0.singleOrNull(), Sequencex.singleOrNull(emptySequence1))
        assertNull(Sequencex.singleOrNull(nullSequence1))

        assertTwoEquals("cj",
                singleSequence0.singleOrNull { it.startsWith("c") },
                Sequencex.singleOrNull(singleSequence1) { it.startsWith("c") })
        assertTwoEquals("cj",
                multiSequence0.singleOrNull { it.startsWith("c") },
                Sequencex.singleOrNull(multiSequence1) { it.startsWith("c") })
        assertTwoEquals(null,
                multiSequence0.singleOrNull { it.startsWith("b") },
                Sequencex.singleOrNull(multiSequence1) { it.startsWith("b") })
        assertTwoEquals(null,
                singleSequence0.singleOrNull { it.startsWith("b") },
                Sequencex.singleOrNull(singleSequence1) { it.startsWith("b") })
        assertTwoEquals(null,
                emptySequence0.singleOrNull { it.startsWith("c") },
                Sequencex.singleOrNull(emptySequence1) { it.startsWith("c") })
        assertEquals(null,
                Sequencex.singleOrNull(nullSequence1) { it.startsWith("b") })
    }

    @Test
    fun testDrop() {
        val normalSequence0 = sequenceOf("aj", "bj", "cj", "dj")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "dj")
        val nullSequence1 = null as Sequence<String>?

        /*
         * drop 系列的方法表示从列表头部开始跳过部分元素
         */

        // drop 方法的意思是从列表头部开始跳过多少个元素

        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.drop(-1) },
                { Sequencex.drop(normalSequence1, -1) }
        )

        assertTrue(normalSequence0 === normalSequence0.drop(0))
        assertTrue(normalSequence1 === Sequencex.drop(normalSequence1, 0))
        assertTrue(Sequencex.emptySequence<String>() === Sequencex.drop(nullSequence1, 0))

        assertTwoEquals("bj, cj, dj",
                normalSequence0.drop(1).joinToString(),
                Sequencex.joinToString(Sequencex.drop(normalSequence1, 1)))

        assertTwoEquals("cj, dj",
                normalSequence0.drop(2).joinToString(),
                Sequencex.joinToString(Sequencex.drop(normalSequence1, 2)))

        assertTwoEquals("dj",
                normalSequence0.drop(3).joinToString(),
                Sequencex.joinToString(Sequencex.drop(normalSequence1, 3)))

        assertTwoEquals("",
                normalSequence0.drop(4).joinToString(),
                Sequencex.joinToString(Sequencex.drop(normalSequence1, 4)))

        assertTwoEquals("",
                normalSequence0.drop(5).joinToString(),
                Sequencex.joinToString(Sequencex.drop(normalSequence1, 5)))

        assertTwoEquals("",
                normalSequence0.drop(5).joinToString(),
                Sequencex.joinToString(Sequencex.drop(Sequencex.drop(normalSequence1, 5), 5)))

        // dropWhile 方法的意思是从不符合条件的元素开始往后遍历

        assertTwoEquals("aj, bj, cj, dj",
                normalSequence0.dropWhile { !it.startsWith("a") }.joinToString(),
                Sequencex.joinToString(Sequencex.dropWhile(normalSequence1) { !it.startsWith("a") }))

        assertTwoEquals("bj, cj, dj",
                normalSequence0.dropWhile { !it.startsWith("b") }.joinToString(),
                Sequencex.joinToString(Sequencex.dropWhile(normalSequence1) { !it.startsWith("b") }))

        assertTwoEquals("cj, dj",
                normalSequence0.dropWhile { !it.startsWith("c") }.joinToString(),
                Sequencex.joinToString(Sequencex.dropWhile(normalSequence1) { !it.startsWith("c") }))

        assertTwoEquals("dj",
                normalSequence0.dropWhile { !it.startsWith("d") }.joinToString(),
                Sequencex.joinToString(Sequencex.dropWhile(normalSequence1) { !it.startsWith("d") }))

        assertTwoEquals("",
                normalSequence0.dropWhile { !it.startsWith("e") }.joinToString(),
                Sequencex.joinToString(Sequencex.dropWhile(normalSequence1) { !it.startsWith("e") }))


        assertTwoEquals("dj",
                normalSequence0.dropWhile { !it.startsWith("b") }.drop(2).joinToString(),
                Sequencex.joinToString(Sequencex.drop(Sequencex.dropWhile(normalSequence1) { !it.startsWith("b") }, 2)))
    }

    @Test
    fun testTake() {
        val normalSequence0 = sequenceOf("aj", "bj", "cj", "dj")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "dj")

        /*
         * take 系列的方法表示从列表头部开始取部分元素
         */

        // take 方法的意思是从列表头部开始取多少个元素

        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.take(-1) },
                { Sequencex.take(normalSequence1, -1) }
        )

        assertTrue(emptySequence<String>() === normalSequence0.take(0))
        assertTrue(Sequencex.emptySequence<String>() === Sequencex.take(normalSequence1, 0))

        assertTwoEquals("aj",
                normalSequence0.take(1).joinToString(),
                Sequencex.joinToString(Sequencex.take(normalSequence1, 1)))

        assertTwoEquals("aj, bj",
                normalSequence0.take(2).joinToString(),
                Sequencex.joinToString(Sequencex.take(normalSequence1, 2)))

        assertTwoEquals("aj, bj, cj",
                normalSequence0.take(3).joinToString(),
                Sequencex.joinToString(Sequencex.take(normalSequence1, 3)))

        assertTwoEquals("aj, bj, cj, dj",
                normalSequence0.take(4).joinToString(),
                Sequencex.joinToString(Sequencex.take(normalSequence1, 4)))

        assertTwoEquals("aj, bj, cj, dj",
                normalSequence0.take(5).joinToString(),
                Sequencex.joinToString(Sequencex.take(normalSequence1, 5)))

        assertTwoEquals("aj, bj, cj, dj",
                normalSequence0.take(5).joinToString(),
                Sequencex.joinToString(Sequencex.take(Sequencex.take(normalSequence1, 5), 5)))

        // takeWhile 方法的意思是从列表头部开始到不符合条件的元素（不含包）终止

        assertTwoEquals("",
                normalSequence0.takeWhile { !it.startsWith("a") }.joinToString(),
                Sequencex.joinToString(Sequencex.takeWhile(normalSequence1) { !it.startsWith("a") }))

        assertTwoEquals("aj",
                normalSequence0.takeWhile { !it.startsWith("b") }.joinToString(),
                Sequencex.joinToString(Sequencex.takeWhile(normalSequence1) { !it.startsWith("b") }))

        assertTwoEquals("aj, bj",
                normalSequence0.takeWhile { !it.startsWith("c") }.joinToString(),
                Sequencex.joinToString(Sequencex.takeWhile(normalSequence1) { !it.startsWith("c") }))

        assertTwoEquals("aj, bj, cj",
                normalSequence0.takeWhile { !it.startsWith("d") }.joinToString(),
                Sequencex.joinToString(Sequencex.takeWhile(normalSequence1) { !it.startsWith("d") }))

        assertTwoEquals("aj, bj, cj, dj",
                normalSequence0.takeWhile { !it.startsWith("e") }.joinToString(),
                Sequencex.joinToString(Sequencex.takeWhile(normalSequence1) { !it.startsWith("e") }))


        assertTwoEquals("aj, bj",
                normalSequence0.takeWhile { !it.startsWith("d") }.take(2).joinToString(),
                Sequencex.joinToString(Sequencex.take(Sequencex.takeWhile(normalSequence1) { !it.startsWith("d") }, 2)))
    }

    @Test
    @Suppress("RedundantAsSequence")
    fun testFilter() {
        val normalSequence0 = sequenceOf("aj", "bo", "cj", "do")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bo", "cj", "do")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals("aj, cj",
                normalSequence0.filter { it.endsWith("j") }.joinToString(),
                Sequencex.joinToString(Sequencex.filter(normalSequence1) { it.endsWith("j") }))

        val filterToDestination = ArrayList<String>()
        val filterToDestination1 = ArrayList<String>()
        val filterToDestinationNull1 = ArrayList<String>()
        val filterToDestinationResult = normalSequence0.filterTo(filterToDestination) { it.endsWith("j") }
        val filterToDestinationResult1 = Sequencex.filterTo(normalSequence1, filterToDestination1) { it.endsWith("j") }
        val filterToDestinationNullResult1 = Sequencex.filterTo(nullSequence1, filterToDestinationNull1) { it.endsWith("j") }
        assertTwoEquals("aj, cj", filterToDestinationResult.joinToString(), filterToDestinationResult1.joinToString())
        assertTrue(filterToDestination === filterToDestinationResult)
        assertTrue(filterToDestination1 === filterToDestinationResult1)
        assertEquals("", filterToDestinationNullResult1.joinToString())
        assertTrue(filterToDestinationNull1 === filterToDestinationNullResult1)

        assertTwoEquals("bo, do",
                normalSequence0.filterIndexed { index, _ -> (index % 2) != 0 }.joinToString(),
                Sequencex.joinToString(Sequencex.filterIndexed(normalSequence1) { index, _ -> (index % 2) != 0 }))

        val filterIndexedToDestination = ArrayList<String>()
        val filterIndexedToDestination1 = ArrayList<String>()
        val filterIndexedToDestinationNull1 = ArrayList<String>()
        val filterIndexedToDestinationResult = normalSequence0.filterIndexedTo(filterIndexedToDestination) { index, _ -> (index % 2) != 0 }
        val filterIndexedToDestinationResult1 = Sequencex.filterIndexedTo(normalSequence1, filterIndexedToDestination1) { index, _ -> (index % 2) != 0 }
        val filterIndexedToDestinationNullResult1 = Sequencex.filterIndexedTo(nullSequence1, filterIndexedToDestinationNull1) { index, _ -> (index % 2) != 0 }
        assertTwoEquals("bo, do", filterIndexedToDestinationResult.joinToString(), filterIndexedToDestinationResult1.joinToString())
        assertTrue(filterIndexedToDestination === filterIndexedToDestinationResult)
        assertTrue(filterIndexedToDestination1 === filterIndexedToDestinationResult1)
        assertEquals("", filterIndexedToDestinationNullResult1.joinToString())
        assertTrue(filterIndexedToDestinationNull1 === filterIndexedToDestinationNullResult1)


        val anySequence0 = sequenceOf(4, "f", 76, "gsdg")
        val anySequence1 = Sequencex.sequenceOf(4, "f", 76, "gsdg")

        assertTwoEquals("4, 76",
                anySequence0.filterIsInstance(Integer::class.java).joinToString(),
                Sequencex.joinToString(Sequencex.filterIsInstance(anySequence1, Integer::class.java)))
        assertTwoEquals("f, gsdg",
                anySequence0.filterIsInstance(String::class.java).joinToString(),
                Sequencex.joinToString(Sequencex.filterIsInstance(anySequence1, String::class.java)))

        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN") val filterIsInstanceToDestination = ArrayList<Integer>()
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN") val filterIsInstanceToDestination1 = arrayListOf<Integer>()
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN") val filterIsInstanceToDestinationNull1 = arrayListOf<Integer>()
        val filterIsInstanceToDestinationResult = anySequence0.filterIsInstanceTo(filterIsInstanceToDestination, Integer::class.java)
        val filterIsInstanceToDestinationResult1 = Sequencex.filterIsInstanceTo(anySequence1, filterIsInstanceToDestination1, Integer::class.java)
        val filterIsInstanceToDestinationNullResult1 = Sequencex.filterIsInstanceTo(nullSequence1, filterIsInstanceToDestinationNull1, Integer::class.java)
        assertTwoEquals("4, 76", filterIsInstanceToDestinationResult.joinToString(), filterIsInstanceToDestinationResult1.joinToString())
        assertTrue(filterIsInstanceToDestination === filterIsInstanceToDestinationResult)
        assertTrue(filterIsInstanceToDestination1 === filterIsInstanceToDestinationResult1)
        assertEquals("", filterIsInstanceToDestinationNullResult1.joinToString())
        assertTrue(filterIsInstanceToDestinationNull1 === filterIsInstanceToDestinationNullResult1)

        assertTwoEquals("bo, do",
                normalSequence0.filterNot { it.endsWith("j") }.joinToString(),
                Sequencex.joinToString(Sequencex.filterNot(normalSequence1) { it.endsWith("j") }))

        val filterNotToDestination = ArrayList<String>()
        val filterNotToDestination1 = ArrayList<String>()
        val filterNotToDestinationNull1 = ArrayList<String>()
        val filterNotToDestinationResult = normalSequence0.filterNotTo(filterNotToDestination) { it.endsWith("j") }
        val filterNotToDestinationResult1 = Sequencex.filterNotTo(normalSequence1, filterNotToDestination1) { it.endsWith("j") }
        val filterNotToDestinationNullResult1 = Sequencex.filterNotTo(nullSequence1, filterNotToDestinationNull1) { it.endsWith("j") }
        assertTwoEquals("bo, do", filterNotToDestinationResult.joinToString(), filterNotToDestinationResult1.joinToString())
        assertTrue(filterNotToDestination === filterNotToDestinationResult)
        assertTrue(filterNotToDestination1 === filterNotToDestinationResult1)
        assertEquals("", filterNotToDestinationNullResult1.joinToString())
        assertTrue(filterNotToDestinationNull1 === filterNotToDestinationNullResult1)


        val notNullSequence0 = sequenceOf(null, "f", null, "gsdg")
        val notNullSequence1 = Sequencex.sequenceOf(null, "f", null, "gsdg")

        assertTwoEquals("f, gsdg",
                notNullSequence0.filterNotNull().joinToString(),
                Sequencex.joinToString(Sequencex.filterNotNull(notNullSequence1)))

        val filterNotNullToDestination = ArrayList<String>()
        val filterNotNullToDestination1 = ArrayList<String>()
        val filterNotNullToDestinationNull1 = ArrayList<String>()
        val filterNotNullToDestinationResult = notNullSequence0.filterNotNullTo(filterNotNullToDestination)
        val filterNotNullToDestinationResult1 = Sequencex.filterNotNullTo(notNullSequence1, filterNotNullToDestination1)
        val filterNotNullToDestinationNullResult1 = Sequencex.filterNotNullTo(nullSequence1, filterNotNullToDestinationNull1)
        assertTwoEquals("f, gsdg", filterNotNullToDestinationResult.joinToString(), filterNotNullToDestinationResult1.joinToString())
        assertTrue(filterNotNullToDestination === filterNotNullToDestinationResult)
        assertTrue(filterNotNullToDestination1 === filterNotNullToDestinationResult1)
        assertEquals("", filterNotNullToDestinationNullResult1.joinToString())
        assertTrue(filterNotNullToDestinationNull1 === filterNotNullToDestinationNullResult1)
    }

    @Test
    fun testSort() {
        val normalSequence0 = sequenceOf("aaa", "h", "uuuu", "gg")
        val normalSequence1 = Sequencex.sequenceOf("aaa", "h", "uuuu", "gg")
        val nullableSequence0 = sequenceOf("aaa", null, null, "gg", null)
        val nullableSequence1 = Sequencex.sequenceOf("aaa", null, null, "gg", null)

        assertTwoEquals("aaa, gg, h, uuuu",
                normalSequence0.sorted().joinToString(),
                Sequencex.joinToString(Sequencex.sorted(normalSequence1)))
        assertThrow(NullPointerException::class) { Sequencex.joinToString(Sequencex.sorted(nullableSequence1)) }

        assertTwoEquals("uuuu, h, gg, aaa",
                normalSequence0.sortedDescending().joinToString(),
                Sequencex.joinToString(Sequencex.sortedDescending(normalSequence1)))
        assertThrow(NullPointerException::class) { Sequencex.joinToString(Sequencex.sortedDescending(nullableSequence1)) }

        assertTwoEquals("h, gg, aaa, uuuu",
                normalSequence0.sortedBy { it.length }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedBy(normalSequence1) { it.length }))
        assertTwoEquals("null, null, null, gg, aaa",
                nullableSequence0.sortedBy { it?.length ?: 0 }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedBy(nullableSequence1) { it.length }))

        assertTwoEquals("uuuu, aaa, gg, h",
                normalSequence0.sortedByDescending { it.length }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedByDescending(normalSequence1) { it.length }))
        assertTwoEquals("aaa, gg, null, null, null",
                nullableSequence0.sortedByDescending { it?.length ?: 0 }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedByDescending(nullableSequence1) { it.length }))

        assertTwoEquals("aaa, gg, h, uuuu",
                normalSequence0.sortedWith { it1, it2 -> it1.compareTo(it2) }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedWith(normalSequence1) { it1, it2 -> it1.compareTo(it2) }))
        assertTwoEquals("null, null, null, aaa, gg",
                nullableSequence0.sortedWith { it1, it2 -> it1.orEmpty().compareTo(it2.orEmpty()) }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedWith(nullableSequence1) { it1, it2 -> it1.orEmpty().compareTo(it2.orEmpty()) }))
    }

    @Test
    @Suppress("ReplaceAssociateFunction")
    fun testAssociate() {
        val normalSequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                normalSequence0.associate { it.first().toString() to it },
                Sequencex.associate(normalSequence1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) },
        )

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                normalSequence0.associateBy { it.first().toString() },
                Sequencex.associateBy(normalSequence1) { it.first().toString() },
        )

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                normalSequence0.associateBy({ it.first().toString() }, { it }),
                Sequencex.associateBy(normalSequence1, { it.first().toString() }, { it }),
        )

        val associateToMap0 = HashMap<String, String>()
        val associateToMap1 = HashMap<String, String>()
        val associateToMapNull1 = HashMap<String, String>()
        val associateToMapResult0 = normalSequence0.associateTo(associateToMap0) { it.first().toString() to it }
        val associateToMapResult1 = Sequencex.associateTo(normalSequence1, associateToMap1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) }
        val associateToMapResultNull1 = Sequencex.associateTo(nullSequence1, associateToMapNull1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) }
        assertTwoEquals(mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"), associateToMap0, associateToMap1)
        assertTrue(associateToMap0 === associateToMapResult0)
        assertTrue(associateToMap1 === associateToMapResult1)
        assertEquals(mapOf<String, String>(), associateToMapResultNull1)
        assertTrue(associateToMapNull1 === associateToMapResultNull1)

        val associateByTo1Map0 = HashMap<String, String>()
        val associateByTo1Map1 = HashMap<String, String>()
        val associateByTo1MapNull1 = HashMap<String, String>()
        val associateByTo1MapResult0 = normalSequence0.associateByTo(associateByTo1Map0) { it.first().toString() }
        val associateByTo1MapResult1 = Sequencex.associateByTo(normalSequence1, associateByTo1Map1) { it.first().toString() }
        val associateByTo1MapResultNull1 = Sequencex.associateByTo(nullSequence1, associateByTo1MapNull1) { it.first().toString() }
        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                associateByTo1Map0, associateByTo1Map1,
        )
        assertTrue(associateByTo1Map0 === associateByTo1MapResult0)
        assertTrue(associateByTo1Map1 === associateByTo1MapResult1)
        assertEquals(mapOf<String, String>(), associateByTo1MapResultNull1)
        assertTrue(associateByTo1MapNull1 === associateByTo1MapResultNull1)

        val associateByTo2Map0 = HashMap<String, String>()
        val associateByTo2Map1 = HashMap<String, String>()
        val associateByTo2MapNull1 = HashMap<String, String>()
        val associateByTo2MapResult0 = normalSequence0.associateByTo(associateByTo2Map0, { it.first().toString() }, { it })
        val associateByTo2MapResult1 = Sequencex.associateByTo(normalSequence1, associateByTo2Map1, { it.first().toString() }, { it })
        val associateByTo2MapResultNull1 = Sequencex.associateByTo(nullSequence1, associateByTo2MapNull1, { it.first().toString() }, { it })
        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                associateByTo2Map0, associateByTo2Map1,
        )
        assertTrue(associateByTo2Map0 === associateByTo2MapResult0)
        assertTrue(associateByTo2Map1 === associateByTo2MapResult1)
        assertEquals(mapOf<String, String>(), associateByTo2MapResultNull1)
        assertTrue(associateByTo2MapNull1 === associateByTo2MapResultNull1)
    }

    @Test
    fun testTo() {
        val normalSequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")
        val nullSequence1 = null as Sequence<Pair2<Int, String>>?

        assertTwoEquals(
                listOf("aj", "bj", "ao", "bo"),
                normalSequence0.toCollection(LinkedList()),
                Sequencex.toCollection(normalSequence1, LinkedList()),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                normalSequence0.toCollection(LinkedList()) is LinkedList,
                Sequencex.toCollection(normalSequence1, LinkedList()) is LinkedList
        )
        assertEquals(listOf<String>(), Sequencex.toCollection(nullSequence1, LinkedList()))

        assertTwoEquals(
                listOf("aj", "bj", "ao", "bo"),
                normalSequence0.toMutableList(),
                Sequencex.toMutableList(normalSequence1),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                normalSequence0.toMutableList() is ArrayList,
                Sequencex.toMutableList(normalSequence1) is ArrayList
        )

        assertTwoEquals(
                linkedSetOf("aj", "bj", "ao", "bo"),
                normalSequence0.toMutableSet(),
                Sequencex.toMutableSet(normalSequence1),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                normalSequence0.toMutableSet() is LinkedHashSet,
                Sequencex.toMutableSet(normalSequence1) is LinkedHashSet
        )
        assertEquals(linkedSetOf<String>(), Sequencex.toMutableSet(nullSequence1))

        assertTwoEquals(
                hashSetOf("aj", "bj", "ao", "bo"),
                normalSequence0.toHashSet(),
                Sequencex.toHashSet(normalSequence1),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                normalSequence0.toHashSet() is HashSet,
                Sequencex.toHashSet(normalSequence1) is HashSet
        )

        assertTwoEquals(
                sortedSetOf("aj", "ao", "bj", "bo"),
                normalSequence0.toSortedSet(),
                Sequencex.toSortedSet(normalSequence1),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                normalSequence0.toSortedSet() is TreeSet,
                Sequencex.toSortedSet(normalSequence1) is TreeSet
        )

        assertTwoEquals(
                sortedSetOf("bo", "bj", "ao", "aj"),
                normalSequence0.toSortedSet { o1, o2 -> o1.compareTo(o2) * -1 },
                Sequencex.toSortedSet(normalSequence1) { o1, o2 -> o1.compareTo(o2) * -1 },
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                normalSequence0.toSortedSet { o1, o2 -> o1.compareTo(o2) * -1 } is TreeSet,
                Sequencex.toSortedSet(normalSequence1) { o1, o2 -> o1.compareTo(o2) * -1 } is TreeSet
        )

        assertTwoEquals(
                mapOf(1 to "a", 3 to "c", 8 to "h"),
                sequenceOf(1 to "a", 3 to "c", 8 to "h").toMap(),
                Sequencex.toMap(Sequencex.sequenceOf(Pair2.of(1, "a"), Pair2.of(3, "c"), Pair2.of(8, "h"))),
        )
        assertTwoEquals(
                true,
                sequenceOf(1 to "a", 3 to "c", 8 to "h").toMap() is LinkedHashMap,
                Sequencex.toMap(Sequencex.sequenceOf(Pair2.of(1, "a"), Pair2.of(3, "c"), Pair2.of(8, "h"))) is LinkedHashMap,
        )

        assertTwoEquals(
                mapOf(1 to "a", 3 to "c", 8 to "h"),
                sequenceOf(1 to "a", 3 to "c", 8 to "h").toMap(HashMap()),
                Sequencex.toMap(Sequencex.sequenceOf(Pair2.of(1, "a"), Pair2.of(3, "c"), Pair2.of(8, "h")), HashMap()),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                true,
                sequenceOf(1 to "a", 3 to "c", 8 to "h").toMap(HashMap()) is HashMap,
                Sequencex.toMap(Sequencex.sequenceOf(Pair2.of(1, "a"), Pair2.of(3, "c"), Pair2.of(8, "h")), HashMap()) is HashMap,
        )
        assertEquals(mapOf<Int, String>(), Sequencex.toMap(nullSequence1, HashMap()))
    }

    @Test
    fun testFlatMap() {
        val normalSequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")
        val nullSequence1 = null as Sequence<String>?

        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "a, j, b, j, a, o, b, o",
                normalSequence0.flatMap { it -> it.toCharArray().map { it.toString() } }.joinToString(),
                Sequencex.joinToString(Sequencex.flatMapOfIterable(normalSequence1) { it -> it.toCharArray().map { it.toString() } }),
        )

        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "a, j, b, j, a, o, b, o",
                normalSequence0.flatMap { it -> it.toCharArray().asSequence().map { it.toString() } }.joinToString(),
                Sequencex.joinToString(Sequencex.flatMap(normalSequence1) { it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { it.toString() } }),
        )

        val flatMapToList0 = ArrayList<String>()
        val flatMapToList1 = ArrayList<String>()
        val flatMapToListNull1 = ArrayList<String>()
        val flatMapToListResult0 = normalSequence0.flatMapTo(flatMapToList0) { it -> it.toCharArray().map { it.toString() } }
        val flatMapToListResult1 = Sequencex.flatMapOfIterableTo(normalSequence1, flatMapToList1) { it -> it.toCharArray().map { it.toString() } }
        val flatMapToListResultNull1 = Sequencex.flatMapOfIterableTo(nullSequence1, flatMapToListNull1) { it -> it.toCharArray().map { it.toString() } }
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "a, j, b, j, a, o, b, o",
                flatMapToListResult0.joinToString(),
                flatMapToListResult1.joinToString(),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                true,
                flatMapToList0 === flatMapToListResult0,
                flatMapToList1 === flatMapToListResult1,
        )
        @Suppress("USELESS_IS_CHECK")
        assertEquals("", flatMapToListResultNull1.joinToString())
        @Suppress("USELESS_IS_CHECK")
        assertTrue(flatMapToListNull1 === flatMapToListResultNull1)

        val flatMapToList2 = ArrayList<String>()
        val flatMapToList3 = ArrayList<String>()
        val flatMapToListNull3 = ArrayList<String>()
        val flatMapToListResult2 = normalSequence0.flatMapTo(flatMapToList2) { it -> it.toCharArray().asSequence().map { it.toString() } }
        val flatMapToListResult3 = Sequencex.flatMapTo(normalSequence1, flatMapToList3) { it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { it.toString() } }
        val flatMapToListResultNull3 = Sequencex.flatMapTo(nullSequence1, flatMapToListNull3) { it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { it.toString() } }
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "a, j, b, j, a, o, b, o",
                flatMapToListResult2.joinToString(),
                flatMapToListResult3.joinToString(),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                true,
                flatMapToList2 === flatMapToListResult2,
                flatMapToList3 === flatMapToListResult3,
        )
        @Suppress("USELESS_IS_CHECK")
        assertEquals("", flatMapToListResultNull1.joinToString())
        @Suppress("USELESS_IS_CHECK")
        assertTrue(flatMapToListNull3 === flatMapToListResultNull3)



        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "0-a, 0-j, 1-b, 1-j, 2-a, 2-o, 3-b, 3-o",
                normalSequence0.flatMapIndexed { index, it -> it.toCharArray().map { "${index}-$it" } }.joinToString(),
                Sequencex.joinToString(Sequencex.flatMapIndexedOfIterable(normalSequence1) { index, it -> it.toCharArray().map { "${index}-$it" } }),
        )

        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "0-a, 0-j, 1-b, 1-j, 2-a, 2-o, 3-b, 3-o",
                normalSequence0.flatMapIndexed { index, it -> it.toCharArray().asSequence().map { "${index}-$it" } }.joinToString(),
                Sequencex.joinToString(Sequencex.flatMapIndexed(normalSequence1) { index, it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { "${index}-$it" } }),
        )

        val flatMapIndexedToList0 = ArrayList<String>()
        val flatMapIndexedToList1 = ArrayList<String>()
        val flatMapIndexedToListNull1 = ArrayList<String>()
        val flatMapIndexedToListResult0 = normalSequence0.flatMapIndexedTo(flatMapIndexedToList0) { index, it -> it.toCharArray().map { "${index}-$it" } }
        val flatMapIndexedToListResult1 = Sequencex.flatMapIndexedOfIterableTo(normalSequence1, flatMapIndexedToList1) { index, it -> it.toCharArray().map { "${index}-$it" } }
        val flatMapIndexedToListResultNull1 = Sequencex.flatMapIndexedOfIterableTo(nullSequence1, flatMapIndexedToListNull1) { index, it -> it.toCharArray().map { "${index}-$it" } }
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "0-a, 0-j, 1-b, 1-j, 2-a, 2-o, 3-b, 3-o",
                flatMapIndexedToListResult0.joinToString(),
                flatMapIndexedToListResult1.joinToString(),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                true,
                flatMapIndexedToList0 === flatMapIndexedToListResult0,
                flatMapIndexedToList1 === flatMapIndexedToListResult1,
        )
        @Suppress("USELESS_IS_CHECK")
        assertEquals("", flatMapIndexedToListResultNull1.joinToString())
        @Suppress("USELESS_IS_CHECK")
        assertTrue(flatMapIndexedToListNull1 === flatMapIndexedToListResultNull1)

        val flatMapIndexedToList2 = ArrayList<String>()
        val flatMapIndexedToList3 = ArrayList<String>()
        val flatMapIndexedToListNull3 = ArrayList<String>()
        val flatMapIndexedToListResult2 = normalSequence0.flatMapIndexedTo(flatMapIndexedToList2) { index, it -> it.toCharArray().asSequence().map { "${index}-$it" } }
        val flatMapIndexedToListResult3 = Sequencex.flatMapIndexedTo(normalSequence1, flatMapIndexedToList3) { index, it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { "${index}-$it" } }
        val flatMapIndexedToListResultNull3 = Sequencex.flatMapIndexedTo(nullSequence1, flatMapIndexedToListNull3) { index, it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { "${index}-$it" } }
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "0-a, 0-j, 1-b, 1-j, 2-a, 2-o, 3-b, 3-o",
                flatMapIndexedToListResult2.joinToString(),
                flatMapIndexedToListResult3.joinToString(),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                true,
                flatMapIndexedToList2 === flatMapIndexedToListResult2,
                flatMapIndexedToList3 === flatMapIndexedToListResult3,
        )
        @Suppress("USELESS_IS_CHECK")
        assertEquals("", flatMapIndexedToListResultNull3.joinToString())
        @Suppress("USELESS_IS_CHECK")
        assertTrue(flatMapIndexedToListNull3 === flatMapIndexedToListResultNull3)
    }

    @Test
    fun testGroup() {
        val normalSequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(
                "{a=[aj, ao], b=[bj, bo]}",
                normalSequence0.groupBy { it.first() }.toString(),
                Sequencex.groupBy(normalSequence1) { it.first() }.toString(),
        )

        assertTwoEquals(
                "{a=[j, o], b=[j, o]}",
                normalSequence0.groupBy({ it.first() }, { it.last() }).toString(),
                Sequencex.groupBy(normalSequence1, { it.first() }, { it.last() }).toString(),
        )

        val groupByToMap0 = HashMap<Char, MutableList<String>>()
        val groupByToMap1 = HashMap<Char, List<String>>()
        val groupByToMapNull1 = HashMap<Char, List<String>>()
        val groupByToMapResult0 = normalSequence0.groupByTo(groupByToMap0) { it.first() }
        val groupByToMapResult1 = Sequencex.groupByTo(normalSequence1, groupByToMap1) { it.first() }
        val groupByToMapResultNull1 = Sequencex.groupByTo(nullSequence1, groupByToMapNull1) { it.first() }
        assertTwoEquals("{a=[aj, ao], b=[bj, bo]}", groupByToMap0.toString(), groupByToMap1.toString())
        assertTrue(groupByToMap0 === groupByToMapResult0)
        assertTrue(groupByToMap1 === groupByToMapResult1)
        assertEquals("{}", groupByToMapNull1.toString())
        assertTrue(groupByToMapNull1 === groupByToMapResultNull1)

        val groupByToMap2 = HashMap<Char, MutableList<Char>>()
        val groupByToMap3 = HashMap<Char, List<Char>>()
        val groupByToMapNull3 = HashMap<Char, List<Char>>()
        val groupByToMapResult2 = normalSequence0.groupByTo(groupByToMap2, { it.first() }, { it.last() })
        val groupByToMapResult3 = Sequencex.groupByTo(normalSequence1, groupByToMap3, { it.first() }, { it.last() })
        val groupByToMapResultNull3 = Sequencex.groupByTo(nullSequence1, groupByToMapNull3, { it.first() }, { it.last() })
        assertTwoEquals("{a=[j, o], b=[j, o]}", groupByToMap2.toString(), groupByToMap3.toString())
        assertTrue(groupByToMap2 === groupByToMapResult2)
        assertTrue(groupByToMap3 === groupByToMapResult3)
        assertEquals("{}", groupByToMapNull3.toString())
        assertTrue(groupByToMapNull3 === groupByToMapResultNull3)
    }

    @Test
    fun testMap() {
        val normalSequence0 = sequenceOf("aj", "bj", "ao", "cc", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "cc", "bo")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(
                "a, b, a, c, b",
                normalSequence0.map { it.first() }.joinToString(),
                Sequencex.joinToString(Sequencex.map(normalSequence1) { it.first() }),
        )

        assertTwoEquals(
                "a, b, a, b",
                normalSequence0.mapNotNull { if (it != "cc") it.first() else null }.joinToString(),
                Sequencex.joinToString(Sequencex.mapNotNull(normalSequence1) { if (it != "cc") it.first() else null }),
        )

        assertTwoEquals(
                "0:a, 1:b, 2:a, 3:c, 4:b",
                normalSequence0.mapIndexed { index, s -> "$index:${s.first()}" }.joinToString(),
                Sequencex.joinToString(Sequencex.mapIndexed(normalSequence1) { index, s -> "$index:${s.first()}" }),
        )

        assertTwoEquals(
                "0:a, 1:b, 2:a, 4:b",
                normalSequence0.mapIndexedNotNull { index, s -> if (s != "cc") "$index:${s.first()}" else null }.joinToString(),
                Sequencex.joinToString(Sequencex.mapIndexedNotNull(normalSequence1) { index, s -> if (s != "cc") "$index:${s.first()}" else null }),
        )

        val mapToList0 = ArrayList<Char>()
        val mapToList1 = ArrayList<Char>()
        val mapToListNull1 = ArrayList<Char>()
        val mapToListResult0 = normalSequence0.mapTo(mapToList0) { it.first() }
        val mapToListResult1 = Sequencex.mapTo(normalSequence1, mapToList1) { it.first() }
        val mapToListResultNull1 = Sequencex.mapTo(nullSequence1, mapToListNull1) { it.first() }
        assertTwoEquals(
                "[a, b, a, c, b]",
                mapToList0.toString(),
                mapToList1.toString(),
        )
        assertTrue(mapToList0 === mapToListResult0)
        assertTrue(mapToList1 === mapToListResult1)
        assertEquals("[]", mapToListNull1.toString())
        assertTrue(mapToListNull1 === mapToListResultNull1)

        val mapNotNullToList0 = ArrayList<Char>()
        val mapNotNullToList1 = ArrayList<Char>()
        val mapNotNullToListNull1 = ArrayList<Char>()
        val mapNotNullToListResult0 = normalSequence0.mapNotNullTo(mapNotNullToList0) { if (it != "cc") it.first() else null }
        val mapNotNullToListResult1 = Sequencex.mapNotNullTo(normalSequence1, mapNotNullToList1) { if (it != "cc") it.first() else null }
        val mapNotNullToListResultNull1 = Sequencex.mapNotNullTo(nullSequence1, mapNotNullToListNull1) { if (it != "cc") it.first() else null }
        assertTwoEquals(
                "[a, b, a, b]",
                mapNotNullToList0.toString(),
                mapNotNullToList1.toString(),
        )
        assertTrue(mapNotNullToList0 === mapNotNullToListResult0)
        assertTrue(mapNotNullToList1 === mapNotNullToListResult1)
        assertEquals(
                "[]",
                mapNotNullToListNull1.toString(),
        )
        assertTrue(mapNotNullToListNull1 === mapNotNullToListResultNull1)

        val mapIndexedToList0 = ArrayList<String>()
        val mapIndexedToList1 = ArrayList<String>()
        val mapIndexedToListNull1 = ArrayList<String>()
        val mapIndexedToListResult0 = normalSequence0.mapIndexedTo(mapIndexedToList0) { index, s -> "$index:${s.first()}" }
        val mapIndexedToListResult1 = Sequencex.mapIndexedTo(normalSequence1, mapIndexedToList1) { index, s -> "$index:${s.first()}" }
        val mapIndexedToListResultNull1 = Sequencex.mapIndexedTo(nullSequence1, mapIndexedToListNull1) { index, s -> "$index:${s.first()}" }
        assertTwoEquals(
                "[0:a, 1:b, 2:a, 3:c, 4:b]",
                mapIndexedToList0.toString(),
                mapIndexedToList1.toString(),
        )
        assertTrue(mapIndexedToList0 === mapIndexedToListResult0)
        assertTrue(mapIndexedToList1 === mapIndexedToListResult1)
        assertEquals("[]", mapIndexedToListNull1.toString())
        assertTrue(mapIndexedToListNull1 === mapIndexedToListResultNull1)

        val mapIndexedNotNullToList0 = ArrayList<String>()
        val mapIndexedNotNullToList1 = ArrayList<String>()
        val mapIndexedNotNullToListNull1 = ArrayList<String>()
        val mapIndexedNotNullToListResult0 = normalSequence0.mapIndexedNotNullTo(mapIndexedNotNullToList0) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
        val mapIndexedNotNullToListResult1 = Sequencex.mapIndexedNotNullTo(normalSequence1, mapIndexedNotNullToList1) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
        val mapIndexedNotNullToListResultNull1 = Sequencex.mapIndexedNotNullTo(nullSequence1, mapIndexedNotNullToListNull1) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
        assertTwoEquals(
                "[0:a, 1:b, 2:a, 4:b]",
                mapIndexedNotNullToList0.toString(),
                mapIndexedNotNullToList1.toString(),
        )
        assertTrue(mapIndexedNotNullToList0 === mapIndexedNotNullToListResult0)
        assertTrue(mapIndexedNotNullToList1 === mapIndexedNotNullToListResult1)
        assertEquals("[]", mapIndexedNotNullToListNull1.toString())
        assertTrue(mapIndexedNotNullToListNull1 === mapIndexedNotNullToListResultNull1)
    }

    @Test
    fun testWithIndex() {
        val normalSequence0 = sequenceOf("aj", "bj", "ao", "cc", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "cc", "bo")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(
                "0:aj, 1:bj, 2:ao, 3:cc, 4:bo",
                normalSequence0.withIndex().joinToString { "${it.index}:${it.value}" },
                Sequencex.joinToString(Sequencex.withIndex(normalSequence1)) { "${it.index}:${it.value}" },
        )

        assertEquals("", Sequencex.joinToString(Sequencex.withIndex(nullSequence1)) { "${it.index}:${it.value}" })

        val iterator0 = normalSequence0.withIndex().iterator()
        if (iterator0 is MutableIterator) {
            assertThrow(UnsupportedOperationException::class) { iterator0.remove() }
        }
        assertThrow(UnsupportedOperationException::class) { Sequencex.withIndex(normalSequence1).iterator().remove() }
    }

    @Test
    fun testDistinct() {
        val normalSequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")

        assertTwoEquals(
                "aj, bj, bo",
                normalSequence0.distinct().joinToString(),
                Sequencex.joinToString(Sequencex.distinct(normalSequence1)),
        )

        assertTwoEquals(
                "aj, bo",
                normalSequence0.distinctBy { it.last() }.joinToString(),
                Sequencex.joinToString(Sequencex.distinctBy(normalSequence1) { it.last() }),
        )
    }

    @Test
    fun testAll() {
        val normalSequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(
                true,
                normalSequence0.all { it -> it.all { it.isLetter() } },
                Sequencex.all(normalSequence1) { it -> it.all { it.isLetter() } },
        )

        assertTwoEquals(
                true,
                emptySequence0.all { it -> it.all { it.isLetter() } },
                Sequencex.all(emptySequence1) { it -> it.all { it.isLetter() } },
        )

        assertTrue(Sequencex.all(nullSequence1) { it.last() == 'j' })

        assertTwoEquals(
                false,
                normalSequence0.all { it.last() == 'j' },
                Sequencex.all(normalSequence1) { it.last() == 'j' },
        )
    }

    @Test
    fun testAny() {
        val normalSequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(true, normalSequence0.any(), Sequencex.any(normalSequence1))
        assertTwoEquals(false, emptySequence0.any(), Sequencex.any(emptySequence1))
        assertFalse(Sequencex.any(nullSequence1))
        assertTwoEquals(
                false,
                sequenceOf<String>().any(),
                Sequencex.any(Sequencex.sequenceOf<String>()),
        )

        assertTwoEquals(true, normalSequence0.any { it.last() == 'j' }, Sequencex.any(normalSequence1) { it.last() == 'j' })
        assertTwoEquals(false, emptySequence0.any { it.last() == 'j' }, Sequencex.any(emptySequence1) { it.last() == 'j' })
        assertFalse(Sequencex.any(nullSequence1) { it -> it.all { it.isDigit() } })
        assertTwoEquals(
                false,
                normalSequence0.any { it -> it.all { it.isDigit() } },
                Sequencex.any(normalSequence1) { it -> it.all { it.isDigit() } }
        )
    }

    @Test
    fun testCount() {
        val normalSequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")

        assertTwoEquals(
                5,
                normalSequence0.count(),
                Sequencex.count(normalSequence1),
        )

        assertTwoEquals(
                0,
                sequenceOf<String>().count(),
                Sequencex.count(Sequencex.sequenceOf<String>()),
        )

        assertTwoEquals(
                0,
                (null as kotlin.sequences.Sequence<String>?)?.count() ?: 0,
                Sequencex.count(null as Sequence<String>?),
        )

        assertTwoEquals(
                4,
                normalSequence0.count { it.last() == 'j' },
                Sequencex.count(normalSequence1) { it.last() == 'j' },
        )

        assertTwoEquals(
                0,
                sequenceOf<String>().count { it.last() == 'j' },
                Sequencex.count(Sequencex.sequenceOf<String>()) { it.last() == 'j' },
        )

        assertTwoEquals(
                0,
                (null as kotlin.sequences.Sequence<String>?)?.count { it.last() == 'j' } ?: 0,
                Sequencex.count(null as Sequence<String>?) { it.last() == 'j' },
        )
    }

    @Test
    fun testFold() {
        val normalSequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(
                "^ajbjajbjbo",
                normalSequence0.fold("^") { r, t -> r + t },
                Sequencex.fold(normalSequence1, "^") { r, t -> r + t },
        )
        assertEquals(
                "^",
                Sequencex.fold(nullSequence1, "^") { r, t -> r + t },
        )

        assertTwoEquals(
                "^0aj1bj2aj3bj4bo",
                normalSequence0.foldIndexed("^") { i, r, t -> r + i.toString() + t },
                Sequencex.foldIndexed(normalSequence1, "^") { i, r, t -> r + i.toString() + t },
        )
        assertEquals(
                "^",
                Sequencex.foldIndexed(nullSequence1, "^") { i, r, t -> r + i.toString() + t },
        )
    }

    @Test
    fun testEach() {
        val normalSequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val normalSequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")

        assertTwoEquals("aj, bj, aj, bj, bo",
                ArrayList<String>().apply { normalSequence0.forEach { add(it) } }.joinToString(),
                ArrayList<String>().apply { Sequencex.forEach(normalSequence1) { add(it) } }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { sequenceOf<String>().forEach { add(it) } }.joinToString(),
                ArrayList<String>().apply { Sequencex.forEach(null as Sequence<String>?) { add(it) } }.joinToString())

        assertTwoEquals("0aj, 1bj, 2aj, 3bj, 4bo",
                ArrayList<String>().apply { normalSequence0.forEachIndexed { i, it -> add(i.toString() + it) } }.joinToString(),
                ArrayList<String>().apply { Sequencex.forEachIndexed(normalSequence1) { i, it -> add(i.toString() + it) } }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { sequenceOf<String>().forEachIndexed { i, it -> add(i.toString() + it) } }.joinToString(),
                ArrayList<String>().apply { Sequencex.forEachIndexed(null as Sequence<String>?) { i, it -> add(i.toString() + it) } }.joinToString())


        assertTwoEquals("aj, bj, aj, bj, bo",
                ArrayList<String>().apply { normalSequence0.onEach { add(it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Sequencex.toMutableList(Sequencex.onEach(normalSequence1) { add(it) }) }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { sequenceOf<String>().onEach { add(it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Sequencex.toMutableList(Sequencex.onEach(null as Sequence<String>?) { add(it) }) }.joinToString())

        assertTwoEquals("0aj, 1bj, 2aj, 3bj, 4bo",
                ArrayList<String>().apply { normalSequence0.onEachIndexed { i, it -> add(i.toString() + it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Sequencex.toMutableList(Sequencex.onEachIndexed(normalSequence1) { i, it -> add(i.toString() + it) }) }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { sequenceOf<String>().onEachIndexed { i, it -> add(i.toString() + it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Sequencex.toMutableList(Sequencex.onEachIndexed(null as Sequence<String>?) { i, it -> add(i.toString() + it) }) }.joinToString())
    }

    @Test
    @Suppress("RedundantSamConstructor", "RemoveExplicitTypeArguments")
    fun testMax() {
        val doubleSequence0 = sequenceOf(3.2, 3.3, 3.0, 5.6, 1.1)
        val doubleSequence1 = Sequencex.sequenceOf(3.2, 3.3, 3.0, 5.6, 1.1)
        val nanDoubleSequence0 = sequenceOf(Double.NaN, 3.0, 3.2, 5.6, 1.1)
        val nanDoubleSequence1 = Sequencex.sequenceOf(Double.NaN, 3.0, 3.2, 5.6, 1.1)
        val nanDoubleSequence00 = sequenceOf(3.0, 3.2, Double.NaN, 5.6, 1.1)
        val nanDoubleSequence11 = Sequencex.sequenceOf(3.0, 3.2, Double.NaN, 5.6, 1.1)
        val emptyDoubleSequence0 = sequenceOf<Double>()
        val emptyDoubleSequence1 = Sequencex.sequenceOf<Double>()
        val nullDoubleSequence1: Sequence<Double>? = null
        assertTwoEquals(5.6, doubleSequence0.maxOrNull(), Sequencex.maxDoubleOrNull(doubleSequence1))
        assertTwoEquals(Double.NaN, nanDoubleSequence0.maxOrNull(), Sequencex.maxDoubleOrNull(nanDoubleSequence1))
        assertTwoEquals(Double.NaN, nanDoubleSequence00.maxOrNull(), Sequencex.maxDoubleOrNull(nanDoubleSequence11))
        assertTwoEquals(null, emptyDoubleSequence0.maxOrNull(), Sequencex.maxDoubleOrNull(emptyDoubleSequence1))
        assertTwoEquals(null, null, Sequencex.maxDoubleOrNull(nullDoubleSequence1))

        val floatSequence0 = sequenceOf(3.2f, 3.3f, 3.0f, 5.6f, 1.1f)
        val floatSequence1 = Sequencex.sequenceOf(3.2f, 3.3f, 3.0f, 5.6f, 1.1f)
        val nanFloatSequence0 = sequenceOf(Float.NaN, 3.0f, 3.2f, 5.6f, 1.1f)
        val nanFloatSequence1 = Sequencex.sequenceOf(Float.NaN, 3.0f, 3.2f, 5.6f, 1.1f)
        val nanFloatSequence00 = sequenceOf(3.0f, 3.2f, Float.NaN, 5.6f, 1.1f)
        val nanFloatSequence11 = Sequencex.sequenceOf(3.0f, 3.2f, Float.NaN, 5.6f, 1.1f)
        val emptyFloatSequence0 = sequenceOf<Float>()
        val emptyFloatSequence1 = Sequencex.sequenceOf<Float>()
        val nullFloatSequence1: Sequence<Float>? = null
        assertTwoEquals(5.6f, floatSequence0.maxOrNull(), Sequencex.maxFloatOrNull(floatSequence1))
        assertTwoEquals(Float.NaN, nanFloatSequence0.maxOrNull(), Sequencex.maxFloatOrNull(nanFloatSequence1))
        assertTwoEquals(Float.NaN, nanFloatSequence00.maxOrNull(), Sequencex.maxFloatOrNull(nanFloatSequence11))
        assertTwoEquals(null, emptyFloatSequence0.maxOrNull(), Sequencex.maxFloatOrNull(emptyFloatSequence1))
        assertTwoEquals(null, null, Sequencex.maxFloatOrNull(nullFloatSequence1))

        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null
        assertTwoEquals("7", normalSequence0.maxOrNull(), Sequencex.maxOrNull(normalSequence1))
        assertTwoEquals(null, emptySequence0.maxOrNull(), Sequencex.maxOrNull(emptySequence1))
        assertTwoEquals(null, null, Sequencex.maxOrNull(nullSequence1))

        assertTwoEquals("7", normalSequence0.maxByOrNull { it.toInt() }, Sequencex.maxByOrNull(normalSequence1) { it.toInt() })
        assertTwoEquals(null, emptySequence0.maxByOrNull { it.toInt() }, Sequencex.maxByOrNull(emptySequence1) { it.toInt() })
        assertTwoEquals(null, null, Sequencex.maxByOrNull(nullSequence1) { it.toInt() })

        assertTwoEquals("1",
                normalSequence0.maxWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Sequencex.maxWithOrNull(normalSequence1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                emptySequence0.maxWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Sequencex.maxWithOrNull(emptySequence1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                null,
                Sequencex.maxWithOrNull(nullSequence1) { it0, it1 -> it0.compareTo(it1) * -1 })

        assertTwoEquals(7.0,
                normalSequence0.maxOf { it.toDouble() },
                Sequencex.maxOfDouble(normalSequence1) { it.toDouble() })
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.maxOf { it.toDouble() } },
                { Sequencex.maxOfDouble(emptySequence1) { it.toDouble() } })
        assertThrow(NoSuchElementException::class) { Sequencex.maxOfDouble(nullSequence1) { it.toDouble() } }

        assertTwoEquals(7.0f,
                normalSequence0.maxOf { it.toFloat() },
                Sequencex.maxOfFloat(normalSequence1) { it.toFloat() })
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.maxOf { it.toFloat() } },
                { Sequencex.maxOfFloat(emptySequence1) { it.toFloat() } })
        assertThrow(NoSuchElementException::class) { Sequencex.maxOfFloat(nullSequence1) { it.toFloat() } }

        assertTwoEquals(7.0, normalSequence0.maxOfOrNull { it.toDouble() }, Sequencex.maxOfDoubleOrNull(normalSequence1) { it.toDouble() })
        assertTwoEquals(null, emptySequence0.maxOfOrNull { it.toDouble() }, Sequencex.maxOfDoubleOrNull(emptySequence1) { it.toDouble() })
        assertTwoEquals(null, null, Sequencex.maxOfDoubleOrNull(nullSequence1) { it.toDouble() })

        assertTwoEquals(7.0f, normalSequence0.maxOfOrNull { it.toFloat() }, Sequencex.maxOfFloatOrNull(normalSequence1) { it.toFloat() })
        assertTwoEquals(null, emptySequence0.maxOfOrNull { it.toFloat() }, Sequencex.maxOfFloatOrNull(emptySequence1) { it.toFloat() })
        assertTwoEquals(null, null, Sequencex.maxOfFloatOrNull(nullSequence1) { it.toFloat() })

        assertTwoEquals("7", normalSequence0.maxOf { it }, Sequencex.maxOf(normalSequence1) { it })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.maxOf { it } }, { Sequencex.maxOf(emptySequence1) { it } })
        assertThrow(NoSuchElementException::class) { Sequencex.maxOf(nullSequence1) { it } }

        assertTwoEquals("7", normalSequence0.maxOfOrNull { it }, Sequencex.maxOfOrNull(normalSequence1) { it })
        assertTwoEquals(null, emptySequence0.maxOfOrNull { it }, Sequencex.maxOfOrNull(emptySequence1) { it })
        assertTwoEquals(null, null, Sequencex.maxOfOrNull(nullSequence1) { it })

        assertTwoEquals("1",
                normalSequence0.maxOfWith({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.maxOfWith(normalSequence1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.maxOfWith(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) },
                { Sequencex.maxOfWith(emptySequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }
        )
        assertThrow(NoSuchElementException::class) { Sequencex.maxOfWith(nullSequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }

        assertTwoEquals("1",
                normalSequence0.maxOfWithOrNull({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.maxOfWithOrNull(normalSequence1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoEquals(null,
                emptySequence0.maxOfWithOrNull(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.maxOfWithOrNull(emptySequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it })
        )
        assertTwoEquals(null, null, Sequencex.maxOfWithOrNull(nullSequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
    }

    @Test
    @Suppress("RedundantSamConstructor", "RemoveExplicitTypeArguments")
    fun testMin() {
        val doubleSequence0 = sequenceOf(3.2, 3.3, 3.0, 5.6, 1.1)
        val doubleSequence1 = Sequencex.sequenceOf(3.2, 3.3, 3.0, 5.6, 1.1)
        val nanDoubleSequence0 = sequenceOf(Double.NaN, 3.0, 3.2, 5.6, 1.1)
        val nanDoubleSequence1 = Sequencex.sequenceOf(Double.NaN, 3.0, 3.2, 5.6, 1.1)
        val nanDoubleSequence00 = sequenceOf(3.0, 3.2, Double.NaN, 5.6, 1.1)
        val nanDoubleSequence11 = Sequencex.sequenceOf(3.0, 3.2, Double.NaN, 5.6, 1.1)
        val emptyDoubleSequence0 = sequenceOf<Double>()
        val emptyDoubleSequence1 = Sequencex.sequenceOf<Double>()
        val nullDoubleSequence1: Sequence<Double>? = null
        assertTwoEquals(1.1, doubleSequence0.minOrNull(), Sequencex.minDoubleOrNull(doubleSequence1))
        assertTwoEquals(Double.NaN, nanDoubleSequence0.minOrNull(), Sequencex.minDoubleOrNull(nanDoubleSequence1))
        assertTwoEquals(Double.NaN, nanDoubleSequence00.minOrNull(), Sequencex.minDoubleOrNull(nanDoubleSequence11))
        assertTwoEquals(null, emptyDoubleSequence0.minOrNull(), Sequencex.minDoubleOrNull(emptyDoubleSequence1))
        assertTwoEquals(null, null, Sequencex.minDoubleOrNull(nullDoubleSequence1))

        val floatSequence0 = sequenceOf(3.2f, 3.3f, 3.0f, 5.6f, 1.1f)
        val floatSequence1 = Sequencex.sequenceOf(3.2f, 3.3f, 3.0f, 5.6f, 1.1f)
        val nanFloatSequence0 = sequenceOf(Float.NaN, 3.0f, 3.2f, 5.6f, 1.1f)
        val nanFloatSequence1 = Sequencex.sequenceOf(Float.NaN, 3.0f, 3.2f, 5.6f, 1.1f)
        val nanFloatSequence00 = sequenceOf(3.0f, 3.2f, Float.NaN, 5.6f, 1.1f)
        val nanFloatSequence11 = Sequencex.sequenceOf(3.0f, 3.2f, Float.NaN, 5.6f, 1.1f)
        val emptyFloatSequence0 = sequenceOf<Float>()
        val emptyFloatSequence1 = Sequencex.sequenceOf<Float>()
        val nullFloatSequence1: Sequence<Float>? = null
        assertTwoEquals(1.1f, floatSequence0.minOrNull(), Sequencex.minFloatOrNull(floatSequence1))
        assertTwoEquals(Float.NaN, nanFloatSequence0.minOrNull(), Sequencex.minFloatOrNull(nanFloatSequence1))
        assertTwoEquals(Float.NaN, nanFloatSequence00.minOrNull(), Sequencex.minFloatOrNull(nanFloatSequence11))
        assertTwoEquals(null, emptyFloatSequence0.minOrNull(), Sequencex.minFloatOrNull(emptyFloatSequence1))
        assertTwoEquals(null, null, Sequencex.minFloatOrNull(nullFloatSequence1))

        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null
        assertTwoEquals("1", normalSequence0.minOrNull(), Sequencex.minOrNull(normalSequence1))
        assertTwoEquals(null, emptySequence0.minOrNull(), Sequencex.minOrNull(emptySequence1))
        assertTwoEquals(null, null, Sequencex.minOrNull(nullSequence1))

        assertTwoEquals("1", normalSequence0.minByOrNull { it.toInt() }, Sequencex.minByOrNull(normalSequence1) { it.toInt() })
        assertTwoEquals(null, emptySequence0.minByOrNull { it.toInt() }, Sequencex.minByOrNull(emptySequence1) { it.toInt() })
        assertTwoEquals(null, null, Sequencex.minByOrNull(nullSequence1) { it.toInt() })

        assertTwoEquals("7",
                normalSequence0.minWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Sequencex.minWithOrNull(normalSequence1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                emptySequence0.minWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Sequencex.minWithOrNull(emptySequence1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                null,
                Sequencex.minWithOrNull(nullSequence1) { it0, it1 -> it0.compareTo(it1) * -1 })

        assertTwoEquals(1.0,
                normalSequence0.minOf { it.toDouble() },
                Sequencex.minOfDouble(normalSequence1) { it.toDouble() })
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.minOf { it.toDouble() } },
                { Sequencex.minOfDouble(emptySequence1) { it.toDouble() } })
        assertThrow(NoSuchElementException::class) { Sequencex.minOfDouble(nullSequence1) { it.toDouble() } }

        assertTwoEquals(1.0f,
                normalSequence0.minOf { it.toFloat() },
                Sequencex.minOfFloat(normalSequence1) { it.toFloat() })
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.minOf { it.toFloat() } },
                { Sequencex.minOfFloat(emptySequence1) { it.toFloat() } })
        assertThrow(NoSuchElementException::class) { Sequencex.minOfFloat(nullSequence1) { it.toFloat() } }

        assertTwoEquals(1.0, normalSequence0.minOfOrNull { it.toDouble() }, Sequencex.minOfDoubleOrNull(normalSequence1) { it.toDouble() })
        assertTwoEquals(null, emptySequence0.minOfOrNull { it.toDouble() }, Sequencex.minOfDoubleOrNull(emptySequence1) { it.toDouble() })
        assertTwoEquals(null, null, Sequencex.minOfDoubleOrNull(nullSequence1) { it.toDouble() })

        assertTwoEquals(1.0f, normalSequence0.minOfOrNull { it.toFloat() }, Sequencex.minOfFloatOrNull(normalSequence1) { it.toFloat() })
        assertTwoEquals(null, emptySequence0.minOfOrNull { it.toFloat() }, Sequencex.minOfFloatOrNull(emptySequence1) { it.toFloat() })
        assertTwoEquals(null, null, Sequencex.minOfFloatOrNull(nullSequence1) { it.toFloat() })

        assertTwoEquals("1", normalSequence0.minOf { it }, Sequencex.minOf(normalSequence1) { it })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.minOf { it } }, { Sequencex.minOf(emptySequence1) { it } })
        assertThrow(NoSuchElementException::class) { Sequencex.minOf(nullSequence1) { it } }

        assertTwoEquals("1", normalSequence0.minOfOrNull { it }, Sequencex.minOfOrNull(normalSequence1) { it })
        assertTwoEquals(null, emptySequence0.minOfOrNull { it }, Sequencex.minOfOrNull(emptySequence1) { it })
        assertTwoEquals(null, null, Sequencex.minOfOrNull(nullSequence1) { it })

        assertTwoEquals("7",
                normalSequence0.minOfWith({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.minOfWith(normalSequence1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.minOfWith(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) },
                { Sequencex.minOfWith(emptySequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }
        )
        assertThrow(NoSuchElementException::class) { Sequencex.minOfWith(nullSequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }

        assertTwoEquals("7",
                normalSequence0.minOfWithOrNull({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.minOfWithOrNull(normalSequence1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoEquals(null,
                emptySequence0.minOfWithOrNull(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.minOfWithOrNull(emptySequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it })
        )
        assertTwoEquals(null, null, Sequencex.minOfWithOrNull(nullSequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
    }

    @Test
    fun testNone() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null

        assertTwoEquals(false, normalSequence0.none(), Sequencex.none(normalSequence1))
        assertTwoEquals(true, emptySequence0.none(), Sequencex.none(emptySequence1))
        assertTrue(Sequencex.none(nullSequence1))

        assertTwoEquals(true, normalSequence0.none { it.length > 1 }, Sequencex.none(normalSequence1) { it.length > 1 })
        assertTwoEquals(false, normalSequence0.none { it.isNotEmpty() }, Sequencex.none(normalSequence1) { it.isNotEmpty() })
        assertTwoEquals(true, emptySequence0.none { it.length > 1 }, Sequencex.none(emptySequence1) { it.length > 1 })
        assertTrue(Sequencex.none(nullSequence1) { it.length > 1 })
    }

    @Test
    fun testReduce() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null

        assertTwoEquals("63721",
                normalSequence0.reduce { it0, it1 -> it0 + it1 },
                Sequencex.reduce(normalSequence1) { it0, it1 -> it0 + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptySequence0.reduce { it0, it1 -> it0 + it1 } },
                { Sequencex.reduce(emptySequence1) { it0, it1 -> it0 + it1 } })
        assertThrow(UnsupportedOperationException::class)
        { Sequencex.reduce(nullSequence1) { it0, it1 -> it0 + it1 } }

        assertTwoEquals("613273241",
                normalSequence0.reduceIndexed { i, it0, it1 -> it0 + i + it1 },
                Sequencex.reduceIndexed(normalSequence1) { i, it0, it1 -> it0 + i + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptySequence0.reduceIndexed { i, it0, it1 -> it0 + i + it1 } },
                { Sequencex.reduceIndexed(emptySequence1) { i, it0, it1 -> it0 + i + it1 } })
        assertThrow(UnsupportedOperationException::class)
        { Sequencex.reduceIndexed(nullSequence1) { i, it0, it1 -> it0 + i + it1 } }
    }

    @Test
    fun testSum() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null
        assertTwoEquals(19, normalSequence0.sumBy { it.toInt() }, Sequencex.sumBy(normalSequence1) { it.toInt() })
        assertTwoEquals(0, emptySequence0.sumBy { it.toInt() }, Sequencex.sumBy(emptySequence1) { it.toInt() })
        assertEquals(0, Sequencex.sumBy(nullSequence1) { it.toInt() })
        assertTwoEquals(19.0, normalSequence0.sumByDouble { it.toDouble() }, Sequencex.sumByDouble(normalSequence1) { it.toDouble() })
        assertTwoEquals(0.0, emptySequence0.sumByDouble { it.toDouble() }, Sequencex.sumByDouble(emptySequence1) { it.toDouble() })
        assertTwoEquals(0.0, 0.0, Sequencex.sumByDouble(nullSequence1) { it.toDouble() })

        val normalByteSequence0 = sequenceOf(6.toByte(), 3.toByte(), 7.toByte(), 2.toByte(), 1.toByte())
        val normalByteSequence1 = Sequencex.sequenceOf(6.toByte(), 3.toByte(), 7.toByte(), 2.toByte(), 1.toByte())
        val emptyByteSequence0 = sequenceOf<Byte>()
        val emptyByteSequence1 = Sequencex.sequenceOf<Byte>()
        val nullByteSequence1: Sequence<Byte>? = null
        assertTwoEquals(19, normalByteSequence0.sum(), Sequencex.sumOfByte(normalByteSequence1))
        assertTwoEquals(0, emptyByteSequence0.sum(), Sequencex.sumOfByte(emptyByteSequence1))
        assertTwoEquals(0, 0, Sequencex.sumOfByte(nullByteSequence1))

        val normalShortSequence0 = sequenceOf(6.toShort(), 3.toShort(), 7.toShort(), 2.toShort(), 1.toShort())
        val normalShortSequence1 = Sequencex.sequenceOf(6.toShort(), 3.toShort(), 7.toShort(), 2.toShort(), 1.toShort())
        val emptyShortSequence0 = sequenceOf<Short>()
        val emptyShortSequence1 = Sequencex.sequenceOf<Short>()
        val nullShortSequence1: Sequence<Short>? = null
        assertTwoEquals(19, normalShortSequence0.sum(), Sequencex.sumOfShort(normalShortSequence1))
        assertTwoEquals(0, emptyShortSequence0.sum(), Sequencex.sumOfShort(emptyShortSequence1))
        assertTwoEquals(0, 0, Sequencex.sumOfShort(nullShortSequence1))

        val normalIntSequence0 = sequenceOf(6, 3, 7, 2, 1)
        val normalIntSequence1 = Sequencex.sequenceOf(6, 3, 7, 2, 1)
        val emptyIntSequence0 = sequenceOf<Int>()
        val emptyIntSequence1 = Sequencex.sequenceOf<Int>()
        val nullIntSequence1: Sequence<Int>? = null
        assertTwoEquals(19, normalIntSequence0.sum(), Sequencex.sumOfInt(normalIntSequence1))
        assertTwoEquals(0, emptyIntSequence0.sum(), Sequencex.sumOfInt(emptyIntSequence1))
        assertTwoEquals(0, 0, Sequencex.sumOfInt(nullIntSequence1))

        val normalLongSequence0 = sequenceOf(6.toLong(), 3.toLong(), 7.toLong(), 2.toLong(), 1.toLong())
        val normalLongSequence1 = Sequencex.sequenceOf(6.toLong(), 3.toLong(), 7.toLong(), 2.toLong(), 1.toLong())
        val emptyLongSequence0 = sequenceOf<Long>()
        val emptyLongSequence1 = Sequencex.sequenceOf<Long>()
        val nullLongSequence1: Sequence<Long>? = null
        assertTwoEquals(19.toLong(), normalLongSequence0.sum(), Sequencex.sumOfLong(normalLongSequence1))
        assertTwoEquals(0.toLong(), emptyLongSequence0.sum(), Sequencex.sumOfLong(emptyLongSequence1))
        assertTwoEquals(0.toLong(), 0.toLong(), Sequencex.sumOfLong(nullLongSequence1))
        @Suppress("DEPRECATION")
        assertTwoEquals(19.toLong(), normalLongSequence0.sum(), Sequencex.sum(normalLongSequence1))
        @Suppress("DEPRECATION")
        assertTwoEquals(0.toLong(), emptyLongSequence0.sum(), Sequencex.sum(emptyLongSequence1))
        @Suppress("DEPRECATION")
        assertTwoEquals(0.toLong(), 0.toLong(), Sequencex.sum(nullLongSequence1))

        val normalFloatSequence0 = sequenceOf(6.toFloat(), 3.toFloat(), 7.toFloat(), 2.toFloat(), 1.toFloat())
        val normalFloatSequence1 = Sequencex.sequenceOf(6.toFloat(), 3.toFloat(), 7.toFloat(), 2.toFloat(), 1.toFloat())
        val emptyFloatSequence0 = sequenceOf<Float>()
        val emptyFloatSequence1 = Sequencex.sequenceOf<Float>()
        val nullFloatSequence1: Sequence<Float>? = null
        assertTwoEquals(19.toFloat(), normalFloatSequence0.sum(), Sequencex.sumOfFloat(normalFloatSequence1))
        assertTwoEquals(0.toFloat(), emptyFloatSequence0.sum(), Sequencex.sumOfFloat(emptyFloatSequence1))
        assertTwoEquals(0.toFloat(), 0.toFloat(), Sequencex.sumOfFloat(nullFloatSequence1))

        val normalDoubleSequence0 = sequenceOf(6.toDouble(), 3.toDouble(), 7.toDouble(), 2.toDouble(), 1.toDouble())
        val normalDoubleSequence1 = Sequencex.sequenceOf(6.toDouble(), 3.toDouble(), 7.toDouble(), 2.toDouble(), 1.toDouble())
        val emptyDoubleSequence0 = sequenceOf<Double>()
        val emptyDoubleSequence1 = Sequencex.sequenceOf<Double>()
        val nullDoubleSequence1: Sequence<Double>? = null
        assertTwoEquals(19.toDouble(), normalDoubleSequence0.sum(), Sequencex.sumOfDouble(normalDoubleSequence1))
        assertTwoEquals(0.toDouble(), emptyDoubleSequence0.sum(), Sequencex.sumOfDouble(emptyDoubleSequence1))
        assertTwoEquals(0.toDouble(), 0.toDouble(), Sequencex.sumOfDouble(nullDoubleSequence1))
    }

    @Test
    fun testRequireNoNulls() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val nullableSequence0 = sequenceOf("6", null, "7", "2", null)
        val nullableSequence1 = Sequencex.sequenceOf("6", null, "7", "2", null)
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null

        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.requireNoNulls().joinToString(),
                Sequencex.joinToString(Sequencex.requireNoNulls(normalSequence1)))
        assertTwoThrow(IllegalArgumentException::class,
                { nullableSequence0.requireNoNulls().joinToString() },
                { Sequencex.joinToString(Sequencex.requireNoNulls(nullableSequence1)) })
        assertTwoEquals("",
                emptySequence0.requireNoNulls().joinToString(),
                Sequencex.joinToString(Sequencex.requireNoNulls(emptySequence1)))
        assertEquals("", Sequencex.joinToString(Sequencex.requireNoNulls(nullSequence1)))
    }

    @Test
    fun testMinus() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null

        assertTwoEquals("6, 7, 2, 1",
                normalSequence0.minus("3").joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, "3")))
        assertTwoEquals("",
                emptySequence0.minus("3").joinToString(),
                Sequencex.joinToString(Sequencex.minus(emptySequence1, "3")))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence1, "3")))

        assertTwoEquals("6, 7, 2",
                normalSequence0.minus(arrayOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, arrayOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.minus(arrayOf()).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, arrayOf<String>())))
        assertTwoEquals("",
                emptySequence0.minus(arrayOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(emptySequence1, arrayOf("3", "1"))))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence1, arrayOf("3", "1"))))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence1, arrayOf<String>())))

        assertTwoEquals("6, 7, 2",
                normalSequence0.minus(listOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, listOf("3", "1"))))
        assertTwoEquals("6, 7, 2",
                normalSequence0.minus(setOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, setOf("3", "1"))))
        assertTwoEquals("6, 7, 2",
                normalSequence0.minus(arrayListOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, arrayListOf("3", "1"))))
        assertTwoEquals("6, 7",
                normalSequence0.minus(arrayListOf("3", "1", "2")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, arrayListOf("3", "1", "2"))))
        assertTwoEquals("6, 7",
                normalSequence0.minus(listOf("3", "1", "2")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, listOf("3", "1", "2"))))
        assertTwoEquals("6, 7",
                normalSequence0.minus(com.github.panpf.tools4j.iterable.TransformingIterable(listOf("3", "1", "2")) { it }).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, com.github.panpf.tools4j.iterable.TransformingIterable(listOf("3", "1", "2")) { it })))
        assertTwoEquals("",
                emptySequence0.minus(listOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(emptySequence1, listOf("3", "1"))))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence1, listOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.minus(listOf()).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, listOf<String>())))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence1, listOf<String>())))

        assertTwoEquals("6, 7, 2",
                normalSequence0.minus(sequenceOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, Sequencex.sequenceOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.minus(sequenceOf()).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, Sequencex.sequenceOf())))
        assertTwoEquals("",
                emptySequence0.minus(sequenceOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(emptySequence1, Sequencex.sequenceOf("3", "1"))))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence1, Sequencex.sequenceOf("3", "1"))))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence1, Sequencex.sequenceOf())))

        assertTwoEquals("6, 7, 2, 1",
                normalSequence0.minusElement("3").joinToString(),
                Sequencex.joinToString(Sequencex.minusElement(normalSequence1, "3")))
        assertTwoEquals("",
                emptySequence0.minusElement("3").joinToString(),
                Sequencex.joinToString(Sequencex.minusElement(emptySequence1, "3")))
        assertEquals("", Sequencex.joinToString(Sequencex.minusElement(nullSequence1, "3")))
    }

    @Test
    fun testPartition() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null

        assertTwoEquals("([6, 2], [3, 7, 1])",
                normalSequence0.partition { it.toInt() % 2 == 0 }.toString(),
                Sequencex.partition(normalSequence1) { it.toInt() % 2 == 0 }.toString())
        assertTwoEquals("([], [])",
                emptySequence0.partition { it.toInt() % 2 == 0 }.toString(),
                Sequencex.partition(emptySequence1) { it.toInt() % 2 == 0 }.toString())
        assertEquals("([], [])", Sequencex.partition(nullSequence1) { it.toInt() % 2 == 0 }.toString())
    }

    @Test
    fun testPlus() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null

        assertTwoEquals("6, 3, 7, 2, 1, 9",
                normalSequence0.plus("9").joinToString(),
                Sequencex.joinToString(Sequencex.plus(normalSequence1, "9")))
        assertTwoEquals("9",
                emptySequence0.plus("9").joinToString(),
                Sequencex.joinToString(Sequencex.plus(emptySequence1, "9")))
        assertEquals("9", Sequencex.joinToString(Sequencex.plus(nullSequence1, "9")))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalSequence0.plus(arrayOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(normalSequence1, arrayOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptySequence0.plus(arrayOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(emptySequence1, arrayOf("9", "4"))))
        assertEquals("9, 4", Sequencex.joinToString(Sequencex.plus(nullSequence1, arrayOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalSequence0.plus(listOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(normalSequence1, listOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptySequence0.plus(listOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(emptySequence1, listOf("9", "4"))))
        assertEquals("9, 4", Sequencex.joinToString(Sequencex.plus(nullSequence1, listOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalSequence0.plus(sequenceOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(normalSequence1, Sequencex.sequenceOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptySequence0.plus(sequenceOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(emptySequence1, Sequencex.sequenceOf("9", "4"))))
        assertEquals("9, 4", Sequencex.joinToString(Sequencex.plus(nullSequence1, Sequencex.sequenceOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9",
                normalSequence0.plusElement("9").joinToString(),
                Sequencex.joinToString(Sequencex.plusElement(normalSequence1, "9")))
        assertTwoEquals("9",
                emptySequence0.plusElement("9").joinToString(),
                Sequencex.joinToString(Sequencex.plusElement(emptySequence1, "9")))
        assertEquals("9", Sequencex.joinToString(Sequencex.plusElement(nullSequence1, "9")))
    }

    @Test
    fun testChunked() {
        val normalSequence0 = sequenceOf("a", "b", "c", "d", "e")
        val normalSequence1 = Sequencex.sequenceOf("a", "b", "c", "d", "e")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence = null as Sequence<String>?

        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.chunked(-1) },
                { Sequencex.chunked(normalSequence1, -1) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.chunked(0) },
                { Sequencex.chunked(normalSequence1, 0) })
        assertTwoEquals("[a], [b], [c], [d], [e]",
                normalSequence0.chunked(1).joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 1)))
        assertTwoEquals("[a, b], [c, d], [e]",
                normalSequence0.chunked(2).joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 2)))
        assertTwoEquals("[a, b, c], [d, e]",
                normalSequence0.chunked(3).joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 3)))
        assertTwoEquals("[a, b, c, d], [e]",
                normalSequence0.chunked(4).joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 4)))
        assertTwoEquals("[a, b, c, d, e]",
                normalSequence0.chunked(5).joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 5)))
        assertTwoEquals("[a, b, c, d, e]",
                normalSequence0.chunked(6).joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 6)))

        assertTwoEquals("",
                emptySequence0.chunked(1).joinToString(),
                Sequencex.joinToString(Sequencex.chunked(emptySequence1, 1)))
        assertEquals("",
                Sequencex.joinToString(Sequencex.chunked(nullSequence, 1)))


        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.chunked(-1) },
                { Sequencex.chunked(normalSequence1, -1) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.chunked(0) },
                { Sequencex.chunked(normalSequence1, 0) })
        assertTwoEquals("[a], [b], [c], [d], [e]",
                normalSequence0.chunked(1) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 1) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[a+b], [c+d], [e]",
                normalSequence0.chunked(2) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 2) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[a+b+c], [d+e]",
                normalSequence0.chunked(3) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 3) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[a+b+c+d], [e]",
                normalSequence0.chunked(4) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 4) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[a+b+c+d+e]",
                normalSequence0.chunked(5) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 5) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[a+b+c+d+e]",
                normalSequence0.chunked(6) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.chunked(normalSequence1, 6) { it.joinToString("+", "[", "]") }))

        assertTwoEquals("",
                emptySequence0.chunked(1) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.chunked(emptySequence1, 1) { it.joinToString("+", "[", "]") }))
        assertEquals("",
                Sequencex.joinToString(Sequencex.chunked(nullSequence, 1) { it.joinToString("+", "[", "]") }))
    }

    @Test
    fun testWindowed() {
        val normalSequence0 = sequenceOf(1, 2, 3, 4, 5)
        val normalSequence1 = Sequencex.sequenceOf(1, 2, 3, 4, 5)
        val emptySequence0 = sequenceOf<Int>()
        val emptySequence1 = Sequencex.sequenceOf<Int>()
        val nullSequence = null as Sequence<Int>?

        // Test illegal size and step parameter
        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.windowed(0, 0, true) },
                { Sequencex.windowed(normalSequence1, 0, 0, true) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.windowed(1, 0, true) },
                { Sequencex.windowed(normalSequence1, 1, 0, true) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.windowed(0, 1, true) },
                { Sequencex.windowed(normalSequence1, 0, 1, true) })

        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.windowed(0, 0, true) },
                { Sequencex.windowed(normalSequence1, 0, 0, true) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.windowed(1, 0, true) },
                { Sequencex.windowed(normalSequence1, 1, 0, true) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalSequence0.windowed(0, 1, true) },
                { Sequencex.windowed(normalSequence1, 0, 1, true) })

        // Test the size parameter
        assertTwoEquals("[1], [2], [3], [4], [5]",
                normalSequence0.windowed(1, 1, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 1, 1, true)))
        assertTwoEquals("[1, 2], [2, 3], [3, 4], [4, 5], [5]",
                normalSequence0.windowed(2, 1, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 1, true)))
        assertTwoEquals("[1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5], [5]",
                normalSequence0.windowed(3, 1, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 3, 1, true)))
        assertTwoEquals("[1, 2, 3, 4], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]",
                normalSequence0.windowed(4, 1, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 4, 1, true)))
        assertTwoEquals("[1, 2, 3, 4, 5], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]",
                normalSequence0.windowed(5, 1, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 5, 1, true)))
        assertTwoEquals("[1, 2, 3, 4, 5], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]",
                normalSequence0.windowed(6, 1, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 6, 1, true)))

        assertTwoEquals("[1], [2], [3], [4], [5]",
                normalSequence0.windowed(1, 1, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 1, 1, true) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2], [2+3], [3+4], [4+5], [5]",
                normalSequence0.windowed(2, 1, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 1, true) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2+3], [2+3+4], [3+4+5], [4+5], [5]",
                normalSequence0.windowed(3, 1, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 3, 1, true) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2+3+4], [2+3+4+5], [3+4+5], [4+5], [5]",
                normalSequence0.windowed(4, 1, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 4, 1, true) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2+3+4+5], [2+3+4+5], [3+4+5], [4+5], [5]",
                normalSequence0.windowed(5, 1, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 5, 1, true) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2+3+4+5], [2+3+4+5], [3+4+5], [4+5], [5]",
                normalSequence0.windowed(6, 1, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 6, 1, true) { it.joinToString("+", "[", "]") }))

        // Test the step parameter
        assertTwoEquals("[1, 2], [3, 4], [5]",
                normalSequence0.windowed(2, 2, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 2, true)))
        assertTwoEquals("[1, 2], [4, 5]",
                normalSequence0.windowed(2, 3, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 3, true)))
        assertTwoEquals("[1, 2], [5]",
                normalSequence0.windowed(2, 4, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 4, true)))
        assertTwoEquals("[1, 2]",
                normalSequence0.windowed(2, 5, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 5, true)))
        assertTwoEquals("[1, 2]",
                normalSequence0.windowed(2, 6, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 6, true)))

        assertTwoEquals("[1+2], [3+4], [5]",
                normalSequence0.windowed(2, 2, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 2, true) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2], [4+5]",
                normalSequence0.windowed(2, 3, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 3, true) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2], [5]",
                normalSequence0.windowed(2, 4, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 4, true) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2]",
                normalSequence0.windowed(2, 5, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 5, true) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2]",
                normalSequence0.windowed(2, 6, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 6, true) { it.joinToString("+", "[", "]") }))

        // Test the partialWindows parameter
        assertTwoEquals("[1], [2], [3], [4], [5]",
                normalSequence0.windowed(1, 1, false).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 1, 1, false)))
        assertTwoEquals("[1, 2], [2, 3], [3, 4], [4, 5]",
                normalSequence0.windowed(2, 1, false).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 1, false)))
        assertTwoEquals("[1, 2], [3, 4]",
                normalSequence0.windowed(2, 2, false).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 2, false)))
        assertTwoEquals("[1, 2, 3], [2, 3, 4], [3, 4, 5]",
                normalSequence0.windowed(3, 1, false).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 3, 1, false)))
        assertTwoEquals("[1, 2, 3, 4], [2, 3, 4, 5]",
                normalSequence0.windowed(4, 1, false).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 4, 1, false)))
        assertTwoEquals("[1, 2, 3, 4, 5]",
                normalSequence0.windowed(5, 1, false).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 5, 1, false)))
        assertTwoEquals("",
                normalSequence0.windowed(6, 1, false).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 6, 1, false)))

        assertTwoEquals("[1], [2], [3], [4], [5]",
                normalSequence0.windowed(1, 1, false) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 1, 1, false) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2], [2+3], [3+4], [4+5]",
                normalSequence0.windowed(2, 1, false) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 2, 1, false) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2+3], [2+3+4], [3+4+5]",
                normalSequence0.windowed(3, 1, false) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 3, 1, false) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2+3+4], [2+3+4+5]",
                normalSequence0.windowed(4, 1, false) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 4, 1, false) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("[1+2+3+4+5]",
                normalSequence0.windowed(5, 1, false) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 5, 1, false) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("",
                normalSequence0.windowed(6, 1, false) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(normalSequence1, 6, 1, false) { it.joinToString("+", "[", "]") }))

        // Test empty or null
        assertTwoEquals("",
                emptySequence0.windowed(1, 1, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(emptySequence1, 1, 1, true)))
        assertTwoEquals("",
                emptySequence0.windowed(1, 1, true).joinToString(),
                Sequencex.joinToString(Sequencex.windowed(nullSequence, 1, 1, true)))

        assertTwoEquals("",
                emptySequence0.windowed(1, 1, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(emptySequence1, 1, 1, true) { it.joinToString("+", "[", "]") }))
        assertTwoEquals("",
                emptySequence0.windowed(1, 1, true) { it.joinToString("+", "[", "]") }.joinToString(),
                Sequencex.joinToString(Sequencex.windowed(nullSequence, 1, 1, true) { it.joinToString("+", "[", "]") }))
    }

    @Test
    fun testZip() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence00 = sequenceOf("4", "9", "5")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val normalSequence11 = Sequencex.sequenceOf("4", "9", "5")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null

        assertTwoEquals("(6, 4), (3, 9), (7, 5)",
                normalSequence0.zip(normalSequence00).joinToString(),
                Sequencex.joinToString(Sequencex.zip(normalSequence1, normalSequence11)))
        assertTwoEquals("",
                emptySequence0.zip(normalSequence00).joinToString(),
                Sequencex.joinToString(Sequencex.zip(emptySequence1, normalSequence11)))
        assertEquals("", Sequencex.joinToString(Sequencex.zip(nullSequence1, normalSequence11)))

        assertTwoEquals("64, 39, 75",
                normalSequence0.zip(normalSequence00) { it0, it1 -> it0 + it1 }.joinToString(),
                Sequencex.joinToString(Sequencex.zip(normalSequence1, normalSequence11) { it0, it1 -> it0 + it1 }))
        assertTwoEquals("",
                emptySequence0.zip(normalSequence00) { it0, it1 -> it0 + it1 }.joinToString(),
                Sequencex.joinToString(Sequencex.zip(emptySequence1, normalSequence11) { it0, it1 -> it0 + it1 }))
        assertEquals("", Sequencex.joinToString(Sequencex.zip(nullSequence1, normalSequence11) { it0, it1 -> it0 + it1 }))

        assertTwoEquals("([6, 3, 7], [4, 9, 5])",
                normalSequence0.zip(normalSequence00).unzip().toString(),
                Sequencex.unzip(Sequencex.zip(normalSequence1, normalSequence11)).toString())
        assertTwoEquals("([], [])",
                sequenceOf<Pair<String, String>>().unzip().toString(),
                Sequencex.unzip(Sequencex.sequenceOf<com.github.panpf.tools4j.common.Pair<String, String>>()).toString())
        assertEquals("([], [])", Sequencex.unzip(null as Sequence<com.github.panpf.tools4j.common.Pair<String, String>>?).toString())
    }

    @Test
    fun testJoinTo() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1: Sequence<String>? = null

        assertTwoEquals("^60:30:70:20:***$",
                normalSequence0.joinTo(buffer = StringBuilder(), separator = ":", prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), ":", "^", "$", 4, "***", { it + "0" }).toString())
        assertTwoEquals("^60, 30, 70, 20, ***$",
                normalSequence0.joinTo(buffer = StringBuilder(), prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), null, "^", "$", 4, "***", { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, ***$",
                normalSequence0.joinTo(buffer = StringBuilder(), postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), null, null, "$", 4, "***", { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, ***",
                normalSequence0.joinTo(buffer = StringBuilder(), limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), null, null, null, 4, "***", { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, ...",
                normalSequence0.joinTo(buffer = StringBuilder(), limit = 4, transform = { it + "0" }).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), null, null, null, 4, null, { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, 10",
                normalSequence0.joinTo(buffer = StringBuilder(), limit = 5, transform = { it + "0" }).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), null, null, null, 5, null, { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, 10",
                normalSequence0.joinTo(buffer = StringBuilder(), transform = { it + "0" }).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), null, null, null, -1, null, { it + "0" }).toString())
        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.joinTo(buffer = StringBuilder()).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), null, null, null, -1, null, null).toString())
        assertTwoEquals("^$",
                emptySequence0.joinTo(buffer = StringBuilder(), prefix = "^", postfix = "$").toString(),
                Sequencex.joinTo(emptySequence1, StringBuilder(), null, "^", "$", -1, null, null).toString())
        assertTwoEquals("",
                emptySequence0.joinTo(buffer = StringBuilder()).toString(),
                Sequencex.joinTo(emptySequence1, StringBuilder(), null, null, null, -1, null, null).toString())
        assertEquals("^$",
                Sequencex.joinTo(nullSequence1, StringBuilder(), null, "^", "$", -1, null, null).toString())
        assertEquals("",
                Sequencex.joinTo(nullSequence1, StringBuilder(), null, null, null, -1, null, null).toString())

        assertTwoEquals("60,30,70,20,10",
                normalSequence0.joinTo(buffer = StringBuilder(), separator = ",", transform = { it + "0" }).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), ",", { it + "0" }).toString())
        assertTwoEquals("",
                emptySequence0.joinTo(buffer = StringBuilder(), separator = ",", transform = { it + "0" }).toString(),
                Sequencex.joinTo(emptySequence1, StringBuilder(), ",", { it + "0" }).toString())
        assertEquals("",
                Sequencex.joinTo(nullSequence1, StringBuilder(), ",", { it + "0" }).toString())

        assertTwoEquals("60, 30, 70, 20, 10",
                normalSequence0.joinTo(buffer = StringBuilder(), transform = { it + "0" }).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), { it + "0" }).toString())
        assertTwoEquals("",
                emptySequence0.joinTo(buffer = StringBuilder(), transform = { it + "0" }).toString(),
                Sequencex.joinTo(emptySequence1, StringBuilder(), { it + "0" }).toString())
        assertEquals("",
                Sequencex.joinTo(nullSequence1, StringBuilder(), { it + "0" }).toString())

        assertTwoEquals("6,3,7,2,1",
                normalSequence0.joinTo(buffer = StringBuilder(), separator = ",").toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), ",").toString())
        assertTwoEquals("",
                emptySequence0.joinTo(buffer = StringBuilder(), separator = ",").toString(),
                Sequencex.joinTo(emptySequence1, StringBuilder(), ",").toString())
        assertEquals("",
                Sequencex.joinTo(nullSequence1, StringBuilder(), ",").toString())

        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.joinTo(buffer = StringBuilder()).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder()).toString())
        assertTwoEquals("",
                emptySequence0.joinTo(buffer = StringBuilder()).toString(),
                Sequencex.joinTo(emptySequence1, StringBuilder()).toString())
        assertEquals("",
                Sequencex.joinTo(nullSequence1, StringBuilder()).toString())

        assertThrow(IOException::class) { emptySequence0.joinTo(buffer = ExceptionAppendable()).toString() }
        assertThrow(IllegalStateException::class) { Sequencex.joinTo(emptySequence1, ExceptionAppendable(), null, null, null, -1, null, null).toString() }


        assertTwoEquals("^60:30:70:20:***$",
                normalSequence0.joinToString(separator = ":", prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }),
                Sequencex.joinToString(normalSequence1, ":", "^", "$", 4, "***") { it + "0" })
        assertTwoEquals("^60, 30, 70, 20, ***$",
                normalSequence0.joinToString(prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }),
                Sequencex.joinToString(normalSequence1, null, "^", "$", 4, "***") { it + "0" })
        assertTwoEquals("60, 30, 70, 20, ***$",
                normalSequence0.joinToString(postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }),
                Sequencex.joinToString(normalSequence1, null, null, "$", 4, "***") { it + "0" })
        assertTwoEquals("60, 30, 70, 20, ***",
                normalSequence0.joinToString(limit = 4, truncated = "***", transform = { it + "0" }),
                Sequencex.joinToString(normalSequence1, null, null, null, 4, "***") { it + "0" })
        assertTwoEquals("60, 30, 70, 20, ...",
                normalSequence0.joinToString(limit = 4, transform = { it + "0" }),
                Sequencex.joinToString(normalSequence1, null, null, null, 4, null) { it + "0" })
        assertTwoEquals("60, 30, 70, 20, 10",
                normalSequence0.joinToString(transform = { it + "0" }),
                Sequencex.joinToString(normalSequence1, null, null, null, -1, null) { it + "0" })
        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.joinToString(),
                Sequencex.joinToString(normalSequence1, null, null, null, -1, null, null))
        assertTwoEquals("^$",
                emptySequence0.joinToString(prefix = "^", postfix = "$"),
                Sequencex.joinToString(emptySequence1, null, "^", "$", -1, null, null))
        assertTwoEquals("",
                emptySequence0.joinToString(),
                Sequencex.joinToString(emptySequence1, null, null, null, -1, null, null))
        assertEquals("^$",
                Sequencex.joinToString(nullSequence1, null, "^", "$", -1, null, null))
        assertEquals("",
                Sequencex.joinToString(nullSequence1, null, null, null, -1, null, null))

        assertTwoEquals("60,30,70,20,10",
                normalSequence0.joinToString(separator = ",", transform = { it + "0" }),
                Sequencex.joinToString(normalSequence1, ",") { it + "0" })
        assertTwoEquals("",
                emptySequence0.joinToString(separator = ",", transform = { it + "0" }),
                Sequencex.joinToString(emptySequence1, ",") { it + "0" })
        assertEquals("",
                Sequencex.joinToString(nullSequence1, ",") { it + "0" })

        assertTwoEquals("60, 30, 70, 20, 10",
                normalSequence0.joinToString(transform = { it + "0" }),
                Sequencex.joinToString(normalSequence1) { it + "0" })
        assertTwoEquals("",
                emptySequence0.joinToString(transform = { it + "0" }),
                Sequencex.joinToString(emptySequence1) { it + "0" })
        assertEquals("",
                Sequencex.joinToString(nullSequence1) { it + "0" })

        assertTwoEquals("6,3,7,2,1",
                normalSequence0.joinToString(separator = ","),
                Sequencex.joinToString(normalSequence1, ","))
        assertTwoEquals("",
                emptySequence0.joinToString(separator = ","),
                Sequencex.joinToString(emptySequence1, ","))
        assertEquals("",
                Sequencex.joinToString(nullSequence1, ","))

        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.joinToString(),
                Sequencex.joinToString(normalSequence1))
        assertTwoEquals("",
                emptySequence0.joinToString(),
                Sequencex.joinToString(emptySequence1))
        assertEquals("",
                Sequencex.joinToString(nullSequence1))
    }

    @Test
    fun testAverage() {
        val normalByteSequence0 = sequenceOf(6.toByte(), 3.toByte(), 7.toByte(), 2.toByte(), 1.toByte())
        val normalByteSequence1 = Sequencex.sequenceOf(6.toByte(), 3.toByte(), 7.toByte(), 2.toByte(), 1.toByte())
        val emptyByteSequence0 = sequenceOf<Byte>()
        val emptyByteSequence1 = Sequencex.sequenceOf<Byte>()
        val nullByteSequence1: Sequence<Byte>? = null
        assertTwoEquals(3.8, normalByteSequence0.average(), Sequencex.averageOfByte(normalByteSequence1))
        assertTwoEquals(Double.NaN, emptyByteSequence0.average(), Sequencex.averageOfByte(emptyByteSequence1))
        assertTwoEquals(Double.NaN, Double.NaN, Sequencex.averageOfByte(nullByteSequence1))

        val normalShortSequence0 = sequenceOf(6.toShort(), 3.toShort(), 7.toShort(), 2.toShort(), 1.toShort())
        val normalShortSequence1 = Sequencex.sequenceOf(6.toShort(), 3.toShort(), 7.toShort(), 2.toShort(), 1.toShort())
        val emptyShortSequence0 = sequenceOf<Short>()
        val emptyShortSequence1 = Sequencex.sequenceOf<Short>()
        val nullShortSequence1: Sequence<Short>? = null
        assertTwoEquals(3.8, normalShortSequence0.average(), Sequencex.averageOfShort(normalShortSequence1))
        assertTwoEquals(Double.NaN, emptyShortSequence0.average(), Sequencex.averageOfShort(emptyShortSequence1))
        assertTwoEquals(Double.NaN, Double.NaN, Sequencex.averageOfShort(nullShortSequence1))

        val normalIntSequence0 = sequenceOf(6, 3, 7, 2, 1)
        val normalIntSequence1 = Sequencex.sequenceOf(6, 3, 7, 2, 1)
        val emptyIntSequence0 = sequenceOf<Int>()
        val emptyIntSequence1 = Sequencex.sequenceOf<Int>()
        val nullIntSequence1: Sequence<Int>? = null
        assertTwoEquals(3.8, normalIntSequence0.average(), Sequencex.averageOfInt(normalIntSequence1))
        assertTwoEquals(Double.NaN, emptyIntSequence0.average(), Sequencex.averageOfInt(emptyIntSequence1))
        assertTwoEquals(Double.NaN, Double.NaN, Sequencex.averageOfInt(nullIntSequence1))

        val normalLongSequence0 = sequenceOf(6.toLong(), 3.toLong(), 7.toLong(), 2.toLong(), 1.toLong())
        val normalLongSequence1 = Sequencex.sequenceOf(6.toLong(), 3.toLong(), 7.toLong(), 2.toLong(), 1.toLong())
        val emptyLongSequence0 = sequenceOf<Long>()
        val emptyLongSequence1 = Sequencex.sequenceOf<Long>()
        val nullLongSequence1: Sequence<Long>? = null
        assertTwoEquals(3.8, normalLongSequence0.average(), Sequencex.averageOfLong(normalLongSequence1))
        assertTwoEquals(Double.NaN, emptyLongSequence0.average(), Sequencex.averageOfLong(emptyLongSequence1))
        assertTwoEquals(Double.NaN, Double.NaN, Sequencex.averageOfLong(nullLongSequence1))

        val normalFloatSequence0 = sequenceOf(6.toFloat(), 3.toFloat(), 7.toFloat(), 2.toFloat(), 1.toFloat())
        val normalFloatSequence1 = Sequencex.sequenceOf(6.toFloat(), 3.toFloat(), 7.toFloat(), 2.toFloat(), 1.toFloat())
        val emptyFloatSequence0 = sequenceOf<Float>()
        val emptyFloatSequence1 = Sequencex.sequenceOf<Float>()
        val nullFloatSequence1: Sequence<Float>? = null
        assertTwoEquals(3.8, normalFloatSequence0.average(), Sequencex.averageOfFloat(normalFloatSequence1))
        assertTwoEquals(Double.NaN, emptyFloatSequence0.average(), Sequencex.averageOfFloat(emptyFloatSequence1))
        assertTwoEquals(Double.NaN, Double.NaN, Sequencex.averageOfFloat(nullFloatSequence1))

        val normalDoubleSequence0 = sequenceOf(6.toDouble(), 3.toDouble(), 7.toDouble(), 2.toDouble(), 1.toDouble())
        val normalDoubleSequence1 = Sequencex.sequenceOf(6.toDouble(), 3.toDouble(), 7.toDouble(), 2.toDouble(), 1.toDouble())
        val emptyDoubleSequence0 = sequenceOf<Double>()
        val emptyDoubleSequence1 = Sequencex.sequenceOf<Double>()
        val nullDoubleSequence1: Sequence<Double>? = null
        assertTwoEquals(3.8, normalDoubleSequence0.average(), Sequencex.averageOfDouble(normalDoubleSequence1))
        assertTwoEquals(Double.NaN, emptyDoubleSequence0.average(), Sequencex.averageOfDouble(emptyDoubleSequence1))
        assertTwoEquals(Double.NaN, Double.NaN, Sequencex.averageOfDouble(nullDoubleSequence1))
    }


    class IntInitialValue(private val end: Int) : InitialValue<Int> {
        private var next = 0

        override fun get(): Int? {
            return if (next <= end) next++ else null
        }
    }

    class ExceptionAppendable : Appendable {
        override fun append(csq: CharSequence?): java.lang.Appendable {
            throw IOException()
        }

        override fun append(csq: CharSequence?, start: Int, end: Int): java.lang.Appendable {
            throw IOException()
        }

        override fun append(c: Char): java.lang.Appendable {
            throw IOException()
        }
    }
}