/*
 * Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>
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
package com.github.panpf.tools4j.grouping

import org.junit.Assert

class Assertx {
    companion object{
        /**
         * Asserts that two objects are equal. If they are not, an
         * [AssertionError] without a message is thrown. If
         * `expected` and `actual` are `null`,
         * they are considered equal.
         *
         * @param expected expected value
         * @param actual1  the value to check against `expected`
         * @param actual2  the value to check against `expected`
         */
        @JvmStatic
        fun assertThreeEquals(expected: Any?, actual1: Any?, actual2: Any?) {
            Assert.assertEquals("actual1", expected, actual1)
            Assert.assertEquals("actual2", expected, actual2)
        }

        @JvmStatic
        fun assertAllNotNull(vararg values: Any?) {
            var index = 0
            for (value in values) {
                if (value == null) {
                    Assert.fail("The index is $index value is null")
                }
                index++
            }
        }

        @JvmStatic
        fun assertAllNull(vararg values: Any?) {
            var index = 0
            for (value in values) {
                if (value != null) {
                    Assert.fail("The index is $index value is not null")
                }
                index++
            }
        }
    }
}