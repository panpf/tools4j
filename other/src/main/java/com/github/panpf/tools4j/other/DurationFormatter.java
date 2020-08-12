package com.github.panpf.tools4j.other;

import org.jetbrains.annotations.NotNull;

public class DurationFormatter {
    // todo 改成专门的格式化器，支持 "\d天 \h小时 \m分钟 \s秒 \ms毫秒"，或者 "\h:\m\s"

    /* ******************************************* totalTime *******************************************/


    private static final long ONE_DAY_MILLISECONDS = 1000 * 60 * 60 * 24;
    private static final long ONE_HOUR_MILLISECONDS = 1000 * 60 * 60;
    private static final long ONE_MINUTE_MILLISECONDS = 1000 * 60;
    private static final long ONE_SECOND_MILLISECONDS = 1000;

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String formatTotalTime(long totalTimeMillis, @NotNull TotalTimeConfig config) {
        long finalTotalTimeMillis = totalTimeMillis >= 0 ? totalTimeMillis : 0;
        int finalLevel = Math.max(config.getLevel(), 0);

        long day = finalLevel <= 4 ? finalTotalTimeMillis / ONE_DAY_MILLISECONDS : 0;
        long hour = finalLevel <= 3 ? finalTotalTimeMillis % (ONE_DAY_MILLISECONDS) / (ONE_HOUR_MILLISECONDS) : 0;
        long minute = finalLevel <= 2 ? finalTotalTimeMillis % (ONE_HOUR_MILLISECONDS) / (ONE_MINUTE_MILLISECONDS) : 0;
        long second = finalLevel <= 1 ? finalTotalTimeMillis % (ONE_MINUTE_MILLISECONDS) / ONE_SECOND_MILLISECONDS : 0;
        long millisecond = finalLevel <= 0 ? finalTotalTimeMillis % ONE_SECOND_MILLISECONDS : 0;

        StringBuilder builder = new StringBuilder();
        if (day > 0) {
            builder.append(day).append(config.getDaySuffix());
        }
        if (hour > 0) {
            if (builder.length() > 0) builder.append(config.getDivider());
            builder.append(hour).append(config.getHourSuffix());
        }
        if (minute > 0) {
            if (builder.length() > 0) builder.append(config.getDivider());
            builder.append(minute).append(config.getMinuteSuffix());
        }
        if (second > 0) {
            if (builder.length() > 0) builder.append(config.getDivider());
            builder.append(second).append(config.getSecondSuffix());
        }
        if (millisecond > 0) {
            if (builder.length() > 0) builder.append(config.getDivider());
            builder.append(millisecond).append(config.getMillisecondSuffix());
        }
        if (builder.length() == 0) {
            if (finalLevel <= 0) {
                builder.append(0).append(config.getSecondSuffix());
            } else if (finalLevel <= 1) {
                builder.append(0).append(config.getSecondSuffix());
            } else if (finalLevel <= 2) {
                builder.append(0).append(config.getMinuteSuffix());
            } else if (finalLevel <= 3) {
                builder.append(0).append(config.getHourSuffix());
            } else if (finalLevel <= 4) {
                builder.append(0).append(config.getDaySuffix());
            } else {
                builder.append(0).append(config.getDaySuffix());
            }
        }
        return builder.toString();
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String formatTotalTime(long totalTimeMillis, int level, @NotNull String divider,
                                         @NotNull String daySuffix, @NotNull String hourSuffix, @NotNull String minuteSuffix,
                                         @NotNull String secondSuffix, @NotNull String millisecondSuffix) {
        return formatTotalTime(totalTimeMillis, new TotalTimeConfig(level, divider, daySuffix, hourSuffix, minuteSuffix, secondSuffix, millisecondSuffix));
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String formatTotalTime(long totalTimeMillis, int level) {
        return formatTotalTime(totalTimeMillis, level, " ", "d", "h", "m", "s", "ms");
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String formatTotalTime(long totalTimeMillis) {
        return formatTotalTime(totalTimeMillis, 0);
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String formatTotalTimeZH(long totalTimeMillis, int level) {
        return formatTotalTime(totalTimeMillis, level, " ", "天", "小时", "分钟", "秒", "毫秒");
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String formatTotalTimeZH(long totalTimeMillis) {
        return formatTotalTimeZH(totalTimeMillis, 0);
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String formatShortTotalTime(long totalTimeMillis, int level) {
        return formatTotalTime(totalTimeMillis, level, "", "d", "h", "m", "s", "ms");
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String formatShortTotalTime(long totalTimeMillis) {
        return formatShortTotalTime(totalTimeMillis, 0);
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String formatShortTotalTimeZH(long totalTimeMillis, int level) {
        return formatTotalTime(totalTimeMillis, level, "", "天", "小时", "分钟", "秒", "毫秒");
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String formatShortTotalTimeZH(long totalTimeMillis) {
        return formatShortTotalTimeZH(totalTimeMillis, 0);
    }


    /* ******************************************* duration *******************************************/


    /**
     * Format the duration of the video or music, such as '01:23:22'
     *
     * @param duration Duration, in milliseconds
     * @param shorted  If true, '00:23:22' will return '23:22'
     */
    @NotNull
    private static String formatDurationTime(long duration, boolean shorted) {
        long secondsRemaining = duration / 1000 % 60;
        long minuteRemaining = duration / 1000 / 60 % 60;
        long hour = duration / 1000 / 60 / 60;

        StringBuilder builder = new StringBuilder();

        if (hour >= 10) {
            builder.append(hour);
        } else if (hour >= 1) {
            builder.append("0").append(hour);
        } else {
            if (!shorted) {
                builder.append("00");
            }
        }

        if (builder.length() > 0) {
            builder.append(":");
        }
        if (minuteRemaining >= 10) {
            builder.append(minuteRemaining);
        } else if (minuteRemaining >= 1) {
            builder.append("0").append(minuteRemaining);
        } else {
            builder.append("00");
        }

        if (builder.length() > 0) {
            builder.append(":");
        }
        if (secondsRemaining >= 10) {
            builder.append(secondsRemaining);
        } else if (secondsRemaining >= 1) {
            builder.append("0").append(secondsRemaining);
        } else {
            builder.append("00");
        }
        return builder.toString();
    }

    /**
     * Format the duration of the video or music, such as '01:23:22'
     *
     * @param duration Duration, in milliseconds
     */
    @NotNull
    public static String formatDurationTime(long duration) {
        return formatDurationTime(duration, false);
    }

    /**
     * Format the duration of the video or music, such as '01:23:22', '00:23:22' will return '23:22'
     *
     * @param duration Duration, in milliseconds
     */
    @NotNull
    public static String formatShortDurationTime(long duration) {
        return formatDurationTime(duration, true);
    }
}
