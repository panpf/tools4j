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
package com.github.panpf.tools4j.zip

import com.github.panpf.tools4j.io.Filex
import com.github.panpf.tools4j.security.MessageDigestx
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
        val resultBytes = Zipx.compress(sourceBytes)
        Assert.assertTrue(resultBytes.size < sourceBytes.size)
        val newBytes = Zipx.decompress(resultBytes)
        Assert.assertEquals(newBytes.size.toLong(), sourceBytes.size.toLong())
        Assert.assertEquals(source, String(newBytes))
    }

    @Test
    fun testGzipBytes() {
        val source = "aabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfjaabbccddasmnvzxkfjufiowerfjzsmncvahffiwqksfj"
        val sourceBytes = source.toByteArray()
        val resultBytes = Zipx.gzipCompress(sourceBytes)
        Assert.assertTrue(resultBytes.size < sourceBytes.size)
        val newBytes = Zipx.gzipDecompress(resultBytes)
        Assert.assertEquals(newBytes.size.toLong(), sourceBytes.size.toLong())
        Assert.assertEquals(source, String(newBytes))
    }

    @Test
    @Throws(IOException::class)
    fun testFilesTo() {
        val dir1 = File("/tmp/testFilesTo1")
        try {
            val sourceDir: File = Filex.createFileTree(File(dir1, "test"), 3, 2, "file.txt", "testFilesTo")
            val sourceContents: String = Filex.listFilesRecursively(sourceDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            val compressDstFile = Zipx.getCompressDstFile(sourceDir)
            val compressProgressListener = ZipProgressListener()
            Zipx.compressFilesTo(sourceDir.listFiles(), compressDstFile, ZipEntryNameTransformer.createByParent(sourceDir), compressProgressListener)
            Assert.assertEquals(Zipx.listEntryName(compressDstFile).sortedWith(Filex.FilePathComparator()).joinToString(prefix = "[", postfix = "]"), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
            Assert.assertEquals(compressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")
            sourceDir.deleteRecursively()
            val decompressDstDir = File(compressDstFile.parentFile, Filex.getNameWithoutExtension(compressDstFile) + "1")
            val decompressProgressListener = ZipProgressListener()
            Zipx.decompressTo(compressDstFile, decompressDstDir, decompressProgressListener)
            Assert.assertEquals(decompressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")
            compressDstFile.deleteRecursively()
            val decompressContents: String = Filex.listFilesRecursively(decompressDstDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            dir1.deleteRecursively()
        }
        val dir2 = File("/tmp/testFilesTo2")
        try {
            val sourceDir: File = Filex.createFileTree(File(dir2, "test"), 3, 2, "file.txt", "testFilesTo")
            val sourceContents: String = Filex.listFilesRecursively(sourceDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            val compressDstFile = Zipx.getCompressDstFile(sourceDir)
            Zipx.compressFilesTo(sourceDir.listFiles(), compressDstFile, ZipEntryNameTransformer.createByParent(sourceDir))
            Assert.assertEquals(Zipx.listEntryName(compressDstFile).sortedWith(Filex.FilePathComparator()).joinToString(prefix = "[", postfix = "]"), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
            Filex.deleteRecursively(sourceDir)
            val decompressDstDir = File(compressDstFile.parentFile, Filex.getNameWithoutExtension(compressDstFile) + "1")
            Zipx.decompressTo(compressDstFile, decompressDstDir)
            Filex.deleteRecursively(compressDstFile)
            val decompressContents: String = Filex.listFilesRecursively(decompressDstDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            Filex.deleteRecursively(dir2)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testFileTo() {
        val dir1 = File("/tmp/testFileTo1")
        try {
            val sourceDir: File = Filex.createFileTree(File(dir1, "test"), 3, 2, "file.txt", "testFilesTo")
            val sourceContents: String = Filex.listFilesRecursively(sourceDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            val compressDstFile = Zipx.getCompressDstFile(sourceDir)
            val compressProgressListener = ZipProgressListener()
            Zipx.compressFileTo(sourceDir, compressDstFile, compressProgressListener)
            Assert.assertEquals(Zipx.listEntryName(compressDstFile).sortedWith(Filex.FilePathComparator()).joinToString(prefix = "[", postfix = "]"), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
            Assert.assertEquals(compressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")
            Filex.deleteRecursively(sourceDir)
            val decompressDstDir = File(compressDstFile.parentFile, Filex.getNameWithoutExtension(compressDstFile) + "1")
            val decompressProgressListener = ZipProgressListener()
            Zipx.decompressTo(compressDstFile, decompressDstDir, decompressProgressListener)
            Assert.assertEquals(decompressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")
            Filex.deleteRecursively(compressDstFile)
            val decompressContents: String = Filex.listFilesRecursively(decompressDstDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            Filex.deleteRecursively(dir1)
        }
        val file2 = File("/tmp/testFileTo2/file.txt")
        try {
            Filex.writeText(Filex.createNewFileOrThrow(file2), "testFilesTo")
            val sourceMd5: String = MessageDigestx.getMD5(file2)
            val compressDstFile = Zipx.getCompressDstFile(file2)
            Zipx.compressFileTo(file2, compressDstFile)
            Filex.deleteRecursively(file2)
            val decompressDstDir = Zipx.getDecompressDstDir(compressDstFile)
            Zipx.decompressTo(compressDstFile, decompressDstDir)
            val decompressMd5: String = MessageDigestx.getMD5(File(decompressDstDir, file2.name))
            Assert.assertEquals(sourceMd5, decompressMd5)
        } finally {
            Filex.deleteRecursively(file2.parentFile)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testFile() {
        val dir1 = File("/tmp/testFile1")
        try {
            val sourceDir: File = Filex.createFileTree(File(dir1, "test"), 3, 2, "file.txt", "testFilesTo")
            val sourceContents: String = Filex.listFilesRecursively(sourceDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            val compressProgressListener = ZipProgressListener()
            val compressDstFile = Zipx.compressFile(sourceDir, compressProgressListener)
            Assert.assertEquals(Zipx.listEntryName(compressDstFile).sortedWith(Filex.FilePathComparator()).joinToString(prefix = "[", postfix = "]"), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
            Assert.assertEquals(compressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")
            Filex.deleteRecursively(sourceDir)
            val decompressProgressListener = ZipProgressListener()
            val decompressDstDir = Zipx.decompress(compressDstFile, decompressProgressListener)
            Assert.assertEquals(decompressProgressListener.log, "[EntryStart: test/dir1/file1.txt, 15/180->test/dir1/file1.txt: 15/15, EntryEnd: test/dir1/file1.txt, EntryStart: test/dir1/file3.txt, 30/180->test/dir1/file3.txt: 15/15, EntryEnd: test/dir1/file3.txt, EntryStart: test/dir1/file2.txt, 45/180->test/dir1/file2.txt: 15/15, EntryEnd: test/dir1/file2.txt, EntryStart: test/dir3/file1.txt, 60/180->test/dir3/file1.txt: 15/15, EntryEnd: test/dir3/file1.txt, EntryStart: test/dir3/file3.txt, 75/180->test/dir3/file3.txt: 15/15, EntryEnd: test/dir3/file3.txt, EntryStart: test/dir3/file2.txt, 90/180->test/dir3/file2.txt: 15/15, EntryEnd: test/dir3/file2.txt, EntryStart: test/dir2/file1.txt, 105/180->test/dir2/file1.txt: 15/15, EntryEnd: test/dir2/file1.txt, EntryStart: test/dir2/file3.txt, 120/180->test/dir2/file3.txt: 15/15, EntryEnd: test/dir2/file3.txt, EntryStart: test/dir2/file2.txt, 135/180->test/dir2/file2.txt: 15/15, EntryEnd: test/dir2/file2.txt, EntryStart: test/file1.txt, 150/180->test/file1.txt: 15/15, EntryEnd: test/file1.txt, EntryStart: test/file3.txt, 165/180->test/file3.txt: 15/15, EntryEnd: test/file3.txt, EntryStart: test/file2.txt, 180/180->test/file2.txt: 15/15, EntryEnd: test/file2.txt]")
            Filex.deleteRecursively(compressDstFile)
            val decompressContents: String = Filex.listFilesRecursively(decompressDstDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            Filex.deleteRecursively(dir1)
        }
        val file2 = File("/tmp/testFile2/file.txt")
        try {
            Filex.writeText(Filex.createNewFileOrThrow(file2), "testFilesTo")
            val sourceMd5: String = MessageDigestx.getMD5(file2)
            val compressDstFile = Zipx.compressFile(file2)
            Filex.deleteRecursively(file2)
            val decompressDstDir = Zipx.decompress(compressDstFile)
            val decompressMd5: String = MessageDigestx.getMD5(File(decompressDstDir, file2.name))
            Assert.assertEquals(sourceMd5, decompressMd5)
        } finally {
            Filex.deleteRecursively(file2.parentFile)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testChildFileTo() {
        val dir1 = File("/tmp/testChildFileTo1")
        try {
            val sourceDir: File = Filex.createFileTree(File(dir1, "test"), 3, 2, "file.txt", "testChildFileTo")
            val sourceContents: String = Filex.listFilesRecursively(sourceDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            val compressDstFile = Zipx.getCompressDstFile(sourceDir)
            val compressProgressListener = ZipProgressListener()
            Zipx.compressChildFileTo(sourceDir, compressDstFile, compressProgressListener)
            Assert.assertEquals(Zipx.listEntryName(compressDstFile).sortedWith(Filex.FilePathComparator()).joinToString(prefix = "[", postfix = "]"), "[file1.txt, file2.txt, file3.txt, dir1/file1.txt, dir1/file2.txt, dir1/file3.txt, dir2/file1.txt, dir2/file2.txt, dir2/file3.txt, dir3/file1.txt, dir3/file2.txt, dir3/file3.txt]")
            Assert.assertEquals(compressProgressListener.log, "[EntryStart: dir1/file1.txt, 19/228->dir1/file1.txt: 19/19, EntryEnd: dir1/file1.txt, EntryStart: dir1/file3.txt, 38/228->dir1/file3.txt: 19/19, EntryEnd: dir1/file3.txt, EntryStart: dir1/file2.txt, 57/228->dir1/file2.txt: 19/19, EntryEnd: dir1/file2.txt, EntryStart: dir3/file1.txt, 76/228->dir3/file1.txt: 19/19, EntryEnd: dir3/file1.txt, EntryStart: dir3/file3.txt, 95/228->dir3/file3.txt: 19/19, EntryEnd: dir3/file3.txt, EntryStart: dir3/file2.txt, 114/228->dir3/file2.txt: 19/19, EntryEnd: dir3/file2.txt, EntryStart: dir2/file1.txt, 133/228->dir2/file1.txt: 19/19, EntryEnd: dir2/file1.txt, EntryStart: dir2/file3.txt, 152/228->dir2/file3.txt: 19/19, EntryEnd: dir2/file3.txt, EntryStart: dir2/file2.txt, 171/228->dir2/file2.txt: 19/19, EntryEnd: dir2/file2.txt, EntryStart: file1.txt, 190/228->file1.txt: 19/19, EntryEnd: file1.txt, EntryStart: file3.txt, 209/228->file3.txt: 19/19, EntryEnd: file3.txt, EntryStart: file2.txt, 228/228->file2.txt: 19/19, EntryEnd: file2.txt]")
            Filex.deleteRecursively(sourceDir)
            val decompressDstDir = File(compressDstFile.parentFile, Filex.getNameWithoutExtension(compressDstFile) + "1")
            val decompressProgressListener = ZipProgressListener()
            Zipx.decompressTo(compressDstFile, decompressDstDir, decompressProgressListener)
            Assert.assertEquals(decompressProgressListener.log, "[EntryStart: dir1/file1.txt, 19/228->dir1/file1.txt: 19/19, EntryEnd: dir1/file1.txt, EntryStart: dir1/file3.txt, 38/228->dir1/file3.txt: 19/19, EntryEnd: dir1/file3.txt, EntryStart: dir1/file2.txt, 57/228->dir1/file2.txt: 19/19, EntryEnd: dir1/file2.txt, EntryStart: dir3/file1.txt, 76/228->dir3/file1.txt: 19/19, EntryEnd: dir3/file1.txt, EntryStart: dir3/file3.txt, 95/228->dir3/file3.txt: 19/19, EntryEnd: dir3/file3.txt, EntryStart: dir3/file2.txt, 114/228->dir3/file2.txt: 19/19, EntryEnd: dir3/file2.txt, EntryStart: dir2/file1.txt, 133/228->dir2/file1.txt: 19/19, EntryEnd: dir2/file1.txt, EntryStart: dir2/file3.txt, 152/228->dir2/file3.txt: 19/19, EntryEnd: dir2/file3.txt, EntryStart: dir2/file2.txt, 171/228->dir2/file2.txt: 19/19, EntryEnd: dir2/file2.txt, EntryStart: file1.txt, 190/228->file1.txt: 19/19, EntryEnd: file1.txt, EntryStart: file3.txt, 209/228->file3.txt: 19/19, EntryEnd: file3.txt, EntryStart: file2.txt, 228/228->file2.txt: 19/19, EntryEnd: file2.txt]")
            Filex.deleteRecursively(compressDstFile)
            val decompressContents: String = Filex.listFilesRecursively(decompressDstDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            Filex.deleteRecursively(dir1)
        }
        val file2 = File("/tmp/testChildFileTo2/file.txt")
        try {
            Filex.writeText(Filex.createNewFileOrThrow(file2), "testChildFileTo")
            val sourceMd5: String = MessageDigestx.getMD5(file2)
            val compressDstFile = Zipx.getCompressDstFile(file2)
            Zipx.compressChildFileTo(file2, compressDstFile)
            Filex.deleteRecursively(file2)
            val decompressDstDir = Zipx.getDecompressDstDir(compressDstFile)
            Zipx.decompressTo(compressDstFile, decompressDstDir)
            val decompressMd5: String = MessageDigestx.getMD5(File(decompressDstDir, file2.name))
            Assert.assertEquals(sourceMd5, decompressMd5)
        } finally {
            Filex.deleteRecursively(file2.parentFile)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testChildFile() {
        val dir1 = File("/tmp/testChildFile1")
        try {
            val sourceDir: File = Filex.createFileTree(File(dir1, "test"), 3, 2, "file.txt", "testChildFile")
            val sourceContents: String = Filex.listFilesRecursively(sourceDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            val compressProgressListener = ZipProgressListener()
            val compressDstFile = Zipx.compressChildFile(sourceDir, compressProgressListener)
            Assert.assertEquals(Zipx.listEntryName(compressDstFile).sortedWith(Filex.FilePathComparator()).joinToString(prefix = "[", postfix = "]"), "[file1.txt, file2.txt, file3.txt, dir1/file1.txt, dir1/file2.txt, dir1/file3.txt, dir2/file1.txt, dir2/file2.txt, dir2/file3.txt, dir3/file1.txt, dir3/file2.txt, dir3/file3.txt]")
            Assert.assertEquals(compressProgressListener.log, "[EntryStart: dir1/file1.txt, 17/204->dir1/file1.txt: 17/17, EntryEnd: dir1/file1.txt, EntryStart: dir1/file3.txt, 34/204->dir1/file3.txt: 17/17, EntryEnd: dir1/file3.txt, EntryStart: dir1/file2.txt, 51/204->dir1/file2.txt: 17/17, EntryEnd: dir1/file2.txt, EntryStart: dir3/file1.txt, 68/204->dir3/file1.txt: 17/17, EntryEnd: dir3/file1.txt, EntryStart: dir3/file3.txt, 85/204->dir3/file3.txt: 17/17, EntryEnd: dir3/file3.txt, EntryStart: dir3/file2.txt, 102/204->dir3/file2.txt: 17/17, EntryEnd: dir3/file2.txt, EntryStart: dir2/file1.txt, 119/204->dir2/file1.txt: 17/17, EntryEnd: dir2/file1.txt, EntryStart: dir2/file3.txt, 136/204->dir2/file3.txt: 17/17, EntryEnd: dir2/file3.txt, EntryStart: dir2/file2.txt, 153/204->dir2/file2.txt: 17/17, EntryEnd: dir2/file2.txt, EntryStart: file1.txt, 170/204->file1.txt: 17/17, EntryEnd: file1.txt, EntryStart: file3.txt, 187/204->file3.txt: 17/17, EntryEnd: file3.txt, EntryStart: file2.txt, 204/204->file2.txt: 17/17, EntryEnd: file2.txt]")
            Filex.deleteRecursively(sourceDir)
            val decompressProgressListener = ZipProgressListener()
            val decompressDstDir = Zipx.decompress(compressDstFile, decompressProgressListener)
            Assert.assertEquals(decompressProgressListener.log, "[EntryStart: dir1/file1.txt, 17/204->dir1/file1.txt: 17/17, EntryEnd: dir1/file1.txt, EntryStart: dir1/file3.txt, 34/204->dir1/file3.txt: 17/17, EntryEnd: dir1/file3.txt, EntryStart: dir1/file2.txt, 51/204->dir1/file2.txt: 17/17, EntryEnd: dir1/file2.txt, EntryStart: dir3/file1.txt, 68/204->dir3/file1.txt: 17/17, EntryEnd: dir3/file1.txt, EntryStart: dir3/file3.txt, 85/204->dir3/file3.txt: 17/17, EntryEnd: dir3/file3.txt, EntryStart: dir3/file2.txt, 102/204->dir3/file2.txt: 17/17, EntryEnd: dir3/file2.txt, EntryStart: dir2/file1.txt, 119/204->dir2/file1.txt: 17/17, EntryEnd: dir2/file1.txt, EntryStart: dir2/file3.txt, 136/204->dir2/file3.txt: 17/17, EntryEnd: dir2/file3.txt, EntryStart: dir2/file2.txt, 153/204->dir2/file2.txt: 17/17, EntryEnd: dir2/file2.txt, EntryStart: file1.txt, 170/204->file1.txt: 17/17, EntryEnd: file1.txt, EntryStart: file3.txt, 187/204->file3.txt: 17/17, EntryEnd: file3.txt, EntryStart: file2.txt, 204/204->file2.txt: 17/17, EntryEnd: file2.txt]")
            Filex.deleteRecursively(compressDstFile)
            val decompressContents: String = Filex.listFilesRecursively(decompressDstDir)?.filter { it.isFile }?.joinToString(separator = "\n") { it.readText() }
                    ?: ""
            Assert.assertEquals(sourceContents, decompressContents)
        } finally {
            Filex.deleteRecursively(dir1)
        }
        val file2 = File("/tmp/testChildFile2/file.txt")
        try {
            Filex.writeText(Filex.createNewFileOrThrow(file2), "testChildFile")
            val sourceMd5: String = MessageDigestx.getMD5(file2)
            val compressDstFile = Zipx.compressChildFile(file2)
            Filex.deleteRecursively(file2)
            val decompressDstDir = Zipx.decompress(compressDstFile)
            val decompressMd5: String = MessageDigestx.getMD5(File(decompressDstDir, file2.name))
            Assert.assertEquals(sourceMd5, decompressMd5)
        } finally {
            Filex.deleteRecursively(file2.parentFile)
        }
    }

    @Test
    fun testGetCompressDstFile() {
        Assert.assertEquals("/tmp/testGetCompressDstFile.txt.zip", Zipx.getCompressDstFile(File("/tmp/testGetCompressDstFile.txt")).path)
    }

    @Test
    fun testGetDecompressDstFile() {
        Assert.assertEquals("/tmp", Zipx.getDecompressDstDir(File("/tmp/testGetCompressDstFile.zip")).path)
    }

    @Test
    @Throws(IOException::class)
    fun testTrueSize() {
        val dir1: File = Filex.createFileTree(File("/tmp/testTrueSize/test"), 3, 2, "file.txt", "testTrueSize")
        try {
            val compressDstFile = Zipx.compressFile(dir1)
            try {
                Assert.assertEquals(192, Zipx.getTrueSize(compressDstFile))
                ZipFile(compressDstFile).use { zipFile -> Assert.assertEquals(192, Zipx.getTrueSize(zipFile)) }
            } finally {
                Filex.deleteRecursively(compressDstFile)
            }
        } finally {
            Filex.deleteRecursively(dir1.parentFile)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testListEntry() {
        val dir1: File = Filex.createFileTree(File("/tmp/testListEntry/test"), 3, 2, "file.txt", "testListEntry")
        try {
            val compressDstFile = Zipx.compressFile(dir1)
            Assert.assertEquals(Zipx.listEntry(compressDstFile).map { zipEntry -> zipEntry.name }.sortedWith(Filex.FilePathComparator()).joinToString(prefix = "[", postfix = "]"), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
            Assert.assertEquals(Zipx.listEntry(ZipFile(compressDstFile)).map { zipEntry -> zipEntry.name }.sortedWith(Filex.FilePathComparator()).joinToString(prefix = "[", postfix = "]"), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
        } finally {
            Filex.deleteRecursively(dir1.parentFile)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testLisEntryName() {
        val dir1: File = Filex.createFileTree(File("/tmp/testLisEntryName/test"), 3, 2, "file.txt", "testListEntry")
        try {
            val compressDstFile = Zipx.compressFile(dir1)
            Assert.assertEquals(Zipx.listEntryName(compressDstFile).sortedWith(Filex.FilePathComparator()).joinToString(prefix = "[", postfix = "]"), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
            Assert.assertEquals(Zipx.listEntryName(ZipFile(compressDstFile)).sortedWith(Filex.FilePathComparator()).joinToString(prefix = "[", postfix = "]"), "[test/file1.txt, test/file2.txt, test/file3.txt, test/dir1/file1.txt, test/dir1/file2.txt, test/dir1/file3.txt, test/dir2/file1.txt, test/dir2/file2.txt, test/dir2/file3.txt, test/dir3/file1.txt, test/dir3/file2.txt, test/dir3/file3.txt]")
        } finally {
            Filex.deleteRecursively(dir1.parentFile)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testSize() {
        val dir1: File = Filex.createFileTree(File("/tmp/testSize/test"), 3, 2, "file.txt", "testListEntry")
        try {
            val compressDstFile = Zipx.compressFile(dir1)
            Assert.assertEquals(12, Zipx.size(compressDstFile).toLong())
        } finally {
            Filex.deleteRecursively(dir1.parentFile)
        }
    }

    private class ZipProgressListener : ZipListener {
        private val progress: MutableList<String> = ArrayList()
        override fun onEntryStart(zipEntry: ZipEntry) {
            progress.add("EntryStart: " + zipEntry.name)
        }

        override fun onUpdateProgress(totalLength: Long, totalCompletedLength: Long, zipEntry: ZipEntry, entryTotalLength: Long, entryCompletedLength: Long) {
            progress.add(totalCompletedLength.toString() + "/" + totalLength + "->" + zipEntry.name + ": " + entryCompletedLength + "/" + entryTotalLength)
        }

        override fun onEntryEnd(zipEntry: ZipEntry) {
            progress.add("EntryEnd: " + zipEntry.name)
        }

        override fun isCanceled(): Boolean {
            return false
        }

        val log: String
            get() = progress.joinToString(prefix = "[", postfix = "]")
    }
}