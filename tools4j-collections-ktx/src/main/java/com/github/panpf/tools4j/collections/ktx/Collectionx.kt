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

package com.github.panpf.tools4j.collections.ktx

import com.github.panpf.tools4j.common.Transformer
import com.github.panpf.tools4j.collections.Collectionx
import java.util.*


/*
 * Collection related extension methods or properties
 */


/* ******************************************* null or empty ******************************************* */


/**
 * Returns `true` if the collection is empty (contains no elements), `false` otherwise.
 */
inline fun <T> Collection<T>?.isNullOrEmpty(): Boolean = Collectionx.isNullOrEmpty(this)

/**
 * Returns `true` if the collection is not empty.
 */
inline fun <T> Collection<T>?.isNotNullOrEmpty(): Boolean = Collectionx.isNotNullOrEmpty(this)


/* ******************************************* joinToArrayString ******************************************* */

/**
 * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
 */
inline fun <T> Iterable<T>?.joinToArrayString(transform: Transformer<T, CharSequence>): String = Collectionx.joinToArrayString(this, transform)

/**
 * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
 */
inline fun <T> Iterable<T>?.joinToArrayString(): String = Collectionx.joinToArrayString(this)


/* ******************************************* listOf ******************************************* */

/**
 * Returns a new readable and writable linked list of given elements
 */
inline fun <T> linkedListOf(vararg elements: T): LinkedList<T> = Collectionx.linkedListOf(*elements)