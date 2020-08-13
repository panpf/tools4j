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

import com.github.panpf.tools4j.security.ktx.getMD5Digest
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

class FilexTest {

    @Test
    fun testCreateNewFile() {
        val dir = File("/tmp/testCreateNewFile1")
        try {
            val file1 = File(dir, "test1.txt")
            assertFalse(file1.exists())
            assertTrue(Filex.createNewFileOrThrow(file1).exists())
            assertTrue(Filex.createNewFileOrThrow(file1).exists())
        } finally {
            Filex.deleteRecursively(dir)
        }

        val dir2 = File("/tmp/testCreateNewFile2")
        try {
            val file2 = File(dir2, "test2.txt")
            assertFalse(file2.exists())
            assertTrue(Filex.createNewFileOrCheck(file2))
            assertTrue(Filex.createNewFileOrCheck(file2))
        } finally {
            Filex.deleteRecursively(dir2)
        }
    }

    @Test
    fun tesMkdirsDir() {
        val dir1 = File("/tmp/tesMkdirsDir")
        try {
            assertFalse(dir1.exists())
            assertTrue(Filex.mkdirsOrThrow(dir1).exists())
            assertTrue(Filex.mkdirsOrThrow(dir1).exists())
        } finally {
            Filex.deleteRecursively(dir1)
        }

        val dir2 = File("/tmp/tesMkdirsDir2")
        try {
            assertFalse(dir2.exists())
            assertTrue(Filex.mkdirsOrCheck(dir2))
            assertTrue(Filex.mkdirsOrCheck(dir2))
        } finally {
            Filex.deleteRecursively(dir2)
        }
    }

    @Test
    fun testEnsureFileNotExist() {
        val file = File("/tmp/testEnsureFileNotExist/source.txt.tmp")

        try {
            Filex.deleteRecursively(file.parentFile)
            assertEquals("/tmp/testEnsureFileNotExist/source.txt.tmp", Filex.ensureFileNotExist(file, false, 100).path)
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        try {
            Filex.deleteRecursively(file.parentFile)
            Filex.createNewFileOrThrow(file)
            assertEquals("/tmp/testEnsureFileNotExist/source.txt-1.tmp", Filex.ensureFileNotExist(file, false, 100).path)
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        try {
            Filex.deleteRecursively(file.parentFile)
            Filex.createNewFileOrThrow(file)
            assertEquals("/tmp/testEnsureFileNotExist/source-1.txt.tmp", Filex.ensureFileNotExist(file, true, 100).path)
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        try {
            Filex.deleteRecursively(file.parentFile)
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, true, 100))
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, true, 100))
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, true, 100))
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, true, 100))
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, true, 100))
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, true, 100))
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, true, 100))
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, true, 100))
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, true, 100))
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, true, 100))
            assertEquals("/tmp/testEnsureFileNotExist/source-9.txt.tmp", Filex.ensureFileNotExist(file, true, 9).path)
            fail("No Exception")
        } catch (e: IllegalStateException) {
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        try {
            Filex.deleteRecursively(file.parentFile)
            Filex.createNewFileOrThrow(file)
            assertEquals("/tmp/testEnsureFileNotExist/source-1.txt.tmp", Filex.ensureFileNotExist(file, true, 0).path)
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        try {
            Filex.deleteRecursively(file.parentFile)
            Filex.createNewFileOrThrow(file)
            assertEquals("/tmp/testEnsureFileNotExist/source.txt-1.tmp", Filex.ensureFileNotExist(file, 100).path)
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        try {
            Filex.deleteRecursively(file.parentFile)
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, 100))
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, 100))
            Filex.createNewFileOrThrow(Filex.ensureFileNotExist(file, 100))
            assertEquals("/tmp/testEnsureFileNotExist/source.txt-1.tmp", Filex.ensureFileNotExist(file, 2).path)
            fail("No Exception")
        } catch (e: IllegalStateException) {
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        try {
            Filex.deleteRecursively(file.parentFile)
            Filex.createNewFileOrThrow(file)
            assertEquals("/tmp/testEnsureFileNotExist/source-1.txt.tmp", Filex.ensureFileNotExist(file, true).path)
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        try {
            Filex.deleteRecursively(file.parentFile)
            Filex.createNewFileOrThrow(file)
            assertEquals("/tmp/testEnsureFileNotExist/source.txt-1.tmp", Filex.ensureFileNotExist(file, false).path)
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        try {
            Filex.deleteRecursively(file.parentFile)
            Filex.createNewFileOrThrow(file)
            assertEquals("/tmp/testEnsureFileNotExist/source.txt-1.tmp", Filex.ensureFileNotExist(file).path)
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        val dir = File("/tmp/testEnsureFileNotExist/testDir")
        try {
            Filex.deleteRecursively(dir.parentFile)
            Filex.mkdirsOrThrow(dir)
            assertEquals("${dir.path}-1", Filex.ensureFileNotExist(dir).path)
        } finally {
            Filex.deleteRecursively(dir.parentFile)
        }
    }

    @Test
    fun testEnsureDirNotExist() {
        val testDir = File("/tmp/testEnsureDirNotExist/source.txt.tmp")

        try {
            Filex.deleteRecursively(testDir.parentFile)
            assertEquals("/tmp/testEnsureDirNotExist/source.txt.tmp", Filex.ensureDirNotExist(testDir, 100).path)
        } finally {
            Filex.deleteRecursively(testDir.parentFile)
        }

        try {
            Filex.deleteRecursively(testDir.parentFile)
            Filex.mkdirsOrThrow(testDir)
            assertEquals("/tmp/testEnsureDirNotExist/source.txt.tmp-1", Filex.ensureDirNotExist(testDir, 100).path)
        } finally {
            Filex.deleteRecursively(testDir.parentFile)
        }

        try {
            Filex.deleteRecursively(testDir.parentFile)
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            assertEquals("/tmp/testEnsureDirNotExist/source.txt.tmp-9", Filex.ensureDirNotExist(testDir, 9).path)
            fail("No Exception")
        } catch (e: IllegalStateException) {
        } finally {
            Filex.deleteRecursively(testDir.parentFile)
        }

        try {
            Filex.deleteRecursively(testDir.parentFile)
            Filex.mkdirsOrThrow(testDir)
            assertEquals("/tmp/testEnsureDirNotExist/source.txt.tmp-1", Filex.ensureDirNotExist(testDir, 0).path)
        } finally {
            Filex.deleteRecursively(testDir.parentFile)
        }

        try {
            Filex.deleteRecursively(testDir.parentFile)
            Filex.mkdirsOrThrow(testDir)
            assertEquals("/tmp/testEnsureDirNotExist/source.txt.tmp-1", Filex.ensureDirNotExist(testDir, 100).path)
        } finally {
            Filex.deleteRecursively(testDir.parentFile)
        }

        try {
            Filex.deleteRecursively(testDir.parentFile)
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            Filex.mkdirsOrThrow(Filex.ensureDirNotExist(testDir, 100))
            assertEquals("/tmp/testEnsureDirNotExist/source.txt.tmp-1", Filex.ensureDirNotExist(testDir, 2).path)
            fail("No Exception")
        } catch (e: IllegalStateException) {
        } finally {
            Filex.deleteRecursively(testDir.parentFile)
        }

        try {
            Filex.deleteRecursively(testDir.parentFile)
            Filex.mkdirsOrThrow(testDir)
            assertEquals("/tmp/testEnsureDirNotExist/source.txt.tmp-1", Filex.ensureDirNotExist(testDir).path)
        } finally {
            Filex.deleteRecursively(testDir.parentFile)
        }

        val testDir2 = File("/tmp/testEnsureDirNotExist/testDir")
        try {
            Filex.deleteRecursively(testDir2.parentFile)
            Filex.mkdirsOrThrow(testDir2)
            assertEquals("/tmp/testEnsureDirNotExist/testDir-1", Filex.ensureDirNotExist(testDir2).path)
        } finally {
            Filex.deleteRecursively(testDir2.parentFile)
        }
    }

    @Test
    fun testClean() {
        /*
         * clean
         */
        val dir = Filex.createFileTree(File("/tmp/testClean"), 3, 3, "file.txt", "testClean")
        try {
            assertTrue(dir.exists())
            assertEquals(dir.listFiles()?.count() ?: 0, 6)
            Filex.clean(dir)
            assertTrue(dir.exists())
            assertEquals(dir.listFiles()?.count() ?: 0, 3)
        } finally {
            Filex.deleteRecursively(dir)
        }

        // is File
        val file = Filex.createNewFileOrThrow(File("/tmp/testClean.file"))
        try {
            assertTrue(Filex.clean(file))
        } finally {
            Filex.deleteRecursively(file)
        }

        // not exists
        val fileNotExists = File("/tmp/testClean.not_exists.file")
        try {
            assertTrue(Filex.clean(fileNotExists))
        } finally {
            Filex.deleteRecursively(fileNotExists)
        }
    }

    @Test
    fun testCleanRecursively() {
        val dir = Filex.createFileTree(File("/tmp/testCleanRecursively"), 3, 3, "file.txt", "testCleanRecursively")
        try {
            assertTrue(dir.exists())
            assertEquals(Filex.listCountRecursively(dir), 51)
            Filex.cleanRecursively(dir)
            assertTrue(dir.exists())
            assertEquals(Filex.listCountRecursively(dir), 0)
        } finally {
            Filex.deleteRecursively(dir)
        }

        // is File
        val file = Filex.createNewFileOrThrow(File("/tmp/testCleanRecursively.file"))
        try {
            assertTrue(Filex.cleanRecursively(file))
        } finally {
            Filex.deleteRecursively(file)
        }

        // not exists
        val fileNotExists = File("/tmp/testCleanRecursively.not_exists.file")
        try {
            assertTrue(Filex.cleanRecursively(fileNotExists))
        } finally {
            Filex.deleteRecursively(fileNotExists)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testLengthRecursively() {
        val dir = File("/tmp/testLengthRecursively")
        try {
            val childFile1 = File(dir, "test1.txt")
            Filex.createNewFileOrThrow(childFile1)
            Filex.writeText(childFile1, "111")
            assertEquals(childFile1.length(), 3)

            val childFile2 = File(dir, "test2.txt")
            Filex.createNewFileOrThrow(childFile2)
            Filex.writeText(childFile2, "111")
            assertEquals(childFile2.length(), 3)

            assertEquals(Filex.lengthRecursively(dir), 6)
            assertEquals(Filex.lengthRecursively(arrayOf(dir)), 6)
            assertEquals(Filex.lengthRecursively(mutableListOf(dir)), 6)
        } finally {
            Filex.deleteRecursively(dir)
        }
    }

    @Test
    fun testListRecursively() {
        val dir = Filex.createFileTree(File("/tmp/testListRecursively"), 3, 3, "file.txt", "testListRecursively")

        try {
            val childPaths = requireNotNull(Filex.listRecursively(dir))
            assertEquals(childPaths.count(), 51)
            assertTrue(childPaths.contains("file1.txt"))
            assertTrue(childPaths.contains("dir1"))
            assertTrue(childPaths.contains("dir1/file1.txt"))
            assertTrue(childPaths.contains("dir1/dir1/file1.txt"))
            assertTrue(childPaths.contains("dir1/dir2/file1.txt"))
            assertTrue(childPaths.contains("dir1/dir3/file1.txt"))
            assertTrue(childPaths.contains("dir2"))
            assertTrue(childPaths.contains("dir2/dir1/file1.txt"))
            assertTrue(childPaths.contains("dir2/dir2/file1.txt"))
            assertTrue(childPaths.contains("dir2/dir3/file1.txt"))
            assertTrue(childPaths.contains("dir3"))
            assertTrue(childPaths.contains("dir3/dir1/file1.txt"))
            assertTrue(childPaths.contains("dir3/dir2/file1.txt"))
            assertTrue(childPaths.contains("dir3/dir3/file1.txt"))

            assertEquals((Filex.listRecursively(dir) { pathname -> pathname.isFile }?.size ?: 0).toLong(), 39)
            assertEquals((Filex.listRecursively(dir) { dir2, name -> File(dir2, name).isDirectory }?.size
                    ?: 0).toLong(), 12)

            val childFiles = requireNotNull(Filex.listFilesRecursively(dir))
            assertEquals(childFiles.count(), 51)
            assertTrue(childFiles.contains(File(dir, "file1.txt")))
            assertTrue(childFiles.contains(File(dir, "dir1")))
            assertTrue(childFiles.contains(File(dir, "dir1/file1.txt")))
            assertTrue(childFiles.contains(File(dir, "dir1/dir1/file1.txt")))
            assertTrue(childFiles.contains(File(dir, "dir1/dir2/file1.txt")))
            assertTrue(childFiles.contains(File(dir, "dir1/dir3/file1.txt")))
            assertTrue(childFiles.contains(File(dir, "dir2")))
            assertTrue(childFiles.contains(File(dir, "dir2/dir1/file1.txt")))
            assertTrue(childFiles.contains(File(dir, "dir2/dir2/file1.txt")))
            assertTrue(childFiles.contains(File(dir, "dir2/dir3/file1.txt")))
            assertTrue(childFiles.contains(File(dir, "dir3")))
            assertTrue(childFiles.contains(File(dir, "dir3/dir1/file1.txt")))
            assertTrue(childFiles.contains(File(dir, "dir3/dir2/file1.txt")))
            assertTrue(childFiles.contains(File(dir, "dir3/dir3/file1.txt")))

            assertEquals((Filex.listFilesRecursively(dir) { pathname -> pathname.isFile }?.size
                    ?: 0).toLong(), 39)
            assertEquals((Filex.listFilesRecursively(dir) { dir2, name -> File(dir2, name).isDirectory }?.size
                    ?: 0).toLong(), 12)
        } finally {
            Filex.deleteRecursively(dir)
        }

        // is file
        val file = Filex.createNewFileOrThrow(File("/tmp/testListRecursively.file"))
        try {
            assertNull(Filex.listRecursively(file))
            assertNull(Filex.listFilesRecursively(file))
        } finally {
            Filex.deleteRecursively(file)
        }

        // not exists
        val fileNotExists = File("/tmp/testListRecursively.not_exists.file")
        try {
            assertNull(Filex.listRecursively(fileNotExists))
            assertNull(Filex.listFilesRecursively(fileNotExists))
        } finally {
            Filex.deleteRecursively(fileNotExists)
        }
    }

    @Test
    fun testListCount() {
        val dir = Filex.createFileTree(File("/tmp/testListCount"), 3, 3, "file.txt", "testListCount")

        try {
            assertEquals(Filex.listCount(dir), 6)
            assertEquals(Filex.listCount(dir) { pathname -> pathname.isFile }, 3)
            assertEquals(Filex.listCount(dir) { dir2, name -> File(dir2, name).isDirectory }, 3)
        } finally {
            Filex.deleteRecursively(dir)
        }

        // is file
        val file = Filex.createNewFileOrThrow(File("/tmp/testListCount.file"))
        try {
            assertEquals(Filex.listCount(file), 0)
        } finally {
            Filex.deleteRecursively(file)
        }

        // not exists
        val fileNotExists = File("/tmp/testListCount.not_exists.file")
        try {
            assertEquals(Filex.listCount(fileNotExists), 0)
        } finally {
            Filex.deleteRecursively(fileNotExists)
        }
    }

    @Test
    fun testListCountRecursively() {
        val dir = Filex.createFileTree(File("/tmp/testListCountRecursively"), 3, 3, "file.txt", "testListCountRecursively")

        try {
            assertEquals(Filex.listCountRecursively(dir), 51)
            assertEquals(Filex.listCountRecursively(dir) { pathname -> pathname.isFile }, 39)
            assertEquals(Filex.listCountRecursively(dir) { dir2, name -> File(dir2, name).isDirectory }, 12)
        } finally {
            Filex.deleteRecursively(dir)
        }

        // is file
        val file = Filex.createNewFileOrThrow(File("/tmp/testListCountRecursively.file"))
        try {
            assertEquals(Filex.listCountRecursively(file), 0)
        } finally {
            Filex.deleteRecursively(file)
        }

        // not exists
        val fileNotExists = File("/tmp/testListCountRecursively.not_exists.file")
        try {
            assertEquals(Filex.listCountRecursively(fileNotExists), 0)
        } finally {
            Filex.deleteRecursively(fileNotExists)
        }
    }

    @Test
    fun testAllExtension() {
        assertEquals("txt", Filex.getAllExtension(File("/tmp/testExtension.txt")))
        assertEquals("txt.zip", Filex.getAllExtension(File("/tmp/testExtension.txt.zip")))
        assertEquals("", Filex.getAllExtension(File("/tmp/testExtension")))
        assertEquals("txt", Filex.getAllExtension(File("/tmp/.txt")))
    }

    @Test
    fun testNameWithoutAllExtension() {
        assertEquals("testExtension", Filex.getNameWithoutAllExtension(File("/tmp/testExtension.txt")))
        assertEquals("testExtension", Filex.getNameWithoutAllExtension(File("/tmp/testExtension.txt.zip")))
        assertEquals("testExtension", Filex.getNameWithoutAllExtension(File("/tmp/testExtension")))
        assertEquals("", Filex.getNameWithoutAllExtension(File("/tmp/.txt")))
    }

    @Test
    fun testCreateFileTree() {
        val dir = File("/tmp/testCreateFileTree")
        try {
            Filex.createFileTree(dir, 3, 3, "file.txt", "testCreateFileTree")
            assertEquals(Filex.listCountRecursively(dir), 51)
            assertEquals(Filex.listCountRecursively(dir) { pathname -> pathname.isFile }, 39)
            assertEquals(Filex.listCountRecursively(dir) { dir2, name -> File(dir2, name).isDirectory }, 12)
        } finally {
            Filex.deleteRecursively(dir)
        }
    }

    @Test
    fun testCompareFilePath() {
        assertEquals(Filex.compareFilePath(File("/a/b/c"), File("/a/b")), 1)
        assertEquals(Filex.compareFilePath(File("/a/c"), File("/a/b")), 1)
        assertEquals(Filex.compareFilePath(File("/a/b"), null), 1)

        assertEquals(Filex.compareFilePath(File("/a/b"), File("/a/b/c")), -1)
        assertEquals(Filex.compareFilePath(File("/a/b"), File("/a/c")), -1)
        assertEquals(Filex.compareFilePath(null, File("/a/b")), -1)

        assertEquals(Filex.compareFilePath(File(""), File("")), 0)
        assertEquals(Filex.compareFilePath(File("/a/b"), File("/a/b")), 0)
        assertEquals(Filex.compareFilePath(null as File?, null as File?), 0)

        assertEquals(Filex.compareFilePath("/a/b/c", "/a/b"), 1)
        assertEquals(Filex.compareFilePath("/a/c", "/a/b"), 1)
        assertEquals(Filex.compareFilePath("/a/b", null), 1)

        assertEquals(Filex.compareFilePath("/a/b", "/a/b/c"), -1)
        assertEquals(Filex.compareFilePath("/a/b", "/a/c"), -1)
        assertEquals(Filex.compareFilePath(null, "/a/b"), -1)

        assertEquals(Filex.compareFilePath("", ""), 0)
        assertEquals(Filex.compareFilePath("/a/b", "/a/b"), 0)
        assertEquals(Filex.compareFilePath(null as String?, null as String?), 0)
    }


    @Test
    fun testFormatFileSize() {
        assertEquals(Filex.formatFileSize(-10L), "0 B")
        assertEquals(Filex.formatFileSize(0L), "0 B")
        assertEquals(Filex.formatFileSize(999), "999 B")
        assertEquals(Filex.formatFileSize(999 + 1), "0.98 KB")
        assertEquals(Filex.formatFileSize(1024), "1 KB")
        assertEquals(Filex.formatFileSize(800 + 1024), "1.78 KB")
        assertEquals(Filex.formatFileSize(800 + 1024 * 500), "500.78 KB")
        assertEquals(Filex.formatFileSize(1024L * 999), "999 KB")
        assertEquals(Filex.formatFileSize(1024L * 999 + 1, 2, false), "0.98 MB")
        assertEquals(Filex.formatFileSize(1024L * 1024, true), "1.00 MB")
        assertEquals(Filex.formatFileSize(1024 * 500 + 1024L * 1024), "1.49 MB")
        assertEquals(Filex.formatFileSize(1024 * 500 + 1024L * 1024 * 500), "500.49 MB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 999), "999 MB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 999 + 1), "0.98 GB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024), "1 GB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024), "1.49 GB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500), "500.49 GB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 999), "999 GB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 999 + 1), "0.98 TB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 1024), "1 TB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024), "1.49 TB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500), "500.49 TB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 1024 * 999), "999 TB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 1024 * 999 + 1), "0.98 PB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 1024 * 1024), "1 PB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024), "1.49 PB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500), "500.49 PB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 999), "999 PB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 999 + 1), "0.98 EB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1 EB")
        assertEquals(Filex.formatFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1.49 EB")
        assertEquals(Filex.formatCompactFileSize(-10L), "0B")
        assertEquals(Filex.formatCompactFileSize(0L), "0B")
        assertEquals(Filex.formatCompactFileSize(999), "999B")
        assertEquals(Filex.formatCompactFileSize(999 + 1), "0.98KB")
        assertEquals(Filex.formatCompactFileSize(1024), "1KB")
        assertEquals(Filex.formatCompactFileSize(800 + 1024), "1.78KB")
        assertEquals(Filex.formatCompactFileSize(800 + 1024 * 500), "500.78KB")
        assertEquals(Filex.formatCompactFileSize(1024L * 999), "999KB")
        assertEquals(Filex.formatCompactFileSize(1024L * 999 + 1, 2, false), "0.98MB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024, true), "1.00MB")
        assertEquals(Filex.formatCompactFileSize(1024 * 500 + 1024L * 1024), "1.49MB")
        assertEquals(Filex.formatCompactFileSize(1024 * 500 + 1024L * 1024 * 500), "500.49MB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 999), "999MB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 999 + 1), "0.98GB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024), "1GB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024), "1.49GB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500), "500.49GB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 999), "999GB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 999 + 1), "0.98TB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 1024), "1TB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024), "1.49TB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500), "500.49TB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 1024 * 999), "999TB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 1024 * 999 + 1), "0.98PB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 1024 * 1024), "1PB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024), "1.49PB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500), "500.49PB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 999), "999PB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 999 + 1), "0.98EB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1EB")
        assertEquals(Filex.formatCompactFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1.49EB")
    }

    @Test
    fun tesFormatMediumSize() {
        assertEquals(Filex.formatMediumFileSize(0), "0 B")
        assertEquals(Filex.formatMediumFileSize(800), "800 B")
        assertEquals(Filex.formatMediumFileSize(1024), "1 KB")
        assertEquals(Filex.formatMediumFileSize(800 + 1024), "1.8 KB")
        assertEquals(Filex.formatMediumFileSize(800 + 1024 * 500), "500.8 KB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024, true), "1.0 MB")
        assertEquals(Filex.formatMediumFileSize(1024 * 500 + 1024L * 1024), "1.5 MB")
        assertEquals(Filex.formatMediumFileSize(1024 * 500 + 1024L * 1024 * 500), "500.5 MB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024 * 1024), "1 GB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024), "1.5 GB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500), "500.5 GB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024 * 1024 * 1024), "1 TB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024), "1.5 TB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500), "500.5 TB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024 * 1024 * 1024 * 1024), "1 PB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024), "1.5 PB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500), "500.5 PB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1 EB")
        assertEquals(Filex.formatMediumFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1.5 EB")
        assertEquals(Filex.formatMediumCompactFileSize(0), "0B")
        assertEquals(Filex.formatMediumCompactFileSize(800), "800B")
        assertEquals(Filex.formatMediumCompactFileSize(1024), "1KB")
        assertEquals(Filex.formatMediumCompactFileSize(800 + 1024), "1.8KB")
        assertEquals(Filex.formatMediumCompactFileSize(800 + 1024 * 500), "500.8KB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024, true), "1.0MB")
        assertEquals(Filex.formatMediumCompactFileSize(1024 * 500 + 1024L * 1024), "1.5MB")
        assertEquals(Filex.formatMediumCompactFileSize(1024 * 500 + 1024L * 1024 * 500), "500.5MB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024 * 1024), "1GB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024), "1.5GB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500), "500.5GB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024 * 1024 * 1024), "1TB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024), "1.5TB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500), "500.5TB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024 * 1024 * 1024 * 1024), "1PB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024), "1.5PB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500), "500.5PB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1EB")
        assertEquals(Filex.formatMediumCompactFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1.5EB")
    }

    @Test
    fun testFormatShortFileSize() {
        assertEquals(Filex.formatShortFileSize(0), "0 B")
        assertEquals(Filex.formatShortFileSize(800), "800 B")
        assertEquals(Filex.formatShortFileSize(1024), "1 KB")
        assertEquals(Filex.formatShortFileSize(800 + 1024), "2 KB")
        assertEquals(Filex.formatShortFileSize(800 + 1024 * 500), "501 KB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024), "1 MB")
        assertEquals(Filex.formatShortFileSize(1024 * 500 + 1024L * 1024), "1 MB")
        assertEquals(Filex.formatShortFileSize(1024 * 500 + 1024L * 1024 * 500), "500 MB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024 * 1024), "1 GB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024), "1 GB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500), "500 GB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024 * 1024 * 1024), "1 TB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024), "1 TB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500), "500 TB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024 * 1024 * 1024 * 1024), "1 PB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024), "1 PB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500), "500 PB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1 EB")
        assertEquals(Filex.formatShortFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1 EB")
        assertEquals(Filex.formatShortCompactFileSize(0), "0B")
        assertEquals(Filex.formatShortCompactFileSize(800), "800B")
        assertEquals(Filex.formatShortCompactFileSize(1024), "1KB")
        assertEquals(Filex.formatShortCompactFileSize(800 + 1024), "2KB")
        assertEquals(Filex.formatShortCompactFileSize(800 + 1024 * 500), "501KB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024), "1MB")
        assertEquals(Filex.formatShortCompactFileSize(1024 * 500 + 1024L * 1024), "1MB")
        assertEquals(Filex.formatShortCompactFileSize(1024 * 500 + 1024L * 1024 * 500), "500MB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024 * 1024), "1GB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024), "1GB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024 * 500 + 1024L * 1024 * 1024 * 500), "500GB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024 * 1024 * 1024), "1TB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024), "1TB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 500), "500TB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024 * 1024 * 1024 * 1024), "1PB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024), "1PB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 500), "500PB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1EB")
        assertEquals(Filex.formatShortCompactFileSize(1024L * 1024 * 1024 * 1024 * 1024 * 500 + 1024L * 1024 * 1024 * 1024 * 1024 * 1024), "1EB")
    }

    @Test
    fun testRequireExist() {
        val testFile = File("/tmp/testRequireExist")
        testFile.delete()
        var result: Boolean
        result = try {
            Filex.requireExist(testFile)
            true
        } catch (e: FileNotFoundException) {
            false
        }
        assertFalse(result)
        Filex.createNewFileOrCheck(testFile)
        result = try {
            Filex.requireExist(testFile)
            true
        } catch (e: FileNotFoundException) {
            false
        }
        assertTrue(result)
        testFile.delete()
    }

    @Test
    fun testRequireIsDir() {
        val testDir = File("/tmp/testRequireIsDir")

        // 不存在时
        Filex.deleteRecursively(testDir)
        var result: Boolean
        result = try {
            Filex.requireIsDir(testDir)
            true
        } catch (e: java.lang.IllegalArgumentException) {
            false
        }
        assertFalse(result)

        // 文件
        Filex.createNewFileOrCheck(testDir)
        result = try {
            Filex.requireIsDir(testDir)
            true
        } catch (e: java.lang.IllegalArgumentException) {
            false
        }
        assertFalse(result)

        // 目录
        Filex.deleteRecursively(testDir)
        Filex.mkdirsOrCheck(testDir)
        result = try {
            Filex.requireIsDir(testDir)
            true
        } catch (e: java.lang.IllegalArgumentException) {
            false
        }
        assertTrue(result)
        Filex.deleteRecursively(testDir)
    }

    @Test
    fun testRequireIsFile() {
        val testFile = File("/tmp/testRequireIsFile")

        // 不存在时
        Filex.deleteRecursively(testFile)
        var result: Boolean
        result = try {
            Filex.requireIsFile(testFile)
            true
        } catch (e: java.lang.IllegalArgumentException) {
            false
        }
        assertFalse(result)

        // 目录
        Filex.mkdirsOrCheck(testFile)
        result = try {
            Filex.requireIsFile(testFile)
            true
        } catch (e: java.lang.IllegalArgumentException) {
            false
        }
        assertFalse(result)

        // 文件
        Filex.deleteRecursively(testFile)
        Filex.createNewFileOrCheck(testFile)
        result = try {
            Filex.requireIsFile(testFile)
            true
        } catch (e: java.lang.IllegalArgumentException) {
            false
        }
        assertTrue(result)
        Filex.deleteRecursively(testFile)
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    @Test
    @Throws(IOException::class)
    fun testCopyTo() {
        /*
         * test copy file
         */
        val copySourceFile = File("/tmp/testCopyFile.txt")
        val copyTargetFile = File(copySourceFile.path + "_copyTarget")
        try {
            Filex.writeText(Filex.createNewFileOrThrow(copySourceFile), "testCopyFile")

            // 覆盖
            Filex.copyTo(copySourceFile, copyTargetFile, true)
            assertEquals(copySourceFile.getMD5Digest(), copyTargetFile.getMD5Digest())

            // 不覆盖
            try {
                Filex.copyTo(copySourceFile, copyTargetFile, 1024 * 4)
                fail()
            } catch (ignored: IOException) {
            }
        } finally {
            Filex.deleteRecursively(copySourceFile)
            Filex.deleteRecursively(copyTargetFile)
        }

        try {
            Filex.writeText(Filex.createNewFileOrThrow(copySourceFile), "testCopyFile")
            Filex.copyTo(copySourceFile, copyTargetFile, 1024 * 4)
            assertEquals(copySourceFile.getMD5Digest(), copyTargetFile.getMD5Digest())
        } finally {
            Filex.deleteRecursively(copySourceFile)
            Filex.deleteRecursively(copyTargetFile)
        }

        try {
            Filex.writeText(Filex.createNewFileOrThrow(copySourceFile), "testCopyFile")
            Filex.copyTo(copySourceFile, copyTargetFile)
            assertEquals(copySourceFile.getMD5Digest(), copyTargetFile.getMD5Digest())
        } finally {
            Filex.deleteRecursively(copySourceFile)
            Filex.deleteRecursively(copyTargetFile)
        }

        /*
         * test copy dir
         */
        val copySourceDir = Filex.createFileTree(File("/tmp/testCopyDir"), 3, 3, "file.txt", "testCopyDir")
        val copyTargetDir = File(copySourceDir.path + "_copyTarget")
        try {
            Filex.deleteRecursively(copyTargetDir)

            Filex.copyTo(copySourceDir, copyTargetDir, true)

            assertTrue(copyTargetDir.exists())
            assertEquals(copyTargetDir.listFiles()?.count()?.toLong() ?: 0, 0)
        } finally {
            Filex.deleteRecursively(copySourceDir)
            Filex.deleteRecursively(copyTargetDir)
        }

        /*
         * test source file not exist
         */
        try {
            Filex.copyTo(File("/tmp/testCopyFile111.txt"), File("/tmp/testCopyFile111_copyTarget.txt"))
            fail()
        } catch (ignored: IOException) {
        }
    }

    @Test
    @Throws(IOException::class)
    fun testCopyRecursively() {
        val copySourceDir = Filex.createFileTree(File("/tmp/testCopyRecursively"), 3, 3, "file.txt", "testCopyRecursively")
        val copyTargetDir = File(copySourceDir.path + "_copyTarget")
        val copyTargetDirKotlin = File(copySourceDir.path + "_copyTarget_kotlin")
        try {
            Filex.deleteRecursively(copyTargetDir)
            Filex.copyRecursively(copySourceDir, copyTargetDir)
            // test overwrite is false
            try {
                Filex.copyRecursively(copySourceDir, copyTargetDir)
                fail()
            } catch (e: FileAlreadyExistsException) {
            }
            // test onError
            try {
                Filex.copyRecursively(copySourceDir, copyTargetDir) { _, e -> throw IllegalArgumentException(e) }
                fail()
            } catch (e: IllegalArgumentException) {
            }
            Filex.copyRecursively(copySourceDir, copyTargetDir, true)
            assertTrue(copyTargetDir.exists())
            assertEquals(Filex.listCountRecursively(copyTargetDir).toLong(), Filex.listCountRecursively(copySourceDir).toLong())
            assertEquals(Filex.lengthRecursively(copyTargetDir), Filex.lengthRecursively(copySourceDir))

            Filex.deleteRecursively(copyTargetDirKotlin)
            copySourceDir.copyRecursively(copyTargetDirKotlin)
            assertTrue(copyTargetDirKotlin.exists())
            assertEquals(Filex.listCountRecursively(copyTargetDirKotlin).toLong(), Filex.listCountRecursively(copySourceDir).toLong())
            assertEquals(Filex.lengthRecursively(copyTargetDirKotlin), Filex.lengthRecursively(copySourceDir))
        } finally {
            Filex.deleteRecursively(copyTargetDir)
            Filex.deleteRecursively(copyTargetDirKotlin)
        }

        try {
            Filex.deleteRecursively(copyTargetDir)
            Filex.copyRecursively(copySourceDir, copyTargetDir) { _, e -> throw IllegalArgumentException(e) }

            assertTrue(copyTargetDir.exists())
            assertEquals(Filex.listCountRecursively(copyTargetDir).toLong(), Filex.listCountRecursively(copySourceDir).toLong())
            assertEquals(Filex.lengthRecursively(copyTargetDir), Filex.lengthRecursively(copySourceDir))

            Filex.deleteRecursively(copyTargetDirKotlin)
            copySourceDir.copyRecursively(copyTargetDirKotlin)
            assertTrue(copyTargetDirKotlin.exists())
            assertEquals(Filex.listCountRecursively(copyTargetDirKotlin).toLong(), Filex.listCountRecursively(copySourceDir).toLong())
            assertEquals(Filex.lengthRecursively(copyTargetDirKotlin), Filex.lengthRecursively(copySourceDir))
        } finally {
            Filex.deleteRecursively(copySourceDir)
            Filex.deleteRecursively(copyTargetDir)
            Filex.deleteRecursively(copyTargetDirKotlin)
        }

        /*
         * test source file not exist
         */
        try {
            Filex.copyRecursively(File("/tmp/testCopyFile111.txt"), File("/tmp/testCopyFile111_copyTarget.txt"))
            fail()
        } catch (ignored: IOException) {
        }
    }

    @Test
    fun testDeleteRecursively() {
        val dir = Filex.createFileTree(File("/tmp/testDeleteRecursively"), 3, 3, "file.txt", "testDeleteRecursively")
        try {
            assertTrue(dir.exists())
            assertTrue(Filex.deleteRecursively(dir))
            assertFalse(dir.exists())
        } finally {
            Filex.deleteRecursively(dir)
        }

        // is File
        val file = Filex.createNewFileOrThrow(File("/tmp/testDeleteRecursively.file"))
        try {
            assertTrue(file.exists())
            assertTrue(Filex.deleteRecursively(file))
            assertFalse(file.exists())
        } finally {
            Filex.deleteRecursively(file)
        }

        // not exists
        val fileNotExists = File("/tmp/testCleanRecursively.not_exists.file")
        try {
            assertFalse(file.exists())
            assertTrue(Filex.deleteRecursively(fileNotExists))
        } finally {
            Filex.deleteRecursively(fileNotExists)
        }
    }

    @Test
    fun testStartsWith() {
        var selfFile = File("/tmp/testStartsWith/file")
        var otherFile = File("/tmp/testStartsWith")
        assertTrue(Filex.startsWith(selfFile, otherFile))
        assertEquals(Filex.startsWith(selfFile, otherFile), selfFile.startsWith(otherFile))

        selfFile = File("/tmp/testStartsWith")
        otherFile = File("/tmp/testStartsWith/dir")
        assertFalse(Filex.startsWith(selfFile, otherFile))
        assertEquals(Filex.startsWith(selfFile, otherFile), selfFile.startsWith(otherFile))

        selfFile = File("/tmp/testStartsWith/file")
        var otherFilePath = "/tmp2/testStartsWith/dir"
        assertFalse(Filex.startsWith(selfFile, otherFilePath))
        assertEquals(Filex.startsWith(selfFile, otherFilePath), selfFile.startsWith(otherFilePath))

        selfFile = File("/tmp/testStartsWith/file")
        otherFilePath = "testStartsWith/dir"
        assertFalse(Filex.startsWith(selfFile, otherFilePath))
        assertEquals(Filex.startsWith(selfFile, otherFilePath), selfFile.startsWith(otherFilePath))
    }

    @Test
    fun testEndsWith() {
        var selfFile = File("/tmp/testEndsWith/file")
        var otherFile = File("/tmp/testEndsWith/file")
        assertTrue(Filex.endsWith(selfFile, otherFile))
        assertEquals(Filex.endsWith(selfFile, otherFile), selfFile.endsWith(otherFile))

        selfFile = File("tmp/testEndsWith")
        otherFile = File("tmp/testEndsWith/dir")
        assertFalse(Filex.endsWith(selfFile, otherFile))
        assertEquals(Filex.endsWith(selfFile, otherFile), selfFile.endsWith(otherFile))

        selfFile = File("tmp/testEndsWith/file")
        val otherFilePath = "tmp2/testEndsWith/dir"
        assertFalse(Filex.endsWith(selfFile, otherFilePath))
        assertEquals(Filex.endsWith(selfFile, otherFile), selfFile.endsWith(otherFilePath))
    }

    @Test
    fun testNormalize() {
        var sourceFile = File("/foo/./bar/gav/../baaz")
        assertEquals(Filex.normalize(sourceFile).path, "/foo/bar/baaz")
        assertEquals(Filex.normalize(sourceFile).path, sourceFile.normalize().path)

        sourceFile = File("/foo/bar/gav/baaz")
        assertEquals(Filex.normalize(sourceFile).path, "/foo/bar/gav/baaz")
        assertEquals(Filex.normalize(sourceFile).path, sourceFile.normalize().path)
    }

    @Test
    fun testResolve() {
        var sourceFile = File("/tmp/testResolve")
        var relativeFile = File("image")
        assertEquals(Filex.resolve(sourceFile, relativeFile).path, "/tmp/testResolve/image")
        assertEquals(Filex.resolve(sourceFile, relativeFile).path, sourceFile.resolve(relativeFile).path)

        sourceFile = File("/tmp/testResolve/")
        relativeFile = File("image")
        assertEquals(Filex.resolve(sourceFile, relativeFile).path, "/tmp/testResolve/image")
        assertEquals(Filex.resolve(sourceFile, relativeFile).path, sourceFile.resolve(relativeFile).path)

        sourceFile = File("/tmp/testResolve/image")
        var relativeFilePath = "/tmp/testResolve"
        assertEquals(Filex.resolve(sourceFile, relativeFilePath).path, "/tmp/testResolve")
        assertEquals(Filex.resolve(sourceFile, relativeFilePath).path, sourceFile.resolve(relativeFilePath).path)

        sourceFile = File("/tmp/testResolve/")
        relativeFilePath = "/tmp/testResolve/file"
        assertEquals(Filex.resolve(sourceFile, relativeFilePath).path, "/tmp/testResolve/file")
        assertEquals(Filex.resolve(sourceFile, relativeFilePath).path, sourceFile.resolve(relativeFilePath).path)
    }

    @Test
    fun testResolveSibling() {
        var sourceFile = File("/tmp/testResolveSibling")
        var relativeFile = File("image")
        assertEquals(Filex.resolveSibling(sourceFile, relativeFile).path, "/tmp/image")
        assertEquals(Filex.resolveSibling(sourceFile, relativeFile).path, sourceFile.resolveSibling(relativeFile).path)

        sourceFile = File("/tmp/testResolveSibling/")
        relativeFile = File("image")
        assertEquals(Filex.resolveSibling(sourceFile, relativeFile).path, "/tmp/image")
        assertEquals(Filex.resolveSibling(sourceFile, relativeFile).path, sourceFile.resolveSibling(relativeFile).path)

        sourceFile = File("/tmp/testResolveSibling/image")
        var relativeFilePath = "/tmp/testResolveSibling"
        assertEquals(Filex.resolveSibling(sourceFile, relativeFilePath).path, "/tmp/testResolveSibling")
        assertEquals(Filex.resolveSibling(sourceFile, relativeFilePath).path, sourceFile.resolveSibling(relativeFilePath).path)

        sourceFile = File("/tmp/testResolveSibling/")
        relativeFilePath = "/tmp/testResolveSibling/file"
        assertEquals(Filex.resolveSibling(sourceFile, relativeFilePath).path, "/tmp/testResolveSibling/file")
        assertEquals(Filex.resolveSibling(sourceFile, relativeFilePath).path, sourceFile.resolveSibling(relativeFilePath).path)
    }

    @Test
    fun testCreateTempDir() {
        var dir = Filex.createTempDir("tmp", "testCreateTempDir")
        try {
            assertTrue(dir.exists())
            assertTrue(dir.isDirectory)
            assertTrue(dir.name.startsWith("tmp"))
            assertTrue(dir.name.endsWith("testCreateTempDir"))
        } finally {
            Filex.deleteRecursively(dir)
        }

        dir = Filex.createTempDir("tmp", "testCreateTempDir", Filex.mkdirsOrThrow(File("/tmp/testCreateTempDir")))
        try {
            assertTrue(dir.exists())
            assertTrue(dir.isDirectory)
            assertTrue(dir.name.startsWith("tmp"))
            assertTrue(dir.name.endsWith("testCreateTempDir"))
            assertEquals(dir.parent, "/tmp/testCreateTempDir")
        } finally {
            Filex.deleteRecursively(dir.parentFile)
        }

        dir = Filex.createTempDir(Filex.mkdirsOrThrow(File("/tmp/testCreateTempDir2")))
        try {
            assertTrue(dir.exists())
            assertTrue(dir.isDirectory)
            assertTrue(dir.name.startsWith("tmp"))
            assertEquals(dir.parent, "/tmp/testCreateTempDir2")
        } finally {
            Filex.deleteRecursively(dir.parentFile)
        }

        dir = Filex.createTempDir()
        try {
            assertTrue(dir.exists())
            assertTrue(dir.isDirectory)
            assertTrue(dir.name.startsWith("tmp"))
        } finally {
            Filex.deleteRecursively(dir)
        }
    }

    @Test
    fun testCreateTempFile() {
        var file = Filex.createTempFile("tmp", "testCreateTempFile")
        try {
            assertTrue(file.exists())
            assertTrue(file.isFile)
            assertTrue(file.name.startsWith("tmp"))
            assertTrue(file.name.endsWith("testCreateTempFile"))
        } finally {
            Filex.deleteRecursively(file)
        }

        file = Filex.createTempFile("tmp", "testCreateTempFile", Filex.mkdirsOrThrow(File("/tmp/testCreateTempFile")))
        try {
            assertTrue(file.exists())
            assertTrue(file.isFile)
            assertTrue(file.name.startsWith("tmp"))
            assertTrue(file.name.endsWith("testCreateTempFile"))
            assertEquals(file.parent, "/tmp/testCreateTempFile")
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        file = Filex.createTempFile(Filex.mkdirsOrThrow(File("/tmp/testCreateTempFile2")))
        try {
            assertTrue(file.exists())
            assertTrue(file.isFile)
            assertTrue(file.name.startsWith("tmp"))
            assertEquals(file.parent, "/tmp/testCreateTempFile2")
        } finally {
            Filex.deleteRecursively(file.parentFile)
        }

        file = Filex.createTempFile()
        try {
            assertTrue(file.exists())
            assertTrue(file.isFile)
            assertTrue(file.name.startsWith("tmp"))
        } finally {
            Filex.deleteRecursively(file)
        }
    }

    @Test
    fun testExtension() {
        var file = File("/tmp/testExtension.txt")
        assertEquals(Filex.getExtension(file), "txt")
        assertEquals(Filex.getExtension(file), file.extension)

        file = File("/tmp/testExtension.txt.zip")
        assertEquals(Filex.getExtension(file), "zip")
        assertEquals(Filex.getExtension(file), file.extension)

        file = File("/tmp/testExtension")
        assertEquals(Filex.getExtension(file), "")
        assertEquals(Filex.getExtension(file), file.extension)

        file = File("/tmp/.txt")
        assertEquals(Filex.getExtension(file), "txt")
        assertEquals(Filex.getExtension(file), file.extension)
    }

    @Test
    fun testNameWithoutExtension() {
        var file = File("/tmp/testExtension.txt")
        assertEquals(Filex.getNameWithoutExtension(file), "testExtension")
        assertEquals(Filex.getNameWithoutExtension(file), file.nameWithoutExtension)

        file = File("/tmp/testExtension.txt.zip")
        assertEquals(Filex.getNameWithoutExtension(file), "testExtension.txt")
        assertEquals(Filex.getNameWithoutExtension(file), file.nameWithoutExtension)

        file = File("/tmp/testExtension")
        assertEquals(Filex.getNameWithoutExtension(file), "testExtension")
        assertEquals(Filex.getNameWithoutExtension(file), file.nameWithoutExtension)

        file = File("/tmp/.txt")
        assertEquals(Filex.getNameWithoutExtension(file), "")
        assertEquals(Filex.getNameWithoutExtension(file), file.nameWithoutExtension)
    }

    @Test
    fun testRelative() {
        val baseDir = File("/tmp/testRelative")

        var childDir = File("/tmp/testRelative")
        assertEquals(Filex.toRelativeString(childDir, baseDir), "")
        assertEquals(Filex.toRelativeString(childDir, baseDir), childDir.toRelativeString(baseDir))

        childDir = File("/tmp/testRelative/new")
        assertEquals(Filex.toRelativeString(childDir, baseDir), "new")
        assertEquals(Filex.toRelativeString(childDir, baseDir), childDir.toRelativeString(baseDir))

        assertEquals(Filex.relativeTo(baseDir, childDir).path, "..")
        assertEquals(Filex.relativeTo(baseDir, childDir).path, baseDir.relativeTo(childDir).path)

        childDir = File("/tmp/testRelative/new/dir")
        assertEquals(Filex.relativeTo(childDir, baseDir).path, "new/dir")
        assertEquals(Filex.relativeTo(childDir, baseDir).path, childDir.relativeTo(baseDir).path)

        assertEquals(Filex.relativeTo(baseDir, childDir).path, "../..")
        assertEquals(Filex.relativeTo(baseDir, childDir).path, baseDir.relativeTo(childDir).path)

        childDir = File("../testRelative")
        assertEquals(Filex.relativeToOrSelf(childDir, baseDir).path, childDir.path)
        assertEquals(Filex.relativeToOrSelf(childDir, baseDir).path, childDir.relativeToOrSelf(baseDir).path)

        childDir = File("../testRelative")
        assertNull(Filex.relativeToOrNull(childDir, baseDir))
        assertEquals(Filex.relativeToOrNull(childDir, baseDir), childDir.relativeToOrNull(baseDir))
    }

    @Test
    fun testRoot() {
        assertTrue(Filex.isRooted(File("/tmp/testRoot")))
        assertFalse(Filex.isRooted(File("tmp/testRoot")))
    }

    @Test
    fun testComponents() {
        var file = File("/tmp/testRoot")
        var components = Filex.toComponents(file)
        assertTrue(components.isRooted)
        assertEquals(components.rootName, "/")
        assertEquals(components.root.path, "/")
        assertEquals(components.size, 2)
        assertEquals(components.segments.joinToString(prefix = "[", postfix = "]"), "[tmp, testRoot]")

        file = File("tmp/testRoot/file")
        components = Filex.toComponents(file)
        assertFalse(components.isRooted)
        assertEquals(components.rootName, "")
        assertEquals(components.root.path, "")
        assertEquals(components.size, 3)
        assertEquals(components.segments.joinToString(prefix = "[", postfix = "]"), "[tmp, testRoot, file]")
    }

    @Test
    fun testGetInvariantSeparatorsPath() {
        assertEquals(Filex.getInvariantSeparatorsPath(File("/tmp/testGetInvariantSeparatorsPath")), "/tmp/testGetInvariantSeparatorsPath")
    }

    @Test
    fun testSubPath() {
        assertEquals(Filex.subPath(File("/tmp/testSubPath"), 1, 2).path, "testSubPath")

        try {
            Filex.subPath(File("/tmp/testSubPath"), -1, 2)
            fail()
        } catch (e: Exception) {
        }

        try {
            Filex.subPath(File("/tmp/testSubPath"), 3, 2)
            fail()
        } catch (e: Exception) {
        }

        try {
            Filex.subPath(File("/tmp/testSubPath"), 1, 3)
            fail()
        } catch (e: Exception) {
        }
    }

    @Test
    fun testInputStream() {
        val file = File("/tmp/testInputStream.txt")
        Filex.writeText(file, "testInputStream")
        try {
            IOx.closeQuietly(Filex.inputStream(file))
            IOx.closeQuietly(Filex.bufferedInputStream(file, 1024 * 4))
            IOx.closeQuietly(Filex.bufferedInputStream(file))
            IOx.closeQuietly(Filex.reader(file, StandardCharsets.UTF_8))
            IOx.closeQuietly(Filex.reader(file))
            IOx.closeQuietly(Filex.bufferedReader(file, StandardCharsets.UTF_8, 1024 * 4))
            IOx.closeQuietly(Filex.bufferedReader(file, 1024 * 4))
            IOx.closeQuietly(Filex.bufferedReader(file, StandardCharsets.UTF_8))
            IOx.closeQuietly(Filex.bufferedReader(file))
        } finally {
            Filex.deleteRecursively(file)
        }
    }

    @Test
    fun testRead() {
        val file = File("/tmp/testRead.txt")
        val content = "abcdefg\nhijklmn\nopqrst\nuvwxyz"
        Filex.writeText(file, content)
        try {
            assertEquals(String(Filex.readBytes(file)), content)
            assertEquals(Filex.readText(file, StandardCharsets.UTF_8), content)
            assertEquals(Filex.readText(file), content)
            assertEquals(Filex.readLines(file, StandardCharsets.UTF_8).joinToString(prefix = "[", postfix = "]"), "[" + content.replace("\n", ", ") + "]")
            assertEquals(Filex.readLines(file).joinToString(prefix = "[", postfix = "]"), "[" + content.replace("\n", ", ") + "]")
        } finally {
            Filex.deleteRecursively(file)
        }
    }

    @Test
    fun testUseLines() {
        val file = File("/tmp/testLines.txt")
        val content = "abcdefg\nhijklmn\nopqrst\nuvwxyz"
        Filex.writeText(file, content)
        try {
            assertEquals(StringBuilder().apply {
                Filex.useLines(file, StandardCharsets.UTF_8) { iterable ->
                    iterable.forEach { lineString ->
                        if (length > 0) append("\n")
                        append(lineString)
                    }
                }
            }.toString(), content)

            assertEquals(StringBuilder().apply {
                Filex.useLines(file) { iterable ->
                    iterable.forEach{ lineString ->
                        if (length > 0) append("\n")
                        append(lineString)
                    }
                }
            }.toString(), content)
        } finally {
            Filex.deleteRecursively(file)
        }
    }

    @Test
    fun testForEachBlock() {
        val file = File("/tmp/testLines.txt")
        val content = "abcdefg\nhijklmn\nopqrst\nuvwxyz"
        Filex.writeText(file, content)
        try {
            assertEquals(StringBuilder().apply {
                Filex.forEachBlock(file, IOx.DEFAULT_BLOCK_SIZE) { data, size ->
                    append(String(data, 0, size))
                }
            }.toString(), content)

            assertEquals(StringBuilder().apply {
                Filex.forEachBlock(file) { data, size ->
                    append(String(data, 0, size))
                }
            }.toString(), content)
        } finally {
            Filex.deleteRecursively(file)
        }
    }

    @Test
    fun testForEachLine() {
        val file = File("/tmp/testLines.txt")
        val content = "abcdefg\nhijklmn\nopqrst\nuvwxyz"
        Filex.writeText(file, content)
        try {
            assertEquals(StringBuilder().apply {
                Filex.forEachLine(file, StandardCharsets.UTF_8) { lineString ->
                    if (length > 0) append("\n")
                    append(lineString)
                }
            }.toString(), content)

            assertEquals(StringBuilder().apply {
                Filex.forEachLine(file) { lineString ->
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
        val file = File("/tmp/testOutputStream.txt")
        Filex.writeText(file, "testOutputStream")
        try {
            IOx.closeQuietly(Filex.outputStream(file))
            IOx.closeQuietly(Filex.bufferedOutputStream(file, 1024 * 4))
            IOx.closeQuietly(Filex.bufferedOutputStream(file))
            IOx.closeQuietly(Filex.writer(file, StandardCharsets.UTF_8))
            IOx.closeQuietly(Filex.writer(file))
            IOx.closeQuietly(Filex.bufferedWriter(file, StandardCharsets.UTF_8, 1024 * 4 * 4))
            IOx.closeQuietly(Filex.bufferedWriter(file, 1024 * 4 * 4))
            IOx.closeQuietly(Filex.bufferedWriter(file, StandardCharsets.UTF_8))
            IOx.closeQuietly(Filex.bufferedWriter(file))
            IOx.closeQuietly(Filex.printWriter(file, StandardCharsets.UTF_8))
            IOx.closeQuietly(Filex.printWriter(file))
        } finally {
            Filex.deleteRecursively(file)
        }
    }

    @Test
    fun testWrite() {
        val file = File("/tmp/testWrite.txt")
        try {
            val content = "abcdefg\nhijklmn\nopqrst\nuvwxyz"

            Filex.writeBytes(file, (content + "1").toByteArray())
            assertEquals(Filex.readText(file), content + "1")

            Filex.appendBytes(file, (content + "2").toByteArray())
            assertEquals(Filex.readText(file), content + "1" + content + "2")

            Filex.writeText(file, content + "3", StandardCharsets.UTF_8)
            assertEquals(Filex.readText(file), content + "3")

            Filex.writeText(file, content + "4")
            assertEquals(Filex.readText(file), content + "4")

            Filex.appendText(file, content + "5", StandardCharsets.UTF_8)
            assertEquals(Filex.readText(file), content + "4" + content + "5")

            Filex.appendText(file, content + "6")
            assertEquals(Filex.readText(file), content + "4" + content + "5" + content + "6")
        } finally {
            Filex.deleteRecursively(file)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testWalk() {
        val sourceDir = Filex.createFileTree(File("/tmp/testWalk"), 3, 3, "file.txt", "testWalk")
        try {
            assertEquals(LinkedList<String>().apply {
                for (file in Filex.walk(sourceDir, FileWalkDirection.BOTTOM_UP)) {
                    add(file.path)
                }
            }.joinToString(prefix = "[", postfix = "]"), LinkedList<String>().apply {
                for (file in sourceDir.walk(kotlin.io.FileWalkDirection.BOTTOM_UP)) {
                    add(file.path)
                }
            }.joinToString(prefix = "[", postfix = "]"))

            assertEquals(LinkedList<String>().apply {
                for (file in Filex.walk(sourceDir)) {
                    add(file.path)
                }
            }.joinToString(prefix = "[", postfix = "]"), LinkedList<String>().apply {
                for (file in sourceDir.walk()) {
                    add(file.path)
                }
            }.joinToString(prefix = "[", postfix = "]"))

            assertEquals(LinkedList<String>().apply {
                for (file in Filex.walkTopDown(sourceDir)) {
                    add(file.path)
                }
            }.joinToString(prefix = "[", postfix = "]"), LinkedList<String>().apply {
                for (file in sourceDir.walkTopDown()) {
                    add(file.path)
                }
            }.joinToString(prefix = "[", postfix = "]"))

            assertEquals(LinkedList<String>().apply {
                for (file in Filex.walkBottomUp(sourceDir)) {
                    add(file.path)
                }
            }.joinToString(prefix = "[", postfix = "]"), LinkedList<String>().apply {
                for (file in sourceDir.walkBottomUp()) {
                    add(file.path)
                }
            }.joinToString(prefix = "[", postfix = "]"))
        } finally {
            Filex.deleteRecursively(sourceDir)
        }
    }
}