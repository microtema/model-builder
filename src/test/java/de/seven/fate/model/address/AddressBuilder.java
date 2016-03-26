package de.seven.fate.model.address;

import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.model.geo.GeoDataBuilder;

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