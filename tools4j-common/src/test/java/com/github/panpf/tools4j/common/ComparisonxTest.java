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

import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

public class ComparisonxTest {

    @Test
    public void testAreEqual() {
        Assert.assertTrue(Comparisonx.areEqual("123", "123"));
        Assert.assertTrue(Comparisonx.areEqual(new String(new StringBuilder("123")),
                new String(new StringBuilder("123"))));

        Assert.assertTrue(Comparisonx.areEqual(Double.valueOf("1.2"), Double.valueOf("1.2")));
        Assert.assertTrue(Comparisonx.areEqual(Double.valueOf("1.2"), 1.2));
        Assert.assertTrue(Comparisonx.areEqual(1.2, Double.valueOf("1.2")));
        Assert.assertFalse(Comparisonx.areEqual(Double.valueOf("1.3"), Double.valueOf("1.2")));

        Assert.assertTrue(Comparisonx.areEqual(Float.valueOf("1.2"), Float.valueOf("1.2")));
        Assert.assertTrue(Comparisonx.areEqual(Float.valueOf("1.2"), 1.2f));
        Assert.assertTrue(Comparisonx.areEqual(1.2f, Float.valueOf("1.2")));
        Assert.assertFalse(Comparisonx.areEqual(Float.valueOf("1.3"), Float.valueOf("1.2")));
    }

    @Test
    public void testCompare() {
        Assert.assertEquals(Comparisonx.compareValues(1, 4), -1);
        Assert.assertEquals(Comparisonx.compareValues(6, 4), 1);
        Assert.assertEquals(Comparisonx.compareValues(6, 6), 0);
        Assert.assertEquals(Comparisonx.compareValues(null, 6), -1);
        Assert.assertEquals(Comparisonx.compareValues(6, null), 1);

        NullableAllTransformer<String, Integer> transformer = new NullableAllTransformer<String, Integer>() {
            @Nullable
            @Override
            public Integer transform(@Nullable String s) {
                return s != null ? s.length() : null;
            }
        };

        Assert.assertEquals(Comparisonx.compareValuesBy("1", "1234", transformer), -1);
        Assert.assertEquals(Comparisonx.compareValuesBy("123456", "1234", transformer), 1);
        Assert.assertEquals(Comparisonx.compareValuesBy("123456", "123456", transformer), 0);
        Assert.assertEquals(Comparisonx.compareValuesBy(null, "123456", transformer), -1);
        Assert.assertEquals(Comparisonx.compareValuesBy("123456", null, transformer), 1);

        Comparator<String> comparator = Comparisonx.compareBy(new NullableAllTransformer<String, Comparable>() {
            @Nullable
            @Override
            public Comparable transform(@Nullable String s) {
                return s != null ? s.length() : null;
            }
        });
        Assert.assertEquals(comparator.compare("1", "1234"), -1);
        Assert.assertEquals(comparator.compare("123456", "1234"), 1);
        Assert.assertEquals(comparator.compare("123456", "123456"), 0);
        Assert.assertEquals(comparator.compare(null, "123456"), -1);
        Assert.assertEquals(comparator.compare("123456", null), 1);

        Comparator<String> comparatorDescending = Comparisonx.compareByDescending(new NullableAllTransformer<String, Comparable>() {
            @Nullable
            @Override
            public Comparable transform(@Nullable String s) {
                return s != null ? s.length() : null;
            }
        });
        Assert.assertEquals(comparatorDescending.compare("1", "1234"), 1);
        Assert.assertEquals(comparatorDescending.compare("123456", "1234"), -1);
        Assert.assertEquals(comparatorDescending.compare("123456", "123456"), 0);
        Assert.assertEquals(comparatorDescending.compare(null, "123456"), 1);
        Assert.assertEquals(comparatorDescending.compare("123456", null), -1);
    }

    @Test
    public void testMaxOf() {
        Assert.assertEquals(Comparisonx.maxOf("abcd", "abcdef"), "abcdef");
        Assert.assertEquals(Comparisonx.maxOf("abcdef", "abcd"), "abcdef");

        Assert.assertEquals(Comparisonx.maxOf((byte) 1, (byte) 6), 6);
        Assert.assertEquals(Comparisonx.maxOf((byte) 6, (byte) 1), 6);

        Assert.assertEquals(Comparisonx.maxOf((short) 1, (short) 6), 6);
        Assert.assertEquals(Comparisonx.maxOf((short) 6, (short) 1), 6);

        Assert.assertEquals(Comparisonx.maxOf(1, 6), 6);
        Assert.assertEquals(Comparisonx.maxOf(6, 1), 6);

        Assert.assertEquals(Comparisonx.maxOf(1L, 6L), 6L);
        Assert.assertEquals(Comparisonx.maxOf(6L, 1L), 6L);

        Assert.assertEquals(Comparisonx.maxOf(1f, 6f), 6f, 0f);
        Assert.assertEquals(Comparisonx.maxOf(6f, 1f), 6f, 0f);

        Assert.assertEquals(Comparisonx.maxOf(1D, 6D), 6D, 0D);
        Assert.assertEquals(Comparisonx.maxOf(6D, 1D), 6D, 0D);

        Assert.assertEquals(Comparisonx.maxOf("abcd", "abcde", "abcdef"), "abcdef");
        Assert.assertEquals(Comparisonx.maxOf("abcdef", "abcde", "abcd"), "abcdef");

        Assert.assertEquals(Comparisonx.maxOf((byte) 1, (byte) 4, (byte) 6), 6);
        Assert.assertEquals(Comparisonx.maxOf((byte) 6, (byte) 4, (byte) 1), 6);

        Assert.assertEquals(Comparisonx.maxOf((short) 1, (short) 4, (short) 6), 6);
        Assert.assertEquals(Comparisonx.maxOf((short) 6, (short) 4, (short) 1), 6);

        Assert.assertEquals(Comparisonx.maxOf(1, 4, 6), 6);
        Assert.assertEquals(Comparisonx.maxOf(6, 4, 1), 6);

        Assert.assertEquals(Comparisonx.maxOf(1L, 4L, 6L), 6L);
        Assert.assertEquals(Comparisonx.maxOf(6L, 4L, 1L), 6L);

        Assert.assertEquals(Comparisonx.maxOf(1f, 4f, 6f), 6f, 0f);
        Assert.assertEquals(Comparisonx.maxOf(6f, 4f, 1f), 6f, 0f);

        Assert.assertEquals(Comparisonx.maxOf(1D, 4D, 6D), 6D, 0D);
        Assert.assertEquals(Comparisonx.maxOf(6D, 4D, 1D), 6D, 0D);
    }

    @Test
    public void testMinOf() {
        Assert.assertEquals(Comparisonx.minOf("abcd", "abcdef"), "abcd");
        Assert.assertEquals(Comparisonx.minOf("abcdef", "abcd"), "abcd");

        Assert.assertEquals(Comparisonx.minOf((byte) 1, (byte) 6), 1);
        Assert.assertEquals(Comparisonx.minOf((byte) 6, (byte) 1), 1);

        Assert.assertEquals(Comparisonx.minOf((short) 1, (short) 6), 1);
        Assert.assertEquals(Comparisonx.minOf((short) 6, (short) 1), 1);

        Assert.assertEquals(Comparisonx.minOf(1, 6), 1);
        Assert.assertEquals(Comparisonx.minOf(6, 1), 1);

        Assert.assertEquals(Comparisonx.minOf(1L, 6L), 1L);
        Assert.assertEquals(Comparisonx.minOf(6L, 1L), 1L);

        Assert.assertEquals(Comparisonx.minOf(1f, 6f), 1f, 0f);
        Assert.assertEquals(Comparisonx.minOf(6f, 1f), 1f, 0f);

        Assert.assertEquals(Comparisonx.minOf(1D, 6D), 1D, 0D);
        Assert.assertEquals(Comparisonx.minOf(6D, 1D), 1D, 0D);

        Assert.assertEquals(Comparisonx.minOf("abcd", "abcde", "abcdef"), "abcd");
        Assert.assertEquals(Comparisonx.minOf("abcdef", "abcde", "abcd"), "abcd");

        Assert.assertEquals(Comparisonx.minOf((byte) 1, (byte) 4, (byte) 6), 1);
        Assert.assertEquals(Comparisonx.minOf((byte) 6, (byte) 4, (byte) 1), 1);

        Assert.assertEquals(Comparisonx.minOf((short) 1, (short) 4, (short) 6), 1);
        Assert.assertEquals(Comparisonx.minOf((short) 6, (short) 4, (short) 1), 1);

        Assert.assertEquals(Comparisonx.minOf(1, 4, 6), 1);
        Assert.assertEquals(Comparisonx.minOf(6, 4, 1), 1);

        Assert.assertEquals(Comparisonx.minOf(1L, 4L, 6L), 1L);
        Assert.assertEquals(Comparisonx.minOf(6L, 4L, 1L), 1L);

        Assert.assertEquals(Comparisonx.minOf(1f, 4f, 6f), 1f, 0f);
        Assert.assertEquals(Comparisonx.minOf(6f, 4f, 1f), 1f, 0f);

        Assert.assertEquals(Comparisonx.minOf(1D, 4D, 6D), 1D, 0D);
        Assert.assertEquals(Comparisonx.minOf(6D, 4D, 1D), 1D, 0D);
    }
}