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

@file:Suppress("RemoveRedundantSpreadOperator")

package com.github.panpf.tools4j.collections

import com.github.panpf.tools4j.iterable.CharSequenceIterable
import org.junit.Assert.*
import org.junit.Test
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashSet

class CollectionxTest {

    // todo Complete test

    @Test
    fun testNullOrEmpty() {
        assertTrue(Collectionx.isNullOrEmpty(null as Collection<String>?))
        assertTrue(Collectionx.isNullOrEmpty(ArrayList<String>()))
        assertFalse(Collectionx.isNullOrEmpty(arrayListOf("1")))

        assertTrue(Collectionx.isNotNullOrEmpty(arrayListOf("1")))
        assertFalse(Collectionx.isNotNullOrEmpty(null as Collection<String>?))
        assertFalse(Collectionx.isNotNullOrEmpty(ArrayList<String>()))
    }

    @Test
    fun testJoinToArrayString() {
        assertEquals("[key4, key3, key2]", Collectionx.joinToArrayString(arrayListOf("4", "3", "2")) { "key$it" })
        assertEquals("[4, 3, 2]", Collectionx.joinToArrayString(arrayListOf("4", "3", "2")))
    }

    @Test
    fun testLinkedListOf() {
        assertEquals("[4, 3, 2]", Collectionx.joinToArrayString(Collectionx.linkedListOf("4", "3", "2")))
        assertEquals("[]", Collectionx.joinToArrayString(Collectionx.linkedListOf<String>()))
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    @Test
    fun testCollectionSizeOrDefault() {
        assertEquals(3, Collectionx.collectionSizeOrDefault(listOf(4, 3, 2), 10))
        assertEquals(0, Collectionx.collectionSizeOrDefault(null, 10))
        assertEquals(10, Collectionx.collectionSizeOrDefault(IntProgression.fromClosedRange(4, 2, -1), 10))
    }

    @Test
    fun testEmpty() {
        assertTrue(Collectionx.isEmpty(ArrayList<String>()))
        assertFalse(Collectionx.isEmpty(arrayListOf("1")))

        assertTrue(Collectionx.isNotEmpty(arrayListOf("1")))
        assertFalse(Collectionx.isNotEmpty(ArrayList<String>()))
    }

    @Test
    fun testOrEmpty() {
        assertTrue(Collectionx.orEmpty(null as Collection<String>?) is ArrayList)
        assertTrue(Collectionx.orEmpty(null as Collection<String>?).isEmpty())
        assertTrue(Collectionx.orEmpty(Collectionx.linkedListOf("1") as Collection<String>) is LinkedList)
        assertTrue(Collectionx.orEmpty(Collectionx.linkedListOf("1") as Collection<String>).isNotEmpty())

        assertTrue(Collectionx.orEmpty(null as List<String>?) is ArrayList)
        assertTrue(Collectionx.orEmpty(null as List<String>?).isEmpty())
        assertTrue(Collectionx.orEmpty(Collectionx.linkedListOf("1")) is LinkedList)
        assertTrue(Collectionx.orEmpty(Collectionx.linkedListOf("1")).isNotEmpty())
    }


    @Test
    fun testListOf() {
        assertTwoEquals(0, Collectionx.immutableListOf<String>().size, listOf<String>().size)
        assertEquals(Collections.EMPTY_LIST::class.java.name, Collectionx.immutableListOf<String>()::class.java.name)
        assertEquals(emptyList<String>()::class.java.name, listOf<String>()::class.java.name)
        try {
            Collectionx.immutableListOf<String>().add("3")
            fail()
        } catch (e: Exception) {
        }

        assertTwoEquals(1, Collectionx.immutableListOf("1").size, listOf("1").size)
        assertTwoEquals(Collections.singletonList("1")::class.java.name, Collectionx.immutableListOf("1")::class.java.name, listOf("1")::class.java.name)
        try {
            Collectionx.immutableListOf("1").add("3")
            fail()
        } catch (e: Exception) {
        }

        assertTwoEquals(1, Collectionx.immutableListOf(null as String?).size, listOf(null as String?).size)
        assertTwoEquals(Collections.singletonList("1")::class.java.name, Collectionx.immutableListOf(null as String?)::class.java.name, listOf(null as String?)::class.java.name)
        try {
            Collectionx.immutableListOf(null as String?).add("3")
            fail()
        } catch (e: Exception) {
        }

        assertTwoEquals(2, Collectionx.immutableListOf("1", "2").size, listOf("1", "2").size)
        assertTwoEquals(listOf("1", "2")::class.java.name, Collectionx.immutableListOf("1", "2")::class.java.name, listOf("1", "2")::class.java.name)
        try {
            Collectionx.immutableListOf("1", "2").add("3")
            fail()
        } catch (e: Exception) {
        }

        assertTwoEquals(0, Collectionx.immutableListOf(*arrayOf<String>()).size, listOf(*arrayOf<String>()).size)
        assertEquals(Collections.EMPTY_LIST::class.java.name, Collectionx.immutableListOf(*arrayOf<String>())::class.java.name)
        assertEquals(emptyList<String>()::class.java.name, listOf(*arrayOf<String>())::class.java.name)
        try {
            Collectionx.immutableListOf(*arrayOf<String>()).add("3")
            fail()
        } catch (e: Exception) {
        }


        assertTwoEquals(0, Collectionx.mutableListOf<String>().size, mutableListOf<String>().size)
        assertTwoEquals(ArrayList::class.java.name, Collectionx.mutableListOf<String>()::class.java.name, mutableListOf<String>()::class.java.name)
        assertTwoEquals(1, Collectionx.mutableListOf<String>().apply { add("3") }.size, mutableListOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.mutableListOf("1", "2").size, mutableListOf("1", "2").size)
        assertTwoEquals(ArrayList::class.java.name, Collectionx.mutableListOf("1", "2")::class.java.name, mutableListOf("1", "2")::class.java.name)
        assertTwoEquals(3, Collectionx.mutableListOf("1", "2").apply { add("3") }.size, mutableListOf("1", "2").apply { add("3") }.size)


        assertTwoEquals(0, Collectionx.arrayListOf<String>().size, arrayListOf<String>().size)
        assertTwoEquals(ArrayList::class.java.name, Collectionx.arrayListOf<String>()::class.java.name, arrayListOf<String>()::class.java.name)
        assertTwoEquals(1, Collectionx.arrayListOf<String>().apply { add("3") }.size, arrayListOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.arrayListOf("1", "2").size, arrayListOf("1", "2").size)
        assertTwoEquals(ArrayList::class.java.name, Collectionx.arrayListOf("1", "2")::class.java.name, arrayListOf("1", "2")::class.java.name)
        assertTwoEquals(3, Collectionx.arrayListOf("1", "2").apply { add("3") }.size, arrayListOf("1", "2").apply { add("3") }.size)
    }

    @Test
    fun testSetOf() {
        assertTwoEquals(0, Collectionx.immutableSetOf<String>().size, setOf<String>().size)
        assertEquals(Collections.EMPTY_SET::class.java.name, Collectionx.immutableSetOf<String>()::class.java.name)
        assertEquals(emptySet<String>()::class.java.name, setOf<String>()::class.java.name)
        try {
            Collectionx.immutableSetOf<String>().add("3")
            fail()
        } catch (e: Exception) {
        }

        assertTwoEquals(1, Collectionx.immutableSetOf("1").size, setOf("1").size)
        assertTwoEquals(Collections.singleton("1")::class.java.name, Collectionx.immutableSetOf("1")::class.java.name, setOf("1")::class.java.name)
        try {
            Collectionx.immutableSetOf("1").add("3")
            fail()
        } catch (e: Exception) {
        }

        assertTwoEquals(1, Collectionx.immutableSetOf(null as String?).size, setOf(null as String?).size)
        assertTwoEquals(Collections.singleton("1")::class.java.name, Collectionx.immutableSetOf(null as String?)::class.java.name, setOf(null as String?)::class.java.name)
        try {
            Collectionx.immutableSetOf(null as String?).add("3")
            fail()
        } catch (e: Exception) {
        }

        assertTwoEquals(2, Collectionx.immutableSetOf("1", "2").size, setOf("1", "2").size)
        assertTwoEquals(LinkedHashSet::class.java.name, Collectionx.immutableSetOf("1", "2")::class.java.name, setOf("1", "2")::class.java.name)
        Collectionx.immutableSetOf("1", "2").add("3")
        (setOf("1", "2") as LinkedHashSet).add("3")

        assertTwoEquals(0, Collectionx.immutableSetOf(*arrayOf<String>()).size, setOf(*arrayOf<String>()).size)
        assertEquals(Collections.EMPTY_SET::class.java.name, Collectionx.immutableSetOf(*arrayOf<String>())::class.java.name)
        assertEquals(emptySet<String>()::class.java.name, setOf(*arrayOf<String>())::class.java.name)
        try {
            Collectionx.immutableSetOf(*arrayOf<String>()).add("3")
            fail()
        } catch (e: Exception) {
        }


        assertTwoEquals(0, Collectionx.mutableSetOf<String>().size, mutableSetOf<String>().size)
        assertTwoEquals(java.util.LinkedHashSet::class.java.name, Collectionx.mutableSetOf<String>()::class.java.name, mutableSetOf<String>()::class.java.name)
        assertTwoEquals(1, Collectionx.mutableSetOf<String>().apply { add("3") }.size, mutableSetOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.mutableSetOf("1", "2").size, mutableSetOf("1", "2").size)
        assertTwoEquals(java.util.LinkedHashSet::class.java.name, Collectionx.mutableSetOf("1", "2")::class.java.name, mutableSetOf("1", "2")::class.java.name)
        assertTwoEquals(3, Collectionx.mutableSetOf("1", "2").apply { add("3") }.size, mutableSetOf("1", "2").apply { add("3") }.size)


        assertTwoEquals(0, Collectionx.hashSetOf<String>().size, hashSetOf<String>().size)
        assertTwoEquals(HashSet::class.java.name, Collectionx.hashSetOf<String>()::class.java.name, hashSetOf<String>()::class.java.name)
        assertTwoEquals(1, Collectionx.hashSetOf<String>().apply { add("3") }.size, hashSetOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.hashSetOf("1", "2").size, hashSetOf("1", "2").size)
        assertTwoEquals(HashSet::class.java.name, Collectionx.hashSetOf("1", "2")::class.java.name, hashSetOf("1", "2")::class.java.name)
        assertTwoEquals(3, Collectionx.hashSetOf("1", "2").apply { add("3") }.size, hashSetOf("1", "2").apply { add("3") }.size)


        assertTwoEquals(0, Collectionx.linkedSetOf<String>().size, linkedSetOf<String>().size)
        assertTwoEquals(java.util.LinkedHashSet::class.java.name, Collectionx.linkedSetOf<String>()::class.java.name, linkedSetOf<String>()::class.java.name)
        assertTwoEquals(1, Collectionx.linkedSetOf<String>().apply { add("3") }.size, linkedSetOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.linkedSetOf("1", "2").size, linkedSetOf("1", "2").size)
        assertTwoEquals(java.util.LinkedHashSet::class.java.name, Collectionx.linkedSetOf("1", "2")::class.java.name, linkedSetOf("1", "2")::class.java.name)
        assertTwoEquals(3, Collectionx.linkedSetOf("1", "2").apply { add("3") }.size, linkedSetOf("1", "2").apply { add("3") }.size)


        assertTwoEquals(0, Collectionx.sortedSetOf<String>().size, sortedSetOf<String>().size)
        assertTwoEquals(java.util.TreeSet::class.java.name, Collectionx.sortedSetOf<String>()::class.java.name, sortedSetOf<String>()::class.java.name)
        assertTwoEquals(1, Collectionx.sortedSetOf<String>().apply { add("3") }.size, sortedSetOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.sortedSetOf("1", "2").size, sortedSetOf("1", "2").size)
        assertTwoEquals(java.util.TreeSet::class.java.name, Collectionx.sortedSetOf("1", "2")::class.java.name, sortedSetOf("1", "2")::class.java.name)
        assertTwoEquals(3, Collectionx.sortedSetOf("1", "2").apply { add("3") }.size, sortedSetOf("1", "2").apply { add("3") }.size)

        assertTwoEquals("2,1", Collectionx.sortedSetOf({ it1, it2 -> (it1.toInt() - it2.toInt()) * -1 }, "1", "2").joinToString(","),
                sortedSetOf(Comparator { it1, it2 -> (it1.toInt() - it2.toInt()) * -1 }, "1", "2").joinToString(","))
    }

    @Test
    fun testToArray() {
        assertArrayEquals(byteArrayOf(4.toByte(), 3.toByte(), 2.toByte()), Collectionx.toByteArray(listOf(4.toByte(), 3.toByte(), 2.toByte())))
        assertArrayEquals(byteArrayOf(), Collectionx.toByteArray(null))
        assertArrayEquals(byteArrayOf(), Collectionx.toByteArray(listOf<Byte>()))

        assertArrayEquals(shortArrayOf(4.toShort(), 3.toShort(), 2.toShort()), Collectionx.toShortArray(listOf(4.toShort(), 3.toShort(), 2.toShort())))
        assertArrayEquals(shortArrayOf(), Collectionx.toShortArray(null))
        assertArrayEquals(shortArrayOf(), Collectionx.toShortArray(listOf<Short>()))

        assertArrayEquals(intArrayOf(4, 3, 2), Collectionx.toIntArray(listOf(4, 3, 2)))
        assertArrayEquals(intArrayOf(), Collectionx.toIntArray(null))
        assertArrayEquals(intArrayOf(), Collectionx.toIntArray(listOf<Int>()))

        assertArrayEquals(longArrayOf(4.toLong(), 3.toLong(), 2.toLong()), Collectionx.toLongArray(listOf(4.toLong(), 3.toLong(), 2.toLong())))
        assertArrayEquals(longArrayOf(), Collectionx.toLongArray(null))
        assertArrayEquals(longArrayOf(), Collectionx.toLongArray(listOf<Long>()))

        assertArrayEquals(floatArrayOf(4f, 3f, 2f), Collectionx.toFloatArray(listOf(4f, 3f, 2f)), 0f)
        assertArrayEquals(floatArrayOf(), Collectionx.toFloatArray(null), 0f)
        assertArrayEquals(floatArrayOf(), Collectionx.toFloatArray(listOf<Float>()), 0f)

        assertArrayEquals(doubleArrayOf(4.toDouble(), 3.toDouble(), 2.toDouble()), Collectionx.toDoubleArray(listOf(4.toDouble(), 3.toDouble(), 2.toDouble())), 0.toDouble())
        assertArrayEquals(doubleArrayOf(), Collectionx.toDoubleArray(null), 0.toDouble())
        assertArrayEquals(doubleArrayOf(), Collectionx.toDoubleArray(listOf<Double>()), 0.toDouble())

        assertArrayEquals(booleanArrayOf(true, false, false), Collectionx.toBooleanArray(listOf(true, false, false)))
        assertArrayEquals(booleanArrayOf(), Collectionx.toBooleanArray(null))
        assertArrayEquals(booleanArrayOf(), Collectionx.toBooleanArray(listOf<Boolean>()))

        assertArrayEquals(charArrayOf(4.toChar(), 3.toChar(), 2.toChar()), Collectionx.toCharArray(listOf(4.toChar(), 3.toChar(), 2.toChar())))
        assertArrayEquals(charArrayOf(), Collectionx.toCharArray(null))
        assertArrayEquals(charArrayOf(), Collectionx.toCharArray(listOf<Char>()))
    }
    
    @Test
    fun testFlatten() {
        assertTwoEquals(
                "a, b, c, d, e, f, g, h, i",
                arrayListOf(
                        arrayListOf("a", "b", "c"),
                        arrayListOf("d", "e", "f"),
                        arrayListOf("g", "h", "i")
                ).flatten().joinToString(),
                Collectionx.joinToString(Collectionx.flatten(Collectionx.arrayListOf(
                        Collectionx.arrayListOf("a", "b", "c"),
                        Collectionx.arrayListOf("d", "e", "f"),
                        Collectionx.arrayListOf("g", "h", "i")
                )))
        )

        assertTwoEquals(
                "a, b, c, d, e, f, g, h, i",
                arrayListOf(
                        CharSequenceIterable("abc"),
                        CharSequenceIterable("def"),
                        CharSequenceIterable("ghi")
                ).flatten().joinToString(),
                Collectionx.joinToString(Collectionx.flatten(Collectionx.arrayListOf(
                        CharSequenceIterable("abc"),
                        CharSequenceIterable("def"),
                        CharSequenceIterable("ghi")
                )))
        )
    }

    @Test
    fun testContains() {
        val list = Collectionx.arrayListOf("a", "b", "c")

        assertTrue(Collectionx.contains(list, "a"))
        assertTrue(Collectionx.contains(list, "b"))
        assertTrue(Collectionx.contains(list, "c"))
        assertFalse(Collectionx.contains(list, "d"))
    }

    @Test
    fun testElementAt() {
        val list0 = arrayListOf("a", "b", "c")
        val list1 = Collectionx.arrayListOf("a", "b", "c")

        assertTwoEquals("a", list0.elementAt(0), Collectionx.elementAt(list1, 0))
        assertTwoEquals("b", list0.elementAt(1), Collectionx.elementAt(list1, 1))
        assertTwoEquals("c", list0.elementAt(2), Collectionx.elementAt(list1, 2))
        assertTwoThrow(ArrayIndexOutOfBoundsException::class, { list0.elementAt(-1) }, { Collectionx.elementAt(list1, -1) })
        assertTwoThrow(IndexOutOfBoundsException::class, { list0.elementAt(3) }, { Collectionx.elementAt(list1, 3) })

        assertTwoEquals("a", list0.elementAtOrElse(0) { "j" }, Collectionx.elementAtOrElse(list1, 0) { "j" })
        assertTwoEquals("b", list0.elementAtOrElse(1) { "k" }, Collectionx.elementAtOrElse(list1, 1) { "j" })
        assertTwoEquals("c", list0.elementAtOrElse(2) { "j" }, Collectionx.elementAtOrElse(list1, 2) { "j" })
        assertTwoEquals("j", list0.elementAtOrElse(-1) { "j" }, Collectionx.elementAtOrElse(list1, -1) { "j" })
        assertTwoEquals("k", list0.elementAtOrElse(3) { "k" }, Collectionx.elementAtOrElse(list1, 3) { "k" })

        assertTwoEquals("a", list0.elementAtOrNull(0), Collectionx.elementAtOrNull(list1, 0))
        assertTwoEquals("b", list0.elementAtOrNull(1), Collectionx.elementAtOrNull(list1, 1))
        assertTwoEquals("c", list0.elementAtOrNull(2), Collectionx.elementAtOrNull(list1, 2))
        assertTwoEquals(null, list0.elementAtOrNull(-1), Collectionx.elementAtOrNull(list1, -1))
        assertTwoEquals(null, list0.elementAtOrNull(3), Collectionx.elementAtOrNull(list1, 3))
    }

    @Test
    fun testFind() {
        val list0 = arrayListOf("aj", "bj", "cj", "bo")
        val list1 = Collectionx.arrayListOf("aj", "bj", "cj", "bo")

        assertTwoEquals("aj", list0.find { it.startsWith("a") }, Collectionx.find(list1) { it.startsWith("a") })
        assertTwoEquals("bj", list0.find { it.startsWith("b") }, Collectionx.find(list1) { it.startsWith("b") })
        assertTwoEquals("cj", list0.find { it.startsWith("c") }, Collectionx.find(list1) { it.startsWith("c") })
        assertTwoEquals(null, list0.find { it.startsWith("k") }, Collectionx.find(list1) { it.startsWith("k") })

        assertTwoEquals("aj", list0.findLast { it.startsWith("a") }, Collectionx.findLast(list1) { it.startsWith("a") })
        assertTwoEquals("bo", list0.findLast { it.startsWith("b") }, Collectionx.findLast(list1) { it.startsWith("b") })
        assertTwoEquals("cj", list0.findLast { it.startsWith("c") }, Collectionx.findLast(list1) { it.startsWith("c") })
        assertTwoEquals(null, list0.findLast { it.startsWith("k") }, Collectionx.findLast(list1) { it.startsWith("k") })
    }

    @Test
    fun testFirst() {
        val list0 = arrayListOf("aj", "bj", "cj", "bo")
        val emptySequence0 = arrayListOf<String>()
        val list1 = Collectionx.arrayListOf("aj", "bj", "cj", "bo")
        val emptySequence1 = Collectionx.arrayListOf<String>()

        assertTwoEquals("aj", list0.first(), Collectionx.first(list1))
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.first() }, { Collectionx.first(emptySequence1) })

        assertTwoEquals("aj", list0.first { it.startsWith("a") }, Collectionx.first(list1) { it.startsWith("a") })
        assertTwoEquals("bj", list0.first { it.startsWith("b") }, Collectionx.first(list1) { it.startsWith("b") })
        assertTwoEquals("cj", list0.first { it.startsWith("c") }, Collectionx.first(list1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { list0.first { it.startsWith("k") } }, { Collectionx.first(list1) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.first { it.startsWith("a") } }, { Collectionx.first(emptySequence1) { it.startsWith("a") } })

        assertTwoEquals("aj", list0.firstOrNull(), Collectionx.firstOrNull(list1))
        assertTwoEquals(null, emptySequence0.firstOrNull(), Collectionx.firstOrNull(emptySequence1))

        assertTwoEquals("aj", list0.firstOrNull { it.startsWith("a") }, Collectionx.firstOrNull(list1) { it.startsWith("a") })
        assertTwoEquals("bj", list0.firstOrNull { it.startsWith("b") }, Collectionx.firstOrNull(list1) { it.startsWith("b") })
        assertTwoEquals("cj", list0.firstOrNull { it.startsWith("c") }, Collectionx.firstOrNull(list1) { it.startsWith("c") })
        assertTwoEquals(null, list0.firstOrNull { it.startsWith("k") }, Collectionx.firstOrNull(list1) { it.startsWith("k") })
        assertTwoEquals(null, emptySequence0.firstOrNull { it.startsWith("k") }, Collectionx.firstOrNull(emptySequence1) { it.startsWith("k") })
    }

    @Test
    fun testIndexOf() {
        val list0 = arrayListOf("aj", "bj", "cj", "bo")
        val list1 = Collectionx.arrayListOf("aj", "bj", "cj", "bo")

        assertTwoEquals(0, list0.indexOf("aj"), Collectionx.indexOf(list1, "aj"))
        assertTwoEquals(1, list0.indexOf("bj"), Collectionx.indexOf(list1, "bj"))
        assertTwoEquals(2, list0.indexOf("cj"), Collectionx.indexOf(list1, "cj"))
        assertTwoEquals(3, list0.indexOf("bo"), Collectionx.indexOf(list1, "bo"))
        assertTwoEquals(-1, list0.indexOf("bb"), Collectionx.indexOf(list1, "bb"))

        assertTwoEquals(0, list0.indexOfFirst { it.startsWith("a") }, Collectionx.indexOfFirst(list1) { it.startsWith("a") })
        assertTwoEquals(1, list0.indexOfFirst { it.startsWith("b") }, Collectionx.indexOfFirst(list1) { it.startsWith("b") })
        assertTwoEquals(2, list0.indexOfFirst { it.startsWith("c") }, Collectionx.indexOfFirst(list1) { it.startsWith("c") })
        assertTwoEquals(-1, list0.indexOfFirst { it.startsWith("k") }, Collectionx.indexOfFirst(list1) { it.startsWith("k") })

        assertTwoEquals(0, list0.indexOfLast { it.startsWith("a") }, Collectionx.indexOfLast(list1) { it.startsWith("a") })
        assertTwoEquals(3, list0.indexOfLast { it.startsWith("b") }, Collectionx.indexOfLast(list1) { it.startsWith("b") })
        assertTwoEquals(2, list0.indexOfLast { it.startsWith("c") }, Collectionx.indexOfLast(list1) { it.startsWith("c") })
        assertTwoEquals(-1, list0.indexOfLast { it.startsWith("k") }, Collectionx.indexOfLast(list1) { it.startsWith("k") })
    }

    @Test
    fun testLast() {
        val list0 = arrayListOf("aj", "bj", "cj", "bo")
        val emptySequence0 = arrayListOf<String>()
        val list1 = Collectionx.arrayListOf("aj", "bj", "cj", "bo")
        val emptySequence1 = Collectionx.arrayListOf<String>()

        assertTwoEquals("bo", list0.last(), Collectionx.last(list1))
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.last() }, { Collectionx.last(emptySequence1) })

        assertTwoEquals("aj", list0.last { it.startsWith("a") }, Collectionx.last(list1) { it.startsWith("a") })
        assertTwoEquals("bo", list0.last { it.startsWith("b") }, Collectionx.last(list1) { it.startsWith("b") })
        assertTwoEquals("cj", list0.last { it.startsWith("c") }, Collectionx.last(list1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { list0.last { it.startsWith("k") } }, { Collectionx.last(list1) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.last { it.startsWith("a") } }, { Collectionx.last(emptySequence1) { it.startsWith("a") } })

        assertTwoEquals("bo", list0.lastOrNull(), Collectionx.lastOrNull(list1))
        assertTwoEquals(null, emptySequence0.lastOrNull(), Collectionx.lastOrNull(emptySequence1))

        assertTwoEquals("aj", list0.lastOrNull { it.startsWith("a") }, Collectionx.lastOrNull(list1) { it.startsWith("a") })
        assertTwoEquals("bo", list0.lastOrNull { it.startsWith("b") }, Collectionx.lastOrNull(list1) { it.startsWith("b") })
        assertTwoEquals("cj", list0.lastOrNull { it.startsWith("c") }, Collectionx.lastOrNull(list1) { it.startsWith("c") })
        assertTwoEquals(null, list0.lastOrNull { it.startsWith("k") }, Collectionx.lastOrNull(list1) { it.startsWith("k") })
        assertTwoEquals(null, emptySequence0.lastOrNull { it.startsWith("k") }, Collectionx.lastOrNull(emptySequence1) { it.startsWith("k") })

        assertTwoEquals(0, list0.lastIndexOf("aj"), Collectionx.lastIndexOf(list1, "aj"))
        assertTwoEquals(1, list0.lastIndexOf("bj"), Collectionx.lastIndexOf(list1, "bj"))
        assertTwoEquals(2, list0.lastIndexOf("cj"), Collectionx.lastIndexOf(list1, "cj"))
        assertTwoEquals(3, list0.lastIndexOf("bo"), Collectionx.lastIndexOf(list1, "bo"))
        assertTwoEquals(-1, list0.lastIndexOf("bb"), Collectionx.lastIndexOf(list1, "bb"))
    }

    @Test
    fun testSingle() {
        val singleSequence0 = arrayListOf("cj")
        val singleSequence1 = Collectionx.arrayListOf("cj")
        val multiSequence0 = arrayListOf("aj", "bj", "cj", "bo")
        val multiSequence1 = Collectionx.arrayListOf("aj", "bj", "cj", "bo")
        val emptySequence0 = arrayListOf<String>()
        val emptySequence1 = Collectionx.arrayListOf<String>()

        assertTwoEquals("cj", singleSequence0.single(), Collectionx.single(singleSequence1))
        assertTwoThrow(NoSuchElementException::class, { emptySequence0.single() }, { Collectionx.single(emptySequence1) })
        assertTwoThrow(IllegalArgumentException::class, { multiSequence0.single() }, { Collectionx.single(multiSequence1) })

        assertTwoEquals("cj",
                singleSequence0.single { it.startsWith("c") },
                Collectionx.single(singleSequence1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class,
                { singleSequence0.single { it.startsWith("b") } },
                { Collectionx.single(singleSequence1) { it.startsWith("b") } })
        assertTwoThrow(NoSuchElementException::class,
                { emptySequence0.single { it.startsWith("c") } },
                { Collectionx.single(emptySequence1) { it.startsWith("c") } })
        assertTwoEquals("cj",
                multiSequence0.single { it.startsWith("c") },
                Collectionx.single(multiSequence1) { it.startsWith("c") })
        assertTwoThrow(IllegalArgumentException::class,
                { multiSequence0.single { it.startsWith("b") } },
                { Collectionx.single(multiSequence1) { it.startsWith("b") } })

        assertTwoEquals("cj", singleSequence0.singleOrNull(), Collectionx.singleOrNull(singleSequence1))
        assertTwoEquals(null, emptySequence0.singleOrNull(), Collectionx.singleOrNull(emptySequence1))
        assertTwoEquals(null, multiSequence0.singleOrNull(), Collectionx.singleOrNull(multiSequence1))

        assertTwoEquals("cj",
                singleSequence0.singleOrNull { it.startsWith("c") },
                Collectionx.singleOrNull(singleSequence1) { it.startsWith("c") })
        assertTwoEquals(null,
                singleSequence0.singleOrNull { it.startsWith("b") },
                Collectionx.singleOrNull(singleSequence1) { it.startsWith("b") })
        assertTwoEquals(null,
                emptySequence0.singleOrNull { it.startsWith("c") },
                Collectionx.singleOrNull(emptySequence1) { it.startsWith("c") })
        assertTwoEquals("cj",
                multiSequence0.singleOrNull { it.startsWith("c") },
                Collectionx.singleOrNull(multiSequence1) { it.startsWith("c") })
        assertTwoEquals(null,
                multiSequence0.singleOrNull { it.startsWith("b") },
                Collectionx.singleOrNull(multiSequence1) { it.startsWith("b") })
    }

    @Test
    fun testDrop() {
        val list0 = arrayListOf("aj", "bj", "cj", "dj")
        val list1 = Collectionx.arrayListOf("aj", "bj", "cj", "dj")

        /*
         * drop 系列的方法表示从列表头部开始跳过部分元素
         */

        // drop 方法的意思是从列表头部开始跳过多少个元素

        assertTwoThrow(IllegalArgumentException::class,
                { list0.drop(-1) },
                { Collectionx.drop(list1, -1) }
        )

        assertTrue(list0 == list0.drop(0))
        assertTrue(list1 == Collectionx.drop(list1, 0))
        assertTrue(list0 !== list0.drop(0))
        assertTrue(list1 !== Collectionx.drop(list1, 0))

        assertTwoEquals("bj, cj, dj",
                list0.drop(1).joinToString(),
                Collectionx.joinToString(Collectionx.drop(list1, 1)))

        assertTwoEquals("cj, dj",
                list0.drop(2).joinToString(),
                Collectionx.joinToString(Collectionx.drop(list1, 2)))

        assertTwoEquals("dj",
                list0.drop(3).joinToString(),
                Collectionx.joinToString(Collectionx.drop(list1, 3)))

        assertTwoEquals("",
                list0.drop(4).joinToString(),
                Collectionx.joinToString(Collectionx.drop(list1, 4)))

        assertTwoEquals("",
                list0.drop(5).joinToString(),
                Collectionx.joinToString(Collectionx.drop(list1, 5)))

        assertTwoEquals("",
                list0.drop(5).joinToString(),
                Collectionx.joinToString(Collectionx.drop(Collectionx.drop(list1, 5), 5)))

        // dropWhile 方法的意思是从不符合条件的元素开始往后遍历

        assertTwoEquals("aj, bj, cj, dj",
                list0.dropWhile { !it.startsWith("a") }.joinToString(),
                Collectionx.joinToString(Collectionx.dropWhile(list1) { !it.startsWith("a") }))

        assertTwoEquals("bj, cj, dj",
                list0.dropWhile { !it.startsWith("b") }.joinToString(),
                Collectionx.joinToString(Collectionx.dropWhile(list1) { !it.startsWith("b") }))

        assertTwoEquals("cj, dj",
                list0.dropWhile { !it.startsWith("c") }.joinToString(),
                Collectionx.joinToString(Collectionx.dropWhile(list1) { !it.startsWith("c") }))

        assertTwoEquals("dj",
                list0.dropWhile { !it.startsWith("d") }.joinToString(),
                Collectionx.joinToString(Collectionx.dropWhile(list1) { !it.startsWith("d") }))

        assertTwoEquals("",
                list0.dropWhile { !it.startsWith("e") }.joinToString(),
                Collectionx.joinToString(Collectionx.dropWhile(list1) { !it.startsWith("e") }))


        assertTwoEquals("dj",
                list0.dropWhile { !it.startsWith("b") }.drop(2).joinToString(),
                Collectionx.joinToString(Collectionx.drop(Collectionx.dropWhile(list1) { !it.startsWith("b") }, 2)))
    }

    @Test
    fun testTake() {
        val list0 = arrayListOf("aj", "bj", "cj", "dj")
        val list1 = Collectionx.arrayListOf("aj", "bj", "cj", "dj")

        /*
         * take 系列的方法表示从列表头部开始取部分元素
         */

        // take 方法的意思是从列表头部开始取多少个元素

        assertTwoThrow(IllegalArgumentException::class,
                { list0.take(-1) },
                { Collectionx.take(list1, -1) }
        )

        assertTrue(emptyList<String>() === list0.take(0))
        assertTrue(Collectionx.emptyList<String>() === Collectionx.take(list1, 0))

        assertTwoEquals("aj",
                list0.take(1).joinToString(),
                Collectionx.joinToString(Collectionx.take(list1, 1)))

        assertTwoEquals("aj, bj",
                list0.take(2).joinToString(),
                Collectionx.joinToString(Collectionx.take(list1, 2)))

        assertTwoEquals("aj, bj, cj",
                list0.take(3).joinToString(),
                Collectionx.joinToString(Collectionx.take(list1, 3)))

        assertTwoEquals("aj, bj, cj, dj",
                list0.take(4).joinToString(),
                Collectionx.joinToString(Collectionx.take(list1, 4)))

        assertTwoEquals("aj, bj, cj, dj",
                list0.take(5).joinToString(),
                Collectionx.joinToString(Collectionx.take(list1, 5)))

        assertTwoEquals("aj, bj, cj, dj",
                list0.take(5).joinToString(),
                Collectionx.joinToString(Collectionx.take(Collectionx.take(list1, 5), 5)))

        // takeWhile 方法的意思是从列表头部开始到不符合条件的元素（不含包）终止

        assertTwoEquals("",
                list0.takeWhile { !it.startsWith("a") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeWhile(list1) { !it.startsWith("a") }))

        assertTwoEquals("aj",
                list0.takeWhile { !it.startsWith("b") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeWhile(list1) { !it.startsWith("b") }))

        assertTwoEquals("aj, bj",
                list0.takeWhile { !it.startsWith("c") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeWhile(list1) { !it.startsWith("c") }))

        assertTwoEquals("aj, bj, cj",
                list0.takeWhile { !it.startsWith("d") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeWhile(list1) { !it.startsWith("d") }))

        assertTwoEquals("aj, bj, cj, dj",
                list0.takeWhile { !it.startsWith("e") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeWhile(list1) { !it.startsWith("e") }))


        assertTwoEquals("aj, bj",
                list0.takeWhile { !it.startsWith("d") }.take(2).joinToString(),
                Collectionx.joinToString(Collectionx.take(Collectionx.takeWhile(list1) { !it.startsWith("d") }, 2)))
    }

    @Test
    fun testFilter() {
        val list0 = arrayListOf("aj", "bo", "cj", "do")
        val list1 = Collectionx.arrayListOf("aj", "bo", "cj", "do")

        assertTwoEquals("aj, cj",
                list0.filter { it.endsWith("j") }.joinToString(),
                Collectionx.joinToString(Collectionx.filter(list1) { it.endsWith("j") }))

        val filterToDestination = java.util.ArrayList<String>()
        val filterToDestination1 = java.util.ArrayList<String>()
        val filterToDestinationResult = list0.filterTo(filterToDestination) { it.endsWith("j") }
        val filterToDestinationResult1 = Collectionx.filterTo(list1, filterToDestination1) { it.endsWith("j") }
        assertTwoEquals("aj, cj",
                filterToDestinationResult.joinToString(),
                Collectionx.joinToString(filterToDestinationResult1))
        assertTrue(filterToDestination === filterToDestinationResult)
        assertTrue(filterToDestination1 === filterToDestinationResult1)

        assertTwoEquals("bo, do",
                list0.filterIndexed { index, _ -> (index % 2) != 0 }.joinToString(),
                Collectionx.joinToString(Collectionx.filterIndexed(list1) { index, _ -> (index % 2) != 0 }))

        val filterIndexedToDestination = java.util.ArrayList<String>()
        val filterIndexedToDestination1 = java.util.ArrayList<String>()
        val filterIndexedToDestinationResult = list0.filterIndexedTo(filterIndexedToDestination) { index, _ -> (index % 2) != 0 }
        val filterIndexedToDestinationResult1 = Collectionx.filterIndexedTo(list1, filterIndexedToDestination1) { index, _ -> (index % 2) != 0 }
        assertTwoEquals("bo, do",
                filterIndexedToDestinationResult.joinToString(),
                Collectionx.joinToString(filterIndexedToDestinationResult1))
        assertTrue(filterIndexedToDestination === filterIndexedToDestinationResult)
        assertTrue(filterIndexedToDestination1 === filterIndexedToDestinationResult1)


        val anyList0 = arrayListOf(4, "f", 76, "gsdg")
        val anyList1 = Collectionx.arrayListOf(4, "f", 76, "gsdg")

        assertTwoEquals("4, 76",
                anyList0.filterIsInstance(Integer::class.java).joinToString(),
                Collectionx.joinToString(Collectionx.filterIsInstance(anyList1, Integer::class.java)))
        assertTwoEquals("f, gsdg",
                anyList0.filterIsInstance(String::class.java).joinToString(),
                Collectionx.joinToString(Collectionx.filterIsInstance(anyList1, String::class.java)))

        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN") val filterIsInstanceToDestination = java.util.ArrayList<Integer>()
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN") val filterIsInstanceToDestination1 = arrayListOf<Integer>()
        val filterIsInstanceToDestinationResult = anyList0.filterIsInstanceTo(filterIsInstanceToDestination, Integer::class.java)
        val filterIsInstanceToDestinationResult1 = Collectionx.filterIsInstanceTo(anyList1, filterIsInstanceToDestination1, Integer::class.java)
        assertTwoEquals("4, 76",
                filterIsInstanceToDestinationResult.joinToString(),
                Collectionx.joinToString(filterIsInstanceToDestinationResult1))
        assertTrue(filterIsInstanceToDestination === filterIsInstanceToDestinationResult)
        assertTrue(filterIsInstanceToDestination1 === filterIsInstanceToDestinationResult1)

        assertTwoEquals("bo, do",
                list0.filterNot { it.endsWith("j") }.joinToString(),
                Collectionx.joinToString(Collectionx.filterNot(list1) { it.endsWith("j") }))

        val filterNotToDestination = java.util.ArrayList<String>()
        val filterNotToDestination1 = java.util.ArrayList<String>()
        val filterNotToDestinationResult = list0.filterNotTo(filterNotToDestination) { it.endsWith("j") }
        val filterNotToDestinationResult1 = Collectionx.filterNotTo(list1, filterNotToDestination1) { it.endsWith("j") }
        assertTwoEquals("bo, do",
                filterNotToDestinationResult.joinToString(),
                Collectionx.joinToString(filterNotToDestinationResult1))
        assertTrue(filterNotToDestination === filterNotToDestinationResult)
        assertTrue(filterNotToDestination1 === filterNotToDestinationResult1)


        val notNullList0 = arrayListOf(null, "f", null, "gsdg")
        val notNullList1 = Collectionx.arrayListOf(null, "f", null, "gsdg")

        assertTwoEquals("f, gsdg",
                notNullList0.filterNotNull().joinToString(),
                Collectionx.joinToString(Collectionx.filterNotNull(notNullList1)))

        val filterNotNullToDestination = java.util.ArrayList<String>()
        val filterNotNullToDestination1 = java.util.ArrayList<String>()
        val filterNotNullToDestinationResult = notNullList0.filterNotNullTo(filterNotNullToDestination)
        val filterNotNullToDestinationResult1 = Collectionx.filterNotNullTo(notNullList1, filterNotNullToDestination1)
        assertTwoEquals("f, gsdg",
                filterNotNullToDestinationResult.joinToString(),
                Collectionx.joinToString(filterNotNullToDestinationResult1))
        assertTrue(filterNotNullToDestination === filterNotNullToDestinationResult)
        assertTrue(filterNotNullToDestination1 === filterNotNullToDestinationResult1)
    }

    @Test
    fun testSort() {
        val list0 = arrayListOf("aaa", "h", "uuuu", "gg")
        val list1 = Collectionx.arrayListOf("aaa", "h", "uuuu", "gg")

        assertTwoEquals("aaa, gg, h, uuuu",
                list0.sorted().joinToString(),
                Collectionx.joinToString(Collectionx.sorted(list1)))

        assertTwoEquals("uuuu, h, gg, aaa",
                list0.sortedDescending().joinToString(),
                Collectionx.joinToString(Collectionx.sortedDescending(list1)))

        assertTwoEquals("h, gg, aaa, uuuu",
                list0.sortedBy { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedBy(list1) { it.length }))

        assertTwoEquals("uuuu, aaa, gg, h",
                list0.sortedByDescending { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedByDescending(list1) { it.length }))

        assertTwoEquals("aaa, gg, h, uuuu",
                list0.sortedWith { it1, it2 -> it1.compareTo(it2) }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedWith(list1) { it1, it2 -> it1.compareTo(it2) }))
    }

    @Test
    @Suppress("ReplaceAssociateFunction")
    fun testAssociate() {
        val list0 = arrayListOf("aj", "bj", "ao", "bo")
        val list1 = Collectionx.arrayListOf("aj", "bj", "ao", "bo")

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                list0.associate { it.first().toString() to it },
                Collectionx.associate(list1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) },
        )

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                list0.associateBy { it.first().toString() },
                Collectionx.associateBy(list1) { it.first().toString() },
        )

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                list0.associateBy({ it.first().toString() }, { it }),
                Collectionx.associateBy(list1, { it.first().toString() }, { it }),
        )

        val associateToMap0 = HashMap<String, String>()
        val associateToMap1 = HashMap<String, String>()
        val associateToMapResult0 = list0.associateTo(associateToMap0) { it.first().toString() to it }
        val associateToMapResult1 = Collectionx.associateTo(list1, associateToMap1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) }
        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                associateToMap0,
                associateToMap1,
        )
        assertTrue(associateToMap0 === associateToMapResult0)
        assertTrue(associateToMap1 === associateToMapResult1)

        val associateByTo1Map0 = HashMap<String, String>()
        val associateByTo1Map1 = HashMap<String, String>()
        val associateByTo1MapResult0 = list0.associateByTo(associateByTo1Map0) { it.first().toString() }
        val associateByTo1MapResult1 = Collectionx.associateByTo(list1, associateByTo1Map1) { it.first().toString() }
        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                associateByTo1Map0,
                associateByTo1Map1,
        )
        assertTrue(associateByTo1Map0 === associateByTo1MapResult0)
        assertTrue(associateByTo1Map1 === associateByTo1MapResult1)

        val associateByTo2Map0 = HashMap<String, String>()
        val associateByTo2Map1 = HashMap<String, String>()
        val associateByTo2MapResult0 = list0.associateByTo(associateByTo2Map0, { it.first().toString() }, { it })
        val associateByTo2MapResult1 = Collectionx.associateByTo(list1, associateByTo2Map1, { it.first().toString() }, { it })
        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                associateByTo2Map0,
                associateByTo2Map1,
        )
        assertTrue(associateByTo2Map0 === associateByTo2MapResult0)
        assertTrue(associateByTo2Map1 === associateByTo2MapResult1)
    }



    @Test
    fun testFlatMap(){
        val list0 = listOf("aj", "bj", "ao", "bo")
        val list1 = Collectionx.arrayListOf("aj", "bj", "ao", "bo")

        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "a, j, b, j, a, o, b, o",
                list0.flatMap { it -> it.toCharArray().map { it.toString() } }.joinToString(),
                Collectionx.joinToString(Collectionx.flatMap(list1) { it -> it.toCharArray().map { it.toString() } }),
        )

        val flatMapToList0 = java.util.ArrayList<String>()
        val flatMapToListResult0 = list0.flatMapTo(flatMapToList0) { it -> it.toCharArray().map { it.toString() } }
        val flatMapToList1 = java.util.ArrayList<String>()
        val flatMapToListResult1 = Collectionx.flatMapTo(list1, flatMapToList1) { it -> it.toCharArray().map { it.toString() } }
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
        assertTwoEquals(
                "0-a, 0-j, 1-b, 1-j, 2-a, 2-o, 3-b, 3-o",
                list0.flatMapIndexed { index, it -> it.toCharArray().map { "${index}-$it" } }.joinToString(),
                Collectionx.joinToString(Collectionx.flatMapIndexed(list1) {index, it -> it.toCharArray().map { "${index}-$it" } }),
        )

        val flatMapIndexedToList0 = java.util.ArrayList<String>()
        val flatMapIndexedToListResult0 = list0.flatMapIndexedTo(flatMapIndexedToList0) {index, it ->  it.toCharArray().map { "${index}-$it" } }
        val flatMapIndexedToList1 = java.util.ArrayList<String>()
        val flatMapIndexedToListResult1 = Collectionx.flatMapIndexedTo(list1, flatMapIndexedToList1) {index, it ->  it.toCharArray().map { "${index}-$it" } }
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
    }
}