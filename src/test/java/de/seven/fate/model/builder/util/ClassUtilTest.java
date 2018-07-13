package de.seven.fate.model.builder.util;

import de.seven.fate.model.builder.geo.GeoFormat;
import de.seven.fate.model.builder.person.Person;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class ClassUtilTest {

    ClassUtil sut;

    List<String> list;

    Map<String, Integer> map;

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


    @Test(expected = IllegalArgumentException.class)
    public void createInstanceWillThrowIllegalArgumentException() {
        assertNotNull(ClassUtil.createInstance(ClassUtil.class));
    }

    @Test
    public void getConstructor() {
        assertNotNull(ClassUtil.getConstructor(Person.class));
    }


    @Test
    public void getGenericTypeFromCollection() throws Exception {

        Type genericType = getClass().getDeclaredField("list").getGenericType();

        Type parameterType = ClassUtil.getGenericType(genericType);

        assertSame(String.class, parameterType);
    }

    @Test
    public void getGenericTypeFromMap() throws Exception {

        Type genericType = getClass().getDeclaredField("map").getGenericType();

        Type parameterType = ClassUtil.getGenericType(genericType, 0);

        assertSame(String.class, parameterType);

        parameterType = ClassUtil.getGenericType(genericType, 1);

        assertSame(Integer.class, parameterType);
    }


    @Test
    public void findParameterTypes() {
        Class<?>[] parameterTypes = ClassUtil.findParameterTypes(Group.class);

        assertNotNull(parameterTypes);

        assertEquals(1, parameterTypes.length);
        assertSame(String.class, parameterTypes[0]);
    }

    @Test
    public void findParameterTypesWillReturnEmpty() {
        Class<?>[] parameterTypes = ClassUtil.findParameterTypes(Person.class);

        assertNotNull(parameterTypes);

        assertEquals(0, parameterTypes.length);
    }

    @Test
    public void createMapInstance() {

        assertNotNull(ClassUtil.createInstance(Map.class));
    }

    @Test
    public void createListInstance() {

        assertNotNull(ClassUtil.createInstance(List.class));
    }

    @Test
    public void createSetInstance() {

        assertNotNull(ClassUtil.createInstance(Set.class));
    }

    @Test
    public void createDateInstance() {

        assertNotNull(ClassUtil.createInstance(Date.class));
    }

    @Test
    public void createDefaultInstance() {

        Company instance = ClassUtil.createInstance(Company.class);

        assertNotNull(instance);
    }

    @Test
    public void createFallbackInstance() {

        Group instance = ClassUtil.createInstance(Group.class);

        assertNotNull(instance);
    }

    @Test
    public void getGenericTypeOnInterface() {

        Class genericType = ClassUtil.getGenericType(BarFoo.class);

        assertSame(Person.class, genericType);
    }

    public interface FooBar<T> {
    }

    public static class Group {
        public Group(String name) {

        }
    }

    public static class Company {

        public Company(String name, Long id, Integer employees) {
        }

        public Company(String name, Long id) {
        }

        public Company() {
        }
    }

    public static class BarFoo implements FooBar<Person> {
    }
}
