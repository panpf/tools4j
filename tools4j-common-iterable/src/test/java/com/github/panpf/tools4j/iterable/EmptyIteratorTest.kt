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

package com.github.panpf.tools4j.iterable

import org.junit.Assert
import org.junit.Test

class EmptyIteratorTest {

    @Test
    fun testNormal() {
        Assert.assertEquals("", EmptyIterator.INSTANCE.asSequence().joinToString { it.toString() })

        try {
            EmptyIterator.INSTANCE.next()
        } catch (e: Exception) {
            Assert.fail()
        }
    }

    @Test
    fun testRemove() {
        try {
            EmptyIterator.INSTANCE.remove()
        } catch (e: Exception) {
            Assert.fail()
        }
    }
}