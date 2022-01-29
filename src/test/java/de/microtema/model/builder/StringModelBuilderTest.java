package de.microtema.model.builder;

import de.microtema.model.builder.adapter.string.StringRandomAdapter;
import de.microtema.model.builder.annotation.Inject;
import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.annotation.Models;
import de.microtema.model.builder.enums.ModelType;
import de.microtema.model.builder.util.FieldInjectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class StringModelBuilderTest {

    @Inject
    StringRandomAdapter sut;

    @Model
    String min;

    @Model(type = ModelType.MAX)
    String max;

    @Models
    List<String> models;

    @Models(size = 5)
    List<String> modelsWithFixedSize;

    @Models
    Set<String> modelsAsSet;

    @Models(size = 3)
    Set<String> modelsAsSetWithFixedSize;

    @Model(resource = "messages.xml")
    String xml;

    @Model(resource = "messages.xml")
    byte[] binary;

    @Before
    public void setUp() {

        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void min() {

        assertNotNull(min);
        assertFalse(StringUtils.isBlank(min));
    }

    @Test
    public void max() {

        assertNotNull(max);
        assertFalse(StringUtils.isBlank(max));
    }

    @Test
    public void list() {

        assertNotNull(models);
        assertFalse(models.isEmpty());
        models.forEach(Assert::assertNotNull);
    }

    @Test
    public void listWithFixedSize() {

        assertNotNull(modelsWithFixedSize);
        assertEquals(5, modelsWithFixedSize.size());
        assertFalse(modelsWithFixedSize.isEmpty());
        models.forEach(Assert::assertNotNull);
    }

    @Test
    public void set() {

        assertNotNull(modelsAsSet);
        assertFalse(modelsAsSet.isEmpty());
        models.forEach(Assert::assertNotNull);
    }

    @Test
    public void setWithFixedSize() {

        assertNotNull(modelsAsSetWithFixedSize);
        assertEquals(3, modelsAsSetWithFixedSize.size());
        assertFalse(modelsAsSetWithFixedSize.isEmpty());
        models.forEach(Assert::assertNotNull);
    }

    @Test
    public void xml() {

        assertNotNull(xml);

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">\n" +
                "<properties>\n" +
                "    <comment>Here are some favorites</comment>\n" +
                "    <entry key=\"key\">xml</entry>\n" +
                "</properties>", xml);
    }

    @Test
    public void binary() {

        assertNotNull(binary);

        assertEquals(xml, new String(binary));
    }
}
