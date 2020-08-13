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

package com.github.panpf.tools4j.lang;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Number tool method
 */
public class Numberx {

    private Numberx() {
    }


    /* ******************************************* require *******************************************/


    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static byte requireNotZero(byte value, @NotNull String paramName) {
        if (value != (byte) 0) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The parameter '%s' cannot be 0", paramName));
        }
    }

    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static byte requireNotZero(byte value) {
        return requireNotZero(value, "unknown");
    }

    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static short requireNotZero(short value, @NotNull String paramName) {
        if (value != (short) 0) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The parameter '%s' cannot be 0", paramName));
        }
    }

    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static short requireNotZero(short value) {
        return requireNotZero(value, "unknown");
    }

    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static int requireNotZero(int value, @NotNull String paramName) {
        if (value != 0) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The parameter '%s' cannot be 0", paramName));
        }
    }

    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static int requireNotZero(int value) {
        return requireNotZero(value, "unknown");
    }

    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static long requireNotZero(long value, @NotNull String paramName) {
        if (value != 0L) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The parameter '%s' cannot be 0", paramName));
        }
    }

    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static long requireNotZero(long value) {
        return requireNotZero(value, "unknown");
    }

    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static float requireNotZero(float value, @NotNull String paramName) {
        if (value != 0f) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The parameter '%s' cannot be 0", paramName));
        }
    }

    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static float requireNotZero(float value) {
        return requireNotZero(value, "unknown");
    }

    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static double requireNotZero(double value, @NotNull String paramName) {
        if (value != 0.0) {
            return value;
        } else {
            throw new IllegalArgumentException(String.format("The parameter '%s' cannot be 0", paramName));
        }
    }

    /**
     * If [value] is not 0, it returns itself, otherwise it throws an exception.
     */
    public static double requireNotZero(double value) {
        return requireNotZero(value, "unknown");
    }


    /* ******************************************* pad *******************************************/


    /**
     * Converts the given number to a string of a given length. If the number of digits is not enough, it is added 0 in front.
     */
    @NotNull
    public static String pad(int digit, int stringLength) {
        return String.format("%0" + stringLength + "d", digit);
    }

    /**
     * Converts the given number to a string of a given length. If the number of digits is not enough, it is added 0 in front.
     */
    @NotNull
    public static String pad(long digit, int stringLength) {
        return String.format("%0" + stringLength + "d", digit);
    }


    /* ******************************************* orZero *******************************************/


    public static byte orZero(@Nullable Byte aByte) {
        return aByte != null ? aByte : 0;
    }

    public static short orZero(@Nullable Short aShort) {
        return aShort != null ? aShort : 0;
    }

    public static int orZero(@Nullable Integer integer) {
        return integer != null ? integer : 0;
    }

    public static long orZero(@Nullable Long aLong) {
        return aLong != null ? aLong : 0;
    }

    public static float orZero(@Nullable Float aFloat) {
        return aFloat != null ? aFloat : 0;
    }

    public static double orZero(@Nullable Double aDouble) {
        return aDouble != null ? aDouble : 0;
    }

    /*
     * *****************************************************************************************************************
     * From kotlin standard library
     * *****************************************************************************************************************
     */


    /**
     * Convert a String to an byte, returning a default value if the conversion fails.
     */
    public static byte toByteOrDefault(@Nullable String str, byte defaultValue) {
        if (Stringx.isNotSafe(str)) return defaultValue;
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Convert a String to a byte, returning 0 if the conversion fails.
     */
    public static byte toByteOrZero(@Nullable String str) {
        return toByteOrDefault(str, (byte) 0);
    }

    /**
     * Convert a String to an short, returning a default value if the conversion fails.
     */
    public static short toShortOrDefault(@Nullable String str, short defaultValue) {
        if (Stringx.isNotSafe(str)) return defaultValue;
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Convert a String to a short, returning 0 if the conversion fails.
     */
    public static short toShortOrZero(@Nullable String str) {
        return toShortOrDefault(str, (short) 0);
    }

    /**
     * Convert a String to an int, returning a default value if the conversion fails.
     */
    public static int toIntOrDefault(@Nullable String str, int defaultValue) {
        if (Stringx.isNotSafe(str)) return defaultValue;
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Convert a String to a int, returning 0 if the conversion fails.
     */
    public static int toIntOrZero(@Nullable String str) {
        return toIntOrDefault(str, 0);
    }

    /**
     * Convert a String to an long, returning a default value if the conversion fails.
     */
    public static long toLongOrDefault(@Nullable String str, long defaultValue) {
        if (Stringx.isNotSafe(str)) return defaultValue;
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Convert a String to a long, returning 0L if the conversion fails.
     */
    public static long toLongOrZero(@Nullable String str) {
        return toLongOrDefault(str, 0L);
    }

    /**
     * Convert a String to an float, returning a default value if the conversion fails.
     */
    public static float toFloatOrDefault(@Nullable String str, float defaultValue) {
        if (Stringx.isNotSafe(str)) return defaultValue;
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Convert a String to a float, returning 0.0f if the conversion fails.
     */
    public static float toFloatOrZero(@Nullable String str) {
        return toFloatOrDefault(str, 0.0f);
    }

    /**
     * Convert a String to an double, returning a default value if the conversion fails.
     */
    public static double toDoubleOrDefault(@Nullable String str, double defaultValue) {
        if (Stringx.isNotSafe(str)) return defaultValue;
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Convert a String to a double, returning 0.0d if the conversion fails.
     */
    public static double toDoubleOrZero(@Nullable String str) {
        return toDoubleOrDefault(str, 0.0d);
    }
}