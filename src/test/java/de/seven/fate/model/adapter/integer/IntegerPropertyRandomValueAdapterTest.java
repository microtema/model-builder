package de.seven.fate.model.adapter.integer;

import de.seven.fate.model.person.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by Mario on 25.03.2016.
 */
public class IntegerPropertyRandomValueAdapterTest {

    IntegerPropertyRandomValueAdapter sut = new IntegerPropertyRandomValueAdapter();

    @Test
    public void randomValue() throws Exception {

        assertNotNull(sut.randomValue("position", null));
    }

    @Test
    public void randomValueByClassType() {

        assertNotNull(sut.randomValue("position", Person.class));
    }

    @Test
    public void randomValueForEmail() {

        assertEquals(new HashSet<String>(Arrays.asList("position")), sut.getAdapterNames());
    }
}