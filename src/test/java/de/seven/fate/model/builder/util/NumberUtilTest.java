package de.seven.fate.model.builder.util;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mario on 24.03.2016.
 */
public class NumberUtilTest {

    NumberUtil sut;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnSameMinAndMax() {

        NumberUtil.random(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnMinGreaterThanMax() {

        NumberUtil.random(1, 0);
    }


    @Test
    public void testRandom() {

        int random = NumberUtil.random(0, 100);
        assertTrue(random >= 0);
        assertTrue(random <= 100);
    }


    @Test
    public void testRandomInteger() {

        Set<Integer> integers = new HashSet<>();

        int count = 0;
        int maxCount = 5;

        while (count++ < maxCount) {
            integers.add(NumberUtil.randomInteger());
        }

        assertTrue(integers.size() > 1);
    }
}
