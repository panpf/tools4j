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

package com.github.panpf.tools4j.lang;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Character tool method
 */
public class Charx {

    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    private Charx() {
    }


    /* ******************************************* isBlank *******************************************/


    /**
     * Return `true` if the given character is blank
     */
    public static boolean isBlank(char c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c);
    }

    /**
     * Return `true` if the given character is blank
     */
    public static boolean isNotBlank(char c) {
        return !isBlank(c);
    }

    /**
     * If the given character is not blank, it return is itself, otherwise it returns the default value.
     */
    public static char notBlankOr(char c, char defaultValue) {
        return isNotBlank(c) ? c : defaultValue;
    }


    /* ******************************************* isChinese *******************************************/


    /**
     * Return `true` if the given character is Chinese
     */
    public static boolean isChinese(char cha) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(cha);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * Return `true` if the given character is chinese
     */
    public static boolean isNotChinese(char cha) {
        return !isChinese(cha);
    }

    /**
     * If the given character is chinese, it return is itself, otherwise it returns the default value.
     */
    public static char chineseOr(char c, char defaultValue) {
        return isChinese(c) ? c : defaultValue;
    }


    /* ******************************************* isDigit *******************************************/

    /**
     * Return `true` if the given character is not digit
     */
    public static boolean isNotDigit(char cha) {
        return !isDigit(cha);
    }

    /**
     * If the given character is digit, it return is itself, otherwise it returns the default value.
     */
    public static char digitOr(char c, char defaultValue) {
        return isDigit(c) ? c : defaultValue;
    }


    /* ******************************************* isLetter *******************************************/

    /**
     * Return `true` if the given character is not letter
     */
    public static boolean isNotLetter(char cha) {
        return !isLetter(cha);
    }

    /**
     * If the given character is letter, it return is itself, otherwise it returns the default value.
     */
    public static char letterOr(char c, char defaultValue) {
        return isLetter(c) ? c : defaultValue;
    }


    /* ******************************************* isLetterOrDigit *******************************************/

    /**
     * Return `true` if the given character is not letter or digit
     */
    public static boolean isNotLetterOrDigit(char cha) {
        return !isLetterOrDigit(cha);
    }

    /**
     * If the given character is letter or digit, it return is itself, otherwise it returns the default value.
     */
    public static char letterOrDigitOr(char c, char defaultValue) {
        return isLetterOrDigit(c) ? c : defaultValue;
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    /**
     * Return `true` if the given character is digit
     */
    public static boolean isDigit(char cha) {
        return Character.isDigit(cha);
    }


    /**
     * Return `true` if the given character is letter
     */
    public static boolean isLetter(char cha) {
        return Character.isLetter(cha);
    }


    /**
     * Return `true` if the given character is letter or digit
     */
    public static boolean isLetterOrDigit(char cha) {
        return Character.isLetterOrDigit(cha);
    }


    /**
     * Returns `true` if this character is equal to the [other] character, optionally ignoring character case.
     *
     * @param ignoreCase `true` to ignore character case when comparing characters. By default `false`.
     *                   <p>
     *                   Two characters are considered the same ignoring case if at least one of the following is `true`:
     *                   - The two characters are the same (as compared by the == operator)
     *                   - Applying the method [toUpperCase] to each character produces the same result
     *                   - Applying the method [toLowerCase] to each character produces the same result
     */
    public static boolean equals(char self, char other, boolean ignoreCase) {
        if (self == other) return true;
        if (!ignoreCase) return false;

        if (Character.toUpperCase(self) == Character.toUpperCase(other)) return true;
        return Character.toUpperCase(self) == Character.toUpperCase(other);
    }

    /**
     * Returns `true` if this character is equal to the [other] character, optionally ignoring character case.
     */
    public static boolean equals(char self, char other) {
        return equals(self, other, false);
    }
}
