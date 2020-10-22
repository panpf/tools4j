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
        assertTrue(Collectionx.orEmpty(null as Collection<String>?)::class.simpleName == "EmptyList")
        assertTrue(Collectionx.orEmpty(null as Collection<String>?).isEmpty())
        assertTrue(Collectionx.orEmpty(Collectionx.linkedListOf("1") as Collection<String>) is LinkedList)
        assertTrue(Collectionx.orEmpty(Collectionx.linkedListOf("1") as Collection<String>).isNotEmpty())

        assertTrue(Collectionx.orEmpty(null as List<String>?)::class.simpleName == "EmptyList")
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

        // todo test mutableList, list method
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
        val normalList = listOf("a", "b", "c")
        val normalProgression = 1.rangeTo(5)
        val emptyList = listOf<String>()
        val nullList = null as List<String>?

        assertTwoEquals(true, normalList.contains("a"), Collectionx.contains(normalList, "a"))
        assertTwoEquals(true, normalList.contains("b"), Collectionx.contains(normalList, "b"))
        assertTwoEquals(true, normalList.contains("c"), Collectionx.contains(normalList, "c"))
        assertTwoEquals(false, normalList.contains("d"), Collectionx.contains(normalList, "d"))
        assertTwoEquals(false, emptyList.contains("a"), Collectionx.contains(emptyList, "d"))
        assertFalse(Collectionx.contains(nullList, "a"))
        assertFalse(Collectionx.contains(nullList, "d"))
        assertTwoEquals(true, normalProgression.contains(3), Collectionx.contains(normalProgression, 3))
        assertTwoEquals(false, normalProgression.contains(6), Collectionx.contains(normalProgression, 6))
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
        val normalIterable = object : Iterable<String> {
            override fun iterator(): Iterator<String> {
                return normalList.iterator()
            }
        }
        val emptyList = listOf<String>()
        val emptyIterable = object : Iterable<String> {
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
        val normalIterable = Iterable { normalList.iterator() }
        val nullableList = listOf("aj", null, "cj", null)
        val nullableIterable = Iterable { nullableList.iterator() }
        val emptyList = listOf<String>()
        val emptyIterable = Iterable { emptyList.iterator() }
        val nullList = null as List<String>?

        assertTwoEquals(0, normalList.indexOf("aj"), Collectionx.indexOf(normalList, "aj"))
        assertTwoEquals(1, normalList.indexOf("bj"), Collectionx.indexOf(normalList, "bj"))
        assertTwoEquals(2, normalList.indexOf("cj"), Collectionx.indexOf(normalList, "cj"))
        assertTwoEquals(3, normalList.indexOf("bo"), Collectionx.indexOf(normalList, "bo"))
        assertTwoEquals(-1, normalList.indexOf("bb"), Collectionx.indexOf(normalList, "bb"))
        assertTwoEquals(-1, emptyList.indexOf("bb"), Collectionx.indexOf(emptyList, "bb"))
        assertEquals(-1, Collectionx.indexOf(normalList, null))
        assertEquals(1, Collectionx.indexOf(nullableList, null))
        assertTwoEquals(0, normalIterable.indexOf("aj"), Collectionx.indexOf(normalIterable, "aj"))
        assertTwoEquals(1, normalIterable.indexOf("bj"), Collectionx.indexOf(normalIterable, "bj"))
        assertTwoEquals(2, normalIterable.indexOf("cj"), Collectionx.indexOf(normalIterable, "cj"))
        assertTwoEquals(3, normalIterable.indexOf("bo"), Collectionx.indexOf(normalIterable, "bo"))
        assertTwoEquals(-1, normalIterable.indexOf("bb"), Collectionx.indexOf(normalIterable, "bb"))
        assertTwoEquals(-1, emptyIterable.indexOf("bb"), Collectionx.indexOf(emptyIterable, "bb"))
        assertEquals(-1, Collectionx.indexOf(normalIterable, null))
        assertEquals(1, Collectionx.indexOf(nullableIterable, null))
        assertEquals(-1, Collectionx.indexOf(nullList, "bb"))
        assertEquals(-1, Collectionx.indexOf(nullList, null))

        assertTwoEquals(0, normalList.asIterable().indexOf("aj"), Collectionx.indexOf(normalList, "aj"))
        assertTwoEquals(1, normalList.asIterable().indexOf("bj"), Collectionx.indexOf(normalList, "bj"))
        assertTwoEquals(2, normalList.asIterable().indexOf("cj"), Collectionx.indexOf(normalList, "cj"))
        assertTwoEquals(3, normalList.asIterable().indexOf("bo"), Collectionx.indexOf(normalList, "bo"))
        assertTwoEquals(-1, normalList.asIterable().indexOf("bb"), Collectionx.indexOf(normalList, "bb"))
        assertTwoEquals(-1, emptyList.indexOf("bb"), Collectionx.indexOf(emptyList, "bb"))
        assertEquals(-1, Collectionx.indexOf(normalList, null))
        assertEquals(1, Collectionx.indexOf(nullableList, null))
        assertEquals(-1, Collectionx.indexOf(nullList, "bb"))
        assertEquals(-1, Collectionx.indexOf(nullList, null))

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
        assertTwoEquals(0, normalIterable.indexOfLast { it.startsWith("a") }, Collectionx.indexOfLast(normalIterable) { it.startsWith("a") })
        assertTwoEquals(3, normalIterable.indexOfLast { it.startsWith("b") }, Collectionx.indexOfLast(normalIterable) { it.startsWith("b") })
        assertTwoEquals(2, normalIterable.indexOfLast { it.startsWith("c") }, Collectionx.indexOfLast(normalIterable) { it.startsWith("c") })
        assertTwoEquals(-1, normalIterable.indexOfLast { it.startsWith("k") }, Collectionx.indexOfLast(normalIterable) { it.startsWith("k") })
        assertTwoEquals(-1, emptyIterable.indexOfLast { it.startsWith("k") }, Collectionx.indexOfLast(emptyIterable) { it.startsWith("k") })
        assertEquals(-1, Collectionx.indexOfLast(nullList) { it.startsWith("k") })

        assertTwoEquals(0, normalList.lastIndexOf("aj"), Collectionx.lastIndexOf(normalList, "aj"))
        assertTwoEquals(1, normalList.lastIndexOf("bj"), Collectionx.lastIndexOf(normalList, "bj"))
        assertTwoEquals(2, normalList.lastIndexOf("cj"), Collectionx.lastIndexOf(normalList, "cj"))
        assertTwoEquals(3, normalList.lastIndexOf("bo"), Collectionx.lastIndexOf(normalList, "bo"))
        assertTwoEquals(-1, normalList.lastIndexOf("bb"), Collectionx.lastIndexOf(normalList, "bb"))
        assertTwoEquals(-1, emptyList.lastIndexOf("bb"), Collectionx.lastIndexOf(emptyList, "bb"))
        assertEquals(-1, Collectionx.lastIndexOf(normalList, null))
        assertEquals(3, Collectionx.lastIndexOf(nullableList, null))
        assertTwoEquals(0, normalIterable.lastIndexOf("aj"), Collectionx.lastIndexOf(normalIterable, "aj"))
        assertTwoEquals(1, normalIterable.lastIndexOf("bj"), Collectionx.lastIndexOf(normalIterable, "bj"))
        assertTwoEquals(2, normalIterable.lastIndexOf("cj"), Collectionx.lastIndexOf(normalIterable, "cj"))
        assertTwoEquals(3, normalIterable.lastIndexOf("bo"), Collectionx.lastIndexOf(normalIterable, "bo"))
        assertTwoEquals(-1, normalIterable.lastIndexOf("bb"), Collectionx.lastIndexOf(normalIterable, "bb"))
        assertTwoEquals(-1, emptyIterable.lastIndexOf("bb"), Collectionx.lastIndexOf(emptyIterable, "bb"))
        assertEquals(-1, Collectionx.lastIndexOf(normalIterable, null))
        assertEquals(3, Collectionx.lastIndexOf(nullableIterable, null))
        assertEquals(-1, Collectionx.lastIndexOf(nullList, "bb"))
        assertEquals(-1, Collectionx.lastIndexOf(nullList, null))
    }

    @Test
    fun testLast() {
        val normalList = listOf("aj", "bj", "cj", "bo")
        val normalAsIterable = normalList as Iterable<String>
        val normalIterable = object : Iterable<String> {
            override fun iterator(): Iterator<String> {
                return normalList.iterator()
            }
        }
        val emptyList = listOf<String>()
        val emptyAsIterable = emptyList as Iterable<String>
        val emptyIterable = object : Iterable<String> {
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
        val normalLinkedList = Collectionx.linkedListOf("aj", "bj", "cj", "dj")
        val normalIterable = Iterable { normalList.iterator() }
        val emptyList = listOf<String>()
        val emptyIterable = Iterable { emptyList.iterator() }
        val nullList: List<String>? = null

        /*
         * take 方法的意思是从列表头部开始取多少个元素
         */
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.take(-1) },
                { Collectionx.take(normalList, -1) })
        assertTwoEquals(true,
                emptyList<String>() === normalList.take(0),
                Collectionx.emptyList<String>() === Collectionx.take(normalList, 0))
        assertTwoEquals(true,
                emptyList<String>() === normalList.take(0),
                Collectionx.emptyList<String>() === Collectionx.take(nullList, 1))
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
        assertTwoEquals("",
                emptyList.take(2).joinToString(),
                Collectionx.joinToString(Collectionx.take(emptyList, 2)))
        assertTwoEquals("aj, bj",
                normalIterable.take(2).joinToString(),
                Collectionx.joinToString(Collectionx.take(normalIterable, 2)))
        assertTwoEquals("",
                emptyIterable.take(2).joinToString(),
                Collectionx.joinToString(Collectionx.take(emptyIterable, 2)))

        /*
         * takeLast 方法的意思是从列表尾部开始取多少个元素
         */
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.takeLast(-1) },
                { Collectionx.takeLast(normalList, -1) })
        assertTwoEquals(true,
                emptyList<String>() === normalList.takeLast(0),
                Collectionx.emptyList<String>() === Collectionx.takeLast(normalList, 0))
        assertTwoEquals(true,
                emptyList<String>() === normalList.takeLast(0),
                Collectionx.emptyList<String>() === Collectionx.takeLast(nullList, 1))
        assertTwoEquals("dj",
                normalList.takeLast(1).joinToString(),
                Collectionx.joinToString(Collectionx.takeLast(normalList, 1)))
        assertTwoEquals("cj, dj",
                normalList.takeLast(2).joinToString(),
                Collectionx.joinToString(Collectionx.takeLast(normalList, 2)))
        assertTwoEquals("bj, cj, dj",
                normalList.takeLast(3).joinToString(),
                Collectionx.joinToString(Collectionx.takeLast(normalList, 3)))
        assertTwoEquals("aj, bj, cj, dj",
                normalList.takeLast(4).joinToString(),
                Collectionx.joinToString(Collectionx.takeLast(normalList, 4)))
        assertTwoEquals("aj, bj, cj, dj",
                normalList.takeLast(5).joinToString(),
                Collectionx.joinToString(Collectionx.takeLast(normalList, 5)))
        assertTwoEquals("aj, bj, cj, dj",
                normalList.takeLast(5).joinToString(),
                Collectionx.joinToString(Collectionx.takeLast(Collectionx.takeLast(normalList, 5), 5)))
        assertTwoEquals("",
                emptyList.takeLast(2).joinToString(),
                Collectionx.joinToString(Collectionx.takeLast(emptyList, 2)))
        assertTwoEquals("cj, dj",
                normalLinkedList.takeLast(2).joinToString(),
                Collectionx.joinToString(Collectionx.takeLast(normalLinkedList, 2)))

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
        assertTwoEquals("",
                emptyList.takeWhile { !it.startsWith("d") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeWhile(nullList) { !it.startsWith("d") }))

        // takeLastWhile 方法的意思是从列表头部开始到不符合条件的元素（不含包）终止

        assertTwoEquals("bj, cj, dj",
                normalList.takeLastWhile { !it.startsWith("a") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeLastWhile(normalList) { !it.startsWith("a") }))
        assertTwoEquals("cj, dj",
                normalList.takeLastWhile { !it.startsWith("b") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeLastWhile(normalList) { !it.startsWith("b") }))
        assertTwoEquals("dj",
                normalList.takeLastWhile { !it.startsWith("c") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeLastWhile(normalList) { !it.startsWith("c") }))
        assertTwoEquals("",
                normalList.takeLastWhile { !it.startsWith("d") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeLastWhile(normalList) { !it.startsWith("d") }))
        assertTwoEquals("aj, bj, cj, dj",
                normalList.takeLastWhile { !it.startsWith("e") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeLastWhile(normalList) { !it.startsWith("e") }))
        assertTwoEquals("",
                normalList.takeLastWhile { !it.startsWith("d") }.take(2).joinToString(),
                Collectionx.joinToString(Collectionx.take(Collectionx.takeLastWhile(normalList) { !it.startsWith("d") }, 2)))
        assertTwoEquals("",
                emptyList.takeLastWhile { !it.startsWith("d") }.joinToString(),
                Collectionx.joinToString(Collectionx.takeLastWhile(nullList) { !it.startsWith("d") }))
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
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals("aaa, gg, h, uuuu",
                normalList.toMutableList().apply { sort() }.joinToString(),
                Collectionx.joinToString(normalList.toMutableList().apply { Collectionx.sort(this) }))
        assertTwoEquals("",
                emptyList.toMutableList().apply { sort() }.joinToString(),
                Collectionx.joinToString(emptyList.toMutableList().apply { Collectionx.sort(this) }))
        assertTwoEquals("",
                emptyList.toMutableList().apply { sort() }.joinToString(),
                Collectionx.joinToString(nullList.apply { Collectionx.sort(this) }))

        assertTwoEquals("uuuu, h, gg, aaa",
                normalList.toMutableList().apply { sortDescending() }.joinToString(),
                Collectionx.joinToString(normalList.toMutableList().apply { Collectionx.sortDescending(this) }))
        assertTwoEquals("",
                emptyList.toMutableList().apply { sortDescending() }.joinToString(),
                Collectionx.joinToString(emptyList.toMutableList().apply { Collectionx.sortDescending(this) }))
        assertTwoEquals("",
                emptyList.toMutableList().apply { sortDescending() }.joinToString(),
                Collectionx.joinToString(nullList.apply { Collectionx.sortDescending(this) }))

        assertTwoEquals("uuuu, h, gg, aaa",
                normalList.toMutableList().apply { sortWith { it0, it1 -> it0.compareTo(it1) * -1 } }.joinToString(),
                Collectionx.joinToString(normalList.toMutableList().apply { Collectionx.sortWith(this) { it0, it1 -> it0.compareTo(it1) * -1 } }))
        assertTwoEquals("",
                emptyList.toMutableList().apply { sortWith { it0, it1 -> it0.compareTo(it1) * -1 } }.joinToString(),
                Collectionx.joinToString(emptyList.toMutableList().apply { Collectionx.sortWith(this) { it0, it1 -> it0.compareTo(it1) * -1 } }))
        assertTwoEquals("",
                emptyList.toMutableList().apply { sortWith { it0, it1 -> it0.compareTo(it1) * -1 } }.joinToString(),
                Collectionx.joinToString(nullList.apply { Collectionx.sortWith(this) { it0, it1 -> it0.compareTo(it1) * -1 } }))

        assertTwoEquals("h, gg, aaa, uuuu",
                normalList.toMutableList().apply { sortBy { it.length } }.joinToString(),
                Collectionx.joinToString(normalList.toMutableList().apply { Collectionx.sortBy(this) { it.length } }))
        assertTwoEquals("null, null, null, gg, aaa",
                nullableList.toMutableList().apply { sortBy { it?.length ?: 0 } }.joinToString(),
                Collectionx.joinToString(nullableList.toMutableList().apply { Collectionx.sortBy(this) { it.length } }))
        assertTwoEquals("",
                emptyList.toMutableList().apply { sortBy { it.length } }.joinToString(),
                Collectionx.joinToString(emptyList.toMutableList().apply { Collectionx.sortBy(this) { it.length } }))
        assertTwoEquals("",
                emptyList.toMutableList().apply { sortBy { it.length } }.joinToString(),
                Collectionx.joinToString(nullList.apply { Collectionx.sortBy(this) { it.length } }))

        assertTwoEquals("uuuu, aaa, gg, h",
                normalList.toMutableList().apply { sortByDescending { it.length } }.joinToString(),
                Collectionx.joinToString(normalList.toMutableList().apply { Collectionx.sortByDescending(this) { it.length } }))
        assertTwoEquals("aaa, gg, null, null, null",
                nullableList.toMutableList().apply { sortByDescending { it?.length ?: 0 } }.joinToString(),
                Collectionx.joinToString(nullableList.toMutableList().apply { Collectionx.sortByDescending(this) { it.length } }))
        assertTwoEquals("",
                emptyList.toMutableList().apply { sortByDescending { it.length } }.joinToString(),
                Collectionx.joinToString(emptyList.toMutableList().apply { Collectionx.sortByDescending(this) { it.length } }))
        assertTwoEquals("",
                emptyList.toMutableList().apply { sortByDescending { it.length } }.joinToString(),
                Collectionx.joinToString(nullList.apply { Collectionx.sortByDescending(this) { it.length } }))


        assertTwoEquals("aaa, gg, h, uuuu",
                normalList.sorted().joinToString(),
                Collectionx.joinToString(Collectionx.sorted(normalList)))
        assertTwoEquals("",
                emptyList.sorted().joinToString(),
                Collectionx.joinToString(Collectionx.sorted(emptyList)))
        assertTwoEquals("",
                emptyList.sorted().joinToString(),
                Collectionx.joinToString(Collectionx.sorted(nullList)))

        assertTwoEquals("uuuu, h, gg, aaa",
                normalList.sortedDescending().joinToString(),
                Collectionx.joinToString(Collectionx.sortedDescending(normalList)))
        assertTwoEquals("",
                emptyList.sortedDescending().joinToString(),
                Collectionx.joinToString(Collectionx.sortedDescending(emptyList)))
        assertTwoEquals("",
                emptyList.sortedDescending().joinToString(),
                Collectionx.joinToString(Collectionx.sortedDescending(nullList)))

        assertTwoEquals("uuuu, h, gg, aaa",
                normalList.sortedWith { it0, it1 -> it0.compareTo(it1) * -1 }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedWith(normalList) { it0, it1 -> it0.compareTo(it1) * -1 }))
        assertTwoEquals("",
                emptyList.sortedWith { it0, it1 -> it0.compareTo(it1) * -1 }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedWith(emptyList) { it0, it1 -> it0.compareTo(it1) * -1 }))
        assertTwoEquals("",
                emptyList.sortedWith { it0, it1 -> it0.compareTo(it1) * -1 }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedWith(nullList) { it0, it1 -> it0.compareTo(it1) * -1 }))

        assertTwoEquals("h, gg, aaa, uuuu",
                normalList.sortedBy { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedBy(normalList) { it.length }))
        assertTwoEquals("null, null, null, gg, aaa",
                nullableList.sortedBy { it?.length ?: 0 }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedBy(nullableList) { it.length }))
        assertTwoEquals("",
                emptyList.sortedBy { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedBy(emptyList) { it.length }))
        assertTwoEquals("",
                emptyList.sortedBy { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedBy(nullList) { it.length }))

        assertTwoEquals("uuuu, aaa, gg, h",
                normalList.sortedByDescending { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedByDescending(normalList) { it.length }))
        assertTwoEquals("aaa, gg, null, null, null",
                nullableList.sortedByDescending { it?.length ?: 0 }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedByDescending(nullableList) { it.length }))
        assertTwoEquals("",
                emptyList.sortedByDescending { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedByDescending(emptyList) { it.length }))
        assertTwoEquals("",
                emptyList.sortedByDescending { it.length }.joinToString(),
                Collectionx.joinToString(Collectionx.sortedByDescending(nullList) { it.length }))
    }

    @Test
    @Suppress("ReplaceAssociateFunction")
    fun testReverse() {
        val normalList = listOf("aj", "bj", "ao", "bo")
        val normalRange = 1.rangeTo(5)
        val emptyList = listOf<String>()
        val nullList = null as List<String>?

        assertTwoEquals(
                "bo, ao, bj, aj",
                normalList.toMutableList().apply { reverse() }.joinToString(),
                Collectionx.joinToString(normalList.toMutableList().apply { Collectionx.reverse(this) }),
        )
        assertTwoEquals(
                "",
                emptyList.toMutableList().apply { reverse() }.joinToString(),
                Collectionx.joinToString(emptyList.toMutableList().apply { Collectionx.reverse(this) }),
        )
        assertTwoEquals(
                "",
                emptyList.toMutableList().apply { reverse() }.joinToString(),
                Collectionx.joinToString(nullList.apply { Collectionx.reverse(this) }),
        )

        assertTwoEquals(
                "bo, ao, bj, aj",
                normalList.reversed().joinToString(),
                Collectionx.joinToString(Collectionx.reversed(normalList)),
        )
        assertTwoEquals(
                "5, 4, 3, 2, 1",
                normalRange.reversed().joinToString(),
                Collectionx.joinToString(Collectionx.reversed(normalRange)),
        )
        assertTwoEquals(
                "",
                emptyList.reversed().joinToString(),
                Collectionx.joinToString(Collectionx.reversed(emptyList)),
        )
        assertTwoEquals(
                "",
                emptyList.reversed().joinToString(),
                Collectionx.joinToString(Collectionx.reversed(nullList)),
        )
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
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals("aj, bj, bo",
                normalList.distinct().joinToString(),
                Collectionx.joinToString(Collectionx.distinct(normalList)))
        assertTwoEquals("",
                emptyList.distinct().joinToString(),
                Collectionx.joinToString(Collectionx.distinct(emptyList)))
        assertTwoEquals("",
                emptyList.distinct().joinToString(),
                Collectionx.joinToString(Collectionx.distinct(nullList)))

        assertTwoEquals("aj, bo",
                normalList.distinctBy { it.last() }.joinToString(),
                Collectionx.joinToString(Collectionx.distinctBy(normalList) { it.last() }))
        assertTwoEquals("",
                emptyList.distinctBy { it.last() }.joinToString(),
                Collectionx.joinToString(Collectionx.distinctBy(emptyList) { it.last() }))
        assertTwoEquals("",
                emptyList.distinctBy { it.last() }.joinToString(),
                Collectionx.joinToString(Collectionx.distinctBy(nullList) { it.last() }))
    }

    @Test
    fun testAll() {
        val normalList = listOf("aj", "bj", "aj", "bj", "bo")
        val progression = 1.rangeTo(5)
        val emptyList = listOf<String>()
        val nullList = null as List<String>?

        assertTwoEquals(
                true,
                normalList.all { it -> it.all { it.isLetter() } },
                Collectionx.all(normalList) { it -> it.all { it.isLetter() } },
        )
        assertTwoEquals(
                false,
                normalList.all { it.last() == 'j' },
                Collectionx.all(normalList) { it.last() == 'j' },
        )
        assertTwoEquals(
                true,
                emptyList.all { it -> it.all { it.isLetter() } },
                Collectionx.all(emptyList) { it -> it.all { it.isLetter() } },
        )
        assertTrue(Collectionx.all(nullList) { it.last() == 'j' })
        assertTwoEquals(false,
                progression.all { it % 2 == 0 },
                Collectionx.all(progression) { it % 2 == 0 })
    }

    @Test
    fun testAny() {
        val normalList = listOf("aj", "bj", "aj", "bj", "bo")
        val progression = 1.rangeTo(5)
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
        assertTwoEquals(true, progression.any(), Collectionx.any(progression))

        assertTwoEquals(true, normalList.any { it.last() == 'j' }, Collectionx.any(normalList) { it.last() == 'j' })
        assertTwoEquals(false, emptyList.any { it.last() == 'j' }, Collectionx.any(emptyList) { it.last() == 'j' })
        assertFalse(Collectionx.any(nullList) { it -> it.all { it.isDigit() } })
        assertTwoEquals(false,
                normalList.any { it -> it.all { it.isDigit() } },
                Collectionx.any(normalList) { it -> it.all { it.isDigit() } })
        assertTwoEquals(true, progression.any { it % 2 == 0 }, Collectionx.any(progression) { it % 2 == 0 })
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
        val normalList = listOf("a", "b", "c", "d", "e")
        val emptyList = listOf<String>()
        val nullList = null as List<String>?

        assertTwoEquals(
                "^abcde",
                normalList.fold("^") { r, t -> r + t },
                Collectionx.fold(normalList, "^") { r, t -> r + t },
        )
        assertTwoEquals(
                "^",
                emptyList.fold("^") { r, t -> r + t },
                Collectionx.fold(emptyList, "^") { r, t -> r + t },
        )
        assertTwoEquals(
                "^",
                emptyList.fold("^") { r, t -> r + t },
                Collectionx.fold(nullList, "^") { r, t -> r + t },
        )

        assertTwoEquals(
                "^0a1b2c3d4e",
                normalList.foldIndexed("^") { i, r, t -> r + i.toString() + t },
                Collectionx.foldIndexed(normalList, "^") { i, r, t -> r + i.toString() + t },
        )
        assertTwoEquals(
                "^",
                emptyList.foldIndexed("^") { i, r, t -> r + i.toString() + t },
                Collectionx.foldIndexed(emptyList, "^") { i, r, t -> r + i.toString() + t },
        )
        assertTwoEquals(
                "^",
                emptyList.foldIndexed("^") { i, r, t -> r + i.toString() + t },
                Collectionx.foldIndexed(nullList, "^") { i, r, t -> r + i.toString() + t },
        )

        assertTwoEquals(
                "abcde^",
                normalList.foldRight("^") { r, t -> r + t },
                Collectionx.foldRight(normalList, "^") { r, t -> r + t },
        )
        assertTwoEquals(
                "^",
                emptyList.foldRight("^") { r, t -> r + t },
                Collectionx.foldRight(emptyList, "^") { r, t -> r + t },
        )
        assertTwoEquals(
                "^",
                emptyList.foldRight("^") { r, t -> r + t },
                Collectionx.foldRight(nullList, "^") { r, t -> r + t },
        )

        assertTwoEquals(
                "a0b1c2d3e4^",
                normalList.foldRightIndexed("^") { i, r, t -> r + i.toString() + t },
                Collectionx.foldRightIndexed(normalList, "^") { i, r, t -> r + i.toString() + t },
        )
        assertTwoEquals(
                "^",
                emptyList.foldRightIndexed("^") { i, r, t -> r + i.toString() + t },
                Collectionx.foldRightIndexed(emptyList, "^") { i, r, t -> r + i.toString() + t },
        )
        assertTwoEquals(
                "^",
                emptyList.foldRightIndexed("^") { i, r, t -> r + i.toString() + t },
                Collectionx.foldRightIndexed(nullList, "^") { i, r, t -> r + i.toString() + t },
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

        assertTwoEquals("aj, bj, aj, bj, bo",
                ArrayList<String>().apply { normalList.iterator().forEach { add(it) } }.joinToString(),
                ArrayList<String>().apply { Collectionx.forEach(normalList.iterator()) { add(it) } }.joinToString())
        assertTwoEquals("",
                ArrayList<String>().apply { listOf<String>().iterator().forEach { add(it) } }.joinToString(),
                ArrayList<String>().apply { Collectionx.forEach(null as Iterator<String>?) { add(it) } }.joinToString())

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
    fun testChunked() {
        val normalList = listOf("a", "b", "c", "d", "e")
        val emptyList = listOf<String>()
        val nullList = null as List<String>?

        assertTwoThrow(IllegalArgumentException::class,
                { normalList.chunked(-1) },
                { Collectionx.chunked(normalList, -1) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.chunked(0) },
                { Collectionx.chunked(normalList, 0) })
        assertTwoEquals("[[a], [b], [c], [d], [e]]",
                normalList.chunked(1).toString(),
                Collectionx.chunked(normalList, 1).toString())
        assertTwoEquals("[[a, b], [c, d], [e]]",
                normalList.chunked(2).toString(),
                Collectionx.chunked(normalList, 2).toString())
        assertTwoEquals("[[a, b, c], [d, e]]",
                normalList.chunked(3).toString(),
                Collectionx.chunked(normalList, 3).toString())
        assertTwoEquals("[[a, b, c, d], [e]]",
                normalList.chunked(4).toString(),
                Collectionx.chunked(normalList, 4).toString())
        assertTwoEquals("[[a, b, c, d, e]]",
                normalList.chunked(5).toString(),
                Collectionx.chunked(normalList, 5).toString())
        assertTwoEquals("[[a, b, c, d, e]]",
                normalList.chunked(6).toString(),
                Collectionx.chunked(normalList, 6).toString())

        assertTwoEquals("[]",
                emptyList.chunked(1).toString(),
                Collectionx.chunked(emptyList, 1).toString())
        assertEquals("[]",
                Collectionx.chunked(nullList, 1).toString())


        assertTwoThrow(IllegalArgumentException::class,
                { normalList.chunked(-1) },
                { Collectionx.chunked(normalList, -1) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.chunked(0) },
                { Collectionx.chunked(normalList, 0) })
        assertTwoEquals("[[a], [b], [c], [d], [e]]",
                normalList.chunked(1) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.chunked(normalList, 1) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[a+b], [c+d], [e]]",
                normalList.chunked(2) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.chunked(normalList, 2) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[a+b+c], [d+e]]",
                normalList.chunked(3) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.chunked(normalList, 3) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[a+b+c+d], [e]]",
                normalList.chunked(4) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.chunked(normalList, 4) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[a+b+c+d+e]]",
                normalList.chunked(5) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.chunked(normalList, 5) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[a+b+c+d+e]]",
                normalList.chunked(6) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.chunked(normalList, 6) { it.joinToString("+", "[", "]") }.toString())

        assertTwoEquals("[]",
                emptyList.chunked(1) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.chunked(emptyList, 1) { it.joinToString("+", "[", "]") }.toString())
        assertEquals("[]",
                Collectionx.chunked(nullList, 1) { it.joinToString("+", "[", "]") }.toString())
    }

    @Test
    fun testWindowed() {
        val normalList = listOf(1, 2, 3, 4, 5)
        val emptyList = listOf<Int>()
        val nullList = null as List<Int>?

        // Test illegal size and step parameter
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.windowed(0, 0, true) },
                { Collectionx.windowed(normalList, 0, 0, true) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.windowed(1, 0, true) },
                { Collectionx.windowed(normalList, 1, 0, true) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.windowed(0, 1, true) },
                { Collectionx.windowed(normalList, 0, 1, true) })

        assertTwoThrow(IllegalArgumentException::class,
                { normalList.windowed(0, 0, true) },
                { Collectionx.windowed(normalList, 0, 0, true) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.windowed(1, 0, true) },
                { Collectionx.windowed(normalList, 1, 0, true) })
        assertTwoThrow(IllegalArgumentException::class,
                { normalList.windowed(0, 1, true) },
                { Collectionx.windowed(normalList, 0, 1, true) })

        // Test the size parameter
        assertTwoEquals("[[1], [2], [3], [4], [5]]",
                normalList.windowed(1, 1, true).toString(),
                Collectionx.windowed(normalList, 1, 1, true).toString())
        assertTwoEquals("[[1, 2], [2, 3], [3, 4], [4, 5], [5]]",
                normalList.windowed(2, 1, true).toString(),
                Collectionx.windowed(normalList, 2, 1, true).toString())
        assertTwoEquals("[[1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5], [5]]",
                normalList.windowed(3, 1, true).toString(),
                Collectionx.windowed(normalList, 3, 1, true).toString())
        assertTwoEquals("[[1, 2, 3, 4], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]]",
                normalList.windowed(4, 1, true).toString(),
                Collectionx.windowed(normalList, 4, 1, true).toString())
        assertTwoEquals("[[1, 2, 3, 4, 5], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]]",
                normalList.windowed(5, 1, true).toString(),
                Collectionx.windowed(normalList, 5, 1, true).toString())
        assertTwoEquals("[[1, 2, 3, 4, 5], [2, 3, 4, 5], [3, 4, 5], [4, 5], [5]]",
                normalList.windowed(6, 1, true).toString(),
                Collectionx.windowed(normalList, 6, 1, true).toString())

        assertTwoEquals("[[1], [2], [3], [4], [5]]",
                normalList.windowed(1, 1, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 1, 1, true) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2], [2+3], [3+4], [4+5], [5]]",
                normalList.windowed(2, 1, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 2, 1, true) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2+3], [2+3+4], [3+4+5], [4+5], [5]]",
                normalList.windowed(3, 1, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 3, 1, true) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2+3+4], [2+3+4+5], [3+4+5], [4+5], [5]]",
                normalList.windowed(4, 1, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 4, 1, true) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2+3+4+5], [2+3+4+5], [3+4+5], [4+5], [5]]",
                normalList.windowed(5, 1, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 5, 1, true) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2+3+4+5], [2+3+4+5], [3+4+5], [4+5], [5]]",
                normalList.windowed(6, 1, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 6, 1, true) { it.joinToString("+", "[", "]") }.toString())

        // Test the step parameter
        assertTwoEquals("[[1, 2], [3, 4], [5]]",
                normalList.windowed(2, 2, true).toString(),
                Collectionx.windowed(normalList, 2, 2, true).toString())
        assertTwoEquals("[[1, 2], [4, 5]]",
                normalList.windowed(2, 3, true).toString(),
                Collectionx.windowed(normalList, 2, 3, true).toString())
        assertTwoEquals("[[1, 2], [5]]",
                normalList.windowed(2, 4, true).toString(),
                Collectionx.windowed(normalList, 2, 4, true).toString())
        assertTwoEquals("[[1, 2]]",
                normalList.windowed(2, 5, true).toString(),
                Collectionx.windowed(normalList, 2, 5, true).toString())
        assertTwoEquals("[[1, 2]]",
                normalList.windowed(2, 6, true).toString(),
                Collectionx.windowed(normalList, 2, 6, true).toString())

        assertTwoEquals("[[1+2], [3+4], [5]]",
                normalList.windowed(2, 2, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 2, 2, true) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2], [4+5]]",
                normalList.windowed(2, 3, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 2, 3, true) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2], [5]]",
                normalList.windowed(2, 4, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 2, 4, true) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2]]",
                normalList.windowed(2, 5, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 2, 5, true) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2]]",
                normalList.windowed(2, 6, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 2, 6, true) { it.joinToString("+", "[", "]") }.toString())

        // Test the partialWindows parameter
        assertTwoEquals("[[1], [2], [3], [4], [5]]",
                normalList.windowed(1, 1, false).toString(),
                Collectionx.windowed(normalList, 1, 1, false).toString())
        assertTwoEquals("[[1, 2], [2, 3], [3, 4], [4, 5]]",
                normalList.windowed(2, 1, false).toString(),
                Collectionx.windowed(normalList, 2, 1, false).toString())
        assertTwoEquals("[[1, 2], [3, 4]]",
                normalList.windowed(2, 2, false).toString(),
                Collectionx.windowed(normalList, 2, 2, false).toString())
        assertTwoEquals("[[1, 2, 3], [2, 3, 4], [3, 4, 5]]",
                normalList.windowed(3, 1, false).toString(),
                Collectionx.windowed(normalList, 3, 1, false).toString())
        assertTwoEquals("[[1, 2, 3, 4], [2, 3, 4, 5]]",
                normalList.windowed(4, 1, false).toString(),
                Collectionx.windowed(normalList, 4, 1, false).toString())
        assertTwoEquals("[[1, 2, 3, 4, 5]]",
                normalList.windowed(5, 1, false).toString(),
                Collectionx.windowed(normalList, 5, 1, false).toString())
        assertTwoEquals("[]",
                normalList.windowed(6, 1, false).toString(),
                Collectionx.windowed(normalList, 6, 1, false).toString())

        assertTwoEquals("[[1], [2], [3], [4], [5]]",
                normalList.windowed(1, 1, false) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 1, 1, false) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2], [2+3], [3+4], [4+5]]",
                normalList.windowed(2, 1, false) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 2, 1, false) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2+3], [2+3+4], [3+4+5]]",
                normalList.windowed(3, 1, false) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 3, 1, false) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2+3+4], [2+3+4+5]]",
                normalList.windowed(4, 1, false) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 4, 1, false) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[[1+2+3+4+5]]",
                normalList.windowed(5, 1, false) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 5, 1, false) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[]",
                normalList.windowed(6, 1, false) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(normalList, 6, 1, false) { it.joinToString("+", "[", "]") }.toString())

        // Test empty or null
        assertTwoEquals("[]",
                emptyList.windowed(1, 1, true).toString(),
                Collectionx.windowed(emptyList, 1, 1, true).toString())
        assertTwoEquals("[]",
                emptyList.windowed(1, 1, true).toString(),
                Collectionx.windowed(nullList, 1, 1, true).toString())

        assertTwoEquals("[]",
                emptyList.windowed(1, 1, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(emptyList, 1, 1, true) { it.joinToString("+", "[", "]") }.toString())
        assertTwoEquals("[]",
                emptyList.windowed(1, 1, true) { it.joinToString("+", "[", "]") }.toString(),
                Collectionx.windowed(nullList, 1, 1, true) { it.joinToString("+", "[", "]") }.toString())
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
    fun testAddAll() {
        val normalList = listOf("6", "3", "7", "2", "1")
        val normalIterable = object : Iterable<String> {
            override fun iterator(): Iterator<String> {
                return normalList.iterator()
            }
        }
        val normalArray = arrayOf("6", "3", "7", "2", "1")
        val emptyList = listOf<String>()
        val emptyArray = arrayOf<String>()
        val nullList: List<String>? = null
        val nullArray: Array<String>? = null
        val collection0 = mutableListOf<String>()
        val collection1 = mutableListOf<String>()
        val set0 = mutableSetOf<String>()
        val set1 = mutableSetOf<String>()

        collection0.clear()
        collection1.clear()
        assertTwoEquals("", collection0.joinToString(), collection1.joinToString())
        assertTwoEquals(true, collection0.addAll(normalList as Iterable<String>), Collectionx.addAll(collection1, normalList as Iterable<String>))
        assertTwoEquals("6, 3, 7, 2, 1", collection0.joinToString(), collection1.joinToString())

        collection0.clear()
        collection1.clear()
        assertTwoEquals("", collection0.joinToString(), collection1.joinToString())
        assertTwoEquals(false, collection0.addAll(emptyList as Iterable<String>), Collectionx.addAll(collection1, emptyList as Iterable<String>))
        assertTwoEquals("", collection0.joinToString(), collection1.joinToString())

        collection1.clear()
        assertEquals("", collection1.joinToString())
        assertEquals(false, Collectionx.addAll(collection1, nullList as Iterable<String>?))
        assertEquals("", collection1.joinToString())

        collection1.clear()
        assertEquals("", collection1.joinToString())
        assertEquals(false, Collectionx.addAll(null as MutableList<String>?, nullList as Iterable<String>?))
        assertEquals("", collection1.joinToString())

        collection0.clear()
        collection1.clear()
        assertTwoEquals("", collection0.joinToString(), collection1.joinToString())
        assertTwoEquals(true, collection0.addAll(normalIterable), Collectionx.addAll(collection1, normalIterable))
        assertTwoEquals("6, 3, 7, 2, 1", collection0.joinToString(), collection1.joinToString())

        set0.clear()
        set1.clear()
        assertTwoEquals("", set0.joinToString(), set1.joinToString())
        assertTwoEquals(true, set0.addAll(normalIterable), Collectionx.addAll(set1, normalIterable))
        assertTwoEquals("6, 3, 7, 2, 1", set0.joinToString(), set1.joinToString())
        assertTwoEquals(false, set0.addAll(normalIterable), Collectionx.addAll(set1, normalIterable))
        assertTwoEquals("6, 3, 7, 2, 1", set0.joinToString(), set1.joinToString())


        collection0.clear()
        collection1.clear()
        assertTwoEquals("", collection0.joinToString(), collection1.joinToString())
        assertTwoEquals(true, collection0.addAll(normalArray), Collectionx.addAll(collection1, normalArray))
        assertTwoEquals("6, 3, 7, 2, 1", collection0.joinToString(), collection1.joinToString())

        collection0.clear()
        collection1.clear()
        assertTwoEquals("", collection0.joinToString(), collection1.joinToString())
        assertTwoEquals(false, collection0.addAll(emptyArray), Collectionx.addAll(collection1, emptyArray))
        assertTwoEquals("", collection0.joinToString(), collection1.joinToString())

        collection1.clear()
        assertEquals("", collection1.joinToString())
        assertEquals(false, Collectionx.addAll(collection1, nullArray))
        assertEquals("", collection1.joinToString())

        collection1.clear()
        assertEquals("", collection1.joinToString())
        assertEquals(false, Collectionx.addAll(null as MutableList<String>?, nullArray))
        assertEquals("", collection1.joinToString())

        set0.clear()
        set1.clear()
        assertTwoEquals("", set0.joinToString(), set1.joinToString())
        assertTwoEquals(true, set0.addAll(normalArray), Collectionx.addAll(set1, normalArray))
        assertTwoEquals("6, 3, 7, 2, 1", set0.joinToString(), set1.joinToString())
        assertTwoEquals(false, set0.addAll(normalArray), Collectionx.addAll(set1, normalArray))
        assertTwoEquals("6, 3, 7, 2, 1", set0.joinToString(), set1.joinToString())
    }

    @Test
    fun testRemoveAll() {
        assertTwoEquals(true,
                (mutableListOf("6", "4", "3") as MutableIterable<String>).removeAll { it.toInt() % 2 == 0 },
                Collectionx.removeAll(mutableListOf("6", "4", "3") as MutableIterable<String>) { it.toInt() % 2 == 0 })
        assertTwoEquals("3",
                (mutableListOf("6", "4", "3") as MutableIterable<String>).apply { removeAll { it.toInt() % 2 == 0 } }.joinToString(),
                (mutableListOf("6", "4", "3") as MutableIterable<String>).apply { Collectionx.removeAll(this) { it.toInt() % 2 == 0 } }.joinToString())
        assertTwoEquals(false,
                (mutableListOf("6", "4", "3") as MutableIterable<String>).removeAll { it.toInt() > 100 },
                Collectionx.removeAll(mutableListOf("6", "4", "3") as MutableIterable<String>) { it.toInt() > 100 })
        assertTwoEquals("6, 4, 3",
                (mutableListOf("6", "4", "3") as MutableIterable<String>).apply { removeAll { it.toInt() > 100 } }.joinToString(),
                (mutableListOf("6", "4", "3") as MutableIterable<String>).apply { Collectionx.removeAll(this) { it.toInt() > 100 } }.joinToString())
        assertFalse(Collectionx.removeAll(null as MutableIterable<String>?) { it.toInt() % 2 == 0 })

        assertTwoEquals(true,
                (mutableListOf("6", "4", "3")).removeAll(listOf("6", "3") as Iterable<String>),
                Collectionx.removeAll(mutableListOf("6", "4", "3"), listOf("6", "3") as Iterable<String>))
        assertTwoEquals("4",
                (mutableListOf("6", "4", "3")).apply { removeAll(listOf("6", "3") as Iterable<String>) }.joinToString(),
                (mutableListOf("6", "4", "3")).apply { Collectionx.removeAll(this, listOf("6", "3") as Iterable<String>) }.joinToString())
        assertTwoEquals(false,
                (mutableListOf("6", "4", "3")).removeAll(listOf("100", "99") as Iterable<String>),
                Collectionx.removeAll(mutableListOf("6", "4", "3"), listOf("100", "99") as Iterable<String>))
        assertTwoEquals("6, 4, 3",
                (mutableListOf("6", "4", "3")).apply { removeAll(listOf("100", "99") as Iterable<String>) }.joinToString(),
                (mutableListOf("6", "4", "3")).apply { Collectionx.removeAll(this, listOf("100", "99") as Iterable<String>) }.joinToString())
        assertFalse(Collectionx.removeAll(mutableListOf("6", "4", "3"), null as Iterable<String>?))
        assertFalse(Collectionx.removeAll(null as Collection<String>?, null as Iterable<String>?))

        assertTwoEquals(true,
                (mutableListOf("6", "4", "3")).removeAll(arrayOf("6", "3")),
                Collectionx.removeAll(mutableListOf("6", "4", "3"), arrayOf("6", "3")))
        assertTwoEquals("4",
                (mutableListOf("6", "4", "3")).apply { removeAll(arrayOf("6", "3")) }.joinToString(),
                (mutableListOf("6", "4", "3")).apply { Collectionx.removeAll(this, arrayOf("6", "3")) }.joinToString())
        assertTwoEquals(false,
                (mutableListOf("6", "4", "3")).removeAll(arrayOf("100", "99")),
                Collectionx.removeAll(mutableListOf("6", "4", "3"), arrayOf("100", "99")))
        assertTwoEquals("6, 4, 3",
                (mutableListOf("6", "4", "3")).apply { removeAll(arrayOf("100", "99")) }.joinToString(),
                (mutableListOf("6", "4", "3")).apply { Collectionx.removeAll(this, arrayOf("100", "99")) }.joinToString())
        assertTwoEquals(false,
                (mutableListOf("6", "4", "3")).removeAll(arrayOf()),
                Collectionx.removeAll(mutableListOf("6", "4", "3"), arrayOf<String>()))
        assertTwoEquals("6, 4, 3",
                (mutableListOf("6", "4", "3")).apply { removeAll(arrayOf()) }.joinToString(),
                (mutableListOf("6", "4", "3")).apply { Collectionx.removeAll(this, arrayOf<String>()) }.joinToString())
        assertFalse(Collectionx.removeAll(mutableListOf("6", "4", "3"), null as Array<String>?))
        assertFalse(Collectionx.removeAll(null as Collection<String>?, null as Array<String>?))

        assertTwoEquals(true,
                (mutableListOf("6", "4", "3")).removeAll(listOf("6", "3")),
                Collectionx.removeAll(mutableListOf("6", "4", "3"), listOf("6", "3")))
        assertTwoEquals("4",
                (mutableListOf("6", "4", "3")).apply { removeAll(listOf("6", "3")) }.joinToString(),
                (mutableListOf("6", "4", "3")).apply { Collectionx.removeAll(this, listOf("6", "3")) }.joinToString())
        assertTwoEquals(false,
                (mutableListOf("6", "4", "3")).removeAll(listOf("100", "99")),
                Collectionx.removeAll(mutableListOf("6", "4", "3"), listOf("100", "99")))
        assertTwoEquals("6, 4, 3",
                (mutableListOf("6", "4", "3")).apply { removeAll(listOf("100", "99")) }.joinToString(),
                (mutableListOf("6", "4", "3")).apply { Collectionx.removeAll(this, listOf("100", "99")) }.joinToString())
        assertTwoEquals(false,
                (mutableListOf("6", "4", "3")).removeAll(listOf()),
                Collectionx.removeAll(mutableListOf("6", "4", "3"), listOf<String>()))
        assertTwoEquals("6, 4, 3",
                (mutableListOf("6", "4", "3")).apply { removeAll(listOf()) }.joinToString(),
                (mutableListOf("6", "4", "3")).apply { Collectionx.removeAll(this, listOf<String>()) }.joinToString())
        assertFalse(Collectionx.removeAll(mutableListOf("6", "4", "3"), null as Collection<String>?))
        assertFalse(Collectionx.removeAll(null as Collection<String>?, null as Collection<String>?))
    }

    @Test
    fun testTo() {
        assertTwoEquals("1, 2, 3",
                1.rangeTo(3).toCollection(ArrayList()).joinToString(),
                Collectionx.toCollection(1.rangeTo(3), ArrayList()).joinToString())
        assertEquals("", Collectionx.toCollection(null as IntProgression?, ArrayList()).joinToString())

        assertTwoEquals("1, 2, 3",
                1.rangeTo(3).toList().joinToString(),
                Collectionx.toList(1.rangeTo(3)).joinToString())
        assertTwoEquals(ArrayList::class,
                1.rangeTo(3).toList()::class,
                Collectionx.toList(1.rangeTo(3))::class)
        assertTwoEquals("1, 2, 3",
                setOf(1, 2, 3).toList().joinToString(),
                Collectionx.toList(setOf(1, 2, 3)).joinToString())
        assertTwoEquals(ArrayList::class,
                setOf(1, 2, 3).toList()::class,
                Collectionx.toList(setOf(1, 2, 3))::class)
        assertEquals("", Collectionx.toList(null as Iterable<Int>?).joinToString())
        assertEquals(ArrayList::class, Collectionx.toList(null as Iterable<Int>?)::class)

        assertTwoEquals("1, 2, 3",
                1.rangeTo(3).toSet().joinToString(),
                Collectionx.toSet(1.rangeTo(3)).joinToString())
        assertTwoEquals(LinkedHashSet::class,
                1.rangeTo(3).toSet()::class,
                Collectionx.toSet(1.rangeTo(3))::class)
        assertTwoEquals("1, 2, 3",
                setOf(1, 2, 3).toSet().joinToString(),
                Collectionx.toSet(setOf(1, 2, 3)).joinToString())
        assertTwoEquals(LinkedHashSet::class,
                setOf(1, 2, 3).toSet()::class,
                Collectionx.toSet(setOf(1, 2, 3))::class)
        assertEquals("", Collectionx.toSet(null as Iterable<Int>?).joinToString())
        assertEquals(LinkedHashSet::class, Collectionx.toSet(null as Iterable<Int>?)::class)

        assertTwoEquals("1, 2, 3",
                setOf(1, 2, 3).toSortedSet().joinToString(),
                Collectionx.toSortedSet(setOf(1, 2, 3)).joinToString())
        assertTwoEquals(TreeSet::class,
                setOf(1, 2, 3).toSortedSet()::class,
                Collectionx.toSortedSet(setOf(1, 2, 3))::class)
        assertEquals("", Collectionx.toSortedSet(null as Iterable<Int>?).joinToString())
        assertEquals(TreeSet::class, Collectionx.toSortedSet(null as Iterable<Int>?)::class)

        assertTwoEquals("3, 2, 1",
                setOf(1, 2, 3).toSortedSet { it1, it2 -> it2 - it1 }.joinToString(),
                Collectionx.toSortedSet(setOf(1, 2, 3)) { it1, it2 -> it2 - it1 }.joinToString())
        assertTwoEquals(TreeSet::class,
                setOf(1, 2, 3).toSortedSet { it1, it2 -> it2 - it1 }::class,
                Collectionx.toSortedSet(setOf(1, 2, 3)) { it1, it2 -> it2 - it1 }::class)
        assertEquals("", Collectionx.toSortedSet(null as Iterable<Int>?) { it1, it2 -> it2 - it1 }.joinToString())
        assertEquals(TreeSet::class, Collectionx.toSortedSet(null as Iterable<Int>?) { it1, it2 -> it2 - it1 }::class)

        assertTwoEquals("1, 2, 3",
                setOf(1, 2, 3).toHashSet().joinToString(),
                Collectionx.toHashSet(setOf(1, 2, 3)).joinToString())
        assertTwoEquals(HashSet::class,
                setOf(1, 2, 3).toHashSet()::class,
                Collectionx.toHashSet(setOf(1, 2, 3))::class)
        assertEquals("", Collectionx.toHashSet(null as Iterable<Int>?).joinToString())
        assertEquals(HashSet::class, Collectionx.toHashSet(null as Iterable<Int>?)::class)


        /* test convertToSetForSetOperationWith() method */
        assertTwoEquals("[]",
                (mutableListOf("6", "4", "3")).minus(listOf("6", "4", "3") as Iterable<String>).toString(),
                Collectionx.minus(mutableListOf("6", "4", "3"), listOf("6", "4", "3") as Iterable<String>).toString())
        assertTwoEquals("[5]",
                (6 downTo 3).minus(mutableListOf(6, 4, 3)).toString(),
                Collectionx.minus(6 downTo 3, mutableListOf(6, 4, 3)).toString())
    }

    @Test
    fun testUnion() {
        assertTwoEquals("1, 3, 2, 4",
                listOf(1, 3).union(listOf(2, 4)).joinToString(),
                Collectionx.union(listOf(1, 3), listOf(2, 4)).joinToString())
        assertTwoEquals(LinkedHashSet::class,
                listOf(1, 3).union(listOf(2, 4))::class,
                Collectionx.union(listOf(1, 3), listOf(2, 4))::class)
    }

    @Test
    fun testNone() {
        val normalList = listOf("6", "3", "7", "2", "1")
        val progression = 1.rangeTo(5)
        @Suppress("EmptyRange") val emptyProgression = 1.rangeTo(0)
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals(false, normalList.none(), Collectionx.none(normalList))
        assertTwoEquals(true, emptyList.none(), Collectionx.none(emptyList))
        assertTrue(Collectionx.none(nullList))
        assertTwoEquals(false, progression.none(), Collectionx.none(progression))
        assertTwoEquals(true, emptyProgression.none(), Collectionx.none(emptyProgression))

        assertTwoEquals(true, normalList.none { it.length > 1 }, Collectionx.none(normalList) { it.length > 1 })
        assertTwoEquals(false, normalList.none { it.isNotEmpty() }, Collectionx.none(normalList) { it.isNotEmpty() })
        assertTwoEquals(true, emptyList.none { it.length > 1 }, Collectionx.none(emptyList) { it.length > 1 })
        assertTrue(Collectionx.none(nullList) { it.length > 1 })
        assertTwoEquals(true, progression.none { it > 10 }, Collectionx.none(progression) { it > 10 })
    }

    @Test
    fun testReduce() {
        val normalList = listOf("a", "b", "c", "d", "e")
        val emptyList = listOf<String>()
        val nullList: List<String>? = null

        assertTwoEquals("abcde",
                normalList.reduce { it0, it1 -> it0 + it1 },
                Collectionx.reduce(normalList) { it0, it1 -> it0 + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList.reduce { it0, it1 -> it0 + it1 } },
                { Collectionx.reduce(emptyList) { it0, it1 -> it0 + it1 } })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList.reduce { it0, it1 -> it0 + it1 } },
                { Collectionx.reduce(nullList) { it0, it1 -> it0 + it1 } })

        assertTwoEquals("a1b2c3d4e",
                normalList.reduceIndexed { i, it0, it1 -> it0 + i + it1 },
                Collectionx.reduceIndexed(normalList) { i, it0, it1 -> it0 + i + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList.reduceIndexed { i, it0, it1 -> it0 + i + it1 } },
                { Collectionx.reduceIndexed(emptyList) { i, it0, it1 -> it0 + i + it1 } })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList.reduceIndexed { i, it0, it1 -> it0 + i + it1 } },
                { Collectionx.reduceIndexed(nullList) { i, it0, it1 -> it0 + i + it1 } })

        assertTwoEquals("abcde",
                normalList.reduceRight { it0, it1 -> it0 + it1 },
                Collectionx.reduceRight(normalList) { it0, it1 -> it0 + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList.reduceRight { it0, it1 -> it0 + it1 } },
                { Collectionx.reduceRight(emptyList) { it0, it1 -> it0 + it1 } })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList.reduceRight { it0, it1 -> it0 + it1 } },
                { Collectionx.reduceRight(nullList) { it0, it1 -> it0 + it1 } })

        assertTwoEquals("a0b1c2d3e",
                normalList.reduceRightIndexed { i, it0, it1 -> it0 + i + it1 },
                Collectionx.reduceRightIndexed(normalList) { i, it0, it1 -> it0 + i + it1 })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList.reduceRightIndexed { i, it0, it1 -> it0 + i + it1 } },
                { Collectionx.reduceRightIndexed(emptyList) { i, it0, it1 -> it0 + i + it1 } })
        assertTwoThrow(UnsupportedOperationException::class,
                { emptyList.reduceRightIndexed { i, it0, it1 -> it0 + i + it1 } },
                { Collectionx.reduceRightIndexed(nullList) { i, it0, it1 -> it0 + i + it1 } })
    }

    @Test
    fun testSlice() {
        val normalList = listOf("6", "3", "7", "2", "1")
        val nullList: List<String>? = null

        assertTwoEquals("[7, 2, 6]",
                normalList.slice(listOf(2, 3, 0)).toString(),
                Collectionx.slice(normalList, listOf(2, 3, 0)).toString())
        assertTwoEquals("[]",
                normalList.slice(listOf()).toString(),
                Collectionx.slice(normalList, listOf<Int>()).toString())
        assertEquals("[]",
                Collectionx.slice(nullList, listOf(2, 3, 0)).toString())
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
        assertTwoEquals("",
                emptyList.plus(arrayOf<String>()).joinToString(),
                Collectionx.joinToString(Collectionx.plus(emptyList, arrayOf<String>())))
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
    fun testIndices() {
        assertTwoEquals("",
                listOf<String>().indices.joinToString(),
                Collectionx.joinToString(Collectionx.indices(Collectionx.listOf<String>())))
        assertTwoEquals("0",
                listOf("a").indices.joinToString(),
                Collectionx.joinToString(Collectionx.indices(Collectionx.listOf("a"))))
        assertTwoEquals("0, 1",
                listOf("a", "b").indices.joinToString(),
                Collectionx.joinToString(Collectionx.indices(Collectionx.listOf("a", "b"))))
        assertTwoEquals("0, 1, 2",
                listOf("a", "b", "c").indices.joinToString(),
                Collectionx.joinToString(Collectionx.indices(Collectionx.listOf("a", "b", "c"))))
        assertTwoEquals("0, 1, 2, 3",
                listOf("a", "b", "c", "e").indices.joinToString(),
                Collectionx.joinToString(Collectionx.indices(Collectionx.listOf("a", "b", "c", "e"))))
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
        assertThrow(IllegalStateException::class) { Collectionx.joinTo(emptyList, ExceptionAppendable(), null, null, null, -1, null, null).toString() }


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