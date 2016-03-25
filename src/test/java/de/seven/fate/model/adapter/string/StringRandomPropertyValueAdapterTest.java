package de.seven.fate.model.adapter.string;

import de.seven.fate.model.person.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Mario on 24.03.2016.
 */
public class StringRandomPropertyValueAdapterTest {

    StringPropertyRandomValueAdapter sut = new StringPropertyRandomValueAdapter();

    @Test
    public void randomValue() {

        String randomName = sut.randomValue("name", null);

        assertNotNull(randomName);
    }

    @Test
    public void randomValueByClassType() {

        String randomName = sut.randomValue("name", Person.class);

        assertNotNull(randomName);
    }

    @Test
    public void randomValueForEmail() {

        assertEquals(new HashSet<String>(Arrays.asList("name", "email", "link", "phonenumber")), sut.getAdapterNames());
    }
}