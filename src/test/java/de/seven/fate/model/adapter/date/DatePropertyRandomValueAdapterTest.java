package de.seven.fate.model.adapter.date;

import de.seven.fate.model.person.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by Mario on 25.03.2016.
 */
public class DatePropertyRandomValueAdapterTest {

    DatePropertyRandomValueAdapter sut = new DatePropertyRandomValueAdapter();

    @Test
    public void randomValue() throws Exception {

        assertNotNull(sut.randomValue("dob", null));
    }

    @Test
    public void randomValueByClassType() {

        assertNotNull(sut.randomValue("dob", Person.class));
    }

    @Test
    public void randomValueForEmail() {

        assertEquals(new HashSet<String>(Arrays.asList("dob")), sut.getAdapterNames());
    }
}