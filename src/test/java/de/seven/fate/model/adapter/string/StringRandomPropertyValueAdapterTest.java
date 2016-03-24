package de.seven.fate.model.adapter.string;

import de.seven.fate.model.person.Person;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mario on 24.03.2016.
 */
public class StringRandomPropertyValueAdapterTest {

    StringRandomPropertyValueAdapter sut = new StringRandomPropertyValueAdapter();

    @Test
    public void randomValue() throws Exception {

        String randomName = sut.randomValue("name", null);

        assertNotNull(randomName);
    }

    @Test
    public void randomValueByClassType() throws Exception {

        String randomName = sut.randomValue("name", Person.class);

        assertNotNull(randomName);
    }
}