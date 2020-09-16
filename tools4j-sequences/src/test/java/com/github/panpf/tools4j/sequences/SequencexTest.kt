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

import com.github.panpf.tools4j.iterable.EmptyIterator
import com.github.panpf.tools4j.test.ktx.*
import org.junit.Assert.*
import org.junit.Test
import java.util.*
import kotlin.collections.HashMap
import com.github.panpf.tools4j.common.Pair as Pair2

class SequencexTest {
    // todo Complete test

    @Test
    fun testJoinToArrayString() {
        assertEquals(
                "[22, 333, 1, 55555]",
                Sequencex.joinToArrayString(Sequencex.asSequence(arrayOf("22", "333", "1", "55555")))
        )
        assertEquals(
                "[2, 3, 1, 5]",
                Sequencex.joinToArrayString(
                        Sequencex.asSequence(arrayOf("22", "333", "1", "55555")),
                        { t -> t.length.toString() }
                )
        )
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

        assertThrow(UnsupportedOperationException::class) {
            Sequencex.asSequence(Hashtable(hashMapOf("2" to 2, "4" to 4, "1" to 1)).elements()).iterator().remove()
        }

        assertTwoEquals(
                "2, 4, 1",
                listOf("2", "4", "1").iterator().asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(listOf("2", "4", "1").iterator()))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2, 4, 1",
                listOf("2", "4", "1").asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(listOf("2", "4", "1")))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2, 4, 1",
                listOf("2", "4", "1").asSequence().asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(Sequencex.asSequence(listOf("2", "4", "1"))))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "2=22, 4=4444, 1=1",
                mapOf("2" to "22", "4" to "4444", "1" to "1").asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(mapOf("2" to "22", "4" to "4444", "1" to "1")))
        )

        @Suppress("RedundantAsSequence")
        assertTwoEquals(
                "",
                mapOf<String, String>().asSequence().joinToString(),
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

        assertTwoEquals("a", sequence0.elementAtOrNull(0), Sequencex.elementAtOrNull(sequence1, 0))
        assertTwoEquals("b", sequence0.elementAtOrNull(1), Sequencex.elementAtOrNull(sequence1, 1))
        assertTwoEquals("c", sequence0.elementAtOrNull(2), Sequencex.elementAtOrNull(sequence1, 2))
        assertTwoEquals(null, sequence0.elementAtOrNull(-1), Sequencex.elementAtOrNull(sequence1, -1))
        assertTwoEquals(null, sequence0.elementAtOrNull(3), Sequencex.elementAtOrNull(sequence1, 3))
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

        assertTwoEquals("aj", sequence0.first(), Sequencex.first(sequence1))
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.first() }, { Sequencex.first(emptySequence1) })

        assertTwoEquals("aj", sequence0.first { it.startsWith("a") }, Sequencex.first(sequence1) { it.startsWith("a") })
        assertTwoEquals("bj", sequence0.first { it.startsWith("b") }, Sequencex.first(sequence1) { it.startsWith("b") })
        assertTwoEquals("cj", sequence0.first { it.startsWith("c") }, Sequencex.first(sequence1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { sequence0.first { it.startsWith("k") } }, { Sequencex.first(sequence1) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.first { it.startsWith("a") } }, { Sequencex.first(emptySequence1) { it.startsWith("a") } })

        assertTwoEquals("aj", sequence0.firstOrNull(), Sequencex.firstOrNull(sequence1))
        assertTwoEquals(null, emptySequence0.firstOrNull(), Sequencex.firstOrNull(emptySequence1))

        assertTwoEquals("aj", sequence0.firstOrNull { it.startsWith("a") }, Sequencex.firstOrNull(sequence1) { it.startsWith("a") })
        assertTwoEquals("bj", sequence0.firstOrNull { it.startsWith("b") }, Sequencex.firstOrNull(sequence1) { it.startsWith("b") })
        assertTwoEquals("cj", sequence0.firstOrNull { it.startsWith("c") }, Sequencex.firstOrNull(sequence1) { it.startsWith("c") })
        assertTwoEquals(null, sequence0.firstOrNull { it.startsWith("k") }, Sequencex.firstOrNull(sequence1) { it.startsWith("k") })
        assertTwoEquals(null, emptySequence0.firstOrNull { it.startsWith("k") }, Sequencex.firstOrNull(emptySequence1) { it.startsWith("k") })
    }

    @Test
    fun testIndexOf() {
        val sequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")

        assertTwoEquals(0, sequence0.indexOf("aj"), Sequencex.indexOf(sequence1, "aj"))
        assertTwoEquals(1, sequence0.indexOf("bj"), Sequencex.indexOf(sequence1, "bj"))
        assertTwoEquals(2, sequence0.indexOf("cj"), Sequencex.indexOf(sequence1, "cj"))
        assertTwoEquals(3, sequence0.indexOf("bo"), Sequencex.indexOf(sequence1, "bo"))
        assertTwoEquals(-1, sequence0.indexOf("bb"), Sequencex.indexOf(sequence1, "bb"))

        assertTwoEquals(0, sequence0.indexOfFirst { it.startsWith("a") }, Sequencex.indexOfFirst(sequence1) { it.startsWith("a") })
        assertTwoEquals(1, sequence0.indexOfFirst { it.startsWith("b") }, Sequencex.indexOfFirst(sequence1) { it.startsWith("b") })
        assertTwoEquals(2, sequence0.indexOfFirst { it.startsWith("c") }, Sequencex.indexOfFirst(sequence1) { it.startsWith("c") })
        assertTwoEquals(-1, sequence0.indexOfFirst { it.startsWith("k") }, Sequencex.indexOfFirst(sequence1) { it.startsWith("k") })

        assertTwoEquals(0, sequence0.indexOfLast { it.startsWith("a") }, Sequencex.indexOfLast(sequence1) { it.startsWith("a") })
        assertTwoEquals(3, sequence0.indexOfLast { it.startsWith("b") }, Sequencex.indexOfLast(sequence1) { it.startsWith("b") })
        assertTwoEquals(2, sequence0.indexOfLast { it.startsWith("c") }, Sequencex.indexOfLast(sequence1) { it.startsWith("c") })
        assertTwoEquals(-1, sequence0.indexOfLast { it.startsWith("k") }, Sequencex.indexOfLast(sequence1) { it.startsWith("k") })
    }

    @Test
    fun testLast() {
        val sequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence1 = Sequencex.sequenceOf<String>()

        assertTwoEquals("bo", sequence0.last(), Sequencex.last(sequence1))
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.last() }, { Sequencex.last(emptySequence1) })

        assertTwoEquals("aj", sequence0.last { it.startsWith("a") }, Sequencex.last(sequence1) { it.startsWith("a") })
        assertTwoEquals("bo", sequence0.last { it.startsWith("b") }, Sequencex.last(sequence1) { it.startsWith("b") })
        assertTwoEquals("cj", sequence0.last { it.startsWith("c") }, Sequencex.last(sequence1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { sequence0.last { it.startsWith("k") } }, { Sequencex.last(sequence1) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.last { it.startsWith("a") } }, { Sequencex.last(emptySequence1) { it.startsWith("a") } })

        assertTwoEquals("bo", sequence0.lastOrNull(), Sequencex.lastOrNull(sequence1))
        assertTwoEquals(null, emptySequence0.lastOrNull(), Sequencex.lastOrNull(emptySequence1))

        assertTwoEquals("aj", sequence0.lastOrNull { it.startsWith("a") }, Sequencex.lastOrNull(sequence1) { it.startsWith("a") })
        assertTwoEquals("bo", sequence0.lastOrNull { it.startsWith("b") }, Sequencex.lastOrNull(sequence1) { it.startsWith("b") })
        assertTwoEquals("cj", sequence0.lastOrNull { it.startsWith("c") }, Sequencex.lastOrNull(sequence1) { it.startsWith("c") })
        assertTwoEquals(null, sequence0.lastOrNull { it.startsWith("k") }, Sequencex.lastOrNull(sequence1) { it.startsWith("k") })
        assertTwoEquals(null, emptySequence0.lastOrNull { it.startsWith("k") }, Sequencex.lastOrNull(emptySequence1) { it.startsWith("k") })

        assertTwoEquals(0, sequence0.lastIndexOf("aj"), Sequencex.lastIndexOf(sequence1, "aj"))
        assertTwoEquals(1, sequence0.lastIndexOf("bj"), Sequencex.lastIndexOf(sequence1, "bj"))
        assertTwoEquals(2, sequence0.lastIndexOf("cj"), Sequencex.lastIndexOf(sequence1, "cj"))
        assertTwoEquals(3, sequence0.lastIndexOf("bo"), Sequencex.lastIndexOf(sequence1, "bo"))
        assertTwoEquals(-1, sequence0.lastIndexOf("bb"), Sequencex.lastIndexOf(sequence1, "bb"))
    }

    @Test
    fun testSingle() {
        val singleSequence0 = sequenceOf("cj")
        val singleSequence1 = Sequencex.sequenceOf("cj")
        val multiSequence0 = sequenceOf("aj", "bj", "cj", "bo")
        val multiSequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "bo")
        val emptySequence0 = sequenceOf<String>()
        val emptySequence1 = Sequencex.sequenceOf<String>()

        assertTwoEquals("cj", singleSequence0.single(), Sequencex.single(singleSequence1))
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.single() }, { Sequencex.single(emptySequence1) })
        assertTwoThrow(IllegalArgumentException::class, { multiSequence0.single() }, { Sequencex.single(multiSequence1) })

        assertTwoEquals("cj",
                singleSequence0.single { it.startsWith("c") },
                Sequencex.single(singleSequence1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class,
                { singleSequence0.single { it.startsWith("b") } },
                { Sequencex.single(singleSequence1) { it.startsWith("b") } })
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.single { it.startsWith("c") } },
                { Sequencex.single(emptySequence1) { it.startsWith("c") } })
        assertTwoEquals("cj",
                multiSequence0.single { it.startsWith("c") },
                Sequencex.single(multiSequence1) { it.startsWith("c") })
        assertTwoThrow(IllegalArgumentException::class,
                { multiSequence0.single { it.startsWith("b") } },
                { Sequencex.single(multiSequence1) { it.startsWith("b") } })

        assertTwoEquals("cj", singleSequence0.singleOrNull(), Sequencex.singleOrNull(singleSequence1))
        assertTwoEquals(null, emptySequence0.singleOrNull(), Sequencex.singleOrNull(emptySequence1))
        assertTwoEquals(null, multiSequence0.singleOrNull(), Sequencex.singleOrNull(multiSequence1))

        assertTwoEquals("cj",
                singleSequence0.singleOrNull { it.startsWith("c") },
                Sequencex.singleOrNull(singleSequence1) { it.startsWith("c") })
        assertTwoEquals(null,
                singleSequence0.singleOrNull { it.startsWith("b") },
                Sequencex.singleOrNull(singleSequence1) { it.startsWith("b") })
        assertTwoEquals(null,
                emptySequence0.singleOrNull { it.startsWith("c") },
                Sequencex.singleOrNull(emptySequence1) { it.startsWith("c") })
        assertTwoEquals("cj",
                multiSequence0.singleOrNull { it.startsWith("c") },
                Sequencex.singleOrNull(multiSequence1) { it.startsWith("c") })
        assertTwoEquals(null,
                multiSequence0.singleOrNull { it.startsWith("b") },
                Sequencex.singleOrNull(multiSequence1) { it.startsWith("b") })
    }

    @Test
    fun testDrop() {
        val sequence0 = sequenceOf("aj", "bj", "cj", "dj")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "cj", "dj")

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

    @Suppress("RedundantAsSequence")
    @Test
    fun testFilter() {
        val sequence0 = sequenceOf("aj", "bo", "cj", "do")
        val sequence1 = Sequencex.sequenceOf("aj", "bo", "cj", "do")

        assertTwoEquals("aj, cj",
                sequence0.filter { it.endsWith("j") }.joinToString(),
                Sequencex.joinToString(Sequencex.filter(sequence1) { it.endsWith("j") }))

        val filterToDestination = ArrayList<String>()
        val filterToDestination1 = ArrayList<String>()
        val filterToDestinationResult = sequence0.filterTo(filterToDestination) { it.endsWith("j") }
        val filterToDestinationResult1 = Sequencex.filterTo(sequence1, filterToDestination1) { it.endsWith("j") }
        assertTwoEquals("aj, cj",
                filterToDestinationResult.asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(filterToDestinationResult1)))
        assertTrue(filterToDestination === filterToDestinationResult)
        assertTrue(filterToDestination1 === filterToDestinationResult1)

        assertTwoEquals("bo, do",
                sequence0.filterIndexed { index, _ -> (index % 2) != 0 }.joinToString(),
                Sequencex.joinToString(Sequencex.filterIndexed(sequence1) { index, _ -> (index % 2) != 0 }))

        val filterIndexedToDestination = ArrayList<String>()
        val filterIndexedToDestination1 = ArrayList<String>()
        val filterIndexedToDestinationResult = sequence0.filterIndexedTo(filterIndexedToDestination) { index, _ -> (index % 2) != 0 }
        val filterIndexedToDestinationResult1 = Sequencex.filterIndexedTo(sequence1, filterIndexedToDestination1) { index, _ -> (index % 2) != 0 }
        assertTwoEquals("bo, do",
                filterIndexedToDestinationResult.asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(filterIndexedToDestinationResult1)))
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
        val filterIsInstanceToDestinationResult = anySequence0.filterIsInstanceTo(filterIsInstanceToDestination, Integer::class.java)
        val filterIsInstanceToDestinationResult1 = Sequencex.filterIsInstanceTo(anySequence1, filterIsInstanceToDestination1, Integer::class.java)
        assertTwoEquals("4, 76",
                filterIsInstanceToDestinationResult.asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(filterIsInstanceToDestinationResult1)))
        assertTrue(filterIsInstanceToDestination === filterIsInstanceToDestinationResult)
        assertTrue(filterIsInstanceToDestination1 === filterIsInstanceToDestinationResult1)

        assertTwoEquals("bo, do",
                sequence0.filterNot { it.endsWith("j") }.joinToString(),
                Sequencex.joinToString(Sequencex.filterNot(sequence1) { it.endsWith("j") }))

        val filterNotToDestination = ArrayList<String>()
        val filterNotToDestination1 = ArrayList<String>()
        val filterNotToDestinationResult = sequence0.filterNotTo(filterNotToDestination) { it.endsWith("j") }
        val filterNotToDestinationResult1 = Sequencex.filterNotTo(sequence1, filterNotToDestination1) { it.endsWith("j") }
        assertTwoEquals("bo, do",
                filterNotToDestinationResult.asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(filterNotToDestinationResult1)))
        assertTrue(filterNotToDestination === filterNotToDestinationResult)
        assertTrue(filterNotToDestination1 === filterNotToDestinationResult1)


        val notNullSequence0 = sequenceOf(null, "f", null, "gsdg")
        val notNullSequence1 = Sequencex.sequenceOf(null, "f", null, "gsdg")

        assertTwoEquals("f, gsdg",
                notNullSequence0.filterNotNull().joinToString(),
                Sequencex.joinToString(Sequencex.filterNotNull(notNullSequence1)))

        val filterNotNullToDestination = ArrayList<String>()
        val filterNotNullToDestination1 = ArrayList<String>()
        val filterNotNullToDestinationResult = notNullSequence0.filterNotNullTo(filterNotNullToDestination)
        val filterNotNullToDestinationResult1 = Sequencex.filterNotNullTo(notNullSequence1, filterNotNullToDestination1)
        assertTwoEquals("f, gsdg",
                filterNotNullToDestinationResult.asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(filterNotNullToDestinationResult1)))
        assertTrue(filterNotNullToDestination === filterNotNullToDestinationResult)
        assertTrue(filterNotNullToDestination1 === filterNotNullToDestinationResult1)
    }

    @Test
    fun testSort() {
        val sequence0 = sequenceOf("aaa", "h", "uuuu", "gg")
        val sequence1 = Sequencex.sequenceOf("aaa", "h", "uuuu", "gg")

        assertTwoEquals("aaa, gg, h, uuuu",
                sequence0.sorted().joinToString(),
                Sequencex.joinToString(Sequencex.sorted(sequence1)))

        assertTwoEquals("uuuu, h, gg, aaa",
                sequence0.sortedDescending().joinToString(),
                Sequencex.joinToString(Sequencex.sortedDescending(sequence1)))

        assertTwoEquals("h, gg, aaa, uuuu",
                sequence0.sortedBy { it.length }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedBy(sequence1) { it.length }))

        assertTwoEquals("uuuu, aaa, gg, h",
                sequence0.sortedByDescending { it.length }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedByDescending(sequence1) { it.length }))

        assertTwoEquals("aaa, gg, h, uuuu",
                sequence0.sortedWith { it1, it2 -> it1.compareTo(it2) }.joinToString(),
                Sequencex.joinToString(Sequencex.sortedWith(sequence1) { it1, it2 -> it1.compareTo(it2) }))
    }

    @Test
    @Suppress("ReplaceAssociateFunction")
    fun testAssociate() {
        val sequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")

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
        val associateToMapResult0 = sequence0.associateTo(associateToMap0) { it.first().toString() to it }
        val associateToMapResult1 = Sequencex.associateTo(sequence1, associateToMap1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) }
        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                associateToMap0,
                associateToMap1,
        )
        assertTrue(associateToMap0 === associateToMapResult0)
        assertTrue(associateToMap1 === associateToMapResult1)

        val associateByTo1Map0 = HashMap<String, String>()
        val associateByTo1Map1 = HashMap<String, String>()
        val associateByTo1MapResult0 = sequence0.associateByTo(associateByTo1Map0) { it.first().toString() }
        val associateByTo1MapResult1 = Sequencex.associateByTo(sequence1, associateByTo1Map1) { it.first().toString() }
        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                associateByTo1Map0,
                associateByTo1Map1,
        )
        assertTrue(associateByTo1Map0 === associateByTo1MapResult0)
        assertTrue(associateByTo1Map1 === associateByTo1MapResult1)

        val associateByTo2Map0 = HashMap<String, String>()
        val associateByTo2Map1 = HashMap<String, String>()
        val associateByTo2MapResult0 = sequence0.associateByTo(associateByTo2Map0, { it.first().toString() }, { it })
        val associateByTo2MapResult1 = Sequencex.associateByTo(sequence1, associateByTo2Map1, { it.first().toString() }, { it })
        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                associateByTo2Map0,
                associateByTo2Map1,
        )
        assertTrue(associateByTo2Map0 === associateByTo2MapResult0)
        assertTrue(associateByTo2Map1 === associateByTo2MapResult1)
    }

    @Test
    fun testTo() {
        val sequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")

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
    }

    @Test
    fun testFlatMap() {
        val sequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")

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
        val flatMapToListResult0 = sequence0.flatMapTo(flatMapToList0) { it -> it.toCharArray().map { it.toString() } }
        val flatMapToList1 = ArrayList<String>()
        val flatMapToListResult1 = Sequencex.flatMapOfIterableTo(sequence1, flatMapToList1) { it -> it.toCharArray().map { it.toString() } }
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

        val flatMapToList2 = ArrayList<String>()
        val flatMapToListResult2 = sequence0.flatMapTo(flatMapToList2) { it -> it.toCharArray().asSequence().map { it.toString() } }
        val flatMapToList3 = ArrayList<String>()
        val flatMapToListResult3 = Sequencex.flatMapTo(sequence1, flatMapToList3) { it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { it.toString() } }
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
        val flatMapIndexedToListResult0 = sequence0.flatMapIndexedTo(flatMapIndexedToList0) { index, it -> it.toCharArray().map { "${index}-$it" } }
        val flatMapIndexedToList1 = ArrayList<String>()
        val flatMapIndexedToListResult1 = Sequencex.flatMapIndexedOfIterableTo(sequence1, flatMapIndexedToList1) { index, it -> it.toCharArray().map { "${index}-$it" } }
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

        val flatMapIndexedToList2 = ArrayList<String>()
        val flatMapIndexedToListResult2 = sequence0.flatMapIndexedTo(flatMapIndexedToList2) { index, it -> it.toCharArray().asSequence().map { "${index}-$it" } }
        val flatMapIndexedToList3 = ArrayList<String>()
        val flatMapIndexedToListResult3 = Sequencex.flatMapIndexedTo(sequence1, flatMapIndexedToList3) { index, it -> Sequencex.map(Sequencex.asSequence(it.toCharArray())) { "${index}-$it" } }
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
    }

    @Test
    fun testGroup() {
        val sequence0 = sequenceOf("aj", "bj", "ao", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "bo")

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
        val groupByToMapResult0 = sequence0.groupByTo(groupByToMap0) { it.first() }
        val groupByToMapResult1 = Sequencex.groupByTo(sequence1, groupByToMap1) { it.first() }
        assertTwoEquals(
                "{a=[aj, ao], b=[bj, bo]}",
                groupByToMap0.toString(),
                groupByToMap1.toString(),
        )
        assertTrue(groupByToMap0 === groupByToMapResult0)
        assertTrue(groupByToMap1 === groupByToMapResult1)

        val groupByToMap2 = HashMap<Char, MutableList<Char>>()
        val groupByToMap3 = HashMap<Char, List<Char>>()
        val groupByToMapResult2 = sequence0.groupByTo(groupByToMap2, { it.first() }, { it.last() })
        val groupByToMapResult3 = Sequencex.groupByTo(sequence1, groupByToMap3, { it.first() }, { it.last() })
        assertTwoEquals(
                "{a=[j, o], b=[j, o]}",
                groupByToMap2.toString(),
                groupByToMap3.toString(),
        )
        assertTrue(groupByToMap2 === groupByToMapResult2)
        assertTrue(groupByToMap3 === groupByToMapResult3)
    }

    @Test
    fun testMap() {
        // todo 测试 sequence null 的情况， 同步到 CollectionxTest
        val sequence0 = sequenceOf("aj", "bj", "ao", "cc", "bo")
        val sequence1 = Sequencex.sequenceOf("aj", "bj", "ao", "cc", "bo")

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
        val mapToListResult0 = sequence0.mapTo(mapToList0) { it.first() }
        val mapToListResult1 = Sequencex.mapTo(sequence1, mapToList1) { it.first() }
        assertTwoEquals(
                "[a, b, a, c, b]",
                mapToList0.toString(),
                mapToList1.toString(),
        )
        assertTrue(mapToList0 === mapToListResult0)
        assertTrue(mapToList1 === mapToListResult1)

        val mapNotNullToList0 = ArrayList<Char>()
        val mapNotNullToList1 = ArrayList<Char>()
        val mapNotNullToListResult0 = sequence0.mapNotNullTo(mapNotNullToList0) { if (it != "cc") it.first() else null }
        val mapNotNullToListResult1 = Sequencex.mapNotNullTo(sequence1, mapNotNullToList1) { if (it != "cc") it.first() else null }
        assertTwoEquals(
                "[a, b, a, b]",
                mapNotNullToList0.toString(),
                mapNotNullToList1.toString(),
        )
        assertTrue(mapNotNullToList0 === mapNotNullToListResult0)
        assertTrue(mapNotNullToList1 === mapNotNullToListResult1)

        val mapIndexedToList0 = ArrayList<String>()
        val mapIndexedToList1 = ArrayList<String>()
        val mapIndexedToListResult0 = sequence0.mapIndexedTo(mapIndexedToList0) { index, s -> "$index:${s.first()}" }
        val mapIndexedToListResult1 = Sequencex.mapIndexedTo(sequence1, mapIndexedToList1) { index, s -> "$index:${s.first()}" }
        assertTwoEquals(
                "[0:a, 1:b, 2:a, 3:c, 4:b]",
                mapIndexedToList0.toString(),
                mapIndexedToList1.toString(),
        )
        assertTrue(mapIndexedToList0 === mapIndexedToListResult0)
        assertTrue(mapIndexedToList1 === mapIndexedToListResult1)

        val mapIndexedNotNullToList0 = ArrayList<String>()
        val mapIndexedNotNullToList1 = ArrayList<String>()
        val mapIndexedNotNullToListResult0 = sequence0.mapIndexedNotNullTo(mapIndexedNotNullToList0) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
        val mapIndexedNotNullToListResult1 = Sequencex.mapIndexedNotNullTo(sequence1, mapIndexedNotNullToList1) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
        assertTwoEquals(
                "[0:a, 1:b, 2:a, 4:b]",
                mapIndexedNotNullToList0.toString(),
                mapIndexedNotNullToList1.toString(),
        )
        assertTrue(mapIndexedNotNullToList0 === mapIndexedNotNullToListResult0)
        assertTrue(mapIndexedNotNullToList1 === mapIndexedNotNullToListResult1)
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

        assertTwoEquals(
                true,
                sequence0.all { it -> it.all { it.isLetter() } },
                Sequencex.all(sequence1) { it -> it.all { it.isLetter() } },
        )

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

        assertTwoEquals(
                true,
                sequence0.any(),
                Sequencex.any(sequence1),
        )

        assertTwoEquals(
                false,
                sequenceOf<String>().any(),
                Sequencex.any(Sequencex.sequenceOf<String>()),
        )

        assertTwoEquals(
                true,
                sequence0.any { it.last() == 'j' },
                Sequencex.any(sequence1) { it.last() == 'j' },
        )

        assertTwoEquals(
                false,
                sequence0.any { it -> it.all { it.isDigit() } },
                Sequencex.any(sequence1) { it -> it.all { it.isDigit() } },
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

        assertTwoEquals(
                "^ajbjajbjbo",
                sequence0.fold("^") { r, t -> r + t },
                Sequencex.fold(sequence1, "^") { r, t -> r + t },
        )

        assertTwoEquals(
                "^0aj1bj2aj3bj4bo",
                sequence0.foldIndexed("^") { i, r, t -> r + i.toString() + t },
                Sequencex.foldIndexed(sequence1, "^") { i, r, t -> r + i.toString() + t },
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


    class IntInitialValue(private val end: Int) : InitialValue<Int> {
        private var next = 0

        override fun get(): Int? {
            return if (next <= end) next++ else null
        }
    }
}