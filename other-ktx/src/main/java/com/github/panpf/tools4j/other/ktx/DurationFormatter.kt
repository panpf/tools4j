@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4j.other.ktx

import com.github.panpf.tools4j.other.DurationFormatter
import com.github.panpf.tools4j.other.TotalTimeConfig


/* ******************************************* totalTime *******************************************/


/**
 * Returns the total time of formatting that can be displayed
 */
inline fun Long.formatTotalTime(config: TotalTimeConfig) = DurationFormatter.formatTotalTime(this, config)

/**
 * Returns the total time of formatting that can be displayed
 *
 * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
 */
inline fun Long.formatTotalTime(level: Int = 0, divider: String = " ",
                                daySuffix: String = "d", hourSuffix: String = "h", minuteSuffix: String = "m",
                                secondSuffix: String = "s", millisecondSuffix: String = "ms"): String {
    return DurationFormatter.formatTotalTime(this, level, divider, daySuffix, hourSuffix, minuteSuffix, secondSuffix, millisecondSuffix)
}

/**
 * Returns the total time of formatting that can be displayed
 *
 * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
 */
inline fun Long.formatTotalTimeZH(level: Int = 0) = DurationFormatter.formatTotalTimeZH(this, level)

/**
 * Returns the total time of formatting that can be displayed
 *
 * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
 */
inline fun Long.formatShortTotalTime(level: Int = 0) = DurationFormatter.formatShortTotalTime(this, level)

/**
 * Returns the total time of formatting that can be displayed
 *
 * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
 */
inline fun Long.formatShortTotalTimeZH(level: Int = 0) = DurationFormatter.formatShortTotalTimeZH(this, level)


/* ******************************************* duration *******************************************/


/**
 * Format the duration of the video or music, such as '01:23:22'
 */
inline fun Long.formatDurationTime() = DurationFormatter.formatDurationTime(this)

/**
 * Format the duration of the video or music, such as '01:23:22', '00:23:22' will return '23:22'
 */
inline fun Long.formatShortDurationTime() = DurationFormatter.formatShortDurationTime(this)