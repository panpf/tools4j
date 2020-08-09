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

package com.github.panpf.tools4j.reflect;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.*;
import java.util.*;

/**
 * Class tool
 */
public class Classx {

    private Classx() {
    }


    /* ******************************************* Field *******************************************/

    /**
     * Get the declared field with the specified name from the specified class
     */
    @NotNull
    public static Field getDeclaredFieldRecursive(@NotNull Class<?> clazz, @NotNull String fieldName) throws NoSuchFieldException {
        Field field = null;

        Class<?> currentClazz = clazz;
        while (field == null && currentClazz != null) {
            try {
                field = currentClazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
            }

            if (field == null) {
                currentClazz = currentClazz.getSuperclass();
            }
        }

        if (field == null) {
            throw new NoSuchFieldException(String.format("No such field by name '%s' in class '%s' and its parent class", fieldName, clazz.getName()));
        } else {
            return field;
        }
    }


    /**
     * Get all the declared fields of a given class and its parent classes
     *
     * @param brake Specify which class to stop searching
     */
    @NotNull
    public static Field[] getDeclaredFieldsRecursive(@NotNull Class<?> clazz, @Nullable Brake brake) {
        List<Field> fieldList = new LinkedList<>();

        Class<?> currentClazz = clazz;
        while (currentClazz != null) {
            if (brake != null && brake.clazz.equals(currentClazz) && !brake.include) {
                break;
            }
            Field[] fields = currentClazz.getDeclaredFields();
            Collections.addAll(fieldList, fields);
            if (brake != null && brake.clazz.equals(currentClazz)) {
                break;
            } else {
                currentClazz = currentClazz.getSuperclass();
            }
        }

        //noinspection ToArrayCallWithZeroLengthArrayArgument
        return fieldList.toArray(new Field[fieldList.size()]);
    }

    /**
     * Get all the declared fields of a given class and all its parent classes
     */
    @NotNull
    public static Field[] getDeclaredFieldsRecursive(@NotNull Class<?> clazz) {
        return getDeclaredFieldsRecursive(clazz, null);
    }


    /**
     * Get the value of the specified field
     */
    @Nullable
    public static <T> T getFieldValue(@NotNull Object object, @NotNull Field field) {
        field.setAccessible(true);
        try {
            //noinspection unchecked
            return (T) field.get(object);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Get the value of the specified field name
     */
    @Nullable
    public static <T> T getFieldValue(@NotNull Object object, @NotNull String fieldName) throws NoSuchFieldException {
        //noinspection unchecked
        return (T) getFieldValue(object, getDeclaredFieldRecursive(object.getClass(), fieldName));
    }


    /**
     * Get the value of the specified field
     */
    @Nullable
    public static <T> T getStaticFieldValue(@NotNull Field field) {
        field.setAccessible(true);
        try {
            //noinspection unchecked
            return (T) field.get(null);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Get the value of the specified field name
     */
    @Nullable
    public static <T> T getStaticFieldValue(@NotNull Class<?> clazz, @NotNull String fieldName) throws NoSuchFieldException {
        return getStaticFieldValue(getDeclaredFieldRecursive(clazz, fieldName));
    }


    /**
     * Set field value
     */
    public static void setFieldValue(@NotNull Object object, @NotNull Field field, @Nullable Object newValue) {
        field.setAccessible(true);
        try {
            field.set(object, newValue);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Set field value by field name
     */
    public static void setFieldValue(@NotNull Object object, @NotNull String fieldName, @Nullable Object newValue) throws NoSuchFieldException {
        setFieldValue(object, getDeclaredFieldRecursive(object.getClass(), fieldName), newValue);
    }


    /**
     * Set field value
     */
    public static void setStaticFieldValue(@NotNull Field field, @Nullable Object newValue) {
        field.setAccessible(true);
        try {
            field.set(null, newValue);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Set field value by field name
     */
    public static void setStaticFieldValue(@NotNull Class<?> clazz, @NotNull String fieldName, @Nullable Object newValue) throws NoSuchFieldException {
        setStaticFieldValue(getDeclaredFieldRecursive(clazz, fieldName), newValue);
    }


    /* ******************************************* Method *******************************************/


    /**
     * Get the declared method with the specified name from the specified class
     */
    @NotNull
    public static Method getDeclaredMethodRecursive(@NotNull Class<?> clazz, @NotNull String methodName, @Nullable Class<?>... params) throws NoSuchMethodException {
        Method method = null;

        Class<?> currentClazz = clazz;
        while (method == null && currentClazz != null) {
            try {
                method = currentClazz.getDeclaredMethod(methodName, params);
            } catch (NoSuchMethodException ignored) {
            }

            if (method == null) {
                currentClazz = currentClazz.getSuperclass();
            }
        }

        if (method == null) {
            throw new NoSuchMethodException(String.format("No such method by name '%s' and params '%s' in class '%s' and its parent class", methodName, Arrays.toString(params), clazz.getName()));
        } else {
            return method;
        }
    }


    /**
     * Get all the declared methods of a given class and its parent classes
     *
     * @param brake Specify which class to stop searching
     */
    @NotNull
    public static Method[] getDeclaredMethodsRecursive(@NotNull Class<?> clazz, @Nullable Brake brake) {
        List<Method> methodList = new LinkedList<>();

        Class<?> currentClazz = clazz;
        while (currentClazz != null) {
            if (brake != null && brake.clazz.equals(currentClazz) && !brake.include) {
                break;
            }
            Method[] methods = currentClazz.getDeclaredMethods();
            Collections.addAll(methodList, methods);
            if (brake != null && brake.clazz.equals(currentClazz)) {
                break;
            } else {
                currentClazz = currentClazz.getSuperclass();
            }
        }

        //noinspection ToArrayCallWithZeroLengthArrayArgument
        return methodList.toArray(new Method[methodList.size()]);
    }

    /**
     * Get all the declared methods of a given class and all its parent classes
     */
    @NotNull
    public static Method[] getDeclaredMethodsRecursive(@NotNull Class<?> clazz) {
        return getDeclaredMethodsRecursive(clazz, null);
    }


    /**
     * Method of executing of the specified object
     */
    @Nullable
    public static <T> T callMethod(@NotNull Object object, @NotNull Method method, @Nullable Object... params) {
        method.setAccessible(true);
        try {
            //noinspection unchecked
            return (T) method.invoke(object, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Method of executing the specified name of the specified object
     */
    @Nullable
    public static <T> T callMethod(@NotNull Object object, @NotNull String methodName, Object... params) throws NoSuchMethodException {
        Class<?>[] paramClazzArray = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            paramClazzArray[i] = params[i].getClass();
        }
        Method method = getDeclaredMethodRecursive(object.getClass(), methodName, paramClazzArray);
        return callMethod(object, method, params);
    }


    /**
     * Method of executing of the specified object
     */
    @Nullable
    public static <T> T callStaticMethod(@NotNull Method method, @Nullable Object... params) {
        method.setAccessible(true);
        try {
            //noinspection unchecked
            return (T) method.invoke(null, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Method of executing the specified name of the specified class
     */
    @Nullable
    public static <T> T callStaticMethod(@NotNull Class<?> clazz, @NotNull String methodName, Object... params) throws NoSuchMethodException {
        Class<?>[] paramClazzArray = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            paramClazzArray[i] = params[i].getClass();
        }
        Method method = getDeclaredMethodRecursive(clazz, methodName, paramClazzArray);
        return callStaticMethod(method, params);
    }


    /* ******************************************* Constructor *******************************************/


    /**
     * Get the declared constructor from the specified class
     */
    @NotNull
    public static Constructor<?> getDeclaredConstructorRecursive(@NotNull Class<?> clazz, @Nullable Class<?>... params) throws NoSuchMethodException {
        Constructor<?> constructor = null;

        Class<?> currentClazz = clazz;
        while (constructor == null && currentClazz != null) {
            try {
                constructor = currentClazz.getDeclaredConstructor(params);
            } catch (NoSuchMethodException ignored) {
            }

            if (constructor == null) {
                currentClazz = currentClazz.getSuperclass();
            }
        }

        if (constructor == null) {
            throw new NoSuchMethodException(String.format("No such constructor by params '%s' in class '%s' and its parent class", Arrays.toString(params), clazz.getName()));
        } else {
            return constructor;
        }
    }


    /**
     * Get all the declared constructors of a given class and its parent classes
     *
     * @param brake Specify which class to stop searching
     */
    @NotNull
    public static Constructor<?>[] getDeclaredConstructorsRecursive(@NotNull Class<?> clazz, @Nullable Brake brake) {
        List<Constructor<?>> constructorList = new LinkedList<>();

        Class<?> currentClazz = clazz;
        while (currentClazz != null) {
            if (brake != null && brake.clazz.equals(currentClazz) && !brake.include) {
                break;
            }
            Constructor<?>[] constructors = currentClazz.getDeclaredConstructors();
            Collections.addAll(constructorList, constructors);
            if (brake != null && brake.clazz.equals(currentClazz)) {
                break;
            } else {
                currentClazz = currentClazz.getSuperclass();
            }
        }

        //noinspection ToArrayCallWithZeroLengthArrayArgument
        return constructorList.toArray(new Constructor[constructorList.size()]);
    }

    /**
     * Get all the declared constructors of a given class and its parent classes
     */
    @NotNull
    public static Constructor<?>[] getDeclaredConstructorsRecursive(@NotNull Class<?> clazz) {
        return getDeclaredConstructorsRecursive(clazz, null);
    }


    /* ******************************************* Class Hierarchy *******************************************/


    /**
     * Get all the inheritance lists of the specified class
     *
     * @param ignoreSelf Ignore myself in the return list
     */
    @NotNull
    public static Class<?>[] getClassHierarchy(@NotNull Class<?> clazz, boolean ignoreSelf) {
        List<Class<?>> classList = new LinkedList<>();
        Class<?> currentClazz;
        if (!ignoreSelf) {
            currentClazz = clazz;
        } else {
            currentClazz = clazz.getSuperclass();
        }
        while (currentClazz != null) {
            classList.add(currentClazz);
            currentClazz = currentClazz.getSuperclass();
        }
        //noinspection ToArrayCallWithZeroLengthArrayArgument
        return classList.toArray(new Class<?>[classList.size()]);
    }

    /**
     * Get all the inheritance lists of the specified class
     */
    @NotNull
    public static Class<?>[] getClassHierarchy(@NotNull Class<?> clazz) {
        return getClassHierarchy(clazz, false);
    }


    /* ******************************************* Type *******************************************/


    /**
     * Determine if the given field is an array of the specified type
     */
    public static boolean isTypeArray(@NotNull Field field, @NotNull Class<?> type) {
        Class<?> fieldType = field.getType();
        return fieldType.isArray() && type.isAssignableFrom(fieldType.getComponentType());
    }

    /**
     * Determine if a given field is a collection of the specified type
     */
    public static boolean isTypeCollection(@NotNull Field field, @NotNull Class<? extends Collection<?>> collectionType, @NotNull Class<?> type) {
        Class<?> fieldType = field.getType();
        if (collectionType.isAssignableFrom(fieldType)) {
            Class<?> first = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            return type.isAssignableFrom(first);
        } else {
            return false;
        }
    }
}
