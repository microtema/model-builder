package de.microtema.model.builder;

import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.annotation.Models;
import de.microtema.model.builder.enums.ModelType;
import de.microtema.model.builder.util.FieldInjectionUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class BooleanModelBuilderTest {

    @Model
    boolean min;

    @Model(type = ModelType.MAX)
    boolean max;

    @Models
    List<Boolean> models;

    @Models
    Boolean[] arrays;

    @Models(size = 5)
    List<Boolean> modelsWithFixedSize;

    @Models
    Set<Boolean> modelsAsSet;

    @Models(size = 2)
    Set<Boolean> modelsAsSetWithFixedSize;

    @Before
    public void setUp() {

        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void min() {

        assertNotNull(min);
    }

    @Test
    public void max() {

        assertNotNull(max);
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
        assertFalse(modelsAsSetWithFixedSize.isEmpty());
        models.forEach(Assert::assertNotNull);
    }

    @Test
    public void arrays() {

        assertNotNull(arrays);
        assertNotEquals(0, arrays.length);
    }
}
