package de.seven.fate.model.builder.adapter.date;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatePropertyPropertyRandomAdapterTest {

    AbstractTypeRandomAdapter<Date> sut = new DateRandomAdapter();

    @Test
    public void randomValue() {

        assertNotNull(sut.randomValue("dob"));
    }

    @Test
    public void randomValueForEmail() {

        assertEquals(new HashSet<>(Collections.singletonList("dob")), sut.getAdapterNames());
    }
}
