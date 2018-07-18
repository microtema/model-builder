package de.seven.fate.model.builder.converter;

import de.seven.fate.model.builder.address.Address;
import de.seven.fate.model.builder.dto.PersonDTO;
import de.seven.fate.model.builder.geo.GeoData;
import de.seven.fate.model.builder.geo.GeoFormat;
import de.seven.fate.model.builder.person.Person;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class Person2PersonDTOConverterTest {

    static final Long ID = 123456L;
    static final String NAME = "Foo Bar";
    static final String PHONE_NUMBER = "123456789";
    static final int POSITION = 0;
    static final String EMAIL = "foo@bar.com";
    static final Date UPDATE_DATE = new Date();
    static final String STREET_NAME = "Wall St. Station";
    static final String STREET_NUMBER = "25";
    static final int ZIP_CODE = 10005;
    static final double LATITUDE = 1000D;
    static final double LONGITUDE = 2000D;
    static final GeoFormat WGS_64 = GeoFormat.WGS64;
    static final String STREET_NAME1 = "Route 66";
    static final String STREET_NUMBER1 = "66";
    static final int ZIP_CODE1 = 10005;
    static final double LATITUDE1 = 1100D;
    static final double LONGITUDE1 = 2100D;
    static final GeoFormat WGS_641 = GeoFormat.WGS64;
    static Date DOB;
    @InjectMocks
    Person2PersonDTOConverter sut;

    @Mock
    Address2AddressDTOConverter address2AddressDTOConverter;

    Person person;

    @Before
    public void setUp() throws Exception {

        DOB = DateUtils.parseDate("20/02/1971", "dd/MM/yyyy");

        person = new Person();

        person.setId(ID);
        person.setName(NAME);
        person.setPhoneNumber(PHONE_NUMBER);
        person.setPosition(POSITION);
        person.setEmail(EMAIL);
        person.setDob(DOB);
        person.setUpdateDate(UPDATE_DATE);

        List<Address> addresses = new ArrayList<>();

        Address address = new Address();
        address.setStreetName(STREET_NAME);
        address.setStreetNumber(STREET_NUMBER);
        address.setZipCode(ZIP_CODE);

        GeoData geoData = new GeoData();
        geoData.setLatitude(LATITUDE);
        geoData.setLongitude(LONGITUDE);
        geoData.setType(WGS_64);

        address.setGeoData(geoData);

        addresses.add(address);

        address = new Address();
        address.setStreetName(STREET_NAME1);
        address.setStreetNumber(STREET_NUMBER1);
        address.setZipCode(ZIP_CODE1);

        geoData = new GeoData();
        geoData.setLatitude(LATITUDE1);
        geoData.setLongitude(LONGITUDE1);
        geoData.setType(WGS_641);

        address.setGeoData(geoData);

        addresses.add(address);

        person.setAddresses(addresses);
    }

    @Test
    public void convert() {

        PersonDTO personDTO = sut.convert(person);

        assertNotNull(personDTO);

        assertEquals(person.getId(), personDTO.getId());
        assertEquals(person.getName(), personDTO.getName());
        assertEquals(person.getPhoneNumber(), personDTO.getPhoneNumber());
        assertEquals(person.getEmail(), personDTO.getEmail());
        assertEquals(person.getPosition(), personDTO.getPosition());
        assertEquals(person.getUpdateDate(), personDTO.getUpdateDate());
        assertEquals(person.getDob(), personDTO.getDob());

        verify(address2AddressDTOConverter).convertToList(person.getAddresses());
    }
}
