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

package com.github.panpf.tools4j.ranges.date.ktx

import com.github.panpf.tools4j.date.Datex
import com.github.panpf.tools4j.date.ktx.format
import com.github.panpf.tools4j.date.ktx.toDate
import org.junit.Assert
import org.junit.Test

class DateRangexTest {

    @Test
    fun testYearIterator() {
        Assert.assertEquals("2018".toDate(Datex.y).yearRangeTo("2021".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "2018, 2019, 2020, 2021")
        Assert.assertEquals("2018".toDate(Datex.y).yearRangeTo("2021".toDate(Datex.y), 2).joinToString { it.format(Datex.y) }, "2018, 2020")
        Assert.assertEquals("2018".toDate(Datex.y).yearUntil("2022".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "2018, 2019, 2020, 2021")
        Assert.assertEquals("2018".toDate(Datex.y).yearUntil("2022".toDate(Datex.y), 2).joinToString { it.format(Datex.y) }, "2018, 2020")
        Assert.assertEquals("2018".toDate(Datex.y).yearDownTo("2015".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "2018, 2017, 2016, 2015")
        Assert.assertEquals("2018".toDate(Datex.y).yearRangeTo("2015".toDate(Datex.y), -2).joinToString { it.format(Datex.y) }, "2018, 2016")
        Assert.assertEquals("2018".toDate(Datex.y).yearRangeTo("2015".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "")
        Assert.assertEquals("2018".toDate(Datex.y).yearRangeTo("2021".toDate(Datex.y), -2).joinToString { it.format(Datex.y) }, "")
        Assert.assertEquals("2018".toDate(Datex.y).yearUntil("2015".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "")
        Assert.assertEquals("2018".toDate(Datex.y).yearUntil("2022".toDate(Datex.y), -2).joinToString { it.format(Datex.y) }, "")
        Assert.assertEquals("2018".toDate(Datex.y).yearDownTo("2021".toDate(Datex.y)).joinToString { it.format(Datex.y) }, "")
        Assert.assertEquals("2018".toDate(Datex.y).yearRangeTo("2015".toDate(Datex.y), 2).joinToString { it.format(Datex.y) }, "")
    }

    @Test
    fun testMonthIterator() {
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthRangeTo("2018-11".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "2018-08, 2018-09, 2018-10, 2018-11")
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthRangeTo("2018-11".toDate(Datex.yM), 2).joinToString { it.format(Datex.yM) }, "2018-08, 2018-10")
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthUntil("2018-12".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "2018-08, 2018-09, 2018-10, 2018-11")
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthUntil("2018-12".toDate(Datex.yM), 2).joinToString { it.format(Datex.yM) }, "2018-08, 2018-10")
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthDownTo("2018-05".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "2018-08, 2018-07, 2018-06, 2018-05")
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthRangeTo("2018-05".toDate(Datex.yM), -2).joinToString { it.format(Datex.yM) }, "2018-08, 2018-06")
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthRangeTo("2018-05".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "")
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthRangeTo("2018-11".toDate(Datex.yM), -2).joinToString { it.format(Datex.yM) }, "")
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthUntil("2018-04".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "")
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthUntil("2018-12".toDate(Datex.yM), -2).joinToString { it.format(Datex.yM) }, "")
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthDownTo("2018-11".toDate(Datex.yM)).joinToString { it.format(Datex.yM) }, "")
        Assert.assertEquals("2018-08".toDate(Datex.yM).monthRangeTo("2018-05".toDate(Datex.yM), 2).joinToString { it.format(Datex.yM) }, "")
    }

    @Test
    fun testDayOfMonthIterator() {
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthRangeTo("2018-08-09".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-07, 2018-08-08, 2018-08-09")
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthRangeTo("2018-08-09".toDate(Datex.yMd), 2).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-08")
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthUntil("2018-08-10".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-07, 2018-08-08, 2018-08-09")
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthUntil("2018-08-10".toDate(Datex.yMd), 2).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-08")
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthDownTo("2018-08-03".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-05, 2018-08-04, 2018-08-03")
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthRangeTo("2018-08-03".toDate(Datex.yMd), -2).joinToString { it.format(Datex.yMd) }, "2018-08-06, 2018-08-04")
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthRangeTo("2018-08-02".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "")
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthRangeTo("2018-08-09".toDate(Datex.yMd), -2).joinToString { it.format(Datex.yMd) }, "")
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthUntil("2018-08-03".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "")
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthUntil("2018-08-10".toDate(Datex.yMd), -2).joinToString { it.format(Datex.yMd) }, "")
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthDownTo("2018-08-10".toDate(Datex.yMd)).joinToString { it.format(Datex.yMd) }, "")
        Assert.assertEquals("2018-08-06".toDate(Datex.yMd).dayOfMonthRangeTo("2018-08-03".toDate(Datex.yMd), 2).joinToString { it.format(Datex.yMd) }, "")
    }

    @Test
    fun testHourOfDayIterator() {
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayRangeTo("2018-08-06 21".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 19, 2018-08-06 20, 2018-08-06 21")
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayRangeTo("2018-08-06 21".toDate(Datex.yMdH), 2).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 20")
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayUntil("2018-08-06 22".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 19, 2018-08-06 20, 2018-08-06 21")
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayUntil("2018-08-06 22".toDate(Datex.yMdH), 2).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 20")
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayDownTo("2018-08-06 15".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 17, 2018-08-06 16, 2018-08-06 15")
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayRangeTo("2018-08-06 15".toDate(Datex.yMdH), -2).joinToString { it.format(Datex.yMdH) }, "2018-08-06 18, 2018-08-06 16")
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayRangeTo("2018-08-06 14".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "")
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayRangeTo("2018-08-06 21".toDate(Datex.yMdH), -2).joinToString { it.format(Datex.yMdH) }, "")
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayUntil("2018-08-06 15".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "")
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayUntil("2018-08-06 22".toDate(Datex.yMdH), -2).joinToString { it.format(Datex.yMdH) }, "")
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayDownTo("2018-08-06 22".toDate(Datex.yMdH)).joinToString { it.format(Datex.yMdH) }, "")
        Assert.assertEquals("2018-08-06 18".toDate(Datex.yMdH).hourOfDayRangeTo("2018-08-06 15".toDate(Datex.yMdH), 2).joinToString { it.format(Datex.yMdH) }, "")
    }

    @Test
    fun testMinuteIterator() {
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteRangeTo("2018-08-06 18:25".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:23, 2018-08-06 18:24, 2018-08-06 18:25")
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteRangeTo("2018-08-06 18:25".toDate(Datex.yMdHm), 2).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:24")
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteUntil("2018-08-06 18:26".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:23, 2018-08-06 18:24, 2018-08-06 18:25")
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteUntil("2018-08-06 18:26".toDate(Datex.yMdHm), 2).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:24")
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteDownTo("2018-08-06 18:19".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:21, 2018-08-06 18:20, 2018-08-06 18:19")
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteRangeTo("2018-08-06 18:19".toDate(Datex.yMdHm), -2).joinToString { it.format(Datex.yMdHm) }, "2018-08-06 18:22, 2018-08-06 18:20")
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteRangeTo("2018-08-06 18:18".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "")
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteRangeTo("2018-08-06 18:25".toDate(Datex.yMdHm), -2).joinToString { it.format(Datex.yMdHm) }, "")
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteUntil("2018-08-06 18:19".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "")
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteUntil("2018-08-06 18:26".toDate(Datex.yMdHm), -2).joinToString { it.format(Datex.yMdHm) }, "")
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteDownTo("2018-08-06 18:26".toDate(Datex.yMdHm)).joinToString { it.format(Datex.yMdHm) }, "")
        Assert.assertEquals("2018-08-06 18:22".toDate(Datex.yMdHm).minuteRangeTo("2018-08-06 18:19".toDate(Datex.yMdHm), 2).joinToString { it.format(Datex.yMdHm) }, "")
    }

    @Test
    fun testSecondIterator() {
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondRangeTo("2018-08-06 18:22:18".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:16, 2018-08-06 18:22:17, 2018-08-06 18:22:18")
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondRangeTo("2018-08-06 18:22:18".toDate(Datex.yMdHms), 2).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:17")
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondUntil("2018-08-06 18:22:19".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:16, 2018-08-06 18:22:17, 2018-08-06 18:22:18")
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondUntil("2018-08-06 18:22:19".toDate(Datex.yMdHms), 2).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:17")
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondDownTo("2018-08-06 18:22:12".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:14, 2018-08-06 18:22:13, 2018-08-06 18:22:12")
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondRangeTo("2018-08-06 18:22:12".toDate(Datex.yMdHms), -2).joinToString { it.format(Datex.yMdHms) }, "2018-08-06 18:22:15, 2018-08-06 18:22:13")
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondRangeTo("2018-08-06 18:22:11".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "")
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondRangeTo("2018-08-06 18:22:18".toDate(Datex.yMdHms), -2).joinToString { it.format(Datex.yMdHms) }, "")
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondUntil("2018-08-06 18:22:12".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "")
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondUntil("2018-08-06 18:22:19".toDate(Datex.yMdHms), -2).joinToString { it.format(Datex.yMdHms) }, "")
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondDownTo("2018-08-06 18:22:19".toDate(Datex.yMdHms)).joinToString { it.format(Datex.yMdHms) }, "")
        Assert.assertEquals("2018-08-06 18:22:15".toDate(Datex.yMdHms).secondRangeTo("2018-08-06 18:22:12".toDate(Datex.yMdHms), 2).joinToString { it.format(Datex.yMdHms) }, "")
    }

    @Test
    fun testMillisecondIterator() {
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 667, 2018-08-06 18:22:15 668, 2018-08-06 18:22:15 669")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS), 2).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 668")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondUntil("2018-08-06 18:22:15 670".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 667, 2018-08-06 18:22:15 668, 2018-08-06 18:22:15 669")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondUntil("2018-08-06 18:22:15 670".toDate(Datex.yMdHmsS), 2).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 668")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondDownTo("2018-08-06 18:22:15 663".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 665, 2018-08-06 18:22:15 664, 2018-08-06 18:22:15 663")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 663".toDate(Datex.yMdHmsS), -2).joinToString { it.format(Datex.yMdHmsS) }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 664")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 662".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS), -2).joinToString { it.format(Datex.yMdHmsS) }, "")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondUntil("2018-08-06 18:22:15 663".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondUntil("2018-08-06 18:22:15 670".toDate(Datex.yMdHmsS), -2).joinToString { it.format(Datex.yMdHmsS) }, "")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondDownTo("2018-08-06 18:22:15 670".toDate(Datex.yMdHmsS)).joinToString { it.format(Datex.yMdHmsS) }, "")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 663".toDate(Datex.yMdHmsS), 2).joinToString { it.format(Datex.yMdHmsS) }, "")
    }

    @Test
    fun testContains() {
        Assert.assertTrue("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS) in "2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)))
        Assert.assertTrue("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS) in "2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)))
        Assert.assertTrue("2018-08-06 18:22:15 667".toDate(Datex.yMdHmsS) in "2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)))
        Assert.assertTrue("2018-08-06 18:22:15 668".toDate(Datex.yMdHmsS) in "2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)))

        Assert.assertFalse("2018-08-06 18:22:15 665".toDate(Datex.yMdHmsS) in "2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)))
        Assert.assertFalse("2018-08-06 18:22:15 670".toDate(Datex.yMdHmsS) in "2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)))
    }

    @Test
    fun testEmpty() {
        Assert.assertTrue("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 665".toDate(Datex.yMdHmsS)).isEmpty)
        Assert.assertFalse("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 669".toDate(Datex.yMdHmsS)).isEmpty)
        Assert.assertFalse("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS).millisecondRangeTo("2018-08-06 18:22:15 666".toDate(Datex.yMdHmsS)).isEmpty)
    }

    @Test
    fun testReversed() {
        Assert.assertEquals(
                "2022, 2021, 2020",
                "2020".toDate(Datex.y).yearRangeTo("2022".toDate(Datex.y)).reversed().joinToString { it.format(Datex.y) }
        )
        Assert.assertEquals(
                "2022-10, 2022-09, 2022-08",
                "2022-08".toDate(Datex.yM).monthRangeTo("2022-10".toDate(Datex.yM)).reversed().joinToString { it.format(Datex.yM) }
        )
        Assert.assertEquals(
                "2022-10-07, 2022-10-06, 2022-10-05",
                "2022-10-05".toDate(Datex.yMd).dayOfMonthRangeTo("2022-10-07".toDate(Datex.yMd)).reversed().joinToString { it.format(Datex.yMd) }
        )
        Assert.assertEquals(
                "2022-10-07 13, 2022-10-07 12, 2022-10-07 11",
                "2022-10-07 11".toDate(Datex.yMdH).hourOfDayRangeTo("2022-10-07 13".toDate(Datex.yMdH)).reversed().joinToString { it.format(Datex.yMdH) }
        )
        Assert.assertEquals(
                "2022-10-07 13:34, 2022-10-07 13:33, 2022-10-07 13:32",
                "2022-10-07 13:32".toDate(Datex.yMdHm).minuteRangeTo("2022-10-07 13:34".toDate(Datex.yMdHm)).reversed().joinToString { it.format(Datex.yMdHm) }
        )
        Assert.assertEquals(
                "2022-10-07 13:34:12, 2022-10-07 13:34:11, 2022-10-07 13:34:10",
                "2022-10-07 13:34:10".toDate(Datex.yMdHms).secondRangeTo("2022-10-07 13:34:12".toDate(Datex.yMdHms)).reversed().joinToString { it.format(Datex.yMdHms) }
        )
        Assert.assertEquals(
                "2022-10-07 13:34:12 555, 2022-10-07 13:34:12 554, 2022-10-07 13:34:12 553",
                "2022-10-07 13:34:12 553".toDate(Datex.yMdHmsS).millisecondRangeTo("2022-10-07 13:34:12 555".toDate(Datex.yMdHmsS)).reversed().joinToString { it.format(Datex.yMdHmsS) }
        )
    }

    @Test
    fun testStep() {
        Assert.assertEquals(
                "2022, 2020",
                "2022".toDate(Datex.y).yearDownTo("2020".toDate(Datex.y)).step(2).joinToString { it.format(Datex.y) }
        )
        Assert.assertEquals(
                "2022-10, 2022-08",
                "2022-10".toDate(Datex.yM).monthDownTo("2022-08".toDate(Datex.yM)).step(2).joinToString { it.format(Datex.yM) }
        )
        Assert.assertEquals(
                "2022-10-07, 2022-10-05",
                "2022-10-07".toDate(Datex.yMd).dayOfMonthDownTo("2022-10-05".toDate(Datex.yMd)).step(2).joinToString { it.format(Datex.yMd) }
        )
        Assert.assertEquals(
                "2022-10-07 13, 2022-10-07 11",
                "2022-10-07 13".toDate(Datex.yMdH).hourOfDayDownTo("2022-10-07 11".toDate(Datex.yMdH)).step(2).joinToString { it.format(Datex.yMdH) }
        )
        Assert.assertEquals(
                "2022-10-07 13:34, 2022-10-07 13:32",
                "2022-10-07 13:34".toDate(Datex.yMdHm).minuteDownTo("2022-10-07 13:32".toDate(Datex.yMdHm)).step(2).joinToString { it.format(Datex.yMdHm) }
        )
        Assert.assertEquals(
                "2022-10-07 13:34:12, 2022-10-07 13:34:10",
                "2022-10-07 13:34:12".toDate(Datex.yMdHms).secondDownTo("2022-10-07 13:34:10".toDate(Datex.yMdHms)).step(2).joinToString { it.format(Datex.yMdHms) }
        )
        Assert.assertEquals(
                "2022-10-07 13:34:12 555, 2022-10-07 13:34:12 553",
                "2022-10-07 13:34:12 555".toDate(Datex.yMdHmsS).millisecondDownTo("2022-10-07 13:34:12 553".toDate(Datex.yMdHmsS)).step(2).joinToString { it.format(Datex.yMdHmsS) }
        )
    }
}