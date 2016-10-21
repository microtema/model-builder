package de.seven.fate.model.util;

import de.seven.fate.model.geo.GeoFormat;
import de.seven.fate.model.person.Person;
import org.junit.Assert;
import org.junit.Test;

public class ClassUtilTest {

    ClassUtil sut;

    @Test
    public void getGenericType() throws Exception {

    }

    @Test
    public void getActualTypeArgument() {

    }

    @Test
    public void createInstance() {

    }

    @Test
    public void getPropertyNames() {

    }

    @Test
    public void getPropertyType() {

    }

    @Test
    public void getAllFields() {

    }

    @Test
    public void stringISsNotComplexType() {
        Assert.assertFalse(ClassUtil.isComplexType(String.class));
    }

    @Test
    public void integerIsNotComplexType() {
        Assert.assertFalse(ClassUtil.isComplexType(Integer.class));
    }

    @Test
    public void personIsComplexType() {
        Assert.assertTrue(ClassUtil.isComplexType(Person.class));
    }

    @Test
    public void geoFormatIsComplexType() {
        Assert.assertTrue(ClassUtil.isComplexType(GeoFormat.class));
    }

}
