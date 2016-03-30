package de.seven.fate.model.person;

import de.seven.fate.model.address.Address;
import de.seven.fate.model.address.AddressBuilder;
import de.seven.fate.model.geo.GeoData;
import de.seven.fate.model.geo.GeoDataBuilder;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mario on 24.03.2016.
 */
public class AddressBuilderTest {

    PersonBuilder sut = new PersonBuilder(new AddressBuilder(new GeoDataBuilder()));

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
        assertTrue(person.getId() >= 1000);

        assertNotNull(person.getName());
        assertNotNull(person.getEmail());

        assertNotNull(person.getPhoneNumber());
        assertNotNull(person.getPosition());

        assertNotNull(person.getUpdateDate());
        assertNotNull(person.getAmount());

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

        Person person = sut.random();

        assertNotNull(person);
        assertNotNull(person.getName());
        assertNotNull(person.getEmail());
        assertNotNull(person.getPhoneNumber());

    }
}