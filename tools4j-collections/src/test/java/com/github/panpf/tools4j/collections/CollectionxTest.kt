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
import com.github.panpf.tools4j.test.ktx.assertNoThrow
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
        val normalList = listOf("6", "3", "7", "2", "1")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals("[60, 30, 70, 20, 10]",
                normalList.joinToString(transform = { it + "0" }, prefix = "[", postfix = "]"),
                Collectionx.joinToArrayString(normalList) { it + "0" })
        assertTwoEquals("[]",
                emptyList.joinToString(transform = { it + "0" }, prefix = "[", postfix = "]"),
                Collectionx.joinToArrayString(emptyList) { it + "0" })
        assertEquals("[]",
                Collectionx.joinToArrayString(nullList) { it + "0" })

        assertTwoEquals("[6, 3, 7, 2, 1]",
                normalList.joinToString(prefix = "[", postfix = "]"),
                Collectionx.joinToArrayString(normalList))
        assertTwoEquals("[]",
                emptyList.joinToString(prefix = "[", postfix = "]"),
                Collectionx.joinToArrayString(emptyList))
        assertEquals("[]",
                Collectionx.joinToArrayString(nullList))
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
        assertTwoEquals(0, Collectionx.listOf<String>().size, listOf<String>().size)
        assertTwoEquals("EmptyList", Collectionx.listOf<String>()::class.simpleName, listOf<String>()::class.simpleName)
        assertThrow(UnsupportedOperationException::class) { Collectionx.listOf<String>().add("3") }

        assertTwoEquals(0, Collectionx.listOf(*arrayOf<String>()).size, listOf(*arrayOf<String>()).size)
        assertEquals("EmptyList", Collectionx.listOf(*arrayOf<String>())::class.simpleName, listOf(*arrayOf<String>())::class.simpleName)
        assertThrow(UnsupportedOperationException::class) { Collectionx.listOf(*arrayOf<String>()).add("3") }

        assertTwoEquals(1, Collectionx.listOf(null as String?).size, listOf(null as String?).size)
        assertTwoEquals(Collections.singletonList(null as String?)::class, Collectionx.listOf(null as String?)::class, listOf(null as String?)::class)
        assertThrow(UnsupportedOperationException::class) { Collectionx.listOf(null as String?).add("3") }

        assertTwoEquals(1, Collectionx.listOf("1").size, listOf("1").size)
        assertTwoEquals(Collections.singletonList("1")::class, Collectionx.listOf("1")::class, listOf("1")::class)
        assertThrow(UnsupportedOperationException::class) { Collectionx.listOf("1").add("3") }

        assertTwoEquals(2, Collectionx.listOf("1", "2").size, listOf("1", "2").size)
        assertTwoEquals("ArrayList", Collectionx.listOf("1", "2")::class.simpleName, listOf("1", "2")::class.simpleName)
        assertThrow(UnsupportedOperationException::class) { Collectionx.listOf("1", "2").add("3") }


        assertTwoEquals(0, Collectionx.immutableListOf<String>().size, listOf<String>().size)
        assertTwoEquals("EmptyList", Collectionx.immutableListOf<String>()::class.simpleName, listOf<String>()::class.simpleName)
        assertThrow(UnsupportedOperationException::class) { Collectionx.immutableListOf<String>().add("3") }

        assertTwoEquals(0, Collectionx.immutableListOf(*arrayOf<String>()).size, listOf(*arrayOf<String>()).size)
        assertEquals("EmptyList", Collectionx.immutableListOf(*arrayOf<String>())::class.simpleName, listOf(*arrayOf<String>())::class.simpleName)
        assertThrow(UnsupportedOperationException::class) { Collectionx.immutableListOf(*arrayOf<String>()).add("3") }

        assertTwoEquals(1, Collectionx.immutableListOf(null as String?).size, listOf(null as String?).size)
        assertTwoEquals(Collections.singletonList(null as String?)::class, Collectionx.immutableListOf(null as String?)::class, listOf(null as String?)::class)
        assertThrow(UnsupportedOperationException::class) { Collectionx.immutableListOf(null as String?).add("3") }

        assertTwoEquals(1, Collectionx.immutableListOf("1").size, listOf("1").size)
        assertTwoEquals(Collections.singletonList("1")::class, Collectionx.immutableListOf("1")::class, listOf("1")::class)
        assertThrow(UnsupportedOperationException::class) { Collectionx.immutableListOf("1").add("3") }

        assertTwoEquals(2, Collectionx.immutableListOf("1", "2").size, listOf("1", "2").size)
        assertTwoEquals("ArrayList", Collectionx.immutableListOf("1", "2")::class.simpleName, listOf("1", "2")::class.simpleName)
        assertThrow(UnsupportedOperationException::class) { Collectionx.immutableListOf("1", "2").add("3") }


        assertTwoEquals(0, Collectionx.mutableListOf<String>().size, mutableListOf<String>().size)
        assertTwoEquals(ArrayList::class, Collectionx.mutableListOf<String>()::class, mutableListOf<String>()::class)
        assertTwoEquals(1, Collectionx.mutableListOf<String>().apply { add("3") }.size, mutableListOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.mutableListOf("1", "2").size, mutableListOf("1", "2").size)
        assertTwoEquals(ArrayList::class, Collectionx.mutableListOf("1", "2")::class, mutableListOf("1", "2")::class)
        assertTwoEquals(3, Collectionx.mutableListOf("1", "2").apply { add("3") }.size, mutableListOf("1", "2").apply { add("3") }.size)


        assertTwoEquals(0, Collectionx.arrayListOf<String>().size, arrayListOf<String>().size)
        assertTwoEquals(ArrayList::class, Collectionx.arrayListOf<String>()::class, arrayListOf<String>()::class)
        assertTwoEquals(1, Collectionx.arrayListOf<String>().apply { add("3") }.size, arrayListOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.arrayListOf("1", "2").size, arrayListOf("1", "2").size)
        assertTwoEquals(ArrayList::class, Collectionx.arrayListOf("1", "2")::class, arrayListOf("1", "2")::class)
        assertTwoEquals(3, Collectionx.arrayListOf("1", "2").apply { add("3") }.size, arrayListOf("1", "2").apply { add("3") }.size)

        assertTwoEquals(0, Collectionx.emptyList<String>().size, emptyList<String>().size)
        assertTwoEquals("EmptyList", Collectionx.emptyList<String>()::class.simpleName, emptyList<String>()::class.simpleName)
    }

    @Test
    fun testSetOf() {
        assertTwoEquals(0, Collectionx.immutableSetOf<String>().size, setOf<String>().size)
        assertEquals("EmptySet", Collectionx.immutableSetOf<String>()::class.simpleName, setOf<String>()::class.simpleName)
        assertThrow(UnsupportedOperationException::class) { Collectionx.immutableSetOf<String>().add("3") }

        assertTwoEquals(0, Collectionx.immutableSetOf(*arrayOf<String>()).size, setOf(*arrayOf<String>()).size)
        assertEquals("EmptySet", Collectionx.immutableSetOf(*arrayOf<String>())::class.simpleName, setOf(*arrayOf<String>())::class.simpleName)
        assertThrow(UnsupportedOperationException::class) { Collectionx.immutableSetOf(*arrayOf<String>()).add("3") }

        assertTwoEquals(1, Collectionx.immutableSetOf(null as String?).size, setOf(null as String?).size)
        assertTwoEquals(Collections.singleton(null as String?)::class, Collectionx.immutableSetOf(null as String?)::class, setOf(null as String?)::class)
        assertThrow(UnsupportedOperationException::class) { Collectionx.immutableSetOf(null as String?).add("3") }

        assertTwoEquals(1, Collectionx.immutableSetOf("1").size, setOf("1").size)
        assertTwoEquals(Collections.singleton("1")::class, Collectionx.immutableSetOf("1")::class, setOf("1")::class)
        assertThrow(UnsupportedOperationException::class) { Collectionx.immutableSetOf("1").add("3") }

        assertTwoEquals(2, Collectionx.immutableSetOf("1", "2").size, setOf("1", "2").size)
        assertTwoEquals(LinkedHashSet::class, Collectionx.immutableSetOf("1", "2")::class, setOf("1", "2")::class)
        assertNoThrow { Collectionx.immutableSetOf("1", "2").add("3") }
        assertNoThrow { (setOf("1", "2") as LinkedHashSet).add("3") }


        assertTwoEquals(0, Collectionx.mutableSetOf<String>().size, mutableSetOf<String>().size)
        assertTwoEquals(java.util.LinkedHashSet::class, Collectionx.mutableSetOf<String>()::class, mutableSetOf<String>()::class)
        assertTwoEquals(1, Collectionx.mutableSetOf<String>().apply { add("3") }.size, mutableSetOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.mutableSetOf("1", "2").size, mutableSetOf("1", "2").size)
        assertTwoEquals(java.util.LinkedHashSet::class, Collectionx.mutableSetOf("1", "2")::class, mutableSetOf("1", "2")::class)
        assertTwoEquals(3, Collectionx.mutableSetOf("1", "2").apply { add("3") }.size, mutableSetOf("1", "2").apply { add("3") }.size)


        assertTwoEquals(0, Collectionx.hashSetOf<String>().size, hashSetOf<String>().size)
        assertTwoEquals(HashSet::class, Collectionx.hashSetOf<String>()::class, hashSetOf<String>()::class)
        assertTwoEquals(1, Collectionx.hashSetOf<String>().apply { add("3") }.size, hashSetOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.hashSetOf("1", "2").size, hashSetOf("1", "2").size)
        assertTwoEquals(HashSet::class, Collectionx.hashSetOf("1", "2")::class, hashSetOf("1", "2")::class)
        assertTwoEquals(3, Collectionx.hashSetOf("1", "2").apply { add("3") }.size, hashSetOf("1", "2").apply { add("3") }.size)


        assertTwoEquals(0, Collectionx.linkedSetOf<String>().size, linkedSetOf<String>().size)
        assertTwoEquals(java.util.LinkedHashSet::class, Collectionx.linkedSetOf<String>()::class, linkedSetOf<String>()::class)
        assertTwoEquals(1, Collectionx.linkedSetOf<String>().apply { add("3") }.size, linkedSetOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.linkedSetOf("1", "2").size, linkedSetOf("1", "2").size)
        assertTwoEquals(java.util.LinkedHashSet::class, Collectionx.linkedSetOf("1", "2")::class, linkedSetOf("1", "2")::class)
        assertTwoEquals(3, Collectionx.linkedSetOf("1", "2").apply { add("3") }.size, linkedSetOf("1", "2").apply { add("3") }.size)


        assertTwoEquals(0, Collectionx.sortedSetOf<String>().size, sortedSetOf<String>().size)
        assertTwoEquals(java.util.TreeSet::class, Collectionx.sortedSetOf<String>()::class, sortedSetOf<String>()::class)
        assertTwoEquals(1, Collectionx.sortedSetOf<String>().apply { add("3") }.size, sortedSetOf<String>().apply { add("3") }.size)

        assertTwoEquals(2, Collectionx.sortedSetOf("1", "2").size, sortedSetOf("1", "2").size)
        assertTwoEquals(java.util.TreeSet::class, Collectionx.sortedSetOf("1", "2")::class, sortedSetOf("1", "2")::class)
        assertTwoEquals(3, Collectionx.sortedSetOf("1", "2").apply { add("3") }.size, sortedSetOf("1", "2").apply { add("3") }.size)

        assertTwoEquals("2,1", Collectionx.sortedSetOf({ it1, it2 -> (it1.toInt() - it2.toInt()) * -1 }, "1", "2").joinToString(","),
                sortedSetOf(Comparator { it1, it2 -> (it1.toInt() - it2.toInt()) * -1 }, "1", "2").joinToString(","))

        assertTwoEquals(0, Collectionx.emptySet<String>().size, emptySet<String>().size)
        assertTwoEquals("EmptySet", Collectionx.emptySet<String>()::class.simpleName, emptySet<String>()::class.simpleName)
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
        val normalList = listOf("a", "b", "c")
        val nullList = null as List<String>?

        assertTwoEquals("a", normalList.elementAt(0), Collectionx.elementAt(normalList, 0))
        assertTwoEquals("b", normalList.elementAt(1), Collectionx.elementAt(normalList, 1))
        assertTwoEquals("c", normalList.elementAt(2), Collectionx.elementAt(normalList, 2))
        assertTwoThrow(ArrayIndexOutOfBoundsException::class, { normalList.elementAt(-1) }, { Collectionx.elementAt(normalList, -1) })
        assertTwoThrow(ArrayIndexOutOfBoundsException::class, { normalList.elementAt(3) }, { Collectionx.elementAt(normalList, 3) })

        assertTwoEquals("a", normalList.elementAtOrElse(0) { "j" }, Collectionx.elementAtOrElse(normalList, 0) { "j" })
        assertTwoEquals("b", normalList.elementAtOrElse(1) { "k" }, Collectionx.elementAtOrElse(normalList, 1) { "j" })
        assertTwoEquals("c", normalList.elementAtOrElse(2) { "j" }, Collectionx.elementAtOrElse(normalList, 2) { "j" })
        assertTwoEquals("j", normalList.elementAtOrElse(-1) { "j" }, Collectionx.elementAtOrElse(normalList, -1) { "j" })
        assertTwoEquals("k", normalList.elementAtOrElse(3) { "k" }, Collectionx.elementAtOrElse(normalList, 3) { "k" })
        assertEquals("x", Collectionx.elementAtOrElse(nullList, 0) { "x" })
        assertEquals("y", Collectionx.elementAtOrElse(nullList, 1) { "y" })
        assertEquals("z", Collectionx.elementAtOrElse(nullList, 2) { "z" })
        assertEquals("j", Collectionx.elementAtOrElse(nullList, -1) { "j" })
        assertEquals("k", Collectionx.elementAtOrElse(nullList, 3) { "k" })

        assertTwoEquals("a", normalList.elementAtOrNull(0), Collectionx.elementAtOrNull(normalList, 0))
        assertTwoEquals("b", normalList.elementAtOrNull(1), Collectionx.elementAtOrNull(normalList, 1))
        assertTwoEquals("c", normalList.elementAtOrNull(2), Collectionx.elementAtOrNull(normalList, 2))
        assertTwoEquals(null, normalList.elementAtOrNull(-1), Collectionx.elementAtOrNull(normalList, -1))
        assertTwoEquals(null, normalList.elementAtOrNull(3), Collectionx.elementAtOrNull(normalList, 3))
        assertNull(Collectionx.elementAtOrNull(nullList, 0))
        assertNull(Collectionx.elementAtOrNull(nullList, 1))
        assertNull(Collectionx.elementAtOrNull(nullList, 2))
        assertNull(Collectionx.elementAtOrNull(nullList, -1))
        assertNull(Collectionx.elementAtOrNull(nullList, 3))
    }

    @Test
    fun testFind() {
        val normalList = listOf("aj", "bj", "cj", "bo")
        val normalIterable = normalList as Iterable<String>

        assertTwoEquals("aj", normalList.find { it.startsWith("a") }, Collectionx.find(normalList) { it.startsWith("a") })
        assertTwoEquals("bj", normalList.find { it.startsWith("b") }, Collectionx.find(normalList) { it.startsWith("b") })
        assertTwoEquals("cj", normalList.find { it.startsWith("c") }, Collectionx.find(normalList) { it.startsWith("c") })
        assertTwoEquals(null, normalList.find { it.startsWith("k") }, Collectionx.find(normalList) { it.startsWith("k") })

        assertTwoEquals("aj", normalIterable.findLast { it.startsWith("a") }, Collectionx.findLast(normalIterable) { it.startsWith("a") })
        assertTwoEquals("bo", normalIterable.findLast { it.startsWith("b") }, Collectionx.findLast(normalIterable) { it.startsWith("b") })
        assertTwoEquals("cj", normalIterable.findLast { it.startsWith("c") }, Collectionx.findLast(normalIterable) { it.startsWith("c") })
        assertTwoEquals(null, normalIterable.findLast { it.startsWith("k") }, Collectionx.findLast(normalIterable) { it.startsWith("k") })

        assertTwoEquals("aj", normalList.findLast { it.startsWith("a") }, Collectionx.findLast(normalList) { it.startsWith("a") })
        assertTwoEquals("bo", normalList.findLast { it.startsWith("b") }, Collectionx.findLast(normalList) { it.startsWith("b") })
        assertTwoEquals("cj", normalList.findLast { it.startsWith("c") }, Collectionx.findLast(normalList) { it.startsWith("c") })
        assertTwoEquals(null, normalList.findLast { it.startsWith("k") }, Collectionx.findLast(normalList) { it.startsWith("k") })
    }

    @Test
    fun testGet() {
        val normalList = listOf("aj", "bj", "cj", "bo")
        val emptyList = listOf<String>()
        val nullList = null as List<String>?

        assertTwoEquals("aj", normalList.getOrElse(0) { it.toString() }, Collectionx.getOrElse(normalList, 0) { it.toString() })
        assertTwoEquals("bj", normalList.getOrElse(1) { it.toString() }, Collectionx.getOrElse(normalList, 1) { it.toString() })
        assertTwoEquals("-1", normalList.getOrElse(-1) { it.toString() }, Collectionx.getOrElse(normalList, -1) { it.toString() })
        assertTwoEquals("5", normalList.getOrElse(5) { it.toString() }, Collectionx.getOrElse(normalList, 5) { it.toString() })
        assertTwoEquals("0", emptyList.getOrElse(0) { it.toString() }, Collectionx.getOrElse(emptyList, 0) { it.toString() })
        assertEquals("0", Collectionx.getOrElse(nullList, 0) { it.toString() })

        assertTwoEquals("aj", normalList.getOrNull(0), Collectionx.getOrNull(normalList, 0))
        assertTwoEquals("bj", normalList.getOrNull(1), Collectionx.getOrNull(normalList, 1))
        assertTwoEquals(null, normalList.getOrNull(-1), Collectionx.getOrNull(normalList, -1))
        assertTwoEquals(null, normalList.getOrNull(5), Collectionx.getOrNull(normalList, 5))
        assertTwoEquals(null, emptyList.getOrNull(0), Collectionx.getOrNull(emptyList, 0))
        assertNull(Collectionx.getOrNull(nullList, 0))
    }

    @Test
    fun testFirst() {
        val normalList = listOf("aj", "bj", "cj", "bo")
        val normalAsIterable = normalList as Iterable<String>
        val normalIterable = object: Iterable<String>{
            override fun iterator(): Iterator<String> {
                return normalList.iterator()
            }
        }
        val emptyList = listOf<String>()
        val emptyIterable = object: Iterable<String>{
            override fun iterator(): Iterator<String> {
                return emptyList.iterator()
            }
        }
        val nullList = null as List<String>?
        val nullIterable = nullList as Iterable<String>?

        assertTwoEquals("aj", normalList.first(), Collectionx.first(normalList))
        assertTwoThrow(NoSuchElementException::class, { emptyList.first() }, { Collectionx.first(emptyList) })
        assertThrow(NoSuchElementException::class) { Collectionx.first(nullList) }

        assertTwoEquals("aj", normalAsIterable.first(), Collectionx.first(normalAsIterable))
        assertTwoEquals("aj", normalIterable.first(), Collectionx.first(normalIterable))
        assertTwoThrow(NoSuchElementException::class, { emptyIterable.first() }, { Collectionx.first(emptyIterable) })
        assertThrow(NoSuchElementException::class) { Collectionx.first(nullIterable) }

        assertTwoEquals("aj", normalList.first { it.startsWith("a") }, Collectionx.first(normalList) { it.startsWith("a") })
        assertTwoEquals("bj", normalList.first { it.startsWith("b") }, Collectionx.first(normalList) { it.startsWith("b") })
        assertTwoEquals("cj", normalList.first { it.startsWith("c") }, Collectionx.first(normalList) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { normalList.first { it.startsWith("k") } }, { Collectionx.first(normalList) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptyList.first { it.startsWith("a") } }, { Collectionx.first(emptyList) { it.startsWith("a") } })
        assertThrow(NoSuchElementException::class) { Collectionx.first(nullList) { it.startsWith("a") } }

        assertTwoEquals("aj", normalList.firstOrNull(), Collectionx.firstOrNull(normalList))
        assertTwoEquals(null, emptyList.firstOrNull(), Collectionx.firstOrNull(emptyList))
        assertNull(Collectionx.firstOrNull(nullList))

        assertTwoEquals("aj", normalAsIterable.firstOrNull(), Collectionx.firstOrNull(normalAsIterable))
        assertTwoEquals("aj", normalIterable.firstOrNull(), Collectionx.firstOrNull(normalIterable))
        assertTwoEquals(null, emptyIterable.firstOrNull(), Collectionx.firstOrNull(emptyIterable))
        assertNull(Collectionx.firstOrNull(nullIterable))

        assertTwoEquals("aj", normalList.firstOrNull { it.startsWith("a") }, Collectionx.firstOrNull(normalList) { it.startsWith("a") })
        assertTwoEquals("bj", normalList.firstOrNull { it.startsWith("b") }, Collectionx.firstOrNull(normalList) { it.startsWith("b") })
        assertTwoEquals("cj", normalList.firstOrNull { it.startsWith("c") }, Collectionx.firstOrNull(normalList) { it.startsWith("c") })
        assertTwoEquals(null, normalList.firstOrNull { it.startsWith("k") }, Collectionx.firstOrNull(normalList) { it.startsWith("k") })
        assertTwoEquals(null, emptyList.firstOrNull { it.startsWith("k") }, Collectionx.firstOrNull(emptyList) { it.startsWith("k") })
        assertNull(Collectionx.firstOrNull(nullList) { it.startsWith("k") })
    }

    @Test
    fun testIndexOf() {
        val normalList = listOf("aj", "bj", "cj", "bo")
        val emptyList = listOf<String>()
        val nullList = null as List<String>?

        assertTwoEquals(0, normalList.indexOf("aj"), Collectionx.indexOf(normalList, "aj"))
        assertTwoEquals(1, normalList.indexOf("bj"), Collectionx.indexOf(normalList, "bj"))
        assertTwoEquals(2, normalList.indexOf("cj"), Collectionx.indexOf(normalList, "cj"))
        assertTwoEquals(3, normalList.indexOf("bo"), Collectionx.indexOf(normalList, "bo"))
        assertTwoEquals(-1, normalList.indexOf("bb"), Collectionx.indexOf(normalList, "bb"))
        assertTwoEquals(-1, emptyList.indexOf("bb"), Collectionx.indexOf(emptyList, "bb"))
        assertEquals(-1, Collectionx.indexOf(nullList, "bb"))

        assertTwoEquals(0, normalList.indexOfFirst { it.startsWith("a") }, Collectionx.indexOfFirst(normalList) { it.startsWith("a") })
        assertTwoEquals(1, normalList.indexOfFirst { it.startsWith("b") }, Collectionx.indexOfFirst(normalList) { it.startsWith("b") })
        assertTwoEquals(2, normalList.indexOfFirst { it.startsWith("c") }, Collectionx.indexOfFirst(normalList) { it.startsWith("c") })
        assertTwoEquals(-1, normalList.indexOfFirst { it.startsWith("k") }, Collectionx.indexOfFirst(normalList) { it.startsWith("k") })
        assertTwoEquals(-1, emptyList.indexOfFirst { it.startsWith("k") }, Collectionx.indexOfFirst(emptyList) { it.startsWith("k") })
        assertEquals(-1, Collectionx.indexOfFirst(nullList) { it.startsWith("k") })

        assertTwoEquals(0, normalList.indexOfLast { it.startsWith("a") }, Collectionx.indexOfLast(normalList) { it.startsWith("a") })
        assertTwoEquals(3, normalList.indexOfLast { it.startsWith("b") }, Collectionx.indexOfLast(normalList) { it.startsWith("b") })
        assertTwoEquals(2, normalList.indexOfLast { it.startsWith("c") }, Collectionx.indexOfLast(normalList) { it.startsWith("c") })
        assertTwoEquals(-1, normalList.indexOfLast { it.startsWith("k") }, Collectionx.indexOfLast(normalList) { it.startsWith("k") })
        assertTwoEquals(-1, emptyList.indexOfLast { it.startsWith("k") }, Collectionx.indexOfLast(emptyList) { it.startsWith("k") })
        assertEquals(-1, Collectionx.indexOfLast(nullList) { it.startsWith("k") })

        assertTwoEquals(0, normalList.lastIndexOf("aj"), Collectionx.lastIndexOf(normalList, "aj"))
        assertTwoEquals(1, normalList.lastIndexOf("bj"), Collectionx.lastIndexOf(normalList, "bj"))
        assertTwoEquals(2, normalList.lastIndexOf("cj"), Collectionx.lastIndexOf(normalList, "cj"))
        assertTwoEquals(3, normalList.lastIndexOf("bo"), Collectionx.lastIndexOf(normalList, "bo"))
        assertTwoEquals(-1, normalList.lastIndexOf("bb"), Collectionx.lastIndexOf(normalList, "bb"))
        assertTwoEquals(-1, emptyList.lastIndexOf("bb"), Collectionx.lastIndexOf(emptyList, "bb"))
        assertEquals(-1, Collectionx.lastIndexOf(nullList, "bb"))
    }

    @Test
    fun testLast() {
        val normalList = listOf("aj", "bj", "cj", "bo")
        val normalAsIterable = normalList as Iterable<String>
        val normalIterable = object: Iterable<String>{
            override fun iterator(): Iterator<String> {
                return normalList.iterator()
            }
        }
        val emptyList = listOf<String>()
        val emptyAsIterable = emptyList as Iterable<String>
        val emptyIterable = object: Iterable<String>{
            override fun iterator(): Iterator<String> {
                return emptyList.iterator()
            }
        }
        val nullList = null as List<String>?
        val nullIterable = nullList as Iterable<String>?

        assertTwoEquals("bo", normalList.last(), Collectionx.last(normalList))
        assertTwoThrow(NoSuchElementException::class, { emptyList.last() }, { Collectionx.last(emptyList) })
        assertThrow(NoSuchElementException::class) { Collectionx.last(nullList) }

        assertTwoEquals("bo", normalAsIterable.last(), Collectionx.last(normalAsIterable))
        assertTwoEquals("bo", normalIterable.last(), Collectionx.last(normalIterable))
        assertTwoThrow(NoSuchElementException::class, { emptyIterable.last() }, { Collectionx.last(emptyIterable) })
        assertThrow(NoSuchElementException::class) { Collectionx.last(nullIterable) }

        assertTwoEquals("aj", normalList.last { it.startsWith("a") }, Collectionx.last(normalList) { it.startsWith("a") })
        assertTwoEquals("bo", normalList.last { it.startsWith("b") }, Collectionx.last(normalList) { it.startsWith("b") })
        assertTwoEquals("cj", normalList.last { it.startsWith("c") }, Collectionx.last(normalList) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { normalList.last { it.startsWith("k") } }, { Collectionx.last(normalList) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptyList.last { it.startsWith("a") } }, { Collectionx.last(emptyList) { it.startsWith("a") } })
        assertThrow(NoSuchElementException::class) { Collectionx.last(nullList) { it.startsWith("a") } }

        assertTwoEquals("aj", normalIterable.last { it.startsWith("a") }, Collectionx.last(normalIterable) { it.startsWith("a") })
        assertTwoEquals("bo", normalIterable.last { it.startsWith("b") }, Collectionx.last(normalIterable) { it.startsWith("b") })
        assertTwoEquals("cj", normalIterable.last { it.startsWith("c") }, Collectionx.last(normalIterable) { it.startsWith("c") })
        assertTwoThrow(NoSuchElementException::class, { normalIterable.last { it.startsWith("k") } }, { Collectionx.last(normalIterable) { it.startsWith("k") } })
        assertTwoThrow(NoSuchElementException::class, { emptyIterable.last { it.startsWith("a") } }, { Collectionx.last(emptyIterable) { it.startsWith("a") } })
        assertThrow(NoSuchElementException::class) { Collectionx.last(nullIterable) { it.startsWith("a") } }

        assertTwoEquals("bo", normalList.lastOrNull(), Collectionx.lastOrNull(normalList))
        assertTwoEquals(null, emptyList.lastOrNull(), Collectionx.lastOrNull(emptyList))
        assertNull(Collectionx.lastOrNull(nullList))

        assertTwoEquals("bo", normalAsIterable.lastOrNull(), Collectionx.lastOrNull(normalAsIterable))
        assertTwoEquals("bo", normalIterable.lastOrNull(), Collectionx.lastOrNull(normalIterable))
        assertTwoEquals(null, emptyAsIterable.lastOrNull(), Collectionx.lastOrNull(emptyAsIterable))
        assertTwoEquals(null, emptyIterable.lastOrNull(), Collectionx.lastOrNull(emptyIterable))
        assertNull(Collectionx.lastOrNull(nullIterable))

        assertTwoEquals("aj", normalList.lastOrNull { it.startsWith("a") }, Collectionx.lastOrNull(normalList) { it.startsWith("a") })
        assertTwoEquals("bo", normalList.lastOrNull { it.startsWith("b") }, Collectionx.lastOrNull(normalList) { it.startsWith("b") })
        assertTwoEquals("cj", normalList.lastOrNull { it.startsWith("c") }, Collectionx.lastOrNull(normalList) { it.startsWith("c") })
        assertTwoEquals(null, normalList.lastOrNull { it.startsWith("k") }, Collectionx.lastOrNull(normalList) { it.startsWith("k") })
        assertTwoEquals(null, emptyList.lastOrNull { it.startsWith("k") }, Collectionx.lastOrNull(emptyList) { it.startsWith("k") })
        assertNull(Collectionx.lastOrNull(nullList) { it.startsWith("k") })

        assertTwoEquals("aj", normalIterable.lastOrNull { it.startsWith("a") }, Collectionx.lastOrNull(normalIterable) { it.startsWith("a") })
        assertTwoEquals("bo", normalIterable.lastOrNull { it.startsWith("b") }, Collectionx.lastOrNull(normalIterable) { it.startsWith("b") })
        assertTwoEquals("cj", normalIterable.lastOrNull { it.startsWith("c") }, Collectionx.lastOrNull(normalIterable) { it.startsWith("c") })
        assertTwoEquals(null, normalIterable.lastOrNull { it.startsWith("k") }, Collectionx.lastOrNull(normalIterable) { it.startsWith("k") })
        assertTwoEquals(null, emptyIterable.lastOrNull { it.startsWith("k") }, Collectionx.lastOrNull(emptyIterable) { it.startsWith("k") })
        assertNull(Collectionx.lastOrNull(nullIterable) { it.startsWith("k") })
    }

    @Test
    fun testSingle() {
        val singleIterable0 = listOf("cj")
        val singleIterable1 = Collectionx.listOf("cj")
        val multiIterable0 = listOf("aj", "bj", "cj", "bo")
        val multiIterable1 = Collectionx.listOf("aj", "bj", "cj", "bo")
        val emptyIterable0 = listOf<String>()
        val emptyIterable1 = Collectionx.listOf<String>()
        val nullList = null as List<String>?

        assertTwoEquals("cj", singleIterable0.single(), Collectionx.single(singleIterable1))
        assertTwoThrow(IllegalArgumentException::class, { multiIterable0.single() }, { Collectionx.single(multiIterable1) })
        assertTwoThrow(NoSuchElementException::class, { emptyIterable0.single() }, { Collectionx.single(emptyIterable1) })
        assertThrow(NoSuchElementException::class) { Collectionx.single(nullList) }

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
        assertThrow(NoSuchElementException::class) { Collectionx.single(nullList) { it.startsWith("b") } }

        assertTwoEquals("cj", singleIterable0.singleOrNull(), Collectionx.singleOrNull(singleIterable1))
        assertTwoEquals(null, multiIterable0.singleOrNull(), Collectionx.singleOrNull(multiIterable1))
        assertTwoEquals(null, emptyIterable0.singleOrNull(), Collectionx.singleOrNull(emptyIterable1))
        assertNull(Collectionx.singleOrNull(nullList))

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
                Collectionx.singleOrNull(nullList) { it.startsWith("b") })
    }

    @Test
    fun testDrop() {
        val normalList = listOf("aj", "bj", "cj", "dj")
        val nullList = null as List<String>?

        /*
         * drop 系列的方法表示从列表头部开始跳过部分元素
         */

        // drop 方法的意思是从列表头部开始跳过多少个元素

        assertTwoThrow(IllegalArgumentException::class,
                { normalList.drop(-1) },
                { Collectionx.drop(normalList, -1) }
        )

        assertTrue(normalList == normalList.drop(0))
        assertTrue(normalList == Collectionx.drop(normalList, 0))
        assertTrue(normalList !== normalList.drop(0))
        assertTrue(normalList !== Collectionx.drop(normalList, 0))
        assertTrue(Collectionx.drop(nullList, 0).isEmpty())

        assertTwoEquals("bj, cj, dj",
                normalList.drop(1).joinToString(),
                Collectionx.joinToString(Collectionx.drop(normalList, 1)))

        assertTwoEquals("cj, dj",
                normalList.drop(2).joinToString(),
                Collectionx.joinToString(Collectionx.drop(normalList, 2)))

        assertTwoEquals("dj",
                normalList.drop(3).joinToString(),
                Collectionx.joinToString(Collectionx.drop(normalList, 3)))

        assertTwoEquals("",
                normalList.drop(4).joinToString(),
                Collectionx.joinToString(Collectionx.drop(normalList, 4)))

        assertTwoEquals("",
                normalList.drop(5).joinToString(),
                Collectionx.joinToString(Collectionx.drop(normalList, 5)))

        assertTwoEquals("",
                normalList.drop(5).joinToString(),
                Collectionx.joinToString(Collectionx.drop(Collectionx.drop(normalList, 5), 5)))

        // dropWhile 方法的意思是从不符合条件的元素开始往后遍历

        assertTwoEquals("aj, bj, cj, dj",
                normalList.dropWhile { !it.startsWith("a") }.joinToString(),
                Collectionx.joinToString(Collectionx.dropWhile(normalList) { !it.startsWith("a") }))

        assertTwoEquals("bj, cj, dj",
                normalList.dropWhile { !it.startsWith("b") }.joinToString(),
                Collectionx.joinToString(Collectionx.dropWhile(normalList) { !it.startsWith("b") }))

        assertTwoEquals("cj, dj",
                normalList.dropWhile { !it.startsWith("c") }.joinToString(),
                Collectionx.joinToString(Collectionx.dropWhile(normalList) { !it.startsWith("c") }))

        assertTwoEquals("dj",
                normalList.dropWhile { !it.startsWith("d") }.joinToString(),
                Collectionx.joinToString(Collectionx.dropWhile(normalList) { !it.startsWith("d") }))

        assertTwoEquals("",
                normalList.dropWhile { !it.startsWith("e") }.joinToString(),
                Collectionx.joinToString(Collectionx.dropWhile(normalList) { !it.startsWith("e") }))


        assertTwoEquals("dj",
                normalList.dropWhile { !it.startsWith("b") }.drop(2).joinToString(),
                Collectionx.joinToString(Collectionx.drop(Collectionx.dropWhile(normalList) { !it.startsWith("b") }, 2)))
    }

    @Test
    fun testTake() {
        val normalList = listOf("aj", "bj", "cj", "dj")

        /*
         * take 系列的方法表示从列表头部开始取部分元素
         */

        // take 方法的意思是从列表头部开始取多少个元素

        assertTwoThrow(IllegalArgumentException::class,
                { normalList.take(-1) },
                { Collectionx.take(normalList, -1) }
        )

        assertTrue(emptyList<String>() === normalList.take(0))
        assertTrue(Collectionx.emptyList<String>() === Collectionx.take(normalList, 0))

        assertTwoEquals("aj",
                normalList.take(1).joinToString(),
                Collectionx.joinToString(Collectionx.take(normalList, 1)))

        assertTwoEquals("aj, bj",
                normalList.take(2).joinToString(),
                Collectionx.joinToString(Collectionx.take(normalList, 2)))

        assertTwoEquals("aj, bj, cj",
                normalList.take(3).joinToString(),
                Collectionx.joinToString(Collectionx.take(normalList, 3)))

        assertTwoEquals("aj, bj, cj, dj",
                normalList.take(4).joinToString(),
                Collectionx.joinToString(Collectionx.take(normalList, 4)))

        assertTwoEquals("aj, bj, cj, dj",
                normalList.take(5).joinToString(),
                Collectionx.joinToString(Collectionx.take(normalList, 5)))

        assertTwoEquals("aj, bj, cj, dj",
                normalList.take(5).joinToString(),
                Collectionx.joinToString(Collectionx.take(Collectionx.take(normalList, 5), 5)))

        // takeWhile 方法的意思是从列表头部开始到不符合条件的元素（不含包）终止

        assertTwoEquals("",
                normalList.takeWhile { !it.startsWith("a") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeWhile(normalList) { !it.startsWith("a") }))

        assertTwoEquals("aj",
                normalList.takeWhile { !it.startsWith("b") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeWhile(normalList) { !it.startsWith("b") }))

        assertTwoEquals("aj, bj",
                normalList.takeWhile { !it.startsWith("c") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeWhile(normalList) { !it.startsWith("c") }))

        assertTwoEquals("aj, bj, cj",
                normalList.takeWhile { !it.startsWith("d") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeWhile(normalList) { !it.startsWith("d") }))

        assertTwoEquals("aj, bj, cj, dj",
                normalList.takeWhile { !it.startsWith("e") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeWhile(normalList) { !it.startsWith("e") }))


        assertTwoEquals("aj, bj",
                normalList.takeWhile { !it.startsWith("d") }.take(2).joinToString(),
                Collectionx.joinToString(Collectionx.take(Collectionx.takeWhile(normalList) { !it.startsWith("d") }, 2)))
    }

    @Test
    fun testFilter() {
        val normalList = listOf("aj", "bo", "cj", "do")
        val nullList = null as List<String>?

        assertTwoEquals("aj, cj",
                normalList.filter { it.endsWith("j") }.joinToString(),
                Collectionx.joinToString(Collectionx.filter(normalList) { it.endsWith("j") }))

        val filterToDestination = java.util.ArrayList<String>()
        val filterToDestination1 = java.util.ArrayList<String>()
        val filterToDestinationNull1 = java.util.ArrayList<String>()
        val filterToDestinationResult = normalList.filterTo(filterToDestination) { it.endsWith("j") }
        val filterToDestinationResult1 = Collectionx.filterTo(normalList, filterToDestination1) { it.endsWith("j") }
        val filterToDestinationNullResult1 = Collectionx.filterTo(nullList, filterToDestinationNull1) { it.endsWith("j") }
        assertTwoEquals("aj, cj", filterToDestinationResult.joinToString(), filterToDestinationResult1.joinToString())
        assertTrue(filterToDestination === filterToDestinationResult)
        assertTrue(filterToDestination1 === filterToDestinationResult1)
        assertEquals("", filterToDestinationNullResult1.joinToString())
        assertTrue(filterToDestinationNull1 === filterToDestinationNullResult1)

        assertTwoEquals("bo, do",
                normalList.filterIndexed { index, _ -> (index % 2) != 0 }.joinToString(),
                Collectionx.joinToString(Collectionx.filterIndexed(normalList) { index, _ -> (index % 2) != 0 }))

        val filterIndexedToDestination = java.util.ArrayList<String>()
        val filterIndexedToDestination1 = java.util.ArrayList<String>()
        val filterIndexedToDestinationNull1 = java.util.ArrayList<String>()
        val filterIndexedToDestinationResult = normalList.filterIndexedTo(filterIndexedToDestination) { index, _ -> (index % 2) != 0 }
        val filterIndexedToDestinationResult1 = Collectionx.filterIndexedTo(normalList, filterIndexedToDestination1) { index, _ -> (index % 2) != 0 }
        val filterIndexedToDestinationNullResult1 = Collectionx.filterIndexedTo(nullList, filterIndexedToDestinationNull1) { index, _ -> (index % 2) != 0 }
        assertTwoEquals("bo, do", filterIndexedToDestinationResult.joinToString(), filterIndexedToDestinationResult1.joinToString())
        assertTrue(filterIndexedToDestination === filterIndexedToDestinationResult)
        assertTrue(filterIndexedToDestination1 === filterIndexedToDestinationResult1)
        assertEquals("", filterIndexedToDestinationNullResult1.joinToString())
        assertTrue(filterIndexedToDestinationNull1 === filterIndexedToDestinationNullResult1)


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
        val filterIsInstanceToDestinationNullResult1 = Collectionx.filterIsInstanceTo(nullList, filterIsInstanceToDestinationNull1, Integer::class.java)
        assertTwoEquals("4, 76", filterIsInstanceToDestinationResult.joinToString(), filterIsInstanceToDestinationResult1.joinToString())
        assertTrue(filterIsInstanceToDestination === filterIsInstanceToDestinationResult)
        assertTrue(filterIsInstanceToDestination1 === filterIsInstanceToDestinationResult1)
        assertEquals("", filterIsInstanceToDestinationNullResult1.joinToString())
        assertTrue(filterIsInstanceToDestinationNull1 === filterIsInstanceToDestinationNullResult1)

        assertTwoEquals("bo, do",
                normalList.filterNot { it.endsWith("j") }.joinToString(),
                Collectionx.joinToString(Collectionx.filterNot(normalList) { it.endsWith("j") }))

        val filterNotToDestination = java.util.ArrayList<String>()
        val filterNotToDestination1 = java.util.ArrayList<String>()
        val filterNotToDestinationNull1 = java.util.ArrayList<String>()
        val filterNotToDestinationResult = normalList.filterNotTo(filterNotToDestination) { it.endsWith("j") }
        val filterNotToDestinationResult1 = Collectionx.filterNotTo(normalList, filterNotToDestination1) { it.endsWith("j") }
        val filterNotToDestinationNullResult1 = Collectionx.filterNotTo(nullList, filterNotToDestinationNull1) { it.endsWith("j") }
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
        val filterNotNullToDestinationNullResult1 = Collectionx.filterNotNullTo(nullList, filterNotNullToDestinationNull1)
        assertTwoEquals("f, gsdg", filterNotNullToDestinationResult.joinToString(), filterNotNullToDestinationResult1.joinToString())
        assertTrue(filterNotNullToDestination === filterNotNullToDestinationResult)
        assertTrue(filterNotNullToDestination1 === filterNotNullToDestinationResult1)
        assertEquals("", filterNotNullToDestinationNullResult1.joinToString())
        assertTrue(filterNotNullToDestinationNull1 === filterNotNullToDestinationNullResult1)
    }

    @Test
    fun testSort() {
        val normalList = listOf("aaa", "h", "uuuu", "gg")
        val nullableList = Collectionx.listOf<String>("aaa", null, null, "gg", null)

        assertTwoEquals("aaa, gg, h, uuuu",
                normalList.sorted().joinToString(),
                Collectionx.joinToString(Collectionx.sorted(normalList)))
        assertThrow(NullPointerException::class) { Collectionx.joinToString(Collectionx.sorted(nullableList)) }

        assertTwoEquals("uuuu, h, gg, aaa",
                normalList.sortedDescending().joinToString(),
                Collectionx.joinToString(Collectionx.sortedDescending(normalList)))
        assertThrow(NullPointerException::class) { Collectionx.joinToString(Collectionx.sortedDescending(nullableList)) }

        assertTwoEquals("h, gg, aaa, uuuu",
                normalList.sortedBy { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedBy(normalList) { it.length }))
        assertTwoEquals("null, null, null, gg, aaa",
                nullableList.sortedBy { it?.length ?: 0 }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedBy(nullableList) { it.length }))

        assertTwoEquals("uuuu, aaa, gg, h",
                normalList.sortedByDescending { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedByDescending(normalList) { it.length }))
        assertTwoEquals("aaa, gg, null, null, null",
                nullableList.sortedByDescending { it?.length ?: 0 }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedByDescending(nullableList) { it.length }))

        assertTwoEquals("aaa, gg, h, uuuu",
                normalList.sortedWith { it1, it2 -> it1.compareTo(it2) }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedWith(normalList) { it1, it2 -> it1.compareTo(it2) }))
        assertTwoEquals("null, null, null, aaa, gg",
                nullableList.sortedWith { it1, it2 -> it1.orEmpty().compareTo(it2.orEmpty()) }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedWith(nullableList) { it1, it2 -> it1.orEmpty().compareTo(it2.orEmpty()) }))
    }

    @Test
    @Suppress("ReplaceAssociateFunction")
    fun testAssociate() {
        val normalList = listOf("aj", "bj", "ao", "bo")
        val nullList = null as List<String>?

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                normalList.associate { it.first().toString() to it },
                Collectionx.associate(normalList) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) },
        )

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                normalList.associateBy { it.first().toString() },
                Collectionx.associateBy(normalList) { it.first().toString() },
        )

        assertTwoEquals(
                mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"),
                normalList.associateBy({ it.first().toString() }, { it }),
                Collectionx.associateBy(normalList, { it.first().toString() }, { it }),
        )

        val associateToMap0 = HashMap<String, String>()
        val associateToMap1 = HashMap<String, String>()
        val associateToMapNull1 = HashMap<String, String>()
        val associateToMapResult0 = normalList.associateTo(associateToMap0) { it.first().toString() to it }
        val associateToMapResult1 = Collectionx.associateTo(normalList, associateToMap1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) }
        val associateToMapResultNull1 = Collectionx.associateTo(nullList, associateToMapNull1) { com.github.panpf.tools4j.common.Pair.of(it.first().toString(), it) }
        assertTwoEquals(mapOf("a" to "aj", "b" to "bj", "a" to "ao", "b" to "bo"), associateToMap0, associateToMap1)
        assertTrue(associateToMap0 === associateToMapResult0)
        assertTrue(associateToMap1 === associateToMapResult1)
        assertEquals(mapOf<String, String>(), associateToMapResultNull1)
        assertTrue(associateToMapNull1 === associateToMapResultNull1)

        val associateByTo1Map0 = HashMap<String, String>()
        val associateByTo1Map1 = HashMap<String, String>()
        val associateByTo1MapNull1 = HashMap<String, String>()
        val associateByTo1MapResult0 = normalList.associateByTo(associateByTo1Map0) { it.first().toString() }
        val associateByTo1MapResult1 = Collectionx.associateByTo(normalList, associateByTo1Map1) { it.first().toString() }
        val associateByTo1MapResultNull1 = Collectionx.associateByTo(nullList, associateByTo1MapNull1) { it.first().toString() }
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
        val associateByTo2MapResult0 = normalList.associateByTo(associateByTo2Map0, { it.first().toString() }, { it })
        val associateByTo2MapResult1 = Collectionx.associateByTo(normalList, associateByTo2Map1, { it.first().toString() }, { it })
        val associateByTo2MapResultNull1 = Collectionx.associateByTo(nullList, associateByTo2MapNull1, { it.first().toString() }, { it })
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
        val normalList = listOf("aj", "bj", "ao", "bo")
        val nullList = null as List<String>?

        @Suppress("USELESS_IS_CHECK")
        assertTwoEquals(
                "a, j, b, j, a, o, b, o",
                normalList.flatMap { it -> it.toCharArray().map { it.toString() } }.joinToString(),
                Collectionx.joinToString(Collectionx.flatMap(normalList) { it -> it.toCharArray().map { it.toString() } }),
        )

        val flatMapToList0 = ArrayList<String>()
        val flatMapToList1 = ArrayList<String>()
        val flatMapToListNull1 = ArrayList<String>()
        val flatMapToListResult0 = normalList.flatMapTo(flatMapToList0) { it -> it.toCharArray().map { it.toString() } }
        val flatMapToListResult1 = Collectionx.flatMapTo(normalList, flatMapToList1) { it -> it.toCharArray().map { it.toString() } }
        val flatMapToListResultNull1 = Collectionx.flatMapTo(nullList, flatMapToListNull1) { it -> it.toCharArray().map { it.toString() } }
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
                normalList.flatMapIndexed { index, it -> it.toCharArray().map { "${index}-$it" } }.joinToString(),
                Collectionx.joinToString(Collectionx.flatMapIndexed(normalList) { index, it -> it.toCharArray().map { "${index}-$it" } }),
        )

        val flatMapIndexedToList0 = ArrayList<String>()
        val flatMapIndexedToList1 = ArrayList<String>()
        val flatMapIndexedToListNull1 = ArrayList<String>()
        val flatMapIndexedToListResult0 = normalList.flatMapIndexedTo(flatMapIndexedToList0) { index, it -> it.toCharArray().map { "${index}-$it" } }
        val flatMapIndexedToListResult1 = Collectionx.flatMapIndexedTo(normalList, flatMapIndexedToList1) { index, it -> it.toCharArray().map { "${index}-$it" } }
        val flatMapIndexedToListResultNull1 = Collectionx.flatMapIndexedTo(nullList, flatMapIndexedToListNull1) { index, it -> it.toCharArray().map { "${index}-$it" } }
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
        val normalList = listOf("aj", "bj", "ao", "bo")
        val nullList = null as List<String>?

        assertTwoEquals(
                "{a=[aj, ao], b=[bj, bo]}",
                normalList.groupBy { it.first() }.toString(),
                Collectionx.groupBy(normalList) { it.first() }.toString(),
        )

        assertTwoEquals(
                "{a=[j, o], b=[j, o]}",
                normalList.groupBy({ it.first() }, { it.last() }).toString(),
                Collectionx.groupBy(normalList, { it.first() }, { it.last() }).toString(),
        )

        val groupByToMap0 = HashMap<Char, MutableList<String>>()
        val groupByToMap1 = HashMap<Char, List<String>>()
        val groupByToMapNull1 = HashMap<Char, List<String>>()
        val groupByToMapResult0 = normalList.groupByTo(groupByToMap0) { it.first() }
        val groupByToMapResult1 = Collectionx.groupByTo(normalList, groupByToMap1) { it.first() }
        val groupByToMapResultNull1 = Collectionx.groupByTo(nullList, groupByToMapNull1) { it.first() }
        assertTwoEquals("{a=[aj, ao], b=[bj, bo]}", groupByToMap0.toString(), groupByToMap1.toString())
        assertTrue(groupByToMap0 === groupByToMapResult0)
        assertTrue(groupByToMap1 === groupByToMapResult1)
        assertEquals("{}", groupByToMapNull1.toString())
        assertTrue(groupByToMapNull1 === groupByToMapResultNull1)

        val groupByToMap2 = HashMap<Char, MutableList<Char>>()
        val groupByToMap3 = HashMap<Char, List<Char>>()
        val groupByToMapNull3 = HashMap<Char, List<Char>>()
        val groupByToMapResult2 = normalList.groupByTo(groupByToMap2, { it.first() }, { it.last() })
        val groupByToMapResult3 = Collectionx.groupByTo(normalList, groupByToMap3, { it.first() }, { it.last() })
        val groupByToMapResultNull3 = Collectionx.groupByTo(nullList, groupByToMapNull3, { it.first() }, { it.last() })
        assertTwoEquals("{a=[j, o], b=[j, o]}", groupByToMap2.toString(), groupByToMap3.toString())
        assertTrue(groupByToMap2 === groupByToMapResult2)
        assertTrue(groupByToMap3 === groupByToMapResult3)
        assertEquals("{}", groupByToMapNull3.toString())
        assertTrue(groupByToMapNull3 === groupByToMapResultNull3)
    }

    @Test
    fun testMap() {
        val normalList = listOf("aj", "bj", "ao", "cc", "bo")
        val nullList = null as List<String>?

        assertTwoEquals(
                "a, b, a, c, b",
                normalList.map { it.first() }.joinToString(),
                Collectionx.joinToString(Collectionx.map(normalList) { it.first() }),
        )

        assertTwoEquals(
                "a, b, a, b",
                normalList.mapNotNull { if (it != "cc") it.first() else null }.joinToString(),
                Collectionx.joinToString(Collectionx.mapNotNull(normalList) { if (it != "cc") it.first() else null }),
        )

        assertTwoEquals(
                "0:a, 1:b, 2:a, 3:c, 4:b",
                normalList.mapIndexed { index, s -> "$index:${s.first()}" }.joinToString(),
                Collectionx.joinToString(Collectionx.mapIndexed(normalList) { index, s -> "$index:${s.first()}" }),
        )

        assertTwoEquals(
                "0:a, 1:b, 2:a, 4:b",
                normalList.mapIndexedNotNull { index, s -> if (s != "cc") "$index:${s.first()}" else null }.joinToString(),
                Collectionx.joinToString(Collectionx.mapIndexedNotNull(normalList) { index, s -> if (s != "cc") "$index:${s.first()}" else null }),
        )

        val mapToList0 = ArrayList<Char>()
        val mapToList1 = ArrayList<Char>()
        val mapToListNull1 = ArrayList<Char>()
        val mapToListResult0 = normalList.mapTo(mapToList0) { it.first() }
        val mapToListResult1 = Collectionx.mapTo(normalList, mapToList1) { it.first() }
        val mapToListResultNull1 = Collectionx.mapTo(nullList, mapToListNull1) { it.first() }
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
        val mapNotNullToListResult0 = normalList.mapNotNullTo(mapNotNullToList0) { if (it != "cc") it.first() else null }
        val mapNotNullToListResult1 = Collectionx.mapNotNullTo(normalList, mapNotNullToList1) { if (it != "cc") it.first() else null }
        val mapNotNullToListResultNull1 = Collectionx.mapNotNullTo(nullList, mapNotNullToListNull1) { if (it != "cc") it.first() else null }
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
        val mapIndexedToListResult0 = normalList.mapIndexedTo(mapIndexedToList0) { index, s -> "$index:${s.first()}" }
        val mapIndexedToListResult1 = Collectionx.mapIndexedTo(normalList, mapIndexedToList1) { index, s -> "$index:${s.first()}" }
        val mapIndexedToListResultNull1 = Collectionx.mapIndexedTo(nullList, mapIndexedToListNull1) { index, s -> "$index:${s.first()}" }
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
        val mapIndexedNotNullToListResult0 = normalList.mapIndexedNotNullTo(mapIndexedNotNullToList0) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
        val mapIndexedNotNullToListResult1 = Collectionx.mapIndexedNotNullTo(normalList, mapIndexedNotNullToList1) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
        val mapIndexedNotNullToListResultNull1 = Collectionx.mapIndexedNotNullTo(nullList, mapIndexedNotNullToListNull1) { index, s -> if (s != "cc") "$index:${s.first()}" else null }
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
        val normalList = listOf("aj", "bj", "ao", "cc", "bo")
        val nullList = null as List<String>?

        assertTwoEquals(
                "0:aj, 1:bj, 2:ao, 3:cc, 4:bo",
                normalList.withIndex().joinToString { "${it.index}:${it.value}" },
                Collectionx.joinToString(Collectionx.withIndex(normalList)) { "${it.index}:${it.value}" },
        )

        assertEquals("", Collectionx.joinToString(Collectionx.withIndex(nullList)) { "${it.index}:${it.value}" })

        val iterator0 = normalList.withIndex().iterator()
        if (iterator0 is MutableIterator) {
            assertThrow(UnsupportedOperationException::class) { iterator0.remove() }
        }
        assertThrow(IllegalStateException::class) { Collectionx.withIndex(normalList).iterator().remove() }
    }

    @Test
    fun testDistinct() {
        val normalList = listOf("aj", "bj", "aj", "bj", "bo")

        assertTwoEquals(
                "aj, bj, bo",
                normalList.distinct().joinToString(),
                Collectionx.joinToString(Collectionx.distinct(normalList)),
        )

        assertTwoEquals(
                "aj, bo",
                normalList.distinctBy { it.last() }.joinToString(),
                Collectionx.joinToString(Collectionx.distinctBy(normalList) { it.last() }),
        )
    }

    @Test
    fun testAll() {
        val normalList = listOf("aj", "bj", "aj", "bj", "bo")
        val emptyList = listOf<String>()
        val nullList = null as List<String>?

        assertTwoEquals(
                true,
                normalList.all { it -> it.all { it.isLetter() } },
                Collectionx.all(normalList) { it -> it.all { it.isLetter() } },
        )

        assertTwoEquals(
                true,
                emptyList.all { it -> it.all { it.isLetter() } },
                Collectionx.all(emptyList) { it -> it.all { it.isLetter() } },
        )

        assertTrue(Collectionx.all(nullList) { it.last() == 'j' })

        assertTwoEquals(
                false,
                normalList.all { it.last() == 'j' },
                Collectionx.all(normalList) { it.last() == 'j' },
        )
    }

    @Test
    fun testAny() {
        val normalList = listOf("aj", "bj", "aj", "bj", "bo")
        val emptyList = listOf<String>()
        val nullList = null as List<String>?

        assertTwoEquals(true, normalList.any(), Collectionx.any(normalList))
        assertTwoEquals(false, emptyList.any(), Collectionx.any(emptyList))
        assertFalse(Collectionx.any(nullList))
        assertTwoEquals(
                false,
                listOf<String>().any(),
                Collectionx.any(Collectionx.listOf<String>()),
        )

        assertTwoEquals(true, normalList.any { it.last() == 'j' }, Collectionx.any(normalList) { it.last() == 'j' })
        assertTwoEquals(false, emptyList.any { it.last() == 'j' }, Collectionx.any(emptyList) { it.last() == 'j' })
        assertFalse(Collectionx.any(nullList) { it -> it.all { it.isDigit() } })
        assertTwoEquals(
                false,
                normalList.any { it -> it.all { it.isDigit() } },
                Collectionx.any(normalList) { it -> it.all { it.isDigit() } }
        )
    }

    @Test
    fun testCount() {
        val normalList = listOf("aj", "bj", "aj", "bj", "bo")
        val iterable0 = object : Iterable<String> {
            override fun iterator(): Iterator<String> {
                return normalList.iterator()
            }
        }
        val emptyList = listOf<String>()
        val emptyIterable0 = object : Iterable<String> {
            override fun iterator(): Iterator<String> {
                return emptyList.iterator()
            }
        }
        val nullList = null as List<String>?

        assertTwoEquals(5, (normalList as Iterable<String>).count(), Collectionx.count(normalList as Iterable<String>))
        assertTwoEquals(5, iterable0.count(), Collectionx.count(iterable0))
        assertTwoEquals(0, (emptyList as Iterable<String>).count(), Collectionx.count(emptyList as Iterable<String>))
        assertEquals(0, Collectionx.count(nullList as Iterable<String>?))

        assertTwoEquals(4, normalList.count { it.last() == 'j' }, Collectionx.count(normalList) { it.last() == 'j' })
        assertTwoEquals(4, iterable0.count { it.last() == 'j' }, Collectionx.count(iterable0) { it.last() == 'j' })
        assertTwoEquals(0, emptyList.count { it.last() == 'j' }, Collectionx.count(emptyList) { it.last() == 'j' })
        assertTwoEquals(0, emptyIterable0.count { it.last() == 'j' }, Collectionx.count(emptyIterable0) { it.last() == 'j' })
        assertEquals(0, Collectionx.count(nullList) { it.last() == 'j' })

        assertTwoEquals(5, normalList.count(), Collectionx.count(normalList))
        assertTwoEquals(0, emptyList.count(), Collectionx.count(emptyList))
        assertEquals(0, Collectionx.count(nullList))
    }

    @Test
    fun testFold() {
        val normalList = listOf("aj", "bj", "aj", "bj", "bo")
        val nullList = null as List<String>?

        assertTwoEquals(
                "^ajbjajbjbo",
                normalList.fold("^") { r, t -> r + t },
                Collectionx.fold(normalList, "^") { r, t -> r + t },
        )
        assertEquals(
                "^",
                Collectionx.fold(nullList, "^") { r, t -> r + t },
        )

        assertTwoEquals(
                "^0aj1bj2aj3bj4bo",
                normalList.foldIndexed("^") { i, r, t -> r + i.toString() + t },
                Collectionx.foldIndexed(normalList, "^") { i, r, t -> r + i.toString() + t },
        )
        assertEquals(
                "^",
                Collectionx.foldIndexed(nullList, "^") { i, r, t -> r + i.toString() + t },
        )
    }

    @Test
    fun testEach() {
        val normalList = listOf("aj", "bj", "aj", "bj", "bo")

        assertTwoEquals("aj, bj, aj, bj, bo",
                ArrayList<String>().apply { normalList.forEach { add(it) } }.joinToString(),
                ArrayList<String>().apply { Collectionx.forEach(normalList) { add(it) } }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { listOf<String>().forEach { add(it) } }.joinToString(),
                ArrayList<String>().apply { Collectionx.forEach(null as List<String>?) { add(it) } }.joinToString())

        assertTwoEquals("0aj, 1bj, 2aj, 3bj, 4bo",
                ArrayList<String>().apply { normalList.forEachIndexed { i, it -> add(i.toString() + it) } }.joinToString(),
                ArrayList<String>().apply { Collectionx.forEachIndexed(normalList) { i, it -> add(i.toString() + it) } }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { listOf<String>().forEachIndexed { i, it -> add(i.toString() + it) } }.joinToString(),
                ArrayList<String>().apply { Collectionx.forEachIndexed(null as List<String>?) { i, it -> add(i.toString() + it) } }.joinToString())


        assertTwoEquals("aj, bj, aj, bj, bo",
                ArrayList<String>().apply { normalList.onEach { add(it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Collectionx.onEach(normalList) { add(it) } }.joinToString())

        assertTwoEquals("",
                ArrayList<String>().apply { listOf<String>().onEach { add(it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Collectionx.onEach(null as List<String>?) { add(it) } }.joinToString())

        assertTwoEquals("0aj, 1bj, 2aj, 3bj, 4bo",
                ArrayList<String>().apply { normalList.onEachIndexed { i, it -> add(i.toString() + it) }.toList() }.joinToString(),
                ArrayList<String>().apply { Collectionx.onEachIndexed(normalList) { i, it -> add(i.toString() + it) } }.joinToString())

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

        val normalList = listOf("6", "3", "7", "2", "1")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null
        assertTwoEquals("7", normalList.maxOrNull(), Collectionx.maxOrNull(normalList))
        assertTwoEquals(null, emptyList.maxOrNull(), Collectionx.maxOrNull(emptyList))
        assertTwoEquals(null, null, Collectionx.maxOrNull(nullList))

        assertTwoEquals("7", normalList.maxByOrNull { it.toInt() }, Collectionx.maxByOrNull(normalList) { it.toInt() })
        assertTwoEquals(null, emptyList.maxByOrNull { it.toInt() }, Collectionx.maxByOrNull(emptyList) { it.toInt() })
        assertTwoEquals(null, null, Collectionx.maxByOrNull(nullList) { it.toInt() })

        assertTwoEquals("1",
                normalList.maxWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Collectionx.maxWithOrNull(normalList) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                emptyList.maxWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Collectionx.maxWithOrNull(emptyList) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                null,
                Collectionx.maxWithOrNull(nullList) { it0, it1 -> it0.compareTo(it1) * -1 })

        assertTwoEquals(7.0,
                normalList.maxOf { it.toDouble() },
                Collectionx.maxOfDouble(normalList) { it.toDouble() })
        assertTwoThrow(NoSuchElementException::class,
                { emptyList.maxOf { it.toDouble() } },
                { Collectionx.maxOfDouble(emptyList) { it.toDouble() } })
        assertThrow(NoSuchElementException::class) { Collectionx.maxOfDouble(nullList) { it.toDouble() } }

        assertTwoEquals(7.0f,
                normalList.maxOf { it.toFloat() },
                Collectionx.maxOfFloat(normalList) { it.toFloat() })
        assertTwoThrow(NoSuchElementException::class,
                { emptyList.maxOf { it.toFloat() } },
                { Collectionx.maxOfFloat(emptyList) { it.toFloat() } })
        assertThrow(NoSuchElementException::class) { Collectionx.maxOfFloat(nullList) { it.toFloat() } }

        assertTwoEquals(7.0, normalList.maxOfOrNull { it.toDouble() }, Collectionx.maxOfDoubleOrNull(normalList) { it.toDouble() })
        assertTwoEquals(null, emptyList.maxOfOrNull { it.toDouble() }, Collectionx.maxOfDoubleOrNull(emptyList) { it.toDouble() })
        assertTwoEquals(null, null, Collectionx.maxOfDoubleOrNull(nullList) { it.toDouble() })

        assertTwoEquals(7.0f, normalList.maxOfOrNull { it.toFloat() }, Collectionx.maxOfFloatOrNull(normalList) { it.toFloat() })
        assertTwoEquals(null, emptyList.maxOfOrNull { it.toFloat() }, Collectionx.maxOfFloatOrNull(emptyList) { it.toFloat() })
        assertTwoEquals(null, null, Collectionx.maxOfFloatOrNull(nullList) { it.toFloat() })

        assertTwoEquals("7", normalList.maxOf { it }, Collectionx.maxOf(normalList) { it })
        assertTwoThrow(NoSuchElementException::class, { emptyList.maxOf { it } }, { Collectionx.maxOf(emptyList) { it } })
        assertThrow(NoSuchElementException::class) { Collectionx.maxOf(nullList) { it } }

        assertTwoEquals("7", normalList.maxOfOrNull { it }, Collectionx.maxOfOrNull(normalList) { it })
        assertTwoEquals(null, emptyList.maxOfOrNull { it }, Collectionx.maxOfOrNull(emptyList) { it })
        assertTwoEquals(null, null, Collectionx.maxOfOrNull(nullList) { it })

        assertTwoEquals("1",
                normalList.maxOfWith({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.maxOfWith(normalList, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoThrow(NoSuchElementException::class,
                { emptyList.maxOfWith(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) },
                { Collectionx.maxOfWith(emptyList, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }
        )
        assertThrow(NoSuchElementException::class) { Collectionx.maxOfWith(nullList, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }

        assertTwoEquals("1",
                normalList.maxOfWithOrNull({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.maxOfWithOrNull(normalList, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoEquals(null,
                emptyList.maxOfWithOrNull(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.maxOfWithOrNull(emptyList, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it })
        )
        assertTwoEquals(null, null, Collectionx.maxOfWithOrNull(nullList, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
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

        val normalList = listOf("6", "3", "7", "2", "1")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null
        assertTwoEquals("1", normalList.minOrNull(), Collectionx.minOrNull(normalList))
        assertTwoEquals(null, emptyList.minOrNull(), Collectionx.minOrNull(emptyList))
        assertTwoEquals(null, null, Collectionx.minOrNull(nullList))

        assertTwoEquals("1", normalList.minByOrNull { it.toInt() }, Collectionx.minByOrNull(normalList) { it.toInt() })
        assertTwoEquals(null, emptyList.minByOrNull { it.toInt() }, Collectionx.minByOrNull(emptyList) { it.toInt() })
        assertTwoEquals(null, null, Collectionx.minByOrNull(nullList) { it.toInt() })

        assertTwoEquals("7",
                normalList.minWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Collectionx.minWithOrNull(normalList) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                emptyList.minWithOrNull { it0, it1 -> it0.compareTo(it1) * -1 },
                Collectionx.minWithOrNull(emptyList) { it0, it1 -> it0.compareTo(it1) * -1 })
        assertTwoEquals(null,
                null,
                Collectionx.minWithOrNull(nullList) { it0, it1 -> it0.compareTo(it1) * -1 })

        assertTwoEquals(1.0,
                normalList.minOf { it.toDouble() },
                Collectionx.minOfDouble(normalList) { it.toDouble() })
        assertTwoThrow(NoSuchElementException::class,
                { emptyList.minOf { it.toDouble() } },
                { Collectionx.minOfDouble(emptyList) { it.toDouble() } })
        assertThrow(NoSuchElementException::class) { Collectionx.minOfDouble(nullList) { it.toDouble() } }

        assertTwoEquals(1.0f,
                normalList.minOf { it.toFloat() },
                Collectionx.minOfFloat(normalList) { it.toFloat() })
        assertTwoThrow(NoSuchElementException::class,
                { emptyList.minOf { it.toFloat() } },
                { Collectionx.minOfFloat(emptyList) { it.toFloat() } })
        assertThrow(NoSuchElementException::class) { Collectionx.minOfFloat(nullList) { it.toFloat() } }

        assertTwoEquals(1.0, normalList.minOfOrNull { it.toDouble() }, Collectionx.minOfDoubleOrNull(normalList) { it.toDouble() })
        assertTwoEquals(null, emptyList.minOfOrNull { it.toDouble() }, Collectionx.minOfDoubleOrNull(emptyList) { it.toDouble() })
        assertTwoEquals(null, null, Collectionx.minOfDoubleOrNull(nullList) { it.toDouble() })

        assertTwoEquals(1.0f, normalList.minOfOrNull { it.toFloat() }, Collectionx.minOfFloatOrNull(normalList) { it.toFloat() })
        assertTwoEquals(null, emptyList.minOfOrNull { it.toFloat() }, Collectionx.minOfFloatOrNull(emptyList) { it.toFloat() })
        assertTwoEquals(null, null, Collectionx.minOfFloatOrNull(nullList) { it.toFloat() })

        assertTwoEquals("1", normalList.minOf { it }, Collectionx.minOf(normalList) { it })
        assertTwoThrow(NoSuchElementException::class, { emptyList.minOf { it } }, { Collectionx.minOf(emptyList) { it } })
        assertThrow(NoSuchElementException::class) { Collectionx.minOf(nullList) { it } }

        assertTwoEquals("1", normalList.minOfOrNull { it }, Collectionx.minOfOrNull(normalList) { it })
        assertTwoEquals(null, emptyList.minOfOrNull { it }, Collectionx.minOfOrNull(emptyList) { it })
        assertTwoEquals(null, null, Collectionx.minOfOrNull(nullList) { it })

        assertTwoEquals("7",
                normalList.minOfWith({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.minOfWith(normalList, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoThrow(NoSuchElementException::class,
                { emptyList.minOfWith(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) },
                { Collectionx.minOfWith(emptyList, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }
        )
        assertThrow(NoSuchElementException::class) { Collectionx.minOfWith(nullList, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }) }

        assertTwoEquals("7",
                normalList.minOfWithOrNull({ it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.minOfWithOrNull(normalList, { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
        assertTwoEquals(null,
                emptyList.minOfWithOrNull(Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }),
                Collectionx.minOfWithOrNull(emptyList, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it })
        )
        assertTwoEquals(null, null, Collectionx.minOfWithOrNull(nullList, Comparator<String> { it0, it1 -> it0.compareTo(it1) * -1 }, { it }))
    }

    @Test
    fun testNone() {
        val normalList = listOf("6", "3", "7", "2", "1")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals(false, normalList.none(), Collectionx.none(normalList))
        assertTwoEquals(true, emptyList.none(), Collectionx.none(emptyList))
        assertTrue(Collectionx.none(nullList))

        assertTwoEquals(true, normalList.none { it.length > 1 }, Collectionx.none(normalList) { it.length > 1 })
        assertTwoEquals(false, normalList.none { it.isNotEmpty() }, Collectionx.none(normalList) { it.isNotEmpty() })
        assertTwoEquals(true, emptyList.none { it.length > 1 }, Collectionx.none(emptyList) { it.length > 1 })
        assertTrue(Collectionx.none(nullList) { it.length > 1 })
    }

    @Test
    fun testReduce() {
        val normalList = listOf("6", "3", "7", "2", "1")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals("63721",
                normalList.reduce { it0, it1 -> it0 + it1 },
                Collectionx.reduce(normalList) { it0, it1 -> it0 + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList.reduce { it0, it1 -> it0 + it1 } },
                { Collectionx.reduce(emptyList) { it0, it1 -> it0 + it1 } })
        assertThrow(UnsupportedOperationException::class)
        { Collectionx.reduce(nullList) { it0, it1 -> it0 + it1 } }

        assertTwoEquals("613273241",
                normalList.reduceIndexed { i, it0, it1 -> it0 + i + it1 },
                Collectionx.reduceIndexed(normalList) { i, it0, it1 -> it0 + i + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList.reduceIndexed { i, it0, it1 -> it0 + i + it1 } },
                { Collectionx.reduceIndexed(emptyList) { i, it0, it1 -> it0 + i + it1 } })
        assertThrow(UnsupportedOperationException::class)
        { Collectionx.reduceIndexed(nullList) { i, it0, it1 -> it0 + i + it1 } }
    }

    @Test
    fun testSum() {
        val normalList = listOf("6", "3", "7", "2", "1")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null
        assertTwoEquals(19, normalList.sumBy { it.toInt() }, Collectionx.sumBy(normalList) { it.toInt() })
        assertTwoEquals(0, emptyList.sumBy { it.toInt() }, Collectionx.sumBy(emptyList) { it.toInt() })
        assertEquals(0, Collectionx.sumBy(nullList) { it.toInt() })
        assertTwoEquals(19.0, normalList.sumByDouble { it.toDouble() }, Collectionx.sumByDouble(normalList) { it.toDouble() })
        assertTwoEquals(0.0, emptyList.sumByDouble { it.toDouble() }, Collectionx.sumByDouble(emptyList) { it.toDouble() })
        assertTwoEquals(0.0, 0.0, Collectionx.sumByDouble(nullList) { it.toDouble() })

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
        val normalList = listOf("6", "3", "7", "2", "1")
        val normalIterable = normalList as Iterable<String?>
        val nullableList = listOf("6", null, "7", "2", null)
        val nullableIterable = nullableList as Iterable<String?>
        val emptyList = listOf<String>()
        val emptyIterable = emptyList as Iterable<String?>
        val nullList: List<String?>? = null
        val nullIterable: Iterable<String?>? = null

        assertTwoEquals("6, 3, 7, 2, 1",
                normalIterable.requireNoNulls().joinToString(),
                Collectionx.joinToString(Collectionx.requireNoNulls(normalIterable)))
        assertTwoThrow(IllegalArgumentException::class,
                { nullableIterable.requireNoNulls().joinToString() },
                { Collectionx.joinToString(Collectionx.requireNoNulls(nullableIterable)) })
        assertTwoEquals("",
                emptyIterable.requireNoNulls().joinToString(),
                Collectionx.joinToString(Collectionx.requireNoNulls(emptyIterable)))
        assertEquals("", Collectionx.joinToString(Collectionx.requireNoNulls(nullIterable)))

        assertTwoEquals("6, 3, 7, 2, 1",
                normalList.requireNoNulls().joinToString(),
                Collectionx.joinToString(Collectionx.requireNoNulls(normalList)))
        assertTwoThrow(IllegalArgumentException::class,
                { nullableList.requireNoNulls().joinToString() },
                { Collectionx.joinToString(Collectionx.requireNoNulls(nullableList)) })
        assertTwoEquals("",
                emptyList.requireNoNulls().joinToString(),
                Collectionx.joinToString(Collectionx.requireNoNulls(emptyList)))
        assertEquals("", Collectionx.joinToString(Collectionx.requireNoNulls(nullList)))
    }

    @Test
    fun testMinus() {
        val normalList = listOf("6", "3", "7", "2", "1")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals("6, 7, 2, 1",
                normalList.minus("3").joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, "3")))
        assertTwoEquals("",
                emptyList.minus("3").joinToString(),
                Collectionx.joinToString(Collectionx.minus(emptyList, "3")))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList, "3")))

        assertTwoEquals("6, 7, 2",
                normalList.minus(arrayOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, arrayOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalList.minus(arrayOf()).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, arrayOf<String>())))
        assertTwoEquals("",
                emptyList.minus(arrayOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(emptyList, arrayOf("3", "1"))))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList, arrayOf("3", "1"))))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList, arrayOf<String>())))

        assertTwoEquals("6, 7, 2",
                normalList.minus(listOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, listOf("3", "1"))))
        assertTwoEquals("6, 7, 2",
                normalList.minus(setOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, setOf("3", "1"))))
        assertTwoEquals("6, 7, 2",
                normalList.minus(arrayListOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, arrayListOf("3", "1"))))
        assertTwoEquals("6, 7",
                normalList.minus(arrayListOf("3", "1", "2")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, arrayListOf("3", "1", "2"))))
        assertTwoEquals("6, 7",
                normalList.minus(listOf("3", "1", "2")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, listOf("3", "1", "2"))))
        assertTwoEquals("6, 7",
                normalList.minus(com.github.panpf.tools4j.iterable.TransformingIterable(listOf("3", "1", "2")) { it }).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, com.github.panpf.tools4j.iterable.TransformingIterable(listOf("3", "1", "2")) { it })))
        assertTwoEquals("",
                emptyList.minus(listOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(emptyList, listOf("3", "1"))))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList, listOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalList.minus(listOf()).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, listOf<String>())))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList, listOf<String>())))

        assertTwoEquals("6, 7, 2",
                normalList.minus(listOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, Collectionx.listOf("3", "1"))))
        assertTwoEquals("6, 3, 7, 2, 1",
                normalList.minus(listOf()).joinToString(),
                Collectionx.joinToString(Collectionx.minus(normalList, Collectionx.listOf())))
        assertTwoEquals("",
                emptyList.minus(listOf("3", "1")).joinToString(),
                Collectionx.joinToString(Collectionx.minus(emptyList, Collectionx.listOf("3", "1"))))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList, Collectionx.listOf("3", "1"))))
        assertEquals("", Collectionx.joinToString(Collectionx.minus(nullList, Collectionx.listOf())))

        assertTwoEquals("6, 7, 2, 1",
                normalList.minusElement("3").joinToString(),
                Collectionx.joinToString(Collectionx.minusElement(normalList, "3")))
        assertTwoEquals("",
                emptyList.minusElement("3").joinToString(),
                Collectionx.joinToString(Collectionx.minusElement(emptyList, "3")))
        assertEquals("", Collectionx.joinToString(Collectionx.minusElement(nullList, "3")))
    }

    @Test
    fun testPartition() {
        val normalList = listOf("6", "3", "7", "2", "1")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals("([6, 2], [3, 7, 1])",
                normalList.partition { it.toInt() % 2 == 0 }.toString(),
                Collectionx.partition(normalList) { it.toInt() % 2 == 0 }.toString())
        assertTwoEquals("([], [])",
                emptyList.partition { it.toInt() % 2 == 0 }.toString(),
                Collectionx.partition(emptyList) { it.toInt() % 2 == 0 }.toString())
        assertEquals("([], [])", Collectionx.partition(nullList) { it.toInt() % 2 == 0 }.toString())
    }

    @Test
    fun testPlus() {
        val normalList = listOf("6", "3", "7", "2", "1")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals("6, 3, 7, 2, 1, 9",
                normalList.plus("9").joinToString(),
                Collectionx.joinToString(Collectionx.plus(normalList, "9")))
        assertTwoEquals("9",
                emptyList.plus("9").joinToString(),
                Collectionx.joinToString(Collectionx.plus(emptyList, "9")))
        assertEquals("9", Collectionx.joinToString(Collectionx.plus(nullList, "9")))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalList.plus(arrayOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(normalList, arrayOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptyList.plus(arrayOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(emptyList, arrayOf("9", "4"))))
        assertEquals("9, 4", Collectionx.joinToString(Collectionx.plus(nullList, arrayOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalList.plus(listOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(normalList, listOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptyList.plus(listOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(emptyList, listOf("9", "4"))))
        assertEquals("9, 4", Collectionx.joinToString(Collectionx.plus(nullList, listOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9, 4",
                normalList.plus(listOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(normalList, Collectionx.listOf("9", "4"))))
        assertTwoEquals("9, 4",
                emptyList.plus(listOf("9", "4")).joinToString(),
                Collectionx.joinToString(Collectionx.plus(emptyList, Collectionx.listOf("9", "4"))))
        assertEquals("9, 4", Collectionx.joinToString(Collectionx.plus(nullList, Collectionx.listOf("9", "4"))))

        assertTwoEquals("6, 3, 7, 2, 1, 9",
                normalList.plusElement("9").joinToString(),
                Collectionx.joinToString(Collectionx.plusElement(normalList, "9")))
        assertTwoEquals("9",
                emptyList.plusElement("9").joinToString(),
                Collectionx.joinToString(Collectionx.plusElement(emptyList, "9")))
        assertEquals("9", Collectionx.joinToString(Collectionx.plusElement(nullList, "9")))
    }

    @Test
    fun testZip() {
        val normalList = listOf("6", "3", "7", "2", "1")
        val normalList1 = listOf("4", "9", "5")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals("(6, 4), (3, 9), (7, 5)",
                normalList.zip(normalList1).joinToString(),
                Collectionx.joinToString(Collectionx.zip(normalList, normalList1)))
        assertTwoEquals("",
                emptyList.zip(normalList1).joinToString(),
                Collectionx.joinToString(Collectionx.zip(emptyList, normalList1)))
        assertEquals("", Collectionx.joinToString(Collectionx.zip(nullList, normalList1)))

        assertTwoEquals("64, 39, 75",
                normalList.zip(normalList1) { it0, it1 -> it0 + it1 }.joinToString(),
                Collectionx.joinToString(Collectionx.zip(normalList, normalList1) { it0, it1 -> it0 + it1 }))
        assertTwoEquals("",
                emptyList.zip(normalList1) { it0, it1 -> it0 + it1 }.joinToString(),
                Collectionx.joinToString(Collectionx.zip(emptyList, normalList1) { it0, it1 -> it0 + it1 }))
        assertEquals("", Collectionx.joinToString(Collectionx.zip(nullList, normalList1) { it0, it1 -> it0 + it1 }))

        assertTwoEquals("([6, 3, 7], [4, 9, 5])",
                normalList.zip(normalList1).unzip().toString(),
                Collectionx.unzip(Collectionx.zip(normalList, normalList1)).toString())
        assertTwoEquals("([], [])",
                listOf<Pair<String, String>>().unzip().toString(),
                Collectionx.unzip(Collectionx.listOf<com.github.panpf.tools4j.common.Pair<String, String>>()).toString())
        assertEquals("([], [])", Collectionx.unzip(null as List<com.github.panpf.tools4j.common.Pair<String, String>>?).toString())
    }

    @Test
    fun testJoinTo() {
        val normalList = listOf("6", "3", "7", "2", "1")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals("^60:30:70:20:***$",
                normalList.joinTo(buffer = StringBuilder(), separator = ":", prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList, StringBuilder(), ":", "^", "$", 4, "***", { it + "0" }).toString())
        assertTwoEquals("^60, 30, 70, 20, ***$",
                normalList.joinTo(buffer = StringBuilder(), prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList, StringBuilder(), null, "^", "$", 4, "***", { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, ***$",
                normalList.joinTo(buffer = StringBuilder(), postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList, StringBuilder(), null, null, "$", 4, "***", { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, ***",
                normalList.joinTo(buffer = StringBuilder(), limit = 4, truncated = "***", transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList, StringBuilder(), null, null, null, 4, "***", { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, ...",
                normalList.joinTo(buffer = StringBuilder(), limit = 4, transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList, StringBuilder(), null, null, null, 4, null, { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, 10",
                normalList.joinTo(buffer = StringBuilder(), limit = 5, transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList, StringBuilder(), null, null, null, 5, null, { it + "0" }).toString())
        assertTwoEquals("60, 30, 70, 20, 10",
                normalList.joinTo(buffer = StringBuilder(), transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList, StringBuilder(), null, null, null, -1, null, { it + "0" }).toString())
        assertTwoEquals("6, 3, 7, 2, 1",
                normalList.joinTo(buffer = StringBuilder()).toString(),
                Collectionx.joinTo(normalList, StringBuilder(), null, null, null, -1, null, null).toString())
        assertTwoEquals("^$",
                emptyList.joinTo(buffer = StringBuilder(), prefix = "^", postfix = "$").toString(),
                Collectionx.joinTo(emptyList, StringBuilder(), null, "^", "$", -1, null, null).toString())
        assertTwoEquals("",
                emptyList.joinTo(buffer = StringBuilder()).toString(),
                Collectionx.joinTo(emptyList, StringBuilder(), null, null, null, -1, null, null).toString())
        assertEquals("^$",
                Collectionx.joinTo(nullList, StringBuilder(), null, "^", "$", -1, null, null).toString())
        assertEquals("",
                Collectionx.joinTo(nullList, StringBuilder(), null, null, null, -1, null, null).toString())

        assertTwoEquals("60,30,70,20,10",
                normalList.joinTo(buffer = StringBuilder(), separator = ",", transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList, StringBuilder(), ",", { it + "0" }).toString())
        assertTwoEquals("",
                emptyList.joinTo(buffer = StringBuilder(), separator = ",", transform = { it + "0" }).toString(),
                Collectionx.joinTo(emptyList, StringBuilder(), ",", { it + "0" }).toString())
        assertEquals("",
                Collectionx.joinTo(nullList, StringBuilder(), ",", { it + "0" }).toString())

        assertTwoEquals("60, 30, 70, 20, 10",
                normalList.joinTo(buffer = StringBuilder(), transform = { it + "0" }).toString(),
                Collectionx.joinTo(normalList, StringBuilder(), { it + "0" }).toString())
        assertTwoEquals("",
                emptyList.joinTo(buffer = StringBuilder(), transform = { it + "0" }).toString(),
                Collectionx.joinTo(emptyList, StringBuilder(), { it + "0" }).toString())
        assertEquals("",
                Collectionx.joinTo(nullList, StringBuilder(), { it + "0" }).toString())

        assertTwoEquals("6,3,7,2,1",
                normalList.joinTo(buffer = StringBuilder(), separator = ",").toString(),
                Collectionx.joinTo(normalList, StringBuilder(), ",").toString())
        assertTwoEquals("",
                emptyList.joinTo(buffer = StringBuilder(), separator = ",").toString(),
                Collectionx.joinTo(emptyList, StringBuilder(), ",").toString())
        assertEquals("",
                Collectionx.joinTo(nullList, StringBuilder(), ",").toString())

        assertTwoEquals("6, 3, 7, 2, 1",
                normalList.joinTo(buffer = StringBuilder()).toString(),
                Collectionx.joinTo(normalList, StringBuilder()).toString())
        assertTwoEquals("",
                emptyList.joinTo(buffer = StringBuilder()).toString(),
                Collectionx.joinTo(emptyList, StringBuilder()).toString())
        assertEquals("",
                Collectionx.joinTo(nullList, StringBuilder()).toString())

        assertThrow(IOException::class) { emptyList.joinTo(buffer = ExceptionAppendable()).toString() }
        assertThrow(RuntimeException::class) { Collectionx.joinTo(emptyList, ExceptionAppendable(), null, null, null, -1, null, null).toString() }


        assertTwoEquals("^60:30:70:20:***$",
                normalList.joinToString(separator = ":", prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }),
                Collectionx.joinToString(normalList, ":", "^", "$", 4, "***") { it + "0" })
        assertTwoEquals("^60, 30, 70, 20, ***$",
                normalList.joinToString(prefix = "^", postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }),
                Collectionx.joinToString(normalList, null, "^", "$", 4, "***") { it + "0" })
        assertTwoEquals("60, 30, 70, 20, ***$",
                normalList.joinToString(postfix = "$", limit = 4, truncated = "***", transform = { it + "0" }),
                Collectionx.joinToString(normalList, null, null, "$", 4, "***") { it + "0" })
        assertTwoEquals("60, 30, 70, 20, ***",
                normalList.joinToString(limit = 4, truncated = "***", transform = { it + "0" }),
                Collectionx.joinToString(normalList, null, null, null, 4, "***") { it + "0" })
        assertTwoEquals("60, 30, 70, 20, ...",
                normalList.joinToString(limit = 4, transform = { it + "0" }),
                Collectionx.joinToString(normalList, null, null, null, 4, null) { it + "0" })
        assertTwoEquals("60, 30, 70, 20, 10",
                normalList.joinToString(transform = { it + "0" }),
                Collectionx.joinToString(normalList, null, null, null, -1, null) { it + "0" })
        assertTwoEquals("6, 3, 7, 2, 1",
                normalList.joinToString(),
                Collectionx.joinToString(normalList, null, null, null, -1, null, null))
        assertTwoEquals("^$",
                emptyList.joinToString(prefix = "^", postfix = "$"),
                Collectionx.joinToString(emptyList, null, "^", "$", -1, null, null))
        assertTwoEquals("",
                emptyList.joinToString(),
                Collectionx.joinToString(emptyList, null, null, null, -1, null, null))
        assertEquals("^$",
                Collectionx.joinToString(nullList, null, "^", "$", -1, null, null))
        assertEquals("",
                Collectionx.joinToString(nullList, null, null, null, -1, null, null))

        assertTwoEquals("60,30,70,20,10",
                normalList.joinToString(separator = ",", transform = { it + "0" }),
                Collectionx.joinToString(normalList, ",") { it + "0" })
        assertTwoEquals("",
                emptyList.joinToString(separator = ",", transform = { it + "0" }),
                Collectionx.joinToString(emptyList, ",") { it + "0" })
        assertEquals("",
                Collectionx.joinToString(nullList, ",") { it + "0" })

        assertTwoEquals("60, 30, 70, 20, 10",
                normalList.joinToString(transform = { it + "0" }),
                Collectionx.joinToString(normalList) { it + "0" })
        assertTwoEquals("",
                emptyList.joinToString(transform = { it + "0" }),
                Collectionx.joinToString(emptyList) { it + "0" })
        assertEquals("",
                Collectionx.joinToString(nullList) { it + "0" })

        assertTwoEquals("6,3,7,2,1",
                normalList.joinToString(separator = ","),
                Collectionx.joinToString(normalList, ","))
        assertTwoEquals("",
                emptyList.joinToString(separator = ","),
                Collectionx.joinToString(emptyList, ","))
        assertEquals("",
                Collectionx.joinToString(nullList, ","))

        assertTwoEquals("6, 3, 7, 2, 1",
                normalList.joinToString(),
                Collectionx.joinToString(normalList))
        assertTwoEquals("",
                emptyList.joinToString(),
                Collectionx.joinToString(emptyList))
        assertEquals("",
                Collectionx.joinToString(nullList))
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