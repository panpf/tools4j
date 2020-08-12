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

package com.github.panpf.tools4j.regex;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexx {

    /**
     * IP v4
     */
    public static final Pattern IPV4 = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|(?:1\\d{2}|[1-9]?\\d))\\.){3}(?:25[0-5]|2[0-4]\\d|(?:1\\d{2}|[1-9]?\\d)))");

    /**
     * IP v6. Supports converged IP v4 format
     */
    public static final Pattern IPV6 = Pattern.compile("\\s*((([0-9A-Fa-f]{1,4}:){7}(([0-9A-Fa-f]{1,4})|:))|(([0-9A-Fa-f]{1,4}:){6}(:|((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})|(:[0-9A-Fa-f]{1,4})))|(([0-9A-Fa-f]{1,4}:){5}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:)(:[0-9A-Fa-f]{1,4}){0,4}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(:(:[0-9A-Fa-f]{1,4}){0,5}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})))(%.+)?\\s*");

    /**
     * Mac Address. Support for splitting in ':' and '-'
     */
    public static final Pattern MAC_ADDRESS = Pattern.compile("([A-Fa-f0-9]{2}(-[A-Fa-f0-9]{2}){5})|([A-Fa-f0-9]{2}(:[A-Fa-f0-9]{2}){5})");

    /**
     * Chinese (without Chinese symbols)
     */
    public static final Pattern CHINESE = Pattern.compile("[\\u4e00-\\u9fa5]+");

    /**
     * Chinese (including Chinese symbols)
     */
    public static final Pattern CHINESE_AND_SYMBOL = Pattern.compile("[^\\x00-\\xff]+");

    /**
     * Blank characters such as spaces, carriage returns, line feeds, etc.
     */
    public static final Pattern BLANK = Pattern.compile("[\\n\\s*\\r]+");

    /**
     * Email address
     */
    public static final Pattern EMAIL = Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");

    /**
     * URI
     */
    public static final Pattern URI = Pattern.compile("[a-zA-z]+://[^\\s]*");

    /**
     * Positive float number
     */
    public static final Pattern POSITIVE_FLOAT_NUMBER = Pattern.compile("[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*");

    /**
     * Negative float number
     */
    public static final Pattern NEGATIVE_FLOAT_NUMBER = Pattern.compile("-[1-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*");

    /**
     * Float number
     */
    public static final Pattern FLOAT_NUMBER = Pattern.compile("-?[1-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*");

    /**
     * Positive integer
     */
    public static final Pattern POSITIVE_INTEGER = Pattern.compile("[1-9]\\d*");

    /**
     * Negative integer
     */
    public static final Pattern NEGATIVE_INTEGER = Pattern.compile("-[1-9]\\d*");

    /**
     * Integer
     */
    public static final Pattern INTEGER = Pattern.compile("-?[1-9]\\d*");

    private Regexx() {
    }

    /**
     * Return true if the entire sequence of characters matches the given regular expression
     */
    public static boolean matches(@NotNull Pattern pattern, @Nullable CharSequence charSequence) {
        return pattern.matcher(charSequence != null ? charSequence : "").matches();
    }

    /**
     * Return true if the entire sequence of characters matches the given regular expression
     */
    public static boolean matches(@NotNull String regex, @Nullable CharSequence charSequence) {
        return matches(Pattern.compile(regex), charSequence != null ? charSequence : "");
    }

    /**
     * Returns true if the specified sequence of characters matches any given position from the specified position to the given regular expression
     */
    public static boolean find(@NotNull Pattern pattern, @Nullable CharSequence charSequence, int start) {
        return pattern.matcher(charSequence != null ? charSequence : "").find(start);
    }

    /**
     * Returns true if the specified sequence of characters matches any given position from the specified position to the given regular expression
     */
    public static boolean find(@NotNull String regex, @Nullable CharSequence charSequence, int start) {
        return find(Pattern.compile(regex), charSequence, start);
    }

    /**
     * Return true if a given regular expression is matched anywhere in a given sequence of characters
     */
    public static boolean find(@NotNull Pattern pattern, @Nullable CharSequence charSequence) {
        return pattern.matcher(charSequence != null ? charSequence : "").find();
    }

    /**
     * Return true if a given regular expression is matched anywhere in a given sequence of characters
     */
    public static boolean find(@NotNull String regex, @Nullable CharSequence charSequence) {
        return find(Pattern.compile(regex), charSequence);
    }

    /**
     * Return true if the given regular expression is matched at the beginning of the character sequence
     */
    public static boolean lookingAt(@NotNull Pattern pattern, @Nullable CharSequence charSequence) {
        return pattern.matcher(charSequence != null ? charSequence : "").lookingAt();
    }

    /**
     * Return true if the given regular expression is matched at the beginning of the character sequence
     */
    public static boolean lookingAt(@NotNull String regex, @Nullable CharSequence charSequence) {
        return lookingAt(Pattern.compile(regex), charSequence);
    }

    /**
     * Get the first matching string
     */
    @Nullable
    public static String getFirst(@NotNull Pattern pattern, @Nullable CharSequence charSequence) {
        Matcher matcher = pattern.matcher(charSequence != null ? charSequence : "");
        return matcher.find() ? matcher.group() : null;
    }

    /**
     * Get the first matching string
     */
    @Nullable
    public static String getFirst(@NotNull String regex, @Nullable CharSequence charSequence) {
        return getFirst(Pattern.compile(regex), charSequence);
    }

    /**
     * Get the all matching string
     */
    @NotNull
    public static String[] getAll(@NotNull Pattern pattern, @Nullable CharSequence charSequence) {
        Matcher matcher = pattern.matcher(charSequence != null ? charSequence : "");
        List<String> stringList = new LinkedList<>();
        while (matcher.find()) {
            stringList.add(matcher.group());
        }
        return stringList.toArray(new String[0]);
    }

    /**
     * Get the all matching string
     */
    @NotNull
    public static String[] getAll(@NotNull String regex, @Nullable CharSequence charSequence) {
        return getAll(Pattern.compile(regex), charSequence);
    }

    /**
     * Get the first matching group
     */
    @Nullable
    public static Group firstGroup(@NotNull Pattern pattern, @Nullable CharSequence charSequence) {
        Matcher matcher = pattern.matcher(charSequence != null ? charSequence : "");
        return matcher.find() ? new Group(matcher.start(), matcher.end(), matcher.group()) : null;
    }

    /**
     * Get the first matching group
     */
    @Nullable
    public static Group firstGroup(@NotNull String regex, @Nullable CharSequence charSequence) {
        return firstGroup(Pattern.compile(regex), charSequence);
    }

    /**
     * Get the all matching group
     */
    @NotNull
    public static Group[] allGroup(@NotNull Pattern pattern, @Nullable CharSequence charSequence) {
        Matcher matcher = pattern.matcher(charSequence != null ? charSequence : "");
        List<Group> stringList = new LinkedList<>();
        while (matcher.find()) {
            stringList.add(new Group(matcher.start(), matcher.end(), matcher.group()));
        }
        return stringList.toArray(new Group[0]);
    }

    /**
     * Get the all matching group
     */
    @NotNull
    public static Group[] allGroup(@NotNull String regex, @Nullable CharSequence charSequence) {
        return allGroup(Pattern.compile(regex), charSequence);
    }

    /**
     * Replace the first matching string
     */
    @NotNull
    public static String replaceFirst(@NotNull Pattern pattern, @Nullable CharSequence charSequence, @NotNull String replacement) {
        return pattern.matcher(charSequence != null ? charSequence : "").replaceFirst(replacement);
    }

    /**
     * Replace the first matching string
     */
    @NotNull
    public static String replaceFirst(@NotNull String regex, @Nullable CharSequence charSequence, @NotNull String replacement) {
        return replaceFirst(Pattern.compile(regex), charSequence, replacement);
    }

    /**
     * Replace the all matching string
     */
    @NotNull
    public static String replaceAll(@NotNull Pattern pattern, @Nullable CharSequence charSequence, @NotNull String replacement) {
        return pattern.matcher(charSequence != null ? charSequence : "").replaceAll(replacement);
    }

    /**
     * Replace the all matching string
     */
    @NotNull
    public static String replaceAll(@NotNull String regex, @Nullable CharSequence charSequence, @NotNull String replacement) {
        return replaceAll(Pattern.compile(regex), charSequence, replacement);
    }

    public static class Group {
        private int start;
        private int end;
        @NotNull
        private String content;

        public Group(int start, int end, @NotNull String content) {
            this.start = start;
            this.end = end;
            this.content = content;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @NotNull
        public String getContent() {
            return content;
        }
    }
}
