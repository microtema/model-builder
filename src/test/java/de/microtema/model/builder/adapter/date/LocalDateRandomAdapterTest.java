package de.microtema.model.builder.adapter.date;

import de.microtema.model.builder.annotation.Inject;
import de.microtema.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class LocalDateRandomAdapterTest {

    @Inject
    LocalDateRandomAdapter sut;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void randomValueDefault() {
        LocalDate dob = sut.randomValueDefault("dob");

        assertNotNull(dob);
    }

    @Test
    public void randomValue() {
        LocalDate dob = sut.randomValue("dob");

        assertNotNull(dob);
    }
}
