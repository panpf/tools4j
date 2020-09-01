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

import com.github.panpf.tools4j.common.Transformer
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
                        Transformer<String, CharSequence> { t -> t.length.toString() }
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
        assertEquals(
                Hashtable(hashMapOf("2" to 2, "4" to 4, "1" to 1)).elements().asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(Hashtable(hashMapOf("2" to 2, "4" to 4, "1" to 1)).elements()))
        )

        assertEquals(
                listOf("2", "4", "1").iterator().asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(listOf("2", "4", "1").iterator()))
        )

        @Suppress("RedundantAsSequence")
        assertEquals(
                listOf("2", "4", "1").asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(listOf("2", "4", "1")))
        )

        @Suppress("RedundantAsSequence")
        assertEquals(
                arrayOf("2", "4", "1").asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(arrayOf("2", "4", "1")))
        )

        @Suppress("RedundantAsSequence")
        assertEquals(
                byteArrayOf(2, 4, 1).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(byteArrayOf(2, 4, 1)))
        )

        @Suppress("RedundantAsSequence")
        assertEquals(
                shortArrayOf(2, 4, 1).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(shortArrayOf(2, 4, 1)))
        )

        @Suppress("RedundantAsSequence")
        assertEquals(
                intArrayOf(2, 4, 1).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(intArrayOf(2, 4, 1)))
        )

        @Suppress("RedundantAsSequence")
        assertEquals(
                longArrayOf(2, 4, 1).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(longArrayOf(2, 4, 1)))
        )

        @Suppress("RedundantAsSequence")
        assertEquals(
                floatArrayOf(2f, 4f, 1f).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(floatArrayOf(2f, 4f, 1f)))
        )

        @Suppress("RedundantAsSequence")
        assertEquals(
                doubleArrayOf(2.0, 4.0, 1.0).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(doubleArrayOf(2.0, 4.0, 1.0)))
        )

        @Suppress("RedundantAsSequence")
        assertEquals(
                booleanArrayOf(true, false, false).asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(booleanArrayOf(true, false, false)))
        )

        @Suppress("RedundantAsSequence")
        assertEquals(
                charArrayOf('a', 'h', 't').asSequence().joinToString(),
                Sequencex.joinToString(Sequencex.asSequence(charArrayOf('a', 'h', 't')))
        )
    }

    @Test
    fun testAsIterable() {
        @Suppress("RedundantAsSequence")
        assertEquals(
                charArrayOf('a', 'h', 't').asSequence().asIterable().joinToString(),
                Sequencex.asIterable(Sequencex.asSequence(charArrayOf('a', 'h', 't'))).joinToString()
        )
    }

    @Test
    fun testSequenceOf() {
        assertEquals(
                sequenceOf(5, 8, 3, 2).joinToString(),
                Sequencex.joinToString(Sequencex.sequenceOf(5, 8, 3, 2))
        )

        assertEquals(
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
        try {
            onceSequence.iterator()
        } catch (e: Exception) {
            fail()
        }
        try {
            onceSequence.iterator()
            fail()
        } catch (e: Exception) {
            if (e !is IllegalStateException || e.message != "This sequence can be consumed only once.") {
                fail()
            }
        }

        val onceSequence1 = Sequencex.constrainOnce(onceSequence)
        assertTrue(onceSequence === onceSequence1)
    }

    @Test
    fun testGenerateSequence() {
        var next = 0
        assertEquals(
                generateSequence { if (next <= 5) next++ else null }.joinToString(),
                Sequencex.joinToString(Sequencex.generateSequence(IntInitialValue(5)))
        )

        assertEquals(
                generateSequence(0) { if (it < 5) it + 1 else null }.joinToString(),
                Sequencex.joinToString(Sequencex.generateSequence(0) { if (it < 5) it + 1 else null })
        )

        assertEquals(
                generateSequence({ 0 }) { if (it < 5) it + 1 else null }.joinToString(),
                Sequencex.joinToString(Sequencex.generateSequence(InitialValue { 0 }, NextValue<Int> { if (it < 5) it + 1 else null }))
        )
    }

    class IntInitialValue(private val end: Int) : InitialValue<Int> {
        private var next = 0

        override fun get(): Int? {
            return if (next <= end) next++ else null
        }
    }
}