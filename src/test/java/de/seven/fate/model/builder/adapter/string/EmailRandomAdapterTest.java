package de.seven.fate.model.builder.adapter.string;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mario on 24.03.2016.
 */
public class EmailRandomAdapterTest {

    EmailPropertyRandomAdapter sut = new EmailPropertyRandomAdapter();

    @Test
    public void randomValue() {

        String randomEmail = sut.randomValue();

        assertNotNull(randomEmail);
        assertTrue(randomEmail.contains("@"));
        assertTrue(randomEmail.contains("."));
    }

    @Test
    public void getPropertyName() {
        Assert.assertEquals("email", sut.getPropertyName());
    }
}
