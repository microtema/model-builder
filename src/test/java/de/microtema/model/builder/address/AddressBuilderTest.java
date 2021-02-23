package de.microtema.model.builder.address;

import de.microtema.model.builder.ModelBuilder;
import de.microtema.model.builder.geo.GeoData;
import de.microtema.model.builder.geo.GeoDataBuilder;
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
