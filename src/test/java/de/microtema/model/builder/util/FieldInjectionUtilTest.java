package de.microtema.model.builder.util;

import de.microtema.model.builder.person.Person;
import de.microtema.model.builder.adapter.string.StringRandomAdapter;
import de.microtema.model.builder.annotation.Inject;
import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.annotation.Models;
import de.microtema.model.builder.enums.ModelType;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FieldInjectionUtilTest {

    FieldInjectionUtil sut;

    @Model
    String model;

    @Models
    List<String> models;

    @Inject
    StringRandomAdapter adapter;

    @Model(type = ModelType.RESOURCE, resource = "messages.properties")
    Properties properties;

    @Model(type = ModelType.RESOURCE, resource = "messages.xml")
    Properties xmlProperties;

    @Models(type = ModelType.MIN)
    List<Person> minPersons;

    @Models(type = ModelType.MAX)
    List<Person> maxPersons;

    @Test(expected = UnsupportedOperationException.class)
    public void utilityClassTest() throws Exception {

        Constructor<FieldInjectionUtil> constructor = FieldInjectionUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw (UnsupportedOperationException) e.getTargetException();
        }
    }

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void injectFieldsAnnotatedWithModel() {
        assertNotNull(model);
    }

    @Test
    public void injectFieldsAnnotatedWithModels() {

        assertNotNull(models);
        assertFalse(models.isEmpty());
        assertTrue(models.get(0) instanceof String);
    }

    @Test
    public void inject() {
        assertNotNull(adapter);
    }

    @Test
    public void properties() {

        assertNotNull(properties);

        assertFalse(properties.isEmpty());
        assertEquals("properties", properties.get("key"));
    }

    @Test
    public void xmlProperties() {

        assertNotNull(xmlProperties);

        assertFalse(xmlProperties.isEmpty());
        assertEquals("xml", xmlProperties.get("key"));
    }

    @Test
    public void minPersons() {

        assertNotNull(minPersons);

        Person person = CollectionUtil.random(minPersons);

        assertNull(person.getUpdateDate());
    }

    @Test
    public void maxPersons() {

        assertNotNull(maxPersons);

        Person person = CollectionUtil.random(maxPersons);

        assertNotNull(person.getUpdateDate());
    }
}
