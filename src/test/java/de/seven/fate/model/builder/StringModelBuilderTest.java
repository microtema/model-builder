package de.seven.fate.model.builder;

import de.seven.fate.model.builder.adapter.string.StringRandomAdapter;
import de.seven.fate.model.builder.annotation.Inject;
import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.annotation.Models;
import de.seven.fate.model.builder.enums.ModelType;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
}
