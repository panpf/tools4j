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

package com.github.panpf.tools4j.common

import org.junit.Assert
import org.junit.Test
import java.util.*

class PairTest {

    @Test
    fun test() {
        val pair1 = Pair(2, "6")
        Assert.assertEquals(2, pair1.first)
        Assert.assertEquals("6", pair1.second)
        Assert.assertEquals("(2, 6)", pair1.toString())

        val pair2 = Pair.of(99, "33")
        Assert.assertEquals(99, pair2.first)
        Assert.assertEquals("33", pair2.second)
        Assert.assertEquals("(99, 33)", pair2.toString())

        val pair3 = Pair(2, "6")
        Assert.assertTrue(pair1 == pair3)
        Assert.assertTrue(pair1 == pair1)
        Assert.assertFalse(pair1 == pair2)
        @Suppress("SENSELESS_COMPARISON")
        Assert.assertFalse(pair1 == null)
        Assert.assertFalse(pair1 == Date())

        val pair4 = Pair(100, "534")
        Assert.assertEquals(Objects.hash(pair4.first, pair4.second), pair4.hashCode())
    }
}