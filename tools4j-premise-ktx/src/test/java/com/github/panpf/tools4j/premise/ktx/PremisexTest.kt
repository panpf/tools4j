package com.github.panpf.tools4j.premise.ktx

import org.junit.Assert.fail
import org.junit.Test

class PremisexTest {

    @Test
    fun testRequire() {
        true.require()
        try {
            false.require()
            fail()
        } catch (ignored: Exception) {
        }
        try {
            (null as Boolean?).require()
            fail()
        } catch (ignored: Exception) {
        }
        try {
            false.require { "is false" }
            fail()
        } catch (ignored: Exception) {
        }
        try {
            true.require { "is true" }
        } catch (ignored: Exception) {
        }
    }

    @Test
    fun testCheck() {
        true.check()
        try {
            false.check()
            fail()
        } catch (ignored: Exception) {
        }
        try {
            (null as Boolean?).check()
            fail()
        } catch (ignored: Exception) {
        }
        try {
            false.check { "is false" }
            fail()
        } catch (ignored: Exception) {
        }
        try {
            true.check { "is true" }
        } catch (ignored: Exception) {
        }
    }

    @Test
    fun testRequireNotNull() {
        "".requireNotNull()
        "".requireNotNull("param")
        "".requireNotNull { "param is null" }

        try {
            @Suppress("IMPLICIT_NOTHING_AS_TYPE_PARAMETER")
            (null as String?).requireNotNull("param")
            @Suppress("UNREACHABLE_CODE")
            fail()
        } catch (ignored: Exception) {
        }
    }

    @Test
    fun testCheckNotNull() {
        "".checkNotNull()
        "".checkNotNull("param")
        "".checkNotNull { "param is null" }

        try {
            @Suppress("IMPLICIT_NOTHING_AS_TYPE_PARAMETER")
            (null as String?).checkNotNull("param")
            @Suppress("UNREACHABLE_CODE")
            fail()
        } catch (ignored: Exception) {
        }
    }
}
