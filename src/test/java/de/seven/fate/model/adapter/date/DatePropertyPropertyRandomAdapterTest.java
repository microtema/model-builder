package de.seven.fate.model.adapter.date;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by Mario on 25.03.2016.
 */
public class DatePropertyPropertyRandomAdapterTest {

    DateRandomAdapter sut = new DateRandomAdapter();

    @Test
    public void randomValue() throws Exception {

        assertNotNull(sut.randomValue("dob"));
    }

    @Test
    public void randomValueForEmail() {

        assertEquals(new HashSet<String>(Arrays.asList("dob")), sut.getAdapterNames());
    }
}