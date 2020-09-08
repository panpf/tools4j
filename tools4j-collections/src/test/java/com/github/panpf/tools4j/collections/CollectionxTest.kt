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
    fun testDrop() {
        val lists = listOf("1", "2", "3", "4")
        assertEquals(Collectionx.joinToArrayString(Collectionx.drop(lists, 2)), Collectionx.joinToArrayString(lists.drop(2)))
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
}