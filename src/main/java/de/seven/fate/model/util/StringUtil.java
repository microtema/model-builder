package de.seven.fate.model.util;

/**
 * Created by Mario on 25.03.2016.
 */
public final class StringUtil {


    public static String leftPad(String str, int maxLength, char placeholder) {

        if (str == null) {
            throw new NullPointerException("str should not be null");
        }

        if (str.length() > maxLength) {
            return str.substring(0, maxLength);
        }

        String temp = str;

        while (temp.length() < maxLength) {
            temp = placeholder + temp;
        }

        return temp;
    }
}
