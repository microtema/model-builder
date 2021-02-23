package de.microtema.model.builder.adapter.date;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;
import de.microtema.model.builder.util.ClassUtil;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatePropertyPropertyRandomAdapterTest {

    AbstractTypeRandomAdapter<Date> sut = ClassUtil.createInstance(DateRandomAdapter.class);

    @Test
    public void randomValue() {

        assertNotNull(sut.randomValue("dob"));
    }

    @Test
    public void randomValueForEmail() {

        assertEquals(new HashSet<>(Collections.singletonList("dob")), sut.getAdapterNames());
    }
}
