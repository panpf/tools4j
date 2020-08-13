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

package com.github.panpf.tools4j.premise;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Premise tool method
 */
public class Premisex {

    private Premisex() {
    }


    /* ******************************************* Expression *******************************************/

    /**
     * Throws an [IllegalArgumentException] with the result of calling [lazyMessage] if the [value] is false.
     */
    public static void require(boolean value, @NotNull LazyValue<String> lazyMessage) {
        if (!value) throw new IllegalArgumentException(lazyMessage.get());
    }

    /**
     * Throws an [IllegalArgumentException] if the [value] is false.
     */
    public static void require(boolean value) {
        require(value, new LazyValue<String>() {
            @NotNull
            @Override
            public String get() {
                return "Failed requirement.";
            }
        });
    }


    /**
     * Throws an [IllegalStateException] with the result of calling [lazyMessage] if the [value] is false.
     */
    public static void check(boolean value, @NotNull LazyValue<String> lazyMessage) {
        if (!value) throw new IllegalStateException(lazyMessage.get());
    }

    /**
     * Throws an [IllegalStateException] if the [value] is false.
     */
    public static void check(boolean value) {
        check(value, new LazyValue<String>() {
            @NotNull
            @Override
            public String get() {
                return "Failed check.";
            }
        });
    }


    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    /* ******************************************* null *******************************************/


    /**
     * If the [value] is not null, it returns itself, otherwise it throws an IllegalArgumentException with the result of calling [lazyMessage]
     */
    @NotNull
    public static <T> T requireNotNull(@Nullable T value, @NotNull LazyValue<String> lazyMessage) {
        if (value != null) {
            return value;
        } else {
            throw new IllegalArgumentException(lazyMessage.get());
        }
    }

    /**
     * If the [value] is not null, it returns itself, otherwise it throws an IllegalArgumentException
     */
    @NotNull
    public static <T> T requireNotNull(@Nullable T value) {
        return requireNotNull(value, new LazyValue<String>() {
            @NotNull
            @Override
            public String get() {
                return "Required value was null.";
            }
        });
    }

    /**
     * If the [value] is not null, it returns itself, otherwise it throws an IllegalArgumentException
     */
    @NotNull
    public static <T> T requireNotNull(@Nullable T value, @NotNull String paramName) {
        if (value != null) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The parameter '%s'cannot be null", paramName));
        }
    }


    /**
     * If the [value] is not null, it returns itself, otherwise it throws an IllegalStateException with the result of calling [lazyMessage]
     */
    @NotNull
    public static <T> T checkNotNull(@Nullable T value, @NotNull LazyValue<String> lazyMessage) {
        if (value != null) {
            return value;
        } else {
            throw new IllegalStateException(lazyMessage.get());
        }
    }

    /**
     * If the [value] is not null, it returns itself, otherwise it throws an IllegalStateException
     */
    @NotNull
    public static <T> T checkNotNull(@Nullable T value) {
        return checkNotNull(value, new LazyValue<String>() {
            @NotNull
            @Override
            public String get() {
                return "Required value was null.";
            }
        });
    }

    /**
     * If the [value] is not null, it returns itself, otherwise it throws an IllegalStateException
     */
    @NotNull
    public static <T> T checkNotNull(@Nullable T value, @NotNull String paramName) {
        if (value != null) {
            return value;
        } else {
            throw new IllegalStateException(String.format("The parameter '%s'cannot be null", paramName));
        }
    }
}
