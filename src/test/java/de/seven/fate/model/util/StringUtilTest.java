package de.seven.fate.model.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mario on 25.03.2016.
 */
public class StringUtilTest {

    StringUtil sut;

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException() {
        sut.leftPad(null, 7, '0');
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() {
        sut.leftPad("1234567", 5, ' ');
    }

    @Test
    public void leftPad() {
        assertEquals("0012345", sut.leftPad("12345", 7, '0'));
    }
}