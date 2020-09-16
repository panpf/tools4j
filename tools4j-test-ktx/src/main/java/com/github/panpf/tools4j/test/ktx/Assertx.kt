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

import com.github.panpf.tools4j.test.Assertx
import kotlin.reflect.KClass


fun assertTwoEquals(expected: Any?, actual1: Any?, actual2: Any?) = Assertx.assertTwoEquals(expected, actual1, actual2)

fun assertAllNotNull(vararg values: Any?) = Assertx.assertAllNotNull(*values)

fun assertAllNull(vararg values: Any?) = Assertx.assertAllNull(*values)

fun assertNoThrow(message: String? = null, block: () -> Unit) = Assertx.assertNoThrow(message, block)

fun assertNoThrow(block: () -> Unit) = Assertx.assertNoThrow(block)

fun assertThrow(message: String? = null, expectedTrowType: KClass<out Throwable>? = null, block: () -> Unit) = Assertx.assertThrow(message, expectedTrowType?.java, block)

fun assertThrow(expectedTrowType: KClass<out Throwable>? = null, block: () -> Unit) = Assertx.assertThrow(expectedTrowType?.java, block)

fun assertThrow(message: String? = null, block: () -> Unit) = Assertx.assertThrow(message, block)

fun assertThrow(block: () -> Unit) = Assertx.assertThrow(block)

fun assertTwoThrow(message: String? = null, expectedTrowType: KClass<out Throwable>? = null, block0: () -> Unit, block1: () -> Unit) = Assertx.assertTwoThrow(message, expectedTrowType?.java, block0, block1)

fun assertTwoThrow(expectedTrowType: KClass<out Throwable>? = null, block0: () -> Unit, block1: () -> Unit) = Assertx.assertTwoThrow(expectedTrowType?.java, block0, block1)

fun assertTwoThrow(message: String? = null, block0: () -> Unit, block1: () -> Unit) = Assertx.assertTwoThrow(message, block0, block1)

fun assertTwoThrow(block0: () -> Unit, block1: () -> Unit) = Assertx.assertTwoThrow(block0, block1)