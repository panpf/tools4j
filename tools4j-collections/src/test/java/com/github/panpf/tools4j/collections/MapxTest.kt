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

import com.github.panpf.tools4j.common.Pair
import com.github.panpf.tools4j.common.Transformer
import org.junit.Assert.*
import org.junit.Test
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap

class MapxTest {

    @Test
    fun testBuilder() {
        assertEquals(1, Mapx.builder("1", "111").buildHashMap().size.toLong())
        assertEquals(3, Mapx.builder("1", "111").put("2", "222").put("3", "333").buildHashMap().size.toLong())
        assertEquals("333", Mapx.builder("1", "111").put("2", "222").put("3", "333").buildHashMap()["3"])

        assertNotEquals("1, 3, 2", Mapx.builder("1", "111").put("3", "333").put("2", "222").buildHashMap().map { entry -> entry.key }.joinToString())
        assertNotEquals("1, 3, 2", Mapx.builder("1", "111").put("3", "333").put("2", "222").buildWeakHashMap().map { entry -> entry.key }.joinToString())
        assertNotEquals("1, 3, 2", Mapx.builder("1", "111").put("3", "333").put("2", "222").buildHashtable().map { entry -> entry.key }.joinToString())
        assertEquals("1, 3, 2", Mapx.builder("1", "111").put("3", "333").put("2", "222").buildLinkedHashMap().map { entry -> entry.key }.joinToString())
        assertEquals("1, 2, 3", Mapx.builder("1", "111").put("3", "333").put("2", "222").buildTreeMap().map { entry -> entry.key }.joinToString())
        assertEquals("1, 2, 3", Mapx.builder("3", "333").put("1", "111").put("2", "222").buildTreeMap().map { entry -> entry.key }.joinToString())
        assertEquals("1, 2, 3", Mapx.builder("3", "333").put("1", "111").put("2", "222").buildSortedMap().map { entry -> entry.key }.joinToString())
    }

    @Test
    fun testNullOrEmpty() {
        assertTrue(Mapx.isNullOrEmpty(HashMap<String, String>()))
        assertTrue(Mapx.isNullOrEmpty(null as HashMap<String, String>?))
        assertFalse(Mapx.isNullOrEmpty(HashMap<String, String>().apply { put("key", "value") }))

        assertFalse(Mapx.isNotNullOrEmpty(HashMap<String, String>()))
        assertFalse(Mapx.isNotNullOrEmpty(null as HashMap<String, String>?))
        assertTrue(Mapx.isNotNullOrEmpty(HashMap<String, String>().apply { put("key", "value") }))
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    @Test
    fun testMapOf() {
        Assertx.assertThreeEquals(0, Mapx.immutableMapOf<String, String>().size, mapOf<String, String>().size)
        assertEquals(Collections.EMPTY_MAP::class.java.name, Mapx.immutableMapOf<String, String>()::class.java.name)
        assertEquals(emptyMap<String, String>()::class.java.name, mapOf<String, String>()::class.java.name)
        try {
            Mapx.immutableMapOf<String, String>()["key3"] = "value3"
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals(1, Mapx.immutableMapOf(Pair("key", "value")).size,
                mapOf(kotlin.Pair("key", "value")).size)
        Assertx.assertThreeEquals(Collections.singletonMap("key", "value")::class.java.name,
                Mapx.immutableMapOf(Pair("key", "value"))::class.java.name,
                mapOf(kotlin.Pair("key", "value"))::class.java.name)
        try {
            Mapx.immutableMapOf(Pair("key", "value"))["key3"] = "value3"
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals(2, Mapx.immutableMapOf(Pair("key", "value"), Pair("key2", "value2")).size,
                mapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2")).size)
        Assertx.assertThreeEquals(LinkedHashMap::class.java.name,
                Mapx.immutableMapOf(Pair("key", "value"), Pair("key2", "value2"))::class.java.name,
                mapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2"))::class.java.name)
        Mapx.immutableMapOf(Pair("key", "value"), Pair("key2", "value2"))["key3"] = "value3"
        (mapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2")) as LinkedHashMap)["key3"] = "value3"

        Assertx.assertThreeEquals(0, Mapx.immutableMapOf(*arrayOf<Pair<String, String>>()).size,
                mapOf(*arrayOf<kotlin.Pair<String, String>>()).size)
        assertEquals(Collections.EMPTY_MAP::class.java.name, Mapx.immutableMapOf(*arrayOf<Pair<String, String>>())::class.java.name)
        assertEquals(emptyMap<String, String>()::class.java.name, mapOf(*arrayOf<kotlin.Pair<String, String>>())::class.java.name)
        try {
            Mapx.immutableMapOf(*arrayOf<Pair<String, String>>())["key3"] = "value3"
            fail()
        } catch (e: Exception) {
        }

        try {
            Mapx.immutableMapOf<String, String>(null)
            fail()
        } catch (e: Exception) {
        }


        Assertx.assertThreeEquals(0, Mapx.mutableMapOf<String, String>().size, mutableMapOf<String, String>().size)
        Assertx.assertThreeEquals(LinkedHashMap::class.java.name, Mapx.mutableMapOf<String, String>()::class.java.name, mutableMapOf<String, String>()::class.java.name)
        Assertx.assertThreeEquals(1, Mapx.mutableMapOf<String, String>().apply { put("key3", "value3") }.size, mutableMapOf<String, String>().apply { put("key3", "value3") }.size)

        Assertx.assertThreeEquals(2, Mapx.mutableMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2")).size,
                mutableMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2")).size)
        Assertx.assertThreeEquals(LinkedHashMap::class.java.name, Mapx.mutableMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2"))::class.java.name,
                mutableMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2"))::class.java.name)
        Assertx.assertThreeEquals(3, Mapx.mutableMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2")).apply { put("key3", "value3") }.size,
                mutableMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2")).apply { put("key3", "value3") }.size)
        Assertx.assertThreeEquals(0, Mapx.mutableMapOf<String, String>(*arrayOf<Pair<String, String>>()).size,
                mutableMapOf(*arrayOf<kotlin.Pair<String, String>>()).size)
        Assertx.assertThreeEquals(LinkedHashMap::class.java.name, Mapx.mutableMapOf<String, String>(*arrayOf<Pair<String, String>>())::class.java.name,
                mutableMapOf(*arrayOf<kotlin.Pair<String, String>>())::class.java.name)

        Assertx.assertThreeEquals(0, Mapx.hashMapOf<String, String>().size, hashMapOf<String, String>().size)
        Assertx.assertThreeEquals(java.util.HashMap::class.java.name, Mapx.hashMapOf<String, String>()::class.java.name, hashMapOf<String, String>()::class.java.name)
        Assertx.assertThreeEquals(1, Mapx.hashMapOf<String, String>().apply { put("key3", "value3") }.size, hashMapOf<String, String>().apply { put("key3", "value3") }.size)

        Assertx.assertThreeEquals(2, Mapx.hashMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2")).size,
                hashMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2")).size)
        Assertx.assertThreeEquals(java.util.HashMap::class.java.name, Mapx.hashMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2"))::class.java.name,
                hashMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2"))::class.java.name)
        Assertx.assertThreeEquals(3, Mapx.hashMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2")).apply { put("key3", "value3") }.size,
                hashMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2")).apply { put("key3", "value3") }.size)


        Assertx.assertThreeEquals(0, Mapx.linkedMapOf<String, String>().size, linkedMapOf<String, String>().size)
        Assertx.assertThreeEquals(LinkedHashMap::class.java.name, Mapx.linkedMapOf<String, String>()::class.java.name, linkedMapOf<String, String>()::class.java.name)
        Assertx.assertThreeEquals(1, Mapx.linkedMapOf<String, String>().apply { put("key3", "value3") }.size, linkedMapOf<String, String>().apply { put("key3", "value3") }.size)

        Assertx.assertThreeEquals(2, Mapx.linkedMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2")).size,
                linkedMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2")).size)
        Assertx.assertThreeEquals(LinkedHashMap::class.java.name, Mapx.linkedMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2"))::class.java.name,
                linkedMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2"))::class.java.name)
        Assertx.assertThreeEquals(3, Mapx.linkedMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2")).apply { put("key3", "value3") }.size,
                linkedMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2")).apply { put("key3", "value3") }.size)


        Assertx.assertThreeEquals(2, Mapx.sortedMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2")).size,
                sortedMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2")).size)
        Assertx.assertThreeEquals(TreeMap::class.java.name, Mapx.sortedMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2"))::class.java.name,
                sortedMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2"))::class.java.name)
        Assertx.assertThreeEquals(3, Mapx.sortedMapOf<String, String>(Pair("key", "value"), Pair("key2", "value2")).apply { put("key3", "value3") }.size,
                sortedMapOf(kotlin.Pair("key", "value"), kotlin.Pair("key2", "value2")).apply { put("key3", "value3") }.size)

        Assertx.assertThreeEquals("{key=value, key2=value2}", Mapx.sortedMapOf<String, String>(Pair("key2", "value2"), Pair("key", "value")).toString(),
                sortedMapOf(kotlin.Pair("key2", "value2"), kotlin.Pair("key", "value")).toString())
    }

    @Test
    fun testEmpty() {
        Assertx.assertThreeEquals(true, Mapx.isEmpty(HashMap<String, String>()), HashMap<String, String>().isEmpty())
        Assertx.assertThreeEquals(false, Mapx.isEmpty(HashMap<String, String>().apply { put("key", "value") }), HashMap<String, String>().apply { put("key", "value") }.isEmpty())
        assertEquals(false, Mapx.isEmpty(null as Map<String, String>?))

        Assertx.assertThreeEquals(false, Mapx.isNotEmpty(HashMap<String, String>()), HashMap<String, String>().isNotEmpty())
        Assertx.assertThreeEquals(true, Mapx.isNotEmpty(HashMap<String, String>().apply { put("key", "value") }), HashMap<String, String>().apply { put("key", "value") }.isNotEmpty())
        assertEquals(false, Mapx.isNotEmpty(null as Map<String, String>?))

        val map: LinkedHashMap<String, String>? = LinkedHashMap()
        Assertx.assertThreeEquals(map, Mapx.orEmpty(map), map.orEmpty())
        Assertx.assertThreeEquals(0, Mapx.orEmpty<String, String>(null).size, null.orEmpty<String, String>().size)
        assertEquals(LinkedHashMap::class.java.name, Mapx.orEmpty<String, String>(null)::class.java.name)
        assertEquals(emptyMap<String, String>()::class.java.name, null.orEmpty<String, String>()::class.java.name)
    }

    @Test
    fun testPut() {
        val map = LinkedHashMap<String, String>()

        assertEquals(0, map.size)
        Assertx.assertAllNull(map["key1"], map["key2"])
        Mapx.putAll(map, Arrayx.arrayOf(Pair("key1", "value1"), Pair("key2", "value2")))
        Mapx.putAll(map, null as Array<Pair<String, String>>?)
        assertEquals(2, map.size)
        Assertx.assertAllNotNull(map["key1"], map["key2"])

        assertEquals(2, map.size)
        Assertx.assertAllNull(map["key3"], map["key4"])
        Mapx.putAll(map, Collectionx.immutableListOf(Pair("key3", "value3"), Pair("key4", "value4")))
        Mapx.putAll(map, null as List<Pair<String, String>>?)
        assertEquals(4, map.size)
        Assertx.assertAllNotNull(map["key3"], map["key4"])
    }

    @Test
    fun testPlus() {
        val map = Mapx.builder("key1", "value1").buildHashMap()
        assertEquals(1, map.size)
        assertNotNull(map["key1"])

        val newMap1 = Mapx.plus(map, Pair("key2", "value2"))
        assertEquals(2, newMap1.size)
        Assertx.assertAllNotNull(newMap1["key1"], newMap1["key2"])
        assertTrue(newMap1 !== map)
        assertNull(map["key2"])

        val newMap2 = Mapx.plus(map, Arrayx.arrayOf(Pair("key2", "value2"), Pair("key3", "value3")))
        assertEquals(3, newMap2.size)
        Assertx.assertAllNotNull(newMap2["key1"], newMap2["key2"], newMap2["key3"])
        assertTrue(newMap2 !== map)
        Assertx.assertAllNull(map["key2"], map["key3"])

        val newMap3 = Mapx.plus(map, Collectionx.immutableListOf(Pair("key2", "value2"), Pair("key3", "value3")))
        assertEquals(3, newMap3.size)
        Assertx.assertAllNotNull(newMap3["key1"], newMap3["key2"], newMap3["key3"])
        assertTrue(newMap3 !== map)
        Assertx.assertAllNull(map["key2"], map["key3"])

        val newMap5 = Mapx.plus(map, Mapx.builder("key2", "value2").put("key3", "value3").buildHashMap())
        assertEquals(3, newMap5.size)
        Assertx.assertAllNotNull(newMap5["key1"], newMap5["key2"], newMap5["key3"])
        assertTrue(newMap5 !== map)
        Assertx.assertAllNull(map["key2"], map["key3"])
        assertEquals(1, Mapx.plus(map, null as Map<String, String>?).size)

        assertEquals(1, map.size.toLong())
        Assertx.assertAllNotNull(map["key1"])
        Assertx.assertAllNull(map["key2"])
        Mapx.plusAssign(map, Pair("key2", "value2"))
        Mapx.plusAssign(map, null as Pair<String, String>?)
        assertEquals(2, map.size.toLong())
        Assertx.assertAllNotNull(map["key1"], map["key2"])

        Assertx.assertAllNull(map["key3"], map["key4"])
        Mapx.plusAssign(map, Arrayx.arrayOf(Pair("key3", "value3"), Pair("key4", "value4")))
        assertEquals(4, map.size.toLong())
        Assertx.assertAllNotNull(map["key1"], map["key2"], map["key3"], map["key4"])

        Assertx.assertAllNull(map["key5"], map["key6"])
        Mapx.plusAssign(map, Collectionx.immutableListOf(Pair("key5", "value5"), Pair("key6", "value6")))
        assertEquals(6, map.size.toLong())
        Assertx.assertAllNotNull(map["key1"], map["key2"], map["key3"], map["key4"], map["key5"], map["key6"])

        Assertx.assertAllNull(map["key7"], map["key8"])
        Mapx.plusAssign(map, Mapx.builder("key7", "value7").put("key8", "value8").buildHashMap())
        Mapx.plusAssign(map, null as Map<String, String>?)
        assertEquals(8, map.size.toLong())
        Assertx.assertAllNotNull(map["key1"], map["key2"], map["key3"], map["key4"], map["key5"], map["key6"], map["key7"], map["key8"])
    }

    @Test
    fun testRemove() {
        val map = Mapx.builder("key1", "value1").buildHashMap()
        assertEquals(1, map.size.toLong())
        assertNotNull(map["key1"])

        Mapx.remove(null as Map<String, String>?, "key1")
        assertEquals(1, map.size.toLong())
        assertNotNull(map["key1"])

        Mapx.remove(map, "key1")
        assertEquals(0, map.size.toLong())
        assertNull(map["key1"])
    }

    @Test
    fun testMinus() {
        val map = Mapx.builder("key1", "value1").put("key2", "value2").put("key3", "value3").buildHashMap()
        assertEquals(3, map.size.toLong())
        Assertx.assertAllNotNull(map["key1"], map["key2"], map["key3"])

        val newMap1 = Mapx.minus(map, "key3")
        assertTrue(newMap1 !== map)
        assertEquals(3, map.size.toLong())
        Assertx.assertAllNotNull(map["key1"], map["key2"], map["key3"])
        assertEquals(2, newMap1.size.toLong())
        Assertx.assertAllNotNull(newMap1["key1"], newMap1["key2"])

        val newMap2 = Mapx.minus(map, arrayOf("key2", "key3"))
        assertTrue(newMap2 !== map)
        assertEquals(3, map.size.toLong())
        Assertx.assertAllNotNull(map["key1"], map["key2"], map["key3"])
        assertEquals(1, newMap2.size.toLong())
        Assertx.assertAllNotNull(newMap2["key1"])

        val newMap3 = Mapx.minus(map, listOf("key2", "key3"))
        assertTrue(newMap3 !== map)
        assertEquals(3, map.size.toLong())
        Assertx.assertAllNotNull(map["key1"], map["key2"], map["key3"])
        assertEquals(1, newMap3.size.toLong())
        Assertx.assertAllNotNull(newMap3["key1"])

        val map2 = Mapx.builder("key1", "value1").put("key2", "value2").put("key3", "value3").put("key4", "value4").put("key5", "value5").put("key6", "value6").put("key7", "value7").put("key8", "value8").put("key9", "value9").put("key10", "value10").buildHashMap()
        assertEquals(10, map2.size.toLong())
        Assertx.assertAllNotNull(map2["key1"], map2["key2"], map2["key3"], map2["key4"], map2["key5"], map2["key6"], map2["key7"], map2["key8"], map2["key9"], map2["key10"])

        Mapx.minusAssign(map2, "key10")
        Mapx.minusAssign(null as Map<String, String>?, "key9")
        assertEquals(9, map2.size.toLong())
        Assertx.assertAllNotNull(map2["key1"], map2["key2"], map2["key3"], map2["key4"], map2["key5"], map2["key6"], map2["key7"], map2["key8"], map2["key9"])

        Mapx.minusAssign(map2, arrayOf("key8", "key9"))
        Mapx.minusAssign(null as Map<String, String>?, arrayOf("key6", "key7"))
        assertEquals(7, map2.size.toLong())
        Assertx.assertAllNotNull(map2["key1"], map2["key2"], map2["key3"], map2["key4"], map2["key5"], map2["key6"], map2["key7"])

        Mapx.minusAssign(map2, listOf("key6", "key7"))
        Mapx.minusAssign(null as Map<String, String>?, listOf("key4", "key5"))
        assertEquals(5, map2.size.toLong())
        Assertx.assertAllNotNull(map2["key1"], map2["key2"], map2["key3"], map2["key4"], map2["key5"])
    }

    @Test
    fun testSet() {
        val map = Mapx.builder("key1", "value1").buildHashMap()
        assertEquals(1, map.size.toLong())
        Assertx.assertAllNotNull(map["key1"])
        assertEquals("value1", map["key1"])

        Mapx.set(map, "key1", "value2")
        assertEquals("value2", map["key1"])

        Mapx.set(null as Map<String, String>?, "key1", "value3")
        assertEquals("value2", map["key1"])
    }

    @Test
    fun testGet() {
        val map = Mapx.builder("key1", "value1").buildHashMap()
        assertEquals(1, map.size.toLong())
        Assertx.assertAllNotNull(map["key1"])

        assertEquals("value1", Mapx.get(map, "key1"))
        assertNull(Mapx.get(null as Map<String, String>?, "key1"))

        assertEquals("value1", Mapx.getOrElse(map, "key1") { "valueDefault" })
        assertEquals("valueDefault", Mapx.getOrElse(map, "key2") { "valueDefault" })
        assertEquals("valueDefault", Mapx.getOrElse(null as Map<String, String>?, "key1") { "valueDefault" })

        assertEquals("value1", Mapx.getValue(map, "key1"))
        try {
            Mapx.getValue(map, "key2")
            fail()
        } catch (e: Exception) {
        }
//        val defaultValue: DefaultValue<String> = DefaultValue { "valueDefault" }
//        val method = Classx.getMethodRecursive(Mapx::class.java, "getOrElseNullable", Map::class.java, Object::class.java, DefaultValue::class.java)
//        assertEquals("valueDefault", Classx.callStaticMethod(method, map, "key2", defaultValue))
        try {
            Mapx.getValue(null as Map<String, String>?, "key1")
            fail()
        } catch (e: Exception) {
        }

        Mapx.getOrPut(map, "key1") { "value1" }
        assertEquals(1, map.size.toLong())
        assertNotNull(map["key1"])

        Mapx.getOrPut(map, "key2") { "value2" }
        assertEquals(2, map.size.toLong())
        assertNotNull(map["key2"])
    }

    @Test
    fun testContains() {
        val map = Mapx.builder("key1", "value1").buildHashMap()

        Assertx.assertThreeEquals(true, Mapx.contains(map, "key1"), map.contains("key1"))
        Assertx.assertThreeEquals(false, Mapx.contains(map, "key2"), map.contains("key2"))
        Assertx.assertThreeEquals(false, Mapx.contains(null as Map<String, String>?, "key1"), HashMap<String, String>().contains("key1"))

        Assertx.assertThreeEquals(true, Mapx.containsKey(map, "key1"), map.containsKey("key1"))
        Assertx.assertThreeEquals(false, Mapx.containsKey(map, "key2"), map.containsKey("key2"))
        Assertx.assertThreeEquals(false, Mapx.containsKey(null as Map<String, String>?, "key1"), HashMap<String, String>().containsKey("key1"))

        Assertx.assertThreeEquals(true, Mapx.containsValue(map, "value1"), map.containsValue("value1"))
        Assertx.assertThreeEquals(false, Mapx.containsValue(map, "value2"), map.containsValue("value2"))
        Assertx.assertThreeEquals(false, Mapx.containsValue(null as Map<String, String>?, "value2"), HashMap<String, String>().containsValue("value2"))
    }

    @Test
    fun testAll() {
        val map = Mapx.builder("3", "333").put("1", "111").put("2", "222").buildTreeMap()
        Assertx.assertThreeEquals(true, Mapx.all(map) { isDigit(it.key) }, map.all { isDigit(it.key) })

        val map2 = Mapx.builder("3", "333").put("1", "111").put("a", "222").buildTreeMap()
        Assertx.assertThreeEquals(false, Mapx.all(map2) { isDigit(it.key) }, map2.all { isDigit(it.key) })

        val map3 = mutableMapOf<String, String>()
        Assertx.assertThreeEquals(true, Mapx.all(map3) { isDigit(it.key) }, map3.all { isDigit(it.key) })

        val map4: Map<String, String>? = null
        Assertx.assertThreeEquals(true, Mapx.all(map4) { isDigit(it.key) }, mutableMapOf<String, String>().all { isDigit(it.key) })
    }

    @Test
    fun testAny() {
        val map1 = Mapx.builder("3", "333").put("1", "111").put("2", "222").buildTreeMap()
        Assertx.assertThreeEquals(true, Mapx.any(map1) { isDigit(it.key) }, map1.any { isDigit(it.key) })
        Assertx.assertThreeEquals(true, Mapx.any(map1), map1.any())

        val map2 = Mapx.builder("a", "333").put("b", "111").put("c", "222").buildTreeMap()
        Assertx.assertThreeEquals(false, Mapx.any(map2) { isDigit(it.key) }, map2.any { isDigit(it.key) })

        val map3 = mutableMapOf<String, String>()
        Assertx.assertThreeEquals(false, Mapx.any(map3) { isDigit(it.key) }, map3.any { isDigit(it.key) })
        Assertx.assertThreeEquals(false, Mapx.any(map3), map3.any())

        val map4: Map<String, String>? = null
        Assertx.assertThreeEquals(false, Mapx.any(map4) { isDigit(it.key) }, mutableMapOf<String, String>().any { isDigit(it.key) })
        Assertx.assertThreeEquals(false, Mapx.any(map4), mutableMapOf<String, String>().any())
    }

    @Test
    fun testCount() {
        val map1 = Mapx.builder("3", "333").put("1", "111").put("a", "222").buildTreeMap()
        Assertx.assertThreeEquals(3, Mapx.count(map1), map1.count())
        Assertx.assertThreeEquals(2, Mapx.count(map1) { isDigit(it.key) }, map1.count { isDigit(it.key) })

        val map2 = mutableMapOf<String, String>()
        Assertx.assertThreeEquals(0, Mapx.count(map2), map2.count())
        Assertx.assertThreeEquals(0, Mapx.count(map2) { isDigit(it.key) }, map2.count { isDigit(it.key) })

        val map3: Map<String, String>? = null
        Assertx.assertThreeEquals(0, Mapx.count(map3), mutableMapOf<String, String>().count())
        Assertx.assertThreeEquals(0, Mapx.count(map3) { isDigit(it.key) }, mutableMapOf<String, String>().count { isDigit(it.key) })
    }

    @Test
    fun testEach() {
        val map = Mapx.builder("1", "111").put("2", "222").put("3", "333").buildHashMap()

        Assertx.assertThreeEquals("1, 2, 3",
                ArrayList<String>().apply { Mapx.forEach(map) { entry -> add(entry.key) } }.joinToString(),
                ArrayList<String>().apply { map.forEach { entry -> add(entry.key) } }.joinToString())

        Assertx.assertThreeEquals("",
                ArrayList<String>().apply { Mapx.forEach(null as Map<String, String>?) { entry -> add(entry.key) } }.joinToString(),
                ArrayList<String>().apply { mutableMapOf<String, String>().forEach { entry -> add(entry.key) } }.joinToString())

        Assertx.assertThreeEquals("1, 2, 3",
                ArrayList<String>().apply { Mapx.onEach(map) { entry -> add(entry.key) } }.joinToString(),
                ArrayList<String>().apply { map.onEach { entry -> add(entry.key) } }.joinToString())
    }

    @Test
    fun testMax() {
        val map = Mapx.builder("1", "111").put("2", "222").put("3", "333").buildHashMap()

        Assertx.assertThreeEquals("3=333",
                Mapx.maxBy(map) { it.key }.toString(),
                map.maxBy { it.key }.toString())

        Assertx.assertThreeEquals(null,
                Mapx.maxBy(null as Map<String, String>?) { it.key },
                mutableMapOf<String, String>().maxBy { it.key })

        Assertx.assertThreeEquals("3=333",
                Mapx.maxWith(map) { it1, it2 -> it1.key.compareTo(it2.key) }.toString(),
                map.maxWith(Comparator { it1, it2 -> it1.key.compareTo(it2.key) }).toString())

        Assertx.assertThreeEquals(null,
                Mapx.maxWith(null as Map<String, String>?) { it1, it2 -> it1.key.compareTo(it2.key) },
                mutableMapOf<String, String>().maxWith(Comparator { it1, it2 -> it1.key.compareTo(it2.key) }))
    }

    @Test
    fun testMin() {
        val map = Mapx.builder("1", "111").put("2", "222").put("3", "333").buildHashMap()

        Assertx.assertThreeEquals("1=111",
                Mapx.minBy(map) { it.key }.toString(),
                map.minBy { it.key }.toString())

        Assertx.assertThreeEquals(null,
                Mapx.minBy(null as Map<String, String>?) { it.key },
                mutableMapOf<String, String>().minBy { it.key })

        Assertx.assertThreeEquals("1=111",
                Mapx.minWith(map) { it1, it2 -> it1.key.compareTo(it2.key) }.toString(),
                map.minWith(Comparator { it1, it2 -> it1.key.compareTo(it2.key) }).toString())

        Assertx.assertThreeEquals(null,
                Mapx.minWith(null as Map<String, String>?) { it1, it2 -> it1.key.compareTo(it2.key) },
                mutableMapOf<String, String>().minWith(Comparator { it1, it2 -> it1.key.compareTo(it2.key) }))
    }

    @Test
    fun testNone() {
        val map1 = Mapx.builder("3", "333").put("1", "111").put("2", "222").buildTreeMap()
        Assertx.assertThreeEquals(false, Mapx.none(map1) { isDigit(it.key) }, map1.none { isDigit(it.key) })
        Assertx.assertThreeEquals(false, Mapx.none(map1), map1.none())

        val map2 = Mapx.builder("a", "333").put("b", "111").put("c", "222").buildTreeMap()
        Assertx.assertThreeEquals(true, Mapx.none(map2) { isDigit(it.key) }, map2.none { isDigit(it.key) })

        val map3 = mutableMapOf<String, String>()
        Assertx.assertThreeEquals(true, Mapx.none(map3) { isDigit(it.key) }, map3.none { isDigit(it.key) })
        Assertx.assertThreeEquals(true, Mapx.none(map3), map3.none())

        val map4: Map<String, String>? = null
        Assertx.assertThreeEquals(true, Mapx.none(map4) { isDigit(it.key) }, mutableMapOf<String, String>().none { isDigit(it.key) })
        Assertx.assertThreeEquals(true, Mapx.none(map4), mutableMapOf<String, String>().none())
    }

    @Test
    fun testFilter() {
        val map = Mapx.builder("3", "333").put("1", "111").put("a", "222").buildTreeMap()

        Assertx.assertThreeEquals(2,
                Mapx.filterKeys(map) { isDigit(it) }.size,
                map.filterKeys { isDigit(it) }.size)
        Assertx.assertThreeEquals(true,
                map !== Mapx.filterKeys(map) { isDigit(it) },
                map !== map.filterKeys { isDigit(it) })
        Assertx.assertThreeEquals(0,
                Mapx.filterKeys(null as Map<String, String>?) { isDigit(it) }.size,
                mutableMapOf<String, String>().filterKeys { isDigit(it) }.size)

        val map2 = Mapx.builder("3", "333").put("1", "111").put("a", "aaa").buildTreeMap()

        Assertx.assertThreeEquals(2,
                Mapx.filterValues(map2) { isDigit(it) }.size,
                map2.filterValues { isDigit(it) }.size)
        Assertx.assertThreeEquals(true,
                map2 !== Mapx.filterValues(map2) { isDigit(it) },
                map2 !== map2.filterValues { isDigit(it) })
        Assertx.assertThreeEquals(0,
                Mapx.filterValues(null as Map<String, String>?) { isDigit(it) }.size,
                mutableMapOf<String, String>().filterValues { isDigit(it) }.size)

        Assertx.assertThreeEquals(2,
                Mapx.filter(map) { isDigit(it.key) }.size,
                map.filter { isDigit(it.key) }.size)
        Assertx.assertThreeEquals(true,
                map !== Mapx.filter(map) { isDigit(it.key) },
                map !== map.filter { isDigit(it.key) })
        Assertx.assertThreeEquals(0,
                Mapx.filter(null as Map<String, String>?) { isDigit(it.key) }.size,
                mutableMapOf<String, String>().filter { isDigit(it.key) }.size)

        Assertx.assertThreeEquals(2,
                Mapx.filterTo(map, LinkedHashMap()) { isDigit(it.key) }.size,
                map.filterTo(LinkedHashMap()) { isDigit(it.key) }.size)
        Assertx.assertThreeEquals(true,
                map !== Mapx.filterTo(map, LinkedHashMap()) { isDigit(it.key) },
                map !== map.filterTo(LinkedHashMap()) { isDigit(it.key) })
        Assertx.assertThreeEquals(0,
                Mapx.filterTo(null as Map<String, String>?, LinkedHashMap()) { isDigit(it.key) }.size,
                mutableMapOf<String, String>().filterTo(LinkedHashMap()) { isDigit(it.key) }.size)

        Assertx.assertThreeEquals(2,
                Mapx.filterNot(map) { !isDigit(it.key) }.size,
                map.filterNot { !isDigit(it.key) }.size)
        Assertx.assertThreeEquals(true,
                map !== Mapx.filterNot(map) { !isDigit(it.key) },
                map !== map.filterNot { !isDigit(it.key) })
        Assertx.assertThreeEquals(0,
                Mapx.filterNot(null as Map<String, String>?) { !isDigit(it.key) }.size,
                mutableMapOf<String, String>().filterNot { !isDigit(it.key) }.size)

        Assertx.assertThreeEquals(2,
                Mapx.filterNotTo(map, LinkedHashMap()) { !isDigit(it.key) }.size,
                map.filterNotTo(LinkedHashMap()) { !isDigit(it.key) }.size)
        Assertx.assertThreeEquals(true,
                map !== Mapx.filterNotTo(map, LinkedHashMap()) { !isDigit(it.key) },
                map !== map.filterNotTo(LinkedHashMap()) { !isDigit(it.key) })
        Assertx.assertThreeEquals(0,
                Mapx.filterNotTo(null as Map<String, String>?, LinkedHashMap()) { !isDigit(it.key) }.size,
                mutableMapOf<String, String>().filterNotTo(LinkedHashMap()) { !isDigit(it.key) }.size)
    }

    @Test
    fun testIterator() {
        val map = Mapx.builder("3", "333").put("1", "111").put("a", "222").buildSortedMap()
        val list = LinkedList<Map.Entry<String, String>>()
        for (char in Mapx.iterator(map)) list.add(char)
        assertEquals("1=111, 3=333, a=222", list.joinToString())
    }

    @Test
    fun testToPair() {
        val map = Mapx.builder("3", "333").put("1", "111").put("a", "222").buildSortedMap()
        Assertx.assertThreeEquals("(1, 111)",
                Mapx.toPair(map.entries.first()).toString(),
                map.entries.first().toPair().toString())
    }

    @Test
    fun testToMap() {
        Assertx.assertThreeEquals(mutableMapOf<String, String>(),
                Mapx.toMap(listOf<Pair<String, String>>()),
                listOf<kotlin.Pair<String, String>>().toMap())

        Assertx.assertThreeEquals(Mapx.builder("key1", "value1").buildLinkedHashMap(),
                Mapx.toMap(listOf(Pair("key1", "value1"))),
                listOf(kotlin.Pair("key1", "value1")).toMap())

        Assertx.assertThreeEquals(Mapx.builder("key1", "value1").put("key2", "value2").buildLinkedHashMap(),
                Mapx.toMap(listOf(Pair("key1", "value1"), Pair("key2", "value2"))),
                listOf(kotlin.Pair("key1", "value1"), kotlin.Pair("key2", "value2")).toMap())

        Assertx.assertThreeEquals(Mapx.builder("key1", "value1").put("key2", "value2").buildLinkedHashMap(),
                Mapx.toMap((TestIterable(listOf(Pair("key1", "value1"), Pair("key2", "value2"))))),
                TestIterable(listOf(kotlin.Pair("key1", "value1"), kotlin.Pair("key2", "value2"))).toMap())

        Assertx.assertThreeEquals(Mapx.builder("key1", "value1").put("key2", "value2").buildLinkedHashMap(),
                Mapx.toMap(listOf(Pair("key1", "value1"), Pair("key2", "value2")), LinkedHashMap<String, String>()),
                listOf(kotlin.Pair("key1", "value1"), kotlin.Pair("key2", "value2")).toMap(LinkedHashMap()))

        Assertx.assertThreeEquals(mutableMapOf<String, String>(),
                Mapx.toMap(arrayOf<Pair<String, String>>()),
                arrayOf<kotlin.Pair<String, String>>().toMap())

        Assertx.assertThreeEquals(Mapx.builder("key1", "value1").buildLinkedHashMap(),
                Mapx.toMap(arrayOf(Pair("key1", "value1"))),
                arrayOf(kotlin.Pair("key1", "value1")).toMap())

        Assertx.assertThreeEquals(Mapx.builder("key1", "value1").put("key2", "value2").buildLinkedHashMap(),
                Mapx.toMap(arrayOf(Pair("key1", "value1"), Pair("key2", "value2"))),
                arrayOf(kotlin.Pair("key1", "value1"), kotlin.Pair("key2", "value2")).toMap())

        Assertx.assertThreeEquals(Mapx.builder("key1", "value1").put("key2", "value2").buildLinkedHashMap(),
                Mapx.toMap(arrayOf(Pair("key1", "value1"), Pair("key2", "value2")), LinkedHashMap<String, String>()),
                arrayOf(kotlin.Pair("key1", "value1"), kotlin.Pair("key2", "value2")).toMap(LinkedHashMap()))

        val map = Mapx.builder("3", "333").put("1", "111").put("2", "222").buildTreeMap()
        Assertx.assertThreeEquals(map, Mapx.toMap(map), map.toMap())
        Assertx.assertThreeEquals(true, Mapx.toMap(map) !== map, map.toMap() !== map)
        Assertx.assertThreeEquals(LinkedHashMap<String, String>(), Mapx.toMap(LinkedHashMap<String, String>()), LinkedHashMap<String, String>().toMap())
        Assertx.assertThreeEquals(LinkedHashMap<String, String>(), Mapx.toMap(null as LinkedHashMap<String, String>?), LinkedHashMap<String, String>().toMap())

        Assertx.assertThreeEquals(map, Mapx.toMap(map), map.toMap())
        Assertx.assertThreeEquals(true, Mapx.toMap(map, LinkedHashMap()) !== map, map.toMap(LinkedHashMap()) !== map)
        Assertx.assertThreeEquals(LinkedHashMap<String, String>(),
                Mapx.toMap(LinkedHashMap<String, String>(), LinkedHashMap()),
                LinkedHashMap<String, String>().toMap(LinkedHashMap()))
        Assertx.assertThreeEquals(LinkedHashMap<String, String>(),
                Mapx.toMap(null as LinkedHashMap<String, String>?, LinkedHashMap()),
                LinkedHashMap<String, String>().toMap(LinkedHashMap()))
    }

    @Test
    fun testToList() {
        val map = Mapx.builder("3", "333").put("1", "111").put("2", "222").buildTreeMap()
        Assertx.assertThreeEquals(listOf(kotlin.Pair("1", "111"), kotlin.Pair("2", "222"), kotlin.Pair("3", "333")).toString(),
                Mapx.toList(map).toString(),
                map.toList().toString())

        Assertx.assertThreeEquals(listOf<kotlin.Pair<String, String>>().toString(),
                Mapx.toList(LinkedHashMap<String, String>()).toString(),
                LinkedHashMap<String, String>().toList().toString())

        Assertx.assertThreeEquals(listOf<kotlin.Pair<String, String>>().toString(),
                Mapx.toList(null as LinkedHashMap<String, String>?).toString(),
                LinkedHashMap<String, String>().toList().toString())
    }

    @Test
    fun testAs() {
        val map = Mapx.builder("3", "333").put("1", "111").put("2", "222").buildTreeMap()
        Assertx.assertThreeEquals(listOf(kotlin.Pair("1", "111"), kotlin.Pair("2", "222"), kotlin.Pair("3", "333")).toString(),
                Mapx.asIterable(map).map { kotlin.Pair(it.key, it.value) }.toString(),
                map.asIterable().map { kotlin.Pair(it.key, it.value) }.toString())
    }

    @Test
    fun testMap() {
        val map = Mapx.builder("3", "333").put("1", "111").put("2", "222").buildTreeMap()

        Assertx.assertThreeEquals(Mapx.builder("3", 3).put("1", 3).put("2", 3).buildTreeMap(),
                Mapx.mapValuesTo(map, LinkedHashMap<String, Int>()) { it.value.length },
                map.mapValuesTo(LinkedHashMap<String, Int>()) { it.value.length })

        Assertx.assertThreeEquals(LinkedHashMap<String, Int>(),
                Mapx.mapValuesTo(null as LinkedHashMap<String, String>?, LinkedHashMap<String, Int>()) { it.value.length },
                LinkedHashMap<String, String>().mapValuesTo(LinkedHashMap()) { it.value.length })

        Assertx.assertThreeEquals(Mapx.builder("3", 3).put("1", 3).put("2", 3).buildTreeMap(),
                Mapx.mapValues(map) { it.value.length },
                map.mapValues { it.value.length })

        Assertx.assertThreeEquals(Mapx.builder(333, "333").put(111, "111").put(222, "222").buildTreeMap(),
                Mapx.mapKeysTo(map, LinkedHashMap<Int, String>()) { (it.key + it.key + it.key).toInt() },
                map.mapKeysTo(LinkedHashMap<Int, String>()) { (it.key + it.key + it.key).toInt() })

        Assertx.assertThreeEquals(LinkedHashMap<String, Int>(),
                Mapx.mapKeysTo(null as LinkedHashMap<String, String>?, LinkedHashMap<Int, String>()) { (it.key + it.key + it.key).toInt() },
                LinkedHashMap<String, String>().mapKeysTo(LinkedHashMap()) { (it.key + it.key + it.key).toInt() })

        Assertx.assertThreeEquals(Mapx.builder(333, "333").put(111, "111").put(222, "222").buildTreeMap(),
                Mapx.mapKeys(map) { (it.key + it.key + it.key).toInt() },
                map.mapKeys { (it.key + it.key + it.key).toInt() })

        Assertx.assertThreeEquals("1, 2, 3",
                Mapx.mapTo(map, ArrayList<String>()) { it.key }.joinToString(),
                map.mapTo(ArrayList<String>()) { it.key }.joinToString())

        Assertx.assertThreeEquals("1, 2, 3",
                Mapx.map(map) { it.key }.joinToString(),
                map.map { it.key }.joinToString())

        Assertx.assertThreeEquals("1, 3",
                Mapx.mapNotNullTo(map, ArrayList()) { if (it.key != "2") it.key else null }.joinToString(),
                map.mapNotNullTo(ArrayList()) { if (it.key != "2") it.key else null }.joinToString())

        Assertx.assertThreeEquals("1, 3",
                Mapx.mapNotNull(map) { if (it.key != "2") it.key else null }.joinToString(),
                map.mapNotNull { if (it.key != "2") it.key else null }.joinToString())

        Assertx.assertThreeEquals("1, 1, 1, 2, 2, 2, 3, 3, 3",
                Mapx.flatMapTo(map, ArrayList<String>()) { it.value.toList().map { it.toString() } }.joinToString(),
                map.flatMapTo(ArrayList()) { it.value.toList().map { it.toString() } }.joinToString())

        Assertx.assertThreeEquals("",
                Mapx.flatMapTo(null as LinkedHashMap<String, String>?, ArrayList<String>()) { it.value.toList().map { it.toString() } }.joinToString(),
                LinkedHashMap<String, String>().flatMapTo(ArrayList()) { it.value.toList().map { it.toString() } }.joinToString())

        Assertx.assertThreeEquals("1, 1, 1, 2, 2, 2, 3, 3, 3",
                Mapx.flatMap(map) { it.value.toList().map { it.toString() } }.joinToString(),
                map.flatMap { it.value.toList().map { it.toString() } }.joinToString())
    }

    @Test
    fun testCapacity() {
        assertEquals(3, Mapx.capacity(2).toLong())

        assertEquals((3 + 3 / 3).toLong(), Mapx.capacity(3).toLong())
        assertEquals((101 + 101 / 3).toLong(), Mapx.capacity(101).toLong())
        assertEquals((Mapx.INT_MAX_POWER_OF_TWO - 1 + (Mapx.INT_MAX_POWER_OF_TWO - 1) / 3).toLong(), Mapx.capacity(Mapx.INT_MAX_POWER_OF_TWO - 1).toLong())
        assertEquals(Integer.MAX_VALUE.toLong(), Mapx.capacity(Mapx.INT_MAX_POWER_OF_TWO).toLong())
    }

    private class TestIterable<T>(val list: List<T>) : Iterable<T> {

        override fun iterator(): Iterator<T> = list.iterator()
    } 
    
    /**
     * Return `true` if the given sequence of characters is all digit
     */
    private fun isDigit(sequence: CharSequence?): Boolean {
        if (sequence == null || sequence.isEmpty()) return false
        var index = 0
        val size = sequence.length
        while (index < size) {
            if (!Character.isDigit(sequence[index])) {
                return false
            }
            index++
        }
        return true
    }
}
