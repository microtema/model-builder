package de.seven.fate.model.adapter.string;

import de.seven.fate.model.adapter.RandomValueAdapter;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mario on 25.03.2016.
 */
public class LinkRandomAdapterTest {

    LinkRandomAdapter sut = new LinkRandomAdapter();

    @Test
    public void randomValue(){

        String randomLink = sut.randomValue();

        assertNotNull(randomLink);
        assertTrue(randomLink.contains("://"));
        assertTrue(randomLink.contains("."));
    }

    @Test
    public void getPropertyName() {
        Assert.assertEquals("link", sut.getPropertyName());
    }
}