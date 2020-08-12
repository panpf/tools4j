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

package com.github.panpf.tools4j.zip.ktx

import com.github.panpf.tools4j.collections.Collectionx
import com.github.panpf.tools4j.collections.ktx.joinToArrayString
import com.github.panpf.tools4j.io.Filex
import com.github.panpf.tools4j.io.ktx.createFileTree
import com.github.panpf.tools4j.io.ktx.createNewFileOrThrow
import com.github.panpf.tools4j.io.ktx.listFilesRecursively
import com.github.panpf.tools4j.security.ktx.getMD5Digest
import com.github.panpf.tools4j.zip.ZipEntryNameTransformer
import com.github.panpf.tools4j.zip.ZipListener
import org.junit.Assert
import org.junit.Test
import java.io.File
import java.io.IOException
import java.util.*
import java.util.zip.DataFormatException
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

class ZipxTest {

    @Test
    @Throws(DataFormatException::class)
    fun testBytes() {
        val source = "aabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfj"
        val sourceBytes = source.toByteArray()

        val resultBytes = sourceBytes.zipCompress()
        Assert.assertTrue(resultBytes.size < sourceBytes.size)

        val newBytes = resultBytes.zipDecompress()
        Assert.assertEquals(newBytes.size.toLong(), sourceBytes.size.toLong())
        Assert.assertEquals(source, String(newBytes))
    }

    @Test
    fun testGzipBytes() {
        val source = "aabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfj"
        val sourceBytes = source.toByteArray()

        val resultBytes = sourceBytes.gzipCompress()
        Assert.assertTrue(resultBytes.size < sourceBytes.size)

        val newBytes = resultBytes.gzipDecompress()
        Assert.assertEquals(newBytes.size.toLong(), sourceBytes.size.toLong())
        Assert.assertEquals(source, String(newBytes))
    }

    @Test
    @Throws(IOException::class)
    fun testFilesTo() {
        val dir1 = File("/tmp/testFilesTo1")
        try {
            val sourceDir = File(dir1, "test").createFileTree(3, 2, "file.txt", "testFilesTo")
            val sourceContents = sourceDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""

            val compressDstFile = sourceDir.getZipCompressDstFile()
            val compressProgressListener = ZipProgressListener()
            sourceDir.listFiles().zipCompressFilesTo(compressDstFile, ZipEntryNameTransformer.createByParent(sourceDir), compressProgressListener)
            Assert.assertEquals(compressDstFile.listZipEntryName().sortedWith(FilePathComparator()).joinToArrayString(), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
            Assert.assertEquals(compressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")

            sourceDir.deleteRecursively()
            val decompressDstDir = File(compressDstFile.parentFile, compressDstFile.nameWithoutExtension + "1")
            val decompressProgressListener = ZipProgressListener()
            compressDstFile.zipDecompressTo(decompressDstDir, decompressProgressListener)
            Assert.assertEquals(decompressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")

            compressDstFile.deleteRecursively()
            val decompressContents = decompressDstDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            dir1.deleteRecursively()
        }

        val dir2 = File("/tmp/testFilesTo2")
        try {
            val sourceDir = File(dir2, "test").createFileTree(3, 2, "file.txt", "testFilesTo")
            val sourceContents = sourceDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""

            val compressDstFile = sourceDir.getZipCompressDstFile()
            sourceDir.listFiles().zipCompressFilesTo(compressDstFile, ZipEntryNameTransformer.createByParent(sourceDir))
            Assert.assertEquals(compressDstFile.listZipEntryName().sortedWith(FilePathComparator()).joinToArrayString(), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")

            Filex.deleteRecursively(sourceDir)
            val decompressDstDir = File(compressDstFile.parentFile, compressDstFile.nameWithoutExtension + "1")
            compressDstFile.zipDecompressTo(decompressDstDir)

            compressDstFile.deleteRecursively()
            val decompressContents = decompressDstDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            dir2.deleteRecursively()
        }

        val dir3 = File("/tmp/testFilesTo3")
        try {
            val sourceDir = File(dir3, "test").createFileTree(3, 2, "file.txt", "testFilesTo")
            val sourceContents = sourceDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""

            val compressDstFile = sourceDir.getZipCompressDstFile()
            val compressProgressListener = ZipProgressListener()
            sourceDir.listFiles().zipCompressFilesTo(compressDstFile, { t -> t.path.replace(sourceDir.parent + File.separator, "") }, compressProgressListener)
            Assert.assertEquals(compressDstFile.listZipEntryName().sortedWith(FilePathComparator()).joinToArrayString(), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
            Assert.assertEquals(compressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")

            sourceDir.deleteRecursively()
            val decompressDstDir = File(compressDstFile.parentFile, compressDstFile.nameWithoutExtension + "1")
            val decompressProgressListener = ZipProgressListener()
            compressDstFile.zipDecompressTo(decompressDstDir, decompressProgressListener)
            Assert.assertEquals(decompressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")

            compressDstFile.deleteRecursively()
            val decompressContents = decompressDstDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            dir3.deleteRecursively()
        }

        val dir4 = File("/tmp/testFilesTo4")
        try {
            val sourceDir = File(dir4, "test").createFileTree(3, 2, "file.txt", "testFilesTo")
            val sourceContents = sourceDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""

            val compressDstFile = sourceDir.getZipCompressDstFile()
            sourceDir.listFiles().zipCompressFilesTo(compressDstFile) { t -> t.path.replace(sourceDir.parent + File.separator, "") }
            Assert.assertEquals(compressDstFile.listZipEntryName().sortedWith(FilePathComparator()).joinToArrayString(), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")

            Filex.deleteRecursively(sourceDir)
            val decompressDstDir = File(compressDstFile.parentFile, compressDstFile.nameWithoutExtension + "1")
            compressDstFile.zipDecompressTo(decompressDstDir)

            compressDstFile.deleteRecursively()
            val decompressContents = decompressDstDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            dir4.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testFileTo() {
        val dir1 = File("/tmp/testFileTo1")
        try {
            val sourceDir = File(dir1, "test").createFileTree(3, 2, "file.txt", "testFilesTo")
            val sourceContents = sourceDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""

            val compressDstFile = sourceDir.getZipCompressDstFile()
            val compressProgressListener = ZipProgressListener()
            sourceDir.zipCompressFileTo(compressDstFile, compressProgressListener)
            Assert.assertEquals(compressDstFile.listZipEntryName().sortedWith(FilePathComparator()).joinToArrayString(), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
            Assert.assertEquals(compressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")

            sourceDir.deleteRecursively()
            val decompressDstDir = File(compressDstFile.parentFile, compressDstFile.nameWithoutExtension + "1")
            val decompressProgressListener = ZipProgressListener()
            compressDstFile.zipDecompressTo(decompressDstDir, decompressProgressListener)
            Assert.assertEquals(decompressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")

            Filex.deleteRecursively(compressDstFile)
            val decompressContents = decompressDstDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            dir1.deleteRecursively()
        }

        val file2 = File("/tmp/testFileTo2/file.txt")
        try {
            Filex.writeText(file2.createNewFileOrThrow(), "testFilesTo")
            val sourceMd5 = file2.getMD5Digest()

            val compressDstFile = file2.getZipCompressDstFile()
            file2.zipCompressFileTo(compressDstFile)

            file2.deleteRecursively()
            val decompressDstDir = compressDstFile.getZipDecompressDstDir()
            compressDstFile.zipDecompressTo(decompressDstDir)

            val decompressMd5 = File(decompressDstDir, file2.name).getMD5Digest()
            Assert.assertEquals(sourceMd5, decompressMd5)
        } finally {
            file2.parentFile.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testFile() {
        val dir1 = File("/tmp/testFile1")
        try {
            val sourceDir = File(dir1, "test").createFileTree(3, 2, "file.txt", "testFilesTo")
            val sourceContents = sourceDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""

            val compressProgressListener = ZipProgressListener()
            val compressDstFile = sourceDir.zipCompressFile(compressProgressListener)
            Assert.assertEquals(compressDstFile.listZipEntryName().sortedWith(FilePathComparator()).joinToArrayString(), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
            Assert.assertEquals(compressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")

            sourceDir.deleteRecursively()
            val decompressProgressListener = ZipProgressListener()
            val decompressDstDir = compressDstFile.zipDecompress(decompressProgressListener)
            Assert.assertEquals(decompressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")

            compressDstFile.deleteRecursively()
            val decompressContents = decompressDstDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            dir1.deleteRecursively()
        }

        val file2 = File("/tmp/testFile2/file.txt")
        try {
            file2.createNewFileOrThrow().writeText("testFilesTo")
            val sourceMd5 = file2.getMD5Digest()

            val compressDstFile = file2.zipCompressFile()

            file2.deleteRecursively()
            val decompressDstDir = compressDstFile.zipDecompress()

            val decompressMd5 = File(decompressDstDir, file2.name).getMD5Digest()
            Assert.assertEquals(sourceMd5, decompressMd5)
        } finally {
            file2.parentFile.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testChildFileTo() {
        val dir1 = File("/tmp/testChildFileTo1")
        try {
            val sourceDir = File(dir1, "test").createFileTree(3, 2, "file.txt", "testChildFileTo")
            val sourceContents = sourceDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""

            val compressDstFile = sourceDir.getZipCompressDstFile()
            val compressProgressListener = ZipProgressListener()
            sourceDir.zipCompressChildFileTo(compressDstFile, compressProgressListener)
            Assert.assertEquals(compressDstFile.listZipEntryName().sortedWith(FilePathComparator()).joinToArrayString(), "[file1.txt, file2.txt, file3.txt, dir1/file1.txt, dir1/file2.txt, dir1/file3.txt, dir2/file1.txt, dir2/file2.txt, dir2/file3.txt, dir3/file1.txt, dir3/file2.txt, dir3/file3.txt]")
            Assert.assertEquals(compressProgressListener.log, "[EntryStart: dir1/file1.txt, 19/228->dir1/file1.txt: 19/19, EntryEnd: dir1/file1.txt, EntryStart: dir1/file3.txt, 38/228->dir1/file3.txt: 19/19, EntryEnd: dir1/file3.txt, EntryStart: dir1/file2.txt, 57/228->dir1/file2.txt: 19/19, EntryEnd: dir1/file2.txt, EntryStart: dir3/file1.txt, 76/228->dir3/file1.txt: 19/19, EntryEnd: dir3/file1.txt, EntryStart: dir3/file3.txt, 95/228->dir3/file3.txt: 19/19, EntryEnd: dir3/file3.txt, EntryStart: dir3/file2.txt, 114/228->dir3/file2.txt: 19/19, EntryEnd: dir3/file2.txt, EntryStart: dir2/file1.txt, 133/228->dir2/file1.txt: 19/19, EntryEnd: dir2/file1.txt, EntryStart: dir2/file3.txt, 152/228->dir2/file3.txt: 19/19, EntryEnd: dir2/file3.txt, EntryStart: dir2/file2.txt, 171/228->dir2/file2.txt: 19/19, EntryEnd: dir2/file2.txt, EntryStart: file1.txt, 190/228->file1.txt: 19/19, EntryEnd: file1.txt, EntryStart: file3.txt, 209/228->file3.txt: 19/19, EntryEnd: file3.txt, EntryStart: file2.txt, 228/228->file2.txt: 19/19, EntryEnd: file2.txt]")

            sourceDir.deleteRecursively()
            val decompressDstDir = File(compressDstFile.parentFile, compressDstFile.nameWithoutExtension + "1")
            val decompressProgressListener = ZipProgressListener()
            compressDstFile.zipDecompressTo(decompressDstDir, decompressProgressListener)
            Assert.assertEquals(decompressProgressListener.log, "[EntryStart: dir1/file1.txt, 19/228->dir1/file1.txt: 19/19, EntryEnd: dir1/file1.txt, EntryStart: dir1/file3.txt, 38/228->dir1/file3.txt: 19/19, EntryEnd: dir1/file3.txt, EntryStart: dir1/file2.txt, 57/228->dir1/file2.txt: 19/19, EntryEnd: dir1/file2.txt, EntryStart: dir3/file1.txt, 76/228->dir3/file1.txt: 19/19, EntryEnd: dir3/file1.txt, EntryStart: dir3/file3.txt, 95/228->dir3/file3.txt: 19/19, EntryEnd: dir3/file3.txt, EntryStart: dir3/file2.txt, 114/228->dir3/file2.txt: 19/19, EntryEnd: dir3/file2.txt, EntryStart: dir2/file1.txt, 133/228->dir2/file1.txt: 19/19, EntryEnd: dir2/file1.txt, EntryStart: dir2/file3.txt, 152/228->dir2/file3.txt: 19/19, EntryEnd: dir2/file3.txt, EntryStart: dir2/file2.txt, 171/228->dir2/file2.txt: 19/19, EntryEnd: dir2/file2.txt, EntryStart: file1.txt, 190/228->file1.txt: 19/19, EntryEnd: file1.txt, EntryStart: file3.txt, 209/228->file3.txt: 19/19, EntryEnd: file3.txt, EntryStart: file2.txt, 228/228->file2.txt: 19/19, EntryEnd: file2.txt]")

            compressDstFile.deleteRecursively()
            val decompressContents = decompressDstDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            dir1.deleteRecursively()
        }

        val file2 = File("/tmp/testChildFileTo2/file.txt")
        try {
            file2.createNewFileOrThrow().writeText("testChildFileTo")
            val sourceMd5 = file2.getMD5Digest()

            val compressDstFile = file2.getZipCompressDstFile()
            file2.zipCompressChildFileTo(compressDstFile)

            file2.deleteRecursively()
            val decompressDstDir = compressDstFile.getZipDecompressDstDir()
            compressDstFile.zipDecompressTo(decompressDstDir)

            val decompressMd5 = File(decompressDstDir, file2.name).getMD5Digest()
            Assert.assertEquals(sourceMd5, decompressMd5)
        } finally {
            file2.parentFile.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testChildFile() {
        val dir1 = File("/tmp/testChildFile1")
        try {
            val sourceDir = File(dir1, "test").createFileTree(3, 2, "file.txt", "testChildFile")
            val sourceContents = sourceDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""

            val compressProgressListener = ZipProgressListener()
            val compressDstFile = sourceDir.zipCompressChildFile(compressProgressListener)
            Assert.assertEquals(compressDstFile.listZipEntryName().sortedWith(FilePathComparator()).joinToArrayString(), "[file1.txt, file2.txt, file3.txt, dir1/file1.txt, dir1/file2.txt, dir1/file3.txt, dir2/file1.txt, dir2/file2.txt, dir2/file3.txt, dir3/file1.txt, dir3/file2.txt, dir3/file3.txt]")
            Assert.assertEquals(compressProgressListener.log, "[EntryStart: dir1/file1.txt, 17/204->dir1/file1.txt: 17/17, EntryEnd: dir1/file1.txt, EntryStart: dir1/file3.txt, 34/204->dir1/file3.txt: 17/17, EntryEnd: dir1/file3.txt, EntryStart: dir1/file2.txt, 51/204->dir1/file2.txt: 17/17, EntryEnd: dir1/file2.txt, EntryStart: dir3/file1.txt, 68/204->dir3/file1.txt: 17/17, EntryEnd: dir3/file1.txt, EntryStart: dir3/file3.txt, 85/204->dir3/file3.txt: 17/17, EntryEnd: dir3/file3.txt, EntryStart: dir3/file2.txt, 102/204->dir3/file2.txt: 17/17, EntryEnd: dir3/file2.txt, EntryStart: dir2/file1.txt, 119/204->dir2/file1.txt: 17/17, EntryEnd: dir2/file1.txt, EntryStart: dir2/file3.txt, 136/204->dir2/file3.txt: 17/17, EntryEnd: dir2/file3.txt, EntryStart: dir2/file2.txt, 153/204->dir2/file2.txt: 17/17, EntryEnd: dir2/file2.txt, EntryStart: file1.txt, 170/204->file1.txt: 17/17, EntryEnd: file1.txt, EntryStart: file3.txt, 187/204->file3.txt: 17/17, EntryEnd: file3.txt, EntryStart: file2.txt, 204/204->file2.txt: 17/17, EntryEnd: file2.txt]")

            sourceDir.deleteRecursively()
            val decompressProgressListener = ZipProgressListener()
            val decompressDstDir = compressDstFile.zipDecompress(decompressProgressListener)
            Assert.assertEquals(decompressProgressListener.log, "[EntryStart: dir1/file1.txt, 17/204->dir1/file1.txt: 17/17, EntryEnd: dir1/file1.txt, EntryStart: dir1/file3.txt, 34/204->dir1/file3.txt: 17/17, EntryEnd: dir1/file3.txt, EntryStart: dir1/file2.txt, 51/204->dir1/file2.txt: 17/17, EntryEnd: dir1/file2.txt, EntryStart: dir3/file1.txt, 68/204->dir3/file1.txt: 17/17, EntryEnd: dir3/file1.txt, EntryStart: dir3/file3.txt, 85/204->dir3/file3.txt: 17/17, EntryEnd: dir3/file3.txt, EntryStart: dir3/file2.txt, 102/204->dir3/file2.txt: 17/17, EntryEnd: dir3/file2.txt, EntryStart: dir2/file1.txt, 119/204->dir2/file1.txt: 17/17, EntryEnd: dir2/file1.txt, EntryStart: dir2/file3.txt, 136/204->dir2/file3.txt: 17/17, EntryEnd: dir2/file3.txt, EntryStart: dir2/file2.txt, 153/204->dir2/file2.txt: 17/17, EntryEnd: dir2/file2.txt, EntryStart: file1.txt, 170/204->file1.txt: 17/17, EntryEnd: file1.txt, EntryStart: file3.txt, 187/204->file3.txt: 17/17, EntryEnd: file3.txt, EntryStart: file2.txt, 204/204->file2.txt: 17/17, EntryEnd: file2.txt]")

            compressDstFile.deleteRecursively()
            val decompressContents = decompressDstDir.listFilesRecursively()?.filter { it.isFile }?.joinToString("\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            dir1.deleteRecursively()
        }

        val file2 = File("/tmp/testChildFile2/file.txt")
        try {
            file2.createNewFileOrThrow().writeText("testChildFile")
            val sourceMd5 = file2.getMD5Digest()

            val compressDstFile = file2.zipCompressChildFile()

            file2.deleteRecursively()
            val decompressDstDir = compressDstFile.zipDecompress()

            val decompressMd5 = File(decompressDstDir, file2.name).getMD5Digest()
            Assert.assertEquals(sourceMd5, decompressMd5)
        } finally {
            file2.parentFile.deleteRecursively()
        }
    }

    @Test
    fun testGetCompressDstFile() {
        Assert.assertEquals("/tmp/testGetCompressDstFile.txt.zip", File("/tmp/testGetCompressDstFile.txt").getZipCompressDstFile().path)
    }

    @Test
    fun testGetDecompressDstFile() {
        Assert.assertEquals("/tmp", File("/tmp/testGetCompressDstFile.zip").getZipDecompressDstDir().path)
    }

    @Test
    @Throws(IOException::class)
    fun testTrueSize() {
        val dir1 = File("/tmp/testTrueSize/test").createFileTree(3, 2, "file.txt", "testTrueSize")
        try {
            val compressDstFile = dir1.zipCompressFile()
            try {
                Assert.assertEquals(192, compressDstFile.getZipTrueSize())
                ZipFile(compressDstFile).use { zipFile -> Assert.assertEquals(192, zipFile.getZipTrueSize()) }
            } finally {
                compressDstFile.deleteRecursively()
            }
        } finally {
            dir1.parentFile.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testListEntry() {
        val dir1 = File("/tmp/testListEntry/test").createFileTree(3, 2, "file.txt", "testListEntry")
        try {
            val compressDstFile = dir1.zipCompressFile()
            Assert.assertEquals(compressDstFile.listZipEntry().map { zipEntry -> zipEntry.name }.sortedWith(FilePathComparator()).joinToArrayString(), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")

            Assert.assertEquals(ZipFile(compressDstFile).listZipEntry().map { zipEntry -> zipEntry.name }.sortedWith(FilePathComparator()).joinToArrayString(), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
        } finally {
            dir1.parentFile.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testLisEntryName() {
        val dir1 = Filex.createFileTree(File("/tmp/testLisEntryName/test"), 3, 2, "file.txt", "testListEntry")
        try {
            val compressDstFile = dir1.zipCompressFile()
            Assert.assertEquals(compressDstFile.listZipEntryName().sortedWith(FilePathComparator()).joinToArrayString(), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")

            Assert.assertEquals(ZipFile(compressDstFile).listZipEntryName().sortedWith(FilePathComparator()).joinToArrayString(), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
        } finally {
            dir1.parentFile.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testSize() {
        val dir1 = File("/tmp/testSize/test").createFileTree(3, 2, "file.txt", "testListEntry")
        try {
            val compressDstFile = dir1.zipCompressFile()
            Assert.assertEquals(12, compressDstFile.zipSize().toLong())
        } finally {
            dir1.parentFile.deleteRecursively()
        }
    }

    private class ZipProgressListener : ZipListener {
        override fun isCanceled(): Boolean = false

        private val progress = ArrayList<String>()

        internal val log: String
            get() = Collectionx.joinToArrayString(progress)

        override fun onEntryStart(zipEntry: ZipEntry) {
            progress.add("EntryStart: " + zipEntry.name)
        }

        override fun onUpdateProgress(totalLength: Long, completedLength: Long, zipEntry: ZipEntry, entryTotalLength: Long, entryCompletedLength: Long) {
            progress.add(completedLength.toString() + "/" + totalLength + "->" + zipEntry.name + ": " + entryCompletedLength + "/" + entryTotalLength)
        }

        override fun onEntryEnd(zipEntry: ZipEntry) {
            progress.add("EntryEnd: " + zipEntry.name)
        }
    }

    class FilePathComparator : Comparator<String?> {
        override fun compare(o1: String?, o2: String?): Int {
            return Filex.compareFilePath(o1, o2)
        }
    }
}
