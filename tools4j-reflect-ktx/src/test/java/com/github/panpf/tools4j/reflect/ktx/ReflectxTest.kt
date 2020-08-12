/*
 * Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>
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

package com.github.panpf.tools4j.reflect.ktx

import com.github.panpf.tools4j.reflect.Brake
import com.github.panpf.tools4j.reflect.Reflectx
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
        Assert.assertNotNull(TestClass3::class.java.getDeclaredFieldRecursive("testField31"))
        try {
            TestClass3::class.java.getDeclaredFieldRecursive("testField31_no")
            Assert.fail()
        } catch (ignored: Exception) {
        }


        /*
         * getFieldsRecursive
         */
        val filterJacocoFieldPredicate = { field: Field -> field.name != "\$jacocoData" }
        Assert.assertEquals(2, TestClass3::class.java.getDeclaredFieldsRecursive(Brake.end(TestClass3::class.java)).filter(filterJacocoFieldPredicate).size.toLong())
        Assert.assertEquals(2, TestClass3::class.java.getDeclaredFieldsRecursive(Brake.until(TestClass2::class.java)).filter(filterJacocoFieldPredicate).size.toLong())
        Assert.assertEquals(4, TestClass3::class.java.getDeclaredFieldsRecursive(Brake.end(TestClass2::class.java)).filter(filterJacocoFieldPredicate).size.toLong())
        Assert.assertEquals(4, TestClass3::class.java.getDeclaredFieldsRecursive(Brake.until(TestClass1::class.java)).filter(filterJacocoFieldPredicate).size.toLong())
        Assert.assertEquals(6, TestClass3::class.java.getDeclaredFieldsRecursive(Brake.end(TestClass1::class.java)).filter(filterJacocoFieldPredicate).size.toLong())
        Assert.assertEquals(6, TestClass3::class.java.getDeclaredFieldsRecursive().filter(filterJacocoFieldPredicate).size.toLong())


        /*
         * get and set field value
         */
        val testClass = TestClass1("one", "two")
        Assert.assertEquals("one", testClass.getFieldValue(testClass.javaClass.getDeclaredFieldRecursive("testField11")))
        testClass.setFieldValue(testClass.javaClass.getDeclaredFieldRecursive("testField11"), "field11y")
        Assert.assertEquals("field11y", testClass.getFieldValue(testClass.javaClass.getDeclaredFieldRecursive("testField11")))

        val testClass1 = TestClass1("one", "two")
        Assert.assertEquals("one", testClass1.getFieldValue("testField11"))
        testClass1.setFieldValue("testField11", "field11y")
        Assert.assertEquals("field11y", testClass1.getFieldValue("testField11"))


        /*
         * get and set static field value
         */
        TestStatic::class.java.getDeclaredFieldRecursive("field1").setStaticValue("field11")
        Assert.assertEquals("field11", Reflectx.getStaticFieldValue(TestStatic::class.java.getDeclaredFieldRecursive("field1")))
        TestStatic::class.java.getDeclaredFieldRecursive("field1").setStaticValue("field12")
        Assert.assertEquals("field12", TestStatic::class.java.getDeclaredFieldRecursive("field1").getStaticValue())

        TestStatic::class.java.getDeclaredFieldRecursive("field1").setStaticValue("field11")
        Assert.assertEquals("field11", TestStatic::class.java.getStaticFieldValue("field1"))
        TestStatic::class.java.setStaticFieldValue("field1", "field12")
        Assert.assertEquals("field12", TestStatic::class.java.getStaticFieldValue("field1"))
    }

    @Test
    @Throws(NoSuchMethodException::class, NoSuchFieldException::class)
    fun testMethod() {
        /*
         * getMethodRecursive
         */
        Assert.assertNotNull(TestClass3::class.java.getDeclaredMethodRecursive("getTestField31"))
        try {
            TestClass3::class.java.getDeclaredMethodRecursive("getTestField31_no")
            Assert.fail()
        } catch (ignored: NoSuchMethodException) {
        }


        /*
         * getMethodsRecursive
         */
        val filterJacocoMethodPredicate = { method: Method -> method.name != "\$jacocoInit" }
        Assert.assertEquals(4, TestClass3::class.java.getDeclaredMethodsRecursive(Brake.end(TestClass3::class.java)).filter(filterJacocoMethodPredicate).size.toLong())
        Assert.assertEquals(4, TestClass3::class.java.getDeclaredMethodsRecursive(Brake.until(TestClass2::class.java)).filter(filterJacocoMethodPredicate).size.toLong())
        Assert.assertEquals(8, TestClass3::class.java.getDeclaredMethodsRecursive(Brake.end(TestClass2::class.java)).filter(filterJacocoMethodPredicate).size.toLong())
        Assert.assertEquals(8, TestClass3::class.java.getDeclaredMethodsRecursive(Brake.until(TestClass1::class.java)).filter(filterJacocoMethodPredicate).size.toLong())
        Assert.assertEquals(12, TestClass3::class.java.getDeclaredMethodsRecursive(Brake.end(TestClass1::class.java)).filter(filterJacocoMethodPredicate).size.toLong())
        Assert.assertEquals(12, TestClass3::class.java.getDeclaredMethodsRecursive(Brake.until(Any::class.java)).filter(filterJacocoMethodPredicate).size.toLong())
        Assert.assertEquals((12 + Any::class.java.getDeclaredMethodsRecursive().size).toLong(), TestClass3::class.java.getDeclaredMethodsRecursive(Brake.end(Any::class.java)).filter(filterJacocoMethodPredicate).size.toLong())
        Assert.assertEquals((12 + Any::class.java.getDeclaredMethodsRecursive().size).toLong(), TestClass3::class.java.getDeclaredMethodsRecursive().filter(filterJacocoMethodPredicate).size.toLong())


        /*
         * callMethod
         */
        val testClass = TestClass1("one", "two")
        Assert.assertEquals("one", testClass.callMethod(testClass.javaClass.getDeclaredMethodRecursive("getTestField11")))
        testClass.callMethod<Any>(testClass.javaClass.getDeclaredMethodRecursive("setTestField11", String::class.java), "updatex")
        Assert.assertEquals("updatex", testClass.callMethod(testClass.javaClass.getDeclaredMethodRecursive("getTestField11")))

        val testClass1 = TestClass1("one", "two")
        Assert.assertEquals("one", testClass1.callMethod("getTestField11"))
        testClass1.callMethod<Any>("setTestField11", "updatex")
        Assert.assertEquals("updatex", testClass1.callMethod("getTestField11"))


        /*
         * callStaticMethod
         */
        TestStatic::class.java.setStaticFieldValue("field1", "field11")
        Assert.assertEquals("field11", TestStatic::class.java.getDeclaredMethodRecursive("getField1").callStaticMethod())
        TestStatic::class.java.getDeclaredMethodRecursive("setField1", String::class.java).callStaticMethod<Any>("field12")
        Assert.assertEquals("field12", TestStatic::class.java.getDeclaredMethodRecursive("getField1").callStaticMethod())

        TestStatic::class.java.setStaticFieldValue("field1", "field11")
        Assert.assertEquals("field11", TestStatic::class.java.callStaticMethod("getField1"))
        TestStatic::class.java.callStaticMethod<Any>("setField1", "field12")
        Assert.assertEquals("field12", TestStatic::class.java.callStaticMethod("getField1"))
    }

    @Test
    @Throws(NoSuchMethodException::class)
    fun testConstructor() {
        Assert.assertNotNull(TestClass1::class.java.getDeclaredConstructorRecursive(String::class.java, String::class.java))
        try {
            TestClass1::class.java.getDeclaredConstructorRecursive(String::class.java)
            Assert.fail()
        } catch (ignored: NoSuchMethodException) {
        }

        Assert.assertNotNull(TestClass2::class.java.getDeclaredConstructorRecursive(String::class.java, String::class.java, String::class.java, String::class.java))
        try {
            TestClass2::class.java.getDeclaredConstructorRecursive(String::class.java)
            Assert.fail()
        } catch (ignored: NoSuchMethodException) {
        }

        Assert.assertNotNull(TestClass3::class.java.getDeclaredConstructorRecursive(String::class.java, String::class.java, String::class.java, String::class.java, String::class.java, String::class.java))
        try {
            TestClass3::class.java.getDeclaredConstructorRecursive(String::class.java)
            Assert.fail()
        } catch (ignored: NoSuchMethodException) {
        }

        Assert.assertEquals(1, TestClass3::class.java.getDeclaredConstructorsRecursive(Brake.end(TestClass3::class.java)).size.toLong())
        Assert.assertEquals(1, TestClass3::class.java.getDeclaredConstructorsRecursive(Brake.until(TestClass2::class.java)).size.toLong())
        Assert.assertEquals(2, TestClass3::class.java.getDeclaredConstructorsRecursive(Brake.end(TestClass2::class.java)).size.toLong())
        Assert.assertEquals(2, TestClass3::class.java.getDeclaredConstructorsRecursive(Brake.until(TestClass1::class.java)).size.toLong())
        Assert.assertEquals(3, TestClass3::class.java.getDeclaredConstructorsRecursive(Brake.end(TestClass1::class.java)).size.toLong())
        Assert.assertEquals(3, TestClass3::class.java.getDeclaredConstructorsRecursive(Brake.until(Any::class.java)).size.toLong())
        Assert.assertEquals((3 + Any::class.java.getDeclaredConstructorsRecursive().size).toLong(), TestClass3::class.java.getDeclaredConstructorsRecursive().size.toLong())
    }

    @Test
    fun testHierarchy() {
        Assert.assertEquals(TestClass3::class.java.getClassHierarchy().size.toLong(), 4)
        Assert.assertEquals(TestClass3::class.java.getClassHierarchy(true).size.toLong(), 3)
    }

    @Test
    fun testType() {
        try {
            Assert.assertTrue(TestType::class.java.getDeclaredFieldRecursive("strings").isTypeArray(String::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }

        try {
            Assert.assertFalse(TestType::class.java.getDeclaredFieldRecursive("strings").isTypeArray(Int::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }

        try {
            Assert.assertFalse(TestType::class.java.getDeclaredFieldRecursive("name").isTypeArray(Int::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }


        try {
            Assert.assertTrue(TestType::class.java.getDeclaredFieldRecursive("stringList").isTypeCollection(List::class.java, String::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }

        try {
            Assert.assertFalse(TestType::class.java.getDeclaredFieldRecursive("stringList").isTypeCollection(List::class.java, Int::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }

        try {
            Assert.assertFalse(TestType::class.java.getDeclaredFieldRecursive("stringList").isTypeCollection(Set::class.java, Int::class.java))
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException(e)
        }
    }

    open class TestClass1(var testField11: String?, var testField12: String?)

    @Suppress("unused")
    open class TestClass2(testField11: String, testField12: String, var testField21: String?, var testField22: String?) : TestClass1(testField11, testField12)

    @Suppress("unused")
    class TestClass3(testField11: String, testField12: String, testField21: String, testField22: String, var testField31: String?, var testField32: String?) : TestClass2(testField11, testField12, testField21, testField22)

    @Suppress("unused")
    class TestStatic {
        companion object {
            @JvmStatic
            var field1 = "field11"
        }
    }

    @Suppress("unused")
    class TestType {
        internal var strings = arrayOf<String>()
        internal var stringList: List<String> = ArrayList()
        internal var name: String = "nameValue"
    }
}
