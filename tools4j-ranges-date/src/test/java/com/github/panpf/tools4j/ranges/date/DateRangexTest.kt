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

package com.github.panpf.tools4j.ranges.date

import com.github.panpf.tools4j.date.ktx.*
import org.junit.Assert.*
import org.junit.Test
import java.text.ParseException

class DateRangexTest {

    // todo 测试 reversed 和 step

    @Test
    fun testYearIterator() {
        assertEquals(DateRangex.yearRangeTo("2018".toDateY(), "2021".toDateY()).joinToString { it.formatY() }, "2018, 2019, 2020, 2021")
        assertEquals(DateRangex.yearRangeTo("2018".toDateY(), "2021".toDateY(), 2).joinToString { it.formatY() }, "2018, 2020")
        assertEquals(DateRangex.yearRangeTo("2018".toDateY(), "2015".toDateY(), -2).joinToString { it.formatY() }, "2018, 2016")
        assertEquals(DateRangex.yearRangeTo("2018".toDateY(), "2015".toDateY()).joinToString { it.formatY() }, "")
        assertEquals(DateRangex.yearRangeTo("2018".toDateY(), "2021".toDateY(), -2).joinToString { it.formatY() }, "")
        assertEquals(DateRangex.yearRangeTo("2018".toDateY(), "2015".toDateY(), 2).joinToString { it.formatY() }, "")

        assertEquals(DateRangex.yearUntil("2018".toDateY(), "2022".toDateY()).joinToString { it.formatY() }, "2018, 2019, 2020, 2021")
        assertEquals(DateRangex.yearUntil("2018".toDateY(), "2022".toDateY(), 2).joinToString { it.formatY() }, "2018, 2020")
        assertEquals(DateRangex.yearUntil("2018".toDateY(), "2015".toDateY()).joinToString { it.formatY() }, "")
        assertEquals(DateRangex.yearUntil("2018".toDateY(), "2022".toDateY(), -2).joinToString { it.formatY() }, "")

        assertEquals(DateRangex.yearDownTo("2018".toDateY(), "2015".toDateY()).joinToString { it.formatY() }, "2018, 2017, 2016, 2015")
        assertEquals(DateRangex.yearDownTo("2018".toDateY(), "2021".toDateY()).joinToString { it.formatY() }, "")
    }

    @Test
    fun testMonthIterator() {
        assertEquals(DateRangex.monthRangeTo("2018-08".toDateYM(), "2018-11".toDateYM()).joinToString { it.formatYM() }, "2018-08, 2018-09, 2018-10, 2018-11")
        assertEquals(DateRangex.monthRangeTo("2018-08".toDateYM(), "2018-11".toDateYM(), 2).joinToString { it.formatYM() }, "2018-08, 2018-10")
        assertEquals(DateRangex.monthRangeTo("2018-08".toDateYM(), "2018-05".toDateYM(), -2).joinToString { it.formatYM() }, "2018-08, 2018-06")
        assertEquals(DateRangex.monthRangeTo("2018-08".toDateYM(), "2018-05".toDateYM()).joinToString { it.formatYM() }, "")
        assertEquals(DateRangex.monthRangeTo("2018-08".toDateYM(), "2018-11".toDateYM(), -2).joinToString { it.formatYM() }, "")
        assertEquals(DateRangex.monthRangeTo("2018-08".toDateYM(), "2018-05".toDateYM(), 2).joinToString { it.formatYM() }, "")

        assertEquals(DateRangex.monthUntil("2018-08".toDateYM(), "2018-12".toDateYM()).joinToString { it.formatYM() }, "2018-08, 2018-09, 2018-10, 2018-11")
        assertEquals(DateRangex.monthUntil("2018-08".toDateYM(), "2018-12".toDateYM(), 2).joinToString { it.formatYM() }, "2018-08, 2018-10")
        assertEquals(DateRangex.monthUntil("2018-08".toDateYM(), "2018-04".toDateYM()).joinToString { it.formatYM() }, "")
        assertEquals(DateRangex.monthUntil("2018-08".toDateYM(), "2018-12".toDateYM(), -2).joinToString { it.formatYM() }, "")

        assertEquals(DateRangex.monthDownTo("2018-08".toDateYM(), "2018-05".toDateYM()).joinToString { it.formatYM() }, "2018-08, 2018-07, 2018-06, 2018-05")
        assertEquals(DateRangex.monthDownTo("2018-08".toDateYM(), "2018-11".toDateYM()).joinToString { it.formatYM() }, "")
    }

    @Test
    fun testDayIterator() {
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-09".toDateYMD()).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-07, 2018-08-08, 2018-08-09")
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-09".toDateYMD(), 2).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-08")
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-03".toDateYMD(), -2).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-04")
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-02".toDateYMD()).joinToString { it.formatYMD() }, "")
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-09".toDateYMD(), -2).joinToString { it.formatYMD() }, "")
        assertEquals(DateRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-03".toDateYMD(), 2).joinToString { it.formatYMD() }, "")

        assertEquals(DateRangex.dayUntil("2018-08-06".toDateYMD(), "2018-08-10".toDateYMD()).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-07, 2018-08-08, 2018-08-09")
        assertEquals(DateRangex.dayUntil("2018-08-06".toDateYMD(), "2018-08-10".toDateYMD(), 2).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-08")
        assertEquals(DateRangex.dayUntil("2018-08-06".toDateYMD(), "2018-08-03".toDateYMD()).joinToString { it.formatYMD() }, "")
        assertEquals(DateRangex.dayUntil("2018-08-06".toDateYMD(), "2018-08-10".toDateYMD(), -2).joinToString { it.formatYMD() }, "")

        assertEquals(DateRangex.dayDownTo("2018-08-06".toDateYMD(), "2018-08-03".toDateYMD()).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-05, 2018-08-04, 2018-08-03")
        assertEquals(DateRangex.dayDownTo("2018-08-06".toDateYMD(), "2018-08-10".toDateYMD()).joinToString { it.formatYMD() }, "")
    }

    @Test
    fun testHourIterator() {
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 21".toDateYMDH()).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 19, 2018-08-06 20, 2018-08-06 21")
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 21".toDateYMDH(), 2).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 20")
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 15".toDateYMDH(), -2).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 16")
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 14".toDateYMDH()).joinToString { it.formatYMDH() }, "")
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 21".toDateYMDH(), -2).joinToString { it.formatYMDH() }, "")
        assertEquals(DateRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 15".toDateYMDH(), 2).joinToString { it.formatYMDH() }, "")

        assertEquals(DateRangex.hourUntil("2018-08-06 18".toDateYMDH(), "2018-08-06 22".toDateYMDH()).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 19, 2018-08-06 20, 2018-08-06 21")
        assertEquals(DateRangex.hourUntil("2018-08-06 18".toDateYMDH(), "2018-08-06 22".toDateYMDH(), 2).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 20")
        assertEquals(DateRangex.hourUntil("2018-08-06 18".toDateYMDH(), "2018-08-06 15".toDateYMDH()).joinToString { it.formatYMDH() }, "")
        assertEquals(DateRangex.hourUntil("2018-08-06 18".toDateYMDH(), "2018-08-06 22".toDateYMDH(), -2).joinToString { it.formatYMDH() }, "")

        assertEquals(DateRangex.hourDownTo("2018-08-06 18".toDateYMDH(), "2018-08-06 15".toDateYMDH()).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 17, 2018-08-06 16, 2018-08-06 15")
        assertEquals(DateRangex.hourDownTo("2018-08-06 18".toDateYMDH(), "2018-08-06 22".toDateYMDH()).joinToString { it.formatYMDH() }, "")
    }

    @Test
    fun testMinuteIterator() {
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:25".toDateYMDHM()).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:23, 2018-08-06 18:24, 2018-08-06 18:25")
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:25".toDateYMDHM(), 2).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:24")
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:19".toDateYMDHM(), -2).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:20")
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:18".toDateYMDHM()).joinToString { it.formatYMDHM() }, "")
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:25".toDateYMDHM(), -2).joinToString { it.formatYMDHM() }, "")
        assertEquals(DateRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:19".toDateYMDHM(), 2).joinToString { it.formatYMDHM() }, "")

        assertEquals(DateRangex.minuteUntil("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:26".toDateYMDHM()).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:23, 2018-08-06 18:24, 2018-08-06 18:25")
        assertEquals(DateRangex.minuteUntil("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:26".toDateYMDHM(), 2).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:24")
        assertEquals(DateRangex.minuteUntil("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:19".toDateYMDHM()).joinToString { it.formatYMDHM() }, "")
        assertEquals(DateRangex.minuteUntil("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:26".toDateYMDHM(), -2).joinToString { it.formatYMDHM() }, "")

        assertEquals(DateRangex.minuteDownTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:19".toDateYMDHM()).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:21, 2018-08-06 18:20, 2018-08-06 18:19")
        assertEquals(DateRangex.minuteDownTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:26".toDateYMDHM()).joinToString { it.formatYMDHM() }, "")
    }

    @Test
    fun testSecondIterator() {
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:18".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:16, 2018-08-06 18:22:17, 2018-08-06 18:22:18")
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:18".toDateYMDHMS(), 2).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:17")
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:12".toDateYMDHMS(), -2).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:13")
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:11".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "")
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:18".toDateYMDHMS(), -2).joinToString { it.formatYMDHMS() }, "")
        assertEquals(DateRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:12".toDateYMDHMS(), 2).joinToString { it.formatYMDHMS() }, "")

        assertEquals(DateRangex.secondUntil("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:19".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:16, 2018-08-06 18:22:17, 2018-08-06 18:22:18")
        assertEquals(DateRangex.secondUntil("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:19".toDateYMDHMS(), 2).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:17")
        assertEquals(DateRangex.secondUntil("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:12".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "")
        assertEquals(DateRangex.secondUntil("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:19".toDateYMDHMS(), -2).joinToString { it.formatYMDHMS() }, "")

        assertEquals(DateRangex.secondDownTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:12".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:14, 2018-08-06 18:22:13, 2018-08-06 18:22:12")
        assertEquals(DateRangex.secondDownTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:19".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "")
    }

    @Test
    fun testMillisecondIterator() {
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 667, 2018-08-06 18:22:15 668, 2018-08-06 18:22:15 669")
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM(), 2).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 668")
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 663".toDateYMDHMSM(), -2).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 664")
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 662".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "")
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM(), -2).joinToString { it.formatYMDHMSM() }, "")
        assertEquals(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 663".toDateYMDHMSM(), 2).joinToString { it.formatYMDHMSM() }, "")

        assertEquals(DateRangex.millisecondUntil("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 670".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 667, 2018-08-06 18:22:15 668, 2018-08-06 18:22:15 669")
        assertEquals(DateRangex.millisecondUntil("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 670".toDateYMDHMSM(), 2).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 668")
        assertEquals(DateRangex.millisecondUntil("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 663".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "")
        assertEquals(DateRangex.millisecondUntil("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 670".toDateYMDHMSM(), -2).joinToString { it.formatYMDHMSM() }, "")

        assertEquals(DateRangex.millisecondDownTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 663".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 665, 2018-08-06 18:22:15 664, 2018-08-06 18:22:15 663")
        assertEquals(DateRangex.millisecondDownTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 670".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "")
    }

    @Test
    @Throws(ParseException::class)
    fun testContains() {
        assertTrue(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 666".toDateYMDHMSM()))
        assertTrue(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 669".toDateYMDHMSM()))
        assertTrue(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 667".toDateYMDHMSM()))
        assertTrue(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 668".toDateYMDHMSM()))

        assertFalse(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 665".toDateYMDHMSM()))
        assertFalse(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 670".toDateYMDHMSM()))
    }

    @Test
    @Throws(ParseException::class)
    fun testEmpty() {
        assertTrue(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 665".toDateYMDHMSM()).isEmpty)
        assertFalse(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).isEmpty)
        assertFalse(DateRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 666".toDateYMDHMSM()).isEmpty)
    }
}
