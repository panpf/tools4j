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

package com.github.panpf.tools4j.math;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Mathx {

    private Mathx() {
    }


    /* ******************************************* divide *******************************************/


    /**
     * Divide two numbers, if the divisor is 0, it always return 0
     */
    public static float divide(byte divided, byte divisor) {
        return divisor != 0 ? (float) divided / (float) divisor : 0f;
    }

    /**
     * Divide two numbers, if the divisor is 0, it always return 0
     */
    public static float divide(short divided, short divisor) {
        return divisor != 0 ? (float) divided / (float) divisor : 0f;
    }

    /**
     * Divide two numbers, if the divisor is 0, it always return 0
     */
    public static float divide(int divided, int divisor) {
        return divisor != 0 ? (float) divided / (float) divisor : 0f;
    }

    /**
     * Divide two numbers, if the divisor is 0, it always return 0
     */
    public static double divide(long divided, long divisor) {
        return divisor != 0 ? (double) divided / (double) divisor : 0f;
    }

    /**
     * Divide two numbers, if the divisor is 0, it always return 0
     */
    public static float divide(float divided, float divisor) {
        return divisor != 0 ? divided / divisor : 0f;
    }

    /**
     * Divide two numbers, if the divisor is 0, it always return 0
     */
    public static double divide(double divided, double divisor) {
        return divisor != 0 ? divided / divisor : 0.0;
    }


    /**
     * Divide the two numbers and convert the result to int. If the divisor is 0, it always return 0.
     */
    public static int divideToInt(byte divided, byte divisor) {
        return (int) (divisor != 0 ? (float) divided / (float) divisor : 0f);
    }

    /**
     * Divide the two numbers and convert the result to int. If the divisor is 0, it always return 0.
     */
    public static int divideToInt(short divided, short divisor) {
        return (int) (divisor != 0 ? (float) divided / (float) divisor : 0f);
    }

    /**
     * Divide the two numbers and convert the result to int. If the divisor is 0, it always return 0.
     */
    public static int divideToInt(int divided, int divisor) {
        return (int) (divisor != 0 ? (float) divided / (float) divisor : 0f);
    }

    /**
     * Divide the two numbers and convert the result to int. If the divisor is 0, it always return 0.
     */
    public static int divideToInt(long divided, long divisor) {
        return (int) (divisor != 0 ? (double) divided / (double) divisor : 0f);
    }

    /**
     * Divide the two numbers and convert the result to int. If the divisor is 0, it always return 0.
     */
    public static int divideToInt(float divided, float divisor) {
        return (int) (divisor != 0 ? divided / divisor : 0f);
    }

    /**
     * Divide the two numbers and convert the result to int. If the divisor is 0, it always return 0.
     */
    public static int divideToInt(double divided, double divisor) {
        return (int) (divisor != 0 ? divided / divisor : 0f);
    }


    /**
     * Divide the two numbers and convert the result to long. If the divisor is 0, it always return 0.
     */
    public static long divideToLong(byte divided, byte divisor) {
        return (long) (divisor != 0 ? (float) divided / (float) divisor : 0f);
    }

    /**
     * Divide the two numbers and convert the result to long. If the divisor is 0, it always return 0.
     */
    public static long divideToLong(short divided, short divisor) {
        return (long) (divisor != 0 ? (float) divided / (float) divisor : 0f);
    }

    /**
     * Divide the two numbers and convert the result to long. If the divisor is 0, it always return 0.
     */
    public static long divideToLong(int divided, int divisor) {
        return (long) (divisor != 0 ? (float) divided / (float) divisor : 0f);
    }

    /**
     * Divide the two numbers and convert the result to long. If the divisor is 0, it always return 0.
     */
    public static long divideToLong(long divided, long divisor) {
        return (long) (divisor != 0 ? (double) divided / (double) divisor : 0f);
    }

    /**
     * Divide the two numbers and convert the result to long. If the divisor is 0, it always return 0.
     */
    public static long divideToLong(float divided, float divisor) {
        return (long) (divisor != 0 ? divided / divisor : 0f);
    }

    /**
     * Divide the two numbers and convert the result to long. If the divisor is 0, it always return 0.
     */
    public static long divideToLong(double divided, double divisor) {
        return (long) (divisor != 0 ? divided / divisor : 0f);
    }


    /* ******************************************* scale *******************************************/


    /**
     * Scale numbers in the specified mode
     *
     * @param newScale Number of digits after the decimal point
     */
    public static float scale(float number, int newScale, @NotNull RoundingMode roundingMode) {
        return new BigDecimal(number).setScale(newScale, roundingMode).floatValue();
    }

    /**
     * Scale numbers in the specified mode
     *
     * @param newScale Number of digits after the decimal point
     */
    public static float scale(float number, int newScale) {
        return new BigDecimal(number).setScale(newScale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * Scale numbers in the specified mode
     *
     * @param newScale Number of digits after the decimal point
     */
    public static double scale(double number, int newScale, @NotNull RoundingMode roundingMode) {
        return new BigDecimal(number).setScale(newScale, roundingMode).doubleValue();
    }

    /**
     * Scale numbers in the specified mode
     *
     * @param newScale Number of digits after the decimal point
     */
    public static double scale(double number, int newScale) {
        return new BigDecimal(number).setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /* ******************************************* proportion *******************************************/


    /**
     * Divide two numbers. If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(byte divided, byte divisor, int newScale) {
        return scale(divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f), newScale);
    }

    /**
     * Divide two numbers, If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(byte divided, byte divisor) {
        return scale(divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f), 2);
    }

    /**
     * Divide two numbers, If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(short divided, short divisor, int newScale) {
        return scale(divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f), newScale);
    }

    /**
     * Divide two numbers, If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(short divided, short divisor) {
        return scale(divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f), 2);
    }

    /**
     * Divide two numbers, If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(int divided, int divisor, int newScale) {
        return scale(divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f), newScale);
    }

    /**
     * Divide two numbers, If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(int divided, int divisor) {
        return scale(divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f), 2);
    }

    /**
     * Divide two numbers, If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(long divided, long divisor, int newScale) {
        return scale(divisor != 0 ? (float) ((double) divided / (double) divisor) : (divided != 0 ? 1f : 0f), newScale);
    }

    /**
     * Divide two numbers, If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(long divided, long divisor) {
        return scale(divisor != 0 ? (float) ((double) divided / (double) divisor) : (divided != 0 ? 1f : 0f), 2);
    }

    /**
     * Divide two numbers, If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(float divided, float divisor, int newScale) {
        return scale(divisor != 0 ? divided / divisor : (divided != 0 ? 1f : 0f), newScale);
    }

    /**
     * Divide two numbers, If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(float divided, float divisor) {
        return scale(divisor != 0 ? divided / divisor : (divided != 0 ? 1f : 0f), 2);
    }

    /**
     * Divide two numbers, If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(double divided, double divisor, int newScale) {
        return scale(divisor != 0 ? (float) (divided / divisor) : (divided != 0 ? 1f : 0f), newScale);
    }

    /**
     * Divide two numbers, If the divisor is 0, then [divided] is not equal to 0, then 1f is returned, otherwise 0f is returned.
     */
    public static float proportion(double divided, double divisor) {
        return scale(divisor != 0 ? (float) (divided / divisor) : (divided != 0 ? 1f : 0f), 2);
    }


    /* ******************************************* percent *******************************************/


    /**
     * Divide the two numbers, multiply the result by 100 and retain [newScale] decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(byte divided, byte divisor, int newScale) {
        return scale((divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f)) * 100, newScale);
    }

    /**
     * Divide the two numbers, multiply the result by 100 and retain two decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(byte divided, byte divisor) {
        return scale((divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f)) * 100, 2);
    }

    /**
     * Divide the two numbers, multiply the result by 100 and retain [newScale] decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(short divided, short divisor, int newScale) {
        return scale((divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f)) * 100, newScale);
    }

    /**
     * Divide the two numbers, multiply the result by 100 and retain two decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(short divided, short divisor) {
        return scale((divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f)) * 100, 2);
    }

    /**
     * Divide the two numbers, multiply the result by 100 and retain [newScale] decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(int divided, int divisor, int newScale) {
        return scale((divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f)) * 100, newScale);
    }

    /**
     * Divide the two numbers, multiply the result by 100 and retain two decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(int divided, int divisor) {
        return scale((divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f)) * 100, 2);
    }

    /**
     * Divide the two numbers, multiply the result by 100 and retain [newScale] decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(long divided, long divisor, int newScale) {
        return scale((divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f)) * 100, newScale);
    }

    /**
     * Divide the two numbers, multiply the result by 100 and retain two decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(long divided, long divisor) {
        return scale((divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f)) * 100, 2);
    }

    /**
     * Divide the two numbers, multiply the result by 100 and retain [newScale] decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(float divided, float divisor, int newScale) {
        return scale((divisor != 0 ? divided / divisor : (divided != 0 ? 1f : 0f)) * 100, newScale);
    }

    /**
     * Divide the two numbers, multiply the result by 100 and retain two decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(float divided, float divisor) {
        return scale((divisor != 0 ? divided / divisor : (divided != 0 ? 1f : 0f)) * 100, 2);
    }

    /**
     * Divide the two numbers, multiply the result by 100 and retain [newScale] decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(double divided, double divisor, int newScale) {
        return scale((divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f)) * 100, newScale);
    }

    /**
     * Divide the two numbers, multiply the result by 100 and retain two decimal places. If the divisor is 0, then [divided] is not equal to 0, then 100f is returned, otherwise 0f is returned.
     */
    public static float percent(double divided, double divisor) {
        return scale((divisor != 0 ? (float) divided / (float) divisor : (divided != 0 ? 1f : 0f)) * 100, 2);
    }

    /* ******************************************* Double and Float *******************************************/

    /**
     * Format a double number
     *
     * @param value                 Value
     * @param suffix                Suffix
     * @param decimalPlacesLength   Keep a few decimal places
     * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
     * @param appendSuffix          If true, append suffix and formatting results in an append
     */
    @NotNull
    public static String format(double value, @NotNull String suffix, int decimalPlacesLength, boolean decimalPlacesFillZero, boolean appendSuffix) {
        StringBuilder buffString = new StringBuilder();
        buffString.append("#");
        if (decimalPlacesLength > 0) {
            buffString.append(".");
            for (int w = 0; w < decimalPlacesLength; w++) {
                buffString.append(decimalPlacesFillZero ? "0" : "#");
            }
        }
        if (!appendSuffix) {
            buffString.append(suffix);
        }
        DecimalFormat format = new DecimalFormat(buffString.toString());
        format.setRoundingMode(RoundingMode.HALF_UP);
        if (!appendSuffix) {
            return format.format(value);
        } else {
            return format.format(value) + suffix;
        }
    }

    /**
     * Format a double number
     *
     * @param value                 Value
     * @param suffix                Suffix
     * @param decimalPlacesLength   Keep a few decimal places
     * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
     */
    @NotNull
    public static String format(double value, @NotNull String suffix, int decimalPlacesLength, boolean decimalPlacesFillZero) {
        return format(value, suffix, decimalPlacesLength, decimalPlacesFillZero, false);
    }

    /**
     * Format a float number
     *
     * @param value                 Value
     * @param suffix                Suffix
     * @param decimalPlacesLength   Keep a few decimal places
     * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
     */
    @NotNull
    public static String format(float value, @NotNull String suffix, int decimalPlacesLength, boolean decimalPlacesFillZero) {
        return format(value, suffix, decimalPlacesLength, decimalPlacesFillZero, false);
    }


    /* ******************************************* formatPercentWith *******************************************/


    /**
     * Calculate the percentage
     *
     * @param value1                value1
     * @param value2                value2
     * @param decimalPlacesLength   Keep a few decimal places
     * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
     */
    @NotNull
    public static String formatPercentWith(double value1, double value2, int decimalPlacesLength, boolean decimalPlacesFillZero) {
        return format(value2 != 0.0 ? value1 / value2 : 1.0, "%", decimalPlacesLength, decimalPlacesFillZero);
    }

    /**
     * Calculate the percentage
     *
     * @param value1              value1
     * @param value2              value2
     * @param decimalPlacesLength Keep a few decimal places
     */
    @NotNull
    public static String formatPercentWith(double value1, double value2, int decimalPlacesLength) {
        return formatPercentWith(value1, value2, decimalPlacesLength, false);
    }

    /**
     * Calculate the percentage, Leave two decimal places by default
     *
     * @param value1 value1
     * @param value2 value2
     */
    @NotNull
    public static String formatPercentWith(double value1, double value2) {
        return formatPercentWith(value1, value2, 2, false);
    }

    /**
     * Calculate the percentage
     *
     * @param value1                value1
     * @param value2                value2
     * @param decimalPlacesLength   Keep a few decimal places
     * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
     */
    @NotNull
    public static String formatPercentWith(float value1, float value2, int decimalPlacesLength, boolean decimalPlacesFillZero) {
        return format(value2 != 0f ? value1 / value2 : 1f, "%", decimalPlacesLength, decimalPlacesFillZero);
    }

    /**
     * Calculate the percentage
     *
     * @param value1              value1
     * @param value2              value2
     * @param decimalPlacesLength Keep a few decimal places
     */
    @NotNull
    public static String formatPercentWith(float value1, float value2, int decimalPlacesLength) {
        return formatPercentWith(value1, value2, decimalPlacesLength, false);
    }

    /**
     * Calculate the percentage, Leave two decimal places by default
     *
     * @param value1 value1
     * @param value2 value2
     */
    @NotNull
    public static String formatPercentWith(float value1, float value2) {
        return formatPercentWith(value1, value2, 2, false);
    }


    /**
     * Calculate the percentage
     *
     * @param value1                value1
     * @param value2                value2
     * @param decimalPlacesLength   Keep a few decimal places
     * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
     */
    @NotNull
    public static String formatPercentWith(int value1, int value2, int decimalPlacesLength, boolean decimalPlacesFillZero) {
        return formatPercentWith((double) value1, value2, decimalPlacesLength, decimalPlacesFillZero);
    }

    /**
     * Calculate the percentage
     *
     * @param value1              value1
     * @param value2              value2
     * @param decimalPlacesLength Keep a few decimal places
     */
    @NotNull
    public static String formatPercentWith(int value1, int value2, int decimalPlacesLength) {
        return formatPercentWith(value1, value2, decimalPlacesLength, false);
    }

    /**
     * Calculate the percentage, Leave two decimal places by default
     *
     * @param value1 value1
     * @param value2 value2
     */
    public static String formatPercentWith(int value1, int value2) {
        return formatPercentWith(value1, value2, 2, false);
    }


    /**
     * Calculate the percentage
     *
     * @param value1                value1
     * @param value2                value2
     * @param decimalPlacesLength   Keep a few decimal places
     * @param decimalPlacesFillZero Use 0 instead when the number of decimal places is insufficient
     */
    @NotNull
    public static String formatPercentWith(long value1, long value2, int decimalPlacesLength, boolean decimalPlacesFillZero) {
        return formatPercentWith((double) value1, (double) value2, decimalPlacesLength, decimalPlacesFillZero);
    }

    /**
     * Calculate the percentage
     *
     * @param value1              value1
     * @param value2              value2
     * @param decimalPlacesLength Keep a few decimal places
     */
    @NotNull
    public static String formatPercentWith(long value1, long value2, int decimalPlacesLength) {
        return formatPercentWith((double) value1, (double) value2, decimalPlacesLength, false);
    }

    /**
     * Calculate the percentage, Leave two decimal places by default
     *
     * @param value1 value1
     * @param value2 value2
     */
    @NotNull
    public static String formatPercentWith(long value1, long value2) {
        return formatPercentWith((double) value1, (double) value2, 2, false);
    }
}
