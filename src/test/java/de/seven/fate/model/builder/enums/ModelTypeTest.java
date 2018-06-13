package de.seven.fate.model.builder.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModelTypeTest {

    ModelType sut;

    @Test
    public void length() {

        assertEquals(5, ModelType.values().length);
    }
}