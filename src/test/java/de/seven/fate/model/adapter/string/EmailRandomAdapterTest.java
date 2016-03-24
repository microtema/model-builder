package de.seven.fate.model.adapter.string;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mario on 24.03.2016.
 */
public class EmailRandomAdapterTest {

    EmailRandomAdapter sut = new EmailRandomAdapter();

    @Test
    public void randomValue() throws Exception {

        String randomEmail = sut.randomValue();

        assertNotNull(randomEmail);
        assertTrue(randomEmail.contains("@"));
        assertTrue(randomEmail.contains("."));
    }
}