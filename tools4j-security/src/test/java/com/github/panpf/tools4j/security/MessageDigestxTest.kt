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
package com.github.panpf.tools4j.security

import org.junit.Assert
import org.junit.Test
import java.io.File
import java.io.IOException

class MessageDigestxTest {
    companion object {
        private val TEST_TEXT: String
        private const val TEXT_MD2 = "dd80c76da4cb0562c9419c9e2b211dca"
        private const val TEXT_MD5 = "d9b1a336c5ea8cd6fb007bb29537a570"
        private const val TEXT_MD5_16 = "c5ea8cd6fb007bb2"
        private const val TEXT_SHA1 = "b3efaef6125f68ff6a62267b238570477403453f"
        private const val TEXT_SHA256 = "44da8744ab8c692f65ffedc2bfcc76cbc12df99f50f7d9a44283f47cebddbc7e"
        private const val TEXT_SHA512 = "6e818ada14b09de2742a1b40f63250bf077fa15ecd2879720c66f290ace5e9d29d3a3c056454f5264a9a6ee39d6f411b70d1e125340c844f18b48493002a4537"
        private val TEST_FILE_TEXT: String
        private const val FILE_MD2 = "650dc6242fa988b583bb68e7a863f373"
        private const val FILE_MD5 = "4764c5fe8e8a602009d5948a8b99693e"
        private const val FILE_MD5_16 = "8e8a602009d5948a"
        private const val FILE_SHA1 = "a8f5cb1288daa8717bf2eaf0db7d3970c56e8252"
        private const val FILE_SHA256 = "d39919a123c98de495c0c094a43ecde08c05db7376b2b7a65ef6058b014946e2"
        private const val FILE_SHA512 = "3e055b14520b741e87560b41b3286ea662868782ae2e696ee77580993c7270086aa32b32f125f2fe717debd1abc3e533244de35699aa6be860ea67ca10aff07d"

        init {
            var index = 0
            val builder = StringBuilder()
            while (index <= 200) {
                builder.append("禅诗灵韵妙和声，秋叶飘飞过江东。一抹时光生惬意，妍媚红尘缱绻中。")
                index++
            }
            TEST_TEXT = builder.toString()
        }

        init {
            var index = 0
            val builder = StringBuilder()
            while (index <= 250) {
                builder.append("天阔任云雀，山深隐羽虫。轻风如助我，一跃过江东。")
                index++
            }
            TEST_FILE_TEXT = builder.toString()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testInputStreamDigest() {
        val callback = TestMessageDigestListener()
        Assert.assertEquals(TEXT_MD2, MessageDigestx.getDigest(TEST_TEXT.byteInputStream(), "MD2"))
        Assert.assertEquals(TEXT_MD2, MessageDigestx.getDigest(TEST_TEXT.byteInputStream(), "MD2", callback.reset()))
        Assert.assertEquals("8192,16384,19296", callback.toString())
        MessageDigestx.getDigest(TEST_TEXT.byteInputStream(), "MD2", callback.resetMakeDamage())
        Assert.assertEquals("8192,16384", callback.toString())
        Assert.assertEquals(TEXT_MD2, MessageDigestx.getDigestOrEmpty(TEST_TEXT.byteInputStream(), "MD2"))
        Assert.assertEquals(TEXT_MD2, MessageDigestx.getDigestOrEmpty(TEST_TEXT.byteInputStream(), "MD2", callback.reset()))
        Assert.assertEquals(TEXT_MD2, MessageDigestx.getDigestOrNull(TEST_TEXT.byteInputStream(), "MD2"))
        Assert.assertEquals(TEXT_MD2, MessageDigestx.getDigestOrNull(TEST_TEXT.byteInputStream(), "MD2", callback.reset()))
        Assert.assertEquals(TEXT_MD5, MessageDigestx.getMD5(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_MD5, MessageDigestx.getMD5(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_MD5, MessageDigestx.getMD5OrEmpty(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_MD5, MessageDigestx.getMD5OrEmpty(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_MD5, MessageDigestx.getMD5OrNull(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_MD5, MessageDigestx.getMD5OrNull(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_MD5_16, MessageDigestx.getMD5_16(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_MD5_16, MessageDigestx.getMD5_16(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_MD5_16, MessageDigestx.getMD5_16OrEmpty(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_MD5_16, MessageDigestx.getMD5_16OrEmpty(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_MD5_16, MessageDigestx.getMD5_16OrNull(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_MD5_16, MessageDigestx.getMD5_16OrNull(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_SHA1, MessageDigestx.getSHA1(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_SHA1, MessageDigestx.getSHA1(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_SHA1, MessageDigestx.getSHA1OrEmpty(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_SHA1, MessageDigestx.getSHA1OrEmpty(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_SHA1, MessageDigestx.getSHA1OrNull(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_SHA1, MessageDigestx.getSHA1OrNull(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_SHA256, MessageDigestx.getSHA256(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_SHA256, MessageDigestx.getSHA256(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_SHA256, MessageDigestx.getSHA256OrEmpty(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_SHA256, MessageDigestx.getSHA256OrEmpty(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_SHA256, MessageDigestx.getSHA256OrNull(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_SHA256, MessageDigestx.getSHA256OrNull(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_SHA512, MessageDigestx.getSHA512(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_SHA512, MessageDigestx.getSHA512(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_SHA512, MessageDigestx.getSHA512OrEmpty(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_SHA512, MessageDigestx.getSHA512OrEmpty(TEST_TEXT.byteInputStream(), callback.reset()))
        Assert.assertEquals(TEXT_SHA512, MessageDigestx.getSHA512OrNull(TEST_TEXT.byteInputStream()))
        Assert.assertEquals(TEXT_SHA512, MessageDigestx.getSHA512OrNull(TEST_TEXT.byteInputStream(), callback.reset()))
    }

    @Test
    fun testBytesDigest() {
        Assert.assertEquals(TEXT_MD2, MessageDigestx.getDigest(TEST_TEXT.toByteArray(), "MD2"))
        Assert.assertEquals(TEXT_MD5, MessageDigestx.getMD5(TEST_TEXT.toByteArray()))
        Assert.assertEquals(TEXT_MD5_16, MessageDigestx.getMD5_16(TEST_TEXT.toByteArray()))
        Assert.assertEquals(TEXT_SHA1, MessageDigestx.getSHA1(TEST_TEXT.toByteArray()))
        Assert.assertEquals(TEXT_SHA256, MessageDigestx.getSHA256(TEST_TEXT.toByteArray()))
        Assert.assertEquals(TEXT_SHA512, MessageDigestx.getSHA512(TEST_TEXT.toByteArray()))
    }

    @Test
    fun testTextDigest() {
        Assert.assertEquals(TEXT_MD2, MessageDigestx.getDigest(TEST_TEXT, "MD2"))
        Assert.assertEquals(TEXT_MD5, MessageDigestx.getMD5(TEST_TEXT))
        Assert.assertEquals(TEXT_MD5_16, MessageDigestx.getMD5_16(TEST_TEXT))
        Assert.assertEquals(TEXT_SHA1, MessageDigestx.getSHA1(TEST_TEXT))
        Assert.assertEquals(TEXT_SHA256, MessageDigestx.getSHA256(TEST_TEXT))
        Assert.assertEquals(TEXT_SHA512, MessageDigestx.getSHA512(TEST_TEXT))
    }

    @Test
    @Throws(IOException::class)
    fun testFileDigest() {
        val callback = TestMessageDigestListener()
        val file = File("/tmp/testDigest.tmp")
        try {
            file.writeText(TEST_FILE_TEXT)
            Assert.assertEquals(FILE_MD2, MessageDigestx.getDigest(file, "MD2"))
            Assert.assertEquals(FILE_MD2, MessageDigestx.getDigest(file, "MD2", callback.reset()))
            Assert.assertEquals("8192,16384,18072", callback.toString())
            MessageDigestx.getDigest(file, "MD2", callback.resetMakeDamage())
            Assert.assertEquals("8192,16384", callback.toString())
            Assert.assertEquals(FILE_MD2, MessageDigestx.getDigestOrEmpty(file, "MD2"))
            Assert.assertEquals(FILE_MD2, MessageDigestx.getDigestOrEmpty(file, "MD2", callback.reset()))
            Assert.assertEquals(FILE_MD2, MessageDigestx.getDigestOrNull(file, "MD2"))
            Assert.assertEquals(FILE_MD2, MessageDigestx.getDigestOrNull(file, "MD2", callback.reset()))
            Assert.assertEquals(FILE_MD5, MessageDigestx.getMD5(file))
            Assert.assertEquals(FILE_MD5, MessageDigestx.getMD5(file, callback.reset()))
            Assert.assertEquals(FILE_MD5, MessageDigestx.getMD5OrEmpty(file))
            Assert.assertEquals(FILE_MD5, MessageDigestx.getMD5OrEmpty(file, callback.reset()))
            Assert.assertEquals(FILE_MD5, MessageDigestx.getMD5OrNull(file))
            Assert.assertEquals(FILE_MD5, MessageDigestx.getMD5OrNull(file, callback.reset()))
            Assert.assertEquals(FILE_MD5_16, MessageDigestx.getMD5_16(file))
            Assert.assertEquals(FILE_MD5_16, MessageDigestx.getMD5_16(file, callback.reset()))
            Assert.assertEquals(FILE_MD5_16, MessageDigestx.getMD5_16OrEmpty(file))
            Assert.assertEquals(FILE_MD5_16, MessageDigestx.getMD5_16OrEmpty(file, callback.reset()))
            Assert.assertEquals(FILE_MD5_16, MessageDigestx.getMD5_16OrNull(file))
            Assert.assertEquals(FILE_MD5_16, MessageDigestx.getMD5_16OrNull(file, callback.reset()))
            Assert.assertEquals(FILE_SHA1, MessageDigestx.getSHA1(file))
            Assert.assertEquals(FILE_SHA1, MessageDigestx.getSHA1(file, callback.reset()))
            Assert.assertEquals(FILE_SHA1, MessageDigestx.getSHA1OrEmpty(file))
            Assert.assertEquals(FILE_SHA1, MessageDigestx.getSHA1OrEmpty(file, callback.reset()))
            Assert.assertEquals(FILE_SHA1, MessageDigestx.getSHA1OrNull(file))
            Assert.assertEquals(FILE_SHA1, MessageDigestx.getSHA1OrNull(file, callback.reset()))
            Assert.assertEquals(FILE_SHA256, MessageDigestx.getSHA256(file))
            Assert.assertEquals(FILE_SHA256, MessageDigestx.getSHA256(file, callback.reset()))
            Assert.assertEquals(FILE_SHA256, MessageDigestx.getSHA256OrEmpty(file))
            Assert.assertEquals(FILE_SHA256, MessageDigestx.getSHA256OrEmpty(file, callback.reset()))
            Assert.assertEquals(FILE_SHA256, MessageDigestx.getSHA256OrNull(file))
            Assert.assertEquals(FILE_SHA256, MessageDigestx.getSHA256OrNull(file, callback.reset()))
            Assert.assertEquals(FILE_SHA512, MessageDigestx.getSHA512(file))
            Assert.assertEquals(FILE_SHA512, MessageDigestx.getSHA512(file, callback.reset()))
            Assert.assertEquals(FILE_SHA512, MessageDigestx.getSHA512OrEmpty(file))
            Assert.assertEquals(FILE_SHA512, MessageDigestx.getSHA512OrEmpty(file, callback.reset()))
            Assert.assertEquals(FILE_SHA512, MessageDigestx.getSHA512OrNull(file))
            Assert.assertEquals(FILE_SHA512, MessageDigestx.getSHA512OrNull(file, callback.reset()))
        } finally {
            file.delete()
        }
    }

    @Test
    fun testError() {
        try {
            Assert.assertEquals(TEXT_MD2, MessageDigestx.getDigest(TEST_TEXT, "MD25"))
            Assert.fail()
        } catch (ignored: RuntimeException) {
        }
    }

    private class TestMessageDigestListener : MessageDigestListener {
        private var builder = StringBuilder()
        private var makeDamage = false
        private var updateProgressCount = 0
        override fun isCanceled(): Boolean {
            return makeDamage && updateProgressCount == 2
        }

        override fun onUpdateProgress(completedLength: Long) {
            if (builder.isNotEmpty()) builder.append(",")
            builder.append(completedLength)
            updateProgressCount++
        }

        fun reset(): TestMessageDigestListener {
            builder = StringBuilder()
            makeDamage = false
            updateProgressCount = 0
            return this
        }

        fun resetMakeDamage(): TestMessageDigestListener {
            builder = StringBuilder()
            makeDamage = true
            updateProgressCount = 0
            return this
        }

        override fun toString(): String {
            return builder.toString()
        }
    }
}