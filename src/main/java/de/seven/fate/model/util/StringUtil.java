package de.seven.fate.model.util;


public final class StringUtil {


    public static String leftPad(String str, int maxLength, char placeholder) {

        if (str == null) {
            throw new NullPointerException("str should not be null");
        }

        if (maxLength < str.length()) {
            throw new IllegalArgumentException("str length should not be greater than: " + maxLength);
        }

        String temp = str;

        while (temp.length() < maxLength) {
            temp = placeholder + temp;
        }

        return temp;
    }
}
