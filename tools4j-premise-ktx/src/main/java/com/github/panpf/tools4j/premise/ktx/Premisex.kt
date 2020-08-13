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

package com.github.panpf.tools4j.premise.ktx

import com.github.panpf.tools4j.premise.LazyValue
import com.github.panpf.tools4j.premise.Premisex


/*
 * Premise extension methods or properties
 */


/* ******************************************* Expression *******************************************/


/**
 * Throws an [IllegalArgumentException] with the result of calling [lazyMessage] if the [this] is false.
 */
inline fun Boolean?.require(lazyMessage: LazyValue<String>) = Premisex.require(this ?: false, lazyMessage)

/**
 * Throws an [IllegalArgumentException] with the result of calling [lazyMessage] if the [this] is false.
 */
inline fun Boolean?.require(crossinline lazyMessage: () -> String) = Premisex.require(this ?: false) { lazyMessage() }

/**
 * Throws an [IllegalArgumentException] if the [this] is false.
 */
inline fun Boolean?.require() = Premisex.require(this ?: false)


/**
 * Throws an [IllegalStateException] with the result of calling [lazyMessage] if the [this] is false.
 */
inline fun Boolean?.check(lazyMessage: LazyValue<String>) = Premisex.check(this ?: false, lazyMessage)


/**
 * Throws an [IllegalStateException] with the result of calling [lazyMessage] if the [this] is false.
 */
inline fun Boolean?.check(crossinline lazyMessage: () -> String) = Premisex.check(this ?: false) { lazyMessage() }

/**
 * Throws an [IllegalStateException] if the [this] is false.
 */
inline fun Boolean?.check() = Premisex.check(this ?: false)


/* ******************************************* null *******************************************/


/**
 * If the self is not null, it returns itself, otherwise it throws an IllegalArgumentException with the result of calling [lazyMessage]
 */
inline fun <T> T?.requireNotNull(lazyMessage: LazyValue<String>): T = Premisex.requireNotNull(this, lazyMessage)

/**
 * If the self is not null, it returns itself, otherwise it throws an IllegalArgumentException with the result of calling [lazyMessage]
 */
inline fun <T> T?.requireNotNull(crossinline lazyMessage: () -> String): T = Premisex.requireNotNull(this) { lazyMessage() }

/**
 * If the self is not null, it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun <T> T?.requireNotNull(): T = Premisex.requireNotNull(this)

/**
 * If the self is not null, it returns itself, otherwise it throws an IllegalArgumentException
 */
inline fun <T> T?.requireNotNull(paramName: String): T = Premisex.requireNotNull(this, paramName)


/**
 * If the self is not null, it returns itself, otherwise it throws an IllegalStateException with the result of calling [lazyMessage]
 */
inline fun <T> T?.checkNotNull(lazyMessage: LazyValue<String>): T = Premisex.requireNotNull(this, lazyMessage)

/**
 * If the self is not null, it returns itself, otherwise it throws an IllegalStateException with the result of calling [lazyMessage]
 */
inline fun <T> T?.checkNotNull(crossinline lazyMessage: () -> String): T = Premisex.requireNotNull(this) { lazyMessage() }

/**
 * If the self is not null, it returns itself, otherwise it throws an IllegalStateException
 */
inline fun <T> T?.checkNotNull(): T = Premisex.requireNotNull(this)

/**
 * If the self is not null, it returns itself, otherwise it throws an IllegalStateException
 */
inline fun <T> T?.checkNotNull(paramName: String): T = Premisex.requireNotNull(this, paramName)