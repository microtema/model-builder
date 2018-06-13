package de.seven.fate.model.builder.util;

import de.seven.fate.model.builder.adapter.string.StringRandomAdapter;
import de.seven.fate.model.builder.annotation.Inject;
import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.annotation.Models;
import de.seven.fate.model.builder.enums.ModelType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class FieldInjectionUtilTest {

    FieldInjectionUtil sut;

    @Model
    String model;

    @Models
    List<String> models;

    @Inject
    StringRandomAdapter adapter;

    @Model(type = ModelType.SOURCE, resource = "messages.properties")
    Properties properties;

    @Model(type = ModelType.SOURCE, resource = "messages.xml")
    Properties xmlProperties;

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
}