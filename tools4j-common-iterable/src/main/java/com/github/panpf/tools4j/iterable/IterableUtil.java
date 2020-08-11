package com.github.panpf.tools4j.iterable;

public class IterableUtil {

    public static int getProgressionLastElement(int start, int end, int step) {
        if (step > 0) {
            return end - differenceModulo(end, start, step);
        } else if (step < 0) {
            return end + differenceModulo(start, end, -step);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    public static long getProgressionLastElement(long start, long end, long step) {
        if (step > 0) {
            return end - differenceModulo(end, start, step);
        } else if (step < 0) {
            return end + differenceModulo(start, end, -step);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }


    private static int differenceModulo(int a, int b, int c) {
        return mod(mod(a, c) - mod(b, c), c);
    }

    private static long differenceModulo(long a, long b, long c) {
        return mod(mod(a, c) - mod(b, c), c);
    }

    private static int mod(int a, int b) {
        int mod = a % b;
        return mod >= 0 ? mod : mod + b;
    }

    private static long mod(long a, long b) {
        long mod = a % b;
        return mod >= 0L ? mod : mod + b;
    }
}
