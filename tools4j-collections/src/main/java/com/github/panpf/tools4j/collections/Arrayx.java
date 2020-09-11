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

package com.github.panpf.tools4j.collections;

import com.github.panpf.tools4j.common.*;
import com.github.panpf.tools4j.iterable.*;
import com.github.panpf.tools4j.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;

/**
 * Array tool method
 */
public class Arrayx {

    private Arrayx() {
    }


    /* ******************************************* null or empty ******************************************* */


    /**
     * Returns `true` if the array is empty.
     */
    public static <T> boolean isNullOrEmpty(@Nullable T[] elements) {
        return elements == null || elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isNullOrEmpty(@Nullable char[] elements) {
        return elements == null || elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isNullOrEmpty(@Nullable byte[] elements) {
        return elements == null || elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isNullOrEmpty(@Nullable short[] elements) {
        return elements == null || elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isNullOrEmpty(@Nullable int[] elements) {
        return elements == null || elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isNullOrEmpty(@Nullable long[] elements) {
        return elements == null || elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isNullOrEmpty(@Nullable float[] elements) {
        return elements == null || elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isNullOrEmpty(@Nullable double[] elements) {
        return elements == null || elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isNullOrEmpty(@Nullable boolean[] elements) {
        return elements == null || elements.length == 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static <T> boolean isNotNullOrEmpty(@Nullable T[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotNullOrEmpty(@Nullable char[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotNullOrEmpty(@Nullable byte[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotNullOrEmpty(@Nullable short[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotNullOrEmpty(@Nullable int[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotNullOrEmpty(@Nullable long[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotNullOrEmpty(@Nullable float[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotNullOrEmpty(@Nullable double[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotNullOrEmpty(@Nullable boolean[] elements) {
        return elements != null && elements.length > 0;
    }


    /* ******************************************* joinToArrayString ******************************************* */


    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static <T> String joinToArrayString(@Nullable T[] elements, @Nullable Transformer<T, CharSequence> transform) {
        return joinToString(elements, null, "[", "]", -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static <T> String joinToArrayString(@Nullable T[] elements) {
        return joinToString(elements, null, "[", "]", -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable byte[] elements, @Nullable Transformer<Byte, CharSequence> transform) {
        return joinToString(elements, null, "[", "]", -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable byte[] elements) {
        return joinToString(elements, null, "[", "]", -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable short[] elements, @Nullable Transformer<Short, CharSequence> transform) {
        return joinToString(elements, null, "[", "]", -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable short[] elements) {
        return joinToString(elements, null, "[", "]", -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable int[] elements, @Nullable Transformer<Integer, CharSequence> transform) {
        return joinToString(elements, null, "[", "]", -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable int[] elements) {
        return joinToString(elements, null, "[", "]", -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable long[] elements, @Nullable Transformer<Long, CharSequence> transform) {
        return joinToString(elements, null, "[", "]", -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable long[] elements) {
        return joinToString(elements, null, "[", "]", -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable float[] elements, @Nullable Transformer<Float, CharSequence> transform) {
        return joinToString(elements, null, "[", "]", -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable float[] elements) {
        return joinToString(elements, null, "[", "]", -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable double[] elements, @Nullable Transformer<Double, CharSequence> transform) {
        return joinToString(elements, null, "[", "]", -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable double[] elements) {
        return joinToString(elements, null, "[", "]", -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable boolean[] elements, @Nullable Transformer<Boolean, CharSequence> transform) {
        return joinToString(elements, null, "[", "]", -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable boolean[] elements) {
        return joinToString(elements, null, "[", "]", -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable char[] elements, @Nullable Transformer<Character, CharSequence> transform) {
        return joinToString(elements, null, "[", "]", -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using ', ' and using the given '[' and ']' if supplied.
     */
    @NotNull
    public static String joinToArrayString(@Nullable char[] elements) {
        return joinToString(elements, null, "[", "]", -1, null, null);
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    /* ******************************************* array of ******************************************* */


    /**
     * Returns an array containing the specified elements.
     */
    @NotNull
    @SafeVarargs
    public static <T> T[] arrayOf(@NotNull T... elements) {
        return elements;
    }

    /**
     * Internal unsafe construction of array based on reference array type
     */
    public static <T> T[] arrayOfNulls(@NotNull T[] reference, int size) {
        //noinspection unchecked
        return (T[]) java.lang.reflect.Array.newInstance(reference.getClass().getComponentType(), size);
    }

    /**
     * Returns an array containing the specified [Double] numbers.
     */
    @NotNull
    public static double[] doubleArrayOf(double... elements) {
        return elements;
    }

    /**
     * Returns an array containing the specified [Float] numbers.
     */
    @NotNull
    public static float[] floatArrayOf(float... elements) {
        return elements;
    }

    /**
     * Returns an array containing the specified [Long] numbers.
     */
    @NotNull
    public static long[] longArrayOf(long... elements) {
        return elements;
    }

    /**
     * Returns an array containing the specified [Int] numbers.
     */
    @NotNull
    public static int[] intArrayOf(int... elements) {
        return elements;
    }

    /**
     * Returns an array containing the specified characters.
     */
    @NotNull
    public static char[] charArrayOf(char... elements) {
        return elements;
    }

    /**
     * Returns an array containing the specified [Short] numbers.
     */
    @NotNull
    public static short[] shortArrayOf(short... elements) {
        return elements;
    }

    /**
     * Returns an array containing the specified [Byte] numbers.
     */
    @NotNull
    public static byte[] byteArrayOf(byte... elements) {
        return elements;
    }

    /**
     * Returns an array containing the specified boolean values.
     */
    @NotNull
    public static boolean[] booleanArrayOf(boolean... elements) {
        return elements;
    }


    /* ******************************************* empty ******************************************* */


    /**
     * Returns `true` if the array is empty.
     */
    public static <T> boolean isEmpty(@Nullable T[] elements) {
        return elements != null && elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isEmpty(@Nullable char[] elements) {
        return elements != null && elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isEmpty(@Nullable byte[] elements) {
        return elements != null && elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isEmpty(@Nullable short[] elements) {
        return elements != null && elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isEmpty(@Nullable int[] elements) {
        return elements != null && elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isEmpty(@Nullable long[] elements) {
        return elements != null && elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isEmpty(@Nullable float[] elements) {
        return elements != null && elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isEmpty(@Nullable double[] elements) {
        return elements != null && elements.length == 0;
    }

    /**
     * Returns `true` if the array is empty.
     */
    public static boolean isEmpty(@Nullable boolean[] elements) {
        return elements != null && elements.length == 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static <T> boolean isNotEmpty(@Nullable T[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotEmpty(@Nullable char[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotEmpty(@Nullable byte[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotEmpty(@Nullable short[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotEmpty(@Nullable int[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotEmpty(@Nullable long[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotEmpty(@Nullable float[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotEmpty(@Nullable double[] elements) {
        return elements != null && elements.length > 0;
    }

    /**
     * Returns `true` if the array is not empty.
     */
    public static boolean isNotEmpty(@Nullable boolean[] elements) {
        return elements != null && elements.length > 0;
    }


    /* ******************************************* orEmpty ******************************************* */


    /**
     * Return itself if it is not null, otherwise return a newly created empty array
     */
    @NotNull
    public static byte[] orEmpty(@Nullable byte[] elements) {
        //noinspection unchecked
        return elements != null ? elements : new byte[0];
    }

    /**
     * Return itself if it is not null, otherwise return a newly created empty array
     */
    @NotNull
    public static short[] orEmpty(@Nullable short[] elements) {
        //noinspection unchecked
        return elements != null ? elements : new short[0];
    }

    /**
     * Return itself if it is not null, otherwise return a newly created empty array
     */
    @NotNull
    public static int[] orEmpty(@Nullable int[] elements) {
        //noinspection unchecked
        return elements != null ? elements : new int[0];
    }

    /**
     * Return itself if it is not null, otherwise return a newly created empty array
     */
    @NotNull
    public static long[] orEmpty(@Nullable long[] elements) {
        //noinspection unchecked
        return elements != null ? elements : new long[0];
    }

    /**
     * Return itself if it is not null, otherwise return a newly created empty array
     */
    @NotNull
    public static float[] orEmpty(@Nullable float[] elements) {
        //noinspection unchecked
        return elements != null ? elements : new float[0];
    }

    /**
     * Return itself if it is not null, otherwise return a newly created empty array
     */
    @NotNull
    public static double[] orEmpty(@Nullable double[] elements) {
        //noinspection unchecked
        return elements != null ? elements : new double[0];
    }

    /**
     * Return itself if it is not null, otherwise return a newly created empty array
     */
    @NotNull
    public static boolean[] orEmpty(@Nullable boolean[] elements) {
        //noinspection unchecked
        return elements != null ? elements : new boolean[0];
    }

    /**
     * Return itself if it is not null, otherwise return a newly created empty array
     */
    @NotNull
    public static char[] orEmpty(@Nullable char[] elements) {
        //noinspection unchecked
        return elements != null ? elements : new char[0];
    }


    /* ******************************************* toTypedArray ******************************************* */


    /**
     * Returns a *typed* object array containing all of the elements of this primitive array.
     */
    @NotNull
    public static Byte[] toTypedArray(@Nullable byte[] elements) {
        Byte[] result = new Byte[count(elements)];
        if (elements != null) {
            int index = 0;
            while (index++ < elements.length) {
                result[index] = elements[index];
            }
        }
        return result;
    }

    /**
     * Returns a *typed* object array containing all of the elements of this primitive array.
     */
    @NotNull
    public static Short[] toTypedArray(@Nullable short[] elements) {
        Short[] result = new Short[count(elements)];
        if (elements != null) {
            int index = 0;
            while (index++ < elements.length) {
                result[index] = elements[index];
            }
        }
        return result;
    }

    /**
     * Returns a *typed* object array containing all of the elements of this primitive array.
     */
    @NotNull
    public static Integer[] toTypedArray(@Nullable int[] elements) {
        Integer[] result = new Integer[count(elements)];
        if (elements != null) {
            int index = 0;
            while (index++ < elements.length) {
                result[index] = elements[index];
            }
        }
        return result;
    }

    /**
     * Returns a *typed* object array containing all of the elements of this primitive array.
     */
    @NotNull
    public static Long[] toTypedArray(@Nullable long[] elements) {
        Long[] result = new Long[count(elements)];
        if (elements != null) {
            int index = 0;
            while (index++ < elements.length) {
                result[index] = elements[index];
            }
        }
        return result;
    }

    /**
     * Returns a *typed* object array containing all of the elements of this primitive array.
     */
    @NotNull
    public static Float[] toTypedArray(@Nullable float[] elements) {
        Float[] result = new Float[count(elements)];
        if (elements != null) {
            int index = 0;
            while (index++ < elements.length) {
                result[index] = elements[index];
            }
        }
        return result;
    }

    /**
     * Returns a *typed* object array containing all of the elements of this primitive array.
     */
    @NotNull
    public static Double[] toTypedArray(@Nullable double[] elements) {
        Double[] result = new Double[count(elements)];
        if (elements != null) {
            int index = 0;
            while (index++ < elements.length) {
                result[index] = elements[index];
            }
        }
        return result;
    }

    /**
     * Returns a *typed* object array containing all of the elements of this primitive array.
     */
    @NotNull
    public static Boolean[] toTypedArray(@Nullable boolean[] elements) {
        Boolean[] result = new Boolean[count(elements)];
        if (elements != null) {
            int index = 0;
            while (index++ < elements.length) {
                result[index] = elements[index];
            }
        }
        return result;
    }

    /**
     * Returns a *typed* object array containing all of the elements of this primitive array.
     */
    @NotNull
    public static Character[] toTypedArray(@Nullable char[] elements) {
        Character[] result = new Character[count(elements)];
        if (elements != null) {
            int index = 0;
            while (index++ < elements.length) {
                result[index] = elements[index];
            }
        }
        return result;
    }


    /* ******************************************* toCollection ******************************************* */


    /**
     * Appends all elements to the given [destination] collection.
     */
    @NotNull
    public static <T, C extends Collection<T>> C toCollection(@Nullable T[] elements, @NotNull C destination) {
        if (elements != null) Collections.addAll(destination, elements);
        return destination;
    }

    /**
     * Appends all elements to the given [destination] collection.
     */
    @NotNull
    public static <C extends Collection<Byte>> C toCollection(@Nullable byte[] elements, @NotNull C destination) {
        if (elements != null) for (byte element : elements) destination.add(element);
        return destination;
    }

    /**
     * Appends all elements to the given [destination] collection.
     */
    @NotNull
    public static <C extends Collection<Short>> C toCollection(@Nullable short[] elements, @NotNull C destination) {
        if (elements != null) for (short element : elements) destination.add(element);
        return destination;
    }

    /**
     * Appends all elements to the given [destination] collection.
     */
    @NotNull
    public static <C extends Collection<Integer>> C toCollection(@Nullable int[] elements, @NotNull C destination) {
        if (elements != null) for (int element : elements) destination.add(element);
        return destination;
    }

    /**
     * Appends all elements to the given [destination] collection.
     */
    @NotNull
    public static <C extends Collection<Long>> C toCollection(@Nullable long[] elements, @NotNull C destination) {
        if (elements != null) for (long element : elements) destination.add(element);
        return destination;
    }

    /**
     * Appends all elements to the given [destination] collection.
     */
    @NotNull
    public static <C extends Collection<Float>> C toCollection(@Nullable float[] elements, @NotNull C destination) {
        if (elements != null) for (float element : elements) destination.add(element);
        return destination;
    }

    /**
     * Appends all elements to the given [destination] collection.
     */
    @NotNull
    public static <C extends Collection<Double>> C toCollection(@Nullable double[] elements, @NotNull C destination) {
        if (elements != null) for (double element : elements) destination.add(element);
        return destination;
    }

    /**
     * Appends all elements to the given [destination] collection.
     */
    @NotNull
    public static <C extends Collection<Boolean>> C toCollection(@Nullable boolean[] elements, @NotNull C destination) {
        if (elements != null) for (boolean element : elements) destination.add(element);
        return destination;
    }

    /**
     * Appends all elements to the given [destination] collection.
     */
    @NotNull
    public static <C extends Collection<Character>> C toCollection(@Nullable char[] elements, @NotNull C destination) {
        if (elements != null) for (char element : elements) destination.add(element);
        return destination;
    }


    /* ******************************************* toList ******************************************* */


    /**
     * Returns a [List] all elements.
     */
    @NotNull
    public static <T> List<T> toList(@Nullable T[] elements) {
        return toCollection(elements, new ArrayList<T>(count(elements)));
    }

    /**
     * Returns a [List] all elements.
     */
    @NotNull
    public static List<Byte> toList(@Nullable byte[] elements) {
        return toCollection(elements, new ArrayList<Byte>(count(elements)));
    }

    /**
     * Returns a [List] all elements.
     */
    @NotNull
    public static List<Character> toList(@Nullable char[] elements) {
        return toCollection(elements, new ArrayList<Character>(count(elements)));
    }

    /**
     * Returns a [List] all elements.
     */
    @NotNull
    public static List<Short> toList(@Nullable short[] elements) {
        return toCollection(elements, new ArrayList<Short>(count(elements)));
    }

    /**
     * Returns a [List] all elements.
     */
    @NotNull
    public static List<Integer> toList(@Nullable int[] elements) {
        return toCollection(elements, new ArrayList<Integer>(count(elements)));
    }

    /**
     * Returns a [List] all elements.
     */
    @NotNull
    public static List<Long> toList(@Nullable long[] elements) {
        return toCollection(elements, new ArrayList<Long>(count(elements)));
    }

    /**
     * Returns a [List] all elements.
     */
    @NotNull
    public static List<Float> toList(@Nullable float[] elements) {
        return toCollection(elements, new ArrayList<Float>(count(elements)));
    }

    /**
     * Returns a [List] all elements.
     */
    @NotNull
    public static List<Double> toList(@Nullable double[] elements) {
        return toCollection(elements, new ArrayList<Double>(count(elements)));
    }

    /**
     * Returns a [List] all elements.
     */
    @NotNull
    public static List<Boolean> toList(@Nullable boolean[] elements) {
        return toCollection(elements, new ArrayList<Boolean>(count(elements)));
    }


    /* ******************************************* toSet ******************************************* */


    /**
     * Returns a mutable set containing all distinct elements from the given array.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static <T> Set<T> toSet(@Nullable T[] elements) {
        return toCollection(elements, new LinkedHashSet<T>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a mutable set containing all distinct elements from the given array.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Byte> toSet(@Nullable byte[] elements) {
        return toCollection(elements, new LinkedHashSet<Byte>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a mutable set containing all distinct elements from the given array.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Short> toSet(@Nullable short[] elements) {
        return toCollection(elements, new LinkedHashSet<Short>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a mutable set containing all distinct elements from the given array.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Integer> toSet(@Nullable int[] elements) {
        return toCollection(elements, new LinkedHashSet<Integer>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a mutable set containing all distinct elements from the given array.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Long> toSet(@Nullable long[] elements) {
        return toCollection(elements, new LinkedHashSet<Long>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a mutable set containing all distinct elements from the given array.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Float> toSet(@Nullable float[] elements) {
        return toCollection(elements, new LinkedHashSet<Float>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a mutable set containing all distinct elements from the given array.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Double> toSet(@Nullable double[] elements) {
        return toCollection(elements, new LinkedHashSet<Double>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a mutable set containing all distinct elements from the given array.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Boolean> toSet(@Nullable boolean[] elements) {
        return toCollection(elements, new LinkedHashSet<Boolean>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a mutable set containing all distinct elements from the given array.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Character> toSet(@Nullable char[] elements) {
        return toCollection(elements, new LinkedHashSet<Character>(Mapx.capacity(count(elements))));
    }


    /* ******************************************* toHashSet ******************************************* */


    /**
     * Returns a [HashSet] of all elements.
     */
    @NotNull
    public static <T> HashSet<T> toHashSet(@Nullable T[] elements) {
        return toCollection(elements, new HashSet<T>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a [HashSet] of all elements.
     */
    @NotNull
    public static HashSet<Byte> toHashSet(@Nullable byte[] elements) {
        return toCollection(elements, new HashSet<Byte>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a [HashSet] of all elements.
     */
    @NotNull
    public static HashSet<Short> toHashSet(@Nullable short[] elements) {
        return toCollection(elements, new HashSet<Short>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a [HashSet] of all elements.
     */
    @NotNull
    public static HashSet<Integer> toHashSet(@Nullable int[] elements) {
        return toCollection(elements, new HashSet<Integer>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a [HashSet] of all elements.
     */
    @NotNull
    public static HashSet<Long> toHashSet(@Nullable long[] elements) {
        return toCollection(elements, new HashSet<Long>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a [HashSet] of all elements.
     */
    @NotNull
    public static HashSet<Float> toHashSet(@Nullable float[] elements) {
        return toCollection(elements, new HashSet<Float>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a [HashSet] of all elements.
     */
    @NotNull
    public static HashSet<Double> toHashSet(@Nullable double[] elements) {
        return toCollection(elements, new HashSet<Double>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a [HashSet] of all elements.
     */
    @NotNull
    public static HashSet<Boolean> toHashSet(@Nullable boolean[] elements) {
        return toCollection(elements, new HashSet<Boolean>(Mapx.capacity(count(elements))));
    }

    /**
     * Returns a [HashSet] of all elements.
     */
    @NotNull
    public static HashSet<Character> toHashSet(@Nullable char[] elements) {
        return toCollection(elements, new HashSet<Character>(Mapx.capacity(count(elements))));
    }


    /* ******************************************* asList ******************************************* */


    /**
     * Returns a [List] that wraps the original array.
     */
    @NotNull
    public static <T> List<T> asList(@Nullable final T[] elements) {
        return new AbstractList<T>() {

            @Override
            public int size() {
                return count(elements);
            }

            @Override
            public T get(int index) {
                if (elements == null) throw new IllegalStateException("This method cannot be called when size is 0");
                return elements[index];
            }
        };
    }

    /**
     * Returns a [List] that wraps the original array.
     */
    @NotNull
    public static List<Byte> asList(@Nullable final byte[] elements) {
        return new AbstractList<Byte>() {

            @Override
            public int size() {
                return count(elements);
            }

            @Override
            public Byte get(int index) {
                if (elements == null) throw new IllegalStateException("This method cannot be called when size is 0");
                return elements[index];
            }
        };
    }

    /**
     * Returns a [List] that wraps the original array.
     */
    @NotNull
    public static List<Short> asList(@Nullable final short[] elements) {
        return new AbstractList<Short>() {

            @Override
            public int size() {
                return count(elements);
            }

            @Override
            public Short get(int index) {
                if (elements == null) throw new IllegalStateException("This method cannot be called when size is 0");
                return elements[index];
            }
        };
    }

    /**
     * Returns a [List] that wraps the original array.
     */
    @NotNull
    public static List<Integer> asList(@Nullable final int[] elements) {
        return new AbstractList<Integer>() {

            @Override
            public int size() {
                return count(elements);
            }

            @Override
            public Integer get(int index) {
                if (elements == null) throw new IllegalStateException("This method cannot be called when size is 0");
                return elements[index];
            }
        };
    }

    /**
     * Returns a [List] that wraps the original array.
     */
    @NotNull
    public static List<Long> asList(@Nullable final long[] elements) {
        return new AbstractList<Long>() {

            @Override
            public int size() {
                return count(elements);
            }

            @Override
            public Long get(int index) {
                if (elements == null) throw new IllegalStateException("This method cannot be called when size is 0");
                return elements[index];
            }
        };
    }

    /**
     * Returns a [List] that wraps the original array.
     */
    @NotNull
    public static List<Float> asList(@Nullable final float[] elements) {
        return new AbstractList<Float>() {

            @Override
            public int size() {
                return count(elements);
            }

            @Override
            public Float get(int index) {
                if (elements == null) throw new IllegalStateException("This method cannot be called when size is 0");
                return elements[index];
            }
        };
    }

    /**
     * Returns a [List] that wraps the original array.
     */
    @NotNull
    public static List<Double> asList(@Nullable final double[] elements) {
        return new AbstractList<Double>() {

            @Override
            public int size() {
                return count(elements);
            }

            @Override
            public Double get(int index) {
                if (elements == null) throw new IllegalStateException("This method cannot be called when size is 0");
                return elements[index];
            }
        };
    }

    /**
     * Returns a [List] that wraps the original array.
     */
    @NotNull
    public static List<Boolean> asList(@Nullable final boolean[] elements) {
        return new AbstractList<Boolean>() {

            @Override
            public int size() {
                return count(elements);
            }

            @Override
            public Boolean get(int index) {
                if (elements == null) throw new IllegalStateException("This method cannot be called when size is 0");
                return elements[index];
            }
        };
    }

    /**
     * Returns a [List] that wraps the original array.
     */
    @NotNull
    public static List<Character> asList(@Nullable final char[] elements) {
        return new AbstractList<Character>() {

            @Override
            public int size() {
                return count(elements);
            }

            @Override
            public Character get(int index) {
                if (elements == null) throw new IllegalStateException("This method cannot be called when size is 0");
                return elements[index];
            }
        };
    }


    /* ******************************************* map ******************************************* */


    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element in the original array.
     */
    @NotNull
    public static <T, R> List<R> map(@Nullable T[] elements, @NotNull Transformer<T, R> transform) {
        return mapTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element in the original array.
     */
    @NotNull
    public static <R> List<R> map(@Nullable byte[] elements, @NotNull Transformer<Byte, R> transform) {
        return mapTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element in the original array.
     */
    @NotNull
    public static <R> List<R> map(@Nullable short[] elements, @NotNull Transformer<Short, R> transform) {
        return mapTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element in the original array.
     */
    @NotNull
    public static <R> List<R> map(@Nullable int[] elements, @NotNull Transformer<Integer, R> transform) {
        return mapTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element in the original array.
     */
    @NotNull
    public static <R> List<R> map(@Nullable long[] elements, @NotNull Transformer<Long, R> transform) {
        return mapTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element in the original array.
     */
    @NotNull
    public static <R> List<R> map(@Nullable float[] elements, @NotNull Transformer<Float, R> transform) {
        return mapTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element in the original array.
     */
    @NotNull
    public static <R> List<R> map(@Nullable double[] elements, @NotNull Transformer<Double, R> transform) {
        return mapTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element in the original array.
     */
    @NotNull
    public static <R> List<R> map(@Nullable boolean[] elements, @NotNull Transformer<Boolean, R> transform) {
        return mapTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element in the original array.
     */
    @NotNull
    public static <R> List<R> map(@Nullable char[] elements, @NotNull Transformer<Character, R> transform) {
        return mapTo(elements, new ArrayList<R>(count(elements)), transform);
    }


    /**
     * Applies the given [transform] function to each element of the original array
     * and appends the results to the given [destination].
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C mapTo(@Nullable T[] elements, @NotNull C destination, @NotNull Transformer<T, R> transform) {
        if (elements != null) for (T item : elements) destination.add(transform.transform(item));
        return destination;
    }

    /**
     * Applies the given [transform] function to each element of the original array
     * and appends the results to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapTo(@Nullable byte[] elements, @NotNull C destination, @NotNull Transformer<Byte, R> transform) {
        if (elements != null) for (byte item : elements) destination.add(transform.transform(item));
        return destination;
    }

    /**
     * Applies the given [transform] function to each element of the original array
     * and appends the results to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapTo(@Nullable short[] elements, @NotNull C destination, @NotNull Transformer<Short, R> transform) {
        if (elements != null) for (short item : elements) destination.add(transform.transform(item));
        return destination;
    }

    /**
     * Applies the given [transform] function to each element of the original array
     * and appends the results to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapTo(@Nullable int[] elements, @NotNull C destination, @NotNull Transformer<Integer, R> transform) {
        if (elements != null) for (int item : elements) destination.add(transform.transform(item));
        return destination;
    }

    /**
     * Applies the given [transform] function to each element of the original array
     * and appends the results to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapTo(@Nullable long[] elements, @NotNull C destination, @NotNull Transformer<Long, R> transform) {
        if (elements != null) for (long item : elements) destination.add(transform.transform(item));
        return destination;
    }

    /**
     * Applies the given [transform] function to each element of the original array
     * and appends the results to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapTo(@Nullable float[] elements, @NotNull C destination, @NotNull Transformer<Float, R> transform) {
        if (elements != null) for (float item : elements) destination.add(transform.transform(item));
        return destination;
    }

    /**
     * Applies the given [transform] function to each element of the original array
     * and appends the results to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapTo(@Nullable double[] elements, @NotNull C destination, @NotNull Transformer<Double, R> transform) {
        if (elements != null) for (double item : elements) destination.add(transform.transform(item));
        return destination;
    }

    /**
     * Applies the given [transform] function to each element of the original array
     * and appends the results to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapTo(@Nullable boolean[] elements, @NotNull C destination, @NotNull Transformer<Boolean, R> transform) {
        if (elements != null) for (boolean item : elements) destination.add(transform.transform(item));
        return destination;
    }

    /**
     * Applies the given [transform] function to each element of the original array
     * and appends the results to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapTo(@Nullable char[] elements, @NotNull C destination, @NotNull Transformer<Character, R> transform) {
        if (elements != null) for (char item : elements) destination.add(transform.transform(item));
        return destination;
    }


    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element and its index in the original array.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <T, R> List<R> mapIndexed(@Nullable T[] elements, @NotNull IndexedTransformer<T, R> transform) {
        return mapIndexedTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element and its index in the original array.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R> List<R> mapIndexed(@Nullable byte[] elements, @NotNull IndexedTransformer<Byte, R> transform) {
        return mapIndexedTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element and its index in the original array.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R> List<R> mapIndexed(@Nullable short[] elements, @NotNull IndexedTransformer<Short, R> transform) {
        return mapIndexedTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element and its index in the original array.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R> List<R> mapIndexed(@Nullable int[] elements, @NotNull IndexedTransformer<Integer, R> transform) {
        return mapIndexedTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element and its index in the original array.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R> List<R> mapIndexed(@Nullable long[] elements, @NotNull IndexedTransformer<Long, R> transform) {
        return mapIndexedTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element and its index in the original array.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R> List<R> mapIndexed(@Nullable float[] elements, @NotNull IndexedTransformer<Float, R> transform) {
        return mapIndexedTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element and its index in the original array.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R> List<R> mapIndexed(@Nullable double[] elements, @NotNull IndexedTransformer<Double, R> transform) {
        return mapIndexedTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element and its index in the original array.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R> List<R> mapIndexed(@Nullable boolean[] elements, @NotNull IndexedTransformer<Boolean, R> transform) {
        return mapIndexedTo(elements, new ArrayList<R>(count(elements)), transform);
    }

    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element and its index in the original array.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R> List<R> mapIndexed(@Nullable char[] elements, @NotNull IndexedTransformer<Character, R> transform) {
        return mapIndexedTo(elements, new ArrayList<R>(count(elements)), transform);
    }


    /**
     * Applies the given [transform] function to each element and its index in the original array
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C mapIndexedTo(@Nullable T[] elements, @NotNull C destination, @NotNull IndexedTransformer<T, R> transform) {
        if (elements != null) {
            int index = 0;
            for (T item : elements) destination.add(transform.transform(index++, item));
        }
        return destination;
    }

    /**
     * Applies the given [transform] function to each element and its index in the original array
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapIndexedTo(@Nullable byte[] elements, @NotNull C destination, @NotNull IndexedTransformer<Byte, R> transform) {
        if (elements != null) {
            int index = 0;
            for (byte item : elements) {
                destination.add(transform.transform(index++, item));
            }
        }
        return destination;
    }

    /**
     * Applies the given [transform] function to each element and its index in the original array
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapIndexedTo(@Nullable short[] elements, @NotNull C destination, @NotNull IndexedTransformer<Short, R> transform) {
        if (elements != null) {
            int index = 0;
            for (short item : elements) {
                destination.add(transform.transform(index++, item));
            }
        }
        return destination;
    }

    /**
     * Applies the given [transform] function to each element and its index in the original array
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapIndexedTo(@Nullable int[] elements, @NotNull C destination, @NotNull IndexedTransformer<Integer, R> transform) {
        if (elements != null) {
            int index = 0;
            for (int item : elements) {
                destination.add(transform.transform(index++, item));
            }
        }
        return destination;
    }

    /**
     * Applies the given [transform] function to each element and its index in the original array
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapIndexedTo(@Nullable long[] elements, @NotNull C destination, @NotNull IndexedTransformer<Long, R> transform) {
        if (elements != null) {
            int index = 0;
            for (long item : elements) {
                destination.add(transform.transform(index++, item));
            }
        }
        return destination;
    }

    /**
     * Applies the given [transform] function to each element and its index in the original array
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapIndexedTo(@Nullable float[] elements, @NotNull C destination, @NotNull IndexedTransformer<Float, R> transform) {
        if (elements != null) {
            int index = 0;
            for (float item : elements) {
                destination.add(transform.transform(index++, item));
            }
        }
        return destination;
    }

    /**
     * Applies the given [transform] function to each element and its index in the original array
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapIndexedTo(@Nullable double[] elements, @NotNull C destination, @NotNull IndexedTransformer<Double, R> transform) {
        if (elements != null) {
            int index = 0;
            for (double item : elements) {
                destination.add(transform.transform(index++, item));
            }
        }
        return destination;
    }

    /**
     * Applies the given [transform] function to each element and its index in the original array
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapIndexedTo(@Nullable boolean[] elements, @NotNull C destination, @NotNull IndexedTransformer<Boolean, R> transform) {
        if (elements != null) {
            int index = 0;
            for (boolean item : elements) {
                destination.add(transform.transform(index++, item));
            }
        }
        return destination;
    }

    /**
     * Applies the given [transform] function to each element and its index in the original array
     * and appends the results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <R, C extends Collection<R>> C mapIndexedTo(@Nullable char[] elements, @NotNull C destination, @NotNull IndexedTransformer<Character, R> transform) {
        if (elements != null) {
            int index = 0;
            for (char item : elements) {
                destination.add(transform.transform(index++, item));
            }
        }
        return destination;
    }


    /**
     * Returns a list containing only the non-null results of applying the given [transform] function
     * to each element in the original array.
     */
    @NotNull
    public static <T, R> List<R> mapNotNull(@Nullable T[] elements, @NotNull NullableTransformer<T, R> transform) {
        return mapNotNullTo(elements, new ArrayList<R>(), transform);
    }

    /**
     * Applies the given [transform] function to each element in the original array
     * and appends only the non-null results to the given [destination].
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C mapNotNullTo(@Nullable T[] elements, @NotNull C destination, @NotNull NullableTransformer<T, R> transform) {
        if (elements != null) {
            for (T item : elements) {
                R r = transform.transform(item);
                if (r != null) {
                    destination.add(r);
                }
            }
        }
        return destination;
    }

    /**
     * Returns a list containing only the non-null results of applying the given [transform] function
     * to each element and its index in the original array.
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <T, R> List<R> mapIndexedNotNull(@Nullable T[] elements, @NotNull NullableIndexedTransformer<T, R> transform) {
        return mapIndexedNotNullTo(elements, new ArrayList<R>(), transform);
    }

    /**
     * Applies the given [transform] function to each element and its index in the original array
     * and appends only the non-null results to the given [destination].
     *
     * @param transform function that takes the index of an element and the element itself
     *                  and returns the result of the transform applied to the element.
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C mapIndexedNotNullTo(@Nullable T[] elements, @NotNull C destination, @NotNull NullableIndexedTransformer<T, R> transform) {
        if (elements != null) {
            int index = 0;
            for (T item : elements) {
                R r = transform.transform(index++, item);
                if (r != null) {
                    destination.add(r);
                }
            }
        }
        return destination;
    }


    /* ******************************************* reverse ******************************************* */


    /**
     * Reverses elements in the array in-place.
     */
    public static <T> void reverse(@Nullable T[] elements) {
        if (elements == null || elements.length == 0) return;
        int midPoint = (elements.length / 2) - 1;
        if (midPoint < 0) return;
        int reverseIndex = elements.length - 1;
        for (int index = 0; index <= midPoint; index++) {
            T tmp = elements[index];
            elements[index] = elements[reverseIndex];
            elements[reverseIndex] = tmp;
            reverseIndex--;
        }
    }

    /**
     * Reverses elements in the array in-place.
     */
    public static void reverse(@Nullable byte[] elements) {
        if (elements == null || elements.length == 0) return;
        int midPoint = (elements.length / 2) - 1;
        if (midPoint < 0) return;
        int reverseIndex = elements.length - 1;
        for (int index = 0; index <= midPoint; index++) {
            byte tmp = elements[index];
            elements[index] = elements[reverseIndex];
            elements[reverseIndex] = tmp;
            reverseIndex--;
        }
    }

    /**
     * Reverses elements in the array in-place.
     */
    public static void reverse(@Nullable short[] elements) {
        if (elements == null || elements.length == 0) return;
        int midPoint = (elements.length / 2) - 1;
        if (midPoint < 0) return;
        int reverseIndex = elements.length - 1;
        for (int index = 0; index <= midPoint; index++) {
            short tmp = elements[index];
            elements[index] = elements[reverseIndex];
            elements[reverseIndex] = tmp;
            reverseIndex--;
        }
    }

    /**
     * Reverses elements in the array in-place.
     */
    public static void reverse(@Nullable int[] elements) {
        if (elements == null || elements.length == 0) return;
        int midPoint = (elements.length / 2) - 1;
        if (midPoint < 0) return;
        int reverseIndex = elements.length - 1;
        for (int index = 0; index <= midPoint; index++) {
            int tmp = elements[index];
            elements[index] = elements[reverseIndex];
            elements[reverseIndex] = tmp;
            reverseIndex--;
        }
    }

    /**
     * Reverses elements in the array in-place.
     */
    public static void reverse(@Nullable long[] elements) {
        if (elements == null || elements.length == 0) return;
        int midPoint = (elements.length / 2) - 1;
        if (midPoint < 0) return;
        int reverseIndex = elements.length - 1;
        for (int index = 0; index <= midPoint; index++) {
            long tmp = elements[index];
            elements[index] = elements[reverseIndex];
            elements[reverseIndex] = tmp;
            reverseIndex--;
        }
    }

    /**
     * Reverses elements in the array in-place.
     */
    public static void reverse(@Nullable float[] elements) {
        if (elements == null || elements.length == 0) return;
        int midPoint = (elements.length / 2) - 1;
        if (midPoint < 0) return;
        int reverseIndex = elements.length - 1;
        for (int index = 0; index <= midPoint; index++) {
            float tmp = elements[index];
            elements[index] = elements[reverseIndex];
            elements[reverseIndex] = tmp;
            reverseIndex--;
        }
    }

    /**
     * Reverses elements in the array in-place.
     */
    public static void reverse(@Nullable double[] elements) {
        if (elements == null || elements.length == 0) return;
        int midPoint = (elements.length / 2) - 1;
        if (midPoint < 0) return;
        int reverseIndex = elements.length - 1;
        for (int index = 0; index <= midPoint; index++) {
            double tmp = elements[index];
            elements[index] = elements[reverseIndex];
            elements[reverseIndex] = tmp;
            reverseIndex--;
        }
    }

    /**
     * Reverses elements in the array in-place.
     */
    public static void reverse(@Nullable boolean[] elements) {
        if (elements == null || elements.length == 0) return;
        int midPoint = (elements.length / 2) - 1;
        if (midPoint < 0) return;
        int reverseIndex = elements.length - 1;
        for (int index = 0; index <= midPoint; index++) {
            boolean tmp = elements[index];
            elements[index] = elements[reverseIndex];
            elements[reverseIndex] = tmp;
            reverseIndex--;
        }
    }

    /**
     * Reverses elements in the array in-place.
     */
    public static void reverse(@Nullable char[] elements) {
        if (elements == null || elements.length == 0) return;
        int midPoint = (elements.length / 2) - 1;
        if (midPoint < 0) return;
        int reverseIndex = elements.length - 1;
        for (int index = 0; index <= midPoint; index++) {
            char tmp = elements[index];
            elements[index] = elements[reverseIndex];
            elements[reverseIndex] = tmp;
            reverseIndex--;
        }
    }

    /**
     * Returns a list with elements in reversed order.
     */
    @NotNull
    public static <T> List<T> reversed(@Nullable T[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        List<T> list = Arrayx.toList(elements);
        Collectionx.reverse(list);
        return list;
    }

    /**
     * Returns a list with elements in reversed order.
     */
    @NotNull
    public static List<Byte> reversed(@Nullable byte[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        List<Byte> list = Arrayx.toList(elements);
        Collectionx.reverse(list);
        return list;
    }

    /**
     * Returns a list with elements in reversed order.
     */
    @NotNull
    public static List<Short> reversed(@Nullable short[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        List<Short> list = Arrayx.toList(elements);
        Collectionx.reverse(list);
        return list;
    }

    /**
     * Returns a list with elements in reversed order.
     */
    @NotNull
    public static List<Integer> reversed(@Nullable int[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        List<Integer> list = Arrayx.toList(elements);
        Collectionx.reverse(list);
        return list;
    }

    /**
     * Returns a list with elements in reversed order.
     */
    @NotNull
    public static List<Long> reversed(@Nullable long[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        List<Long> list = Arrayx.toList(elements);
        Collectionx.reverse(list);
        return list;
    }

    /**
     * Returns a list with elements in reversed order.
     */
    @NotNull
    public static List<Float> reversed(@Nullable float[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        List<Float> list = Arrayx.toList(elements);
        Collectionx.reverse(list);
        return list;
    }

    /**
     * Returns a list with elements in reversed order.
     */
    @NotNull
    public static List<Double> reversed(@Nullable double[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        List<Double> list = Arrayx.toList(elements);
        Collectionx.reverse(list);
        return list;
    }

    /**
     * Returns a list with elements in reversed order.
     */
    @NotNull
    public static List<Boolean> reversed(@Nullable boolean[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        List<Boolean> list = Arrayx.toList(elements);
        Collectionx.reverse(list);
        return list;
    }

    /**
     * Returns a list with elements in reversed order.
     */
    @NotNull
    public static List<Character> reversed(@Nullable char[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        List<Character> list = Arrayx.toList(elements);
        Collectionx.reverse(list);
        return list;
    }

    /**
     * Returns an array with elements of this array in reversed order.
     */
    @NotNull
    public static <T> T[] reversedArray(@NotNull T[] elements) {
        if (isEmpty(elements)) return elements;
        T[] result = arrayOfNulls(elements, elements.length);
        int lastIndex = elements.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            result[lastIndex - i] = elements[i];
        }
        return result;
    }

    /**
     * Returns an array with elements of this array in reversed order.
     */
    @NotNull
    public static byte[] reversedArray(@Nullable byte[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return new byte[0];
        byte[] result = new byte[elements.length];
        int lastIndex = elements.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            result[lastIndex - i] = elements[i];
        }
        return result;
    }

    /**
     * Returns an array with elements of this array in reversed order.
     */
    @NotNull
    public static short[] reversedArray(@Nullable short[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return new short[0];
        short[] result = new short[elements.length];
        int lastIndex = elements.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            result[lastIndex - i] = elements[i];
        }
        return result;
    }

    /**
     * Returns an array with elements of this array in reversed order.
     */
    @NotNull
    public static int[] reversedArray(@Nullable int[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return new int[0];
        int[] result = new int[elements.length];
        int lastIndex = elements.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            result[lastIndex - i] = elements[i];
        }
        return result;
    }

    /**
     * Returns an array with elements of this array in reversed order.
     */
    @NotNull
    public static long[] reversedArray(@Nullable long[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return new long[0];
        long[] result = new long[elements.length];
        int lastIndex = elements.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            result[lastIndex - i] = elements[i];
        }
        return result;
    }

    /**
     * Returns an array with elements of this array in reversed order.
     */
    @NotNull
    public static float[] reversedArray(@Nullable float[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return new float[0];
        float[] result = new float[elements.length];
        int lastIndex = elements.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            result[lastIndex - i] = elements[i];
        }
        return result;
    }

    /**
     * Returns an array with elements of this array in reversed order.
     */
    @NotNull
    public static double[] reversedArray(@Nullable double[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return new double[0];
        double[] result = new double[elements.length];
        int lastIndex = elements.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            result[lastIndex - i] = elements[i];
        }
        return result;
    }

    /**
     * Returns an array with elements of this array in reversed order.
     */
    @NotNull
    public static boolean[] reversedArray(@Nullable boolean[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return new boolean[0];
        boolean[] result = new boolean[elements.length];
        int lastIndex = elements.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            result[lastIndex - i] = elements[i];
        }
        return result;
    }

    /**
     * Returns an array with elements of this array in reversed order.
     */
    @NotNull
    public static char[] reversedArray(@Nullable char[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return new char[0];
        char[] result = new char[elements.length];
        int lastIndex = elements.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            result[lastIndex - i] = elements[i];
        }
        return result;
    }


    /* ******************************************* sort ******************************************* */


    /**
     * Sorts the array in-place according to the natural order of its elements.
     */
    public static <T extends Comparable<T>> void sort(@Nullable T[] elements) {
        sortWith(elements, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
    }

    /**
     * Sorts the array in-place according to the natural order of its elements.
     *
     * @throws ClassCastException if any element of the array is not [Comparable].
     */
    public static <T> void sort(@Nullable T[] elements) {
        if (elements != null && elements.length > 1) Arrays.sort(elements);
    }

    /**
     * Sorts the array in-place.
     */
    public static void sort(@Nullable int[] elements) {
        if (elements != null && elements.length > 1) Arrays.sort(elements);
    }

    /**
     * Sorts the array in-place.
     */
    public static void sort(@Nullable long[] elements) {
        if (elements != null && elements.length > 1) Arrays.sort(elements);
    }

    /**
     * Sorts the array in-place.
     */
    public static void sort(@Nullable byte[] elements) {
        if (elements != null && elements.length > 1) Arrays.sort(elements);
    }

    /**
     * Sorts the array in-place.
     */
    public static void sort(@Nullable short[] elements) {
        if (elements != null && elements.length > 1) Arrays.sort(elements);
    }

    /**
     * Sorts the array in-place.
     */
    public static void sort(@Nullable double[] elements) {
        if (elements != null && elements.length > 1) Arrays.sort(elements);
    }

    /**
     * Sorts the array in-place.
     */
    public static void sort(@Nullable float[] elements) {
        if (elements != null && elements.length > 1) Arrays.sort(elements);
    }

    /**
     * Sorts the array in-place.
     */
    public static void sort(@Nullable char[] elements) {
        if (elements != null && elements.length > 1) Arrays.sort(elements);
    }

    /**
     * Sorts a range in the array in-place.
     */
    public static <T> void sort(@Nullable T[] elements, int fromIndex, int toIndex) {
        if (elements != null) Arrays.sort(elements, fromIndex, toIndex);
    }

    /**
     * Sorts a range in the array in-place.
     */
    public static void sort(@Nullable byte[] elements, int fromIndex, int toIndex) {
        if (elements != null) Arrays.sort(elements, fromIndex, toIndex);
    }

    /**
     * Sorts a range in the array in-place.
     */
    public static void sort(@Nullable short[] elements, int fromIndex, int toIndex) {
        if (elements != null) Arrays.sort(elements, fromIndex, toIndex);
    }

    /**
     * Sorts a range in the array in-place.
     */
    public static void sort(@Nullable int[] elements, int fromIndex, int toIndex) {
        if (elements != null) Arrays.sort(elements, fromIndex, toIndex);
    }

    /**
     * Sorts a range in the array in-place.
     */
    public static void sort(@Nullable long[] elements, int fromIndex, int toIndex) {
        if (elements != null) Arrays.sort(elements, fromIndex, toIndex);
    }

    /**
     * Sorts a range in the array in-place.
     */
    public static void sort(@Nullable float[] elements, int fromIndex, int toIndex) {
        if (elements != null) Arrays.sort(elements, fromIndex, toIndex);
    }

    /**
     * Sorts a range in the array in-place.
     */
    public static void sort(@Nullable double[] elements, int fromIndex, int toIndex) {
        if (elements != null) Arrays.sort(elements, fromIndex, toIndex);
    }

    /**
     * Sorts a range in the array in-place.
     */
    public static void sort(@Nullable char[] elements, int fromIndex, int toIndex) {
        if (elements != null) Arrays.sort(elements, fromIndex, toIndex);
    }


    /**
     * Sorts elements in the array in-place descending according to their natural sort order.
     */
    public static <T extends Comparable<T>> void sortDescending(@Nullable T[] elements) {
        sortWith(elements, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o2.compareTo(o1);
            }
        });
    }

    /**
     * Sorts elements in the array in-place descending according to their natural sort order.
     */
    public static void sortDescending(@Nullable byte[] elements) {
        if (elements != null && elements.length > 1) {
            sort(elements);
            reverse(elements);
        }
    }

    /**
     * Sorts elements in the array in-place descending according to their natural sort order.
     */
    public static void sortDescending(@Nullable short[] elements) {
        if (elements != null && elements.length > 1) {
            sort(elements);
            reverse(elements);
        }
    }

    /**
     * Sorts elements in the array in-place descending according to their natural sort order.
     */
    public static void sortDescending(@Nullable int[] elements) {
        if (elements != null && elements.length > 1) {
            sort(elements);
            reverse(elements);
        }
    }

    /**
     * Sorts elements in the array in-place descending according to their natural sort order.
     */
    public static void sortDescending(@Nullable long[] elements) {
        if (elements != null && elements.length > 1) {
            sort(elements);
            reverse(elements);
        }
    }

    /**
     * Sorts elements in the array in-place descending according to their natural sort order.
     */
    public static void sortDescending(@Nullable float[] elements) {
        if (elements != null && elements.length > 1) {
            sort(elements);
            reverse(elements);
        }
    }

    /**
     * Sorts elements in the array in-place descending according to their natural sort order.
     */
    public static void sortDescending(@Nullable double[] elements) {
        if (elements != null && elements.length > 1) {
            sort(elements);
            reverse(elements);
        }
    }

    /**
     * Sorts elements in the array in-place descending according to their natural sort order.
     */
    public static void sortDescending(@Nullable char[] elements) {
        if (elements != null && elements.length > 1) {
            sort(elements);
            reverse(elements);
        }
    }


    /**
     * Sorts the array in-place according to the order specified by the given [comparator].
     */
    public static <T> void sortWith(@Nullable T[] elements, @NotNull Comparator<T> comparator) {
        if (elements != null && elements.length > 1) Arrays.sort(elements, comparator);
    }

    /**
     * Sorts a range in the array in-place with the given [comparator].
     */
    public static <T> void sortWith(@Nullable T[] elements, @NotNull Comparator<T> comparator, int fromIndex, int toIndex) {
        if (elements != null && elements.length > 1) Arrays.sort(elements, fromIndex, toIndex, comparator);
    }


    /**
     * Sorts elements in the array in-place according to natural sort order of the value returned by specified [selector] function.
     */
    public static <T, R extends Comparable<R>> void sortBy(@Nullable T[] elements, @NotNull final NullableTransformer<T, R> transformer) {
        if (elements != null && elements.length > 1) {
            sortWith(elements, new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    R r1 = o1 != null ? transformer.transform(o1) : null;
                    R r2 = o2 != null ? transformer.transform(o2) : null;
                    return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
                }
            });
        }
    }


    /**
     * Sorts elements in the array in-place descending according to natural sort order of the value returned by specified [selector] function.
     */
    public static <T, R extends Comparable<R>> void sortByDescending(@Nullable T[] elements, @NotNull final NullableTransformer<T, R> transformer) {
        if (elements != null && elements.length > 1) {
            sortWith(elements, new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    R r1 = o2 != null ? transformer.transform(o2) : null;
                    R r2 = o1 != null ? transformer.transform(o1) : null;
                    return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
                }
            });
        }
    }


    /**
     * Returns a list of all elements sorted according to their natural sort order.
     */
    @NotNull
    public static <T extends Comparable<T>> List<T> sorted(@NotNull T[] elements) {
        return asList(sortedArray(elements));
    }

    /**
     * Returns a list of all elements sorted according to their natural sort order.
     */
    @NotNull
    public static List<Byte> sorted(@Nullable byte[] elements) {
        Byte[] result = toTypedArray(elements);
        sort(result);
        return asList(result);
    }

    /**
     * Returns a list of all elements sorted according to their natural sort order.
     */
    @NotNull
    public static List<Short> sorted(@Nullable short[] elements) {
        Short[] result = toTypedArray(elements);
        sort(result);
        return asList(result);
    }

    /**
     * Returns a list of all elements sorted according to their natural sort order.
     */
    @NotNull
    public static List<Integer> sorted(@Nullable int[] elements) {
        Integer[] result = toTypedArray(elements);
        sort(result);
        return asList(result);
    }

    /**
     * Returns a list of all elements sorted according to their natural sort order.
     */
    @NotNull
    public static List<Long> sorted(@Nullable long[] elements) {
        Long[] result = toTypedArray(elements);
        sort(result);
        return asList(result);
    }

    /**
     * Returns a list of all elements sorted according to their natural sort order.
     */
    @NotNull
    public static List<Float> sorted(@Nullable float[] elements) {
        Float[] result = toTypedArray(elements);
        sort(result);
        return asList(result);
    }

    /**
     * Returns a list of all elements sorted according to their natural sort order.
     */
    @NotNull
    public static List<Double> sorted(@Nullable double[] elements) {
        Double[] result = toTypedArray(elements);
        sort(result);
        return asList(result);
    }

    /**
     * Returns a list of all elements sorted according to their natural sort order.
     */
    @NotNull
    public static List<Character> sorted(@Nullable char[] elements) {
        Character[] result = toTypedArray(elements);
        sort(result);
        return asList(result);
    }


    /**
     * Returns a list of all elements sorted descending according to their natural sort order.
     */
    @NotNull
    public static <T extends Comparable<T>> List<T> sortedDescending(@Nullable T[] elements) {
        return Arrayx.sortedWith(elements, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o2.compareTo(o1);
            }
        });
    }

    /**
     * Returns a list of all elements sorted descending according to their natural sort order.
     */
    @NotNull
    public static List<Byte> sortedDescending(@Nullable byte[] elements) {
        byte[] result = Arrayx2.copyOf(elements);
        Arrayx.sort(result);
        return Arrayx.reversed(result);
    }

    /**
     * Returns a list of all elements sorted descending according to their natural sort order.
     */
    @NotNull
    public static List<Short> sortedDescending(@Nullable short[] elements) {
        short[] result = Arrayx2.copyOf(elements);
        Arrayx.sort(result);
        return Arrayx.reversed(result);
    }

    /**
     * Returns a list of all elements sorted descending according to their natural sort order.
     */
    @NotNull
    public static List<Integer> sortedDescending(@Nullable int[] elements) {
        int[] result = Arrayx2.copyOf(elements);
        Arrayx.sort(result);
        return Arrayx.reversed(result);
    }

    /**
     * Returns a list of all elements sorted descending according to their natural sort order.
     */
    @NotNull
    public static List<Long> sortedDescending(@Nullable long[] elements) {
        long[] result = Arrayx2.copyOf(elements);
        Arrayx.sort(result);
        return Arrayx.reversed(result);
    }

    /**
     * Returns a list of all elements sorted descending according to their natural sort order.
     */
    @NotNull
    public static List<Float> sortedDescending(@Nullable float[] elements) {
        float[] result = Arrayx2.copyOf(elements);
        Arrayx.sort(result);
        return Arrayx.reversed(result);
    }

    /**
     * Returns a list of all elements sorted descending according to their natural sort order.
     */
    @NotNull
    public static List<Double> sortedDescending(@Nullable double[] elements) {
        double[] result = Arrayx2.copyOf(elements);
        Arrayx.sort(result);
        return Arrayx.reversed(result);
    }

    /**
     * Returns a list of all elements sorted descending according to their natural sort order.
     */
    @NotNull
    public static List<Character> sortedDescending(@Nullable char[] elements) {
        char[] result = Arrayx2.copyOf(elements);
        Arrayx.sort(result);
        return Arrayx.reversed(result);
    }


    /**
     * Returns a list of all elements sorted according to the specified [comparator].
     */
    @NotNull
    public static <T> List<T> sortedWith(@Nullable T[] elements, @NotNull Comparator<T> comparator) {
        return Arrayx.asList(Arrayx.sortedArrayWith(elements, comparator));
    }

    /**
     * Returns a list of all elements sorted according to the specified [comparator].
     */
    @NotNull
    public static List<Byte> sortedWith(@Nullable byte[] elements, @NotNull Comparator<Byte> comparator) {
        Byte[] result = Arrayx.toTypedArray(elements);
        Arrayx.sortWith(result, comparator);
        return Arrayx.asList(result);
    }

    /**
     * Returns a list of all elements sorted according to the specified [comparator].
     */
    @NotNull
    public static List<Short> sortedWith(@Nullable short[] elements, @NotNull Comparator<Short> comparator) {
        Short[] result = Arrayx.toTypedArray(elements);
        Arrayx.sortWith(result, comparator);
        return Arrayx.asList(result);
    }

    /**
     * Returns a list of all elements sorted according to the specified [comparator].
     */
    @NotNull
    public static List<Integer> sortedWith(@Nullable int[] elements, @NotNull Comparator<Integer> comparator) {
        Integer[] result = Arrayx.toTypedArray(elements);
        Arrayx.sortWith(result, comparator);
        return Arrayx.asList(result);
    }

    /**
     * Returns a list of all elements sorted according to the specified [comparator].
     */
    @NotNull
    public static List<Long> sortedWith(@Nullable long[] elements, @NotNull Comparator<Long> comparator) {
        Long[] result = Arrayx.toTypedArray(elements);
        Arrayx.sortWith(result, comparator);
        return Arrayx.asList(result);
    }

    /**
     * Returns a list of all elements sorted according to the specified [comparator].
     */
    @NotNull
    public static List<Float> sortedWith(@Nullable float[] elements, @NotNull Comparator<Float> comparator) {
        Float[] result = Arrayx.toTypedArray(elements);
        Arrayx.sortWith(result, comparator);
        return Arrayx.asList(result);
    }

    /**
     * Returns a list of all elements sorted according to the specified [comparator].
     */
    @NotNull
    public static List<Double> sortedWith(@Nullable double[] elements, @NotNull Comparator<Double> comparator) {
        Double[] result = Arrayx.toTypedArray(elements);
        Arrayx.sortWith(result, comparator);
        return Arrayx.asList(result);
    }

    /**
     * Returns a list of all elements sorted according to the specified [comparator].
     */
    @NotNull
    public static List<Boolean> sortedWith(@Nullable boolean[] elements, @NotNull Comparator<Boolean> comparator) {
        Boolean[] result = Arrayx.toTypedArray(elements);
        Arrayx.sortWith(result, comparator);
        return Arrayx.asList(result);
    }

    /**
     * Returns a list of all elements sorted according to the specified [comparator].
     */
    @NotNull
    public static List<Character> sortedWith(@Nullable char[] elements, @NotNull Comparator<Character> comparator) {
        Character[] result = Arrayx.toTypedArray(elements);
        Arrayx.sortWith(result, comparator);
        return Arrayx.asList(result);
    }


    /**
     * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
     */
    @NotNull
    public static <T, R extends Comparable<R>> List<T> sortedBy(@Nullable T[] elements, @NotNull final NullableTransformer<T, R> transformer) {
        return sortedWith(elements, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
     */
    @NotNull
    public static <R extends Comparable<R>> List<Byte> sortedBy(@Nullable byte[] elements, @NotNull final NullableTransformer<Byte, R> transformer) {
        return sortedWith(elements, new Comparator<Byte>() {
            @Override
            public int compare(Byte o1, Byte o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
     */
    @NotNull
    public static <R extends Comparable<R>> List<Short> sortedBy(@Nullable short[] elements, @NotNull final NullableTransformer<Short, R> transformer) {
        return sortedWith(elements, new Comparator<Short>() {
            @Override
            public int compare(Short o1, Short o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
     */
    @NotNull
    public static <R extends Comparable<R>> List<Integer> sortedBy(@Nullable int[] elements, @NotNull final NullableTransformer<Integer, R> transformer) {
        return sortedWith(elements, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
     */
    @NotNull
    public static <R extends Comparable<R>> List<Long> sortedBy(@Nullable long[] elements, @NotNull final NullableTransformer<Long, R> transformer) {
        return sortedWith(elements, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
     */
    @NotNull
    public static <R extends Comparable<R>> List<Float> sortedBy(@Nullable float[] elements, @NotNull final NullableTransformer<Float, R> transformer) {
        return sortedWith(elements, new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
     */
    @NotNull
    public static <R extends Comparable<R>> List<Double> sortedBy(@Nullable double[] elements, @NotNull final NullableTransformer<Double, R> transformer) {
        return sortedWith(elements, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
     */
    @NotNull
    public static <R extends Comparable<R>> List<Boolean> sortedBy(@Nullable boolean[] elements, @NotNull final NullableTransformer<Boolean, R> transformer) {
        return sortedWith(elements, new Comparator<Boolean>() {
            @Override
            public int compare(Boolean o1, Boolean o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
     */
    @NotNull
    public static <R extends Comparable<R>> List<Character> sortedBy(@Nullable char[] elements, @NotNull final NullableTransformer<Character, R> transformer) {
        return sortedWith(elements, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }


    /**
     * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
     */
    public static <T, R extends Comparable<R>> List<T> sortedByDescending(@Nullable T[] elements, @NotNull final NullableTransformer<T, R> transformer) {
        return sortedWith(elements, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
     */
    public static <R extends Comparable<R>> List<Byte> sortedByDescending(@Nullable byte[] elements, @NotNull final NullableTransformer<Byte, R> transformer) {
        return sortedWith(elements, new Comparator<Byte>() {
            @Override
            public int compare(Byte o1, Byte o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
     */
    public static <R extends Comparable<R>> List<Short> sortedByDescending(@Nullable short[] elements, @NotNull final NullableTransformer<Short, R> transformer) {
        return sortedWith(elements, new Comparator<Short>() {
            @Override
            public int compare(Short o1, Short o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
     */
    public static <R extends Comparable<R>> List<Integer> sortedByDescending(@Nullable int[] elements, @NotNull final NullableTransformer<Integer, R> transformer) {
        return sortedWith(elements, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                R r1 = o1 != null ? transformer.transform(o1) : null;
                R r2 = o2 != null ? transformer.transform(o2) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
     */
    public static <R extends Comparable<R>> List<Long> sortedByDescending(@Nullable long[] elements, @NotNull final NullableTransformer<Long, R> transformer) {
        return sortedWith(elements, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                R r1 = o2 != null ? transformer.transform(o2) : null;
                R r2 = o1 != null ? transformer.transform(o1) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
     */
    public static <R extends Comparable<R>> List<Float> sortedByDescending(@Nullable float[] elements, @NotNull final NullableTransformer<Float, R> transformer) {
        return sortedWith(elements, new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
                R r1 = o2 != null ? transformer.transform(o2) : null;
                R r2 = o1 != null ? transformer.transform(o1) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
     */
    public static <R extends Comparable<R>> List<Double> sortedByDescending(@Nullable double[] elements, @NotNull final NullableTransformer<Double, R> transformer) {
        return sortedWith(elements, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                R r1 = o2 != null ? transformer.transform(o2) : null;
                R r2 = o1 != null ? transformer.transform(o1) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
     */
    public static <R extends Comparable<R>> List<Boolean> sortedByDescending(@Nullable boolean[] elements, @NotNull final NullableTransformer<Boolean, R> transformer) {
        return sortedWith(elements, new Comparator<Boolean>() {
            @Override
            public int compare(Boolean o1, Boolean o2) {
                R r1 = o2 != null ? transformer.transform(o2) : null;
                R r2 = o1 != null ? transformer.transform(o1) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }

    /**
     * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
     */
    public static <R extends Comparable<R>> List<Character> sortedByDescending(@Nullable char[] elements, @NotNull final NullableTransformer<Character, R> transformer) {
        return sortedWith(elements, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                R r1 = o2 != null ? transformer.transform(o2) : null;
                R r2 = o1 != null ? transformer.transform(o1) : null;
                return r1 == r2 ? 0 : (r1 == null ? -1 : (r2 == null ? 1 : (r1.compareTo(r2))));
            }
        });
    }


    /**
     * Returns an array with all elements of this array sorted according to their natural sort order.
     */
    @NotNull
    public static <T extends Comparable<T>> T[] sortedArray(@NotNull T[] elements) {
        T[] result = Arrayx2.copyOf(elements);
        sort(result);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted according to their natural sort order.
     */
    @NotNull
    public static byte[] sortedArray(@Nullable byte[] elements) {
        byte[] result = Arrayx2.copyOf(elements);
        sort(result);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted according to their natural sort order.
     */
    @NotNull
    public static short[] sortedArray(@Nullable short[] elements) {
        short[] result = Arrayx2.copyOf(elements);
        sort(result);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted according to their natural sort order.
     */
    @NotNull
    public static int[] sortedArray(@Nullable int[] elements) {
        int[] result = Arrayx2.copyOf(elements);
        sort(result);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted according to their natural sort order.
     */
    @NotNull
    public static long[] sortedArray(@Nullable long[] elements) {
        long[] result = Arrayx2.copyOf(elements);
        sort(result);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted according to their natural sort order.
     */
    @NotNull
    public static float[] sortedArray(@Nullable float[] elements) {
        float[] result = Arrayx2.copyOf(elements);
        sort(result);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted according to their natural sort order.
     */
    @NotNull
    public static double[] sortedArray(@Nullable double[] elements) {
        double[] result = Arrayx2.copyOf(elements);
        sort(result);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted according to their natural sort order.
     */
    @NotNull
    public static char[] sortedArray(@Nullable char[] elements) {
        char[] result = Arrayx2.copyOf(elements);
        sort(result);
        return result;
    }


    /**
     * Returns an array with all elements of this array sorted descending according to their natural sort order.
     */
    @NotNull
    public static <T extends Comparable<T>> T[] sortedArrayDescending(@NotNull T[] elements) {
        T[] result = Arrayx2.copyOf(elements);
        Arrayx.sortWith(elements, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o2.compareTo(o1);
            }
        });
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted descending according to their natural sort order.
     */
    @NotNull
    public static byte[] sortedArrayDescending(@Nullable byte[] elements) {
        byte[] result = Arrayx2.copyOf(elements);
        Arrayx.sortDescending(elements);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted descending according to their natural sort order.
     */
    @NotNull
    public static short[] sortedArrayDescending(@Nullable short[] elements) {
        short[] result = Arrayx2.copyOf(elements);
        Arrayx.sortDescending(elements);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted descending according to their natural sort order.
     */
    @NotNull
    public static int[] sortedArrayDescending(@Nullable int[] elements) {
        int[] result = Arrayx2.copyOf(elements);
        Arrayx.sortDescending(elements);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted descending according to their natural sort order.
     */
    @NotNull
    public static long[] sortedArrayDescending(@Nullable long[] elements) {
        long[] result = Arrayx2.copyOf(elements);
        Arrayx.sortDescending(elements);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted descending according to their natural sort order.
     */
    @NotNull
    public static float[] sortedArrayDescending(@Nullable float[] elements) {
        float[] result = Arrayx2.copyOf(elements);
        Arrayx.sortDescending(elements);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted descending according to their natural sort order.
     */
    @NotNull
    public static double[] sortedArrayDescending(@Nullable double[] elements) {
        double[] result = Arrayx2.copyOf(elements);
        Arrayx.sortDescending(elements);
        return result;
    }

    /**
     * Returns an array with all elements of this array sorted descending according to their natural sort order.
     */
    @NotNull
    public static char[] sortedArrayDescending(@Nullable char[] elements) {
        char[] result = Arrayx2.copyOf(elements);
        Arrayx.sortDescending(elements);
        return result;
    }


    /**
     * Returns an array with all elements of this array sorted according the specified [comparator].
     */
    @NotNull
    public static <T> T[] sortedArrayWith(@NotNull T[] elements, @NotNull Comparator<T> comparator) {
        T[] result = Arrayx2.copyOf(elements);
        Arrayx.sortWith(elements, comparator);
        return result;
    }


    /* ******************************************* single ******************************************* */


    /**
     * Returns the single element, or throws an exception if the array is empty or has more than one element.
     */
    @NotNull
    public static <T> T single(@Nullable T[] elements) {
        if (elements == null || elements.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        } else if (elements.length == 1) {
            return elements[0];
        } else {
            throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    /**
     * Returns the single element, or throws an exception if the array is empty or has more than one element.
     */
    public static char single(@Nullable char[] elements) {
        if (elements == null || elements.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        } else if (elements.length == 1) {
            return elements[0];
        } else {
            throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    /**
     * Returns the single element, or throws an exception if the array is empty or has more than one element.
     */
    public static byte single(@Nullable byte[] elements) {
        if (elements == null || elements.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        } else if (elements.length == 1) {
            return elements[0];
        } else {
            throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    /**
     * Returns the single element, or throws an exception if the array is empty or has more than one element.
     */
    public static short single(@Nullable short[] elements) {
        if (elements == null || elements.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        } else if (elements.length == 1) {
            return elements[0];
        } else {
            throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    /**
     * Returns the single element, or throws an exception if the array is empty or has more than one element.
     */
    public static int single(@Nullable int[] elements) {
        if (elements == null || elements.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        } else if (elements.length == 1) {
            return elements[0];
        } else {
            throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    /**
     * Returns the single element, or throws an exception if the array is empty or has more than one element.
     */
    public static long single(@Nullable long[] elements) {
        if (elements == null || elements.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        } else if (elements.length == 1) {
            return elements[0];
        } else {
            throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    /**
     * Returns the single element, or throws an exception if the array is empty or has more than one element.
     */
    public static float single(@Nullable float[] elements) {
        if (elements == null || elements.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        } else if (elements.length == 1) {
            return elements[0];
        } else {
            throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    /**
     * Returns the single element, or throws an exception if the array is empty or has more than one element.
     */
    public static double single(@Nullable double[] elements) {
        if (elements == null || elements.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        } else if (elements.length == 1) {
            return elements[0];
        } else {
            throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    /**
     * Returns the single element, or throws an exception if the array is empty or has more than one element.
     */
    public static boolean single(@Nullable boolean[] elements) {
        if (elements == null || elements.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        } else if (elements.length == 1) {
            return elements[0];
        } else {
            throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    /**
     * Returns single element, or `null` if the array is empty or has more than one element.
     */
    @Nullable
    public static <T> T singleOrNull(@Nullable T[] elements) {
        return elements != null && elements.length == 1 ? elements[0] : null;
    }

    /**
     * Returns single element, or `null` if the array is empty or has more than one element.
     */
    @Nullable
    public static Character singleOrNull(@Nullable char[] elements) {
        return elements != null && elements.length == 1 ? elements[0] : null;
    }

    /**
     * Returns single element, or `null` if the array is empty or has more than one element.
     */
    @Nullable
    public static Byte singleOrNull(@Nullable byte[] elements) {
        return elements != null && elements.length == 1 ? elements[0] : null;
    }

    /**
     * Returns single element, or `null` if the array is empty or has more than one element.
     */
    @Nullable
    public static Short singleOrNull(@Nullable short[] elements) {
        return elements != null && elements.length == 1 ? elements[0] : null;
    }

    /**
     * Returns single element, or `null` if the array is empty or has more than one element.
     */
    @Nullable
    public static Integer singleOrNull(@Nullable int[] elements) {
        return elements != null && elements.length == 1 ? elements[0] : null;
    }

    /**
     * Returns single element, or `null` if the array is empty or has more than one element.
     */
    @Nullable
    public static Long singleOrNull(@Nullable long[] elements) {
        return elements != null && elements.length == 1 ? elements[0] : null;
    }

    /**
     * Returns single element, or `null` if the array is empty or has more than one element.
     */
    @Nullable
    public static Float singleOrNull(@Nullable float[] elements) {
        return elements != null && elements.length == 1 ? elements[0] : null;
    }

    /**
     * Returns single element, or `null` if the array is empty or has more than one element.
     */
    @Nullable
    public static Double singleOrNull(@Nullable double[] elements) {
        return elements != null && elements.length == 1 ? elements[0] : null;
    }

    /**
     * Returns single element, or `null` if the array is empty or has more than one element.
     */
    @Nullable
    public static Boolean singleOrNull(@Nullable boolean[] elements) {
        return elements != null && elements.length == 1 ? elements[0] : null;
    }

    /**
     * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
     */
    @NotNull
    public static <T> T single(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        T single = null;
        boolean found = false;
        if (elements != null) {
            for (T element : elements) {
                if (predicate.accept(element)) {
                    if (found) throw new IllegalArgumentException("Array contains more than one matching element.");
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) throw new NoSuchElementException("Array contains no element matching the predicate.");
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
     */
    public static char single(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        char single = 0;
        boolean found = false;
        if (elements != null) {
            for (char element : elements) {
                if (predicate.accept(element)) {
                    if (found) throw new IllegalArgumentException("Array contains more than one matching element.");
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) throw new NoSuchElementException("Array contains no element matching the predicate.");
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
     */
    public static byte single(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        byte single = 0;
        boolean found = false;
        if (elements != null) {
            for (byte element : elements) {
                if (predicate.accept(element)) {
                    if (found) throw new IllegalArgumentException("Array contains more than one matching element.");
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) throw new NoSuchElementException("Array contains no element matching the predicate.");
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
     */
    public static short single(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        short single = 0;
        boolean found = false;
        if (elements != null) {
            for (short element : elements) {
                if (predicate.accept(element)) {
                    if (found) throw new IllegalArgumentException("Array contains more than one matching element.");
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) throw new NoSuchElementException("Array contains no element matching the predicate.");
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
     */
    public static int single(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        int single = 0;
        boolean found = false;
        if (elements != null) {
            for (int element : elements) {
                if (predicate.accept(element)) {
                    if (found) throw new IllegalArgumentException("Array contains more than one matching element.");
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) throw new NoSuchElementException("Array contains no element matching the predicate.");
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
     */
    public static long single(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        long single = 0;
        boolean found = false;
        if (elements != null) {
            for (long element : elements) {
                if (predicate.accept(element)) {
                    if (found) throw new IllegalArgumentException("Array contains more than one matching element.");
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) throw new NoSuchElementException("Array contains no element matching the predicate.");
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
     */
    public static float single(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        float single = 0;
        boolean found = false;
        if (elements != null) {
            for (float element : elements) {
                if (predicate.accept(element)) {
                    if (found) throw new IllegalArgumentException("Array contains more than one matching element.");
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) throw new NoSuchElementException("Array contains no element matching the predicate.");
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
     */
    public static double single(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        double single = 0;
        boolean found = false;
        if (elements != null) {
            for (double element : elements) {
                if (predicate.accept(element)) {
                    if (found) throw new IllegalArgumentException("Array contains more than one matching element.");
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) throw new NoSuchElementException("Array contains no element matching the predicate.");
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
     */
    public boolean single(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        boolean single = false;
        boolean found = false;
        if (elements != null) {
            for (boolean element : elements) {
                if (predicate.accept(element)) {
                    if (found) throw new IllegalArgumentException("Array contains more than one matching element.");
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) throw new NoSuchElementException("Array contains no element matching the predicate.");
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
     */
    @Nullable
    public static <T> T singleOrNull(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        T single = null;
        boolean found = false;
        if (elements != null) {
            for (T element : elements) {
                if (predicate.accept(element)) {
                    if (found) return null;
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) return null;
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
     */
    @Nullable
    public static Character singleOrNull(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        char single = 0;
        boolean found = false;
        if (elements != null) {
            for (char element : elements) {
                if (predicate.accept(element)) {
                    if (found) return null;
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) return null;
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
     */
    @Nullable
    public static Byte singleOrNull(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        byte single = 0;
        boolean found = false;
        if (elements != null) {
            for (byte element : elements) {
                if (predicate.accept(element)) {
                    if (found) return null;
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) return null;
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
     */
    @Nullable
    public static Short singleOrNull(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        short single = 0;
        boolean found = false;
        if (elements != null) {
            for (short element : elements) {
                if (predicate.accept(element)) {
                    if (found) return null;
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) return null;
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
     */
    @Nullable
    public static Integer singleOrNull(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        int single = 0;
        boolean found = false;
        if (elements != null) {
            for (int element : elements) {
                if (predicate.accept(element)) {
                    if (found) return null;
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) return null;
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
     */
    @Nullable
    public static Long singleOrNull(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        long single = 0;
        boolean found = false;
        if (elements != null) {
            for (long element : elements) {
                if (predicate.accept(element)) {
                    if (found) return null;
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) return null;
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
     */
    @Nullable
    public static Float singleOrNull(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        float single = 0;
        boolean found = false;
        if (elements != null) {
            for (float element : elements) {
                if (predicate.accept(element)) {
                    if (found) return null;
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) return null;
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
     */
    @Nullable
    public static Double singleOrNull(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        double single = 0;
        boolean found = false;
        if (elements != null) {
            for (double element : elements) {
                if (predicate.accept(element)) {
                    if (found) return null;
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) return null;
        return single;
    }

    /**
     * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
     */
    @Nullable
    public static Boolean singleOrNull(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        boolean single = false;
        boolean found = false;
        if (elements != null) {
            for (boolean element : elements) {
                if (predicate.accept(element)) {
                    if (found) return null;
                    single = element;
                    found = true;
                }
            }
        }
        if (!found) return null;
        return single;
    }


    /* ******************************************* any ******************************************* */


    /**
     * Returns `true` if array has at least one element.
     */
    public static <T> boolean any(@Nullable T[] elements) {
        return !isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if array has at least one element.
     */
    public static boolean any(@Nullable byte[] elements) {
        return !isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if array has at least one element.
     */
    public static boolean any(@Nullable short[] elements) {
        return !isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if array has at least one element.
     */
    public static boolean any(@Nullable int[] elements) {
        return !isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if array has at least one element.
     */
    public static boolean any(@Nullable long[] elements) {
        return !isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if array has at least one element.
     */
    public static boolean any(@Nullable float[] elements) {
        return !isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if array has at least one element.
     */
    public static boolean any(@Nullable double[] elements) {
        return !isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if array has at least one element.
     */
    public static boolean any(@Nullable boolean[] elements) {
        return !isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if array has at least one element.
     */
    public static boolean any(@Nullable char[] elements) {
        return !isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if at least one element matches the given [predicate].
     */
    public static <T> boolean any(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        if (elements != null) for (T element : elements) if (predicate.accept(element)) return true;
        return false;
    }

    /**
     * Returns `true` if at least one element matches the given [predicate].
     */
    public static boolean any(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        if (elements != null) for (byte element : elements) if (predicate.accept(element)) return true;
        return false;
    }

    /**
     * Returns `true` if at least one element matches the given [predicate].
     */
    public static boolean any(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        if (elements != null) for (short element : elements) if (predicate.accept(element)) return true;
        return false;
    }

    /**
     * Returns `true` if at least one element matches the given [predicate].
     */
    public static boolean any(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        if (elements != null) for (int element : elements) if (predicate.accept(element)) return true;
        return false;
    }

    /**
     * Returns `true` if at least one element matches the given [predicate].
     */
    public static boolean any(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        if (elements != null) for (long element : elements) if (predicate.accept(element)) return true;
        return false;
    }

    /**
     * Returns `true` if at least one element matches the given [predicate].
     */
    public static boolean any(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        if (elements != null) for (float element : elements) if (predicate.accept(element)) return true;
        return false;
    }

    /**
     * Returns `true` if at least one element matches the given [predicate].
     */
    public static boolean any(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        if (elements != null) for (double element : elements) if (predicate.accept(element)) return true;
        return false;
    }

    /**
     * Returns `true` if at least one element matches the given [predicate].
     */
    public static boolean any(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        if (elements != null) for (boolean element : elements) if (predicate.accept(element)) return true;
        return false;
    }

    /**
     * Returns `true` if at least one element matches the given [predicate].
     */
    public static boolean any(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        if (elements != null) for (char element : elements) if (predicate.accept(element)) return true;
        return false;
    }


    /* ******************************************* all ******************************************* */

    /**
     * Returns `true` if all elements match the given [predicate].
     */
    public static <T> boolean all(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        if (elements == null) return false;
        for (T element : elements) if (!predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if all elements match the given [predicate].
     */
    public static boolean all(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        if (elements == null) return false;
        for (byte element : elements) if (!predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if all elements match the given [predicate].
     */
    public static boolean all(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        if (elements == null) return false;
        for (short element : elements) if (!predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if all elements match the given [predicate].
     */
    public static boolean all(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        if (elements == null) return false;
        for (int element : elements) if (!predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if all elements match the given [predicate].
     */
    public static boolean all(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        if (elements == null) return false;
        for (long element : elements) if (!predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if all elements match the given [predicate].
     */
    public static boolean all(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        if (elements == null) return false;
        for (float element : elements) if (!predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if all elements match the given [predicate].
     */
    public static boolean all(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        if (elements == null) return false;
        for (double element : elements) if (!predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if all elements match the given [predicate].
     */
    public static boolean all(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        if (elements == null) return false;
        for (boolean element : elements) if (!predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if all elements match the given [predicate].
     */
    public static boolean all(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        if (elements == null) return false;
        for (char element : elements) if (!predicate.accept(element)) return false;
        return true;
    }


    /* ******************************************* iterator ******************************************* */


    /**
     * Return a array iterator of original array.
     */
    @NotNull
    public static <T> Iterator<T> iterator(@Nullable T[] elements) {
        return new ArrayIterator<>(elements);
    }

    /**
     * Return a array iterator of original array.
     */
    @NotNull
    public static Iterator<Byte> iterator(@Nullable byte[] elements) {
        return new ArrayByteIterator(elements);
    }

    /**
     * Return a array iterator of original array.
     */
    @NotNull
    public static Iterator<Short> iterator(@Nullable short[] elements) {
        return new ArrayShortIterator(elements);
    }

    /**
     * Return a array iterator of original array.
     */
    @NotNull
    public static Iterator<Integer> iterator(@Nullable int[] elements) {
        return new ArrayIntIterator(elements);
    }

    /**
     * Return a array iterator of original array.
     */
    @NotNull
    public static Iterator<Long> iterator(@Nullable long[] elements) {
        return new ArrayLongIterator(elements);
    }

    /**
     * Return a array iterator of original array.
     */
    @NotNull
    public static Iterator<Float> iterator(@Nullable float[] elements) {
        return new ArrayFloatIterator(elements);
    }

    /**
     * Return a array iterator of original array.
     */
    @NotNull
    public static Iterator<Double> iterator(@Nullable double[] elements) {
        return new ArrayDoubleIterator(elements);
    }

    /**
     * Return a array iterator of original array.
     */
    @NotNull
    public static Iterator<Boolean> iterator(@Nullable boolean[] elements) {
        return new ArrayBooleanIterator(elements);
    }

    /**
     * Return a array iterator of original array.
     */
    @NotNull
    public static Iterator<Character> iterator(@Nullable char[] elements) {
        return new ArrayCharIterator(elements);
    }

    /**
     * Creates an [Iterable] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static <T> Iterable<T> asIterable(@Nullable final T[] elements) {
        if (isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        return new Iterable<T>() {
            @NotNull
            @Override
            public Iterator<T> iterator() {
                return Arrayx.iterator(elements);
            }
        };
    }

    /**
     * Creates an [Iterable] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Iterable<Byte> asIterable(@Nullable final byte[] elements) {
        if (isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        return new Iterable<Byte>() {
            @NotNull
            @Override
            public Iterator<Byte> iterator() {
                return Arrayx.iterator(elements);
            }
        };
    }

    /**
     * Creates an [Iterable] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Iterable<Short> asIterable(@Nullable final short[] elements) {
        if (isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        return new Iterable<Short>() {
            @NotNull
            @Override
            public Iterator<Short> iterator() {
                return Arrayx.iterator(elements);
            }
        };
    }

    /**
     * Creates an [Iterable] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Iterable<Integer> asIterable(@Nullable final int[] elements) {
        if (isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        return new Iterable<Integer>() {
            @NotNull
            @Override
            public Iterator<Integer> iterator() {
                return Arrayx.iterator(elements);
            }
        };
    }

    /**
     * Creates an [Iterable] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Iterable<Long> asIterable(@Nullable final long[] elements) {
        if (isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        return new Iterable<Long>() {
            @NotNull
            @Override
            public Iterator<Long> iterator() {
                return Arrayx.iterator(elements);
            }
        };
    }

    /**
     * Creates an [Iterable] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Iterable<Float> asIterable(@Nullable final float[] elements) {
        if (isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        return new Iterable<Float>() {
            @NotNull
            @Override
            public Iterator<Float> iterator() {
                return Arrayx.iterator(elements);
            }
        };
    }

    /**
     * Creates an [Iterable] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Iterable<Double> asIterable(@Nullable final double[] elements) {
        if (isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        return new Iterable<Double>() {
            @NotNull
            @Override
            public Iterator<Double> iterator() {
                return Arrayx.iterator(elements);
            }
        };
    }

    /**
     * Creates an [Iterable] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Iterable<Boolean> asIterable(@Nullable final boolean[] elements) {
        if (isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        return new Iterable<Boolean>() {
            @NotNull
            @Override
            public Iterator<Boolean> iterator() {
                return Arrayx.iterator(elements);
            }
        };
    }

    /**
     * Creates an [Iterable] instance that wraps the original array returning its elements when being iterated.
     */
    @NotNull
    public static Iterable<Character> asIterable(@Nullable final char[] elements) {
        if (isNullOrEmpty(elements)) return Collectionx.arrayListOf();
        return new Iterable<Character>() {
            @NotNull
            @Override
            public Iterator<Character> iterator() {
                return Arrayx.iterator(elements);
            }
        };
    }


    /* ******************************************* group ******************************************* */


    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <T, K> Map<K, List<T>> groupBy(@Nullable T[] elements, @NotNull Transformer<T, K> keySelector) {
        return groupByTo(elements, new LinkedHashMap<K, List<T>>(), keySelector);
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K> Map<K, List<Byte>> groupBy(@Nullable byte[] elements, @NotNull Transformer<Byte, K> keySelector) {
        return groupByTo(elements, new LinkedHashMap<K, List<Byte>>(), keySelector);
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K> Map<K, List<Short>> groupBy(@Nullable short[] elements, @NotNull Transformer<Short, K> keySelector) {
        return groupByTo(elements, new LinkedHashMap<K, List<Short>>(), keySelector);
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K> Map<K, List<Integer>> groupBy(@Nullable int[] elements, @NotNull Transformer<Integer, K> keySelector) {
        return groupByTo(elements, new LinkedHashMap<K, List<Integer>>(), keySelector);
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K> Map<K, List<Long>> groupBy(@Nullable long[] elements, @NotNull Transformer<Long, K> keySelector) {
        return groupByTo(elements, new LinkedHashMap<K, List<Long>>(), keySelector);
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K> Map<K, List<Float>> groupBy(@Nullable float[] elements, @NotNull Transformer<Float, K> keySelector) {
        return groupByTo(elements, new LinkedHashMap<K, List<Float>>(), keySelector);
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K> Map<K, List<Double>> groupBy(@Nullable double[] elements, @NotNull Transformer<Double, K> keySelector) {
        return groupByTo(elements, new LinkedHashMap<K, List<Double>>(), keySelector);
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K> Map<K, List<Boolean>> groupBy(@Nullable boolean[] elements, @NotNull Transformer<Boolean, K> keySelector) {
        return groupByTo(elements, new LinkedHashMap<K, List<Boolean>>(), keySelector);
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K> Map<K, List<Character>> groupBy(@Nullable char[] elements, @NotNull Transformer<Character, K> keySelector) {
        return groupByTo(elements, new LinkedHashMap<K, List<Character>>(), keySelector);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <T, K, V> Map<K, List<V>> groupBy(@Nullable T[] elements, @NotNull Transformer<T, K> keySelector, @NotNull Transformer<T, V> valueTransform) {
        return groupByTo(elements, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K, V> Map<K, List<V>> groupBy(@Nullable byte[] elements, @NotNull Transformer<Byte, K> keySelector, @NotNull Transformer<Byte, V> valueTransform) {
        return groupByTo(elements, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K, V> Map<K, List<V>> groupBy(@Nullable short[] elements, @NotNull Transformer<Short, K> keySelector, @NotNull Transformer<Short, V> valueTransform) {
        return groupByTo(elements, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K, V> Map<K, List<V>> groupBy(@Nullable int[] elements, @NotNull Transformer<Integer, K> keySelector, @NotNull Transformer<Integer, V> valueTransform) {
        return groupByTo(elements, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K, V> Map<K, List<V>> groupBy(@Nullable long[] elements, @NotNull Transformer<Long, K> keySelector, @NotNull Transformer<Long, V> valueTransform) {
        return groupByTo(elements, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K, V> Map<K, List<V>> groupBy(@Nullable float[] elements, @NotNull Transformer<Float, K> keySelector, @NotNull Transformer<Float, V> valueTransform) {
        return groupByTo(elements, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K, V> Map<K, List<V>> groupBy(@Nullable double[] elements, @NotNull Transformer<Double, K> keySelector, @NotNull Transformer<Double, V> valueTransform) {
        return groupByTo(elements, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K, V> Map<K, List<V>> groupBy(@Nullable boolean[] elements, @NotNull Transformer<Boolean, K> keySelector, @NotNull Transformer<Boolean, V> valueTransform) {
        return groupByTo(elements, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and returns a map where each group key is associated with a list of corresponding values.
     * <p>
     * The returned map preserves the entry iteration order of the keys produced from the original array.
     */
    @NotNull
    public static <K, V> Map<K, List<V>> groupBy(@Nullable char[] elements, @NotNull Transformer<Character, K> keySelector, @NotNull Transformer<Character, V> valueTransform) {
        return groupByTo(elements, new LinkedHashMap<K, List<V>>(), keySelector, valueTransform);
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <T, K, M extends Map<K, List<T>>> M groupByTo(@Nullable T[] elements, @NotNull M destination, @NotNull Transformer<T, K> keySelector) {
        DefaultValue<List<T>> defaultValue = new DefaultValue<List<T>>() {
            @NotNull
            @Override
            public List<T> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (T element : elements) {
                K key = keySelector.transform(element);
                List<T> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(element);
            }
        }
        return destination;
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, M extends Map<K, List<Byte>>> M groupByTo(@Nullable byte[] elements, @NotNull M destination, @NotNull Transformer<Byte, K> keySelector) {
        DefaultValue<List<Byte>> defaultValue = new DefaultValue<List<Byte>>() {
            @NotNull
            @Override
            public List<Byte> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (byte element : elements) {
                K key = keySelector.transform(element);
                List<Byte> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(element);
            }
        }
        return destination;
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, M extends Map<K, List<Short>>> M groupByTo(@Nullable short[] elements, @NotNull M destination, @NotNull Transformer<Short, K> keySelector) {
        DefaultValue<List<Short>> defaultValue = new DefaultValue<List<Short>>() {
            @NotNull
            @Override
            public List<Short> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (short element : elements) {
                K key = keySelector.transform(element);
                List<Short> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(element);
            }
        }
        return destination;
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, M extends Map<K, List<Integer>>> M groupByTo(@Nullable int[] elements, @NotNull M destination, @NotNull Transformer<Integer, K> keySelector) {
        DefaultValue<List<Integer>> defaultValue = new DefaultValue<List<Integer>>() {
            @NotNull
            @Override
            public List<Integer> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (int element : elements) {
                K key = keySelector.transform(element);
                List<Integer> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(element);
            }
        }
        return destination;
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, M extends Map<K, List<Long>>> M groupByTo(@Nullable long[] elements, @NotNull M destination, @NotNull Transformer<Long, K> keySelector) {
        DefaultValue<List<Long>> defaultValue = new DefaultValue<List<Long>>() {
            @NotNull
            @Override
            public List<Long> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (long element : elements) {
                K key = keySelector.transform(element);
                List<Long> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(element);
            }
        }
        return destination;
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, M extends Map<K, List<Float>>> M groupByTo(@Nullable float[] elements, @NotNull M destination, @NotNull Transformer<Float, K> keySelector) {
        DefaultValue<List<Float>> defaultValue = new DefaultValue<List<Float>>() {
            @NotNull
            @Override
            public List<Float> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (float element : elements) {
                K key = keySelector.transform(element);
                List<Float> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(element);
            }
        }
        return destination;
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, M extends Map<K, List<Double>>> M groupByTo(@Nullable double[] elements, @NotNull M destination, @NotNull Transformer<Double, K> keySelector) {
        DefaultValue<List<Double>> defaultValue = new DefaultValue<List<Double>>() {
            @NotNull
            @Override
            public List<Double> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (double element : elements) {
                K key = keySelector.transform(element);
                List<Double> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(element);
            }
        }
        return destination;
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, M extends Map<K, List<Boolean>>> M groupByTo(@Nullable boolean[] elements, @NotNull M destination, @NotNull Transformer<Boolean, K> keySelector) {
        DefaultValue<List<Boolean>> defaultValue = new DefaultValue<List<Boolean>>() {
            @NotNull
            @Override
            public List<Boolean> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (boolean element : elements) {
                K key = keySelector.transform(element);
                List<Boolean> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(element);
            }
        }
        return destination;
    }

    /**
     * Groups elements of the original array by the key returned by the given [keySelector] function
     * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, M extends Map<K, List<Character>>> M groupByTo(@Nullable char[] elements, @NotNull M destination, @NotNull Transformer<Character, K> keySelector) {
        DefaultValue<List<Character>> defaultValue = new DefaultValue<List<Character>>() {
            @NotNull
            @Override
            public List<Character> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (char element : elements) {
                K key = keySelector.transform(element);
                List<Character> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(element);
            }
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <T, K, V, M extends Map<K, List<V>>> M groupByTo(@Nullable T[] elements, @NotNull M destination, @NotNull Transformer<T, K> keySelector, @NotNull Transformer<T, V> valueTransform) {
        DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
            @NotNull
            @Override
            public List<V> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (T element : elements) {
                K key = keySelector.transform(element);
                List<V> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(valueTransform.transform(element));
            }
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, V, M extends Map<K, List<V>>> M groupByTo(@Nullable byte[] elements, @NotNull M destination, @NotNull Transformer<Byte, K> keySelector, @NotNull Transformer<Byte, V> valueTransform) {
        DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
            @NotNull
            @Override
            public List<V> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (byte element : elements) {
                K key = keySelector.transform(element);
                List<V> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(valueTransform.transform(element));
            }
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, V, M extends Map<K, List<V>>> M groupByTo(@Nullable short[] elements, @NotNull M destination, @NotNull Transformer<Short, K> keySelector, @NotNull Transformer<Short, V> valueTransform) {
        DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
            @NotNull
            @Override
            public List<V> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (short element : elements) {
                K key = keySelector.transform(element);
                List<V> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(valueTransform.transform(element));
            }
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, V, M extends Map<K, List<V>>> M groupByTo(@Nullable int[] elements, @NotNull M destination, @NotNull Transformer<Integer, K> keySelector, @NotNull Transformer<Integer, V> valueTransform) {
        DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
            @NotNull
            @Override
            public List<V> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (int element : elements) {
                K key = keySelector.transform(element);
                List<V> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(valueTransform.transform(element));
            }
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, V, M extends Map<K, List<V>>> M groupByTo(@Nullable long[] elements, @NotNull M destination, @NotNull Transformer<Long, K> keySelector, @NotNull Transformer<Long, V> valueTransform) {
        DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
            @NotNull
            @Override
            public List<V> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (long element : elements) {
                K key = keySelector.transform(element);
                List<V> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(valueTransform.transform(element));
            }
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, V, M extends Map<K, List<V>>> M groupByTo(@Nullable float[] elements, @NotNull M destination, @NotNull Transformer<Float, K> keySelector, @NotNull Transformer<Float, V> valueTransform) {
        DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
            @NotNull
            @Override
            public List<V> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (float element : elements) {
                K key = keySelector.transform(element);
                List<V> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(valueTransform.transform(element));
            }
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, V, M extends Map<K, List<V>>> M groupByTo(@Nullable double[] elements, @NotNull M destination, @NotNull Transformer<Double, K> keySelector, @NotNull Transformer<Double, V> valueTransform) {
        DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
            @NotNull
            @Override
            public List<V> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (double element : elements) {
                K key = keySelector.transform(element);
                List<V> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(valueTransform.transform(element));
            }
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, V, M extends Map<K, List<V>>> M groupByTo(@Nullable boolean[] elements, @NotNull M destination, @NotNull Transformer<Boolean, K> keySelector, @NotNull Transformer<Boolean, V> valueTransform) {
        DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
            @NotNull
            @Override
            public List<V> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (boolean element : elements) {
                K key = keySelector.transform(element);
                List<V> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(valueTransform.transform(element));
            }
        }
        return destination;
    }

    /**
     * Groups values returned by the [valueTransform] function applied to each element of the original array
     * by the key returned by the given [keySelector] function applied to the element
     * and puts to the [destination] map each group key associated with a list of corresponding values.
     *
     * @return The [destination] map.
     */
    @NotNull
    public static <K, V, M extends Map<K, List<V>>> M groupByTo(@Nullable char[] elements, @NotNull M destination, @NotNull Transformer<Character, K> keySelector, @NotNull Transformer<Character, V> valueTransform) {
        DefaultValue<List<V>> defaultValue = new DefaultValue<List<V>>() {
            @NotNull
            @Override
            public List<V> get() {
                return new ArrayList<>();
            }
        };
        if (elements != null) {
            for (char element : elements) {
                K key = keySelector.transform(element);
                List<V> list = Mapx.getOrPut(destination, key, defaultValue);
                list.add(valueTransform.transform(element));
            }
        }
        return destination;
    }


    /* ******************************************* count ******************************************* */


    /**
     * Returns the number of elements in this array.
     */
    public static <T> int count(@Nullable T[] elements) {
        return elements != null ? elements.length : 0;
    }

    /**
     * Returns the number of elements in this array.
     */
    public static int count(@Nullable byte[] elements) {
        return elements != null ? elements.length : 0;
    }

    /**
     * Returns the number of elements in this array.
     */
    public static int count(@Nullable short[] elements) {
        return elements != null ? elements.length : 0;
    }

    /**
     * Returns the number of elements in this array.
     */
    public static int count(@Nullable int[] elements) {
        return elements != null ? elements.length : 0;
    }

    /**
     * Returns the number of elements in this array.
     */
    public static int count(@Nullable long[] elements) {
        return elements != null ? elements.length : 0;
    }

    /**
     * Returns the number of elements in this array.
     */
    public static int count(@Nullable float[] elements) {
        return elements != null ? elements.length : 0;
    }

    /**
     * Returns the number of elements in this array.
     */
    public static int count(@Nullable double[] elements) {
        return elements != null ? elements.length : 0;
    }

    /**
     * Returns the number of elements in this array.
     */
    public static int count(@Nullable boolean[] elements) {
        return elements != null ? elements.length : 0;
    }

    /**
     * Returns the number of elements in this array.
     */
    public static int count(@Nullable char[] elements) {
        return elements != null ? elements.length : 0;
    }

    /**
     * Returns the number of elements matching the given [predicate].
     */
    public static <T> int count(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        if (elements == null) return 0;
        int count = 0;
        for (T element : elements) {
            if (predicate.accept(element)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of elements matching the given [predicate].
     */
    public static int count(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        if (elements == null) return 0;
        int count = 0;
        for (byte element : elements) {
            if (predicate.accept(element)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of elements matching the given [predicate].
     */
    public static int count(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        if (elements == null) return 0;
        int count = 0;
        for (short element : elements) {
            if (predicate.accept(element)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of elements matching the given [predicate].
     */
    public static int count(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        if (elements == null) return 0;
        int count = 0;
        for (int element : elements) {
            if (predicate.accept(element)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of elements matching the given [predicate].
     */
    public static int count(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        if (elements == null) return 0;
        int count = 0;
        for (long element : elements) {
            if (predicate.accept(element)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of elements matching the given [predicate].
     */
    public static int count(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        if (elements == null) return 0;
        int count = 0;
        for (float element : elements) {
            if (predicate.accept(element)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of elements matching the given [predicate].
     */
    public static int count(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        if (elements == null) return 0;
        int count = 0;
        for (double element : elements) {
            if (predicate.accept(element)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of elements matching the given [predicate].
     */
    public static int count(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        if (elements == null) return 0;
        int count = 0;
        for (boolean element : elements) {
            if (predicate.accept(element)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of elements matching the given [predicate].
     */
    public static int count(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        if (elements == null) return 0;
        int count = 0;
        for (char element : elements) {
            if (predicate.accept(element)) {
                count++;
            }
        }
        return count;
    }


    /* ******************************************* max ******************************************* */


    /**
     * Returns the largest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Double maxOrNull(@Nullable Double[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        Double max = elements[0];
        if (max.isNaN()) return max;
        for (int i = 1, size = elements.length; i < size; i++) {
            Double e = elements[i];
            if (e.isNaN()) return e;
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Float maxOrNull(@Nullable Float[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        Float max = elements[0];
        if (max.isNaN()) return max;
        for (int i = 1, size = elements.length; i < size; i++) {
            Float e = elements[i];
            if (e.isNaN()) return e;
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     */
    @Nullable
    public static <T extends Comparable<T>> T maxOrNull(@Nullable T[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        T max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            T e = elements[i];
            if (max.compareTo(e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     */
    @Nullable
    public static Byte maxOrNull(@Nullable byte[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        byte max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            byte e = elements[i];
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     */
    @Nullable
    public static Short maxOrNull(@Nullable short[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        short max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            short e = elements[i];
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     */
    @Nullable
    public static Integer maxOrNull(@Nullable int[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        int max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            int e = elements[i];
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     */
    @Nullable
    public static Long maxOrNull(@Nullable long[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        long max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            long e = elements[i];
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Float maxOrNull(@Nullable float[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        Float max = elements[0];
        if (max.isNaN()) return max;
        for (int i = 1, size = elements.length; i < size; i++) {
            Float e = elements[i];
            if (e.isNaN()) return e;
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Double maxOrNull(@Nullable double[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        Double max = elements[0];
        if (max.isNaN()) return max;
        for (int i = 1, size = elements.length; i < size; i++) {
            Double e = elements[i];
            if (e.isNaN()) return e;
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the largest element or `null` if there are no elements.
     */
    @Nullable
    public static Character maxOrNull(@Nullable char[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        char max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            char e = elements[i];
            if (max < e) max = e;
        }
        return max;
    }

    /**
     * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <T, R extends Comparable<R>> T maxByOrNull(@Nullable T[] elements, @NotNull Transformer<T, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        T maxElem = elements[0];
        R maxValue = transformer.transform(maxElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            T e = elements[i];
            R v = transformer.transform(e);
            if (maxValue.compareTo(v) < 0) {
                maxElem = e;
                maxValue = v;
            }
        }
        return maxElem;
    }

    /**
     * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Byte maxByOrNull(@Nullable byte[] elements, @NotNull Transformer<Byte, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        byte maxElem = elements[0];
        R maxValue = transformer.transform(maxElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            byte e = elements[i];
            R v = transformer.transform(e);
            if (maxValue.compareTo(v) < 0) {
                maxElem = e;
                maxValue = v;
            }
        }
        return maxElem;
    }

    /**
     * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Short maxByOrNull(@Nullable short[] elements, @NotNull Transformer<Short, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        short maxElem = elements[0];
        R maxValue = transformer.transform(maxElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            short e = elements[i];
            R v = transformer.transform(e);
            if (maxValue.compareTo(v) < 0) {
                maxElem = e;
                maxValue = v;
            }
        }
        return maxElem;
    }

    /**
     * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Integer maxByOrNull(@Nullable int[] elements, @NotNull Transformer<Integer, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        int maxElem = elements[0];
        R maxValue = transformer.transform(maxElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            int e = elements[i];
            R v = transformer.transform(e);
            if (maxValue.compareTo(v) < 0) {
                maxElem = e;
                maxValue = v;
            }
        }
        return maxElem;
    }

    /**
     * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Long maxByOrNull(@Nullable long[] elements, @NotNull Transformer<Long, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        long maxElem = elements[0];
        R maxValue = transformer.transform(maxElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            long e = elements[i];
            R v = transformer.transform(e);
            if (maxValue.compareTo(v) < 0) {
                maxElem = e;
                maxValue = v;
            }
        }
        return maxElem;
    }

    /**
     * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Float maxByOrNull(@Nullable float[] elements, @NotNull Transformer<Float, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        float maxElem = elements[0];
        R maxValue = transformer.transform(maxElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            float e = elements[i];
            R v = transformer.transform(e);
            if (maxValue.compareTo(v) < 0) {
                maxElem = e;
                maxValue = v;
            }
        }
        return maxElem;
    }

    /**
     * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Double maxByOrNull(@Nullable double[] elements, @NotNull Transformer<Double, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        double maxElem = elements[0];
        R maxValue = transformer.transform(maxElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            double e = elements[i];
            R v = transformer.transform(e);
            if (maxValue.compareTo(v) < 0) {
                maxElem = e;
                maxValue = v;
            }
        }
        return maxElem;
    }

    /**
     * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Boolean maxByOrNull(@Nullable boolean[] elements, @NotNull Transformer<Boolean, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        boolean maxElem = elements[0];
        R maxValue = transformer.transform(maxElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            boolean e = elements[i];
            R v = transformer.transform(e);
            if (maxValue.compareTo(v) < 0) {
                maxElem = e;
                maxValue = v;
            }
        }
        return maxElem;
    }

    /**
     * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Character maxByOrNull(@Nullable char[] elements, @NotNull Transformer<Character, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        char maxElem = elements[0];
        R maxValue = transformer.transform(maxElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            char e = elements[i];
            R v = transformer.transform(e);
            if (maxValue.compareTo(v) < 0) {
                maxElem = e;
                maxValue = v;
            }
        }
        return maxElem;
    }

    /**
     * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static <T> T maxWithOrNull(@Nullable T[] elements, @NotNull Comparator<T> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        T max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            T e = elements[i];
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Byte maxWithOrNull(@Nullable byte[] elements, @NotNull Comparator<Byte> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        byte max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            byte e = elements[i];
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Short maxWithOrNull(@Nullable short[] elements, @NotNull Comparator<Short> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        short max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            short e = elements[i];
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Integer maxWithOrNull(@Nullable int[] elements, @NotNull Comparator<Integer> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        int max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            int e = elements[i];
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Long maxWithOrNull(@Nullable long[] elements, @NotNull Comparator<Long> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        long max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            long e = elements[i];
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Float maxWithOrNull(@Nullable float[] elements, @NotNull Comparator<Float> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        float max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            float e = elements[i];
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Double maxWithOrNull(@Nullable double[] elements, @NotNull Comparator<Double> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        double max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            double e = elements[i];
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Boolean maxWithOrNull(@Nullable boolean[] elements, @NotNull Comparator<Boolean> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        boolean max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            boolean e = elements[i];
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }

    /**
     * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Character maxWithOrNull(@Nullable char[] elements, @NotNull Comparator<Character> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        char max = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            char e = elements[i];
            if (comparator.compare(max, e) < 0) max = e;
        }
        return max;
    }

    // todo add maxOf method


    /* ******************************************* min ******************************************* */


    /**
     * Returns the smallest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Double minOrNull(@Nullable Double[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        Double min = elements[0];
        if (min.isNaN()) return min;
        for (int i = 1, size = elements.length; i < size; i++) {
            Double e = elements[i];
            if (e.isNaN()) return e;
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Float minOrNull(@Nullable Float[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        Float min = elements[0];
        if (min.isNaN()) return min;
        for (int i = 1, size = elements.length; i < size; i++) {
            Float e = elements[i];
            if (e.isNaN()) return e;
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     */
    @Nullable
    public static <T extends Comparable<T>> T minOrNull(@Nullable T[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        T min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            T e = elements[i];
            if (min.compareTo(e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     */
    @Nullable
    public static Byte minOrNull(@Nullable byte[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        byte min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            byte e = elements[i];
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     */
    @Nullable
    public static Short minOrNull(@Nullable short[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        short min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            short e = elements[i];
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     */
    @Nullable
    public static Integer minOrNull(@Nullable int[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        int min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            int e = elements[i];
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     */
    @Nullable
    public static Long minOrNull(@Nullable long[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        long min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            long e = elements[i];
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Float minOrNull(@Nullable float[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        Float min = elements[0];
        if (min.isNaN()) return min;
        for (int i = 1, size = elements.length; i < size; i++) {
            Float e = elements[i];
            if (e.isNaN()) return e;
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     * <p>
     * If any of elements is `NaN` returns `NaN`.
     */
    @Nullable
    public static Double minOrNull(@Nullable double[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        Double min = elements[0];
        if (min.isNaN()) return min;
        for (int i = 1, size = elements.length; i < size; i++) {
            Double e = elements[i];
            if (e.isNaN()) return e;
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the smallest element or `null` if there are no elements.
     */
    @Nullable
    public static Character minOrNull(@Nullable char[] elements) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        char min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            char e = elements[i];
            if (min > e) min = e;
        }
        return min;
    }

    /**
     * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <T, R extends Comparable<R>> T minByOrNull(@Nullable T[] elements, @NotNull Transformer<T, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        T minElem = elements[0];
        R minValue = transformer.transform(minElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            T e = elements[i];
            R v = transformer.transform(e);
            if (minValue.compareTo(v) > 0) {
                minElem = e;
                minValue = v;
            }
        }
        return minElem;
    }

    /**
     * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Byte minByOrNull(@Nullable byte[] elements, @NotNull Transformer<Byte, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        byte minElem = elements[0];
        R minValue = transformer.transform(minElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            byte e = elements[i];
            R v = transformer.transform(e);
            if (minValue.compareTo(v) > 0) {
                minElem = e;
                minValue = v;
            }
        }
        return minElem;
    }

    /**
     * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Short minByOrNull(@Nullable short[] elements, @NotNull Transformer<Short, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        short minElem = elements[0];
        R minValue = transformer.transform(minElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            short e = elements[i];
            R v = transformer.transform(e);
            if (minValue.compareTo(v) > 0) {
                minElem = e;
                minValue = v;
            }
        }
        return minElem;
    }

    /**
     * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Integer minByOrNull(@Nullable int[] elements, @NotNull Transformer<Integer, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        int minElem = elements[0];
        R minValue = transformer.transform(minElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            int e = elements[i];
            R v = transformer.transform(e);
            if (minValue.compareTo(v) > 0) {
                minElem = e;
                minValue = v;
            }
        }
        return minElem;
    }

    /**
     * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Long minByOrNull(@Nullable long[] elements, @NotNull Transformer<Long, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        long minElem = elements[0];
        R minValue = transformer.transform(minElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            long e = elements[i];
            R v = transformer.transform(e);
            if (minValue.compareTo(v) > 0) {
                minElem = e;
                minValue = v;
            }
        }
        return minElem;
    }

    /**
     * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Float minByOrNull(@Nullable float[] elements, @NotNull Transformer<Float, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        float minElem = elements[0];
        R minValue = transformer.transform(minElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            float e = elements[i];
            R v = transformer.transform(e);
            if (minValue.compareTo(v) > 0) {
                minElem = e;
                minValue = v;
            }
        }
        return minElem;
    }

    /**
     * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Double minByOrNull(@Nullable double[] elements, @NotNull Transformer<Double, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        double minElem = elements[0];
        R minValue = transformer.transform(minElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            double e = elements[i];
            R v = transformer.transform(e);
            if (minValue.compareTo(v) > 0) {
                minElem = e;
                minValue = v;
            }
        }
        return minElem;
    }

    /**
     * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Boolean minByOrNull(@Nullable boolean[] elements, @NotNull Transformer<Boolean, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        boolean minElem = elements[0];
        R minValue = transformer.transform(minElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            boolean e = elements[i];
            R v = transformer.transform(e);
            if (minValue.compareTo(v) > 0) {
                minElem = e;
                minValue = v;
            }
        }
        return minElem;
    }

    /**
     * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
     */
    @Nullable
    public static <R extends Comparable<R>> Character minByOrNull(@Nullable char[] elements, @NotNull Transformer<Character, R> transformer) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        char minElem = elements[0];
        R minValue = transformer.transform(minElem);
        for (int i = 1, size = elements.length; i < size; i++) {
            char e = elements[i];
            R v = transformer.transform(e);
            if (minValue.compareTo(v) > 0) {
                minElem = e;
                minValue = v;
            }
        }
        return minElem;
    }

    /**
     * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static <T> T minWithOrNull(@Nullable T[] elements, @NotNull Comparator<T> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        T min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            T e = elements[i];
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Byte minWithOrNull(@Nullable byte[] elements, @NotNull Comparator<Byte> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        byte min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            byte e = elements[i];
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Short minWithOrNull(@Nullable short[] elements, @NotNull Comparator<Short> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        short min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            short e = elements[i];
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Integer minWithOrNull(@Nullable int[] elements, @NotNull Comparator<Integer> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        int min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            int e = elements[i];
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Long minWithOrNull(@Nullable long[] elements, @NotNull Comparator<Long> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        long min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            long e = elements[i];
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Float minWithOrNull(@Nullable float[] elements, @NotNull Comparator<Float> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        float min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            float e = elements[i];
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Double minWithOrNull(@Nullable double[] elements, @NotNull Comparator<Double> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        double min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            double e = elements[i];
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Boolean minWithOrNull(@Nullable boolean[] elements, @NotNull Comparator<Boolean> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        boolean min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            boolean e = elements[i];
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }

    /**
     * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
     */
    @Nullable
    public static Character minWithOrNull(@Nullable char[] elements, @NotNull Comparator<Character> comparator) {
        if (Arrayx.isNullOrEmpty(elements)) return null;
        char min = elements[0];
        for (int i = 1, size = elements.length; i < size; i++) {
            char e = elements[i];
            if (comparator.compare(min, e) > 0) min = e;
        }
        return min;
    }

    // todo add minOf method


    /* ******************************************* join ******************************************* */

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T, A extends Appendable> A joinTo(@Nullable T[] elements, @NotNull A buffer, @Nullable CharSequence separator,
                                                     @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                                     @Nullable CharSequence truncated, @Nullable Transformer<T, CharSequence> transform) throws IOException {
        if (separator == null) separator = ", ";
        if (prefix == null) prefix = "";
        if (postfix == null) postfix = "";
        if (truncated == null) truncated = "";
        buffer.append(prefix);
        int count = 0;
        if (elements != null) {
            for (T element : elements) {
                if (++count > 1) buffer.append(separator);
                if (limit < 0 || count <= limit) {
                    Collectionx.appendElement(buffer, element, transform);
                } else {
                    break;
                }
            }
        }
        if (limit >= 0 && count > limit) buffer.append(truncated);
        buffer.append(postfix);
        return buffer;
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <A extends Appendable> A joinTo(@Nullable byte[] elements, @NotNull A buffer, @Nullable CharSequence separator,
                                                  @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                                  @Nullable CharSequence truncated, @Nullable Transformer<Byte, CharSequence> transform) throws IOException {
        if (separator == null) separator = ", ";
        if (prefix == null) prefix = "";
        if (postfix == null) postfix = "";
        if (truncated == null) truncated = "";
        buffer.append(prefix);
        int count = 0;
        if (elements != null) {
            for (byte element : elements) {
                if (++count > 1) buffer.append(separator);
                if (limit < 0 || count <= limit) {
                    if (transform != null)
                        buffer.append(transform.transform(element));
                    else
                        buffer.append(String.valueOf(element));
                } else {
                    break;
                }
            }
        }
        if (limit >= 0 && count > limit) buffer.append(truncated);
        buffer.append(postfix);
        return buffer;
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <A extends Appendable> A joinTo(@Nullable short[] elements, @NotNull A buffer, @Nullable CharSequence separator,
                                                  @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                                  @Nullable CharSequence truncated, @Nullable Transformer<Short, CharSequence> transform) throws IOException {
        if (separator == null) separator = ", ";
        if (prefix == null) prefix = "";
        if (postfix == null) postfix = "";
        if (truncated == null) truncated = "";
        buffer.append(prefix);
        int count = 0;
        if (elements != null) {
            for (short element : elements) {
                if (++count > 1) buffer.append(separator);
                if (limit < 0 || count <= limit) {
                    if (transform != null)
                        buffer.append(transform.transform(element));
                    else
                        buffer.append(String.valueOf(element));
                } else {
                    break;
                }
            }
        }
        if (limit >= 0 && count > limit) buffer.append(truncated);
        buffer.append(postfix);
        return buffer;
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <A extends Appendable> A joinTo(@Nullable int[] elements, @NotNull A buffer, @Nullable CharSequence separator,
                                                  @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                                  @Nullable CharSequence truncated, @Nullable Transformer<Integer, CharSequence> transform) throws IOException {
        if (separator == null) separator = ", ";
        if (prefix == null) prefix = "";
        if (postfix == null) postfix = "";
        if (truncated == null) truncated = "";
        buffer.append(prefix);
        int count = 0;
        if (elements != null) {
            for (int element : elements) {
                if (++count > 1) buffer.append(separator);
                if (limit < 0 || count <= limit) {
                    if (transform != null)
                        buffer.append(transform.transform(element));
                    else
                        buffer.append(String.valueOf(element));
                } else {
                    break;
                }
            }
        }
        if (limit >= 0 && count > limit) buffer.append(truncated);
        buffer.append(postfix);
        return buffer;
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <A extends Appendable> A joinTo(@Nullable long[] elements, @NotNull A buffer, @Nullable CharSequence separator,
                                                  @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                                  @Nullable CharSequence truncated, @Nullable Transformer<Long, CharSequence> transform) throws IOException {
        if (separator == null) separator = ", ";
        if (prefix == null) prefix = "";
        if (postfix == null) postfix = "";
        if (truncated == null) truncated = "";
        buffer.append(prefix);
        int count = 0;
        if (elements != null) {
            for (long element : elements) {
                if (++count > 1) buffer.append(separator);
                if (limit < 0 || count <= limit) {
                    if (transform != null)
                        buffer.append(transform.transform(element));
                    else
                        buffer.append(String.valueOf(element));
                } else {
                    break;
                }
            }
        }
        if (limit >= 0 && count > limit) buffer.append(truncated);
        buffer.append(postfix);
        return buffer;
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <A extends Appendable> A joinTo(@Nullable float[] elements, @NotNull A buffer, @Nullable CharSequence separator,
                                                  @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                                  @Nullable CharSequence truncated, @Nullable Transformer<Float, CharSequence> transform) throws IOException {
        if (separator == null) separator = ", ";
        if (prefix == null) prefix = "";
        if (postfix == null) postfix = "";
        if (truncated == null) truncated = "";
        buffer.append(prefix);
        int count = 0;
        if (elements != null) {
            for (float element : elements) {
                if (++count > 1) buffer.append(separator);
                if (limit < 0 || count <= limit) {
                    if (transform != null)
                        buffer.append(transform.transform(element));
                    else
                        buffer.append(String.valueOf(element));
                } else {
                    break;
                }
            }
        }
        if (limit >= 0 && count > limit) buffer.append(truncated);
        buffer.append(postfix);
        return buffer;
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <A extends Appendable> A joinTo(@Nullable double[] elements, @NotNull A buffer, @Nullable CharSequence separator,
                                                  @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                                  @Nullable CharSequence truncated, @Nullable Transformer<Double, CharSequence> transform) throws IOException {
        if (separator == null) separator = ", ";
        if (prefix == null) prefix = "";
        if (postfix == null) postfix = "";
        if (truncated == null) truncated = "";
        buffer.append(prefix);
        int count = 0;
        if (elements != null) {
            for (double element : elements) {
                if (++count > 1) buffer.append(separator);
                if (limit < 0 || count <= limit) {
                    if (transform != null)
                        buffer.append(transform.transform(element));
                    else
                        buffer.append(String.valueOf(element));
                } else {
                    break;
                }
            }
        }
        if (limit >= 0 && count > limit) buffer.append(truncated);
        buffer.append(postfix);
        return buffer;
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <A extends Appendable> A joinTo(@Nullable boolean[] elements, @NotNull A buffer, @Nullable CharSequence separator,
                                                  @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                                  @Nullable CharSequence truncated, @Nullable Transformer<Boolean, CharSequence> transform) throws IOException {
        if (separator == null) separator = ", ";
        if (prefix == null) prefix = "";
        if (postfix == null) postfix = "";
        if (truncated == null) truncated = "";
        buffer.append(prefix);
        int count = 0;
        if (elements != null) {
            for (boolean element : elements) {
                if (++count > 1) buffer.append(separator);
                if (limit < 0 || count <= limit) {
                    if (transform != null)
                        buffer.append(transform.transform(element));
                    else
                        buffer.append(String.valueOf(element));
                } else {
                    break;
                }
            }
        }
        if (limit >= 0 && count > limit) buffer.append(truncated);
        buffer.append(postfix);
        return buffer;
    }

    /**
     * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <A extends Appendable> A joinTo(@Nullable char[] elements, @NotNull A buffer, @Nullable CharSequence separator,
                                                  @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                                  @Nullable CharSequence truncated, @Nullable Transformer<Character, CharSequence> transform) throws IOException {
        if (separator == null) separator = ", ";
        if (prefix == null) prefix = "";
        if (postfix == null) postfix = "";
        if (truncated == null) truncated = "";
        buffer.append(prefix);
        int count = 0;
        if (elements != null) {
            for (char element : elements) {
                if (++count > 1) buffer.append(separator);
                if (limit < 0 || count <= limit) {
                    if (transform != null)
                        buffer.append(transform.transform(element));
                    else
                        buffer.append(element);
                } else {
                    break;
                }
            }
        }
        if (limit >= 0 && count > limit) buffer.append(truncated);
        buffer.append(postfix);
        return buffer;
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T> String joinToString(@Nullable T[] elements, @Nullable CharSequence separator,
                                          @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                          @Nullable CharSequence truncated, @Nullable Transformer<T, CharSequence> transform) {
        try {
            return joinTo(elements, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T> String joinToString(@Nullable T[] elements, @Nullable CharSequence separator, @Nullable Transformer<T, CharSequence> transform) {
        return joinToString(elements, separator, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T> String joinToString(@Nullable T[] elements, @Nullable CharSequence separator) {
        return joinToString(elements, separator, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T> String joinToString(@Nullable T[] elements, @Nullable Transformer<T, CharSequence> transform) {
        return joinToString(elements, null, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static <T> String joinToString(@Nullable T[] elements) {
        return joinToString(elements, null, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(byte[] elements, @Nullable CharSequence separator,
                                      @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                      @Nullable CharSequence truncated, @Nullable Transformer<Byte, CharSequence> transform) {
        try {
            return joinTo(elements, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable byte[] elements, @Nullable CharSequence separator, @Nullable Transformer<Byte, CharSequence> transform) {
        return joinToString(elements, separator, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable byte[] elements, @Nullable CharSequence separator) {
        return joinToString(elements, separator, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable byte[] elements, @Nullable Transformer<Byte, CharSequence> transform) {
        return joinToString(elements, null, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable byte[] elements) {
        return joinToString(elements, null, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(short[] elements, @Nullable CharSequence separator,
                                      @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                      @Nullable CharSequence truncated, @Nullable Transformer<Short, CharSequence> transform) {
        try {
            return joinTo(elements, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable short[] elements, @Nullable CharSequence separator, @Nullable Transformer<Short, CharSequence> transform) {
        return joinToString(elements, separator, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable short[] elements, @Nullable CharSequence separator) {
        return joinToString(elements, separator, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable short[] elements, @Nullable Transformer<Short, CharSequence> transform) {
        return joinToString(elements, null, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable short[] elements) {
        return joinToString(elements, null, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(int[] elements, @Nullable CharSequence separator,
                                      @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                      @Nullable CharSequence truncated, @Nullable Transformer<Integer, CharSequence> transform) {
        try {
            return joinTo(elements, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable int[] elements, @Nullable CharSequence separator, @Nullable Transformer<Integer, CharSequence> transform) {
        return joinToString(elements, separator, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable int[] elements, @Nullable CharSequence separator) {
        return joinToString(elements, separator, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable int[] elements, @Nullable Transformer<Integer, CharSequence> transform) {
        return joinToString(elements, null, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable int[] elements) {
        return joinToString(elements, null, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(long[] elements, @Nullable CharSequence separator,
                                      @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                      @Nullable CharSequence truncated, @Nullable Transformer<Long, CharSequence> transform) {
        try {
            return joinTo(elements, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable long[] elements, @Nullable CharSequence separator, @Nullable Transformer<Long, CharSequence> transform) {
        return joinToString(elements, separator, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable long[] elements, @Nullable CharSequence separator) {
        return joinToString(elements, separator, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable long[] elements, @Nullable Transformer<Long, CharSequence> transform) {
        return joinToString(elements, null, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable long[] elements) {
        return joinToString(elements, null, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(float[] elements, @Nullable CharSequence separator,
                                      @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                      @Nullable CharSequence truncated, @Nullable Transformer<Float, CharSequence> transform) {
        try {
            return joinTo(elements, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable float[] elements, @Nullable CharSequence separator, @Nullable Transformer<Float, CharSequence> transform) {
        return joinToString(elements, separator, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable float[] elements, @Nullable CharSequence separator) {
        return joinToString(elements, separator, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable float[] elements, @Nullable Transformer<Float, CharSequence> transform) {
        return joinToString(elements, null, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable float[] elements) {
        return joinToString(elements, null, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(double[] elements, @Nullable CharSequence separator,
                                      @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                      @Nullable CharSequence truncated, @Nullable Transformer<Double, CharSequence> transform) {
        try {
            return joinTo(elements, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable double[] elements, @Nullable CharSequence separator, @Nullable Transformer<Double, CharSequence> transform) {
        return joinToString(elements, separator, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable double[] elements, @Nullable CharSequence separator) {
        return joinToString(elements, separator, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable double[] elements, @Nullable Transformer<Double, CharSequence> transform) {
        return joinToString(elements, null, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable double[] elements) {
        return joinToString(elements, null, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(boolean[] elements, @Nullable CharSequence separator,
                                      @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                      @Nullable CharSequence truncated, @Nullable Transformer<Boolean, CharSequence> transform) {
        try {
            return joinTo(elements, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable boolean[] elements, @Nullable CharSequence separator, @Nullable Transformer<Boolean, CharSequence> transform) {
        return joinToString(elements, separator, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable boolean[] elements, @Nullable CharSequence separator) {
        return joinToString(elements, separator, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable boolean[] elements, @Nullable Transformer<Boolean, CharSequence> transform) {
        return joinToString(elements, null, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable boolean[] elements) {
        return joinToString(elements, null, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(char[] elements, @Nullable CharSequence separator,
                                      @Nullable CharSequence prefix, @Nullable CharSequence postfix, int limit,
                                      @Nullable CharSequence truncated, @Nullable Transformer<Character, CharSequence> transform) {
        try {
            return joinTo(elements, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable char[] elements, @Nullable CharSequence separator, @Nullable Transformer<Character, CharSequence> transform) {
        return joinToString(elements, separator, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable char[] elements, @Nullable CharSequence separator) {
        return joinToString(elements, separator, null, null, -1, null, null);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable char[] elements, @Nullable Transformer<Character, CharSequence> transform) {
        return joinToString(elements, null, null, null, -1, null, transform);
    }

    /**
     * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
     * <p>
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     */
    @NotNull
    public static String joinToString(@Nullable char[] elements) {
        return joinToString(elements, null, null, null, -1, null, null);
    }


    /* ******************************************* sum ******************************************* */


    /**
     * Returns the sum of all elements in the array.
     */
    public static int sum(@Nullable Byte[] elements) {
        int sum = 0;
        if (elements != null) {
            for (byte element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the array.
     */
    public static int sum(@Nullable Short[] elements) {
        int sum = 0;
        if (elements != null) {
            for (short element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the array.
     */
    public static int sum(@Nullable Integer[] elements) {
        int sum = 0;
        if (elements != null) {
            for (int element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the array.
     */
    public static long sum(@Nullable Long[] elements) {
        long sum = 0;
        if (elements != null) {
            for (long element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the array.
     */
    public static float sum(@Nullable Float[] elements) {
        float sum = 0;
        if (elements != null) {
            for (float element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the array.
     */
    public static double sum(@Nullable Double[] elements) {
        double sum = 0;
        if (elements != null) {
            for (double element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the array.
     */
    public static int sum(@Nullable byte[] elements) {
        int sum = 0;
        if (elements != null) {
            for (byte element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the array.
     */
    public static int sum(@Nullable short[] elements) {
        int sum = 0;
        if (elements != null) {
            for (short element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the array.
     */
    public static int sum(@Nullable int[] elements) {
        int sum = 0;
        if (elements != null) {
            for (int element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the array.
     */
    public static long sum(@Nullable long[] elements) {
        long sum = 0;
        if (elements != null) {
            for (long element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the array.
     */
    public static float sum(@Nullable float[] elements) {
        float sum = 0;
        if (elements != null) {
            for (float element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all elements in the array.
     */
    public static double sum(@Nullable double[] elements) {
        double sum = 0;
        if (elements != null) {
            for (double element : elements) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static <T> int sumBy(@Nullable T[] elements, @NotNull Transformer<T, Integer> transformer) {
        int sum = 0;
        if (elements != null) {
            for (T element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static int sumBy(@Nullable byte[] elements, @NotNull Transformer<Byte, Integer> transformer) {
        int sum = 0;
        if (elements != null) {
            for (byte element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static int sumBy(@Nullable short[] elements, @NotNull Transformer<Short, Integer> transformer) {
        int sum = 0;
        if (elements != null) {
            for (short element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static int sumBy(@Nullable int[] elements, @NotNull Transformer<Integer, Integer> transformer) {
        int sum = 0;
        if (elements != null) {
            for (int element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static int sumBy(@Nullable long[] elements, @NotNull Transformer<Long, Integer> transformer) {
        int sum = 0;
        if (elements != null) {
            for (long element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static int sumBy(@Nullable float[] elements, @NotNull Transformer<Float, Integer> transformer) {
        int sum = 0;
        if (elements != null) {
            for (float element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static int sumBy(@Nullable double[] elements, @NotNull Transformer<Double, Integer> transformer) {
        int sum = 0;
        if (elements != null) {
            for (double element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static int sumBy(@Nullable boolean[] elements, @NotNull Transformer<Boolean, Integer> transformer) {
        int sum = 0;
        if (elements != null) {
            for (boolean element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static int sumBy(@Nullable char[] elements, @NotNull Transformer<Character, Integer> transformer) {
        int sum = 0;
        if (elements != null) {
            for (char element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static <T> double sumByDouble(@Nullable T[] elements, @NotNull Transformer<T, Double> transformer) {
        double sum = 0.0;
        if (elements != null) {
            for (T element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static double sumByDouble(@Nullable byte[] elements, @NotNull Transformer<Byte, Double> transformer) {
        double sum = 0.0;
        if (elements != null) {
            for (byte element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static double sumByDouble(@Nullable short[] elements, @NotNull Transformer<Short, Double> transformer) {
        double sum = 0.0;
        if (elements != null) {
            for (short element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static double sumByDouble(@Nullable int[] elements, @NotNull Transformer<Integer, Double> transformer) {
        double sum = 0.0;
        if (elements != null) {
            for (int element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static double sumByDouble(@Nullable long[] elements, @NotNull Transformer<Long, Double> transformer) {
        double sum = 0.0;
        if (elements != null) {
            for (long element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static double sumByDouble(@Nullable float[] elements, @NotNull Transformer<Float, Double> transformer) {
        double sum = 0.0;
        if (elements != null) {
            for (float element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static double sumByDouble(@Nullable double[] elements, @NotNull Transformer<Double, Double> transformer) {
        double sum = 0.0;
        if (elements != null) {
            for (double element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static double sumByDouble(@Nullable boolean[] elements, @NotNull Transformer<Boolean, Double> transformer) {
        double sum = 0.0;
        if (elements != null) {
            for (boolean element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }

    /**
     * Returns the sum of all values produced by [selector] function applied to each element in the array.
     */
    public static double sumByDouble(@Nullable char[] elements, @NotNull Transformer<Character, Double> transformer) {
        double sum = 0.0;
        if (elements != null) {
            for (char element : elements) {
                sum += transformer.transform(element);
            }
        }
        return sum;
    }


    /* ******************************************* plus ******************************************* */


    /**
     * Returns an array containing all elements of the original array and then the given [element].
     */
    @NotNull
    public static <T> T[] plus(@NotNull T[] elements, @Nullable T element) {
        if (element == null) {
            //noinspection unchecked
            return Arrayx2.copyOf(elements);
        }
        int index = elements.length;
        T[] result = Arrayx2.copyOf(elements, index + 1);
        result[index] = element;
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then the given [element].
     */
    @NotNull
    public static byte[] plus(@Nullable byte[] elements, byte element) {
        if (elements == null) return byteArrayOf(element);
        int index = elements.length;
        byte[] result = Arrayx2.copyOf(elements, index + 1);
        result[index] = element;
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then the given [element].
     */
    @NotNull
    public static short[] plus(@Nullable short[] elements, short element) {
        if (elements == null) return shortArrayOf(element);
        int index = elements.length;
        short[] result = Arrayx2.copyOf(elements, index + 1);
        result[index] = element;
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then the given [element].
     */
    @NotNull
    public static int[] plus(@Nullable int[] elements, int element) {
        if (elements == null) return intArrayOf(element);
        int index = elements.length;
        int[] result = Arrayx2.copyOf(elements, index + 1);
        result[index] = element;
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then the given [element].
     */
    @NotNull
    public static long[] plus(@Nullable long[] elements, long element) {
        if (elements == null) return longArrayOf(element);
        int index = elements.length;
        long[] result = Arrayx2.copyOf(elements, index + 1);
        result[index] = element;
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then the given [element].
     */
    @NotNull
    public static float[] plus(@Nullable float[] elements, float element) {
        if (elements == null) return floatArrayOf(element);
        int index = elements.length;
        float[] result = Arrayx2.copyOf(elements, index + 1);
        result[index] = element;
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then the given [element].
     */
    @NotNull
    public static double[] plus(@Nullable double[] elements, double element) {
        if (elements == null) return doubleArrayOf(element);
        int index = elements.length;
        double[] result = Arrayx2.copyOf(elements, index + 1);
        result[index] = element;
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then the given [element].
     */
    @NotNull
    public static boolean[] plus(@Nullable boolean[] elements, boolean element) {
        if (elements == null) return booleanArrayOf(element);
        int index = elements.length;
        boolean[] result = Arrayx2.copyOf(elements, index + 1);
        result[index] = element;
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then the given [element].
     */
    @NotNull
    public static char[] plus(@Nullable char[] elements, char element) {
        if (elements == null) return charArrayOf(element);
        int index = elements.length;
        char[] result = Arrayx2.copyOf(elements, index + 1);
        result[index] = element;
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] collection.
     */
    @NotNull
    public static <T> T[] plus(@NotNull T[] selfElements, @NotNull Collection<T> elements) {
        if (Collectionx.isEmpty(elements)) {
            //noinspection unchecked
            return Arrayx2.copyOf(selfElements);
        }
        int index = selfElements.length;
        T[] result = Arrayx2.copyOf(selfElements, index + elements.size());
        for (T element : elements) {
            result[index++] = element;
        }
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] collection.
     */
    @NotNull
    public static byte[] plus(@Nullable byte[] selfElements, @Nullable Collection<Byte> elements) {
        if (Collectionx.isNullOrEmpty(elements))
            return selfElements != null ? Arrayx2.copyOf(selfElements) : new byte[0];
        if (selfElements == null) return Collectionx.toByteArray(elements);
        int index = selfElements.length;
        byte[] result = Arrayx2.copyOf(selfElements, index + elements.size());
        for (byte element : elements) {
            result[index++] = element;
        }
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] collection.
     */
    @NotNull
    public static short[] plus(@Nullable short[] selfElements, @Nullable Collection<Short> elements) {
        if (Collectionx.isNullOrEmpty(elements))
            return selfElements != null ? Arrayx2.copyOf(selfElements) : new short[0];
        if (selfElements == null) return Collectionx.toShortArray(elements);
        int index = selfElements.length;
        short[] result = Arrayx2.copyOf(selfElements, index + elements.size());
        for (short element : elements) {
            result[index++] = element;
        }
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] collection.
     */
    @NotNull
    public static int[] plus(@Nullable int[] selfElements, @Nullable Collection<Integer> elements) {
        if (Collectionx.isNullOrEmpty(elements))
            return selfElements != null ? Arrayx2.copyOf(selfElements) : new int[0];
        if (selfElements == null) return Collectionx.toIntArray(elements);
        int index = selfElements.length;
        int[] result = Arrayx2.copyOf(selfElements, index + elements.size());
        for (int element : elements) {
            result[index++] = element;
        }
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] collection.
     */
    @NotNull
    public static long[] plus(@Nullable long[] selfElements, @Nullable Collection<Long> elements) {
        if (Collectionx.isNullOrEmpty(elements))
            return selfElements != null ? Arrayx2.copyOf(selfElements) : new long[0];
        if (selfElements == null) return Collectionx.toLongArray(elements);
        int index = selfElements.length;
        long[] result = Arrayx2.copyOf(selfElements, index + elements.size());
        for (long element : elements) {
            result[index++] = element;
        }
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] collection.
     */
    @NotNull
    public static float[] plus(@Nullable float[] selfElements, @Nullable Collection<Float> elements) {
        if (Collectionx.isNullOrEmpty(elements))
            return selfElements != null ? Arrayx2.copyOf(selfElements) : new float[0];
        if (selfElements == null) return Collectionx.toFloatArray(elements);
        int index = selfElements.length;
        float[] result = Arrayx2.copyOf(selfElements, index + elements.size());
        for (float element : elements) {
            result[index++] = element;
        }
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] collection.
     */
    @NotNull
    public static double[] plus(@Nullable double[] selfElements, @Nullable Collection<Double> elements) {
        if (Collectionx.isNullOrEmpty(elements))
            return selfElements != null ? Arrayx2.copyOf(selfElements) : new double[0];
        if (selfElements == null) return Collectionx.toDoubleArray(elements);
        int index = selfElements.length;
        double[] result = Arrayx2.copyOf(selfElements, index + elements.size());
        for (double element : elements) {
            result[index++] = element;
        }
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] collection.
     */
    @NotNull
    public static boolean[] plus(@Nullable boolean[] selfElements, @Nullable Collection<Boolean> elements) {
        if (Collectionx.isNullOrEmpty(elements))
            return selfElements != null ? Arrayx2.copyOf(selfElements) : new boolean[0];
        if (selfElements == null) return Collectionx.toBooleanArray(elements);
        int index = selfElements.length;
        boolean[] result = Arrayx2.copyOf(selfElements, index + elements.size());
        for (boolean element : elements) {
            result[index++] = element;
        }
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] collection.
     */
    @NotNull
    public static char[] plus(@Nullable char[] selfElements, @Nullable Collection<Character> elements) {
        if (Collectionx.isNullOrEmpty(elements))
            return selfElements != null ? Arrayx2.copyOf(selfElements) : new char[0];
        if (selfElements == null) return Collectionx.toCharArray(elements);
        int index = selfElements.length;
        char[] result = Arrayx2.copyOf(selfElements, index + elements.size());
        for (char element : elements) {
            result[index++] = element;
        }
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] array.
     */
    @NotNull
    public static <T> T[] plus(@NotNull T[] selfElements, @NotNull T[] elements) {
        if (isNullOrEmpty(elements)) {
            //noinspection unchecked
            return Arrayx2.copyOf(selfElements);
        }
        int thisSize = selfElements.length;
        int arraySize = elements.length;
        T[] result = Arrayx2.copyOf(selfElements, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] array.
     */
    @NotNull
    public static byte[] plus(@Nullable byte[] selfElements, @NotNull byte[] elements) {
        if (isNullOrEmpty(elements)) return selfElements != null ? Arrayx2.copyOf(selfElements) : new byte[0];
        if (selfElements == null) return Arrayx2.copyOf(elements);
        int thisSize = selfElements.length;
        int arraySize = elements.length;
        byte[] result = Arrayx2.copyOf(selfElements, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] array.
     */
    @NotNull
    public static short[] plus(@Nullable short[] selfElements, @NotNull short[] elements) {
        if (isNullOrEmpty(elements)) return selfElements != null ? Arrayx2.copyOf(selfElements) : new short[0];
        if (selfElements == null) return Arrayx2.copyOf(elements);
        int thisSize = selfElements.length;
        int arraySize = elements.length;
        short[] result = Arrayx2.copyOf(selfElements, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] array.
     */
    @NotNull
    public static int[] plus(@Nullable int[] selfElements, @NotNull int[] elements) {
        if (isNullOrEmpty(elements)) return selfElements != null ? Arrayx2.copyOf(selfElements) : new int[0];
        if (selfElements == null) return Arrayx2.copyOf(elements);
        int thisSize = selfElements.length;
        int arraySize = elements.length;
        int[] result = Arrayx2.copyOf(selfElements, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] array.
     */
    @NotNull
    public static long[] plus(@Nullable long[] selfElements, @NotNull long[] elements) {
        if (isNullOrEmpty(elements)) return selfElements != null ? Arrayx2.copyOf(selfElements) : new long[0];
        if (selfElements == null) return Arrayx2.copyOf(elements);
        int thisSize = selfElements.length;
        int arraySize = elements.length;
        long[] result = Arrayx2.copyOf(selfElements, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] array.
     */
    @NotNull
    public static float[] plus(@Nullable float[] selfElements, @NotNull float[] elements) {
        if (isNullOrEmpty(elements)) return selfElements != null ? Arrayx2.copyOf(selfElements) : new float[0];
        if (selfElements == null) return Arrayx2.copyOf(elements);
        int thisSize = selfElements.length;
        int arraySize = elements.length;
        float[] result = Arrayx2.copyOf(selfElements, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] array.
     */
    @NotNull
    public static double[] plus(@Nullable double[] selfElements, @NotNull double[] elements) {
        if (isNullOrEmpty(elements)) return selfElements != null ? Arrayx2.copyOf(selfElements) : new double[0];
        if (selfElements == null) return Arrayx2.copyOf(elements);
        int thisSize = selfElements.length;
        int arraySize = elements.length;
        double[] result = Arrayx2.copyOf(selfElements, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] array.
     */
    @NotNull
    public static boolean[] plus(@Nullable boolean[] selfElements, @NotNull boolean[] elements) {
        if (isNullOrEmpty(elements)) return selfElements != null ? Arrayx2.copyOf(selfElements) : new boolean[0];
        if (selfElements == null) return Arrayx2.copyOf(elements);
        int thisSize = selfElements.length;
        int arraySize = elements.length;
        boolean[] result = Arrayx2.copyOf(selfElements, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then all elements of the given [elements] array.
     */
    @NotNull
    public static char[] plus(@Nullable char[] selfElements, @NotNull char[] elements) {
        if (isNullOrEmpty(elements)) return selfElements != null ? Arrayx2.copyOf(selfElements) : new char[0];
        if (selfElements == null) return Arrayx2.copyOf(elements);
        int thisSize = selfElements.length;
        int arraySize = elements.length;
        char[] result = Arrayx2.copyOf(selfElements, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        return result;
    }

    /**
     * Returns an array containing all elements of the original array and then the given [element].
     */
    @NotNull
    public static <T> T[] plusElement(@NotNull T[] elements, @NotNull T element) {
        return plus(elements, element);
    }


    /* ******************************************* indices ******************************************* */


    /**
     * Returns the range of valid indices for the array.
     */
    @NotNull
    public static <T> IntRange indices(@Nullable T[] elements) {
        return new IntRange(0, count(elements) - 1);
    }

    /**
     * Returns the range of valid indices for the array.
     */
    @NotNull
    public static IntRange indices(@Nullable byte[] elements) {
        return new IntRange(0, count(elements) - 1);
    }

    /**
     * Returns the range of valid indices for the array.
     */
    @NotNull
    public static IntRange indices(@Nullable short[] elements) {
        return new IntRange(0, count(elements) - 1);
    }

    /**
     * Returns the range of valid indices for the array.
     */
    @NotNull
    public static IntRange indices(@Nullable int[] elements) {
        return new IntRange(0, count(elements) - 1);
    }

    /**
     * Returns the range of valid indices for the array.
     */
    @NotNull
    public static IntRange indices(@Nullable long[] elements) {
        return new IntRange(0, count(elements) - 1);
    }

    /**
     * Returns the range of valid indices for the array.
     */
    @NotNull
    public static IntRange indices(@Nullable float[] elements) {
        return new IntRange(0, count(elements) - 1);
    }

    /**
     * Returns the range of valid indices for the array.
     */
    @NotNull
    public static IntRange indices(@Nullable double[] elements) {
        return new IntRange(0, count(elements) - 1);
    }

    /**
     * Returns the range of valid indices for the array.
     */
    @NotNull
    public static IntRange indices(@Nullable boolean[] elements) {
        return new IntRange(0, count(elements) - 1);
    }

    /**
     * Returns the range of valid indices for the array.
     */
    @NotNull
    public static IntRange indices(@Nullable char[] elements) {
        return new IntRange(0, count(elements) - 1);
    }


    /* ******************************************* indexOf ******************************************* */


    /**
     * Returns first index of [element], or -1 if the array does not contain element.
     */
    public static <T> int indexOf(@Nullable T[] elements, @Nullable T element) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && (element != null ? element.equals(elements[index]) : null == elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns first index of [element], or -1 if the array does not contain element.
     */
    public static int indexOf(@Nullable byte[] elements, byte element) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns first index of [element], or -1 if the array does not contain element.
     */
    public static int indexOf(@Nullable short[] elements, short element) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns first index of [element], or -1 if the array does not contain element.
     */
    public static int indexOf(@Nullable int[] elements, int element) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns first index of [element], or -1 if the array does not contain element.
     */
    public static int indexOf(@Nullable long[] elements, long element) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns first index of [element], or -1 if the array does not contain element.
     */
    public static int indexOf(@Nullable float[] elements, float element) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns first index of [element], or -1 if the array does not contain element.
     */
    public static int indexOf(@Nullable double[] elements, double element) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns first index of [element], or -1 if the array does not contain element.
     */
    public static int indexOf(@Nullable boolean[] elements, boolean element) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns first index of [element], or -1 if the array does not contain element.
     */
    public static int indexOf(@Nullable char[] elements, char element) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static <T> int indexOfFirst(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfFirst(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfFirst(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfFirst(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfFirst(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfFirst(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfFirst(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfFirst(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the first element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfFirst(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        for (int index = 0, size = count(elements); index < size; index++) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static <T> int indexOfLast(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfLast(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfLast(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfLast(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfLast(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfLast(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfLast(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfLast(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns index of the last element matching the given [predicate], or -1 if the array does not contain such element.
     */
    public static int indexOfLast(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && predicate.accept(elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns last index of [element], or -1 if the array does not contain element.
     */
    public static <T> int lastIndexOf(@Nullable T[] elements, @Nullable T element) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && (element != null ? element.equals(elements[index]) : null == elements[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns last index of [element], or -1 if the array does not contain element.
     */
    public static int lastIndexOf(@Nullable byte[] elements, byte element) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns last index of [element], or -1 if the array does not contain element.
     */
    public static int lastIndexOf(@Nullable short[] elements, short element) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns last index of [element], or -1 if the array does not contain element.
     */
    public static int lastIndexOf(@Nullable int[] elements, int element) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns last index of [element], or -1 if the array does not contain element.
     */
    public static int lastIndexOf(@Nullable long[] elements, long element) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns last index of [element], or -1 if the array does not contain element.
     */
    public static int lastIndexOf(@Nullable float[] elements, float element) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns last index of [element], or -1 if the array does not contain element.
     */
    public static int lastIndexOf(@Nullable double[] elements, double element) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns last index of [element], or -1 if the array does not contain element.
     */
    public static int lastIndexOf(@Nullable boolean[] elements, boolean element) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns last index of [element], or -1 if the array does not contain element.
     */
    public static int lastIndexOf(@Nullable char[] elements, char element) {
        for (int index = count(elements) - 1; index >= 0; index--) {
            if (elements != null && element == elements[index]) {
                return index;
            }
        }
        return -1;
    }


    /* ******************************************* contains ******************************************* */


    /**
     * Returns `true` if [element] is found in the array.
     */
    public static <T> boolean contains(@Nullable T[] elements, @Nullable T element) {
        return indexOf(elements, element) >= 0;
    }

    /**
     * Returns `true` if [element] is found in the array.
     */
    public static boolean contains(@Nullable byte[] elements, byte element) {
        return indexOf(elements, element) >= 0;
    }

    /**
     * Returns `true` if [element] is found in the array.
     */
    public static boolean contains(@Nullable short[] elements, short element) {
        return indexOf(elements, element) >= 0;
    }

    /**
     * Returns `true` if [element] is found in the array.
     */
    public static boolean contains(@Nullable int[] elements, int element) {
        return indexOf(elements, element) >= 0;
    }

    /**
     * Returns `true` if [element] is found in the array.
     */
    public static boolean contains(@Nullable long[] elements, long element) {
        return indexOf(elements, element) >= 0;
    }

    /**
     * Returns `true` if [element] is found in the array.
     */
    public static boolean contains(@Nullable float[] elements, float element) {
        return indexOf(elements, element) >= 0;
    }

    /**
     * Returns `true` if [element] is found in the array.
     */
    public static boolean contains(@Nullable double[] elements, double element) {
        return indexOf(elements, element) >= 0;
    }

    /**
     * Returns `true` if [element] is found in the array.
     */
    public static boolean contains(@Nullable boolean[] elements, boolean element) {
        return indexOf(elements, element) >= 0;
    }

    /**
     * Returns `true` if [element] is found in the array.
     */
    public static boolean contains(@Nullable char[] elements, char element) {
        return indexOf(elements, element) >= 0;
    }


    /* ******************************************* forEach ******************************************* */


    /**
     * Performs the given [action] on each element.
     */
    public static <T> void forEach(@Nullable T[] elements, @NotNull Action<T> action) {
        if (elements != null) for (T element : elements) action.action(element);
    }

    /**
     * Performs the given [action] on each element.
     */
    public static void forEach(@Nullable byte[] elements, @NotNull Action<Byte> action) {
        if (elements != null) for (byte element : elements) action.action(element);
    }

    /**
     * Performs the given [action] on each element.
     */
    public static void forEach(@Nullable short[] elements, @NotNull Action<Short> action) {
        if (elements != null) for (short element : elements) action.action(element);
    }

    /**
     * Performs the given [action] on each element.
     */
    public static void forEach(@Nullable int[] elements, @NotNull Action<Integer> action) {
        if (elements != null) for (int element : elements) action.action(element);
    }

    /**
     * Performs the given [action] on each element.
     */
    public static void forEach(@Nullable long[] elements, @NotNull Action<Long> action) {
        if (elements != null) for (long element : elements) action.action(element);
    }

    /**
     * Performs the given [action] on each element.
     */
    public static void forEach(@Nullable float[] elements, @NotNull Action<Float> action) {
        if (elements != null) for (float element : elements) action.action(element);
    }

    /**
     * Performs the given [action] on each element.
     */
    public static void forEach(@Nullable double[] elements, @NotNull Action<Double> action) {
        if (elements != null) for (double element : elements) action.action(element);
    }

    /**
     * Performs the given [action] on each element.
     */
    public static void forEach(@Nullable boolean[] elements, @NotNull Action<Boolean> action) {
        if (elements != null) for (boolean element : elements) action.action(element);
    }

    /**
     * Performs the given [action] on each element.
     */
    public static void forEach(@Nullable char[] elements, @NotNull Action<Character> action) {
        if (elements != null) for (char element : elements) action.action(element);
    }

    /**
     * Performs the given [action] on each element, providing sequential index with the element.
     *
     * @param action function that takes the index of an element and the element itself
     *               and performs the desired action on the element.
     */
    public static <T> void forEachIndexed(@Nullable T[] elements, @NotNull IndexedAction<T> action) {
        int index = 0;
        if (elements != null) for (T item : elements) action.action(index++, item);
    }

    /**
     * Performs the given [action] on each element, providing sequential index with the element.
     *
     * @param action function that takes the index of an element and the element itself
     *               and performs the desired action on the element.
     */
    public static void forEachIndexed(@Nullable byte[] elements, @NotNull IndexedAction<Byte> action) {
        int index = 0;
        if (elements != null) for (byte item : elements) action.action(index++, item);
    }

    /**
     * Performs the given [action] on each element, providing sequential index with the element.
     *
     * @param action function that takes the index of an element and the element itself
     *               and performs the desired action on the element.
     */
    public static void forEachIndexed(@Nullable short[] elements, @NotNull IndexedAction<Short> action) {
        int index = 0;
        if (elements != null) for (short item : elements) action.action(index++, item);
    }

    /**
     * Performs the given [action] on each element, providing sequential index with the element.
     *
     * @param action function that takes the index of an element and the element itself
     *               and performs the desired action on the element.
     */
    public static void forEachIndexed(@Nullable int[] elements, @NotNull IndexedAction<Integer> action) {
        int index = 0;
        if (elements != null) for (int item : elements) action.action(index++, item);
    }

    /**
     * Performs the given [action] on each element, providing sequential index with the element.
     *
     * @param action function that takes the index of an element and the element itself
     *               and performs the desired action on the element.
     */
    public static void forEachIndexed(@Nullable long[] elements, @NotNull IndexedAction<Long> action) {
        int index = 0;
        if (elements != null) for (long item : elements) action.action(index++, item);
    }

    /**
     * Performs the given [action] on each element, providing sequential index with the element.
     *
     * @param action function that takes the index of an element and the element itself
     *               and performs the desired action on the element.
     */
    public static void forEachIndexed(@Nullable float[] elements, @NotNull IndexedAction<Float> action) {
        int index = 0;
        if (elements != null) for (float item : elements) action.action(index++, item);
    }

    /**
     * Performs the given [action] on each element, providing sequential index with the element.
     *
     * @param action function that takes the index of an element and the element itself
     *               and performs the desired action on the element.
     */
    public static void forEachIndexed(@Nullable double[] elements, @NotNull IndexedAction<Double> action) {
        int index = 0;
        if (elements != null) for (double item : elements) action.action(index++, item);
    }

    /**
     * Performs the given [action] on each element, providing sequential index with the element.
     *
     * @param action function that takes the index of an element and the element itself
     *               and performs the desired action on the element.
     */
    public static void forEachIndexed(@Nullable boolean[] elements, @NotNull IndexedAction<Boolean> action) {
        int index = 0;
        if (elements != null) for (boolean item : elements) action.action(index++, item);
    }

    /**
     * Performs the given [action] on each element, providing sequential index with the element.
     *
     * @param action function that takes the index of an element and the element itself
     *               and performs the desired action on the element.
     */
    public static void forEachIndexed(@Nullable char[] elements, @NotNull IndexedAction<Character> action) {
        int index = 0;
        if (elements != null) for (char item : elements) action.action(index++, item);
    }


    /* ******************************************* filter ******************************************* */


    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    @NotNull
    public static <T> List<T> filter(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        return filterTo(elements, new ArrayList<T>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    @NotNull
    public static List<Byte> filter(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        return filterTo(elements, new ArrayList<Byte>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    @NotNull
    public static List<Short> filter(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        return filterTo(elements, new ArrayList<Short>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    @NotNull
    public static List<Integer> filter(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        return filterTo(elements, new ArrayList<Integer>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    @NotNull
    public static List<Long> filter(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        return filterTo(elements, new ArrayList<Long>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    @NotNull
    public static List<Float> filter(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        return filterTo(elements, new ArrayList<Float>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    @NotNull
    public static List<Double> filter(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        return filterTo(elements, new ArrayList<Double>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    @NotNull
    public static List<Boolean> filter(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        return filterTo(elements, new ArrayList<Boolean>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    @NotNull
    public static List<Character> filter(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        return filterTo(elements, new ArrayList<Character>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <T> List<T> filterIndexed(@Nullable T[] elements, @NotNull IndexedPredicate<T> predicate) {
        return filterIndexedTo(elements, new ArrayList<T>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static List<Byte> filterIndexed(@Nullable byte[] elements, @NotNull IndexedPredicate<Byte> predicate) {
        return filterIndexedTo(elements, new ArrayList<Byte>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static List<Short> filterIndexed(@Nullable short[] elements, @NotNull IndexedPredicate<Short> predicate) {
        return filterIndexedTo(elements, new ArrayList<Short>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static List<Integer> filterIndexed(@Nullable int[] elements, @NotNull IndexedPredicate<Integer> predicate) {
        return filterIndexedTo(elements, new ArrayList<Integer>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static List<Long> filterIndexed(@Nullable long[] elements, @NotNull IndexedPredicate<Long> predicate) {
        return filterIndexedTo(elements, new ArrayList<Long>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static List<Float> filterIndexed(@Nullable float[] elements, @NotNull IndexedPredicate<Float> predicate) {
        return filterIndexedTo(elements, new ArrayList<Float>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static List<Double> filterIndexed(@Nullable double[] elements, @NotNull IndexedPredicate<Double> predicate) {
        return filterIndexedTo(elements, new ArrayList<Double>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static List<Boolean> filterIndexed(@Nullable boolean[] elements, @NotNull IndexedPredicate<Boolean> predicate) {
        return filterIndexedTo(elements, new ArrayList<Boolean>(), predicate);
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static List<Character> filterIndexed(@Nullable char[] elements, @NotNull IndexedPredicate<Character> predicate) {
        return filterIndexedTo(elements, new ArrayList<Character>(), predicate);
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <T, C extends Collection<T>> C filterIndexedTo(@Nullable T[] elements, @NotNull final C destination, @NotNull final IndexedPredicate<T> predicate) {
        forEachIndexed(elements, new IndexedAction<T>() {
            @Override
            public void action(int index, @NotNull T element) {
                if (predicate.accept(index, element)) {
                    destination.add(element);
                }
            }
        });
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <C extends Collection<Byte>> C filterIndexedTo(@Nullable byte[] elements, @NotNull final C destination, @NotNull final IndexedPredicate<Byte> predicate) {
        forEachIndexed(elements, new IndexedAction<Byte>() {
            @Override
            public void action(int index, @NotNull Byte element) {
                if (predicate.accept(index, element)) {
                    destination.add(element);
                }
            }
        });
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <C extends Collection<Short>> C filterIndexedTo(@Nullable short[] elements, @NotNull final C destination, @NotNull final IndexedPredicate<Short> predicate) {
        forEachIndexed(elements, new IndexedAction<Short>() {
            @Override
            public void action(int index, @NotNull Short element) {
                if (predicate.accept(index, element)) {
                    destination.add(element);
                }
            }
        });
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <C extends Collection<Integer>> C filterIndexedTo(@Nullable int[] elements, @NotNull final C destination, @NotNull final IndexedPredicate<Integer> predicate) {
        forEachIndexed(elements, new IndexedAction<Integer>() {
            @Override
            public void action(int index, @NotNull Integer element) {
                if (predicate.accept(index, element)) {
                    destination.add(element);
                }
            }
        });
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <C extends Collection<Long>> C filterIndexedTo(@Nullable long[] elements, @NotNull final C destination, @NotNull final IndexedPredicate<Long> predicate) {
        forEachIndexed(elements, new IndexedAction<Long>() {
            @Override
            public void action(int index, @NotNull Long element) {
                if (predicate.accept(index, element)) {
                    destination.add(element);
                }
            }
        });
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <C extends Collection<Float>> C filterIndexedTo(@Nullable float[] elements, @NotNull final C destination, @NotNull final IndexedPredicate<Float> predicate) {
        forEachIndexed(elements, new IndexedAction<Float>() {
            @Override
            public void action(int index, @NotNull Float element) {
                if (predicate.accept(index, element)) {
                    destination.add(element);
                }
            }
        });
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <C extends Collection<Double>> C filterIndexedTo(@Nullable double[] elements, @NotNull final C destination, @NotNull final IndexedPredicate<Double> predicate) {
        forEachIndexed(elements, new IndexedAction<Double>() {
            @Override
            public void action(int index, @NotNull Double element) {
                if (predicate.accept(index, element)) {
                    destination.add(element);
                }
            }
        });
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <C extends Collection<Boolean>> C filterIndexedTo(@Nullable boolean[] elements, @NotNull final C destination, @NotNull final IndexedPredicate<Boolean> predicate) {
        forEachIndexed(elements, new IndexedAction<Boolean>() {
            @Override
            public void action(int index, @NotNull Boolean element) {
                if (predicate.accept(index, element)) {
                    destination.add(element);
                }
            }
        });
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     *
     * @param predicate function that takes the index of an element and the element itself
     *                  and returns the result of predicate evaluation on the element.
     */
    @NotNull
    public static <C extends Collection<Character>> C filterIndexedTo(@Nullable char[] elements, @NotNull final C destination, @NotNull final IndexedPredicate<Character> predicate) {
        forEachIndexed(elements, new IndexedAction<Character>() {
            @Override
            public void action(int index, @NotNull Character element) {
                if (predicate.accept(index, element)) {
                    destination.add(element);
                }
            }
        });
        return destination;
    }

    /**
     * Returns a list containing all elements that are instances of specified class.
     */
    @NotNull
    public static <R> List<R> filterIsInstance(@Nullable Object[] elements, @NotNull Class<R> clazz) {
        return filterIsInstanceTo(elements, new ArrayList<R>(), clazz);
    }

    /**
     * Appends all elements that are instances of specified class to the given [destination].
     */
    @NotNull
    public static <C extends Collection<R>, R> C filterIsInstanceTo(@Nullable Object[] elements, @NotNull C destination, @NotNull Class<R> clazz) {
        if (elements != null) {
            for (Object element : elements) {
                if (clazz.isInstance(element)) {
                    //noinspection unchecked
                    destination.add((R) element);
                }
            }
        }
        return destination;
    }

    /**
     * Returns a list containing all elements not matching the given [predicate].
     */
    @NotNull
    public static <T> List<T> filterNot(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        return filterNotTo(elements, new ArrayList<T>(), predicate);
    }

    /**
     * Returns a list containing all elements not matching the given [predicate].
     */
    @NotNull
    public static List<Byte> filterNot(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        return filterNotTo(elements, new ArrayList<Byte>(), predicate);
    }

    /**
     * Returns a list containing all elements not matching the given [predicate].
     */
    @NotNull
    public static List<Short> filterNot(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        return filterNotTo(elements, new ArrayList<Short>(), predicate);
    }

    /**
     * Returns a list containing all elements not matching the given [predicate].
     */
    @NotNull
    public static List<Integer> filterNot(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        return filterNotTo(elements, new ArrayList<Integer>(), predicate);
    }

    /**
     * Returns a list containing all elements not matching the given [predicate].
     */
    @NotNull
    public static List<Long> filterNot(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        return filterNotTo(elements, new ArrayList<Long>(), predicate);
    }

    /**
     * Returns a list containing all elements not matching the given [predicate].
     */
    @NotNull
    public static List<Float> filterNot(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        return filterNotTo(elements, new ArrayList<Float>(), predicate);
    }

    /**
     * Returns a list containing all elements not matching the given [predicate].
     */
    @NotNull
    public static List<Double> filterNot(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        return filterNotTo(elements, new ArrayList<Double>(), predicate);
    }

    /**
     * Returns a list containing all elements not matching the given [predicate].
     */
    @NotNull
    public static List<Boolean> filterNot(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        return filterNotTo(elements, new ArrayList<Boolean>(), predicate);
    }

    /**
     * Returns a list containing all elements not matching the given [predicate].
     */
    @NotNull
    public static List<Character> filterNot(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        return filterNotTo(elements, new ArrayList<Character>(), predicate);
    }

    /**
     * Returns a list containing all elements that are not `null`.
     */
    @NotNull
    public static <T> List<T> filterNotNull(@Nullable T[] elements) {
        return filterNotNullTo(elements, new ArrayList<T>());
    }

    /**
     * Appends all elements that are not `null` to the given [destination].
     */
    @NotNull
    public static <C extends Collection<T>, T> C filterNotNullTo(@Nullable T[] elements, @NotNull C destination) {
        if (elements != null) {
            for (T element : elements) if (element != null) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements not matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <T, C extends Collection<T>> C filterNotTo(@Nullable T[] elements, @NotNull C destination, @NotNull Predicate<T> predicate) {
        if (elements != null) {
            for (T element : elements) if (!predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements not matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Byte>> C filterNotTo(@Nullable byte[] elements, @NotNull C destination, @NotNull Predicate<Byte> predicate) {
        if (elements != null) {
            for (byte element : elements) if (!predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements not matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Short>> C filterNotTo(@Nullable short[] elements, @NotNull C destination, @NotNull Predicate<Short> predicate) {
        if (elements != null) {
            for (short element : elements) if (!predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements not matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Integer>> C filterNotTo(@Nullable int[] elements, @NotNull C destination, @NotNull Predicate<Integer> predicate) {
        if (elements != null) {
            for (int element : elements) if (!predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements not matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Long>> C filterNotTo(@Nullable long[] elements, @NotNull C destination, @NotNull Predicate<Long> predicate) {
        if (elements != null) {
            for (long element : elements) if (!predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements not matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Float>> C filterNotTo(@Nullable float[] elements, @NotNull C destination, @NotNull Predicate<Float> predicate) {
        if (elements != null) {
            for (float element : elements) if (!predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements not matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Double>> C filterNotTo(@Nullable double[] elements, @NotNull C destination, @NotNull Predicate<Double> predicate) {
        if (elements != null) {
            for (double element : elements) if (!predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements not matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Boolean>> C filterNotTo(@Nullable boolean[] elements, @NotNull C destination, @NotNull Predicate<Boolean> predicate) {
        if (elements != null) {
            for (boolean element : elements) if (!predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements not matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Character>> C filterNotTo(@Nullable char[] elements, @NotNull C destination, @NotNull Predicate<Character> predicate) {
        if (elements != null) {
            for (char element : elements) if (!predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <T, C extends Collection<T>> C filterTo(@Nullable T[] elements, @NotNull C destination, @NotNull Predicate<T> predicate) {
        if (elements != null) {
            for (T element : elements) if (predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Byte>> C filterTo(@Nullable byte[] elements, @NotNull C destination, @NotNull Predicate<Byte> predicate) {
        if (elements != null) {
            for (byte element : elements) if (predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Short>> C filterTo(@Nullable short[] elements, @NotNull C destination, @NotNull Predicate<Short> predicate) {
        if (elements != null) {
            for (short element : elements) if (predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Integer>> C filterTo(@Nullable int[] elements, @NotNull C destination, @NotNull Predicate<Integer> predicate) {
        if (elements != null) {
            for (int element : elements) if (predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Long>> C filterTo(@Nullable long[] elements, @NotNull C destination, @NotNull Predicate<Long> predicate) {
        if (elements != null) {
            for (long element : elements) if (predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Float>> C filterTo(@Nullable float[] elements, @NotNull C destination, @NotNull Predicate<Float> predicate) {
        if (elements != null) {
            for (float element : elements) if (predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Double>> C filterTo(@Nullable double[] elements, @NotNull C destination, @NotNull Predicate<Double> predicate) {
        if (elements != null) {
            for (double element : elements) if (predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Boolean>> C filterTo(@Nullable boolean[] elements, @NotNull C destination, @NotNull Predicate<Boolean> predicate) {
        if (elements != null) {
            for (boolean element : elements) if (predicate.accept(element)) destination.add(element);
        }
        return destination;
    }

    /**
     * Appends all elements matching the given [predicate] to the given [destination].
     */
    @NotNull
    public static <C extends Collection<Character>> C filterTo(@Nullable char[] elements, @NotNull C destination, @NotNull Predicate<Character> predicate) {
        if (elements != null) {
            for (char element : elements) if (predicate.accept(element)) destination.add(element);
        }
        return destination;
    }


    /* ******************************************* zip ******************************************* */


    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <T, R> List<Pair<T, R>> zip(@Nullable T[] elements, @Nullable R[] other) {
        return zip(elements, other, new Transformer2<T, R, Pair<T, R>>() {
            @NotNull
            @Override
            public Pair<T, R> transform(@NotNull T t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Byte, R>> zip(@Nullable byte[] elements, @Nullable R[] other) {
        return zip(elements, other, new Transformer2<Byte, R, Pair<Byte, R>>() {
            @NotNull
            @Override
            public Pair<Byte, R> transform(@NotNull Byte aByte, @NotNull R r) {
                return Pair.of(aByte, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Short, R>> zip(@Nullable short[] elements, @Nullable R[] other) {
        return zip(elements, other, new Transformer2<Short, R, Pair<Short, R>>() {
            @NotNull
            @Override
            public Pair<Short, R> transform(@NotNull Short t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Integer, R>> zip(@Nullable int[] elements, @Nullable R[] other) {
        return zip(elements, other, new Transformer2<Integer, R, Pair<Integer, R>>() {
            @NotNull
            @Override
            public Pair<Integer, R> transform(@NotNull Integer t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Long, R>> zip(@Nullable long[] elements, @Nullable R[] other) {
        return zip(elements, other, new Transformer2<Long, R, Pair<Long, R>>() {
            @NotNull
            @Override
            public Pair<Long, R> transform(@NotNull Long t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Float, R>> zip(@Nullable float[] elements, @Nullable R[] other) {
        return zip(elements, other, new Transformer2<Float, R, Pair<Float, R>>() {
            @NotNull
            @Override
            public Pair<Float, R> transform(@NotNull Float t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Double, R>> zip(@Nullable double[] elements, @Nullable R[] other) {
        return zip(elements, other, new Transformer2<Double, R, Pair<Double, R>>() {
            @NotNull
            @Override
            public Pair<Double, R> transform(@NotNull Double t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Boolean, R>> zip(@Nullable boolean[] elements, @Nullable R[] other) {
        return zip(elements, other, new Transformer2<Boolean, R, Pair<Boolean, R>>() {
            @NotNull
            @Override
            public Pair<Boolean, R> transform(@NotNull Boolean t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Character, R>> zip(@Nullable char[] elements, @Nullable R[] other) {
        return zip(elements, other, new Transformer2<Character, R, Pair<Character, R>>() {
            @NotNull
            @Override
            public Pair<Character, R> transform(@NotNull Character t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <T, R, V> List<V> zip(@Nullable T[] elements, @Nullable R[] other, @NotNull Transformer2<T, R, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable byte[] elements, @Nullable R[] other, @NotNull Transformer2<Byte, R, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable short[] elements, @Nullable R[] other, @NotNull Transformer2<Short, R, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable int[] elements, @Nullable R[] other, @NotNull Transformer2<Integer, R, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable long[] elements, @Nullable R[] other, @NotNull Transformer2<Long, R, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable float[] elements, @Nullable R[] other, @NotNull Transformer2<Float, R, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable double[] elements, @Nullable R[] other, @NotNull Transformer2<Double, R, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable boolean[] elements, @Nullable R[] other, @NotNull Transformer2<Boolean, R, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable char[] elements, @Nullable R[] other, @NotNull Transformer2<Character, R, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <T, R> List<Pair<T, R>> zip(@Nullable T[] elements, @Nullable Iterable<R> other) {
        return zip(elements, other, new Transformer2<T, R, Pair<T, R>>() {
            @NotNull
            @Override
            public Pair<T, R> transform(@NotNull T t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Byte, R>> zip(@Nullable byte[] elements, @Nullable Iterable<R> other) {
        return zip(elements, other, new Transformer2<Byte, R, Pair<Byte, R>>() {
            @NotNull
            @Override
            public Pair<Byte, R> transform(@NotNull Byte t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Short, R>> zip(@Nullable short[] elements, @Nullable Iterable<R> other) {
        return zip(elements, other, new Transformer2<Short, R, Pair<Short, R>>() {
            @NotNull
            @Override
            public Pair<Short, R> transform(@NotNull Short t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Integer, R>> zip(@Nullable int[] elements, @Nullable Iterable<R> other) {
        return zip(elements, other, new Transformer2<Integer, R, Pair<Integer, R>>() {
            @NotNull
            @Override
            public Pair<Integer, R> transform(@NotNull Integer t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Long, R>> zip(@Nullable long[] elements, @Nullable Iterable<R> other) {
        return zip(elements, other, new Transformer2<Long, R, Pair<Long, R>>() {
            @NotNull
            @Override
            public Pair<Long, R> transform(@NotNull Long t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Float, R>> zip(@Nullable float[] elements, @Nullable Iterable<R> other) {
        return zip(elements, other, new Transformer2<Float, R, Pair<Float, R>>() {
            @NotNull
            @Override
            public Pair<Float, R> transform(@NotNull Float t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Double, R>> zip(@Nullable double[] elements, @Nullable Iterable<R> other) {
        return zip(elements, other, new Transformer2<Double, R, Pair<Double, R>>() {
            @NotNull
            @Override
            public Pair<Double, R> transform(@NotNull Double t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Boolean, R>> zip(@Nullable boolean[] elements, @Nullable Iterable<R> other) {
        return zip(elements, other, new Transformer2<Boolean, R, Pair<Boolean, R>>() {
            @NotNull
            @Override
            public Pair<Boolean, R> transform(@NotNull Boolean t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static <R> List<Pair<Character, R>> zip(@Nullable char[] elements, @Nullable Iterable<R> other) {
        return zip(elements, other, new Transformer2<Character, R, Pair<Character, R>>() {
            @NotNull
            @Override
            public Pair<Character, R> transform(@NotNull Character t, @NotNull R r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <T, R, V> List<V> zip(@Nullable T[] elements, @Nullable Iterable<R> other, @NotNull Transformer2<T, R, V> transform) {
        if (isNullOrEmpty(elements) || other == null) return Collectionx.arrayListOf();
        int arraySize = elements.length;
        List<V> list = new ArrayList<>(Math.min(Collectionx.collectionSizeOrDefault(other, 10), arraySize));
        int i = 0;
        for (R element : other) {
            if (i >= arraySize) break;
            list.add(transform.transform(elements[i++], element));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable byte[] elements, @Nullable Iterable<R> other, @NotNull Transformer2<Byte, R, V> transform) {
        if (isNullOrEmpty(elements) || other == null) return Collectionx.arrayListOf();
        int arraySize = elements.length;
        List<V> list = new ArrayList<>(Math.min(Collectionx.collectionSizeOrDefault(other, 10), arraySize));
        int i = 0;
        for (R element : other) {
            if (i >= arraySize) break;
            list.add(transform.transform(elements[i++], element));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable short[] elements, @Nullable Iterable<R> other, @NotNull Transformer2<Short, R, V> transform) {
        if (isNullOrEmpty(elements) || other == null) return Collectionx.arrayListOf();
        int arraySize = elements.length;
        List<V> list = new ArrayList<>(Math.min(Collectionx.collectionSizeOrDefault(other, 10), arraySize));
        int i = 0;
        for (R element : other) {
            if (i >= arraySize) break;
            list.add(transform.transform(elements[i++], element));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable int[] elements, @Nullable Iterable<R> other, @NotNull Transformer2<Integer, R, V> transform) {
        if (isNullOrEmpty(elements) || other == null) return Collectionx.arrayListOf();
        int arraySize = elements.length;
        List<V> list = new ArrayList<>(Math.min(Collectionx.collectionSizeOrDefault(other, 10), arraySize));
        int i = 0;
        for (R element : other) {
            if (i >= arraySize) break;
            list.add(transform.transform(elements[i++], element));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable long[] elements, @Nullable Iterable<R> other, @NotNull Transformer2<Long, R, V> transform) {
        if (isNullOrEmpty(elements) || other == null) return Collectionx.arrayListOf();
        int arraySize = elements.length;
        List<V> list = new ArrayList<>(Math.min(Collectionx.collectionSizeOrDefault(other, 10), arraySize));
        int i = 0;
        for (R element : other) {
            if (i >= arraySize) break;
            list.add(transform.transform(elements[i++], element));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable float[] elements, @Nullable Iterable<R> other, @NotNull Transformer2<Float, R, V> transform) {
        if (isNullOrEmpty(elements) || other == null) return Collectionx.arrayListOf();
        int arraySize = elements.length;
        List<V> list = new ArrayList<>(Math.min(Collectionx.collectionSizeOrDefault(other, 10), arraySize));
        int i = 0;
        for (R element : other) {
            if (i >= arraySize) break;
            list.add(transform.transform(elements[i++], element));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable double[] elements, @Nullable Iterable<R> other, @NotNull Transformer2<Double, R, V> transform) {
        if (isNullOrEmpty(elements) || other == null) return Collectionx.arrayListOf();
        int arraySize = elements.length;
        List<V> list = new ArrayList<>(Math.min(Collectionx.collectionSizeOrDefault(other, 10), arraySize));
        int i = 0;
        for (R element : other) {
            if (i >= arraySize) break;
            list.add(transform.transform(elements[i++], element));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable boolean[] elements, @Nullable Iterable<R> other, @NotNull Transformer2<Boolean, R, V> transform) {
        if (isNullOrEmpty(elements) || other == null) return Collectionx.arrayListOf();
        int arraySize = elements.length;
        List<V> list = new ArrayList<>(Math.min(Collectionx.collectionSizeOrDefault(other, 10), arraySize));
        int i = 0;
        for (R element : other) {
            if (i >= arraySize) break;
            list.add(transform.transform(elements[i++], element));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <R, V> List<V> zip(@Nullable char[] elements, @Nullable Iterable<R> other, @NotNull Transformer2<Character, R, V> transform) {
        if (isNullOrEmpty(elements) || other == null) return Collectionx.arrayListOf();
        int arraySize = elements.length;
        List<V> list = new ArrayList<>(Math.min(Collectionx.collectionSizeOrDefault(other, 10), arraySize));
        int i = 0;
        for (R element : other) {
            if (i >= arraySize) break;
            list.add(transform.transform(elements[i++], element));
        }
        return list;
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static List<Pair<Byte, Byte>> zip(@Nullable byte[] elements, @Nullable byte[] other) {
        return zip(elements, other, new Transformer2<Byte, Byte, Pair<Byte, Byte>>() {
            @NotNull
            @Override
            public Pair<Byte, Byte> transform(@NotNull Byte t, @NotNull Byte r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static List<Pair<Short, Short>> zip(@Nullable short[] elements, @Nullable short[] other) {
        return zip(elements, other, new Transformer2<Short, Short, Pair<Short, Short>>() {
            @NotNull
            @Override
            public Pair<Short, Short> transform(@NotNull Short t, @NotNull Short r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static List<Pair<Integer, Integer>> zip(@Nullable int[] elements, @Nullable int[] other) {
        return zip(elements, other, new Transformer2<Integer, Integer, Pair<Integer, Integer>>() {
            @NotNull
            @Override
            public Pair<Integer, Integer> transform(@NotNull Integer t, @NotNull Integer r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static List<Pair<Long, Long>> zip(@Nullable long[] elements, @Nullable long[] other) {
        return zip(elements, other, new Transformer2<Long, Long, Pair<Long, Long>>() {
            @NotNull
            @Override
            public Pair<Long, Long> transform(@NotNull Long t, @NotNull Long r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static List<Pair<Float, Float>> zip(@Nullable float[] elements, @Nullable float[] other) {
        return zip(elements, other, new Transformer2<Float, Float, Pair<Float, Float>>() {
            @NotNull
            @Override
            public Pair<Float, Float> transform(@NotNull Float t, @NotNull Float r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static List<Pair<Double, Double>> zip(@Nullable double[] elements, @Nullable double[] other) {
        return zip(elements, other, new Transformer2<Double, Double, Pair<Double, Double>>() {
            @NotNull
            @Override
            public Pair<Double, Double> transform(@NotNull Double t, @NotNull Double r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static List<Pair<Boolean, Boolean>> zip(@Nullable boolean[] elements, @Nullable boolean[] other) {
        return zip(elements, other, new Transformer2<Boolean, Boolean, Pair<Boolean, Boolean>>() {
            @NotNull
            @Override
            public Pair<Boolean, Boolean> transform(@NotNull Boolean t, @NotNull Boolean r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
     */
    @NotNull
    public static List<Pair<Character, Character>> zip(@Nullable char[] elements, @Nullable char[] other) {
        return zip(elements, other, new Transformer2<Character, Character, Pair<Character, Character>>() {
            @NotNull
            @Override
            public Pair<Character, Character> transform(@NotNull Character t, @NotNull Character r) {
                return Pair.of(t, r);
            }
        });
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <V> List<V> zip(@Nullable byte[] elements, @Nullable byte[] other, @NotNull Transformer2<Byte, Byte, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <V> List<V> zip(@Nullable short[] elements, @Nullable short[] other, @NotNull Transformer2<Short, Short, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <V> List<V> zip(@Nullable int[] elements, @Nullable int[] other, @NotNull Transformer2<Integer, Integer, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <V> List<V> zip(@Nullable long[] elements, @Nullable long[] other, @NotNull Transformer2<Long, Long, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <V> List<V> zip(@Nullable float[] elements, @Nullable float[] other, @NotNull Transformer2<Float, Float, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <V> List<V> zip(@Nullable double[] elements, @Nullable double[] other, @NotNull Transformer2<Double, Double, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <V> List<V> zip(@Nullable boolean[] elements, @Nullable boolean[] other, @NotNull Transformer2<Boolean, Boolean, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }

    /**
     * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
     */
    @NotNull
    public static <V> List<V> zip(@Nullable char[] elements, @Nullable char[] other, @NotNull Transformer2<Character, Character, V> transform) {
        if (isNullOrEmpty(elements) || isNullOrEmpty(other)) return Collectionx.arrayListOf();
        int size = Math.min(count(elements), count(other));
        List<V> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(transform.transform(elements[i], other[i]));
        }
        return list;
    }


    /* ******************************************* partition ******************************************* */


    /**
     * Splits the original array into pair of lists,
     * where *first* list contains elements for which [predicate] yielded `true`,
     * while *second* list contains elements for which [predicate] yielded `false`.
     */
    @NotNull
    public static <T> Pair<List<T>, List<T>> partition(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        List<T> first = new ArrayList<>();
        List<T> second = new ArrayList<>();
        if (elements != null) {
            for (T element : elements) {
                if (predicate.accept(element)) {
                    first.add(element);
                } else {
                    second.add(element);
                }
            }
        }
        return new Pair<>(first, second);
    }

    /**
     * Splits the original array into pair of lists,
     * where *first* list contains elements for which [predicate] yielded `true`,
     * while *second* list contains elements for which [predicate] yielded `false`.
     */
    @NotNull
    public static Pair<List<Byte>, List<Byte>> partition(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        List<Byte> first = new ArrayList<>();
        List<Byte> second = new ArrayList<>();
        if (elements != null) {
            for (byte element : elements) {
                if (predicate.accept(element)) {
                    first.add(element);
                } else {
                    second.add(element);
                }
            }
        }
        return new Pair<>(first, second);
    }

    /**
     * Splits the original array into pair of lists,
     * where *first* list contains elements for which [predicate] yielded `true`,
     * while *second* list contains elements for which [predicate] yielded `false`.
     */
    @NotNull
    public static Pair<List<Short>, List<Short>> partition(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        List<Short> first = new ArrayList<>();
        List<Short> second = new ArrayList<>();
        if (elements != null) {
            for (short element : elements) {
                if (predicate.accept(element)) {
                    first.add(element);
                } else {
                    second.add(element);
                }
            }
        }
        return new Pair<>(first, second);
    }

    /**
     * Splits the original array into pair of lists,
     * where *first* list contains elements for which [predicate] yielded `true`,
     * while *second* list contains elements for which [predicate] yielded `false`.
     */
    @NotNull
    public static Pair<List<Integer>, List<Integer>> partition(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        if (elements != null) {
            for (int element : elements) {
                if (predicate.accept(element)) {
                    first.add(element);
                } else {
                    second.add(element);
                }
            }
        }
        return new Pair<>(first, second);
    }

    /**
     * Splits the original array into pair of lists,
     * where *first* list contains elements for which [predicate] yielded `true`,
     * while *second* list contains elements for which [predicate] yielded `false`.
     */
    @NotNull
    public static Pair<List<Long>, List<Long>> partition(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        List<Long> first = new ArrayList<>();
        List<Long> second = new ArrayList<>();
        if (elements != null) {
            for (long element : elements) {
                if (predicate.accept(element)) {
                    first.add(element);
                } else {
                    second.add(element);
                }
            }
        }
        return new Pair<>(first, second);
    }

    /**
     * Splits the original array into pair of lists,
     * where *first* list contains elements for which [predicate] yielded `true`,
     * while *second* list contains elements for which [predicate] yielded `false`.
     */
    @NotNull
    public static Pair<List<Float>, List<Float>> partition(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        List<Float> first = new ArrayList<>();
        List<Float> second = new ArrayList<>();
        if (elements != null) {
            for (float element : elements) {
                if (predicate.accept(element)) {
                    first.add(element);
                } else {
                    second.add(element);
                }
            }
        }
        return new Pair<>(first, second);
    }

    /**
     * Splits the original array into pair of lists,
     * where *first* list contains elements for which [predicate] yielded `true`,
     * while *second* list contains elements for which [predicate] yielded `false`.
     */
    @NotNull
    public static Pair<List<Double>, List<Double>> partition(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        List<Double> first = new ArrayList<>();
        List<Double> second = new ArrayList<>();
        if (elements != null) {
            for (double element : elements) {
                if (predicate.accept(element)) {
                    first.add(element);
                } else {
                    second.add(element);
                }
            }
        }
        return new Pair<>(first, second);
    }

    /**
     * Splits the original array into pair of lists,
     * where *first* list contains elements for which [predicate] yielded `true`,
     * while *second* list contains elements for which [predicate] yielded `false`.
     */
    @NotNull
    public static Pair<List<Boolean>, List<Boolean>> partition(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        List<Boolean> first = new ArrayList<>();
        List<Boolean> second = new ArrayList<>();
        if (elements != null) {
            for (boolean element : elements) {
                if (predicate.accept(element)) {
                    first.add(element);
                } else {
                    second.add(element);
                }
            }
        }
        return new Pair<>(first, second);
    }

    /**
     * Splits the original array into pair of lists,
     * where *first* list contains elements for which [predicate] yielded `true`,
     * while *second* list contains elements for which [predicate] yielded `false`.
     */
    @NotNull
    public static Pair<List<Character>, List<Character>> partition(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        List<Character> first = new ArrayList<>();
        List<Character> second = new ArrayList<>();
        if (elements != null) {
            for (char element : elements) {
                if (predicate.accept(element)) {
                    first.add(element);
                } else {
                    second.add(element);
                }
            }
        }
        return new Pair<>(first, second);
    }


    /* ******************************************* first ******************************************* */


    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    @NotNull
    public static <T> T first(@Nullable T[] elements) {
        if (elements == null || elements.length == 0) throw new NoSuchElementException("Array is empty.");
        return elements[0];
    }

    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static byte first(@Nullable byte[] elements) {
        if (elements == null || elements.length == 0) throw new NoSuchElementException("Array is empty.");
        return elements[0];
    }

    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static short first(@Nullable short[] elements) {
        if (elements == null || elements.length == 0) throw new NoSuchElementException("Array is empty.");
        return elements[0];
    }

    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static int first(@Nullable int[] elements) {
        if (elements == null || elements.length == 0) throw new NoSuchElementException("Array is empty.");
        return elements[0];
    }

    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static long first(@Nullable long[] elements) {
        if (elements == null || elements.length == 0) throw new NoSuchElementException("Array is empty.");
        return elements[0];
    }

    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static float first(@Nullable float[] elements) {
        if (elements == null || elements.length == 0) throw new NoSuchElementException("Array is empty.");
        return elements[0];
    }

    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static double first(@Nullable double[] elements) {
        if (elements == null || elements.length == 0) throw new NoSuchElementException("Array is empty.");
        return elements[0];
    }

    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static boolean first(@Nullable boolean[] elements) {
        if (elements == null || elements.length == 0) throw new NoSuchElementException("Array is empty.");
        return elements[0];
    }

    /**
     * Returns first element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static char first(@Nullable char[] elements) {
        if (elements == null || elements.length == 0) throw new NoSuchElementException("Array is empty.");
        return elements[0];
    }

    /**
     * Returns the first element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    @NotNull
    public static <T> T first(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        if (elements != null) {
            for (T element : elements) if (predicate.accept(element)) return element;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the first element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static byte first(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        if (elements != null) {
            for (byte element : elements) if (predicate.accept(element)) return element;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the first element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static short first(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        if (elements != null) {
            for (short element : elements) if (predicate.accept(element)) return element;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the first element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static int first(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        if (elements != null) {
            for (int element : elements) if (predicate.accept(element)) return element;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the first element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static long first(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        if (elements != null) {
            for (long element : elements) if (predicate.accept(element)) return element;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the first element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static float first(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        if (elements != null) {
            for (float element : elements) if (predicate.accept(element)) return element;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the first element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static double first(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        if (elements != null) {
            for (double element : elements) if (predicate.accept(element)) return element;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the first element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static boolean first(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        if (elements != null) {
            for (boolean element : elements) if (predicate.accept(element)) return element;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the first element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static char first(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        if (elements != null) {
            for (char element : elements) if (predicate.accept(element)) return element;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the first element, or `null` if the array is empty.
     */
    @Nullable
    public static <T> T firstOrNull(@Nullable T[] elements) {
        return elements == null || elements.length == 0 ? null : elements[0];
    }

    /**
     * Returns the first element, or `null` if the array is empty.
     */
    @Nullable
    public static Byte firstOrNull(@Nullable byte[] elements) {
        return elements == null || elements.length == 0 ? null : elements[0];
    }

    /**
     * Returns the first element, or `null` if the array is empty.
     */
    @Nullable
    public static Short firstOrNull(@Nullable short[] elements) {
        return elements == null || elements.length == 0 ? null : elements[0];
    }

    /**
     * Returns the first element, or `null` if the array is empty.
     */
    @Nullable
    public static Integer firstOrNull(@Nullable int[] elements) {
        return elements == null || elements.length == 0 ? null : elements[0];
    }

    /**
     * Returns the first element, or `null` if the array is empty.
     */
    @Nullable
    public static Long firstOrNull(@Nullable long[] elements) {
        return elements == null || elements.length == 0 ? null : elements[0];
    }

    /**
     * Returns the first element, or `null` if the array is empty.
     */
    @Nullable
    public static Float firstOrNull(@Nullable float[] elements) {
        return elements == null || elements.length == 0 ? null : elements[0];
    }

    /**
     * Returns the first element, or `null` if the array is empty.
     */
    @Nullable
    public static Double firstOrNull(@Nullable double[] elements) {
        return elements == null || elements.length == 0 ? null : elements[0];
    }

    /**
     * Returns the first element, or `null` if the array is empty.
     */
    @Nullable
    public static Boolean firstOrNull(@Nullable boolean[] elements) {
        return elements == null || elements.length == 0 ? null : elements[0];
    }

    /**
     * Returns the first element, or `null` if the array is empty.
     */
    @Nullable
    public static Character firstOrNull(@Nullable char[] elements) {
        return elements == null || elements.length == 0 ? null : elements[0];
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if element was not found.
     */
    @Nullable
    public static <T> T firstOrNull(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        if (elements != null) {
            for (T element : elements) if (predicate.accept(element)) return element;
        }
        return null;
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if element was not found.
     */
    @Nullable
    public static Byte firstOrNull(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        if (elements != null) for (byte element : elements) if (predicate.accept(element)) return element;
        return null;
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if element was not found.
     */
    @Nullable
    public static Short firstOrNull(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        if (elements != null) for (short element : elements) if (predicate.accept(element)) return element;
        return null;
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if element was not found.
     */
    @Nullable
    public static Integer firstOrNull(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        if (elements != null) for (int element : elements) if (predicate.accept(element)) return element;
        return null;
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if element was not found.
     */
    @Nullable
    public static Long firstOrNull(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        if (elements != null) for (long element : elements) if (predicate.accept(element)) return element;
        return null;
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if element was not found.
     */
    @Nullable
    public static Float firstOrNull(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        if (elements != null) for (float element : elements) if (predicate.accept(element)) return element;
        return null;
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if element was not found.
     */
    @Nullable
    public static Double firstOrNull(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        if (elements != null) for (double element : elements) if (predicate.accept(element)) return element;
        return null;
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if element was not found.
     */
    @Nullable
    public static Boolean firstOrNull(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        if (elements != null) for (boolean element : elements) if (predicate.accept(element)) return element;
        return null;
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if element was not found.
     */
    @Nullable
    public static Character firstOrNull(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        if (elements != null) for (char element : elements) if (predicate.accept(element)) return element;
        return null;
    }


    /* ******************************************* last ******************************************* */


    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    @NotNull
    public static <T> T last(@Nullable T[] elements) {
        if (elements == null || elements.length == 0) throw new NoSuchElementException("Array is empty.");
        return elements[elements.length - 1];
    }

    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static byte last(@Nullable byte[] elements) {
        if (elements == null || elements.length == 0)
            throw new NoSuchElementException("Array is empty.");
        return elements[elements.length - 1];
    }

    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static short last(@Nullable short[] elements) {
        if (elements == null || elements.length == 0)
            throw new NoSuchElementException("Array is empty.");
        return elements[elements.length - 1];
    }

    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static int last(@Nullable int[] elements) {
        if (elements == null || elements.length == 0)
            throw new NoSuchElementException("Array is empty.");
        return elements[elements.length - 1];
    }

    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static long last(@Nullable long[] elements) {
        if (elements == null || elements.length == 0)
            throw new NoSuchElementException("Array is empty.");
        return elements[elements.length - 1];
    }

    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static float last(@Nullable float[] elements) {
        if (elements == null || elements.length == 0)
            throw new NoSuchElementException("Array is empty.");
        return elements[elements.length - 1];
    }

    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static double last(@Nullable double[] elements) {
        if (elements == null || elements.length == 0)
            throw new NoSuchElementException("Array is empty.");
        return elements[elements.length - 1];
    }

    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static boolean last(@Nullable boolean[] elements) {
        if (elements == null || elements.length == 0)
            throw new NoSuchElementException("Array is empty.");
        return elements[elements.length - 1];
    }

    /**
     * Returns the last element.
     *
     * @throws NoSuchElementException if the array is empty.
     */
    public static char last(@Nullable char[] elements) {
        if (elements == null || elements.length == 0)
            throw new NoSuchElementException("Array is empty.");
        return elements[elements.length - 1];
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    @NotNull
    public static <T> T last(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                T element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static byte last(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                byte element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static short last(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                short element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static int last(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                int element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static long last(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                long element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static float last(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                float element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static double last(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                double element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static boolean last(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                boolean element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the last element matching the given [predicate].
     *
     * @throws NoSuchElementException if no such element is found.
     */
    public static char last(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                char element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    /**
     * Returns the last element, or `null` if the array is empty.
     */
    @Nullable
    public static <T> T lastOrNull(@Nullable T[] elements) {
        return elements == null || elements.length == 0 ? null : elements[elements.length - 1];
    }

    /**
     * Returns the last element, or `null` if the array is empty.
     */
    @Nullable
    public static Byte lastOrNull(@Nullable byte[] elements) {
        return elements == null || elements.length == 0 ? null : elements[elements.length - 1];
    }

    /**
     * Returns the last element, or `null` if the array is empty.
     */
    @Nullable
    public static Short lastOrNull(@Nullable short[] elements) {
        return elements == null || elements.length == 0 ? null : elements[elements.length - 1];
    }

    /**
     * Returns the last element, or `null` if the array is empty.
     */
    @Nullable
    public static Integer lastOrNull(@Nullable int[] elements) {
        return elements == null || elements.length == 0 ? null : elements[elements.length - 1];
    }

    /**
     * Returns the last element, or `null` if the array is empty.
     */
    @Nullable
    public static Long lastOrNull(@Nullable long[] elements) {
        return elements == null || elements.length == 0 ? null : elements[elements.length - 1];
    }

    /**
     * Returns the last element, or `null` if the array is empty.
     */
    @Nullable
    public static Float lastOrNull(@Nullable float[] elements) {
        return elements == null || elements.length == 0 ? null : elements[elements.length - 1];
    }

    /**
     * Returns the last element, or `null` if the array is empty.
     */
    @Nullable
    public static Double lastOrNull(@Nullable double[] elements) {
        return elements == null || elements.length == 0 ? null : elements[elements.length - 1];
    }

    /**
     * Returns the last element, or `null` if the array is empty.
     */
    @Nullable
    public static Boolean lastOrNull(@Nullable boolean[] elements) {
        return elements == null || elements.length == 0 ? null : elements[elements.length - 1];
    }

    /**
     * Returns the last element, or `null` if the array is empty.
     */
    @Nullable
    public static Character lastOrNull(@Nullable char[] elements) {
        return elements == null || elements.length == 0 ? null : elements[elements.length - 1];
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static <T> T lastOrNull(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                T element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Byte lastOrNull(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                byte element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Short lastOrNull(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                short element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Integer lastOrNull(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                int element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Long lastOrNull(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                long element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Float lastOrNull(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                float element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Double lastOrNull(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                double element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Boolean lastOrNull(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                boolean element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Character lastOrNull(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                char element = elements[index];
                if (predicate.accept(element)) return element;
            }
        }
        return null;
    }


    /* ******************************************* find ******************************************* */


    /**
     * Returns the first element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static <T> T find(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        return firstOrNull(elements, predicate);
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Byte find(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        return firstOrNull(elements, predicate);
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Short find(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        return firstOrNull(elements, predicate);
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Integer find(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        return firstOrNull(elements, predicate);
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Long find(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        return firstOrNull(elements, predicate);
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Float find(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        return firstOrNull(elements, predicate);
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Double find(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        return firstOrNull(elements, predicate);
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Boolean find(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        return firstOrNull(elements, predicate);
    }

    /**
     * Returns the first element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Character find(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        return firstOrNull(elements, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static <T> T findLast(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        return lastOrNull(elements, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Byte findLast(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        return lastOrNull(elements, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Short findLast(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        return lastOrNull(elements, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Integer findLast(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        return lastOrNull(elements, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Long findLast(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        return lastOrNull(elements, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Float findLast(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        return lastOrNull(elements, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Double findLast(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        return lastOrNull(elements, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Boolean findLast(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        return lastOrNull(elements, predicate);
    }

    /**
     * Returns the last element matching the given [predicate], or `null` if no such element was found.
     */
    @Nullable
    public static Character findLast(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        return lastOrNull(elements, predicate);
    }


    /* ******************************************* get ******************************************* */


    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    @NotNull
    public static <T> T getOrElse(@Nullable T[] elements, int index, @NotNull IndexedDefaultValue<T> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static byte getOrElse(@Nullable byte[] elements, int index, @NotNull IndexedDefaultValue<Byte> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static short getOrElse(@Nullable short[] elements, int index, @NotNull IndexedDefaultValue<Short> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static int getOrElse(@Nullable int[] elements, int index, @NotNull IndexedDefaultValue<Integer> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static long getOrElse(@Nullable long[] elements, int index, @NotNull IndexedDefaultValue<Long> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static float getOrElse(@Nullable float[] elements, int index, @NotNull IndexedDefaultValue<Float> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static double getOrElse(@Nullable double[] elements, int index, @NotNull IndexedDefaultValue<Double> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static boolean getOrElse(@Nullable boolean[] elements, int index, @NotNull IndexedDefaultValue<Boolean> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static char getOrElse(@Nullable char[] elements, int index, @NotNull IndexedDefaultValue<Character> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static <T> T getOrNull(@Nullable T[] elements, int index) {
        return elements != null && (index >= 0 && index < elements.length) ? elements[index] : null;
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Byte getOrNull(@Nullable byte[] elements, int index) {
        return elements != null && (index >= 0 && index < elements.length) ? elements[index] : null;
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Short getOrNull(@Nullable short[] elements, int index) {
        return elements != null && (index >= 0 && index < elements.length) ? elements[index] : null;
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Integer getOrNull(@Nullable int[] elements, int index) {
        return elements != null && (index >= 0 && index < elements.length) ? elements[index] : null;
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Long getOrNull(@Nullable long[] elements, int index) {
        return elements != null && (index >= 0 && index < elements.length) ? elements[index] : null;
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Float getOrNull(@Nullable float[] elements, int index) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : null;
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Double getOrNull(@Nullable double[] elements, int index) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : null;
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Boolean getOrNull(@Nullable boolean[] elements, int index) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : null;
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Character getOrNull(@Nullable char[] elements, int index) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : null;
    }


    /* ******************************************* elementAt ******************************************* */


    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    @NotNull
    public static <T> T elementAt(@NotNull T[] elements, int index) {
        return elements[index];
    }

    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public static byte elementAt(@NotNull byte[] elements, int index) {
        return elements[index];
    }

    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public static short elementAt(@NotNull short[] elements, int index) {
        return elements[index];
    }

    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public static int elementAt(@NotNull int[] elements, int index) {
        return elements[index];
    }

    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public static long elementAt(@NotNull long[] elements, int index) {
        return elements[index];
    }

    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public static float elementAt(@NotNull float[] elements, int index) {
        return elements[index];
    }

    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public static double elementAt(@NotNull double[] elements, int index) {
        return elements[index];
    }

    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public static boolean elementAt(@NotNull boolean[] elements, int index) {
        return elements[index];
    }

    /**
     * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public static char elementAt(@NotNull char[] elements, int index) {
        return elements[index];
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    @NotNull
    public static <T> T elementAtOrElse(@Nullable T[] elements, int index, @NotNull IndexedDefaultValue<T> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static byte elementAtOrElse(@Nullable byte[] elements, int index, @NotNull IndexedDefaultValue<Byte> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static short elementAtOrElse(@Nullable short[] elements, int index, @NotNull IndexedDefaultValue<Short> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static int elementAtOrElse(@Nullable int[] elements, int index, @NotNull IndexedDefaultValue<Integer> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static long elementAtOrElse(@Nullable long[] elements, int index, @NotNull IndexedDefaultValue<Long> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static float elementAtOrElse(@Nullable float[] elements, int index, @NotNull IndexedDefaultValue<Float> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static double elementAtOrElse(@Nullable double[] elements, int index, @NotNull IndexedDefaultValue<Double> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static boolean elementAtOrElse(@Nullable boolean[] elements, int index, @NotNull IndexedDefaultValue<Boolean> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this array.
     */
    public static char elementAtOrElse(@Nullable char[] elements, int index, @NotNull IndexedDefaultValue<Character> defaultValue) {
        return elements != null && index >= 0 && index < elements.length ? elements[index] : defaultValue.get(index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static <T> T elementAtOrNull(@Nullable T[] elements, int index) {
        return Arrayx.getOrNull(elements, index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Byte elementAtOrNull(@Nullable byte[] elements, int index) {
        return Arrayx.getOrNull(elements, index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Short elementAtOrNull(@Nullable short[] elements, int index) {
        return Arrayx.getOrNull(elements, index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Integer elementAtOrNull(@Nullable int[] elements, int index) {
        return Arrayx.getOrNull(elements, index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Long elementAtOrNull(@Nullable long[] elements, int index) {
        return Arrayx.getOrNull(elements, index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Float elementAtOrNull(@Nullable float[] elements, int index) {
        return Arrayx.getOrNull(elements, index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Double elementAtOrNull(@Nullable double[] elements, int index) {
        return Arrayx.getOrNull(elements, index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Boolean elementAtOrNull(@Nullable boolean[] elements, int index) {
        return Arrayx.getOrNull(elements, index);
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this array.
     */
    @Nullable
    public static Character elementAtOrNull(@Nullable char[] elements, int index) {
        return Arrayx.getOrNull(elements, index);
    }


    /* ******************************************* flatMap ******************************************* */


    /**
     * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original array.
     */
    @NotNull
    public static <T, R> List<R> flatMap(@Nullable T[] elements, @NotNull Transformer<T, Iterable<R>> transform) {
        return flatMapTo(elements, new ArrayList<R>(), transform);
    }

    /**
     * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original array.
     */
    @NotNull
    public static <R> List<R> flatMap(@Nullable byte[] elements, @NotNull Transformer<Byte, Iterable<R>> transform) {
        return flatMapTo(elements, new ArrayList<R>(), transform);
    }

    /**
     * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original array.
     */
    @NotNull
    public static <R> List<R> flatMap(@Nullable short[] elements, @NotNull Transformer<Short, Iterable<R>> transform) {
        return flatMapTo(elements, new ArrayList<R>(), transform);
    }

    /**
     * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original array.
     */
    @NotNull
    public static <R> List<R> flatMap(@Nullable int[] elements, @NotNull Transformer<Integer, Iterable<R>> transform) {
        return flatMapTo(elements, new ArrayList<R>(), transform);
    }

    /**
     * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original array.
     */
    @NotNull
    public static <R> List<R> flatMap(@Nullable long[] elements, @NotNull Transformer<Long, Iterable<R>> transform) {
        return flatMapTo(elements, new ArrayList<R>(), transform);
    }

    /**
     * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original array.
     */
    @NotNull
    public static <R> List<R> flatMap(@Nullable float[] elements, @NotNull Transformer<Float, Iterable<R>> transform) {
        return flatMapTo(elements, new ArrayList<R>(), transform);
    }

    /**
     * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original array.
     */
    @NotNull
    public static <R> List<R> flatMap(@Nullable double[] elements, @NotNull Transformer<Double, Iterable<R>> transform) {
        return flatMapTo(elements, new ArrayList<R>(), transform);
    }

    /**
     * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original array.
     */
    @NotNull
    public static <R> List<R> flatMap(@Nullable boolean[] elements, @NotNull Transformer<Boolean, Iterable<R>> transform) {
        return flatMapTo(elements, new ArrayList<R>(), transform);
    }

    /**
     * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original array.
     */
    @NotNull
    public static <R> List<R> flatMap(@Nullable char[] elements, @NotNull Transformer<Character, Iterable<R>> transform) {
        return flatMapTo(elements, new ArrayList<R>(), transform);
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original array, to the given [destination].
     */
    @NotNull
    public static <T, R, C extends Collection<R>> C flatMapTo(@Nullable T[] elements, @NotNull C destination, @NotNull Transformer<T, Iterable<R>> transform) {
        if (elements != null) {
            for (T element : elements) {
                Iterable<R> list = transform.transform(element);
                Collectionx.addAll(destination, list);
            }
        }
        return destination;
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original array, to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C flatMapTo(@Nullable byte[] elements, @NotNull C destination, @NotNull Transformer<Byte, Iterable<R>> transform) {
        if (elements != null) {
            for (byte element : elements) {
                Iterable<R> list = transform.transform(element);
                Collectionx.addAll(destination, list);
            }
        }
        return destination;
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original array, to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C flatMapTo(@Nullable short[] elements, @NotNull C destination, @NotNull Transformer<Short, Iterable<R>> transform) {
        if (elements != null) {
            for (short element : elements) {
                Iterable<R> list = transform.transform(element);
                Collectionx.addAll(destination, list);
            }
        }
        return destination;
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original array, to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C flatMapTo(@Nullable int[] elements, @NotNull C destination, @NotNull Transformer<Integer, Iterable<R>> transform) {
        if (elements != null) {
            for (int element : elements) {
                Iterable<R> list = transform.transform(element);
                Collectionx.addAll(destination, list);
            }
        }
        return destination;
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original array, to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C flatMapTo(@Nullable long[] elements, @NotNull C destination, @NotNull Transformer<Long, Iterable<R>> transform) {
        if (elements != null) {
            for (long element : elements) {
                Iterable<R> list = transform.transform(element);
                Collectionx.addAll(destination, list);
            }
        }
        return destination;
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original array, to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C flatMapTo(@Nullable float[] elements, @NotNull C destination, @NotNull Transformer<Float, Iterable<R>> transform) {
        if (elements != null) {
            for (float element : elements) {
                Iterable<R> list = transform.transform(element);
                Collectionx.addAll(destination, list);
            }
        }
        return destination;
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original array, to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C flatMapTo(@Nullable double[] elements, @NotNull C destination, @NotNull Transformer<Double, Iterable<R>> transform) {
        if (elements != null) {
            for (double element : elements) {
                Iterable<R> list = transform.transform(element);
                Collectionx.addAll(destination, list);
            }
        }
        return destination;
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original array, to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C flatMapTo(@Nullable boolean[] elements, @NotNull C destination, @NotNull Transformer<Boolean, Iterable<R>> transform) {
        if (elements != null) {
            for (boolean element : elements) {
                Iterable<R> list = transform.transform(element);
                Collectionx.addAll(destination, list);
            }
        }
        return destination;
    }

    /**
     * Appends all elements yielded from results of [transform] function being invoked on each element of original array, to the given [destination].
     */
    @NotNull
    public static <R, C extends Collection<R>> C flatMapTo(@Nullable char[] elements, @NotNull C destination, @NotNull Transformer<Character, Iterable<R>> transform) {
        if (elements != null) {
            for (char element : elements) {
                Iterable<R> list = transform.transform(element);
                Collectionx.addAll(destination, list);
            }
        }
        return destination;
    }

    // todo add flatMapIndexed and flatMapIndexedTo method


    /* ******************************************* average ******************************************* */


    /**
     * Returns an average value of elements in the array.
     */
    public static double averageOfByte(@Nullable Byte[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (Byte element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the array.
     */
    public static double averageOfShort(@Nullable Short[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (Short element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the array.
     */
    public static double averageOfInt(@Nullable Integer[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (Integer element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the array.
     */
    public static double averageOfLong(@Nullable Long[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (Long element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the array.
     */
    public static double averageOfFloat(@Nullable Float[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (Float element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the array.
     */
    public static double averageOfDouble(@Nullable Double[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (Double element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the array.
     */
    public static double average(@Nullable byte[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (byte element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the array.
     */
    public static double average(@Nullable short[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (short element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the array.
     */
    public static double average(@Nullable int[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (int element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the array.
     */
    public static double average(@Nullable long[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (long element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the array.
     */
    public static double average(@Nullable float[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (float element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }

    /**
     * Returns an average value of elements in the array.
     */
    public static double average(@Nullable double[] elements) {
        double sum = 0.0d;
        int count = 0;
        if (elements != null) {
            for (double element : elements) {
                sum += element;
                count += 1;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }


    /* ******************************************* none ******************************************* */


    /**
     * Returns `true` if the array has no elements.
     */
    public static <T> boolean none(@Nullable T[] elements) {
        return Arrayx.isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if the array has no elements.
     */
    public static boolean none(@Nullable byte[] elements) {
        return Arrayx.isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if the array has no elements.
     */
    public static boolean none(@Nullable short[] elements) {
        return Arrayx.isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if the array has no elements.
     */
    public static boolean none(@Nullable int[] elements) {
        return Arrayx.isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if the array has no elements.
     */
    public static boolean none(@Nullable long[] elements) {
        return Arrayx.isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if the array has no elements.
     */
    public static boolean none(@Nullable float[] elements) {
        return Arrayx.isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if the array has no elements.
     */
    public static boolean none(@Nullable double[] elements) {
        return Arrayx.isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if the array has no elements.
     */
    public static boolean none(@Nullable boolean[] elements) {
        return Arrayx.isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if the array has no elements.
     */
    public static boolean none(@Nullable char[] elements) {
        return Arrayx.isNullOrEmpty(elements);
    }

    /**
     * Returns `true` if no elements match the given [predicate].
     */
    public static <T> boolean none(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        if (elements != null) for (T element : elements) if (predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if no elements match the given [predicate].
     */
    public static boolean none(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        if (elements != null) for (byte element : elements) if (predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if no elements match the given [predicate].
     */
    public static boolean none(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        if (elements != null) for (short element : elements) if (predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if no elements match the given [predicate].
     */
    public static boolean none(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        if (elements != null) for (int element : elements) if (predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if no elements match the given [predicate].
     */
    public static boolean none(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        if (elements != null) for (long element : elements) if (predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if no elements match the given [predicate].
     */
    public static boolean none(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        if (elements != null) for (float element : elements) if (predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if no elements match the given [predicate].
     */
    public static boolean none(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        if (elements != null) for (double element : elements) if (predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if no elements match the given [predicate].
     */
    public static boolean none(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        if (elements != null) for (boolean element : elements) if (predicate.accept(element)) return false;
        return true;
    }

    /**
     * Returns `true` if no elements match the given [predicate].
     */
    public static boolean none(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        if (elements != null) for (char element : elements) if (predicate.accept(element)) return false;
        return true;
    }


    /* ******************************************* reduce ******************************************* */


    /**
     * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <S, T extends S> S reduce(@Nullable T[] elements, @NotNull Operation<T, S> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        S accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
     */
    public static byte reduce(@Nullable byte[] elements, @NotNull Operation<Byte, Byte> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        byte accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
     */
    public static short reduce(@Nullable short[] elements, @NotNull Operation<Short, Short> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        short accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
     */
    public static int reduce(@Nullable int[] elements, @NotNull Operation<Integer, Integer> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
     */
    public static long reduce(@Nullable long[] elements, @NotNull Operation<Long, Long> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        long accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
     */
    public static float reduce(@Nullable float[] elements, @NotNull Operation<Float, Float> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        float accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
     */
    public static double reduce(@Nullable double[] elements, @NotNull Operation<Double, Double> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        double accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
     */
    public static boolean reduce(@Nullable boolean[] elements, @NotNull Operation<Boolean, Boolean> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        boolean accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
     */
    public static char reduce(@Nullable char[] elements, @NotNull Operation<Character, Character> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        char accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself and calculates the next accumulator value.
     */
    @NotNull
    public static <S, T extends S> S reduceIndexed(@Nullable T[] elements, @NotNull IndexedOperation<T, S> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        S accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(index, accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself and calculates the next accumulator value.
     */
    public static byte reduceIndexed(@Nullable byte[] elements, @NotNull IndexedOperation<Byte, Byte> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        byte accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(index, accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself and calculates the next accumulator value.
     */
    public static short reduceIndexed(@Nullable short[] elements, @NotNull IndexedOperation<Short, Short> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        short accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(index, accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself and calculates the next accumulator value.
     */
    public static int reduceIndexed(@Nullable int[] elements, @NotNull IndexedOperation<Integer, Integer> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(index, accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself and calculates the next accumulator value.
     */
    public static long reduceIndexed(@Nullable long[] elements, @NotNull IndexedOperation<Long, Long> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        long accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(index, accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself and calculates the next accumulator value.
     */
    public static float reduceIndexed(@Nullable float[] elements, @NotNull IndexedOperation<Float, Float> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        float accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(index, accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself and calculates the next accumulator value.
     */
    public static double reduceIndexed(@Nullable double[] elements, @NotNull IndexedOperation<Double, Double> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        double accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(index, accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself and calculates the next accumulator value.
     */
    public static boolean reduceIndexed(@Nullable boolean[] elements, @NotNull IndexedOperation<Boolean, Boolean> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        boolean accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(index, accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with the first element and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself and calculates the next accumulator value.
     */
    public static char reduceIndexed(@Nullable char[] elements, @NotNull IndexedOperation<Character, Character> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        char accumulator = elements[0];
        for (int index = 1, size = elements.length; index < size; index++) {
            accumulator = operation.operation(index, accumulator, elements[index]);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <S, T extends S> S reduceRight(@Nullable T[] elements, @NotNull RightOperation<T, S> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        S accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(elements[index--], accumulator);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
     */
    public static byte reduceRight(@Nullable byte[] elements, @NotNull RightOperation<Byte, Byte> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        byte accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(elements[index--], accumulator);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
     */
    public static short reduceRight(@Nullable short[] elements, @NotNull RightOperation<Short, Short> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        short accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(elements[index--], accumulator);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
     */
    public static int reduceRight(@Nullable int[] elements, @NotNull RightOperation<Integer, Integer> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(elements[index--], accumulator);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
     */
    public static long reduceRight(@Nullable long[] elements, @NotNull RightOperation<Long, Long> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        long accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(elements[index--], accumulator);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
     */
    public static float reduceRight(@Nullable float[] elements, @NotNull RightOperation<Float, Float> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        float accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(elements[index--], accumulator);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
     */
    public static double reduceRight(@Nullable double[] elements, @NotNull RightOperation<Double, Double> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        double accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(elements[index--], accumulator);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
     */
    public static boolean reduceRight(@Nullable boolean[] elements, @NotNull RightOperation<Boolean, Boolean> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        boolean accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(elements[index--], accumulator);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
     */
    public static char reduceRight(@Nullable char[] elements, @NotNull RightOperation<Character, Character> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        char accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(elements[index--], accumulator);
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <S, T extends S> S reduceRightIndexed(@Nullable T[] elements, @NotNull IndexedRightOperation<T, S> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        S accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(index, elements[index], accumulator);
            --index;
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    public static byte reduceRightIndexed(@Nullable byte[] elements, @NotNull IndexedRightOperation<Byte, Byte> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        byte accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(index, elements[index], accumulator);
            --index;
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    public static short reduceRightIndexed(@Nullable short[] elements, @NotNull IndexedRightOperation<Short, Short> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        short accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(index, elements[index], accumulator);
            --index;
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    public static int reduceRightIndexed(@Nullable int[] elements, @NotNull IndexedRightOperation<Integer, Integer> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(index, elements[index], accumulator);
            --index;
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    public static long reduceRightIndexed(@Nullable long[] elements, @NotNull IndexedRightOperation<Long, Long> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        long accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(index, elements[index], accumulator);
            --index;
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    public static float reduceRightIndexed(@Nullable float[] elements, @NotNull IndexedRightOperation<Float, Float> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        float accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(index, elements[index], accumulator);
            --index;
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    public static double reduceRightIndexed(@Nullable double[] elements, @NotNull IndexedRightOperation<Double, Double> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        double accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(index, elements[index], accumulator);
            --index;
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    public static boolean reduceRightIndexed(@Nullable boolean[] elements, @NotNull IndexedRightOperation<Boolean, Boolean> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        boolean accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(index, elements[index], accumulator);
            --index;
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with last element and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    public static char reduceRightIndexed(@Nullable char[] elements, @NotNull IndexedRightOperation<Character, Character> operation) {
        if (Arrayx.isNullOrEmpty(elements)) throw new UnsupportedOperationException("Empty array can't be reduced.");
        int index = count(elements) - 1;
        if (index < 0) throw new UnsupportedOperationException("Empty array can't be reduced.");
        char accumulator = elements[index--];
        while (index >= 0) {
            accumulator = operation.operation(index, elements[index], accumulator);
            --index;
        }
        return accumulator;
    }


    /* ******************************************* fold ******************************************* */


    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <T, R> R fold(@Nullable T[] elements, @NotNull R initial, @NotNull Operation<T, R> operation) {
        R accumulator = initial;
        if (elements != null) for (T element : elements) accumulator = operation.operation(accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <R> R fold(@Nullable byte[] elements, @NotNull R initial, @NotNull Operation<Byte, R> operation) {
        R accumulator = initial;
        if (elements != null) for (byte element : elements) accumulator = operation.operation(accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <R> R fold(@Nullable short[] elements, @NotNull R initial, @NotNull Operation<Short, R> operation) {
        R accumulator = initial;
        if (elements != null) for (short element : elements) accumulator = operation.operation(accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <R> R fold(@Nullable int[] elements, @NotNull R initial, @NotNull Operation<Integer, R> operation) {
        R accumulator = initial;
        if (elements != null) for (int element : elements) accumulator = operation.operation(accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <R> R fold(@Nullable long[] elements, @NotNull R initial, @NotNull Operation<Long, R> operation) {
        R accumulator = initial;
        if (elements != null) for (long element : elements) accumulator = operation.operation(accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <R> R fold(@Nullable float[] elements, @NotNull R initial, @NotNull Operation<Float, R> operation) {
        R accumulator = initial;
        if (elements != null) for (float element : elements) accumulator = operation.operation(accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <R> R fold(@Nullable double[] elements, @NotNull R initial, @NotNull Operation<Double, R> operation) {
        R accumulator = initial;
        if (elements != null) for (double element : elements) accumulator = operation.operation(accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <R> R fold(@Nullable boolean[] elements, @NotNull R initial, @NotNull Operation<Boolean, R> operation) {
        R accumulator = initial;
        if (elements != null) for (boolean element : elements) accumulator = operation.operation(accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
     */
    @NotNull
    public static <R> R fold(@Nullable char[] elements, @NotNull R initial, @NotNull Operation<Character, R> operation) {
        R accumulator = initial;
        if (elements != null) for (char element : elements) accumulator = operation.operation(accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself, and calculates the next accumulator value.
     */
    @NotNull
    public static <T, R> R foldIndexed(@Nullable T[] elements, @NotNull R initial, @NotNull IndexedOperation<T, R> operation) {
        int index = 0;
        R accumulator = initial;
        if (elements != null)
            for (T element : elements) accumulator = operation.operation(index++, accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldIndexed(@Nullable byte[] elements, @NotNull R initial, @NotNull IndexedOperation<Byte, R> operation) {
        int index = 0;
        R accumulator = initial;
        if (elements != null)
            for (byte element : elements) accumulator = operation.operation(index++, accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldIndexed(@Nullable short[] elements, @NotNull R initial, @NotNull IndexedOperation<Short, R> operation) {
        int index = 0;
        R accumulator = initial;
        if (elements != null)
            for (short element : elements) accumulator = operation.operation(index++, accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldIndexed(@Nullable int[] elements, @NotNull R initial, @NotNull IndexedOperation<Integer, R> operation) {
        int index = 0;
        R accumulator = initial;
        if (elements != null)
            for (int element : elements) accumulator = operation.operation(index++, accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldIndexed(@Nullable long[] elements, @NotNull R initial, @NotNull IndexedOperation<Long, R> operation) {
        int index = 0;
        R accumulator = initial;
        if (elements != null)
            for (long element : elements) accumulator = operation.operation(index++, accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldIndexed(@Nullable float[] elements, @NotNull R initial, @NotNull IndexedOperation<Float, R> operation) {
        int index = 0;
        R accumulator = initial;
        if (elements != null)
            for (float element : elements) accumulator = operation.operation(index++, accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldIndexed(@Nullable double[] elements, @NotNull R initial, @NotNull IndexedOperation<Double, R> operation) {
        int index = 0;
        R accumulator = initial;
        if (elements != null)
            for (double element : elements) accumulator = operation.operation(index++, accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldIndexed(@Nullable boolean[] elements, @NotNull R initial, @NotNull IndexedOperation<Boolean, R> operation) {
        int index = 0;
        R accumulator = initial;
        if (elements != null)
            for (boolean element : elements) accumulator = operation.operation(index++, accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from left to right
     * to current accumulator value and each element with its index in the original array.
     *
     * @param operation function that takes the index of an element, current accumulator value
     *                  and the element itself, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldIndexed(@Nullable char[] elements, @NotNull R initial, @NotNull IndexedOperation<Character, R> operation) {
        int index = 0;
        R accumulator = initial;
        if (elements != null)
            for (char element : elements) accumulator = operation.operation(index++, accumulator, element);
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <T, R> R foldRight(@Nullable T[] elements, @NotNull R initial, @NotNull RightOperation<T, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(elements[index--], accumulator);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <R> R foldRight(@Nullable byte[] elements, @NotNull R initial, @NotNull RightOperation<Byte, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(elements[index--], accumulator);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <R> R foldRight(@Nullable short[] elements, @NotNull R initial, @NotNull RightOperation<Short, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(elements[index--], accumulator);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <R> R foldRight(@Nullable int[] elements, @NotNull R initial, @NotNull RightOperation<Integer, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(elements[index--], accumulator);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <R> R foldRight(@Nullable long[] elements, @NotNull R initial, @NotNull RightOperation<Long, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(elements[index--], accumulator);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <R> R foldRight(@Nullable float[] elements, @NotNull R initial, @NotNull RightOperation<Float, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(elements[index--], accumulator);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <R> R foldRight(@Nullable double[] elements, @NotNull R initial, @NotNull RightOperation<Double, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(elements[index--], accumulator);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <R> R foldRight(@Nullable boolean[] elements, @NotNull R initial, @NotNull RightOperation<Boolean, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(elements[index--], accumulator);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
     */
    @NotNull
    public static <R> R foldRight(@Nullable char[] elements, @NotNull R initial, @NotNull RightOperation<Character, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(elements[index--], accumulator);
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <T, R> R foldRightIndexed(@Nullable T[] elements, @NotNull R initial, @NotNull IndexedRightOperation<T, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(index, elements[index], accumulator);
                --index;
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldRightIndexed(@Nullable byte[] elements, @NotNull R initial, @NotNull IndexedRightOperation<Byte, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(index, elements[index], accumulator);
                --index;
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldRightIndexed(@Nullable short[] elements, @NotNull R initial, @NotNull IndexedRightOperation<Short, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(index, elements[index], accumulator);
                --index;
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldRightIndexed(@Nullable int[] elements, @NotNull R initial, @NotNull IndexedRightOperation<Integer, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(index, elements[index], accumulator);
                --index;
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldRightIndexed(@Nullable long[] elements, @NotNull R initial, @NotNull IndexedRightOperation<Long, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(index, elements[index], accumulator);
                --index;
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldRightIndexed(@Nullable float[] elements, @NotNull R initial, @NotNull IndexedRightOperation<Float, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(index, elements[index], accumulator);
                --index;
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldRightIndexed(@Nullable double[] elements, @NotNull R initial, @NotNull IndexedRightOperation<Double, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(index, elements[index], accumulator);
                --index;
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldRightIndexed(@Nullable boolean[] elements, @NotNull R initial, @NotNull IndexedRightOperation<Boolean, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(index, elements[index], accumulator);
                --index;
            }
        }
        return accumulator;
    }

    /**
     * Accumulates value starting with [initial] value and applying [operation] from right to left
     * to each element with its index in the original array and current accumulator value.
     *
     * @param operation function that takes the index of an element, the element itself
     *                  and current accumulator value, and calculates the next accumulator value.
     */
    @NotNull
    public static <R> R foldRightIndexed(@Nullable char[] elements, @NotNull R initial, @NotNull IndexedRightOperation<Character, R> operation) {
        R accumulator = initial;
        if (elements != null) {
            int index = elements.length - 1;
            while (index >= 0) {
                accumulator = operation.operation(index, elements[index], accumulator);
                --index;
            }
        }
        return accumulator;
    }


    /* ******************************************* intersect ******************************************* */


    /**
     * Returns a set containing all elements that are contained by both this set and the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static <T> Set<T> intersect(@Nullable T[] elements, @NotNull Iterable<T> other) {
        Set<T> set = Arrayx.toSet(elements);
        Collectionx.retainAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by both this set and the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Byte> intersect(@Nullable byte[] elements, @NotNull Iterable<Byte> other) {
        Set<Byte> set = Arrayx.toSet(elements);
        Collectionx.retainAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by both this set and the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Short> intersect(@Nullable short[] elements, @NotNull Iterable<Short> other) {
        Set<Short> set = Arrayx.toSet(elements);
        Collectionx.retainAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by both this set and the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Integer> intersect(@Nullable int[] elements, @NotNull Iterable<Integer> other) {
        Set<Integer> set = Arrayx.toSet(elements);
        Collectionx.retainAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by both this set and the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Long> intersect(@Nullable long[] elements, @NotNull Iterable<Long> other) {
        Set<Long> set = Arrayx.toSet(elements);
        Collectionx.retainAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by both this set and the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Float> intersect(@Nullable float[] elements, @NotNull Iterable<Float> other) {
        Set<Float> set = Arrayx.toSet(elements);
        Collectionx.retainAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by both this set and the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Double> intersect(@Nullable double[] elements, @NotNull Iterable<Double> other) {
        Set<Double> set = Arrayx.toSet(elements);
        Collectionx.retainAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by both this set and the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Boolean> intersect(@Nullable boolean[] elements, @NotNull Iterable<Boolean> other) {
        Set<Boolean> set = Arrayx.toSet(elements);
        Collectionx.retainAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by both this set and the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Character> intersect(@Nullable char[] elements, @NotNull Iterable<Character> other) {
        Set<Character> set = Arrayx.toSet(elements);
        Collectionx.retainAll(set, other);
        return set;
    }


    /* ******************************************* subtract ******************************************* */


    /**
     * Returns a set containing all elements that are contained by this array and not contained by the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static <T> Set<T> subtract(@Nullable T[] elements, @NotNull Iterable<T> other) {
        Set<T> set = Arrayx.toSet(elements);
        Collectionx.removeAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by this array and not contained by the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Byte> subtract(@Nullable byte[] elements, @NotNull Iterable<Byte> other) {
        Set<Byte> set = Arrayx.toSet(elements);
        Collectionx.removeAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by this array and not contained by the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Short> subtract(@Nullable short[] elements, @NotNull Iterable<Short> other) {
        Set<Short> set = Arrayx.toSet(elements);
        Collectionx.removeAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by this array and not contained by the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Integer> subtract(@Nullable int[] elements, @NotNull Iterable<Integer> other) {
        Set<Integer> set = Arrayx.toSet(elements);
        Collectionx.removeAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by this array and not contained by the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Long> subtract(@Nullable long[] elements, @NotNull Iterable<Long> other) {
        Set<Long> set = Arrayx.toSet(elements);
        Collectionx.removeAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by this array and not contained by the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Float> subtract(@Nullable float[] elements, @NotNull Iterable<Float> other) {
        Set<Float> set = Arrayx.toSet(elements);
        Collectionx.removeAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by this array and not contained by the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Double> subtract(@Nullable double[] elements, @NotNull Iterable<Double> other) {
        Set<Double> set = Arrayx.toSet(elements);
        Collectionx.removeAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by this array and not contained by the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Boolean> subtract(@Nullable boolean[] elements, @NotNull Iterable<Boolean> other) {
        Set<Boolean> set = Arrayx.toSet(elements);
        Collectionx.removeAll(set, other);
        return set;
    }

    /**
     * Returns a set containing all elements that are contained by this array and not contained by the specified collection.
     * <p>
     * The returned set preserves the element iteration order of the original array.
     */
    @NotNull
    public static Set<Character> subtract(@Nullable char[] elements, @NotNull Iterable<Character> other) {
        Set<Character> set = Arrayx.toSet(elements);
        Collectionx.removeAll(set, other);
        return set;
    }


    /* ******************************************* drop ******************************************* */


    /**
     * Returns a list containing all elements except first [n] elements.
     */
    @NotNull
    public static <T> List<T> drop(@Nullable T[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return takeLast(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except first [n] elements.
     */
    @NotNull
    public static List<Byte> drop(@Nullable byte[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return takeLast(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except first [n] elements.
     */
    @NotNull
    public static List<Short> drop(@Nullable short[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return takeLast(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except first [n] elements.
     */
    @NotNull
    public static List<Integer> drop(@Nullable int[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return takeLast(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except first [n] elements.
     */
    @NotNull
    public static List<Long> drop(@Nullable long[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return takeLast(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except first [n] elements.
     */
    @NotNull
    public static List<Float> drop(@Nullable float[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return takeLast(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except first [n] elements.
     */
    @NotNull
    public static List<Double> drop(@Nullable double[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return takeLast(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except first [n] elements.
     */
    @NotNull
    public static List<Boolean> drop(@Nullable boolean[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return takeLast(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except first [n] elements.
     */
    @NotNull
    public static List<Character> drop(@Nullable char[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return takeLast(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except last [n] elements.
     */
    @NotNull
    public static <T> List<T> dropLast(@Nullable T[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return take(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except last [n] elements.
     */
    @NotNull
    public static List<Byte> dropLast(@Nullable byte[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return take(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except last [n] elements.
     */
    @NotNull
    public static List<Short> dropLast(@Nullable short[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return take(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except last [n] elements.
     */
    @NotNull
    public static List<Integer> dropLast(@Nullable int[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return take(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except last [n] elements.
     */
    @NotNull
    public static List<Long> dropLast(@Nullable long[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return take(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except last [n] elements.
     */
    @NotNull
    public static List<Float> dropLast(@Nullable float[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return take(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except last [n] elements.
     */
    @NotNull
    public static List<Double> dropLast(@Nullable double[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return take(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except last [n] elements.
     */
    @NotNull
    public static List<Boolean> dropLast(@Nullable boolean[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return take(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except last [n] elements.
     */
    @NotNull
    public static List<Character> dropLast(@Nullable char[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        int dropSize = count(elements) - n;
        return take(elements, Math.max(dropSize, 0));
    }

    /**
     * Returns a list containing all elements except last elements that satisfy the given [predicate].
     */
    @NotNull
    public static <T> List<T> dropLastWhile(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return take(elements, index + 1);
                }
            }
        }
        return Collectionx.arrayListOf();
    }

    /**
     * Returns a list containing all elements except last elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Byte> dropLastWhile(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return take(elements, index + 1);
                }
            }
        }
        return Collectionx.arrayListOf();
    }

    /**
     * Returns a list containing all elements except last elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Short> dropLastWhile(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return take(elements, index + 1);
                }
            }
        }
        return Collectionx.arrayListOf();
    }

    /**
     * Returns a list containing all elements except last elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Integer> dropLastWhile(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return take(elements, index + 1);
                }
            }
        }
        return Collectionx.arrayListOf();
    }

    /**
     * Returns a list containing all elements except last elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Long> dropLastWhile(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return take(elements, index + 1);
                }
            }
        }
        return Collectionx.arrayListOf();
    }

    /**
     * Returns a list containing all elements except last elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Float> dropLastWhile(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return take(elements, index + 1);
                }
            }
        }
        return Collectionx.arrayListOf();
    }

    /**
     * Returns a list containing all elements except last elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Double> dropLastWhile(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return take(elements, index + 1);
                }
            }
        }
        return Collectionx.arrayListOf();
    }

    /**
     * Returns a list containing all elements except last elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Boolean> dropLastWhile(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return take(elements, index + 1);
                }
            }
        }
        return Collectionx.arrayListOf();
    }

    /**
     * Returns a list containing all elements except last elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Character> dropLastWhile(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        if (elements != null) {
            for (int index = count(elements) - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return take(elements, index + 1);
                }
            }
        }
        return Collectionx.arrayListOf();
    }

    /**
     * Returns a list containing all elements except first elements that satisfy the given [predicate].
     */
    @NotNull
    public static <T> List<T> dropWhile(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        List<T> list = new ArrayList<>();
        if (elements != null) {
            boolean yielding = false;
            for (T item : elements) {
                if (yielding) {
                    list.add(item);
                } else if (!predicate.accept(item)) {
                    list.add(item);
                    yielding = true;
                }
            }
        }
        return list;
    }

    /**
     * Returns a list containing all elements except first elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Byte> dropWhile(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        List<Byte> list = new ArrayList<>();
        if (elements != null) {
            boolean yielding = false;
            for (byte item : elements) {
                if (yielding) {
                    list.add(item);
                } else if (!predicate.accept(item)) {
                    list.add(item);
                    yielding = true;
                }
            }
        }
        return list;
    }

    /**
     * Returns a list containing all elements except first elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Short> dropWhile(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        List<Short> list = new ArrayList<>();
        if (elements != null) {
            boolean yielding = false;
            for (short item : elements) {
                if (yielding) {
                    list.add(item);
                } else if (!predicate.accept(item)) {
                    list.add(item);
                    yielding = true;
                }
            }
        }
        return list;
    }

    /**
     * Returns a list containing all elements except first elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Integer> dropWhile(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        List<Integer> list = new ArrayList<>();
        if (elements != null) {
            boolean yielding = false;
            for (int item : elements) {
                if (yielding) {
                    list.add(item);
                } else if (!predicate.accept(item)) {
                    list.add(item);
                    yielding = true;
                }
            }
        }
        return list;
    }

    /**
     * Returns a list containing all elements except first elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Long> dropWhile(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        List<Long> list = new ArrayList<>();
        if (elements != null) {
            boolean yielding = false;
            for (long item : elements) {
                if (yielding) {
                    list.add(item);
                } else if (!predicate.accept(item)) {
                    list.add(item);
                    yielding = true;
                }
            }
        }
        return list;
    }

    /**
     * Returns a list containing all elements except first elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Float> dropWhile(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        List<Float> list = new ArrayList<>();
        if (elements != null) {
            boolean yielding = false;
            for (float item : elements) {
                if (yielding) {
                    list.add(item);
                } else if (!predicate.accept(item)) {
                    list.add(item);
                    yielding = true;
                }
            }
        }
        return list;
    }

    /**
     * Returns a list containing all elements except first elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Double> dropWhile(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        List<Double> list = new ArrayList<>();
        if (elements != null) {
            boolean yielding = false;
            for (double item : elements) {
                if (yielding) {
                    list.add(item);
                } else if (!predicate.accept(item)) {
                    list.add(item);
                    yielding = true;
                }
            }
        }
        return list;
    }

    /**
     * Returns a list containing all elements except first elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Boolean> dropWhile(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        List<Boolean> list = new ArrayList<>();
        if (elements != null) {
            boolean yielding = false;
            for (boolean item : elements) {
                if (yielding) {
                    list.add(item);
                } else if (!predicate.accept(item)) {
                    list.add(item);
                    yielding = true;
                }
            }
        }
        return list;
    }

    /**
     * Returns a list containing all elements except first elements that satisfy the given [predicate].
     */
    @NotNull
    public static List<Character> dropWhile(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        List<Character> list = new ArrayList<>();
        if (elements != null) {
            boolean yielding = false;
            for (char item : elements) {
                if (yielding) {
                    list.add(item);
                } else if (!predicate.accept(item)) {
                    list.add(item);
                    yielding = true;
                }
            }
        }
        return list;
    }


    /* ******************************************* take ******************************************* */


    /**
     * Returns a list containing first [n] elements.
     */
    @NotNull
    public static <T> List<T> take(@Nullable T[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        if (n >= elements.length) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[0]);
        int count = 0;
        List<T> list = new ArrayList<>(n);
        for (T item : elements) {
            if (count++ == n)
                break;
            list.add(item);
        }
        return list;
    }

    /**
     * Returns a list containing first [n] elements.
     */
    @NotNull
    public static List<Byte> take(@Nullable byte[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        if (n >= elements.length) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[0]);
        int count = 0;
        List<Byte> list = new ArrayList<>(n);
        for (byte item : elements) {
            if (count++ == n)
                break;
            list.add(item);
        }
        return list;
    }

    /**
     * Returns a list containing first [n] elements.
     */
    @NotNull
    public static List<Short> take(@Nullable short[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        if (n >= elements.length) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[0]);
        int count = 0;
        List<Short> list = new ArrayList<>(n);
        for (short item : elements) {
            if (count++ == n)
                break;
            list.add(item);
        }
        return list;
    }

    /**
     * Returns a list containing first [n] elements.
     */
    @NotNull
    public static List<Integer> take(@Nullable int[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        if (n >= elements.length) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[0]);
        int count = 0;
        List<Integer> list = new ArrayList<>(n);
        for (int item : elements) {
            if (count++ == n)
                break;
            list.add(item);
        }
        return list;
    }

    /**
     * Returns a list containing first [n] elements.
     */
    @NotNull
    public static List<Long> take(@Nullable long[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        if (n >= elements.length) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[0]);
        int count = 0;
        List<Long> list = new ArrayList<>(n);
        for (long item : elements) {
            if (count++ == n)
                break;
            list.add(item);
        }
        return list;
    }

    /**
     * Returns a list containing first [n] elements.
     */
    @NotNull
    public static List<Float> take(@Nullable float[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        if (n >= elements.length) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[0]);
        int count = 0;
        List<Float> list = new ArrayList<>(n);
        for (float item : elements) {
            if (count++ == n)
                break;
            list.add(item);
        }
        return list;
    }

    /**
     * Returns a list containing first [n] elements.
     */
    @NotNull
    public static List<Double> take(@Nullable double[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        if (n >= elements.length) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[0]);
        int count = 0;
        List<Double> list = new ArrayList<>(n);
        for (double item : elements) {
            if (count++ == n)
                break;
            list.add(item);
        }
        return list;
    }

    /**
     * Returns a list containing first [n] elements.
     */
    @NotNull
    public static List<Boolean> take(@Nullable boolean[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        if (n >= elements.length) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[0]);
        int count = 0;
        List<Boolean> list = new ArrayList<>(n);
        for (boolean item : elements) {
            if (count++ == n)
                break;
            list.add(item);
        }
        return list;
    }

    /**
     * Returns a list containing first [n] elements.
     */
    @NotNull
    public static List<Character> take(@Nullable char[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        if (n >= elements.length) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[0]);
        int count = 0;
        List<Character> list = new ArrayList<>(n);
        for (char item : elements) {
            if (count++ == n)
                break;
            list.add(item);
        }
        return list;
    }

    /**
     * Returns a list containing last [n] elements.
     */
    @NotNull
    public static <T> List<T> takeLast(@Nullable T[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        int size = elements.length;
        if (n >= size) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[size - 1]);
        List<T> list = new ArrayList<>(n);
        for (int index = size - n; index < size; index++)
            list.add(elements[index]);
        return list;
    }

    /**
     * Returns a list containing last [n] elements.
     */
    @NotNull
    public static List<Byte> takeLast(@Nullable byte[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        int size = elements.length;
        if (n >= size) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[size - 1]);
        List<Byte> list = new ArrayList<>(n);
        for (int index = size - n; index < size; index++)
            list.add(elements[index]);
        return list;
    }

    /**
     * Returns a list containing last [n] elements.
     */
    @NotNull
    public static List<Short> takeLast(@Nullable short[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        int size = elements.length;
        if (n >= size) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[size - 1]);
        List<Short> list = new ArrayList<>(n);
        for (int index = size - n; index < size; index++)
            list.add(elements[index]);
        return list;
    }

    /**
     * Returns a list containing last [n] elements.
     */
    @NotNull
    public static List<Integer> takeLast(@Nullable int[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        int size = elements.length;
        if (n >= size) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[size - 1]);
        List<Integer> list = new ArrayList<>(n);
        for (int index = size - n; index < size; index++)
            list.add(elements[index]);
        return list;
    }

    /**
     * Returns a list containing last [n] elements.
     */
    @NotNull
    public static List<Long> takeLast(@Nullable long[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        int size = elements.length;
        if (n >= size) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[size - 1]);
        List<Long> list = new ArrayList<>(n);
        for (int index = size - n; index < size; index++)
            list.add(elements[index]);
        return list;
    }

    /**
     * Returns a list containing last [n] elements.
     */
    @NotNull
    public static List<Float> takeLast(@Nullable float[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        int size = elements.length;
        if (n >= size) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[size - 1]);
        List<Float> list = new ArrayList<>(n);
        for (int index = size - n; index < size; index++)
            list.add(elements[index]);
        return list;
    }

    /**
     * Returns a list containing last [n] elements.
     */
    @NotNull
    public static List<Double> takeLast(@Nullable double[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        int size = elements.length;
        if (n >= size) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[size - 1]);
        List<Double> list = new ArrayList<>(n);
        for (int index = size - n; index < size; index++)
            list.add(elements[index]);
        return list;
    }

    /**
     * Returns a list containing last [n] elements.
     */
    @NotNull
    public static List<Boolean> takeLast(@Nullable boolean[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        int size = elements.length;
        if (n >= size) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[size - 1]);
        List<Boolean> list = new ArrayList<>(n);
        for (int index = size - n; index < size; index++)
            list.add(elements[index]);
        return list;
    }

    /**
     * Returns a list containing last [n] elements.
     */
    @NotNull
    public static List<Character> takeLast(@Nullable char[] elements, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Param 'n' is less than zero.");
        }
        if (elements == null || n == 0) return Collectionx.arrayListOf();
        int size = elements.length;
        if (n >= size) return Arrayx.toList(elements);
        if (n == 1) return Collectionx.mutableListOf(elements[size - 1]);
        List<Character> list = new ArrayList<>(n);
        for (int index = size - n; index < size; index++)
            list.add(elements[index]);
        return list;
    }

    /**
     * Returns a list containing last elements satisfying the given [predicate].
     */
    @NotNull
    public static <T> List<T> takeLastWhile(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        if (elements != null) {
            for (int index = elements.length - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return drop(elements, index + 1);
                }
            }
        }
        return Arrayx.toList(elements);
    }

    /**
     * Returns a list containing last elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Byte> takeLastWhile(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        if (elements != null) {
            for (int index = elements.length - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return drop(elements, index + 1);
                }
            }
        }
        return Arrayx.toList(elements);
    }

    /**
     * Returns a list containing last elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Short> takeLastWhile(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        if (elements != null) {
            for (int index = elements.length - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return drop(elements, index + 1);
                }
            }
        }
        return Arrayx.toList(elements);
    }

    /**
     * Returns a list containing last elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Integer> takeLastWhile(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        if (elements != null) {
            for (int index = elements.length - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return drop(elements, index + 1);
                }
            }
        }
        return Arrayx.toList(elements);
    }

    /**
     * Returns a list containing last elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Long> takeLastWhile(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        if (elements != null) {
            for (int index = elements.length - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return drop(elements, index + 1);
                }
            }
        }
        return Arrayx.toList(elements);
    }

    /**
     * Returns a list containing last elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Float> takeLastWhile(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        if (elements != null) {
            for (int index = elements.length - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return drop(elements, index + 1);
                }
            }
        }
        return Arrayx.toList(elements);
    }

    /**
     * Returns a list containing last elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Double> takeLastWhile(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        if (elements != null) {
            for (int index = elements.length - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return drop(elements, index + 1);
                }
            }
        }
        return Arrayx.toList(elements);
    }

    /**
     * Returns a list containing last elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Boolean> takeLastWhile(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        if (elements != null) {
            for (int index = elements.length - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return drop(elements, index + 1);
                }
            }
        }
        return Arrayx.toList(elements);
    }

    /**
     * Returns a list containing last elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Character> takeLastWhile(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        if (elements != null) {
            for (int index = elements.length - 1; index >= 0; index--) {
                if (!predicate.accept(elements[index])) {
                    return drop(elements, index + 1);
                }
            }
        }
        return Arrayx.toList(elements);
    }

    /**
     * Returns a list containing first elements satisfying the given [predicate].
     */
    @NotNull
    public static <T> List<T> takeWhile(@Nullable T[] elements, @NotNull Predicate<T> predicate) {
        List<T> list = new ArrayList<>();
        if (elements != null) {
            for (T item : elements) {
                if (!predicate.accept(item))
                    break;
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Returns a list containing first elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Byte> takeWhile(@Nullable byte[] elements, @NotNull Predicate<Byte> predicate) {
        List<Byte> list = new ArrayList<>();
        if (elements != null) {
            for (byte item : elements) {
                if (!predicate.accept(item))
                    break;
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Returns a list containing first elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Short> takeWhile(@Nullable short[] elements, @NotNull Predicate<Short> predicate) {
        List<Short> list = new ArrayList<>();
        if (elements != null) {
            for (short item : elements) {
                if (!predicate.accept(item))
                    break;
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Returns a list containing first elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Integer> takeWhile(@Nullable int[] elements, @NotNull Predicate<Integer> predicate) {
        List<Integer> list = new ArrayList<>();
        if (elements != null) {
            for (int item : elements) {
                if (!predicate.accept(item))
                    break;
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Returns a list containing first elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Long> takeWhile(@Nullable long[] elements, @NotNull Predicate<Long> predicate) {
        List<Long> list = new ArrayList<>();
        if (elements != null) {
            for (long item : elements) {
                if (!predicate.accept(item))
                    break;
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Returns a list containing first elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Float> takeWhile(@Nullable float[] elements, @NotNull Predicate<Float> predicate) {
        List<Float> list = new ArrayList<>();
        if (elements != null) {
            for (float item : elements) {
                if (!predicate.accept(item))
                    break;
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Returns a list containing first elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Double> takeWhile(@Nullable double[] elements, @NotNull Predicate<Double> predicate) {
        List<Double> list = new ArrayList<>();
        if (elements != null) {
            for (double item : elements) {
                if (!predicate.accept(item))
                    break;
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Returns a list containing first elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Boolean> takeWhile(@Nullable boolean[] elements, @NotNull Predicate<Boolean> predicate) {
        List<Boolean> list = new ArrayList<>();
        if (elements != null) {
            for (boolean item : elements) {
                if (!predicate.accept(item))
                    break;
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Returns a list containing first elements satisfying the given [predicate].
     */
    @NotNull
    public static List<Character> takeWhile(@Nullable char[] elements, @NotNull Predicate<Character> predicate) {
        List<Character> list = new ArrayList<>();
        if (elements != null) {
            for (char item : elements) {
                if (!predicate.accept(item))
                    break;
                list.add(item);
            }
        }
        return list;
    }


    /* ******************************************* distinct ******************************************* */


    /**
     * Returns a list containing only distinct elements from the given array.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static <T> List<T> distinct(@Nullable T[] elements) {
        return Collectionx.toList(Arrayx.toSet(elements));
    }

    /**
     * Returns a list containing only distinct elements from the given array.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static List<Byte> distinct(@Nullable byte[] elements) {
        return Collectionx.toList(Arrayx.toSet(elements));
    }

    /**
     * Returns a list containing only distinct elements from the given array.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static List<Short> distinct(@Nullable short[] elements) {
        return Collectionx.toList(Arrayx.toSet(elements));
    }

    /**
     * Returns a list containing only distinct elements from the given array.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static List<Integer> distinct(@Nullable int[] elements) {
        return Collectionx.toList(Arrayx.toSet(elements));
    }

    /**
     * Returns a list containing only distinct elements from the given array.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static List<Long> distinct(@Nullable long[] elements) {
        return Collectionx.toList(Arrayx.toSet(elements));
    }

    /**
     * Returns a list containing only distinct elements from the given array.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static List<Float> distinct(@Nullable float[] elements) {
        return Collectionx.toList(Arrayx.toSet(elements));
    }

    /**
     * Returns a list containing only distinct elements from the given array.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static List<Double> distinct(@Nullable double[] elements) {
        return Collectionx.toList(Arrayx.toSet(elements));
    }

    /**
     * Returns a list containing only distinct elements from the given array.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static List<Boolean> distinct(@Nullable boolean[] elements) {
        return Collectionx.toList(Arrayx.toSet(elements));
    }

    /**
     * Returns a list containing only distinct elements from the given array.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static List<Character> distinct(@Nullable char[] elements) {
        return Collectionx.toList(Arrayx.toSet(elements));
    }

    /**
     * Returns a list containing only elements from the given array
     * having distinct keys returned by the given [selector] function.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static <T, K> List<T> distinctBy(@Nullable T[] elements, @NotNull Transformer<T, K> transformer) {
        List<T> list = new ArrayList<>();
        if (elements != null) {
            HashSet<K> set = new HashSet<>();
            for (T e : elements) {
                K key = transformer.transform(e);
                if (set.add(key)) list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a list containing only elements from the given array
     * having distinct keys returned by the given [selector] function.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static <K> List<Byte> distinctBy(@Nullable byte[] elements, @NotNull Transformer<Byte, K> transformer) {
        List<Byte> list = new ArrayList<>();
        if (elements != null) {
            HashSet<K> set = new HashSet<>();
            for (byte e : elements) {
                K key = transformer.transform(e);
                if (set.add(key)) list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a list containing only elements from the given array
     * having distinct keys returned by the given [selector] function.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static <K> List<Short> distinctBy(@Nullable short[] elements, @NotNull Transformer<Short, K> transformer) {
        List<Short> list = new ArrayList<>();
        if (elements != null) {
            HashSet<K> set = new HashSet<>();
            for (short e : elements) {
                K key = transformer.transform(e);
                if (set.add(key)) list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a list containing only elements from the given array
     * having distinct keys returned by the given [selector] function.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static <K> List<Integer> distinctBy(@Nullable int[] elements, @NotNull Transformer<Integer, K> transformer) {
        List<Integer> list = new ArrayList<>();
        if (elements != null) {
            HashSet<K> set = new HashSet<>();
            for (int e : elements) {
                K key = transformer.transform(e);
                if (set.add(key)) list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a list containing only elements from the given array
     * having distinct keys returned by the given [selector] function.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static <K> List<Long> distinctBy(@Nullable long[] elements, @NotNull Transformer<Long, K> transformer) {
        List<Long> list = new ArrayList<>();
        if (elements != null) {
            HashSet<K> set = new HashSet<>();
            for (long e : elements) {
                K key = transformer.transform(e);
                if (set.add(key)) list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a list containing only elements from the given array
     * having distinct keys returned by the given [selector] function.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static <K> List<Float> distinctBy(@Nullable float[] elements, @NotNull Transformer<Float, K> transformer) {
        List<Float> list = new ArrayList<>();
        if (elements != null) {
            HashSet<K> set = new HashSet<>();
            for (float e : elements) {
                K key = transformer.transform(e);
                if (set.add(key)) list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a list containing only elements from the given array
     * having distinct keys returned by the given [selector] function.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static <K> List<Double> distinctBy(@Nullable double[] elements, @NotNull Transformer<Double, K> transformer) {
        List<Double> list = new ArrayList<>();
        if (elements != null) {
            HashSet<K> set = new HashSet<>();
            for (double e : elements) {
                K key = transformer.transform(e);
                if (set.add(key)) list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a list containing only elements from the given array
     * having distinct keys returned by the given [selector] function.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static <K> List<Boolean> distinctBy(@Nullable boolean[] elements, @NotNull Transformer<Boolean, K> transformer) {
        List<Boolean> list = new ArrayList<>();
        if (elements != null) {
            HashSet<K> set = new HashSet<>();
            for (boolean e : elements) {
                K key = transformer.transform(e);
                if (set.add(key)) list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a list containing only elements from the given array
     * having distinct keys returned by the given [selector] function.
     * <p>
     * The elements in the resulting list are in the same order as they were in the source array.
     */
    @NotNull
    public static <K> List<Character> distinctBy(@Nullable char[] elements, @NotNull Transformer<Character, K> transformer) {
        List<Character> list = new ArrayList<>();
        if (elements != null) {
            HashSet<K> set = new HashSet<>();
            for (char e : elements) {
                K key = transformer.transform(e);
                if (set.add(key)) list.add(e);
            }
        }
        return list;
    }


    /* ******************************************* withIndex ******************************************* */


    /**
     * Returns a lazy [Iterable] of [IndexedValue] for each element of the original array.
     */
    public static <T> Iterable<IndexedValue<T>> withIndex(@Nullable final T[] elements) {
        return new IndexingIterable<>(new DefaultValue<Iterator<T>>() {
            @NotNull
            @Override
            public Iterator<T> get() {
                return Arrayx.iterator(elements);
            }
        });
    }

    /**
     * Returns a lazy [Iterable] of [IndexedValue] for each element of the original array.
     */
    @NotNull
    public static Iterable<IndexedValue<Byte>> withIndex(@Nullable final byte[] elements) {
        return new IndexingIterable<>(new DefaultValue<Iterator<Byte>>() {
            @NotNull
            @Override
            public Iterator<Byte> get() {
                return Arrayx.iterator(elements);
            }
        });
    }

    /**
     * Returns a lazy [Iterable] of [IndexedValue] for each element of the original array.
     */
    @NotNull
    public static Iterable<IndexedValue<Short>> withIndex(@Nullable final short[] elements) {
        return new IndexingIterable<>(new DefaultValue<Iterator<Short>>() {
            @NotNull
            @Override
            public Iterator<Short> get() {
                return Arrayx.iterator(elements);
            }
        });
    }

    /**
     * Returns a lazy [Iterable] of [IndexedValue] for each element of the original array.
     */
    @NotNull
    public static Iterable<IndexedValue<Integer>> withIndex(@Nullable final int[] elements) {
        return new IndexingIterable<>(new DefaultValue<Iterator<Integer>>() {
            @NotNull
            @Override
            public Iterator<Integer> get() {
                return Arrayx.iterator(elements);
            }
        });
    }

    /**
     * Returns a lazy [Iterable] of [IndexedValue] for each element of the original array.
     */
    @NotNull
    public static Iterable<IndexedValue<Long>> withIndex(@Nullable final long[] elements) {
        return new IndexingIterable<>(new DefaultValue<Iterator<Long>>() {
            @NotNull
            @Override
            public Iterator<Long> get() {
                return Arrayx.iterator(elements);
            }
        });
    }

    /**
     * Returns a lazy [Iterable] of [IndexedValue] for each element of the original array.
     */
    @NotNull
    public static Iterable<IndexedValue<Float>> withIndex(@Nullable final float[] elements) {
        return new IndexingIterable<>(new DefaultValue<Iterator<Float>>() {
            @NotNull
            @Override
            public Iterator<Float> get() {
                return Arrayx.iterator(elements);
            }
        });
    }

    /**
     * Returns a lazy [Iterable] of [IndexedValue] for each element of the original array.
     */
    @NotNull
    public static Iterable<IndexedValue<Double>> withIndex(@Nullable final double[] elements) {
        return new IndexingIterable<>(new DefaultValue<Iterator<Double>>() {
            @NotNull
            @Override
            public Iterator<Double> get() {
                return Arrayx.iterator(elements);
            }
        });
    }

    /**
     * Returns a lazy [Iterable] of [IndexedValue] for each element of the original array.
     */
    @NotNull
    public static Iterable<IndexedValue<Boolean>> withIndex(@Nullable final boolean[] elements) {
        return new IndexingIterable<>(new DefaultValue<Iterator<Boolean>>() {
            @NotNull
            @Override
            public Iterator<Boolean> get() {
                return Arrayx.iterator(elements);
            }
        });
    }

    /**
     * Returns a lazy [Iterable] of [IndexedValue] for each element of the original array.
     */
    @NotNull
    public static Iterable<IndexedValue<Character>> withIndex(@Nullable final char[] elements) {
        return new IndexingIterable<>(new DefaultValue<Iterator<Character>>() {
            @NotNull
            @Override
            public Iterator<Character> get() {
                return Arrayx.iterator(elements);
            }
        });
    }
}