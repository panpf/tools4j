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

package com.github.panpf.tools4j.io

import com.github.panpf.tools4j.reflect.ktx.getFieldValue
import com.github.panpf.tools4j.regex.Regexx
import com.github.panpf.tools4j.regex.ktx.regexFind
import org.junit.Assert
import org.junit.Test
import java.io.*
import java.net.URL
import java.nio.charset.Charset

class IOxTest {

    @Test
    fun testSafeClose() {
        IOx.closeQuietly(ByteArrayInputStream("1234567890".toByteArray()))
        IOx.closeQuietly(object : ByteArrayInputStream("1234567890".toByteArray()) {
            override fun close() {
                throw RuntimeException("test")
            }
        })

        IOx.closeQuietly(ByteArrayOutputStream())
        IOx.closeQuietly(object : ByteArrayOutputStream() {
            override fun close() {
                throw RuntimeException("test")
            }
        })

        IOx.closeQuietly(null)
    }

    @Test
    fun testReadClose() {
        val file = File("/tmp/testReadClose.txt")
        val content = "abcdefg\nhijklmn\nopqrst\nuvwxyz"
        Filex.writeText(file, content)
        try {
            Filex.inputStream(file).let { input ->
                Assert.assertFalse(input.getFieldValue<Boolean>("closed") ?: false)
                Assert.assertEquals(String(IOx.readBytesAndClose(input, IOx.DEFAULT_BUFFER_SIZE)), content)
                Assert.assertTrue(input.getFieldValue<Boolean>("closed") ?: false)
            }
            Filex.inputStream(file).let { input ->
                Assert.assertFalse(input.getFieldValue<Boolean>("closed") ?: false)
                Assert.assertEquals(String(IOx.readBytesAndClose(input)), content)
                Assert.assertTrue(input.getFieldValue<Boolean>("closed") ?: false)
            }
            Filex.reader(file).let { reader ->
                val input = reader.getFieldValue<Any>("sd")!!.getFieldValue<FileInputStream>("in")!!
                Assert.assertFalse(input.getFieldValue("closed") ?: false)
                Assert.assertEquals(IOx.readTextAndClose(reader), content)
                Assert.assertTrue(input.getFieldValue("closed") ?: false)
            }
        } finally {
            Filex.deleteRecursively(file)
        }
    }

    @Test
    fun testWriteClose() {
        val file = File("/tmp/testWriteClose.txt")
        val content = "abcdefg"
        val contentBytes = content.toByteArray()
        val contentChars = content.toCharArray()

        try {
            Filex.outputStream(file).let { output ->
                Assert.assertFalse(output.getFieldValue<Boolean>("closed") ?: false)
                IOx.writeByteAndClose(output, contentBytes[0].toInt())
                Assert.assertTrue(output.getFieldValue<Boolean>("closed") ?: false)
                Assert.assertEquals(contentBytes[0], IOx.readBytes(Filex.inputStream(file))[0])
            }

            Filex.outputStream(file).let { output ->
                Assert.assertFalse(output.getFieldValue<Boolean>("closed") ?: false)
                IOx.writeBytesAndClose(output, contentBytes)
                Assert.assertTrue(output.getFieldValue<Boolean>("closed") ?: false)
                Assert.assertArrayEquals(contentBytes, IOx.readBytes(Filex.inputStream(file)))
            }

            Filex.outputStream(file).let { output ->
                Assert.assertFalse(output.getFieldValue<Boolean>("closed") ?: false)
                IOx.writeBytesAndClose(output, contentBytes, 2, 3)
                Assert.assertTrue(output.getFieldValue<Boolean>("closed") ?: false)
                Assert.assertArrayEquals(contentBytes.sliceArray(2..4), IOx.readBytes(Filex.inputStream(file)))
            }

            Filex.writer(file).let { writer ->
                val output = writer.getFieldValue<Any>("se")!!.getFieldValue<FileOutputStream>("out")!!
                Assert.assertFalse(output.getFieldValue<Boolean>("closed") ?: false)
                IOx.writeTextAndClose(writer, content)
                Assert.assertTrue(output.getFieldValue<Boolean>("closed") ?: false)
                Assert.assertEquals(content, IOx.readText(Filex.reader(file)))
            }

            Filex.writer(file).let { writer ->
                val output = writer.getFieldValue<Any>("se")!!.getFieldValue<FileOutputStream>("out")!!
                Assert.assertFalse(output.getFieldValue<Boolean>("closed") ?: false)
                IOx.writeTextAndClose(writer, content, 2, 3)
                Assert.assertTrue(output.getFieldValue<Boolean>("closed") ?: false)
                Assert.assertEquals(content.slice(2..4), IOx.readText(Filex.reader(file)))
            }

            Filex.writer(file).let { writer ->
                val output = writer.getFieldValue<Any>("se")!!.getFieldValue<FileOutputStream>("out")!!
                Assert.assertFalse(output.getFieldValue<Boolean>("closed") ?: false)
                IOx.writeCharAndClose(writer, 'x'.toInt())
                Assert.assertTrue(output.getFieldValue<Boolean>("closed") ?: false)
                Assert.assertEquals("x", IOx.readText(Filex.reader(file)))
            }

            Filex.writer(file).let { writer ->
                val output = writer.getFieldValue<Any>("se")!!.getFieldValue<FileOutputStream>("out")!!
                Assert.assertFalse(output.getFieldValue<Boolean>("closed") ?: false)
                IOx.writeCharsAndClose(writer, contentChars)
                Assert.assertTrue(output.getFieldValue<Boolean>("closed") ?: false)
                Assert.assertArrayEquals(contentChars, IOx.readText(Filex.reader(file)).toCharArray())
            }

            Filex.writer(file).let { writer ->
                val output = writer.getFieldValue<Any>("se")!!.getFieldValue<FileOutputStream>("out")!!
                Assert.assertFalse(output.getFieldValue<Boolean>("closed") ?: false)
                IOx.writeCharsAndClose(writer, contentChars, 2, 3)
                Assert.assertTrue(output.getFieldValue<Boolean>("closed") ?: false)
                Assert.assertArrayEquals(contentChars.sliceArray(2..4), IOx.readText(Filex.reader(file)).toCharArray())
            }
        } finally {
            file.delete()
        }
    }

    @Test
    fun testInputStream() {
        IOx.closeQuietly(IOx.inputStream("1234567890".toByteArray()))
        IOx.closeQuietly(IOx.inputStream("1234567890".toByteArray(), 0, 5))

        IOx.closeQuietly(IOx.byteInputStream("1234567890", Charset.forName("UTF-8")))
        IOx.closeQuietly(IOx.byteInputStream("1234567890"))

        IOx.closeQuietly(IOx.reader(IOx.byteInputStream("1234567890"), Charset.forName("UTF-8")))
        IOx.closeQuietly(IOx.reader(IOx.byteInputStream("1234567890")))
        IOx.closeQuietly(IOx.reader("1234567890"))

        IOx.closeQuietly(IOx.buffered(IOx.byteInputStream("1234567890"), IOx.DEFAULT_BUFFER_SIZE))
        IOx.closeQuietly(IOx.buffered(IOx.byteInputStream("1234567890")))
        IOx.closeQuietly(IOx.buffered(IOx.reader("1234567890"), IOx.DEFAULT_BUFFER_SIZE))
        IOx.closeQuietly(IOx.buffered(IOx.reader("1234567890")))

        IOx.closeQuietly(IOx.bufferedReader(IOx.byteInputStream("1234567890"), Charset.forName("UTF-8"), IOx.DEFAULT_BUFFER_SIZE))
        IOx.closeQuietly(IOx.bufferedReader(IOx.byteInputStream("1234567890"), IOx.DEFAULT_BUFFER_SIZE))
        IOx.closeQuietly(IOx.bufferedReader(IOx.byteInputStream("1234567890"), Charset.forName("UTF-8")))
        IOx.closeQuietly(IOx.bufferedReader(IOx.byteInputStream("1234567890")))
    }

    @Test
    fun testRead() {
        val file = File("/tmp/testIORead.txt")
        val content = "abcdefg\nhijklmn\nopqrst\nuvwxyz"
        Filex.writeText(file, content)
        try {
            Assert.assertEquals(String(Filex.inputStream(file).use { IOx.readBytes(it, IOx.DEFAULT_BUFFER_SIZE) }), content)
            Assert.assertEquals(String(Filex.inputStream(file).use { IOx.readBytes(it) }), content)
            Assert.assertEquals(Filex.reader(file).use { IOx.readText(it) }, content)

            val resultBytes = IOx.readBytes(URL("https://pv.sohu.com/cityjson"))
            Assert.assertTrue(String(resultBytes).regexFind(Regexx.IPV4))

            val result = IOx.readText(URL("https://pv.sohu.com/cityjson"), Charset.forName("UTF-8"))
            Assert.assertTrue(result.regexFind(Regexx.IPV4))

            val result2 = IOx.readText(URL("https://pv.sohu.com/cityjson"))
            Assert.assertTrue(result2.regexFind(Regexx.IPV4))

            Assert.assertEquals(StringBuilder().apply {
                IOx.readLines(Filex.reader(file)).forEach { lineString ->
                    if (length > 0) append("\n")
                    append(lineString)
                }
            }.toString(), content)
        } finally {
            Filex.deleteRecursively(file)
        }
    }

    @Test
    fun testLines() {
        val file = File("/tmp/testIOLines.txt")
        val content = "abcdefg\nhijklmn\nopqrst\nuvwxyz"
        Filex.writeText(file, content)
        try {
            Assert.assertEquals(StringBuilder().apply {
                IOx.lineIterable(Filex.bufferedReader(file)).forEach { lineString ->
                    if (length > 0) append("\n")
                    append(lineString)
                }
            }.toString(), content)

            Assert.assertEquals(StringBuilder().apply {
                IOx.useLines(Filex.reader(file)) { sequence ->
                    sequence.forEach { lineString ->
                        if (length > 0) append("\n")
                        append(lineString)
                    }
                }
            }.toString(), content)

            Assert.assertEquals(StringBuilder().apply {
                IOx.forEachLine(Filex.reader(file)) { lineString ->
                    if (length > 0) append("\n")
                    append(lineString)
                }
            }.toString(), content)
        } finally {
            Filex.deleteRecursively(file)
        }
    }

    @Test
    fun testOutputStream() {
        IOx.closeQuietly(IOx.writer(ByteArrayOutputStream(), Charset.forName("UTF-8")))
        IOx.closeQuietly(IOx.writer(ByteArrayOutputStream()))

        IOx.closeQuietly(IOx.buffered(ByteArrayOutputStream(), 1024 * 4))
        IOx.closeQuietly(IOx.buffered(ByteArrayOutputStream()))

        IOx.closeQuietly(IOx.buffered(IOx.writer(ByteArrayOutputStream()), 1024 * 4))
        IOx.closeQuietly(IOx.buffered(IOx.writer(ByteArrayOutputStream())))

        IOx.closeQuietly(IOx.bufferedWriter(ByteArrayOutputStream(), Charset.forName("UTF-8"), 1024 * 4))
        IOx.closeQuietly(IOx.bufferedWriter(ByteArrayOutputStream(), Charset.forName("UTF-8")))
        IOx.closeQuietly(IOx.bufferedWriter(ByteArrayOutputStream(), 1024 * 4))
        IOx.closeQuietly(IOx.bufferedWriter(ByteArrayOutputStream()))
    }

    @Test
    fun testCopyTo() {
        var inputStream = IOx.byteInputStream("1234567890")
        var outputStream = ByteArrayOutputStream()
        try {
            IOx.copyTo(inputStream, outputStream, 1024 * 4, object : CopyListener {
                override fun onUpdateProgress(completedLength: Long) {
                }

                override fun isCanceled(): Boolean = false
            })
        } finally {
            IOx.closeQuietly(inputStream)
            IOx.closeQuietly(outputStream)
        }

        inputStream = IOx.byteInputStream("1234567890")
        outputStream = ByteArrayOutputStream()
        try {
            IOx.copyTo(inputStream, outputStream, 1024 * 4)
        } finally {
            IOx.closeQuietly(inputStream)
            IOx.closeQuietly(outputStream)
        }

        inputStream = IOx.byteInputStream("1234567890")
        outputStream = ByteArrayOutputStream()
        try {
            IOx.copyTo(inputStream, outputStream, object : CopyListener {
                override fun onUpdateProgress(completedLength: Long) {
                }

                override fun isCanceled(): Boolean = false
            })
        } finally {
            IOx.closeQuietly(inputStream)
            IOx.closeQuietly(outputStream)
        }

        inputStream = IOx.byteInputStream("1234567890")
        outputStream = ByteArrayOutputStream()
        try {
            IOx.copyTo(inputStream, outputStream)
        } finally {
            IOx.closeQuietly(inputStream)
            IOx.closeQuietly(outputStream)
        }

        inputStream = IOx.byteInputStream("1234567890")
        outputStream = ByteArrayOutputStream()
        try {
            IOx.copyTo(IOx.reader(inputStream), IOx.writer(outputStream), 1024 * 4, object : CopyListener {
                override fun onUpdateProgress(completedLength: Long) {
                }

                override fun isCanceled(): Boolean = false
            })
        } finally {
            IOx.closeQuietly(inputStream)
            IOx.closeQuietly(outputStream)
        }

        inputStream = IOx.byteInputStream("1234567890")
        outputStream = ByteArrayOutputStream()
        try {
            IOx.copyTo(IOx.reader(inputStream), IOx.writer(outputStream), 1024 * 4)
        } finally {
            IOx.closeQuietly(inputStream)
            IOx.closeQuietly(outputStream)
        }

        inputStream = IOx.byteInputStream("1234567890")
        outputStream = ByteArrayOutputStream()
        try {
            IOx.copyTo(IOx.reader(inputStream), IOx.writer(outputStream), object : CopyListener {
                override fun onUpdateProgress(completedLength: Long) {
                }

                override fun isCanceled(): Boolean = false
            })
        } finally {
            IOx.closeQuietly(inputStream)
            IOx.closeQuietly(outputStream)
        }

        inputStream = IOx.byteInputStream("1234567890")
        outputStream = ByteArrayOutputStream()
        try {
            IOx.copyTo(IOx.reader(inputStream), IOx.writer(outputStream))
        } finally {
            IOx.closeQuietly(inputStream)
            IOx.closeQuietly(outputStream)
        }
    }
}
