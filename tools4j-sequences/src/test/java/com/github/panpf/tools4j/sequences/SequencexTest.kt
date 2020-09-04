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
import org.junit.Assert.*
import org.junit.Test
import java.util.*

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

    class IntInitialValue(private val end: Int) : InitialValue<Int> {
        private var next = 0

        override fun get(): Int? {
            return if (next <= end) next++ else null
        }
    }
}