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

package com.github.panpf.tools4j.ranges.ktx

import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class RangexTest {

    // todo 测试 reversed 和 step

    @Test
    fun testByteRange() {
        Assert.assertEquals(1.toByte().rangeTo(10.toByte(), 4).count().toLong(), 3)
        Assert.assertEquals(10.toByte().rangeTo(1.toByte(), (-4)).count().toLong(), 3)
        Assert.assertEquals(1.toByte().until(11.toByte(), 4).count().toLong(), 3)
    }

    @Test
    fun testShortRange() {
        Assert.assertEquals(1.toShort().rangeTo(10.toShort(), 4).count().toLong(), 3)
        Assert.assertEquals(10.toShort().rangeTo(1.toShort(), (-4)).count().toLong(), 3)
        Assert.assertEquals(1.toShort().until(11.toShort(), 4).count().toLong(), 3)
    }

    @Test
    fun testIntRange() {
        Assert.assertEquals(1.rangeTo(10, 4).count().toLong(), 3)
        Assert.assertEquals(10.rangeTo(1, (-4)).count().toLong(), 3)
        Assert.assertEquals(1.until(11, 4).count().toLong(), 3)
    }

    @Test
    fun testLongRange() {
        Assert.assertEquals(1L.rangeTo(10L, 4).count().toLong(), 3)
        Assert.assertEquals(10L.rangeTo(1L, (-4)).count().toLong(), 3)
        Assert.assertEquals(1L.until(11L, 4).count().toLong(), 3)
    }

    @Test
    fun testCharRange() {
        Assert.assertEquals(1.toChar().rangeTo(10.toChar(), 4).count().toLong(), 3)
        Assert.assertEquals(10.toChar().rangeTo(1.toChar(), (-4)).count().toLong(), 3)
        Assert.assertEquals(1.toChar().until(11.toChar(), 4).count().toLong(), 3)
    }

    @Test
    fun testYearIterator() {
        Assert.assertEquals("2018".toDateY().yearRangeTo("2021".toDateY()).joinToString { it.formatY() }, "2018, 2019, 2020, 2021")
        Assert.assertEquals("2018".toDateY().yearRangeTo("2021".toDateY(), 2).joinToString { it.formatY() }, "2018, 2020")
        Assert.assertEquals("2018".toDateY().yearUntil("2022".toDateY()).joinToString { it.formatY() }, "2018, 2019, 2020, 2021")
        Assert.assertEquals("2018".toDateY().yearUntil("2022".toDateY(), 2).joinToString { it.formatY() }, "2018, 2020")
        Assert.assertEquals("2018".toDateY().yearDownTo("2015".toDateY()).joinToString { it.formatY() }, "2018, 2017, 2016, 2015")
        Assert.assertEquals("2018".toDateY().yearRangeTo("2015".toDateY(), -2).joinToString { it.formatY() }, "2018, 2016")
        Assert.assertEquals("2018".toDateY().yearRangeTo("2015".toDateY()).joinToString { it.formatY() }, "")
        Assert.assertEquals("2018".toDateY().yearRangeTo("2021".toDateY(), -2).joinToString { it.formatY() }, "")
        Assert.assertEquals("2018".toDateY().yearUntil("2015".toDateY()).joinToString { it.formatY() }, "")
        Assert.assertEquals("2018".toDateY().yearUntil("2022".toDateY(), -2).joinToString { it.formatY() }, "")
        Assert.assertEquals("2018".toDateY().yearDownTo("2021".toDateY()).joinToString { it.formatY() }, "")
        Assert.assertEquals("2018".toDateY().yearRangeTo("2015".toDateY(), 2).joinToString { it.formatY() }, "")
    }

    @Test
    fun testMonthIterator() {
        Assert.assertEquals("2018-08".toDateYM().monthRangeTo("2018-11".toDateYM()).joinToString { it.formatYM() }, "2018-08, 2018-09, 2018-10, 2018-11")
        Assert.assertEquals("2018-08".toDateYM().monthRangeTo("2018-11".toDateYM(), 2).joinToString { it.formatYM() }, "2018-08, 2018-10")
        Assert.assertEquals("2018-08".toDateYM().monthUntil("2018-12".toDateYM()).joinToString { it.formatYM() }, "2018-08, 2018-09, 2018-10, 2018-11")
        Assert.assertEquals("2018-08".toDateYM().monthUntil("2018-12".toDateYM(), 2).joinToString { it.formatYM() }, "2018-08, 2018-10")
        Assert.assertEquals("2018-08".toDateYM().monthDownTo("2018-05".toDateYM()).joinToString { it.formatYM() }, "2018-08, 2018-07, 2018-06, 2018-05")
        Assert.assertEquals("2018-08".toDateYM().monthRangeTo("2018-05".toDateYM(), -2).joinToString { it.formatYM() }, "2018-08, 2018-06")
        Assert.assertEquals("2018-08".toDateYM().monthRangeTo("2018-05".toDateYM()).joinToString { it.formatYM() }, "")
        Assert.assertEquals("2018-08".toDateYM().monthRangeTo("2018-11".toDateYM(), -2).joinToString { it.formatYM() }, "")
        Assert.assertEquals("2018-08".toDateYM().monthUntil("2018-04".toDateYM()).joinToString { it.formatYM() }, "")
        Assert.assertEquals("2018-08".toDateYM().monthUntil("2018-12".toDateYM(), -2).joinToString { it.formatYM() }, "")
        Assert.assertEquals("2018-08".toDateYM().monthDownTo("2018-11".toDateYM()).joinToString { it.formatYM() }, "")
        Assert.assertEquals("2018-08".toDateYM().monthRangeTo("2018-05".toDateYM(), 2).joinToString { it.formatYM() }, "")
    }

    @Test
    fun testDayIterator() {
        Assert.assertEquals("2018-08-06".toDateYMD().dayRangeTo("2018-08-09".toDateYMD()).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-07, 2018-08-08, 2018-08-09")
        Assert.assertEquals("2018-08-06".toDateYMD().dayRangeTo("2018-08-09".toDateYMD(), 2).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-08")
        Assert.assertEquals("2018-08-06".toDateYMD().dayUntil("2018-08-10".toDateYMD()).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-07, 2018-08-08, 2018-08-09")
        Assert.assertEquals("2018-08-06".toDateYMD().dayUntil("2018-08-10".toDateYMD(), 2).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-08")
        Assert.assertEquals("2018-08-06".toDateYMD().dayDownTo("2018-08-03".toDateYMD()).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-05, 2018-08-04, 2018-08-03")
        Assert.assertEquals("2018-08-06".toDateYMD().dayRangeTo("2018-08-03".toDateYMD(), -2).joinToString { it.formatYMD() }, "2018-08-06, 2018-08-04")
        Assert.assertEquals("2018-08-06".toDateYMD().dayRangeTo("2018-08-02".toDateYMD()).joinToString { it.formatYMD() }, "")
        Assert.assertEquals("2018-08-06".toDateYMD().dayRangeTo("2018-08-09".toDateYMD(), -2).joinToString { it.formatYMD() }, "")
        Assert.assertEquals("2018-08-06".toDateYMD().dayUntil("2018-08-03".toDateYMD()).joinToString { it.formatYMD() }, "")
        Assert.assertEquals("2018-08-06".toDateYMD().dayUntil("2018-08-10".toDateYMD(), -2).joinToString { it.formatYMD() }, "")
        Assert.assertEquals("2018-08-06".toDateYMD().dayDownTo("2018-08-10".toDateYMD()).joinToString { it.formatYMD() }, "")
        Assert.assertEquals("2018-08-06".toDateYMD().dayRangeTo("2018-08-03".toDateYMD(), 2).joinToString { it.formatYMD() }, "")
    }

    @Test
    fun testHourIterator() {
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourRangeTo("2018-08-06 21".toDateYMDH()).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 19, 2018-08-06 20, 2018-08-06 21")
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourRangeTo("2018-08-06 21".toDateYMDH(), 2).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 20")
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourUntil("2018-08-06 22".toDateYMDH()).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 19, 2018-08-06 20, 2018-08-06 21")
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourUntil("2018-08-06 22".toDateYMDH(), 2).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 20")
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourDownTo("2018-08-06 15".toDateYMDH()).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 17, 2018-08-06 16, 2018-08-06 15")
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourRangeTo("2018-08-06 15".toDateYMDH(), -2).joinToString { it.formatYMDH() }, "2018-08-06 18, 2018-08-06 16")
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourRangeTo("2018-08-06 14".toDateYMDH()).joinToString { it.formatYMDH() }, "")
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourRangeTo("2018-08-06 21".toDateYMDH(), -2).joinToString { it.formatYMDH() }, "")
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourUntil("2018-08-06 15".toDateYMDH()).joinToString { it.formatYMDH() }, "")
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourUntil("2018-08-06 22".toDateYMDH(), -2).joinToString { it.formatYMDH() }, "")
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourDownTo("2018-08-06 22".toDateYMDH()).joinToString { it.formatYMDH() }, "")
        Assert.assertEquals("2018-08-06 18".toDateYMDH().hourRangeTo("2018-08-06 15".toDateYMDH(), 2).joinToString { it.formatYMDH() }, "")
    }

    @Test
    fun testMinuteIterator() {
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteRangeTo("2018-08-06 18:25".toDateYMDHM()).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:23, 2018-08-06 18:24, 2018-08-06 18:25")
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteRangeTo("2018-08-06 18:25".toDateYMDHM(), 2).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:24")
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteUntil("2018-08-06 18:26".toDateYMDHM()).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:23, 2018-08-06 18:24, 2018-08-06 18:25")
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteUntil("2018-08-06 18:26".toDateYMDHM(), 2).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:24")
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteDownTo("2018-08-06 18:19".toDateYMDHM()).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:21, 2018-08-06 18:20, 2018-08-06 18:19")
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteRangeTo("2018-08-06 18:19".toDateYMDHM(), -2).joinToString { it.formatYMDHM() }, "2018-08-06 18:22, 2018-08-06 18:20")
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteRangeTo("2018-08-06 18:18".toDateYMDHM()).joinToString { it.formatYMDHM() }, "")
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteRangeTo("2018-08-06 18:25".toDateYMDHM(), -2).joinToString { it.formatYMDHM() }, "")
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteUntil("2018-08-06 18:19".toDateYMDHM()).joinToString { it.formatYMDHM() }, "")
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteUntil("2018-08-06 18:26".toDateYMDHM(), -2).joinToString { it.formatYMDHM() }, "")
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteDownTo("2018-08-06 18:26".toDateYMDHM()).joinToString { it.formatYMDHM() }, "")
        Assert.assertEquals("2018-08-06 18:22".toDateYMDHM().minuteRangeTo("2018-08-06 18:19".toDateYMDHM(), 2).joinToString { it.formatYMDHM() }, "")
    }

    @Test
    fun testSecondIterator() {
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondRangeTo("2018-08-06 18:22:18".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:16, 2018-08-06 18:22:17, 2018-08-06 18:22:18")
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondRangeTo("2018-08-06 18:22:18".toDateYMDHMS(), 2).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:17")
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondUntil("2018-08-06 18:22:19".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:16, 2018-08-06 18:22:17, 2018-08-06 18:22:18")
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondUntil("2018-08-06 18:22:19".toDateYMDHMS(), 2).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:17")
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondDownTo("2018-08-06 18:22:12".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:14, 2018-08-06 18:22:13, 2018-08-06 18:22:12")
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondRangeTo("2018-08-06 18:22:12".toDateYMDHMS(), -2).joinToString { it.formatYMDHMS() }, "2018-08-06 18:22:15, 2018-08-06 18:22:13")
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondRangeTo("2018-08-06 18:22:11".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "")
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondRangeTo("2018-08-06 18:22:18".toDateYMDHMS(), -2).joinToString { it.formatYMDHMS() }, "")
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondUntil("2018-08-06 18:22:12".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "")
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondUntil("2018-08-06 18:22:19".toDateYMDHMS(), -2).joinToString { it.formatYMDHMS() }, "")
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondDownTo("2018-08-06 18:22:19".toDateYMDHMS()).joinToString { it.formatYMDHMS() }, "")
        Assert.assertEquals("2018-08-06 18:22:15".toDateYMDHMS().secondRangeTo("2018-08-06 18:22:12".toDateYMDHMS(), 2).joinToString { it.formatYMDHMS() }, "")
    }

    @Test
    fun testMillisecondIterator() {
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 669".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 667, 2018-08-06 18:22:15 668, 2018-08-06 18:22:15 669")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 669".toDateYMDHMSM(), 2).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 668")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondUntil("2018-08-06 18:22:15 670".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 667, 2018-08-06 18:22:15 668, 2018-08-06 18:22:15 669")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondUntil("2018-08-06 18:22:15 670".toDateYMDHMSM(), 2).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 668")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondDownTo("2018-08-06 18:22:15 663".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 665, 2018-08-06 18:22:15 664, 2018-08-06 18:22:15 663")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 663".toDateYMDHMSM(), -2).joinToString { it.formatYMDHMSM() }, "2018-08-06 18:22:15 666, 2018-08-06 18:22:15 664")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 662".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 669".toDateYMDHMSM(), -2).joinToString { it.formatYMDHMSM() }, "")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondUntil("2018-08-06 18:22:15 663".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondUntil("2018-08-06 18:22:15 670".toDateYMDHMSM(), -2).joinToString { it.formatYMDHMSM() }, "")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondDownTo("2018-08-06 18:22:15 670".toDateYMDHMSM()).joinToString { it.formatYMDHMSM() }, "")
        Assert.assertEquals("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 663".toDateYMDHMSM(), 2).joinToString { it.formatYMDHMSM() }, "")
    }

    @Test
    fun testContains() {
        Assert.assertTrue("2018-08-06 18:22:15 666".toDateYMDHMSM() in "2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 669".toDateYMDHMSM()))
        Assert.assertTrue("2018-08-06 18:22:15 669".toDateYMDHMSM() in "2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 669".toDateYMDHMSM()))
        Assert.assertTrue("2018-08-06 18:22:15 667".toDateYMDHMSM() in "2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 669".toDateYMDHMSM()))
        Assert.assertTrue("2018-08-06 18:22:15 668".toDateYMDHMSM() in "2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 669".toDateYMDHMSM()))

        Assert.assertFalse("2018-08-06 18:22:15 665".toDateYMDHMSM() in "2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 669".toDateYMDHMSM()))
        Assert.assertFalse("2018-08-06 18:22:15 670".toDateYMDHMSM() in "2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 669".toDateYMDHMSM()))
    }

    @Test
    fun testEmpty() {
        Assert.assertTrue("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 665".toDateYMDHMSM()).isEmpty)
        Assert.assertFalse("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 669".toDateYMDHMSM()).isEmpty)
        Assert.assertFalse("2018-08-06 18:22:15 666".toDateYMDHMSM().millisecondRangeTo("2018-08-06 18:22:15 666".toDateYMDHMSM()).isEmpty)
    }
}

fun String.toDateY(): Date {
    return SimpleDateFormat("yyyy").parse(this)
}

fun String.toDateYM(): Date {
    return SimpleDateFormat("yyyy-MM").parse(this)
}

fun String.toDateYMD(): Date {
    return SimpleDateFormat("yyyy-MM-dd").parse(this)
}

fun String.toDateYMDH(): Date {
    return SimpleDateFormat("yyyy-MM-dd HH").parse(this)
}

fun String.toDateYMDHM(): Date {
    return SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this)
}

fun String.toDateYMDHMS(): Date {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this)
}

fun String.toDateYMDHMSM(): Date {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").parse(this)
}

fun Date.formatY(): String {
    return SimpleDateFormat("yyyy").format(this)
}

fun Date.formatYM(): String {
    return SimpleDateFormat("yyyy-MM").format(this)
}

fun Date.formatYMD(): String {
    return SimpleDateFormat("yyyy-MM-dd").format(this)
}

fun Date.formatYMDH(): String {
    return SimpleDateFormat("yyyy-MM-dd HH").format(this)
}

fun Date.formatYMDHM(): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm").format(this)
}

fun Date.formatYMDHMS(): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this)
}

fun Date.formatYMDHMSM(): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(this)
}