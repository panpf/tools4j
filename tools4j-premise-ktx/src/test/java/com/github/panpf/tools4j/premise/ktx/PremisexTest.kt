package com.github.panpf.tools4j.premise.ktx

import com.github.panpf.tools4j.premise.LazyValue
import org.junit.Assert.fail
import org.junit.Test

class PremisexTest {

    @Test
    fun testRequire() {
        true.require()
        try {
            false.require()
            fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            (null as Boolean?).require()
            fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            false.require(LazyValue { "is false" })
            fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            false.require { "is false" }
            fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            true.require(LazyValue { "is true" })
        } catch (e: Exception) {
            // e.printStackTrace();
        }
    }

    @Test
    fun testCheck() {
        true.check()
        try {
            false.check()
            fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            (null as Boolean?).check()
            fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            false.check(LazyValue { "is false" })
            fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            false.check { "is false" }
            fail()
        } catch (e: Exception) {
            // e.printStackTrace();
        }
        try {
            true.check(LazyValue { "is true" })
        } catch (e: Exception) {
            // e.printStackTrace();
        }
    }

    @Test
    fun testRequireNotNull() {
        "".requireNotNull()
        "".requireNotNull("param")
        "".requireNotNull { "param is null" }
        "".requireNotNull(LazyValue { "param is null" })

        try {
            @Suppress("IMPLICIT_NOTHING_AS_TYPE_PARAMETER")
            null.requireNotNull("param")
            @Suppress("UNREACHABLE_CODE")
            fail()
        } catch (e: Exception) {
            // // e.printStackTrace();;
        }
    }

    @Test
    fun testCheckNotNull() {
        "".checkNotNull()
        "".checkNotNull("param")
        "".checkNotNull { "param is null" }
        "".checkNotNull(LazyValue { "param is null" })

        try {
            @Suppress("IMPLICIT_NOTHING_AS_TYPE_PARAMETER")
            null.checkNotNull("param")
            @Suppress("UNREACHABLE_CODE")
            fail()
        } catch (e: Exception) {
            // // e.printStackTrace();;
        }
    }
}
