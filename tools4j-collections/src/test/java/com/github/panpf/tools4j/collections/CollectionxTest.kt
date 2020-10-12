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
import com.github.panpf.tools4j.test.ktx.assertThrow
import com.github.panpf.tools4j.test.ktx.assertTwoEquals
import com.github.panpf.tools4j.test.ktx.assertTwoThrow
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
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
        val normalList0 = listOf("6", "3", "7", "2", "1")
        val normalList1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null

        assertTwoEquals("[60, 30, 70, 20, 10]",
                normalList0.joinToString(transform = { it + "0" }, prefix = "[", postfix = "]"),
                Collectionx.joinToArrayString(normalList1) { it + "0" })
        assertTwoEquals("[]",
                emptyList0.joinToString(transform = { it + "0" }, prefix = "[", postfix = "]"),
                Collectionx.joinToArrayString(emptyList1) { it + "0" })
        assertEquals("[]",
                Collectionx.joinToArrayString(nullList0) { it + "0" })

        assertTwoEquals("[6, 3, 7, 2, 1]",
                normalList0.joinToString(prefix = "[", postfix = "]"),
                Collectionx.joinToArrayString(normalList1))
        assertTwoEquals("[]",
                emptyList0.joinToString(prefix = "[", postfix = "]"),
                Collectionx.joinToArrayString(emptyList1))
        assertEquals("[]",
                Collectionx.joinToArrayString(nullList0))
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
                listOf(
                        listOf("a", "b", "c"),
                        listOf("d", "e", "f"),
                        listOf("g", "h", "i")
                ).flatten().joinToString(),
                Collectionx.joinToString(Collectionx.flatten(Collectionx.listOf(
                        Collectionx.listOf("a", "b", "c"),
                        Collectionx.listOf("d", "e", "f"),
                        Collectionx.listOf("g", "h", "i")
                )))
        )

        assertTwoEquals(
                "a, b, c, d, e, f, g, h, i",
                listOf(
                        CharSequenceIterable("abc"),
                        CharSequenceIterable("def"),
                        CharSequenceIterable("ghi")
                ).flatten().joinToString(),
                Collectionx.joinToString(Collectionx.flatten(Collectionx.listOf(
                        CharSequenceIterable("abc"),
                        CharSequenceIterable("def"),
                        CharSequenceIterable("ghi")
                )))
        )
    }

    @Test
    fun testContains() {
        val list = Collectionx.listOf("a", "b", "c")

        assertTrue(Collectionx.contains(list, "a"))
        assertTrue(Collectionx.contains(list, "b"))
        assertTrue(Collectionx.contains(list, "c"))
        assertFalse(Collectionx.contains(list, "d"))
    }

    @Test
    fun testElementAt() {
        val list0 = listOf("a", "b", "c")
        val list1 = Collectionx.listOf("a", "b", "c")
        val nullList = null as List<String>?

        assertTwoEquals("a", list0.elementAt(0), Collectionx.elementAt(list1, 0))
        assertTwoEquals("b", list0.elementAt(1), Collectionx.elementAt(list1, 1))
        assertTwoEquals("c", list0.elementAt(2), Collectionx.elementAt(list1, 2))
        assertTwoThrow(ArrayIndexOutOfBoundsException::class, { list0.elementAt(-1) }, { Collectionx.elementAt(list1, -1) })
        assertTwoThrow(ArrayIndexOutOfBoundsException::class, { list0.elementAt(3) }, { Collectionx.elementAt(list1, 3) })

        assertTwoEquals("a", list0.elementAtOrElse(0) { "j" }, Collectionx.elementAtOrElse(list1, 0) { "j" })
        assertTwoEquals("b", list0.elementAtOrElse(1) { "k" }, Collectionx.elementAtOrElse(list1, 1) { "j" })
        assertTwoEquals("c", list0.elementAtOrElse(2) { "j" }, Collectionx.elementAtOrElse(list1, 2) { "j" })
        assertTwoEquals("j", list0.elementAtOrElse(-1) { "j" }, Collectionx.elementAtOrElse(list1, -1) { "j" })
        assertTwoEquals("k", list0.elementAtOrElse(3) { "k" }, Collectionx.elementAtOrElse(list1, 3) { "k" })
        assertEquals("x", Collectionx.elementAtOrElse(nullList, 0) { "x" })
        assertEquals("y", Collectionx.elementAtOrElse(nullList, 1) { "y" })
        assertEquals("z", Collectionx.elementAtOrElse(nullList, 2) { "z" })
        assertEquals("j", Collectionx.elementAtOrElse(nullList, -1) { "j" })
        assertEquals("k", Collectionx.elementAtOrElse(nullList, 3) { "k" })

        assertTwoEquals("a", list0.elementAtOrNull(0), Collectionx.elementAtOrNull(list1, 0))
        assertTwoEquals("b", list0.elementAtOrNull(1), Collectionx.elementAtOrNull(list1, 1))
        assertTwoEquals("c", list0.elementAtOrNull(2), Collectionx.elementAtOrNull(list1, 2))
        assertTwoEquals(null, list0.elementAtOrNull(-1), Collectionx.elementAtOrNull(list1, -1))
        assertTwoEquals(null, list0.elementAtOrNull(3), Collectionx.elementAtOrNull(list1, 3))
        assertNull(Collectionx.elementAtOrNull(nullList, 0))
        assertNull(Collectionx.elementAtOrNull(nullList, 1))
        assertNull(Collectionx.elementAtOrNull(nullList, 2))
        assertNull(Collectionx.elementAtOrNull(nullList, -1))
        assertNull(Collectionx.elementAtOrNull(nullList, 3))
    }

    @Test
    fun testFind() {
        val list0 = listOf("aj", "bj", "cj", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "cj", "bo")

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
        val list0 = listOf("aj", "bj", "cj", "bo")
        val emptyList0 = listOf<String>()
        val list1 = Collectionx.listOf("aj", "bj", "cj", "bo")
        val emptyList1 = Collectionx.listOf<String>()
        val nullList1 = null as List<String>?

        assertTwoEquals("aj", list0.first(), Collectionx.first(list1))
        assertTwoThrow(NoSuchElementException::class, { emptyList0.first() }, { Collectionx.first(emptyList1) })
        assertThrow(NoSuchElementException::class) { Collectionx.first(nullList1) }

        assertTwoEquals("aj", list0.first { it.startsWith("a") }, Collectionx.first(list1) { it.startsWith("a") })
        assertTwoEquals("bj", list0.first { it.startsWith("b") }, Collectionx.first(list1) { it.startsWith("b") })
        assertTwoEquals("cj", list0.first { it.startsWith("c") }, Collectionx.first(list1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { list0.first { it.startsWith("k") } }, { Collectionx.first(list1) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptyList0.first { it.startsWith("a") } }, { Collectionx.first(emptyList1) { it.startsWith("a") } })
        assertThrow(NoSuchElementException::class) { Collectionx.first(nullList1) { it.startsWith("a") } }

        assertTwoEquals("aj", list0.firstOrNull(), Collectionx.firstOrNull(list1))
        assertTwoEquals(null, emptyList0.firstOrNull(), Collectionx.firstOrNull(emptyList1))
        assertNull(Collectionx.firstOrNull(nullList1))

        assertTwoEquals("aj", list0.firstOrNull { it.startsWith("a") }, Collectionx.firstOrNull(list1) { it.startsWith("a") })
        assertTwoEquals("bj", list0.firstOrNull { it.startsWith("b") }, Collectionx.firstOrNull(list1) { it.startsWith("b") })
        assertTwoEquals("cj", list0.firstOrNull { it.startsWith("c") }, Collectionx.firstOrNull(list1) { it.startsWith("c") })
        assertTwoEquals(null, list0.firstOrNull { it.startsWith("k") }, Collectionx.firstOrNull(list1) { it.startsWith("k") })
        assertTwoEquals(null, emptyList0.firstOrNull { it.startsWith("k") }, Collectionx.firstOrNull(emptyList1) { it.startsWith("k") })
        assertNull(Collectionx.firstOrNull(nullList1) { it.startsWith("k") })
    }

    @Test
    fun testIndexOf() {
        val list0 = listOf("aj", "bj", "cj", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "cj", "bo")
        val nullList1 = null as List<String>?

        assertTwoEquals(0, list0.indexOf("aj"), Collectionx.indexOf(list1, "aj"))
        assertTwoEquals(1, list0.indexOf("bj"), Collectionx.indexOf(list1, "bj"))
        assertTwoEquals(2, list0.indexOf("cj"), Collectionx.indexOf(list1, "cj"))
        assertTwoEquals(3, list0.indexOf("bo"), Collectionx.indexOf(list1, "bo"))
        assertTwoEquals(-1, list0.indexOf("bb"), Collectionx.indexOf(list1, "bb"))
        assertEquals(-1, Collectionx.indexOf(nullList1, "bb"))

        assertTwoEquals(0, list0.indexOfFirst { it.startsWith("a") }, Collectionx.indexOfFirst(list1) { it.startsWith("a") })
        assertTwoEquals(1, list0.indexOfFirst { it.startsWith("b") }, Collectionx.indexOfFirst(list1) { it.startsWith("b") })
        assertTwoEquals(2, list0.indexOfFirst { it.startsWith("c") }, Collectionx.indexOfFirst(list1) { it.startsWith("c") })
        assertTwoEquals(-1, list0.indexOfFirst { it.startsWith("k") }, Collectionx.indexOfFirst(list1) { it.startsWith("k") })
        assertEquals(-1, Collectionx.indexOfFirst(nullList1) { it.startsWith("k") })

        assertTwoEquals(0, list0.indexOfLast { it.startsWith("a") }, Collectionx.indexOfLast(list1) { it.startsWith("a") })
        assertTwoEquals(3, list0.indexOfLast { it.startsWith("b") }, Collectionx.indexOfLast(list1) { it.startsWith("b") })
        assertTwoEquals(2, list0.indexOfLast { it.startsWith("c") }, Collectionx.indexOfLast(list1) { it.startsWith("c") })
        assertTwoEquals(-1, list0.indexOfLast { it.startsWith("k") }, Collectionx.indexOfLast(list1) { it.startsWith("k") })
        assertEquals(-1, Collectionx.indexOfLast(nullList1) { it.startsWith("k") })
    }

    @Test
    fun testLast() {
        val list0 = listOf("aj", "bj", "cj", "bo")
        val emptyList0 = listOf<String>()
        val list1 = Collectionx.listOf("aj", "bj", "cj", "bo")
        val emptyList1 = Collectionx.listOf<String>()
        val nullList1 = null as List<String>?

        assertTwoEquals("bo", list0.last(), Collectionx.last(list1))
        assertTwoThrow(NoSuchElementException::class, { emptyList0.last() }, { Collectionx.last(emptyList1) })
        assertThrow(NoSuchElementException::class) { Collectionx.last(nullList1) }

        assertTwoEquals("aj", list0.last { it.startsWith("a") }, Collectionx.last(list1) { it.startsWith("a") })
        assertTwoEquals("bo", list0.last { it.startsWith("b") }, Collectionx.last(list1) { it.startsWith("b") })
        assertTwoEquals("cj", list0.last { it.startsWith("c") }, Collectionx.last(list1) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { list0.last { it.startsWith("k") } }, { Collectionx.last(list1) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptyList0.last { it.startsWith("a") } }, { Collectionx.last(emptyList1) { it.startsWith("a") } })
        assertThrow(NoSuchElementException::class) { Collectionx.last(nullList1) { it.startsWith("a") } }

        assertTwoEquals("bo", list0.lastOrNull(), Collectionx.lastOrNull(list1))
        assertTwoEquals(null, emptyList0.lastOrNull(), Collectionx.lastOrNull(emptyList1))
        assertNull(Collectionx.lastOrNull(nullList1))

        assertTwoEquals("aj", list0.lastOrNull { it.startsWith("a") }, Collectionx.lastOrNull(list1) { it.startsWith("a") })
        assertTwoEquals("bo", list0.lastOrNull { it.startsWith("b") }, Collectionx.lastOrNull(list1) { it.startsWith("b") })
        assertTwoEquals("cj", list0.lastOrNull { it.startsWith("c") }, Collectionx.lastOrNull(list1) { it.startsWith("c") })
        assertTwoEquals(null, list0.lastOrNull { it.startsWith("k") }, Collectionx.lastOrNull(list1) { it.startsWith("k") })
        assertTwoEquals(null, emptyList0.lastOrNull { it.startsWith("k") }, Collectionx.lastOrNull(emptyList1) { it.startsWith("k") })
        assertNull(Collectionx.lastOrNull(nullList1) { it.startsWith("k") })

        assertTwoEquals(0, list0.lastIndexOf("aj"), Collectionx.lastIndexOf(list1, "aj"))
        assertTwoEquals(1, list0.lastIndexOf("bj"), Collectionx.lastIndexOf(list1, "bj"))
        assertTwoEquals(2, list0.lastIndexOf("cj"), Collectionx.lastIndexOf(list1, "cj"))
        assertTwoEquals(3, list0.lastIndexOf("bo"), Collectionx.lastIndexOf(list1, "bo"))
        assertTwoEquals(-1, list0.lastIndexOf("bb"), Collectionx.lastIndexOf(list1, "bb"))
        assertEquals(-1, Collectionx.lastIndexOf(nullList1, "bb"))
    }

    @Test
    fun testSingle() {
        val singleIterable0 = listOf("cj")
        val singleIterable1 = Collectionx.listOf("cj")
        val multiIterable0 = listOf("aj", "bj", "cj", "bo")
        val multiIterable1 = Collectionx.listOf("aj", "bj", "cj", "bo")
        val emptyIterable0 = listOf<String>()
        val emptyIterable1 = Collectionx.listOf<String>()
        val nullList1 = null as List<String>?

        assertTwoEquals("cj", singleIterable0.single(), Collectionx.single(singleIterable1))
        assertTwoThrow(IllegalArgumentException::class, { multiIterable0.single() }, { Collectionx.single(multiIterable1) })
        assertTwoThrow(NoSuchElementException::class, { emptyIterable0.single() }, { Collectionx.single(emptyIterable1) })
        assertThrow(NoSuchElementException::class) { Collectionx.single(nullList1) }

        assertTwoEquals("cj",
                singleIterable0.single { it.startsWith("c") },
                Collectionx.single(singleIterable1) { it.startsWith("c") })
        assertTwoEquals("cj",
                multiIterable0.single { it.startsWith("c") },
                Collectionx.single(multiIterable1) { it.startsWith("c") })
        assertTwoThrow(IllegalArgumentException::class,
                { multiIterable0.single { it.startsWith("b") } },
                { Collectionx.single(multiIterable1) { it.startsWith("b") } })
        assertTwoThrow(NoSuchElementException::class,
                { singleIterable0.single { it.startsWith("b") } },
                { Collectionx.single(singleIterable1) { it.startsWith("b") } })
        assertTwoThrow(NoSuchElementException::class,
                { emptyIterable0.single { it.startsWith("c") } },
                { Collectionx.single(emptyIterable1) { it.startsWith("c") } })
        assertThrow(NoSuchElementException::class) { Collectionx.single(nullList1) { it.startsWith("b") } }

        assertTwoEquals("cj", singleIterable0.singleOrNull(), Collectionx.singleOrNull(singleIterable1))
        assertTwoEquals(null, multiIterable0.singleOrNull(), Collectionx.singleOrNull(multiIterable1))
        assertTwoEquals(null, emptyIterable0.singleOrNull(), Collectionx.singleOrNull(emptyIterable1))
        assertNull(Collectionx.singleOrNull(nullList1))

        assertTwoEquals("cj",
                singleIterable0.singleOrNull { it.startsWith("c") },
                Collectionx.singleOrNull(singleIterable1) { it.startsWith("c") })
        assertTwoEquals("cj",
                multiIterable0.singleOrNull { it.startsWith("c") },
                Collectionx.singleOrNull(multiIterable1) { it.startsWith("c") })
        assertTwoEquals(null,
                multiIterable0.singleOrNull { it.startsWith("b") },
                Collectionx.singleOrNull(multiIterable1) { it.startsWith("b") })
        assertTwoEquals(null,
                singleIterable0.singleOrNull { it.startsWith("b") },
                Collectionx.singleOrNull(singleIterable1) { it.startsWith("b") })
        assertTwoEquals(null,
                emptyIterable0.singleOrNull { it.startsWith("c") },
                Collectionx.singleOrNull(emptyIterable1) { it.startsWith("c") })
        assertEquals(null,
                Collectionx.singleOrNull(nullList1) { it.startsWith("b") })
    }

    @Test
    fun testDrop() {
        val list0 = listOf("aj", "bj", "cj", "dj")
        val list1 = Collectionx.listOf("aj", "bj", "cj", "dj")
        val nullList1 = null as List<String>?

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
        assertTrue(Collectionx.drop(nullList1, 0).isEmpty())

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
        val list0 = listOf("aj", "bj", "cj", "dj")
        val list1 = Collectionx.listOf("aj", "bj", "cj", "dj")

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
        val list0 = listOf("aj", "bo", "cj", "do")
        val list1 = Collectionx.listOf("aj", "bo", "cj", "do")
        val nullList1 = null as List<String>?

        assertTwoEquals("aj, cj",
                list0.filter { it.endsWith("j") }.joinToString(),
                Collectionx.joinToString(Collectionx.filter(list1) { it.endsWith("j") }))

        val filterToDestination = java.util.ArrayList<String>()
        val filterToDestination1 = java.util.ArrayList<String>()
        val filterToDestinationNull1 = java.util.ArrayList<String>()
        val filterToDestinationResult = list0.filterTo(filterToDestination) { it.endsWith("j") }
        val filterToDestinationResult1 = Collectionx.filterTo(list1, filterToDestination1) { it.endsWith("j") }
        val filterToDestinationNullResult1 = Collectionx.filterTo(nullList1, filterToDestinationNull1) { it.endsWith("j") }
        assertTwoEquals("aj, cj", filterToDestinationResult.joinToString(), filterToDestinationResult1.joinToString())
        assertTrue(filterToDestination === filterToDestinationResult)
        assertTrue(filterToDestination1 === filterToDestinationResult1)
        assertEquals("", filterToDestinationNullResult1.joinToString())
        assertTrue(filterToDestinationNull1 === filterToDestinationNullResult1)

        assertTwoEquals("bo, do",
                list0.filterIndexed { index, _ -> (index % 2) != 0 }.joinToString(),
                Collectionx.joinToString(Collectionx.filterIndexed(list1) { index, _ -> (index % 2) != 0 }))

        val filterIndexedToDestination = java.util.ArrayList<String>()
        val filterIndexedToDestination1 = java.util.ArrayList<String>()
        val filterIndexedToDestinationResult = list0.filterIndexedTo(filterIndexedToDestination) { index, _ -> (index % 2) != 0 }
        val filterIndexedToDestinationResult1 = Collectionx.filterIndexedTo(list1, filterIndexedToDestination1) { index, _ -> (index % 2) != 0 }
        assertTwoEquals("bo, do", filterIndexedToDestinationResult.joinToString(), filterIndexedToDestinationResult1.joinToString())
        assertTrue(filterIndexedToDestination === filterIndexedToDestinationResult)
        assertTrue(filterIndexedToDestination1 === filterIndexedToDestinationResult1)


        val anyList0 = listOf(4, "f", 76, "gsdg")
        val anyList1 = Collectionx.listOf(4, "f", 76, "gsdg")

        assertTwoEquals("4, 76",
                anyList0.filterIsInstance(Integer::class.java).joinToString(),
                Collectionx.joinToString(Collectionx.filterIsInstance(anyList1, Integer::class.java)))
        assertTwoEquals("f, gsdg",
                anyList0.filterIsInstance(String::class.java).joinToString(),
                Collectionx.joinToString(Collectionx.filterIsInstance(anyList1, String::class.java)))

        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN") val filterIsInstanceToDestination = java.util.ArrayList<Integer>()
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN") val filterIsInstanceToDestination1 = arrayListOf<Integer>()
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN") val filterIsInstanceToDestinationNull1 = arrayListOf<Integer>()
        val filterIsInstanceToDestinationResult = anyList0.filterIsInstanceTo(filterIsInstanceToDestination, Integer::class.java)
        val filterIsInstanceToDestinationResult1 = Collectionx.filterIsInstanceTo(anyList1, filterIsInstanceToDestination1, Integer::class.java)
        val filterIsInstanceToDestinationNullResult1 = Collectionx.filterIsInstanceTo(nullList1, filterIsInstanceToDestinationNull1, Integer::class.java)
        assertTwoEquals("4, 76", filterIsInstanceToDestinationResult.joinToString(), filterIsInstanceToDestinationResult1.joinToString())
        assertTrue(filterIsInstanceToDestination === filterIsInstanceToDestinationResult)
        assertTrue(filterIsInstanceToDestination1 === filterIsInstanceToDestinationResult1)
        assertEquals("", filterIsInstanceToDestinationNullResult1.joinToString())
        assertTrue(filterIsInstanceToDestinationNull1 === filterIsInstanceToDestinationNullResult1)

        assertTwoEquals("bo, do",
                list0.filterNot { it.endsWith("j") }.joinToString(),
                Collectionx.joinToString(Collectionx.filterNot(list1) { it.endsWith("j") }))

        val filterNotToDestination = java.util.ArrayList<String>()
        val filterNotToDestination1 = java.util.ArrayList<String>()
        val filterNotToDestinationNull1 = java.util.ArrayList<String>()
        val filterNotToDestinationResult = list0.filterNotTo(filterNotToDestination) { it.endsWith("j") }
        val filterNotToDestinationResult1 = Collectionx.filterNotTo(list1, filterNotToDestination1) { it.endsWith("j") }
        val filterNotToDestinationNullResult1 = Collectionx.filterNotTo(nullList1, filterNotToDestinationNull1) { it.endsWith("j") }
        assertTwoEquals("bo, do", filterNotToDestinationResult.joinToString(), filterNotToDestinationResult1.joinToString())
        assertTrue(filterNotToDestination === filterNotToDestinationResult)
        assertTrue(filterNotToDestination1 === filterNotToDestinationResult1)
        assertEquals("", filterNotToDestinationNullResult1.joinToString())
        assertTrue(filterNotToDestinationNull1 === filterNotToDestinationNullResult1)


        val notNullList0 = listOf(null, "f", null, "gsdg")
        val notNullList1 = Collectionx.listOf(null, "f", null, "gsdg")

        assertTwoEquals("f, gsdg",
                notNullList0.filterNotNull().joinToString(),
                Collectionx.joinToString(Collectionx.filterNotNull(notNullList1)))

        val filterNotNullToDestination = java.util.ArrayList<String>()
        val filterNotNullToDestination1 = java.util.ArrayList<String>()
        val filterNotNullToDestinationNull1 = java.util.ArrayList<String>()
        val filterNotNullToDestinationResult = notNullList0.filterNotNullTo(filterNotNullToDestination)
        val filterNotNullToDestinationResult1 = Collectionx.filterNotNullTo(notNullList1, filterNotNullToDestination1)
        val filterNotNullToDestinationNullResult1 = Collectionx.filterNotNullTo(nullList1, filterNotNullToDestinationNull1)
        assertTwoEquals("f, gsdg", filterNotNullToDestinationResult.joinToString(), filterNotNullToDestinationResult1.joinToString())
        assertTrue(filterNotNullToDestination === filterNotNullToDestinationResult)
        assertTrue(filterNotNullToDestination1 === filterNotNullToDestinationResult1)
        assertEquals("", filterNotNullToDestinationNullResult1.joinToString())
        assertTrue(filterNotNullToDestinationNull1 === filterNotNullToDestinationNullResult1)
    }

    @Test
    fun testSort() {
        val list0 = listOf("aaa", "h", "uuuu", "gg")
        val list1 = Collectionx.listOf("aaa", "h", "uuuu", "gg")
        val nullableList0 = listOf("aaa", null, null, "gg", null)
        val nullableList1 = Collectionx.listOf<String>("aaa", null, null, "gg", null)

        assertTwoEquals("aaa, gg, h, uuuu",
                list0.sorted().joinToString(),
                Collectionx.joinToString(Collectionx.sorted(list1)))
        assertThrow(NullPointerException::class) { Collectionx.joinToString(Collectionx.sorted(nullableList1)) }

        assertTwoEquals("uuuu, h, gg, aaa",
                list0.sortedDescending().joinToString(),
                Collectionx.joinToString(Collectionx.sortedDescending(list1)))
        assertThrow(NullPointerException::class) { Collectionx.joinToString(Collectionx.sortedDescending(nullableList1)) }

        assertTwoEquals("h, gg, aaa, uuuu",
                list0.sortedBy { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedBy(list1) { it.length }))
        assertTwoEquals("null, null, null, gg, aaa",
                nullableList0.sortedBy { it?.length ?: 0 }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedBy(nullableList1) { it.length }))

        assertTwoEquals("uuuu, aaa, gg, h",
                list0.sortedByDescending { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedByDescending(list1) { it.length }))
        assertTwoEquals("aaa, gg, null, null, null",
                nullableList0.sortedByDescending { it?.length ?: 0 }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedByDescending(nullableList1) { it.length }))

        assertTwoEquals("aaa, gg, h, uuuu",
                list0.sortedWith { it1, it2 -> it1.compareTo(it2) }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedWith(list1) { it1, it2 -> it1.compareTo(it2) }))
        assertTwoEquals("null, null, null, aaa, gg",
                nullableList0.sortedWith { it1, it2 -> it1.orEmpty().compareTo(it2.orEmpty()) }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedWith(nullableList1) { it1, it2 -> it1.orEmpty().compareTo(it2.orEmpty()) }))
    }

    @Test
    @Suppress("ReplaceAssociateFunction")
    fun testAssociate() {
        val list0 = listOf("aj", "bj", "ao", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "ao", "bo")
        val nullList1 = null as List<String>?

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
        val associateToMapNull1 = HashMap<String, String>()
        val associateToMapResult0 = list0.associateTo(associateToMap0) { it.first().toString() to it }
        val associateToMapResult1 = Collectionx.associateTo(list1, associateToMap1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) }
        val associateToMapResultNull1 = Collectionx.associateTo(nullList1, associateToMapNull1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) }
        assertTwoEquals(mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"), associateToMap0, associateToMap1)
        assertTrue(associateToMap0 === associateToMapResult0)
        assertTrue(associateToMap1 === associateToMapResult1)
        assertEquals(mapOf<String, String>(), associateToMapResultNull1)
        assertTrue(associateToMapNull1 === associateToMapResultNull1)

        val associateByTo1Map0 = HashMap<String, String>()
        val associateByTo1Map1 = HashMap<String, String>()
        val associateByTo1MapNull1 = HashMap<String, String>()
        val associateByTo1MapResult0 = list0.associateByTo(associateByTo1Map0) { it.first().toString() }
        val associateByTo1MapResult1 = Collectionx.associateByTo(list1, associateByTo1Map1) { it.first().toString() }
        val associateByTo1MapResultNull1 = Collectionx.associateByTo(nullList1, associateByTo1MapNull1) { it.first().toString() }
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
        val associateByTo2MapResult0 = list0.associateByTo(associateByTo2Map0, { it.first().toString() }, { it })
        val associateByTo2MapResult1 = Collectionx.associateByTo(list1, associateByTo2Map1, { it.first().toString() }, { it })
        val associateByTo2MapResultNull1 = Collectionx.associateByTo(nullList1, associateByTo2MapNull1, { it.first().toString() }, { it })
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
    fun testFlatMap() {
        val list0 = listOf("aj", "bj", "ao", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "ao", "bo")
        val nullList1 = null as List<String>?

        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "a, j, b, j, a, o, b, o",
                list0.flatMap { it -> it.toCharArray().map { it.toString() } }.joinToString(),
                Collectionx.joinToString(Collectionx.flatMap(list1) { it -> it.toCharArray().map { it.toString() } }),
        )

        val flatMapToList0 = ArrayList<String>()
        val flatMapToList1 = ArrayList<String>()
        val flatMapToListNull1 = ArrayList<String>()
        val flatMapToListResult0 = list0.flatMapTo(flatMapToList0) { it -> it.toCharArray().map { it.toString() } }
        val flatMapToListResult1 = Collectionx.flatMapTo(list1, flatMapToList1) { it -> it.toCharArray().map { it.toString() } }
        val flatMapToListResultNull1 = Collectionx.flatMapTo(nullList1, flatMapToListNull1) { it -> it.toCharArray().map { it.toString() } }
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

        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "0-a, 0-j, 1-b, 1-j, 2-a, 2-o, 3-b, 3-o",
                list0.flatMapIndexed { index, it -> it.toCharArray().map { "${index}-$it" } }.joinToString(),
                Collectionx.joinToString(Collectionx.flatMapIndexed(list1) { index, it -> it.toCharArray().map { "${index}-$it" } }),
        )

        val flatMapIndexedToList0 = ArrayList<String>()
        val flatMapIndexedToList1 = ArrayList<String>()
        val flatMapIndexedToListNull1 = ArrayList<String>()
        val flatMapIndexedToListResult0 = list0.flatMapIndexedTo(flatMapIndexedToList0) { index, it -> it.toCharArray().map { "${index}-$it" } }
        val flatMapIndexedToListResult1 = Collectionx.flatMapIndexedTo(list1, flatMapIndexedToList1) { index, it -> it.toCharArray().map { "${index}-$it" } }
        val flatMapIndexedToListResultNull1 = Collectionx.flatMapIndexedTo(nullList1, flatMapIndexedToListNull1) { index, it -> it.toCharArray().map { "${index}-$it" } }
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
    }

    @Test
    fun testGroup() {
        val list0 = listOf("aj", "bj", "ao", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "ao", "bo")
        val nullList1 = null as List<String>?

        assertTwoEquals(
                "{a=[aj, ao], b=[bj, bo]}",
                list0.groupBy { it.first() }.toString(),
                Collectionx.groupBy(list1) { it.first() }.toString(),
        )

        assertTwoEquals(
                "{a=[j, o], b=[j, o]}",
                list0.groupBy({ it.first() }, { it.last() }).toString(),
                Collectionx.groupBy(list1, { it.first() }, { it.last() }).toString(),
        )

        val groupByToMap0 = HashMap<Char, MutableList<String>>()
        val groupByToMap1 = HashMap<Char, List<String>>()
        val groupByToMapNull1 = HashMap<Char, List<String>>()
        val groupByToMapResult0 = list0.groupByTo(groupByToMap0) { it.first() }
        val groupByToMapResult1 = Collectionx.groupByTo(list1, groupByToMap1) { it.first() }
        val groupByToMapResultNull1 = Collectionx.groupByTo(nullList1, groupByToMapNull1) { it.first() }
        assertTwoEquals("{a=[aj, ao], b=[bj, bo]}", groupByToMap0.toString(), groupByToMap1.toString())
        assertTrue(groupByToMap0 === groupByToMapResult0)
        assertTrue(groupByToMap1 === groupByToMapResult1)
        assertEquals("{}", groupByToMapNull1.toString())
        assertTrue(groupByToMapNull1 === groupByToMapResultNull1)

        val groupByToMap2 = HashMap<Char, MutableList<Char>>()
        val groupByToMap3 = HashMap<Char, List<Char>>()
        val groupByToMapNull3 = HashMap<Char, List<Char>>()
        val groupByToMapResult2 = list0.groupByTo(groupByToMap2, { it.first() }, { it.last() })
        val groupByToMapResult3 = Collectionx.groupByTo(list1, groupByToMap3, { it.first() }, { it.last() })
        val groupByToMapResultNull3 = Collectionx.groupByTo(nullList1, groupByToMapNull3, { it.first() }, { it.last() })
        assertTwoEquals("{a=[j, o], b=[j, o]}", groupByToMap2.toString(), groupByToMap3.toString())
        assertTrue(groupByToMap2 === groupByToMapResult2)
        assertTrue(groupByToMap3 === groupByToMapResult3)
        assertEquals("{}", groupByToMapNull3.toString())
        assertTrue(groupByToMapNull3 === groupByToMapResultNull3)
    }

    @Test
    fun testMap() {
        val list0 = listOf("aj", "bj", "ao", "cc", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "ao", "cc", "bo")
        val nullList1 = null as List<String>?

        assertTwoEquals(
                "a, b, a, c, b",
                list0.map { it.first() }.joinToString(),
                Collectionx.joinToString(Collectionx.map(list1) { it.first() }),
        )

        assertTwoEquals(
                "a, b, a, b",
                list0.mapNotNull { if (it != "cc") it.first() else null }.joinToString(),
                Collectionx.joinToString(Collectionx.mapNotNull(list1) { if (it != "cc") it.first() else null }),
        )

        assertTwoEquals(
                "0:a, 1:b, 2:a, 3:c, 4:b",
                list0.mapIndexed { index, s -> "$index:${s.first()}" }.joinToString(),
                Collectionx.joinToString(Collectionx.mapIndexed(list1) { index, s -> "$index:${s.first()}" }),
        )

        assertTwoEquals(
                "0:a, 1:b, 2:a, 4:b",
                list0.mapIndexedNotNull { index, s -> if (s != "cc") "$index:${s.first()}" else null }.joinToString(),
                Collectionx.joinToString(Collectionx.mapIndexedNotNull(list1) { index, s -> if (s != "cc") "$index:${s.first()}" else null }),
        )

        val mapToList0 = ArrayList<Char>()
        val mapToList1 = ArrayList<Char>()
        val mapToListNull1 = ArrayList<Char>()
        val mapToListResult0 = list0.mapTo(mapToList0) { it.first() }
        val mapToListResult1 = Collectionx.mapTo(list1, mapToList1) { it.first() }
        val mapToListResultNull1 = Collectionx.mapTo(nullList1, mapToListNull1) { it.first() }
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
        val mapNotNullToListResult0 = list0.mapNotNullTo(mapNotNullToList0) { if (it != "cc") it.first() else null }
        val mapNotNullToListResult1 = Collectionx.mapNotNullTo(list1, mapNotNullToList1) { if (it != "cc") it.first() else null }
        val mapNotNullToListResultNull1 = Collectionx.mapNotNullTo(nullList1, mapNotNullToListNull1) { if (it != "cc") it.first() else null }
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
        val mapIndexedToListResult0 = list0.mapIndexedTo(mapIndexedToList0) { index, s -> "$index:${s.first()}" }
        val mapIndexedToListResult1 = Collectionx.mapIndexedTo(list1, mapIndexedToList1) { index, s -> "$index:${s.first()}" }
        val mapIndexedToListResultNull1 = Collectionx.mapIndexedTo(nullList1, mapIndexedToListNull1) { index, s -> "$index:${s.first()}" }
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
        val mapIndexedNotNullToListResult0 = list0.mapIndexedNotNullTo(mapIndexedNotNullToList0) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
        val mapIndexedNotNullToListResult1 = Collectionx.mapIndexedNotNullTo(list1, mapIndexedNotNullToList1) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
        val mapIndexedNotNullToListResultNull1 = Collectionx.mapIndexedNotNullTo(nullList1, mapIndexedNotNullToListNull1) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
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
        val list0 = listOf("aj", "bj", "ao", "cc", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "ao", "cc", "bo")

        assertTwoEquals(
                "0:aj, 1:bj, 2:ao, 3:cc, 4:bo",
                list0.withIndex().joinToString { "${it.index}:${it.value}" },
                Collectionx.joinToString(Collectionx.withIndex(list1)) { "${it.index}:${it.value}" },
        )

        val iterator0 = list0.withIndex().iterator()
        if (iterator0 is MutableIterator) {
            assertThrow(UnsupportedOperationException::class) { iterator0.remove() }
        }
        assertThrow(IllegalStateException::class) { Collectionx.withIndex(list1).iterator().remove() }
    }

    @Test
    fun testDistinct() {
        val list0 = listOf("aj", "bj", "aj", "bj", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "aj", "bj", "bo")

        assertTwoEquals(
                "aj, bj, bo",
                list0.distinct().joinToString(),
                Collectionx.joinToString(Collectionx.distinct(list1)),
        )

        assertTwoEquals(
                "aj, bo",
                list0.distinctBy { it.last() }.joinToString(),
                Collectionx.joinToString(Collectionx.distinctBy(list1) { it.last() }),
        )
    }

    @Test
    fun testAll() {
        val list0 = listOf("aj", "bj", "aj", "bj", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "aj", "bj", "bo")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList1 = null as List<String>?

        assertTwoEquals(
                true,
                list0.all { it -> it.all { it.isLetter() } },
                Collectionx.all(list1) { it -> it.all { it.isLetter() } },
        )

        assertTwoEquals(
                true,
                emptyList0.all { it -> it.all { it.isLetter() } },
                Collectionx.all(emptyList1) { it -> it.all { it.isLetter() } },
        )

        assertTrue(Collectionx.all(nullList1) { it.last() == 'j' })

        assertTwoEquals(
                false,
                list0.all { it.last() == 'j' },
                Collectionx.all(list1) { it.last() == 'j' },
        )
    }

    @Test
    fun testAny() {
        val list0 = listOf("aj", "bj", "aj", "bj", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "aj", "bj", "bo")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList1 = null as List<String>?

        assertTwoEquals(true, list0.any(), Collectionx.any(list1))
        assertTwoEquals(false, emptyList0.any(), Collectionx.any(emptyList1))
        assertFalse(Collectionx.any(nullList1))
        assertTwoEquals(
                false,
                listOf<String>().any(),
                Collectionx.any(Collectionx.listOf<String>()),
        )

        assertTwoEquals(true, list0.any { it.last() == 'j' }, Collectionx.any(list1) { it.last() == 'j' })
        assertTwoEquals(false, emptyList0.any { it.last() == 'j' }, Collectionx.any(emptyList1) { it.last() == 'j' })
        assertFalse(Collectionx.any(nullList1) { it -> it.all { it.isDigit() } })
        assertTwoEquals(
                false,
                list0.any { it -> it.all { it.isDigit() } },
                Collectionx.any(list1) { it -> it.all { it.isDigit() } }
        )
    }

    @Test
    fun testCount() {
        val list0 = listOf("aj", "bj", "aj", "bj", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "aj", "bj", "bo")

        assertTwoEquals(
                5,
                list0.count(),
                Collectionx.count(list1),
        )

        assertTwoEquals(
                0,
                listOf<String>().count(),
                Collectionx.count(Collectionx.listOf<String>()),
        )

        assertTwoEquals(
                0,
                (null as Iterable<String>?)?.count() ?: 0,
                Collectionx.count(null as List<String>?),
        )

        assertTwoEquals(
                4,
                list0.count { it.last() == 'j' },
                Collectionx.count(list1) { it.last() == 'j' },
        )

        assertTwoEquals(
                0,
                listOf<String>().count { it.last() == 'j' },
                Collectionx.count(Collectionx.listOf<String>()) { it.last() == 'j' },
        )

        assertTwoEquals(
                0,
                (null as Iterable<String>?)?.count { it.last() == 'j' } ?: 0,
                Collectionx.count(null as List<String>?) { it.last() == 'j' },
        )
    }

    @Test
    fun testFold() {
        val list0 = listOf("aj", "bj", "aj", "bj", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "aj", "bj", "bo")
        val nullList1 = null as List<String>?

        assertTwoEquals(
                "^ajbjajbjbo",
                list0.fold("^") { r, t -> r + t },
                Collectionx.fold(list1, "^") { r, t -> r + t },
        )
        assertEquals(
                "^",
                Collectionx.fold(nullList1, "^") { r, t -> r + t },
        )

        assertTwoEquals(
                "^0aj1bj2aj3bj4bo",
                list0.foldIndexed("^") { i, r, t -> r + i.toString() + t },
                Collectionx.foldIndexed(list1, "^") { i, r, t -> r + i.toString() + t },
        )
        assertEquals(
                "^",
                Collectionx.foldIndexed(nullList1, "^") { i, r, t -> r + i.toString() + t },
        )
    }

    @Test
    fun testEach() {
        val list0 = listOf("aj", "bj", "aj", "bj", "bo")
        val list1 = Collectionx.listOf("aj", "bj", "aj", "bj", "bo")

        assertTwoEquals("aj, bj, aj, bj, bo",
                ArrayList<String>().apply { list0.forEach { add(it) } }.joinToString(),
                ArrayList<String>().apply { Collectionx.forEach(list1) { add(it) } }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { listOf<String>().forEach { add(it) } }.joinToString(),
                ArrayList<String>().apply { Collectionx.forEach(null as List<String>?) { add(it) } }.joinToString())

        assertTwoEquals("0aj, 1bj, 2aj, 3bj, 4bo",
                ArrayList<String>().apply { list0.forEachIndexed { i, it -> add(i.toString() + it) } }.joinToString(),
                ArrayList<String>().apply { Collectionx.forEachIndexed(list1) { i, it -> add(i.toString() + it) } }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { listOf<String>().forEachIndexed { i, it -> add(i.toString() + it) } }.joinToString(),
                ArrayList<String>().apply { Collectionx.forEachIndexed(null as List<String>?) { i, it -> add(i.toString() + it) } }.joinToString())


        assertTwoEquals("aj, bj, aj, bj, bo",
                ArrayList<String>().apply { list0.onEach { add(it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Collectionx.onEach(list1) { add(it) } }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { listOf<String>().onEach { add(it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Collectionx.onEach(null as List<String>?) { add(it) } }.joinToString())

        assertTwoEquals("0aj, 1bj, 2aj, 3bj, 4bo",
                ArrayList<String>().apply { list0.onEachIndexed { i, it -> add(i.toString() + it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Collectionx.onEachIndexed(list1) { i, it -> add(i.toString() + it) } }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { listOf<String>().onEachIndexed { i, it -> add(i.toString() + it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Collectionx.onEachIndexed(null as List<String>?) { i, it -> add(i.toString() + it) } }.joinToString())
    }

    @Test
    @Suppress("RedundantSamConstructor", "RemoveExplicitTypeArguments")
    fun testMax() {
        val doubleList0 = listOf(3.2, 3.3, 3.0, 5.6, 1.1)
        val doubleList1 = Collectionx.listOf(3.2, 3.3, 3.0, 5.6, 1.1)
        val nanDoubleList0 = listOf(Double.NaN, 3.0, 3.2, 5.6, 1.1)
        val nanDoubleList1 = Collectionx.listOf(Double.NaN, 3.0, 3.2, 5.6, 1.1)
        val nanDoubleList00 = listOf(3.0, 3.2, Double.NaN, 5.6, 1.1)
        val nanDoubleList11 = Collectionx.listOf(3.0, 3.2, Double.NaN, 5.6, 1.1)
        val emptyDoubleList0 = listOf<Double>()
        val emptyDoubleList1 = Collectionx.listOf<Double>()
        val nullDoubleList1: List<Double>? = null
        assertTwoEquals(5.6, doubleList0.maxOrNull(), Collectionx.maxDoubleOrNull(doubleList1))
        assertTwoEquals(Double.NaN, nanDoubleList0.maxOrNull(), Collectionx.maxDoubleOrNull(nanDoubleList1))
        assertTwoEquals(Double.NaN, nanDoubleList00.maxOrNull(), Collectionx.maxDoubleOrNull(nanDoubleList11))
        assertTwoEquals(null, emptyDoubleList0.maxOrNull(), Collectionx.maxDoubleOrNull(emptyDoubleList1))
        assertTwoEquals(null, null, Collectionx.maxDoubleOrNull(nullDoubleList1))

        val floatList0 = listOf(3.2f, 3.3f, 3.0f, 5.6f, 1.1f)
        val floatList1 = Collectionx.listOf(3.2f, 3.3f, 3.0f, 5.6f, 1.1f)
        val nanFloatList0 = listOf(Float.NaN, 3.0f, 3.2f, 5.6f, 1.1f)
        val nanFloatList1 = Collectionx.listOf(Float.NaN, 3.0f, 3.2f, 5.6f, 1.1f)
        val nanFloatList00 = listOf(3.0f, 3.2f, Float.NaN, 5.6f, 1.1f)
        val nanFloatList11 = Collectionx.listOf(3.0f, 3.2f, Float.NaN, 5.6f, 1.1f)
        val emptyFloatList0 = listOf<Float>()
        val emptyFloatList1 = Collectionx.listOf<Float>()
        val nullFloatList1: List<Float>? = null
        assertTwoEquals(5.6f, floatList0.maxOrNull(), Collectionx.maxFloatOrNull(floatList1))
        assertTwoEquals(Float.NaN, nanFloatList0.maxOrNull(), Collectionx.maxFloatOrNull(nanFloatList1))
        assertTwoEquals(Float.NaN, nanFloatList00.maxOrNull(), Collectionx.maxFloatOrNull(nanFloatList11))
        assertTwoEquals(null, emptyFloatList0.maxOrNull(), Collectionx.maxFloatOrNull(emptyFloatList1))
        assertTwoEquals(null, null, Collectionx.maxFloatOrNull(nullFloatList1))

        val list0 = listOf("6", "3", "7", "2", "1")
        val list1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null
        assertTwoEquals("7", list0.maxOrNull(), Collectionx.maxOrNull(list1))
        assertTwoEquals(null, emptyList0.maxOrNull(), Collectionx.maxOrNull(emptyList1))
        assertTwoEquals(null, null, Collectionx.maxOrNull(nullList0))

        assertTwoEquals("7", list0.maxByOrNull { it.toInt() }, Collectionx.maxByOrNull(list1) { it.toInt() })
        assertTwoEquals(null, emptyList0.maxByOrNull { it.toInt() }, Collectionx.maxByOrNull(emptyList1) { it.toInt() })
        assertTwoEquals(null, null, Collectionx.maxByOrNull(nullList0) { it.toInt() })

        assertTwoEquals("1",
                list0.maxWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Collectionx.maxWithOrNull(list1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                emptyList0.maxWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Collectionx.maxWithOrNull(emptyList1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                null,
                Collectionx.maxWithOrNull(nullList0) { it0, it1 -> it0.compareTo(it1) * -1 })

        assertTwoEquals(7.0,
                list0.maxOf { it.toDouble() },
                Collectionx.maxOfDouble(list1) { it.toDouble() })
        assertTwoThrow(NoSuchElementException::class,
                { emptyList0.maxOf { it.toDouble() } },
                { Collectionx.maxOfDouble(emptyList1) { it.toDouble() } })
        assertThrow(NoSuchElementException::class) { Collectionx.maxOfDouble(nullList0) { it.toDouble() } }

        assertTwoEquals(7.0f,
                list0.maxOf { it.toFloat() },
                Collectionx.maxOfFloat(list1) { it.toFloat() })
        assertTwoThrow(NoSuchElementException::class,
                { emptyList0.maxOf { it.toFloat() } },
                { Collectionx.maxOfFloat(emptyList1) { it.toFloat() } })
        assertThrow(NoSuchElementException::class) { Collectionx.maxOfFloat(nullList0) { it.toFloat() } }

        assertTwoEquals(7.0, list0.maxOfOrNull { it.toDouble() }, Collectionx.maxOfDoubleOrNull(list1) { it.toDouble() })
        assertTwoEquals(null, emptyList0.maxOfOrNull { it.toDouble() }, Collectionx.maxOfDoubleOrNull(emptyList1) { it.toDouble() })
        assertTwoEquals(null, null, Collectionx.maxOfDoubleOrNull(nullList0) { it.toDouble() })

        assertTwoEquals(7.0f, list0.maxOfOrNull { it.toFloat() }, Collectionx.maxOfFloatOrNull(list1) { it.toFloat() })
        assertTwoEquals(null, emptyList0.maxOfOrNull { it.toFloat() }, Collectionx.maxOfFloatOrNull(emptyList1) { it.toFloat() })
        assertTwoEquals(null, null, Collectionx.maxOfFloatOrNull(nullList0) { it.toFloat() })

        assertTwoEquals("7", list0.maxOf { it }, Collectionx.maxOf(list1) { it })
        assertTwoThrow(NoSuchElementException::class, { emptyList0.maxOf { it } }, { Collectionx.maxOf(emptyList1) { it } })
        assertThrow(NoSuchElementException::class) { Collectionx.maxOf(nullList0) { it } }

        assertTwoEquals("7", list0.maxOfOrNull { it }, Collectionx.maxOfOrNull(list1) { it })
        assertTwoEquals(null, emptyList0.maxOfOrNull { it }, Collectionx.maxOfOrNull(emptyList1) { it })
        assertTwoEquals(null, null, Collectionx.maxOfOrNull(nullList0) { it })

        assertTwoEquals("1",
                list0.maxOfWith({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.maxOfWith(list1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoThrow(NoSuchElementException::class,
                { emptyList0.maxOfWith(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) },
                { Collectionx.maxOfWith(emptyList1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }
        )
        assertThrow(NoSuchElementException::class) { Collectionx.maxOfWith(nullList0, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }

        assertTwoEquals("1",
                list0.maxOfWithOrNull({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.maxOfWithOrNull(list1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoEquals(null,
                emptyList0.maxOfWithOrNull(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.maxOfWithOrNull(emptyList1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it })
        )
        assertTwoEquals(null, null, Collectionx.maxOfWithOrNull(nullList0, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
    }

    @Test
    @Suppress("RedundantSamConstructor", "RemoveExplicitTypeArguments")
    fun testMin() {
        val doubleList0 = listOf(3.2, 3.3, 3.0, 5.6, 1.1)
        val doubleList1 = Collectionx.listOf(3.2, 3.3, 3.0, 5.6, 1.1)
        val nanDoubleList0 = listOf(Double.NaN, 3.0, 3.2, 5.6, 1.1)
        val nanDoubleList1 = Collectionx.listOf(Double.NaN, 3.0, 3.2, 5.6, 1.1)
        val nanDoubleList00 = listOf(3.0, 3.2, Double.NaN, 5.6, 1.1)
        val nanDoubleList11 = Collectionx.listOf(3.0, 3.2, Double.NaN, 5.6, 1.1)
        val emptyDoubleList0 = listOf<Double>()
        val emptyDoubleList1 = Collectionx.listOf<Double>()
        val nullDoubleList1: List<Double>? = null
        assertTwoEquals(1.1, doubleList0.minOrNull(), Collectionx.minDoubleOrNull(doubleList1))
        assertTwoEquals(Double.NaN, nanDoubleList0.minOrNull(), Collectionx.minDoubleOrNull(nanDoubleList1))
        assertTwoEquals(Double.NaN, nanDoubleList00.minOrNull(), Collectionx.minDoubleOrNull(nanDoubleList11))
        assertTwoEquals(null, emptyDoubleList0.minOrNull(), Collectionx.minDoubleOrNull(emptyDoubleList1))
        assertTwoEquals(null, null, Collectionx.minDoubleOrNull(nullDoubleList1))

        val floatList0 = listOf(3.2f, 3.3f, 3.0f, 5.6f, 1.1f)
        val floatList1 = Collectionx.listOf(3.2f, 3.3f, 3.0f, 5.6f, 1.1f)
        val nanFloatList0 = listOf(Float.NaN, 3.0f, 3.2f, 5.6f, 1.1f)
        val nanFloatList1 = Collectionx.listOf(Float.NaN, 3.0f, 3.2f, 5.6f, 1.1f)
        val nanFloatList00 = listOf(3.0f, 3.2f, Float.NaN, 5.6f, 1.1f)
        val nanFloatList11 = Collectionx.listOf(3.0f, 3.2f, Float.NaN, 5.6f, 1.1f)
        val emptyFloatList0 = listOf<Float>()
        val emptyFloatList1 = Collectionx.listOf<Float>()
        val nullFloatList1: List<Float>? = null
        assertTwoEquals(1.1f, floatList0.minOrNull(), Collectionx.minFloatOrNull(floatList1))
        assertTwoEquals(Float.NaN, nanFloatList0.minOrNull(), Collectionx.minFloatOrNull(nanFloatList1))
        assertTwoEquals(Float.NaN, nanFloatList00.minOrNull(), Collectionx.minFloatOrNull(nanFloatList11))
        assertTwoEquals(null, emptyFloatList0.minOrNull(), Collectionx.minFloatOrNull(emptyFloatList1))
        assertTwoEquals(null, null, Collectionx.minFloatOrNull(nullFloatList1))

        val list0 = listOf("6", "3", "7", "2", "1")
        val list1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null
        assertTwoEquals("1", list0.minOrNull(), Collectionx.minOrNull(list1))
        assertTwoEquals(null, emptyList0.minOrNull(), Collectionx.minOrNull(emptyList1))
        assertTwoEquals(null, null, Collectionx.minOrNull(nullList0))

        assertTwoEquals("1", list0.minByOrNull { it.toInt() }, Collectionx.minByOrNull(list1) { it.toInt() })
        assertTwoEquals(null, emptyList0.minByOrNull { it.toInt() }, Collectionx.minByOrNull(emptyList1) { it.toInt() })
        assertTwoEquals(null, null, Collectionx.minByOrNull(nullList0) { it.toInt() })

        assertTwoEquals("7",
                list0.minWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Collectionx.minWithOrNull(list1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                emptyList0.minWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Collectionx.minWithOrNull(emptyList1) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                null,
                Collectionx.minWithOrNull(nullList0) { it0, it1 -> it0.compareTo(it1) * -1 })

        assertTwoEquals(1.0,
                list0.minOf { it.toDouble() },
                Collectionx.minOfDouble(list1) { it.toDouble() })
        assertTwoThrow(NoSuchElementException::class,
                { emptyList0.minOf { it.toDouble() } },
                { Collectionx.minOfDouble(emptyList1) { it.toDouble() } })
        assertThrow(NoSuchElementException::class) { Collectionx.minOfDouble(nullList0) { it.toDouble() } }

        assertTwoEquals(1.0f,
                list0.minOf { it.toFloat() },
                Collectionx.minOfFloat(list1) { it.toFloat() })
        assertTwoThrow(NoSuchElementException::class,
                { emptyList0.minOf { it.toFloat() } },
                { Collectionx.minOfFloat(emptyList1) { it.toFloat() } })
        assertThrow(NoSuchElementException::class) { Collectionx.minOfFloat(nullList0) { it.toFloat() } }

        assertTwoEquals(1.0, list0.minOfOrNull { it.toDouble() }, Collectionx.minOfDoubleOrNull(list1) { it.toDouble() })
        assertTwoEquals(null, emptyList0.minOfOrNull { it.toDouble() }, Collectionx.minOfDoubleOrNull(emptyList1) { it.toDouble() })
        assertTwoEquals(null, null, Collectionx.minOfDoubleOrNull(nullList0) { it.toDouble() })

        assertTwoEquals(1.0f, list0.minOfOrNull { it.toFloat() }, Collectionx.minOfFloatOrNull(list1) { it.toFloat() })
        assertTwoEquals(null, emptyList0.minOfOrNull { it.toFloat() }, Collectionx.minOfFloatOrNull(emptyList1) { it.toFloat() })
        assertTwoEquals(null, null, Collectionx.minOfFloatOrNull(nullList0) { it.toFloat() })

        assertTwoEquals("1", list0.minOf { it }, Collectionx.minOf(list1) { it })
        assertTwoThrow(NoSuchElementException::class, { emptyList0.minOf { it } }, { Collectionx.minOf(emptyList1) { it } })
        assertThrow(NoSuchElementException::class) { Collectionx.minOf(nullList0) { it } }

        assertTwoEquals("1", list0.minOfOrNull { it }, Collectionx.minOfOrNull(list1) { it })
        assertTwoEquals(null, emptyList0.minOfOrNull { it }, Collectionx.minOfOrNull(emptyList1) { it })
        assertTwoEquals(null, null, Collectionx.minOfOrNull(nullList0) { it })

        assertTwoEquals("7",
                list0.minOfWith({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.minOfWith(list1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoThrow(NoSuchElementException::class,
                { emptyList0.minOfWith(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) },
                { Collectionx.minOfWith(emptyList1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }
        )
        assertThrow(NoSuchElementException::class) { Collectionx.minOfWith(nullList0, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }

        assertTwoEquals("7",
                list0.minOfWithOrNull({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.minOfWithOrNull(list1, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoEquals(null,
                emptyList0.minOfWithOrNull(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.minOfWithOrNull(emptyList1, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it })
        )
        assertTwoEquals(null, null, Collectionx.minOfWithOrNull(nullList0, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
    }

    @Test
    fun testNone() {
        val normalList0 = listOf("6", "3", "7", "2", "1")
        val normalList1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null

        assertTwoEquals(false, normalList0.none(), Collectionx.none(normalList1))
        assertTwoEquals(true, emptyList0.none(), Collectionx.none(emptyList1))
        assertTrue(Collectionx.none(nullList0))

        assertTwoEquals(true, normalList0.none { it.length > 1 }, Collectionx.none(normalList1) { it.length > 1 })
        assertTwoEquals(false, normalList0.none { it.isNotEmpty() }, Collectionx.none(normalList1) { it.isNotEmpty() })
        assertTwoEquals(true, emptyList0.none { it.length > 1 }, Collectionx.none(emptyList1) { it.length > 1 })
        assertTrue(Collectionx.none(nullList0) { it.length > 1 })
    }

    @Test
    fun testReduce() {
        val normalList0 = listOf("6", "3", "7", "2", "1")
        val normalList1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null

        assertTwoEquals("63721",
                normalList0.reduce { it0, it1 -> it0 + it1 },
                Collectionx.reduce(normalList1) { it0, it1 -> it0 + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList0.reduce { it0, it1 -> it0 + it1 } },
                { Collectionx.reduce(emptyList1) { it0, it1 -> it0 + it1 } })
        assertThrow(UnsupportedOperationException::class)
        { Collectionx.reduce(nullList0) { it0, it1 -> it0 + it1 } }

        assertTwoEquals("613273241",
                normalList0.reduceIndexed { i, it0, it1 -> it0 + i + it1 },
                Collectionx.reduceIndexed(normalList1) { i, it0, it1 -> it0 + i + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList0.reduceIndexed { i, it0, it1 -> it0 + i + it1 } },
                { Collectionx.reduceIndexed(emptyList1) { i, it0, it1 -> it0 + i + it1 } })
        assertThrow(UnsupportedOperationException::class)
        { Collectionx.reduceIndexed(nullList0) { i, it0, it1 -> it0 + i + it1 } }
    }

    @Test
    fun testSum() {
        val normalList0 = listOf("6", "3", "7", "2", "1")
        val normalList1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null
        assertTwoEquals(19, normalList0.sumBy { it.toInt() }, Collectionx.sumBy(normalList1) { it.toInt() })
        assertTwoEquals(0, emptyList0.sumBy { it.toInt() }, Collectionx.sumBy(emptyList1) { it.toInt() })
        assertEquals(0, Collectionx.sumBy(nullList0) { it.toInt() })
        assertTwoEquals(19.0, normalList0.sumByDouble { it.toDouble() }, Collectionx.sumByDouble(normalList1) { it.toDouble() })
        assertTwoEquals(0.0, emptyList0.sumByDouble { it.toDouble() }, Collectionx.sumByDouble(emptyList1) { it.toDouble() })
        assertTwoEquals(0.0, 0.0, Collectionx.sumByDouble(nullList0) { it.toDouble() })

        val normalByteList0 = listOf(6.toByte(), 3.toByte(), 7.toByte(), 2.toByte(), 1.toByte())
        val normalByteList1 = Collectionx.listOf(6.toByte(), 3.toByte(), 7.toByte(), 2.toByte(), 1.toByte())
        val emptyByteList0 = listOf<Byte>()
        val emptyByteList1 = Collectionx.listOf<Byte>()
        val nullByteList1: List<Byte>? = null
        assertTwoEquals(19, normalByteList0.sum(), Collectionx.sumOfByte(normalByteList1))
        assertTwoEquals(0, emptyByteList0.sum(), Collectionx.sumOfByte(emptyByteList1))
        assertTwoEquals(0, 0, Collectionx.sumOfByte(nullByteList1))

        val normalShortList0 = listOf(6.toShort(), 3.toShort(), 7.toShort(), 2.toShort(), 1.toShort())
        val normalShortList1 = Collectionx.listOf(6.toShort(), 3.toShort(), 7.toShort(), 2.toShort(), 1.toShort())
        val emptyShortList0 = listOf<Short>()
        val emptyShortList1 = Collectionx.listOf<Short>()
        val nullShortList1: List<Short>? = null
        assertTwoEquals(19, normalShortList0.sum(), Collectionx.sumOfShort(normalShortList1))
        assertTwoEquals(0, emptyShortList0.sum(), Collectionx.sumOfShort(emptyShortList1))
        assertTwoEquals(0, 0, Collectionx.sumOfShort(nullShortList1))

        val normalIntList0 = listOf(6, 3, 7, 2, 1)
        val normalIntList1 = Collectionx.listOf(6, 3, 7, 2, 1)
        val emptyIntList0 = listOf<Int>()
        val emptyIntList1 = Collectionx.listOf<Int>()
        val nullIntList1: List<Int>? = null
        assertTwoEquals(19, normalIntList0.sum(), Collectionx.sumOfInt(normalIntList1))
        assertTwoEquals(0, emptyIntList0.sum(), Collectionx.sumOfInt(emptyIntList1))
        assertTwoEquals(0, 0, Collectionx.sumOfInt(nullIntList1))

        val normalLongList0 = listOf(6.toLong(), 3.toLong(), 7.toLong(), 2.toLong(), 1.toLong())
        val normalLongList1 = Collectionx.listOf(6.toLong(), 3.toLong(), 7.toLong(), 2.toLong(), 1.toLong())
        val emptyLongList0 = listOf<Long>()
        val emptyLongList1 = Collectionx.listOf<Long>()
        val nullLongList1: List<Long>? = null
        assertTwoEquals(19.toLong(), normalLongList0.sum(), Collectionx.sumOfLong(normalLongList1))
        assertTwoEquals(0.toLong(), emptyLongList0.sum(), Collectionx.sumOfLong(emptyLongList1))
        assertTwoEquals(0.toLong(), 0.toLong(), Collectionx.sumOfLong(nullLongList1))

        val normalFloatList0 = listOf(6.toFloat(), 3.toFloat(), 7.toFloat(), 2.toFloat(), 1.toFloat())
        val normalFloatList1 = Collectionx.listOf(6.toFloat(), 3.toFloat(), 7.toFloat(), 2.toFloat(), 1.toFloat())
        val emptyFloatList0 = listOf<Float>()
        val emptyFloatList1 = Collectionx.listOf<Float>()
        val nullFloatList1: List<Float>? = null
        assertTwoEquals(19.toFloat(), normalFloatList0.sum(), Collectionx.sumOfFloat(normalFloatList1))
        assertTwoEquals(0.toFloat(), emptyFloatList0.sum(), Collectionx.sumOfFloat(emptyFloatList1))
        assertTwoEquals(0.toFloat(), 0.toFloat(), Collectionx.sumOfFloat(nullFloatList1))

        val normalDoubleList0 = listOf(6.toDouble(), 3.toDouble(), 7.toDouble(), 2.toDouble(), 1.toDouble())
        val normalDoubleList1 = Collectionx.listOf(6.toDouble(), 3.toDouble(), 7.toDouble(), 2.toDouble(), 1.toDouble())
        val emptyDoubleList0 = listOf<Double>()
        val emptyDoubleList1 = Collectionx.listOf<Double>()
        val nullDoubleList1: List<Double>? = null
        assertTwoEquals(19.toDouble(), normalDoubleList0.sum(), Collectionx.sumOfDouble(normalDoubleList1))
        assertTwoEquals(0.toDouble(), emptyDoubleList0.sum(), Collectionx.sumOfDouble(emptyDoubleList1))
        assertTwoEquals(0.toDouble(), 0.toDouble(), Collectionx.sumOfDouble(nullDoubleList1))
    }

    @Test
    fun testRequireNoNulls() {
        val normalList0 = listOf("6", "3", "7", "2", "1")
        val normalList1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val nullableList0 = listOf("6", null, "7", "2", null)
        val nullableList1 = Collectionx.listOf("6", null, "7", "2", null)
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null

        assertTwoEquals("6, 3, 7, 2, 1",
                normalList0.requireNoNulls().joinToString(),
                Collectionx.joinToString(Collectionx.requireNoNulls(normalList1)))
        assertTwoThrow(IllegalArgumentException::class,
                { nullableList0.requireNoNulls().joinToString() },
                { Collectionx.joinToString(Collectionx.requireNoNulls(nullableList1)) })
        assertTwoEquals("",
                emptyList0.requireNoNulls().joinToString(),
                Collectionx.joinToString(Collectionx.requireNoNulls(emptyList1)))
        assertEquals("", Collectionx.joinToString(Collectionx.requireNoNulls(nullList0)))
    }

    @Test
    fun testMinus() {
        val normalList0 = listOf("6", "3", "7", "2", "1")
        val normalList1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null

        assertTwoEquals("6, 7, 2, 1",
                normalList0.minus("3").joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, "3")))
        assertTwoEquals("",
                emptyList0.minus("3").joinToString(),
                Collectionx.joinToString(Collectionx.minus(emptyList1, "3")))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList0, "3")))

        assertTwoEquals("6, 7, 2",
                normalList0.minus(arrayOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, arrayOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalList0.minus(arrayOf()).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, arrayOf<String>())))
        assertTwoEquals("",
                emptyList0.minus(arrayOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(emptyList1, arrayOf("3", "1"))))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList0, arrayOf("3", "1"))))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList0, arrayOf<String>())))

        assertTwoEquals("6, 7, 2",
                normalList0.minus(listOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, listOf("3", "1"))))
        assertTwoEquals("6, 7, 2",
                normalList0.minus(setOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, setOf("3", "1"))))
        assertTwoEquals("6, 7, 2",
                normalList0.minus(arrayListOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, arrayListOf("3", "1"))))
        assertTwoEquals("6, 7",
                normalList0.minus(arrayListOf("3", "1", "2")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, arrayListOf("3", "1", "2"))))
        assertTwoEquals("6, 7",
                normalList0.minus(listOf("3", "1", "2")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, listOf("3", "1", "2"))))
        assertTwoEquals("6, 7",
                normalList0.minus(com.github.panpf.tools4j.iterable.TransformingIterable(listOf("3", "1", "2")) { it }).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, com.github.panpf.tools4j.iterable.TransformingIterable(listOf("3", "1", "2")) { it })))
        assertTwoEquals("",
                emptyList0.minus(listOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(emptyList1, listOf("3", "1"))))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList0, listOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalList0.minus(listOf()).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, listOf<String>())))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList0, listOf<String>())))

        assertTwoEquals("6, 7, 2",
                normalList0.minus(listOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, Collectionx.listOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalList0.minus(listOf()).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList1, Collectionx.listOf())))
        assertTwoEquals("",
                emptyList0.minus(listOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(emptyList1, Collectionx.listOf("3", "1"))))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList0, Collectionx.listOf("3", "1"))))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList0, Collectionx.listOf())))

        assertTwoEquals("6, 7, 2, 1",
                normalList0.minusElement("3").joinToString(),
                Collectionx.joinToString(Collectionx.minusElement(normalList1, "3")))
        assertTwoEquals("",
                emptyList0.minusElement("3").joinToString(),
                Collectionx.joinToString(Collectionx.minusElement(emptyList1, "3")))
        assertEquals("", Collectionx.joinToString(Collectionx.minusElement(nullList0, "3")))
    }

    @Test
    fun testPartition() {
        val normalList0 = listOf("6", "3", "7", "2", "1")
        val normalList1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null

        assertTwoEquals("([6, 2], [3, 7, 1])",
                normalList0.partition { it.toInt() % 2 == 0 }.toString(),
                Collectionx.partition(normalList1) { it.toInt() % 2 == 0 }.toString())
        assertTwoEquals("([], [])",
                emptyList0.partition { it.toInt() % 2 == 0 }.toString(),
                Collectionx.partition(emptyList1) { it.toInt() % 2 == 0 }.toString())
        assertEquals("([], [])", Collectionx.partition(nullList0) { it.toInt() % 2 == 0 }.toString())
    }

    @Test
    fun testPlus() {
        val normalList0 = listOf("6", "3", "7", "2", "1")
        val normalList1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null

        assertTwoEquals("6, 3, 7, 2, 1, 9",
                normalList0.plus("9").joinToString(),
                Collectionx.joinToString(Collectionx.plus(normalList1, "9")))
        assertTwoEquals("9",
                emptyList0.plus("9").joinToString(),
                Collectionx.joinToString(Collectionx.plus(emptyList1, "9")))
        assertEquals("9", Collectionx.joinToString(Collectionx.plus(nullList0, "9")))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalList0.plus(arrayOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(normalList1, arrayOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptyList0.plus(arrayOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(emptyList1, arrayOf("9", "4"))))
        assertEquals("9, 4", Collectionx.joinToString(Collectionx.plus(nullList0, arrayOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalList0.plus(listOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(normalList1, listOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptyList0.plus(listOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(emptyList1, listOf("9", "4"))))
        assertEquals("9, 4", Collectionx.joinToString(Collectionx.plus(nullList0, listOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalList0.plus(listOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(normalList1, Collectionx.listOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptyList0.plus(listOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(emptyList1, Collectionx.listOf("9", "4"))))
        assertEquals("9, 4", Collectionx.joinToString(Collectionx.plus(nullList0, Collectionx.listOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9",
                normalList0.plusElement("9").joinToString(),
                Collectionx.joinToString(Collectionx.plusElement(normalList1, "9")))
        assertTwoEquals("9",
                emptyList0.plusElement("9").joinToString(),
                Collectionx.joinToString(Collectionx.plusElement(emptyList1, "9")))
        assertEquals("9", Collectionx.joinToString(Collectionx.plusElement(nullList0, "9")))
    }

    @Test
    fun testZip() {
        val normalList0 = listOf("6", "3", "7", "2", "1")
        val normalList00 = listOf("4", "9", "5")
        val normalList1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val normalList11 = Collectionx.listOf("4", "9", "5")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null

        assertTwoEquals("(6, 4), (3, 9), (7, 5)",
                normalList0.zip(normalList00).joinToString(),
                Collectionx.joinToString(Collectionx.zip(normalList1, normalList11)))
        assertTwoEquals("",
                emptyList0.zip(normalList00).joinToString(),
                Collectionx.joinToString(Collectionx.zip(emptyList1, normalList11)))
        assertEquals("", Collectionx.joinToString(Collectionx.zip(nullList0, normalList11)))

        assertTwoEquals("64, 39, 75",
                normalList0.zip(normalList00) { it0, it1 -> it0 + it1 }.joinToString(),
                Collectionx.joinToString(Collectionx.zip(normalList1, normalList11) { it0, it1 -> it0 + it1 }))
        assertTwoEquals("",
                emptyList0.zip(normalList00) { it0, it1 -> it0 + it1 }.joinToString(),
                Collectionx.joinToString(Collectionx.zip(emptyList1, normalList11) { it0, it1 -> it0 + it1 }))
        assertEquals("", Collectionx.joinToString(Collectionx.zip(nullList0, normalList11) { it0, it1 -> it0 + it1 }))

        assertTwoEquals("([6, 3, 7], [4, 9, 5])",
                normalList0.zip(normalList00).unzip().toString(),
                Collectionx.unzip(Collectionx.zip(normalList1, normalList11)).toString())
        assertTwoEquals("([], [])",
                listOf<Pair<String, String>>().unzip().toString(),
                Collectionx.unzip(Collectionx.listOf<com.github.panpf.tools4j.common.Pair<String, String>>()).toString())
        assertEquals("([], [])", Collectionx.unzip(null as List<com.github.panpf.tools4j.common.Pair<String, String>>?).toString())
    }

    @Test
    fun testJoinTo() {
        val normalList0 = listOf("6", "3", "7", "2", "1")
        val normalList1 = Collectionx.listOf("6", "3", "7", "2", "1")
        val emptyList0 = listOf<String>()
        val emptyList1 = Collectionx.listOf<String>()
        val nullList0: List<String>? = null

        assertTwoEquals("^60:30:70:20:***$",
                normalList0.joinTo(buffer = StringBuilder(), separator = ":", prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList1, StringBuilder(), ":", "^", "$", 4, "***", { it + "0" }).toString())
        assertTwoEquals("^60, 30, 70, 20, ***$",
                normalList0.joinTo(buffer = StringBuilder(), prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList1, StringBuilder(), null, "^", "$", 4, "***", { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, ***$",
                normalList0.joinTo(buffer = StringBuilder(), postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList1, StringBuilder(), null, null, "$", 4, "***", { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, ***",
                normalList0.joinTo(buffer = StringBuilder(), limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList1, StringBuilder(), null, null, null, 4, "***", { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, ...",
                normalList0.joinTo(buffer = StringBuilder(), limit = 4, transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList1, StringBuilder(), null, null, null, 4, null, { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, 10",
                normalList0.joinTo(buffer = StringBuilder(), limit = 5, transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList1, StringBuilder(), null, null, null, 5, null, { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, 10",
                normalList0.joinTo(buffer = StringBuilder(), transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList1, StringBuilder(), null, null, null, -1, null, { it + "0" }).toString())
        assertTwoEquals("6, 3, 7, 2, 1",
                normalList0.joinTo(buffer = StringBuilder()).toString(),
                Collectionx.joinTo(normalList1, StringBuilder(), null, null, null, -1, null, null).toString())
        assertTwoEquals("^$",
                emptyList0.joinTo(buffer = StringBuilder(), prefix = "^", postfix = "$").toString(),
                Collectionx.joinTo(emptyList1, StringBuilder(), null, "^", "$", -1, null, null).toString())
        assertTwoEquals("",
                emptyList0.joinTo(buffer = StringBuilder()).toString(),
                Collectionx.joinTo(emptyList1, StringBuilder(), null, null, null, -1, null, null).toString())
        assertEquals("^$",
                Collectionx.joinTo(nullList0, StringBuilder(), null, "^", "$", -1, null, null).toString())
        assertEquals("",
                Collectionx.joinTo(nullList0, StringBuilder(), null, null, null, -1, null, null).toString())

        assertTwoEquals("60,30,70,20,10",
                normalList0.joinTo(buffer = StringBuilder(), separator = ",", transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList1, StringBuilder(), ",", { it + "0" }).toString())
        assertTwoEquals("",
                emptyList0.joinTo(buffer = StringBuilder(), separator = ",", transform = { it + "0" }).toString(),
                Collectionx.joinTo(emptyList1, StringBuilder(), ",", { it + "0" }).toString())
        assertEquals("",
                Collectionx.joinTo(nullList0, StringBuilder(), ",", { it + "0" }).toString())

        assertTwoEquals("60, 30, 70, 20, 10",
                normalList0.joinTo(buffer = StringBuilder(), transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList1, StringBuilder(), { it + "0" }).toString())
        assertTwoEquals("",
                emptyList0.joinTo(buffer = StringBuilder(), transform = { it + "0" }).toString(),
                Collectionx.joinTo(emptyList1, StringBuilder(), { it + "0" }).toString())
        assertEquals("",
                Collectionx.joinTo(nullList0, StringBuilder(), { it + "0" }).toString())

        assertTwoEquals("6,3,7,2,1",
                normalList0.joinTo(buffer = StringBuilder(), separator = ",").toString(),
                Collectionx.joinTo(normalList1, StringBuilder(), ",").toString())
        assertTwoEquals("",
                emptyList0.joinTo(buffer = StringBuilder(), separator = ",").toString(),
                Collectionx.joinTo(emptyList1, StringBuilder(), ",").toString())
        assertEquals("",
                Collectionx.joinTo(nullList0, StringBuilder(), ",").toString())

        assertTwoEquals("6, 3, 7, 2, 1",
                normalList0.joinTo(buffer = StringBuilder()).toString(),
                Collectionx.joinTo(normalList1, StringBuilder()).toString())
        assertTwoEquals("",
                emptyList0.joinTo(buffer = StringBuilder()).toString(),
                Collectionx.joinTo(emptyList1, StringBuilder()).toString())
        assertEquals("",
                Collectionx.joinTo(nullList0, StringBuilder()).toString())

        assertThrow(IOException::class) { emptyList0.joinTo(buffer = ExceptionAppendable()).toString() }
        assertThrow(RuntimeException::class) { Collectionx.joinTo(emptyList1, ExceptionAppendable(), null, null, null, -1, null, null).toString() }


        assertTwoEquals("^60:30:70:20:***$",
                normalList0.joinToString(separator = ":", prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }),
                Collectionx.joinToString(normalList1, ":", "^", "$", 4, "***") { it + "0" })
        assertTwoEquals("^60, 30, 70, 20, ***$",
                normalList0.joinToString(prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }),
                Collectionx.joinToString(normalList1, null, "^", "$", 4, "***") { it + "0" })
        assertTwoEquals("60, 30, 70, 20, ***$",
                normalList0.joinToString(postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }),
                Collectionx.joinToString(normalList1, null, null, "$", 4, "***") { it + "0" })
        assertTwoEquals("60, 30, 70, 20, ***",
                normalList0.joinToString(limit = 4, truncated = "***", transform = { it + "0" }),
                Collectionx.joinToString(normalList1, null, null, null, 4, "***") { it + "0" })
        assertTwoEquals("60, 30, 70, 20, ...",
                normalList0.joinToString(limit = 4, transform = { it + "0" }),
                Collectionx.joinToString(normalList1, null, null, null, 4, null) { it + "0" })
        assertTwoEquals("60, 30, 70, 20, 10",
                normalList0.joinToString(transform = { it + "0" }),
                Collectionx.joinToString(normalList1, null, null, null, -1, null) { it + "0" })
        assertTwoEquals("6, 3, 7, 2, 1",
                normalList0.joinToString(),
                Collectionx.joinToString(normalList1, null, null, null, -1, null, null))
        assertTwoEquals("^$",
                emptyList0.joinToString(prefix = "^", postfix = "$"),
                Collectionx.joinToString(emptyList1, null, "^", "$", -1, null, null))
        assertTwoEquals("",
                emptyList0.joinToString(),
                Collectionx.joinToString(emptyList1, null, null, null, -1, null, null))
        assertEquals("^$",
                Collectionx.joinToString(nullList0, null, "^", "$", -1, null, null))
        assertEquals("",
                Collectionx.joinToString(nullList0, null, null, null, -1, null, null))

        assertTwoEquals("60,30,70,20,10",
                normalList0.joinToString(separator = ",", transform = { it + "0" }),
                Collectionx.joinToString(normalList1, ",") { it + "0" })
        assertTwoEquals("",
                emptyList0.joinToString(separator = ",", transform = { it + "0" }),
                Collectionx.joinToString(emptyList1, ",") { it + "0" })
        assertEquals("",
                Collectionx.joinToString(nullList0, ",") { it + "0" })

        assertTwoEquals("60, 30, 70, 20, 10",
                normalList0.joinToString(transform = { it + "0" }),
                Collectionx.joinToString(normalList1) { it + "0" })
        assertTwoEquals("",
                emptyList0.joinToString(transform = { it + "0" }),
                Collectionx.joinToString(emptyList1) { it + "0" })
        assertEquals("",
                Collectionx.joinToString(nullList0) { it + "0" })

        assertTwoEquals("6,3,7,2,1",
                normalList0.joinToString(separator = ","),
                Collectionx.joinToString(normalList1, ","))
        assertTwoEquals("",
                emptyList0.joinToString(separator = ","),
                Collectionx.joinToString(emptyList1, ","))
        assertEquals("",
                Collectionx.joinToString(nullList0, ","))

        assertTwoEquals("6, 3, 7, 2, 1",
                normalList0.joinToString(),
                Collectionx.joinToString(normalList1))
        assertTwoEquals("",
                emptyList0.joinToString(),
                Collectionx.joinToString(emptyList1))
        assertEquals("",
                Collectionx.joinToString(nullList0))
    }

    @Test
    fun testAverage() {
        val normalByteList0 = listOf(6.toByte(), 3.toByte(), 7.toByte(), 2.toByte(), 1.toByte())
        val normalByteList1 = Collectionx.listOf(6.toByte(), 3.toByte(), 7.toByte(), 2.toByte(), 1.toByte())
        val emptyByteList0 = listOf<Byte>()
        val emptyByteList1 = Collectionx.listOf<Byte>()
        val nullByteList1: List<Byte>? = null
        assertTwoEquals(3.8, normalByteList0.average(), Collectionx.averageOfByte(normalByteList1))
        assertTwoEquals(Double.NaN, emptyByteList0.average(), Collectionx.averageOfByte(emptyByteList1))
        assertTwoEquals(Double.NaN, Double.NaN, Collectionx.averageOfByte(nullByteList1))

        val normalShortList0 = listOf(6.toShort(), 3.toShort(), 7.toShort(), 2.toShort(), 1.toShort())
        val normalShortList1 = Collectionx.listOf(6.toShort(), 3.toShort(), 7.toShort(), 2.toShort(), 1.toShort())
        val emptyShortList0 = listOf<Short>()
        val emptyShortList1 = Collectionx.listOf<Short>()
        val nullShortList1: List<Short>? = null
        assertTwoEquals(3.8, normalShortList0.average(), Collectionx.averageOfShort(normalShortList1))
        assertTwoEquals(Double.NaN, emptyShortList0.average(), Collectionx.averageOfShort(emptyShortList1))
        assertTwoEquals(Double.NaN, Double.NaN, Collectionx.averageOfShort(nullShortList1))

        val normalIntList0 = listOf(6, 3, 7, 2, 1)
        val normalIntList1 = Collectionx.listOf(6, 3, 7, 2, 1)
        val emptyIntList0 = listOf<Int>()
        val emptyIntList1 = Collectionx.listOf<Int>()
        val nullIntList1: List<Int>? = null
        assertTwoEquals(3.8, normalIntList0.average(), Collectionx.averageOfInt(normalIntList1))
        assertTwoEquals(Double.NaN, emptyIntList0.average(), Collectionx.averageOfInt(emptyIntList1))
        assertTwoEquals(Double.NaN, Double.NaN, Collectionx.averageOfInt(nullIntList1))

        val normalLongList0 = listOf(6.toLong(), 3.toLong(), 7.toLong(), 2.toLong(), 1.toLong())
        val normalLongList1 = Collectionx.listOf(6.toLong(), 3.toLong(), 7.toLong(), 2.toLong(), 1.toLong())
        val emptyLongList0 = listOf<Long>()
        val emptyLongList1 = Collectionx.listOf<Long>()
        val nullLongList1: List<Long>? = null
        assertTwoEquals(3.8, normalLongList0.average(), Collectionx.averageOfLong(normalLongList1))
        assertTwoEquals(Double.NaN, emptyLongList0.average(), Collectionx.averageOfLong(emptyLongList1))
        assertTwoEquals(Double.NaN, Double.NaN, Collectionx.averageOfLong(nullLongList1))

        val normalFloatList0 = listOf(6.toFloat(), 3.toFloat(), 7.toFloat(), 2.toFloat(), 1.toFloat())
        val normalFloatList1 = Collectionx.listOf(6.toFloat(), 3.toFloat(), 7.toFloat(), 2.toFloat(), 1.toFloat())
        val emptyFloatList0 = listOf<Float>()
        val emptyFloatList1 = Collectionx.listOf<Float>()
        val nullFloatList1: List<Float>? = null
        assertTwoEquals(3.8, normalFloatList0.average(), Collectionx.averageOfFloat(normalFloatList1))
        assertTwoEquals(Double.NaN, emptyFloatList0.average(), Collectionx.averageOfFloat(emptyFloatList1))
        assertTwoEquals(Double.NaN, Double.NaN, Collectionx.averageOfFloat(nullFloatList1))

        val normalDoubleList0 = listOf(6.toDouble(), 3.toDouble(), 7.toDouble(), 2.toDouble(), 1.toDouble())
        val normalDoubleList1 = Collectionx.listOf(6.toDouble(), 3.toDouble(), 7.toDouble(), 2.toDouble(), 1.toDouble())
        val emptyDoubleList0 = listOf<Double>()
        val emptyDoubleList1 = Collectionx.listOf<Double>()
        val nullDoubleList1: List<Double>? = null
        assertTwoEquals(3.8, normalDoubleList0.average(), Collectionx.averageOfDouble(normalDoubleList1))
        assertTwoEquals(Double.NaN, emptyDoubleList0.average(), Collectionx.averageOfDouble(emptyDoubleList1))
        assertTwoEquals(Double.NaN, Double.NaN, Collectionx.averageOfDouble(nullDoubleList1))
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