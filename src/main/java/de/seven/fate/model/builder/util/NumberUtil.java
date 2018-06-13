package de.seven.fate.model.builder.util;

import java.util.Random;

public final class NumberUtil {

    private NumberUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    public static int random(int minSize, int maxSize) {

        if (minSize > maxSize) {
            throw new IllegalArgumentException("min should be less than max");
        }

        if (minSize == maxSize) {
            throw new IllegalArgumentException("min should not be equal to max");
        }

        Random rn = new Random();
        int n = maxSize - minSize + 1;
        int i = rn.nextInt(maxSize) % n;
        int randomNum = Math.max(minSize, i);

        randomNum = Math.min(maxSize, randomNum);

        return randomNum;
    }


    public static Integer randomInteger() {

        return random(0, Integer.MAX_VALUE);
    }
}
