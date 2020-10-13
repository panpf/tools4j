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

package com.github.panpf.tools4j.test.ktx

import com.github.panpf.tools4j.test.ktx.*
import org.junit.Assert
import org.junit.Test

class AssertxTest {

    @Test
    fun testAssertTwoEquals() {
        try {
            assertTwoEquals("testAssertTwoEquals", "1", "1", "1")
        } catch (e: AssertionError) {
            Assert.fail()
        }

        try {
            assertTwoEquals("testAssertTwoEquals", "1", "0", "1")
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertTwoEquals("testAssertTwoEquals", "1", "1", "0")
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertTwoEquals("1", "1", "1")
        } catch (e: AssertionError) {
            Assert.fail()
        }

        try {
            assertTwoEquals("1", "0", "1")
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertTwoEquals("1", "1", "0")
            Assert.fail()
        } catch (e: AssertionError) {
        }
    }

    @Test
    fun testAssertAllNull() {
        try {
            assertAllNull(null, null, null)
        } catch (e: AssertionError) {
            Assert.fail()
        }

        try {
            assertAllNull("", null, null)
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertAllNull(null, "", null)
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertAllNull(null, null, "")
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertAllNull("", "", "")
            Assert.fail()
        } catch (e: AssertionError) {
        }
    }

    @Test
    fun testAssertAllNotNull() {

        try {
            assertAllNotNull(null, null, null)
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertAllNotNull("", null, null)
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertAllNotNull(null, "", null)
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertAllNotNull(null, null, "")
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertAllNotNull("", "", "")
        } catch (e: AssertionError) {
            Assert.fail()
        }
    }

    @Test
    fun testAssertThrow() {
        try {
            assertThrow("testAssertThrow", IllegalStateException::class) {
                throw IllegalStateException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertThrow("testAssertThrow", IllegalStateException::class) {
                throw IllegalArgumentException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertThrow("testAssertThrow", IllegalStateException::class) {}
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertThrow(IllegalStateException::class) {
                throw IllegalStateException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertThrow(IllegalStateException::class) {
                throw IllegalArgumentException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertThrow(IllegalStateException::class) {}
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertThrow("testAssertThrow") {
                throw IllegalStateException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertThrow("testAssertThrow") {
                throw IllegalArgumentException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertThrow("testAssertThrow") {}
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertThrow {
                throw IllegalStateException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertThrow {
                throw IllegalArgumentException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertThrow {}
            Assert.fail()
        } catch (e: AssertionError) {
        }
    }

    @Test
    fun testAssertNoThrow() {
        try {
            assertNoThrow("testAssertThrow") {
                throw IllegalStateException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertNoThrow("testAssertThrow") {
                throw IllegalArgumentException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertNoThrow("testAssertThrow") {}
        } catch (e: AssertionError) {
            Assert.fail()
        }

        try {
            assertNoThrow {
                throw IllegalStateException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertNoThrow {
                throw IllegalArgumentException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertNoThrow {}
        } catch (e: AssertionError) {
            Assert.fail()
        }
    }

    @Test
    fun testAssertTwoThrow() {
        try {
            assertTwoThrow("testAssertThrow", IllegalStateException::class,
                    { throw IllegalStateException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertTwoThrow("testAssertThrow", IllegalStateException::class,
                    { throw IllegalStateException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow("testAssertThrow", IllegalStateException::class,
                    { throw IllegalArgumentException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow("testAssertThrow", IllegalStateException::class,
                    { throw IllegalArgumentException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow("testAssertThrow", IllegalStateException::class,
                    { throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow("testAssertThrow", IllegalStateException::class,
                    { }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow("testAssertThrow", IllegalStateException::class, {}, {})
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            assertTwoThrow(IllegalStateException::class,
                    { throw IllegalStateException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertTwoThrow(IllegalStateException::class,
                    { throw IllegalStateException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow(IllegalStateException::class,
                    { throw IllegalArgumentException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow(IllegalStateException::class,
                    { throw IllegalArgumentException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow(IllegalStateException::class,
                    { throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow(IllegalStateException::class,
                    { }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow(IllegalStateException::class, {}, {})
            Assert.fail()
        } catch (e: AssertionError) {
        }


        try {
            assertTwoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertTwoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { throw IllegalArgumentException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertTwoThrow("testAssertThrow",
                    { throw IllegalArgumentException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertTwoThrow("testAssertThrow",
                    { throw IllegalArgumentException() }, { throw IllegalArgumentException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertTwoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow("testAssertThrow",
                    { }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow("testAssertThrow", {}, {})
            Assert.fail()
        } catch (e: AssertionError) {
        }


        try {
            assertTwoThrow({ throw IllegalStateException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertTwoThrow({ throw IllegalStateException() }, { throw IllegalArgumentException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertTwoThrow({ throw IllegalArgumentException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertTwoThrow({ throw IllegalArgumentException() }, { throw IllegalArgumentException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            assertTwoThrow({ throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow({ }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoThrow({}, {})
            Assert.fail()
        } catch (e: AssertionError) {
        }
    }

    @Test
    fun testAssertTwoNoThrow() {
        try {
            assertTwoNoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow("testAssertThrow",
                    { throw IllegalArgumentException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow("testAssertThrow",
                    { throw IllegalArgumentException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow("testAssertThrow",
                    { }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow("testAssertThrow", {}, {})
        } catch (e: AssertionError) {
            Assert.fail()
        }


        try {
            assertTwoNoThrow({ throw IllegalStateException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow({ throw IllegalStateException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow({ throw IllegalArgumentException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow({ throw IllegalArgumentException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow({ throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow({ }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            assertTwoNoThrow({}, {})
        } catch (e: AssertionError) {
            Assert.fail()
        }
    }
}