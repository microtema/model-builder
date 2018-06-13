package de.seven.fate.model.builder.address;

import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.geo.GeoData;
import de.seven.fate.model.builder.geo.GeoDataBuilder;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AddressBuilderTest {

    ModelBuilder<Address> sut = new AddressBuilder(new GeoDataBuilder());

    @Test
    public void min() {

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
