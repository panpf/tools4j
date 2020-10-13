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

package com.github.panpf.tools4j.ranges

import com.github.panpf.tools4j.test.ktx.assertThrow
import org.junit.Assert.assertEquals
import org.junit.Test

class ProgressionUtilTest {

    @Test
    fun testIntGetProgressionLastElement() {
        assertEquals(11, ProgressionUtil.getProgressionLastElement(0, 11, 1))
        assertEquals(11, ProgressionUtil.getProgressionLastElement(0, 11, -1))
        assertEquals(10, ProgressionUtil.getProgressionLastElement(0, 11, 2))
        assertEquals(12, ProgressionUtil.getProgressionLastElement(0, 11, -2))
        assertThrow(IllegalArgumentException::class) {
            ProgressionUtil.getProgressionLastElement(0, 11, 0)
        }

        assertEquals(0, ProgressionUtil.getProgressionLastElement(11, 0, 1))
        assertEquals(0, ProgressionUtil.getProgressionLastElement(11, 0, -1))
        assertEquals(-1, ProgressionUtil.getProgressionLastElement(11, 0, 2))
        assertEquals(1, ProgressionUtil.getProgressionLastElement(11, 0, -2))
        assertThrow(IllegalArgumentException::class) {
            ProgressionUtil.getProgressionLastElement(11, 0, 0)
        }

        assertEquals(-11, ProgressionUtil.getProgressionLastElement(0, -11, 1))
        assertEquals(-11, ProgressionUtil.getProgressionLastElement(0, -11, -1))
        assertEquals(-12, ProgressionUtil.getProgressionLastElement(0, -11, 2))
        assertEquals(-10, ProgressionUtil.getProgressionLastElement(0, -11, -2))
        assertThrow(IllegalArgumentException::class) {
            ProgressionUtil.getProgressionLastElement(0, -11, 0)
        }

        assertEquals(0, ProgressionUtil.getProgressionLastElement(-11, 0, 1))
        assertEquals(0, ProgressionUtil.getProgressionLastElement(-11, 0, -1))
        assertEquals(-1, ProgressionUtil.getProgressionLastElement(-11, 0, 2))
        assertEquals(1, ProgressionUtil.getProgressionLastElement(-11, 0, -2))
        assertThrow(IllegalArgumentException::class) {
            ProgressionUtil.getProgressionLastElement(-11, 0, 0)
        }
    }

    @Test
    fun testLongGetProgressionLastElement() {
        assertEquals(11L, ProgressionUtil.getProgressionLastElement(0L, 11L, 1L))
        assertEquals(11L, ProgressionUtil.getProgressionLastElement(0L, 11L, -1L))
        assertEquals(10L, ProgressionUtil.getProgressionLastElement(0L, 11L, 2L))
        assertEquals(12, ProgressionUtil.getProgressionLastElement(0L, 11L, -2L))
        assertThrow(IllegalArgumentException::class) {
            ProgressionUtil.getProgressionLastElement(0L, 11L, 0L)
        }

        assertEquals(0L, ProgressionUtil.getProgressionLastElement(11L, 0L, 1L))
        assertEquals(0L, ProgressionUtil.getProgressionLastElement(11L, 0L, -1L))
        assertEquals(-1L, ProgressionUtil.getProgressionLastElement(11L, 0L, 2L))
        assertEquals(1L, ProgressionUtil.getProgressionLastElement(11L, 0L, -2L))
        assertThrow(IllegalArgumentException::class) {
            ProgressionUtil.getProgressionLastElement(11L, 0L, 0L)
        }

        assertEquals(-11L, ProgressionUtil.getProgressionLastElement(0L, -11L, 1L))
        assertEquals(-11L, ProgressionUtil.getProgressionLastElement(0L, -11L, -1L))
        assertEquals(-12, ProgressionUtil.getProgressionLastElement(0L, -11L, 2L))
        assertEquals(-10L, ProgressionUtil.getProgressionLastElement(0L, -11L, -2L))
        assertThrow(IllegalArgumentException::class) {
            ProgressionUtil.getProgressionLastElement(0L, -11L, 0L)
        }

        assertEquals(0L, ProgressionUtil.getProgressionLastElement(-11L, 0L, 1L))
        assertEquals(0L, ProgressionUtil.getProgressionLastElement(-11L, 0L, -1L))
        assertEquals(-1L, ProgressionUtil.getProgressionLastElement(-11L, 0L, 2L))
        assertEquals(1L, ProgressionUtil.getProgressionLastElement(-11L, 0L, -2L))
        assertThrow(IllegalArgumentException::class) {
            ProgressionUtil.getProgressionLastElement(-11L, 0L, 0L)
        }
    }
}