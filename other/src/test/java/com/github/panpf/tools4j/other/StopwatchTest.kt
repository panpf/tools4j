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

package com.github.panpf.tools4j.other

import org.junit.Assert
import org.junit.Test

class StopwatchTest {

    @Test
    @Throws(InterruptedException::class)
    fun testStartTime() {
        /*
         * 允许有 100 毫秒的误差
         */
        val time = System.currentTimeMillis()
        Thread.sleep(1000L)
        val stopwatch = Stopwatch()
        Assert.assertTrue(stopwatch.startTime in time + 1000L..time + 1100L)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testCountLap() {
        /*
         * 允许有 10 毫秒的误差，暂停次数越多误差越大
         */
        val stopwatch = Stopwatch()
        Thread.sleep(1000L)
        val firstCountLap = stopwatch.countLap()
        Assert.assertTrue(firstCountLap.time in (stopwatch.startTime + 1000L)..(stopwatch.startTime + 2000L))
        Assert.assertTrue(firstCountLap.distanceLastTime in 1000L..1010L)
        Assert.assertTrue(firstCountLap.distanceStartTime in 1000L..1010L)
        Thread.sleep(2000L)
        val secondCountLap = stopwatch.countLap()
        Assert.assertTrue(secondCountLap.time in (stopwatch.startTime + 3000L)..(stopwatch.startTime + 4000L))
        Assert.assertTrue(secondCountLap.distanceLastTime in 2000L..3000L)
        Assert.assertTrue(secondCountLap.distanceStartTime in 3000L..4000L)
        Thread.sleep(3000L)
        val thirdCountLap = stopwatch.countLap()
        Assert.assertTrue(thirdCountLap.time in (stopwatch.startTime + 6000L)..(stopwatch.startTime + 7000L))
        Assert.assertTrue(thirdCountLap.distanceLastTime in 3000L..3030L)
        Assert.assertTrue(thirdCountLap.distanceStartTime in 6000L..7000L)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testEnd() {
        val stopwatch = Stopwatch()
        Assert.assertFalse(stopwatch.isEnded)
        stopwatch.countLap()
        Thread.sleep(1000L)
        stopwatch.countLap()
        Thread.sleep(1000L)
        stopwatch.countLap()
        stopwatch.end()
        Assert.assertTrue(stopwatch.isEnded)
        try {
            stopwatch.countLap()
            Assert.fail()
        } catch (ignored: Exception) {
        }
        stopwatch.end()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testHistory() {
        val stopwatch = Stopwatch()
        stopwatch.countLap()
        Thread.sleep(1000L)
        stopwatch.countLap()
        Thread.sleep(1000L)
        stopwatch.countLap()
        Assert.assertNull(stopwatch.historyList)
        val stopwatch2 = Stopwatch(true)
        stopwatch2.countLap()
        Thread.sleep(1000L)
        stopwatch2.countLap()
        Thread.sleep(1000L)
        stopwatch2.countLap()
        Assert.assertEquals(stopwatch2.historyList?.count() ?: 0, 3)
    }
}