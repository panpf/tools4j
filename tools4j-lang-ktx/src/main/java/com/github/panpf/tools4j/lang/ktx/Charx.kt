@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4j.lang.ktx

import com.github.panpf.tools4j.lang.Charx


/*
 * Char related extension methods or properties
 */


/* ******************************************* isBlank *******************************************/


/**
 * Return `true` if the given character is blank
 */
inline fun Char.isBlank(): Boolean = Charx.isBlank(this)

/**
 * Return `true` if the given character is blank
 */
inline fun Char.isNotBlank(): Boolean = Charx.isNotBlank(this)

/**
 * If the given character is not blank, it return is itself, otherwise it returns the default value.
 */
inline fun Char.notBlankOr(defaultValue: Char): Char = Charx.notBlankOr(this, defaultValue)


/* ******************************************* isChinese *******************************************/


/**
 * Return `true` if the given character is Chinese
 */
inline fun Char.isChinese(): Boolean = Charx.isChinese(this)

/**
 * Return `true` if the given character is chinese
 */
inline fun Char.isNotChinese(): Boolean = Charx.isNotChinese(this)

/**
 * If the given character is chinese, it return is itself, otherwise it returns the default value.
 */
inline fun Char.chineseOr(defaultValue: Char): Char = Charx.chineseOr(this, defaultValue)


/* ******************************************* isDigit *******************************************/


///**
// * Return `true` if the given character is digit
// */
//inline fun Char.isDigit(): Boolean = Charx.isDigit(this)

/**
 * Return `true` if the given character is not digit
 */
inline fun Char.isNotDigit(): Boolean = Charx.isNotDigit(this)


/**
 * If the given character is digit, it return is itself, otherwise it returns the default value.
 */
inline fun Char.digitOr(defaultValue: Char): Char = Charx.digitOr(this, defaultValue)


/* ******************************************* isLetter *******************************************/


///**
// * Return `true` if the given character is letter
// */
//inline fun Char.isLetter(): Boolean = Charx.isLetter(this)

/**
 * Return `true` if the given character is not letter
 */
inline fun Char.isNotLetter(): Boolean = Charx.isNotLetter(this)

/**
 * If the given character is letter, it return is itself, otherwise it returns the default value.
 */
inline fun Char.letterOr(defaultValue: Char): Char = Charx.letterOr(this, defaultValue)


/* ******************************************* isLetterOrDigit *******************************************/


///**
// * Return `true` if the given character is letter or digit
// */
//inline fun Char.isLetterOrDigit(): Boolean = Charx.isLetterOrDigit(this)

/**
 * Return `true` if the given character is not letter or digit
 */
inline fun Char.isNotLetterOrDigit(): Boolean = Charx.isNotLetterOrDigit(this)

/**
 * If the given character is letter or digit, it return is itself, otherwise it returns the default value.
 */
inline fun Char.letterOrDigitOr(defaultValue: Char): Char = Charx.letterOrDigitOr(this, defaultValue)