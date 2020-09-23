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
package com.github.panpf.tools4j.net

import org.junit.Assert
import org.junit.Test
import java.net.MalformedURLException
import java.net.URL

class NetxTest {

    @Test
    fun testIpType() {
        Assert.assertTrue(Netx.isIPV4("8.8.8.8"))
        Assert.assertFalse(Netx.isIPV4("11:11:e:1EEE:11:11:200.200.200.200"))
        Assert.assertFalse(Netx.isIPV4("5e:0:0:0:0:0:5668:eeee"))
        Assert.assertFalse(Netx.isIPV4("e:ee:5:e::0.0.0.254"))
        Assert.assertFalse(Netx.isIPV4("::120.0.0.1"))
        Assert.assertFalse(Netx.isIPV4("ee:ee::11.11.11.125"))
        Assert.assertFalse(Netx.isIPV4("天天向上"))
        Assert.assertTrue(Netx.isIPV6("11:11:e:1EEE:11:11:200.200.200.200"))
        Assert.assertTrue(Netx.isIPV6("5e:0:0:0:0:0:5668:eeee"))
        Assert.assertTrue(Netx.isIPV6("8.8.8.8"))
        Assert.assertTrue(Netx.isIPV6("e:ee:5:e::0.0.0.254"))
        Assert.assertTrue(Netx.isIPV6("::120.0.0.1"))
        Assert.assertTrue(Netx.isIPV6("ee:ee::11.11.11.125"))
        Assert.assertFalse(Netx.isIPV6("天天向上"))
        Assert.assertTrue(Netx.isMacAddress("58:E8:76:83:A2:C7"))
        Assert.assertTrue(Netx.isMacAddress("58-E8-76-83-A2-C7"))
        Assert.assertTrue(Netx.isMacAddress("58:e8:76:83:a2:c7"))
        Assert.assertTrue(Netx.isMacAddress("58-e8-76-83-a2-c7"))
        Assert.assertFalse(Netx.isMacAddress("58/E8:76:83:A2:C7"))
        Assert.assertFalse(Netx.isMacAddress("天天向上"))
    }

    @Test
    fun testLocalIP() {
        var ip = Netx.getLocalIPAddress()
        if (ip != null) {
            Assert.assertTrue(Netx.isIPV4(ip) || Netx.isIPV6(ip))
        }
        ip = Netx.getLocalIPAddress("8.8.8.8")
        Assert.assertNotNull(ip)
        Assert.assertTrue(Netx.isIPV4(ip) || Netx.isIPV6(ip))
    }

    @Test
    fun testLocalIPV4() {
        var ip = Netx.getLocalIPV4Address()
        if (ip != null) {
            Assert.assertTrue(Netx.isIPV4(ip))
        }
        ip = Netx.getLocalIPV4Address("8.8.8.8")
        Assert.assertNotNull(ip)
        Assert.assertTrue(Netx.isIPV4(ip))
    }

    @Test
    fun testExternalIPV4From() {
        var ipv4: String? = null
        try {
            ipv4 = Netx.getExternalIPV4AddressFrom(URL("https://pv.sohu.com/cityjson"), Netx.IPV4)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        Assert.assertNotNull(ipv4)
        Assert.assertTrue("ipv4: $ipv4", Netx.isIPV4(ipv4))
    }

    @Test
    fun testExternalIPV4FromSohu() {
        val ipv4 = Netx.getExternalIPV4AddressFromSohu()
        Assert.assertNotNull(ipv4)
        Assert.assertTrue("ipv4: $ipv4", Netx.isIPV4(ipv4))
    }

    @Test
    fun testExternalIPV4() {
        val ipv4 = Netx.getExternalIPV4Address()
        Assert.assertNotNull(ipv4)
        Assert.assertTrue("ipv4: $ipv4", Netx.isIPV4(ipv4))
    }

    @Test
    fun testCity() {
        val city = Netx.getCityFromSohu()
        Assert.assertNotNull(city)
        Assert.assertNotNull(city!!.id)
        Assert.assertNotNull(city.name)
        Assert.assertTrue("ipv4: " + city.ip, Netx.isIPV4(city.ip))
        val city2 = Netx.getCity()
        Assert.assertNotNull(city2)
        Assert.assertNotNull(city2!!.id)
        Assert.assertNotNull(city2.name)
        Assert.assertTrue("ipv4: " + city2.ip, Netx.isIPV4(city2.ip))
    }

    @Test
    fun testIpLong2String() {
        val ip = "221.217.228.166"
        Assert.assertEquals(Netx.ipLongToString(Netx.ipStringToLong(ip)), ip)
    }

    @Test
    fun testMatchMimeType() {
        Assert.assertTrue(Netx.matchMimeType("application/zip", "application/zip"))
        Assert.assertTrue(Netx.matchMimeType("application/*", "application/octet-stream"))
        Assert.assertTrue(Netx.matchMimeType("*/*", "application/octet-stream"))
        Assert.assertFalse(Netx.matchMimeType("application/zip", "application/octet-stream"))
        Assert.assertFalse(Netx.matchMimeType("application/octet-stream", "application/zip"))
        Assert.assertFalse(Netx.matchMimeType("application/zip", "application/zip/apk"))
        Assert.assertFalse(Netx.matchMimeType("application/zip/apk", "application/zip"))
        Assert.assertFalse(Netx.matchMimeType("application/octet-stream", "application/*"))
        Assert.assertFalse(Netx.matchMimeType("application/octet-stream", "*/*"))
    }

    @Test
    fun testGuessFileName() {
        val contentDisposition = "attachment; filename=\"test_disposition.zip\"; filename*=utf-8' 'test_disposition2.zip"
        val contentLocation = "http://localhost/test_location.zip"
        val url = "http://localhost/test_url.zip"
        Assert.assertEquals("test_disposition.zip", Netx.guessFileName(contentDisposition, contentLocation, url))
        Assert.assertEquals("test_location.zip", Netx.guessFileName("", contentLocation, url))
        Assert.assertEquals("test_url.zip", Netx.guessFileName("", "", url))
        Assert.assertNull(Netx.guessFileName("", "", ""))
        Assert.assertEquals("test_disposition.zip", Netx.guessFileNameFromContentDisposition(contentDisposition))
        Assert.assertEquals("test_disposition.zip", Netx.guessFileNameFromContentDisposition("attachment; filename=\"/storage/test_disposition.zip\"; filename*=utf-8' '/storage/test_disposition2.zip"))
        Assert.assertEquals("test_disposition.zip", Netx.guessFileNameFromContentDisposition("attachment; filename=\"test_disposition.zip; filename*=utf-8' '/storage/test_disposition2.zip"))
        Assert.assertEquals("test_disposition.zip", Netx.guessFileNameFromContentDisposition("attachment; filename=test_disposition.zip\"; filename*=utf-8' '/storage/test_disposition2.zip"))
        Assert.assertEquals("test_disposition.zip", Netx.guessFileNameFromContentDisposition("attachment; filename=test_disposition.zip; filename*=utf-8' '/storage/test_disposition2.zip"))
        Assert.assertEquals("test_disposition", Netx.guessFileNameFromContentDisposition("attachment; filename=\"test_disposition\"; filename*=utf-8' 'test_disposition2"))
        Assert.assertEquals("test_disposition", Netx.guessFileNameFromContentDisposition("attachment; filename=\"test_disposition; filename*=utf-8' 'test_disposition2"))
        Assert.assertEquals("test_disposition", Netx.guessFileNameFromContentDisposition("attachment; filename=test_disposition\"; filename*=utf-8' 'test_disposition2"))
        Assert.assertEquals("test_disposition", Netx.guessFileNameFromContentDisposition("attachment; filename=test_disposition; filename*=utf-8' 'test_disposition2"))
        Assert.assertNull(Netx.guessFileNameFromContentDisposition(""))
        Assert.assertNull(Netx.guessFileNameFromContentDisposition("attachment; filename*=utf-8' 'test_disposition2"))
        Assert.assertEquals("test_url.zip", Netx.guessFileNameFromUrl(url))
        Assert.assertEquals("test_url.zip", Netx.guessFileNameFromUrl("sample/test_url.zip"))
        Assert.assertEquals("test_url.zip", Netx.guessFileNameFromUrl("http://localhost/test_url.zip?d=2018"))
        Assert.assertEquals("test_url.zip", Netx.guessFileNameFromUrl("test_url.zip"))
        Assert.assertNull("test_url", Netx.guessFileNameFromUrl("http://localhost/test_url"))
        Assert.assertNull(Netx.guessFileNameFromUrl(""))
        Assert.assertNull(Netx.guessFileNameFromUrl("http://localhost/test_url/"))
        Assert.assertNull(Netx.guessFileNameFromUrl(""))
        Assert.assertNull(Netx.guessFileNameFromUrl("http://localhost/test_url."))
    }
}