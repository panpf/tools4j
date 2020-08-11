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
package com.github.panpf.tools4j.regex

import org.junit.Assert
import org.junit.Test

class RegexxTest {

    companion object {
        private const val REGEX_IPV4 = "((?:(?:25[0-5]|2[0-4]\\d|(?:1\\d{2}|[1-9]?\\d))\\.){3}(?:25[0-5]|2[0-4]\\d|(?:1\\d{2}|[1-9]?\\d)))"
        private const val SAMPLE_IP_V4_MATCHES = "221.217.228.166"
        private const val SAMPLE_IP_V4 = "var returnCitySN = {\"cip\": \"221.217.228.166\", \"cid\": \"110105\", \"cname\": \"北京市朝阳区\"};"
        private const val SAMPLE_IP_V4_MULTI = "var returnCitySN = {\"cip\": \"221.217.228.166\", \"cip2\": \"221.217.228.167\", \"cip3\": \"221.217.228.168\", \"cid\": \"110105\", \"cname\": \"北京市朝阳区\"};"
        private const val SAMPLE_IP_V4_REPLACE = "var returnCitySN = {\"cip\": \"天天向上\", \"cid\": \"110105\", \"cname\": \"北京市朝阳区\"};"
        private const val SAMPLE_IP_V4_MULTI_REPLACE = "var returnCitySN = {\"cip\": \"天天向上\", \"cip2\": \"天天向上\", \"cip3\": \"天天向上\", \"cid\": \"110105\", \"cname\": \"北京市朝阳区\"};"
    }

    @Test
    fun testRegex() {
        Assert.assertTrue(Regexx.matches(Regexx.IPV4, "8.8.8.8"))
        Assert.assertFalse(Regexx.matches(Regexx.IPV4, "11:11:e:1EEE:11:11:200.200.200.200"))
        Assert.assertFalse(Regexx.matches(Regexx.IPV4, "5e:0:0:0:0:0:5668:eeee"))
        Assert.assertFalse(Regexx.matches(Regexx.IPV4, "e:ee:5:e::0.0.0.254"))
        Assert.assertFalse(Regexx.matches(Regexx.IPV4, "::120.0.0.1"))
        Assert.assertFalse(Regexx.matches(Regexx.IPV4, "ee:ee::11.11.11.125"))
        Assert.assertFalse(Regexx.matches(Regexx.IPV4, "天天向上"))
        Assert.assertTrue(Regexx.matches(Regexx.IPV6, "11:11:e:1EEE:11:11:200.200.200.200"))
        Assert.assertTrue(Regexx.matches(Regexx.IPV6, "5e:0:0:0:0:0:5668:eeee"))
        Assert.assertTrue(Regexx.matches(Regexx.IPV6, "8.8.8.8"))
        Assert.assertTrue(Regexx.matches(Regexx.IPV6, "e:ee:5:e::0.0.0.254"))
        Assert.assertTrue(Regexx.matches(Regexx.IPV6, "::120.0.0.1"))
        Assert.assertTrue(Regexx.matches(Regexx.IPV6, "ee:ee::11.11.11.125"))
        Assert.assertFalse(Regexx.matches(Regexx.IPV6, "天天向上"))
        Assert.assertTrue(Regexx.matches(Regexx.CHINESE, "天天向上"))
        Assert.assertFalse(Regexx.matches(Regexx.CHINESE, "天天向上a"))
        Assert.assertTrue(Regexx.find(Regexx.CHINESE, "天天向上a"))
        Assert.assertFalse(Regexx.find(Regexx.CHINESE, "，。，"))
        Assert.assertFalse(Regexx.find(Regexx.CHINESE, ""))
        Assert.assertTrue(Regexx.matches(Regexx.CHINESE_AND_SYMBOL, "天天向上"))
        Assert.assertFalse(Regexx.matches(Regexx.CHINESE_AND_SYMBOL, "天天向上a"))
        Assert.assertTrue(Regexx.find(Regexx.CHINESE_AND_SYMBOL, "天天向上a"))
        Assert.assertTrue(Regexx.find(Regexx.CHINESE_AND_SYMBOL, "，。，"))
        Assert.assertFalse(Regexx.find(Regexx.CHINESE_AND_SYMBOL, ""))
        Assert.assertTrue(Regexx.find(Regexx.BLANK, "天天向上,好好学习 "))
        Assert.assertTrue(Regexx.find(Regexx.BLANK, "天天向上,好好学习\t"))
        Assert.assertTrue(Regexx.find(Regexx.BLANK, "天天向上,好好学习\n"))
        Assert.assertFalse(Regexx.find(Regexx.BLANK, "天天向上，好好学习"))
        Assert.assertFalse(Regexx.find(Regexx.BLANK, ""))
        Assert.assertTrue(Regexx.find(Regexx.EMAIL, "service@gmail.com"))
        Assert.assertTrue(Regexx.find(Regexx.EMAIL, "sky@panpf.me"))
        Assert.assertFalse(Regexx.find(Regexx.EMAIL, "skypanpf.me"))
        Assert.assertFalse(Regexx.find(Regexx.EMAIL, ""))
        Assert.assertTrue(Regexx.find(Regexx.URI, "https://google.com"))
        Assert.assertTrue(Regexx.find(Regexx.URI, "https://home.panpf.me"))
        Assert.assertTrue(Regexx.find(Regexx.URI, "custom://home.panpf.me"))
        Assert.assertFalse(Regexx.find(Regexx.URI, "https:/home.panpf.me"))
        Assert.assertFalse(Regexx.find(Regexx.URI, ""))
        Assert.assertTrue(Regexx.matches(Regexx.POSITIVE_FLOAT_NUMBER, "3.21"))
        Assert.assertTrue(Regexx.matches(Regexx.POSITIVE_FLOAT_NUMBER, "3.00"))
        Assert.assertFalse(Regexx.matches(Regexx.POSITIVE_FLOAT_NUMBER, "-3.00"))
        Assert.assertFalse(Regexx.matches(Regexx.POSITIVE_FLOAT_NUMBER, "3"))
        Assert.assertTrue(Regexx.matches(Regexx.NEGATIVE_FLOAT_NUMBER, "-3.21"))
        Assert.assertTrue(Regexx.matches(Regexx.NEGATIVE_FLOAT_NUMBER, "-3.00"))
        Assert.assertFalse(Regexx.matches(Regexx.NEGATIVE_FLOAT_NUMBER, "3.00"))
        Assert.assertFalse(Regexx.matches(Regexx.NEGATIVE_FLOAT_NUMBER, "-3"))
        Assert.assertTrue(Regexx.matches(Regexx.FLOAT_NUMBER, "3.21"))
        Assert.assertTrue(Regexx.matches(Regexx.FLOAT_NUMBER, "-3.00"))
        Assert.assertFalse(Regexx.matches(Regexx.FLOAT_NUMBER, "-3"))
        Assert.assertTrue(Regexx.matches(Regexx.POSITIVE_INTEGER, "3"))
        Assert.assertFalse(Regexx.matches(Regexx.POSITIVE_INTEGER, "-3"))
        Assert.assertTrue(Regexx.matches(Regexx.NEGATIVE_INTEGER, "-3"))
        Assert.assertFalse(Regexx.matches(Regexx.NEGATIVE_INTEGER, "3"))
        Assert.assertTrue(Regexx.matches(Regexx.INTEGER, "-3"))
        Assert.assertTrue(Regexx.matches(Regexx.INTEGER, "3"))
    }

    @Test
    fun testMatches() {
        Assert.assertTrue(Regexx.matches(Regexx.IPV4, SAMPLE_IP_V4_MATCHES))
        Assert.assertFalse(Regexx.matches(Regexx.IPV4, SAMPLE_IP_V4))
        Assert.assertTrue(Regexx.matches(REGEX_IPV4, SAMPLE_IP_V4_MATCHES))
        Assert.assertFalse(Regexx.matches(REGEX_IPV4, SAMPLE_IP_V4))
    }

    @Test
    fun testFind() {
        Assert.assertTrue(Regexx.find(Regexx.IPV4, SAMPLE_IP_V4))
        Assert.assertTrue(Regexx.find(Regexx.IPV4, SAMPLE_IP_V4, 30))
        Assert.assertFalse(Regexx.find(Regexx.IPV4, SAMPLE_IP_V4, 31))
        Assert.assertTrue(Regexx.find(REGEX_IPV4, SAMPLE_IP_V4))
        Assert.assertTrue(Regexx.find(REGEX_IPV4, SAMPLE_IP_V4, 30))
        Assert.assertFalse(Regexx.find(REGEX_IPV4, SAMPLE_IP_V4, 31))
    }

    @Test
    fun testLookingAt() {
        Assert.assertTrue(Regexx.lookingAt(Regexx.IPV4, "221.217.228.166\", \"cid\": \"110105\", \"cname\": \"北京市朝阳区\"};"))
        Assert.assertFalse(Regexx.lookingAt(Regexx.IPV4, SAMPLE_IP_V4))
        Assert.assertTrue(Regexx.lookingAt(REGEX_IPV4, "221.217.228.166\", \"cid\": \"110105\", \"cname\": \"北京市朝阳区\"};"))
        Assert.assertFalse(Regexx.lookingAt(REGEX_IPV4, SAMPLE_IP_V4))
    }

    @Test
    fun testGetFirst() {
        Assert.assertEquals(Regexx.getFirst(Regexx.IPV4, SAMPLE_IP_V4), "221.217.228.166")
        Assert.assertNull(Regexx.getFirst(Regexx.IPV4, "好好学习"))
        Assert.assertEquals(Regexx.getFirst(REGEX_IPV4, SAMPLE_IP_V4), "221.217.228.166")
        Assert.assertNull(Regexx.getFirst(REGEX_IPV4, "好好学习"))
    }

    @Test
    fun testGetAll() {
        Assert.assertArrayEquals(Regexx.getAll(Regexx.IPV4, SAMPLE_IP_V4), arrayOf("221.217.228.166"))
        Assert.assertArrayEquals(Regexx.getAll(Regexx.IPV4, "好好学习"), arrayOfNulls<String>(0))
        Assert.assertArrayEquals(Regexx.getAll(Regexx.IPV4, SAMPLE_IP_V4_MULTI), arrayOf("221.217.228.166", "221.217.228.167", "221.217.228.168"))
        Assert.assertArrayEquals(Regexx.getAll(REGEX_IPV4, SAMPLE_IP_V4), arrayOf("221.217.228.166"))
        Assert.assertArrayEquals(Regexx.getAll(REGEX_IPV4, "好好学习"), arrayOfNulls<String>(0))
        Assert.assertArrayEquals(Regexx.getAll(REGEX_IPV4, SAMPLE_IP_V4_MULTI), arrayOf("221.217.228.166", "221.217.228.167", "221.217.228.168"))
    }

    @Test
    fun testGroupFirst() {
        var group = Regexx.firstGroup(Regexx.IPV4, SAMPLE_IP_V4)
        Assert.assertNotNull(group)
        Assert.assertEquals(group!!.start.toLong(), 28)
        Assert.assertEquals(group.content, "221.217.228.166")
        Assert.assertEquals(group.end.toLong(), 43)
        group = Regexx.firstGroup(REGEX_IPV4, SAMPLE_IP_V4)
        Assert.assertNotNull(group)
        Assert.assertEquals(group!!.start.toLong(), 28)
        Assert.assertEquals(group.content, "221.217.228.166")
        Assert.assertEquals(group.end.toLong(), 43)
    }

    @Test
    fun testGroupAll() {
        var groups = Regexx.allGroup(Regexx.IPV4, SAMPLE_IP_V4_MULTI)
        Assert.assertNotNull(groups)
        Assert.assertEquals(groups[0].start.toLong(), 28)
        Assert.assertEquals(groups[0].content, "221.217.228.166")
        Assert.assertEquals(groups[0].end.toLong(), 43)
        Assert.assertEquals(groups[1].start.toLong(), 55)
        Assert.assertEquals(groups[1].content, "221.217.228.167")
        Assert.assertEquals(groups[1].end.toLong(), 70)
        Assert.assertEquals(groups[2].start.toLong(), 82)
        Assert.assertEquals(groups[2].content, "221.217.228.168")
        Assert.assertEquals(groups[2].end.toLong(), 97)
        groups = Regexx.allGroup(REGEX_IPV4, SAMPLE_IP_V4_MULTI)
        Assert.assertNotNull(groups)
        Assert.assertEquals(groups[0].start.toLong(), 28)
        Assert.assertEquals(groups[0].content, "221.217.228.166")
        Assert.assertEquals(groups[0].end.toLong(), 43)
        Assert.assertEquals(groups[1].start.toLong(), 55)
        Assert.assertEquals(groups[1].content, "221.217.228.167")
        Assert.assertEquals(groups[1].end.toLong(), 70)
        Assert.assertEquals(groups[2].start.toLong(), 82)
        Assert.assertEquals(groups[2].content, "221.217.228.168")
        Assert.assertEquals(groups[2].end.toLong(), 97)
    }

    @Test
    fun testReplaceFirst() {
        Assert.assertEquals(Regexx.replaceFirst(Regexx.IPV4, SAMPLE_IP_V4, "天天向上"), SAMPLE_IP_V4_REPLACE)
        Assert.assertEquals(Regexx.replaceFirst(Regexx.IPV4, "好好学习", "天天向上"), "好好学习")
        Assert.assertEquals(Regexx.replaceFirst(REGEX_IPV4, SAMPLE_IP_V4, "天天向上"), SAMPLE_IP_V4_REPLACE)
        Assert.assertEquals(Regexx.replaceFirst(REGEX_IPV4, "好好学习", "天天向上"), "好好学习")
    }

    @Test
    fun testReplaceAll() {
        Assert.assertEquals(Regexx.replaceAll(Regexx.IPV4, SAMPLE_IP_V4_MULTI, "天天向上"), SAMPLE_IP_V4_MULTI_REPLACE)
        Assert.assertEquals(Regexx.replaceAll(Regexx.IPV4, "好好学习", "天天向上"), "好好学习")
        Assert.assertEquals(Regexx.replaceAll(REGEX_IPV4, SAMPLE_IP_V4_MULTI, "天天向上"), SAMPLE_IP_V4_MULTI_REPLACE)
        Assert.assertEquals(Regexx.replaceAll(REGEX_IPV4, "好好学习", "天天向上"), "好好学习")
    }
}