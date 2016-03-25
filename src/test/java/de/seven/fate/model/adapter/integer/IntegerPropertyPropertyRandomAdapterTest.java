package de.seven.fate.model.adapter.integer;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by Mario on 25.03.2016.
 */
public class IntegerPropertyPropertyRandomAdapterTest {

    IntegerRandomAdapter sut = new IntegerRandomAdapter();

    @Test
    public void randomValue() throws Exception {

        assertNotNull(sut.randomValue("position"));
    }


    @Test
    public void randomValueForEmail() {

        assertEquals(new HashSet<String>(Arrays.asList("position")), sut.getAdapterNames());
    }
}