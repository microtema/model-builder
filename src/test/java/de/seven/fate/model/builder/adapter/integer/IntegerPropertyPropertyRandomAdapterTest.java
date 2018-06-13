package de.seven.fate.model.builder.adapter.integer;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Mario on 25.03.2016.
 */
public class IntegerPropertyPropertyRandomAdapterTest {

    IntegerRandomAdapter sut = new IntegerRandomAdapter();

    @Test
    public void randomValue() {

        assertNotNull(sut.randomValue("position"));
    }


    @Test
    public void randomValueForEmail() {

        Assert.assertEquals(new HashSet<String>(Arrays.asList("position", "zipcode")), sut.getAdapterNames());
    }
}
