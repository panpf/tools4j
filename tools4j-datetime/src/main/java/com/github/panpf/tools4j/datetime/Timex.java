package com.github.panpf.tools4j.datetime;

import org.jetbrains.annotations.NotNull;

public class Timex {


    /* ******************************************* totalTime *******************************************/


    private static final long ONE_DAY_MILLISECONDS = 1000 * 60 * 60 * 24;
    private static final long ONE_HOUR_MILLISECONDS = 1000 * 60 * 60;
    private static final long ONE_MINUTE_MILLISECONDS = 1000 * 60;
    private static final long ONE_SECOND_MILLISECONDS = 1000;

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String totalTime(long totalTimeMillis, @NotNull TotalTimeConfig config) {
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
    public static String totalTime(long totalTimeMillis, int level, @NotNull String divider,
                                   @NotNull String daySuffix, @NotNull String hourSuffix, @NotNull String minuteSuffix,
                                   @NotNull String secondSuffix, @NotNull String millisecondSuffix) {
        return totalTime(totalTimeMillis, new TotalTimeConfig(level, divider, daySuffix, hourSuffix, minuteSuffix, secondSuffix, millisecondSuffix));
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String totalTime(long totalTimeMillis, int level) {
        return totalTime(totalTimeMillis, level, " ", "d", "h", "m", "s", "ms");
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String totalTime(long totalTimeMillis) {
        return totalTime(totalTimeMillis, 0);
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String totalTime(int totalTime, int level) {
        return totalTime((long) totalTime, level);
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String totalTime(int totalTime) {
        return totalTime((long) totalTime, 0);
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String totalTimeZH(long totalTimeMillis, int level) {
        return totalTime(totalTimeMillis, level, " ", "天", "小时", "分钟", "秒", "毫秒");
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String totalTimeZH(long totalTimeMillis) {
        return totalTimeZH(totalTimeMillis, 0);
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String totalTimeZH(int totalTime, int level) {
        return totalTimeZH((long) totalTime, level);
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String totalTimeZH(int totalTime) {
        return totalTimeZH((long) totalTime, 0);
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String shortTotalTime(long totalTimeMillis, int level) {
        return totalTime(totalTimeMillis, level, "", "d", "h", "m", "s", "ms");
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String shortTotalTime(long totalTimeMillis) {
        return shortTotalTime(totalTimeMillis, 0);
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String shortTotalTime(int totalTime, int level) {
        return shortTotalTime((long) totalTime, level);
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String shortTotalTime(int totalTime) {
        return shortTotalTime((long) totalTime, 0);
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String shortTotalTimeZH(long totalTimeMillis, int level) {
        return totalTime(totalTimeMillis, level, "", "天", "小时", "分钟", "秒", "毫秒");
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String shortTotalTimeZH(long totalTimeMillis) {
        return shortTotalTimeZH(totalTimeMillis, 0);
    }

    /**
     * Returns the total time of formatting that can be displayed
     *
     * @param level 0: Accurate to milliseconds; 1: Accurate to seconds; 2: Accurate to minute; 3: Accurate to hour; 4: Accurate to day; 5 or more always returns 0 seconds
     */
    @NotNull
    public static String shortTotalTimeZH(int totalTime, int level) {
        return shortTotalTimeZH((long) totalTime, level);
    }

    /**
     * Returns the total time of formatting that can be displayed
     */
    @NotNull
    public static String shortTotalTimeZH(int totalTime) {
        return shortTotalTimeZH((long) totalTime, 0);
    }


    /* ******************************************* duration *******************************************/


    /**
     * Format the duration of the video or music, such as '01:23:22'
     *
     * @param duration Duration, in milliseconds
     * @param shorted  If true, '00:23:22' will return '23:22'
     */
    private static String duration(long duration, boolean shorted) {
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
    public static String duration(long duration) {
        return duration(duration, false);
    }

    /**
     * Format the duration of the video or music, such as '01:23:22', '00:23:22' will return '23:22'
     *
     * @param duration Duration, in milliseconds
     */
    public static String shortDuration(long duration) {
        return duration(duration, true);
    }
}
