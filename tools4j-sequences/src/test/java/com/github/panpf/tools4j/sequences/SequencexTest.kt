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
        val nullSequence0: Sequence<String>? = null

        assertTwoEquals("[60, 30, 70, 20, 10]",
                normalSequence0.joinToString(transform = { it + "0" }, prefix = "[", postfix = "]"),
                Sequencex.joinToArrayString(normalSequence1) { it + "0" })
        assertTwoEquals("[]",
                emptySequence0.joinToString(transform = { it + "0" }, prefix = "[", postfix = "]"),
                Sequencex.joinToArrayString(emptySequence1) { it + "0" })
        assertEquals("[]",
                Sequencex.joinToArrayString(nullSequence0) { it + "0" })

        assertTwoEquals("[6, 3, 7, 2, 1]",
                normalSequence0.joinToString(prefix = "[", postfix = "]"),
                Sequencex.joinToArrayString(normalSequence1))
        assertTwoEquals("[]",
                emptySequence0.joinToString(prefix = "[", postfix = "]"),
                Sequencex.joinToArrayString(emptySequence1))
        assertEquals("[]",
                Sequencex.joinToArrayString(nullSequence0))
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
        val sequence = Sequencex.sequenceOf("a", "b", "c")

        assertTrue(Sequencex.contains(sequence, "a"))
        assertTrue(Sequencex.contains(sequence, "b"))
        assertTrue(Sequencex.contains(sequence, "c"))
        assertFalse(Sequencex.contains(sequence, "d"))
    }

    @Test
    fun testElementAt() {
        val sequence0 = sequenceOf("a", "b", "c")
        val sequence1 = Sequencex.sequenceOf("a", "b", "c")
        val nullSequence = null as Sequence<String>?

        assertTwoEquals("a", sequence0.elementAt(0), Sequencex.elementAt(sequence1, 0))
        assertTwoEquals("b", sequence0.elementAt(1), Sequencex.elementAt(sequence1, 1))
        assertTwoEquals("c", sequence0.elementAt(2), Sequencex.elementAt(sequence1, 2))
        assertTwoThrow(IndexOutOfBoundsException::class, { sequence0.elementAt(-1) }, { Sequencex.elementAt(sequence1, -1) })
        assertTwoThrow(IndexOutOfBoundsException::class, { sequence0.elementAt(3) }, { Sequencex.elementAt(sequence1, 3) })

        assertTwoEquals("a", sequence0.elementAtOrElse(0) { "j" }, Sequencex.elementAtOrElse(sequence1, 0) { "j" })
        assertTwoEquals("b", sequence0.elementAtOrElse(1) { "k" }, Sequencex.elementAtOrElse(sequence1, 1) { "j" })
        assertTwoEquals("c", sequence0.elementAtOrElse(2) { "j" }, Sequencex.elementAtOrElse(sequence1, 2) { "j" })
        assertTwoEquals("j", sequence0.elementAtOrElse(-1) { "j" }, Sequencex.elementAtOrElse(sequence1, -1) { "j" })
        assertTwoEquals("k", sequence0.elementAtOrElse(3) { "k" }, Sequencex.elementAtOrElse(sequence1, 3) { "k" })
        assertEquals("x", Sequencex.elementAtOrElse(nullSequence, 0) { "x" })
        assertEquals("y", Sequencex.elementAtOrElse(nullSequence, 1) { "y" })
        assertEquals("z", Sequencex.elementAtOrElse(nullSequence, 2) { "z" })
        assertEquals("j", Sequencex.elementAtOrElse(nullSequence, -1) { "j" })
        assertEquals("k", Sequencex.elementAtOrElse(nullSequence, 3) { "k" })

        assertTwoEquals("a", sequence0.elementAtOrNull(0), Sequencex.elementAtOrNull(sequence1, 0))
        assertTwoEquals("b", sequence0.elementAtOrNull(1), Sequencex.elementAtOrNull(sequence1, 1))
        assertTwoEquals("c", sequence0.elementAtOrNull(2), Sequencex.elementAtOrNull(sequence1, 2))
        assertTwoEquals(null, sequence0.elementAtOrNull(-1), Sequencex.elementAtOrNull(sequence1, -1))
        assertTwoEquals(null, sequence0.elementAtOrNull(3), Sequencex.elementAtOrNull(sequence1, 3))
        assertNull(Sequencex.elementAtOrNull(nullSequence, 0))
        assertNull(Sequencex.elementAtOrNull(nullSequence, 1))
        assertNull(Sequencex.elementAtOrNull(nullSequence, 2))
        assertNull(Sequencex.elementAtOrNull(nullSequence, -1))
        assertNull(Sequencex.elementAtOrNull(nullSequence, 3))
    }

    @Test
    fun testFind() {
        val sequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")

        assertTwoEquals("aj", sequence0.find { it.startsWith("a") }, Sequencex.find(sequence1) { it.startsWith("a") })
        assertTwoEquals("bj", sequence0.find { it.startsWith("b") }, Sequencex.find(sequence1) { it.startsWith("b") })
        assertTwoEquals("cj", sequence0.find { it.startsWith("c") }, Sequencex.find(sequence1) { it.startsWith("c") })
        assertTwoEquals(null, sequence0.find { it.startsWith("k") }, Sequencex.find(sequence1) { it.startsWith("k") })

        assertTwoEquals("aj", sequence0.findLast { it.startsWith("a") }, Sequencex.findLast(sequence1) { it.startsWith("a") })
        assertTwoEquals("bo", sequence0.findLast { it.startsWith("b") }, Sequencex.findLast(sequence1) { it.startsWith("b") })
        assertTwoEquals("cj", sequence0.findLast { it.startsWith("c") }, Sequencex.findLast(sequence1) { it.startsWith("c") })
        assertTwoEquals(null, sequence0.findLast { it.startsWith("k") }, Sequencex.findLast(sequence1) { it.startsWith("k") })
    }

    @Test
    fun testFirst() {
        val sequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals("aj", sequence0.first(), Sequencex.first(sequence1))
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.first() }, { Sequencex.first(emptySequence1) })
        assertThrow(NoSuchElementException::class) { Sequencex.first(nullSequence1) }

        assertTwoEquals("aj", sequence0.first { it.startsWith("a") }, Sequencex.first(sequence1) { it.startsWith("a") })
        assertTwoEquals("bj", sequence0.first { it.startsWith("b") }, Sequencex.first(sequence1) { it.startsWith("b") })
        assertTwoEquals("cj", sequence0.first { it.startsWith("c") }, Sequencex.first(sequence1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { sequence0.first { it.startsWith("k") } }, { Sequencex.first(sequence1) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.first { it.startsWith("a") } }, { Sequencex.first(emptySequence1) { it.startsWith("a") } })
        assertThrow(NoSuchElementException::class) { Sequencex.first(nullSequence1) { it.startsWith("a") } }

        assertTwoEquals("aj", sequence0.firstOrNull(), Sequencex.firstOrNull(sequence1))
        assertTwoEquals(null, emptySequence0.firstOrNull(), Sequencex.firstOrNull(emptySequence1))
        assertNull(Sequencex.firstOrNull(nullSequence1))

        assertTwoEquals("aj", sequence0.firstOrNull { it.startsWith("a") }, Sequencex.firstOrNull(sequence1) { it.startsWith("a") })
        assertTwoEquals("bj", sequence0.firstOrNull { it.startsWith("b") }, Sequencex.firstOrNull(sequence1) { it.startsWith("b") })
        assertTwoEquals("cj", sequence0.firstOrNull { it.startsWith("c") }, Sequencex.firstOrNull(sequence1) { it.startsWith("c") })
        assertTwoEquals(null, sequence0.firstOrNull { it.startsWith("k") }, Sequencex.firstOrNull(sequence1) { it.startsWith("k") })
        assertTwoEquals(null, emptySequence0.firstOrNull { it.startsWith("k") }, Sequencex.firstOrNull(emptySequence1) { it.startsWith("k") })
        assertNull(Sequencex.firstOrNull(nullSequence1) { it.startsWith("k") })
    }

    @Test
    fun testIndexOf() {
        val sequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(0, sequence0.indexOf("aj"), Sequencex.indexOf(sequence1, "aj"))
        assertTwoEquals(1, sequence0.indexOf("bj"), Sequencex.indexOf(sequence1, "bj"))
        assertTwoEquals(2, sequence0.indexOf("cj"), Sequencex.indexOf(sequence1, "cj"))
        assertTwoEquals(3, sequence0.indexOf("bo"), Sequencex.indexOf(sequence1, "bo"))
        assertTwoEquals(-1, sequence0.indexOf("bb"), Sequencex.indexOf(sequence1, "bb"))
        assertEquals(-1, Sequencex.indexOf(nullSequence1, "bb"))

        assertTwoEquals(0, sequence0.indexOfFirst { it.startsWith("a") }, Sequencex.indexOfFirst(sequence1) { it.startsWith("a") })
        assertTwoEquals(1, sequence0.indexOfFirst { it.startsWith("b") }, Sequencex.indexOfFirst(sequence1) { it.startsWith("b") })
        assertTwoEquals(2, sequence0.indexOfFirst { it.startsWith("c") }, Sequencex.indexOfFirst(sequence1) { it.startsWith("c") })
        assertTwoEquals(-1, sequence0.indexOfFirst { it.startsWith("k") }, Sequencex.indexOfFirst(sequence1) { it.startsWith("k") })
        assertEquals(-1, Sequencex.indexOfFirst(nullSequence1) { it.startsWith("k") })

        assertTwoEquals(0, sequence0.indexOfLast { it.startsWith("a") }, Sequencex.indexOfLast(sequence1) { it.startsWith("a") })
        assertTwoEquals(3, sequence0.indexOfLast { it.startsWith("b") }, Sequencex.indexOfLast(sequence1) { it.startsWith("b") })
        assertTwoEquals(2, sequence0.indexOfLast { it.startsWith("c") }, Sequencex.indexOfLast(sequence1) { it.startsWith("c") })
        assertTwoEquals(-1, sequence0.indexOfLast { it.startsWith("k") }, Sequencex.indexOfLast(sequence1) { it.startsWith("k") })
        assertEquals(-1, Sequencex.indexOfLast(nullSequence1) { it.startsWith("k") })
    }

    @Test
    fun testLast() {
        val sequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals("bo", sequence0.last(), Sequencex.last(sequence1))
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.last() }, { Sequencex.last(emptySequence1) })
        assertThrow(NoSuchElementException::class) { Sequencex.last(nullSequence1) }

        assertTwoEquals("aj", sequence0.last { it.startsWith("a") }, Sequencex.last(sequence1) { it.startsWith("a") })
        assertTwoEquals("bo", sequence0.last { it.startsWith("b") }, Sequencex.last(sequence1) { it.startsWith("b") })
        assertTwoEquals("cj", sequence0.last { it.startsWith("c") }, Sequencex.last(sequence1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { sequence0.last { it.startsWith("k") } }, { Sequencex.last(sequence1) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.last { it.startsWith("a") } }, { Sequencex.last(emptySequence1) { it.startsWith("a") } })
        assertThrow(NoSuchElementException::class) { Sequencex.last(nullSequence1) { it.startsWith("a") } }

        assertTwoEquals("bo", sequence0.lastOrNull(), Sequencex.lastOrNull(sequence1))
        assertTwoEquals(null, emptySequence0.lastOrNull(), Sequencex.lastOrNull(emptySequence1))
        assertNull(Sequencex.lastOrNull(nullSequence1))

        assertTwoEquals("aj", sequence0.lastOrNull { it.startsWith("a") }, Sequencex.lastOrNull(sequence1) { it.startsWith("a") })
        assertTwoEquals("bo", sequence0.lastOrNull { it.startsWith("b") }, Sequencex.lastOrNull(sequence1) { it.startsWith("b") })
        assertTwoEquals("cj", sequence0.lastOrNull { it.startsWith("c") }, Sequencex.lastOrNull(sequence1) { it.startsWith("c") })
        assertTwoEquals(null, sequence0.lastOrNull { it.startsWith("k") }, Sequencex.lastOrNull(sequence1) { it.startsWith("k") })
        assertTwoEquals(null, emptySequence0.lastOrNull { it.startsWith("k") }, Sequencex.lastOrNull(emptySequence1) { it.startsWith("k") })
        assertNull(Sequencex.lastOrNull(nullSequence1) { it.startsWith("k") })

        assertTwoEquals(0, sequence0.lastIndexOf("aj"), Sequencex.lastIndexOf(sequence1, "aj"))
        assertTwoEquals(1, sequence0.lastIndexOf("bj"), Sequencex.lastIndexOf(sequence1, "bj"))
        assertTwoEquals(2, sequence0.lastIndexOf("cj"), Sequencex.lastIndexOf(sequence1, "cj"))
        assertTwoEquals(3, sequence0.lastIndexOf("bo"), Sequencex.lastIndexOf(sequence1, "bo"))
        assertTwoEquals(-1, sequence0.lastIndexOf("bb"), Sequencex.lastIndexOf(sequence1, "bb"))
        assertEquals(-1, Sequencex.lastIndexOf(nullSequence1, "bb"))
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
        val sequence0 = sequenceOf("aj", "bj", "cj", "dj")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "dj")
        val nullSequence1 = null as Sequence<String>?

        /*
         * drop 系列的方法表示从列表头部开始跳过部分元素
         */

        // drop 方法的意思是从列表头部开始跳过多少个元素

        assertTwoThrow(IllegalArgumentException::class,
                { sequence0.drop(-1) },
                { Sequencex.drop(sequence1, -1) }
        )

        assertTrue(sequence0 === sequence0.drop(0))
        assertTrue(sequence1 === Sequencex.drop(sequence1, 0))
        assertTrue(Sequencex.emptySequence<String>() === Sequencex.drop(nullSequence1, 0))

        assertTwoEquals("bj, cj, dj",
                sequence0.drop(1).joinToString(),
                Sequencex.joinToString(Sequencex.drop(sequence1, 1)))

        assertTwoEquals("cj, dj",
                sequence0.drop(2).joinToString(),
                Sequencex.joinToString(Sequencex.drop(sequence1, 2)))

        assertTwoEquals("dj",
                sequence0.drop(3).joinToString(),
                Sequencex.joinToString(Sequencex.drop(sequence1, 3)))

        assertTwoEquals("",
                sequence0.drop(4).joinToString(),
                Sequencex.joinToString(Sequencex.drop(sequence1, 4)))

        assertTwoEquals("",
                sequence0.drop(5).joinToString(),
                Sequencex.joinToString(Sequencex.drop(sequence1, 5)))

        assertTwoEquals("",
                sequence0.drop(5).joinToString(),
                Sequencex.joinToString(Sequencex.drop(Sequencex.drop(sequence1, 5), 5)))

        // dropWhile 方法的意思是从不符合条件的元素开始往后遍历

        assertTwoEquals("aj, bj, cj, dj",
                sequence0.dropWhile { !it.startsWith("a") }.joinToString(),
                Sequencex.joinToString(Sequencex.dropWhile(sequence1) { !it.startsWith("a") }))

        assertTwoEquals("bj, cj, dj",
                sequence0.dropWhile { !it.startsWith("b") }.joinToString(),
                Sequencex.joinToString(Sequencex.dropWhile(sequence1) { !it.startsWith("b") }))

        assertTwoEquals("cj, dj",
                sequence0.dropWhile { !it.startsWith("c") }.joinToString(),
                Sequencex.joinToString(Sequencex.dropWhile(sequence1) { !it.startsWith("c") }))

        assertTwoEquals("dj",
                sequence0.dropWhile { !it.startsWith("d") }.joinToString(),
                Sequencex.joinToString(Sequencex.dropWhile(sequence1) { !it.startsWith("d") }))

        assertTwoEquals("",
                sequence0.dropWhile { !it.startsWith("e") }.joinToString(),
                Sequencex.joinToString(Sequencex.dropWhile(sequence1) { !it.startsWith("e") }))


        assertTwoEquals("dj",
                sequence0.dropWhile { !it.startsWith("b") }.drop(2).joinToString(),
                Sequencex.joinToString(Sequencex.drop(Sequencex.dropWhile(sequence1) { !it.startsWith("b") }, 2)))
    }

    @Test
    fun testTake() {
        val sequence0 = sequenceOf("aj", "bj", "cj", "dj")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "dj")

        /*
         * take 系列的方法表示从列表头部开始取部分元素
         */

        // take 方法的意思是从列表头部开始取多少个元素

        assertTwoThrow(IllegalArgumentException::class,
                { sequence0.take(-1) },
                { Sequencex.take(sequence1, -1) }
        )

        assertTrue(emptySequence<String>() === sequence0.take(0))
        assertTrue(Sequencex.emptySequence<String>() === Sequencex.take(sequence1, 0))

        assertTwoEquals("aj",
                sequence0.take(1).joinToString(),
                Sequencex.joinToString(Sequencex.take(sequence1, 1)))

        assertTwoEquals("aj, bj",
                sequence0.take(2).joinToString(),
                Sequencex.joinToString(Sequencex.take(sequence1, 2)))

        assertTwoEquals("aj, bj, cj",
                sequence0.take(3).joinToString(),
                Sequencex.joinToString(Sequencex.take(sequence1, 3)))

        assertTwoEquals("aj, bj, cj, dj",
                sequence0.take(4).joinToString(),
                Sequencex.joinToString(Sequencex.take(sequence1, 4)))

        assertTwoEquals("aj, bj, cj, dj",
                sequence0.take(5).joinToString(),
                Sequencex.joinToString(Sequencex.take(sequence1, 5)))

        assertTwoEquals("aj, bj, cj, dj",
                sequence0.take(5).joinToString(),
                Sequencex.joinToString(Sequencex.take(Sequencex.take(sequence1, 5), 5)))

        // takeWhile 方法的意思是从列表头部开始到不符合条件的元素（不含包）终止

        assertTwoEquals("",
                sequence0.takeWhile { !it.startsWith("a") }.joinToString(),
                Sequencex.joinToString(Sequencex.takeWhile(sequence1) { !it.startsWith("a") }))

        assertTwoEquals("aj",
                sequence0.takeWhile { !it.startsWith("b") }.joinToString(),
                Sequencex.joinToString(Sequencex.takeWhile(sequence1) { !it.startsWith("b") }))

        assertTwoEquals("aj, bj",
                sequence0.takeWhile { !it.startsWith("c") }.joinToString(),
                Sequencex.joinToString(Sequencex.takeWhile(sequence1) { !it.startsWith("c") }))

        assertTwoEquals("aj, bj, cj",
                sequence0.takeWhile { !it.startsWith("d") }.joinToString(),
                Sequencex.joinToString(Sequencex.takeWhile(sequence1) { !it.startsWith("d") }))

        assertTwoEquals("aj, bj, cj, dj",
                sequence0.takeWhile { !it.startsWith("e") }.joinToString(),
                Sequencex.joinToString(Sequencex.takeWhile(sequence1) { !it.startsWith("e") }))


        assertTwoEquals("aj, bj",
                sequence0.takeWhile { !it.startsWith("d") }.take(2).joinToString(),
                Sequencex.joinToString(Sequencex.take(Sequencex.takeWhile(sequence1) { !it.startsWith("d") }, 2)))
    }

    @Test
    @Suppress("RedundantAsSequence")
    fun testFilter() {
        val sequence0 = sequenceOf("aj", "bo", "cj", "do")
        val sequence1 = Sequencex.sequenceOf("aj", "bo", "cj", "do")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals("aj, cj",
                sequence0.filter { it.endsWith("j") }.joinToString(),
                Sequencex.joinToString(Sequencex.filter(sequence1) { it.endsWith("j") }))

        val filterToDestination = ArrayList<String>()
        val filterToDestination1 = ArrayList<String>()
        val filterToDestinationNull1 = ArrayList<String>()
        val filterToDestinationResult = sequence0.filterTo(filterToDestination) { it.endsWith("j") }
        val filterToDestinationResult1 = Sequencex.filterTo(sequence1, filterToDestination1) { it.endsWith("j") }
        val filterToDestinationNullResult1 = Sequencex.filterTo(nullSequence1, filterToDestinationNull1) { it.endsWith("j") }
        assertTwoEquals("aj, cj", filterToDestinationResult.joinToString(), filterToDestinationResult1.joinToString())
        assertTrue(filterToDestination === filterToDestinationResult)
        assertTrue(filterToDestination1 === filterToDestinationResult1)
        assertEquals("", filterToDestinationNullResult1.joinToString())
        assertTrue(filterToDestinationNull1 === filterToDestinationNullResult1)

        assertTwoEquals("bo, do",
                sequence0.filterIndexed { index, _ -> (index % 2) != 0 }.joinToString(),
                Sequencex.joinToString(Sequencex.filterIndexed(sequence1) { index, _ -> (index % 2) != 0 }))

        val filterIndexedToDestination = ArrayList<String>()
        val filterIndexedToDestination1 = ArrayList<String>()
        val filterIndexedToDestinationResult = sequence0.filterIndexedTo(filterIndexedToDestination) { index, _ -> (index % 2) != 0 }
        val filterIndexedToDestinationResult1 = Sequencex.filterIndexedTo(sequence1, filterIndexedToDestination1) { index, _ -> (index % 2) != 0 }
        assertTwoEquals("bo, do", filterIndexedToDestinationResult.joinToString(), filterIndexedToDestinationResult1.joinToString())
        assertTrue(filterIndexedToDestination === filterIndexedToDestinationResult)
        assertTrue(filterIndexedToDestination1 === filterIndexedToDestinationResult1)


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
                sequence0.filterNot { it.endsWith("j") }.joinToString(),
                Sequencex.joinToString(Sequencex.filterNot(sequence1) { it.endsWith("j") }))

        val filterNotToDestination = ArrayList<String>()
        val filterNotToDestination1 = ArrayList<String>()
        val filterNotToDestinationNull1 = ArrayList<String>()
        val filterNotToDestinationResult = sequence0.filterNotTo(filterNotToDestination) { it.endsWith("j") }
        val filterNotToDestinationResult1 = Sequencex.filterNotTo(sequence1, filterNotToDestination1) { it.endsWith("j") }
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
        val sequence0 = sequenceOf("aaa", "h", "uuuu", "gg")
        val sequence1 = Sequencex.sequenceOf("aaa", "h", "uuuu", "gg")
        val nullableSequence0 = sequenceOf("aaa", null, null, "gg", null)
        val nullableSequence1 = Sequencex.sequenceOf("aaa", null, null, "gg", null)

        assertTwoEquals("aaa, gg, h, uuuu",
                sequence0.sorted().joinToString(),
                Sequencex.joinToString(Sequencex.sorted(sequence1)))
        assertThrow(NullPointerException::class) { Sequencex.joinToString(Sequencex.sorted(nullableSequence1)) }

        assertTwoEquals("uuuu, h, gg, aaa",
                sequence0.sortedDescending().joinToString(),
                Sequencex.joinToString(Sequencex.sortedDescending(sequence1)))
        assertThrow(NullPointerException::class) { Sequencex.joinToString(Sequencex.sortedDescending(nullableSequence1)) }

        assertTwoEquals("h, gg, aaa, uuuu",
                sequence0.sortedBy { it.length }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedBy(sequence1) { it.length }))
        assertTwoEquals("null, null, null, gg, aaa",
                nullableSequence0.sortedBy { it?.length ?: 0 }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedBy(nullableSequence1) { it.length }))

        assertTwoEquals("uuuu, aaa, gg, h",
                sequence0.sortedByDescending { it.length }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedByDescending(sequence1) { it.length }))
        assertTwoEquals("aaa, gg, null, null, null",
                nullableSequence0.sortedByDescending { it?.length ?: 0 }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedByDescending(nullableSequence1) { it.length }))

        assertTwoEquals("aaa, gg, h, uuuu",
                sequence0.sortedWith { it1, it2 -> it1.compareTo(it2) }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedWith(sequence1) { it1, it2 -> it1.compareTo(it2) }))
        assertTwoEquals("null, null, null, aaa, gg",
                nullableSequence0.sortedWith { it1, it2 -> it1.orEmpty().compareTo(it2.orEmpty()) }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedWith(nullableSequence1) { it1, it2 -> it1.orEmpty().compareTo(it2.orEmpty()) }))
    }

    @Test
    @Suppress("ReplaceAssociateFunction")
    fun testAssociate() {
        val sequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                sequence0.associate { it.first().toString() to it },
                Sequencex.associate(sequence1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) },
        )

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                sequence0.associateBy { it.first().toString() },
                Sequencex.associateBy(sequence1) { it.first().toString() },
        )

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                sequence0.associateBy({ it.first().toString() }, { it }),
                Sequencex.associateBy(sequence1, { it.first().toString() }, { it }),
        )

        val associateToMap0 = HashMap<String, String>()
        val associateToMap1 = HashMap<String, String>()
        val associateToMapNull1 = HashMap<String, String>()
        val associateToMapResult0 = sequence0.associateTo(associateToMap0) { it.first().toString() to it }
        val associateToMapResult1 = Sequencex.associateTo(sequence1, associateToMap1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) }
        val associateToMapResultNull1 = Sequencex.associateTo(nullSequence1, associateToMapNull1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) }
        assertTwoEquals(mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"), associateToMap0, associateToMap1)
        assertTrue(associateToMap0 === associateToMapResult0)
        assertTrue(associateToMap1 === associateToMapResult1)
        assertEquals(mapOf<String, String>(), associateToMapResultNull1)
        assertTrue(associateToMapNull1 === associateToMapResultNull1)

        val associateByTo1Map0 = HashMap<String, String>()
        val associateByTo1Map1 = HashMap<String, String>()
        val associateByTo1MapNull1 = HashMap<String, String>()
        val associateByTo1MapResult0 = sequence0.associateByTo(associateByTo1Map0) { it.first().toString() }
        val associateByTo1MapResult1 = Sequencex.associateByTo(sequence1, associateByTo1Map1) { it.first().toString() }
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
        val associateByTo2MapResult0 = sequence0.associateByTo(associateByTo2Map0, { it.first().toString() }, { it })
        val associateByTo2MapResult1 = Sequencex.associateByTo(sequence1, associateByTo2Map1, { it.first().toString() }, { it })
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
        val sequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")
        val nullSequence1 = null as Sequence<Pair2<Int, String>>?

        assertTwoEquals(
                listOf("aj", "bj", "ao", "bo"),
                sequence0.toCollection(LinkedList()),
                Sequencex.toCollection(sequence1, LinkedList()),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                sequence0.toCollection(LinkedList()) is LinkedList,
                Sequencex.toCollection(sequence1, LinkedList()) is LinkedList
        )
        assertEquals(listOf<String>(), Sequencex.toCollection(nullSequence1, LinkedList()))

        assertTwoEquals(
                listOf("aj", "bj", "ao", "bo"),
                sequence0.toMutableList(),
                Sequencex.toMutableList(sequence1),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                sequence0.toMutableList() is ArrayList,
                Sequencex.toMutableList(sequence1) is ArrayList
        )

        assertTwoEquals(
                linkedSetOf("aj", "bj", "ao", "bo"),
                sequence0.toMutableSet(),
                Sequencex.toMutableSet(sequence1),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                sequence0.toMutableSet() is LinkedHashSet,
                Sequencex.toMutableSet(sequence1) is LinkedHashSet
        )
        assertEquals(linkedSetOf<String>(), Sequencex.toMutableSet(nullSequence1))

        assertTwoEquals(
                hashSetOf("aj", "bj", "ao", "bo"),
                sequence0.toHashSet(),
                Sequencex.toHashSet(sequence1),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                sequence0.toHashSet() is HashSet,
                Sequencex.toHashSet(sequence1) is HashSet
        )

        assertTwoEquals(
                sortedSetOf("aj", "ao", "bj", "bo"),
                sequence0.toSortedSet(),
                Sequencex.toSortedSet(sequence1),
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                sequence0.toSortedSet() is TreeSet,
                Sequencex.toSortedSet(sequence1) is TreeSet
        )

        assertTwoEquals(
                sortedSetOf("bo", "bj", "ao", "aj"),
                sequence0.toSortedSet { o1, o2 -> o1.compareTo(o2) * -1 },
                Sequencex.toSortedSet(sequence1) { o1, o2 -> o1.compareTo(o2) * -1 },
        )
        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(true,
                sequence0.toSortedSet { o1, o2 -> o1.compareTo(o2) * -1 } is TreeSet,
                Sequencex.toSortedSet(sequence1) { o1, o2 -> o1.compareTo(o2) * -1 } is TreeSet
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
        val sequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")
        val nullSequence1 = null as Sequence<String>?

        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "a, j, b, j, a, o, b, o",
                sequence0.flatMap { it -> it.toCharArray().map { it.toString() } }.joinToString(),
                Sequencex.joinToString(Sequencex.flatMapOfIterable(sequence1) { it -> it.toCharArray().map { it.toString() } }),
        )

        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "a, j, b, j, a, o, b, o",
                sequence0.flatMap { it -> it.toCharArray().asSequence().map { it.toString() } }.joinToString(),
                Sequencex.joinToString(Sequencex.flatMap(sequence1) { it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { it.toString() } }),
        )

        val flatMapToList0 = ArrayList<String>()
        val flatMapToList1 = ArrayList<String>()
        val flatMapToListNull1 = ArrayList<String>()
        val flatMapToListResult0 = sequence0.flatMapTo(flatMapToList0) { it -> it.toCharArray().map { it.toString() } }
        val flatMapToListResult1 = Sequencex.flatMapOfIterableTo(sequence1, flatMapToList1) { it -> it.toCharArray().map { it.toString() } }
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
        val flatMapToListResult2 = sequence0.flatMapTo(flatMapToList2) { it -> it.toCharArray().asSequence().map { it.toString() } }
        val flatMapToListResult3 = Sequencex.flatMapTo(sequence1, flatMapToList3) { it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { it.toString() } }
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
                sequence0.flatMapIndexed { index, it -> it.toCharArray().map { "${index}-$it" } }.joinToString(),
                Sequencex.joinToString(Sequencex.flatMapIndexedOfIterable(sequence1) { index, it -> it.toCharArray().map { "${index}-$it" } }),
        )

        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "0-a, 0-j, 1-b, 1-j, 2-a, 2-o, 3-b, 3-o",
                sequence0.flatMapIndexed { index, it -> it.toCharArray().asSequence().map { "${index}-$it" } }.joinToString(),
                Sequencex.joinToString(Sequencex.flatMapIndexed(sequence1) { index, it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { "${index}-$it" } }),
        )

        val flatMapIndexedToList0 = ArrayList<String>()
        val flatMapIndexedToList1 = ArrayList<String>()
        val flatMapIndexedToListNull1 = ArrayList<String>()
        val flatMapIndexedToListResult0 = sequence0.flatMapIndexedTo(flatMapIndexedToList0) { index, it -> it.toCharArray().map { "${index}-$it" } }
        val flatMapIndexedToListResult1 = Sequencex.flatMapIndexedOfIterableTo(sequence1, flatMapIndexedToList1) { index, it -> it.toCharArray().map { "${index}-$it" } }
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
        val flatMapIndexedToListResult2 = sequence0.flatMapIndexedTo(flatMapIndexedToList2) { index, it -> it.toCharArray().asSequence().map { "${index}-$it" } }
        val flatMapIndexedToListResult3 = Sequencex.flatMapIndexedTo(sequence1, flatMapIndexedToList3) { index, it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { "${index}-$it" } }
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
        val sequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(
                "{a=[aj, ao], b=[bj, bo]}",
                sequence0.groupBy { it.first() }.toString(),
                Sequencex.groupBy(sequence1) { it.first() }.toString(),
        )

        assertTwoEquals(
                "{a=[j, o], b=[j, o]}",
                sequence0.groupBy({ it.first() }, { it.last() }).toString(),
                Sequencex.groupBy(sequence1, { it.first() }, { it.last() }).toString(),
        )

        val groupByToMap0 = HashMap<Char, MutableList<String>>()
        val groupByToMap1 = HashMap<Char, List<String>>()
        val groupByToMapNull1 = HashMap<Char, List<String>>()
        val groupByToMapResult0 = sequence0.groupByTo(groupByToMap0) { it.first() }
        val groupByToMapResult1 = Sequencex.groupByTo(sequence1, groupByToMap1) { it.first() }
        val groupByToMapResultNull1 = Sequencex.groupByTo(nullSequence1, groupByToMapNull1) { it.first() }
        assertTwoEquals("{a=[aj, ao], b=[bj, bo]}", groupByToMap0.toString(), groupByToMap1.toString())
        assertTrue(groupByToMap0 === groupByToMapResult0)
        assertTrue(groupByToMap1 === groupByToMapResult1)
        assertEquals("{}", groupByToMapNull1.toString())
        assertTrue(groupByToMapNull1 === groupByToMapResultNull1)

        val groupByToMap2 = HashMap<Char, MutableList<Char>>()
        val groupByToMap3 = HashMap<Char, List<Char>>()
        val groupByToMapNull3 = HashMap<Char, List<Char>>()
        val groupByToMapResult2 = sequence0.groupByTo(groupByToMap2, { it.first() }, { it.last() })
        val groupByToMapResult3 = Sequencex.groupByTo(sequence1, groupByToMap3, { it.first() }, { it.last() })
        val groupByToMapResultNull3 = Sequencex.groupByTo(nullSequence1, groupByToMapNull3, { it.first() }, { it.last() })
        assertTwoEquals("{a=[j, o], b=[j, o]}", groupByToMap2.toString(), groupByToMap3.toString())
        assertTrue(groupByToMap2 === groupByToMapResult2)
        assertTrue(groupByToMap3 === groupByToMapResult3)
        assertEquals("{}", groupByToMapNull3.toString())
        assertTrue(groupByToMapNull3 === groupByToMapResultNull3)
    }

    @Test
    fun testMap() {
        val sequence0 = sequenceOf("aj", "bj", "ao", "cc", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "cc", "bo")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(
                "a, b, a, c, b",
                sequence0.map { it.first() }.joinToString(),
                Sequencex.joinToString(Sequencex.map(sequence1) { it.first() }),
        )

        assertTwoEquals(
                "a, b, a, b",
                sequence0.mapNotNull { if (it != "cc") it.first() else null }.joinToString(),
                Sequencex.joinToString(Sequencex.mapNotNull(sequence1) { if (it != "cc") it.first() else null }),
        )

        assertTwoEquals(
                "0:a, 1:b, 2:a, 3:c, 4:b",
                sequence0.mapIndexed { index, s -> "$index:${s.first()}" }.joinToString(),
                Sequencex.joinToString(Sequencex.mapIndexed(sequence1) { index, s -> "$index:${s.first()}" }),
        )

        assertTwoEquals(
                "0:a, 1:b, 2:a, 4:b",
                sequence0.mapIndexedNotNull { index, s -> if (s != "cc") "$index:${s.first()}" else null }.joinToString(),
                Sequencex.joinToString(Sequencex.mapIndexedNotNull(sequence1) { index, s -> if (s != "cc") "$index:${s.first()}" else null }),
        )

        val mapToList0 = ArrayList<Char>()
        val mapToList1 = ArrayList<Char>()
        val mapToListNull1 = ArrayList<Char>()
        val mapToListResult0 = sequence0.mapTo(mapToList0) { it.first() }
        val mapToListResult1 = Sequencex.mapTo(sequence1, mapToList1) { it.first() }
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
        val mapNotNullToListResult0 = sequence0.mapNotNullTo(mapNotNullToList0) { if (it != "cc") it.first() else null }
        val mapNotNullToListResult1 = Sequencex.mapNotNullTo(sequence1, mapNotNullToList1) { if (it != "cc") it.first() else null }
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
        val mapIndexedToListResult0 = sequence0.mapIndexedTo(mapIndexedToList0) { index, s -> "$index:${s.first()}" }
        val mapIndexedToListResult1 = Sequencex.mapIndexedTo(sequence1, mapIndexedToList1) { index, s -> "$index:${s.first()}" }
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
        val mapIndexedNotNullToListResult0 = sequence0.mapIndexedNotNullTo(mapIndexedNotNullToList0) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
        val mapIndexedNotNullToListResult1 = Sequencex.mapIndexedNotNullTo(sequence1, mapIndexedNotNullToList1) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
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
        val sequence0 = sequenceOf("aj", "bj", "ao", "cc", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "cc", "bo")

        assertTwoEquals(
                "0:aj, 1:bj, 2:ao, 3:cc, 4:bo",
                sequence0.withIndex().joinToString { "${it.index}:${it.value}" },
                Sequencex.joinToString(Sequencex.withIndex(sequence1)) { "${it.index}:${it.value}" },
        )

        val iterator0 = sequence0.withIndex().iterator()
        if (iterator0 is MutableIterator) {
            assertThrow(UnsupportedOperationException::class) { iterator0.remove() }
        }
        assertThrow(UnsupportedOperationException::class) { Sequencex.withIndex(sequence1).iterator().remove() }
    }

    @Test
    fun testDistinct() {
        val sequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")

        assertTwoEquals(
                "aj, bj, bo",
                sequence0.distinct().joinToString(),
                Sequencex.joinToString(Sequencex.distinct(sequence1)),
        )

        assertTwoEquals(
                "aj, bo",
                sequence0.distinctBy { it.last() }.joinToString(),
                Sequencex.joinToString(Sequencex.distinctBy(sequence1) { it.last() }),
        )
    }

    @Test
    fun testAll() {
        val sequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(
                true,
                sequence0.all { it -> it.all { it.isLetter() } },
                Sequencex.all(sequence1) { it -> it.all { it.isLetter() } },
        )

        assertTwoEquals(
                true,
                emptySequence0.all { it -> it.all { it.isLetter() } },
                Sequencex.all(emptySequence1) { it -> it.all { it.isLetter() } },
        )

        assertTrue(Sequencex.all(nullSequence1) { it.last() == 'j' })

        assertTwoEquals(
                false,
                sequence0.all { it.last() == 'j' },
                Sequencex.all(sequence1) { it.last() == 'j' },
        )
    }

    @Test
    fun testAny() {
        val sequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(true, sequence0.any(), Sequencex.any(sequence1))
        assertTwoEquals(false, emptySequence0.any(), Sequencex.any(emptySequence1))
        assertFalse(Sequencex.any(nullSequence1))
        assertTwoEquals(
                false,
                sequenceOf<String>().any(),
                Sequencex.any(Sequencex.sequenceOf<String>()),
        )

        assertTwoEquals(true, sequence0.any { it.last() == 'j' }, Sequencex.any(sequence1) { it.last() == 'j' })
        assertTwoEquals(false, emptySequence0.any { it.last() == 'j' }, Sequencex.any(emptySequence1) { it.last() == 'j' })
        assertFalse(Sequencex.any(nullSequence1) { it -> it.all { it.isDigit() } })
        assertTwoEquals(
                false,
                sequence0.any { it -> it.all { it.isDigit() } },
                Sequencex.any(sequence1) { it -> it.all { it.isDigit() } }
        )
    }

    @Test
    fun testCount() {
        val sequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")

        assertTwoEquals(
                5,
                sequence0.count(),
                Sequencex.count(sequence1),
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
                sequence0.count { it.last() == 'j' },
                Sequencex.count(sequence1) { it.last() == 'j' },
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
        val sequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")
        val nullSequence1 = null as Sequence<String>?

        assertTwoEquals(
                "^ajbjajbjbo",
                sequence0.fold("^") { r, t -> r + t },
                Sequencex.fold(sequence1, "^") { r, t -> r + t },
        )
        assertEquals(
                "^",
                Sequencex.fold(nullSequence1, "^") { r, t -> r + t },
        )

        assertTwoEquals(
                "^0aj1bj2aj3bj4bo",
                sequence0.foldIndexed("^") { i, r, t -> r + i.toString() + t },
                Sequencex.foldIndexed(sequence1, "^") { i, r, t -> r + i.toString() + t },
        )
        assertEquals(
                "^",
                Sequencex.foldIndexed(nullSequence1, "^") { i, r, t -> r + i.toString() + t },
        )
    }

    @Test
    fun testEach() {
        val sequence0 = sequenceOf("aj", "bj", "aj", "bj", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "aj", "bj", "bo")

        assertTwoEquals("aj, bj, aj, bj, bo",
                ArrayList<String>().apply { sequence0.forEach { add(it) } }.joinToString(),
                ArrayList<String>().apply { Sequencex.forEach(sequence1) { add(it) } }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { sequenceOf<String>().forEach { add(it) } }.joinToString(),
                ArrayList<String>().apply { Sequencex.forEach(null as Sequence<String>?) { add(it) } }.joinToString())

        assertTwoEquals("0aj, 1bj, 2aj, 3bj, 4bo",
                ArrayList<String>().apply { sequence0.forEachIndexed { i, it -> add(i.toString() + it) } }.joinToString(),
                ArrayList<String>().apply { Sequencex.forEachIndexed(sequence1) { i, it -> add(i.toString() + it) } }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { sequenceOf<String>().forEachIndexed { i, it -> add(i.toString() + it) } }.joinToString(),
                ArrayList<String>().apply { Sequencex.forEachIndexed(null as Sequence<String>?) { i, it -> add(i.toString() + it) } }.joinToString())


        assertTwoEquals("aj, bj, aj, bj, bo",
                ArrayList<String>().apply { sequence0.onEach { add(it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Sequencex.toMutableList(Sequencex.onEach(sequence1) { add(it) }) }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { sequenceOf<String>().onEach { add(it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Sequencex.toMutableList(Sequencex.onEach(null as Sequence<String>?) { add(it) }) }.joinToString())

        assertTwoEquals("0aj, 1bj, 2aj, 3bj, 4bo",
                ArrayList<String>().apply { sequence0.onEachIndexed { i, it -> add(i.toString() + it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Sequencex.toMutableList(Sequencex.onEachIndexed(sequence1) { i, it -> add(i.toString() + it) }) }.joinToString())

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

        val sequence0 = sequenceOf("6", "3", "7", "2", "1")
        val sequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence0: Sequence<String>? = null
        assertTwoEquals("7", sequence0.maxOrNull(), Sequencex.maxOrNull(sequence1))
        assertTwoEquals(null, emptySequence0.maxOrNull(), Sequencex.maxOrNull(emptySequence1))
        assertTwoEquals(null, null, Sequencex.maxOrNull(nullSequence0))

        assertTwoEquals("7", sequence0.maxByOrNull { it.toInt() }, Sequencex.maxByOrNull(sequence1) { it.toInt() })
        assertTwoEquals(null, emptySequence0.maxByOrNull { it.toInt() }, Sequencex.maxByOrNull(emptySequence1) { it.toInt() })
        assertTwoEquals(null, null, Sequencex.maxByOrNull(nullSequence0) { it.toInt() })

        assertTwoEquals("1",
                sequence0.maxWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Sequencex.maxWithOrNull(sequence1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                emptySequence0.maxWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Sequencex.maxWithOrNull(emptySequence1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                null,
                Sequencex.maxWithOrNull(nullSequence0) { it0, it1 -> it0.compareTo(it1) * -1 })

        assertTwoEquals(7.0,
                sequence0.maxOf { it.toDouble() },
                Sequencex.maxOfDouble(sequence1) { it.toDouble() })
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.maxOf { it.toDouble() } },
                { Sequencex.maxOfDouble(emptySequence1) { it.toDouble() } })
        assertThrow(NoSuchElementException::class) { Sequencex.maxOfDouble(nullSequence0) { it.toDouble() } }

        assertTwoEquals(7.0f,
                sequence0.maxOf { it.toFloat() },
                Sequencex.maxOfFloat(sequence1) { it.toFloat() })
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.maxOf { it.toFloat() } },
                { Sequencex.maxOfFloat(emptySequence1) { it.toFloat() } })
        assertThrow(NoSuchElementException::class) { Sequencex.maxOfFloat(nullSequence0) { it.toFloat() } }

        assertTwoEquals(7.0, sequence0.maxOfOrNull { it.toDouble() }, Sequencex.maxOfDoubleOrNull(sequence1) { it.toDouble() })
        assertTwoEquals(null, emptySequence0.maxOfOrNull { it.toDouble() }, Sequencex.maxOfDoubleOrNull(emptySequence1) { it.toDouble() })
        assertTwoEquals(null, null, Sequencex.maxOfDoubleOrNull(nullSequence0) { it.toDouble() })

        assertTwoEquals(7.0f, sequence0.maxOfOrNull { it.toFloat() }, Sequencex.maxOfFloatOrNull(sequence1) { it.toFloat() })
        assertTwoEquals(null, emptySequence0.maxOfOrNull { it.toFloat() }, Sequencex.maxOfFloatOrNull(emptySequence1) { it.toFloat() })
        assertTwoEquals(null, null, Sequencex.maxOfFloatOrNull(nullSequence0) { it.toFloat() })

        assertTwoEquals("7", sequence0.maxOf { it }, Sequencex.maxOf(sequence1) { it })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.maxOf { it } }, { Sequencex.maxOf(emptySequence1) { it } })
        assertThrow(NoSuchElementException::class) { Sequencex.maxOf(nullSequence0) { it } }

        assertTwoEquals("7", sequence0.maxOfOrNull { it }, Sequencex.maxOfOrNull(sequence1) { it })
        assertTwoEquals(null, emptySequence0.maxOfOrNull { it }, Sequencex.maxOfOrNull(emptySequence1) { it })
        assertTwoEquals(null, null, Sequencex.maxOfOrNull(nullSequence0) { it })

        assertTwoEquals("1",
                sequence0.maxOfWith({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.maxOfWith(sequence1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.maxOfWith(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) },
                { Sequencex.maxOfWith(emptySequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }
        )
        assertThrow(NoSuchElementException::class) { Sequencex.maxOfWith(nullSequence0, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }

        assertTwoEquals("1",
                sequence0.maxOfWithOrNull({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.maxOfWithOrNull(sequence1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoEquals(null,
                emptySequence0.maxOfWithOrNull(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.maxOfWithOrNull(emptySequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it })
        )
        assertTwoEquals(null, null, Sequencex.maxOfWithOrNull(nullSequence0, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
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

        val sequence0 = sequenceOf("6", "3", "7", "2", "1")
        val sequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence0: Sequence<String>? = null
        assertTwoEquals("1", sequence0.minOrNull(), Sequencex.minOrNull(sequence1))
        assertTwoEquals(null, emptySequence0.minOrNull(), Sequencex.minOrNull(emptySequence1))
        assertTwoEquals(null, null, Sequencex.minOrNull(nullSequence0))

        assertTwoEquals("1", sequence0.minByOrNull { it.toInt() }, Sequencex.minByOrNull(sequence1) { it.toInt() })
        assertTwoEquals(null, emptySequence0.minByOrNull { it.toInt() }, Sequencex.minByOrNull(emptySequence1) { it.toInt() })
        assertTwoEquals(null, null, Sequencex.minByOrNull(nullSequence0) { it.toInt() })

        assertTwoEquals("7",
                sequence0.minWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Sequencex.minWithOrNull(sequence1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                emptySequence0.minWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Sequencex.minWithOrNull(emptySequence1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                null,
                Sequencex.minWithOrNull(nullSequence0) { it0, it1 -> it0.compareTo(it1) * -1 })

        assertTwoEquals(1.0,
                sequence0.minOf { it.toDouble() },
                Sequencex.minOfDouble(sequence1) { it.toDouble() })
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.minOf { it.toDouble() } },
                { Sequencex.minOfDouble(emptySequence1) { it.toDouble() } })
        assertThrow(NoSuchElementException::class) { Sequencex.minOfDouble(nullSequence0) { it.toDouble() } }

        assertTwoEquals(1.0f,
                sequence0.minOf { it.toFloat() },
                Sequencex.minOfFloat(sequence1) { it.toFloat() })
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.minOf { it.toFloat() } },
                { Sequencex.minOfFloat(emptySequence1) { it.toFloat() } })
        assertThrow(NoSuchElementException::class) { Sequencex.minOfFloat(nullSequence0) { it.toFloat() } }

        assertTwoEquals(1.0, sequence0.minOfOrNull { it.toDouble() }, Sequencex.minOfDoubleOrNull(sequence1) { it.toDouble() })
        assertTwoEquals(null, emptySequence0.minOfOrNull { it.toDouble() }, Sequencex.minOfDoubleOrNull(emptySequence1) { it.toDouble() })
        assertTwoEquals(null, null, Sequencex.minOfDoubleOrNull(nullSequence0) { it.toDouble() })

        assertTwoEquals(1.0f, sequence0.minOfOrNull { it.toFloat() }, Sequencex.minOfFloatOrNull(sequence1) { it.toFloat() })
        assertTwoEquals(null, emptySequence0.minOfOrNull { it.toFloat() }, Sequencex.minOfFloatOrNull(emptySequence1) { it.toFloat() })
        assertTwoEquals(null, null, Sequencex.minOfFloatOrNull(nullSequence0) { it.toFloat() })

        assertTwoEquals("1", sequence0.minOf { it }, Sequencex.minOf(sequence1) { it })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.minOf { it } }, { Sequencex.minOf(emptySequence1) { it } })
        assertThrow(NoSuchElementException::class) { Sequencex.minOf(nullSequence0) { it } }

        assertTwoEquals("1", sequence0.minOfOrNull { it }, Sequencex.minOfOrNull(sequence1) { it })
        assertTwoEquals(null, emptySequence0.minOfOrNull { it }, Sequencex.minOfOrNull(emptySequence1) { it })
        assertTwoEquals(null, null, Sequencex.minOfOrNull(nullSequence0) { it })

        assertTwoEquals("7",
                sequence0.minOfWith({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.minOfWith(sequence1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.minOfWith(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) },
                { Sequencex.minOfWith(emptySequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }
        )
        assertThrow(NoSuchElementException::class) { Sequencex.minOfWith(nullSequence0, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }

        assertTwoEquals("7",
                sequence0.minOfWithOrNull({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.minOfWithOrNull(sequence1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoEquals(null,
                emptySequence0.minOfWithOrNull(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Sequencex.minOfWithOrNull(emptySequence1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it })
        )
        assertTwoEquals(null, null, Sequencex.minOfWithOrNull(nullSequence0, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
    }

    @Test
    fun testNone() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence0: Sequence<String>? = null

        assertTwoEquals(false, normalSequence0.none(), Sequencex.none(normalSequence1))
        assertTwoEquals(true, emptySequence0.none(), Sequencex.none(emptySequence1))
        assertTrue(Sequencex.none(nullSequence0))

        assertTwoEquals(true, normalSequence0.none { it.length > 1 }, Sequencex.none(normalSequence1) { it.length > 1 })
        assertTwoEquals(false, normalSequence0.none { it.isNotEmpty() }, Sequencex.none(normalSequence1) { it.isNotEmpty() })
        assertTwoEquals(true, emptySequence0.none { it.length > 1 }, Sequencex.none(emptySequence1) { it.length > 1 })
        assertTrue(Sequencex.none(nullSequence0) { it.length > 1 })
    }

    @Test
    fun testReduce() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence0: Sequence<String>? = null

        assertTwoEquals("63721",
                normalSequence0.reduce { it0, it1 -> it0 + it1 },
                Sequencex.reduce(normalSequence1) { it0, it1 -> it0 + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptySequence0.reduce { it0, it1 -> it0 + it1 } },
                { Sequencex.reduce(emptySequence1) { it0, it1 -> it0 + it1 } })
        assertThrow(UnsupportedOperationException::class)
        { Sequencex.reduce(nullSequence0) { it0, it1 -> it0 + it1 } }

        assertTwoEquals("613273241",
                normalSequence0.reduceIndexed { i, it0, it1 -> it0 + i + it1 },
                Sequencex.reduceIndexed(normalSequence1) { i, it0, it1 -> it0 + i + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptySequence0.reduceIndexed { i, it0, it1 -> it0 + i + it1 } },
                { Sequencex.reduceIndexed(emptySequence1) { i, it0, it1 -> it0 + i + it1 } })
        assertThrow(UnsupportedOperationException::class)
        { Sequencex.reduceIndexed(nullSequence0) { i, it0, it1 -> it0 + i + it1 } }
    }

    @Test
    fun testSum() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence0: Sequence<String>? = null
        assertTwoEquals(19, normalSequence0.sumBy { it.toInt() }, Sequencex.sumBy(normalSequence1) { it.toInt() })
        assertTwoEquals(0, emptySequence0.sumBy { it.toInt() }, Sequencex.sumBy(emptySequence1) { it.toInt() })
        assertEquals(0, Sequencex.sumBy(nullSequence0) { it.toInt() })
        assertTwoEquals(19.0, normalSequence0.sumByDouble { it.toDouble() }, Sequencex.sumByDouble(normalSequence1) { it.toDouble() })
        assertTwoEquals(0.0, emptySequence0.sumByDouble { it.toDouble() }, Sequencex.sumByDouble(emptySequence1) { it.toDouble() })
        assertTwoEquals(0.0, 0.0, Sequencex.sumByDouble(nullSequence0) { it.toDouble() })

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
        val nullSequence0: Sequence<String>? = null

        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.requireNoNulls().joinToString(),
                Sequencex.joinToString(Sequencex.requireNoNulls(normalSequence1)))
        assertTwoThrow(IllegalArgumentException::class,
                { nullableSequence0.requireNoNulls().joinToString() },
                { Sequencex.joinToString(Sequencex.requireNoNulls(nullableSequence1)) })
        assertTwoEquals("",
                emptySequence0.requireNoNulls().joinToString(),
                Sequencex.joinToString(Sequencex.requireNoNulls(emptySequence1)))
        assertEquals("", Sequencex.joinToString(Sequencex.requireNoNulls(nullSequence0)))
    }

    @Test
    fun testMinus() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence0: Sequence<String>? = null

        assertTwoEquals("6, 7, 2, 1",
                normalSequence0.minus("3").joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, "3")))
        assertTwoEquals("",
                emptySequence0.minus("3").joinToString(),
                Sequencex.joinToString(Sequencex.minus(emptySequence1, "3")))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence0, "3")))

        assertTwoEquals("6, 7, 2",
                normalSequence0.minus(arrayOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, arrayOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.minus(arrayOf()).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, arrayOf<String>())))
        assertTwoEquals("",
                emptySequence0.minus(arrayOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(emptySequence1, arrayOf("3", "1"))))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence0, arrayOf("3", "1"))))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence0, arrayOf<String>())))

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
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence0, listOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.minus(listOf()).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, listOf<String>())))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence0, listOf<String>())))

        assertTwoEquals("6, 7, 2",
                normalSequence0.minus(sequenceOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, Sequencex.sequenceOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.minus(sequenceOf()).joinToString(),
                Sequencex.joinToString(Sequencex.minus(normalSequence1, Sequencex.sequenceOf())))
        assertTwoEquals("",
                emptySequence0.minus(sequenceOf("3", "1")).joinToString(),
                Sequencex.joinToString(Sequencex.minus(emptySequence1, Sequencex.sequenceOf("3", "1"))))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence0, Sequencex.sequenceOf("3", "1"))))
        assertEquals("", Sequencex.joinToString(Sequencex.minus(nullSequence0, Sequencex.sequenceOf())))

        assertTwoEquals("6, 7, 2, 1",
                normalSequence0.minusElement("3").joinToString(),
                Sequencex.joinToString(Sequencex.minusElement(normalSequence1, "3")))
        assertTwoEquals("",
                emptySequence0.minusElement("3").joinToString(),
                Sequencex.joinToString(Sequencex.minusElement(emptySequence1, "3")))
        assertEquals("", Sequencex.joinToString(Sequencex.minusElement(nullSequence0, "3")))
    }

    @Test
    fun testPartition() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence0: Sequence<String>? = null

        assertTwoEquals("([6, 2], [3, 7, 1])",
                normalSequence0.partition { it.toInt() % 2 == 0 }.toString(),
                Sequencex.partition(normalSequence1) { it.toInt() % 2 == 0 }.toString())
        assertTwoEquals("([], [])",
                emptySequence0.partition { it.toInt() % 2 == 0 }.toString(),
                Sequencex.partition(emptySequence1) { it.toInt() % 2 == 0 }.toString())
        assertEquals("([], [])", Sequencex.partition(nullSequence0) { it.toInt() % 2 == 0 }.toString())
    }

    @Test
    fun testPlus() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence0: Sequence<String>? = null

        assertTwoEquals("6, 3, 7, 2, 1, 9",
                normalSequence0.plus("9").joinToString(),
                Sequencex.joinToString(Sequencex.plus(normalSequence1, "9")))
        assertTwoEquals("9",
                emptySequence0.plus("9").joinToString(),
                Sequencex.joinToString(Sequencex.plus(emptySequence1, "9")))
        assertEquals("9", Sequencex.joinToString(Sequencex.plus(nullSequence0, "9")))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalSequence0.plus(arrayOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(normalSequence1, arrayOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptySequence0.plus(arrayOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(emptySequence1, arrayOf("9", "4"))))
        assertEquals("9, 4", Sequencex.joinToString(Sequencex.plus(nullSequence0, arrayOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalSequence0.plus(listOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(normalSequence1, listOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptySequence0.plus(listOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(emptySequence1, listOf("9", "4"))))
        assertEquals("9, 4", Sequencex.joinToString(Sequencex.plus(nullSequence0, listOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalSequence0.plus(sequenceOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(normalSequence1, Sequencex.sequenceOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptySequence0.plus(sequenceOf("9", "4")).joinToString(),
                Sequencex.joinToString(Sequencex.plus(emptySequence1, Sequencex.sequenceOf("9", "4"))))
        assertEquals("9, 4", Sequencex.joinToString(Sequencex.plus(nullSequence0, Sequencex.sequenceOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9",
                normalSequence0.plusElement("9").joinToString(),
                Sequencex.joinToString(Sequencex.plusElement(normalSequence1, "9")))
        assertTwoEquals("9",
                emptySequence0.plusElement("9").joinToString(),
                Sequencex.joinToString(Sequencex.plusElement(emptySequence1, "9")))
        assertEquals("9", Sequencex.joinToString(Sequencex.plusElement(nullSequence0, "9")))
    }

    @Test
    fun testZip() {
        val normalSequence0 = sequenceOf("6", "3", "7", "2", "1")
        val normalSequence00 = sequenceOf("4", "9", "5")
        val normalSequence1 = Sequencex.sequenceOf("6", "3", "7", "2", "1")
        val normalSequence11 = Sequencex.sequenceOf("4", "9", "5")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()
        val nullSequence0: Sequence<String>? = null

        assertTwoEquals("(6, 4), (3, 9), (7, 5)",
                normalSequence0.zip(normalSequence00).joinToString(),
                Sequencex.joinToString(Sequencex.zip(normalSequence1, normalSequence11)))
        assertTwoEquals("",
                emptySequence0.zip(normalSequence00).joinToString(),
                Sequencex.joinToString(Sequencex.zip(emptySequence1, normalSequence11)))
        assertEquals("", Sequencex.joinToString(Sequencex.zip(nullSequence0, normalSequence11)))

        assertTwoEquals("64, 39, 75",
                normalSequence0.zip(normalSequence00) { it0, it1 -> it0 + it1 }.joinToString(),
                Sequencex.joinToString(Sequencex.zip(normalSequence1, normalSequence11) { it0, it1 -> it0 + it1 }))
        assertTwoEquals("",
                emptySequence0.zip(normalSequence00) { it0, it1 -> it0 + it1 }.joinToString(),
                Sequencex.joinToString(Sequencex.zip(emptySequence1, normalSequence11) { it0, it1 -> it0 + it1 }))
        assertEquals("", Sequencex.joinToString(Sequencex.zip(nullSequence0, normalSequence11) { it0, it1 -> it0 + it1 }))

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
        val nullSequence0: Sequence<String>? = null

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
                Sequencex.joinTo(nullSequence0, StringBuilder(), null, "^", "$", -1, null, null).toString())
        assertEquals("",
                Sequencex.joinTo(nullSequence0, StringBuilder(), null, null, null, -1, null, null).toString())

        assertTwoEquals("60,30,70,20,10",
                normalSequence0.joinTo(buffer = StringBuilder(), separator = ",", transform = { it + "0" }).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), ",", { it + "0" }).toString())
        assertTwoEquals("",
                emptySequence0.joinTo(buffer = StringBuilder(), separator = ",", transform = { it + "0" }).toString(),
                Sequencex.joinTo(emptySequence1, StringBuilder(), ",", { it + "0" }).toString())
        assertEquals("",
                Sequencex.joinTo(nullSequence0, StringBuilder(), ",", { it + "0" }).toString())

        assertTwoEquals("60, 30, 70, 20, 10",
                normalSequence0.joinTo(buffer = StringBuilder(), transform = { it + "0" }).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), { it + "0" }).toString())
        assertTwoEquals("",
                emptySequence0.joinTo(buffer = StringBuilder(), transform = { it + "0" }).toString(),
                Sequencex.joinTo(emptySequence1, StringBuilder(), { it + "0" }).toString())
        assertEquals("",
                Sequencex.joinTo(nullSequence0, StringBuilder(), { it + "0" }).toString())

        assertTwoEquals("6,3,7,2,1",
                normalSequence0.joinTo(buffer = StringBuilder(), separator = ",").toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder(), ",").toString())
        assertTwoEquals("",
                emptySequence0.joinTo(buffer = StringBuilder(), separator = ",").toString(),
                Sequencex.joinTo(emptySequence1, StringBuilder(), ",").toString())
        assertEquals("",
                Sequencex.joinTo(nullSequence0, StringBuilder(), ",").toString())

        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.joinTo(buffer = StringBuilder()).toString(),
                Sequencex.joinTo(normalSequence1, StringBuilder()).toString())
        assertTwoEquals("",
                emptySequence0.joinTo(buffer = StringBuilder()).toString(),
                Sequencex.joinTo(emptySequence1, StringBuilder()).toString())
        assertEquals("",
                Sequencex.joinTo(nullSequence0, StringBuilder()).toString())

        assertThrow(IOException::class) { emptySequence0.joinTo(buffer = ExceptionAppendable()).toString() }
        assertThrow(RuntimeException::class) { Sequencex.joinTo(emptySequence1, ExceptionAppendable(), null, null, null, -1, null, null).toString() }


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
                Sequencex.joinToString(nullSequence0, null, "^", "$", -1, null, null))
        assertEquals("",
                Sequencex.joinToString(nullSequence0, null, null, null, -1, null, null))

        assertTwoEquals("60,30,70,20,10",
                normalSequence0.joinToString(separator = ",", transform = { it + "0" }),
                Sequencex.joinToString(normalSequence1, ",") { it + "0" })
        assertTwoEquals("",
                emptySequence0.joinToString(separator = ",", transform = { it + "0" }),
                Sequencex.joinToString(emptySequence1, ",") { it + "0" })
        assertEquals("",
                Sequencex.joinToString(nullSequence0, ",") { it + "0" })

        assertTwoEquals("60, 30, 70, 20, 10",
                normalSequence0.joinToString(transform = { it + "0" }),
                Sequencex.joinToString(normalSequence1) { it + "0" })
        assertTwoEquals("",
                emptySequence0.joinToString(transform = { it + "0" }),
                Sequencex.joinToString(emptySequence1) { it + "0" })
        assertEquals("",
                Sequencex.joinToString(nullSequence0) { it + "0" })

        assertTwoEquals("6,3,7,2,1",
                normalSequence0.joinToString(separator = ","),
                Sequencex.joinToString(normalSequence1, ","))
        assertTwoEquals("",
                emptySequence0.joinToString(separator = ","),
                Sequencex.joinToString(emptySequence1, ","))
        assertEquals("",
                Sequencex.joinToString(nullSequence0, ","))

        assertTwoEquals("6, 3, 7, 2, 1",
                normalSequence0.joinToString(),
                Sequencex.joinToString(normalSequence1))
        assertTwoEquals("",
                emptySequence0.joinToString(),
                Sequencex.joinToString(emptySequence1))
        assertEquals("",
                Sequencex.joinToString(nullSequence0))
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