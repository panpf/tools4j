package com.github.panpf.tools4j.lang

import org.junit.Assert
import org.junit.Test

class ThrowablexTest {
    @Test
    fun test() {
        val result = Throwablex.stackTraceToString(Throwable())
        Assert.assertNotNull("stackTraceToString", result)
    }
}