package de.seven.fate.model.person;

import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mario on 24.03.2016.
 */
public class PersonBuilderTest {

    PersonBuilder sut = new PersonBuilder();

    @Test
    public void testGenericType() throws Exception {

        assertSame(Person.class, sut.getGenericType());
    }

    @Test
    public void testList() throws Exception {

        Collection<Person> persons = sut.list();

        assertNotNull(persons);
        assertFalse(persons.isEmpty());

        for (Person person : persons) {
            assertNotNull(person.getName());
            assertNotNull(person.getEmail());
            assertNotNull(person.getPhoneNumber());
        }
    }

    @Test
    public void testSet() throws Exception {

        Collection<Person> persons = sut.set();

        assertNotNull(persons);
        assertFalse(persons.isEmpty());

        for (Person person : persons) {
            assertNotNull(person.getName());
            assertNotNull(person.getEmail());
            assertNotNull(person.getPhoneNumber());
        }
    }

    @Test
    public void testMin() throws Exception {

        Person person = sut.min();

        assertNotNull(person);
        assertNotNull(person.getName());
        assertNotNull(person.getEmail());
        assertNotNull(person.getPhoneNumber());

    }

    @Test
    public void testMax() throws Exception {

        Person person = sut.max();

        assertNotNull(person);
        assertNotNull(person.getName());
        assertNotNull(person.getEmail());
        assertNotNull(person.getPhoneNumber());

    }

    @Test
    public void testRandom() throws Exception {

        Person person = sut.random();

        assertNotNull(person);
        assertNotNull(person.getName());
        assertNotNull(person.getEmail());
        assertNotNull(person.getPhoneNumber());

    }
}