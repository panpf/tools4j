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

package com.github.panpf.tools4j.ranges.date

import com.github.panpf.tools4j.date.Datex
import com.github.panpf.tools4j.date.ktx.*
import org.junit.Assert.*
import org.junit.Test
import java.text.ParseException

class DateRangexTest {

    // todo 测试 reversed 和 step

    @Test
    fun testYearIterator() {
        assertEquals(DateRangex.yearRangeTo("2018".toDate(Datex.y), "2021".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "2018, 2019, 2020, 2021")
        assertEquals(DateRangex.yearRangeTo("2018".toDate(Datex.y), "2021".toDate(Datex.y), 2).joinToString { it.format(Datex.y) }, "2018, 2020")
        assertEquals(DateRangex.yearRangeTo("2018".toDate(Datex.y), "2015".toDate(Datex.y), -2).joinToString { it.format(Datex.y) }, "2018, 2016")
        assertEquals(DateRangex.yearRangeTo("2018".toDate(Datex.y), "2015".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "")
        assertEquals(DateRangex.yearRangeTo("2018".toDate(Datex.y), "2021".toDate(Datex.y), -2).joinToString { it.format(Datex.y) }, "")
        assertEquals(DateRangex.yearRangeTo("2018".toDate(Datex.y), "2015".toDate(Datex.y), 2).joinToString { it.format(Datex.y) }, "")

        assertEquals(DateRangex.yearUntil("2018".toDate(Datex.y), "2022".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "2018, 2019, 2020, 2021")
        assertEquals(DateRangex.yearUntil("2018".toDate(Datex.y), "2022".toDate(Datex.y), 2).joinToString { it.format(Datex.y) }, "2018, 2020")
        assertEquals(DateRangex.yearUntil("2018".toDate(Datex.y), "2015".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "")
        assertEquals(DateRangex.yearUntil("2018".toDate(Datex.y), "2022".toDate(Datex.y), -2).joinToString { it.format(Datex.y) }, "")

        assertEquals(DateRangex.yearDownTo("2018".toDate(Datex.y), "2015".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "2018, 2017, 2016, 2015")
        assertEquals(DateRangex.yearDownTo("2018".toDate(Datex.y), "2021".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "")
    }

    @Test
    fun testMonthIterator() {
        assertEquals(DateRangex.monthRangeTo("2018-08".toDate(Datex.yM), "2018-11".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "2018-08, 2018-09, 2018-10, 2018-11")
        assertEquals(DateRangex.monthRangeTo("2018-08".toDate(Datex.yM), "2018-11".toDate(Datex.yM), 2).joinToString { it.format(Datex.yM) }, "2018-08, 2018-10")
        assertEquals(DateRangex.monthRangeTo("2018-08".toDate(Datex.yM), "2018-05".toDate(Datex.yM), -2).joinToString { it.format(Datex.yM) }, "2018-08, 2018-06")
        assertEquals(DateRangex.monthRangeTo("2018-08".toDate(Datex.yM), "2018-05".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "")
        assertEquals(DateRangex.monthRangeTo("2018-08".toDate(Datex.yM), "2018-11".toDate(Datex.yM), -2).joinToString { it.format(Datex.yM) }, "")
        assertEquals(DateRangex.monthRangeTo("2018-08".toDate(Datex.yM), "2018-05".toDate(Datex.yM), 2).joinToString { it.format(Datex.yM) }, "")

        assertEquals(DateRangex.monthUntil("2018-08".toDate(Datex.yM), "2018-12".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "2018-08, 2018-09, 2018-10, 2018-11")
        assertEquals(DateRangex.monthUntil("2018-08".toDate(Datex.yM), "2018-12".toDate(Datex.yM), 2).joinToString { it.format(Datex.yM) }, "2018-08, 2018-10")
        assertEquals(DateRangex.monthUntil("2018-08".toDate(Datex.yM), "2018-04".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "")
        assertEquals(DateRangex.monthUntil("2018-08".toDate(Datex.yM), "2018-12".toDate(Datex.yM), -2).joinToString { it.format(Datex.yM) }, "")

        assertEquals(DateRangex.monthDownTo("2018-08".toDate(Datex.yM), "2018-05".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "2018-08, 2018-07, 2018-06, 2018-05")
        assertEquals(DateRangex.monthDownTo("2018-08".toDate(Datex.yM), "2018-11".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "")
    }

    @Test
    fun testDayIterator() {
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDate(Datex.yMd), "2018-08-09".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-07, 2018-08-08, 2018-08-09")
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDate(Datex.yMd), "2018-08-09".toDate(Datex.yMd), 2).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-08")
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDate(Datex.yMd), "2018-08-03".toDate(Datex.yMd), -2).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-04")
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDate(Datex.yMd), "2018-08-02".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "")
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDate(Datex.yMd), "2018-08-09".toDate(Datex.yMd), -2).joinToString { it.format(Datex.yMd) }, "")
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDate(Datex.yMd), "2018-08-03".toDate(Datex.yMd), 2).joinToString { it.format(Datex.yMd) }, "")

        assertEquals(DateRangex.dayUntil("2018-08-06".toDate(Datex.yMd), "2018-08-10".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-07, 2018-08-08, 2018-08-09")
        assertEquals(DateRangex.dayUntil("2018-08-06".toDate(Datex.yMd), "2018-08-10".toDate(Datex.yMd), 2).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-08")
        assertEquals(DateRangex.dayUntil("2018-08-06".toDate(Datex.yMd), "2018-08-03".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "")
        assertEquals(DateRangex.dayUntil("2018-08-06".toDate(Datex.yMd), "2018-08-10".toDate(Datex.yMd), -2).joinToString { it.format(Datex.yMd) }, "")

        assertEquals(DateRangex.dayDownTo("2018-08-06".toDate(Datex.yMd), "2018-08-03".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-05, 2018-08-04, 2018-08-03")
        assertEquals(DateRangex.dayDownTo("2018-08-06".toDate(Datex.yMd), "2018-08-10".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "")
    }

    @Test
    fun testHourIterator() {
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 21".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 19, 2018-08-06 20, 2018-08-06 21")
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 21".toDate(Datex.yMdH), 2).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 20")
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 15".toDate(Datex.yMdH), -2).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 16")
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 14".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "")
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 21".toDate(Datex.yMdH), -2).joinToString { it.format(Datex.yMdH) }, "")
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 15".toDate(Datex.yMdH), 2).joinToString { it.format(Datex.yMdH) }, "")

        assertEquals(DateRangex.hourUntil("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 22".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 19, 2018-08-06 20, 2018-08-06 21")
        assertEquals(DateRangex.hourUntil("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 22".toDate(Datex.yMdH), 2).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 20")
        assertEquals(DateRangex.hourUntil("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 15".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "")
        assertEquals(DateRangex.hourUntil("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 22".toDate(Datex.yMdH), -2).joinToString { it.format(Datex.yMdH) }, "")

        assertEquals(DateRangex.hourDownTo("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 15".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 17, 2018-08-06 16, 2018-08-06 15")
        assertEquals(DateRangex.hourDownTo("2018-08-06 18".toDate(Datex.yMdH), "2018-08-06 22".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "")
    }

    @Test
    fun testMinuteIterator() {
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:25".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:23, 2018-08-06 18:24, 2018-08-06 18:25")
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:25".toDate(Datex.yMdHm), 2).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:24")
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:19".toDate(Datex.yMdHm), -2).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:20")
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:18".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "")
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:25".toDate(Datex.yMdHm), -2).joinToString { it.format(Datex.yMdHm) }, "")
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:19".toDate(Datex.yMdHm), 2).joinToString { it.format(Datex.yMdHm) }, "")

        assertEquals(DateRangex.minuteUntil("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:26".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:23, 2018-08-06 18:24, 2018-08-06 18:25")
        assertEquals(DateRangex.minuteUntil("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:26".toDate(Datex.yMdHm), 2).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:24")
        assertEquals(DateRangex.minuteUntil("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:19".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "")
        assertEquals(DateRangex.minuteUntil("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:26".toDate(Datex.yMdHm), -2).joinToString { it.format(Datex.yMdHm) }, "")

        assertEquals(DateRangex.minuteDownTo("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:19".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:21, 2018-08-06 18:20, 2018-08-06 18:19")
        assertEquals(DateRangex.minuteDownTo("2018-08-06 18:22".toDate(Datex.yMdHm), "2018-08-06 18:26".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "")
    }

    @Test
    fun testSecondIterator() {
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:18".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:16, 2018-08-06 18:22:17, 2018-08-06 18:22:18")
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:18".toDate(Datex.yMdHms), 2).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:17")
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:12".toDate(Datex.yMdHms), -2).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:13")
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:11".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "")
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:18".toDate(Datex.yMdHms), -2).joinToString { it.format(Datex.yMdHms) }, "")
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:12".toDate(Datex.yMdHms), 2).joinToString { it.format(Datex.yMdHms) }, "")

        assertEquals(DateRangex.secondUntil("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:19".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:16, 2018-08-06 18:22:17, 2018-08-06 18:22:18")
        assertEquals(DateRangex.secondUntil("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:19".toDate(Datex.yMdHms), 2).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:17")
        assertEquals(DateRangex.secondUntil("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:12".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "")
        assertEquals(DateRangex.secondUntil("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:19".toDate(Datex.yMdHms), -2).joinToString { it.format(Datex.yMdHms) }, "")

        assertEquals(DateRangex.secondDownTo("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:12".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:14, 2018-08-06 18:22:13, 2018-08-06 18:22:12")
        assertEquals(DateRangex.secondDownTo("2018-08-06 18:22:15".toDate(Datex.yMdHms), "2018-08-06 18:22:19".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "")
    }

    @Test
    fun testMillisecondIterator() {
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 667, 2018-08-06 18:22:15 668, 2018-08-06 18:22:15 669")
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS), 2).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 668")
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 663".toDate(Datex.yMdHmsS), -2).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 664")
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 662".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "")
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS), -2).joinToString { it.format(Datex.yMdHmsS) }, "")
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 663".toDate(Datex.yMdHmsS), 2).joinToString { it.format(Datex.yMdHmsS) }, "")

        assertEquals(DateRangex.millisecondUntil("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 670".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 667, 2018-08-06 18:22:15 668, 2018-08-06 18:22:15 669")
        assertEquals(DateRangex.millisecondUntil("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 670".toDate(Datex.yMdHmsS), 2).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 668")
        assertEquals(DateRangex.millisecondUntil("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 663".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "")
        assertEquals(DateRangex.millisecondUntil("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 670".toDate(Datex.yMdHmsS), -2).joinToString { it.format(Datex.yMdHmsS) }, "")

        assertEquals(DateRangex.millisecondDownTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 663".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 665, 2018-08-06 18:22:15 664, 2018-08-06 18:22:15 663")
        assertEquals(DateRangex.millisecondDownTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 670".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "")
    }

    @Test
    @Throws(ParseException::class)
    fun testContains() {
        assertTrue(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)).contains("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS)))
        assertTrue(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)).contains("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)))
        assertTrue(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)).contains("2018-08-06 18:22:15 667".toDate(Datex.yMdHmsS)))
        assertTrue(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)).contains("2018-08-06 18:22:15 668".toDate(Datex.yMdHmsS)))

        assertFalse(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)).contains("2018-08-06 18:22:15 665".toDate(Datex.yMdHmsS)))
        assertFalse(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)).contains("2018-08-06 18:22:15 670".toDate(Datex.yMdHmsS)))
    }

    @Test
    @Throws(ParseException::class)
    fun testEmpty() {
        assertTrue(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 665".toDate(Datex.yMdHmsS)).isEmpty)
        assertFalse(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)).isEmpty)
        assertFalse(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS), "2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS)).isEmpty)
    }
}
