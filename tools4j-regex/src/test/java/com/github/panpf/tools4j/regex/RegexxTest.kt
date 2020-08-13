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
        Assert.assertTrue(Regexx.matches("8.8.8.8", Regexx.IPV4))
        Assert.assertFalse(Regexx.matches("11:11:e:1EEE:11:11:200.200.200.200", Regexx.IPV4))
        Assert.assertFalse(Regexx.matches("5e:0:0:0:0:0:5668:eeee", Regexx.IPV4))
        Assert.assertFalse(Regexx.matches("e:ee:5:e::0.0.0.254", Regexx.IPV4))
        Assert.assertFalse(Regexx.matches("::120.0.0.1", Regexx.IPV4))
        Assert.assertFalse(Regexx.matches("ee:ee::11.11.11.125", Regexx.IPV4))
        Assert.assertFalse(Regexx.matches("天天向上", Regexx.IPV4))
        Assert.assertTrue(Regexx.matches("11:11:e:1EEE:11:11:200.200.200.200", Regexx.IPV6))
        Assert.assertTrue(Regexx.matches("5e:0:0:0:0:0:5668:eeee", Regexx.IPV6))
        Assert.assertTrue(Regexx.matches("8.8.8.8", Regexx.IPV6))
        Assert.assertTrue(Regexx.matches("e:ee:5:e::0.0.0.254", Regexx.IPV6))
        Assert.assertTrue(Regexx.matches("::120.0.0.1", Regexx.IPV6))
        Assert.assertTrue(Regexx.matches("ee:ee::11.11.11.125", Regexx.IPV6))
        Assert.assertFalse(Regexx.matches("天天向上", Regexx.IPV6))
        Assert.assertTrue(Regexx.matches("天天向上", Regexx.CHINESE))
        Assert.assertFalse(Regexx.matches("天天向上a", Regexx.CHINESE))
        Assert.assertTrue(Regexx.find("天天向上a", Regexx.CHINESE))
        Assert.assertFalse(Regexx.find("，。，", Regexx.CHINESE))
        Assert.assertFalse(Regexx.find("", Regexx.CHINESE))
        Assert.assertTrue(Regexx.matches("天天向上", Regexx.CHINESE_AND_SYMBOL))
        Assert.assertFalse(Regexx.matches("天天向上a", Regexx.CHINESE_AND_SYMBOL))
        Assert.assertTrue(Regexx.find("天天向上a", Regexx.CHINESE_AND_SYMBOL))
        Assert.assertTrue(Regexx.find("，。，", Regexx.CHINESE_AND_SYMBOL))
        Assert.assertFalse(Regexx.find("", Regexx.CHINESE_AND_SYMBOL))
        Assert.assertTrue(Regexx.find("天天向上,好好学习 ", Regexx.BLANK))
        Assert.assertTrue(Regexx.find("天天向上,好好学习\t", Regexx.BLANK))
        Assert.assertTrue(Regexx.find("天天向上,好好学习\n", Regexx.BLANK))
        Assert.assertFalse(Regexx.find("天天向上，好好学习", Regexx.BLANK))
        Assert.assertFalse(Regexx.find("", Regexx.BLANK))
        Assert.assertTrue(Regexx.find("service@gmail.com", Regexx.EMAIL))
        Assert.assertTrue(Regexx.find("test@gmail.me", Regexx.EMAIL))
        Assert.assertFalse(Regexx.find("testgmail.me", Regexx.EMAIL))
        Assert.assertFalse(Regexx.find("", Regexx.EMAIL))
        Assert.assertTrue(Regexx.find("https://google.com", Regexx.URI))
        Assert.assertTrue(Regexx.find("https://home.gmail.me", Regexx.URI))
        Assert.assertTrue(Regexx.find("custom://home.gmail.me", Regexx.URI))
        Assert.assertFalse(Regexx.find("https:/home.gmail.me", Regexx.URI))
        Assert.assertFalse(Regexx.find("", Regexx.URI))
        Assert.assertTrue(Regexx.matches("3.21", Regexx.POSITIVE_FLOAT_NUMBER))
        Assert.assertTrue(Regexx.matches("3.00", Regexx.POSITIVE_FLOAT_NUMBER))
        Assert.assertFalse(Regexx.matches("-3.00", Regexx.POSITIVE_FLOAT_NUMBER))
        Assert.assertFalse(Regexx.matches("3", Regexx.POSITIVE_FLOAT_NUMBER))
        Assert.assertTrue(Regexx.matches("-3.21", Regexx.NEGATIVE_FLOAT_NUMBER))
        Assert.assertTrue(Regexx.matches("-3.00", Regexx.NEGATIVE_FLOAT_NUMBER))
        Assert.assertFalse(Regexx.matches("3.00", Regexx.NEGATIVE_FLOAT_NUMBER))
        Assert.assertFalse(Regexx.matches("-3", Regexx.NEGATIVE_FLOAT_NUMBER))
        Assert.assertTrue(Regexx.matches("3.21", Regexx.FLOAT_NUMBER))
        Assert.assertTrue(Regexx.matches("-3.00", Regexx.FLOAT_NUMBER))
        Assert.assertFalse(Regexx.matches("-3", Regexx.FLOAT_NUMBER))
        Assert.assertTrue(Regexx.matches("3", Regexx.POSITIVE_INTEGER))
        Assert.assertFalse(Regexx.matches("-3", Regexx.POSITIVE_INTEGER))
        Assert.assertTrue(Regexx.matches("-3", Regexx.NEGATIVE_INTEGER))
        Assert.assertFalse(Regexx.matches("3", Regexx.NEGATIVE_INTEGER))
        Assert.assertTrue(Regexx.matches("-3", Regexx.INTEGER))
        Assert.assertTrue(Regexx.matches("3", Regexx.INTEGER))
        Assert.assertTrue(Regexx.matches("58:E8:76:83:A2:C7", Regexx.MAC_ADDRESS))
        Assert.assertTrue(Regexx.matches("58-E8-76-83-A2-C7", Regexx.MAC_ADDRESS))
        Assert.assertTrue(Regexx.matches("58:e8:76:83:a2:c7", Regexx.MAC_ADDRESS))
        Assert.assertTrue(Regexx.matches("58-e8-76-83-a2-c7", Regexx.MAC_ADDRESS))
        Assert.assertFalse(Regexx.matches("58/E8:76:83:A2:C7", Regexx.MAC_ADDRESS))
        Assert.assertFalse(Regexx.matches("天天向上", Regexx.MAC_ADDRESS))
    }

    @Test
    fun testMatches() {
        Assert.assertTrue(Regexx.matches(SAMPLE_IP_V4_MATCHES, Regexx.IPV4))
        Assert.assertFalse(Regexx.matches(SAMPLE_IP_V4, Regexx.IPV4))

        Assert.assertTrue(Regexx.matches(SAMPLE_IP_V4_MATCHES, REGEX_IPV4))
        Assert.assertFalse(Regexx.matches(SAMPLE_IP_V4, REGEX_IPV4))
    }

    @Test
    fun testFind() {
        Assert.assertTrue(Regexx.find(SAMPLE_IP_V4, Regexx.IPV4))
        Assert.assertTrue(Regexx.find(SAMPLE_IP_V4, Regexx.IPV4, 30))
        Assert.assertFalse(Regexx.find(SAMPLE_IP_V4, Regexx.IPV4, 31))

        Assert.assertTrue(Regexx.find(SAMPLE_IP_V4, REGEX_IPV4))
        Assert.assertTrue(Regexx.find(SAMPLE_IP_V4, REGEX_IPV4, 30))
        Assert.assertFalse(Regexx.find(SAMPLE_IP_V4, REGEX_IPV4, 31))
    }

    @Test
    fun testLookingAt() {
        Assert.assertTrue(Regexx.lookingAt("221.217.228.166\", \"cid\": \"110105\", \"cname\": \"北京市朝阳区\"};", Regexx.IPV4))
        Assert.assertFalse(Regexx.lookingAt(SAMPLE_IP_V4, Regexx.IPV4))

        Assert.assertTrue(Regexx.lookingAt("221.217.228.166\", \"cid\": \"110105\", \"cname\": \"北京市朝阳区\"};", REGEX_IPV4))
        Assert.assertFalse(Regexx.lookingAt(SAMPLE_IP_V4, REGEX_IPV4))
    }

    @Test
    fun testGetFirst() {
        Assert.assertEquals("221.217.228.166", Regexx.getFirst(SAMPLE_IP_V4, Regexx.IPV4))
        Assert.assertNull(Regexx.getFirst("好好学习", Regexx.IPV4))

        Assert.assertEquals("221.217.228.166", Regexx.getFirst(SAMPLE_IP_V4, REGEX_IPV4))
        Assert.assertNull(Regexx.getFirst("好好学习", REGEX_IPV4))
    }

    @Test
    fun testGetAll() {
        Assert.assertArrayEquals(arrayOf("221.217.228.166"), Regexx.getAll(SAMPLE_IP_V4, Regexx.IPV4))
        Assert.assertArrayEquals(arrayOfNulls<String>(0), Regexx.getAll("好好学习", Regexx.IPV4))
        Assert.assertArrayEquals(arrayOf("221.217.228.166", "221.217.228.167", "221.217.228.168"), Regexx.getAll(SAMPLE_IP_V4_MULTI, Regexx.IPV4))

        Assert.assertArrayEquals(arrayOf("221.217.228.166"), Regexx.getAll(SAMPLE_IP_V4, REGEX_IPV4))
        Assert.assertArrayEquals(arrayOfNulls<String>(0), Regexx.getAll("好好学习", REGEX_IPV4))
        Assert.assertArrayEquals(arrayOf("221.217.228.166", "221.217.228.167", "221.217.228.168"), Regexx.getAll(SAMPLE_IP_V4_MULTI, REGEX_IPV4))
    }

    @Test
    fun testGroupFirst() {
        var group = Regexx.firstGroup(SAMPLE_IP_V4, Regexx.IPV4)
        Assert.assertNotNull(group)
        Assert.assertEquals(28, group!!.start.toLong())
        Assert.assertEquals("221.217.228.166", group.content)
        Assert.assertEquals(43, group.end.toLong())

        group = Regexx.firstGroup(SAMPLE_IP_V4, REGEX_IPV4)
        Assert.assertNotNull(group)
        Assert.assertEquals(28, group!!.start.toLong())
        Assert.assertEquals("221.217.228.166", group.content)
        Assert.assertEquals(43, group.end.toLong())
    }

    @Test
    fun testGroupAll() {
        var groups = Regexx.allGroup(SAMPLE_IP_V4_MULTI, Regexx.IPV4)
        Assert.assertNotNull(groups)
        Assert.assertEquals(28, groups[0].start.toLong())
        Assert.assertEquals("221.217.228.166", groups[0].content)
        Assert.assertEquals(43, groups[0].end.toLong())
        Assert.assertEquals(55, groups[1].start.toLong())
        Assert.assertEquals("221.217.228.167", groups[1].content)
        Assert.assertEquals(70, groups[1].end.toLong())
        Assert.assertEquals(82, groups[2].start.toLong())
        Assert.assertEquals("221.217.228.168", groups[2].content)
        Assert.assertEquals(97, groups[2].end.toLong())

        groups = Regexx.allGroup(SAMPLE_IP_V4_MULTI, REGEX_IPV4)
        Assert.assertNotNull(groups)
        Assert.assertEquals(28, groups[0].start.toLong())
        Assert.assertEquals("221.217.228.166", groups[0].content)
        Assert.assertEquals(43, groups[0].end.toLong())
        Assert.assertEquals(55, groups[1].start.toLong())
        Assert.assertEquals("221.217.228.167", groups[1].content)
        Assert.assertEquals(70, groups[1].end.toLong())
        Assert.assertEquals(82, groups[2].start.toLong())
        Assert.assertEquals("221.217.228.168", groups[2].content)
        Assert.assertEquals(97, groups[2].end.toLong())
    }

    @Test
    fun testReplaceFirst() {
        Assert.assertEquals(SAMPLE_IP_V4_REPLACE, Regexx.replaceFirst(SAMPLE_IP_V4, Regexx.IPV4, "天天向上"))
        Assert.assertEquals("好好学习", Regexx.replaceFirst("好好学习", Regexx.IPV4, "天天向上"))

        Assert.assertEquals(SAMPLE_IP_V4_REPLACE, Regexx.replaceFirst(SAMPLE_IP_V4, REGEX_IPV4, "天天向上"))
        Assert.assertEquals("好好学习", Regexx.replaceFirst("好好学习", REGEX_IPV4, "天天向上"))
    }

    @Test
    fun testReplaceAll() {
        Assert.assertEquals(SAMPLE_IP_V4_MULTI_REPLACE, Regexx.replaceAll(SAMPLE_IP_V4_MULTI, Regexx.IPV4, "天天向上"))
        Assert.assertEquals("好好学习", Regexx.replaceAll("好好学习", Regexx.IPV4, "天天向上"))

        Assert.assertEquals(SAMPLE_IP_V4_MULTI_REPLACE, Regexx.replaceAll(SAMPLE_IP_V4_MULTI, REGEX_IPV4, "天天向上"))
        Assert.assertEquals("好好学习", Regexx.replaceAll("好好学习", REGEX_IPV4, "天天向上"))
    }
}