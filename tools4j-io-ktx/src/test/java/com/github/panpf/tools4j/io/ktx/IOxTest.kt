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

package com.github.panpf.tools4j.io.ktx

import com.github.panpf.tools4j.io.CopyListener
import com.github.panpf.tools4j.io.IOx
import com.github.panpf.tools4j.reflect.ktx.getFieldValue
import org.junit.Assert
import org.junit.Test
import java.io.*

class IOxTest {

    @Test
    fun testSafeClose() {
        ByteArrayInputStream("1234567890".toByteArray()).closeQuietly()
        object : ByteArrayInputStream("1234567890".toByteArray()) {
            override fun close() {
                throw RuntimeException("test")
            }
        }.closeQuietly()

        ByteArrayOutputStream().closeQuietly()
        object : ByteArrayOutputStream() {
            override fun close() {
                throw RuntimeException("test")
            }
        }.closeQuietly()

        null.closeQuietly()
    }

    @Test
    fun testReadClose() {
        val file = File("/tmp/testReadClose.txt")
        val content = "abcdefg\nhijklmn\nopqrst\nuvwxyz"
        file.writeText(content)
        try {
            file.inputStream().let { input ->
                Assert.assertFalse(input.getFieldValue("closed") ?: false)
                Assert.assertEquals(String(input.readBytesAndClose(IOx.DEFAULT_BUFFER_SIZE)), content)
                Assert.assertTrue(input.getFieldValue("closed") ?: false)
            }
            file.inputStream().let { input ->
                Assert.assertFalse(input.getFieldValue("closed") ?: false)
                Assert.assertEquals(String(input.readBytesAndClose()), content)
                Assert.assertTrue(input.getFieldValue("closed") ?: false)
            }
            file.reader().let { reader ->
                val input = reader.getFieldValue<Any>("sd")!!.getFieldValue<FileInputStream>("in")!!
                Assert.assertFalse(input.getFieldValue("closed") ?: false)
                Assert.assertEquals(reader.readTextAndClose(), content)
                Assert.assertTrue(input.getFieldValue("closed") ?: false)
            }
        } finally {
            file.deleteRecursively()
        }
    }

    @Test
    fun testWriteClose() {
        val file = File("/tmp/testWriteClose.txt")
        val content = "abcdefg"
        val contentBytes = content.toByteArray()
        val contentChars = content.toCharArray()

        try {
            file.outputStream().let { output ->
                Assert.assertFalse(output.getFieldValue("closed") ?: false)
                output.writeByteAndClose(contentBytes[0].toInt())
                Assert.assertTrue(output.getFieldValue("closed") ?: false)
                Assert.assertEquals(contentBytes[0], file.inputStream().readBytes()[0])
            }

            file.outputStream().let { output ->
                Assert.assertFalse(output.getFieldValue("closed") ?: false)
                output.writeBytesAndClose(contentBytes)
                Assert.assertTrue(output.getFieldValue("closed") ?: false)
                Assert.assertArrayEquals(contentBytes, file.inputStream().readBytes())
            }

            file.outputStream().let { output ->
                Assert.assertFalse(output.getFieldValue("closed") ?: false)
                output.writeBytesAndClose(contentBytes, 2, 3)
                Assert.assertTrue(output.getFieldValue("closed") ?: false)
                Assert.assertArrayEquals(contentBytes.sliceArray(2..4), file.inputStream().readBytes())
            }

            file.writer().let { writer ->
                val output = writer.getFieldValue<Any>("se")!!.getFieldValue<FileOutputStream>("out")!!
                Assert.assertFalse(output.getFieldValue("closed") ?: false)
                writer.writeTextAndClose(content)
                Assert.assertTrue(output.getFieldValue("closed") ?: false)
                Assert.assertEquals(content, file.reader().readText())
            }

            file.writer().let { writer ->
                val output = writer.getFieldValue<Any>("se")!!.getFieldValue<FileOutputStream>("out")!!
                Assert.assertFalse(output.getFieldValue("closed") ?: false)
                writer.writeTextAndClose(content, 2, 3)
                Assert.assertTrue(output.getFieldValue("closed") ?: false)
                Assert.assertEquals(content.slice(2..4), file.reader().readText())
            }

            file.writer().let { writer ->
                val output = writer.getFieldValue<Any>("se")!!.getFieldValue<FileOutputStream>("out")!!
                Assert.assertFalse(output.getFieldValue("closed") ?: false)
                writer.writeCharAndClose('x'.toInt())
                Assert.assertTrue(output.getFieldValue("closed") ?: false)
                Assert.assertEquals("x", file.reader().readText())
            }

            file.writer().let { writer ->
                val output = writer.getFieldValue<Any>("se")!!.getFieldValue<FileOutputStream>("out")!!
                Assert.assertFalse(output.getFieldValue("closed") ?: false)
                writer.writeCharsAndClose(contentChars)
                Assert.assertTrue(output.getFieldValue("closed") ?: false)
                Assert.assertArrayEquals(contentChars, file.reader().readText().toCharArray())
            }

            file.writer().let { writer ->
                val output = writer.getFieldValue<Any>("se")!!.getFieldValue<FileOutputStream>("out")!!
                Assert.assertFalse(output.getFieldValue("closed") ?: false)
                writer.writeCharsAndClose(contentChars, 2, 3)
                Assert.assertTrue(output.getFieldValue("closed") ?: false)
                Assert.assertArrayEquals(contentChars.sliceArray(2..4), file.reader().readText().toCharArray())
            }
        } finally {
            file.delete()
        }
    }

    @Test
    fun testCopyTo() {
        var inputStream = "1234567890".byteInputStream()
        var outputStream = ByteArrayOutputStream()
        try {
            inputStream.copyTo(outputStream, 1024 * 4)
        } finally {
            inputStream.closeQuietly()
            outputStream.closeQuietly()
        }

        inputStream = "1234567890".byteInputStream()
        outputStream = ByteArrayOutputStream()
        try {
            inputStream.copyTo(outputStream, object : CopyListener {
                override fun onUpdateProgress(completedLength: Long) {
                }

                override fun isCanceled(): Boolean = false
            })
        } finally {
            inputStream.closeQuietly()
            outputStream.closeQuietly()
        }

        inputStream = "1234567890".byteInputStream()
        outputStream = ByteArrayOutputStream()
        try {
            inputStream.reader().copyTo(outputStream.writer(), 1024 * 4, object : CopyListener {
                override fun onUpdateProgress(completedLength: Long) {
                }

                override fun isCanceled(): Boolean = false
            })
        } finally {
            inputStream.closeQuietly()
            outputStream.closeQuietly()
        }

        inputStream = "1234567890".byteInputStream()
        outputStream = ByteArrayOutputStream()
        try {
            inputStream.reader().copyTo(outputStream.writer(), object : CopyListener {
                override fun onUpdateProgress(completedLength: Long) {
                }

                override fun isCanceled(): Boolean = false
            })
        } finally {
            inputStream.closeQuietly()
            outputStream.closeQuietly()
        }
    }
}