package de.microtema.model.builder.adapter.longv;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Mario on 25.03.2016.
 */
public class LongPropertyPropertyRandomAdapterTest {

    LongRandomAdapter sut = new LongRandomAdapter();

    @Test
    public void randomValue() {

        assertNotNull(sut.randomValue("id"));
    }

    @Test
    public void randomValueForEmail() {

        Assert.assertEquals(new HashSet<String>(Arrays.asList("id")), sut.getAdapterNames());
    }
}
