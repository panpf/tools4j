package com.github.panpf.tools4j.resources

import com.github.panpf.tools4j.io.ktx.createNewFileOrThrow
import com.github.panpf.tools4j.io.ktx.listFilesRecursively
import org.junit.Assert
import org.junit.Test
import java.io.File
import java.io.FileFilter

class ResourcesCacheHelperTest {

    @Test
    fun testGetFileFromCache() {
        val cacheDir = File("/tmp/ResourcesCacheHelperTest")
        val helper = ResourcesCacheHelper(ResourcesCacheHelperTest::class.java, cacheDir)
        val resourcesDir = helper.resourcesDir
        println(resourcesDir.path)

        val filePathInResources = "test.txt"
        val testResourceFile = File(resourcesDir, filePathInResources)

        try {
            // The test does not exist
            testResourceFile.delete()
            try {
                helper.getFile(filePathInResources)
                Assert.fail()
            } catch (e: Exception) {
                if (!e.message.orEmpty().contains("Not found source resources file")) {
                    Assert.fail()
                }
            }

            // The test is executed for the first time
            testResourceFile.createNewFileOrThrow()
            testResourceFile.writeText("123456")
            val testCacheFile = helper.getFile(filePathInResources)
            Assert.assertEquals(File(cacheDir, filePathInResources).path, testCacheFile.path)
            Assert.assertEquals("123456", testCacheFile.readText())

            // Changes to the test file
            testResourceFile.writeText("abcdefg")
            val testCacheFile2 = helper.getFile(filePathInResources)
            Assert.assertEquals(File(cacheDir, filePathInResources).path, testCacheFile2.path)
            Assert.assertEquals("abcdefg", testCacheFile2.readText())
        } finally {
            testResourceFile.delete()
            cacheDir.deleteRecursively()
        }
    }

    @Test
    fun testGetDirFromCache() {
        val cacheDir = File("/tmp/ResourcesCacheHelperTest")
        val helper = ResourcesCacheHelper(ResourcesCacheHelperTest::class.java, cacheDir)
        val resourcesDir = helper.resourcesDir
        println(resourcesDir.path)

        val dirPathInResources = "testDir"
        val testResourceDir = File(resourcesDir, dirPathInResources)
        try {
            // The test does not exist
            testResourceDir.deleteRecursively()
            try {
                helper.getDir(dirPathInResources)
                Assert.fail()
            } catch (e: Exception) {
                if (!e.message.orEmpty().contains("Not found source resources dir")) {
                    Assert.fail()
                }
            }

            // The test is executed for the first time
            val child1File = File(testResourceDir, "/a.so").apply { createNewFileOrThrow();writeText("a.txt") }
            val child2File = File(testResourceDir, "/libs/b.so").apply { createNewFileOrThrow();writeText("b.txt") }
            val child3File = File(testResourceDir, "/libs/x86/c.so").apply { createNewFileOrThrow();writeText("c.txt") }
            val testCacheDir = helper.getDir(dirPathInResources)
            Assert.assertEquals(File(cacheDir, dirPathInResources).path, testCacheDir.path)
            Assert.assertEquals(3, testCacheDir.listFilesRecursively(FileFilter { it.isFile })?.size ?: 0)
            Assert.assertEquals("a.txt", child1File.readText())
            Assert.assertEquals("b.txt", child2File.readText())
            Assert.assertEquals("c.txt", child3File.readText())

            // Changes to the test file
            child2File.delete()
            child1File.writeText("aa.txt")
            child3File.writeText("cc.txt")
            val testCacheDir2 = helper.getDir(dirPathInResources)
            Assert.assertEquals(File(cacheDir, dirPathInResources).path, testCacheDir2.path)
            Assert.assertEquals(2, testCacheDir.listFilesRecursively(FileFilter { it.isFile })?.size ?: 0)
            Assert.assertEquals("aa.txt", child1File.readText())
            Assert.assertEquals("cc.txt", child3File.readText())
        } finally {
            testResourceDir.deleteRecursively()
            cacheDir.deleteRecursively()
        }
    }
}