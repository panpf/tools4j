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

package com.github.panpf.tools4j.test

import org.junit.Assert
import org.junit.Test

class AssertxTest {

    @Test
    fun testAssertTwoEquals() {
        try {
            Assertx.assertTwoEquals("testAssertTwoEquals", "1", "1", "1")
        } catch (e: AssertionError) {
            Assert.fail()
        }

        try {
            Assertx.assertTwoEquals("testAssertTwoEquals", "1", "0", "1")
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertTwoEquals("testAssertTwoEquals", "1", "1", "0")
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertTwoEquals("1", "1", "1")
        } catch (e: AssertionError) {
            Assert.fail()
        }

        try {
            Assertx.assertTwoEquals("1", "0", "1")
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertTwoEquals("1", "1", "0")
            Assert.fail()
        } catch (e: AssertionError) {
        }
    }

    @Test
    fun testAssertAllNull() {
        try {
            Assertx.assertAllNull(null, null, null)
        } catch (e: AssertionError) {
            Assert.fail()
        }

        try {
            Assertx.assertAllNull("", null, null)
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertAllNull(null, "", null)
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertAllNull(null, null, "")
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertAllNull("", "", "")
            Assert.fail()
        } catch (e: AssertionError) {
        }
    }

    @Test
    fun testAssertAllNotNull() {

        try {
            Assertx.assertAllNotNull(null, null, null)
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertAllNotNull("", null, null)
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertAllNotNull(null, "", null)
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertAllNotNull(null, null, "")
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertAllNotNull("", "", "")
        } catch (e: AssertionError) {
            Assert.fail()
        }
    }

    @Test
    fun testAssertThrow() {
        try {
            Assertx.assertThrow("testAssertThrow", IllegalStateException::class.java) {
                throw IllegalStateException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertThrow("testAssertThrow", IllegalStateException::class.java) {
                throw IllegalArgumentException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertThrow("testAssertThrow", IllegalStateException::class.java) {}
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertThrow(IllegalStateException::class.java) {
                throw IllegalStateException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertThrow(IllegalStateException::class.java) {
                throw IllegalArgumentException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertThrow(IllegalStateException::class.java) {}
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertThrow("testAssertThrow") {
                throw IllegalStateException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertThrow("testAssertThrow") {
                throw IllegalArgumentException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertThrow("testAssertThrow") {}
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertThrow {
                throw IllegalStateException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertThrow {
                throw IllegalArgumentException()
            }
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertThrow {}
            Assert.fail()
        } catch (e: AssertionError) {
        }
    }

    @Test
    fun testAssertNoThrow() {
        try {
            Assertx.assertNoThrow("testAssertThrow") {
                throw IllegalStateException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertNoThrow("testAssertThrow") {
                throw IllegalArgumentException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertNoThrow("testAssertThrow") {}
        } catch (e: AssertionError) {
            Assert.fail()
        }

        try {
            Assertx.assertNoThrow {
                throw IllegalStateException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertNoThrow {
                throw IllegalArgumentException()
            }
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertNoThrow {}
        } catch (e: AssertionError) {
            Assert.fail()
        }
    }

    @Test
    fun testAssertTwoThrow() {
        try {
            Assertx.assertTwoThrow("testAssertThrow", IllegalStateException::class.java,
                    { throw IllegalStateException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow", IllegalStateException::class.java,
                    { throw IllegalStateException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow", IllegalStateException::class.java,
                    { throw IllegalArgumentException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow", IllegalStateException::class.java,
                    { throw IllegalArgumentException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow", IllegalStateException::class.java,
                    { throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow", IllegalStateException::class.java,
                    { }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow", IllegalStateException::class.java, {}, {})
            Assert.fail()
        } catch (e: AssertionError) {
        }

        try {
            Assertx.assertTwoThrow(IllegalStateException::class.java,
                    { throw IllegalStateException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertTwoThrow(IllegalStateException::class.java,
                    { throw IllegalStateException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow(IllegalStateException::class.java,
                    { throw IllegalArgumentException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow(IllegalStateException::class.java,
                    { throw IllegalArgumentException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow(IllegalStateException::class.java,
                    { throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow(IllegalStateException::class.java,
                    { }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow(IllegalStateException::class.java, {}, {})
            Assert.fail()
        } catch (e: AssertionError) {
        }


        try {
            Assertx.assertTwoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { throw IllegalArgumentException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow",
                    { throw IllegalArgumentException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow",
                    { throw IllegalArgumentException() }, { throw IllegalArgumentException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow",
                    { }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow("testAssertThrow", {}, {})
            Assert.fail()
        } catch (e: AssertionError) {
        }


        try {
            Assertx.assertTwoThrow({ throw IllegalStateException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertTwoThrow({ throw IllegalStateException() }, { throw IllegalArgumentException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertTwoThrow({ throw IllegalArgumentException() }, { throw IllegalStateException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertTwoThrow({ throw IllegalArgumentException() }, { throw IllegalArgumentException() })
        } catch (e: AssertionError) {
            Assert.fail()
        }
        try {
            Assertx.assertTwoThrow({ throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow({ }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoThrow({}, {})
            Assert.fail()
        } catch (e: AssertionError) {
        }
    }

    @Test
    fun testAssertTwoNoThrow() {
        try {
            Assertx.assertTwoNoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow("testAssertThrow",
                    { throw IllegalArgumentException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow("testAssertThrow",
                    { throw IllegalArgumentException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow("testAssertThrow",
                    { throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow("testAssertThrow",
                    { }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow("testAssertThrow", {}, {})
        } catch (e: AssertionError) {
            Assert.fail()
        }


        try {
            Assertx.assertTwoNoThrow({ throw IllegalStateException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow({ throw IllegalStateException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow({ throw IllegalArgumentException() }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow({ throw IllegalArgumentException() }, { throw IllegalArgumentException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow({ throw IllegalStateException() }, { })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow({ }, { throw IllegalStateException() })
            Assert.fail()
        } catch (e: AssertionError) {
        }
        try {
            Assertx.assertTwoNoThrow({}, {})
        } catch (e: AssertionError) {
            Assert.fail()
        }
    }
}