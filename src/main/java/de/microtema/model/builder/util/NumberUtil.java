package de.microtema.model.builder.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.util.Random;

@UtilityClass
public final class NumberUtil {

    public static int random(int minSize, int maxSize) {

        if (minSize > maxSize) {
            throw new IllegalArgumentException("min should be less than max");
        }

        if (minSize == maxSize) {
            throw new IllegalArgumentException("min should not be equal to max");
        }

        Random rn = new SecureRandom();
        int n = maxSize - minSize + 1;
        int i = rn.nextInt(maxSize) % n;
        int randomNum = Math.max(minSize, i);

        randomNum = Math.min(maxSize, randomNum);

        return randomNum;
    }


    public static Integer randomInteger() {

        return random(0, Integer.MAX_VALUE);
    }

    public int getStringSum(String str) {

        if (StringUtils.isEmpty(str)) {
            return 0;
        }

        int sum = 0;

        for (char ch : str.toLowerCase().toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                sum += 1 + ch - 'a';
            }
        }

        return sum;
    }
}
