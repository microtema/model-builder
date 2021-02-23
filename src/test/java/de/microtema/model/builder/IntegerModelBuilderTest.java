package de.microtema.model.builder;

import de.microtema.model.builder.adapter.integer.IntegerRandomAdapter;
import de.microtema.model.builder.annotation.Inject;
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
import static org.junit.Assert.assertNotNull;

public class IntegerModelBuilderTest {

    @Inject
    IntegerRandomAdapter sut;

    @Model
    Integer min;

    @Model(type = ModelType.MAX)
    Integer max;

    @Models
    List<Integer> models;

    @Models(size = 5)
    List<Integer> modelsWithFixedSize;

    @Models
    Set<Integer> modelsAsSet;

    @Models(size = 3)
    Set<Integer> modelsAsSetWithFixedSize;

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
        assertEquals(3, modelsAsSetWithFixedSize.size());
        assertFalse(modelsAsSetWithFixedSize.isEmpty());
        models.forEach(Assert::assertNotNull);
    }
}
