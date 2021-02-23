package de.microtema.model.builder;

import de.microtema.model.builder.adapter.integer.IntegerRandomAdapter;
import de.microtema.model.builder.annotation.Inject;
import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.enums.ModelType;
import de.microtema.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IntModelBuilderTest {

    @Inject
    IntegerRandomAdapter sut;

    @Model
    int min;

    @Model(type = ModelType.MAX)
    int max;


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
