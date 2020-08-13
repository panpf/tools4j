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

package com.github.panpf.tools4j.lang.ktx

import com.github.panpf.tools4j.lang.Numberx


/*
 * Number related extension methods or properties
 */


/* ******************************************* number zero *******************************************/


/**
 * If self is not 0, it returns itself, otherwise it throws an exception.
 */
inline fun Byte.requireNotZero(paramName: String = "unknown"): Byte = Numberx.requireNotZero(this, paramName)

/**
 * If self is not 0, it returns itself, otherwise it throws an exception.
 */
inline fun Short.requireNotZero(paramName: String = "unknown"): Short = Numberx.requireNotZero(this, paramName)

/**
 * If self is not 0, it returns itself, otherwise it throws an exception.
 */
inline fun Int.requireNotZero(paramName: String = "unknown"): Int = Numberx.requireNotZero(this, paramName)

/**
 * If self is not 0, it returns itself, otherwise it throws an exception.
 */
inline fun Long.requireNotZero(paramName: String = "unknown"): Long = Numberx.requireNotZero(this, paramName)

/**
 * If self is not 0, it returns itself, otherwise it throws an exception.
 */
inline fun Float.requireNotZero(paramName: String = "unknown"): Float = Numberx.requireNotZero(this, paramName)

/**
 * If self is not 0, it returns itself, otherwise it throws an exception.
 */
inline fun Double.requireNotZero(paramName: String = "unknown"): Double = Numberx.requireNotZero(this, paramName)


/* ******************************************* pad *******************************************/


/**
 * Converts the given number to a string of a given length. If the number of digits is not enough, it is added 0 in front.
 */
inline fun Int?.pad(stringLength: Int): String = Numberx.pad(this.orZero(), stringLength)

/**
 * Converts the given number to a string of a given length. If the number of digits is not enough, it is added 0 in front.
 */
inline fun Long?.pad(stringLength: Int): String = Numberx.pad(this.orZero(), stringLength)


/* ******************************************* orZero *******************************************/



/**
 * Return to yourself if you are not null, otherwise return 0
 */
inline fun Byte?.orZero(): Byte = this ?: 0.toByte()

/**
 * Return to yourself if you are not null, otherwise return 0
 */
inline fun Short?.orZero(): Short = this ?: 0.toShort()

/**
 * Return to yourself if you are not null, otherwise return 0
 */
inline fun Int?.orZero(): Int = this ?: 0

/**
 * Return to yourself if you are not null, otherwise return 0
 */
inline fun Long?.orZero(): Long = this ?: 0.toLong()

/**
 * Return to yourself if you are not null, otherwise return 0
 */
inline fun Float?.orZero(): Float = this ?: 0.toFloat()

/**
 * Return to yourself if you are not null, otherwise return 0
 */
inline fun Double?.orZero(): Double = this ?: 0.toDouble()


/**
 * Returns a string representation of the integer argument as an unsigned integer in base16.
 */
inline fun Int.toHexString(): String = Integer.toHexString(this)

/**
 * Returns a string representation or 'defaultValue' of the integer argument as an unsigned integer in base16.
 */
inline fun Int?.toHexStringOr(defaultValue: String): String = if (this != null) Integer.toHexString(this) else defaultValue

/**
 * Returns a string representation or "null" of the integer argument as an unsigned integer in base16.
 */
inline fun Int?.toHexStringOrNull(): String = if (this != null) Integer.toHexString(this) else "null"


/**
 * Returns a string representation of the integer argument as an unsigned integer in base2.
 */
inline fun Int.toBinaryString(): String = Integer.toBinaryString(this)

/**
 * Returns a string representation or 'defaultValue' of the integer argument as an unsigned integer in base2.
 */
inline fun Int?.toBinaryStringOr(defaultValue: String): String = if (this != null) Integer.toBinaryString(this) else defaultValue

/**
 * Returns a string representation or "null" of the integer argument as an unsigned integer in base2.
 */
inline fun Int?.toBinaryStringOrNull(): String = if (this != null) Integer.toBinaryString(this) else "null"


/**
 * Returns a string representation of the integer argument as an unsigned integer in base8.
 */
inline fun Int.toOctalString(): String = Integer.toOctalString(this)

/**
 * Returns a string representation or 'defaultValue' of the integer argument as an unsigned integer in base8.
 */
inline fun Int?.toOctalStringOr(defaultValue: String): String = if (this != null) Integer.toOctalString(this) else defaultValue

/**
 * Returns a string representation or "null" of the integer argument as an unsigned integer in base8.
 */
inline fun Int?.toOctalStringOrNull(): String = if (this != null) Integer.toOctalString(this) else "null"


/**
 * Returns a string representation of the long argument as an unsigned integer in base16.
 */
inline fun Long.toHexString(): String = java.lang.Long.toHexString(this)

/**
 * Returns a string representation or 'defaultValue' of the long argument as an unsigned integer in base16.
 */
inline fun Long?.toHexStringOr(defaultValue: String): String = if (this != null) java.lang.Long.toHexString(this) else defaultValue

/**
 * Returns a string representation or "null" of the long argument as an unsigned integer in base16.
 */
inline fun Long?.toHexStringOrNull(): String = if (this != null) java.lang.Long.toHexString(this) else "null"


/**
 * Returns a string representation of the long argument as an unsigned integer in base2.
 */
inline fun Long.toBinaryString(): String = java.lang.Long.toBinaryString(this)

/**
 * Returns a string representation or 'defaultValue' of the long argument as an unsigned integer in base2.
 */
inline fun Long?.toBinaryStringOr(defaultValue: String): String = if (this != null) java.lang.Long.toBinaryString(this) else defaultValue

/**
 * Returns a string representation or "null" of the long argument as an unsigned integer in base2.
 */
inline fun Long?.toBinaryStringOrNull(): String = if (this != null) java.lang.Long.toBinaryString(this) else "null"


/**
 * Returns a string representation of the long argument as an unsigned integer in base8.
 */
inline fun Long.toOctalString(): String = java.lang.Long.toOctalString(this)

/**
 * Returns a string representation or 'defaultValue' of the long argument as an unsigned integer in base8.
 */
inline fun Long?.toOctalStringOr(defaultValue: String): String = if (this != null) java.lang.Long.toOctalString(this) else defaultValue

/**
 * Returns a string representation or "null" of the long argument as an unsigned integer in base8.
 */
inline fun Long?.toOctalStringOrNull(): String = if (this != null) java.lang.Long.toOctalString(this) else "null"


/*
 * *****************************************************************************************************************
 * From kotlin standard library
 * *****************************************************************************************************************
 */


/**
 * Convert a String to an byte, returning a default value if the conversion fails.
 */
inline fun String?.toByteOrDefault(defaultValue: Byte): Byte = Numberx.toByteOrDefault(this, defaultValue)

/**
 * Convert a String to a byte, returning 0 if the conversion fails.
 */
inline fun String?.toByteOrZero(): Byte = Numberx.toByteOrZero(this)

/**
 * Convert a String to an short, returning a default value if the conversion fails.
 */
inline fun String?.toShortOrDefault(defaultValue: Short): Short = Numberx.toShortOrDefault(this, defaultValue)

/**
 * Convert a String to a short, returning 0 if the conversion fails.
 */
inline fun String?.toShortOrZero(): Short = Numberx.toShortOrZero(this)

/**
 * Convert a String to an int, returning a default value if the conversion fails.
 */
inline fun String?.toIntOrDefault(defaultValue: Int): Int = Numberx.toIntOrDefault(this, defaultValue)

/**
 * Convert a String to a int, returning 0 if the conversion fails.
 */
inline fun String?.toIntOrZero(): Int = Numberx.toIntOrZero(this)

/**
 * Convert a String to an long, returning a default value if the conversion fails.
 */
inline fun String?.toLongOrDefault(defaultValue: Long): Long = Numberx.toLongOrDefault(this, defaultValue)

/**
 * Convert a String to a long, returning 0L if the conversion fails.
 */
inline fun String?.toLongOrZero(): Long = Numberx.toLongOrZero(this)

/**
 * Convert a String to an float, returning a default value if the conversion fails.
 */
inline fun String?.toFloatOrDefault(defaultValue: Float): Float = Numberx.toFloatOrDefault(this, defaultValue)

/**
 * Convert a String to a float, returning 0.0f if the conversion fails.
 */
inline fun String?.toFloatOrZero(): Float = Numberx.toFloatOrZero(this)

/**
 * Convert a String to an double, returning a default value if the conversion fails.
 */
inline fun String?.toDoubleOrDefault(defaultValue: Double): Double = Numberx.toDoubleOrDefault(this, defaultValue)

/**
 * Convert a String to a double, returning 0.0d if the conversion fails.
 */
inline fun String?.toDoubleOrZero(): Double = Numberx.toDoubleOrZero(this)