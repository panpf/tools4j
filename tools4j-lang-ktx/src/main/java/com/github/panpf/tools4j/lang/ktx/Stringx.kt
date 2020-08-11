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

package com.github.panpf.tools4j.lang.ktx

import com.github.panpf.tools4j.lang.Stringx


/*
 * String related extension methods or properties
 */


/* ******************************************* safe *******************************************/


/**
 * Returns `true` if this string is is not `null` and not empty and contains some characters except of whitespace characters.
 */
inline fun CharSequence?.isSafe(): Boolean = Stringx.isSafe(this)

/**
 * Returns `true` if this string is is `null` or empty or consists solely of whitespace characters.
 */
inline fun CharSequence?.isNotSafe(): Boolean = Stringx.isNotSafe(this)

/**
 * If the given character sequence is safe, it return is itself, otherwise it returns the default value.
 */
inline fun CharSequence?.safeOr(defaultValue: CharSequence): CharSequence = Stringx.safeOr(this, defaultValue)

/**
 * If the given character sequence is safe, it return is itself, otherwise it returns the default value.
 */
inline fun String?.safeOr(defaultValue: String): String = Stringx.safeOr(this, defaultValue)

/**
 * If the given character sequence is safe, it return is itself, otherwise it returns null.
 */
inline fun CharSequence?.safeOrNull(): CharSequence? = Stringx.safeOrNull(this)

/**
 * If the given character sequence is safe, it return is itself, otherwise it returns null.
 */
inline fun String?.safeOrNull(): String? = Stringx.safeOrNull(this)

/**
 * If the self is null or empty or blank, it returns itself, otherwise it throws an IllegalArgumentException.
 */
inline fun <T : CharSequence> T?.requireSafe(paramName: String = "unknown"): T? = Stringx.requireSafe(this, paramName)

/**
 * If the self is not null or empty or blank, it returns itself, otherwise it throws an IllegalArgumentException.
 */
inline fun <T : CharSequence> T?.requireNotSafe(paramName: String = "unknown"): T? = Stringx.requireNotSafe(this, paramName)


/* ******************************************* blank *******************************************/


/**
 * If the given character sequence is not blank, it return is itself, otherwise it returns the default value.
 */
inline fun CharSequence?.notBlankOr(defaultValue: CharSequence): CharSequence? = Stringx.notBlankOr(this, defaultValue)

/**
 * If the given character sequence is not blank, it return is itself, otherwise it returns the default value.
 */
inline fun String?.notBlankOr(defaultValue: String): String? = Stringx.notBlankOr(this, defaultValue)


/**
 * Return `true` if the character sequence is not `null` or whitespace characters.
 */
inline fun CharSequence?.isNotNullOrBlank(): Boolean = Stringx.isNotNullOrBlank(this)

/**
 * If the given character sequence is not null or blank, it return is itself, otherwise it returns the default value.
 */
inline fun CharSequence?.notNullOrBlankOr(defaultValue: CharSequence): CharSequence = Stringx.notNullOrBlankOr(this, defaultValue)

/**
 * If the given character sequence is not null or blank, it return is itself, otherwise it returns the default value.
 */
inline fun String?.notNullOrBlankOr(defaultValue: String): String = Stringx.notNullOrBlankOr(this, defaultValue)


/* ******************************************* empty *******************************************/


/**
 * If the given character sequence is not empty, it return is itself, otherwise it returns the default value.
 */
inline fun CharSequence?.notEmptyOr(defaultValue: CharSequence): CharSequence = Stringx.notEmptyOr(this, defaultValue)

/**
 * If the given character sequence is not empty, it return is itself, otherwise it returns the default value.
 */
inline fun String?.notEmptyOr(defaultValue: String): String = Stringx.notEmptyOr(this, defaultValue)


/**
 * Return `true` if the character sequence is not `null` or empty
 */
inline fun CharSequence?.isNotNullOrEmpty(): Boolean = Stringx.isNotNullOrEmpty(this)

/**
 * If the given character sequence is not null or empty, it return is itself, otherwise it returns the default value.
 */
inline fun CharSequence?.notNullOrEmptyOr(defaultValue: CharSequence): CharSequence = Stringx.notNullOrEmptyOr(this, defaultValue)

/**
 * If the given character sequence is not null or empty, it return is itself, otherwise it returns the default value.
 */
inline fun String?.notNullOrEmptyOr(defaultValue: String): String = Stringx.notNullOrEmptyOr(this, defaultValue)


/* ******************************************* chinese *******************************************/


/**
 * Return `true` if the given sequence of characters is all chinese
 */
inline fun CharSequence?.isChinese(): Boolean = Stringx.isChinese(this)

/**
 * Return `true` if the given sequence of characters is all not chinese
 */
inline fun CharSequence?.isNotChinese(): Boolean = Stringx.isNotChinese(this)

/**
 * If the given character sequence is chinese, it return is itself, otherwise it returns the default value.
 */
inline fun CharSequence?.chineseOr(defaultValue: CharSequence): CharSequence = Stringx.chineseOr(this, defaultValue)

/**
 * If the given character sequence is chinese, it return is itself, otherwise it returns the default value.
 */
inline fun String?.chineseOr(defaultValue: String): String = Stringx.chineseOr(this, defaultValue)


/* ******************************************* digit *******************************************/


/**
 * Return `true` if the given sequence of characters is all digit
 */
inline fun CharSequence?.isDigit(): Boolean = Stringx.isDigit(this)

/**
 * Return `true` if the given sequence of characters is all not digit
 */
inline fun CharSequence?.isNotDigit(): Boolean = Stringx.isNotDigit(this)

/**
 * If the given character sequence is digit, it return is itself, otherwise it returns the default value.
 */
inline fun CharSequence?.digitOr(defaultValue: CharSequence): CharSequence = Stringx.digitOr(this, defaultValue)

/**
 * If the given character sequence is digit, it return is itself, otherwise it returns the default value.
 */
inline fun String?.digitOr(defaultValue: String): String = Stringx.digitOr(this, defaultValue)


/* ******************************************* letter *******************************************/


/**
 * Return `true` if the given sequence of characters is all letter
 */
inline fun CharSequence?.isLetter(): Boolean = Stringx.isLetter(this)

/**
 * Return `true` if the given sequence of characters is all not letter
 */
inline fun CharSequence?.isNotLetter(): Boolean = Stringx.isNotLetter(this)

/**
 * If the given character sequence is letter, it return is itself, otherwise it returns the default value.
 */
inline fun CharSequence?.letterOr(defaultValue: CharSequence): CharSequence = Stringx.letterOr(this, defaultValue)

/**
 * If the given character sequence is letter, it return is itself, otherwise it returns the default value.
 */
inline fun String?.letterOr(defaultValue: String): String = Stringx.letterOr(this, defaultValue)


/* ******************************************* letterOrDigit *******************************************/


/**
 * Return `true` if the given sequence of characters is all digit or letter
 */
inline fun CharSequence?.isLetterOrDigit(): Boolean = Stringx.isLetterOrDigit(this)

/**
 * Return `true` if the given sequence of characters is all not digit or letter
 */
inline fun CharSequence?.isNotLetterOrDigit(): Boolean = Stringx.isNotLetterOrDigit(this)

/**
 * If the given character sequence is digit or letter, it return is itself, otherwise it returns the default value.
 */
inline fun CharSequence?.letterOrDigitOr(defaultValue: CharSequence): CharSequence = Stringx.letterOrDigitOr(this, defaultValue)

/**
 * If the given character sequence is digit or letter, it return is itself, otherwise it returns the default value.
 */
inline fun String?.letterOrDigitOr(defaultValue: String): String = Stringx.letterOrDigitOr(this, defaultValue)


/* ******************************************* containsAny and containsAll *******************************************/


/**
 * Return true if the specified string contains any of the strings in [params]
 */
inline fun String?.containsAny(params: Array<String>?, ignoreCase: Boolean): Boolean = Stringx.containsAny(this, params, ignoreCase)

/**
 * Return true if the specified string contains any of the strings in [params]
 */
inline fun String?.containsAny(params: Array<String>?): Boolean = Stringx.containsAny(this, params)

/**
 * Return true if the specified string contains any of the strings in [params]
 */
inline fun String?.containsAny(params: Collection<String>?, ignoreCase: Boolean): Boolean = Stringx.containsAny(this, params, ignoreCase)

/**
 * Return true if the specified string contains any of the strings in [params]
 */
inline fun String?.containsAny(params: Collection<String>?): Boolean = Stringx.containsAny(this, params)

/**
 * Return true if the specified string contains all the strings in[params]
 */
inline fun String?.containsAll(params: Array<String>?, ignoreCase: Boolean): Boolean = Stringx.containsAll(this, params, ignoreCase)

/**
 * Return true if the specified string contains all the strings in[params]
 */
inline fun String?.containsAll(params: Array<String>?): Boolean = Stringx.containsAll(this, params)

/**
 * Return true if the specified string contains all the strings in[params]
 */
inline fun String?.containsAll(params: Collection<String>?, ignoreCase: Boolean): Boolean = Stringx.containsAll(this, params, ignoreCase)

/**
 * Return true if the specified string contains all the strings in[params]
 */
inline fun String?.containsAll(params: Collection<String>?): Boolean = Stringx.containsAll(this, params)


/* ******************************************* or and to *******************************************/


/**
 * Returns the given character sequence if it is not null, otherwise return an empty sequence of characters
 */
inline fun CharSequence?.orEmpty(): CharSequence = Stringx.orEmpty(this)

/**
 * Returns the given character sequence if it is not null, otherwise return an empty sequence of characters
 */
inline fun String?.orEmpty(): String = Stringx.orEmpty(this)

/**
 * Returns the given character sequence if it is not null, otherwise return given defaultValue
 */
inline fun CharSequence?.orDefault(defaultValue: CharSequence): CharSequence = Stringx.orDefault(this, defaultValue)

/**
 * Returns the given character sequence if it is not null, otherwise return given defaultValue
 */
inline fun String?.orDefault(defaultValue: String): String = Stringx.orDefault(this, defaultValue)


/**
 * If the given char sequence is empty, it return `null` otherwise it return itself
 */
inline fun CharSequence?.emptyToNull(): CharSequence? = Stringx.emptyToNull(this)

/**
 * If the given char sequence is empty, it return `null` otherwise it return itself
 */
inline fun String?.emptyToNull(): String? = Stringx.emptyToNull(this)

/**
 * If the given char sequence is blank, it return `null` otherwise it return itself
 */
inline fun CharSequence?.blankToNull(): CharSequence? = Stringx.blankToNull(this)

/**
 * If the given char sequence is blank, it return `null` otherwise it return itself
 */
inline fun String?.blankToNull(): String? = Stringx.blankToNull(this)


/* ******************************************* filterBlank *******************************************/


/**
 * Filter out whitespace characters in a sequence of characters
 */
inline fun CharSequence?.filterBlank(): CharSequence = Stringx.filterBlank(this)

/**
 * Filter out whitespace characters in a sequence of characters
 */
inline fun String?.filterBlank(): String = Stringx.filterBlank(this)


/* ******************************************* remove *******************************************/


/**
 * Delete all specified characters in the string and return the new string
 */
inline fun String?.removeChar(ch: Char): String = Stringx.removeChar(this, ch)

/**
 * Delete the first occurrence of the specified character in the string and return the new string
 */
inline fun String?.removeFirstChar(ch: Char): String = Stringx.removeFirstChar(this, ch)

/**
 * Delete the last specified character in the string and return the new string
 */
inline fun String?.removeLastChar(ch: Char): String = Stringx.removeLastChar(this, ch)

/**
 * Delete the character at the specified position in the string and return the new string
 */
inline fun String?.removeIndex(removeIndex: Int): String = Stringx.removeIndex(this, removeIndex)


/* ******************************************* limit *******************************************/


/**
 * If the length of the character sequences exceeds the specified length, the character sequences is intercepted
 * and the specified suffix is ​​returned to return the new character sequences, otherwise it return itself.
 */
inline fun CharSequence?.limit(length: Int, suffix: String?): CharSequence = Stringx.limit(this, length, suffix)

/**
 * If the length of the character sequences exceeds the specified length, the character sequences is intercepted
 * and the specified suffix is ​​returned to return the new character sequences, otherwise it return itself.
 */
inline fun CharSequence?.limit(length: Int): CharSequence = Stringx.limit(this, length)

/**
 * If the length of the string exceeds the specified length, the string is intercepted and the specified suffix
 * is ​​returned to return the new string, otherwise it return itself.
 */
inline fun String?.limit(length: Int, suffix: String?): String = Stringx.limit(this, length, suffix)

/**
 * If the length of the string exceeds the specified length, the string is intercepted and the specified suffix
 * is ​​returned to return the new string, otherwise it return itself.
 */
inline fun String?.limit(length: Int): String = Stringx.limit(this, length)


/* ******************************************* replace ****************************************** */

/**
 * Returns a new string obtained by replacing each substring of this char sequence that matches the given [target]
 * with the given [replacement].
 * <p>
 * Compared to String.replace(), the result of "*12345*".replace("*", "\*").replace("*", "\*") is '\\*12345\\*' , and the result of "*12345*".replaceNoRepeat("*", "\*").replaceNoRepeat("*", "\*") is '\*12345\*'
 */
inline fun String?.replaceNoRepeat(target: String, replacement: String) = Stringx.replaceNoRepeat(this, target, replacement)


/* ******************************************* firstLetter UpperCase or LowerCase ****************************************** */

/**
 * Convert the first letter of a given string to uppercase
 */
inline fun String?.firstLetterUpperCase(): String = Stringx.firstLetterUpperCase(this)

/**
 * Convert the first letter of a given string to lowercase
 */
inline fun String?.firstLetterLowerCase(): String = Stringx.firstLetterLowerCase(this)


/* ******************************************* hidden *******************************************/


/**
 * Replaces the character of the specified length at the beginning of the string with the specified character
 */
inline fun String?.hiddenStartChars(hiddenLength: Int, targetChar: Char): String = Stringx.hiddenStartChars(this, hiddenLength, targetChar)

/**
 * Replace the character of the specified length at the beginning of the string with the '*' character
 */
inline fun String?.hiddenStartChars(hiddenLength: Int): String = Stringx.hiddenStartChars(this, hiddenLength)

/**
 * Replaces the specified length of characters in the middle of the string with the specified character
 */
inline fun String?.hiddenMiddleChars(hiddenLength: Int, targetChar: Char): String = Stringx.hiddenMiddleChars(this, hiddenLength, targetChar)

/**
 * Replace the character of the specified length in the middle of the string with the '*' character
 */
inline fun String?.hiddenMiddleChars(hiddenLength: Int): String = Stringx.hiddenMiddleChars(this, hiddenLength)

/**
 * Replaces the specified length of characters at the end of the string with the specified character
 */
inline fun String?.hiddenEndChars(hiddenLength: Int, targetChar: Char): String = Stringx.hiddenEndChars(this, hiddenLength, targetChar)

/**
 * Replace the character of the specified length at the end of the string with the '*' character
 */
inline fun String?.hiddenEndChars(hiddenLength: Int): String = Stringx.hiddenEndChars(this, hiddenLength)