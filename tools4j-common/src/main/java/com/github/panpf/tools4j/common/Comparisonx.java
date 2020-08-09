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

package com.github.panpf.tools4j.common;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * Comparison tool method
 */
public class Comparisonx {

    private Comparisonx() {
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */

    public static boolean areEqual(@Nullable Object first, @Nullable Object second) {
        return first == null ? second == null : first.equals(second);
    }

    public static boolean areEqual(@Nullable Double first, @Nullable Double second) {
        return first == null ? second == null : second != null && first.doubleValue() == second.doubleValue();
    }

    public static boolean areEqual(@Nullable Double first, double second) {
        return first != null && first == second;
    }

    public static boolean areEqual(double first, Double second) {
        return second != null && first == second;
    }

    public static boolean areEqual(@Nullable Float first, @Nullable Float second) {
        return first == null ? second == null : second != null && first.floatValue() == second.floatValue();
    }

    public static boolean areEqual(@Nullable Float first, float second) {
        return first != null && first == second;
    }

    public static boolean areEqual(float first, @Nullable Float second) {
        return second != null && first == second;
    }


    /**
     * Compares two nullable [Comparable] values. Null is considered less than any value.
     */
    public static <T extends Comparable<T>> int compareValues(@Nullable T a, @Nullable T b) {
        if (a == b) return 0;
        if (a == null) return -1;
        if (b == null) return 1;

        return a.compareTo(b);
    }

    /**
     * Compares two values using the specified [selector] function to calculate the result of the comparison.
     * The function is applied to the given values [a] and [b] and return [Comparable] objects.
     * The result of comparison of these [Comparable] instances is returned.
     */
    public static <T, R extends Comparable<R>> int compareValuesBy(@Nullable T a, @Nullable T b, NullableAllTransformer<T, R> selector) {
        return compareValues(selector.transform(a), selector.transform(b));
    }

    /**
     * Creates a comparator using the function to transform value to a [Comparable] instance for comparison.
     */
    @NotNull
    public static <T, R extends Comparable<R>> Comparator<T> compareBy(@NotNull final NullableAllTransformer<T, R> selector) {
        return (a, b) -> compareValuesBy(a, b, selector);
    }

    /**
     * Creates a descending comparator using the function to transform value to a [Comparable] instance for comparison.
     */
    @NotNull
    public static <T, R extends Comparable<R>> Comparator<T> compareByDescending(@NotNull final NullableAllTransformer<T, R> selector) {
        return (a, b) -> compareValuesBy(b, a, selector);
    }

    /**
     * Returns a comparator that compares [Comparable] objects in natural order.
     */
    @NotNull
    public static <T extends Comparable<T>> Comparator<T> naturalOrder() {
        //noinspection unchecked
        return (Comparator<T>) NaturalOrderComparator.INSTANCE;
    }

    /**
     * Returns a comparator that compares [Comparable] objects in reversed natural order.
     */
    @NotNull
    public static <T extends Comparable<T>> Comparator<T> reverseOrder() {
        //noinspection unchecked
        return (Comparator<T>) ReverseOrderComparator.INSTANCE;
    }

    public static class NaturalOrderComparator<T extends Comparable<T>> implements Comparator<T> {

        private static final NaturalOrderComparator<?> INSTANCE = new NaturalOrderComparator<>();

        @Override
        public int compare(T a, T b) {
            return a.compareTo(b);
        }

        public Comparator<T> reversed() {
            return new ReverseOrderComparator<>();
        }
    }

    public static class ReverseOrderComparator<T extends Comparable<T>> implements Comparator<T> {

        private static final ReverseOrderComparator<?> INSTANCE = new ReverseOrderComparator<>();

        @Override
        public int compare(T a, T b) {
            return b.compareTo(a);
        }

        public Comparator<T> reversed() {
            return new NaturalOrderComparator<>();
        }
    }

    /**
     * Returns the greater of two values.
     * If values are equal, returns the first one.
     */
    @Nullable
    public static <T extends Comparable<T>> T maxOf(@Nullable T a, @Nullable T b) {
        if (a == b) return a;
        if (a == null) return b;
        if (b == null) return a;
        return a.compareTo(b) >= 0 ? a : b;
    }

    /**
     * Returns the greater of two values.
     */
    public static byte maxOf(byte a, byte b) {
        return (byte) Math.max((int) a, (int) b);
    }

    /**
     * Returns the greater of two values.
     */
    public static short maxOf(short a, short b) {
        return (short) Math.max((int) a, (int) b);
    }

    /**
     * Returns the greater of two values.
     */
    public static int maxOf(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * Returns the greater of two values.
     */
    public static long maxOf(long a, long b) {
        return Math.max(a, b);
    }

    /**
     * Returns the greater of two values.
     */
    public static float maxOf(float a, float b) {
        return Math.max(a, b);
    }

    /**
     * Returns the greater of two values.
     */
    public static double maxOf(double a, double b) {
        return Math.max(a, b);
    }

    /**
     * Returns the greater of three values.
     */
    @Nullable
    public static <T extends Comparable<T>> T maxOf(@Nullable T a, @Nullable T b, @Nullable T c) {
        return maxOf(a, maxOf(b, c));
    }

    /**
     * Returns the greater of three values.
     */
    public static byte maxOf(byte a, byte b, byte c) {
        return (byte) Math.max((int) a, Math.max((int) b, (int) c));
    }

    /**
     * Returns the greater of three values.
     */
    public static short maxOf(short a, short b, short c) {
        return (short) Math.max((int) a, Math.max((int) b, (int) c));
    }

    /**
     * Returns the greater of three values.
     */
    public static int maxOf(int a, int b, int c) {
        return maxOf(a, maxOf(b, c));
    }

    /**
     * Returns the greater of three values.
     */
    public static long maxOf(long a, long b, long c) {
        return maxOf(a, maxOf(b, c));
    }

    /**
     * Returns the greater of three values.
     */
    public static float maxOf(float a, float b, float c) {
        return maxOf(a, maxOf(b, c));
    }

    /**
     * Returns the greater of three values.
     */
    public static double maxOf(double a, double b, double c) {
        return maxOf(a, maxOf(b, c));
    }

    /**
     * Returns the smaller of two values.
     * If values are equal, returns the first one.
     */
    @Nullable
    public static <T extends Comparable<T>> T minOf(@Nullable T a, @Nullable T b) {
        if (a == b) return a;
        if (a == null) return null;
        if (b == null) return null;
        return a.compareTo(b) <= 0 ? a : b;
    }

    /**
     * Returns the smaller of two values.
     */
    public static byte minOf(byte a, byte b) {
        return (byte) Math.min((int) a, (int) b);
    }

    /**
     * Returns the smaller of two values.
     */
    public static short minOf(short a, short b) {
        return (short) Math.min((int) a, (int) b);
    }

    /**
     * Returns the smaller of two values.
     */
    public static int minOf(int a, int b) {
        return Math.min(a, b);
    }

    /**
     * Returns the smaller of two values.
     */
    public static long minOf(long a, long b) {
        return Math.min(a, b);
    }

    /**
     * Returns the smaller of two values.
     */
    public static float minOf(float a, float b) {
        return Math.min(a, b);
    }

    /**
     * Returns the smaller of two values.
     */
    public static double minOf(double a, double b) {
        return Math.min(a, b);
    }

    /**
     * Returns the smaller of three values.
     */
    @Nullable
    public static <T extends Comparable<T>> T minOf(@Nullable T a, @Nullable T b, @Nullable T c) {
        return minOf(a, minOf(b, c));
    }

    /**
     * Returns the smaller of three values.
     */
    public static byte minOf(byte a, byte b, byte c) {
        return (byte) Math.min((int) a, Math.min((int) b, (int) c));
    }

    /**
     * Returns the smaller of three values.
     */
    public static short minOf(short a, short b, short c) {
        return (short) Math.min((int) a, Math.min((int) b, (int) c));
    }

    /**
     * Returns the smaller of three values.
     */
    public static int minOf(int a, int b, int c) {
        return minOf(a, minOf(b, c));
    }

    /**
     * Returns the smaller of three values.
     */
    public static long minOf(long a, long b, long c) {
        return minOf(a, minOf(b, c));
    }

    /**
     * Returns the smaller of three values.
     */
    public static float minOf(float a, float b, float c) {
        return minOf(a, minOf(b, c));
    }

    /**
     * Returns the smaller of three values.
     */
    public static double minOf(double a, double b, double c) {
        return minOf(a, minOf(b, c));
    }
}
