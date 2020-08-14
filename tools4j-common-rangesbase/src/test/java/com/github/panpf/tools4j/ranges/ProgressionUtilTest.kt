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

import org.junit.Assert
import org.junit.Test

class ProgressionUtilTest {

    @Test
    fun testGetProgressionLastElement() {
        Assert.assertEquals(10, ProgressionUtil.getProgressionLastElement(0, 10, 1))
        Assert.assertEquals(10, ProgressionUtil.getProgressionLastElement(0, 11, 2))
        try {
            ProgressionUtil.getProgressionLastElement(0, 10, 0)
            Assert.fail()
        } catch (e: IllegalArgumentException) {
        }

        Assert.assertEquals(10L, ProgressionUtil.getProgressionLastElement(0L, 10L, 1L))
        Assert.assertEquals(10L, ProgressionUtil.getProgressionLastElement(0L, 11L, 2L))
        try {
            ProgressionUtil.getProgressionLastElement(0L, 10L, 0L)
            Assert.fail()
        } catch (e: IllegalArgumentException) {
        }

        Assert.assertEquals(0, ProgressionUtil.getProgressionLastElement(10, 0, -1))
        Assert.assertEquals(1, ProgressionUtil.getProgressionLastElement(11, 0, -2))
        try {
            ProgressionUtil.getProgressionLastElement(10, 0, 0)
            Assert.fail()
        } catch (e: IllegalArgumentException) {
        }

        Assert.assertEquals(0L, ProgressionUtil.getProgressionLastElement(10L, 0L, -1L))
        Assert.assertEquals(1L, ProgressionUtil.getProgressionLastElement(11L, 0L, -2L))
        try {
            ProgressionUtil.getProgressionLastElement(10L, 0L, 0L)
            Assert.fail()
        } catch (e: IllegalArgumentException) {
        }
    }
}