package de.seven.fate.model.adapter.date;

import de.seven.fate.model.adapter.AbstractTypeRandomAdapter;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatePropertyPropertyRandomAdapterTest {

    AbstractTypeRandomAdapter<Date> sut = new DateRandomAdapter();

    @Test
    public void randomValue() throws Exception {

        assertNotNull(sut.randomValue("dob"));
    }

    @Test
    public void randomValueForEmail() {

        assertEquals(new HashSet<>(Arrays.asList("dob")), sut.getAdapterNames());
    }
}
