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
package com.github.panpf.tools4j.reflect

import org.junit.Assert
import org.junit.Test
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.*

class ReflectxTest {
    @Test
    @Throws(NoSuchFieldException::class)
    fun testField() {
        /*
         * getFieldRecursive
         */
        Assert.assertNotNull(Reflectx.getDeclaredFieldRecursive(TestClass3::class.java, "testField31"))
        try {
            Reflectx.getDeclaredFieldRecursive(TestClass3::class.java, "testField31_no")
            Assert.fail()
        } catch (ignored: Exception) {
        }


        /*
         * getFieldsRecursive
         */
        val filterJacocoFieldPredicate: (Field) -> Boolean = { it.name != "\$jacocoData" }
        Assert.assertEquals(2, Reflectx.getDeclaredFieldsRecursive(TestClass3::class.java, Brake.end(TestClass3::class.java)).filter(filterJacocoFieldPredicate).size)
        Assert.assertEquals(2, Reflectx.getDeclaredFieldsRecursive(TestClass3::class.java, Brake.until(TestClass2::class.java)).filter(filterJacocoFieldPredicate).size)
        Assert.assertEquals(4, Reflectx.getDeclaredFieldsRecursive(TestClass3::class.java, Brake.end(TestClass2::class.java)).filter(filterJacocoFieldPredicate).size)
        Assert.assertEquals(4, Reflectx.getDeclaredFieldsRecursive(TestClass3::class.java, Brake.until(TestClass1::class.java)).filter(filterJacocoFieldPredicate).size)
        Assert.assertEquals(6, Reflectx.getDeclaredFieldsRecursive(TestClass3::class.java, Brake.end(TestClass1::class.java)).filter(filterJacocoFieldPredicate).size)
        Assert.assertEquals(6, Reflectx.getDeclaredFieldsRecursive(TestClass3::class.java).filter(filterJacocoFieldPredicate).size)


        /*
         * get and set field value
         */
        val testClass = TestClass1("one", "two")
        Assert.assertEquals("one", Reflectx.getFieldValue(testClass, Reflectx.getDeclaredFieldRecursive(testClass.javaClass, "testField11")))
        Reflectx.setFieldValue(testClass, Reflectx.getDeclaredFieldRecursive(testClass.javaClass, "testField11"), "field11y")
        Assert.assertEquals("field11y", Reflectx.getFieldValue(testClass, Reflectx.getDeclaredFieldRecursive(testClass.javaClass, "testField11")))
        val testClass1 = TestClass1("one", "two")
        Assert.assertEquals("one", Reflectx.getFieldValue(testClass1, "testField11"))
        Reflectx.setFieldValue(testClass1, "testField11", "field11y")
        Assert.assertEquals("field11y", Reflectx.getFieldValue(testClass1, "testField11"))


        /*
         * get and set static field value
         */Reflectx.setStaticFieldValue(Reflectx.getDeclaredFieldRecursive(TestStatic::class.java, "field1"), "field11")
        Assert.assertEquals("field11", Reflectx.getStaticFieldValue(Reflectx.getDeclaredFieldRecursive(TestStatic::class.java, "field1")))
        Reflectx.setStaticFieldValue(Reflectx.getDeclaredFieldRecursive(TestStatic::class.java, "field1"), "field12")
        Assert.assertEquals("field12", Reflectx.getStaticFieldValue(Reflectx.getDeclaredFieldRecursive(TestStatic::class.java, "field1")))
        Reflectx.setStaticFieldValue(Reflectx.getDeclaredFieldRecursive(TestStatic::class.java, "field1"), "field11")
        Assert.assertEquals("field11", Reflectx.getStaticFieldValue(TestStatic::class.java, "field1"))
        Reflectx.setStaticFieldValue(TestStatic::class.java, "field1", "field12")
        Assert.assertEquals("field12", Reflectx.getStaticFieldValue(TestStatic::class.java, "field1"))
    }

    @Test
    @Throws(NoSuchMethodException::class, NoSuchFieldException::class)
    fun testMethod() {
        /*
         * getMethodRecursive
         */
        Assert.assertNotNull(Reflectx.getDeclaredMethodRecursive(TestClass3::class.java, "getTestField31"))
        try {
            Reflectx.getDeclaredMethodRecursive(TestClass3::class.java, "getTestField31_no")
            Assert.fail()
        } catch (ignored: NoSuchMethodException) {
        }


        /*
         * getMethodsRecursive
         */
        val filterJacocoMethodPredicate: (Method) -> Boolean = { it.name != "\$jacocoInit" }
        Assert.assertEquals(4, Reflectx.getDeclaredMethodsRecursive(TestClass3::class.java, Brake.end(TestClass3::class.java)).filter(filterJacocoMethodPredicate).size)
        Assert.assertEquals(4, Reflectx.getDeclaredMethodsRecursive(TestClass3::class.java, Brake.until(TestClass2::class.java)).filter(filterJacocoMethodPredicate).size)
        Assert.assertEquals(8, Reflectx.getDeclaredMethodsRecursive(TestClass3::class.java, Brake.end(TestClass2::class.java)).filter(filterJacocoMethodPredicate).size)
        Assert.assertEquals(8, Reflectx.getDeclaredMethodsRecursive(TestClass3::class.java, Brake.until(TestClass1::class.java)).filter(filterJacocoMethodPredicate).size)
        Assert.assertEquals(12, Reflectx.getDeclaredMethodsRecursive(TestClass3::class.java, Brake.end(TestClass1::class.java)).filter(filterJacocoMethodPredicate).size)
        Assert.assertEquals(12, Reflectx.getDeclaredMethodsRecursive(TestClass3::class.java, Brake.until(Any::class.java)).filter(filterJacocoMethodPredicate).size)
        Assert.assertEquals(12 + Reflectx.getDeclaredMethodsRecursive(Any::class.java).size, Reflectx.getDeclaredMethodsRecursive(TestClass3::class.java, Brake.end(Any::class.java)).filter(filterJacocoMethodPredicate).size)
        Assert.assertEquals(12 + Reflectx.getDeclaredMethodsRecursive(Any::class.java).size, Reflectx.getDeclaredMethodsRecursive(TestClass3::class.java).filter(filterJacocoMethodPredicate).size)


        /*
         * callMethod
         */
        val testClass = TestClass1("one", "two")
        Assert.assertEquals("one", Reflectx.callMethod(testClass, Reflectx.getDeclaredMethodRecursive(testClass.javaClass, "getTestField11")))
        Reflectx.callMethod<Any>(testClass, Reflectx.getDeclaredMethodRecursive(testClass.javaClass, "setTestField11", String::class.java), "updatex")
        Assert.assertEquals("updatex", Reflectx.callMethod(testClass, Reflectx.getDeclaredMethodRecursive(testClass.javaClass, "getTestField11")))
        val testClass1 = TestClass1("one", "two")
        Assert.assertEquals("one", Reflectx.callMethod(testClass1, "getTestField11"))
        Reflectx.callMethod<Any>(testClass1, "setTestField11", "updatex")
        Assert.assertEquals("updatex", Reflectx.callMethod(testClass1, "getTestField11"))


        /*
         * callStaticMethod
         */
        Reflectx.setStaticFieldValue(TestStatic::class.java, "field1", "field11")
        Assert.assertEquals("field11", Reflectx.callStaticMethod(Reflectx.getDeclaredMethodRecursive(TestStatic::class.java, "getField1")))
        Reflectx.callStaticMethod<Any>(Reflectx.getDeclaredMethodRecursive(TestStatic::class.java, "setField1", String::class.java), "field12")
        Assert.assertEquals("field12", Reflectx.callStaticMethod(Reflectx.getDeclaredMethodRecursive(TestStatic::class.java, "getField1")))
        Reflectx.setStaticFieldValue(TestStatic::class.java, "field1", "field11")
        Assert.assertEquals("field11", Reflectx.callStaticMethod(TestStatic::class.java, "getField1"))
        Reflectx.callStaticMethod<Any>(TestStatic::class.java, "setField1", "field12")
        Assert.assertEquals("field12", Reflectx.callStaticMethod(TestStatic::class.java, "getField1"))
    }

    @Test
    @Throws(NoSuchMethodException::class)
    fun testConstructor() {
        Assert.assertNotNull(Reflectx.getDeclaredConstructorRecursive(TestClass1::class.java, String::class.java, String::class.java))
        try {
            Reflectx.getDeclaredConstructorRecursive(TestClass1::class.java, String::class.java)
            Assert.fail()
        } catch (ignored: NoSuchMethodException) {
        }
        Assert.assertNotNull(Reflectx.getDeclaredConstructorRecursive(TestClass2::class.java, String::class.java, String::class.java, String::class.java, String::class.java))
        try {
            Reflectx.getDeclaredConstructorRecursive(TestClass2::class.java, String::class.java)
            Assert.fail()
        } catch (ignored: NoSuchMethodException) {
        }
        Assert.assertNotNull(Reflectx.getDeclaredConstructorRecursive(TestClass3::class.java, String::class.java, String::class.java, String::class.java, String::class.java, String::class.java, String::class.java))
        try {
            Reflectx.getDeclaredConstructorRecursive(TestClass3::class.java, String::class.java)
            Assert.fail()
        } catch (ignored: NoSuchMethodException) {
        }
        Assert.assertEquals(1, Reflectx.getDeclaredConstructorsRecursive(TestClass3::class.java, Brake.end(TestClass3::class.java)).size.toLong())
        Assert.assertEquals(1, Reflectx.getDeclaredConstructorsRecursive(TestClass3::class.java, Brake.until(TestClass2::class.java)).size.toLong())
        Assert.assertEquals(2, Reflectx.getDeclaredConstructorsRecursive(TestClass3::class.java, Brake.end(TestClass2::class.java)).size.toLong())
        Assert.assertEquals(2, Reflectx.getDeclaredConstructorsRecursive(TestClass3::class.java, Brake.until(TestClass1::class.java)).size.toLong())
        Assert.assertEquals(3, Reflectx.getDeclaredConstructorsRecursive(TestClass3::class.java, Brake.end(TestClass1::class.java)).size.toLong())
        Assert.assertEquals(3, Reflectx.getDeclaredConstructorsRecursive(TestClass3::class.java, Brake.until(Any::class.java)).size.toLong())
        Assert.assertEquals(3 + Reflectx.getDeclaredConstructorsRecursive(Any::class.java).size.toLong(), Reflectx.getDeclaredConstructorsRecursive(TestClass3::class.java).size.toLong())
    }

    @Test
    fun testHierarchy() {
        Assert.assertEquals(Reflectx.getClassHierarchy(TestClass3::class.java).size.toLong(), 4)
        Assert.assertEquals(Reflectx.getClassHierarchy(TestClass3::class.java, true).size.toLong(), 3)
    }

    @Test
    fun testType() {
        try {
            Assert.assertTrue(Reflectx.isTypeArray(Reflectx.getDeclaredFieldRecursive(TestType::class.java, "strings"), String::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }
        try {
            Assert.assertFalse(Reflectx.isTypeArray(Reflectx.getDeclaredFieldRecursive(TestType::class.java, "strings"), Int::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }
        try {
            Assert.assertFalse(Reflectx.isTypeArray(Reflectx.getDeclaredFieldRecursive(TestType::class.java, "name"), Int::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }
        try {
            Assert.assertTrue(Reflectx.isTypeCollection(Reflectx.getDeclaredFieldRecursive(TestType::class.java, "stringList"), MutableList::class.java, String::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }
        try {
            Assert.assertFalse(Reflectx.isTypeCollection(Reflectx.getDeclaredFieldRecursive(TestType::class.java, "stringList"), MutableList::class.java, Int::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }
        try {
            Assert.assertFalse(Reflectx.isTypeCollection(Reflectx.getDeclaredFieldRecursive(TestType::class.java, "stringList"), MutableSet::class.java, Int::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }
    }

    open class TestClass1(var testField11: String, var testField12: String)

    @Suppress("unused")
    open class TestClass2(testField11: String, testField12: String, var testField21: String, var testField22: String) : TestClass1(testField11, testField12)

    @Suppress("unused")
    class TestClass3(testField11: String, testField12: String, testField21: String, testField22: String, var testField31: String, var testField32: String) : TestClass2(testField11, testField12, testField21, testField22)

    @Suppress("unused")
    class TestStatic {
        companion object{
            private var field1 = "field11"

            @JvmStatic
            fun getField1(): String {
                return field1
            }

            @JvmStatic
            fun setField1(field1: String) {
                TestStatic.field1 = field1
            }
        }
    }

    @Suppress("unused")
    class TestType {
        var strings = arrayOf<String>()
        var stringList: List<String> = ArrayList()
        var name = "nameValue"
    }
}