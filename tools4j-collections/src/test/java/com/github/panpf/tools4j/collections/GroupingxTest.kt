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

package com.github.panpf.tools4j.collections

import com.github.panpf.tools4j.test.ktx.assertTwoEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class GroupingxTest {

    @Test
    fun testGroupingBy() {
        val charSequence = "13652689453"
        val stringArray = arrayOf("ab", "ba", "ab", "ba", "ba")
        val stringList = listOf("ab", "ba", "ab", "ba", "ba")

        assertTwoEquals("{1=1, 3=2, 6=2, 5=2, 2=1, 8=1, 9=1, 4=1}",
                charSequence.groupingBy { it }.eachCount().toString(),
                Groupingx.eachCount(Groupingx.groupingBy(charSequence) { it }).toString())
        assertTwoEquals("{}",
                "".groupingBy { it }.eachCount().toString(),
                Groupingx.eachCount(Groupingx.groupingBy("") { it }).toString())
        assertEquals("{}",
                Groupingx.eachCount(Groupingx.groupingBy(null as CharSequence?) { it }).toString())

        assertTwoEquals("{ab=2, ba=3}",
                stringArray.groupingBy { it }.eachCount().toString(),
                Groupingx.eachCount(Groupingx.groupingBy(stringArray) { it }).toString())
        assertTwoEquals("{}",
                arrayOf<String>().groupingBy { it }.eachCount().toString(),
                Groupingx.eachCount(Groupingx.groupingBy(arrayOf<String>()) { it }).toString())
        assertEquals("{}",
                Groupingx.eachCount(Groupingx.groupingBy(null as Array<String>?) { it }).toString())

        assertTwoEquals("{ab=2, ba=3}",
                stringList.groupingBy { it }.eachCount().toString(),
                Groupingx.eachCount(Groupingx.groupingBy(stringList) { it }).toString())
        assertTwoEquals("{}",
                listOf<String>().groupingBy { it }.eachCount().toString(),
                Groupingx.eachCount(Groupingx.groupingBy(listOf<String>()) { it }).toString())
        assertEquals("{}",
                Groupingx.eachCount(Groupingx.groupingBy(null as List<String>?) { it }).toString())
    }

    @Test
    fun testAggregate() {
        val list = listOf("王五", "张三", "李四", "赵六", "李四", "王五", "张三", "李四", "张三")

        assertTwoEquals(mapOf("王" to 2, "张" to 3, "李" to 3, "赵" to 1),
                list.groupingBy { it.first().toString() }.aggregate { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap(),
                Groupingx.aggregate(Groupingx.groupingBy(list) { it.first().toString() }) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap())

        assertTwoEquals(mapOf("王" to 2, "张" to 3, "李" to 3, "赵" to 1),
                list.groupingBy { it.first().toString() }.aggregateTo(LinkedHashMap()) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap(),
                Groupingx.aggregateTo(Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap()) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap())

        assertTwoEquals(LinkedHashMap<String, Int>(),
                listOf<String>().groupingBy { it.first().toString() }.aggregateTo(LinkedHashMap()) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap(),
                Groupingx.aggregateTo(null as Grouping<String, String>?, LinkedHashMap()) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap())

        assertTwoEquals(mapOf("王" to 2, "张" to 3, "李" to 3, "赵" to 1),
                list.groupingBy { it.first().toString() }.aggregateTo(LinkedHashMap<String, Int?>().apply { put("王", null) }) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap(),
                Groupingx.aggregateTo(Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap<String, Int?>().apply { put("王", null) }) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap())
    }

    @Test
    fun testFold() {
        val list = listOf("王五", "张三", "李四", "赵六", "李四", "王五", "张三", "李四", "张三")

        assertTwoEquals(mapOf("王" to 12, "张" to 13, "李" to 13, "赵" to 11),
                list.groupingBy { it.first().toString() }.fold({ _, _ -> 10 }) { _, accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap(),
                Groupingx.fold(Groupingx.groupingBy(list) { it.first().toString() }, { _, _ -> 10 }) { _, accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap())

        assertTwoEquals(mapOf("王" to 12, "张" to 13, "李" to 13, "赵" to 11),
                list.groupingBy { it.first().toString() }.fold(10) { accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap(),
                Groupingx.fold(Groupingx.groupingBy(list) { it.first().toString() }, 10) { accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap())

        assertTwoEquals(mapOf("王" to 12, "张" to 13, "李" to 13, "赵" to 11),
                list.groupingBy { it.first().toString() }.foldTo(LinkedHashMap(), { _, _ -> 10 }) { _, accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap(),
                Groupingx.foldTo(Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap(), { _, _ -> 10 }) { _, accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap())

        assertTwoEquals(mapOf("王" to 12, "张" to 13, "李" to 13, "赵" to 11),
                list.groupingBy { it.first().toString() }.foldTo(LinkedHashMap(), 10) { accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap(),
                Groupingx.foldTo(Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap(), 10) { accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap())
    }

    @Test
    fun testReduce() {
        val list = listOf("王五", "张三", "李四", "赵六", "李四", "王五", "张三", "李四", "张三")

        assertTwoEquals(mapOf("王" to "王五王五", "张" to "张三张三张三", "李" to "李四李四李四", "赵" to "赵六"),
                list.groupingBy { it.first().toString() }.reduce { _, accumulator, element ->
                    accumulator + element
                }.toSortedMap(),
                Groupingx.reduce(Groupingx.groupingBy(list) { it.first().toString() }) { _, accumulator, element ->
                    accumulator + element
                }.toSortedMap())

        assertTwoEquals(mapOf("王" to "王五王五", "张" to "张三张三张三", "李" to "李四李四李四", "赵" to "赵六"),
                list.groupingBy { it.first().toString() }.reduceTo(LinkedHashMap()) { _, accumulator, element ->
                    accumulator + element
                }.toSortedMap(),
                Groupingx.reduceTo(Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap()) { _, accumulator, element ->
                    accumulator + element
                }.toSortedMap())
    }

    @Test
    fun testEachCount() {
        val list = listOf("王五", "张三", "李四", "赵六", "李四", "王五", "张三", "李四", "张三")

        assertTwoEquals(mapOf("王" to 2, "张" to 3, "李" to 3, "赵" to 1),
                list.groupingBy { it.first().toString() }.eachCount().toSortedMap(),
                Groupingx.eachCount(Groupingx.groupingBy(list) { it.first().toString() }).toSortedMap())

        assertTwoEquals(mapOf("王" to 2, "张" to 3, "李" to 3, "赵" to 1),
                list.groupingBy { it.first().toString() }.eachCountTo(LinkedHashMap()).toSortedMap(),
                Groupingx.eachCountTo(Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap()).toSortedMap())
    }
}