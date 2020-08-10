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

@file:Suppress("RemoveRedundantSpreadOperator")

package com.github.panpf.tools4j.lang

import com.github.panpf.tools4j.base64.Base64x
import com.github.panpf.tools4j.common.Pair
import com.github.panpf.tools4j.ranges.Rangex
import com.github.panpf.tools4j.regex.Regexx
import org.junit.Assert.*
import org.junit.Test
import java.nio.charset.Charset
import java.util.*
import java.util.regex.Pattern

class StringxTest {

    companion object {
        private const val BLANK = "     "
        private val BLANK_CHAR_SEQUENCE: CharSequence = "     "
        private const val SPACE = " "
        private const val ENTRY = "\r"
        private const val TAB = "\t"
        private const val WRAP = "\n"
        private const val EMPTY = ""
        private val EMPTY_CHAR_SEQUENCE: CharSequence = ""
        private const val YES = "yes"
        private val YES_CHAR_SEQUENCE: CharSequence = "yes"
        private const val DIGIT = "8"
        private val DIGIT_CHAR_SEQUENCE: CharSequence = "8"
        private const val LETTER = "a飞"
        private val LETTER_CHAR_SEQUENCE: CharSequence = "a飞"
        private const val CHINESE = "飞"
        private val CHINESE_CHAR_SEQUENCE: CharSequence = "飞"
        private const val LETTER_OR_DIGIT = "a飞8"
        private val LETTER_OR_DIGIT_CHAR_SEQUENCE: CharSequence = "a飞8"
        private const val SYMBOL = "*%￥#@"
    }

    @Test
    fun testSafe() {
        assertTrue(Stringx.isSafe(YES))
        assertFalse(Stringx.isSafe(null))
        assertFalse(Stringx.isSafe(BLANK))
        assertFalse(Stringx.isSafe(EMPTY))

        assertTrue(Stringx.isNotSafe(null))
        assertTrue(Stringx.isNotSafe(BLANK))
        assertTrue(Stringx.isNotSafe(EMPTY))
        assertFalse(Stringx.isNotSafe(YES))

        assertEquals(Stringx.safeOr(EMPTY, "default"), "default")
        assertEquals(Stringx.safeOr(EMPTY_CHAR_SEQUENCE, "default"), "default")
        assertEquals(Stringx.safeOr(YES, "default"), YES)
        assertEquals(Stringx.safeOr(YES_CHAR_SEQUENCE, "default"), YES_CHAR_SEQUENCE)

        assertEquals("123" as CharSequence, Stringx.safeOrNull("123" as CharSequence))
        assertNull(Stringx.safeOrNull("" as CharSequence))
        assertNull(Stringx.safeOrNull(null as CharSequence?))
        assertEquals("123", Stringx.safeOrNull("123"))
        assertNull(Stringx.safeOrNull(""))
        assertNull(Stringx.safeOrNull(null as String?))
    }

    @Test
    fun testIsBlank() {
        assertTrue(Stringx.isBlank(BLANK))
        assertTrue(Stringx.isBlank(EMPTY))
        assertTrue(Stringx.isBlank(SPACE))
        assertTrue(Stringx.isBlank(ENTRY))
        assertTrue(Stringx.isBlank(TAB))
        assertTrue(Stringx.isBlank(WRAP))
        assertFalse(Stringx.isBlank(YES))
        assertFalse(Stringx.isBlank(DIGIT))
        assertFalse(Stringx.isBlank(LETTER))
        assertFalse(Stringx.isBlank(CHINESE))
        assertFalse(Stringx.isBlank(LETTER_OR_DIGIT))
        assertFalse(Stringx.isBlank(SYMBOL))
        assertFalse(Stringx.isBlank(null))

        assertTrue(Stringx.isNotBlank(YES))
        assertTrue(Stringx.isNotBlank(DIGIT))
        assertTrue(Stringx.isNotBlank(LETTER))
        assertTrue(Stringx.isNotBlank(CHINESE))
        assertTrue(Stringx.isNotBlank(LETTER_OR_DIGIT))
        assertTrue(Stringx.isNotBlank(SYMBOL))
        assertTrue(Stringx.isNotBlank(null))
        assertFalse(Stringx.isNotBlank(BLANK))
        assertFalse(Stringx.isNotBlank(EMPTY))
        assertFalse(Stringx.isNotBlank(SPACE))
        assertFalse(Stringx.isNotBlank(ENTRY))
        assertFalse(Stringx.isNotBlank(TAB))
        assertFalse(Stringx.isNotBlank(WRAP))

        assertEquals(Stringx.notBlankOr(BLANK, "default"), "default")
        assertEquals(Stringx.notBlankOr(BLANK_CHAR_SEQUENCE, "default"), "default")
        assertEquals(Stringx.notBlankOr(YES, "default"), YES)
        assertEquals(Stringx.notBlankOr(YES_CHAR_SEQUENCE, "default"), YES_CHAR_SEQUENCE)
        assertEquals(Stringx.notBlankOr(null, "default"), null)


        assertTrue(Stringx.isNullOrBlank(null))
        assertTrue(Stringx.isNullOrBlank(BLANK))
        assertTrue(Stringx.isNullOrBlank(EMPTY))
        assertFalse(Stringx.isNullOrBlank(YES))

        assertTrue(Stringx.isNotNullOrBlank(YES))
        assertFalse(Stringx.isNotNullOrBlank(null))
        assertFalse(Stringx.isNotNullOrBlank(BLANK))
        assertFalse(Stringx.isNotNullOrBlank(EMPTY))

        assertEquals(Stringx.notNullOrBlankOr(BLANK, "default"), "default")
        assertEquals(Stringx.notNullOrBlankOr(BLANK_CHAR_SEQUENCE, "default"), "default")
        assertEquals(Stringx.notNullOrBlankOr(null, "default"), "default")
        assertEquals(Stringx.notNullOrBlankOr(YES, "default"), YES)
        assertEquals(Stringx.notNullOrBlankOr(YES_CHAR_SEQUENCE, "default"), YES_CHAR_SEQUENCE)
    }

    @Test
    fun testIsEmpty() {
        assertTrue(Stringx.isEmpty(EMPTY))
        assertFalse(Stringx.isEmpty(BLANK))
        assertFalse(Stringx.isEmpty(YES))
        assertFalse(Stringx.isEmpty(null))

        assertTrue(Stringx.isNotEmpty(BLANK))
        assertTrue(Stringx.isNotEmpty(YES))
        assertFalse(Stringx.isNotEmpty(null))
        assertFalse(Stringx.isNotEmpty(EMPTY))

        assertEquals(Stringx.notEmptyOr(EMPTY, "default"), "default")
        assertEquals(Stringx.notEmptyOr(EMPTY_CHAR_SEQUENCE, "default"), "default")
        assertEquals(Stringx.notEmptyOr(YES, "default"), YES)
        assertEquals(Stringx.notEmptyOr(YES_CHAR_SEQUENCE, "default"), YES_CHAR_SEQUENCE)


        assertTrue(Stringx.isNullOrEmpty(null))
        assertTrue(Stringx.isNullOrEmpty(EMPTY))
        assertFalse(Stringx.isNullOrEmpty(YES))

        assertTrue(Stringx.isNotNullOrEmpty(YES))
        assertFalse(Stringx.isNotNullOrEmpty(null))
        assertFalse(Stringx.isNotNullOrEmpty(EMPTY))

        assertEquals(Stringx.notNullOrEmptyOr(EMPTY, "default"), "default")
        assertEquals(Stringx.notNullOrEmptyOr(EMPTY_CHAR_SEQUENCE, "default"), "default")
        assertEquals(Stringx.notNullOrEmptyOr(null, "default"), "default")
        assertEquals(Stringx.notNullOrEmptyOr(YES, "default"), YES)
        assertEquals(Stringx.notNullOrEmptyOr(YES_CHAR_SEQUENCE, "default"), YES_CHAR_SEQUENCE)
    }

    @Test
    fun testChinese() {
        assertTrue(Stringx.isChinese(CHINESE))
        assertFalse(Stringx.isChinese(EMPTY))
        assertFalse(Stringx.isChinese(DIGIT))
        assertFalse(Stringx.isChinese(LETTER))
        assertFalse(Stringx.isChinese(BLANK))
        assertFalse(Stringx.isChinese(null))

        assertTrue(Stringx.isNotChinese(EMPTY))
        assertTrue(Stringx.isNotChinese(DIGIT))
        assertTrue(Stringx.isNotChinese(LETTER))
        assertTrue(Stringx.isNotChinese(BLANK))
        assertTrue(Stringx.isNotChinese(null))
        assertFalse(Stringx.isNotChinese(CHINESE))

        assertEquals(Stringx.chineseOr(LETTER, "default"), "default")
        assertEquals(Stringx.chineseOr(LETTER_CHAR_SEQUENCE, "default"), "default")
        assertEquals(Stringx.chineseOr(null, "default"), "default")
        assertEquals(Stringx.chineseOr(CHINESE, "default"), CHINESE)
        assertEquals(Stringx.chineseOr(CHINESE_CHAR_SEQUENCE, "default"), CHINESE_CHAR_SEQUENCE)
    }

    @Test
    fun testDigit() {
        assertTrue(Stringx.isDigit(DIGIT))
        assertFalse(Stringx.isDigit(EMPTY))
        assertFalse(Stringx.isDigit(CHINESE))
        assertFalse(Stringx.isDigit(LETTER))
        assertFalse(Stringx.isDigit(BLANK))
        assertFalse(Stringx.isDigit(null))

        assertTrue(Stringx.isNotDigit(EMPTY))
        assertTrue(Stringx.isNotDigit(CHINESE))
        assertTrue(Stringx.isNotDigit(LETTER))
        assertTrue(Stringx.isNotDigit(BLANK))
        assertTrue(Stringx.isNotDigit(null))
        assertFalse(Stringx.isNotDigit(DIGIT))

        assertEquals(Stringx.digitOr(LETTER, "3"), "3")
        assertEquals(Stringx.digitOr(LETTER_CHAR_SEQUENCE, "3"), "3")
        assertEquals(Stringx.digitOr(null, "3"), "3")
        assertEquals(Stringx.digitOr(DIGIT, "3"), DIGIT)
        assertEquals(Stringx.digitOr(DIGIT_CHAR_SEQUENCE, "3"), DIGIT_CHAR_SEQUENCE)
    }

    @Test
    fun testLetter() {
        assertTrue(Stringx.isLetter(LETTER))
        assertFalse(Stringx.isLetter(EMPTY))
        assertFalse(Stringx.isLetter(DIGIT))
        assertFalse(Stringx.isLetter(BLANK))
        assertFalse(Stringx.isLetter(null))

        assertTrue(Stringx.isNotLetter(EMPTY))
        assertTrue(Stringx.isNotLetter(DIGIT))
        assertTrue(Stringx.isNotLetter(BLANK))
        assertTrue(Stringx.isNotLetter(null))
        assertFalse(Stringx.isNotLetter(LETTER))

        assertEquals(Stringx.letterOr(DIGIT, "default"), "default")
        assertEquals(Stringx.letterOr(DIGIT_CHAR_SEQUENCE, "default"), "default")
        assertEquals(Stringx.letterOr(null, "default"), "default")
        assertEquals(Stringx.letterOr(LETTER, "default"), LETTER)
        assertEquals(Stringx.letterOr(LETTER_CHAR_SEQUENCE, "default"), LETTER_CHAR_SEQUENCE)
    }

    @Test
    fun testLetterOrDigit() {
        assertTrue(Stringx.isLetterOrDigit(LETTER_OR_DIGIT))
        assertFalse(Stringx.isLetterOrDigit(EMPTY))
        assertFalse(Stringx.isLetterOrDigit(BLANK))
        assertFalse(Stringx.isLetterOrDigit(SYMBOL))
        assertFalse(Stringx.isLetterOrDigit(null))

        assertTrue(Stringx.isNotLetterOrDigit(EMPTY))
        assertTrue(Stringx.isNotLetterOrDigit(BLANK))
        assertTrue(Stringx.isNotLetterOrDigit(null))
        assertTrue(Stringx.isNotLetterOrDigit(SYMBOL))
        assertFalse(Stringx.isNotLetterOrDigit(LETTER_OR_DIGIT))

        assertEquals(Stringx.letterOrDigitOr(EMPTY, "default"), "default")
        assertEquals(Stringx.letterOrDigitOr(EMPTY_CHAR_SEQUENCE, "default"), "default")
        assertEquals(Stringx.letterOrDigitOr(null, "default"), "default")
        assertEquals(Stringx.letterOrDigitOr(LETTER_OR_DIGIT, "default"), LETTER_OR_DIGIT)
        assertEquals(Stringx.letterOrDigitOr(LETTER_OR_DIGIT_CHAR_SEQUENCE, "default"), LETTER_OR_DIGIT_CHAR_SEQUENCE)
    }

    @Test
    fun testContainsAnyAndAll() {
        assertTrue(Stringx.containsAny("今天天气晴", arrayOf("哈", "天")))
        assertFalse(Stringx.containsAny("今天天气晴", arrayOf("哈")))
        assertTrue(Stringx.containsAny("今天天气晴", mutableListOf("哈", "天")))
        assertFalse(Stringx.containsAny("今天天气晴", mutableListOf("哈")))
        assertFalse(Stringx.containsAny("今天天气晴", null as Array<String>?))
        assertFalse(Stringx.containsAny(null, null as Array<String>?))
        assertFalse(Stringx.containsAny("今天天气晴", null as Collection<String>?))
        assertFalse(Stringx.containsAny(null, null as Collection<String>?))
        assertFalse(Stringx.containsAny("今天天气晴", arrayOfNulls(0)))
        assertFalse(Stringx.containsAny("今天天气晴", LinkedList()))

        assertTrue(Stringx.containsAll("今天天气晴", arrayOf("晴", "天")))
        assertFalse(Stringx.containsAll("今天天气晴", arrayOf("哈", "天")))
        assertTrue(Stringx.containsAll("今天天气晴", mutableListOf("晴", "天")))
        assertFalse(Stringx.containsAll("今天天气晴", mutableListOf("哈", "天")))
        assertFalse(Stringx.containsAll("今天天气晴", null as Array<String>?))
        assertFalse(Stringx.containsAll(null, null as Array<String>?))
        assertFalse(Stringx.containsAll("今天天气晴", null as Collection<String>?))
        assertFalse(Stringx.containsAll(null, null as Collection<String>?))
        assertFalse(Stringx.containsAll("今天天气晴", arrayOfNulls(0)))
        assertFalse(Stringx.containsAll("今天天气晴", LinkedList()))

        assertFalse(Stringx.containsAny("HCHC", arrayOf("h", "a")))
        assertTrue(Stringx.containsAny("HCHC", arrayOf("h", "a"), true))
        assertFalse(Stringx.containsAny("HCHC", mutableListOf("h", "a")))
        assertTrue(Stringx.containsAny("HCHC", mutableListOf("h", "a"), true))

        assertFalse(Stringx.containsAll("HAHA", arrayOf("h", "a")))
        assertTrue(Stringx.containsAll("HAHA", arrayOf("h", "a"), true))
        assertFalse(Stringx.containsAll("HAHA", mutableListOf("h", "a")))
        assertTrue(Stringx.containsAll("HAHA", mutableListOf("h", "a"), true))
    }

    @Test
    fun testOrAndTo() {
        assertEquals(Stringx.orEmpty("今"), "今")
        assertEquals(Stringx.orEmpty(null), "")
        assertEquals(Stringx.orEmpty(StringBuilder("今")).toString(), "今")
        assertEquals(Stringx.orEmpty(null as CharSequence?).toString(), "")
        assertEquals(Stringx.orEmpty("JavaBean"), "JavaBean")
        assertEquals(Stringx.orEmpty(null), EMPTY)

        assertEquals(Stringx.orDefault("今", "天"), "今")
        assertEquals(Stringx.orDefault(null, "天"), "天")
        assertEquals(Stringx.orDefault(StringBuilder("今"), "天").toString(), "今")
        assertEquals(Stringx.orDefault(null as CharSequence?, "天").toString(), "天")
        assertEquals(Stringx.orDefault("JavaBean", "defaultValue"), "JavaBean")
        assertEquals(Stringx.orDefault(null, "defaultValue"), "defaultValue")

        assertNotNull(Stringx.emptyToNull("今"))
        assertNull(Stringx.emptyToNull(""))
        assertNotNull(Stringx.emptyToNull(StringBuilder("今")))
        assertNull(Stringx.emptyToNull(StringBuilder("")))
        assertEquals(Stringx.emptyToNull("JavaBean"), "JavaBean")
        assertNull(Stringx.emptyToNull(EMPTY))

        assertNotNull(Stringx.blankToNull("今"))
        assertNull(Stringx.blankToNull(" "))
        assertNotNull(Stringx.blankToNull(StringBuilder("今")))
        assertNull(Stringx.blankToNull(StringBuilder(" ")))
        assertEquals(Stringx.blankToNull("JavaBean"), "JavaBean")
        assertNull(Stringx.blankToNull("    "))
    }

    @Test
    fun testFilterBlank() {
        val source = " 费劲啊是否将as\n\n\t523\t\tcxbv\r\r而乏味 贵公司   "
        assertEquals(Stringx.filterBlank(source), "费劲啊是否将as523cxbv而乏味贵公司")
        assertEquals(Stringx.filterBlank(StringBuilder(source)).toString(), "费劲啊是否将as523cxbv而乏味贵公司")
    }

    @Test
    fun testRemoveChar() {
        assertEquals("012456789", Stringx.removeChar("0123456789", '3'))
        assertEquals("123456789", Stringx.removeChar("0123456789", '0'))
        assertEquals("012345678", Stringx.removeChar("0123456789", '9'))
        assertEquals("", Stringx.removeChar(null, '9'))

        assertEquals("021456789", Stringx.removeFirstChar("0121456789", '1'))
        assertEquals("", Stringx.removeFirstChar(null, '1'))
        assertEquals("012456789", Stringx.removeLastChar("0121456789", '1'))
        assertEquals("", Stringx.removeLastChar(null, '1'))

        assertEquals("012456789", Stringx.removeIndex("0123456789", 3))
        assertEquals("123456789", Stringx.removeIndex("0123456789", 0))
        assertEquals("012345678", Stringx.removeIndex("0123456789", 9))
        assertEquals("", Stringx.removeIndex(null, 9))
    }

    @Test
    fun testLimit() {
        assertEquals("今天天气晴", Stringx.limit("今天天气晴", 6))
        assertEquals("今天天气晴", Stringx.limit("今天天气晴", 6))
        assertEquals("今天天气晴", Stringx.limit("今天天气晴", 6, "..."))
        assertEquals("今天天气晴朗", Stringx.limit("今天天气晴朗，万里无云", 6))
        assertEquals("今天天气晴朗...", Stringx.limit("今天天气晴朗，万里无云", 6, "..."))

        assertEquals("今天天气晴", Stringx.limit(StringBuilder("今天天气晴"), 6).toString())
        assertEquals("今天天气晴", Stringx.limit(StringBuilder("今天天气晴"), 6).toString())
        assertEquals("今天天气晴", Stringx.limit(StringBuilder("今天天气晴"), 6, "...").toString())
        assertEquals("今天天气晴朗", Stringx.limit(StringBuilder("今天天气晴朗，万里无云"), 6).toString())
        assertEquals("今天天气晴朗...", Stringx.limit(StringBuilder("今天天气晴朗，万里无云"), 6, "...").toString())

        assertEquals("", Stringx.limit(null, 6, "..."))
        try {
            Stringx.limit("今天天气晴", -1, "...")
            fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testReplaceNoRepeat() {
        assertEquals("\\\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\\\"", Stringx.replaceNoRepeat(Stringx.replaceNoRepeat("\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\"", "\"", "\\\""), "\"", "\\\""))
        assertEquals("\\\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\\\"", Stringx.replaceNoRepeat(Stringx.replaceNoRepeat("\\\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\"", "\"", "\\\""), "\"", "\\\""))
        assertEquals("\\\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\\\"", Stringx.replaceNoRepeat(Stringx.replaceNoRepeat("\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\\\"", "\"", "\\\""), "\"", "\\\""))
        assertEquals("loRSr5UDBBtHtGiQfHiCzoK1nl_1", Stringx.replaceNoRepeat(Stringx.replaceNoRepeat("loRSr5UDBBtHtGiQfHiCzoK1nl_1", "\"", "\\\""), "\"", "\\\""))

        assertEquals("爱芬。，，出现在。，，女方家唉都", Stringx.replaceNoRepeat("爱芬，，出现在，，女方家唉都", "，，", "。，，"))
        assertEquals("爱芬。，，出现在。，，女方家唉都", Stringx.replaceNoRepeat(Stringx.replaceNoRepeat("爱芬，，出现在，，女方家唉都", "，，", "。，，"), "，，", "。，，"))
        assertEquals("。，，爱芬出现在。，，女方家唉都", Stringx.replaceNoRepeat(Stringx.replaceNoRepeat("，，爱芬出现在，，女方家唉都", "，，", "。，，"), "，，", "。，，"))
        assertEquals("爱芬。，，出现在女方家唉都。，，", Stringx.replaceNoRepeat(Stringx.replaceNoRepeat("爱芬，，出现在女方家唉都，，", "，，", "。，，"), "，，", "。，，"))
    }

    @Test
    fun testFirstLetterUpperCase() {
        assertEquals("HelloMan", Stringx.firstLetterUpperCase("helloMan"))
        assertEquals("HelloMan", Stringx.firstLetterUpperCase("HelloMan"))
        assertEquals("4124", Stringx.firstLetterUpperCase("4124"))
        assertEquals("你好", Stringx.firstLetterUpperCase("你好"))
        assertEquals("", Stringx.firstLetterUpperCase(""))
        assertEquals("", Stringx.firstLetterUpperCase(null))
        assertEquals(" ", Stringx.firstLetterUpperCase(" "))
    }

    @Test
    fun testFirstLetterLowerCase() {
        assertEquals("helloMan", Stringx.firstLetterLowerCase("HelloMan"))
        assertEquals("helloMan", Stringx.firstLetterLowerCase("HelloMan"))
        assertEquals("4124", Stringx.firstLetterLowerCase("4124"))
        assertEquals("你好", Stringx.firstLetterLowerCase("你好"))
        assertEquals("", Stringx.firstLetterLowerCase(""))
        assertEquals("", Stringx.firstLetterLowerCase(null))
        assertEquals(" ", Stringx.firstLetterLowerCase(" "))
    }

    @org.junit.Test
    fun testStartChars() {
        assertEquals(Stringx.hiddenStartChars("12345", 4), "****5")
        assertEquals(Stringx.hiddenStartChars("123456", 4), "****56")
        assertEquals(Stringx.hiddenStartChars("1234", 4), "****")
        assertEquals(Stringx.hiddenStartChars("123", 4), "***")
        assertEquals(Stringx.hiddenStartChars("13509853689", 4), "****9853689")
        assertEquals(Stringx.hiddenStartChars(null, 4), "")
        assertEquals(Stringx.hiddenStartChars("12345", 4, '$'), "$$$$5")
        assertEquals(Stringx.hiddenStartChars("123456", 4, '$'), "$$$$56")
        assertEquals(Stringx.hiddenStartChars("1234", 4, '$'), "$$$$")
        assertEquals(Stringx.hiddenStartChars("123", 4, '$'), "$$$")
        assertEquals(Stringx.hiddenStartChars("13509853689", 4, '$'), "$$$$9853689")
        assertEquals(Stringx.hiddenStartChars(null, 4, '$'), "")
    }

    @org.junit.Test
    fun testHiddenChars() {
        assertEquals(Stringx.hiddenMiddleChars("12345", 4), "****5")
        assertEquals(Stringx.hiddenMiddleChars("123456", 4), "1****6")
        assertEquals(Stringx.hiddenMiddleChars("1234", 4), "****")
        assertEquals(Stringx.hiddenMiddleChars("123", 4), "***")
        assertEquals(Stringx.hiddenMiddleChars("13509853689", 4), "135****3689")
        assertEquals(Stringx.hiddenMiddleChars(null, 4), "")
        assertEquals(Stringx.hiddenMiddleChars("12345", 4, '$'), "$$$$5")
        assertEquals(Stringx.hiddenMiddleChars("123456", 4, '$'), "1$$$$6")
        assertEquals(Stringx.hiddenMiddleChars("1234", 4, '$'), "$$$$")
        assertEquals(Stringx.hiddenMiddleChars("123", 4, '$'), "$$$")
        assertEquals(Stringx.hiddenMiddleChars("13509853689", 4, '$'), "135$$$$3689")
        assertEquals(Stringx.hiddenMiddleChars(null, 4, '$'), "")
    }

    @org.junit.Test
    fun testEndChars() {
        assertEquals(Stringx.hiddenEndChars("12345", 4), "1****")
        assertEquals(Stringx.hiddenEndChars("123456", 4), "12****")
        assertEquals(Stringx.hiddenEndChars("1234", 4), "****")
        assertEquals(Stringx.hiddenEndChars("123", 4), "***")
        assertEquals(Stringx.hiddenEndChars("13509853689", 4), "1350985****")
        assertEquals(Stringx.hiddenEndChars(null, 4), "")
        assertEquals(Stringx.hiddenEndChars("12345", 4, '$'), "1$$$$")
        assertEquals(Stringx.hiddenEndChars("123456", 4, '$'), "12$$$$")
        assertEquals(Stringx.hiddenEndChars("1234", 4, '$'), "$$$$")
        assertEquals(Stringx.hiddenEndChars("123", 4, '$'), "$$$")
        assertEquals(Stringx.hiddenEndChars("13509853689", 4, '$'), "1350985$$$$")
        assertEquals(Stringx.hiddenEndChars(null, 4, '$'), "")
    }

    @Test
    fun testRequireSafe() {
        Stringx.requireSafe("fas")
        try {
            Stringx.requireSafe("")
            fail()
        } catch (ignored: java.lang.Exception) {
        }
    }

    @Test
    fun testRequireNotSafe() {
        Stringx.requireNotSafe("")
        try {
            Stringx.requireNotSafe("fas")
            fail()
        } catch (ignored: java.lang.Exception) {
        }
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    @Test
    fun testStartsWith() {
        Assertx.assertThreeEquals(true, Stringx.startsWith("JavaBean", 'J'), "JavaBean".startsWith('J'))
        Assertx.assertThreeEquals(false, Stringx.startsWith("javaBean", 'b'), "javaBean".startsWith('b'))
        Assertx.assertThreeEquals(false, Stringx.startsWith("javaBean", 'J'), "javaBean".startsWith('J'))
        Assertx.assertThreeEquals(true, Stringx.startsWith("javaBean", 'J', true), "javaBean".startsWith('J', true))
        Assertx.assertThreeEquals(false, Stringx.startsWith("", 'J', true), "".startsWith('J', true))
        Assertx.assertThreeEquals(false, Stringx.startsWith(null, 'J', true), "".startsWith('J', true))

        Assertx.assertThreeEquals(true, Stringx.startsWith(StringBuilder("JavaBean"), "Java"), StringBuilder("JavaBean").startsWith("Java"))
        Assertx.assertThreeEquals(false, Stringx.startsWith(StringBuilder("JavaBean"), "java"), StringBuilder("JavaBean").startsWith("java"))
        Assertx.assertThreeEquals(true, Stringx.startsWith(StringBuilder("JavaBean"), "java", true), StringBuilder("JavaBean").startsWith("java", true))
        Assertx.assertThreeEquals(false, Stringx.startsWith(null as StringBuilder?, "java"), "".startsWith("java"))
        Assertx.assertThreeEquals(true, Stringx.startsWith("JavaBean" as CharSequence, StringBuilder("Java")), ("JavaBean" as CharSequence).startsWith(StringBuilder("Java")))

        Assertx.assertThreeEquals(true, Stringx.startsWith(StringBuilder("HeaderJavaBean"), "Java", 6), StringBuilder("HeaderJavaBean").startsWith("Java", 6))
        Assertx.assertThreeEquals(false, Stringx.startsWith(StringBuilder("HeaderJavaBean"), "java", 6), StringBuilder("HeaderJavaBean").startsWith("java", 6))
        Assertx.assertThreeEquals(true, Stringx.startsWith(StringBuilder("HeaderJavaBean"), "java", 6, true), StringBuilder("HeaderJavaBean").startsWith("java", 6, true))
        Assertx.assertThreeEquals(true, Stringx.startsWith("HeaderJavaBean" as CharSequence, "Java" as CharSequence, 6), ("HeaderJavaBean" as CharSequence).startsWith("Java" as CharSequence, 6))
        Assertx.assertThreeEquals(true, Stringx.startsWith("HeaderJavaBean" as CharSequence, StringBuilder("Java"), 6), ("HeaderJavaBean" as CharSequence).startsWith(StringBuilder("Java"), 6))

        Assertx.assertThreeEquals(false, Stringx.startsWith("JavaBean", "Jave"), "JavaBean".startsWith("Jave"))
        Assertx.assertThreeEquals(true, Stringx.startsWith("JavaBean", "Java"), "JavaBean".startsWith("Java"))
        Assertx.assertThreeEquals(false, Stringx.startsWith("JavaBean", "Jave"), "JavaBean".startsWith("Jave"))
        Assertx.assertThreeEquals(false, Stringx.startsWith("JavaBean", "java"), "JavaBean".startsWith("java"))
        Assertx.assertThreeEquals(true, Stringx.startsWith("JavaBean", "java", true), "JavaBean".startsWith("java", true))
        Assertx.assertThreeEquals(false, Stringx.startsWith(null, "java"), "".startsWith("java"))

        Assertx.assertThreeEquals(true, Stringx.startsWith("HeaderJavaBean", "Java", 6), "HeaderJavaBean".startsWith("Java", 6))
        Assertx.assertThreeEquals(false, Stringx.startsWith("HeaderJavaBean", "Jave", 6), "HeaderJavaBean".startsWith("Jave", 6))
        Assertx.assertThreeEquals(false, Stringx.startsWith("HeaderJavaBean", "java", 6), "HeaderJavaBean".startsWith("java", 6))
        Assertx.assertThreeEquals(true, Stringx.startsWith("HeaderJavaBean", "java", 6, true), "HeaderJavaBean".startsWith("java", 6, true))
        Assertx.assertThreeEquals(false, Stringx.startsWith(null, "java", 6), "".startsWith("java", 6))
    }

    @Test
    fun testEndsWith() {
        Assertx.assertThreeEquals(true, Stringx.endsWith(StringBuilder("JavaBean"), 'n'), StringBuilder("JavaBean").endsWith('n'))
        Assertx.assertThreeEquals(false, Stringx.endsWith(StringBuilder("JavaBean"), 'N'), StringBuilder("JavaBean").endsWith('N'))
        Assertx.assertThreeEquals(true, Stringx.endsWith(StringBuilder("JavaBean"), 'N', true), StringBuilder("JavaBean").endsWith('N', true))
        Assertx.assertThreeEquals(false, Stringx.endsWith(StringBuilder(""), 'n'), StringBuilder("").endsWith('n'))
        Assertx.assertThreeEquals(false, Stringx.endsWith(null, 'n'), StringBuilder("").endsWith('n'))

        Assertx.assertThreeEquals(true, Stringx.endsWith(StringBuilder("JavaBean"), "Bean"), StringBuilder("JavaBean").endsWith("Bean"))
        Assertx.assertThreeEquals(false, Stringx.endsWith(StringBuilder("JavaBean"), "bean"), StringBuilder("JavaBean").endsWith("bean"))
        Assertx.assertThreeEquals(true, Stringx.endsWith(StringBuilder("JavaBean"), "bean", true), StringBuilder("JavaBean").endsWith("bean", true))
        Assertx.assertThreeEquals(true, Stringx.endsWith("JavaBean" as CharSequence, "Bean" as CharSequence), ("JavaBean" as CharSequence).endsWith("Bean" as CharSequence))
        Assertx.assertThreeEquals(true, Stringx.endsWith("JavaBean" as CharSequence, StringBuilder("Bean")), ("JavaBean" as CharSequence).endsWith(StringBuilder("Bean")))
        Assertx.assertThreeEquals(false, Stringx.endsWith(null as CharSequence?, "bean"), "".endsWith("bean"))

        Assertx.assertThreeEquals(true, Stringx.endsWith("JavaBean", "Bean"), "JavaBean".endsWith("Bean"))
        Assertx.assertThreeEquals(false, Stringx.endsWith("JavaBean", "BEAN"), "JavaBean".endsWith("BEAN"))
        Assertx.assertThreeEquals(true, Stringx.endsWith("JavaBean", "BEAN", true), "JavaBean".endsWith("BEAN", true))
        Assertx.assertThreeEquals(false, Stringx.endsWith("JavaBean", "Bea"), "JavaBean".endsWith("Bea"))
        Assertx.assertThreeEquals(false, Stringx.endsWith("", "Bea"), "".endsWith("Bea"))
        Assertx.assertThreeEquals(false, Stringx.endsWith(null, "Bea"), "".endsWith("Bea"))
    }

    @Test
    fun testEquals() {
        assertTrue(Stringx.equals("JavaBean", "JavaBean"))
        assertTrue(Stringx.equals("JavaBean", "JavaBean"))
        assertFalse(Stringx.equals("JavaBean", "javabean"))
        assertTrue(Stringx.equals("JavaBean", "javabean", true))

        assertFalse(Stringx.equals("JavaBean", null))
        assertFalse(Stringx.equals(null, "javabean"))
        assertTrue(Stringx.equals(null, null))
    }

    @Test
    fun testRemoveRange() {
        Assertx.assertThreeEquals("0126789", Stringx.removeRange("0123456789", 3, 6), "0123456789".removeRange(3, 6))
        Assertx.assertThreeEquals("0126789", Stringx.removeRange("0123456789", Rangex.rangeTo(3, 5)), "0123456789".removeRange(3..5))
        assertEquals("0123456789", Stringx.removeRange("0123456789", Rangex.rangeTo(3, 2)))
        assertEquals("", Stringx.removeRange(null, Rangex.rangeTo(3, 5)))
        try {
            Stringx.removeRange("0123456789", Rangex.rangeTo(3, 1))
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals("3456789", Stringx.removePrefix(StringBuilder("0123456789"), "012"), StringBuilder("0123456789").removePrefix("012"))
        Assertx.assertThreeEquals("0123456789", Stringx.removePrefix(StringBuilder("0123456789"), "210"), StringBuilder("0123456789").removePrefix("210"))

        Assertx.assertThreeEquals("3456789", Stringx.removePrefix("0123456789", "012"), "0123456789".removePrefix("012"))
        Assertx.assertThreeEquals("0123456789", Stringx.removePrefix("0123456789", "210"), "0123456789".removePrefix("210"))

        Assertx.assertThreeEquals("0123456", Stringx.removeSuffix(StringBuilder("0123456789"), "789"), StringBuilder("0123456789").removeSuffix("789"))
        Assertx.assertThreeEquals("0123456789", Stringx.removeSuffix(StringBuilder("0123456789"), "987"), StringBuilder("0123456789").removeSuffix("987"))

        Assertx.assertThreeEquals("0123456", Stringx.removeSuffix("0123456789", "789"), "0123456789".removeSuffix("789"))
        Assertx.assertThreeEquals("0123456789", Stringx.removeSuffix("0123456789", "987"), "0123456789".removeSuffix("987"))

        Assertx.assertThreeEquals("3456", Stringx.removeSurrounding(StringBuilder("0123456789"), "012", "789"), StringBuilder("0123456789").removeSurrounding("012", "789"))
        Assertx.assertThreeEquals("145", Stringx.removeSurrounding(StringBuilder("145"), "012", "789"), StringBuilder("145").removeSurrounding("012", "789"))
        Assertx.assertThreeEquals("013456789", Stringx.removeSurrounding(StringBuilder("013456789"), "012", "789"), StringBuilder("013456789").removeSurrounding("012", "789"))
        Assertx.assertThreeEquals("012345678", Stringx.removeSurrounding(StringBuilder("012345678"), "012", "789"), StringBuilder("012345678").removeSurrounding("012", "789"))
        Assertx.assertThreeEquals("0123456789", Stringx.removeSurrounding(StringBuilder("0123456789"), "210", "987"), StringBuilder("0123456789").removeSurrounding("210", "987"))

        Assertx.assertThreeEquals("3456", Stringx.removeSurrounding("0123456789", "012", "789"), "0123456789".removeSurrounding("012", "789"))
        Assertx.assertThreeEquals("145", Stringx.removeSurrounding("145", "012", "789"), "145".removeSurrounding("012", "789"))
        Assertx.assertThreeEquals("013456789", Stringx.removeSurrounding("013456789", "012", "789"), "013456789".removeSurrounding("012", "789"))
        Assertx.assertThreeEquals("012345678", Stringx.removeSurrounding("012345678", "012", "789"), "012345678".removeSurrounding("012", "789"))
        Assertx.assertThreeEquals("0123456789", Stringx.removeSurrounding("0123456789", "210", "987"), "0123456789".removeSurrounding("210", "987"))

        Assertx.assertThreeEquals("456", Stringx.removeSurrounding(StringBuilder(".456."), "."), StringBuilder(".456.").removeSurrounding("."))
        Assertx.assertThreeEquals("123.456.789", Stringx.removeSurrounding(StringBuilder("123.456.789"), "."), StringBuilder("123.456.789").removeSurrounding("."))

        Assertx.assertThreeEquals("456", Stringx.removeSurrounding(".456.", "."), ".456.".removeSurrounding("."))
        Assertx.assertThreeEquals("123.456.789", Stringx.removeSurrounding("123.456.789", "."), "123.456.789".removeSurrounding("."))
    }

    @Test
    fun testCapitalize() {
        Assertx.assertThreeEquals("Android", Stringx.capitalize("android"), "android".capitalize())
        Assertx.assertThreeEquals("Android", Stringx.capitalize("Android"), "Android".capitalize())
        Assertx.assertThreeEquals("", Stringx.capitalize(""), "".capitalize())
        Assertx.assertThreeEquals("", Stringx.capitalize(null), "".capitalize())

        Assertx.assertThreeEquals("android", Stringx.decapitalize("Android"), "Android".decapitalize())
        Assertx.assertThreeEquals("android", Stringx.decapitalize("android"), "android".decapitalize())
        Assertx.assertThreeEquals("", Stringx.decapitalize(""), "".decapitalize())
        Assertx.assertThreeEquals("", Stringx.decapitalize(null), "".decapitalize())
    }

    @Test
    fun testPad() {
        Assertx.assertThreeEquals("今天天气晴", Stringx.padStart("今天天气晴", 4), "今天天气晴".padStart(4))
        Assertx.assertThreeEquals("今天天气晴", Stringx.padStart("今天天气晴", 5), "今天天气晴".padStart(5))
        Assertx.assertThreeEquals("     今天天气晴", Stringx.padStart("今天天气晴", 10), "     今天天气晴".padStart(10))
        Assertx.assertThreeEquals(".....今天天气晴", Stringx.padStart("今天天气晴", 10, '.'), ".....今天天气晴".padStart(10, '.'))
        Assertx.assertThreeEquals("     今天天气晴", Stringx.padStart(StringBuilder("今天天气晴"), 10).toString(), StringBuilder("今天天气晴").padStart(10).toString())
        Assertx.assertThreeEquals(".....今天天气晴", Stringx.padStart(StringBuilder("今天天气晴"), 10, '.').toString(), StringBuilder(".....今天天气晴").padStart(10, '.').toString())
        try {
            Stringx.padStart("今天天气晴", -1)
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals("今天天气晴", Stringx.padEnd("今天天气晴", 4), "今天天气晴".padEnd(4))
        Assertx.assertThreeEquals("今天天气晴", Stringx.padEnd("今天天气晴", 5), "今天天气晴".padEnd(5))
        Assertx.assertThreeEquals("今天天气晴     ", Stringx.padEnd("今天天气晴", 10), "今天天气晴     ".padEnd(10))
        Assertx.assertThreeEquals("今天天气晴.....", Stringx.padEnd("今天天气晴", 10, '.'), "今天天气晴.....".padEnd(10, '.'))
        Assertx.assertThreeEquals("今天天气晴     ", Stringx.padEnd(StringBuilder("今天天气晴"), 10).toString(), StringBuilder("今天天气晴").padEnd(10).toString())
        Assertx.assertThreeEquals("今天天气晴.....", Stringx.padEnd(StringBuilder("今天天气晴"), 10, '.').toString(), StringBuilder("今天天气晴").padEnd(10, '.').toString())
        Assertx.assertThreeEquals("    ", Stringx.padEnd(null, 4), "".padEnd(4))
        try {
            Stringx.padEnd("今天天气晴", -1)
            fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testMatches() {
        Assertx.assertThreeEquals(true, Stringx.matches("hello@gmai.com", Regexx.EMAIL), "hello@gmai.com".matches(Regex(Regexx.EMAIL.pattern())))
        Assertx.assertThreeEquals(false, Stringx.matches("hello@gmai", Regexx.EMAIL), "hello@gmai".matches(Regex(Regexx.EMAIL.pattern())))
        Assertx.assertThreeEquals(false, Stringx.matches("", Regexx.EMAIL), "".matches(Regex(Regexx.EMAIL.pattern())))
        Assertx.assertThreeEquals(false, Stringx.matches(null, Regexx.EMAIL), "".matches(Regex(Regexx.EMAIL.pattern())))

        Assertx.assertThreeEquals(true, Stringx.regionMatches("onlyOne", 0, "onlyYou", 0, 4), "onlyOne".regionMatches(0, "onlyYou", 0, 4))
        Assertx.assertThreeEquals(false, Stringx.regionMatches("onlyOne", 0, "OnlyYou", 0, 4), "onlyOne".regionMatches(0, "OnlyYou", 0, 4))
        Assertx.assertThreeEquals(false, Stringx.regionMatches(null, 0, "onlyYou", 0, 4), "".regionMatches(0, "onlyYou", 0, 4))
        Assertx.assertThreeEquals(false, Stringx.regionMatches("onlyOne", 0, null, 0, 4), "onlyOne".regionMatches(0, "", 0, 4))

        Assertx.assertThreeEquals(true, Stringx.regionMatchesImpl("onlyOne", 0, "onlyYou", 0, 4), "onlyOne".regionMatches(0, "onlyYou", 0, 4))
        Assertx.assertThreeEquals(false, Stringx.regionMatchesImpl("onlyOne", 0, "OnlyYou", 0, 4), "onlyOne".regionMatches(0, "OnlyYou", 0, 4))
        Assertx.assertThreeEquals(false, Stringx.regionMatchesImpl(null, 0, "onlyYou", 0, 4), "".regionMatches(0, "onlyYou", 0, 4))
        Assertx.assertThreeEquals(false, Stringx.regionMatchesImpl("onlyOne", 0, null, 0, 4), "onlyOne".regionMatches(0, "", 0, 4))
        Assertx.assertThreeEquals(false, Stringx.regionMatchesImpl("onlyOne", -1, "onlyYou", 0, 4), "onlyOne".regionMatches(-1, "onlyYou", 0, 4))
        Assertx.assertThreeEquals(false, Stringx.regionMatchesImpl("onlyOne", 0, "onlyYou", -1, 4), "onlyOne".regionMatches(0, "onlyYou", -1, 4))
    }

    @Test
    fun testFind() {
        Assertx.assertThreeEquals('3'.toString(), Stringx.find(StringBuilder("0123456789")) { it == '3' }.toString(), StringBuilder("0123456789").find { it == '3' }.toString())
        Assertx.assertThreeEquals("null", Stringx.find(StringBuilder("0123456789")) { it == 'a' }.toString(), StringBuilder("0123456789").find { it == 'a' }.toString())

        Assertx.assertThreeEquals('3'.toString(), Stringx.findLast(StringBuilder("0123456789")) { it == '3' }.toString(), StringBuilder("0123456789").findLast { it == '3' }.toString())
        Assertx.assertThreeEquals("null", Stringx.findLast(StringBuilder("0123456789")) { it == 'a' }.toString(), StringBuilder("0123456789").findLast { it == 'a' }.toString())

        Assertx.assertThreeEquals(Pair(3, "3").toString(), Stringx.findAnyOf("0123456789", listOf("3", "8"), 0, false).toString(), "0123456789".findAnyOf(listOf("3", "8"), 0, false).toString())
        Assertx.assertThreeEquals(Pair(8, "8").toString(), Stringx.findAnyOf("0123456789", listOf("a", "8"), 0, false).toString(), "0123456789".findAnyOf(listOf("a", "8"), 0, false).toString())
        Assertx.assertThreeEquals(Pair(8, "8").toString(), Stringx.findAnyOf("0123456789", listOf("a", "8"), 0).toString(), "0123456789".findAnyOf(listOf("a", "8"), 0).toString())
        Assertx.assertThreeEquals("null", Stringx.findAnyOf("abcdefg", listOf("F", "8")).toString(), "abcdefg".findAnyOf(listOf("F", "8")).toString())
        Assertx.assertThreeEquals(Pair(5, "F").toString(), Stringx.findAnyOf("abcdefg", listOf("F", "8"), true).toString(), "abcdefg".findAnyOf(listOf("F", "8"), ignoreCase = true).toString())
        assertNull(Stringx.findAnyOf(null, listOf("3", "8"), 0, false))
        Assertx.assertThreeEquals(Pair(3, "3").toString(), Stringx.findAnyOf(StringBuilder("0123456789"), listOf("3", "8"), 0, false).toString(), StringBuilder("0123456789").findAnyOf(listOf("3", "8"), 0, false).toString())
        Assertx.assertThreeEquals(Pair(3, "3").toString(), Stringx.findAnyOf(StringBuilder("0123456789"), listOf("3"), 0, false).toString(), StringBuilder("0123456789").findAnyOf(listOf("3"), 0, false).toString())
        Assertx.assertThreeEquals(null, Stringx.findAnyOf(StringBuilder("0123456789"), listOf("a"), 0, false), StringBuilder("0123456789").findAnyOf(listOf("a"), 0, false))

        Assertx.assertThreeEquals(Pair(6, "d").toString(), Stringx.findLastAnyOf("android", listOf("d", "a"), Stringx.count("android") - 1, false).toString(), "android".findLastAnyOf(listOf("d", "a"), "android".lastIndex, false).toString())
        Assertx.assertThreeEquals(Pair(6, "d").toString(), Stringx.findLastAnyOf("android", listOf("a", "d"), Stringx.count("android") - 1, false).toString(), "android".findLastAnyOf(listOf("a", "d"), "android".lastIndex, false).toString())
        Assertx.assertThreeEquals(Pair(6, "d").toString(), Stringx.findLastAnyOf("android", listOf("d", "a"), Stringx.count("android") - 1).toString(), "android".findLastAnyOf(listOf("d", "a"), "android".lastIndex).toString())
        Assertx.assertThreeEquals(null.toString(), Stringx.findLastAnyOf("android", listOf("D", "A")).toString(), "android".findLastAnyOf(listOf("D", "A")).toString())
        Assertx.assertThreeEquals(Pair(6, "D").toString(), Stringx.findLastAnyOf("android", listOf("D", "A"), true).toString(), "android".findLastAnyOf(listOf("D", "A"), ignoreCase = true).toString())
        Assertx.assertThreeEquals(Pair(6, "d").toString(), Stringx.findLastAnyOf("android", listOf("d")).toString(), "android".findLastAnyOf(listOf("d")).toString())
        Assertx.assertThreeEquals(null, Stringx.findLastAnyOf("android", listOf("6")), "android".findLastAnyOf(listOf("6")))
        Assertx.assertThreeEquals(null, Stringx.findLastAnyOf(StringBuilder(""), listOf("4", "6")), StringBuilder("").findLastAnyOf(listOf("4", "6")))
    }

    @Test
    fun testFirst() {
        Assertx.assertThreeEquals('0', Stringx.first("0123456789"), "0123456789".first())
        try {
            Stringx.first("")
            fail()
        } catch (e: Exception) {
        }
        try {
            "".first(); fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.first(null)
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals('3', Stringx.first("0123456789") { it == '3' }, "0123456789".first { it == '3' })
        try {
            Stringx.first("") { it == '3' }
            fail()
        } catch (e: Exception) {
        }
        try {
            "".first { it == '3' }
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.first("") { it == 'a' }
            fail()
        } catch (e: Exception) {
        }
        try {
            "".first { it == 'a' }
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.first(null) { it == '3' }
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals('0', Stringx.firstOrNull("0123456789"), "0123456789".firstOrNull())
        Assertx.assertThreeEquals(null, Stringx.firstOrNull(""), "".firstOrNull())
        assertNull(Stringx.firstOrNull(null))

        Assertx.assertThreeEquals('3', Stringx.firstOrNull("0123456789") { it == '3' }, "0123456789".firstOrNull { it == '3' })
        Assertx.assertThreeEquals(null, Stringx.firstOrNull("") { it == '3' }, "".firstOrNull { it == '3' })
        Assertx.assertThreeEquals(null, Stringx.firstOrNull("") { it == 'a' }, "".firstOrNull { it == 'a' })
        assertNull(Stringx.firstOrNull(null) { it == '3' })
    }

    @Test
    fun testLast() {
        Assertx.assertThreeEquals('9', Stringx.last("0123456789"), "0123456789".last())
        try {
            Stringx.last("")
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.last(null)
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals('3', Stringx.last("0123456789") { it == '3' }, "0123456789".last { it == '3' })
        try {
            Stringx.last("") { it == '3' }
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.last("") { it == 'a' }
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.last(null) { it == '3' }
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals('9', Stringx.lastOrNull("0123456789"), "0123456789".lastOrNull())
        Assertx.assertThreeEquals(null, Stringx.lastOrNull(""), "".lastOrNull())
        assertNull(Stringx.lastOrNull(null))

        Assertx.assertThreeEquals('3', Stringx.lastOrNull("0123456789") { it == '3' }, "0123456789".lastOrNull { it == '3' })
        Assertx.assertThreeEquals(null, Stringx.lastOrNull("") { it == '3' }, "".lastOrNull { it == '3' })
        Assertx.assertThreeEquals(null, Stringx.lastOrNull("") { it == 'a' }, "".lastOrNull { it == 'a' })
        assertNull(Stringx.lastOrNull(null) { it == '3' })
    }

    @Test
    fun testGet() {
        Assertx.assertThreeEquals('2', Stringx.getOrElse("0123456789", 2) { 'a' }, "0123456789".getOrElse(2) { 'a' })
        Assertx.assertThreeEquals('a', Stringx.getOrElse("0123456789", 10) { 'a' }, "0123456789".getOrElse(10) { 'a' })
        Assertx.assertThreeEquals('a', Stringx.getOrElse("0123456789", -1) { 'a' }, "0123456789".getOrElse(-1) { 'a' })
        Assertx.assertThreeEquals('a', Stringx.getOrElse("", 2) { 'a' }, "".getOrElse(2) { 'a' })
        Assertx.assertThreeEquals('a', Stringx.getOrElse(null, 2) { 'a' }, "".getOrElse(2) { 'a' })

        Assertx.assertThreeEquals('2', Stringx.getOrNull("0123456789", 2), "0123456789".getOrNull(2))
        Assertx.assertThreeEquals(null, Stringx.getOrNull("0123456789", 10), "0123456789".getOrNull(10))
        Assertx.assertThreeEquals(null, Stringx.getOrNull("0123456789", -1), "0123456789".getOrNull(-1))
        Assertx.assertThreeEquals(null, Stringx.getOrNull("", 2), "".getOrNull(2))
        Assertx.assertThreeEquals(null, Stringx.getOrNull(null, 2), "".getOrNull(2))
    }

    @Test
    fun testIndexOf() {
        val sourceText = "0123456789abcdefgfedcba987654321"
        val sourceTextCount = Stringx.count(sourceText)
        val sourceText2 = "0123456789abcdefg0123456789abcdefg"
        val sourceText2Count = Stringx.count(sourceText2)

        Assertx.assertThreeEquals(10, Stringx.indexOf(sourceText, 'a', 0, false), sourceText.indexOf('a', 0, false))
        Assertx.assertThreeEquals(22, Stringx.indexOf(sourceText, 'a', 14, false), sourceText.indexOf('a', 14, false))
        Assertx.assertThreeEquals(-1, Stringx.indexOf(sourceText, 'A', 0, false), sourceText.indexOf('A', 0, false))
        Assertx.assertThreeEquals(10, Stringx.indexOf(sourceText, 'A', 0, true), sourceText.indexOf('A', 0, true))
        Assertx.assertThreeEquals(-1, Stringx.indexOf(sourceText, 'A', false), sourceText.indexOf('A', ignoreCase = false))
        Assertx.assertThreeEquals(10, Stringx.indexOf(sourceText, 'A', true), sourceText.indexOf('A', ignoreCase = true))
        Assertx.assertThreeEquals(10, Stringx.indexOf(sourceText, 'a', 0), sourceText.indexOf('a', 0))
        Assertx.assertThreeEquals(22, Stringx.indexOf(sourceText, 'a', 14), sourceText.indexOf('a', 14))
        Assertx.assertThreeEquals(10, Stringx.indexOf(sourceText, 'a'), sourceText.indexOf('a'))
        Assertx.assertThreeEquals(-1, Stringx.indexOf(sourceText, 'A'), sourceText.indexOf('A'))
        Assertx.assertThreeEquals(-1, Stringx.indexOf(StringBuilder(sourceText), 'A'), StringBuilder(sourceText).indexOf('A'))

        Assertx.assertThreeEquals(11, Stringx.indexOf(sourceText2, "bc", 0, false), sourceText2.indexOf("bc", 0, false))
        Assertx.assertThreeEquals(28, Stringx.indexOf(sourceText2, "bc", 14, false), sourceText2.indexOf("bc", 14, false))
        Assertx.assertThreeEquals(-1, Stringx.indexOf(sourceText2, "BC", 0, false), sourceText2.indexOf("BC", 0, false))
        Assertx.assertThreeEquals(11, Stringx.indexOf(sourceText2, "BC", 0, true), sourceText2.indexOf("BC", 0, true))
        Assertx.assertThreeEquals(11, Stringx.indexOf(StringBuilder(sourceText2), "BC", 0, true), StringBuilder(sourceText2).indexOf("BC", 0, true))
        Assertx.assertThreeEquals(-1, Stringx.indexOf(sourceText2, "BC", false), sourceText2.indexOf("BC", ignoreCase = false))
        Assertx.assertThreeEquals(11, Stringx.indexOf(sourceText2, "BC", true), sourceText2.indexOf("BC", ignoreCase = true))
        Assertx.assertThreeEquals(11, Stringx.indexOf(sourceText2, "bc", 0), sourceText2.indexOf("bc", 0))
        Assertx.assertThreeEquals(28, Stringx.indexOf(sourceText2, "bc", 14), sourceText2.indexOf("bc", 14))
        Assertx.assertThreeEquals(11, Stringx.indexOf(sourceText2, "bc"), sourceText2.indexOf("bc"))
        Assertx.assertThreeEquals(-1, Stringx.indexOf(sourceText2, "BC"), sourceText2.indexOf("BC"))
        Assertx.assertThreeEquals(-1, Stringx.indexOf(StringBuilder(sourceText2), "BC"), StringBuilder(sourceText2).indexOf("BC"))

        Assertx.assertThreeEquals(10, Stringx.indexOfAny(sourceText, charArrayOf('t', 'a'), 0, false), sourceText.indexOfAny(charArrayOf('t', 'a'), 0, false))
        Assertx.assertThreeEquals(22, Stringx.indexOfAny(sourceText, charArrayOf('t', 'a'), 14, false), sourceText.indexOfAny(charArrayOf('t', 'a'), 14, false))
        Assertx.assertThreeEquals(-1, Stringx.indexOfAny(sourceText, charArrayOf('t', 'A'), 0, false), sourceText.indexOfAny(charArrayOf('t', 'A'), 0, false))
        Assertx.assertThreeEquals(10, Stringx.indexOfAny(sourceText, charArrayOf('t', 'A'), 0, true), sourceText.indexOfAny(charArrayOf('t', 'A'), 0, true))
        Assertx.assertThreeEquals(-1, Stringx.indexOfAny(sourceText, charArrayOf('t', 'A'), false), sourceText.indexOfAny(charArrayOf('t', 'A'), ignoreCase = false))
        Assertx.assertThreeEquals(10, Stringx.indexOfAny(sourceText, charArrayOf('t', 'A'), true), sourceText.indexOfAny(charArrayOf('t', 'A'), ignoreCase = true))
        Assertx.assertThreeEquals(10, Stringx.indexOfAny(sourceText, charArrayOf('t', 'a'), 0), sourceText.indexOfAny(charArrayOf('t', 'a'), 0))
        Assertx.assertThreeEquals(22, Stringx.indexOfAny(sourceText, charArrayOf('t', 'a'), 14), sourceText.indexOfAny(charArrayOf('t', 'a'), 14))
        Assertx.assertThreeEquals(10, Stringx.indexOfAny(sourceText, charArrayOf('t', 'a')), sourceText.indexOfAny(charArrayOf('t', 'a')))
        Assertx.assertThreeEquals(-1, Stringx.indexOfAny(sourceText, charArrayOf('t', 'A')), sourceText.indexOfAny(charArrayOf('t', 'A')))
        Assertx.assertThreeEquals(-1, Stringx.indexOfAny(sourceText, charArrayOf('t')), sourceText.indexOfAny(charArrayOf('t')))
        Assertx.assertThreeEquals(-1, Stringx.indexOfAny(null, charArrayOf('t')), "".indexOfAny(charArrayOf('t')))

        Assertx.assertThreeEquals(11, Stringx.indexOfAny(sourceText2, listOf("bg", "bc"), 0, false), sourceText2.indexOfAny(listOf("bg", "bc"), 0, false))
        Assertx.assertThreeEquals(28, Stringx.indexOfAny(sourceText2, listOf("bg", "bc"), 14, false), sourceText2.indexOfAny(listOf("bg", "bc"), 14, false))
        Assertx.assertThreeEquals(-1, Stringx.indexOfAny(sourceText2, listOf("bg", "BC"), 0, false), sourceText2.indexOfAny(listOf("bg", "BC"), 0, false))
        Assertx.assertThreeEquals(11, Stringx.indexOfAny(sourceText2, listOf("bg", "BC"), 0, true), sourceText2.indexOfAny(listOf("bg", "BC"), 0, true))
        Assertx.assertThreeEquals(-1, Stringx.indexOfAny(sourceText2, listOf("bg", "BC"), false), sourceText2.indexOfAny(listOf("bg", "BC"), ignoreCase = false))
        Assertx.assertThreeEquals(11, Stringx.indexOfAny(sourceText2, listOf("bg", "BC"), true), sourceText2.indexOfAny(listOf("bg", "BC"), ignoreCase = true))
        Assertx.assertThreeEquals(11, Stringx.indexOfAny(sourceText2, listOf("bg", "bc"), 0), sourceText2.indexOfAny(listOf("bg", "bc"), 0))
        Assertx.assertThreeEquals(28, Stringx.indexOfAny(sourceText2, listOf("bg", "bc"), 14), sourceText2.indexOfAny(listOf("bg", "bc"), 14))
        Assertx.assertThreeEquals(11, Stringx.indexOfAny(sourceText2, listOf("bg", "bc")), sourceText2.indexOfAny(listOf("bg", "bc")))
        Assertx.assertThreeEquals(-1, Stringx.indexOfAny(sourceText2, listOf("bg", "BC")), sourceText2.indexOfAny(listOf("bg", "BC")))

        Assertx.assertThreeEquals(10, Stringx.indexOfFirst(sourceText) { it == 'a' }, sourceText.indexOfFirst { it == 'a' })
        Assertx.assertThreeEquals(-1, Stringx.indexOfFirst("") { it == 'a' }, "".indexOfFirst { it == 'a' })
        Assertx.assertThreeEquals(-1, Stringx.indexOfFirst(null) { it == 'a' }, "".indexOfFirst { it == 'a' })
        Assertx.assertThreeEquals(22, Stringx.indexOfLast(sourceText) { it == 'a' }, sourceText.indexOfLast { it == 'a' })
        Assertx.assertThreeEquals(-1, Stringx.indexOfLast("") { it == 'a' }, "".indexOfLast { it == 'a' })
        Assertx.assertThreeEquals(-1, Stringx.indexOfLast(null) { it == 'a' }, "".indexOfLast { it == 'a' })

        Assertx.assertThreeEquals(22, Stringx.lastIndexOf(sourceText, 'a', sourceTextCount - 1, false), sourceText.lastIndexOf('a', sourceTextCount - 1, false))
        Assertx.assertThreeEquals(10, Stringx.lastIndexOf(sourceText, 'a', 14, false), sourceText.lastIndexOf('a', 14, false))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOf(sourceText, 'A', sourceTextCount - 1, false), sourceText.lastIndexOf('A', sourceTextCount - 1, false))
        Assertx.assertThreeEquals(22, Stringx.lastIndexOf(sourceText, 'A', sourceTextCount - 1, true), sourceText.lastIndexOf('A', sourceTextCount - 1, true))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOf(sourceText, 'A', false), sourceText.lastIndexOf('A', ignoreCase = false))
        Assertx.assertThreeEquals(22, Stringx.lastIndexOf(sourceText, 'A', true), sourceText.lastIndexOf('A', ignoreCase = true))
        Assertx.assertThreeEquals(22, Stringx.lastIndexOf(sourceText, 'a', sourceTextCount - 1), sourceText.lastIndexOf('a', sourceTextCount - 1))
        Assertx.assertThreeEquals(10, Stringx.lastIndexOf(sourceText, 'a', 14), sourceText.lastIndexOf('a', 14))
        Assertx.assertThreeEquals(22, Stringx.lastIndexOf(sourceText, 'a'), sourceText.lastIndexOf('a'))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOf(sourceText, 'A'), sourceText.lastIndexOf('A'))
        Assertx.assertThreeEquals(22, Stringx.lastIndexOf(StringBuilder(sourceText), 'a'), StringBuilder(sourceText).lastIndexOf('a'))

        Assertx.assertThreeEquals(28, Stringx.lastIndexOf(sourceText2, "bc", sourceText2Count - 1, false), sourceText2.lastIndexOf("bc", sourceText2Count - 1, false))
        Assertx.assertThreeEquals(11, Stringx.lastIndexOf(sourceText2, "bc", 14, false), sourceText2.lastIndexOf("bc", 14, false))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOf(sourceText2, "BC", sourceText2Count - 1, false), sourceText2.lastIndexOf("BC", sourceText2Count - 1, false))
        Assertx.assertThreeEquals(28, Stringx.lastIndexOf(sourceText2, "BC", sourceText2Count - 1, true), sourceText2.lastIndexOf("BC", sourceText2Count - 1, true))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOf(sourceText2, "BC", false), sourceText2.lastIndexOf("BC", ignoreCase = false))
        Assertx.assertThreeEquals(28, Stringx.lastIndexOf(sourceText2, "BC", true), sourceText2.lastIndexOf("BC", ignoreCase = true))
        Assertx.assertThreeEquals(28, Stringx.lastIndexOf(sourceText2, "bc", sourceText2Count - 1), sourceText2.lastIndexOf("bc", sourceText2Count - 1))
        Assertx.assertThreeEquals(11, Stringx.lastIndexOf(sourceText2, "bc", 14), sourceText2.lastIndexOf("bc", 14))
        Assertx.assertThreeEquals(28, Stringx.lastIndexOf(sourceText2, "bc"), sourceText2.lastIndexOf("bc"))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOf(sourceText2, "BC"), sourceText2.lastIndexOf("BC"))
        Assertx.assertThreeEquals(28, Stringx.lastIndexOf(StringBuilder(sourceText2), "bc"), StringBuilder(sourceText2).lastIndexOf("bc"))

        Assertx.assertThreeEquals(22, Stringx.lastIndexOfAny(sourceText, charArrayOf('t', 'a'), sourceTextCount - 1, false), sourceText.lastIndexOfAny(charArrayOf('t', 'a'), sourceTextCount - 1, false))
        Assertx.assertThreeEquals(10, Stringx.lastIndexOfAny(sourceText, charArrayOf('t', 'a'), 14, false), sourceText.lastIndexOfAny(charArrayOf('t', 'a'), 14, false))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOfAny(sourceText, charArrayOf('t', 'A'), sourceTextCount - 1, false), sourceText.lastIndexOfAny(charArrayOf('t', 'A'), sourceTextCount - 1, false))
        Assertx.assertThreeEquals(22, Stringx.lastIndexOfAny(sourceText, charArrayOf('t', 'A'), sourceTextCount - 1, true), sourceText.lastIndexOfAny(charArrayOf('t', 'A'), sourceTextCount - 1, true))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOfAny(sourceText, charArrayOf('t', 'A'), false), sourceText.lastIndexOfAny(charArrayOf('t', 'A'), ignoreCase = false))
        Assertx.assertThreeEquals(22, Stringx.lastIndexOfAny(sourceText, charArrayOf('t', 'A'), true), sourceText.lastIndexOfAny(charArrayOf('t', 'A'), ignoreCase = true))
        Assertx.assertThreeEquals(22, Stringx.lastIndexOfAny(sourceText, charArrayOf('t', 'a'), sourceTextCount - 1), sourceText.lastIndexOfAny(charArrayOf('t', 'a'), sourceTextCount - 1))
        Assertx.assertThreeEquals(10, Stringx.lastIndexOfAny(sourceText, charArrayOf('t', 'a'), 14), sourceText.lastIndexOfAny(charArrayOf('t', 'a'), 14))
        Assertx.assertThreeEquals(22, Stringx.lastIndexOfAny(sourceText, charArrayOf('t', 'a')), sourceText.lastIndexOfAny(charArrayOf('t', 'a')))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOfAny(sourceText, charArrayOf('t', 'A')), sourceText.lastIndexOfAny(charArrayOf('t', 'A')))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOfAny(null, charArrayOf('t', 'A')), "".lastIndexOfAny(charArrayOf('t', 'A')))
        Assertx.assertThreeEquals(22, Stringx.lastIndexOfAny(sourceText, charArrayOf('a')), sourceText.lastIndexOfAny(charArrayOf('a')))
        Assertx.assertThreeEquals(22, Stringx.lastIndexOfAny(StringBuilder(sourceText), charArrayOf('a')), StringBuilder(sourceText).lastIndexOfAny(charArrayOf('a')))

        Assertx.assertThreeEquals(28, Stringx.lastIndexOfAny(sourceText2, listOf("bg", "bc"), sourceText2Count - 1, false), sourceText2.lastIndexOfAny(listOf("bg", "bc"), sourceText2Count - 1, false))
        Assertx.assertThreeEquals(11, Stringx.lastIndexOfAny(sourceText2, listOf("bg", "bc"), 14, false), sourceText2.lastIndexOfAny(listOf("bg", "bc"), 14, false))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOfAny(sourceText2, listOf("bg", "BC"), sourceText2Count - 1, false), sourceText2.lastIndexOfAny(listOf("bg", "BC"), sourceText2Count - 1, false))
        Assertx.assertThreeEquals(28, Stringx.lastIndexOfAny(sourceText2, listOf("bg", "BC"), sourceText2Count - 1, true), sourceText2.lastIndexOfAny(listOf("bg", "BC"), sourceText2Count - 1, true))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOfAny(sourceText2, listOf("bg", "BC"), false), sourceText2.lastIndexOfAny(listOf("bg", "BC"), ignoreCase = false))
        Assertx.assertThreeEquals(28, Stringx.lastIndexOfAny(sourceText2, listOf("bg", "BC"), true), sourceText2.lastIndexOfAny(listOf("bg", "BC"), ignoreCase = true))
        Assertx.assertThreeEquals(28, Stringx.lastIndexOfAny(sourceText2, listOf("bg", "bc"), sourceText2Count - 1), sourceText2.lastIndexOfAny(listOf("bg", "bc"), sourceText2Count - 1))
        Assertx.assertThreeEquals(11, Stringx.lastIndexOfAny(sourceText2, listOf("bg", "bc"), 14), sourceText2.lastIndexOfAny(listOf("bg", "bc"), 14))
        Assertx.assertThreeEquals(28, Stringx.lastIndexOfAny(sourceText2, listOf("bg", "bc")), sourceText2.lastIndexOfAny(listOf("bg", "bc")))
        Assertx.assertThreeEquals(-1, Stringx.lastIndexOfAny(sourceText2, listOf("bg", "BC")), sourceText2.lastIndexOfAny(listOf("bg", "BC")))
    }

    @Test
    fun testSubstring() {
        Assertx.assertThreeEquals("345678", Stringx.subSequence(StringBuilder("0123456789"), Rangex.rangeTo(3, 8)).toString(), StringBuilder("0123456789").subSequence(3..8).toString())

        Assertx.assertThreeEquals("345678", Stringx.substring("0123456789", Rangex.rangeTo(3, 8)), "0123456789".substring(3..8))
        Assertx.assertThreeEquals("345678", Stringx.substring(StringBuilder("0123456789"), 3, 9), StringBuilder("0123456789").substring(3, 9))
        Assertx.assertThreeEquals("345678", Stringx.substring(StringBuilder("0123456789"), Rangex.rangeTo(3, 8)), StringBuilder("0123456789").substring(3..8))

        Assertx.assertThreeEquals("test", Stringx.substringBefore("test.txt.zip", '.', "test.txt.zip"), "test.txt.zip".substringBefore('.', "test.txt.zip"))
        Assertx.assertThreeEquals("test", Stringx.substringBefore("testtxtzip", '.', "test"), "testtxtzip".substringBefore('.', "test"))

        Assertx.assertThreeEquals("test", Stringx.substringBefore("test.txt.zip", ".", "test.txt.zip"), "test.txt.zip".substringBefore('.', "test.txt.zip"))
        Assertx.assertThreeEquals("test", Stringx.substringBefore("testtxtzip", ".", "test"), "testtxtzip".substringBefore('.', "test"))

        Assertx.assertThreeEquals("txt.zip", Stringx.substringAfter("test.txt.zip", '.', "test.txt.zip"), "test.txt.zip".substringAfter('.', "test.txt.zip"))
        Assertx.assertThreeEquals("txt.zip", Stringx.substringAfter("testtxtzip", '.', "txt.zip"), "testtxtzip".substringAfter('.', "txt.zip"))

        Assertx.assertThreeEquals("txt.zip", Stringx.substringAfter("test.txt.zip", ".", "test.txt.zip"), "test.txt.zip".substringAfter('.', "test.txt.zip"))
        Assertx.assertThreeEquals("txt.zip", Stringx.substringAfter("testtxtzip", ".", "txt.zip"), "testtxtzip".substringAfter('.', "txt.zip"))

        Assertx.assertThreeEquals("test.txt", Stringx.substringBeforeLast("test.txt.zip", '.', "test.txt.zip"), "test.txt.zip".substringBeforeLast('.', "test.txt.zip"))
        Assertx.assertThreeEquals("test.txt", Stringx.substringBeforeLast("testtxtzip", '.', "test.txt"), "testtxtzip".substringBeforeLast('.', "test.txt"))

        Assertx.assertThreeEquals("test.txt", Stringx.substringBeforeLast("test.txt.zip", ".", "test.txt.zip"), "test.txt.zip".substringBeforeLast('.', "test.txt.zip"))
        Assertx.assertThreeEquals("test.txt", Stringx.substringBeforeLast("testtxtzip", ".", "test.txt"), "testtxtzip".substringBeforeLast('.', "test.txt"))

        Assertx.assertThreeEquals("zip", Stringx.substringAfterLast("test.txt.zip", '.', "test.txt.zip"), "test.txt.zip".substringAfterLast('.', "test.txt.zip"))
        Assertx.assertThreeEquals("zip", Stringx.substringAfterLast("testtxtzip", '.', "zip"), "testtxtzip".substringAfterLast('.', "zip"))

        Assertx.assertThreeEquals("zip", Stringx.substringAfterLast("test.txt.zip", ".", "test.txt.zip"), "test.txt.zip".substringAfterLast('.', "test.txt.zip"))
        Assertx.assertThreeEquals("zip", Stringx.substringAfterLast("testtxtzip", ".", "zip"), "testtxtzip".substringAfterLast('.', "zip"))
    }

    @Test
    fun testToByteArray() {
        val sourceText = Base64x.encodeToString("abcdefg")
        val charset = Charset.defaultCharset()

        Assertx.assertThreeEquals(sourceText, String(Stringx.toByteArray(sourceText, charset), charset), String(sourceText.toByteArray(charset), charset))
        assertEquals("", String(Stringx.toByteArray(null, charset), charset))

        Assertx.assertThreeEquals(sourceText, String(Stringx.toByteArray(sourceText)), String(sourceText.toByteArray()))
        assertEquals("", String(Stringx.toByteArray(null)))
    }

    @Test
    fun testReversed() {
        val sourceText = "abcdefg"
        val resultText = "gfedcba"

        Assertx.assertThreeEquals(resultText, Stringx.reversed(sourceText), sourceText.reversed())
        Assertx.assertThreeEquals("", Stringx.reversed(null), "".reversed())

        Assertx.assertThreeEquals(StringBuilder(resultText).toString(), Stringx.reversed(StringBuilder(sourceText)).toString(), StringBuilder(sourceText).reversed().toString())
        Assertx.assertThreeEquals(StringBuilder("").toString(), Stringx.reversed(null as StringBuilder?).toString(), StringBuilder("").reversed().toString())
    }

    @Test
    fun testFilter() {
        val sourceText = "abcdefgfedcba"
        val resultText = "abcdegedcba"

        Assertx.assertThreeEquals(StringBuilder(resultText).toString(), Stringx.filterTo(StringBuilder(sourceText), StringBuilder()) { it != 'f' }.toString(),
                StringBuilder(sourceText).filterTo(StringBuilder()) { it != 'f' }.toString())
        Assertx.assertThreeEquals(StringBuilder("").toString(), Stringx.filterTo(null, StringBuilder()) { it != 'f' }.toString(),
                StringBuilder("").filterTo(StringBuilder()) { it != 'f' }.toString())
        Assertx.assertThreeEquals(StringBuilder(resultText).toString(), Stringx.filter(StringBuilder(sourceText)) { it != 'f' }.toString(),
                StringBuilder(sourceText).filter { it != 'f' }.toString())
        Assertx.assertThreeEquals(resultText, Stringx.filter(sourceText) { it != 'f' }, sourceText.filter { it != 'f' })

        val indexed5 = StringBuilder()
        val indexed6 = StringBuilder()
        Assertx.assertThreeEquals(StringBuilder(resultText).toString(), Stringx.filterIndexedTo(StringBuilder(sourceText), StringBuilder()) { index, it -> indexed5.append(index).append(","); it != 'f' }.toString(),
                StringBuilder(sourceText).filterIndexedTo(StringBuilder()) { index, it -> indexed6.append(index).append(","); it != 'f' }.toString()); assertEquals(indexed5.toString(), indexed6.toString())
        Assertx.assertThreeEquals(StringBuilder().toString(), Stringx.filterIndexedTo(null, StringBuilder()) { index, it -> indexed5.append(index).append(","); it != 'f' }.toString(),
                StringBuilder("").filterIndexedTo(StringBuilder()) { index, it -> indexed6.append(index).append(","); it != 'f' }.toString()); assertEquals(indexed5.toString(), indexed6.toString())

        val indexed3 = StringBuilder()
        val indexed4 = StringBuilder()
        Assertx.assertThreeEquals(StringBuilder(resultText).toString(), Stringx.filterIndexed(StringBuilder(sourceText)) { index, it -> indexed3.append(index).append(","); it != 'f' }.toString(),
                StringBuilder(sourceText).filterIndexed { index, it -> indexed4.append(index).append(","); it != 'f' }.toString()); assertEquals(indexed3.toString(), indexed4.toString())

        val indexed1 = StringBuilder()
        val indexed2 = StringBuilder()
        Assertx.assertThreeEquals(resultText, Stringx.filterIndexed(sourceText) { index, it -> indexed1.append(index).append(","); it != 'f' },
                sourceText.filterIndexed { index, it -> indexed2.append(index).append(","); it != 'f' }); assertEquals(indexed1.toString(), indexed2.toString())

        Assertx.assertThreeEquals(StringBuilder(resultText).toString(), Stringx.filterNotTo(StringBuilder(sourceText), StringBuilder()) { it == 'f' }.toString(),
                StringBuilder(sourceText).filterNotTo(StringBuilder()) { it == 'f' }.toString())
        Assertx.assertThreeEquals(StringBuilder().toString(), Stringx.filterNotTo(null, StringBuilder()) { it == 'f' }.toString(),
                StringBuilder("").filterNotTo(StringBuilder()) { it == 'f' }.toString())
        Assertx.assertThreeEquals(StringBuilder(resultText).toString(), Stringx.filterNot(StringBuilder(sourceText)) { it == 'f' }.toString(),
                StringBuilder(sourceText).filterNot { it == 'f' }.toString())
        Assertx.assertThreeEquals(resultText, Stringx.filterNot(sourceText) { it == 'f' }, sourceText.filterNot { it == 'f' })
    }

    @Test
    fun testTrim() {
        val sourceText = " \tabcdefgfedcba \n"
        val resultText = "abcdefgfedcba"
        val sourceText2 = "abcdefgfedcba"
        val resultText2 = "bcdefgfedcb"
        Assertx.assertThreeEquals(resultText2, Stringx.trim(StringBuilder(sourceText2)) { it == 'a' }, StringBuilder(sourceText2).trim { it == 'a' })
        Assertx.assertThreeEquals("", Stringx.trim(StringBuilder("")) { it == 'a' }, StringBuilder("").trim { it == 'a' })
        Assertx.assertThreeEquals(resultText2, Stringx.trim(sourceText2) { it == 'a' }, sourceText2.trim { it == 'a' })
        Assertx.assertThreeEquals(resultText2, Stringx.trim(StringBuilder(sourceText2), 'a'), StringBuilder(sourceText2).trim('a'))
        Assertx.assertThreeEquals(resultText2, Stringx.trim(sourceText2, 'a'), sourceText2.trim('a'))
        Assertx.assertThreeEquals(resultText, Stringx.trim(StringBuilder(sourceText)), StringBuilder(sourceText).trim())
        Assertx.assertThreeEquals(resultText, Stringx.trim(sourceText), sourceText.trim())

        val sourceTextStart = " \tabcdefgfedcba \n"
        val resultTextStart = "abcdefgfedcba \n"
        val sourceTextStart2 = "abcdefgfedcba"
        val resultTextStart2 = "bcdefgfedcba"
        Assertx.assertThreeEquals(resultTextStart2, Stringx.trimStart(StringBuilder(sourceTextStart2)) { it == 'a' }, StringBuilder(sourceTextStart2).trimStart { it == 'a' })
        Assertx.assertThreeEquals("", Stringx.trimStart(StringBuilder("")) { it == 'a' }, StringBuilder("").trimStart { it == 'a' })
        Assertx.assertThreeEquals(resultTextStart2, Stringx.trimStart(sourceTextStart2) { it == 'a' }, sourceTextStart2.trimStart { it == 'a' })
        Assertx.assertThreeEquals(resultTextStart2, Stringx.trimStart(StringBuilder(sourceTextStart2), 'a'), StringBuilder(sourceTextStart2).trimStart('a'))
        Assertx.assertThreeEquals(resultTextStart2, Stringx.trimStart(sourceTextStart2, 'a'), sourceTextStart2.trimStart('a'))
        Assertx.assertThreeEquals(resultTextStart, Stringx.trimStart(StringBuilder(sourceTextStart)), StringBuilder(sourceTextStart).trimStart())
        Assertx.assertThreeEquals(resultTextStart, Stringx.trimStart(sourceTextStart), sourceTextStart.trimStart())
        Assertx.assertThreeEquals(resultText, Stringx.trim(sourceText), sourceText.trim())

        val sourceTextEnd = " \tabcdefgfedcba \n"
        val resultTextEnd = " \tabcdefgfedcba"
        val sourceTextEnd2 = "abcdefgfedcba"
        val resultTextEnd2 = "abcdefgfedcb"
        Assertx.assertThreeEquals(resultTextEnd2, Stringx.trimEnd(StringBuilder(sourceTextEnd2)) { it == 'a' }, StringBuilder(sourceTextEnd2).trimEnd { it == 'a' })
        Assertx.assertThreeEquals("", Stringx.trimEnd(StringBuilder("")) { it == 'a' }, StringBuilder("").trimEnd { it == 'a' })
        Assertx.assertThreeEquals(resultTextEnd2, Stringx.trimEnd(sourceTextEnd2) { it == 'a' }, sourceTextEnd2.trimEnd { it == 'a' })
        Assertx.assertThreeEquals(resultTextEnd2, Stringx.trimEnd(StringBuilder(sourceTextEnd2), 'a'), StringBuilder(sourceTextEnd2).trimEnd('a'))
        Assertx.assertThreeEquals(resultTextEnd2, Stringx.trimEnd(sourceTextEnd2, 'a'), sourceTextEnd2.trimEnd('a'))
        Assertx.assertThreeEquals(resultTextEnd, Stringx.trimEnd(StringBuilder(sourceTextEnd)), StringBuilder(sourceTextEnd).trimEnd())
        Assertx.assertThreeEquals(resultTextEnd, Stringx.trimEnd(sourceTextEnd), sourceTextEnd.trimEnd())
    }

    @Test
    fun testIterator() {
        val sourceText = "0123456789"
        val resultText = "0, 1, 2, 3, 4, 5, 6, 7, 8, 9"
        assertEquals(resultText, Stringx.iterable(sourceText).joinToString())

        val list = LinkedList<Char>()
        for (char in Stringx.iterator(sourceText)) {
            list.add(char)
        }
        assertEquals(resultText, list.joinToString())
    }

    @Test
    fun testReplace() {
        val sourceTextRange = "0123456789"
        val resultTextRange = "01aaaaaa89"
        Assertx.assertThreeEquals(resultTextRange, Stringx.replaceRange(StringBuilder(sourceTextRange), 2, 8, "aaaaaa").toString(), StringBuilder(sourceTextRange).replaceRange(2, 8, "aaaaaa").toString())
        Assertx.assertThreeEquals(resultTextRange, Stringx.replaceRange(sourceTextRange, 2, 8, "aaaaaa"), sourceTextRange.replaceRange(2, 8, "aaaaaa"))
        Assertx.assertThreeEquals(resultTextRange, Stringx.replaceRange(StringBuilder(sourceTextRange), Rangex.rangeTo(2, 7), "aaaaaa").toString(), StringBuilder(sourceTextRange).replaceRange(2..7, "aaaaaa").toString())
        Assertx.assertThreeEquals(resultTextRange, Stringx.replaceRange(sourceTextRange, Rangex.rangeTo(2, 7), "aaaaaa"), sourceTextRange.replaceRange(2..7, "aaaaaa"))
        try {
            Stringx.replaceRange(sourceTextRange, 2, 1, "aaaa")
            fail()
        } catch (e: Exception) {
        }

        val sourceTextBefore = "test.txt"
        val sourceTextBeforeError = "testtxt"
        val resultTextBefore = "simple.txt"
        Assertx.assertThreeEquals(resultTextBefore, Stringx.replaceBefore(sourceTextBefore, '.', "simple", sourceTextBefore), sourceTextBefore.replaceBefore('.', "simple", sourceTextBefore))
        Assertx.assertThreeEquals(resultTextBefore, Stringx.replaceBefore(sourceTextBefore, '.', "simple", null), sourceTextBefore.replaceBefore('.', "simple"))
        Assertx.assertThreeEquals(resultTextBefore, Stringx.replaceBefore(sourceTextBeforeError, '.', "simple", resultTextBefore), sourceTextBeforeError.replaceBefore('.', "simple", resultTextBefore))
        Assertx.assertThreeEquals(resultTextBefore, Stringx.replaceBefore(sourceTextBefore, ".", "simple", sourceTextBefore), sourceTextBefore.replaceBefore(".", "simple", sourceTextBefore))
        Assertx.assertThreeEquals(resultTextBefore, Stringx.replaceBefore(sourceTextBefore, ".", "simple", null), sourceTextBefore.replaceBefore(".", "simple"))
        Assertx.assertThreeEquals(resultTextBefore, Stringx.replaceBefore(sourceTextBeforeError, ".", "simple", resultTextBefore), sourceTextBeforeError.replaceBefore(".", "simple", resultTextBefore))
        Assertx.assertThreeEquals(sourceTextBefore, Stringx.replaceBefore(null, '.', "simple", sourceTextBefore), "".replaceBefore('.', "simple", sourceTextBefore))
        Assertx.assertThreeEquals(sourceTextBefore, Stringx.replaceBefore(null, ".", "simple", sourceTextBefore), "".replaceBefore(".", "simple", sourceTextBefore))

        val sourceTextBeforeLast = "test.txt.zip"
        val sourceTextBeforeLastError = "testtxtzip"
        val resultTextBeforeLast = "simple.txt.zip"
        Assertx.assertThreeEquals(resultTextBeforeLast, Stringx.replaceBeforeLast(sourceTextBeforeLast, '.', "simple.txt", sourceTextBeforeLast), sourceTextBeforeLast.replaceBeforeLast('.', "simple.txt", sourceTextBeforeLast))
        Assertx.assertThreeEquals(resultTextBeforeLast, Stringx.replaceBeforeLast(sourceTextBeforeLast, '.', "simple.txt", null), sourceTextBeforeLast.replaceBeforeLast('.', "simple.txt"))
        Assertx.assertThreeEquals(resultTextBeforeLast, Stringx.replaceBeforeLast(sourceTextBeforeLastError, '.', "simple.txt", resultTextBeforeLast), sourceTextBeforeLastError.replaceBeforeLast('.', "simple.txt", resultTextBeforeLast))
        Assertx.assertThreeEquals(resultTextBeforeLast, Stringx.replaceBeforeLast(sourceTextBeforeLast, ".", "simple.txt", sourceTextBeforeLast), sourceTextBeforeLast.replaceBeforeLast(".", "simple.txt", sourceTextBeforeLast))
        Assertx.assertThreeEquals(resultTextBeforeLast, Stringx.replaceBeforeLast(sourceTextBeforeLast, ".", "simple.txt", null), sourceTextBeforeLast.replaceBeforeLast(".", "simple.txt"))
        Assertx.assertThreeEquals(resultTextBeforeLast, Stringx.replaceBeforeLast(sourceTextBeforeLastError, ".", "simple.txt", resultTextBeforeLast), sourceTextBeforeLastError.replaceBeforeLast(".", "simple.txt", resultTextBeforeLast))
        Assertx.assertThreeEquals(sourceTextBefore, Stringx.replaceBeforeLast(null, '.', "simple.txt", sourceTextBefore), "".replaceBeforeLast('.', "simple.txt", sourceTextBefore))
        Assertx.assertThreeEquals(sourceTextBefore, Stringx.replaceBeforeLast(null, ".", "simple.txt", sourceTextBefore), "".replaceBeforeLast(".", "simple.txt", sourceTextBefore))

        val sourceTextAfter = "test.txt"
        val sourceTextAfterError = "testtxt"
        val resultTextAfter = "test.zip"
        Assertx.assertThreeEquals(resultTextAfter, Stringx.replaceAfter(sourceTextAfter, '.', "zip", sourceTextAfter), sourceTextAfter.replaceAfter('.', "zip", sourceTextAfter))
        Assertx.assertThreeEquals(resultTextAfter, Stringx.replaceAfter(sourceTextAfter, '.', "zip", null), sourceTextAfter.replaceAfter('.', "zip"))
        Assertx.assertThreeEquals(resultTextAfter, Stringx.replaceAfter(sourceTextAfterError, '.', "zip", resultTextAfter), sourceTextAfterError.replaceAfter('.', "zip", resultTextAfter))
        Assertx.assertThreeEquals(resultTextAfter, Stringx.replaceAfter(sourceTextAfter, ".", "zip", sourceTextAfter), sourceTextAfter.replaceAfter(".", "zip", sourceTextAfter))
        Assertx.assertThreeEquals(resultTextAfter, Stringx.replaceAfter(sourceTextAfter, ".", "zip", null), sourceTextAfter.replaceAfter(".", "zip"))
        Assertx.assertThreeEquals(resultTextAfter, Stringx.replaceAfter(sourceTextAfterError, ".", "zip", resultTextAfter), sourceTextAfterError.replaceAfter(".", "zip", resultTextAfter))
        Assertx.assertThreeEquals(sourceTextAfter, Stringx.replaceAfter(null, '.', "zip", sourceTextAfter), "".replaceAfter('.', "zip", sourceTextAfter))
        Assertx.assertThreeEquals(sourceTextAfter, Stringx.replaceAfter(null, ".", "zip", sourceTextAfter), "".replaceAfter(".", "zip", sourceTextAfter))

        val sourceTextAfterLast = "test.txt.zip"
        val sourceTextAfterLastError = "testtxtzip"
        val resultTextAfterLast = "test.txt.rar"
        Assertx.assertThreeEquals(resultTextAfterLast, Stringx.replaceAfterLast(sourceTextAfterLast, '.', "rar", sourceTextAfterLast), sourceTextAfterLast.replaceAfterLast('.', "rar", sourceTextAfterLast))
        Assertx.assertThreeEquals(resultTextAfterLast, Stringx.replaceAfterLast(sourceTextAfterLast, '.', "rar", null), sourceTextAfterLast.replaceAfterLast('.', "rar"))
        Assertx.assertThreeEquals(resultTextAfterLast, Stringx.replaceAfterLast(sourceTextAfterLastError, '.', "rar", resultTextAfterLast), sourceTextAfterLastError.replaceAfterLast('.', "rar", resultTextAfterLast))
        Assertx.assertThreeEquals(resultTextAfterLast, Stringx.replaceAfterLast(sourceTextAfterLast, ".", "rar", sourceTextAfterLast), sourceTextAfterLast.replaceAfterLast(".", "rar", sourceTextAfterLast))
        Assertx.assertThreeEquals(resultTextAfterLast, Stringx.replaceAfterLast(sourceTextAfterLast, ".", "rar", null), sourceTextAfterLast.replaceAfterLast(".", "rar"))
        Assertx.assertThreeEquals(resultTextAfterLast, Stringx.replaceAfterLast(sourceTextAfterLastError, ".", "rar", resultTextAfterLast), sourceTextAfterLastError.replaceAfterLast(".", "rar", resultTextAfterLast))
        Assertx.assertThreeEquals(sourceTextAfterLast, Stringx.replaceAfterLast(null, '.', "rar", sourceTextAfterLast), "".replaceAfterLast('.', "rar", sourceTextAfterLast))
        Assertx.assertThreeEquals(sourceTextAfterLast, Stringx.replaceAfterLast(null, ".", "rar", sourceTextAfterLast), "".replaceAfterLast(".", "rar", sourceTextAfterLast))

        val sourceTextPattern = "fasfjs hello@gmail.com fasf hello@outlook.com"
        val resultTextPattern = "fasfjs http://google.com fasf http://google.com"
        Assertx.assertThreeEquals(resultTextPattern, Stringx.replace(sourceTextPattern, Regexx.EMAIL, "http://google.com"), sourceTextPattern.replace(Regex(Regexx.EMAIL.pattern()), "http://google.com"))
        Assertx.assertThreeEquals("", Stringx.replace(null, Regexx.EMAIL, "http://google.com"), "".replace(Regex(Regexx.EMAIL.pattern()), "http://google.com"))

        val sourceTextFirstPattern = "fasfjs hello@gmail.com fasf hello@outlook.com"
        val resultTextFirstPattern = "fasfjs http://google.com fasf hello@outlook.com"
        Assertx.assertThreeEquals(resultTextFirstPattern, Stringx.replaceFirst(sourceTextFirstPattern, Regexx.EMAIL, "http://google.com"), sourceTextFirstPattern.replaceFirst(Regex(Regexx.EMAIL.pattern()), "http://google.com"))
        Assertx.assertThreeEquals("", Stringx.replaceFirst(null, Regexx.EMAIL, "http://google.com"), "".replaceFirst(Regex(Regexx.EMAIL.pattern()), "http://google.com"))
    }

    @Test
    fun testCommonWith() {
        val self = "startYourPerformance"
        val other = "startyourTrip"
        Assertx.assertThreeEquals("start", Stringx.commonPrefixWith(self, other), self.commonPrefixWith(other))
        Assertx.assertThreeEquals("startYour", Stringx.commonPrefixWith(self, other, true), self.commonPrefixWith(other, true))
        Assertx.assertThreeEquals("startyour", Stringx.commonPrefixWith(other, self, true), other.commonPrefixWith(self, true))
        Assertx.assertThreeEquals("", Stringx.commonPrefixWith(self, "unknown", true), self.commonPrefixWith("unknown", true))
        assertEquals("", Stringx.commonPrefixWith(null, other, true))
        assertEquals("", Stringx.commonPrefixWith(self, null, true))

        val self2 = "GoFromHome"
        val other2 = "LeavefromHome"
        Assertx.assertThreeEquals("romHome", Stringx.commonSuffixWith(self2, other2), self2.commonSuffixWith(other2))
        Assertx.assertThreeEquals("FromHome", Stringx.commonSuffixWith(self2, other2, true), self2.commonSuffixWith(other2, true))
        Assertx.assertThreeEquals("fromHome", Stringx.commonSuffixWith(other2, self2, true), other2.commonSuffixWith(self2, true))
        Assertx.assertThreeEquals("", Stringx.commonSuffixWith(self2, "unknown", true), self2.commonSuffixWith("unknown", true))
        assertEquals("", Stringx.commonSuffixWith(null, other2, true))
        assertEquals("", Stringx.commonSuffixWith(self2, null, true))
    }

    @Test
    fun testContains() {
        val self = "abcdefg"

        Assertx.assertThreeEquals(true, Stringx.contains(self, "cd"), self.contains("cd"))
        Assertx.assertThreeEquals(false, Stringx.contains(self, "cD"), self.contains("cD"))
        Assertx.assertThreeEquals(true, Stringx.contains(self, "cD", true), self.contains("cD", true))

        Assertx.assertThreeEquals(true, Stringx.contains(StringBuilder(self), StringBuilder("cd")), StringBuilder(self).contains("cd"))
        Assertx.assertThreeEquals(false, Stringx.contains(StringBuilder(self), StringBuilder("cD")), StringBuilder(self).contains("cD"))
        Assertx.assertThreeEquals(true, Stringx.contains(StringBuilder(self), StringBuilder("cD"), true), StringBuilder(self).contains("cD", true))

        Assertx.assertThreeEquals(true, Stringx.contains(self, 'd'), self.contains('d'))
        Assertx.assertThreeEquals(false, Stringx.contains(self, 'D'), self.contains('D'))
        Assertx.assertThreeEquals(true, Stringx.contains(self, 'D', true), self.contains('D', true))

        val self2 = "abc hello@gmail.com defg"

        Assertx.assertThreeEquals(true, Stringx.contains(self2, Regexx.EMAIL), self2.contains(Regex(Regexx.EMAIL.pattern())))
        Assertx.assertThreeEquals(false, Stringx.contains(self2, Regexx.IPV4), self2.contains(Regex(Regexx.IPV4.pattern())))
        assertFalse(Stringx.contains(null, Regexx.IPV4))
    }

    @Test
    fun testSplit() {
        Assertx.assertThreeEquals(
                "112:135:678:345",
                Stringx.splitToIterable("112.135.678,345", arrayOf(".", ",")).joinToString(":"),
                "112.135.678,345".splitToSequence(*arrayOf(".", ",")).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678,345",
                Stringx.splitToIterable("112.135.678,345", arrayOf(".", ","), 3).joinToString(":"),
                "112.135.678,345".splitToSequence(*arrayOf(".", ","), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals(
                "112a135a678b345",
                Stringx.splitToIterable("112a135a678b345", arrayOf("A", "B"), 3).toMutableList().joinToString(":"),
                "112a135a678b345".splitToSequence(*arrayOf("A", "B"), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678:345",
                Stringx.splitToIterable("112a135a678a345", arrayOf("A", "B"), true).joinToString(":"),
                "112a135a678a345".splitToSequence(*arrayOf("A", "B"), ignoreCase = true).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678:345",
                Stringx.splitToIterable("112.135.678.345", arrayOf(".")).joinToString(":"),
                "112.135.678.345".splitToSequence(*arrayOf(".")).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678.345",
                Stringx.splitToIterable("112.135.678.345", arrayOf("."), 3).joinToString(":"),
                "112.135.678.345".splitToSequence(*arrayOf("."), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals(
                ":1:1:2:.:1:3:5:.:6:7:8:.:3:4:5:",
                Stringx.splitToIterable("112.135.678.345", arrayOf("")).joinToString(":"),
                "112.135.678.345".splitToSequence(*arrayOf("")).joinToString(":"))
        Assertx.assertThreeEquals(
                "112.135.678.345",
                Stringx.splitToIterable("112.135.678.345", arrayOf(" ")).joinToString(":"),
                "112.135.678.345".splitToSequence(*arrayOf(" ")).joinToString(":"))

        Assertx.assertThreeEquals(
                "112:135:678:345",
                Stringx.split("112.135.678,345", arrayOf(".", ",")).joinToString(":"),
                "112.135.678,345".split(*arrayOf(".", ",")).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678,345",
                Stringx.split("112.135.678,345", arrayOf(".", ","), 3).joinToString(":"),
                "112.135.678,345".split(*arrayOf(".", ","), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals(
                "112a135a678b345",
                Stringx.split("112a135a678b345", arrayOf("A", "B"), 3).joinToString(":"),
                "112a135a678b345".split(*arrayOf("A", "B"), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678:345",
                Stringx.split("112a135a678a345", arrayOf("A", "B"), true).joinToString(":"),
                "112a135a678a345".split(*arrayOf("A", "B"), ignoreCase = true).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678:345",
                Stringx.split("112.135.678.345", arrayOf(".")).joinToString(":"),
                "112.135.678.345".split(*arrayOf(".")).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678.345",
                Stringx.split("112.135.678.345", arrayOf("."), 3).joinToString(":"),
                "112.135.678.345".split(*arrayOf("."), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals(
                ":1:1:2:.:1:3:5:.:6:7:8:.:3:4:5:",
                Stringx.split("112.135.678.345", arrayOf("")).joinToString(":"),
                "112.135.678.345".split(*arrayOf("")).joinToString(":"))
        Assertx.assertThreeEquals(
                "112.135.678.345",
                Stringx.split("112.135.678.345", arrayOf(" ")).joinToString(":"),
                "112.135.678.345".split(*arrayOf(" ")).joinToString(":"))

        Assertx.assertThreeEquals("112:135:678:345",
                Stringx.splitToIterable("112.135.678,345", charArrayOf('.', ',')).joinToString(":"),
                "112.135.678,345".splitToSequence(*charArrayOf('.', ',')).joinToString(":"))
        Assertx.assertThreeEquals("112:135:678,345",
                Stringx.splitToIterable("112.135.678,345", charArrayOf('.', ','), 3).joinToString(":"),
                "112.135.678,345".splitToSequence(*charArrayOf('.', ','), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals("112a135a678b345",
                Stringx.splitToIterable("112a135a678b345", charArrayOf('A', 'B'), 3).joinToString(":"),
                "112a135a678b345".splitToSequence(*charArrayOf('A', 'B'), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals("112:135:678:345",
                Stringx.splitToIterable("112a135a678a345", charArrayOf('A', 'B'), true).joinToString(":"),
                "112a135a678a345".splitToSequence(*charArrayOf('A', 'B'), ignoreCase = true).joinToString(":"))
        Assertx.assertThreeEquals("112:135:678:345",
                Stringx.splitToIterable("112.135.678.345", charArrayOf('.')).joinToString(":"),
                "112.135.678.345".splitToSequence(*charArrayOf('.')).joinToString(":"))
        Assertx.assertThreeEquals("112:135:678.345",
                Stringx.splitToIterable("112.135.678.345", charArrayOf('.'), 3).joinToString(":"),
                "112.135.678.345".splitToSequence(*charArrayOf('.'), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals("112.135.678.345",
                Stringx.splitToIterable("112.135.678.345", charArrayOf(' ')).joinToString(":"),
                "112.135.678.345".splitToSequence(*charArrayOf(' ')).joinToString(":"))

        Assertx.assertThreeEquals(
                "112:135:678:345",
                Stringx.split("112.135.678,345", charArrayOf('.', ',')).joinToString(":"),
                "112.135.678,345".split(*charArrayOf('.', ',')).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678,345",
                Stringx.split("112.135.678,345", charArrayOf('.', ','), 3).joinToString(":"),
                "112.135.678,345".split(*charArrayOf('.', ','), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals(
                "112a135a678b345",
                Stringx.split("112a135a678b345", charArrayOf('A', 'B'), 3).joinToString(":"),
                "112a135a678b345".split(*charArrayOf('A', 'B'), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678:345",
                Stringx.split("112a135a678a345", charArrayOf('A', 'B'), true).joinToString(":"),
                "112a135a678a345".split(*charArrayOf('A', 'B'), ignoreCase = true).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678:345",
                Stringx.split("112.135.678.345", charArrayOf('.')).joinToString(":"),
                "112.135.678.345".split(*charArrayOf('.')).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678.345",
                Stringx.split("112.135.678.345", charArrayOf('.'), 3).joinToString(":"),
                "112.135.678.345".split(*charArrayOf('.'), limit = 3).joinToString(":"))
        Assertx.assertThreeEquals(
                "112.135.678.345",
                Stringx.split("112.135.678.345", charArrayOf(' ')).joinToString(":"),
                "112.135.678.345".split(*charArrayOf(' ')).joinToString(":"))
        Assertx.assertThreeEquals(
                "112.135.678.345",
                Stringx.split("112.135.678.345", charArrayOf('a')).joinToString(":"),
                "112.135.678.345".split(*charArrayOf('a')).joinToString(":"))
        Assertx.assertThreeEquals(
                "112.135.678.345",
                Stringx.split("112.135.678.345", charArrayOf('.'), 1).joinToString(":"),
                "112.135.678.345".split(*charArrayOf('.'), limit = 1).joinToString(":"))

        Assertx.assertThreeEquals(
                "112:135:678:345",
                Stringx.split("112.135.678,345", Pattern.compile("[.,]")).joinToString(":"),
                "112.135.678,345".split(Regex("[.,]")).joinToString(":"))
        Assertx.assertThreeEquals(
                "112:135:678,345",
                Stringx.split("112.135.678,345", Pattern.compile("[.,]"), 3).joinToString(":"),
                "112.135.678,345".split(Regex("[.,]"), 3).joinToString(":"))

        try {
            Stringx.splitToIterable("112.135.678,345", arrayOf(".", ","), -1)
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.splitToIterable("112.135.678,345", charArrayOf('.', ','), -1)
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.split("112.135.678,345", charArrayOf('.'), -1)
            fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testLines() {
        Assertx.assertThreeEquals(
                mutableListOf("1", "2", "3", "4"),
                Stringx.lines("1\n2\n3\n4"),
                "1\n2\n3\n4".lines())
        Assertx.assertThreeEquals(
                mutableListOf("1", "2", "3", "4"),
                Stringx.lineIterable("1\n2\n3\n4").toMutableList(),
                "1\n2\n3\n4".lineSequence().toMutableList())
    }

    @Test
    fun testElementAt() {
        val self = "abcdefg"

        Assertx.assertThreeEquals('e', Stringx.elementAt(self, 4), self.elementAt(4))
        try {
            Stringx.elementAt(self, 9)
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals('e', Stringx.elementAtOrElse(self, 4) { 'g' }, self.elementAtOrElse(4) { 'g' })
        Assertx.assertThreeEquals('g', Stringx.elementAtOrElse(self, 9) { 'g' }, self.elementAtOrElse(9) { 'g' })
        Assertx.assertThreeEquals('g', Stringx.elementAtOrElse("", 9) { 'g' }, "".elementAtOrElse(9) { 'g' })
        Assertx.assertThreeEquals('g', Stringx.elementAtOrElse(null, 9) { 'g' }, "".elementAtOrElse(9) { 'g' })
        Assertx.assertThreeEquals('g', Stringx.elementAtOrElse(self, -1) { 'g' }, self.elementAtOrElse(-1) { 'g' })

        Assertx.assertThreeEquals('e', Stringx.elementAtOrNull(self, 4), self.elementAtOrNull(4))
        Assertx.assertThreeEquals(null, Stringx.elementAtOrNull(self, 9), self.elementAtOrNull(9))
        Assertx.assertThreeEquals(null, Stringx.elementAtOrNull("", 9), "".elementAtOrNull(9))
        Assertx.assertThreeEquals(null, Stringx.elementAtOrNull(null, 9), "".elementAtOrNull(9))
        Assertx.assertThreeEquals(null, Stringx.elementAtOrNull(self, -1), self.elementAtOrNull(-1))
    }

    @Test
    fun testSingle() {
        Assertx.assertThreeEquals('a', Stringx.single("a"), "a".single())
        try {
            Stringx.single("ab")
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.single(null)
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.single("")
            fail()
        } catch (e: Exception) {
        }
        Assertx.assertThreeEquals('a', Stringx.singleOrNull("a"), "a".singleOrNull())
        Assertx.assertThreeEquals(null, Stringx.singleOrNull("ab"), "ab".singleOrNull())
        Assertx.assertThreeEquals(null, Stringx.singleOrNull(""), "".singleOrNull())
        assertNull(Stringx.singleOrNull(null))


        Assertx.assertThreeEquals('b', Stringx.single("ab") { it == 'b' }, "ab".single { it == 'b' })
        try {
            Stringx.single("abcb") { it == 'b' }
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.single("ac") { it == 'b' }
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.single("") { it == 'b' }
            fail()
        } catch (e: Exception) {
        }
        Assertx.assertThreeEquals('a', Stringx.singleOrNull("abcb") { it == 'a' }, "abcb".singleOrNull { it == 'a' })
        Assertx.assertThreeEquals(null, Stringx.singleOrNull("abcb") { it == 'b' }, "abcb".singleOrNull { it == 'b' })
        Assertx.assertThreeEquals(null, Stringx.singleOrNull("ac") { it == 'b' }, "ac".singleOrNull { it == 'b' })
        Assertx.assertThreeEquals(null, Stringx.singleOrNull("") { it == 'b' }, "".singleOrNull { it == 'b' })
    }

    @Test
    fun testDrop() {
        val self = "abcdefg"

        Assertx.assertThreeEquals("defg", Stringx.drop(self, 3), self.drop(3))
        Assertx.assertThreeEquals("", Stringx.drop(self, self.length + 1), self.drop(self.length + 1))
        Assertx.assertThreeEquals(self, Stringx.drop(self, 0), self.drop(0))
        try {
            Stringx.drop(self, -1)
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals("defg", Stringx.drop(StringBuilder(self), 3).toString(), StringBuilder(self).drop(3).toString())
        Assertx.assertThreeEquals("", Stringx.drop(StringBuilder(self), self.length + 1).toString(), StringBuilder(self).drop(self.length + 1).toString())
        Assertx.assertThreeEquals(self, Stringx.drop(StringBuilder(self), 0).toString(), StringBuilder(self).drop(0).toString())
        try {
            Stringx.drop(StringBuilder(self), -1)
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals("abcd", Stringx.dropLast(self, 3), self.dropLast(3))
        Assertx.assertThreeEquals("", Stringx.dropLast(self, self.length + 1), self.dropLast(self.length + 1))
        Assertx.assertThreeEquals(self, Stringx.dropLast(self, 0), self.dropLast(0))
        try {
            Stringx.dropLast(self, -1)
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals("abcd", Stringx.dropLast(StringBuilder(self), 3).toString(), StringBuilder(self).dropLast(3).toString())
        Assertx.assertThreeEquals("", Stringx.dropLast(StringBuilder(self), self.length + 1).toString(), StringBuilder(self).dropLast(self.length + 1).toString())
        Assertx.assertThreeEquals(self, Stringx.dropLast(StringBuilder(self), 0).toString(), StringBuilder(self).dropLast(0).toString())
        try {
            Stringx.dropLast(StringBuilder(self), -1)
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals("abcdef", Stringx.dropLastWhile(StringBuilder(self)) { it != 'f' }.toString(), StringBuilder(self).dropLastWhile { it != 'f' }.toString())
        Assertx.assertThreeEquals("", Stringx.dropLastWhile(StringBuilder("")) { it != 'f' }.toString(), StringBuilder("").dropLastWhile { it != 'f' }.toString())
        Assertx.assertThreeEquals("", Stringx.dropLastWhile(null as StringBuilder?) { it != 'f' }.toString(), StringBuilder("").dropLastWhile { it != 'f' }.toString())

        Assertx.assertThreeEquals("abcdef", Stringx.dropLastWhile(self) { it != 'f' }, self.dropLastWhile { it != 'f' })
        Assertx.assertThreeEquals("", Stringx.dropLastWhile("") { it != 'f' }, "".dropLastWhile { it != 'f' })
        Assertx.assertThreeEquals("", Stringx.dropLastWhile(null as String?) { it != 'f' }, "".dropLastWhile { it != 'f' })

        Assertx.assertThreeEquals("bcdefg", Stringx.dropWhile(StringBuilder(self)) { it != 'b' }.toString(), StringBuilder(self).dropWhile { it != 'b' }.toString())
        Assertx.assertThreeEquals("", Stringx.dropWhile(StringBuilder("")) { it != 'b' }.toString(), StringBuilder("").dropWhile { it != 'b' }.toString())
        Assertx.assertThreeEquals("", Stringx.dropWhile(null as StringBuilder?) { it != 'b' }.toString(), StringBuilder("").dropWhile { it != 'b' }.toString())

        Assertx.assertThreeEquals("bcdefg", Stringx.dropWhile(self) { it != 'b' }, self.dropWhile { it != 'b' })
        Assertx.assertThreeEquals("", Stringx.dropWhile("") { it != 'b' }, "".dropWhile { it != 'b' })
        Assertx.assertThreeEquals("", Stringx.dropWhile(null as String?) { it != 'b' }, "".dropWhile { it != 'b' })
    }

    @Test
    fun testSlice() {
        val source = "0123456789"

        Assertx.assertThreeEquals("34567", Stringx.slice(source, Rangex.rangeTo(3, 7)), source.slice(3..7))
        @Suppress("EmptyRange")
        Assertx.assertThreeEquals("", Stringx.slice(source, Rangex.rangeTo(3, 2)), source.slice(3..2))
        Assertx.assertThreeEquals("34567", Stringx.slice(StringBuilder(source), Rangex.rangeTo(3, 7)).toString(), StringBuilder(source).slice(3..7).toString())
        @Suppress("EmptyRange")
        Assertx.assertThreeEquals("", Stringx.slice(StringBuilder(source), Rangex.rangeTo(3, 2)).toString(), StringBuilder(source).slice(3..2).toString())

        Assertx.assertThreeEquals("158", Stringx.slice(source, mutableListOf(1, 5, 8)), source.slice(listOf(1, 5, 8)))
        Assertx.assertThreeEquals("", Stringx.slice(source, mutableListOf()), source.slice(listOf()))
        Assertx.assertThreeEquals("158", Stringx.slice(StringBuilder(source), mutableListOf(1, 5, 8)).toString(), StringBuilder(source).slice(listOf(1, 5, 8)).toString())
        Assertx.assertThreeEquals("", Stringx.slice(StringBuilder(source), mutableListOf()).toString(), StringBuilder(source).slice(listOf()).toString())
        Assertx.assertThreeEquals("", Stringx.slice(null, mutableListOf()), "".slice(mutableListOf()))
    }

    @Test
    fun testTake() {
        val source = "0123456789"

        Assertx.assertThreeEquals("0123456", Stringx.take(source, 7), source.take(7))
        Assertx.assertThreeEquals(source, Stringx.take(source, 15), source.take(15))
        Assertx.assertThreeEquals("", Stringx.take(source, 0), source.take(0))
        try {
            Stringx.take(source, -1)
            fail()
        } catch (e: Exception) {
        }
        Assertx.assertThreeEquals(StringBuilder("0123456").toString(), Stringx.take(StringBuilder(source), 7).toString(), StringBuilder(source).take(7).toString())
        Assertx.assertThreeEquals(StringBuilder(source).toString(), Stringx.take(StringBuilder(source), 15).toString(), StringBuilder(source).take(15).toString())
        Assertx.assertThreeEquals(StringBuilder("").toString(), Stringx.take(StringBuilder(source), 0).toString(), StringBuilder(source).take(0).toString())
        try {
            Stringx.take(StringBuilder(source), -1)
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals("3456789", Stringx.takeLast(source, 7), source.takeLast(7))
        Assertx.assertThreeEquals(source, Stringx.takeLast(source, 15), source.takeLast(15))
        Assertx.assertThreeEquals("", Stringx.takeLast(source, 0), source.takeLast(0))
        try {
            Stringx.takeLast(source, -1)
            fail()
        } catch (e: Exception) {
        }
        Assertx.assertThreeEquals(StringBuilder("3456789").toString(), Stringx.takeLast(StringBuilder(source), 7).toString(), StringBuilder(source).takeLast(7).toString())
        Assertx.assertThreeEquals(StringBuilder(source).toString(), Stringx.takeLast(StringBuilder(source), 15).toString(), StringBuilder(source).takeLast(15).toString())
        Assertx.assertThreeEquals(StringBuilder("").toString(), Stringx.takeLast(StringBuilder(source), 0).toString(), StringBuilder(source).takeLast(0).toString())
        try {
            Stringx.takeLast(StringBuilder(source), -1)
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals("012345", Stringx.takeWhile(source) { it != '6' }, source.takeWhile { it != '6' })
        Assertx.assertThreeEquals("", Stringx.takeWhile(null) { it != '6' }, "".takeWhile { it != '6' })
        Assertx.assertThreeEquals(source, Stringx.takeWhile(source) { it != 'a' }, source.takeWhile { it != 'a' })

        Assertx.assertThreeEquals(StringBuilder("012345").toString(), Stringx.takeWhile(StringBuilder(source)) { it != '6' }.toString(), StringBuilder(source).takeWhile { it != '6' }.toString())
        Assertx.assertThreeEquals("", Stringx.takeWhile(StringBuilder("")) { it != '6' }.toString(), StringBuilder("").takeWhile { it != '6' }.toString())
        Assertx.assertThreeEquals(StringBuilder("").toString(), Stringx.takeWhile(null as StringBuilder?) { it != '6' }, StringBuilder("").takeWhile { it != '6' })

        Assertx.assertThreeEquals("789", Stringx.takeLastWhile(source) { it != '6' }, source.takeLastWhile { it != '6' })
        Assertx.assertThreeEquals("", Stringx.takeLastWhile(null) { it != '6' }, "".takeLastWhile { it != '6' })
        Assertx.assertThreeEquals(source, Stringx.takeLastWhile(source) { it != 'a' }, source.takeLastWhile { it != 'a' })

        Assertx.assertThreeEquals(StringBuilder("789").toString(), Stringx.takeLastWhile(StringBuilder(source)) { it != '6' }.toString(), StringBuilder(source).takeLastWhile { it != '6' }.toString())
        Assertx.assertThreeEquals("", Stringx.takeLastWhile(StringBuilder("")) { it != '6' }.toString(), StringBuilder("").takeLastWhile { it != '6' }.toString())
        Assertx.assertThreeEquals(StringBuilder("").toString(), Stringx.takeLastWhile(null as StringBuilder?) { it != '6' }, StringBuilder("").takeLastWhile { it != '6' })
    }

    @Test
    fun testAssociate() {
        val source = "0123456789"
        assertEquals(
                source.associate { kotlin.Pair(it.toString(), it.toInt()) }.toList(),
                Stringx.associate(source) { Pair<String, Int>(it.toString(), it.toInt()) }.toList())
        assertEquals(
                source.associateBy { it.toString() }.toList(),
                Stringx.associateBy(source) { it.toString() }.toList())
        assertEquals(
                source.associateBy({ it.toString() }) { it.toInt() }.toList(),
                Stringx.associateBy(source, { it.toString() }) { it.toInt() }.toList())
        assertEquals(
                source.associateTo(LinkedHashMap()) { kotlin.Pair(it.toString(), it.toInt()) }.toList(),
                Stringx.associateTo(source, LinkedHashMap()) { Pair<String, Int>(it.toString(), it.toInt()) }.toList())
        assertEquals(
                source.associateByTo(LinkedHashMap()) { it.toString() }.toList(),
                Stringx.associateByTo(source, LinkedHashMap()) { it.toString() }.toList())
        assertEquals(
                source.associateByTo(LinkedHashMap(), { it.toString() }) { it.toInt() }.toList(),
                Stringx.associateByTo(source, LinkedHashMap(), { it.toString() }) { it.toInt() }.toList())
    }

    @Test
    fun testTo() {
        val source = "0123456789"
        Assertx.assertThreeEquals(source, Stringx.toList(source).joinToString(""), source.toList().joinToString(""))
        Assertx.assertThreeEquals(source, Stringx.toSet(source).joinToString(""), source.toSet().joinToString(""))
        Assertx.assertThreeEquals("", Stringx.toSet("").joinToString(""), "".toSet().joinToString(""))
        assertEquals("", Stringx.toSet(null).joinToString(""))
        assertEquals("0", Stringx.toSet("0").joinToString(""))
        Assertx.assertThreeEquals(source, Stringx.toHashSet(source).joinToString(""), source.toHashSet().joinToString(""))
        Assertx.assertThreeEquals(source, Stringx.toSortedSet(source).joinToString(""), source.toSortedSet().joinToString(""))
        Assertx.assertThreeEquals(source, Stringx.toCollection(source, LinkedList<Char>()).joinToString(""), source.toCollection(LinkedList()).joinToString(""))
    }

    @Test
    fun testFlatMap() {
        val source = "0123456789"
        Assertx.assertThreeEquals(listOf("1", "2", "2", "3", "3", "3", "4", "4", "4", "4", "5", "5", "5", "5", "5",
                "6", "6", "6", "6", "6", "6", "7", "7", "7", "7", "7", "7", "7",
                "8", "8", "8", "8", "8", "8", "8", "8", "9", "9", "9", "9", "9", "9", "9", "9", "9"), Stringx.flatMap(source) {
            LinkedList<String>().apply {
                for (int in 0 until it.toString().toInt()) {
                    add(it.toString())
                }
            }
        }, source.flatMap {
            LinkedList<String>().apply {
                for (int in 0 until it.toString().toInt()) {
                    add(it.toString())
                }
            }
        })

        Assertx.assertThreeEquals(listOf("1", "2", "2", "3", "3", "3", "4", "4", "4", "4", "5", "5", "5", "5", "5",
                "6", "6", "6", "6", "6", "6", "7", "7", "7", "7", "7", "7", "7",
                "8", "8", "8", "8", "8", "8", "8", "8", "9", "9", "9", "9", "9", "9", "9", "9", "9"), Stringx.flatMapTo(source, ArrayList<String>()) {
            LinkedList<String>().apply {
                for (int in 0 until it.toString().toInt()) {
                    add(it.toString())
                }
            }
        }, source.flatMapTo(ArrayList()) {
            LinkedList<String>().apply {
                for (int in 0 until it.toString().toInt()) {
                    add(it.toString())
                }
            }
        })
    }

    @Test
    fun testGroup() {
        val source = "1365268945336807532324589"
        val map = sortedMapOf("0" to listOf('0')
                , "1" to listOf('1')
                , "2" to listOf('2', '2', '2')
                , "3" to listOf('3', '3', '3', '3', '3')
                , "4" to listOf('4', '4')
                , "5" to listOf('5', '5', '5', '5')
                , "6" to listOf('6', '6', '6')
                , "7" to listOf('7')
                , "8" to listOf('8', '8', '8')
                , "9" to listOf('9', '9'))
        val map2 = sortedMapOf("0" to listOf("0")
                , "1" to listOf("1")
                , "2" to listOf("2", "2", "2")
                , "3" to listOf("3", "3", "3", "3", "3")
                , "4" to listOf("4", "4")
                , "5" to listOf("5", "5", "5", "5")
                , "6" to listOf("6", "6", "6")
                , "7" to listOf("7")
                , "8" to listOf("8", "8", "8")
                , "9" to listOf("9", "9"))

        Assertx.assertThreeEquals(map, Stringx.groupBy(source) { it.toString() }.toSortedMap(), source.groupBy { it.toString() }.toSortedMap())
        Assertx.assertThreeEquals(map2, Stringx.groupBy(source, { it.toString() }) { it.toString() }.toSortedMap(), source.groupBy({ it.toString() }) { it.toString() }.toSortedMap())

        Assertx.assertThreeEquals(map, Stringx.groupByTo(source, LinkedHashMap<String, List<Char>>()) { it.toString() }.toSortedMap(),
                source.groupByTo(LinkedHashMap()) { it.toString() }.toSortedMap())
        Assertx.assertThreeEquals(map2, Stringx.groupByTo(source, LinkedHashMap<String, List<String>>(), { it.toString() }) { it.toString() }.toSortedMap(),
                source.groupByTo(LinkedHashMap(), { it.toString() }) { it.toString() }.toSortedMap())
    }

    @Test
    fun testMap() {
        val source = "1365268945336"
        val result = listOf("1", "3", "6", "5", "2", "6", "8", "9", "4", "5", "3", "3", "6")

        Assertx.assertThreeEquals(result, Stringx.map(source) { it.toString() }, source.map { it.toString() })
        Assertx.assertThreeEquals(result, Stringx.mapTo(source, ArrayList(source.length)) { it.toString() },
                source.mapTo(ArrayList(source.length)) { it.toString() })

        val indexPair1 = Pair(StringBuilder(), StringBuilder())
        Assertx.assertThreeEquals(result, Stringx.mapIndexed(source) { index, it -> indexPair1.first.append(index);it.toString() },
                source.mapIndexed { index, it -> indexPair1.second.append(index); it.toString() })
        Assertx.assertThreeEquals("0123456789101112", indexPair1.first.toString(), indexPair1.second.toString())
        val indexPair2 = Pair(StringBuilder(), StringBuilder())
        Assertx.assertThreeEquals(result, Stringx.mapIndexedTo(source, ArrayList(source.length)) { index, it -> indexPair2.first.append(index);it.toString() },
                source.mapIndexedTo(ArrayList(source.length)) { index, it -> indexPair2.second.append(index); it.toString() })
        Assertx.assertThreeEquals("0123456789101112", indexPair2.first.toString(), indexPair2.second.toString())

        val resultNotNull = listOf("1", "6", "5", "2", "6", "8", "9", "4", "5", "6")
        Assertx.assertThreeEquals(resultNotNull, Stringx.mapNotNull(source) { if (it != '3') it.toString() else null },
                source.mapNotNull { if (it != '3') it.toString() else null })
        Assertx.assertThreeEquals(resultNotNull, Stringx.mapNotNullTo(source, ArrayList(source.length)) { if (it != '3') it.toString() else null },
                source.mapNotNullTo(ArrayList(source.length)) { if (it != '3') it.toString() else null })

        val indexPair3 = Pair(StringBuilder(), StringBuilder())
        Assertx.assertThreeEquals(resultNotNull, Stringx.mapIndexedNotNull(source) { index, it -> indexPair3.first.append(index);if (it != '3') it.toString() else null },
                source.mapIndexedNotNull { index, it -> indexPair3.second.append(index);if (it != '3') it.toString() else null })
        Assertx.assertThreeEquals("0123456789101112", indexPair3.first.toString(), indexPair3.second.toString())
        val indexPair4 = Pair(StringBuilder(), StringBuilder())
        Assertx.assertThreeEquals(resultNotNull, Stringx.mapIndexedNotNullTo(source, ArrayList(source.length)) { index, it -> indexPair4.first.append(index);if (it != '3') it.toString() else null },
                source.mapIndexedNotNullTo(ArrayList(source.length)) { index, it -> indexPair4.second.append(index);if (it != '3') it.toString() else null })
        Assertx.assertThreeEquals("0123456789101112", indexPair4.first.toString(), indexPair4.second.toString())
    }

    @Test
    fun testWithIndex() {
        val source = "1365268945336"
        Assertx.assertThreeEquals(listOf(Pair(0, '1')
                , Pair(1, '3')
                , Pair(2, '6')
                , Pair(3, '5')
                , Pair(4, '2')
                , Pair(5, '6')
                , Pair(6, '8')
                , Pair(7, '9')
                , Pair(8, '4')
                , Pair(9, '5')
                , Pair(10, '3')
                , Pair(11, '3')
                , Pair(12, '6')
        ), Stringx.withIndex(source).map { Pair(it.index, it.value) }, source.withIndex().map { Pair(it.index, it.value) })
    }

    @Test
    fun testAll() {
        Assertx.assertThreeEquals(true, Stringx.all("14134543") { it.isDigit() }, "14134543".all { it.isDigit() })
        Assertx.assertThreeEquals(false, Stringx.all("fas42rqwr  \nrqw") { it.isDigit() }, "fas42rqwr  \nrqw".all { it.isDigit() })
    }

    @Test
    fun testAny() {
        Assertx.assertThreeEquals(true, Stringx.any("14134543"), "14134543".any())
        Assertx.assertThreeEquals(false, Stringx.any(""), "".any())
        Assertx.assertThreeEquals(true, Stringx.any("as发生法萨芬") { Charx.isChinese(it) }, "as发生法萨芬".any { Charx.isChinese(it) })
        Assertx.assertThreeEquals(false, Stringx.any("as789fksnfs") { Charx.isChinese(it) }, "as789fksnfs".any { Charx.isChinese(it) })
    }

    @Test
    fun testCount() {
        val source = "1365268945336"
        Assertx.assertThreeEquals(13, Stringx.count(source), source.count())
        Assertx.assertThreeEquals(0, Stringx.count(""), "".count())
        assertEquals(0, Stringx.count(null))

        Assertx.assertThreeEquals(6, Stringx.count(source) { it.toString().toInt() % 2 == 0 }, source.count { it.toString().toInt() % 2 == 0 })
        assertEquals(0, Stringx.count(null) { it.toString().toInt() % 2 == 0 })
    }

    @Test
    fun testFold() {
        val source = "1365268945336"

        Assertx.assertThreeEquals(61, Stringx.fold(source, 0) { accumulator, it -> accumulator + it.toString().toInt() },
                source.fold(0) { accumulator, it -> accumulator + it.toString().toInt() })
        Assertx.assertThreeEquals(63, Stringx.fold(source, 2) { accumulator, it -> accumulator + it.toString().toInt() },
                source.fold(2) { accumulator, it -> accumulator + it.toString().toInt() })

        val indexPair1 = Pair(StringBuilder(), StringBuilder())
        Assertx.assertThreeEquals(61, Stringx.foldIndexed(source, 0) { index, accumulator, it -> indexPair1.first.append(index);accumulator + it.toString().toInt() },
                source.foldIndexed(0) { index, accumulator, it -> indexPair1.second.append(index);accumulator + it.toString().toInt() })
        Assertx.assertThreeEquals("0123456789101112", indexPair1.first.toString(), indexPair1.second.toString())

        Assertx.assertThreeEquals(61, Stringx.foldRight(source, 0) { it, accumulator -> accumulator + it.toString().toInt() },
                source.foldRight(0) { it, accumulator -> accumulator + it.toString().toInt() })
        Assertx.assertThreeEquals(63, Stringx.foldRight(source, 2) { it, accumulator -> accumulator + it.toString().toInt() },
                source.foldRight(2) { it, accumulator -> accumulator + it.toString().toInt() })
        assertEquals(3, Stringx.foldRight(null, 3) { it, accumulator -> accumulator + it.toString().toInt() })

        val indexPair2 = Pair(StringBuilder(), StringBuilder())
        Assertx.assertThreeEquals(61, Stringx.foldRightIndexed(source, 0) { index, it, accumulator -> indexPair2.first.append(index);accumulator + it.toString().toInt() },
                source.foldRightIndexed(0) { index, it, accumulator -> indexPair2.second.append(index);accumulator + it.toString().toInt() })
        Assertx.assertThreeEquals("1211109876543210", indexPair2.first.toString(), indexPair2.second.toString())
        assertEquals(3, Stringx.foldRightIndexed(null, 3) { _, it, accumulator -> accumulator + it.toString().toInt() })
    }

    @Test
    fun testForEach() {
        val source = "1365268945336"

        Assertx.assertThreeEquals(source, StringBuilder().apply { Stringx.forEach(source) { append(it) } }.toString(),
                StringBuilder().apply { source.forEach { append(it) } }.toString())
        Assertx.assertThreeEquals("0:11:32:63:54:25:66:87:98:49:510:311:312:6", StringBuilder().apply { Stringx.forEachIndexed(source) { index, it -> append(index.toString() + ":" + it) } }.toString(),
                StringBuilder().apply { source.forEachIndexed { index, it -> append(index.toString() + ":" + it) } }.toString())
    }

    @Test
    fun testMax() {
        val source = "1365268945336"
        Assertx.assertThreeEquals('9', Stringx.max(source), source.max())
        Assertx.assertThreeEquals(null, Stringx.max(""), "".max())
        assertNull(Stringx.max(null))

        Assertx.assertThreeEquals('9', Stringx.maxBy(source) { it.toString().toInt() }, source.maxBy { it.toString().toInt() })
        Assertx.assertThreeEquals(null, Stringx.maxBy("") { it.toString().toInt() }, "".maxBy { it.toString().toInt() })
        assertNull(Stringx.maxBy(null) { it.toString().toInt() })

        Assertx.assertThreeEquals('9', Stringx.maxWith(source) { it1, it2 -> it1 - it2 }, source.maxWith(kotlin.Comparator { o1, o2 -> o1 - o2 }))
        Assertx.assertThreeEquals(null, Stringx.maxWith("") { it1, it2 -> it1 - it2 }, "".maxWith(kotlin.Comparator { o1, o2 -> o1 - o2 }))
        assertNull(Stringx.maxWith(null) { it1, it2 -> it1 - it2 })
    }

    @Test
    fun testMin() {
        val source = "3652689453361"
        Assertx.assertThreeEquals('1', Stringx.min(source), source.min())
        Assertx.assertThreeEquals(null, Stringx.min(""), "".min())
        assertNull(Stringx.min(null))

        Assertx.assertThreeEquals('1', Stringx.minBy(source) { it.toString().toInt() }, source.minBy { it.toString().toInt() })
        Assertx.assertThreeEquals(null, Stringx.minBy("") { it.toString().toInt() }, "".minBy { it.toString().toInt() })
        assertNull(Stringx.minBy(null) { it.toString().toInt() })

        Assertx.assertThreeEquals('1', Stringx.minWith(source) { it1, it2 -> it1 - it2 }, source.minWith(kotlin.Comparator { o1, o2 -> o1 - o2 }))
        Assertx.assertThreeEquals(null, Stringx.minWith("") { it1, it2 -> it1 - it2 }, "".minWith(kotlin.Comparator { o1, o2 -> o1 - o2 }))
        assertNull(Stringx.minWith(null) { it1, it2 -> it1 - it2 })
    }

    @Test
    fun testNone() {
        Assertx.assertThreeEquals(true, Stringx.none(""), "".none())
        Assertx.assertThreeEquals(false, Stringx.none("1"), "1".none())
        assertEquals(false, Stringx.none(null))

        Assertx.assertThreeEquals(true, Stringx.none("132412dsg") { Charx.isChinese(it) }, "132412dsg".none { Charx.isChinese(it) })
        Assertx.assertThreeEquals(false, Stringx.none("13241天2dsg") { Charx.isChinese(it) }, "13241天2dsg".none { Charx.isChinese(it) })
        assertEquals(true, Stringx.none(null) { Charx.isChinese(it) })
    }

    @Test
    fun testOnEach() {
        val source = "1365268945336"

        val pair1 = Pair(StringBuilder(), StringBuilder())
        Assertx.assertThreeEquals(source, Stringx.onEach(source) { pair1.first.append(it) }, source.onEach { pair1.second.append(it) })
        Assertx.assertThreeEquals(source, pair1.first.toString(), pair1.second.toString())

        val pair2 = Pair(StringBuilder(), StringBuilder())
        Assertx.assertThreeEquals("", Stringx.onEach("") { pair2.first.append(it) }, "".onEach { pair2.second.append(it) })
        Assertx.assertThreeEquals("", pair2.first.toString(), pair2.second.toString())

        val pair3 = Pair(StringBuilder(), StringBuilder())
        assertEquals("", Stringx.onEach(null as String?) { pair3.first.append(it) })
        Assertx.assertThreeEquals("", pair3.first.toString(), pair3.second.toString())
    }

    @Test
    fun testReduce() {
        val source = "1365268945336"

        Assertx.assertThreeEquals(
                'm',
                Stringx.reduce(source) { accumulator, it -> accumulator + it.toString().toInt() },
                source.reduce { accumulator, it -> accumulator + it.toString().toInt() })
        try {
            Stringx.reduce("") { accumulator, it -> accumulator + it.toString().toInt() }
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.reduce(null) { accumulator, it -> accumulator + it.toString().toInt() }
            fail()
        } catch (e: Exception) {
        }

        val indexPair1 = Pair(StringBuilder(), StringBuilder())
        Assertx.assertThreeEquals(
                'm',
                Stringx.reduceIndexed(source) { index, accumulator, it -> indexPair1.first.append(index);accumulator + it.toString().toInt() },
                source.reduceIndexed { index, accumulator, it -> indexPair1.second.append(index);accumulator + it.toString().toInt() })
        Assertx.assertThreeEquals(
                "123456789101112",
                indexPair1.first.toString(),
                indexPair1.second.toString())
        try {
            Stringx.reduceIndexed("") { _, accumulator, it -> accumulator + it.toString().toInt() }
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.reduceIndexed(null) { _, accumulator, it -> accumulator + it.toString().toInt() }
            fail()
        } catch (e: Exception) {
        }

        Assertx.assertThreeEquals(
                'm',
                Stringx.reduceRight(source) { it, accumulator -> accumulator + it.toString().toInt() },
                source.reduceRight { it, accumulator -> accumulator + it.toString().toInt() })
        try {
            Stringx.reduceRight("") { accumulator, it -> accumulator + it.toString().toInt() }
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.reduceRight(null) { accumulator, it -> accumulator + it.toString().toInt() }
            fail()
        } catch (e: Exception) {
        }

        val indexPair2 = Pair(StringBuilder(), StringBuilder())
        Assertx.assertThreeEquals(
                'm',
                Stringx.reduceRightIndexed(source) { index, it, accumulator -> indexPair2.first.append(index);accumulator + it.toString().toInt() },
                source.reduceRightIndexed { index, it, accumulator -> indexPair2.second.append(index);accumulator + it.toString().toInt() })
        Assertx.assertThreeEquals(
                "11109876543210",
                indexPair2.first.toString(),
                indexPair2.second.toString())
        try {
            Stringx.reduceRightIndexed("") { _, accumulator, it -> accumulator + it.toString().toInt() }
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.reduceRightIndexed(null) { _, accumulator, it -> accumulator + it.toString().toInt() }
            fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testSum() {
        val source = "1365268945336"

        Assertx.assertThreeEquals(
                61,
                Stringx.sumBy(source) { it.toString().toInt() },
                source.sumBy { it.toString().toInt() })
        Assertx.assertThreeEquals(
                61.0,
                Stringx.sumByDouble(source) { it.toString().toDouble() },
                source.sumByDouble { it.toString().toDouble() })
    }

    @Test
    fun testChunked() {
        val source = "0123456789"
        Assertx.assertThreeEquals(
                listOf("012", "345", "678", "9"),
                Stringx.chunked(source, 3),
                source.chunked(3))
        Assertx.assertThreeEquals(
                listOf(3, 12, 21, 9),
                Stringx.chunked(source, 3) { partial -> partial.sumBy { it.toString().toInt() } },
                source.chunked(3) { partial -> partial.sumBy { it.toString().toInt() } })

        Assertx.assertThreeEquals(
                listOf("012", "345", "678", "9"),
                Stringx.chunkedIterable(source, 3).toMutableList(),
                source.chunkedSequence(3).toMutableList())
        Assertx.assertThreeEquals(
                listOf(3, 12, 21, 9),
                Stringx.chunkedIterable(source, 3) { partial -> partial.sumBy { it.toString().toInt() } }.toMutableList(),
                source.chunkedSequence(3) { partial -> partial.sumBy { it.toString().toInt() } }.toMutableList())
    }

    @Test
    fun testPartition() {
        val source = "0123456789"
        Assertx.assertThreeEquals(
                Pair("02468", "13579").toString(),
                Stringx.partition(source) { it.toString().toInt() % 2 == 0 }.toString(),
                source.partition { it.toString().toInt() % 2 == 0 }.toString())
        Assertx.assertThreeEquals(
                Pair("02468", "13579").toString(),
                Stringx.partition(StringBuilder(source)) { it.toString().toInt() % 2 == 0 }.toString(),
                StringBuilder(source).partition { it.toString().toInt() % 2 == 0 }.toString())
    }

    @Test
    fun testWindowed() {
        val source = "0123456789"
        Assertx.assertThreeEquals(
                listOf("012", "234", "456", "678"),
                Stringx.windowed(source, 3, 2, false),
                source.windowed(3, 2, false))
        Assertx.assertThreeEquals(
                listOf("012", "234", "456", "678", "89"),
                Stringx.windowed(source, 3, 2, true),
                source.windowed(3, 2, true))

        Assertx.assertThreeEquals(
                listOf(3, 9, 15, 21),
                Stringx.windowed(source, 3, 2, false) { partial -> partial.sumBy { it.toString().toInt() } },
                source.windowed(3, 2, false) { partial -> partial.sumBy { it.toString().toInt() } })

        assertEquals(listOf<String>(), Stringx.windowed(null, 3, 2, false) { partial -> partial.sumBy { it.toString().toInt() } })

        Assertx.assertThreeEquals(
                listOf("012", "234", "456", "678"),
                Stringx.windowedIterable(source, 3, 2, false).toMutableList(),
                source.windowedSequence(3, 2, false).toMutableList())
        Assertx.assertThreeEquals(
                listOf(3, 9, 15, 21),
                Stringx.windowedIterable(source, 3, 2, false) { partial -> partial.sumBy { it.toString().toInt() } }.toMutableList(),
                source.windowedSequence(3, 2, false) { partial -> partial.sumBy { it.toString().toInt() } }.toMutableList())

        try {
            Stringx.windowed(source, 3, 0, false)
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.windowed(source, 0, 3, false)
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.windowed(source, 0, 0, false)
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.windowedIterable(source, 3, 0, false)
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.windowedIterable(source, 0, 3, false)
            fail()
        } catch (e: Exception) {
        }
        try {
            Stringx.windowedIterable(source, 0, 0, false)
            fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testZip() {
        val source = "0123456789"
        val other = "01234567"
        Assertx.assertThreeEquals(
                listOf("0:0", "1:1", "2:2", "3:3", "4:4", "5:5", "6:6", "7:7"),
                Stringx.zip(source, other) { it1, it2 -> "$it1:$it2" },
                source.zip(other) { it1, it2 -> "$it1:$it2" })
        Assertx.assertThreeEquals(
                listOf(Pair("0", "0").toString(), Pair("1", "1").toString(), Pair("2", "2").toString(), Pair("3", "3").toString(), Pair("4", "4").toString(), Pair("5", "5").toString(), Pair("6", "6").toString(), Pair("7", "7").toString()),
                Stringx.zip(source, other).map { it.toString() },
                source.zip(other).map { it.toString() })

        assertEquals(listOf<String>(), Stringx.zip(null, other).map { it.toString() })
        assertEquals(listOf<String>(), Stringx.zip(source, null).map { it.toString() })

        Assertx.assertThreeEquals(
                listOf("0:1", "1:2", "2:3", "3:4", "4:5", "5:6", "6:7", "7:8", "8:9"),
                Stringx.zipWithNext(source) { it1, it2 -> "$it1:$it2" },
                source.zipWithNext { it1, it2 -> "$it1:$it2" })
        Assertx.assertThreeEquals(
                listOf(Pair("0", "1").toString(), Pair("1", "2").toString(), Pair("2", "3").toString(), Pair("3", "4").toString(), Pair("4", "5").toString(), Pair("5", "6").toString(), Pair("6", "7").toString(), Pair("7", "8").toString(), Pair("8", "9").toString()),
                Stringx.zipWithNext(source).map { it.toString() },
                source.zipWithNext().map { it.toString() })
        assertEquals(listOf<String>(), Stringx.zipWithNext("").map { it.toString() })
        assertEquals(listOf<String>(), Stringx.zipWithNext(null).map { it.toString() })
    }

    @Test
    fun testAs() {
        val source = "0123456789"
        Assertx.assertThreeEquals(
                "0,1,2,3,4,5,6,7,8,9",
                Stringx.asIterable(source).joinToString(","),
                source.asIterable().joinToString(","))
        Assertx.assertThreeEquals(
                "",
                Stringx.asIterable(StringBuilder("")).joinToString(","),
                StringBuilder("").asIterable().joinToString(","))
        assertEquals(listOf<Char>(), Stringx.asIterable(null))
        assertEquals(listOf<Char>(), Stringx.asIterable(""))
    }
}
