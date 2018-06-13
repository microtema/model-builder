package de.seven.fate.model.builder.address;

import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.model.builder.geo.GeoDataBuilder;

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
