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

@file:Suppress("PrivatePropertyName")

package com.github.panpf.tools4j.lang.ktx

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class StringxTest {

    private val BLANK = "     "
    private val BLANK_CHAR_SEQUENCE: CharSequence = "     "
    private val SPACE = " "
    private val ENTRY = "\r"
    private val TAB = "\t"
    private val WRAP = "\n"
    private val EMPTY = ""
    private val EMPTY_CHAR_SEQUENCE: CharSequence = ""
    private val YES = "yes"
    private val YES_CHAR_SEQUENCE: CharSequence = "*%￥#@"
    private val DIGIT = "8"
    private val DIGIT_CHAR_SEQUENCE: CharSequence = "8"
    private val LETTER = "a飞"
    private val LETTER_CHAR_SEQUENCE: CharSequence = "a飞"
    private val CHINESE = "飞"
    private val CHINESE_CHAR_SEQUENCE: CharSequence = "飞"
    private val LETTER_OR_DIGIT = "a飞8"
    private val LETTER_OR_DIGIT_CHAR_SEQUENCE: CharSequence = "a飞8"
    private val SYMBOL = "*%￥#@"

    @Test
    fun testSafe() {
        assertTrue(YES.isSafe())
        assertFalse(null.isSafe())
        assertFalse(BLANK.isSafe())
        assertFalse(EMPTY.isSafe())

        assertTrue((null as CharSequence?).isNotSafe())
        assertTrue(BLANK.isNotSafe())
        assertTrue(EMPTY.isNotSafe())
        assertFalse(YES.isNotSafe())

        assertEquals(EMPTY.safeOr("default"), "default")
        assertEquals(EMPTY_CHAR_SEQUENCE.safeOr("default"), "default")
        assertEquals(YES.safeOr("default"), YES)
        assertEquals(YES_CHAR_SEQUENCE.safeOr("default"), YES_CHAR_SEQUENCE)

        assertEquals("123" as CharSequence, ("123" as CharSequence).safeOrNull())
        assertNull(("" as CharSequence).safeOrNull())
        assertNull((null as CharSequence?).safeOrNull())
        assertEquals("123", "123".safeOrNull())
        assertNull("".safeOrNull())
        assertNull((null as String?).safeOrNull())
    }

    @Test
    fun testRequireSafe() {
        "fas".requireSafe()

        try {
            "".requireSafe()
            fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }

        "fas".requireSafe()

        try {
            "".requireSafe()
            fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }

    }

    @Test
    fun testRequireNotSafe() {
        "".requireNotSafe()

        try {
            "fas".requireNotSafe()
            fail()
        } catch (e: Exception) {
            // // e.printStackTrace();
        }

        "".requireNotSafe()

        try {
            "fas".requireNotSafe()
            fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
    }

    @Test
    fun testIsBlank() {
        assertTrue(BLANK.isBlank())
        assertTrue(EMPTY.isBlank())
        assertTrue(SPACE.isBlank())
        assertTrue(ENTRY.isBlank())
        assertTrue(TAB.isBlank())
        assertTrue(WRAP.isBlank())
        assertFalse(YES.isBlank())
        assertFalse(DIGIT.isBlank())
        assertFalse(LETTER.isBlank())
        assertFalse(CHINESE.isBlank())
        assertFalse(LETTER_OR_DIGIT.isBlank())
        assertFalse(SYMBOL.isBlank())
//        assertFalse(null.isBlank())

        assertTrue(YES.isNotBlank())
        assertTrue(DIGIT.isNotBlank())
        assertTrue(LETTER.isNotBlank())
        assertTrue(CHINESE.isNotBlank())
        assertTrue(LETTER_OR_DIGIT.isNotBlank())
        assertTrue(SYMBOL.isNotBlank())
//        assertTrue(null.isNotBlank())
        assertFalse(BLANK.isNotBlank())
        assertFalse(EMPTY.isNotBlank())
        assertFalse(SPACE.isNotBlank())
        assertFalse(ENTRY.isNotBlank())
        assertFalse(TAB.isNotBlank())
        assertFalse(WRAP.isNotBlank())

        assertEquals(BLANK.notBlankOr("default"), "default")
        assertEquals(BLANK_CHAR_SEQUENCE.notBlankOr("default"), "default")
        assertEquals(YES.notBlankOr("default"), YES)
        assertEquals(YES_CHAR_SEQUENCE.notBlankOr("default"), YES_CHAR_SEQUENCE)
        assertEquals(null.notBlankOr("default"), null)


        assertTrue(null.isNullOrBlank())
        @Suppress("UselessCallOnNotNull")
        assertTrue(BLANK.isNullOrBlank())
        @Suppress("UselessCallOnNotNull")
        assertTrue(EMPTY.isNullOrBlank())
        @Suppress("UselessCallOnNotNull")
        assertFalse(YES.isNullOrBlank())

        assertTrue(YES.isNotNullOrBlank())
        assertFalse(null.isNotNullOrBlank())
        assertFalse(BLANK.isNotNullOrBlank())
        assertFalse(EMPTY.isNotNullOrBlank())

        assertEquals(BLANK.notNullOrBlankOr("default"), "default")
        assertEquals(BLANK_CHAR_SEQUENCE.notNullOrBlankOr("default"), "default")
        assertEquals(null.notNullOrBlankOr("default"), "default")
        assertEquals(YES.notNullOrBlankOr("default"), YES)
        assertEquals(YES_CHAR_SEQUENCE.notNullOrBlankOr("default"), YES_CHAR_SEQUENCE)
    }

    @Test
    fun testIsEmpty() {
        assertTrue(EMPTY.isEmpty())
        assertFalse(BLANK.isEmpty())
        assertFalse(YES.isEmpty())
//        assertFalse(null.isEmpty())

        assertTrue(BLANK.isNotEmpty())
        assertTrue(YES.isNotEmpty())
//        assertTrue(null.isNotEmpty())
        assertFalse(EMPTY.isNotEmpty())

        assertEquals(EMPTY.notEmptyOr("default"), "default")
        assertEquals(EMPTY_CHAR_SEQUENCE.notEmptyOr("default"), "default")
        assertEquals(YES.notEmptyOr("default"), YES)
        assertEquals(YES_CHAR_SEQUENCE.notEmptyOr("default"), YES_CHAR_SEQUENCE)


        assertTrue((null as String?).isNullOrEmpty())
        @Suppress("UselessCallOnNotNull")
        assertTrue(EMPTY.isNullOrEmpty())
        @Suppress("UselessCallOnNotNull")
        assertFalse(YES.isNullOrEmpty())

        assertTrue(YES.isNotNullOrEmpty())
        assertFalse(null.isNotNullOrEmpty())
        assertFalse(EMPTY.isNotNullOrEmpty())

        assertEquals(EMPTY.notNullOrEmptyOr("default"), "default")
        assertEquals(EMPTY_CHAR_SEQUENCE.notNullOrEmptyOr("default"), "default")
        assertEquals(null.notNullOrEmptyOr("default"), "default")
        assertEquals(YES.notNullOrEmptyOr("default"), YES)
        assertEquals(YES_CHAR_SEQUENCE.notNullOrEmptyOr("default"), YES_CHAR_SEQUENCE)
    }

    @Test
    fun testChinese() {
        assertTrue(CHINESE.isChinese())
        assertFalse(EMPTY.isChinese())
        assertFalse(DIGIT.isChinese())
        assertFalse(LETTER.isChinese())
        assertFalse(BLANK.isChinese())
        assertFalse(null.isChinese())

        assertTrue(EMPTY.isNotChinese())
        assertTrue(DIGIT.isNotChinese())
        assertTrue(LETTER.isNotChinese())
        assertTrue(BLANK.isNotChinese())
        assertTrue(null.isNotChinese())
        assertFalse(CHINESE.isNotChinese())

        assertEquals(LETTER.chineseOr("default"), "default")
        assertEquals(LETTER_CHAR_SEQUENCE.chineseOr("default"), "default")
        assertEquals(null.chineseOr("default"), "default")
        assertEquals(CHINESE.chineseOr("default"), CHINESE)
        assertEquals(CHINESE_CHAR_SEQUENCE.chineseOr("default"), CHINESE_CHAR_SEQUENCE)
    }

    @Test
    fun testDigit() {
        assertTrue(DIGIT.isDigit())
        assertFalse(EMPTY.isDigit())
        assertFalse(CHINESE.isDigit())
        assertFalse(LETTER.isDigit())
        assertFalse(BLANK.isDigit())
        assertFalse(null.isDigit())

        assertTrue(EMPTY.isNotDigit())
        assertTrue(CHINESE.isNotDigit())
        assertTrue(LETTER.isNotDigit())
        assertTrue(BLANK.isNotDigit())
        assertTrue(null.isNotDigit())
        assertFalse(DIGIT.isNotDigit())

        assertEquals(LETTER.digitOr("3"), "3")
        assertEquals(LETTER_CHAR_SEQUENCE.digitOr("3"), "3")
        assertEquals(null.digitOr("3"), "3")
        assertEquals(DIGIT.digitOr("3"), DIGIT)
        assertEquals(DIGIT_CHAR_SEQUENCE.digitOr("3"), DIGIT_CHAR_SEQUENCE)
    }

    @Test
    fun testLetter() {
        assertTrue(LETTER.isLetter())
        assertFalse(EMPTY.isLetter())
        assertFalse(DIGIT.isLetter())
        assertFalse(BLANK.isLetter())
        assertFalse(null.isLetter())

        assertTrue(EMPTY.isNotLetter())
        assertTrue(DIGIT.isNotLetter())
        assertTrue(BLANK.isNotLetter())
        assertTrue(null.isNotLetter())
        assertFalse(LETTER.isNotLetter())

        assertEquals(DIGIT.letterOr("default"), "default")
        assertEquals(DIGIT_CHAR_SEQUENCE.letterOr("default"), "default")
        assertEquals(null.letterOr("default"), "default")
        assertEquals(LETTER.letterOr("default"), LETTER)
        assertEquals(LETTER_CHAR_SEQUENCE.letterOr("default"), LETTER_CHAR_SEQUENCE)
    }

    @Test
    fun testLetterOrDigit() {
        assertTrue(LETTER_OR_DIGIT.isLetterOrDigit())
        assertFalse(EMPTY.isLetterOrDigit())
        assertFalse(BLANK.isLetterOrDigit())
        assertFalse(SYMBOL.isLetterOrDigit())
        assertFalse(null.isLetterOrDigit())

        assertTrue(EMPTY.isNotLetterOrDigit())
        assertTrue(BLANK.isNotLetterOrDigit())
        assertTrue(null.isNotLetterOrDigit())
        assertTrue(SYMBOL.isNotLetterOrDigit())
        assertFalse(LETTER_OR_DIGIT.isNotLetterOrDigit())

        assertEquals(EMPTY.letterOrDigitOr("default"), "default")
        assertEquals(EMPTY_CHAR_SEQUENCE.letterOrDigitOr("default"), "default")
        assertEquals(null.letterOrDigitOr("default"), "default")
        assertEquals(LETTER_OR_DIGIT.letterOrDigitOr("default"), LETTER_OR_DIGIT)
        assertEquals(LETTER_OR_DIGIT_CHAR_SEQUENCE.letterOrDigitOr("default"), LETTER_OR_DIGIT_CHAR_SEQUENCE)
    }

    @Test
    fun testContains() {
        assertTrue("今天天气晴".containsAny(arrayOf("哈", "天")))
        assertFalse("今天天气晴".containsAny(arrayOf("哈")))
        assertTrue("今天天气晴".containsAny(listOf("哈", "天")))
        assertFalse("今天天气晴".containsAny(listOf("哈")))
        assertFalse("今天天气晴".containsAny(null as Array<String>?))
        assertFalse(null.containsAny(null as Array<String>?))
        assertFalse("今天天气晴".containsAny(null as Collection<String>?))
        assertFalse(null.containsAny(null as Collection<String>?))
        assertFalse("今天天气晴".containsAny(arrayOf()))
        assertFalse("今天天气晴".containsAny(LinkedList()))

        assertTrue("今天天气晴".containsAll(arrayOf("晴", "天")))
        assertFalse("今天天气晴".containsAll(arrayOf("哈", "天")))
        assertTrue("今天天气晴".containsAll(listOf("晴", "天")))
        assertFalse("今天天气晴".containsAll(listOf("哈", "天")))
        assertFalse("今天天气晴".containsAll(null as Array<String>?))
        assertFalse(null.containsAll(null as Array<String>?))
        assertFalse("今天天气晴".containsAll(null as Collection<String>?))
        assertFalse(null.containsAll(null as Collection<String>?))
        assertFalse("今天天气晴".containsAll(arrayOf()))
        assertFalse("今天天气晴".containsAll(LinkedList()))

        assertFalse("HCHC".containsAny(arrayOf("h", "a")))
        assertTrue("HCHC".containsAny(arrayOf("h", "a"), true))
        assertFalse("HCHC".containsAny(listOf("h", "a")))
        assertTrue("HCHC".containsAny(listOf("h", "a"), true))

        assertFalse("HAHA".containsAll(arrayOf("h", "a")))
        assertTrue("HAHA".containsAll(arrayOf("h", "a"), true))
        assertFalse("HAHA".containsAll(listOf("h", "a")))
        assertTrue("HAHA".containsAll(listOf("h", "a"), true))
    }

    @Test
    fun testOrAndTo() {
        assertEquals("今".orEmpty(), "今")
        assertEquals(null.orEmpty(), "")
        assertEquals(("今" as CharSequence).orEmpty(), "今")
        assertEquals((null as CharSequence?).orEmpty(), "")

        assertEquals("今".orDefault("天"), "今")
        assertEquals(null.orDefault("天"), "天")
        assertEquals(("今" as CharSequence).orDefault("天"), "今")
        assertEquals((null as CharSequence?).orDefault("天"), "天")

        assertNotNull("今".emptyToNull())
        assertNull("".emptyToNull())
        assertNotNull(("今" as CharSequence).emptyToNull())
        assertNull(("" as CharSequence).emptyToNull())

        assertNotNull("今".blankToNull())
        assertNull(" ".blankToNull())
        assertNotNull(("今" as CharSequence).blankToNull())
        assertNull((" " as CharSequence).blankToNull())
    }

    @Test
    fun testFilterBlank() {
        val source = " 费劲啊是否将as\n\n\t523\t\tcxbv\r\r而乏味 贵公司   "
        assertEquals(source.filterBlank(), "费劲啊是否将as523cxbv而乏味贵公司")
        assertEquals(StringBuilder(source).filterBlank().toString(), "费劲啊是否将as523cxbv而乏味贵公司")
    }

    @Test
    fun testRemoveChar() {
        assertEquals("012456789", "0123456789".removeChar('3'))
        assertEquals("123456789", "0123456789".removeChar('0'))
        assertEquals("012345678", "0123456789".removeChar('9'))
        assertEquals("", null.removeChar('9'))

        assertEquals("021456789", "0121456789".removeFirstChar('1'))
        assertEquals("", null.removeFirstChar('1'))
        assertEquals("012456789", "0121456789".removeLastChar('1'))
        assertEquals("", null.removeLastChar('1'))

        assertEquals("012456789", "0123456789".removeIndex(3))
        assertEquals("123456789", "0123456789".removeIndex(0))
        assertEquals("012345678", "0123456789".removeIndex(9))
        assertEquals("", null.removeIndex(9))
    }

    @Test
    fun testLimit() {
        assertEquals("今天天气晴", "今天天气晴".limit(6))
        assertEquals("今天天气晴", "今天天气晴".limit(6))
        assertEquals("今天天气晴", "今天天气晴".limit(6, "..."))
        assertEquals("今天天气晴朗", "今天天气晴朗，万里无云".limit(6))
        assertEquals("今天天气晴朗...", "今天天气晴朗，万里无云".limit(6, "..."))

        assertEquals("今天天气晴", ("今天天气晴" as CharSequence).limit(6))
        assertEquals("今天天气晴", ("今天天气晴" as CharSequence).limit(6))
        assertEquals("今天天气晴", ("今天天气晴" as CharSequence).limit(6, "..."))
        assertEquals("今天天气晴朗", ("今天天气晴朗，万里无云" as CharSequence).limit(6))
        assertEquals("今天天气晴朗...", ("今天天气晴朗，万里无云" as CharSequence).limit(6, "..."))

        assertEquals("", null.limit(6, "..."))
        try {
            "今天天气晴".limit(-1, "...")
            fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testReplaceNoRepeat() {
        assertEquals("\\\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\\\"", "\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\"".replaceNoRepeat("\"", "\\\"").replaceNoRepeat("\"", "\\\""))
        assertEquals("\\\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\\\"", "\\\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\"".replaceNoRepeat("\"", "\\\"").replaceNoRepeat("\"", "\\\""))
        assertEquals("\\\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\\\"", "\"loRSr5UDBBtHtGiQfHiCzoK1nl_1\\\"".replaceNoRepeat("\"", "\\\"").replaceNoRepeat("\"", "\\\""))
        assertEquals("loRSr5UDBBtHtGiQfHiCzoK1nl_1", "loRSr5UDBBtHtGiQfHiCzoK1nl_1".replaceNoRepeat("\"", "\\\"").replaceNoRepeat("\"", "\\\""))

        assertEquals("爱芬。，，出现在。，，女方家唉都", "爱芬，，出现在，，女方家唉都".replaceNoRepeat("，，", "。，，"))
        assertEquals("爱芬。，，出现在。，，女方家唉都", "爱芬，，出现在，，女方家唉都".replaceNoRepeat("，，", "。，，").replaceNoRepeat("，，", "。，，"))
        assertEquals("。，，爱芬出现在。，，女方家唉都", "，，爱芬出现在，，女方家唉都".replaceNoRepeat("，，", "。，，").replaceNoRepeat("，，", "。，，"))
        assertEquals("爱芬。，，出现在女方家唉都。，，", "爱芬，，出现在女方家唉都，，".replaceNoRepeat("，，", "。，，").replaceNoRepeat("，，", "。，，"))
    }

    @Test
    fun testFirstLetterUpperCase() {
        assertEquals("HelloMan", "helloMan".firstLetterUpperCase())
        assertEquals("HelloMan", "HelloMan".firstLetterUpperCase())
        assertEquals("4124", "4124".firstLetterUpperCase())
        assertEquals("你好", "你好".firstLetterUpperCase())
        assertEquals("", "".firstLetterUpperCase())
        assertEquals("", null.firstLetterUpperCase())
        assertEquals(" ", " ".firstLetterUpperCase())
    }

    @Test
    fun testFirstLetterLowerCase() {
        assertEquals("helloMan", "HelloMan".firstLetterLowerCase())
        assertEquals("helloMan", "HelloMan".firstLetterLowerCase())
        assertEquals("4124", "4124".firstLetterLowerCase())
        assertEquals("你好", "你好".firstLetterLowerCase())
        assertEquals("", "".firstLetterLowerCase())
        assertEquals("", null.firstLetterLowerCase())
        assertEquals(" ", " ".firstLetterLowerCase())
    }

    @Test
    fun testHiddenStartChars() {
        assertEquals("12345".hiddenStartChars(4), "****5")
        assertEquals("123456".hiddenStartChars(4), "****56")
        assertEquals("1234".hiddenStartChars(4), "****")
        assertEquals("123".hiddenStartChars(4), "***")
        assertEquals("13509853689".hiddenStartChars(4), "****9853689")
        assertEquals(null.hiddenStartChars(4), "")

        assertEquals("12345".hiddenStartChars(4, '$'), "$$$$5")
        assertEquals("123456".hiddenStartChars(4, '$'), "$$$$56")
        assertEquals("1234".hiddenStartChars(4, '$'), "$$$$")
        assertEquals("123".hiddenStartChars(4, '$'), "$$$")
        assertEquals("13509853689".hiddenStartChars(4, '$'), "$$$$9853689")
        assertEquals(null.hiddenStartChars(4, '$'), "")
    }

    @Test
    fun testHiddenMiddleChars() {
        assertEquals("12345".hiddenMiddleChars(4), "****5")
        assertEquals("123456".hiddenMiddleChars(4), "1****6")
        assertEquals("1234".hiddenMiddleChars(4), "****")
        assertEquals("123".hiddenMiddleChars(4), "***")
        assertEquals("13509853689".hiddenMiddleChars(4), "135****3689")
        assertEquals(null.hiddenMiddleChars(4), "")

        assertEquals("12345".hiddenMiddleChars(4, '$'), "$$$$5")
        assertEquals("123456".hiddenMiddleChars(4, '$'), "1$$$$6")
        assertEquals("1234".hiddenMiddleChars(4, '$'), "$$$$")
        assertEquals("123".hiddenMiddleChars(4, '$'), "$$$")
        assertEquals("13509853689".hiddenMiddleChars(4, '$'), "135$$$$3689")
        assertEquals(null.hiddenMiddleChars(4, '$'), "")
    }

    @Test
    fun testHiddenEndChars() {
        assertEquals("12345".hiddenEndChars(4), "1****")
        assertEquals("123456".hiddenEndChars(4), "12****")
        assertEquals("1234".hiddenEndChars(4), "****")
        assertEquals("123".hiddenEndChars(4), "***")
        assertEquals("13509853689".hiddenEndChars(4), "1350985****")
        assertEquals(null.hiddenEndChars(4), "")

        assertEquals("12345".hiddenEndChars(4, '$'), "1$$$$")
        assertEquals("123456".hiddenEndChars(4, '$'), "12$$$$")
        assertEquals("1234".hiddenEndChars(4, '$'), "$$$$")
        assertEquals("123".hiddenEndChars(4, '$'), "$$$")
        assertEquals("13509853689".hiddenEndChars(4, '$'), "1350985$$$$")
        assertEquals(null.hiddenEndChars(4, '$'), "")
    }
}