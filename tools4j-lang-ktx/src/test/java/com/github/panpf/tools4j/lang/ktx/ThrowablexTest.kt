package com.github.panpf.tools4j.lang.ktx

import org.junit.Assert
import org.junit.Test

class ThrowablexTest {

    @Test
    fun test() {
        val result = Throwable().stackTraceToString()
        Assert.assertNotNull("stackTraceToString", result)
    }
}
