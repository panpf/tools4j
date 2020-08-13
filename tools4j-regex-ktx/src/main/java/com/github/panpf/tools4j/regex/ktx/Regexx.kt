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

package com.github.panpf.tools4j.regex.ktx

import com.github.panpf.tools4j.regex.Group
import com.github.panpf.tools4j.regex.Regexx
import java.util.regex.Pattern


/**
 * Return true if the entire sequence of characters matches the given regular expression
 */
inline fun CharSequence?.regexMatches(pattern: Pattern): Boolean = Regexx.matches(this, pattern)

/**
 * Return true if the entire sequence of characters matches the given regular expression
 */
inline fun CharSequence?.regexMatches(regex: String): Boolean = Regexx.matches(this, regex)

/**
 * Returns true if the specified sequence of characters matches any given position from the specified position to the given regular expression
 */
inline fun CharSequence?.regexFind(pattern: Pattern, start: Int): Boolean = Regexx.find(this, pattern, start)

/**
 * Returns true if the specified sequence of characters matches any given position from the specified position to the given regular expression
 */
inline fun CharSequence?.regexFind(regex: String, start: Int): Boolean = Regexx.find(this, regex, start)

/**
 * Return true if a given regular expression is matched anywhere in a given sequence of characters
 */
inline fun CharSequence?.regexFind(pattern: Pattern): Boolean = Regexx.find(this, pattern)

/**
 * Return true if a given regular expression is matched anywhere in a given sequence of characters
 */
inline fun CharSequence?.regexFind(regex: String): Boolean = Regexx.find(this, regex)

/**
 * Return true if the given regular expression is matched at the beginning of the character sequence
 */
inline fun CharSequence?.regexLookingAt(pattern: Pattern): Boolean = Regexx.lookingAt(this, pattern)

/**
 * Return true if the given regular expression is matched at the beginning of the character sequence
 */
inline fun CharSequence?.regexLookingAt(regex: String): Boolean = Regexx.lookingAt(this, regex)

/**
 * Get the first matching string
 */
inline fun CharSequence?.regexGetFirst(pattern: Pattern): String? = Regexx.getFirst(this, pattern)

/**
 * Get the first matching string
 */
inline fun CharSequence?.regexGetFirst(regex: String): String? = Regexx.getFirst(this, regex)

/**
 * Get the all matching string
 */
inline fun CharSequence?.regexGetAll(pattern: Pattern): Array<String> = Regexx.getAll(this, pattern)

/**
 * Get the all matching string
 */
inline fun CharSequence?.regexGetAll(regex: String): Array<String> = Regexx.getAll(this, regex)

/**
 * Get the first matching group
 */
inline fun CharSequence?.regexFirstGroup(pattern: Pattern): Group? = Regexx.firstGroup(this, pattern)

/**
 * Get the first matching group
 */
inline fun CharSequence?.regexFirstGroup(regex: String): Group? = Regexx.firstGroup(this, regex)

/**
 * Get the all matching group
 */
inline fun CharSequence?.regexAllGroup(pattern: Pattern): Array<Group> = Regexx.allGroup(this, pattern)

/**
 * Get the all matching group
 */
inline fun CharSequence?.regexAllGroup(regex: String): Array<Group> = Regexx.allGroup(this, regex)

/**
 * Replace the first matching string
 */
inline fun CharSequence?.regexReplaceFirst(pattern: Pattern, replacement: String): String = Regexx.replaceFirst(this, pattern, replacement)

/**
 * Replace the first matching string
 */
inline fun CharSequence?.regexReplaceFirst(regex: String, replacement: String): String = Regexx.replaceFirst(this, regex, replacement)

/**
 * Replace the all matching string
 */
inline fun CharSequence?.regexReplaceAll(pattern: Pattern, replacement: String): String = Regexx.replaceAll(this, pattern, replacement)

/**
 * Replace the all matching string
 */
inline fun CharSequence?.regexReplaceAll(regex: String, replacement: String): String = Regexx.replaceAll(this, regex, replacement)