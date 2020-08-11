@file:Suppress("RemoveRedundantSpreadOperator")

package com.github.panpf.tools4j.collections.ktx

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MapxTest {

    @Test
    fun testNullOrEmpty() {
        assertTrue(HashMap<String, String>().isNullOrEmpty())
        assertTrue((null as HashMap<String, String>?).isNullOrEmpty())
        assertFalse(HashMap<String, String>().apply { put("key", "value") }.isNullOrEmpty())

        assertFalse(HashMap<String, String>().isNotNullOrEmpty())
        assertFalse((null as HashMap<String, String>?).isNotNullOrEmpty())
        assertTrue(HashMap<String, String>().apply { put("key", "value") }.isNotNullOrEmpty())
    }
}
