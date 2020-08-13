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

package com.github.panpf.tools4j.messagedigest.ktx

import com.github.panpf.tools4j.messagedigest.MessageDigestListener
import org.junit.Assert
import org.junit.Test
import java.io.File

class MessageDigestxTest {

    companion object {
        private val TEST_TEXT: String

        init {
            var index = 0
            val builder = StringBuilder()
            while (index <= 200) {
                builder.append("禅诗灵韵妙和声，秋叶飘飞过江东。一抹时光生惬意，妍媚红尘缱绻中。")
                index++
            }
            TEST_TEXT = builder.toString()
        }

        private const val TEXT_MD2 = "dd80c76da4cb0562c9419c9e2b211dca"
        private const val TEXT_MD5 = "d9b1a336c5ea8cd6fb007bb29537a570"
        private const val TEXT_MD5_16 = "c5ea8cd6fb007bb2"
        private const val TEXT_SHA1 = "b3efaef6125f68ff6a62267b238570477403453f"
        private const val TEXT_SHA256 = "44da8744ab8c692f65ffedc2bfcc76cbc12df99f50f7d9a44283f47cebddbc7e"
        private const val TEXT_SHA512 = "6e818ada14b09de2742a1b40f63250bf077fa15ecd2879720c66f290ace5e9d29d3a3c056454f5264a9a6ee39d6f411b70d1e125340c844f18b48493002a4537"

        private val TEST_FILE_TEXT: String

        init {
            var index = 0
            val builder = StringBuilder()
            while (index <= 250) {
                builder.append("天阔任云雀，山深隐羽虫。轻风如助我，一跃过江东。")
                index++
            }
            TEST_FILE_TEXT = builder.toString()
        }

        private const val FILE_MD2 = "650dc6242fa988b583bb68e7a863f373"
        private const val FILE_MD5 = "4764c5fe8e8a602009d5948a8b99693e"
        private const val FILE_MD5_16 = "8e8a602009d5948a"
        private const val FILE_SHA1 = "a8f5cb1288daa8717bf2eaf0db7d3970c56e8252"
        private const val FILE_SHA256 = "d39919a123c98de495c0c094a43ecde08c05db7376b2b7a65ef6058b014946e2"
        private const val FILE_SHA512 = "3e055b14520b741e87560b41b3286ea662868782ae2e696ee77580993c7270086aa32b32f125f2fe717debd1abc3e533244de35699aa6be860ea67ca10aff07d"
    }

    @Test
    fun testInputStreamDigest() {
        val callback = TestDigestListener()
        Assert.assertEquals(TEXT_MD2, TEST_TEXT.byteInputStream().getDigest("MD2"))
        Assert.assertEquals(TEXT_MD2, TEST_TEXT.byteInputStream().getDigest("MD2", callback.reset()))
        Assert.assertEquals("8192,16384,19296", callback.toString())
        TEST_TEXT.byteInputStream().getDigest("MD2", callback.resetMakeDamage())
        Assert.assertEquals(callback.toString(), "8192,16384")
        Assert.assertEquals(TEXT_MD2, TEST_TEXT.byteInputStream().getDigestOrEmpty("MD2"))
        Assert.assertEquals(TEXT_MD2, TEST_TEXT.byteInputStream().getDigestOrEmpty("MD2", callback.reset()))
        Assert.assertEquals(TEXT_MD2, TEST_TEXT.byteInputStream().getDigestOrNull("MD2"))
        Assert.assertEquals(TEXT_MD2, TEST_TEXT.byteInputStream().getDigestOrNull("MD2", callback.reset()))

        Assert.assertEquals(TEXT_MD5, TEST_TEXT.byteInputStream().getMD5Digest())
        Assert.assertEquals(TEXT_MD5, TEST_TEXT.byteInputStream().getMD5Digest(callback))
        Assert.assertEquals(TEXT_MD5, TEST_TEXT.byteInputStream().getMD5DigestOrEmpty())
        Assert.assertEquals(TEXT_MD5, TEST_TEXT.byteInputStream().getMD5DigestOrEmpty(callback))
        Assert.assertEquals(TEXT_MD5, TEST_TEXT.byteInputStream().getMD5DigestOrNull())
        Assert.assertEquals(TEXT_MD5, TEST_TEXT.byteInputStream().getMD5DigestOrNull(callback))

        Assert.assertEquals(TEXT_MD5_16, TEST_TEXT.byteInputStream().getMD5_16Digest())
        Assert.assertEquals(TEXT_MD5_16, TEST_TEXT.byteInputStream().getMD5_16Digest(callback))
        Assert.assertEquals(TEXT_MD5_16, TEST_TEXT.byteInputStream().getMD5_16DigestOrEmpty())
        Assert.assertEquals(TEXT_MD5_16, TEST_TEXT.byteInputStream().getMD5_16DigestOrEmpty(callback))
        Assert.assertEquals(TEXT_MD5_16, TEST_TEXT.byteInputStream().getMD5_16DigestOrNull())
        Assert.assertEquals(TEXT_MD5_16, TEST_TEXT.byteInputStream().getMD5_16DigestOrNull(callback))

        Assert.assertEquals(TEXT_SHA1, TEST_TEXT.byteInputStream().getSHA1Digest())
        Assert.assertEquals(TEXT_SHA1, TEST_TEXT.byteInputStream().getSHA1Digest(callback))
        Assert.assertEquals(TEXT_SHA1, TEST_TEXT.byteInputStream().getSHA1DigestOrEmpty())
        Assert.assertEquals(TEXT_SHA1, TEST_TEXT.byteInputStream().getSHA1DigestOrEmpty(callback))
        Assert.assertEquals(TEXT_SHA1, TEST_TEXT.byteInputStream().getSHA1DigestOrNull())
        Assert.assertEquals(TEXT_SHA1, TEST_TEXT.byteInputStream().getSHA1DigestOrNull(callback))

        Assert.assertEquals(TEXT_SHA256, TEST_TEXT.byteInputStream().getSHA256Digest())
        Assert.assertEquals(TEXT_SHA256, TEST_TEXT.byteInputStream().getSHA256Digest(callback))
        Assert.assertEquals(TEXT_SHA256, TEST_TEXT.byteInputStream().getSHA256DigestOrEmpty())
        Assert.assertEquals(TEXT_SHA256, TEST_TEXT.byteInputStream().getSHA256DigestOrEmpty(callback))
        Assert.assertEquals(TEXT_SHA256, TEST_TEXT.byteInputStream().getSHA256DigestOrNull())
        Assert.assertEquals(TEXT_SHA256, TEST_TEXT.byteInputStream().getSHA256DigestOrNull(callback))

        Assert.assertEquals(TEXT_SHA512, TEST_TEXT.byteInputStream().getSHA512Digest())
        Assert.assertEquals(TEXT_SHA512, TEST_TEXT.byteInputStream().getSHA512Digest(callback))
        Assert.assertEquals(TEXT_SHA512, TEST_TEXT.byteInputStream().getSHA512DigestOrEmpty())
        Assert.assertEquals(TEXT_SHA512, TEST_TEXT.byteInputStream().getSHA512DigestOrEmpty(callback))
        Assert.assertEquals(TEXT_SHA512, TEST_TEXT.byteInputStream().getSHA512DigestOrNull())
        Assert.assertEquals(TEXT_SHA512, TEST_TEXT.byteInputStream().getSHA512DigestOrNull(callback))
    }

    @Test
    fun testBytesDigest() {
        Assert.assertEquals(TEXT_MD2, TEST_TEXT.toByteArray().getDigest("MD2"))
        Assert.assertEquals(TEXT_MD5, TEST_TEXT.toByteArray().getMD5Digest())
        Assert.assertEquals(TEXT_MD5_16, TEST_TEXT.toByteArray().getMD5_16Digest())
        Assert.assertEquals(TEXT_SHA1, TEST_TEXT.toByteArray().getSHA1Digest())
        Assert.assertEquals(TEXT_SHA256, TEST_TEXT.toByteArray().getSHA256Digest())
        Assert.assertEquals(TEXT_SHA512, TEST_TEXT.toByteArray().getSHA512Digest())
    }

    @Test
    fun testTextDigest() {
        Assert.assertEquals(TEXT_MD2, TEST_TEXT.getDigest("MD2"))
        Assert.assertEquals(TEXT_MD5, TEST_TEXT.getMD5Digest())
        Assert.assertEquals(TEXT_MD5_16, TEST_TEXT.getMD5_16Digest())
        Assert.assertEquals(TEXT_SHA1, TEST_TEXT.getSHA1Digest())
        Assert.assertEquals(TEXT_SHA256, TEST_TEXT.getSHA256Digest())
        Assert.assertEquals(TEXT_SHA512, TEST_TEXT.getSHA512Digest())
    }

    @Test
    fun testFileDigest() {
        val callback = TestDigestListener()
        val file = File("/tmp/testFileDigest.tmp").apply { writeText(TEST_FILE_TEXT) }

        try {
            Assert.assertEquals(FILE_MD2, file.getDigest("MD2"))
            Assert.assertEquals(FILE_MD2, file.getDigest("MD2", callback.reset()))
            Assert.assertEquals("8192,16384,18072", callback.toString())
            file.getDigest("MD2", callback.resetMakeDamage())
            Assert.assertEquals("8192,16384", callback.toString())
            Assert.assertEquals(FILE_MD2, file.getDigestOrEmpty("MD2"))
            Assert.assertEquals(FILE_MD2, file.getDigestOrEmpty("MD2", callback.reset()))
            Assert.assertEquals(FILE_MD2, file.getDigestOrNull("MD2"))
            Assert.assertEquals(FILE_MD2, file.getDigestOrNull("MD2", callback.reset()))

            Assert.assertEquals(FILE_MD5, file.getMD5Digest())
            Assert.assertEquals(FILE_MD5, file.getMD5Digest(callback))
            Assert.assertEquals(FILE_MD5, file.getMD5DigestOrEmpty())
            Assert.assertEquals(FILE_MD5, file.getMD5DigestOrEmpty(callback))
            Assert.assertEquals(FILE_MD5, file.getMD5DigestOrNull())
            Assert.assertEquals(FILE_MD5, file.getMD5DigestOrNull(callback))

            Assert.assertEquals(FILE_MD5_16, file.getMD5_16Digest())
            Assert.assertEquals(FILE_MD5_16, file.getMD5_16Digest(callback))
            Assert.assertEquals(FILE_MD5_16, file.getMD5_16DigestOrEmpty())
            Assert.assertEquals(FILE_MD5_16, file.getMD5_16DigestOrEmpty(callback))
            Assert.assertEquals(FILE_MD5_16, file.getMD5_16DigestOrNull())
            Assert.assertEquals(FILE_MD5_16, file.getMD5_16DigestOrNull(callback))

            Assert.assertEquals(FILE_SHA1, file.getSHA1Digest())
            Assert.assertEquals(FILE_SHA1, file.getSHA1Digest(callback))
            Assert.assertEquals(FILE_SHA1, file.getSHA1DigestOrEmpty())
            Assert.assertEquals(FILE_SHA1, file.getSHA1DigestOrEmpty(callback))
            Assert.assertEquals(FILE_SHA1, file.getSHA1DigestOrNull())
            Assert.assertEquals(FILE_SHA1, file.getSHA1DigestOrNull(callback))

            Assert.assertEquals(FILE_SHA256, file.getSHA256Digest())
            Assert.assertEquals(FILE_SHA256, file.getSHA256Digest(callback))
            Assert.assertEquals(FILE_SHA256, file.getSHA256DigestOrEmpty())
            Assert.assertEquals(FILE_SHA256, file.getSHA256DigestOrEmpty(callback))
            Assert.assertEquals(FILE_SHA256, file.getSHA256DigestOrNull())
            Assert.assertEquals(FILE_SHA256, file.getSHA256DigestOrNull(callback))

            Assert.assertEquals(FILE_SHA512, file.getSHA512Digest())
            Assert.assertEquals(FILE_SHA512, file.getSHA512Digest(callback))
            Assert.assertEquals(FILE_SHA512, file.getSHA512DigestOrEmpty())
            Assert.assertEquals(FILE_SHA512, file.getSHA512DigestOrEmpty(callback))
            Assert.assertEquals(FILE_SHA512, file.getSHA512DigestOrNull())
            Assert.assertEquals(FILE_SHA512, file.getSHA512DigestOrNull(callback))
        } finally {
            file.delete()
        }
    }

    @Test
    fun testError() {
        try {
            Assert.assertEquals(TEXT_MD2, TEST_TEXT.getDigest("MD25"))
            Assert.fail()
        } catch (e: RuntimeException) {
        }
    }

    private class TestDigestListener : MessageDigestListener {

        private var builder = StringBuilder()
        private var makeDamage: Boolean = false
        private var updateProgressCount: Int = 0

        override fun isCanceled(): Boolean {
            return makeDamage && updateProgressCount == 2
        }

        override fun onUpdateProgress(completedLength: Long) {
            if (builder.isNotEmpty()) builder.append(",")
            builder.append(completedLength)
            updateProgressCount++
        }

        fun reset(): TestDigestListener {
            builder = StringBuilder()
            makeDamage = false
            updateProgressCount = 0
            return this
        }

        fun resetMakeDamage(): TestDigestListener {
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
