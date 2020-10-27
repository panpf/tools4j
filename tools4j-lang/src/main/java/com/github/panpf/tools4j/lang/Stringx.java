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

package com.github.panpf.tools4j.lang;

import com.github.panpf.tools4j.common.*;
import com.github.panpf.tools4j.iterable.*;
import com.github.panpf.tools4j.ranges.IntProgression;
import com.github.panpf.tools4j.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

/**
 * String tool method
 * <br>
 * <br>from kotlin files: Strings.kt, StringsJvm.kt, _Strings.kt, _StringsJvm.kt
 */
public class Stringx {

    private Stringx() {
    }

    // todo add ifEmpty ifBlank method from kotlin


    /* ******************************************* safe ****************************************** */


    /**
     * Returns `true` if this string is is not `null` and not empty and contains some characters except of whitespace characters.
     */
    public static boolean isSafe(@Nullable CharSequence sequence) {
        return sequence != null && sequence.length() > 0 && isNotBlank(sequence);
    }

    /**
     * Returns `true` if this string is is `null` or empty or consists solely of whitespace characters.
     */
    public static boolean isNotSafe(@Nullable CharSequence sequence) {
        return !isSafe(sequence);
    }

    /**
     * If the given character sequence is safe, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static CharSequence safeOr(@Nullable CharSequence sequence, @NotNull CharSequence defaultValue) {
        return isSafe(sequence) ? sequence : defaultValue;
    }

    /**
     * If the given character sequence is safe, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static String safeOr(@Nullable String string, @NotNull String defaultValue) {
        return isSafe(string) ? string : defaultValue;
    }

    /**
     * If the given character sequence is safe, it return is itself, otherwise it returns null.
     */
    @Nullable
    public static CharSequence safeOrNull(@Nullable CharSequence string) {
        return isSafe(string) ? string : null;
    }

    /**
     * If the given character sequence is safe, it return is itself, otherwise it returns null.
     */
    @Nullable
    public static String safeOrNull(@Nullable String string) {
        return isSafe(string) ? string : null;
    }

    /**
     * If the [value] is null or empty or blank, it returns itself, otherwise it throws an IllegalArgumentException.
     */
    public static <T extends CharSequence> T requireSafe(@Nullable T value, @NotNull String paramName) {
        if (isSafe(value)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The string parameter '%s' is null or empty or blank", paramName));
        }
    }

    /**
     * If the [value] is null or empty or blank, it returns itself, otherwise it throws an IllegalArgumentException.
     */
    public static <T extends CharSequence> T requireSafe(@Nullable T value) {
        return requireSafe(value, "unknown");
    }

    /**
     * If the [value] is not null or empty or blank, it returns itself, otherwise it throws an IllegalArgumentException.
     */
    public static <T extends CharSequence> T requireNotSafe(@Nullable T value, @NotNull String paramName) {
        if (isNotSafe(value)) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The string parameter '%s' is not null or empty or blank", paramName));
        }
    }

    /**
     * If the [value] is not null or empty or blank, it returns itself, otherwise it throws an IllegalArgumentException.
     */
    public static <T extends CharSequence> T requireNotSafe(@Nullable T value) {
        return requireNotSafe(value, "unknown");
    }


    /* ******************************************* blank ****************************************** */


    /**
     * If the given character sequence is not blank, it return is itself, otherwise it returns the default value.
     */
    @Nullable
    public static CharSequence notBlankOr(@Nullable CharSequence sequence, @NotNull CharSequence defaultValue) {
        return isNotBlank(sequence) ? sequence : defaultValue;
    }

    /**
     * If the given character sequence is not blank, it return is itself, otherwise it returns the default value.
     */
    @Nullable
    public static String notBlankOr(@Nullable String string, @NotNull String defaultValue) {
        return isNotBlank(string) ? string : defaultValue;
    }


    /**
     * Return `true` if the character sequence is not `null` or whitespace characters.
     */
    public static boolean isNotNullOrBlank(@Nullable CharSequence sequence) {
        return !isNullOrBlank(sequence);
    }

    /**
     * If the given character sequence is not null or blank, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static CharSequence notNullOrBlankOr(@Nullable CharSequence sequence, @NotNull CharSequence defaultValue) {
        return isNotNullOrBlank(sequence) ? sequence : defaultValue;
    }

    /**
     * If the given character sequence is not null or blank, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static String notNullOrBlankOr(@Nullable String string, @NotNull String defaultValue) {
        return isNotNullOrBlank(string) ? string : defaultValue;
    }


    /* ******************************************* empty ****************************************** */


    /**
     * If the given character sequence is not empty, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static CharSequence notEmptyOr(@Nullable CharSequence sequence, @NotNull CharSequence defaultValue) {
        return isNotEmpty(sequence) ? sequence : defaultValue;
    }

    /**
     * If the given character sequence is not empty, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static String notEmptyOr(@Nullable String string, @NotNull String defaultValue) {
        return isNotEmpty(string) ? string : defaultValue;
    }


    /**
     * Return `true` if the character sequence is not `null` or empty
     */
    public static boolean isNotNullOrEmpty(@Nullable CharSequence sequence) {
        return sequence != null && sequence.length() > 0;
    }

    /**
     * If the given character sequence is not null or empty, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static CharSequence notNullOrEmptyOr(@Nullable CharSequence sequence, @NotNull CharSequence defaultValue) {
        return isNotNullOrEmpty(sequence) ? sequence : defaultValue;
    }

    /**
     * If the given character sequence is not null or empty, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static String notNullOrEmptyOr(@Nullable String string, @NotNull String defaultValue) {
        return isNotNullOrEmpty(string) ? string : defaultValue;
    }


    /* ******************************************* chinese ****************************************** */


    /**
     * Return `true` if the given sequence of characters is all chinese
     */
    public static boolean isChinese(@Nullable CharSequence sequence) {
        if (sequence == null || isNotSafe(sequence)) return false;
        for (int index = 0, size = sequence.length(); index < size; index++) {
            if (Charx.isNotChinese(sequence.charAt(index))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return `true` if the given sequence of characters is all not chinese
     */
    public static boolean isNotChinese(@Nullable CharSequence sequence) {
        return !isChinese(sequence);
    }

    /**
     * If the given character sequence is chinese, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static CharSequence chineseOr(@Nullable CharSequence sequence, @NotNull CharSequence defaultValue) {
        return isChinese(sequence) ? sequence : defaultValue;
    }

    /**
     * If the given character sequence is chinese, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static String chineseOr(@Nullable String string, @NotNull String defaultValue) {
        return isChinese(string) ? string : defaultValue;
    }


    /* ******************************************* digit ****************************************** */


    /**
     * Return `true` if the given sequence of characters is all digit
     */
    public static boolean isDigit(@Nullable CharSequence sequence) {
        if (sequence == null || isNotSafe(sequence)) return false;
        for (int index = 0, size = sequence.length(); index < size; index++) {
            if (Charx.isNotDigit(sequence.charAt(index))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return `true` if the given sequence of characters is all not digit
     */
    public static boolean isNotDigit(@Nullable CharSequence sequence) {
        return !isDigit(sequence);
    }

    /**
     * If the given character sequence is digit, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static CharSequence digitOr(@Nullable CharSequence sequence, @NotNull CharSequence defaultValue) {
        return isDigit(sequence) ? sequence : defaultValue;
    }

    /**
     * If the given character sequence is digit, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static String digitOr(@Nullable String string, @NotNull String defaultValue) {
        return isDigit(string) ? string : defaultValue;
    }


    /* ******************************************* lisLetter ****************************************** */


    /**
     * Return `true` if the given sequence of characters is all letter
     */
    public static boolean isLetter(@Nullable CharSequence sequence) {
        if (sequence == null || isNotSafe(sequence)) return false;
        for (int index = 0, size = sequence.length(); index < size; index++) {
            if (Charx.isNotLetter(sequence.charAt(index))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return `true` if the given sequence of characters is all not letter
     */
    public static boolean isNotLetter(@Nullable CharSequence sequence) {
        return !isLetter(sequence);
    }

    /**
     * If the given character sequence is letter, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static CharSequence letterOr(@Nullable CharSequence sequence, @NotNull CharSequence defaultValue) {
        return isLetter(sequence) ? sequence : defaultValue;
    }

    /**
     * If the given character sequence is letter, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static String letterOr(@Nullable String string, @NotNull String defaultValue) {
        return isLetter(string) ? string : defaultValue;
    }


    /* ******************************************* letterOrDigit ****************************************** */


    /**
     * Return `true` if the given sequence of characters is all digit or letter
     */
    public static boolean isLetterOrDigit(@Nullable CharSequence sequence) {
        if (sequence == null || isNotSafe(sequence)) return false;
        for (int index = 0, size = sequence.length(); index < size; index++) {
            if (Charx.isNotLetterOrDigit(sequence.charAt(index))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return `true` if the given sequence of characters is all not digit or letter
     */
    public static boolean isNotLetterOrDigit(@Nullable CharSequence sequence) {
        return !isLetterOrDigit(sequence);
    }

    /**
     * If the given character sequence is digit or letter, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static CharSequence letterOrDigitOr(@Nullable CharSequence sequence, @NotNull CharSequence defaultValue) {
        return isLetterOrDigit(sequence) ? sequence : defaultValue;
    }

    /**
     * If the given character sequence is digit or letter, it return is itself, otherwise it returns the default value.
     */
    @NotNull
    public static String letterOrDigitOr(@Nullable String string, @NotNull String defaultValue) {
        return isLetterOrDigit(string) ? string : defaultValue;
    }


    /* ******************************************* containsAny and containsAll ****************************************** */


    /**
     * Return true if the specified string contains any of the strings in [params]
     */
    public static boolean containsAny(@Nullable String string, @Nullable String[] params, boolean ignoreCase) {
        if (string == null || params == null || params.length == 0) return false;
        for (String param : params) if (contains(string, param, ignoreCase)) return true;
        return false;
    }

    /**
     * Return true if the specified string contains any of the strings in [params]
     */
    public static boolean containsAny(@Nullable String string, @Nullable String[] params) {
        return containsAny(string, params, false);
    }

    /**
     * Return true if the specified string contains any of the strings in [params]
     */
    public static boolean containsAny(@Nullable String string, @Nullable Collection<String> params, boolean ignoreCase) {
        if (string == null || params == null || params.size() == 0) return false;
        for (String param : params) if (contains(string, param, ignoreCase)) return true;
        return false;
    }

    /**
     * Return true if the specified string contains any of the strings in [params]
     */
    public static boolean containsAny(@Nullable String string, @Nullable Collection<String> params) {
        return containsAny(string, params, false);
    }

    /**
     * Return true if the specified string contains all the strings in [params]
     */
    public static boolean containsAll(@Nullable String string, @Nullable String[] params, boolean ignoreCase) {
        if (string == null || params == null || params.length == 0) return false;
        for (String param : params) if (!contains(string, param, ignoreCase)) return false;
        return true;
    }

    /**
     * Return true if the specified string contains all the strings in [params]
     */
    public static boolean containsAll(@Nullable String string, @Nullable String[] params) {
        return containsAll(string, params, false);
    }

    /**
     * Return true if the specified string contains all the strings in [params]
     */
    public static boolean containsAll(@Nullable String string, @Nullable Collection<String> params, boolean ignoreCase) {
        if (string == null || params == null || params.size() == 0) return false;
        for (String param : params) if (!contains(string, param, ignoreCase)) return false;
        return true;
    }

    /**
     * Return true if the specified string contains all the strings in [params]
     */
    public static boolean containsAll(@Nullable String string, @Nullable Collection<String> params) {
        return containsAll(string, params, false);
    }


    /* ******************************************* or and to ****************************************** */


    /**
     * Returns the given character sequence if it is not null, otherwise return an empty sequence of characters
     */
    @NotNull
    public static CharSequence orEmpty(@Nullable CharSequence sequence) {
        return sequence != null ? sequence : "";
    }

    /**
     * Returns the given character sequence if it is not null, otherwise return an empty sequence of characters
     */
    @NotNull
    public static String orEmpty(@Nullable String string) {
        return string != null ? string : "";
    }

    /**
     * Returns the given character sequence if it is not null, otherwise return given defaultValue
     */
    @NotNull
    public static CharSequence orDefault(@Nullable CharSequence sequence, @NotNull CharSequence defaultValue) {
        return sequence != null ? sequence : defaultValue;
    }

    /**
     * Returns the given character sequence if it is not null, otherwise return given defaultValue
     */
    @NotNull
    public static String orDefault(@Nullable String string, @NotNull String defaultValue) {
        return string != null ? string : defaultValue;
    }

    /**
     * If the given char sequence is empty, it return `null` otherwise it return itself
     */
    @Nullable
    public static CharSequence emptyToNull(@Nullable CharSequence sequence) {
        return isEmpty(sequence) ? null : sequence;
    }

    /**
     * If the given string is empty, it return `null` otherwise it return itself
     */
    @Nullable
    public static String emptyToNull(@Nullable String string) {
        return isEmpty(string) ? null : string;
    }

    /**
     * If the given char sequence is blank, it return `null` otherwise it return itself
     */
    @Nullable
    public static CharSequence blankToNull(@Nullable CharSequence sequence) {
        return isBlank(sequence) ? null : sequence;
    }

    /**
     * If the given string is blank, it return `null` otherwise it return itself
     */
    @Nullable
    public static String blankToNull(@Nullable String string) {
        return isBlank(string) ? null : string;
    }


    /* ******************************************* filterBlank ****************************************** */


    /**
     * Filter out whitespace characters in a sequence of characters
     */
    @NotNull
    public static CharSequence filterBlank(@Nullable CharSequence sequence) {
        return filter(sequence, new Predicate<Character>() {
            @Override
            public boolean accept(@NotNull Character character) {
                return Charx.isNotBlank(character);
            }
        });
    }

    /**
     * Filter out whitespace characters in a sequence of characters
     */
    @NotNull
    public static String filterBlank(@Nullable String string) {
        return filterBlank((CharSequence) string).toString();
    }


    /* ******************************************* remove ****************************************** */


    /**
     * Delete all specified characters in the string and return the new string
     */
    @NotNull
    public static String removeChar(@Nullable String string, char ch) {
        StringBuilder sb = new StringBuilder();
        if (string != null) {
            for (char cha : string.toCharArray()) {
                if (cha != ch) {
                    sb.append(cha);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Delete the first occurrence of the specified character in the string and return the new string
     */
    @NotNull
    public static String removeFirstChar(@Nullable String string, char ch) {
        StringBuilder sb = new StringBuilder();
        if (string != null) {
            boolean removed = false;
            for (int index = 0, size = count(string); index < size; index++) {
                char cha = string.charAt(index);
                if (cha != ch || removed) {
                    sb.append(cha);
                } else {
                    removed = true;
                }
            }
        }
        return sb.toString();
    }

    /**
     * Delete the last specified character in the string and return the new string
     */
    @NotNull
    public static String removeLastChar(@Nullable String string, char ch) {
        StringBuilder sb = new StringBuilder();
        if (string != null) {
            boolean removed = false;
            for (int index = count(string) - 1; index >= 0; index--) {
                char cha = string.charAt(index);
                if (cha != ch || removed) {
                    sb.insert(0, cha);
                } else {
                    removed = true;
                }
            }
        }
        return sb.toString();
    }

    /**
     * Delete the character at the specified position in the string and return the new string
     */
    @NotNull
    public static String removeIndex(@Nullable String string, int removeIndex) {
        StringBuilder sb = new StringBuilder();
        if (string != null) {
            for (int index = 0, size = count(string); index < size; index++) {
                char cha = string.charAt(index);
                if (index != removeIndex) {
                    sb.append(cha);
                }
            }
        }
        return sb.toString();
    }


    /* ******************************************* limit ****************************************** */


    /**
     * If the length of the character sequences exceeds the specified length, the character sequences is intercepted
     * and the specified suffix is ​​returned to return the new character sequences, otherwise it return itself.
     */
    @NotNull
    public static CharSequence limit(@Nullable CharSequence charSequence, final int length, @Nullable String suffix) {
        if (length < 0) {
            throw new IllegalArgumentException("Param 'length' is less than to zero.");
        }
        if (count(charSequence) <= length) return orEmpty(charSequence);

        CharSequence limitString = orEmpty(charSequence).subSequence(0, length);
        if (suffix != null) {
            return limitString + suffix;
        } else {
            return limitString;
        }
    }

    /**
     * If the length of the character sequences exceeds the specified length, the character sequences is intercepted
     * and the specified suffix is ​​returned to return the new character sequences, otherwise it return itself.
     */
    @NotNull
    public static CharSequence limit(@Nullable CharSequence charSequence, int length) {
        return limit(charSequence, length, null);
    }

    /**
     * If the length of the string exceeds the specified length, the string is intercepted and the specified suffix
     * is ​​returned to return the new string, otherwise it return itself.
     */
    @NotNull
    public static String limit(@Nullable String string, int length, @Nullable String suffix) {
        return limit((CharSequence) string, length, suffix).toString();
    }

    /**
     * If the length of the string exceeds the specified length, the string is intercepted and the specified suffix
     * is ​​returned to return the new string, otherwise it return itself.
     */
    @NotNull
    public static String limit(@Nullable String string, int length) {
        return limit((CharSequence) string, length, null).toString();
    }


    /* ******************************************* replace ****************************************** */

    /**
     * Returns a new string obtained by replacing each substring of this char sequence that matches the given [target]
     * with the given [replacement].
     * <p>
     * Compared to String.replace(), the result of "*12345*".replace("*", "\*").replace("*", "\*") is '\\*12345\\*' , and the result of replaceNoRepeat(replaceNoRepeat("*12345*", "*", "\*"), "*", "\*") is '\*12345\*'
     */
    @NotNull
    public static String replaceNoRepeat(@Nullable String string, @NotNull String target, @NotNull String replacement) {
        string = orEmpty(string);
        if (target.equals("") || !string.contains(target)) return string;

        StringBuilder builder = new StringBuilder();
        int startIndex = 0;
        while (true) {
            int targetStartIndex = startIndex < string.length() ? string.indexOf(target, startIndex) : -1;
            if (targetStartIndex == -1) {
                if (startIndex < string.length()) {
                    for (int index = startIndex; index < string.length(); index++) {
                        builder.append(string.charAt(index));
                    }
                }
                break;
            }
            int targetEndIndex = targetStartIndex + target.length();

            int itemStartIndex = targetEndIndex - replacement.length();
            String item = itemStartIndex >= 0 ? string.substring(itemStartIndex, targetEndIndex) : null;
            if (item == null || !item.equals(replacement)) {
                for (int index = startIndex; index < targetStartIndex; index++) {
                    builder.append(string.charAt(index));
                }
                builder.append(replacement);
            } else {
                for (int index = startIndex; index < targetEndIndex; index++) {
                    builder.append(string.charAt(index));
                }
            }
            startIndex = targetEndIndex;
        }
        return builder.toString();
    }


    /* ******************************************* firstLetter UpperCase or LowerCase ****************************************** */

    /**
     * Convert the first letter of a given string to uppercase
     */
    @NotNull
    public static String firstLetterUpperCase(@Nullable String string) {
        String safeString = orEmpty(string);
        if (safeString.length() <= 0) return safeString;
        char firstLetter = safeString.charAt(0);
        if (firstLetter >= 'a' && firstLetter <= 'z') {
            char[] chars = safeString.toCharArray();
            chars[0] -= 32;
            return String.valueOf(chars);
        } else {
            return safeString;
        }
    }

    /**
     * Convert the first letter of a given string to lowercase
     */
    @NotNull
    public static String firstLetterLowerCase(@Nullable String string) {
        String safeString = orEmpty(string);
        if (safeString.length() <= 0) return safeString;
        char firstLetter = safeString.charAt(0);
        if (firstLetter >= 'A' && firstLetter <= 'Z') {
            char[] chars = safeString.toCharArray();
            chars[0] += 32;
            return String.valueOf(chars);
        } else {
            return safeString;
        }
    }


    /* ******************************************* hidden *******************************************/


    /**
     * Replaces the character of the specified length at the beginning of the string with the specified character
     */
    @NotNull
    public static String hiddenStartChars(@Nullable final String input, final int hiddenLength, final char targetChar) {
        if (input == null) return "";

        final int inputLength = input.length();
        StringBuilder builder = new StringBuilder();

        for (int index = 0, size = Math.min(hiddenLength, inputLength); index < size; index++) {
            builder.append(targetChar);
        }

        if (inputLength > hiddenLength) {
            builder.append(input, hiddenLength, inputLength);
        }

        return builder.toString();
    }

    /**
     * Replace the character of the specified length at the beginning of the string with the '*' character
     */
    @NotNull
    public static String hiddenStartChars(@Nullable final String input, final int hiddenLength) {
        return hiddenStartChars(input, hiddenLength, '*');
    }

    /**
     * Replaces the specified length of characters in the middle of the string with the specified character
     */
    @NotNull
    public static String hiddenMiddleChars(@Nullable final String input, final int hiddenLength, final char targetChar) {
        if (input == null) return "";

        final int inputLength = input.length();
        StringBuilder builder = new StringBuilder();

        if (inputLength > hiddenLength) {
            builder.append(input, 0, (inputLength - hiddenLength) / 2);
        }

        for (int index = 0, size = Math.min(hiddenLength, inputLength); index < size; index++) {
            builder.append(targetChar);
        }

        if (inputLength > hiddenLength) {
            builder.append(input, hiddenLength + ((inputLength - hiddenLength) / 2), inputLength);
        }

        return builder.toString();
    }

    /**
     * Replace the character of the specified length in the middle of the string with the '*' character
     */
    @NotNull
    public static String hiddenMiddleChars(@Nullable final String input, final int hiddenLength) {
        return hiddenMiddleChars(input, hiddenLength, '*');
    }

    /**
     * Replaces the specified length of characters at the end of the string with the specified character
     */
    @NotNull
    public static String hiddenEndChars(@Nullable final String input, final int hiddenLength, final char targetChar) {
        if (input == null) return "";

        final int inputLength = input.length();
        StringBuilder builder = new StringBuilder();

        if (inputLength > hiddenLength) {
            builder.append(input, 0, inputLength - hiddenLength);
        }

        for (int index = 0, size = Math.min(hiddenLength, inputLength); index < size; index++) {
            builder.append(targetChar);
        }

        return builder.toString();
    }

    /**
     * Replace the character of the specified length at the end of the string with the '*' character
     */
    @NotNull
    public static String hiddenEndChars(@Nullable final String input, final int hiddenLength) {
        return hiddenEndChars(input, hiddenLength, '*');
    }

    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    /* ******************************************* isBlank ****************************************** */


    /**
     * Returns `true` if this string is empty or consists solely of whitespace characters.
     */
    public static boolean isBlank(@Nullable CharSequence sequence) {
        if (sequence == null) return false;
        for (int index = 0, size = sequence.length(); index < size; index++) {
            if (Charx.isNotBlank(sequence.charAt(index))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns `true` if this char sequence is not empty and contains some characters except of whitespace characters.
     */
    public static boolean isNotBlank(@Nullable CharSequence sequence) {
        return !isBlank(sequence);
    }

    /**
     * Returns `true` if this nullable char sequence is either `null` or empty or consists solely of whitespace characters.
     */
    public static boolean isNullOrBlank(@Nullable CharSequence sequence) {
        return sequence == null || isBlank(sequence);
    }


    /* ******************************************* isEmpty ****************************************** */


    /**
     * Returns `true` if this char sequence is empty (contains no characters).
     */
    public static boolean isEmpty(@Nullable CharSequence sequence) {
        return sequence != null && sequence.length() == 0;
    }

    /**
     * Returns `true` if this char sequence is not empty.
     */
    public static boolean isNotEmpty(@Nullable CharSequence sequence) {
        return sequence != null && sequence.length() > 0;
    }

    /**
     * Returns `true` if this nullable char sequence is either `null` or empty.
     */
    public static boolean isNullOrEmpty(@Nullable CharSequence sequence) {
        return sequence == null || sequence.length() == 0;
    }


    /* ******************************************* other ****************************************** */


    /**
     * Returns the range of valid character indices for this char sequence.
     */
    @NotNull
    public static IntRange indices(@Nullable CharSequence charSequence) {
        return new IntRange(0, count(charSequence) - 1);
    }

    /**
     * Returns the index of the last character in the char sequence or -1 if it is empty.
     */
    public static int lastIndex(@Nullable CharSequence charSequence) {
        return count(charSequence) - 1;
    }

    /**
     * Returns `true` if this CharSequence has Unicode surrogate pair at the specified [index].
     */
    public static boolean hasSurrogatePairAt(@Nullable CharSequence charSequence, int index) {
        return charSequence != null && index >= 0 && index <= charSequence.length() - 2
                && Character.isHighSurrogate(charSequence.charAt(index))
                && Character.isLowSurrogate(charSequence.charAt(index + 1));
    }


    /* ******************************************* startsWith ****************************************** */


    /**
     * Returns `true` if this char sequence starts with the specified character.
     */
    public static boolean startsWith(@Nullable CharSequence charSequence, char cha, boolean ignoreCase) {
        return charSequence != null && count(charSequence) > 0 && Charx.equals(charSequence.charAt(0), cha, ignoreCase);
    }

    /**
     * Returns `true` if this char sequence starts with the specified character.
     */
    public static boolean startsWith(@Nullable CharSequence charSequence, char cha) {
        return startsWith(charSequence, cha, false);
    }

    /**
     * Returns `true` if this char sequence starts with the specified prefix.
     */
    public static boolean startsWith(@Nullable CharSequence charSequence, @NotNull CharSequence prefix, boolean ignoreCase) {
        if (!ignoreCase && charSequence instanceof String && prefix instanceof String) {
            return ((String) charSequence).startsWith((String) prefix);
        } else {
            return regionMatchesImpl(charSequence, 0, prefix, 0, prefix.length(), ignoreCase);
        }
    }

    /**
     * Returns `true` if this char sequence starts with the specified prefix.
     */
    public static boolean startsWith(@Nullable CharSequence charSequence, @NotNull CharSequence prefix) {
        return startsWith(charSequence, prefix, false);
    }

    /**
     * Returns `true` if a substring of this char sequence starting at the specified offset [startIndex] starts with the specified prefix.
     */
    public static boolean startsWith(@Nullable CharSequence charSequence, @NotNull CharSequence prefix, int startIndex, boolean ignoreCase) {
        if (!ignoreCase && charSequence instanceof String && prefix instanceof String) {
            return ((String) charSequence).startsWith((String) prefix, startIndex);
        } else {
            return regionMatchesImpl(charSequence, startIndex, prefix, 0, prefix.length(), ignoreCase);
        }
    }

    /**
     * Returns `true` if a substring of this char sequence starting at the specified offset [startIndex] starts with the specified prefix.
     */
    public static boolean startsWith(@Nullable CharSequence charSequence, @NotNull CharSequence prefix, int startIndex) {
        return startsWith(charSequence, prefix, startIndex, false);
    }

    /**
     * Returns `true` if this string starts with the specified prefix.
     */
    public static boolean startsWith(@Nullable String string, @NotNull String prefix, boolean ignoreCase) {
        if (!ignoreCase) {
            return string != null && string.startsWith(prefix);
        } else {
            //noinspection ConstantConditions
            return regionMatches(string, 0, prefix, 0, prefix.length(), ignoreCase);
        }
    }

    /**
     * Returns `true` if this string starts with the specified prefix.
     */
    public static boolean startsWith(@Nullable String string, @NotNull String prefix) {
        return startsWith(string, prefix, false);
    }

    /**
     * Returns `true` if a substring of this string starting at the specified offset [startIndex] starts with the specified prefix.
     */
    public static boolean startsWith(@Nullable String string, @NotNull String prefix, int startIndex, boolean ignoreCase) {
        if (!ignoreCase) {
            return string != null && string.startsWith(prefix, startIndex);
        } else {
            //noinspection ConstantConditions
            return regionMatches(string, startIndex, prefix, 0, prefix.length(), ignoreCase);
        }
    }

    /**
     * Returns `true` if a substring of this string starting at the specified offset [startIndex] starts with the specified prefix.
     */
    public static boolean startsWith(@Nullable String string, @NotNull String prefix, int startIndex) {
        return startsWith(string, prefix, startIndex, false);
    }


    /* ******************************************* endsWith ****************************************** */


    /**
     * Returns `true` if this char sequence ends with the specified character.
     */
    public static boolean endsWith(@Nullable CharSequence charSequence, char cha, boolean ignoreCase) {
        return charSequence != null && count(charSequence) > 0 && Charx.equals(charSequence.charAt(count(charSequence) - 1), cha, ignoreCase);
    }

    /**
     * Returns `true` if this char sequence ends with the specified character.
     */
    public static boolean endsWith(@Nullable CharSequence charSequence, char cha) {
        return endsWith(charSequence, cha, false);
    }

    /**
     * Returns `true` if this char sequence ends with the specified suffix.
     */
    public static boolean endsWith(@Nullable CharSequence charSequence, @NotNull CharSequence suffix, boolean ignoreCase) {
        if (!ignoreCase && charSequence instanceof String && suffix instanceof String) {
            return ((String) charSequence).endsWith((String) suffix);
        } else {
            return regionMatchesImpl(charSequence, count(charSequence) - suffix.length(), suffix, 0, suffix.length(), ignoreCase);
        }
    }

    /**
     * Returns `true` if this char sequence ends with the specified suffix.
     */
    public static boolean endsWith(@Nullable CharSequence charSequence, @NotNull CharSequence suffix) {
        return endsWith(charSequence, suffix, false);
    }

    /**
     * Returns `true` if this string ends with the specified suffix.
     */
    public static boolean endsWith(@Nullable String string, @NotNull String suffix, boolean ignoreCase) {
        if (!ignoreCase) {
            return string != null && string.endsWith(suffix);
        } else {
            //noinspection ConstantConditions
            return regionMatches(string, count(string) - suffix.length(), suffix, 0, suffix.length(), ignoreCase);
        }
    }

    /**
     * Returns `true` if this string ends with the specified suffix.
     */
    public static boolean endsWith(@Nullable String string, @NotNull String suffix) {
        return endsWith(string, suffix, false);
    }


    /* ******************************************* startsWith ****************************************** */


    /**
     * Returns `true` if this string is equal to [other], optionally ignoring character case.
     *
     * @param ignoreCase `true` to ignore character case when comparing strings.
     */
    public static boolean equals(@Nullable String self, @Nullable String other, boolean ignoreCase) {
        if (self == null) {
            return other == null;
        } else if (!ignoreCase) {
            return self.equals(other);
        } else {
            return self.equalsIgnoreCase(other);
        }
    }

    /**
     * Returns `true` if this string is equal to [other], no ignoring character case.
     */
    public static boolean equals(@Nullable String self, @Nullable String other) {
        return equals(self, other, false);
    }


    /* ******************************************* remove ****************************************** */


    /**
     * Returns a char sequence with content of this char sequence where its part at the given range is removed.
     *
     * @param startIndex the index of the first character to be removed.
     * @param endIndex   the index of the first character after the removed part to keep in the string.
     *                   <p>
     *                   [endIndex] is not included in the removed part.
     */
    @NotNull
    public static CharSequence removeRange(@Nullable CharSequence charSequence, int startIndex, int endIndex) {
        final CharSequence finalCharSequence = orEmpty(charSequence);
        if (finalCharSequence.length() <= 0) return "";
        if (endIndex < startIndex)
            throw new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
        if (endIndex == startIndex) return finalCharSequence.subSequence(0, count(finalCharSequence));

        StringBuilder sb = new StringBuilder();
        sb.append(finalCharSequence, 0, startIndex);
        sb.append(finalCharSequence, endIndex, count(finalCharSequence));
        return sb;
    }

    /**
     * Removes the part of a string at a given range.
     *
     * @param startIndex the index of the first character to be removed.
     * @param endIndex   the index of the first character after the removed part to keep in the string.
     *                   <p>
     *                   [endIndex] is not included in the removed part.
     */
    @NotNull
    public static String removeRange(@Nullable String string, int startIndex, int endIndex) {
        return removeRange((CharSequence) string, startIndex, endIndex).toString();
    }

    /**
     * Returns a char sequence with content of this char sequence where its part at the given [range] is removed.
     * <p>
     * The end index of the [range] is included in the removed part.
     */
    @NotNull
    public static CharSequence removeRange(@Nullable CharSequence charSequence, @NotNull IntRange range) {
        return removeRange(charSequence, range.getStart(), range.getEndInclusive() + 1);
    }

    /**
     * Removes the part of a string at the given [range].
     * <p>
     * The end index of the [range] is included in the removed part.
     */
    @NotNull
    public static String removeRange(@Nullable String string, @NotNull IntRange range) {
        return removeRange((CharSequence) string, range).toString();
    }

    /**
     * If this char sequence starts with the given [prefix], returns a new char sequence
     * with the prefix removed. Otherwise, returns a new char sequence with the same characters.
     */
    @NotNull
    public static CharSequence removePrefix(@Nullable CharSequence charSequence, @NotNull CharSequence prefix) {
        final CharSequence finalCharSequence = orEmpty(charSequence);
        if (startsWith(finalCharSequence, prefix))
            return finalCharSequence.subSequence(prefix.length(), count(finalCharSequence));
        return finalCharSequence.subSequence(0, count(finalCharSequence));
    }

    /**
     * If this string starts with the given [prefix], returns a copy of this string
     * with the prefix removed. Otherwise, returns this string.
     */
    @NotNull
    public static String removePrefix(@Nullable String string, @NotNull CharSequence prefix) {
        final String finalString = orEmpty(string);
        if (startsWith(finalString, prefix)) return finalString.substring(prefix.length());
        return finalString;
    }

    /**
     * If this char sequence ends with the given [suffix], returns a new char sequence
     * with the suffix removed. Otherwise, returns a new char sequence with the same characters.
     */
    @NotNull
    public static CharSequence removeSuffix(@Nullable CharSequence charSequence, @NotNull CharSequence suffix) {
        final CharSequence finalCharSequence = orEmpty(charSequence);
        if (endsWith(charSequence, suffix))
            return finalCharSequence.subSequence(0, count(finalCharSequence) - suffix.length());
        return finalCharSequence.subSequence(0, count(finalCharSequence));
    }

    /**
     * If this string ends with the given [suffix], returns a copy of this string
     * with the suffix removed. Otherwise, returns this string.
     */
    @NotNull
    public static String removeSuffix(@Nullable String string, @NotNull CharSequence suffix) {
        final String finalString = orEmpty(string);
        if (endsWith(finalString, suffix)) return finalString.substring(0, count(finalString) - suffix.length());
        return finalString;
    }

    /**
     * When this char sequence starts with the given [prefix] and ends with the given [suffix],
     * returns a new char sequence having both the given [prefix] and [suffix] removed.
     * Otherwise returns a new char sequence with the same characters.
     */
    @NotNull
    public static CharSequence removeSurrounding(@Nullable CharSequence charSequence, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        final CharSequence finalCharSequence = orEmpty(charSequence);
        if ((count(finalCharSequence) >= prefix.length() + suffix.length()) && startsWith(finalCharSequence, prefix) && endsWith(finalCharSequence, suffix)) {
            return finalCharSequence.subSequence(prefix.length(), count(finalCharSequence) - suffix.length());
        }
        return finalCharSequence.subSequence(0, count(finalCharSequence));
    }

    /**
     * Removes from a string both the given [prefix] and [suffix] if and only if
     * it starts with the [prefix] and ends with the [suffix].
     * Otherwise returns this string unchanged.
     */
    @NotNull
    public static String removeSurrounding(@Nullable String string, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        final String finalString = orEmpty(string);
        if ((count(finalString) >= prefix.length() + suffix.length()) && startsWith(finalString, prefix) && endsWith(finalString, suffix)) {
            return finalString.substring(prefix.length(), count(finalString) - suffix.length());
        }
        return finalString;
    }

    /**
     * When this char sequence starts with and ends with the given [delimiter],
     * returns a new char sequence having this [delimiter] removed both from the start and end.
     * Otherwise returns a new char sequence with the same characters.
     */
    @NotNull
    public static CharSequence removeSurrounding(@Nullable CharSequence charSequence, @NotNull CharSequence delimiter) {
        return removeSurrounding(charSequence, delimiter, delimiter);
    }

    /**
     * Removes the given [delimiter] string from both the start and the end of this string
     * if and only if it starts with and ends with the [delimiter].
     * Otherwise returns this string unchanged.
     */
    @NotNull
    public static String removeSurrounding(@Nullable String string, @NotNull CharSequence delimiter) {
        return removeSurrounding(string, delimiter, delimiter);
    }


    /* ******************************************* capitalize ****************************************** */


    /**
     * Returns a copy of this string having its first letter uppercased, or the original string,
     * if it's empty or already starts with an upper case letter.
     */
    @NotNull
    public static String capitalize(@Nullable String string) {
        return isNotEmpty(string) && isLowerCase(string.charAt(0)) ? string.substring(0, 1).toUpperCase() + string.substring(1) : orEmpty(string);
    }

    /**
     * Returns a copy of this string having its first letter lowercased, or the original string,
     * if it's empty or already starts with a lower case letter.
     */
    @NotNull
    public static String decapitalize(@Nullable String string) {
        return isNotEmpty(string) && isUpperCase(string.charAt(0)) ? string.substring(0, 1).toLowerCase() + string.substring(1) : orEmpty(string);
    }


    /* ******************************************* pad ****************************************** */


    /**
     * Returns a char sequence with content of this char sequence padded at the beginning
     * to the specified [length] with the specified character or space.
     *
     * @param length  the desired string length.
     * @param padChar the character to pad string with, if it has length less than the [length] specified. Space is used by default.
     * @return Returns a string, of length at least [length], consisting of string prepended with [padChar] as many times.
     * as are necessary to reach that length.
     */
    @NotNull
    public static CharSequence padStart(@Nullable CharSequence charSequence, final int length, char padChar) {
        if (length < 0) {
            throw new IllegalArgumentException("Param 'length' is less than to zero.");
        }
        final CharSequence finalCharSequence = orEmpty(charSequence);
        if (length <= count(finalCharSequence))
            return finalCharSequence.subSequence(0, count(finalCharSequence));

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0, size = length - count(finalCharSequence); i < size; i++) {
            sb.append(padChar);
        }
        sb.append(finalCharSequence);
        return sb;
    }

    /**
     * Returns a char sequence with content of this char sequence padded at the beginning
     * to the specified [length] with the specified character or space. Default padding ' '
     *
     * @param length the desired string length.
     * @return Returns a string, of length at least [length], consisting of string prepended with [padChar] as many times.
     * as are necessary to reach that length.
     */
    @NotNull
    public static CharSequence padStart(@Nullable CharSequence charSequence, int length) {
        return padStart(charSequence, length, ' ');
    }

    /**
     * Pads the string to the specified [length] at the beginning with the specified character or space.
     *
     * @param length  the desired string length.
     * @param padChar the character to pad string with, if it has length less than the [length] specified. Space is used by default.
     * @return Returns a string, of length at least [length], consisting of string prepended with [padChar] as many times.
     * as are necessary to reach that length.
     */
    @NotNull
    public static String padStart(@Nullable String string, int length, char padChar) {
        return padStart((CharSequence) string, length, padChar).toString();
    }

    /**
     * Pads the string to the specified [length] at the beginning with the specified character or space. Default padding ' '
     *
     * @param length the desired string length.
     * @return Returns a string, of length at least [length], consisting of string prepended with [padChar] as many times.
     * as are necessary to reach that length.
     */
    @NotNull
    public static String padStart(@Nullable String string, int length) {
        return padStart((CharSequence) string, length, ' ').toString();
    }


    /**
     * Returns a char sequence with content of this char sequence padded at the end
     * to the specified [length] with the specified character or space.
     *
     * @param length  the desired string length.
     * @param padChar the character to pad string with, if it has length less than the [length] specified. Space is used by default.
     * @return Returns a string, of length at least [length], consisting of string prepended with [padChar] as many times.
     * as are necessary to reach that length.
     */
    @NotNull
    public static CharSequence padEnd(@Nullable CharSequence charSequence, final int length, char padChar) {
        if (length < 0) {
            throw new IllegalArgumentException("Param 'length' is less than to zero.");
        }
        final CharSequence finalCharSequence = orEmpty(charSequence);
        if (length <= count(finalCharSequence))
            return finalCharSequence.subSequence(0, count(finalCharSequence));

        StringBuilder sb = new StringBuilder(length);
        sb.append(finalCharSequence);
        for (int i = 0, size = length - count(finalCharSequence); i < size; i++) {
            sb.append(padChar);
        }
        return sb;
    }

    /**
     * Returns a char sequence with content of this char sequence padded at the end
     * to the specified [length] with the specified character or space. Default padding ' '
     *
     * @param length the desired string length.
     * @return Returns a string, of length at least [length], consisting of string prepended with [padChar] as many times.
     * as are necessary to reach that length.
     */
    @NotNull
    public static CharSequence padEnd(@Nullable CharSequence charSequence, int length) {
        return padEnd(charSequence, length, ' ');
    }

    /**
     * Pads the string to the specified [length] at the end with the specified character or space.
     *
     * @param length  the desired string length.
     * @param padChar the character to pad string with, if it has length less than the [length] specified. Space is used by default.
     * @return Returns a string, of length at least [length], consisting of string prepended with [padChar] as many times.
     * as are necessary to reach that length.
     */
    @NotNull
    public static String padEnd(@Nullable String string, int length, char padChar) {
        return padEnd((CharSequence) string, length, padChar).toString();
    }

    /**
     * Pads the string to the specified [length] at the end with the specified character or space. Default padding ' '
     *
     * @param length the desired string length.
     * @return Returns a string, of length at least [length], consisting of string prepended with [padChar] as many times.
     * as are necessary to reach that length.
     */
    @NotNull
    public static String padEnd(@Nullable String string, int length) {
        return padEnd((CharSequence) string, length, ' ').toString();
    }


    /* ******************************************* matches ****************************************** */


    /**
     * Returns `true` if this char sequence matches the given regular expression.
     */
    public static boolean matches(@Nullable CharSequence charSequence, @NotNull Pattern regex) {
        return charSequence != null && regex.matcher(charSequence).matches();
    }

    /**
     * Returns `true` if the specified range in this string is equal to the specified range in another string.
     *
     * @param thisOffset  the start offset in this string of the substring to compare.
     * @param other       the string against a substring of which the comparison is performed.
     * @param otherOffset the start offset in the other string of the substring to compare.
     * @param length      the length of the substring to compare.
     */
    public static boolean regionMatches(@Nullable String self, int thisOffset, @Nullable String other, int otherOffset, int length, boolean ignoreCase) {
        if (!ignoreCase) {
            return orEmpty(self).regionMatches(thisOffset, orEmpty(other), otherOffset, length);
        } else {
            //noinspection ConstantConditions
            return orEmpty(self).regionMatches(ignoreCase, thisOffset, orEmpty(other), otherOffset, length);
        }
    }

    /**
     * Returns `true` if the specified range in this string is equal to the specified range in another string.
     *
     * @param thisOffset  the start offset in this string of the substring to compare.
     * @param other       the string against a substring of which the comparison is performed.
     * @param otherOffset the start offset in the other string of the substring to compare.
     * @param length      the length of the substring to compare.
     */
    public static boolean regionMatches(@Nullable String self, int thisOffset, @Nullable String other, int otherOffset, int length) {
        return regionMatches(self, thisOffset, other, otherOffset, length, false);
    }

    /**
     * Implementation of [regionMatches] for CharSequences.
     * Invoked when it's already known that arguments are not Strings, so that no additional type checks are performed.
     */
    public static boolean regionMatchesImpl(@Nullable CharSequence self, int thisOffset, @Nullable CharSequence other, int otherOffset, int length, boolean ignoreCase) {
        final CharSequence finalSelf = orEmpty(self);
        final CharSequence finalOther = orEmpty(other);
        if ((otherOffset < 0) || (thisOffset < 0) || (thisOffset > finalSelf.length() - length)
                || (otherOffset > finalOther.length() - length)) {
            return false;
        }

        for (int index = 0; index < length; index++) {
            if (!Charx.equals(finalSelf.charAt(thisOffset + index), (finalOther.charAt(otherOffset + index)), ignoreCase)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Implementation of [regionMatches] for CharSequences.
     * Invoked when it's already known that arguments are not Strings, so that no additional type checks are performed.
     */
    public static boolean regionMatchesImpl(@Nullable CharSequence self, int thisOffset, @Nullable CharSequence other, int otherOffset, int length) {
        return regionMatchesImpl(self, thisOffset, other, otherOffset, length, false);
    }


    /* ******************************************* find ****************************************** */


    /**
     * Returns the first character matching the given [predicate], or `null` if no such character was found.
     */
    @Nullable
    public static Character find(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        return firstOrNull(charSequence, predicate);
    }

    /**
     * Returns the last character matching the given [predicate], or `null` if no such character was found.
     */
    @Nullable
    public static Character findLast(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        return lastOrNull(charSequence, predicate);
    }


    @Nullable
    private static Pair<Integer, String> findAnyOf(@Nullable final CharSequence charSequence, @NotNull Collection<String> strings,
                                                   int startIndex, final boolean ignoreCase, boolean last) {
        final CharSequence finalCharSequence = orEmpty(charSequence);

        if (!ignoreCase && strings.size() == 1) {
            String string;
            if (strings instanceof List) {
                string = ((List<String>) strings).get(0);
            } else {
                Iterator<String> iterator = strings.iterator();
                if (!iterator.hasNext()) {
                    throw new NoSuchElementException("Collection is empty.");
                }
                String single = iterator.next();
                if (iterator.hasNext()) {
                    throw new IllegalArgumentException("Collection has more than one element.");
                } else {
                    string = single;
                }
            }
            int index = !last ? indexOf(finalCharSequence, string, startIndex, false) : lastIndexOf(finalCharSequence, string, startIndex, false);
            return index < 0 ? null : Pair.of(index, string);
        }

        IntProgression indices = !last
                ? new IntRange(Math.max(startIndex, 0), count(finalCharSequence))
                : new IntProgression(Math.min(startIndex, count(finalCharSequence) - 1), 0, -1);

        if (finalCharSequence instanceof String) {
            for (final int index : indices) {
                String matchingString = null;
                for (String s : strings) {
                    if (regionMatches(s, 0, (String) finalCharSequence, index, count(s), ignoreCase)) {
                        matchingString = s;
                        break;
                    }
                }
                if (matchingString != null) {
                    return Pair.of(index, matchingString);
                }
            }
        } else {
            for (final int index : indices) {
                String matchingString = null;
                for (String s : strings) {
                    if (regionMatchesImpl(s, 0, finalCharSequence, index, count(s), ignoreCase)) {
                        matchingString = s;
                        break;
                    }
                }
                if (matchingString != null) {
                    return Pair.of(index, matchingString);
                }
            }
        }

        return null;
    }

    /**
     * Finds the first occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return A pair of an index of the first occurrence of matched string from [strings] and the string matched
     * or `null` if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the beginning to the end of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    @Nullable
    public static Pair<Integer, String> findAnyOf(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        return findAnyOf(charSequence, strings, startIndex, ignoreCase, false);
    }

    /**
     * Finds the first occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return A pair of an index of the first occurrence of matched string from [strings] and the string matched
     * or `null` if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the beginning to the end of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    @Nullable
    public static Pair<Integer, String> findAnyOf(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, boolean ignoreCase) {
        return findAnyOf(charSequence, strings, 0, ignoreCase, false);
    }

    /**
     * Finds the first occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @return A pair of an index of the first occurrence of matched string from [strings] and the string matched
     * or `null` if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the beginning to the end of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    @Nullable
    public static Pair<Integer, String> findAnyOf(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, int startIndex) {
        return findAnyOf(charSequence, strings, startIndex, false, false);
    }

    /**
     * Finds the first occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @return A pair of an index of the first occurrence of matched string from [strings] and the string matched
     * or `null` if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the beginning to the end of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    @Nullable
    public static Pair<Integer, String> findAnyOf(@Nullable CharSequence charSequence, @NotNull Collection<String> strings) {
        return findAnyOf(charSequence, strings, 0, false, false);
    }

    /**
     * Finds the last occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return A pair of an index of the last occurrence of matched string from [strings] and the string matched or `null` if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the end toward the beginning of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    @Nullable
    public static Pair<Integer, String> findLastAnyOf(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        return findAnyOf(charSequence, strings, startIndex, ignoreCase, true);
    }

    /**
     * Finds the last occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
     * @return A pair of an index of the last occurrence of matched string from [strings] and the string matched or `null` if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the end toward the beginning of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    @Nullable
    public static Pair<Integer, String> findLastAnyOf(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, int startIndex) {
        return findAnyOf(charSequence, strings, startIndex, false, true);
    }

    /**
     * Finds the last occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return A pair of an index of the last occurrence of matched string from [strings] and the string matched or `null` if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the end toward the beginning of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    @Nullable
    public static Pair<Integer, String> findLastAnyOf(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, boolean ignoreCase) {
        return findAnyOf(charSequence, strings, count(charSequence) - 1, ignoreCase, true);
    }

    /**
     * Finds the last occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @return A pair of an index of the last occurrence of matched string from [strings] and the string matched or `null` if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the end toward the beginning of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    @Nullable
    public static Pair<Integer, String> findLastAnyOf(@Nullable CharSequence charSequence, @NotNull Collection<String> strings) {
        return findAnyOf(charSequence, strings, count(charSequence) - 1, false, true);
    }


    /* ******************************************* first ****************************************** */


    /**
     * Returns first character.
     *
     * @throws NoSuchElementException if the char sequence is empty.
     */
    @NotNull
    public static Character first(@Nullable CharSequence charSequence) {
        if (isNullOrEmpty(charSequence)) throw new NoSuchElementException("Char sequence is empty.");
        return charSequence.charAt(0);
    }

    /**
     * Returns the first character matching the given [predicate].
     *
     * @throws NoSuchElementException if no such character is found.
     */
    @NotNull
    public static Character first(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        for (char element : iterable(charSequence)) if (predicate.accept(element)) return element;
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    /**
     * Returns the first character, or `null` if the char sequence is empty.
     */
    @Nullable
    public static Character firstOrNull(@Nullable CharSequence charSequence) {
        return isNullOrEmpty(charSequence) ? null : charSequence.charAt(0);
    }

    /**
     * Returns the first character matching the given [predicate], or `null` if character was not found.
     */
    @Nullable
    public static Character firstOrNull(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        for (char element : iterable(charSequence)) if (predicate.accept(element)) return element;
        return null;
    }


    /* ******************************************* last ****************************************** */


    /**
     * Returns the last character.
     *
     * @throws NoSuchElementException if the char sequence is empty.
     */
    @NotNull
    public static Character last(@Nullable CharSequence charSequence) {
        if (isNullOrEmpty(charSequence))
            throw new NoSuchElementException("Char sequence is empty.");
        return charSequence.charAt(count(charSequence) - 1);
    }

    /**
     * Returns the last character matching the given [predicate].
     *
     * @throws NoSuchElementException if no such character is found.
     */
    @NotNull
    public static Character last(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        if (charSequence != null) {
            for (int index = charSequence.length() - 1; index >= 0; index--) {
                char element = charSequence.charAt(index);
                if (predicate.accept(element)) return element;
            }
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    /**
     * Returns the last character, or `null` if the char sequence is empty.
     */
    @Nullable
    public static Character lastOrNull(@Nullable CharSequence charSequence) {
        return isNullOrEmpty(charSequence) ? null : charSequence.charAt(count(charSequence) - 1);
    }

    /**
     * Returns the last character matching the given [predicate], or `null` if no such character was found.
     */
    @Nullable
    public static Character lastOrNull(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        if (charSequence != null) {
            for (int index = charSequence.length() - 1; index >= 0; index--) {
                char element = charSequence.charAt(index);
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }


    /* ******************************************* get ****************************************** */


    /**
     * Returns a character at the given [index] or the result of calling the [defaultValue] staticction if the [index] is out of bounds of this char sequence.
     */
    @NotNull
    public static Character getOrElse(@Nullable CharSequence charSequence, int index, @NotNull IndexedDefaultValue<Character> defaultValue) {
        return charSequence != null && index >= 0 && index <= lastIndex(charSequence) ? charSequence.charAt(index) : defaultValue.get(index);
    }

    /**
     * Returns a character at the given [index] or `null` if the [index] is out of bounds of this char sequence.
     */
    @Nullable
    public static Character getOrNull(@Nullable CharSequence charSequence, int index) {
        return charSequence != null && index >= 0 && index <= lastIndex(charSequence) ? charSequence.charAt(index) : null;
    }


    /* ******************************************* indexOf ****************************************** */


    private static int indexOf(@Nullable CharSequence self, @Nullable CharSequence other, int startIndex, int endIndex, boolean ignoreCase, boolean last) {
        final CharSequence finalSelf = orEmpty(self);
        final CharSequence finalOther = orEmpty(other);

        IntProgression indices = !last
                ? new IntRange(Math.max(startIndex, 0), Math.min(endIndex, count(finalSelf)))
                : new IntProgression(Math.min(startIndex, count(finalSelf) - 1), Math.max(endIndex, 0), -1);

        if (finalSelf instanceof String && finalOther instanceof String) {
            for (int index : indices) {
                if (regionMatches((String) finalOther, 0, (String) finalSelf, index, finalOther.length(), ignoreCase)) {
                    return index;
                }
            }
        } else {
            for (int index : indices) {
                if (regionMatchesImpl(finalOther, 0, finalSelf, index, finalOther.length(), ignoreCase))
                    return index;
            }
        }
        return -1;
    }

    /**
     * Returns the index within this string of the first occurrence of the specified character, starting from the specified [startIndex].
     *
     * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
     * @return An index of the first occurrence of [char] or -1 if none is found.
     */
    public static int indexOf(@Nullable CharSequence charSequence, char delimiter, int startIndex, boolean ignoreCase) {
        if (ignoreCase || !(charSequence instanceof String)) {
            return indexOfAny(charSequence, new char[]{delimiter}, startIndex, ignoreCase);
        } else {
            return ((String) charSequence).indexOf(delimiter, startIndex);
        }
    }

    /**
     * Returns the index within this string of the first occurrence of the specified character, starting from the specified [startIndex].
     *
     * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
     * @return An index of the first occurrence of [char] or -1 if none is found.
     */
    public static int indexOf(@Nullable CharSequence charSequence, char delimiter, boolean ignoreCase) {
        return indexOf(charSequence, delimiter, 0, ignoreCase);
    }

    /**
     * Returns the index within this string of the first occurrence of the specified character, starting from the specified [startIndex].
     *
     * @return An index of the first occurrence of [char] or -1 if none is found.
     */
    public static int indexOf(@Nullable CharSequence charSequence, char delimiter, int startIndex) {
        return indexOf(charSequence, delimiter, startIndex, false);
    }

    /**
     * Returns the index within this string of the first occurrence of the specified character, starting from the specified [startIndex].
     *
     * @return An index of the first occurrence of [char] or -1 if none is found.
     */
    public static int indexOf(@Nullable CharSequence charSequence, char delimiter) {
        return indexOf(charSequence, delimiter, 0, false);
    }

    /**
     * Returns the index within this char sequence of the first occurrence of the specified [string],
     * starting from the specified [startIndex].
     *
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return An index of the first occurrence of [string] or `-1` if none is found.
     */
    public static int indexOf(@Nullable CharSequence charSequence, @NotNull String string, int startIndex, boolean ignoreCase) {
        if (ignoreCase || !(charSequence instanceof String)) {
            return indexOf(charSequence, string, startIndex, count(charSequence), ignoreCase, false);
        } else {
            return ((String) charSequence).indexOf(string, startIndex);
        }
    }

    /**
     * Returns the index within this char sequence of the first occurrence of the specified [string],
     * starting from the specified [startIndex].
     *
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return An index of the first occurrence of [string] or `-1` if none is found.
     */
    public static int indexOf(@Nullable CharSequence charSequence, @NotNull String string, boolean ignoreCase) {
        return indexOf(charSequence, string, 0, ignoreCase);
    }

    /**
     * Returns the index within this char sequence of the first occurrence of the specified [string],
     * starting from the specified [startIndex].
     *
     * @return An index of the first occurrence of [string] or `-1` if none is found.
     */
    public static int indexOf(@Nullable CharSequence charSequence, @NotNull String string, int startIndex) {
        return indexOf(charSequence, string, startIndex, false);
    }

    /**
     * Returns the index within this char sequence of the first occurrence of the specified [string],
     * starting from the specified [startIndex].
     *
     * @return An index of the first occurrence of [string] or `-1` if none is found.
     */
    public static int indexOf(@Nullable CharSequence charSequence, @NotNull String string) {
        return indexOf(charSequence, string, 0, false);
    }


    /**
     * Finds the index of the first occurrence of any of the specified [chars] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
     * @return An index of the first occurrence of matched character from [chars] or -1 if none of [chars] are found.
     */
    public static int indexOfAny(@Nullable CharSequence charSequence, @NotNull char[] chars, int startIndex, final boolean ignoreCase) {
        if (!ignoreCase && chars.length == 1 && charSequence instanceof String) {
            char charValue = chars[0];
            return ((String) charSequence).indexOf(charValue, startIndex);
        } else {
            if (charSequence != null) {
                for (int index = Math.max(startIndex, 0), size = count(charSequence); index < size; index++) {
                    final char charAtIndex = charSequence.charAt(index);
                    boolean find = false;
                    for (char aChar : chars) {
                        if (Charx.equals(aChar, charAtIndex, ignoreCase)) {
                            find = true;
                            break;
                        }
                    }
                    if (find) {
                        return index;
                    }
                }
            }
            return -1;
        }
    }

    /**
     * Finds the index of the first occurrence of any of the specified [chars] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @return An index of the first occurrence of matched character from [chars] or -1 if none of [chars] are found.
     */
    public static int indexOfAny(@Nullable CharSequence charSequence, char[] chars, int startIndex) {
        return indexOfAny(charSequence, chars, startIndex, false);
    }

    /**
     * Finds the index of the first occurrence of any of the specified [chars] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @return An index of the first occurrence of matched character from [chars] or -1 if none of [chars] are found.
     */
    public static int indexOfAny(@Nullable CharSequence charSequence, char[] chars, final boolean ignoreCase) {
        return indexOfAny(charSequence, chars, 0, ignoreCase);
    }

    /**
     * Finds the index of the first occurrence of any of the specified [chars] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @return An index of the first occurrence of matched character from [chars] or -1 if none of [chars] are found.
     */
    public static int indexOfAny(@Nullable CharSequence charSequence, char[] chars) {
        return indexOfAny(charSequence, chars, 0, false);
    }

    /**
     * Finds the index of the first occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return An index of the first occurrence of matched string from [strings] or -1 if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the beginning to the end of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    public static int indexOfAny(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Pair<Integer, String> pair = findAnyOf(charSequence, strings, startIndex, ignoreCase, false);
        return pair != null ? pair.first : -1;
    }

    /**
     * Finds the index of the first occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @return An index of the first occurrence of matched string from [strings] or -1 if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the beginning to the end of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    public static int indexOfAny(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, int startIndex) {
        return indexOfAny(charSequence, strings, startIndex, false);
    }

    /**
     * Finds the index of the first occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return An index of the first occurrence of matched string from [strings] or -1 if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the beginning to the end of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    public static int indexOfAny(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, boolean ignoreCase) {
        return indexOfAny(charSequence, strings, 0, ignoreCase);
    }

    /**
     * Finds the index of the first occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @return An index of the first occurrence of matched string from [strings] or -1 if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the beginning to the end of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    public static int indexOfAny(@Nullable CharSequence charSequence, @NotNull Collection<String> strings) {
        return indexOfAny(charSequence, strings, 0, false);
    }


    /**
     * Returns index of the first character matching the given [predicate], or -1 if the char sequence does not contain such character.
     */
    public static int indexOfFirst(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        if (charSequence != null) {
            for (int index : indices(charSequence)) {
                if (predicate.accept(charSequence.charAt(index))) {
                    return index;
                }
            }
        }
        return -1;
    }

    /**
     * Returns index of the last character matching the given [predicate], or -1 if the char sequence does not contain such character.
     */
    public static int indexOfLast(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        if (charSequence != null) {
            for (int index = charSequence.length() - 1; index >= 0; index--) {
                if (predicate.accept(charSequence.charAt(index))) {
                    return index;
                }
            }
        }
        return -1;
    }


    /**
     * Returns the index within this char sequence of the last occurrence of the specified character,
     * starting from the specified [startIndex].
     *
     * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
     * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
     * @return An index of the first occurrence of [char] or -1 if none is found.
     */
    public static int lastIndexOf(@Nullable CharSequence charSequence, char charValue, int startIndex, boolean ignoreCase) {
        if (ignoreCase || !(charSequence instanceof String)) {
            return lastIndexOfAny(charSequence, new char[]{charValue}, startIndex, ignoreCase);
        } else {
            return ((String) charSequence).lastIndexOf(charValue, startIndex);
        }
    }

    /**
     * Returns the index within this char sequence of the last occurrence of the specified character,
     * starting from the specified [startIndex].
     *
     * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
     * @return An index of the first occurrence of [char] or -1 if none is found.
     */
    public static int lastIndexOf(@Nullable CharSequence charSequence, char charr, int startIndex) {
        return lastIndexOf(charSequence, charr, startIndex, false);
    }

    /**
     * Returns the index within this char sequence of the last occurrence of the specified character,
     * starting from the specified [startIndex].
     *
     * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
     * @return An index of the first occurrence of [char] or -1 if none is found.
     */
    public static int lastIndexOf(@Nullable CharSequence charSequence, char charr, boolean ignoreCase) {
        return lastIndexOf(charSequence, charr, count(charSequence) - 1, ignoreCase);
    }

    /**
     * Returns the index within this char sequence of the last occurrence of the specified character,
     * starting from the specified [startIndex].
     *
     * @return An index of the first occurrence of [char] or -1 if none is found.
     */
    public static int lastIndexOf(@Nullable CharSequence charSequence, char charr) {
        return lastIndexOf(charSequence, charr, count(charSequence) - 1, false);
    }

    /**
     * Returns the index within this char sequence of the last occurrence of the specified [string],
     * starting from the specified [startIndex].
     *
     * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return An index of the first occurrence of [string] or -1 if none is found.
     */
    public static int lastIndexOf(@Nullable CharSequence charSequence, @NotNull String string, int startIndex, boolean ignoreCase) {
        if (ignoreCase || !(charSequence instanceof String)) {
            return indexOf(charSequence, string, startIndex, 0, ignoreCase, true);
        } else {
            return ((String) charSequence).lastIndexOf(string, startIndex);
        }
    }

    /**
     * Returns the index within this char sequence of the last occurrence of the specified [string],
     * starting from the specified [startIndex].
     *
     * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
     * @return An index of the first occurrence of [string] or -1 if none is found.
     */
    public static int lastIndexOf(@Nullable CharSequence charSequence, @NotNull String string, int startIndex) {
        return lastIndexOf(charSequence, string, startIndex, false);
    }

    /**
     * Returns the index within this char sequence of the last occurrence of the specified [string],
     * starting from the specified [startIndex].
     *
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return An index of the first occurrence of [string] or -1 if none is found.
     */
    public static int lastIndexOf(@Nullable CharSequence charSequence, @NotNull String string, boolean ignoreCase) {
        return lastIndexOf(charSequence, string, count(charSequence) - 1, ignoreCase);
    }

    /**
     * Returns the index within this char sequence of the last occurrence of the specified [string],
     * starting from the specified [startIndex].
     *
     * @return An index of the first occurrence of [string] or -1 if none is found.
     */
    public static int lastIndexOf(@Nullable CharSequence charSequence, @NotNull String string) {
        return lastIndexOf(charSequence, string, count(charSequence) - 1, false);
    }


    /**
     * Finds the index of the last occurrence of any of the specified [chars] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
     * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
     * @return An index of the last occurrence of matched character from [chars] or -1 if none of [chars] are found.
     */
    public static int lastIndexOfAny(@Nullable CharSequence charSequence, char[] chars, int startIndex, final boolean ignoreCase) {
        if (charSequence == null) return -1;

        if (!ignoreCase && chars.length == 1 && charSequence instanceof String) {
            char charValue = chars[0];
            return ((String) charSequence).lastIndexOf(charValue, startIndex);
        }

        for (int index : new IntProgression(Math.min(startIndex, count(charSequence) - 1), 0, -1)) {
            final char charAtIndex = charSequence.charAt(index);
            boolean find = false;
            for (char aChar : chars) {
                if (Charx.equals(aChar, charAtIndex, ignoreCase)) {
                    find = true;
                    break;
                }
            }
            if (find) {
                return index;
            }
        }

        return -1;
    }

    /**
     * Finds the index of the last occurrence of any of the specified [chars] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
     * @return An index of the last occurrence of matched character from [chars] or -1 if none of [chars] are found.
     */
    public static int lastIndexOfAny(@Nullable CharSequence charSequence, char[] chars, int startIndex) {
        return lastIndexOfAny(charSequence, chars, startIndex, false);
    }

    /**
     * Finds the index of the last occurrence of any of the specified [chars] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
     * @return An index of the last occurrence of matched character from [chars] or -1 if none of [chars] are found.
     */
    public static int lastIndexOfAny(@Nullable CharSequence charSequence, char[] chars, final boolean ignoreCase) {
        return lastIndexOfAny(charSequence, chars, count(charSequence) - 1, ignoreCase);
    }

    /**
     * Finds the index of the last occurrence of any of the specified [chars] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @return An index of the last occurrence of matched character from [chars] or -1 if none of [chars] are found.
     */
    public static int lastIndexOfAny(@Nullable CharSequence charSequence, char[] chars) {
        return lastIndexOfAny(charSequence, chars, count(charSequence) - 1, false);
    }

    /**
     * Finds the index of the last occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return An index of the last occurrence of matched string from [strings] or -1 if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the end toward the beginning of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    public static int lastIndexOfAny(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Pair<Integer, String> pair = findAnyOf(charSequence, strings, startIndex, ignoreCase, true);
        return pair != null ? pair.first : -1;
    }

    /**
     * Finds the index of the last occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
     * @return An index of the last occurrence of matched string from [strings] or -1 if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the end toward the beginning of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    public static int lastIndexOfAny(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, int startIndex) {
        return lastIndexOfAny(charSequence, strings, startIndex, false);
    }

    /**
     * Finds the index of the last occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
     * @return An index of the last occurrence of matched string from [strings] or -1 if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the end toward the beginning of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    public static int lastIndexOfAny(@Nullable CharSequence charSequence, @NotNull Collection<String> strings, boolean ignoreCase) {
        return lastIndexOfAny(charSequence, strings, count(charSequence) - 1, ignoreCase);
    }

    /**
     * Finds the index of the last occurrence of any of the specified [strings] in this char sequence,
     * starting from the specified [startIndex] and optionally ignoring the case.
     *
     * @return An index of the last occurrence of matched string from [strings] or -1 if none of [strings] are found.
     * To avoid ambiguous results when strings in [strings] have characters in common, this method proceeds from
     * the end toward the beginning of this string, and finds at each position the first element in [strings]
     * that matches this string at that position.
     */
    public static int lastIndexOfAny(@Nullable CharSequence charSequence, @NotNull Collection<String> strings) {
        return lastIndexOfAny(charSequence, strings, count(charSequence) - 1, false);
    }


    /* ******************************************* substring ****************************************** */


    /**
     * Returns a subsequence of this char sequence specified by the given [range] of indices.
     */
    @NotNull
    public static CharSequence subSequence(@Nullable CharSequence charSequence, @NotNull IntRange range) {
        return orEmpty(charSequence).subSequence(range.getStart(), range.getEndInclusive() + 1);
    }

    /**
     * Returns a subsequence of this char sequence specified by the given [range] of indices.
     */
    @NotNull
    public static CharSequence subSequence(@Nullable CharSequence charSequence, int start, int end) {
        return orEmpty(charSequence).subSequence(start, end);
    }

    /**
     * Returns a substring specified by the given [range] of indices.
     */
    @NotNull
    public static String substring(@Nullable String string, @NotNull IntRange range) {
        return orEmpty(string).substring(range.getStart(), range.getEndInclusive() + 1);
    }

    /**
     * Returns a substring of chars at indices from the specified [range] of this char sequence.
     */
    @NotNull
    public static String substring(@Nullable CharSequence charSequence, @NotNull IntRange range) {
        return orEmpty(charSequence).subSequence(range.getStart(), range.getEndInclusive() + 1).toString();
    }

    /**
     * Returns a substring specified by the given [range] of indices.
     */
    @NotNull
    public static String substring(@Nullable String string, int beginIndex, int endIndex) {
        return orEmpty(string).substring(beginIndex, endIndex);
    }

    /**
     * Returns a substring of chars from a range of this char sequence starting at the [startIndex] and ending right before the [endIndex].
     *
     * @param startIndex the start index (inclusive).
     * @param endIndex   the end index (exclusive). If not specified, the length of the char sequence is used.
     */
    @NotNull
    public static String substring(@Nullable CharSequence charSequence, int startIndex, int endIndex) {
        return orEmpty(charSequence).subSequence(startIndex, endIndex).toString();
    }

    /**
     * Returns a substring before the first occurrence of [delimiter].
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String substringBefore(@Nullable String string, char delimiter, @Nullable String missingDelimiterValue) {
        int index = indexOf(string, delimiter, 0, false);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : orEmpty(string).substring(0, index);
    }

    /**
     * Returns a substring before the first occurrence of [delimiter].
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String substringBefore(@Nullable String string, @NotNull String delimiter, @Nullable String missingDelimiterValue) {
        int index = indexOf(string, delimiter, 0, false);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : orEmpty(string).substring(0, index);
    }

    /**
     * Returns a substring after the first occurrence of [delimiter].
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String substringAfter(@Nullable String string, char delimiter, @Nullable String missingDelimiterValue) {
        int index = indexOf(string, delimiter, 0, false);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : orEmpty(string).substring(index + 1);
    }

    /**
     * Returns a substring after the first occurrence of [delimiter].
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String substringAfter(@Nullable String string, @NotNull String delimiter, @Nullable String missingDelimiterValue) {
        int index = indexOf(string, delimiter, 0, false);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : orEmpty(string).substring(index + delimiter.length());
    }

    /**
     * Returns a substring before the last occurrence of [delimiter].
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String substringBeforeLast(@Nullable String string, char delimiter, @Nullable String missingDelimiterValue) {
        int index = lastIndexOf(string, delimiter, count(string) - 1, false);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : orEmpty(string).substring(0, index);
    }

    /**
     * Returns a substring before the last occurrence of [delimiter].
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String substringBeforeLast(@Nullable String string, @NotNull String delimiter, @Nullable String missingDelimiterValue) {
        int index = lastIndexOf(string, delimiter, count(string) - 1, false);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : orEmpty(string).substring(0, index);
    }

    /**
     * Returns a substring after the last occurrence of [delimiter].
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String substringAfterLast(@Nullable String string, char delimiter, @Nullable String missingDelimiterValue) {
        int index = lastIndexOf(string, delimiter, count(string) - 1, false);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : orEmpty(string).substring(index + 1);
    }

    /**
     * Returns a substring after the last occurrence of [delimiter].
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String substringAfterLast(@Nullable String string, @NotNull String delimiter, @Nullable String missingDelimiterValue) {
        int index = lastIndexOf(string, delimiter, count(string) - 1, false);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : orEmpty(string).substring(index + delimiter.length());
    }


    /* ******************************************* toByteArray ****************************************** */


    /**
     * Encodes the contents of this string using the specified character set and returns the resulting byte array.
     */
    @NotNull
    public static byte[] toByteArray(@Nullable String string, @NotNull Charset charset) {
        return string != null ? string.getBytes(charset) : new byte[0];
    }

    /**
     * Encodes the contents of this string using UTF-8 set and returns the resulting byte array.
     */
    @NotNull
    public static byte[] toByteArray(@Nullable String string) {
        return string != null ? string.getBytes(Charset.forName("UTF-8")) : new byte[0];
    }


    /* ******************************************* reversed ****************************************** */


    /**
     * Returns a char sequence with characters in reversed order.
     */
    @NotNull
    public static CharSequence reversed(@Nullable CharSequence sequence) {
        return sequence != null ? new StringBuilder(sequence).reverse() : new StringBuilder(0);
    }

    /**
     * Returns a string with characters in reversed order.
     */
    @NotNull
    public static String reversed(@Nullable String string) {
        return string != null ? new StringBuilder(string).reverse().toString() : "";
    }


    /* ******************************************* filter ****************************************** */


    /**
     * Appends all characters matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Appendable> C filterTo(@Nullable CharSequence sequence, @NotNull C destination, @NotNull Predicate<Character> predicate) {
        if (sequence != null) {
            for (int index = 0, size = sequence.length(); index < size; index++) {
                char element = sequence.charAt(index);
                if (predicate.accept(element)) {
                    try {
                        destination.append(element);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return destination;
    }

    /**
     * Returns a char sequence containing only those characters from the original char sequence that match the given [predicate].
     */
    @NotNull
    public static CharSequence filter(@Nullable CharSequence sequence, @NotNull Predicate<Character> predicate) {
        return filterTo(sequence, new StringBuilder(), predicate);
    }

    /**
     * Returns a string containing only those characters from the original string that match the given [predicate].
     */
    @NotNull
    public static String filter(@Nullable String string, @NotNull Predicate<Character> predicate) {
        return filterTo(string, new StringBuilder(), predicate).toString();
    }

    /**
     * Appends all characters matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of a character and the character itself
     *                  and returns the result of predicate evaluation on the character.
     */
    @NotNull
    public static <C extends Appendable> C filterIndexedTo(@Nullable CharSequence sequence, @NotNull C destination, @NotNull IndexedPredicate<Character> predicate) {
        if (sequence != null) {
            for (int index = 0, size = sequence.length(); index < size; index++) {
                char element = sequence.charAt(index);
                if (predicate.accept(index, element)) {
                    try {
                        destination.append(element);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return destination;
    }

    /**
     * Returns a char sequence containing only those characters from the original char sequence that match the given [predicate].
     *
     * @param predicate function that takes the index of a character and the character itself
     *                  and returns the result of predicate evaluation on the character.
     */
    @NotNull
    public static CharSequence filterIndexed(@Nullable CharSequence sequence, @NotNull IndexedPredicate<Character> predicate) {
        return filterIndexedTo(sequence, new StringBuilder(), predicate);
    }

    /**
     * Returns a string containing only those characters from the original string that match the given [predicate].
     *
     * @param predicate function that takes the index of a character and the character itself
     *                  and returns the result of predicate evaluation on the character.
     */
    @NotNull
    public static String filterIndexed(@Nullable String string, @NotNull IndexedPredicate<Character> predicate) {
        return filterIndexedTo(string, new StringBuilder(), predicate).toString();
    }

    /**
     * Appends all characters not matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Appendable> C filterNotTo(@Nullable CharSequence sequence, @NotNull C destination, @NotNull Predicate<Character> predicate) {
        if (sequence != null) {
            for (int index = 0, size = sequence.length(); index < size; index++) {
                char element = sequence.charAt(index);
                if (!predicate.accept(element)) {
                    try {
                        destination.append(element);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return destination;
    }

    /**
     * Returns a char sequence containing only those characters from the original char sequence that do not match the given [predicate].
     */
    @NotNull
    public static CharSequence filterNot(@Nullable CharSequence sequence, @NotNull Predicate<Character> predicate) {
        return filterNotTo(sequence, new StringBuilder(), predicate);
    }

    /**
     * Returns a string containing only those characters from the original string that do not match the given [predicate].
     */
    @NotNull
    public static String filterNot(@Nullable String string, @NotNull Predicate<Character> predicate) {
        return filterNotTo(string, new StringBuilder(), predicate).toString();
    }


    /* ******************************************* trim ****************************************** */


    /**
     * Returns a sub sequence of this char sequence having leading and trailing characters matching the [predicate] removed.
     */
    @NotNull
    public static CharSequence trim(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        final CharSequence finalCharSequence = orEmpty(charSequence);

        int startIndex = 0;
        int endIndex = count(finalCharSequence) - 1;
        boolean startFound = false;

        while (startIndex <= endIndex) {
            int index = !startFound ? startIndex : endIndex;
            boolean match = predicate.accept(finalCharSequence.charAt(index));

            if (!startFound) {
                if (!match)
                    startFound = true;
                else
                    startIndex += 1;
            } else {
                if (!match)
                    break;
                else
                    endIndex -= 1;
            }
        }

        return finalCharSequence.subSequence(startIndex, endIndex + 1);
    }

    /**
     * Returns a string having leading and trailing characters matching the [predicate] removed.
     */
    @NotNull
    public static String trim(@Nullable String string, @NotNull Predicate<Character> predicate) {
        return trim((CharSequence) string, predicate).toString();
    }

    /**
     * Returns a sub sequence of this char sequence having leading and trailing characters from the [chars] array removed.
     */
    @NotNull
    public static CharSequence trim(@Nullable CharSequence charSequence, final char... chars) {
        return trim(charSequence, new Predicate<Character>() {
            @Override
            public boolean accept(@NotNull Character character) {
                for (char aChar : chars) {
                    if (aChar == character) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * Returns a string having leading and trailing characters from the [chars] array removed.
     */
    @NotNull
    public static String trim(@Nullable String string, final char... chars) {
        return trim(string, new Predicate<Character>() {
            @Override
            public boolean accept(@NotNull Character character) {
                for (char aChar : chars) {
                    if (aChar == character) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * Returns a sub sequence of this char sequence having leading and trailing whitespace removed.
     */
    @NotNull
    public static CharSequence trim(@Nullable CharSequence charSequence) {
        return trim(charSequence, new Predicate<Character>() {
            @Override
            public boolean accept(@NotNull Character character) {
                return Character.isWhitespace(character);
            }
        });
    }

    /**
     * Returns a string having leading and trailing whitespace removed.
     */
    @NotNull
    public static String trim(@Nullable String string) {
        return trim((CharSequence) string).toString();
    }

    /**
     * Returns a sub sequence of this char sequence having leading characters matching the [predicate] removed.
     */
    @NotNull
    public static CharSequence trimStart(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        final CharSequence finalCharSequence = orEmpty(charSequence);

        for (int index : indices(finalCharSequence)) {
            if (!predicate.accept(finalCharSequence.charAt(index))) {
                return finalCharSequence.subSequence(index, count(finalCharSequence));
            }
        }
        return "";
    }

    /**
     * Returns a string having leading characters matching the [predicate] removed.
     */
    @NotNull
    public static String trimStart(@Nullable String string, @NotNull Predicate<Character> predicate) {
        return trimStart((CharSequence) string, predicate).toString();
    }

    /**
     * Returns a sub sequence of this char sequence having leading characters from the [chars] array removed.
     */
    @NotNull
    public static CharSequence trimStart(@Nullable CharSequence charSequence, final char... chars) {
        return trimStart(charSequence, new Predicate<Character>() {
            @Override
            public boolean accept(@NotNull Character character) {
                for (char aChar : chars) {
                    if (aChar == character) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * Returns a string having leading characters from the [chars] array removed.
     */
    @NotNull
    public static String trimStart(@Nullable String string, final char... chars) {
        return trimStart(string, new Predicate<Character>() {
            @Override
            public boolean accept(@NotNull Character character) {
                for (char aChar : chars) {
                    if (aChar == character) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * Returns a sub sequence of this char sequence having leading whitespace removed.
     */
    @NotNull
    public static CharSequence trimStart(@Nullable CharSequence charSequence) {
        return trimStart(charSequence, new Predicate<Character>() {
            @Override
            public boolean accept(@NotNull Character character) {
                return Character.isWhitespace(character);
            }
        });
    }

    /**
     * Returns a string having leading whitespace removed.
     */
    @NotNull
    public static String trimStart(@Nullable String string) {
        return trimStart((CharSequence) string).toString();
    }

    /**
     * Returns a sub sequence of this char sequence having trailing characters matching the [predicate] removed.
     */
    @NotNull
    public static CharSequence trimEnd(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        final CharSequence finalCharSequence = orEmpty(charSequence);
        for (int index = finalCharSequence.length() - 1; index >= 0; index--) {
            if (!predicate.accept(finalCharSequence.charAt(index))) {
                return finalCharSequence.subSequence(0, index + 1);
            }
        }
        return "";
    }

    /**
     * Returns a string having trailing characters matching the [predicate] removed.
     */
    @NotNull
    public static String trimEnd(@Nullable String string, @NotNull Predicate<Character> predicate) {
        return trimEnd((CharSequence) string, predicate).toString();
    }

    /**
     * Returns a sub sequence of this char sequence having trailing characters from the [chars] array removed.
     */
    @NotNull
    public static CharSequence trimEnd(@Nullable CharSequence charSequence, final char... chars) {
        return trimEnd(charSequence, new Predicate<Character>() {
            @Override
            public boolean accept(@NotNull Character character) {
                for (char aChar : chars) {
                    if (aChar == character) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * Returns a string having trailing characters from the [chars] array removed.
     */
    @NotNull
    public static String trimEnd(@Nullable String string, final char... chars) {
        return trimEnd(string, new Predicate<Character>() {
            @Override
            public boolean accept(@NotNull Character character) {
                for (char aChar : chars) {
                    if (aChar == character) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * Returns a sub sequence of this char sequence having trailing whitespace removed.
     */
    @NotNull
    public static CharSequence trimEnd(@Nullable CharSequence charSequence) {
        return trimEnd(charSequence, new Predicate<Character>() {
            @Override
            public boolean accept(@NotNull Character character) {
                return Character.isWhitespace(character);
            }
        });
    }

    /**
     * Returns a string having trailing whitespace removed.
     */
    @NotNull
    public static String trimEnd(@Nullable String string) {
        return trimEnd((CharSequence) string).toString();
    }


    /* ******************************************* iterator ****************************************** */


    /**
     * Iterator for characters of the given char sequence.
     */
    @NotNull
    public static Iterator<Character> iterator(@Nullable final CharSequence charSequence) {
        return new CharSequenceIterator(orEmpty(charSequence));
    }

    /**
     * Iterator for characters of the given char sequence.
     */
    @NotNull
    public static Iterable<Character> iterable(@Nullable final CharSequence charSequence) {
        return new CharSequenceIterable(orEmpty(charSequence));
    }


    /* ******************************************* replace ****************************************** */


    /**
     * Returns a char sequence with content of this char sequence where its part at the given range
     * is replaced with the [replacement] char sequence.
     *
     * @param startIndex the index of the first character to be replaced.
     * @param endIndex   the index of the first character after the replacement to keep in the string.
     */
    @NotNull
    public static CharSequence replaceRange(@Nullable final CharSequence charSequence, int startIndex, int endIndex, @NotNull CharSequence replacement) {
        if (endIndex < startIndex)
            throw new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
        StringBuilder sb = new StringBuilder();
        sb.append(charSequence, 0, startIndex);
        sb.append(replacement);
        sb.append(charSequence, endIndex, count(charSequence));
        return sb;
    }

    /**
     * Returns a char sequence with content of this char sequence where its part at the given [range]
     * is replaced with the [replacement] char sequence.
     * <p>
     * The end index of the [range] is included in the part to be replaced.
     */
    @NotNull
    public static CharSequence replaceRange(@Nullable final CharSequence charSequence, @NotNull IntRange range, @NotNull CharSequence replacement) {
        return replaceRange(charSequence, range.getStart(), range.getEndInclusive() + 1, replacement);
    }

    /**
     * Replaces the part of the string at the given range with the [replacement] char sequence.
     *
     * @param startIndex the index of the first character to be replaced.
     * @param endIndex   the index of the first character after the replacement to keep in the string.
     */
    @NotNull
    public static String replaceRange(@Nullable String string, int startIndex, int endIndex, @NotNull CharSequence replacement) {
        return replaceRange(((CharSequence) string), startIndex, endIndex, replacement).toString();
    }

    /**
     * Replace the part of string at the given [range] with the [replacement] string.
     * <p>
     * The end index of the [range] is included in the part to be replaced.
     */
    @NotNull
    public static String replaceRange(@Nullable String string, IntRange range, @NotNull CharSequence replacement) {
        return replaceRange(((CharSequence) string), range, replacement).toString();
    }

    /**
     * Replace part of string before the first occurrence of given delimiter with the [replacement] string.
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String replaceBefore(@Nullable String string, char delimiter, @NotNull String replacement, @Nullable String missingDelimiterValue) {
        int index = orEmpty(string).indexOf(delimiter);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : replaceRange(string, 0, index, replacement);
    }

    /**
     * Replace part of string before the first occurrence of given delimiter with the [replacement] string.
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String replaceBefore(@Nullable String string, @NotNull String delimiter, @NotNull String replacement, @Nullable String missingDelimiterValue) {
        int index = orEmpty(string).indexOf(delimiter);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : replaceRange(string, 0, index, replacement);
    }

    /**
     * Replace part of string before the last occurrence of given delimiter with the [replacement] string.
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String replaceBeforeLast(@Nullable String string, char delimiter, @NotNull String replacement, @Nullable String missingDelimiterValue) {
        int index = orEmpty(string).lastIndexOf(delimiter);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : replaceRange(string, 0, index, replacement);
    }

    /**
     * Replace part of string before the last occurrence of given delimiter with the [replacement] string.
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String replaceBeforeLast(@Nullable String string, @NotNull String delimiter, @NotNull String replacement, @Nullable String missingDelimiterValue) {
        int index = orEmpty(string).lastIndexOf(delimiter);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : replaceRange(string, 0, index, replacement);
    }

    /**
     * Replace part of string after the first occurrence of given delimiter with the [replacement] string.
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String replaceAfter(@Nullable String string, char delimiter, @NotNull String replacement, @Nullable String missingDelimiterValue) {
        int index = orEmpty(string).indexOf(delimiter);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : replaceRange(string, index + 1, count(string), replacement);
    }

    /**
     * Replace part of string after the first occurrence of given delimiter with the [replacement] string.
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String replaceAfter(@Nullable String string, @NotNull String delimiter, @NotNull String replacement, @Nullable String missingDelimiterValue) {
        int index = orEmpty(string).indexOf(delimiter);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : replaceRange(string, index + delimiter.length(), count(string), replacement);
    }

    /**
     * Replace part of string after the last occurrence of given delimiter with the [replacement] string.
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String replaceAfterLast(@Nullable String string, @NotNull String delimiter, @NotNull String replacement, @Nullable String missingDelimiterValue) {
        int index = orEmpty(string).lastIndexOf(delimiter);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : replaceRange(string, index + delimiter.length(), count(string), replacement);
    }

    /**
     * Replace part of string after the last occurrence of given delimiter with the [replacement] string.
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    @NotNull
    public static String replaceAfterLast(@Nullable String string, char delimiter, @NotNull String replacement, @Nullable String missingDelimiterValue) {
        int index = orEmpty(string).lastIndexOf(delimiter);
        return index == -1 ? orDefault(missingDelimiterValue, orEmpty(string)) : replaceRange(string, index + 1, count(string), replacement);
    }


// public static String.replace(oldChar: Character, newChar: Character, ignoreCase: Boolean): String // JVM- and JS-specific
// public static String.replace(oldValue: String, newValue: String, ignoreCase: Boolean): String // JVM- and JS-specific

    /**
     * Returns a new string obtained by replacing each substring of this char sequence that matches the given regular expression
     * with the given [replacement].
     * <p>
     * The [replacement] can consist of any combination of literal text and $-substitutions. To treat the replacement string
     * literally escape it with the [kotlin.text.Regex.Companion.escapeReplacement] method.
     */
    @NotNull
    public static String replace(@Nullable final CharSequence charSequence, @NotNull Pattern regex, @NotNull String replacement) {
        return regex.matcher(orEmpty(charSequence)).replaceAll(replacement);
    }

///**
// * Returns a new string obtained by replacing each substring of this char sequence that matches the given regular expression
// * with the result of the given function [transform] that takes [MatchResult] and returns a string to be used as a
// * replacement for that match.
// */
//    public static String replace(CharSequence charSequence, Pattern pattern, Transformer<MatchResult, CharSequence> transform{
//        regex.replace(this, transform)
//        MatchResult match = pattern.matcher(charSequence).findNext(0, charSequence);
//        if(match == null) return charSequence.toString();
//
//        var lastStart = 0
//        val length = charSequence.length
//        val sb = StringBuilder(length)
//        do {
//            val foundMatch = match!!
//                    sb.append(charSequence, lastStart, foundMatch.range.start)
//            sb.append(transform(foundMatch))
//            lastStart = foundMatch.range.endInclusive + 1
//            match = foundMatch.next()
//        } while (lastStart < length && match != null)
//
//        if (lastStart < length) {
//            sb.append(charSequence, lastStart, length)
//        }
//
//        return sb.toString()
//    }


    /**
     * Replaces the first occurrence of the given regular expression [regex] in this char sequence with specified [replacement] expression.
     *
     * @param replacement A replacement expression that can include substitutions. See [Regex.replaceFirst] for details.
     */
    @NotNull
    public static String replaceFirst(@Nullable final CharSequence charSequence, @NotNull Pattern regex, @NotNull String replacement) {
        return regex.matcher(orEmpty(charSequence)).replaceFirst(replacement);
    }


    /* ******************************************* commonWith ****************************************** */


    /**
     * Returns the longest string `prefix` such that this char sequence and [other] char sequence both start with this prefix,
     * taking care not to split surrogate pairs.
     * If this and [other] have no common prefix, returns the empty string.
     *
     * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
     */
    @NotNull
    public static String commonPrefixWith(@Nullable CharSequence charSequence, @Nullable CharSequence other, boolean ignoreCase) {
        if (charSequence == null || other == null) return "";
        int shortestLength = Math.min(count(charSequence), other.length());

        int i = 0;
        while (i < shortestLength && Charx.equals(charSequence.charAt(i), other.charAt(i), ignoreCase)) {
            i++;
        }
        if (hasSurrogatePairAt(charSequence, i - 1) || hasSurrogatePairAt(other, i - 1)) {
            i--;
        }
        return charSequence.subSequence(0, i).toString();
    }


    /**
     * Returns the longest string `prefix` such that this char sequence and [other] char sequence both start with this prefix,
     * taking care not to split surrogate pairs.
     * If this and [other] have no common prefix, returns the empty string.
     */
    @NotNull
    public static String commonPrefixWith(@Nullable CharSequence charSequence, @Nullable CharSequence other) {
        return commonPrefixWith(charSequence, other, false);
    }

    /**
     * Returns the longest string `suffix` such that this char sequence and [other] char sequence both end with this suffix,
     * taking care not to split surrogate pairs.
     * If this and [other] have no common suffix, returns the empty string.
     *
     * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
     */
    public static String commonSuffixWith(@Nullable CharSequence charSequence, @Nullable CharSequence other, boolean ignoreCase) {
        if (charSequence == null || other == null) return "";
        int thisLength = count(charSequence);
        int otherLength = other.length();
        int shortestLength = Math.min(thisLength, otherLength);

        int i = 0;
        while (i < shortestLength && Charx.equals(charSequence.charAt(thisLength - i - 1), other.charAt(otherLength - i - 1), ignoreCase)) {
            i++;
        }
        if (hasSurrogatePairAt(charSequence, thisLength - i - 1) || hasSurrogatePairAt(other, otherLength - i - 1)) {
            i--;
        }
        return charSequence.subSequence(thisLength - i, thisLength).toString();
    }

    /**
     * Returns the longest string `suffix` such that this char sequence and [other] char sequence both end with this suffix,
     * taking care not to split surrogate pairs.
     * If this and [other] have no common suffix, returns the empty string.
     */
    public static String commonSuffixWith(@Nullable CharSequence charSequence, @Nullable CharSequence other) {
        return commonSuffixWith(charSequence, other, false);
    }


    /* ******************************************* contains ****************************************** */


    /**
     * Returns `true` if this char sequence contains the specified [other] sequence of characters as a substring.
     *
     * @param ignoreCase `true` to ignore character case when comparing strings. By default `false`.
     */
    public static boolean contains(@Nullable CharSequence charSequence, @NotNull CharSequence other, boolean ignoreCase) {
        if (other instanceof String) {
            return indexOf(charSequence, (String) other, ignoreCase) >= 0;
        } else {
            return indexOf(charSequence, other, 0, count(charSequence), ignoreCase, false) >= 0;
        }
    }

    /**
     * Returns `true` if this char sequence contains the specified [other] sequence of characters as a substring.
     */
    public static boolean contains(@Nullable CharSequence charSequence, @NotNull CharSequence other) {
        return contains(charSequence, other, false);
    }

    /**
     * Returns `true` if this char sequence contains the specified character [char].
     *
     * @param ignoreCase `true` to ignore character case when comparing characters. By default `false`.
     */
    public static boolean contains(@Nullable CharSequence charSequence, char cha, boolean ignoreCase) {
        return indexOf(charSequence, cha, ignoreCase) >= 0;
    }

    /**
     * Returns `true` if this char sequence contains the specified character [char].
     */
    public static boolean contains(@Nullable CharSequence charSequence, char cha) {
        return contains(charSequence, cha, false);
    }

    /**
     * Returns `true` if this char sequence contains at least one match of the specified regular expression [regex].
     */
    public static boolean contains(@Nullable CharSequence charSequence, @NotNull Pattern regex) {
        return charSequence != null && regex.matcher(charSequence).find();
    }


    /* ******************************************* split ****************************************** */


    /**
     * Returns a sequence of index ranges of substrings in this char sequence around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more characters to be used as delimiters.
     * @param startIndex The index to start searching delimiters from.
     *                   No range having its start value less than [startIndex] is returned.
     *                   [startIndex] is coerced to be non-negative and not greater than length of this string.
     * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
     * @param limit      The maximum number of substrings to return. Zero by default means no limit is set.
     */
    @NotNull
    private static Iterable<IntRange> rangesDelimitedBy(@Nullable CharSequence charSequence, @NotNull final char[] delimiters,
                                                        @SuppressWarnings("SameParameterValue") int startIndex, final boolean ignoreCase, final int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Param 'limit' is less than to zero.");
        }
        return new DelimitedRangesIterable(orEmpty(charSequence), startIndex, limit, new NextMatch() {
            @Nullable
            @Override
            public Pair<Integer, Integer> getNextMatch(@NotNull CharSequence charSequence, int startIndex) {
                int nextIndex = indexOfAny(charSequence, delimiters, startIndex, ignoreCase);
                return nextIndex < 0 ? null : Pair.of(nextIndex, 1);
            }
        });
    }

    /**
     * Returns a sequence of index ranges of substrings in this char sequence around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more strings to be used as delimiters.
     * @param startIndex The index to start searching delimiters from.
     *                   No range having its start value less than [startIndex] is returned.
     *                   [startIndex] is coerced to be non-negative and not greater than length of this string.
     * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
     * @param limit      The maximum number of substrings to return. Zero by default means no limit is set.
     *                   <p>
     *                   To avoid ambiguous results when strings in [delimiters] have characters in common, this method proceeds from
     *                   the beginning to the end of this string, and finds at each position the first element in [delimiters]
     *                   that matches this string at that position.
     */
    @NotNull
    private static Iterable<IntRange> rangesDelimitedBy(@Nullable CharSequence charSequence, @NotNull String[] delimiters,
                                                        @SuppressWarnings("SameParameterValue") int startIndex, final boolean ignoreCase, final int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Param 'limit' is less than to zero.");
        }
        final List<String> delimitersList = new ArrayList<String>(delimiters.length);
        Collections.addAll(delimitersList, delimiters);
        return new DelimitedRangesIterable(charSequence != null ? charSequence : "", startIndex, limit, new NextMatch() {
            @Nullable
            @Override
            public Pair<Integer, Integer> getNextMatch(@NotNull CharSequence charSequence, int startIndex) {
                Pair<Integer, String> next = findAnyOf(charSequence, delimitersList, startIndex, ignoreCase, false);
                return next != null ? Pair.of(next.first, next.second.length()) : null;
            }
        });
    }


    /**
     * Splits this char sequence to a sequence of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more strings to be used as delimiters.
     * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
     * @param limit      The maximum number of substrings to return. Zero by default means no limit is set.
     *                   <p>
     *                   To avoid ambiguous results when strings in [delimiters] have characters in common, this method proceeds from
     *                   the beginning to the end of this string, and finds at each position the first element in [delimiters]
     *                   that matches this string at that position.
     */
    @NotNull
    public static Iterable<String> splitToIterable(@Nullable final CharSequence charSequence, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
        return new TransformingIterable<IntRange, String>(rangesDelimitedBy(charSequence, delimiters, 0, ignoreCase, limit), new Transformer<IntRange, String>() {
            @NotNull
            @Override
            public String transform(@NotNull IntRange range) {
                return substring(charSequence, range);
            }
        });
    }

    /**
     * Splits this char sequence to a sequence of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more strings to be used as delimiters.
     * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
     */
    @NotNull
    public static Iterable<String> splitToIterable(@Nullable final CharSequence charSequence, @NotNull String[] delimiters, boolean ignoreCase) {
        return splitToIterable(charSequence, delimiters, ignoreCase, 0);
    }

    /**
     * Splits this char sequence to a sequence of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more strings to be used as delimiters.
     * @param limit      The maximum number of substrings to return. Zero by default means no limit is set.
     *                   <p>
     *                   To avoid ambiguous results when strings in [delimiters] have characters in common, this method proceeds from
     *                   the beginning to the end of this string, and finds at each position the first element in [delimiters]
     *                   that matches this string at that position.
     */
    @NotNull
    public static Iterable<String> splitToIterable(@Nullable final CharSequence charSequence, @NotNull String[] delimiters, int limit) {
        return splitToIterable(charSequence, delimiters, false, limit);
    }

    /**
     * Splits this char sequence to a sequence of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more strings to be used as delimiters.
     */
    @NotNull
    public static Iterable<String> splitToIterable(@Nullable final CharSequence charSequence, @NotNull String[] delimiters) {
        return splitToIterable(charSequence, delimiters, false, 0);
    }


    /**
     * Splits this char sequence to a sequence of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more characters to be used as delimiters.
     * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
     * @param limit      The maximum number of substrings to return.
     */
    @NotNull
    public static Iterable<String> splitToIterable(@Nullable final CharSequence charSequence, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
        return new TransformingIterable<IntRange, String>(rangesDelimitedBy(charSequence, delimiters, 0, ignoreCase, limit), new Transformer<IntRange, String>() {
            @NotNull
            @Override
            public String transform(@NotNull IntRange range) {
                return substring(charSequence, range);
            }
        });
    }

    /**
     * Splits this char sequence to a sequence of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more characters to be used as delimiters.
     * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
     */
    @NotNull
    public static Iterable<String> splitToIterable(@Nullable final CharSequence charSequence, @NotNull char[] delimiters, boolean ignoreCase) {
        return splitToIterable(charSequence, delimiters, ignoreCase, 0);
    }

    /**
     * Splits this char sequence to a sequence of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more characters to be used as delimiters.
     * @param limit      The maximum number of substrings to return.
     */
    @NotNull
    public static Iterable<String> splitToIterable(@Nullable final CharSequence charSequence, @NotNull char[] delimiters, int limit) {
        return splitToIterable(charSequence, delimiters, false, limit);
    }

    /**
     * Splits this char sequence to a sequence of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more characters to be used as delimiters.
     */
    @NotNull
    public static Iterable<String> splitToIterable(@Nullable final CharSequence charSequence, @NotNull char[] delimiters) {
        return splitToIterable(charSequence, delimiters, false, 0);
    }

    /**
     * Splits this char sequence to a list of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more strings to be used as delimiters.
     * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
     * @param limit      The maximum number of substrings to return. Zero by default means no limit is set.
     *                   <p>
     *                   To avoid ambiguous results when strings in [delimiters] have characters in common, this method proceeds from
     *                   the beginning to the end of this string, and matches at each position the first element in [delimiters]
     *                   that is equal to a delimiter in this instance at that position.
     */
    @NotNull
    public static List<String> split(@Nullable final CharSequence charSequence, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
        if (delimiters.length == 1) {
            String delimiter = delimiters[0];
            if (!delimiter.isEmpty()) {
                return split(charSequence, delimiter, ignoreCase, limit);
            }
        }

        List<String> stringList = new ArrayList<String>();
        for (IntRange range : rangesDelimitedBy(charSequence, delimiters, 0, ignoreCase, limit)) {
            stringList.add(substring(charSequence, range));
        }
        return stringList;
    }

    /**
     * Splits this char sequence to a list of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more strings to be used as delimiters.
     * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
     */
    @NotNull
    public static List<String> split(@Nullable final CharSequence charSequence, @NotNull String[] delimiters, boolean ignoreCase) {
        return split(charSequence, delimiters, ignoreCase, 0);
    }

    /**
     * Splits this char sequence to a list of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more strings to be used as delimiters.
     * @param limit      The maximum number of substrings to return. Zero by default means no limit is set.
     *                   <p>
     *                   To avoid ambiguous results when strings in [delimiters] have characters in common, this method proceeds from
     *                   the beginning to the end of this string, and matches at each position the first element in [delimiters]
     *                   that is equal to a delimiter in this instance at that position.
     */
    @NotNull
    public static List<String> split(@Nullable final CharSequence charSequence, @NotNull String[] delimiters, int limit) {
        return split(charSequence, delimiters, false, limit);
    }

    /**
     * Splits this char sequence to a list of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more strings to be used as delimiters.
     */
    @NotNull
    public static List<String> split(@Nullable final CharSequence charSequence, @NotNull String[] delimiters) {
        return split(charSequence, delimiters, false, 0);
    }


    /**
     * Splits this char sequence to a list of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more characters to be used as delimiters.
     * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
     * @param limit      The maximum number of substrings to return.
     */
    @NotNull
    public static List<String> split(@Nullable final CharSequence charSequence, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
        if (delimiters.length == 1) {
            return split(charSequence, String.valueOf(delimiters[0]), ignoreCase, limit);
        }
        List<String> stringList = new ArrayList<String>();
        for (IntRange range : rangesDelimitedBy(charSequence, delimiters, 0, ignoreCase, limit)) {
            stringList.add(substring(charSequence, range));
        }
        return stringList;
    }

    /**
     * Splits this char sequence to a list of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more characters to be used as delimiters.
     * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
     */
    @NotNull
    public static List<String> split(@Nullable final CharSequence charSequence, @NotNull char[] delimiters, boolean ignoreCase) {
        return split(charSequence, delimiters, ignoreCase, 0);
    }

    /**
     * Splits this char sequence to a list of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more characters to be used as delimiters.
     * @param limit      The maximum number of substrings to return.
     */
    @NotNull
    public static List<String> split(@Nullable final CharSequence charSequence, @NotNull char[] delimiters, int limit) {
        return split(charSequence, delimiters, false, limit);
    }

    /**
     * Splits this char sequence to a list of strings around occurrences of the specified [delimiters].
     *
     * @param delimiters One or more characters to be used as delimiters.
     */
    @NotNull
    public static List<String> split(@Nullable final CharSequence charSequence, @NotNull char[] delimiters) {
        return split(charSequence, delimiters, false, 0);
    }

    /**
     * Splits this char sequence to a list of strings around occurrences of the specified [delimiter].
     * This is specialized version of split which receives single non-empty delimiter and offers better performance
     *
     * @param delimiter  String used as delimiter
     * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
     * @param limit      The maximum number of substrings to return.
     */
    @NotNull
    private static List<String> split(@Nullable CharSequence charSequence, @NotNull String delimiter, boolean ignoreCase, final int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Param 'limit' is less than to zero.");
        }

        int currentOffset = 0;
        int nextIndex = indexOf(charSequence, delimiter, currentOffset, ignoreCase);
        if (nextIndex == -1 || limit == 1) {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(orEmpty(charSequence).toString());
            return arrayList;
        }

        boolean isLimited = limit > 0;
        ArrayList<String> result = new ArrayList<String>(isLimited ? Math.min(limit, 10) : 10);
        do {
            result.add(substring(charSequence, currentOffset, nextIndex));
            currentOffset = nextIndex + delimiter.length();
            // Do not search for next occurrence if we're reaching limit
            if (isLimited && result.size() == limit - 1) break;
            nextIndex = indexOf(charSequence, delimiter, currentOffset, ignoreCase);
        } while (nextIndex != -1);

        result.add(substring(charSequence, currentOffset, orEmpty(charSequence).length()));
        return result;
    }


    /**
     * Splits this char sequence around matches of the given regular expression.
     *
     * @param limit Non-negative value specifying the maximum number of substrings to return.
     *              Zero by default means no limit is set.
     */
    @NotNull
    public static List<String> split(@Nullable CharSequence charSequence, @NotNull Pattern regex, int limit) {
        String[] splitItems = regex.split(orEmpty(charSequence), limit == 0 ? -1 : limit);
        ArrayList<String> arrayList = new ArrayList<String>(splitItems.length);
        Collections.addAll(arrayList, splitItems);
        return arrayList;
    }

    /**
     * Splits this char sequence around matches of the given regular expression.
     */
    @NotNull
    public static List<String> split(@Nullable CharSequence charSequence, @NotNull Pattern regex) {
        return split(charSequence, regex, 0);
    }


    /* ******************************************* lines ****************************************** */


    /**
     * Splits this char sequence to a sequence of lines delimited by any of the following character sequences: CRLF, LF or CR.
     */
    @NotNull
    public static Iterable<String> lineIterable(@Nullable CharSequence charSequence) {
        return splitToIterable(charSequence, new String[]{"\r\n", "\n", "\r"}, false, 0);
    }

    /**
     * Splits this char sequence to a list of lines delimited by any of the following character sequences: CRLF, LF or CR.
     */
    @NotNull
    public static List<String> lines(@Nullable CharSequence charSequence) {
        List<String> stringList = new ArrayList<String>();
        for (String s : lineIterable(charSequence)) {
            stringList.add(s);
        }
        return stringList;
    }


    /* ******************************************* elementAt ****************************************** */


    /**
     * Returns a character at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this char sequence.
     */
    public static char elementAt(@NotNull CharSequence charSequence, int index) {
        return charSequence.charAt(index);
    }

    /**
     * Returns a character at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this char sequence.
     */
    public static char elementAtOrElse(@Nullable CharSequence charSequence, int index, @NotNull IndexedDefaultValue<Character> defaultValue) {
        return charSequence != null && index >= 0 && index <= lastIndex(charSequence) ? charSequence.charAt(index) : defaultValue.get(index);
    }

    /**
     * Returns a character at the given [index] or `null` if the [index] is out of bounds of this char sequence.
     */
    @Nullable
    public static Character elementAtOrNull(@Nullable CharSequence charSequence, int index) {
        return charSequence != null && index >= 0 && index <= lastIndex(charSequence) ? charSequence.charAt(index) : null;
    }


    /* ******************************************* single ****************************************** */


    /**
     * Returns the single character, or throws an exception if the char sequence is empty or has more than one character.
     */
    public static char single(@Nullable CharSequence charSequence) {
        if (charSequence == null || count(charSequence) == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        } else if (count(charSequence) == 1) {
            return charSequence.charAt(0);
        } else {
            throw new IllegalArgumentException("Char sequence has more than one element.");
        }
    }

    /**
     * Returns the single character matching the given [predicate], or throws exception if there is no or more than one matching character.
     */
    public static char single(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        Character single = null;
        boolean found = false;
        for (char element : iterable(charSequence)) {
            if (predicate.accept(element)) {
                if (found) {
                    throw new IllegalArgumentException("Char sequence contains more than one matching element.");
                }
                single = element;
                found = true;
            }
        }
        if (!found) throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
        return single;
    }

    /**
     * Returns single character, or `null` if the char sequence is empty or has more than one character.
     */
    @Nullable
    public static Character singleOrNull(@Nullable CharSequence charSequence) {
        return charSequence != null && count(charSequence) == 1 ? charSequence.charAt(0) : null;
    }

    /**
     * Returns the single character matching the given [predicate], or `null` if character was not found or more than one character was found.
     */
    @Nullable
    public static Character singleOrNull(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        Character single = null;
        boolean found = false;
        for (char element : iterable(charSequence)) {
            if (predicate.accept(element)) {
                if (found) {
                    return null;
                }
                single = element;
                found = true;
            }
        }
        return found ? single : null;
    }


    /* ******************************************* drop ****************************************** */


    /**
     * Returns a subsequence of this char sequence with the first [n] characters removed.
     */
    @NotNull
    public static CharSequence drop(@Nullable CharSequence charSequence, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than to zero.");
        }
        return orEmpty(charSequence).subSequence(Math.min(n, count(charSequence)), count(charSequence));
    }

    /**
     * Returns a string with the first [n] characters removed.
     */
    @NotNull
    public static String drop(@Nullable String string, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than to zero.");
        }
        return orEmpty(string).substring(Math.min(n, count(string)));
    }

    /**
     * Returns a subsequence of this char sequence with the last [n] characters removed.
     */
    @NotNull
    public static CharSequence dropLast(@Nullable CharSequence charSequence, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than to zero.");
        }
        return take(charSequence, Math.max((count(charSequence) - n), 0));
    }

    /**
     * Returns a string with the last [n] characters removed.
     */
    @NotNull
    public static String dropLast(@Nullable String string, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than to zero.");
        }
        return take(string, Math.max((count(string) - n), 0));
    }

    /**
     * Returns a subsequence of this char sequence containing all characters except last characters that satisfy the given [predicate].
     */
    @NotNull
    public static CharSequence dropLastWhile(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        if (charSequence != null) {
            for (int index = lastIndex(charSequence); index >= 0; index--) {
                if (!predicate.accept(charSequence.charAt(index))) {
                    return charSequence.subSequence(0, index + 1);
                }
            }
        }
        return "";
    }

    /**
     * Returns a string containing all characters except last characters that satisfy the given [predicate].
     */
    @NotNull
    public static String dropLastWhile(@Nullable String string, @NotNull Predicate<Character> predicate) {
        if (string != null) {
            for (int index = lastIndex(string); index >= 0; index--) {
                if (!predicate.accept(string.charAt(index))) {
                    return string.substring(0, index + 1);
                }
            }
        }
        return "";
    }

    /**
     * Returns a subsequence of this char sequence containing all characters except first characters that satisfy the given [predicate].
     */
    @NotNull
    public static CharSequence dropWhile(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        if (charSequence != null) {
            for (int index : indices(charSequence)) {
                if (!predicate.accept(charSequence.charAt(index))) {
                    return charSequence.subSequence(index, count(charSequence));
                }
            }
        }
        return "";
    }

    /**
     * Returns a string containing all characters except first characters that satisfy the given [predicate].
     */
    @NotNull
    public static String dropWhile(@Nullable String string, @NotNull Predicate<Character> predicate) {
        if (string != null) {
            for (int index : indices(string)) {
                if (!predicate.accept(string.charAt(index))) {
                    return string.substring(index);
                }
            }
        }
        return "";
    }


    /* ******************************************* slice ****************************************** */


    /**
     * Returns a char sequence containing characters of the original char sequence at the specified range of [indices].
     */
    @NotNull
    public static CharSequence slice(@Nullable CharSequence charSequence, @NotNull IntRange indices) {
        if (indices.isEmpty()) return "";
        return subSequence(charSequence, indices);
    }

    /**
     * Returns a string containing characters of the original string at the specified range of [indices].
     */
    @NotNull
    public static String slice(@Nullable String string, @NotNull IntRange indices) {
        if (indices.isEmpty()) return "";
        return substring(string, indices);
    }

    /**
     * Returns a char sequence containing characters of the original char sequence at specified [indices].
     */
    @NotNull
    public static CharSequence slice(@Nullable CharSequence charSequence, @NotNull Iterable<Integer> indices) {
        if (charSequence == null) return "";
        StringBuilder result = new StringBuilder();
        for (int i : indices) {
            result.append(charSequence.charAt(i));
        }
        return result;
    }

    /**
     * Returns a string containing characters of the original string at specified [indices].
     */
    @NotNull
    public static String slice(@Nullable String string, @NotNull Iterable<Integer> indices) {
        return slice(((CharSequence) string), indices).toString();
    }


    /* ******************************************* take ****************************************** */


    /**
     * Returns a subsequence of this char sequence containing the first [n] characters from this char sequence, or the entire char sequence if this char sequence is shorter.
     */
    @NotNull
    public static CharSequence take(@Nullable CharSequence charSequence, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than to zero.");
        }
        return orEmpty(charSequence).subSequence(0, Math.min(n, count(charSequence)));
    }

    /**
     * Returns a string containing the first [n] characters from this string, or the entire string if this string is shorter.
     */
    @NotNull
    public static String take(@Nullable String string, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than to zero.");
        }
        return orEmpty(string).substring(0, Math.min(n, count(string)));
    }

    /**
     * Returns a subsequence of this char sequence containing the last [n] characters from this char sequence, or the entire char sequence if this char sequence is shorter.
     */
    @NotNull
    public static CharSequence takeLast(@Nullable CharSequence charSequence, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than to zero.");
        }
        int length = count(charSequence);
        return orEmpty(charSequence).subSequence(length - Math.min(n, length), length);
    }

    /**
     * Returns a string containing the last [n] characters from this string, or the entire string if this string is shorter.
     */
    @NotNull
    public static String takeLast(@Nullable String string, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than to zero.");
        }
        int length = count(string);
        return orEmpty(string).substring(length - Math.min(n, length));
    }

    /**
     * Returns a subsequence of this char sequence containing last characters that satisfy the given [predicate].
     */
    @NotNull
    public static CharSequence takeLastWhile(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        if (charSequence != null) {
            for (int index = lastIndex(charSequence); index >= 0; index--) {
                if (!predicate.accept(charSequence.charAt(index))) {
                    return charSequence.subSequence(index + 1, count(charSequence));
                }
            }
        }
        return orEmpty(charSequence).subSequence(0, count(charSequence));
    }

    /**
     * Returns a string containing last characters that satisfy the given [predicate].
     */
    @NotNull
    public static String takeLastWhile(@Nullable String string, @NotNull Predicate<Character> predicate) {
        if (string != null) {
            for (int index = lastIndex(string); index >= 0; index--) {
                if (!predicate.accept(string.charAt(index))) {
                    return string.substring(index + 1);
                }
            }
        }
        return orEmpty(string);
    }

    /**
     * Returns a subsequence of this char sequence containing the first characters that satisfy the given [predicate].
     */
    @NotNull
    public static CharSequence takeWhile(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        if (charSequence != null) {
            for (int index = 0, size = count(charSequence); index < size; index++) {
                if (!predicate.accept(charSequence.charAt(index))) {
                    return charSequence.subSequence(0, index);
                }
            }
        }
        return orEmpty(charSequence).subSequence(0, count(charSequence));
    }

    /**
     * Returns a string containing the first characters that satisfy the given [predicate].
     */
    @NotNull
    public static String takeWhile(@Nullable String string, @NotNull Predicate<Character> predicate) {
        if (string != null) {
            for (int index = 0, size = count(string); index < size; index++) {
                if (!predicate.accept(string.charAt(index))) {
                    return string.substring(0, index);
                }
            }
        }
        return orEmpty(string);
    }


    /* ******************************************* associate ****************************************** */


    /**
     * Populates and returns the [destination] mutable map with key-value pairs
     * provided by [transform] function applied to each character of the given char sequence.
     * <p>
     * If any of two pairs would have the same key the last one gets added to the map.
     */
    @NotNull
    public static <K, V, M extends Map<K, V>> M associateTo(@Nullable CharSequence charSequence, @NotNull M destination,
                                                            @NotNull Transformer<Character, Pair<K, V>> transform) {
        for (char element : iterable(charSequence)) {
            Pair<K, V> pair = transform.transform(element);
            destination.put(pair.first, pair.second);
        }
        return destination;
    }

    /**
     * Returns a [Map] containing key-value pairs provided by [transform] function
     * applied to characters of the given char sequence.
     * <p>
     * If any of two pairs would have the same key the last one gets added to the map.
     * <p>
     * The returned map preserves the entry iteration order of the original char sequence.
     */
    @NotNull
    public static <K, V> Map<K, V> associate(@Nullable CharSequence charSequence, @NotNull Transformer<Character, Pair<K, V>> transform) {
        int capacity = Math.max(capacity(count(charSequence)), 16);
        return associateTo(charSequence, new LinkedHashMap<K, V>(capacity), transform);
    }

    /**
     * Populates and returns the [destination] mutable map with key-value pairs,
     * where key is provided by the [keySelector] function applied to each character of the given char sequence
     * and value is the character itself.
     * <p>
     * If any two characters would have the same key returned by [keySelector] the last one gets added to the map.
     */
    @NotNull
    public static <K, M extends Map<K, Character>> M associateByTo(@Nullable CharSequence charSequence, @NotNull M destination, @NotNull Transformer<Character, K> keySelector) {
        for (char element : iterable(charSequence)) {
            destination.put(keySelector.transform(element), element);
        }
        return destination;
    }

    /**
     * Returns a [Map] containing the characters from the given char sequence indexed by the key
     * returned from [keySelector] function applied to each character.
     * <p>
     * If any two characters would have the same key returned by [keySelector] the last one gets added to the map.
     * <p>
     * The returned map preserves the entry iteration order of the original char sequence.
     */
    @NotNull
    public static <K> Map<K, Character> associateBy(@Nullable CharSequence charSequence, @NotNull Transformer<Character, K> keySelector) {
        int capacity = Math.max(capacity(count(charSequence)), 16);
        return associateByTo(charSequence, new LinkedHashMap<K, Character>(capacity), keySelector);
    }

    /**
     * Populates and returns the [destination] mutable map with key-value pairs,
     * where key is provided by the [keySelector] function and
     * and value is provided by the [valueTransform] function applied to characters of the given char sequence.
     * <p>
     * If any two characters would have the same key returned by [keySelector] the last one gets added to the map.
     */
    @NotNull
    public static <K, V, M extends Map<K, V>> M associateByTo(@Nullable CharSequence charSequence, @NotNull M destination,
                                                              @NotNull Transformer<Character, K> keySelector, @NotNull Transformer<Character, V> valueTransform) {
        for (char element : iterable(charSequence)) {
            destination.put(keySelector.transform(element), valueTransform.transform(element));
        }
        return destination;
    }

    /**
     * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to characters of the given char sequence.
     * <p>
     * If any two characters would have the same key returned by [keySelector] the last one gets added to the map.
     * <p>
     * The returned map preserves the entry iteration order of the original char sequence.
     */
    @NotNull
    public static <K, V> Map<K, V> associateBy(@Nullable CharSequence charSequence, @NotNull Transformer<Character, K> keySelector, @NotNull Transformer<Character, V> valueTransform) {
        int capacity = Math.max(capacity(count(charSequence)), 16);
        return associateByTo(charSequence, new LinkedHashMap<K, V>(capacity), keySelector, valueTransform);
    }


    /* ******************************************* to ****************************************** */


    /**
     * Appends all characters to the given [destination] collection.
     */
    @NotNull
    public static <C extends Collection<Character>> C toCollection(@Nullable CharSequence charSequence, @NotNull C destination) {
        for (char item : iterable(charSequence)) {
            destination.add(item);
        }
        return destination;
    }

    /**
     * Returns a [HashSet] of all characters.
     */
    @NotNull
    public static HashSet<Character> toHashSet(@Nullable CharSequence charSequence) {
        return toCollection(charSequence, new HashSet<Character>(capacity(count(charSequence))));
    }

    /**
     * Returns a [List] filled with all characters of this char sequence.
     */
    @NotNull
    public static List<Character> toList(@Nullable CharSequence charSequence) {
        return toCollection(charSequence, new ArrayList<Character>(count(charSequence)));
    }

    /**
     * Returns a [Set] of all characters.
     * <p>
     * The returned set preserves the element iteration order of the original char sequence.
     */
    @NotNull
    public static Set<Character> toSet(@Nullable CharSequence charSequence) {
        if (charSequence == null || count(charSequence) == 0) {
            return new LinkedHashSet<Character>();
        } else if (count(charSequence) == 1) {
            Set<Character> characterSet = new LinkedHashSet<Character>();
            characterSet.add(charSequence.charAt(0));
            return characterSet;
        } else {
            return toCollection(charSequence, new LinkedHashSet<Character>(capacity(count(charSequence))));
        }
    }

    /**
     * Returns a [SortedSet][java.util.SortedSet] of all characters.
     */
    @NotNull
    public static SortedSet<Character> toSortedSet(@Nullable CharSequence charSequence) {
        return toCollection(charSequence, new TreeSet<Character>());
    }


    /* ******************************************* flatMap ****************************************** */


    /**
     * Returns a single list of all elements yielded from results of [transform] function being invoked on each character of original char sequence.
     */
    @NotNull
    public static <R> List<R> flatMap(@Nullable CharSequence charSequence, @NotNull Transformer<Character, Iterable<R>> transform) {
        return flatMapTo(charSequence, new ArrayList<R>(), transform);
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each character of original char sequence, to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C flatMapTo(@Nullable CharSequence charSequence, @NotNull C destination,
                                                           Transformer<Character, Iterable<R>> transform) {
        for (char element : iterable(charSequence)) {
            for (R r : transform.transform(element)) {
                destination.add(r);
            }
        }
        return destination;
    }


    /* ******************************************* group ****************************************** */


    /**
     * Groups characters of the original char sequence by the key returned by the given [keySelector] function
     * applied to each character and returns a map where each group key is associated with a list of corresponding characters.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original char sequence.
     */
    @NotNull
    public static <K> Map<K, List<Character>> groupBy(@Nullable CharSequence charSequence, @NotNull Transformer<Character, K> keySelector) {
        return groupByTo(charSequence, new LinkedHashMap<K, List<Character>>(), keySelector);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each character of the original char sequence
     * by the key returned by the given [keySelector] function applied to the character
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original char sequence.
     */
    @NotNull
    public static <K, V> Map<K, List<V>> groupBy(@Nullable CharSequence charSequence, @NotNull Transformer<Character, K> keySelector, @NotNull Transformer<Character, V> valueTransform) {
        return groupByTo(charSequence, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }

    /**
     * Groups characters of the original char sequence by the key returned by the given [keySelector] function
     * applied to each character and puts to the [destination] map each group key associated with a list of corresponding characters.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, M extends Map<K, List<Character>>> M groupByTo(@Nullable CharSequence charSequence, @NotNull M destination, @NotNull Transformer<Character, K> keySelector) {
        DefaultValue<List<Character>> defaultValue = new DefaultValue<List<Character>>() {
            @NotNull
            @Override
            public List<Character> get() {
                return new ArrayList<Character>();
            }
        };
        for (char element : iterable(charSequence)) {
            K key = keySelector.transform(element);
            List<Character> list = destination.get(key);
            if (list == null) {
                list = defaultValue.get();
                destination.put(key, list);
            }
            list.add(element);
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each character of the original char sequence
     * by the key returned by the given [keySelector] function applied to the character
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, V, M extends Map<K, List<V>>> M groupByTo(@Nullable CharSequence charSequence, @NotNull M destination,
                                                                @NotNull Transformer<Character, K> keySelector,
                                                                @NotNull Transformer<Character, V> valueTransform) {
        DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
            @NotNull
            @Override
            public List<V> get() {
                return new ArrayList<V>();
            }
        };
        for (char element : iterable(charSequence)) {
            K key = keySelector.transform(element);
            List<V> list = destination.get(key);
            if (list == null) {
                list = defaultValue.get();
                destination.put(key, list);
            }
            list.add(valueTransform.transform(element));
        }
        return destination;
    }


    /* ******************************************* map ****************************************** */


    /**
     * Applies the given [transform] function to each character of the original char sequence
     * and appends the results to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapTo(@Nullable CharSequence charSequence, @NotNull C destination, @NotNull Transformer<Character, R> transform) {
        for (char item : iterable(charSequence)) {
            destination.add(transform.transform(item));
        }
        return destination;
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each character in the original char sequence.
     */
    @NotNull
    public static <R> List<R> map(@Nullable CharSequence charSequence, @NotNull Transformer<Character, R> transform) {
        return mapTo(charSequence, new ArrayList<R>(count(charSequence)), transform);
    }

    /**
     * Applies the given [transform] function to each character and its index in the original char sequence
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of a character and the character itself
     *                  and returns the result of the transform applied to the character.
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapIndexedTo(@Nullable CharSequence charSequence, @NotNull C destination, @NotNull IndexedTransformer<Character, R> transform) {
        int index = 0;
        for (char item : iterable(charSequence)) {
            destination.add(transform.transform(index++, item));
        }
        return destination;
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each character and its index in the original char sequence.
     *
     * @param transform function that takes the index of a character and the character itself
     *                  and returns the result of the transform applied to the character.
     */
    @NotNull
    public static <R> List<R> mapIndexed(@Nullable CharSequence charSequence, @NotNull IndexedTransformer<Character, R> transform) {
        return mapIndexedTo(charSequence, new ArrayList<R>(count(charSequence)), transform);
    }

    /**
     * Applies the given [transform] function to each character in the original char sequence
     * and appends only the non-null results to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapNotNullTo(@Nullable CharSequence charSequence, @NotNull final C destination, @NotNull final NullableTransformer<Character, R> transform) {
        forEach(charSequence, new Action<Character>() {
            @Override
            public void action(@NotNull Character character) {
                R r = transform.transform(character);
                if (r != null) destination.add(r);
            }
        });
        return destination;
    }

    /**
     * Returns a list containing only the non-null results of applying the given [transform] function
     * to each character in the original char sequence.
     */
    @NotNull
    public static <R> List<R> mapNotNull(@Nullable CharSequence charSequence, @NotNull NullableTransformer<Character, R> transform) {
        return mapNotNullTo(charSequence, new ArrayList<R>(), transform);
    }

    /**
     * Applies the given [transform] function to each character and its index in the original char sequence
     * and appends only the non-null results to the given [destination].
     *
     * @param transform function that takes the index of a character and the character itself
     *                  and returns the result of the transform applied to the character.
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapIndexedNotNullTo(@Nullable CharSequence charSequence, @NotNull final C destination, @NotNull final NullableIndexedTransformer<Character, R> transform) {
        forEachIndexed(charSequence, new IndexedAction<Character>() {
            @Override
            public void action(int index, @NotNull Character character) {
                R r = transform.transform(index, character);
                if (r != null) destination.add(r);
            }
        });
        return destination;
    }

    /**
     * Returns a list containing only the non-null results of applying the given [transform] function
     * to each character and its index in the original char sequence.
     *
     * @param transform function that takes the index of a character and the character itself
     *                  and returns the result of the transform applied to the character.
     */
    @NotNull
    public static <R> List<R> mapIndexedNotNull(@Nullable CharSequence charSequence, @NotNull NullableIndexedTransformer<Character, R> transform) {
        return mapIndexedNotNullTo(charSequence, new ArrayList<R>(), transform);
    }


    /* ******************************************* withIndex ****************************************** */


    /**
     * Returns a lazy [Iterable] of [IndexedValue] for each character of the original char sequence.
     */
    @NotNull
    public static Iterable<IndexedValue<Character>> withIndex(@Nullable final CharSequence charSequence) {
        return new IndexingIterable<Character>(new DefaultValue<Iterator<Character>>() {
            @NotNull
            @Override
            public Iterator<Character> get() {
                return iterator(charSequence);
            }
        });
    }


    /* ******************************************* all ****************************************** */


    /**
     * Returns `true` if all characters match the given [predicate].
     */
    public static boolean all(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        for (char element : iterable(charSequence)) if (!predicate.accept(element)) return false;
        return true;
    }


    /* ******************************************* any ****************************************** */


    /**
     * Returns `true` if char sequence has at least one character.
     */
    public static boolean any(@Nullable CharSequence charSequence) {
        return !isEmpty(charSequence);
    }

    /**
     * Returns `true` if at least one character matches the given [predicate].
     */
    public static boolean any(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        for (char element : iterable(charSequence)) if (predicate.accept(element)) return true;
        return false;
    }


    /* ******************************************* count ****************************************** */


    /**
     * Returns the length of this char sequence.
     */
    public static int count(@Nullable CharSequence charSequence) {
        return charSequence != null ? charSequence.length() : 0;
    }

    /**
     * Returns the number of characters matching the given [predicate].
     */
    public static int count(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        int count = 0;
        if (charSequence != null) {
            for (char element : iterable(charSequence)) if (predicate.accept(element)) count++;
        }
        return count;
    }


    /* ******************************************* fold ****************************************** */


    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each character.
     */
    @NotNull
    public static <R> R fold(@Nullable CharSequence charSequence, @NotNull R initial, @NotNull Operation<Character, R> operation) {
        R accumulator = initial;
        for (char element : iterable(charSequence)) accumulator = operation.operation(accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each character with its index in the original char sequence.
     *
     * @param operation function that takes the index of a character, current accumulator value
     *                  and the character itself, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldIndexed(@Nullable CharSequence charSequence, @NotNull R initial, @NotNull IndexedOperation<Character, R> operation) {
        int index = 0;
        R accumulator = initial;
        for (char element : iterable(charSequence)) accumulator = operation.operation(index++, accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left to each character and current accumulator value.
     */
    @NotNull
    public static <R> R foldRight(@Nullable CharSequence charSequence, @NotNull R initial, @NotNull RightOperation<Character, R> operation) {
        R accumulator = initial;
        if (charSequence != null) {
            int index = lastIndex(charSequence);
            while (index >= 0) {
                accumulator = operation.operation(charSequence.charAt(index--), accumulator);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left
     * to each character with its index in the original char sequence and current accumulator value.
     *
     * @param operation function that takes the index of a character, the character itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldRightIndexed(@Nullable CharSequence charSequence, @NotNull R initial, @NotNull IndexedRightOperation<Character, R> operation) {
        R accumulator = initial;
        if (charSequence != null) {
            int index = lastIndex(charSequence);
            while (index >= 0) {
                accumulator = operation.operation(index, charSequence.charAt(index), accumulator);
                --index;
            }
        }
        return accumulator;
    }


    /* ******************************************* forEach ****************************************** */


    /**
     * Performs the given [action] on each character.
     */
    public static void forEach(@Nullable CharSequence charSequence, @NotNull Action<Character> action) {
        for (char item : iterable(charSequence)) action.action(item);
    }

    /**
     * Performs the given [action] on each character, providing sequential index with the character.
     *
     * @param action function that takes the index of a character and the character itself
     *               and performs the desired action on the character.
     */
    public static void forEachIndexed(@Nullable CharSequence charSequence, @NotNull IndexedAction<Character> action) {
        int index = 0;
        for (char item : iterable(charSequence)) action.action(index++, item);
    }


    /* ******************************************* max ****************************************** */


    /**
     * Returns the largest character or `null` if there are no characters.
     */
    @Nullable
    public static Character maxOrNull(@Nullable CharSequence charSequence) {
        if (isNullOrEmpty(charSequence)) return null;
        char max = charSequence.charAt(0);
        for (int i = 1, lastIndex = lastIndex(charSequence); i <= lastIndex; i++) {
            char e = charSequence.charAt(i);
            if (max < e) max = e;
        }
        return max;
    }


    /**
     * Returns the first character yielding the largest value of the given function or `null` if there are no characters.
     */
    @Nullable
    public static <R extends Comparable<R>> Character maxByOrNull(@Nullable CharSequence charSequence, @NotNull Transformer<Character, R> selector) {
        if (isNullOrEmpty(charSequence)) return null;
        char maxElem = charSequence.charAt(0);
        R maxValue = selector.transform(maxElem);
        for (int i = 1, lastIndex = lastIndex(charSequence); i <= lastIndex; i++) {
            char e = charSequence.charAt(i);
            R v = selector.transform(e);
            if (maxValue.compareTo(v) < 0) {
                maxElem = e;
                maxValue = v;
            }
        }
        return maxElem;
    }

    /**
     * Returns the first character having the largest value according to the provided [comparator] or `null` if there are no characters.
     */
    @Nullable
    public static Character maxWithOrNull(@Nullable CharSequence charSequence, Comparator<Character> comparator) {
        if (isNullOrEmpty(charSequence)) return null;
        char max = charSequence.charAt(0);
        for (int i = 1, lastIndex = lastIndex(charSequence); i <= lastIndex; i++) {
            char e = charSequence.charAt(i);
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the largest character or `null` if there are no characters.
     * @deprecated Please use maxOrNull instead
     */
    @Nullable
    @Deprecated
    public static Character max(@Nullable CharSequence charSequence) {
        return maxOrNull(charSequence);
    }

    /**
     * Returns the first character yielding the largest value of the given function or `null` if there are no characters.
     * @deprecated Please use maxByOrNull instead
     */
    @Nullable
    @Deprecated
    public static <R extends Comparable<R>> Character maxBy(@Nullable CharSequence charSequence, @NotNull Transformer<Character, R> selector) {
        return maxByOrNull(charSequence, selector);
    }

    /**
     * Returns the first character having the largest value according to the provided [comparator] or `null` if there are no characters.
     * @deprecated Please use maxWithOrNull instead
     */
    @Nullable
    @Deprecated
    public static Character maxWith(@Nullable CharSequence charSequence, Comparator<Character> comparator) {
        return maxWithOrNull(charSequence, comparator);
    }


    /* ******************************************* min ****************************************** */


    /**
     * Returns the smallest character or `null` if there are no characters.
     */
    @Nullable
    public static Character minOrNull(@Nullable CharSequence charSequence) {
        if (isNullOrEmpty(charSequence)) return null;
        char min = charSequence.charAt(0);
        for (int i = 1, lastIndex = lastIndex(charSequence); i <= lastIndex; i++) {
            char e = charSequence.charAt(i);
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the first character yielding the smallest value of the given function or `null` if there are no characters.
     */
    @Nullable
    public static <R extends Comparable<R>> Character minByOrNull(@Nullable CharSequence charSequence, @NotNull Transformer<Character, R> selector) {
        if (isNullOrEmpty(charSequence)) return null;
        char minElem = charSequence.charAt(0);
        R minValue = selector.transform(minElem);
        for (int i = 1, lastIndex = lastIndex(charSequence); i <= lastIndex; i++) {
            char e = charSequence.charAt(i);
            R v = selector.transform(e);
            if (minValue.compareTo(v) > 0) {
                minElem = e;
                minValue = v;
            }
        }
        return minElem;
    }

    /**
     * Returns the first character having the smallest value according to the provided [comparator] or `null` if there are no characters.
     */
    @Nullable
    public static Character minWithOrNull(@Nullable CharSequence charSequence, @NotNull Comparator<Character> comparator) {
        if (isNullOrEmpty(charSequence)) return null;
        char min = charSequence.charAt(0);
        for (int i = 1, lastIndex = lastIndex(charSequence); i <= lastIndex; i++) {
            char e = charSequence.charAt(i);
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }


    /**
     * Returns the smallest character or `null` if there are no characters.
     * @deprecated Please use minOrNull instead
     */
    @Nullable
    @Deprecated
    public static Character min(@Nullable CharSequence charSequence) {
        return minOrNull(charSequence);
    }

    /**
     * Returns the first character yielding the smallest value of the given function or `null` if there are no characters.
     * @deprecated Please use minByOrNull instead
     */
    @Nullable
    @Deprecated
    public static <R extends Comparable<R>> Character minBy(@Nullable CharSequence charSequence, @NotNull Transformer<Character, R> selector) {
        return minByOrNull(charSequence, selector);
    }

    /**
     * Returns the first character having the smallest value according to the provided [comparator] or `null` if there are no characters.
     * @deprecated Please use minWithOrNull instead
     */
    @Nullable
    @Deprecated
    public static Character minWith(@Nullable CharSequence charSequence, @NotNull Comparator<Character> comparator) {
        return minWithOrNull(charSequence, comparator);
    }


    /* ******************************************* none ****************************************** */


    /**
     * Returns `true` if the char sequence has no characters.
     */
    public static boolean none(@Nullable CharSequence charSequence) {
        return isEmpty(charSequence);
    }

    /**
     * Returns `true` if no characters match the given [predicate].
     */
    public static boolean none(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        for (char element : iterable(charSequence)) if (predicate.accept(element)) return false;
        return true;
    }


    /* ******************************************* onEach ****************************************** */


    /**
     * Performs the given [action] on each character and returns the char sequence itself afterwards.
     */
    @NotNull
    public static <S extends CharSequence> S onEach(@Nullable S s, @NotNull Action<Character> action) {
        for (char element : iterable(s)) action.action(element);
        //noinspection unchecked
        return (S) orEmpty(s);
    }


    /* ******************************************* reduce ****************************************** */


    /**
     * Accumulates value starting with the first character and applying [operation] from left to right to current accumulator value and each character.
     */
    @NotNull
    public static Character reduce(@Nullable CharSequence charSequence, @NotNull Operation<Character, Character> operation) {
        if (isNullOrEmpty(charSequence))
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        char accumulator = charSequence.charAt(0);
        for (int index = 1, lastIndex = lastIndex(charSequence); index <= lastIndex; index++) {
            accumulator = operation.operation(accumulator, charSequence.charAt(index));
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first character and applying [operation] from left to right
     * to current accumulator value and each character with its index in the original char sequence.
     *
     * @param operation function that takes the index of a character, current accumulator value
     *                  and the character itself and calculates the next accumulator value.
     */
    @NotNull
    public static Character reduceIndexed(@Nullable CharSequence charSequence, @NotNull IndexedOperation<Character, Character> operation) {
        if (isNullOrEmpty(charSequence))
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        char accumulator = charSequence.charAt(0);
        for (int index = 1, lastIndex = lastIndex(charSequence); index <= lastIndex; index++) {
            accumulator = operation.operation(index, accumulator, charSequence.charAt(index));
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last character and applying [operation] from right to left to each character and current accumulator value.
     */
    @NotNull
    public static Character reduceRight(@Nullable CharSequence charSequence, @NotNull Operation<Character, Character> operation) {
        int index = lastIndex(charSequence);
        if (charSequence == null || index < 0)
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        char accumulator = charSequence.charAt(index--);
        while (index >= 0) {
            accumulator = operation.operation(charSequence.charAt(index--), accumulator);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last character and applying [operation] from right to left
     * to each character with its index in the original char sequence and current accumulator value.
     *
     * @param operation function that takes the index of a character, the character itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static Character reduceRightIndexed(@Nullable CharSequence charSequence, @NotNull IndexedOperation<Character, Character> operation) {
        int index = lastIndex(charSequence);
        if (charSequence == null || index < 0)
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        char accumulator = charSequence.charAt(index--);
        while (index >= 0) {
            accumulator = operation.operation(index, charSequence.charAt(index), accumulator);
            --index;
        }
        return accumulator;
    }


    /* ******************************************* sum ****************************************** */


    /**
     * Returns the sum of all values produced by [selector] function applied to each character in the char sequence.
     */
    public static int sumBy(@Nullable CharSequence charSequence, @NotNull Transformer<Character, Integer> selector) {
        int sum = 0;
        for (char element : iterable(charSequence)) {
            sum += selector.transform(element);
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each character in the char sequence.
     */
    public static double sumByDouble(@Nullable CharSequence charSequence, @NotNull Transformer<Character, Double> selector) {
        double sum = 0;
        for (char element : iterable(charSequence)) {
            sum += selector.transform(element);
        }
        return sum;
    }


    /* ******************************************* chunked ****************************************** */


    /**
     * Splits this char sequence into a list of strings each not exceeding the given [size].
     * <p>
     * The last string in the resulting list may have less characters than the given [size].
     *
     * @param size the number of elements to take in each string, must be positive and can be greater than the number of elements in this char sequence.
     */
    @NotNull
    public static List<String> chunked(@Nullable CharSequence charSequence, int size) {
        return windowed(charSequence, size, size, true);
    }

    /**
     * Splits this char sequence into several char sequences each not exceeding the given [size]
     * and applies the given [transform] function to an each.
     *
     * @param size the number of elements to take in each char sequence, must be positive and can be greater than the number of elements in this char sequence.
     * @return list of results of the [transform] applied to an each char sequence.
     * <p>
     * Note that the char sequence passed to the [transform] function is ephemeral and is valid only inside that function.
     * You should not store it or allow it to escape in some way, unless you made a snapshot of it.
     * The last char sequence may have less characters than the given [size].
     */
    @NotNull
    public static <R> List<R> chunked(@Nullable CharSequence charSequence, int size, @NotNull Transformer<CharSequence, R> transform) {
        return windowed(charSequence, size, size, true, transform);
    }

    /**
     * Splits this char sequence into several char sequences each not exceeding the given [size]
     * and applies the given [transform] function to an each.
     *
     * @param size the number of elements to take in each char sequence, must be positive and can be greater than the number of elements in this char sequence.
     * @return sequence of results of the [transform] applied to an each char sequence.
     * <p>
     * Note that the char sequence passed to the [transform] function is ephemeral and is valid only inside that function.
     * You should not store it or allow it to escape in some way, unless you made a snapshot of it.
     * The last char sequence may have less characters than the given [size].
     */
    @NotNull
    public static <R> Iterable<R> chunkedIterable(@Nullable CharSequence charSequence, int size, @NotNull Transformer<CharSequence, R> transform) {
        return windowedIterable(charSequence, size, size, true, transform);
    }

    /**
     * Splits this char sequence into a sequence of strings each not exceeding the given [size].
     * <p>
     * The last string in the resulting sequence may have less characters than the given [size].
     *
     * @param size the number of elements to take in each string, must be positive and can be greater than the number of elements in this char sequence.
     */
    @NotNull
    public static Iterable<String> chunkedIterable(@Nullable CharSequence charSequence, int size) {
        return chunkedIterable(charSequence, size, new Transformer<CharSequence, String>() {
            @NotNull
            @Override
            public String transform(@NotNull CharSequence charSequence) {
                return charSequence.toString();
            }
        });
    }


    /* ******************************************* partition ****************************************** */


    /**
     * Splits the original char sequence into pair of char sequences,
     * where *first* char sequence contains characters for which [predicate] yielded `true`,
     * while *second* char sequence contains characters for which [predicate] yielded `false`.
     */
    @NotNull
    public static Pair<CharSequence, CharSequence> partition(@Nullable CharSequence charSequence, @NotNull Predicate<Character> predicate) {
        StringBuilder first = new StringBuilder();
        StringBuilder second = new StringBuilder();
        for (char element : iterable(charSequence)) {
            if (predicate.accept(element)) {
                first.append(element);
            } else {
                second.append(element);
            }
        }
        return new Pair<CharSequence, CharSequence>(first, second);
    }

    /**
     * Splits the original string into pair of strings,
     * where *first* string contains characters for which [predicate] yielded `true`,
     * while *second* string contains characters for which [predicate] yielded `false`.
     */
    @NotNull
    public static Pair<String, String> partition(@Nullable String string, @NotNull Predicate<Character> predicate) {
        StringBuilder first = new StringBuilder();
        StringBuilder second = new StringBuilder();
        for (char element : iterable(string)) {
            if (predicate.accept(element)) {
                first.append(element);
            } else {
                second.append(element);
            }
        }
        return new Pair<String, String>(first.toString(), second.toString());
    }


    /* ******************************************* windowed ****************************************** */


    /**
     * Returns a list of results of applying the given [transform] function to
     * an each char sequence representing a view over the window of the given [size]
     * sliding along this char sequence with the given [step].
     * <p>
     * Note that the char sequence passed to the [transform] function is ephemeral and is valid only inside that function.
     * You should not store it or allow it to escape in some way, unless you made a snapshot of it.
     * Several last char sequences may have less characters than the given [size].
     * <p>
     * Both [size] and [step] must be positive and can be greater than the number of elements in this char sequence.
     *
     * @param size           the number of elements to take in each window
     * @param step           the number of elements to move the window forward by on an each step, by default 1
     * @param partialWindows controls whether or not to keep partial windows in the end if any,
     *                       by default `false` which means partial windows won't be preserved
     */
    @NotNull
    public static <R> List<R> windowed(@Nullable CharSequence charSequence, final int size, final int step, boolean partialWindows, @NotNull Transformer<CharSequence, R> transform) {
        if (size <= 0) {
            throw new IllegalArgumentException("Param 'size' is less than or equal to zero.");
        }
        if (step <= 0) {
            throw new IllegalArgumentException("Param 'step' is less than or equal to zero.");
        }
        int thisSize = count(charSequence);
        List<R> result = new ArrayList<R>((thisSize + step - 1) / step);
        if (charSequence != null) {
            int index = 0;
            while (index < thisSize) {
                int end = index + size;
                int coercedEnd;
                if (end > thisSize) {
                    if (partialWindows) {
                        coercedEnd = thisSize;
                    } else {
                        break;
                    }
                } else {
                    coercedEnd = end;
                }
                result.add(transform.transform(charSequence.subSequence(index, coercedEnd)));
                index += step;
            }
        }
        return result;
    }

    /**
     * Returns a list of snapshots of the window of the given [size]
     * sliding along this char sequence with the given [step], where each
     * snapshot is a string.
     * <p>
     * Several last strings may have less characters than the given [size].
     * <p>
     * Both [size] and [step] must be positive and can be greater than the number of elements in this char sequence.
     *
     * @param size           the number of elements to take in each window
     * @param step           the number of elements to move the window forward by on an each step, by default 1
     * @param partialWindows controls whether or not to keep partial windows in the end if any,
     *                       by default `false` which means partial windows won't be preserved
     */
    @NotNull
    public static List<String> windowed(@Nullable CharSequence charSequence, int size, int step, boolean partialWindows) {
        return windowed(charSequence, size, step, partialWindows, new Transformer<CharSequence, String>() {
            @NotNull
            @Override
            public String transform(@Nullable CharSequence t) {
                return orEmpty(t).toString();
            }
        });
    }

    /**
     * Returns a sequence of results of applying the given [transform] function to
     * an each char sequence representing a view over the window of the given [size]
     * sliding along this char sequence with the given [step].
     * <p>
     * Note that the char sequence passed to the [transform] function is ephemeral and is valid only inside that function.
     * You should not store it or allow it to escape in some way, unless you made a snapshot of it.
     * Several last char sequences may have less characters than the given [size].
     * <p>
     * Both [size] and [step] must be positive and can be greater than the number of elements in this char sequence.
     *
     * @param size           the number of elements to take in each window
     * @param step           the number of elements to move the window forward by on an each step, by default 1
     * @param partialWindows controls whether or not to keep partial windows in the end if any,
     *                       by default `false` which means partial windows won't be preserved
     */
    @NotNull
    public static <R> Iterable<R> windowedIterable(@Nullable final CharSequence charSequence, final int size, final int step, boolean partialWindows, @NotNull final Transformer<CharSequence, R> transform) {
        if (size <= 0) {
            throw new IllegalArgumentException("Param 'size' is less than or equal to zero.");
        }
        if (step <= 0) {
            throw new IllegalArgumentException("Param 'step' is less than or equal to zero.");
        }
        IntProgression progression = (partialWindows ? indices(charSequence) : new IntRange(0, count(charSequence) - size));
        IntProgression windows = IntProgression.fromClosedRange(progression.getFirst(), progression.getLast(), step);
        return new TransformingIterable<Integer, R>(windows, new Transformer<Integer, R>() {
            @NotNull
            @Override
            public R transform(@NotNull Integer index) {
                return transform.transform(orEmpty(charSequence).subSequence(index, Math.min((index + size), count(charSequence))));
            }
        });
    }

    /**
     * Returns a sequence of snapshots of the window of the given [size]
     * sliding along this char sequence with the given [step], where each
     * snapshot is a string.
     * <p>
     * Several last strings may have less characters than the given [size].
     * <p>
     * Both [size] and [step] must be positive and can be greater than the number of elements in this char sequence.
     *
     * @param size           the number of elements to take in each window
     * @param step           the number of elements to move the window forward by on an each step, by default 1
     * @param partialWindows controls whether or not to keep partial windows in the end if any,
     *                       by default `false` which means partial windows won't be preserved
     */
    @NotNull
    public static Iterable<String> windowedIterable(@Nullable CharSequence charSequence, int size, int step, boolean partialWindows) {
        return windowedIterable(charSequence, size, step, partialWindows, new Transformer<CharSequence, String>() {
            @NotNull
            @Override
            public String transform(@NotNull CharSequence charSequence) {
                return charSequence.toString();
            }
        });
    }


    /* ******************************************* zip ****************************************** */


    /**
     * Returns a list of values built from the characters of `this` and the [other] char sequences with the same index
     * using the provided [transform] function applied to each pair of characters.
     * The returned list has length of the shortest char sequence.
     */
    @NotNull
    public static <V> List<V> zip(@Nullable CharSequence charSequence, @Nullable CharSequence other, @NotNull Transformer2<Character, Character, V> transform) {
        int length = Math.min(count(charSequence), count(other));
        List<V> list = new ArrayList<V>(length);
        if (charSequence != null && other != null) {
            for (int i = 0; i < length; i++) {
                list.add(transform.transform(charSequence.charAt(i), other.charAt(i)));
            }
        }
        return list;
    }

    /**
     * Returns a list of pairs built from the characters of `this` and the [other] char sequences with the same index
     * The returned list has length of the shortest char sequence.
     */
    @NotNull
    public static List<Pair<Character, Character>> zip(@Nullable CharSequence charSequence, @Nullable CharSequence other) {
        return zip(charSequence, other, new Transformer2<Character, Character, Pair<Character, Character>>() {
            @NotNull
            @Override
            public Pair<Character, Character> transform(@NotNull Character character, @NotNull Character character2) {
                return new Pair<Character, Character>(character, character2);
            }
        });
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to an each pair of two adjacent characters in this char sequence.
     * <p>
     * The returned list is empty if this char sequence contains less than two characters.
     */
    @NotNull
    public static <R> List<R> zipWithNext(@Nullable CharSequence charSequence, @NotNull Transformer2<Character, Character, R> transform) {
        int size = count(charSequence) - 1;
        if (charSequence == null || size < 1) return new ArrayList<R>(0);
        List<R> result = new ArrayList<R>(size);
        for (int index = 0; index < size; index++) {
            result.add(transform.transform(charSequence.charAt(index), charSequence.charAt(index + 1)));
        }
        return result;
    }

    /**
     * Returns a list of pairs of each two adjacent characters in this char sequence.
     * <p>
     * The returned list is empty if this char sequence contains less than two characters.
     */
    @NotNull
    public static List<Pair<Character, Character>> zipWithNext(@Nullable CharSequence charSequence) {
        return zipWithNext(charSequence, new Transformer2<Character, Character, Pair<Character, Character>>() {
            @NotNull
            @Override
            public Pair<Character, Character> transform(@NotNull Character character, @NotNull Character character2) {
                return new Pair<Character, Character>(character, character2);
            }
        });
    }


    /* ******************************************* as ****************************************** */


    /**
     * Creates an [Iterable] instance that wraps the original char sequence returning its characters when being iterated.
     */
    @NotNull
    public static Iterable<Character> asIterable(@Nullable CharSequence charSequence) {
        if (charSequence == null || charSequence instanceof String && isEmpty(charSequence))
            return new ArrayList<Character>(0);
        return new CharSequenceIterable(charSequence);
    }

    /**
     * Calculate the initial capacity of a map, based on Guava's com.google.common.collect.Maps approach. This is equivalent
     * to the Collection constructor for HashSet, (c.size()/.75f) + 1, but provides further optimisations for very small or
     * very large sizes, allows support non-collection classes, and provides consistency for all map based class construction.
     */
    private static int capacity(int expectedSize) {
        if (expectedSize < 3) {
            return expectedSize + 1;
        }
        if (expectedSize < (Integer.MAX_VALUE / 2 + 1)) {
            return expectedSize + expectedSize / 3;
        }
        return Integer.MAX_VALUE; // any large value
    }

    private static class DelimitedRangesIterable implements Iterable<IntRange> {

        @NotNull
        private final CharSequence input;
        private final int startIndex;
        private final int limit;
        @NotNull
        private final NextMatch getNextMatch;

        DelimitedRangesIterable(@NotNull CharSequence input, int startIndex, int limit, @NotNull NextMatch getNextMatch) {
            this.input = input;
            this.startIndex = startIndex;
            this.limit = limit;
            this.getNextMatch = getNextMatch;
        }

        @NotNull
        @Override
        public Iterator<IntRange> iterator() {
            return new DelimitedRangesIterator(input, startIndex, limit, getNextMatch);
        }
    }

    private static class DelimitedRangesIterator implements Iterator<IntRange> {
        @NotNull
        private final CharSequence input;
        private final int limit;
        @NotNull
        private final NextMatch getNextMatch;

        @Nullable
        private IntRange nextItem = null;
        private int nextState = -1; // -1 for unknown, 0 for done, 1 for continue
        private int currentStartIndex;
        private int nextSearchIndex = currentStartIndex;
        private int counter = 0;

        public DelimitedRangesIterator(@NotNull CharSequence input, int startIndex, int limit, @NotNull NextMatch getNextMatch) {
            this.input = input;
            this.limit = limit;
            this.getNextMatch = getNextMatch;
            this.currentStartIndex = Math.max(Math.min(startIndex, input.length()), 0);
        }

        @Override
        public IntRange next() {
            if (nextState == -1)
                calcNext();
            if (nextState == 0)
                throw new NoSuchElementException();
            IntRange result = nextItem;
            // Clean next to avoid keeping reference on yielded instance
            nextItem = null;
            nextState = -1;
            return result;
        }

        @Override
        public boolean hasNext() {
            if (nextState == -1)
                calcNext();
            return nextState == 1;
        }

        private void calcNext() {
            if (nextSearchIndex < 0) {
                nextState = 0;
                nextItem = null;
            } else {
                if (limit > 0 && ++counter >= limit || nextSearchIndex > input.length()) {
                    nextItem = new IntRange(currentStartIndex, input.length() - 1);
                    nextSearchIndex = -1;
                } else {
                    Pair<Integer, Integer> match = getNextMatch.getNextMatch(input, nextSearchIndex);
                    if (match == null) {
                        nextItem = new IntRange(currentStartIndex, input.length() - 1);
                        nextSearchIndex = -1;
                    } else {
                        int index = match.first;
                        int length = match.second;
                        nextItem = new IntRange(currentStartIndex, index - 1);
                        currentStartIndex = index + length;
                        nextSearchIndex = currentStartIndex + (length == 0 ? 1 : 0);
                    }
                }
                nextState = 1;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    private interface NextMatch {
        @Nullable
        Pair<Integer, Integer> getNextMatch(@NotNull CharSequence charSequence, int startIndex);
    }
}