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

@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4j.security.ktx

import com.github.panpf.tools4j.security.Keyx
import java.security.Key


/*
 * Key related extension methods or properties
 */


/**
 * Convert key to a Base64 string
 */
inline fun Key.toBase64(): String = Keyx.toBase64(this)

/**
 * Convert key to a byte array
 */
inline fun Key.toBytes(): ByteArray = Keyx.toBytes(this)