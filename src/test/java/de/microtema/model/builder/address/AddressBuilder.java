package de.microtema.model.builder.address;

import de.microtema.model.builder.AbstractModelBuilder;
import de.microtema.model.builder.geo.GeoDataBuilder;

public class AddressBuilder extends AbstractModelBuilder<Address> {

    private final GeoDataBuilder geoDataBuilder;

    public AddressBuilder(GeoDataBuilder geoDataBuilder) {
        this.geoDataBuilder = geoDataBuilder;
    }

    @Override
    public Address min() {

        Address min = super.min();

        min.setGeoData(geoDataBuilder.min());

        return min;
    }
}
