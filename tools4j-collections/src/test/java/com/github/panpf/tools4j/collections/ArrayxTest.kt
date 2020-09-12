package com.github.panpf.tools4j.collections

import org.junit.Assert.*
import org.junit.Test

class ArrayxTest {

    // todo Complete test

    @Test
    fun testNullOrEmpty() {
        arrayOf(3.4).maxOrNull()
        assertTrue(Arrayx.isNullOrEmpty(null as Array<String>?))
        assertTrue(Arrayx.isNullOrEmpty(arrayOf<String>()))
        assertFalse(Arrayx.isNullOrEmpty(arrayOf("1")))
        assertTrue(Arrayx.isNotNullOrEmpty(arrayOf("1")))
        assertFalse(Arrayx.isNotNullOrEmpty(null as Array<String>?))
        assertFalse(Arrayx.isNotNullOrEmpty(arrayOf<String>()))

        assertTrue(Arrayx.isNullOrEmpty((null as CharArray?)))
        assertTrue(Arrayx.isNullOrEmpty(charArrayOf()))
        assertFalse(Arrayx.isNullOrEmpty(charArrayOf(1.toChar())))
        assertTrue(Arrayx.isNotNullOrEmpty(charArrayOf(1.toChar())))
        assertFalse(Arrayx.isNotNullOrEmpty(null as CharArray?))
        assertFalse(Arrayx.isNotNullOrEmpty(charArrayOf()))

        assertTrue(Arrayx.isNullOrEmpty(null as ByteArray?))
        assertTrue(Arrayx.isNullOrEmpty(byteArrayOf()))
        assertFalse(Arrayx.isNullOrEmpty(byteArrayOf(1.toByte())))
        assertTrue(Arrayx.isNotNullOrEmpty(byteArrayOf(1.toByte())))
        assertFalse(Arrayx.isNotNullOrEmpty(null as ByteArray?))
        assertFalse(Arrayx.isNotNullOrEmpty(byteArrayOf()))

        assertTrue(Arrayx.isNullOrEmpty(null as ShortArray?))
        assertTrue(Arrayx.isNullOrEmpty(shortArrayOf()))
        assertFalse(Arrayx.isNullOrEmpty(shortArrayOf(1.toShort())))
        assertTrue(Arrayx.isNotNullOrEmpty(shortArrayOf(1.toShort())))
        assertFalse(Arrayx.isNotNullOrEmpty(null as ShortArray?))
        assertFalse(Arrayx.isNotNullOrEmpty(shortArrayOf()))

        assertTrue(Arrayx.isNullOrEmpty(null as IntArray?))
        assertTrue(Arrayx.isNullOrEmpty(intArrayOf()))
        assertFalse(Arrayx.isNullOrEmpty(intArrayOf(1)))
        assertTrue(Arrayx.isNotNullOrEmpty(intArrayOf(1)))
        assertFalse(Arrayx.isNotNullOrEmpty(null as IntArray?))
        assertFalse(Arrayx.isNotNullOrEmpty(intArrayOf()))

        assertTrue(Arrayx.isNullOrEmpty(null as LongArray?))
        assertTrue(Arrayx.isNullOrEmpty(longArrayOf()))
        assertFalse(Arrayx.isNullOrEmpty(longArrayOf(1.toLong())))
        assertTrue(Arrayx.isNotNullOrEmpty(longArrayOf(1.toLong())))
        assertFalse(Arrayx.isNotNullOrEmpty(null as LongArray?))
        assertFalse(Arrayx.isNotNullOrEmpty(longArrayOf()))

        assertTrue(Arrayx.isNullOrEmpty(null as FloatArray?))
        assertTrue(Arrayx.isNullOrEmpty(floatArrayOf()))
        assertFalse(Arrayx.isNullOrEmpty(floatArrayOf(1.toFloat())))
        assertTrue(Arrayx.isNotNullOrEmpty(floatArrayOf(1.toFloat())))
        assertFalse(Arrayx.isNotNullOrEmpty(null as FloatArray?))
        assertFalse(Arrayx.isNotNullOrEmpty(floatArrayOf()))

        assertTrue(Arrayx.isNullOrEmpty(null as DoubleArray?))
        assertTrue(Arrayx.isNullOrEmpty(doubleArrayOf()))
        assertFalse(Arrayx.isNullOrEmpty(doubleArrayOf(1.toDouble())))
        assertTrue(Arrayx.isNotNullOrEmpty(doubleArrayOf(1.toDouble())))
        assertFalse(Arrayx.isNotNullOrEmpty(null as DoubleArray?))
        assertFalse(Arrayx.isNotNullOrEmpty(doubleArrayOf()))

        assertTrue(Arrayx.isNullOrEmpty(null as BooleanArray?))
        assertTrue(Arrayx.isNullOrEmpty(booleanArrayOf()))
        assertFalse(Arrayx.isNullOrEmpty(booleanArrayOf(true)))
        assertTrue(Arrayx.isNotNullOrEmpty(booleanArrayOf(true)))
        assertFalse(Arrayx.isNotNullOrEmpty(null as BooleanArray?))
        assertFalse(Arrayx.isNotNullOrEmpty(booleanArrayOf()))
    }

    @Test
    fun testJoinToArrayString() {
        assertEquals("[key4, key3, key2]", Arrayx.joinToArrayString(arrayOf("4", "3", "2")) { "key$it" })
        assertEquals("[4, 3, 2]", Arrayx.joinToArrayString(arrayOf("4", "3", "2")))

        assertEquals("[key4, key3, key2]", Arrayx.joinToArrayString(charArrayOf('4', '3', '2')) { "key${it}" })
        assertEquals("[4, 3, 2]", Arrayx.joinToArrayString(charArrayOf('4', '3', '2')))

        assertEquals("[key4, key3, key2]", Arrayx.joinToArrayString(byteArrayOf(4.toByte(), 3.toByte(), 2.toByte())) { "key$it" })
        assertEquals("[4, 3, 2]", Arrayx.joinToArrayString(byteArrayOf(4.toByte(), 3.toByte(), 2.toByte())))

        assertEquals("[key4, key3, key2]", Arrayx.joinToArrayString(shortArrayOf(4.toShort(), 3.toShort(), 2.toShort())) { "key$it" })
        assertEquals("[4, 3, 2]", Arrayx.joinToArrayString(shortArrayOf(4.toShort(), 3.toShort(), 2.toShort())))

        assertEquals("[key4, key3, key2]", Arrayx.joinToArrayString(intArrayOf(4, 3, 2)) { "key$it" })
        assertEquals("[4, 3, 2]", Arrayx.joinToArrayString(intArrayOf(4, 3, 2)))

        assertEquals("[key4, key3, key2]", Arrayx.joinToArrayString(longArrayOf(4.toLong(), 3.toLong(), 2.toLong())) { "key$it" })
        assertEquals("[4, 3, 2]", Arrayx.joinToArrayString(longArrayOf(4.toLong(), 3.toLong(), 2.toLong())))

        assertEquals("[key4.0, key3.0, key2.0]", Arrayx.joinToArrayString(floatArrayOf(4.toFloat(), 3.toFloat(), 2.toFloat())) { "key$it" })
        assertEquals("[4.0, 3.0, 2.0]", Arrayx.joinToArrayString(floatArrayOf(4.toFloat(), 3.toFloat(), 2.toFloat())))

        assertEquals("[key4.0, key3.0, key2.0]", Arrayx.joinToArrayString(doubleArrayOf(4.toDouble(), 3.toDouble(), 2.toDouble())) { "key$it" })
        assertEquals("[4.0, 3.0, 2.0]", Arrayx.joinToArrayString(doubleArrayOf(4.toDouble(), 3.toDouble(), 2.toDouble())))

        assertEquals("[keytrue, keyfalse, keyfalse]", Arrayx.joinToArrayString(booleanArrayOf(true, false, false)) { "key$it" })
        assertEquals("[true, false, false]", Arrayx.joinToArrayString(booleanArrayOf(true, false, false)))
    }
}