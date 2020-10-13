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
@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4j.test.ktx

import com.github.panpf.tools4j.test.Assertx
import kotlin.reflect.KClass


inline fun assertTwoEquals(message: String?, expected: Any?, actual1: Any?, actual2: Any?) =
        Assertx.assertTwoEquals(message, expected, actual1, actual2)

inline fun assertTwoEquals(expected: Any?, actual1: Any?, actual2: Any?) =
        Assertx.assertTwoEquals(expected, actual1, actual2)


inline fun assertAllNull(vararg values: Any?) = Assertx.assertAllNull(*values)

inline fun assertAllNotNull(vararg values: Any?) = Assertx.assertAllNotNull(*values)


inline fun assertThrow(message: String? = null, expectedTrowType: KClass<out Throwable>? = null, block: Runnable) =
        Assertx.assertThrow(message, expectedTrowType?.java, block)

inline fun assertThrow(expectedTrowType: KClass<out Throwable>? = null, block: Runnable) =
        Assertx.assertThrow(expectedTrowType?.java, block)

inline fun assertThrow(message: String? = null, block: Runnable) = Assertx.assertThrow(message, block)

inline fun assertThrow(block: Runnable) = Assertx.assertThrow(block)


inline fun assertNoThrow(message: String? = null, block: Runnable) = Assertx.assertNoThrow(message, block)

inline fun assertNoThrow(block: Runnable) = Assertx.assertNoThrow(block)


inline fun assertTwoThrow(
        message: String? = null, expectedTrowType: KClass<out Throwable>? = null, block0: Runnable, block1: Runnable) =
        Assertx.assertTwoThrow(message, expectedTrowType?.java, block0, block1)

inline fun assertTwoThrow(expectedTrowType: KClass<out Throwable>? = null, block0: Runnable, block1: Runnable) =
        Assertx.assertTwoThrow(expectedTrowType?.java, block0, block1)

inline fun assertTwoThrow(message: String? = null, block0: Runnable, block1: Runnable) =
        Assertx.assertTwoThrow(message, block0, block1)

inline fun assertTwoThrow(block0: Runnable, block1: Runnable) = Assertx.assertTwoThrow(block0, block1)


inline fun assertTwoNoThrow(message: String?, block0: Runnable, block1: Runnable) =
        Assertx.assertTwoNoThrow(message, block0, block1)

inline fun assertTwoNoThrow(block0: Runnable, block1: Runnable) = Assertx.assertTwoNoThrow(block0, block1)