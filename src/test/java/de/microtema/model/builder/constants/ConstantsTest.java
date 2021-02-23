package de.microtema.model.builder.constants;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

public class ConstantsTest {

    Constants sut;

    @Test(expected = UnsupportedOperationException.class)
    public void utilityClassTest() throws Exception {

        Constructor<Constants> constructor = Constants.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw (UnsupportedOperationException) e.getTargetException();
        }
    }

    @Test
    public void MAY_NOT_BE_NULL() {

        assertEquals("person may not be null", String.format(Constants.MAY_NOT_BE_NULL, "person"));
    }

    @Test
    public void MAY_NOT_BE_EMPTY() {

        assertEquals("name may not be empty", String.format(Constants.MAY_NOT_BE_EMPTY, "name"));
    }
}