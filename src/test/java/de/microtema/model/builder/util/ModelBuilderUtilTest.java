package de.microtema.model.builder.util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ModelBuilderUtilTest {

    ModelBuilderUtil sut;

    @Test(expected = UnsupportedOperationException.class)
    public void utilityClassTest() throws Exception {

        Constructor<ModelBuilderUtil> constructor = ModelBuilderUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw (UnsupportedOperationException) e.getTargetException();
        }
    }

    @Test
    public void randomCollectionSize() {

        int randomCollectionSize = ModelBuilderUtil.randomCollectionSize();

        assertTrue(randomCollectionSize > 0);
        assertTrue(randomCollectionSize <= 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromPropertiesWIllThrow() {

        ModelBuilderUtil.fromProperties("foo.properties");
    }

    @Test
    public void fromProperties() {

        Properties properties = ModelBuilderUtil.fromProperties("messages.properties");

        assertNotNull(properties);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromXmlWIllThrow() {

        ModelBuilderUtil.fromProperties("foo.xml");
    }

    @Test
    public void fromXml() {

        Properties properties = ModelBuilderUtil.fromProperties("messages.xml");

        assertNotNull(properties);
    }

    @Test
    public void fromString() {

        String properties = ModelBuilderUtil.fromString("messages.xml");

        assertNotNull(properties);
    }

    @Test
    public void fromBinary() {

        byte[] properties = ModelBuilderUtil.fromBinary("messages.xml");

        assertNotNull(properties);
    }
}
