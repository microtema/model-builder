package de.seven.fate.model.address;

import de.seven.fate.model.geo.GeoData;
import de.seven.fate.model.geo.GeoDataBuilder;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Mario on 26.03.2016.
 */
public class AddressBuilderTest {

    AddressBuilder sut = new AddressBuilder(new GeoDataBuilder());

    @Test
    public void min() throws Exception {
        Address address = sut.min();

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