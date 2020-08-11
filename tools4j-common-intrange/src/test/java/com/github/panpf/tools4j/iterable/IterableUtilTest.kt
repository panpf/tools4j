package com.github.panpf.tools4j.iterable

import org.junit.Assert
import org.junit.Test

class IterableUtilTest {

    // todo Complete test

    @Test
    fun testGetProgressionLastElement() {
        Assert.assertEquals(10, IterableUtil.getProgressionLastElement(0, 10, 1))
        Assert.assertEquals(10, IterableUtil.getProgressionLastElement(0, 11, 2))
        try {
            IterableUtil.getProgressionLastElement(0, 10, 0)
            Assert.fail()
        } catch (e: IllegalArgumentException) {
        }

        Assert.assertEquals(10L, IterableUtil.getProgressionLastElement(0L, 10L, 1L))
        Assert.assertEquals(10L, IterableUtil.getProgressionLastElement(0L, 11L, 2L))
        try {
            IterableUtil.getProgressionLastElement(0L, 10L, 0L)
            Assert.fail()
        } catch (e: IllegalArgumentException) {
        }

        Assert.assertEquals(0, IterableUtil.getProgressionLastElement(10, 0, -1))
        Assert.assertEquals(1, IterableUtil.getProgressionLastElement(11, 0, -2))
        try {
            IterableUtil.getProgressionLastElement(10, 0, 0)
            Assert.fail()
        } catch (e: IllegalArgumentException) {
        }

        Assert.assertEquals(0L, IterableUtil.getProgressionLastElement(10L, 0L, -1L))
        Assert.assertEquals(1L, IterableUtil.getProgressionLastElement(11L, 0L, -2L))
        try {
            IterableUtil.getProgressionLastElement(10L, 0L, 0L)
            Assert.fail()
        } catch (e: IllegalArgumentException) {
        }
    }
}