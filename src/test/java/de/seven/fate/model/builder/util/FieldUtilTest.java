package de.seven.fate.model.builder.util;

import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.person.Person;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FieldUtilTest {

    FieldUtil sut;

    @Model
    String str;

    @Model
    String value;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void utilityClassTest() throws Exception {

        Constructor<FieldUtil> constructor = FieldUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw (UnsupportedOperationException) e.getTargetException();
        }
    }

    @Test
    public void getPropertyFields() {

        List<Field> propertyFields = FieldUtil.getPropertyFields(Person.class);

        assertNotNull(propertyFields);

        assertEquals(10, propertyFields.size());
    }

    @Test
    public void isRequiredField() throws Exception {

        Field field = FieldUtilTest.class.getDeclaredField("sut");

        assertFalse(FieldUtil.isRequiredField(field));
    }

    @Test
    public void getFieldValue() throws Exception {

        Field field = FieldUtilTest.class.getDeclaredField("str");

        assertEquals(str, FieldUtil.getFieldValue(field, this));
    }

    @Test
    public void setFieldValue() throws Exception {

        Field field = FieldUtilTest.class.getDeclaredField("str");

        FieldUtil.setFieldValue(field, this, value);

        assertEquals(str, value);
    }

    @Test
    public void doWithFields() {

        List<Field> fields = new ArrayList<>();

        FieldUtil.doWithFields(getClass(), fields::add, it -> it.isAnnotationPresent(Model.class));

        assertEquals(2, fields.size());
    }

    @Test
    public void doWithFieldsWithNUllFilter() {

        List<Field> fields = new ArrayList<>();

        FieldUtil.doWithFields(getClass(), fields::add, null);

        assertTrue(fields.size() > 3);
    }
}
