package de.microtema.model.builder.fix;

import de.microtema.model.builder.address.Address;
import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.enums.ModelType;
import de.microtema.model.builder.geo.GeoData;
import de.microtema.model.builder.geo.GeoFormat;
import de.microtema.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import static de.microtema.model.builder.ModelBuilderFactory.fix;
import static org.junit.Assert.*;

public class ModelBuilderTest {

    @Model(type = ModelType.FIX)
    Address sut;

    @Model(type = ModelType.FIX)
    Address model;

    @Before
    public void setUp()  {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void shouldHaveFixedValues() {

        assertNotNull(sut);

        assertNull( sut.getDescription());
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
}
