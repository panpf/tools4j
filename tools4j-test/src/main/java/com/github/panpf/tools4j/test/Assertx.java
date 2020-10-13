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

package com.github.panpf.tools4j.test;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;

public class Assertx {

    private Assertx() {

    }


    public static void assertTwoEquals(@Nullable String message, @Nullable Object expected, @Nullable Object actual0, @Nullable Object actual1) {
        Assert.assertEquals(message + "_actual0", expected, actual0);
        Assert.assertEquals(message + "_actual1", expected, actual1);
    }

    public static void assertTwoEquals(@Nullable Object expected, @Nullable Object actual0, @Nullable Object actual1) {
        Assert.assertEquals("actual0", expected, actual0);
        Assert.assertEquals("actual1", expected, actual1);
    }


    public static void assertAllNull(Object... values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                Assert.fail("The index is " + i + " value is not null");
            }
        }
    }

    public static void assertAllNotNull(Object... values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                Assert.fail("The index is " + i + " value is null");
            }
        }
    }


    public static void assertThrow(@Nullable String message, @Nullable Class<? extends Throwable> expectedTrowType,
                                   @NotNull Runnable block) {
        try {
            block.run();
            StringBuilder builder = new StringBuilder();
            builder.append("No throw. ");
            if (expectedTrowType != null) {
                if (builder.length() > 10) builder.append(", ");
                builder.append("expectedThrow='").append(expectedTrowType.getName()).append("'");
            }
            if (message != null) {
                if (builder.length() > 10) builder.append(", ");
                builder.append("message='").append(message).append("'");
            }
            Assert.fail(builder.toString());
        } catch (Throwable e) {
            if (expectedTrowType != null && !e.getClass().equals(expectedTrowType)) {
                StringBuilder builder = new StringBuilder();
                builder.append("Throw type error. ");
                builder.append("expectedThrow='").append(expectedTrowType.getName()).append("'");
                builder.append(", actualThrow='").append(e.getClass().getName()).append("'");
                if (message != null) {
                    builder.append(", message='").append(message).append("'");
                }
                Assert.fail(builder.toString());
            }
        }
    }

    public static void assertThrow(@Nullable Class<? extends Throwable> expectedTrowType, @NotNull Runnable block) {
        assertThrow(null, expectedTrowType, block);
    }

    public static void assertThrow(@Nullable String message, @NotNull Runnable block) {
        assertThrow(message, null, block);
    }

    public static void assertThrow(@NotNull Runnable block) {
        assertThrow(null, null, block);
    }


    public static void assertNoThrow(@Nullable String message, @NotNull Runnable block) {
        try {
            block.run();
        } catch (Throwable e) {
            e.printStackTrace();
            StringBuilder builder = new StringBuilder();
            builder.append("Throw. ");
            builder.append(e.toString());
            if (message != null) {
                builder.append("message='$message'");
            }
            Assert.fail(builder.toString());
        }
    }

    public static void assertNoThrow(@NotNull Runnable block) {
        assertNoThrow(null, block);
    }


    public static void assertTwoThrow(@Nullable String message, @Nullable Class<? extends Throwable> expectedTrowType, @NotNull Runnable block0, @NotNull Runnable block1) {
        assertThrow(message, expectedTrowType, block0);
        assertThrow(message, expectedTrowType, block1);
    }

    public static void assertTwoThrow(@Nullable Class<? extends Throwable> expectedTrowType, @NotNull Runnable block0, @NotNull Runnable block1) {
        assertThrow(null, expectedTrowType, block0);
        assertThrow(null, expectedTrowType, block1);
    }

    public static void assertTwoThrow(@Nullable String message, @NotNull Runnable block0, @NotNull Runnable block1) {
        assertThrow(message, null, block0);
        assertThrow(message, null, block1);
    }

    public static void assertTwoThrow(@NotNull Runnable block0, @NotNull Runnable block1) {
        assertThrow(null, null, block0);
        assertThrow(null, null, block1);
    }


    public static void assertTwoNoThrow(@Nullable String message, @NotNull Runnable block0, @NotNull Runnable block1) {
        assertNoThrow(message + "_block0", block0);
        assertNoThrow(message + "_block1", block1);
    }

    public static void assertTwoNoThrow(@NotNull Runnable block0, @NotNull Runnable block1) {
        assertNoThrow("block0", block0);
        assertNoThrow("block1", block1);
    }
}
