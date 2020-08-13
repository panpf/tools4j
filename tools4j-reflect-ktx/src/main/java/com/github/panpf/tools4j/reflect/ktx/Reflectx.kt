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

@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4j.reflect.ktx

import com.github.panpf.tools4j.reflect.Brake
import com.github.panpf.tools4j.reflect.Reflectx
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method


/*
 * Reflect related extension methods or properties
 */


/* ******************************************* Field *******************************************/


/**
 * Get the declared field with the specified name from the specified class
 */
@Throws(NoSuchFieldException::class)
inline fun Class<*>.getDeclaredFieldRecursive(fieldName: String): Field = Reflectx.getDeclaredFieldRecursive(this, fieldName)


/**
 * Get all the declared fields of a given class and its parent classes
 *
 * @param brake Specify which class to stop searching
 */
inline fun Class<*>.getDeclaredFieldsRecursive(brake: Brake): Array<Field> = Reflectx.getDeclaredFieldsRecursive(this, brake)

/**
 * Get all the declared fields of a given class and all its parent classes
 */
inline fun Class<*>.getDeclaredFieldsRecursive(): Array<Field> = Reflectx.getDeclaredFieldsRecursive(this)


/**
 * Get the value of the specified field
 */
inline fun <T> Any.getFieldValue(field: Field): T? = Reflectx.getFieldValue<T>(this, field)

/**
 * Get the value of the specified field name
 */
@Throws(NoSuchFieldException::class)
inline fun <T> Any.getFieldValue(fieldName: String): T? = Reflectx.getFieldValue<T>(this, fieldName)


/**
 * Get the value of the specified field
 */
inline fun <T> Field.getStaticValue(): T? = Reflectx.getStaticFieldValue<T>(this)

/**
 * Get the value of the specified field name
 */
@Throws(NoSuchFieldException::class)
inline fun <T> Class<*>.getStaticFieldValue(fieldName: String): T? = Reflectx.getStaticFieldValue<T>(this, fieldName)


/**
 * Set field value
 */
inline fun Any.setFieldValue(field: Field, newValue: Any?) = Reflectx.setFieldValue(this, field, newValue)

/**
 * Set field value by field name
 */
@Throws(NoSuchFieldException::class)
inline fun Any.setFieldValue(fieldName: String, newValue: Any?) = Reflectx.setFieldValue(this, fieldName, newValue)


/**
 * Set field value
 */
inline fun Field.setStaticValue(newValue: Any?) = Reflectx.setStaticFieldValue(this, newValue)

/**
 * Set field value by field name
 */
@Throws(NoSuchFieldException::class)
inline fun Class<*>.setStaticFieldValue(fieldName: String, newValue: Any?) = Reflectx.setStaticFieldValue(this, fieldName, newValue)


/* ******************************************* Method *******************************************/


/**
 * Get the declared method with the specified name from the specified class
 */
@Throws(NoSuchMethodException::class)
inline fun Class<*>.getDeclaredMethodRecursive(methodName: String, vararg params: Class<*>): Method = Reflectx.getDeclaredMethodRecursive(this, methodName, *params)


/**
 * Get all the declared methods of a given class and its parent classes
 *
 * @param brake Specify which class to stop searching
 */
inline fun Class<*>.getDeclaredMethodsRecursive(brake: Brake): Array<Method> = Reflectx.getDeclaredMethodsRecursive(this, brake)

/**
 * Get all the declared methods of a given class and its parent classes
 */
inline fun Class<*>.getDeclaredMethodsRecursive(): Array<Method> = Reflectx.getDeclaredMethodsRecursive(this)


/**
 * Method of executing of the specified object
 */
inline fun <T> Any.callMethod(method: Method, vararg params: Any): T? = Reflectx.callMethod<T>(this, method, *params)

/**
 * Method of executing the specified name of the specified object
 */
@Throws(NoSuchMethodException::class)
inline fun <T> Any.callMethod(methodName: String, vararg params: Any): T? = Reflectx.callMethod<T>(this, methodName, *params)


/**
 * Method of executing of the specified object
 */
inline fun <T> Method.callStaticMethod(vararg params: Any): T? = Reflectx.callStaticMethod<T>(this, *params)

/**
 * Method of executing the specified name of the specified object
 */
@Throws(NoSuchMethodException::class)
inline fun <T> Class<*>.callStaticMethod(methodName: String, vararg params: Any): T? = Reflectx.callStaticMethod<T>(this, methodName, *params)


/* ******************************************* Constructor *******************************************/


/**
 * Get the declared constructor from the specified class
 */
@Throws(NoSuchMethodException::class)
inline fun Class<*>.getDeclaredConstructorRecursive(vararg params: Class<*>): Constructor<*> = Reflectx.getDeclaredConstructorRecursive(this, *params)


/**
 * Get all the declared constructors of a given class and its parent classes
 *
 * @param brake Specify which class to stop searching
 */
inline fun Class<*>.getDeclaredConstructorsRecursive(brake: Brake): Array<Constructor<*>> = Reflectx.getDeclaredConstructorsRecursive(this, brake)

/**
 * Get all the declared constructors of a given class and its parent classes
 */
inline fun Class<*>.getDeclaredConstructorsRecursive(): Array<Constructor<*>> = Reflectx.getDeclaredConstructorsRecursive(this)


/* ******************************************* Class Hierarchy *******************************************/


/**
 * Get all the inheritance lists of the specified class
 *
 * @param ignoreSelf Ignore myself in the return list
 */
inline fun Class<*>.getClassHierarchy(ignoreSelf: Boolean): Array<Class<*>> = Reflectx.getClassHierarchy(this, ignoreSelf)

/**
 * Get all the inheritance lists of the specified class
 */
inline fun Class<*>.getClassHierarchy(): Array<Class<*>> = Reflectx.getClassHierarchy(this)


/* ******************************************* Type *******************************************/


/**
 * Determine if the given field is an array of the specified type
 */
inline fun Field.isTypeArray(type: Class<*>): Boolean = Reflectx.isTypeArray(this, type)

/**
 * Determine if a given field is a collection of the specified type
 */
inline fun Field.isTypeCollection(collectionType: Class<out Collection<*>>, type: Class<*>): Boolean = Reflectx.isTypeCollection(this, collectionType, type)