package de.microtema.model.builder.fix;

import de.microtema.model.builder.ModelBuilderFactory;
import de.microtema.model.builder.address.Address;
import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.annotation.Models;
import de.microtema.model.builder.enums.ModelType;
import de.microtema.model.builder.geo.GeoData;
import de.microtema.model.builder.geo.GeoFormat;
import de.microtema.model.builder.util.CollectionUtil;
import de.microtema.model.builder.util.FieldInjectionUtil;
import lombok.Value;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static de.microtema.model.builder.ModelBuilderFactory.fix;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class ModelBuilderTest {

    @Model(type = ModelType.FIX)
    Address sut;

    @Model(type = ModelType.FIX)
    Address model;

    @Model
    User user;

    @Model(resource = "address.json")
    Address fromResource;

    @Models(resource = "addresses.json")
    List<Address> collectionFromResource;

    @Models(resource = "addresses.json")
    Set<Address> setFromResource;

    @Models(resource = "addresses.json")
    List<Address> listFromResource;

    @Models(resource = "addresses.json")
    Address[] arrayFromResource;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void shouldHaveFixedValues() {

        assertNotNull(sut);

        assertNull(sut.getDescription());
        assertEquals("streetName", sut.getStreetName());
        assertEquals(new Integer(7), sut.getZipCode());
        assertEquals("streetNumber", sut.getStreetNumber());

        GeoData geoData = sut.getGeoData();

        assertNotNull("streetNumber", geoData);
        assertEquals(new Double(8.0), geoData.getLatitude());
        assertEquals(new Double(9.0), geoData.getLongitude());
        assertEquals(GeoFormat.LAMBERT, geoData.getType());
    }

    @Test
    public void shouldBeEqualsOnFactoryMethod() {

        assertEquals(sut, fix(Address.class));
    }

    @Test
    public void shouldBeEquals() {

        assertEquals(sut, model);
    }

    @Test
    public void shouldCreateOnStaticConstructor() {

        assertNotNull(user);
        assertNotNull(user.getName());
    }

    @Test
    public void shouldCreateInterface() {

        SuperUser min = ModelBuilderFactory.min(SuperUser.class);

        assertNotNull(min);
        assertNotNull(min.getName());
        assertEquals(min.getName(), min.getName()); // check cached value
    }

    @Test
    public void fromResource() {

        assertNotNull(fromResource);

        assertEquals(new Integer(76185), fromResource.getZipCode());

        GeoData geoData = fromResource.getGeoData();
        assertNotNull(geoData);
        assertEquals(GeoFormat.LAMBERT, geoData.getType());
    }

    @Test
    public void collectionFromResource() {

        assertNotNull(collectionFromResource);
        assertEquals(1, collectionFromResource.size());

        Address address = collectionFromResource.get(0);

        assertEquals(new Integer(76185), address.getZipCode());

        GeoData geoData = address.getGeoData();
        assertNotNull(geoData);
        assertEquals(GeoFormat.LAMBERT, geoData.getType());
    }

    @Test
    public void listFromResource() {

        assertNotNull(listFromResource);
        assertEquals(1, listFromResource.size());

        Address address = listFromResource.get(0);

        assertEquals(new Integer(76185), address.getZipCode());

        GeoData geoData = address.getGeoData();
        assertNotNull(geoData);
        assertEquals(GeoFormat.LAMBERT, geoData.getType());
    }

    @Test
    public void setFromResource() {

        assertNotNull(setFromResource);
        assertEquals(1, setFromResource.size());

        Address address = CollectionUtil.first(setFromResource);
        assertNotNull(address);

        assertEquals(new Integer(76185), address.getZipCode());

        GeoData geoData = address.getGeoData();
        assertNotNull(geoData);
        assertEquals(GeoFormat.LAMBERT, geoData.getType());
    }

    @Test
    public void arrayFromResource() {

        assertNotNull(arrayFromResource);
        assertEquals(1, arrayFromResource.length);

        Address address = arrayFromResource[0];

        assertEquals(new Integer(76185), address.getZipCode());

        GeoData geoData = address.getGeoData();
        assertNotNull(geoData);
        assertEquals(GeoFormat.LAMBERT, geoData.getType());
    }

    @Value(staticConstructor = "of")
    public static class User {

        String name;
    }

    public interface SuperUser {

        String getName();
    }
}
