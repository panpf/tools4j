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

package com.github.panpf.tools4j.regex.ktx

import com.github.panpf.tools4j.regex.Regexx
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
    fun testMatches() {
        Assert.assertTrue(SAMPLE_IP_V4_MATCHES.regexMatches(Regexx.IPV4))
        Assert.assertFalse(SAMPLE_IP_V4.regexMatches(Regexx.IPV4))

        Assert.assertTrue(SAMPLE_IP_V4_MATCHES.regexMatches(REGEX_IPV4))
        Assert.assertFalse(SAMPLE_IP_V4.regexMatches(REGEX_IPV4))
    }

    @Test
    fun testFind() {
        Assert.assertTrue(SAMPLE_IP_V4.regexFind(Regexx.IPV4))
        Assert.assertTrue(SAMPLE_IP_V4.regexFind(Regexx.IPV4, 30))
        Assert.assertFalse(SAMPLE_IP_V4.regexFind(Regexx.IPV4, 31))

        Assert.assertTrue(SAMPLE_IP_V4.regexFind(REGEX_IPV4))
        Assert.assertTrue(SAMPLE_IP_V4.regexFind(REGEX_IPV4, 30))
        Assert.assertFalse(SAMPLE_IP_V4.regexFind(REGEX_IPV4, 31))
    }

    @Test
    fun testLookingAt() {
        Assert.assertTrue("221.217.228.166\", \"cid\": \"110105\", \"cname\": \"北京市朝阳区\"};".regexLookingAt(Regexx.IPV4))
        Assert.assertFalse(SAMPLE_IP_V4.regexLookingAt(Regexx.IPV4))

        Assert.assertTrue("221.217.228.166\", \"cid\": \"110105\", \"cname\": \"北京市朝阳区\"};".regexLookingAt(REGEX_IPV4))
        Assert.assertFalse(SAMPLE_IP_V4.regexLookingAt(REGEX_IPV4))
    }

    @Test
    fun testGetFirst() {
        Assert.assertEquals("221.217.228.166", SAMPLE_IP_V4.regexGetFirst(Regexx.IPV4))
        Assert.assertNull("好好学习".regexGetFirst(Regexx.IPV4))

        Assert.assertEquals("221.217.228.166", SAMPLE_IP_V4.regexGetFirst(REGEX_IPV4))
        Assert.assertNull("好好学习".regexGetFirst(REGEX_IPV4))
    }

    @Test
    fun testGetAll() {
        Assert.assertArrayEquals(arrayOf("221.217.228.166"), SAMPLE_IP_V4.regexGetAll(Regexx.IPV4))
        Assert.assertArrayEquals(arrayOfNulls<String>(0), "好好学习".regexGetAll(Regexx.IPV4))
        Assert.assertArrayEquals(arrayOf("221.217.228.166", "221.217.228.167", "221.217.228.168"), SAMPLE_IP_V4_MULTI.regexGetAll(Regexx.IPV4))

        Assert.assertArrayEquals(arrayOf("221.217.228.166"), SAMPLE_IP_V4.regexGetAll(REGEX_IPV4))
        Assert.assertArrayEquals(arrayOfNulls<String>(0), "好好学习".regexGetAll(REGEX_IPV4))
        Assert.assertArrayEquals(arrayOf("221.217.228.166", "221.217.228.167", "221.217.228.168"), SAMPLE_IP_V4_MULTI.regexGetAll(REGEX_IPV4))
    }

    @Test
    fun testGroupFirst() {
        var group = SAMPLE_IP_V4.regexFirstGroup(Regexx.IPV4)
        Assert.assertNotNull(group)
        Assert.assertEquals(28, group!!.start.toLong())
        Assert.assertEquals("221.217.228.166", group.content)
        Assert.assertEquals(43, group.end.toLong())

        group = SAMPLE_IP_V4.regexFirstGroup(REGEX_IPV4)
        Assert.assertNotNull(group)
        Assert.assertEquals(28, group!!.start.toLong())
        Assert.assertEquals("221.217.228.166", group.content)
        Assert.assertEquals(43, group.end.toLong())
    }

    @Test
    fun testGroupAll() {
        var groups = SAMPLE_IP_V4_MULTI.regexAllGroup(Regexx.IPV4)
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

        groups = SAMPLE_IP_V4_MULTI.regexAllGroup(REGEX_IPV4)
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
        Assert.assertEquals(SAMPLE_IP_V4_REPLACE, SAMPLE_IP_V4.regexReplaceFirst(Regexx.IPV4, "天天向上"))
        Assert.assertEquals("好好学习", "好好学习".regexReplaceFirst(Regexx.IPV4, "天天向上"))

        Assert.assertEquals(SAMPLE_IP_V4_REPLACE, SAMPLE_IP_V4.regexReplaceFirst(REGEX_IPV4, "天天向上"))
        Assert.assertEquals("好好学习", "好好学习".regexReplaceFirst(REGEX_IPV4, "天天向上"))
    }

    @Test
    fun testReplaceAll() {
        Assert.assertEquals(SAMPLE_IP_V4_MULTI_REPLACE, SAMPLE_IP_V4_MULTI.regexReplaceAll(Regexx.IPV4, "天天向上"))
        Assert.assertEquals("好好学习", "好好学习".regexReplaceAll(Regexx.IPV4, "天天向上"))

        Assert.assertEquals(SAMPLE_IP_V4_MULTI_REPLACE, SAMPLE_IP_V4_MULTI.regexReplaceAll(REGEX_IPV4, "天天向上"))
        Assert.assertEquals("好好学习", "好好学习".regexReplaceAll(REGEX_IPV4, "天天向上"))
    }
}
