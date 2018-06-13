package de.seven.fate.model.builder.util;

import de.seven.fate.model.builder.geo.GeoFormat;
import de.seven.fate.model.builder.person.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ClassUtilTest {

    ClassUtil sut;

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


    @Test
    public void isCollectionType() {
        Assert.assertTrue(ClassUtil.isCollectionType(List.class));
    }

}
