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
package com.github.panpf.tools4j.sequences

import org.junit.Assert
import kotlin.reflect.KClass

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
fun assertTwoEquals(expected: Any?, actual1: Any?, actual2: Any?) {
    Assert.assertEquals("actual1", expected, actual1)
    Assert.assertEquals("actual2", expected, actual2)
}

fun assertAllNotNull(vararg values: Any?) {
    for ((index, value) in values.withIndex()) {
        if (value == null) {
            Assert.fail("The index is $index value is null")
        }
    }
}

fun assertAllNull(vararg values: Any?) {
    for ((index, value) in values.withIndex()) {
        if (value != null) {
            Assert.fail("The index is $index value is not null")
        }
    }
}

fun assertNoThrow(message: String? = null, block: () -> Unit) {
    try {
        block()
    } catch (ignored: Throwable) {
        ignored.printStackTrace()
        Assert.fail(buildString {
            append("Throw. ")
            append(ignored.toString())
            if (message != null) {
                append("message='$message'")
            }
        })
    }
}

fun assertThrow(message: String? = null, expectedTrowType: KClass<out Throwable>? = null, block: () -> Unit) {
    try {
        block()
        Assert.fail(buildString {
            append("No throw. ")
            if (expectedTrowType != null) {
                if (length > 10) append(", ")
                append("expectedThrow='${expectedTrowType.qualifiedName}'")
            }
            if (message != null) {
                if (length > 10) append(", ")
                append("message='$message'")
            }
        })
    } catch (e: Throwable) {
        if (expectedTrowType != null && e::class != expectedTrowType) {
            Assert.fail(buildString {
                append("Throw type error. ")
                append("expectedThrow='${expectedTrowType.qualifiedName}'")
                append(", actualThrow='$e'")
                if (message != null) {
                    append(", message='$message'")
                }
            })
        }
    }
}

fun assertThrow(expectedTrowType: KClass<out Throwable>? = null, block: () -> Unit) {
    assertThrow(message = null, expectedTrowType, block)
}

fun assertThrow(message: String? = null, block: () -> Unit) {
    assertThrow(message = message, expectedTrowType = null, block)
}

fun assertTwoThrow(message: String? = null, expectedTrowType: KClass<out Throwable>? = null, block: () -> Unit, block1: () -> Unit) {
    assertThrow(message, expectedTrowType, block)
    assertThrow(message, expectedTrowType, block1)
}

fun assertTwoThrow(expectedTrowType: KClass<out Throwable>? = null, block: () -> Unit, block1: () -> Unit) {
    assertThrow(message = null, expectedTrowType, block)
    assertThrow(message = null, expectedTrowType, block1)
}

fun assertTwoThrow(message: String? = null, block: () -> Unit, block1: () -> Unit) {
    assertThrow(message, expectedTrowType = null, block)
    assertThrow(message, expectedTrowType = null, block1)
}