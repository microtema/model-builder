package de.microtema.model.builder.address;

import de.microtema.model.builder.ModelBuilder;
import de.microtema.model.builder.geo.GeoDataBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddressBuilder implements ModelBuilder<Address> {

    private final GeoDataBuilder geoDataBuilder;

    @Override
    public Address min() {

        Address min = ModelBuilder.super.min();

        min.setGeoData(geoDataBuilder.min());

        return min;
    }
}
