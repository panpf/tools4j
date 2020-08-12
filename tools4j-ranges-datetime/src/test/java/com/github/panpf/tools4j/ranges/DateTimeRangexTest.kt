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

package com.github.panpf.tools4j.ranges

import com.github.panpf.tools4j.datetime.ktx.*
import org.junit.Assert.*
import org.junit.Test
import java.text.ParseException

class DateTimeRangexTest {

    // todo 测试 reversed 和 step

    @Test
    fun testYearIterator() {
        assertEquals(DateTimeRangex.yearRangeTo("2018".toDateY(), "2021".toDateY()).joinToString { it.formatY() }, "2018, 2019, 2020, 2021")
        assertEquals(DateTimeRangex.yearRangeTo("2018".toDateY(), "2021".toDateY(), 2).joinToString { it.formatY() }, "2018, 2020")
        assertEquals(DateTimeRangex.yearRangeTo("2018".toDateY(), "2015".toDateY(), -2).joinToString { it.formatY() }, "2018, 2016")
        assertEquals(DateTimeRangex.yearRangeTo("2018".toDateY(), "2015".toDateY()).joinToString { it.formatY() }, "")
        assertEquals(DateTimeRangex.yearRangeTo("2018".toDateY(), "2021".toDateY(), -2).joinToString { it.formatY() }, "")
        assertEquals(DateTimeRangex.yearRangeTo("2018".toDateY(), "2015".toDateY(), 2).joinToString { it.formatY() }, "")

        assertEquals(DateTimeRangex.yearUntil("2018".toDateY(), "2022".toDateY()).joinToString { it.formatY() }, "2018, 2019, 2020, 2021")
        assertEquals(DateTimeRangex.yearUntil("2018".toDateY(), "2022".toDateY(), 2).joinToString { it.formatY() }, "2018, 2020")
        assertEquals(DateTimeRangex.yearUntil("2018".toDateY(), "2015".toDateY()).joinToString { it.formatY() }, "")
        assertEquals(DateTimeRangex.yearUntil("2018".toDateY(), "2022".toDateY(), -2).joinToString { it.formatY() }, "")

        assertEquals(DateTimeRangex.yearDownTo("2018".toDateY(), "2015".toDateY()).joinToString { it.formatY() }, "2018, 2017, 2016, 2015")
        assertEquals(DateTimeRangex.yearDownTo("2018".toDateY(), "2021".toDateY()).joinToString { it.formatY() }, "")
    }

    @Test
    fun testMonthIterator() {
        assertEquals(DateTimeRangex.monthRangeTo("2018-08".toDateYM(), "2018-11".toDateYM()).joinToString { it.formatYM() }, "2018-08, 2018-09, 2018-10, 2018-11")
        assertEquals(DateTimeRangex.monthRangeTo("2018-08".toDateYM(), "2018-11".toDateYM(), 2).joinToString { it.formatYM() }, "2018-08, 2018-10")
        assertEquals(DateTimeRangex.monthRangeTo("2018-08".toDateYM(), "2018-05".toDateYM(), -2).joinToString { it.formatYM() }, "2018-08, 2018-06")
        assertEquals(DateTimeRangex.monthRangeTo("2018-08".toDateYM(), "2018-05".toDateYM()).joinToString { it.formatYM() }, "")
        assertEquals(DateTimeRangex.monthRangeTo("2018-08".toDateYM(), "2018-11".toDateYM(), -2).joinToString { it.formatYM() }, "")
        assertEquals(DateTimeRangex.monthRangeTo("2018-08".toDateYM(), "2018-05".toDateYM(), 2).joinToString { it.formatYM() }, "")

        assertEquals(DateTimeRangex.monthUntil("2018-08".toDateYM(), "2018-12".toDateYM()).joinToString { it.formatYM() }, "2018-08, 2018-09, 2018-10, 2018-11")
        assertEquals(DateTimeRangex.monthUntil("2018-08".toDateYM(), "2018-12".toDateYM(), 2).joinToString { it.formatYM() }, "2018-08, 2018-10")
        assertEquals(DateTimeRangex.monthUntil("2018-08".toDateYM(), "2018-04".toDateYM()).joinToString { it.formatYM() }, "")
        assertEquals(DateTimeRangex.monthUntil("2018-08".toDateYM(), "2018-12".toDateYM(), -2).joinToString { it.formatYM() }, "")

        assertEquals(DateTimeRangex.monthDownTo("2018-08".toDateYM(), "2018-05".toDateYM()).joinToString { it.formatYM() }, "2018-08, 2018-07, 2018-06, 2018-05")
        assertEquals(DateTimeRangex.monthDownTo("2018-08".toDateYM(), "2018-11".toDateYM()).joinToString { it.formatYM() }, "")
    }

    @Test
    fun testDayIterator() {
        assertEquals(DateTimeRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-09".toDateYMD()).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-07, 2018-08-08, 2018-08-09")
        assertEquals(DateTimeRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-09".toDateYMD(), 2).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-08")
        assertEquals(DateTimeRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-03".toDateYMD(), -2).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-04")
        assertEquals(DateTimeRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-02".toDateYMD()).joinToString { it.formatYMD() }, "")
        assertEquals(DateTimeRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-09".toDateYMD(), -2).joinToString { it.formatYMD() }, "")
        assertEquals(DateTimeRangex.dayRangeTo("2018-08-06".toDateYMD(), "2018-08-03".toDateYMD(), 2).joinToString { it.formatYMD() }, "")

        assertEquals(DateTimeRangex.dayUntil("2018-08-06".toDateYMD(), "2018-08-10".toDateYMD()).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-07, 2018-08-08, 2018-08-09")
        assertEquals(DateTimeRangex.dayUntil("2018-08-06".toDateYMD(), "2018-08-10".toDateYMD(), 2).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-08")
        assertEquals(DateTimeRangex.dayUntil("2018-08-06".toDateYMD(), "2018-08-03".toDateYMD()).joinToString { it.formatYMD() }, "")
        assertEquals(DateTimeRangex.dayUntil("2018-08-06".toDateYMD(), "2018-08-10".toDateYMD(), -2).joinToString { it.formatYMD() }, "")

        assertEquals(DateTimeRangex.dayDownTo("2018-08-06".toDateYMD(), "2018-08-03".toDateYMD()).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-05, 2018-08-04, 2018-08-03")
        assertEquals(DateTimeRangex.dayDownTo("2018-08-06".toDateYMD(), "2018-08-10".toDateYMD()).joinToString { it.formatYMD() }, "")
    }

    @Test
    fun testHourIterator() {
        assertEquals(DateTimeRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 21".toDateYMDH()).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 19, 2018-08-06 20, 2018-08-06 21")
        assertEquals(DateTimeRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 21".toDateYMDH(), 2).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 20")
        assertEquals(DateTimeRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 15".toDateYMDH(), -2).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 16")
        assertEquals(DateTimeRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 14".toDateYMDH()).joinToString { it.formatYMDH() }, "")
        assertEquals(DateTimeRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 21".toDateYMDH(), -2).joinToString { it.formatYMDH() }, "")
        assertEquals(DateTimeRangex.hourRangeTo("2018-08-06 18".toDateYMDH(), "2018-08-06 15".toDateYMDH(), 2).joinToString { it.formatYMDH() }, "")

        assertEquals(DateTimeRangex.hourUntil("2018-08-06 18".toDateYMDH(), "2018-08-06 22".toDateYMDH()).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 19, 2018-08-06 20, 2018-08-06 21")
        assertEquals(DateTimeRangex.hourUntil("2018-08-06 18".toDateYMDH(), "2018-08-06 22".toDateYMDH(), 2).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 20")
        assertEquals(DateTimeRangex.hourUntil("2018-08-06 18".toDateYMDH(), "2018-08-06 15".toDateYMDH()).joinToString { it.formatYMDH() }, "")
        assertEquals(DateTimeRangex.hourUntil("2018-08-06 18".toDateYMDH(), "2018-08-06 22".toDateYMDH(), -2).joinToString { it.formatYMDH() }, "")

        assertEquals(DateTimeRangex.hourDownTo("2018-08-06 18".toDateYMDH(), "2018-08-06 15".toDateYMDH()).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 17, 2018-08-06 16, 2018-08-06 15")
        assertEquals(DateTimeRangex.hourDownTo("2018-08-06 18".toDateYMDH(), "2018-08-06 22".toDateYMDH()).joinToString { it.formatYMDH() }, "")
    }

    @Test
    fun testMinuteIterator() {
        assertEquals(DateTimeRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:25".toDateYMDHM()).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:23, 2018-08-06 18:24, 2018-08-06 18:25")
        assertEquals(DateTimeRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:25".toDateYMDHM(), 2).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:24")
        assertEquals(DateTimeRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:19".toDateYMDHM(), -2).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:20")
        assertEquals(DateTimeRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:18".toDateYMDHM()).joinToString { it.formatYMDHM() }, "")
        assertEquals(DateTimeRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:25".toDateYMDHM(), -2).joinToString { it.formatYMDHM() }, "")
        assertEquals(DateTimeRangex.minuteRangeTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:19".toDateYMDHM(), 2).joinToString { it.formatYMDHM() }, "")

        assertEquals(DateTimeRangex.minuteUntil("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:26".toDateYMDHM()).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:23, 2018-08-06 18:24, 2018-08-06 18:25")
        assertEquals(DateTimeRangex.minuteUntil("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:26".toDateYMDHM(), 2).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:24")
        assertEquals(DateTimeRangex.minuteUntil("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:19".toDateYMDHM()).joinToString { it.formatYMDHM() }, "")
        assertEquals(DateTimeRangex.minuteUntil("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:26".toDateYMDHM(), -2).joinToString { it.formatYMDHM() }, "")

        assertEquals(DateTimeRangex.minuteDownTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:19".toDateYMDHM()).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:21, 2018-08-06 18:20, 2018-08-06 18:19")
        assertEquals(DateTimeRangex.minuteDownTo("2018-08-06 18:22".toDateYMDHM(), "2018-08-06 18:26".toDateYMDHM()).joinToString { it.formatYMDHM() }, "")
    }

    @Test
    fun testSecondIterator() {
        assertEquals(DateTimeRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:18".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:16, 2018-08-06 18:22:17, 2018-08-06 18:22:18")
        assertEquals(DateTimeRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:18".toDateYMDHMS(), 2).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:17")
        assertEquals(DateTimeRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:12".toDateYMDHMS(), -2).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:13")
        assertEquals(DateTimeRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:11".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "")
        assertEquals(DateTimeRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:18".toDateYMDHMS(), -2).joinToString { it.formatYMDHMS() }, "")
        assertEquals(DateTimeRangex.secondRangeTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:12".toDateYMDHMS(), 2).joinToString { it.formatYMDHMS() }, "")

        assertEquals(DateTimeRangex.secondUntil("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:19".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:16, 2018-08-06 18:22:17, 2018-08-06 18:22:18")
        assertEquals(DateTimeRangex.secondUntil("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:19".toDateYMDHMS(), 2).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:17")
        assertEquals(DateTimeRangex.secondUntil("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:12".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "")
        assertEquals(DateTimeRangex.secondUntil("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:19".toDateYMDHMS(), -2).joinToString { it.formatYMDHMS() }, "")

        assertEquals(DateTimeRangex.secondDownTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:12".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:14, 2018-08-06 18:22:13, 2018-08-06 18:22:12")
        assertEquals(DateTimeRangex.secondDownTo("2018-08-06 18:22:15".toDateYMDHMS(), "2018-08-06 18:22:19".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "")
    }

    @Test
    fun testMillisecondIterator() {
        assertEquals(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 667, 2018-08-06 18:22:15 668, 2018-08-06 18:22:15 669")
        assertEquals(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM(), 2).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 668")
        assertEquals(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 663".toDateYMDHMSM(), -2).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 664")
        assertEquals(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 662".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "")
        assertEquals(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM(), -2).joinToString { it.formatYMDHMSM() }, "")
        assertEquals(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 663".toDateYMDHMSM(), 2).joinToString { it.formatYMDHMSM() }, "")

        assertEquals(DateTimeRangex.millisecondUntil("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 670".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 667, 2018-08-06 18:22:15 668, 2018-08-06 18:22:15 669")
        assertEquals(DateTimeRangex.millisecondUntil("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 670".toDateYMDHMSM(), 2).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 668")
        assertEquals(DateTimeRangex.millisecondUntil("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 663".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "")
        assertEquals(DateTimeRangex.millisecondUntil("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 670".toDateYMDHMSM(), -2).joinToString { it.formatYMDHMSM() }, "")

        assertEquals(DateTimeRangex.millisecondDownTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 663".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 665, 2018-08-06 18:22:15 664, 2018-08-06 18:22:15 663")
        assertEquals(DateTimeRangex.millisecondDownTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 670".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "")
    }

    @Test
    @Throws(ParseException::class)
    fun testContains() {
        assertTrue(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 666".toDateYMDHMSM()))
        assertTrue(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 669".toDateYMDHMSM()))
        assertTrue(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 667".toDateYMDHMSM()))
        assertTrue(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 668".toDateYMDHMSM()))

        assertFalse(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 665".toDateYMDHMSM()))
        assertFalse(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).contains("2018-08-06 18:22:15 670".toDateYMDHMSM()))
    }

    @Test
    @Throws(ParseException::class)
    fun testEmpty() {
        assertTrue(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 665".toDateYMDHMSM()).isEmpty)
        assertFalse(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 669".toDateYMDHMSM()).isEmpty)
        assertFalse(DateTimeRangex.millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM(), "2018-08-06 18:22:15 666".toDateYMDHMSM()).isEmpty)
    }
}
