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

package com.github.panpf.tools4j.collections.ktx

import com.github.panpf.tools4j.common.Transformer
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class CollectionxTest {

    @Test
    fun testNullOrEmpty() {
        assertTrue((null as Collection<String>?).isNullOrEmpty())
        assertTrue((ArrayList<String>()).isNullOrEmpty())
        assertFalse(arrayListOf("1").isNullOrEmpty())

        assertTrue(arrayListOf("1").isNotNullOrEmpty())
        assertFalse((null as Collection<String>?).isNotNullOrEmpty())
        assertFalse(ArrayList<String>().isNotNullOrEmpty())
    }

    @Test
    fun testJoinToArrayString() {
        assertEquals("[key4, key3, key2]", arrayListOf("4", "3", "2").joinToArrayString(Transformer { "key$it" }))
        assertEquals("[4, 3, 2]", arrayListOf("4", "3", "2").joinToArrayString())
    }

    @Test
    fun testLinkedListOf() {
        assertEquals("[4, 3, 2]", linkedListOf("4", "3", "2").joinToArrayString())
        assertEquals("[]", linkedListOf<String>().joinToArrayString())
    }
}