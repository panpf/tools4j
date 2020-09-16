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

import com.github.panpf.tools4j.test.ktx.*
import org.junit.Test

class GroupingxTest {

    @Test
    fun testAggregate() {
        val list = listOf("王五", "张三", "李四", "赵六", "李四", "王五", "张三", "李四", "张三")
        assertTwoEquals(
                mapOf("王" to 2, "张" to 3, "李" to 3, "赵" to 1),
                com.github.panpf.tools4j.collections.Groupingx.aggregate(com.github.panpf.tools4j.collections.Groupingx.groupingBy(list) { it.first().toString() }) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap(),
                list.groupingBy { it.first().toString() }.aggregate { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap())

        assertTwoEquals(
                mapOf("王" to 2, "张" to 3, "李" to 3, "赵" to 1),
                com.github.panpf.tools4j.collections.Groupingx.aggregateTo(com.github.panpf.tools4j.collections.Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap()) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap(),
                list.groupingBy { it.first().toString() }.aggregateTo(LinkedHashMap()) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap())

        assertTwoEquals(
                LinkedHashMap<String, Int>(),
                com.github.panpf.tools4j.collections.Groupingx.aggregateTo(null as com.github.panpf.tools4j.collections.Grouping<String, String>?, LinkedHashMap()) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap(),
                listOf<String>().groupingBy { it.first().toString() }.aggregateTo(LinkedHashMap()) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap())

        assertTwoEquals(
                mapOf("王" to 2, "张" to 3, "李" to 3, "赵" to 1),
                com.github.panpf.tools4j.collections.Groupingx.aggregateTo(com.github.panpf.tools4j.collections.Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap<String, Int?>().apply { put("王", null) }) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap(),
                list.groupingBy { it.first().toString() }.aggregateTo(LinkedHashMap<String, Int?>().apply { put("王", null) }) { _, accumulator: Int?, _, _ ->
                    (accumulator ?: 0) + 1
                }.toSortedMap())
    }

    @Test
    fun testFold() {
        val list = listOf("王五", "张三", "李四", "赵六", "李四", "王五", "张三", "李四", "张三")

        assertTwoEquals(
                mapOf("王" to 12, "张" to 13, "李" to 13, "赵" to 11),
                com.github.panpf.tools4j.collections.Groupingx.fold(com.github.panpf.tools4j.collections.Groupingx.groupingBy(list) { it.first().toString() }, { _, _ -> 10 }) { _, accumulator: Int, _ -> accumulator + 1 }.toSortedMap(),
                list.groupingBy { it.first().toString() }.fold({ _, _ -> 10 }) { _, accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap())

        assertTwoEquals(
                mapOf("王" to 12, "张" to 13, "李" to 13, "赵" to 11),
                com.github.panpf.tools4j.collections.Groupingx.fold(com.github.panpf.tools4j.collections.Groupingx.groupingBy(list) { it.first().toString() }, 10) { accumulator: Int, _ -> accumulator + 1 }.toSortedMap(),
                list.groupingBy { it.first().toString() }.fold(10) { accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap())

        assertTwoEquals(
                mapOf("王" to 12, "张" to 13, "李" to 13, "赵" to 11),
                com.github.panpf.tools4j.collections.Groupingx.foldTo(com.github.panpf.tools4j.collections.Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap(), { _, _ -> 10 }) { _, accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap(),
                list.groupingBy { it.first().toString() }.foldTo(LinkedHashMap(), { _, _ -> 10 }) { _, accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap())

        assertTwoEquals(
                mapOf("王" to 12, "张" to 13, "李" to 13, "赵" to 11),
                com.github.panpf.tools4j.collections.Groupingx.foldTo(com.github.panpf.tools4j.collections.Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap(), 10) { accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap(),
                list.groupingBy { it.first().toString() }.foldTo(LinkedHashMap(), 10) { accumulator: Int, _ ->
                    accumulator + 1
                }.toSortedMap())
    }

    @Test
    fun testReduce() {
        val list = listOf("王五", "张三", "李四", "赵六", "李四", "王五", "张三", "李四", "张三")

        assertTwoEquals(
                mapOf("王" to "王五王五", "张" to "张三张三张三", "李" to "李四李四李四", "赵" to "赵六"),
                com.github.panpf.tools4j.collections.Groupingx.reduce(com.github.panpf.tools4j.collections.Groupingx.groupingBy(list) { it.first().toString() }) { _, accumulator, element -> accumulator + element }.toSortedMap(),
                list.groupingBy { it.first().toString() }.reduce { _, accumulator, element -> accumulator + element }.toSortedMap())

        assertTwoEquals(
                mapOf("王" to "王五王五", "张" to "张三张三张三", "李" to "李四李四李四", "赵" to "赵六"),
                com.github.panpf.tools4j.collections.Groupingx.reduceTo(com.github.panpf.tools4j.collections.Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap()) { _, accumulator, element -> accumulator + element }.toSortedMap(),
                list.groupingBy { it.first().toString() }.reduceTo(LinkedHashMap()) { _, accumulator, element -> accumulator + element }.toSortedMap())
    }

    @Test
    fun testEachCount() {
        val list = listOf("王五", "张三", "李四", "赵六", "李四", "王五", "张三", "李四", "张三")

        assertTwoEquals(
                mapOf("王" to 2, "张" to 3, "李" to 3, "赵" to 1),
                com.github.panpf.tools4j.collections.Groupingx.eachCount(com.github.panpf.tools4j.collections.Groupingx.groupingBy(list) { it.first().toString() }).toSortedMap(),
                list.groupingBy { it.first().toString() }.eachCount().toSortedMap())

        assertTwoEquals(
                mapOf("王" to 2, "张" to 3, "李" to 3, "赵" to 1),
                com.github.panpf.tools4j.collections.Groupingx.eachCountTo(com.github.panpf.tools4j.collections.Groupingx.groupingBy(list) { it.first().toString() }, LinkedHashMap()).toSortedMap(),
                list.groupingBy { it.first().toString() }.eachCountTo(LinkedHashMap()).toSortedMap())
    }

    @Test
    fun testGroupingString() {
        val source = "1365268945336807532324589"
        val map2 = sortedMapOf("0" to listOf("0")
                , "1" to listOf("1")
                , "2" to listOf("2", "2", "2")
                , "3" to listOf("3", "3", "3", "3", "3")
                , "4" to listOf("4", "4")
                , "5" to listOf("5", "5", "5", "5")
                , "6" to listOf("6", "6", "6")
                , "7" to listOf("7")
                , "8" to listOf("8", "8", "8")
                , "9" to listOf("9", "9"))

        val destination = java.util.LinkedHashMap<String, MutableList<String>>()
        val grouping = com.github.panpf.tools4j.collections.Groupingx.groupingBy(source) { it.toString() }
        for (element in grouping.sourceIterator()) {
            val key = grouping.keyOf(element)
            val list = destination.getOrPut(key) { ArrayList() }
            list.add(element.toString())
        }
        assertTwoEquals(map2, destination, source.groupByTo(java.util.LinkedHashMap(), { it.toString() }) { it.toString() }.toSortedMap())
    }
}