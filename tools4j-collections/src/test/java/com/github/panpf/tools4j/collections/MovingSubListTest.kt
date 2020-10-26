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

import com.github.panpf.tools4j.test.ktx.assertThrow
import org.junit.Assert.assertEquals
import org.junit.Test

class MovingSubListTest {

    @Test
    fun test() {
        val normalList = listOf(1, 2, 3, 4, 5)
        val movingSubList = MovingSubList(normalList)

        assertEquals(0, movingSubList.size)
        assertThrow(IndexOutOfBoundsException::class) {
            movingSubList[-1]
        }
        assertThrow(IndexOutOfBoundsException::class) {
            movingSubList[0]
        }
        assertThrow(IndexOutOfBoundsException::class) {
            movingSubList[4]
        }

        assertThrow(IndexOutOfBoundsException::class) {
            movingSubList.move(-1, 2)
        }
        assertThrow(IndexOutOfBoundsException::class) {
            movingSubList.move(0, 6)
        }
        assertThrow(IllegalArgumentException::class) {
            movingSubList.move(2, 0)
        }

        movingSubList.move(0, 5)
        assertEquals(5, movingSubList.size)
        assertEquals(1, movingSubList[0])
        assertEquals(5, movingSubList.last())

        movingSubList.move(1, 3)
        assertEquals(2, movingSubList.size)
        assertEquals(2, movingSubList[0])
        assertEquals(3, movingSubList.last())
    }
}

