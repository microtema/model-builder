package de.seven.fate.model.builder.adapter.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Mario on 24.03.2016.
 */
public class StringRandomAdapterTest {

    StringRandomAdapter sut = new StringRandomAdapter();

    @Test
    public void randomValue() {

        String randomName = sut.randomValue("name");

        assertNotNull(randomName);
    }

    @Test
    public void randomValueForEmail() {

        Assert.assertEquals(new HashSet<>(Arrays.asList("name", "email", "link", "url", "phonenumber", "streetnumber", "streetname")), sut.getAdapterNames());
    }
}
