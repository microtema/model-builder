package de.seven.fate.model.builder;

import de.seven.fate.model.builder.adapter.integer.IntegerRandomAdapter;
import de.seven.fate.model.builder.annotation.Inject;
import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.enums.ModelType;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CharModelBuilderTest {

    @Inject
    IntegerRandomAdapter sut;

    @Model
    char min;

    @Model(type = ModelType.MAX)
    char max;


    @Before
    public void setUp() {

        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void min() {

        assertTrue(min != 0);
    }

    @Test
    public void max() {

        assertTrue(max != 0);
    }
}
