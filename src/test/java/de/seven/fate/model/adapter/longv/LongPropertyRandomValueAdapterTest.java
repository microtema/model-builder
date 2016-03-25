package de.seven.fate.model.adapter.longv;

import de.seven.fate.model.person.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by Mario on 25.03.2016.
 */
public class LongPropertyRandomValueAdapterTest {

    LongPropertyRandomValueAdapter sut = new LongPropertyRandomValueAdapter();

    @Test
    public void randomValue() throws Exception {

        assertNotNull(sut.randomValue("id", null));
    }

    @Test
    public void randomValueByClassType() {

        assertNotNull(sut.randomValue("id", Person.class));
    }

    @Test
    public void randomValueForEmail() {

        assertEquals(new HashSet<String>(Arrays.asList("id")), sut.getAdapterNames());
    }
}