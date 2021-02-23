package de.microtema.model.builder.person;

import de.microtema.model.builder.address.Address;
import de.microtema.model.builder.annotation.Inject;
import de.microtema.model.builder.geo.GeoData;
import de.microtema.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class PersonBuilderTest {

    @Inject
    PersonBuilder sut;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void testGenericType() {

        assertSame(Person.class, sut.getGenericType());
    }

    @Test
    public void testList() {

        Collection<Person> persons = sut.list();

        assertNotNull(persons);
        assertFalse(persons.isEmpty());
    }

    @Test
    public void testSet() {

        Collection<Person> persons = sut.set();

        assertNotNull(persons);
        assertFalse(persons.isEmpty());
    }

    @Test
    public void testMin() {

        Person person = sut.min();

        assertNotNull(person);

        assertNotNull(person.getId());
        assertNotNull(person.getId());

        assertNotNull(person.getName());
        assertNotNull(person.getEmail());

        assertNotNull(person.getPhoneNumber());
        assertNotNull(person.getPosition());

        assertNull(person.getUpdateDate());
        assertNull(person.getAmount());

        List<Address> addresses = person.getAddresses();
        assertNotNull(addresses);

        for (Address address : addresses) {

            assertNotNull(address);

            assertNotNull(address.getStreetName());
            assertNotNull(address.getStreetNumber());
            assertNotNull(address.getZipCode());

            GeoData geoData = address.getGeoData();

            assertNotNull(geoData);

            assertNotNull(geoData.getLatitude());
            assertNotNull(geoData.getLongitude());
            assertNotNull(geoData.getType());
        }
    }

    @Test
    public void testMax() {

        Person person = sut.max();

        assertNotNull(person);
        assertNotNull(person.getName());
        assertNotNull(person.getEmail());
        assertNotNull(person.getPhoneNumber());
    }

    @Test
    public void testRandom() {

        Person person = sut.mix();

        assertNotNull(person);
        assertNotNull(person.getName());
        assertNotNull(person.getEmail());
        assertNotNull(person.getPhoneNumber());
    }

    @Test
    public void testFix() {

        Person person = sut.fix();

        assertNotNull(person);
        assertNotNull(person.getName());
        assertNotNull(person.getEmail());
        assertNotNull(person.getPhoneNumber());
    }
}
