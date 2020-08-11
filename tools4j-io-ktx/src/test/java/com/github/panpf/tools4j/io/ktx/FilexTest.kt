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

import org.junit.Assert
import org.junit.Test
import java.io.File
import java.io.FileNotFoundException

class FilexTest {

    @Test
    fun testCreateNewFile() {
        val dir = File("/tmp/testCreateNewFile1")
        try {
            val file1 = File(dir, "test1.txt")
            Assert.assertFalse(file1.exists())
            Assert.assertTrue(file1.createNewFileOrThrow().exists())
            Assert.assertTrue(file1.createNewFileOrThrow().exists())
        } finally {
            dir.deleteRecursively()
        }

        val dir2 = File("/tmp/testCreateNewFile2")
        try {
            val file2 = File(dir2, "test2.txt")
            Assert.assertFalse(file2.exists())
            Assert.assertTrue(file2.createNewFileOrCheck())
            Assert.assertTrue(file2.createNewFileOrCheck())
        } finally {
            dir2.deleteRecursively()
        }
    }

    @Test
    fun tesMkdirsDir() {
        val dir1 = File("/tmp/tesMkdirsDir")
        try {
            Assert.assertFalse(dir1.exists())
            Assert.assertTrue(dir1.mkdirsOrThrow().exists())
            Assert.assertTrue(dir1.mkdirsOrThrow().exists())
        } finally {
            dir1.deleteRecursively()
        }

        val dir2 = File("/tmp/tesMkdirsDir2")
        try {
            Assert.assertFalse(dir2.exists())
            Assert.assertTrue(dir2.mkdirsOrCheck())
            Assert.assertTrue(dir2.mkdirsOrCheck())
        } finally {
            dir2.deleteRecursively()
        }
    }

    @Test
    fun testEnsureFileNotExist() {
        val file = File("/tmp/testEnsureFileNotExist/source.txt.tmp")

        try {
            file.parentFile.deleteRecursively()
            Assert.assertEquals("/tmp/testEnsureFileNotExist/source.txt.tmp", file.ensureFileNotExist(false, 100).path)
        } finally {
            file.parentFile.deleteRecursively()
        }

        try {
            file.parentFile.deleteRecursively()
            file.createNewFileOrThrow()
            Assert.assertEquals("/tmp/testEnsureFileNotExist/source.txt-1.tmp", file.ensureFileNotExist(false, 100).path)
        } finally {
            file.parentFile.deleteRecursively()
        }

        try {
            file.parentFile.deleteRecursively()
            file.createNewFileOrThrow()
            Assert.assertEquals("/tmp/testEnsureFileNotExist/source-1.txt.tmp", file.ensureFileNotExist(true, 100).path)
        } finally {
            file.parentFile.deleteRecursively()
        }

        try {
            file.parentFile.deleteRecursively()
            file.createNewFileOrThrow()
            file.ensureFileNotExist(true, 100).createNewFileOrThrow()
            file.ensureFileNotExist(true, 100).createNewFileOrThrow()
            file.ensureFileNotExist(true, 100).createNewFileOrThrow()
            file.ensureFileNotExist(true, 100).createNewFileOrThrow()
            file.ensureFileNotExist(true, 100).createNewFileOrThrow()
            file.ensureFileNotExist(true, 100).createNewFileOrThrow()
            file.ensureFileNotExist(true, 100).createNewFileOrThrow()
            file.ensureFileNotExist(true, 100).createNewFileOrThrow()
            file.ensureFileNotExist(true, 100).createNewFileOrThrow()
            Assert.assertEquals("/tmp/testEnsureFileNotExist/source-9.txt.tmp", file.ensureFileNotExist(true, 9).path)
            Assert.fail("No Exception")
        } catch (e: IllegalStateException) {
        } finally {
            file.parentFile.deleteRecursively()
        }

        try {
            file.parentFile.deleteRecursively()
            file.createNewFileOrThrow()
            Assert.assertEquals("/tmp/testEnsureFileNotExist/source-1.txt.tmp", file.ensureFileNotExist(true, 0).path)
        } finally {
            file.parentFile.deleteRecursively()
        }

        try {
            file.parentFile.deleteRecursively()
            file.createNewFileOrThrow()
            Assert.assertEquals("/tmp/testEnsureFileNotExist/source.txt-1.tmp", file.ensureFileNotExist(100).path)
        } finally {
            file.parentFile.deleteRecursively()
        }

        try {
            file.parentFile.deleteRecursively()
            file.ensureFileNotExist(100).createNewFileOrThrow()
            file.ensureFileNotExist(100).createNewFileOrThrow()
            file.ensureFileNotExist(100).createNewFileOrThrow()
            Assert.assertEquals("/tmp/testEnsureFileNotExist/source.txt-1.tmp", file.ensureFileNotExist(2).path)
            Assert.fail("No Exception")
        } catch (e: IllegalStateException) {
        } finally {
            file.parentFile.deleteRecursively()
        }

        try {
            file.parentFile.deleteRecursively()
            file.createNewFileOrThrow()
            Assert.assertEquals("/tmp/testEnsureFileNotExist/source-1.txt.tmp", file.ensureFileNotExist(true).path)
        } finally {
            file.parentFile.deleteRecursively()
        }

        try {
            file.parentFile.deleteRecursively()
            file.createNewFileOrThrow()
            Assert.assertEquals("/tmp/testEnsureFileNotExist/source.txt-1.tmp", file.ensureFileNotExist(false).path)
        } finally {
            file.parentFile.deleteRecursively()
        }

        try {
            file.parentFile.deleteRecursively()
            file.createNewFileOrThrow()
            Assert.assertEquals("/tmp/testEnsureFileNotExist/source.txt-1.tmp", file.ensureFileNotExist().path)
        } finally {
            file.parentFile.deleteRecursively()
        }

        val dir = File("/tmp/testEnsureFileNotExist/testDir")
        try {
            dir.parentFile.deleteRecursively()
            dir.mkdirsOrThrow()
            Assert.assertEquals("${dir.path}-1", dir.ensureFileNotExist().path)
        } finally {
            dir.parentFile.deleteRecursively()
        }
    }

    // todo add ensurDirNotExist test method

    @Test
    fun testClean() {
        /*
         * clean
         */
        val dir = File("/tmp/testClean").createFileTree(3, 3, "file.txt", "testClean")
        try {
            Assert.assertTrue(dir.exists())
            Assert.assertEquals(dir.listFiles()?.count() ?: 0, 6)
            dir.clean()
            Assert.assertTrue(dir.exists())
            Assert.assertEquals(dir.listFiles()?.count() ?: 0, 3)
        } finally {
            dir.deleteRecursively()
        }

        // is File
        val file = File("/tmp/testClean.file").createNewFileOrThrow()
        try {
            Assert.assertTrue(file.clean())
        } finally {
            file.deleteRecursively()
        }

        // not exists
        val fileNotExists = File("/tmp/testClean.not_exists.file")
        try {
            Assert.assertTrue(fileNotExists.clean())
        } finally {
            fileNotExists.deleteRecursively()
        }
    }

    @Test
    fun testCleanRecursively() {
        val dir = File("/tmp/testCleanRecursively").createFileTree(3, 3, "file.txt", "testCleanRecursively")
        try {
            Assert.assertTrue(dir.exists())
            Assert.assertEquals(dir.listCountRecursively(), 51)
            dir.cleanRecursively()
            Assert.assertTrue(dir.exists())
            Assert.assertEquals(dir.listCountRecursively(), 0)
        } finally {
            dir.deleteRecursively()
        }

        // is File
        val file = File("/tmp/testCleanRecursively.file").createNewFileOrThrow()
        try {
            Assert.assertTrue(file.cleanRecursively())
        } finally {
            file.deleteRecursively()
        }

        // not exists
        val fileNotExists = File("/tmp/testCleanRecursively.not_exists.file")
        try {
            Assert.assertTrue(fileNotExists.cleanRecursively())
        } finally {
            fileNotExists.deleteRecursively()
        }
    }

    @Test
    fun testLengthRecursively() {
        val dir = File("/tmp/testLengthRecursively")
        try {
            val childFile1 = File(dir, "test1.txt")
            childFile1.createNewFileOrThrow()
            childFile1.writeText("111")
            Assert.assertEquals(childFile1.length(), 3)

            val childFile2 = File(dir, "test2.txt")
            childFile2.createNewFileOrThrow()
            childFile2.writeText("111")
            Assert.assertEquals(childFile2.length(), 3)

            Assert.assertEquals(dir.lengthRecursively(), 6)
            Assert.assertEquals(arrayOf(dir).lengthRecursively(), 6)
            Assert.assertEquals(listOf(dir).lengthRecursively(), 6)
        } finally {
            dir.deleteRecursively()
        }
    }

    @Test
    fun testListRecursively() {
        val dir = File("/tmp/testListRecursively").createFileTree(3, 3, "file.txt", "testListRecursively")

        try {
            val childPaths = dir.listRecursively()
            Assert.assertEquals(childPaths?.count() ?: 0, 51)
            Assert.assertTrue(childPaths?.contains("file1.txt") ?: false)
            Assert.assertTrue(childPaths?.contains("dir1") ?: false)
            Assert.assertTrue(childPaths?.contains("dir1/file1.txt") ?: false)
            Assert.assertTrue(childPaths?.contains("dir1/dir1/file1.txt") ?: false)
            Assert.assertTrue(childPaths?.contains("dir1/dir2/file1.txt") ?: false)
            Assert.assertTrue(childPaths?.contains("dir1/dir3/file1.txt") ?: false)
            Assert.assertTrue(childPaths?.contains("dir2") ?: false)
            Assert.assertTrue(childPaths?.contains("dir2/dir1/file1.txt") ?: false)
            Assert.assertTrue(childPaths?.contains("dir2/dir2/file1.txt") ?: false)
            Assert.assertTrue(childPaths?.contains("dir2/dir3/file1.txt") ?: false)
            Assert.assertTrue(childPaths?.contains("dir3") ?: false)
            Assert.assertTrue(childPaths?.contains("dir3/dir1/file1.txt") ?: false)
            Assert.assertTrue(childPaths?.contains("dir3/dir2/file1.txt") ?: false)
            Assert.assertTrue(childPaths?.contains("dir3/dir3/file1.txt") ?: false)

            Assert.assertEquals((dir.listRecursively { pathname -> pathname.isFile }?.size ?: 0).toLong(), 39)
            Assert.assertEquals((dir.listRecursively { dir2, name -> File(dir2, name).isDirectory }?.size
                    ?: 0).toLong(), 12)

            val childFiles = dir.listFilesRecursively()
            Assert.assertEquals(childFiles?.count() ?: 0, 51)
            Assert.assertTrue(childFiles?.contains(File(dir, "file1.txt")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir1")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir1/file1.txt")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir1/dir1/file1.txt")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir1/dir2/file1.txt")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir1/dir3/file1.txt")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir2")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir2/dir1/file1.txt")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir2/dir2/file1.txt")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir2/dir3/file1.txt")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir3")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir3/dir1/file1.txt")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir3/dir2/file1.txt")) ?: false)
            Assert.assertTrue(childFiles?.contains(File(dir, "dir3/dir3/file1.txt")) ?: false)

            Assert.assertEquals((dir.listFilesRecursively { pathname -> pathname.isFile }?.size
                    ?: 0).toLong(), 39)
            Assert.assertEquals((dir.listFilesRecursively { dir2, name -> File(dir2, name).isDirectory }?.size
                    ?: 0).toLong(), 12)
        } finally {
            dir.deleteRecursively()
        }

        // is file
        val file = File("/tmp/testListRecursively.file").createNewFileOrThrow()
        try {
            Assert.assertNull(file.listRecursively())
            Assert.assertNull(file.listFilesRecursively())
        } finally {
            file.deleteRecursively()
        }

        // not exists
        val fileNotExists = File("/tmp/testListRecursively.not_exists.file")
        try {
            Assert.assertNull(fileNotExists.listRecursively())
            Assert.assertNull(fileNotExists.listFilesRecursively())
        } finally {
            fileNotExists.deleteRecursively()
        }
    }

    @Test
    fun testListCount() {
        val dir = File("/tmp/testListCount").createFileTree(3, 3, "file.txt", "testListCount")

        try {
            Assert.assertEquals(dir.listCount(), 6)
            Assert.assertEquals(dir.listCount { pathname -> pathname.isFile }, 3)
            Assert.assertEquals(dir.listCount { dir2, name -> File(dir2, name).isDirectory }, 3)
        } finally {
            dir.deleteRecursively()
        }

        // is file
        val file = File("/tmp/testListCount.file").createNewFileOrThrow()
        try {
            Assert.assertEquals(file.listCount(), 0)
        } finally {
            file.deleteRecursively()
        }

        // not exists
        val fileNotExists = File("/tmp/testListCount.not_exists.file")
        try {
            Assert.assertEquals(fileNotExists.listCount(), 0)
        } finally {
            fileNotExists.deleteRecursively()
        }
    }

    @Test
    fun testListCountRecursively() {
        val dir = File("/tmp/testListCountRecursively").createFileTree(3, 3, "file.txt", "testListCountRecursively")

        try {
            Assert.assertEquals(dir.listCountRecursively(), 51)
            Assert.assertEquals(dir.listCountRecursively { pathname -> pathname.isFile }, 39)
            Assert.assertEquals(dir.listCountRecursively { dir2, name -> File(dir2, name).isDirectory }, 12)
        } finally {
            dir.deleteRecursively()
        }

        // is file
        val file = File("/tmp/testListCountRecursively.file").createNewFileOrThrow()
        try {
            Assert.assertEquals(file.listCountRecursively(), 0)
        } finally {
            file.deleteRecursively()
        }

        // not exists
        val fileNotExists = File("/tmp/testListCountRecursively.not_exists.file")
        try {
            Assert.assertEquals(fileNotExists.listCountRecursively(), 0)
        } finally {
            fileNotExists.deleteRecursively()
        }
    }

    @Test
    fun testAllExtension() {
        Assert.assertEquals("txt", File("/tmp/testExtension.txt").allExtension)
        Assert.assertEquals("txt.zip", File("/tmp/testExtension.txt.zip").allExtension)
        Assert.assertEquals("", File("/tmp/testExtension").allExtension)
        Assert.assertEquals("txt", File("/tmp/.txt").allExtension)
    }

    @Test
    fun testNameWithoutAllExtension() {
        Assert.assertEquals("testExtension", File("/tmp/testExtension.txt").nameWithoutAllExtension)
        Assert.assertEquals("testExtension", File("/tmp/testExtension.txt.zip").nameWithoutAllExtension)
        Assert.assertEquals("testExtension", File("/tmp/testExtension").nameWithoutAllExtension)
        Assert.assertEquals("", File("/tmp/.txt").nameWithoutAllExtension)
    }

    @Test
    fun testCreateFileTree() {
        val dir = File("/tmp/testCreateFileTree")
        try {
            dir.createFileTree(3, 3, "file.txt", "testCreateFileTree")
            Assert.assertEquals(dir.listCountRecursively(), 51)
            Assert.assertEquals(dir.listCountRecursively { pathname -> pathname.isFile }, 39)
            Assert.assertEquals(dir.listCountRecursively { dir2, name -> File(dir2, name).isDirectory }, 12)
        } finally {
            dir.deleteRecursively()
        }
    }

    @Test
    fun testCompareFilePath() {
        Assert.assertEquals(File("/a/b/c").compareFilePath(File("/a/b")), 1)
        Assert.assertEquals(File("/a/c").compareFilePath(File("/a/b")), 1)
        Assert.assertEquals(File("/a/b").compareFilePath(null), 1)

        Assert.assertEquals(File("/a/b").compareFilePath(File("/a/b/c")), -1)
        Assert.assertEquals(File("/a/b").compareFilePath(File("/a/c")), -1)
        Assert.assertEquals(null.compareFilePath(File("/a/b")), -1)

        Assert.assertEquals(File("").compareFilePath(File("")), 0)
        Assert.assertEquals(File("/a/b").compareFilePath(File("/a/b")), 0)
        Assert.assertEquals((null as File?).compareFilePath(null as File?), 0)

        Assert.assertEquals("/a/b/c".compareFilePath("/a/b"), 1)
        Assert.assertEquals("/a/c".compareFilePath("/a/b"), 1)
        Assert.assertEquals("/a/b".compareFilePath(null), 1)

        Assert.assertEquals("/a/b".compareFilePath("/a/b/c"), -1)
        Assert.assertEquals("/a/b".compareFilePath("/a/c"), -1)
        Assert.assertEquals(null.compareFilePath("/a/b"), -1)

        Assert.assertEquals("".compareFilePath(""), 0)
        Assert.assertEquals("/a/b".compareFilePath("/a/b"), 0)
        Assert.assertEquals((null as String?).compareFilePath(null as String?), 0)
    }

    @Test
    fun testFormatFileSize() {
        Assert.assertEquals((-10L).formatFileSize(), "0 B")
        Assert.assertEquals(0L.formatFileSize(2, false), "0 B")
        Assert.assertEquals(999L.formatFileSize(), "999 B")

        Assert.assertEquals((999L + 1).formatFileSize(), "0.98 KB")
        Assert.assertEquals(1024L.formatFileSize(), "1 KB")
        Assert.assertEquals((800L + 1024).formatFileSize(), "1.78 KB")
        Assert.assertEquals((800L + 1024 * 500).formatFileSize(), "500.78 KB")
        Assert.assertEquals((1024L * 999).formatFileSize(), "999 KB")

        Assert.assertEquals((1024L * 999 + 1).formatFileSize(2, false), "0.98 MB")
        Assert.assertEquals((1024L * 1024).formatFileSize(true), "1.00 MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024).formatFileSize(), "1.49 MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024 * 500).formatFileSize(), "500.49 MB")
        Assert.assertEquals((1024L * 1024 * 999).formatFileSize(), "999 MB")

        Assert.assertEquals((1024L * 1024 * 999 + 1).formatFileSize(), "0.98 GB")
        Assert.assertEquals((1024L * 1024 * 1024).formatFileSize(), "1 GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024).formatFileSize(), "1.49 GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500).formatFileSize(), "500.49 GB")
        Assert.assertEquals((1024L * 1024 * 1024 * 999).formatFileSize(), "999 GB")

        Assert.assertEquals((1024L * 1024 * 1024 * 999 + 1).formatFileSize(), "0.98 TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024).formatFileSize(), "1 TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024).formatFileSize(), "1.49 TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500).formatFileSize(), "500.49 TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 999).formatFileSize(), "999 TB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 999 + 1).formatFileSize(), "0.98 PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024).formatFileSize(), "1 PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024).formatFileSize(), "1.49 PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500).formatFileSize(), "500.49 PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 999).formatFileSize(), "999 PB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 999 + 1).formatFileSize(), "0.98 EB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatFileSize(), "1 EB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatFileSize(), "1.49 EB")

        Assert.assertEquals((-10L).formatCompactFileSize(), "0B")
        Assert.assertEquals(0L.formatCompactFileSize(2, false), "0B")
        Assert.assertEquals(999L.formatCompactFileSize(), "999B")

        Assert.assertEquals((999L + 1).formatCompactFileSize(), "0.98KB")
        Assert.assertEquals(1024L.formatCompactFileSize(), "1KB")
        Assert.assertEquals((800L + 1024).formatCompactFileSize(), "1.78KB")
        Assert.assertEquals((800L + 1024 * 500).formatCompactFileSize(), "500.78KB")
        Assert.assertEquals((1024L * 999).formatCompactFileSize(), "999KB")

        Assert.assertEquals((1024L * 999 + 1).formatCompactFileSize(2, false), "0.98MB")
        Assert.assertEquals((1024L * 1024).formatCompactFileSize(true), "1.00MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024).formatCompactFileSize(), "1.49MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024 * 500).formatCompactFileSize(), "500.49MB")
        Assert.assertEquals((1024L * 1024 * 999).formatCompactFileSize(), "999MB")

        Assert.assertEquals((1024L * 1024 * 999 + 1).formatCompactFileSize(), "0.98GB")
        Assert.assertEquals((1024L * 1024 * 1024).formatCompactFileSize(), "1GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024).formatCompactFileSize(), "1.49GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500).formatCompactFileSize(), "500.49GB")
        Assert.assertEquals((1024L * 1024 * 1024 * 999).formatCompactFileSize(), "999GB")

        Assert.assertEquals((1024L * 1024 * 1024 * 999 + 1).formatCompactFileSize(), "0.98TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024).formatCompactFileSize(), "1TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024).formatCompactFileSize(), "1.49TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500).formatCompactFileSize(), "500.49TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 999).formatCompactFileSize(), "999TB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 999 + 1).formatCompactFileSize(), "0.98PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024).formatCompactFileSize(), "1PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024).formatCompactFileSize(), "1.49PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500).formatCompactFileSize(), "500.49PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 999).formatCompactFileSize(), "999PB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 999 + 1).formatCompactFileSize(), "0.98EB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatCompactFileSize(), "1EB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatCompactFileSize(), "1.49EB")
    }

    @Test
    fun tesFormatMediumSize() {
        Assert.assertEquals((0L).formatMediumFileSize(), "0 B")
        Assert.assertEquals((800L).formatMediumFileSize(), "800 B")

        Assert.assertEquals((1024L).formatMediumFileSize(), "1 KB")
        Assert.assertEquals((800L + 1024).formatMediumFileSize(), "1.8 KB")
        Assert.assertEquals((800L + 1024 * 500).formatMediumFileSize(), "500.8 KB")

        Assert.assertEquals((1024L * 1024).formatMediumFileSize(true), "1.0 MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024).formatMediumFileSize(), "1.5 MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024 * 500).formatMediumFileSize(), "500.5 MB")

        Assert.assertEquals((1024L * 1024 * 1024).formatMediumFileSize(), "1 GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024).formatMediumFileSize(), "1.5 GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500).formatMediumFileSize(), "500.5 GB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024).formatMediumFileSize(), "1 TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024).formatMediumFileSize(), "1.5 TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500).formatMediumFileSize(), "500.5 TB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024).formatMediumFileSize(), "1 PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024).formatMediumFileSize(), "1.5 PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500).formatMediumFileSize(), "500.5 PB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatMediumFileSize(), "1 EB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatMediumFileSize(), "1.5 EB")

        Assert.assertEquals((0L).formatMediumCompactFileSize(), "0B")
        Assert.assertEquals((800L).formatMediumCompactFileSize(), "800B")

        Assert.assertEquals((1024L).formatMediumCompactFileSize(), "1KB")
        Assert.assertEquals((800L + 1024).formatMediumCompactFileSize(), "1.8KB")
        Assert.assertEquals((800L + 1024 * 500).formatMediumCompactFileSize(), "500.8KB")

        Assert.assertEquals((1024L * 1024).formatMediumCompactFileSize(true), "1.0MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024).formatMediumCompactFileSize(), "1.5MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024 * 500).formatMediumCompactFileSize(), "500.5MB")

        Assert.assertEquals((1024L * 1024 * 1024).formatMediumCompactFileSize(), "1GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024).formatMediumCompactFileSize(), "1.5GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500).formatMediumCompactFileSize(), "500.5GB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024).formatMediumCompactFileSize(), "1TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024).formatMediumCompactFileSize(), "1.5TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500).formatMediumCompactFileSize(), "500.5TB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024).formatMediumCompactFileSize(), "1PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024).formatMediumCompactFileSize(), "1.5PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500).formatMediumCompactFileSize(), "500.5PB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatMediumCompactFileSize(), "1EB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatMediumCompactFileSize(), "1.5EB")
    }

    @Test
    fun testFormatShortFileSize() {
        Assert.assertEquals((0L).formatShortFileSize(), "0 B")
        Assert.assertEquals((800L).formatShortFileSize(), "800 B")

        Assert.assertEquals((1024L).formatShortFileSize(), "1 KB")
        Assert.assertEquals((800L + 1024).formatShortFileSize(), "2 KB")
        Assert.assertEquals((800L + 1024 * 500).formatShortFileSize(), "501 KB")

        Assert.assertEquals((1024L * 1024).formatShortFileSize(), "1 MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024).formatShortFileSize(), "1 MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024 * 500).formatShortFileSize(), "500 MB")

        Assert.assertEquals((1024L * 1024 * 1024).formatShortFileSize(), "1 GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024).formatShortFileSize(), "1 GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500).formatShortFileSize(), "500 GB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024).formatShortFileSize(), "1 TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024).formatShortFileSize(), "1 TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500).formatShortFileSize(), "500 TB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024).formatShortFileSize(), "1 PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024).formatShortFileSize(), "1 PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500).formatShortFileSize(), "500 PB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatShortFileSize(), "1 EB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatShortFileSize(), "1 EB")

        Assert.assertEquals((0L).formatShortCompactFileSize(), "0B")
        Assert.assertEquals((800L).formatShortCompactFileSize(), "800B")

        Assert.assertEquals((1024L).formatShortCompactFileSize(), "1KB")
        Assert.assertEquals((800L + 1024).formatShortCompactFileSize(), "2KB")
        Assert.assertEquals((800L + 1024 * 500).formatShortCompactFileSize(), "501KB")

        Assert.assertEquals((1024L * 1024).formatShortCompactFileSize(), "1MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024).formatShortCompactFileSize(), "1MB")
        Assert.assertEquals((1024 * 500 + 1024L * 1024 * 500).formatShortCompactFileSize(), "500MB")

        Assert.assertEquals((1024L * 1024 * 1024).formatShortCompactFileSize(), "1GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024).formatShortCompactFileSize(), "1GB")
        Assert.assertEquals((1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500).formatShortCompactFileSize(), "500GB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024).formatShortCompactFileSize(), "1TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024).formatShortCompactFileSize(), "1TB")
        Assert.assertEquals((1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500).formatShortCompactFileSize(), "500TB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024).formatShortCompactFileSize(), "1PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024).formatShortCompactFileSize(), "1PB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500).formatShortCompactFileSize(), "500PB")

        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatShortCompactFileSize(), "1EB")
        Assert.assertEquals((1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024).formatShortCompactFileSize(), "1EB")
    }

    @Test
    fun testRequireExist() {
        val testFile = File("/tmp/testRequireFileExist")
        testFile.delete()

        Assert.assertFalse(try {
            testFile.requireExist()
            true
        } catch (e: FileNotFoundException) {
            false
        })

        testFile.createNewFileOrCheck()

        Assert.assertTrue(try {
            testFile.requireExist()
            true
        } catch (e: FileNotFoundException) {
            false
        })

        testFile.delete()
    }

    @Test
    fun testRequireIsDir() {
        val testDir = File("/tmp/testRequireIsDir")

        // 不存在时
        testDir.deleteRecursively()
        Assert.assertFalse(try {
            testDir.requireIsDir()
            true
        } catch (e: IllegalArgumentException) {
            false
        })

        // 文件
        testDir.createNewFileOrCheck()
        Assert.assertFalse(try {
            testDir.requireIsDir()
            true
        } catch (e: IllegalArgumentException) {
            false
        })

        // 目录
        testDir.deleteRecursively()
        testDir.mkdirsOrCheck()
        Assert.assertTrue(try {
            testDir.requireIsDir()
            true
        } catch (e: IllegalArgumentException) {
            false
        })

        testDir.deleteRecursively()
    }

    @Test
    fun testRequireIsFile() {
        val testFile = File("/tmp/testRequireIsFile")

        // 不存在时
        testFile.deleteRecursively()
        Assert.assertFalse(try {
            testFile.requireIsFile()
            true
        } catch (e: IllegalArgumentException) {
            false
        })

        // 目录
        testFile.mkdirsOrCheck()
        Assert.assertFalse(try {
            testFile.requireIsFile()
            true
        } catch (e: IllegalArgumentException) {
            false
        })

        // 文件
        testFile.deleteRecursively()
        testFile.createNewFileOrCheck()
        Assert.assertTrue(try {
            testFile.requireIsFile()
            true
        } catch (e: IllegalArgumentException) {
            false
        })

        testFile.deleteRecursively()
    }


    @Test
    fun testComponents() {
        var file = File("/tmp/testRoot")
        var components = file.toComponents()
        Assert.assertTrue(components.isRooted)
        Assert.assertEquals(components.rootName, "/")
        Assert.assertEquals(components.root.path, "/")
        Assert.assertEquals(components.size, 2)
        Assert.assertEquals(components.segments.joinToString(prefix = "[", postfix = "]"), "[tmp, testRoot]")

        file = File("tmp/testRoot/file")
        components = file.toComponents()
        Assert.assertFalse(components.isRooted)
        Assert.assertEquals(components.rootName, "")
        Assert.assertEquals(components.root.path, "")
        Assert.assertEquals(components.size, 3)
        Assert.assertEquals(components.segments.joinToString(prefix = "[", postfix = "]"), "[tmp, testRoot, file]")
    }

    @Test
    fun testSubPath() {
        Assert.assertEquals(File("/tmp/testSubPath").subPath(1, 2).path, "testSubPath")

        try {
            File("/tmp/testSubPath").subPath(-1, 2)
            Assert.fail()
        } catch (e: Exception) {
        }

        try {
            File("/tmp/testSubPath").subPath(3, 2)
            Assert.fail()
        } catch (e: Exception) {
        }

        try {
            File("/tmp/testSubPath").subPath(1, 3)
            Assert.fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testInputStream() {
        val file = File("/tmp/testInputStream.txt")
        file.writeText("testInputStream")
        try {
            file.bufferedInputStream(1024 * 4).closeQuietly()
            file.bufferedInputStream().closeQuietly()
        } finally {
            file.deleteRecursively()
        }
    }

    @Test
    fun testOutputStream() {
        val file = File("/tmp/testOutputStream.txt")
        file.writeText("testOutputStream")
        try {
            file.bufferedOutputStream(1024 * 4).closeQuietly()
            file.bufferedOutputStream().closeQuietly()
        } finally {
            file.deleteRecursively()
        }
    }
}